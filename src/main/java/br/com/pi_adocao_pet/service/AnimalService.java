package br.com.pi_adocao_pet.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.domain.entity.Animal;
import br.com.pi_adocao_pet.domain.entity.Especie;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.AnimalRepository;

@Service

public class AnimalService {

	@Autowired
	AnimalRepository repository;
	
	public Animal inserir(Animal animal) {
		return repository.save(animal);
		
	}
   
public List<Animal> buscarTodos(Pageable pageable){
var page = repository.findAll(pageable);
return page.toList();
}

public Animal buscarPorId(Long id) {
	var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
	return entity;
}

public Animal atualizar(Animal animal) {
	var entity = repository.findById(animal.getId())
			.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));

	entity.setNome(animal.getNome());
	entity.setPorte(animal.getPorte());
	entity.setIdade(animal.getIdade());
	entity.setSexo(animal.getSexo());

	return repository.save(animal);
}

public void delete(Long id) {
	var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
	repository.delete(entity);
}

public List<Animal> buscarPorDisponibilidade(){
	return repository.findAllByDisponibilidade(true);
}

public List<Animal> buscarPorIdEspecie(Especie especie){
	return repository.findByIdEspecie(especie.getId());
}
}