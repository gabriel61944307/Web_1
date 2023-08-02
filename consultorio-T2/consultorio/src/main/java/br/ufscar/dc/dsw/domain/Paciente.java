package br.ufscar.dc.dsw.domain;

// import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;
// import org.springframework.format.annotation.NumberFormat;
// import org.springframework.format.annotation.NumberFormat.Style;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "Paciente")
public class Paciente extends Usuario {
    // CPF, telefone, sexo e data de nascimento

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
    @Past(message = "{past.paciente.dataNascimento}")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataNascimento;
    
    public String getCPF() {
		return CPF;
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
