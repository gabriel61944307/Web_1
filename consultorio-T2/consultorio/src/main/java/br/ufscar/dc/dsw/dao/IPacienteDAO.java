package br.ufscar.dc.dsw.dao;

import java.util.List;

// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.query.Param;

// import br.ufscar.dc.dsw.domain.Livro;
import br.ufscar.dc.dsw.domain.Paciente;

@SuppressWarnings("unchecked")
public interface IPacienteDAO extends CrudRepository<Paciente, Long> {
    Paciente save(Paciente paciente);

    List<Paciente> findAll();

    Paciente findById(long id);

	void deleteById(Long id);
}
