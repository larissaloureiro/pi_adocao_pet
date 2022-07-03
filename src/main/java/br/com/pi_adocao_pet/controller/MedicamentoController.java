package br.com.pi_adocao_pet.controller;

import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

import br.com.pi_adocao_pet.domain.vo.v1.MedicamentoVO;
import br.com.pi_adocao_pet.service.MedicamentoService;


@RestController
@RequestMapping ("/api/medicamento/v1/")
public class MedicamentoController {

    @Autowired
	MedicamentoService service;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<MedicamentoVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "tipo"));
		Page<MedicamentoVO> medicamentoVO = service.buscarTodos(pageable);
		medicamentoVO.stream()
				.forEach(f -> f.add(linkTo(methodOn(MedicamentoController.class).findById(f.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(medicamentoVO));
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public MedicamentoVO findById(@PathVariable("id") Long id) {
		MedicamentoVO medicamentoVO = service.buscarPorId(id);
		medicamentoVO.add(linkTo(methodOn(MedicamentoController.class).findById(id)).withSelfRel());
		return medicamentoVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public MedicamentoVO create(@Valid @RequestBody MedicamentoVO medicamento) {
		MedicamentoVO medicamentoVO = service.inserir(medicamento);
		medicamentoVO.add(linkTo(methodOn(MedicamentoController.class).findById(medicamentoVO.getKey())).withSelfRel());
		return medicamentoVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public MedicamentoVO update(@Valid @RequestBody MedicamentoVO medicamento) {
		MedicamentoVO medicamentoVO = service.atualizar(medicamento);
		medicamentoVO.add(linkTo(methodOn(MedicamentoController.class).findById(medicamentoVO.getKey())).withSelfRel());
		return medicamentoVO;
	}

	@DeleteMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
