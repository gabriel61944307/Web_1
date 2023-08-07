package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Especialidade;


@SuppressWarnings("unchecked")
public interface IEspecialidadeDAO extends CrudRepository<Especialidade, Long> {
    List<Especialidade> findAll();
    Especialidade save(Especialidade especialidade);
}
