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
	@Table(name = "tb_Vacina")
	public class Vacina implements Serializable{
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_vacina")
		private Long id;
		
		@NotBlank
		@Size(max=45)
		@Column(name="tipo_vacina")
		private String tipo;
		
		@NotBlank
		@Size(max=45)
		@Column(name="fabricante_vacina")
		private String fabricanteVacina;
		

}
