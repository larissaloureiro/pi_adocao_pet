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
@Table(name = "tb_Adocao")
public class Adocao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_adocao")
	private Long id;

	@ManyToOne // confirmar esta cardinalidade
	@JoinColumn(name = "id_tutor")
	private Tutor tutor;

	@OneToOne
	@JoinColumn(name = "id_Animal")
	private Animal animal;

	@NotBlank
	@Column(name = "status")
	private Status status;

	@NotBlank
	@Column(name = "data_solicitacao")
	private Date dataSolicitacao;

	@NotBlank
	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

}
