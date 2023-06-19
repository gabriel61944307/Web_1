package br.ufscar.dc.dsw.domain;

public class Usuario {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String papel;

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(String nome, String email, String senha, String papel) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.papel = papel;
	}

	public Usuario(Long id, String nome, String email, String senha, String papel) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.papel = papel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public void setSenha(String password) {
		this.senha = password;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}
}