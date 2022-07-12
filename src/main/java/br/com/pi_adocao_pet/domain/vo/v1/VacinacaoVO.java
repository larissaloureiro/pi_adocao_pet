package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

public class VacinacaoVO extends RepresentationModel<VacinacaoVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	private AnimalVO Animal;
	private VacinaVO Vacina;
	private FuncionarioVO Funcionario;
	
	private Date dataVacinacao;

	public VacinacaoVO(Long key, AnimalVO animal, VacinaVO vacina, FuncionarioVO funcionario, Date dataVacinacao) {
		super();
		this.key = key;
		Animal = animal;
		Vacina = vacina;
		Funcionario = funcionario;
		this.dataVacinacao = dataVacinacao;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public AnimalVO getAnimal() {
		return Animal;
	}

	public void setAnimal(AnimalVO animal) {
		Animal = animal;
	}

	public VacinaVO getVacina() {
		return Vacina;
	}

	public void setVacina(VacinaVO vacina) {
		Vacina = vacina;
	}

	public FuncionarioVO getFuncionario() {
		return Funcionario;
	}

	public void setFuncionario(FuncionarioVO funcionario) {
		Funcionario = funcionario;
	}

	public Date getDataVacinacao() {
		return dataVacinacao;
	}

	public void setDataVacinacao(Date dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(Animal, Funcionario, Vacina, dataVacinacao, key);
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
		VacinacaoVO other = (VacinacaoVO) obj;
		return Objects.equals(Animal, other.Animal) && Objects.equals(Funcionario, other.Funcionario)
				&& Objects.equals(Vacina, other.Vacina) && Objects.equals(dataVacinacao, other.dataVacinacao)
				&& Objects.equals(key, other.key);
	}
	
}
