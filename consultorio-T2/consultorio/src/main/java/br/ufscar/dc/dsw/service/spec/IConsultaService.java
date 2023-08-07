package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Medico;

public interface IConsultaService {
    
    Consulta buscarPorId(Long id);

    List<Consulta> buscarTodasPorPaciente(Paciente p);

    List<Consulta> buscarTodasPorMedico(Medico p);

    void salvar(Consulta consulta);

    void excluir(Long id);

    // edição e remoção?
}
