package br.ufscar.dc.dsw.domain;

public class Consulta {

    private Long id;
    private String cpfPaciente;
    private String crmMedico;
    private String dataHora;

    public Consulta(Long id, String cpfPaciente, String crmMedico, String dataHora) {
        this.id = id;
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
        this.dataHora = dataHora;
    }

    public Consulta(String cpfPaciente, String crmMedico, String dataHora) {
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
