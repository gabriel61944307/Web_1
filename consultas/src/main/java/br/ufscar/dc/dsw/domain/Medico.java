package br.ufscar.dc.dsw.domain;

public class Medico {

    private Long id;
    private String crm;
    private String especialidade;

    public Medico(Long id, String crm, String especialidade) {
        this.id = id;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico(String crm, String especialidade) {
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
