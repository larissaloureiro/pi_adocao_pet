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

import br.com.pi_adocao_pet.domain.vo.v1.TutorVO;
import br.com.pi_adocao_pet.service.TutorService;

@RestController
@RequestMapping("/api/tutor/v1")
public class TutorController {
	@Autowired
	TutorService service;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.OK)
	public ResponseEntity<CollectionModel<TutorVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<TutorVO> tutoresVO = service.buscarTodos(pageable);
		tutoresVO.stream().forEach(t -> t.add(linkTo(methodOn(TutorController.class).findById(t.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(tutoresVO));
	}
	
	@GetMapping(value="/{id}", produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.OK)
	public TutorVO findById(@PathVariable("id") Long id) {
		TutorVO tutorVO = service.buscarPorId(id);
		tutorVO.add(linkTo(methodOn(TutorController.class).findById(id)).withSelfRel());
		return tutorVO;
	}
	
	@PostMapping(consumes = {"application/json","application/xml"}, produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.CREATED)
	public TutorVO create(@Valid @RequestBody TutorVO tutor) {
		TutorVO tutorVO = service.inserir(tutor);
		tutorVO.add(linkTo(methodOn(TutorController.class).findById(tutorVO.getKey())).withSelfRel());
		return tutorVO;
	}
	
	@PutMapping(consumes = {"application/json","application/xml"}, produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.OK)
	public TutorVO update(@Valid @RequestBody TutorVO tutor) {
		TutorVO tutorVO = service.atualizar(tutor);
		tutorVO.add(linkTo(methodOn(TutorController.class).findById(tutorVO.getKey())).withSelfRel());
		return tutorVO;
	}
	
	@DeleteMapping(value="/{id}", produces={"application/json","application/xml"})
	@ResponseStatus(value=HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
