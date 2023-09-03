package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Local;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.service.spec.IPacienteService;

@RestController
public class MedicoRestController {
    
    @Autowired
    private IMedicoService service;

    @Autowired
    BCryptPasswordEncoder encoder;

    private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Paciente paciente, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				paciente.setId(((Integer) id).longValue());
			} else {
				paciente.setId((Long) id);
			}
		}

        paciente.setNome((String) json.get("nome"));
        paciente.setPassword(encoder.encode((String) json.get("password")));
        paciente.setEmail((String) json.get("email"));
        paciente.setCPF((String) json.get("CPF"));
        paciente.setTelefone((String) json.get("telefone"));
        paciente.setSexo((String) json.get("sexo"));

        String dataNascimentoStr = (String) json.get("dataNascimento");
        if (dataNascimentoStr != null) {
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);
            paciente.setDataNascimento(dataNascimento);
        }

        Object consultasObject = json.get("consultas");
        if (consultasObject instanceof List) {
            List<Consulta> consultas = new ObjectMapper().convertValue(consultasObject, new TypeReference<List<Consulta>>() {
            });
            paciente.setConsultas(consultas);
        }
	}

	private void parse(Medico medico, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				medico.setId(((Integer) id).longValue());
			} else {
				medico.setId((Long) id);
			}
		}

        medico.setNome((String) json.get("nome"));
        medico.setPassword(encoder.encode((String) json.get("password")));
        medico.setEmail((String) json.get("email"));
        medico.setCRM((String) json.get("crm"));
        medico.setRole((String) json.get("role"));
        medico.setEspecialidade((String) json.get("especialidade"));
        medico.setEnabled((Boolean) json.get("enabled"));

	}

    @GetMapping(path = "/medicos")
	public ResponseEntity<List<Medico>> lista() {
		List<Medico> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 	}

     @DeleteMapping(path = "medicos/{id}")
     public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
         Medico medico = service.buscarPorId(id);
         if (medico == null) {
             return ResponseEntity.notFound().build();
         } else {
             if (service.medicoTemConsultas(id)) {
                 return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
             } else {
                 service.excluir(id);
                 return new ResponseEntity<Boolean>(true, HttpStatus.OK);
             }
         }
     }
	
    
    @GetMapping(path = "/medicos/{id}")
	public ResponseEntity<Medico> lista(@PathVariable("id") long id) {
        Medico medico = service.buscarPorId(id);
		if (medico == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(medico);
	}

    @GetMapping(path = "/medicos/especialidades/{nome}")
	public ResponseEntity<List<Medico>> lista(@PathVariable("nome") String especialidade) {
        List<Medico> lista = service.buscarPorEspecialidade(especialidade);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

/*

{
    "nome": "Médico Teste",
    "password": "$2a$10$OLbuLZEL.YIUHSt26UDXKu3CLdffEM/xOGxPxAqy2lmSJeJri5PK.",
    "email": "medico@email.com",
    "role": "ROLE_MEDICO",
    "enabled": true,
    "especialidade": "Cardiologia",
    "crm": "12.335/SP"
}

 */

    @PostMapping(path = "/medicos")
	@ResponseBody
	public ResponseEntity<Medico> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Medico medico = new Medico();
				parse(medico, json);
				service.salvar(medico);
				return ResponseEntity.ok(medico);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 	}

    /*
	@PostMapping(path = "/pacientes")
	@ResponseBody
	public ResponseEntity<Paciente> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Paciente paciente = new Paciente();
				parse(paciente, json);
				service.salvar(paciente);
				return ResponseEntity.ok(paciente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 	}

    @PutMapping(path = "pacientes/{id}")
    public ResponseEntity<Paciente> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Paciente paciente = service.buscarPorId(id);
                if (paciente == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    parse(paciente, json);
                    service.salvar(paciente);
                    return ResponseEntity.ok(paciente);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @DeleteMapping(path = "pacientes/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
        Paciente paciente = service.buscarPorId(id);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        } else {
            if (service.pacienteTemConsultas(id)) {
                return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
            } else {
                service.excluir(id);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            }
        }
    }*/
}
