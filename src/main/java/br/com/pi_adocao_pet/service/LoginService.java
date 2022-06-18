package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	LoginRepository repository;
	
}
