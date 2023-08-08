package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Medico;

@SuppressWarnings("unchecked")
public interface IMedicoDAO extends CrudRepository<Medico, Long> {
    Medico save(Medico medico);

    Medico findById(long id);

    List<Medico> findAll();

    void deleteById(Long id);

    // Consulta personalizada para buscar médicos por especialidade
    @Query("SELECT m FROM Medico m WHERE m.especialidade = :especialidade")
    List<Medico> findByEspecialidade(@Param("especialidade") String especialidade);
}
