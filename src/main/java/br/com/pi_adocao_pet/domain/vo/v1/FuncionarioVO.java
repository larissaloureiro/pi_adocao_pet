package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

public class FuncionarioVO extends RepresentationModel<EnderecoVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	private EnderecoVO endereco;
	private String nome;
	private String telefone;
	private String email;
	private String rg;
	private String cpf;

	private String cargo;
	private String carteiraTrabalho;
	private Date dataAdmissao;

	public FuncionarioVO(Long key, EnderecoVO endereco, String nome, String telefone, String email, String rg,
			String cpf, String cargo, String carteiraTrabalho, Date dataAdmissao) {
		this.key = key;
		this.endereco = endereco;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.cargo = cargo;
		this.carteiraTrabalho = carteiraTrabalho;
		this.dataAdmissao = dataAdmissao;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public EnderecoVO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoVO endereco) {
		this.endereco = endereco;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}

	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(cargo, carteiraTrabalho, cpf, dataAdmissao, email, endereco, key, nome, rg, telefone);
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
		FuncionarioVO other = (FuncionarioVO) obj;
		return Objects.equals(cargo, other.cargo) && Objects.equals(carteiraTrabalho, other.carteiraTrabalho)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(dataAdmissao, other.dataAdmissao)
				&& Objects.equals(email, other.email) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(key, other.key) && Objects.equals(nome, other.nome) && Objects.equals(rg, other.rg)
				&& Objects.equals(telefone, other.telefone);
	}

}
