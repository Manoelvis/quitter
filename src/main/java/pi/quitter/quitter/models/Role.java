package pi.quitter.quitter.models;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = -1149259983254260718L;

	@Id
	private Long id;
	private String nome;

	@Override
	public String getAuthority() {
		return this.nome;
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

	@Override
	public String toString() {
		return "Role [id=" + id + ", nome=" + nome + "]";
	}
}