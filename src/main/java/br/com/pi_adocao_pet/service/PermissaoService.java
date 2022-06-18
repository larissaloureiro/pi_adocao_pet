package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	PermissaoRepository repository;	
}
