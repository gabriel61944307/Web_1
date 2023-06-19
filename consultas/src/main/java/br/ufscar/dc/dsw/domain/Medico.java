package br.ufscar.dc.dsw.domain;

public class Medico {

    private Long id;
    private String email;
    private String senha;
    private String crm;
    private String nome;
    private String especialidade;

    public Medico(Long id, String email, String senha, String crm, String nome, String especialidade) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Medico(String email, String senha, String crm, String nome, String especialidade) {
        this.email = email;
        this.senha = senha;
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
