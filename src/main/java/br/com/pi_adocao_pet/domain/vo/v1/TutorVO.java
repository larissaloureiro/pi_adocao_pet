package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

public class TutorVO extends RepresentationModel<EnderecoVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	private EnderecoVO idEndereco;
	private String nome;
	private String telefone;
	private String email;
	private String rg;
	private String cpf;

	private Date dataNascimento;
	private Date dataCadastro;

	public TutorVO(Long key, EnderecoVO idEndereco, String nome, String telefone, String email, String rg, String cpf,
			Date dataNascimento, Date dataCadastro) {
		this.key = key;
		this.idEndereco = idEndereco;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public EnderecoVO getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(EnderecoVO idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(cpf, dataCadastro, dataNascimento, email, idEndereco, key, nome, rg, telefone);
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
		TutorVO other = (TutorVO) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(email, other.email)
				&& Objects.equals(idEndereco, other.idEndereco) && Objects.equals(key, other.key)
				&& Objects.equals(nome, other.nome) && Objects.equals(rg, other.rg)
				&& Objects.equals(telefone, other.telefone);
	}

}
