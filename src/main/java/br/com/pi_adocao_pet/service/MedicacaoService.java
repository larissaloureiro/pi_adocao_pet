package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.repository.MedicacaoRepository;

@Service
public class MedicacaoService {
	
	@Autowired
	MedicacaoRepository repository;
	
}