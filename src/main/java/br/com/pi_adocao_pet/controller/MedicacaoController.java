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

import br.com.pi_adocao_pet.domain.vo.v1.MedicacaoVO;
import br.com.pi_adocao_pet.service.MedicacaoService;


@RestController
@RequestMapping ("/api/medicacao/v1/")
public class MedicacaoController {

    @Autowired
	MedicacaoService service;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<MedicacaoVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "dataMedicacao"));
		Page<MedicacaoVO> medicacaoVO = service.buscarTodos(pageable);
		medicacaoVO.stream()
				.forEach(f -> f.add(linkTo(methodOn(MedicacaoController.class).findById(f.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(medicacaoVO));
	}

	@GetMapping(value = "/busca", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<MedicacaoVO>> findAllByIdAnimal(
			@RequestParam(value = "idAnimal") Long idAnimal,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "dataMedicacao"));
		Page<MedicacaoVO> medicacaoVO = service.buscarTodosPorIdAnimal(idAnimal, pageable);
		medicacaoVO.stream()
				.forEach(f -> f.add(linkTo(methodOn(MedicacaoController.class).findById(f.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(medicacaoVO));
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public MedicacaoVO findById(@PathVariable("id") Long id) {
		MedicacaoVO medicacaoVO = service.buscarPorId(id);
		medicacaoVO.add(linkTo(methodOn(MedicacaoController.class).findById(id)).withSelfRel());
		return medicacaoVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public MedicacaoVO create(@Valid @RequestBody MedicacaoVO medicacao) {
		MedicacaoVO medicacaoVO = service.inserir(medicacao);
		medicacaoVO.add(linkTo(methodOn(MedicacaoController.class).findById(medicacaoVO.getKey())).withSelfRel());
		return medicacaoVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public MedicacaoVO update(@Valid @RequestBody MedicacaoVO medicacao) {
		MedicacaoVO medicacaoVO = service.atualizar(medicacao);
		medicacaoVO.add(linkTo(methodOn(MedicacaoController.class).findById(medicacaoVO.getKey())).withSelfRel());
		return medicacaoVO;
	}

	@DeleteMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
