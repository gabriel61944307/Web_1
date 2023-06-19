package br.ufscar.dc.dsw.domain;

public class Paciente extends Usuario {

    private String cpf;
    private String telefone;
    private String sexo;
    private String dataNascimento;

    public Paciente(Long id, String nome, String email, String senha, String papel, String cpf, String telefone, String sexo, String dataNascimento) {
        super(id, nome, email, senha, papel);
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Paciente(String nome, String email, String senha, String papel, String cpf, String telefone, String sexo, String dataNascimento) {
        super(nome, email, senha, papel);
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
