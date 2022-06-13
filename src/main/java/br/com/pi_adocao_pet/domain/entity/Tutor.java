package br.com.pi_adocao_pet.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	public class Tutor implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_tutor")
		private Long id;
		
		@OneToOne
		@JoinColumn(name="id_usuario")
		private Usuario idUsuario ;
		
		@NotBlank
		@Column(name="data_nascimento")
		private Date dataNascimento;
		
		
		@NotBlank
		@Column(name="data_cadastro")
		private Date dataCadastro; 
		
		

		
		
}
