package br.ufscar.dc.dsw.service.spec;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.domain.Especialidade;

public interface IEspecialidadeService {
    List<Especialidade> buscarTodos();
}

