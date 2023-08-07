package br.ufscar.dc.dsw.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import javax.validation.constraints.NotBlank;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Especialidade")
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 40)
    private String especialidade;

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String Especialidade) {
        especialidade = Especialidade;
    }

    @Override
    public String toString() {
        return especialidade;
    }

    // public void addEspecialidade(String especialidade){
    //     especialidades.add(especialidade);
    // }
}
