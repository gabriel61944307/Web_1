package br.ufscar.dc.dsw.domain;

public class Medico extends Usuario {

    private String crm;
    private String especialidade;

    public Medico(Long id, String nome, String email, String senha, String papel, String crm, String especialidade) {
        super(id, nome, email, senha, papel);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico(String nome, String email, String senha, String papel, String crm, String especialidade) {
        super(nome, email, senha, papel);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
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
