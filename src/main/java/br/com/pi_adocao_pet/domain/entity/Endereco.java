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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long id;

	@NotBlank
	@Size(max = 50)
	@Column(name = "logradouro_endereco")
	private String logradouro;

	@NotBlank
	@Size(max = 9)
	@Column(name = "cep_endereco")
	private String cep;

	@NotBlank
	@Size(max = 9)
	@Column(name = "numero_endereco")
	private String numero;

	@NotBlank
	@Size(max = 15)
	@Column(name = "complemento_endereco")
	private String complemento;

	@NotBlank
	@Size(max = 45)
	@Column(name = "referencia_endereco")
	private String referencia;

}
