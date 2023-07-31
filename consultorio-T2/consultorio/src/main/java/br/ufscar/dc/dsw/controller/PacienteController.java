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
// import br.ufscar.dc.dsw.domain.Editora;
// import br.ufscar.dc.dsw.service.spec.IEditoraService;

import br.ufscar.dc.dsw.dao.IPacienteDAO;
import br.ufscar.dc.dsw.domain.Paciente;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    
    @Autowired
    private IPacienteDAO dao;

    @Autowired
	private BCryptPasswordEncoder encoder;

    @GetMapping("/cadastrar")
    public String cadastrar(Paciente paciente) {
        paciente.setRole("ROLE_PACIENTE");
        return "pacientes/cadastro";
    }

    @PostMapping("/salvar")
	public String salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr) {
		
   
        System.out.println("PACIENTE: " + paciente);
        //System.out.println(result);

        //paciente.setRole("ROLE_PACIENTE");

		if (result.hasErrors()) {
            
            System.out.println(result);
			return "pacientes/cadastro";
		}

		//System.out.println("password = " + paciente.getPassword());
		
		paciente.setPassword(encoder.encode(paciente.getPassword()));
		dao.save(paciente);
		attr.addFlashAttribute("sucess", "paciente.create.sucess");
		return "redirect:/pacientes/listar";
	}
}
