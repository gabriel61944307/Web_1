package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IMedicoDAO;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Service
@Transactional(readOnly = false)
public class MedicoService implements IMedicoService {
    
    @Autowired
	IMedicoDAO dao;

	public void salvar(Medico medico) {
		dao.save(medico);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Medico buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Medico> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Medico> buscarPorEspecialidade(String especialidade) {
		return dao.findByEspecialidade(especialidade);
	}

    // esse aqui tem que implementar quando a gente criar a consulta pra verificar se o medico tem uma consulta e não deletar ele se tiver (e tem que ter a mesma coisa pra médico)
    @Transactional(readOnly = true)
	public boolean medicoTemConsultas(Long id) {
		//return !dao.findById(id.longValue()).getLivros().isEmpty(); 
        //return true;
		return !dao.findById(id.longValue()).getConsultas().isEmpty();
	}
}
