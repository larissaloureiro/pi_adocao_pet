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
	public class Funcionario implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_funcionario")
		private Long id;
		
		@OneToOne
		@JoinColumn(name="id_usuario")
		private Usuario idUsuario ;
		
		@NotBlank
		@Size(max=45)
		@Column(name="cargo_funcionario")
		private String cargo;
		
		
		/*@NotBlank
		@Size(max=45)
		@Column(name="formacaoAcademica_funcionario")
		private String formacaoAcademica; */
		
		/*@NotBlank
		@Size(max=45)
		@Column(name="ExperienciaProfissional_funcionario")
		private String experienciaProfissional; */
		
		@NotBlank
		@Size(max=8)
		@Column(name="carteira_trabalho")
		private String carteiraTrabalho; 
		
		

		
		
}
