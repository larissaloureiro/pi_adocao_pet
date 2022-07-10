package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import br.com.pi_adocao_pet.domain.entity.Especie;

public class RacaVO extends RepresentationModel<RacaVO> implements Serializable {
	
	private static final long serialVersionUID= 1L;
	
	@Mapping("id")
	
	private long id;
	private Especie idEspecie;
	private String nome;
	
	public RacaVO(long id, Especie idEspecie, String nome) {
		super();
		this.id = id;
		this.idEspecie = idEspecie;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Especie getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(Especie idEspecie) {
		this.idEspecie = idEspecie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, idEspecie, nome);
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
		RacaVO other = (RacaVO) obj;
		return id == other.id && Objects.equals(idEspecie, other.idEspecie) && Objects.equals(nome, other.nome);
	}

	public Long getKey() {
		
		return null;
	}
	
	
	

}
