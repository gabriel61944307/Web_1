package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IConsultaService;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {
    
    @Autowired
    IConsultaDAO dao;

    public void salvar(Consulta consulta) {
        dao.save(consulta);
    }

    @Transactional(readOnly = true)
    public Consulta buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Consulta> buscarTodasPorPaciente(Paciente p) {
        return dao.findAllByPaciente(p);
    }

    @Transactional(readOnly = true)
    public List<Consulta> buscarTodasPorMedico(Medico m) {
        return dao.findAllByMedico(m);
    }
}
