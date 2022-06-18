package br.com.pi_adocao_pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.domain.entity.Especie;
import br.com.pi_adocao_pet.domain.entity.Raca;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.RacaRepository;

@Service

public class RacaService {

	@Autowired

	RacaRepository repository;

	public Raca inserir(Raca raca) {
		return repository.save(raca);
	}

	public List<Raca> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.toList();
	}

	public Raca buscaPorId(Long Id) {
		Raca entity = repository.findByIdRaca(idRaca);
		return entity;
	}

	public Raca atualizar(Raca raca) {
		var entity = repository.findById(raca.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));

		entity.setNome(raca.getNome());

		return repository.save(raca);

	}

	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);

	}
	
	public List<Raca> buscaPorIdEspecie(Especie especie){
		return repository.findByIdEspecie(especie.getId());
	}

}
