package br.com.pi_adocao_pet.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_Tutor")
public class Tutor extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@NotBlank
	@Column(name = "data_cadastro")
	private Date dataCadastro;

}
