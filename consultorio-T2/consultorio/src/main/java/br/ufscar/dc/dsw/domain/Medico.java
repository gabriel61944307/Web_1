package br.ufscar.dc.dsw.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Medico")
public class Medico extends Usuario {

    @NotBlank
    @Column(nullable = false, length = 9)
    private String CRM;

    @NotBlank
    @Column(nullable = false, length = 16)
    private String especialidade;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas = new ArrayList<>();

    public List<Consulta> getConsultas() {
        return consultas;
    }


    public String getCRM() {
        return CRM;
    }

    public void setCRM(String cRM) {
        CRM = cRM;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String Especialidade) {
        especialidade = Especialidade;
    }
}
