package br.com.pi_adocao_pet.domain.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Tutor extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@NotBlank
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	public Boolean possuiIdadeMinima() {
		Integer idade = this.calculaIdade(this.dataNascimento);
		if (idade >= 18) {
			return true;
		}
		return false;
	}
	
	public Integer calculaIdade(Date dataNascimento) {		
		
		Calendar dataNascimentoCalendar = Calendar.getInstance();
		dataNascimentoCalendar.setTime(dataNascimento);
		Integer anoNascimento = dataNascimentoCalendar.get(Calendar.YEAR);
		Integer mesNascimento = dataNascimentoCalendar.get(Calendar.MONTH);
		Integer diaNascimento = dataNascimentoCalendar.get(Calendar.DAY_OF_MONTH);

		Calendar dataAtual = Calendar.getInstance();
		Integer anoAtual = dataAtual.get(Calendar.YEAR);
		Integer mesAtual = dataAtual.get(Calendar.MONTH);
		Integer diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
		
		Integer idade = anoAtual - anoNascimento;
		if (mesAtual < mesNascimento || (mesAtual == mesNascimento && diaAtual < diaNascimento)) {
			idade--;
		}
		
		return idade;
	}
	
	

}
