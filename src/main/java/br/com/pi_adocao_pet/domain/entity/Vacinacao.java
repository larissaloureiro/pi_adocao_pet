package br.com.pi_adocao_pet.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_Vacinacao")
public class Vacinacao implements Serializable {
	

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_vacinacao")
	private Long id;
	
	@OneToOne // confirmar esta cardinalidade
	@JoinColumn(name="id_animal")
	private Animal idAnimal ;
	
	@ManyToOne // confirmar esta cardinalidade
	@JoinColumn(name="id_vacina")
	private Vacina idVacina;
	
	@NotBlank
	@Column(name="data_vacinacao")
	private Date dataVacinacao;
	
	

}