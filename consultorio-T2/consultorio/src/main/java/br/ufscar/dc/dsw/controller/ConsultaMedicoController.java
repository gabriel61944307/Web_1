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
import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Controller
@RequestMapping("/consultas-medico")
public class ConsultaMedicoController {
     @Autowired
    private IConsultaService consultaService;

    @Autowired
    private IMedicoService medicoService;

    // @Autowired
    // private IMedicoService medicoService;

    private Medico getMedicoLogado() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //System.out.println(usuarioDetails.getUsuario().getId());

        Long idMedico = usuarioDetails.getUsuario().getId();

        Medico medicoLogado = medicoService.buscarPorId(idMedico);

		//return usuarioDetails.getUsuario();
        
        return medicoLogado;
	}

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("consultas", consultaService.buscarTodasPorMedico(getMedicoLogado()));

        return "consultas-medico/lista";
    }

}