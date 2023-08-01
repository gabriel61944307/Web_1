package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IPacienteDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IPacienteService;

@Service
@Transactional(readOnly = false)
public class PacienteService implements IPacienteService {
    
    @Autowired
	IPacienteDAO dao;

	public void salvar(Paciente paciente) {
		dao.save(paciente);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Paciente buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Paciente> buscarTodos() {
		return dao.findAll();
	}

    // esse aqui tem que implementar quando a gente criar a consulta pra verificar se o paciente tem uma consulta e não deletar ele se tiver (e tem que ter a mesma coisa pra médico)
    @Transactional(readOnly = true)
	public boolean pacienteTemConsultas(Long id) {
		//return !dao.findById(id.longValue()).getLivros().isEmpty(); 
        return true;
	}
}
