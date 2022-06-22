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
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.pi_adocao_pet.domain.vo.v1.VacinaVO;
import br.com.pi_adocao_pet.service.VacinaService;

@RestController
@RequestMapping("api/vacinas/v1")
public class VacinaController {

	@Autowired
	VacinaService service;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<VacinaVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<VacinaVO> vacinasVO = service.buscarTodos(pageable);
		vacinasVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(VacinaController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(vacinasVO));
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public VacinaVO findById(@PathVariable("id") Long id) {
		VacinaVO vacinaVO = service.buscarPorId(id);
		vacinaVO.add(linkTo(methodOn(VacinaController.class).findById(id)).withSelfRel());
		return vacinaVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public VacinaVO create(@Valid @RequestBody VacinaVO vacina) {
		VacinaVO vacinaVO = service.inserir(vacina);
		vacinaVO.add(linkTo(methodOn(VacinaController.class).findById(vacinaVO.getKey())).withSelfRel());
		return vacinaVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public VacinaVO update(@Valid @RequestBody VacinaVO vacina) {
		VacinaVO vacinaVO = service.atualizar(vacina);
		vacinaVO.add(linkTo(methodOn(VacinaController.class).findById(vacinaVO.getKey())).withSelfRel());
		return vacinaVO;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
