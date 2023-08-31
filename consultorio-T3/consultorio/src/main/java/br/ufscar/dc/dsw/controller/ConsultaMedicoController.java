package br.ufscar.dc.dsw.controller;

import java.time.format.DateTimeFormatter;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.utils.EmailService;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/consultas-medico")
public class ConsultaMedicoController {
     @Autowired
    private IConsultaService consultaService;

    @Autowired
    private IMedicoService medicoService;

    private Medico getMedicoLogado() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long idMedico = usuarioDetails.getUsuario().getId();
        Medico medicoLogado = medicoService.buscarPorId(idMedico);
        
        return medicoLogado;
	}

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("consultas", consultaService.buscarTodasPorMedico(getMedicoLogado()));

        return "consultas-medico/lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) throws UnsupportedEncodingException {

        Consulta consulta = consultaService.buscarPorId(id);

        // Envio de E-mail
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = consulta.getDataConsulta().format(dateFormatter);

        EmailService emailService = new EmailService();
        InternetAddress from = new InternetAddress("gabrielrodriguesmalaquias1111@gmail.com", "Admin");

        Paciente pac = consulta.getPaciente();
        String nomePac = pac.getNome();
        String emailPac = pac.getEmail();
        
        InternetAddress to = new InternetAddress(emailPac, nomePac);
        String subject = "Sua consulta foi cancelada!";
        String body = "Olá, " + nomePac + ", este e-mail tem como objetivo lhe informar que sua consulta com " + consulta.getMedico().getNome() + " no dia " + formattedDate + " às " + consulta.getHoraConsulta()  + " foi cancelada. Por favor, acesse novamente o sistema e marque uma nova consulta.\n\nAtenciosamente, \nAdministrador do sistema do hospital.";

        emailService.send(from, to, subject, body);

        Medico med = consulta.getMedico();
        String nomeMed = med.getNome();
        String emailMed = med.getEmail();

        to = new InternetAddress(emailMed, nomeMed);
        subject = "Uma de suas consultas foi cancelada com sucesso";
        body = "Olá, " + nomeMed + ", este e-mail tem como objetivo lhe informar que sua consulta com " + consulta.getPaciente().getNome() + " no dia " + formattedDate + " às " + consulta.getHoraConsulta() + " foi cancelada com sucesso.\n\nAtenciosamente, \nAdministrador do sistema do hospital.";

        emailService.send(from, to, subject, body);

        consultaService.excluir(id);
		model.addAttribute("success", "consulta.delete.success");
        
        return listar(model);
    }
}