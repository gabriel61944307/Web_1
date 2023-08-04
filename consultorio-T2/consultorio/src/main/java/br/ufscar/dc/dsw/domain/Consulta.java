package br.ufscar.dc.dsw.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//import org.springframework.format.annotation.DateTimeFormat;

// O sistema deve possuir um cadastro de consultas, com os seguintes dados: CPF do paciente, CRM
// do médico e data/hora da consulta. Assume-se que a duração da consulta é de 30 minutos e
// sempre inicia-se em “hora cheia” (14h 00min etc) ou “hora meia” (14h 30min etc).

@SuppressWarnings("serial")
@Entity
@Table(name = "Consulta")
public class Consulta extends AbstractEntity<Long> {
    
    // private Long id;
    // private String cpfPaciente;
    // private String crmMedico;
    // private String data;
    // private String hora;

    // será que não precisa de id? no exemplo do prof não tem...
    
    @NotNull(message = "{NotNull.consulta.data}")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDateTime dataHoraConsulta;

    //@NotNull(message = "{NotNull.consulta.paciente}")
    @ManyToOne
    @JoinColumn(name = "pacienteID")
    private Paciente paciente;

    @NotNull(message = "{NotNull.consulta.medico}")
    @ManyToOne
    @JoinColumn(name = "medicoID")
    private Medico medico;

    public LocalDateTime getDataHoraConsulta() {
        return dataHoraConsulta;
    }

    public void setDataHoraConsulta(LocalDateTime dataHoraConsulta) {
        this.dataHoraConsulta = dataHoraConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
