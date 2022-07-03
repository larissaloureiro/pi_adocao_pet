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

import br.com.pi_adocao_pet.domain.vo.v1.EnderecoVO;
import br.com.pi_adocao_pet.service.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Endereco Endpoint")
@RestController
@RequestMapping("/api/endereco/v1")
public class EnderecoController {
	
	@Autowired
	EnderecoService service;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<EnderecoVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<EnderecoVO> enderecoVO = service.buscarTodos(pageable);
		enderecoVO.stream()
				.forEach(f -> f.add(linkTo(methodOn(EnderecoController.class).findById(f.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(enderecoVO));
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public EnderecoVO findById(@PathVariable("id") Long id) {
		EnderecoVO funcionarioVO = service.buscarPorId(id);
		funcionarioVO.add(linkTo(methodOn(EnderecoController.class).findById(id)).withSelfRel());
		return funcionarioVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public EnderecoVO create(@Valid @RequestBody EnderecoVO funcionario) {
		EnderecoVO funcionarioVO = service.inserir(funcionario);
		funcionarioVO.add(linkTo(methodOn(EnderecoController.class).findById(funcionarioVO.getKey())).withSelfRel());
		return funcionarioVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public EnderecoVO update(@Valid @RequestBody EnderecoVO funcionario) {
		EnderecoVO funcionarioVO = service.atualizar(funcionario);
		funcionarioVO.add(linkTo(methodOn(EnderecoController.class).findById(funcionarioVO.getKey())).withSelfRel());
		return funcionarioVO;
	}

	@DeleteMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}