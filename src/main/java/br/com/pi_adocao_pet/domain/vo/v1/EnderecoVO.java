package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;


public class EnderecoVO extends RepresentationModel<EnderecoVO> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	private Long key;
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String referencia;
	
	
	
	public EnderecoVO(Long key, String logradouro, String cep, String numero, String complemento, String referencia) {
		super();
		this.key = key;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.referencia = referencia;
	}
	
	
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cep, complemento, key, logradouro, numero, referencia);
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
		EnderecoVO other = (EnderecoVO) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(complemento, other.complemento)
				&& Objects.equals(key, other.key) && Objects.equals(logradouro, other.logradouro)
				&& Objects.equals(numero, other.numero) && Objects.equals(referencia, other.referencia);
	}

	
	
	

}
