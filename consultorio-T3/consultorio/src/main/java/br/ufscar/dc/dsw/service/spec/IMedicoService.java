package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Medico;

public interface IMedicoService {
    Medico buscarPorId(Long id);

	List<Medico> buscarTodos();

	void salvar(Medico editora);

	void excluir(Long id);

    boolean medicoTemConsultas(Long id);

	List<Medico> buscarPorEspecialidade(String especialidade);
}
