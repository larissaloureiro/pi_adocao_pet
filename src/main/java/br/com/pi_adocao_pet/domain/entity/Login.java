package br.com.pi_adocao_pet.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_login")
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_login")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario idUsuario;

	@Column(name = "username_login", unique = true) // não permite repetição do valor do objeto
	private String username;

	@Column(name = "senha_login")
	private String senha;

}
