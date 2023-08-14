package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

// import br.ufscar.dc.dsw.validation.UniqueEmail;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long> {
      
	@NotBlank
    @Column(nullable = false, length = 60)
    private String nome;

	@NotBlank
    @Column(nullable = false, length = 64)
    private String password;

	// @UniqueEmail (message = "{Unique.usuario.email}")
	@NotBlank
	@Column(nullable = false, length = 64)
	private String email;
	
    @NotBlank
    @Column(nullable = false, length = 16)
    private String role;
    
    @Column(nullable = false)
    private boolean enabled;

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
