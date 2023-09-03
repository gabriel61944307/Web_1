package br.ufscar.dc.dsw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;

@RestController
public class ConsultaRestController {
    
    @Autowired
    private IConsultaService service;

    @Autowired
    BCryptPasswordEncoder encoder;

   /* @GetMapping(path = "/medicos")
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
     }*/
	
    
    @GetMapping(path = "/consultas/{id}")
	public ResponseEntity<Consulta> lista(@PathVariable("id") long id) {
        Consulta consulta = service.buscarPorId(id);
		if (consulta == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(consulta);
	}

    /*@GetMapping(path = "/medicos/especialidades/{nome}")
	public ResponseEntity<List<Medico>> lista(@PathVariable("nome") String especialidade) {
        List<Medico> lista = service.buscarPorEspecialidade(especialidade);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}*/
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
/* 
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

    
    @PutMapping(path = "medicos/{id}")
    public ResponseEntity<Medico> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Medico medico = service.buscarPorId(id);
                if (medico == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    parse(medico, json);
                    service.salvar(medico);
                    return ResponseEntity.ok(medico);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }*/
}
