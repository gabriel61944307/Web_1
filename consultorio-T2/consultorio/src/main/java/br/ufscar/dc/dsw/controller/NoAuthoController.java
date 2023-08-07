package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Controller
@RequestMapping("/noAutho")
public class NoAuthoController {

	@Autowired
	private IMedicoService service;

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("medicos", service.buscarTodos());
		return "noAutho/listarMedicos";
	}

	@GetMapping("/listaresp")
	public String listarPorEspecialidade(@RequestParam(value = "especialidade", required = false) String especialidade,
			ModelMap model) {
		model.addAttribute("especialidade", especialidade);
		model.addAttribute("medicos", service.buscarPorEspecialidade(especialidade));
		return "noAutho/listarMedicosEsp";
	}

}
