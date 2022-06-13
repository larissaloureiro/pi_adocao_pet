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
@Table(name = "tb_Medicacao")
public class Medicacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_medicacao")
	private Long id;

	@OneToOne // confirmar esta cardinalidade
	@JoinColumn(name = "id_animal")
	private Animal idAnimal;

	@OneToOne // confirmar esta cardinalidade
	@JoinColumn(name = "id_medicamento")
	private Medicamento idMedicamento;

	@NotBlank
	@Column(name = "data_medicacao")
	private Date dataMedicacao;

	@NotBlank
	@Size(max = 10)
	@Column(name = "dosagem")
	private String dosagem;

}