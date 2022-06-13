package br.com.pi_adocao_pet.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@Table(name = "tb_Especie")
	public class Especie implements Serializable {
		
		private static final long serialVersionUID = 1L;
		

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_especie")
		private Long id;
		

		@ManyToOne  // confirmar esta cardinalidade
		@JoinColumn(name="id_raca")
		private Raca idRaca ;
		
		@NotBlank
		@Size(max=45)
		@Column(name="nome_especie")
		private String nome;

}
