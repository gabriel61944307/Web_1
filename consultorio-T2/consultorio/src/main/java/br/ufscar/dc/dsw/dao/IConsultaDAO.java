package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Medico;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long> {
    
    Consulta findById(long id);

    List<Consulta> findAllByPaciente(Paciente p);

    List<Consulta> findAllByMedico(Medico p);

    Consulta save(Consulta consulta);

    // edição e remoção?
}
