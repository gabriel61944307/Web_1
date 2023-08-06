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

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private IMedicoService service;

    @Autowired
	private BCryptPasswordEncoder encoder;

    @GetMapping("/cadastrar")
    public String cadastrar(Medico medico) {
        medico.setRole("ROLE_PACIENTE");
        return "medicos/cadastro";
    }

    @GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("medicos",service.buscarTodos());
		return "medicos/lista";
	}

    @PostMapping("/salvar")
	public String salvar(@Valid Medico medico, BindingResult result, RedirectAttributes attr) {
		
   
        //System.out.println("PACIENTE: " + medico);
        //System.out.println(result);

        //medico.setRole("ROLE_PACIENTE");

		if (result.hasErrors()) {
            
            System.out.println(result);
			return "medicos/cadastro";
		}

		//System.out.println("password = " + medico.getPassword());
		
		medico.setPassword(encoder.encode(medico.getPassword()));
		service.salvar(medico);
		attr.addFlashAttribute("success", "medico.create.success");
		return "redirect:/medicos/listar";
	}

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", service.buscarPorId(id));

        return "medicos/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Medico medico, String novoPassword, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "medicos/cadastro";
        }

        if (novoPassword != null && !novoPassword.trim().isEmpty()) {
            medico.setPassword(encoder.encode(novoPassword));
        } else {
			System.out.println("Senha não foi editada");
		}

        service.salvar(medico);
        attr.addFlashAttribute("success", "medico.edit.success");
        return "redirect:/medicos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        // if (service.editoraTemLivros(id)) {
		// 	model.addAttribute("fail", "editora.delete.fail");
		// } else {
		// 	service.excluir(id);
		// 	model.addAttribute("success", "editora.delete.success");
		// }
        if (service.medicoTemConsultas(id)) {
            model.addAttribute("fail", "medico.delete.fail");
        } else {
            service.excluir(id);
            model.addAttribute("success", "medico.delete.success");
        }
        
        return listar(model);
    }
}






