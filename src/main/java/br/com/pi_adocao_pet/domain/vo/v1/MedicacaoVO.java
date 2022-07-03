package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import br.com.pi_adocao_pet.domain.entity.Animal;
import br.com.pi_adocao_pet.domain.entity.Funcionario;
import br.com.pi_adocao_pet.domain.entity.Medicamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicacaoVO extends RepresentationModel<MedicacaoVO> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	private Long key;
	private Animal idAnimal;
	private Medicamento idMedicamento;
    private Funcionario idFuncionario;
    private Date dataMedicacao;
    private String dosagem;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(key, idAnimal, idMedicamento, idFuncionario, dataMedicacao, dosagem);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicacaoVO other = (MedicacaoVO) obj;
		return Objects.equals(key, other.key) 
                && Objects.equals(idAnimal, other.idAnimal)
				&& Objects.equals(idMedicamento, other.idMedicamento)
                && Objects.equals(idFuncionario, other.idFuncionario)
                && Objects.equals(dataMedicacao, other.dataMedicacao)
                && Objects.equals(dosagem, other.dosagem);
	}
}
