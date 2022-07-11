package br.com.pi_adocao_pet.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pi_adocao_pet.domain.vo.v1.AdocaoVO;
import br.com.pi_adocao_pet.domain.vo.v1.AnimalVO;
import br.com.pi_adocao_pet.service.AnimalService;

@RestController
@RequestMapping ("/api/animal/v1/")
public class AnimalController {
	
	@Autowired
	AnimalService service;
	
	/*
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<AdocaoVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<AnimalVO> animalVO = service.buscarTodos(pageable);
		animalVO.stream()
				.forEach(f -> f.add(linkTo(methodOn(AnimalController.class).findById(f.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(animalVO));
	} */
	
	@GetMapping(value = "/busca", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<AnimalVO>> findAllByIdEspecie(
			@RequestParam(value = "idEspecie") Long idEspecie,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		Page<AnimalVO> animalVO = service.buscarTodosPorIdEspecie(idEspecie, pageable);
		animalVO.stream()
				.forEach(f -> f.add(linkTo(methodOn(AnimalController.class).findById(f.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(animalVO));
	}
	
	
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public AnimalVO findById(@PathVariable("id") Long id) {
		AnimalVO animalVO = service.buscarPorId(id);
		animalVO.add(linkTo(methodOn(AnimalController.class).findById(id)).withSelfRel());
		return animalVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public AnimalVO create(@Valid @RequestBody AnimalVO animal) {
		AnimalVO animalVO = service.inserir(animal);
		animalVO.add(linkTo(methodOn(AnimalController.class).findById(animalVO.getKey())).withSelfRel());
		return animalVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public AnimalVO update(@Valid @RequestBody AnimalVO animal) {
		AnimalVO animalVO = service.atualizar(animal);
		animalVO.add(linkTo(methodOn(AdocaoController.class).findById(animalVO.getKey())).withSelfRel());
		return animalVO;
	}

	@DeleteMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}

	

