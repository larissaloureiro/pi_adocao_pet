package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.domain.entity.Especie;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.EspecieRepository;

@Service
public class EspecieService {
	
	@Autowired

	EspecieRepository repository;

	public Especie inserir(Especie especie) {
		return repository.save(especie);
	}

	public Page<Especie> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page;
	}

	public Especie buscaPorId(Long id) {
		Especie entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return entity;
	}

	public Especie atualizar(Especie especie) {
		var entity = repository.findById(especie.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));

		entity.setNome(especie.getNome());

		return repository.save(especie);

	}

	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);

	}

	

}



