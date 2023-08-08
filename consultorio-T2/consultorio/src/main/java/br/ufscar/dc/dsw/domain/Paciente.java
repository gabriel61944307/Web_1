package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "Paciente")
public class Paciente extends Usuario {

    @NotBlank
    @Column(nullable = false, length = 14)
    private String CPF;

    @NotBlank
    @Column(nullable = false, length = 15)
    private String telefone;

    @NotBlank
    @Column(nullable = false, length = 1)
    private String sexo;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas = new ArrayList<>();
    
    public String getCPF() {
		return CPF;
	}

	public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public void addConsultas(Consulta consulta) {
        this.consultas.add(consulta);
    }

    public void setCPF(String CPF) {
		this.CPF = CPF;
	}

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
