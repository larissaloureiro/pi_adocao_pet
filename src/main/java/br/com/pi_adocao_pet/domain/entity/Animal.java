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
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Entity
	@Table(name = "tb_Animal")
	public class Animal implements Serializable {
		

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_animal")
		private Long id;
		
		@OneToOne  // confirmar esta cardinalidade
		@JoinColumn(name="id_especie")
		private Especie idEspecie ;
		
		@NotBlank
		@Size(max=45)
		@Column(name="nome_animal")
		private String nome;
		
		@NotBlank
		@Size(max=10)
		@Column(name="porte_animal")
		private String porte;
		
		
		@NotBlank
		@Size(max=3)
		@Column(name="idade_animal")
		private int idade;
		
		@NotBlank
		@Size(max=9)
		@Column(name="sexo_animal")
		private String sexo;
		
		
		/* @NotBlank
		@Size(max=45)
		@Column(name="foto_animal")
		private String foto; */  //verificar como adicionar foto 


		@NotBlank
		@Column(name="data_cadastro")
		private Date dataCadastro; 
		

		@NotBlank
		@Column(name="disponibilidade_animal")
		private Boolean disponibilidade; 
		
		@NotBlank
		@Size(max=45)
		@Column(name="informacoes_animal")
		private String informacoes;
		
		

}
