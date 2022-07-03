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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.pi_adocao_pet.domain.vo.v1.VacinacaoVO;
import br.com.pi_adocao_pet.service.VacinacaoService;


@RestController
@RequestMapping("api/vacinaçao/v1")
public class VacinacaoController {

	@Autowired
	VacinacaoService service;

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public VacinacaoVO create(@Valid @RequestBody VacinacaoVO vacinacao) {
		VacinacaoVO vacinacaoVO = service.registrar(vacinacao);
		vacinacaoVO.add(linkTo(methodOn(VacinacaoController.class).findById(vacinacaoVO.getKey())).withSelfRel());
		return vacinacaoVO;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<VacinacaoVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<VacinacaoVO> vacinacoesVO = service.consultarVacinacao(pageable);
		vacinacoesVO.stream()
				.forEach(v -> v.add(linkTo(methodOn(VacinacaoController.class).findById(v.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(vacinacoesVO));
	}

	@CrossOrigin({ "localhost:8080", "http://www.fgateste.com.br" }) // permitido o acesso
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public VacinacaoVO findById(@PathVariable("id") Long id) {
		VacinacaoVO vacinacaoVO = service.buscarPorId(id);
		vacinacaoVO.add(linkTo(methodOn(VacinacaoController.class).findById(id)).withSelfRel());
		return vacinacaoVO;
	}

	@GetMapping(value = "/buscarPorIdAnimal/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<CollectionModel<VacinacaoVO>> findPersonByName(@PathVariable("idAnimal") Long idAnimal,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		Page<VacinacaoVO> vacinacoesVO = service.mostrarVacinacaoPorIdAnimal(idAnimal, pageable);
		vacinacoesVO.stream()
				.forEach(v -> v.add(linkTo(methodOn(VacinacaoController.class).findById(v.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(vacinacoesVO));
	}

}
