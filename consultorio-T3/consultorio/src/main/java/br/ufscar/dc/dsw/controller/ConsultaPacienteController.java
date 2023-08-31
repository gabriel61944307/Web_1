package br.ufscar.dc.dsw.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IPacienteService;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.utils.EmailService;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.InternetAddress;

@Controller
@RequestMapping("/consultas-paciente")
public class ConsultaPacienteController {
    
    @Autowired
    private IConsultaService consultaService;

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IMedicoService medicoService;

    private Paciente getPacienteLogado() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long idPaciente = usuarioDetails.getUsuario().getId();
        Paciente pacienteLogado = pacienteService.buscarPorId(idPaciente);

        return pacienteLogado;
	}

    @GetMapping("/cadastrar")
    public String cadastrar(Consulta consulta, ModelMap model) {
        consulta.setPaciente(getPacienteLogado());

        model.addAttribute("medicos", medicoService.buscarTodos());

        return "consultas-paciente/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("consultas", consultaService.buscarTodasPorPaciente(getPacienteLogado()));

        return "consultas-paciente/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr, ModelMap model) throws UnsupportedEncodingException {

        if (result.hasErrors()) {
            model.addAttribute("medicos", medicoService.buscarTodos());
            return "consultas-paciente/cadastro";
        }

        // Verifica se o médico da consulta tem essa data/horário disponíveis
        List<Consulta> consultasMedico = consultaService.buscarTodasPorMedico(consulta.getMedico());

        for (Consulta consultaItem : consultasMedico) {
            if (consultaItem.getDataConsulta().equals(consulta.getDataConsulta())) {
                if (consultaItem.getHoraConsulta().equals(consulta.getHoraConsulta())) {
                    attr.addFlashAttribute("fail", "consulta.create.indisponivel");
                    return "redirect:/consultas-paciente/cadastrar";
                }
            }
        }

        // Verifica se o paciente da consulta tem essa data/horário disponíveis
        List<Consulta> consultasPaciente = consultaService.buscarTodasPorPaciente(consulta.getPaciente());
        
        for (Consulta consultaItem : consultasPaciente) {
            if (consultaItem.getDataConsulta().equals(consulta.getDataConsulta())) {
                if (consultaItem.getHoraConsulta().equals(consulta.getHoraConsulta())) {
                    attr.addFlashAttribute("fail", "consulta.create.indisponivel");
                    return "redirect:/consultas-paciente/cadastrar";
                }
            }
        }

        // Envio de E-mail
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = consulta.getDataConsulta().format(dateFormatter);

        EmailService emailService = new EmailService();
        InternetAddress from = new InternetAddress("gabrielrodriguesmalaquias1111@gmail.com", "Admin");

        Paciente pac = consulta.getPaciente();
        String nomePac = pac.getNome();
        String emailPac = pac.getEmail();
        
        InternetAddress to = new InternetAddress(emailPac, nomePac);
        String subject = "Sua consulta foi agendada com sucesso!";
        String body = "Olá, " + nomePac + ", este e-mail tem como objetivo lhe informar que sua consulta foi marcada com sucesso para " + formattedDate + " às " + consulta.getHoraConsulta() + ".\n\nAtenciosamente, \nAdministrador do sistema do hospital.";

        emailService.send(from, to, subject, body);

        Medico med = consulta.getMedico();
        String nomeMed = med.getNome();
        String emailMed = med.getEmail();

        to = new InternetAddress(emailMed, nomeMed);
        subject = "Você possui uma nova consulta agendada";
        body = "Olá, " + nomeMed + ", este e-mail tem como objetivo lhe informar que foi marcada uma consulta com você no dia " + formattedDate + " às " + consulta.getHoraConsulta() + ".\n\nAtenciosamente, \nAdministrador do sistema do hospital.";

        emailService.send(from, to, subject, body);

        consultaService.salvar(consulta);
        attr.addFlashAttribute("success", "consulta.create.success");
        return "redirect:/consultas-paciente/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consulta", consultaService.buscarPorId(id));
        model.addAttribute("medicos", medicoService.buscarTodos());
        
        return "consultas-paciente/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr, ModelMap model) {
        
        if (result.hasErrors()) {
            model.addAttribute("medicos", medicoService.buscarTodos());
            return "consultas-paciente/cadastro";
        }

        // Verifica se o médico da consulta tem essa data/horário disponíveis
        List<Consulta> consultasMedico = consultaService.buscarTodasPorMedico(consulta.getMedico());

        for (Consulta consultaItem : consultasMedico) {
            if (consultaItem.getDataConsulta().equals(consulta.getDataConsulta())) {
                if (consultaItem.getHoraConsulta().equals(consulta.getHoraConsulta())) {
                    attr.addFlashAttribute("fail", "consulta.create.indisponivel");
                    attr.addFlashAttribute("consulta", consultaService.buscarPorId(consulta.getId()));

                    return "redirect:/consultas-paciente/cadastrar";
                }
            }
        }

        // Verifica se o paciente da consulta tem essa data/horário disponíveis
        List<Consulta> consultasPaciente = consultaService.buscarTodasPorPaciente(consulta.getPaciente());
        
        for (Consulta consultaItem : consultasPaciente) {
            if (consultaItem.getDataConsulta().equals(consulta.getDataConsulta())) {
                if (consultaItem.getHoraConsulta().equals(consulta.getHoraConsulta())) {
                    attr.addFlashAttribute("fail", "consulta.create.indisponivel");
                    attr.addFlashAttribute("consulta", consultaService.buscarPorId(consulta.getId()));

                    return "redirect:/consultas-paciente/cadastrar";
                }
            }
        }

        consultaService.salvar(consulta);
        attr.addFlashAttribute("success", "consulta.create.success");
        return "redirect:/consultas-paciente/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {

        consultaService.excluir(id);
		model.addAttribute("success", "consulta.delete.success");
        
        return listar(model);
    }

    @ModelAttribute("horarios")
    public List<LocalTime> listaHorarios() {
        List<LocalTime> listaHorario = new ArrayList<>();
        
        LocalTime horarioInicial = LocalTime.of(8, 0);
        LocalTime horarioFinal = LocalTime.of(19, 0);
        
        while (horarioInicial.isBefore(horarioFinal)) {
            listaHorario.add(horarioInicial);
            horarioInicial = horarioInicial.plusMinutes(30);
        }

        return listaHorario;
    }
}
