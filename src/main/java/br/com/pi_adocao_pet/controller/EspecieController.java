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
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
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

import br.com.pi_adocao_pet.domain.entity.Especie;
import br.com.pi_adocao_pet.domain.entity.Raca;
import br.com.pi_adocao_pet.domain.vo.v1.EspecieVO;
import br.com.pi_adocao_pet.domain.vo.v1.RacaVO;
import br.com.pi_adocao_pet.service.EspecieService;

@RestController
@RequestMapping

public class EspecieController {

	@Autowired
	EspecieService service;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<Especie>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<Especie> especieVO = service.buscarTodos(pageable);
				especieVO.stream()
				.forEach(v -> v.add(linkTo(methodOn(EspecieController.class).findById(v.getId())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(especieVO));
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public RepresentationModel<EspecieVO> findById(@PathVariable("id") Long id) {
		EspecieVO especieVO = service.buscarPorId(id);
		RepresentationModel<EspecieVO> especieVO;
		especieVO.add(linkTo(methodOn(EspecieController.class).findById(id)).withSelfRel());
		return especieVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public EspecieVO create(@Valid @RequestBody EspecieVO especie) {
		EspecieVO especieVO = service.inserir(especie);
		especieVO.add(linkTo(methodOn(EspecieController.class).findById(especieVO.getId())).withSelfRel());
		return especieVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public EspecieVO update(@Valid @RequestBody EspecieVO especie) {
		EspecieVO especieVO = service.atualizar(especie);
		especieVO.add(linkTo(methodOn(EspecieController.class).findById(especieVO.getId())).withSelfRel());
		return especieVO;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}




