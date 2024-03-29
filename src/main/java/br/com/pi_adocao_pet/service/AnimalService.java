package br.com.pi_adocao_pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Animal;
import br.com.pi_adocao_pet.domain.vo.v1.AnimalVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.AnimalRepository;

@Service

public class AnimalService {

	@Autowired
	AnimalRepository repository;

	public AnimalVO inserir(AnimalVO animal) {
		var entity = DozerConverter.parseObject(animal, Animal.class);
		var vo = DozerConverter.parseObject(repository.save(entity), AnimalVO.class);
		return vo;
	}

	public Page<AnimalVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToAnimalVO);
	}

	private AnimalVO convertToAnimalVO(Animal entity) {
		return DozerConverter.parseObject(entity, AnimalVO.class);
	}
	
	public Page<AnimalVO> buscarTodosPorIdEspecie(long idEspecie, Pageable pageable){
		var page = repository.findAllByIdEspecie(idEspecie, pageable);
		return page.map(this::convertToAnimalVO);
	}

	public AnimalVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		return DozerConverter.parseObject(entity, AnimalVO.class);
	}

	public void delete(Long id) {
		Animal entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		repository.delete(entity);
	}

	public AnimalVO atualizar(AnimalVO animal) {
		var entity = repository.findById(animal.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));

		entity.setNome(animal.getNome());
		entity.setPorte(animal.getPorte());
		entity.setIdade(animal.getIdade());
		entity.setSexo(animal.getSexo());

		var vo = DozerConverter.parseObject(repository.save(entity), AnimalVO.class);
		return vo;
	}


	public List<Animal> buscarPorDisponibilidade() {
		return repository.findAllByDisponibilidade(true);
	}


}