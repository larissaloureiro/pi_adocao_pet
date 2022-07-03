package br.com.pi_adocao_pet.security;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String username;
	private String senha;
}
