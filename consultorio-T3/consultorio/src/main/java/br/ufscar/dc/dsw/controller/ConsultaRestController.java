package br.ufscar.dc.dsw.controller;


import java.util.List;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.service.spec.IPacienteService;

@RestController
public class ConsultaRestController {
    
    @Autowired
    private IConsultaService service;
    
    @Autowired
    private IPacienteService servicePaciente;

    @Autowired
    private IMedicoService serviceMedico;


    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping(path = "/consultas")
	public ResponseEntity<List<Consulta>> lista() {

		List<Paciente> listaPaciente = servicePaciente.buscarTodos();
        List<Consulta> lista = new ArrayList<>();
        for(Paciente p: listaPaciente){
            for(Consulta c: service.buscarTodasPorPaciente(p)){
                lista.add(c);
            }
        }
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 	}
    
    @GetMapping(path = "/consultas/{id}")
	public ResponseEntity<Consulta> lista(@PathVariable("id") long id) {
        Consulta consulta = service.buscarPorId(id);
		if (consulta == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(consulta);
	}

    @GetMapping(path = "/consultas/pacientes/{id}")
	public ResponseEntity<List<Consulta>> listaPacientes(@PathVariable("id") long id) {
        Paciente paciente = servicePaciente.buscarPorId(id);
        List<Consulta> consulta = service.buscarTodasPorPaciente(paciente);
		if (consulta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(consulta);
	}

    @GetMapping(path = "/consultas/medicos/{id}")
	public ResponseEntity<List<Consulta>> listaMedicos(@PathVariable("id") long id) {
        Medico medico = serviceMedico.buscarPorId(id);
        List<Consulta> consulta = service.buscarTodasPorMedico(medico);
		if (consulta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(consulta);
	}
}
