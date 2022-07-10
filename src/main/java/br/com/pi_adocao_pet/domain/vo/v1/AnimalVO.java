package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.domain.Sort.Order;
import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

public class AnimalVO extends RepresentationModel<AnimalVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private long key;
	private String nome;
	private String porte;
	private int idade;
	private String sexo;

	public AnimalVO(long key, String nome, String porte, int idade, String sexo) {
		super();
		this.key = key;
		this.nome = nome;
		this.porte = porte;
		this.idade = idade;
		this.sexo = sexo;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(idade, key, nome, porte, sexo);
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
		AnimalVO other = (AnimalVO) obj;
		return idade == other.idade && key == other.key && Objects.equals(nome, other.nome)
				&& Objects.equals(porte, other.porte) && Objects.equals(sexo, other.sexo);
	}

	public static Iterable<Order> stream() {
		// TODO Auto-generated method stub
		return null;
	}

}
