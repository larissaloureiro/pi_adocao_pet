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

import br.com.pi_adocao_pet.domain.entity.Raca;
import br.com.pi_adocao_pet.domain.vo.v1.RacaVO;
import br.com.pi_adocao_pet.domain.vo.v1.VacinaVO;
import br.com.pi_adocao_pet.service.RacaService;
import br.com.pi_adocao_pet.service.VacinaService;


@RestController
@RequestMapping

public class RacaController {
	@Autowired
	RacaService service;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<Order>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<Raca> RacaVO = service.buscarTodos(pageable);
		Streamable<Order> racaVO;
		racaVO.stream()
				.forEach(v -> v.add(linkTo(methodOn(RacaController.class).findById(v.getId())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(racaVO));
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public RepresentationModel<RacaVO> findById(@PathVariable("id") Long id) {
		Raca raca = service.buscaPorId(id);
		RepresentationModel<RacaVO> racaVO;
		racaVO.add(linkTo(methodOn(RacaController.class).findById(id)).withSelfRel());
		return racaVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public RacaVO create(@Valid @RequestBody RacaVO raca) {
		RacaVO racaVO = service.inserir(raca);
		racaVO.add(linkTo(methodOn(RacaController.class).findById(racaVO.getId())).withSelfRel());
		return racaVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public RacaVO update(@Valid @RequestBody RacaVO raca) {
		RacaVO racaVO = service.atualizar(raca);
		racaVO.add(linkTo(methodOn(RacaController.class).findById(racaVO.getId())).withSelfRel());
		return racaVO;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}


