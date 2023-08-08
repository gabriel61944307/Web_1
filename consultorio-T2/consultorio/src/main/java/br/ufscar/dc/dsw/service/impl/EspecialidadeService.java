package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IEspecialidadeDAO;
import br.ufscar.dc.dsw.domain.Especialidade;
import br.ufscar.dc.dsw.service.spec.IEspecialidadeService;

@Service
@Transactional(readOnly = false)
public class EspecialidadeService implements IEspecialidadeService{
    @Autowired
	IEspecialidadeDAO dao;

    @Transactional(readOnly = true)
	public List<Especialidade> buscarTodos() {
		return dao.findAll();
	}
}
