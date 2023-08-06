package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IPacienteService;
// import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Controller
@RequestMapping("/consultas-paciente")
public class ConsultaPacienteController {
    
    @Autowired
    private IConsultaService consultaService;

    @Autowired
    private IPacienteService pacienteService;

    // @Autowired
    // private IMedicoService medicoService;

    private Paciente getPacienteLogado() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //System.out.println(usuarioDetails.getUsuario().getId());

        Long idPaciente = usuarioDetails.getUsuario().getId();

        Paciente pacienteLogado = pacienteService.buscarPorId(idPaciente);

		//return usuarioDetails.getUsuario();
        
        return pacienteLogado;
	}

    @GetMapping("/cadastrar")
    public String cadastrar(Consulta consulta) {
        // consulta.setPaciente();
        //consulta.setPaciente(this.getUsuario());
        consulta.setPaciente(getPacienteLogado());

        return "consultas-paciente/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("consultas", consultaService.buscarTodasPorPaciente(getPacienteLogado()));

        return "consultas-paciente/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "consultas-paciente/cadastro";
        }

        //LocalDateTime dataHoraConsulta = dataConsulta.atTime(horaConsulta);

        //consulta.setDataHoraConsulta(dataHoraConsulta);

        consultaService.salvar(consulta);
        attr.addFlashAttribute("success", "consulta.create.success");
        return "redirect:/consultas-paciente/listar";
    }

    // @ModelAttribute("medicos")
    // public List<Medico> listaMedicos() {
    //     return medicoService.buscarTodos();
    // }
}
