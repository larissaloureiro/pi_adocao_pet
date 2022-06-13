package br.com.pi_adocao_pet.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_usuario")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco idEndereco;

	@NotBlank
	@Size(max = 45)
	@Column(name = "nome_usuario")
	private String nome;

	@NotBlank
	@Size(max = 20)
	@Column(name = "telefone_usuario")
	private String telefone;

	@NotBlank
	@Email
	@Size(max = 255)
	@Column(name = "email_usuario")
	private String email;

	@NotBlank
	@Size(max = 9)
	@Column(name = "rg_usuario")
	private String rg;

	@NotBlank
	@Size(max = 11)
	@Column(name = "cpf_usuario")
	private String cpf;

}
