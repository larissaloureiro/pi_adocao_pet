package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.Link;
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

	
	
	
	//Substituir por GET/SET
	public Long getKey() {
		return null;
	}


	
}
