package br.com.pi_adocao_pet.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tb_Funcionario")
public class Funcionario extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 45)
	@Column(name = "cargo_funcionario")
	private String cargo;

	/*
	 * @NotBlank
	 * 
	 * @Size(max=45)
	 * 
	 * @Column(name="formacaoAcademica_funcionario") private String
	 * formacaoAcademica;
	 */

	/*
	 * @NotBlank
	 * 
	 * @Size(max=45)
	 * 
	 * @Column(name="ExperienciaProfissional_funcionario") private String
	 * experienciaProfissional;
	 */

	@NotBlank
	@Size(max = 8)
	@Column(name = "carteira_trabalho")
	private String carteiraTrabalho;

	@NotBlank
	@Column(name = "data_admissao")
	private Date dataAdmissao;

}
