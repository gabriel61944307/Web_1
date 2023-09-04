package br.ufscar.dc.dsw.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import br.ufscar.dc.dsw.validation.UniqueCRM;

@SuppressWarnings("serial")
@Entity
@JsonIgnoreProperties(value = { "consultas", "role", "enabled", "password" })
@Table(name = "Medico")
public class Medico extends Usuario {

    //@UniqueCRM (message = "{Unique.medico.CRM}")
    @NotBlank
    @Column(nullable = false, length = 9, unique = true)
    @Size(min = 9, max = 9, message = "{size.medico.CRM}")
    private String CRM;

    @NotBlank
    @Column(nullable = false, length = 16)
    private String especialidade;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas = new ArrayList<>();

    public Medico() {
        this.setRole("ROLE_PACIENTE");
        this.setEnabled(true);
    }

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
