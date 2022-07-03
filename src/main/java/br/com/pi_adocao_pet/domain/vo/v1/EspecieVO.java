package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

public class EspecieVO extends RepresentationModel<EspecieVO> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
@Mapping("id")

private long id;
private String nome;

public EspecieVO(long id, String nome) {
	super();
	this.id = id;
	this.nome = nome;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
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
	result = prime * result + Objects.hash(id, nome);
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
	EspecieVO other = (EspecieVO) obj;
	return id == other.id && Objects.equals(nome, other.nome);
}




}
