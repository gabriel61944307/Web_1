package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.service.spec.IPacienteService;
import br.ufscar.dc.dsw.domain.Paciente;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    
    @Autowired
    private IPacienteService service;

    @Autowired
	private BCryptPasswordEncoder encoder;

    @GetMapping("/cadastrar")
    public String cadastrar(Paciente paciente) {
        paciente.setRole("ROLE_PACIENTE");
        return "pacientes/cadastro";
    }

    @GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("pacientes",service.buscarTodos());
		return "pacientes/lista";
	}

    @PostMapping("/salvar")
	public String salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "pacientes/cadastro";
		}
		
		paciente.setPassword(encoder.encode(paciente.getPassword()));
		service.salvar(paciente);
		attr.addFlashAttribute("success", "paciente.create.success");
		return "redirect:/pacientes/listar";
	}

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", service.buscarPorId(id));

        return "pacientes/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Paciente paciente, String novoPassword, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "pacientes/cadastro";
        }

        if (novoPassword != null && !novoPassword.trim().isEmpty()) {
            paciente.setPassword(encoder.encode(novoPassword));
        } else {
			System.out.println("Senha não foi editada");
		}

        service.salvar(paciente);
        attr.addFlashAttribute("success", "paciente.edit.success");
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        if (service.pacienteTemConsultas(id)) {
            model.addAttribute("fail", "paciente.delete.fail");
        } else {
            service.excluir(id);
            model.addAttribute("success", "paciente.delete.success");
        }
        
        return listar(model);
    }
}
