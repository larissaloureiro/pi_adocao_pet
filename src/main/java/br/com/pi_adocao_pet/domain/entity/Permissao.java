package br.com.pi_adocao_pet.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_Permissao")
public class Permissao implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permissao")
	private Long id;

	@NotBlank
	@Size(max = 20)
	@Column(name = "descricao_permissao")
	private String descricao;

	@Override
	public String getAuthority() {
		return this.descricao;
	}

}
