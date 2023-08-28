package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}