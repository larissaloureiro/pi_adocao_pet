package br.com.pi_adocao_pet.security;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

public class LoginVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String username;
	private String senha;
	
	public LoginVO() {
		
	}
	
	public LoginVO(String username, String senha) {
		this.username = username;
		this.senha = senha;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String password) {
		this.senha = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(senha, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginVO other = (LoginVO) obj;
		return Objects.equals(senha, other.senha) && Objects.equals(username, other.username);
	}

}
