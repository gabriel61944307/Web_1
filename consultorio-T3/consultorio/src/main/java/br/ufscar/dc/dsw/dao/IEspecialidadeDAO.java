package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Especialidade;


@SuppressWarnings("unchecked")
public interface IEspecialidadeDAO extends CrudRepository<Especialidade, Long> {
    List<Especialidade> findAll();
    Especialidade save(Especialidade especialidade);
}
