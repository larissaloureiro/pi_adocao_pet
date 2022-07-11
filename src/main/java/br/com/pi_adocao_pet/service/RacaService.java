package br.com.pi_adocao_pet.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Raca;
import br.com.pi_adocao_pet.domain.vo.v1.RacaVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.RacaRepository;

@Service

public class RacaService {

	@Autowired

	RacaRepository repository;

	public RacaVO inserir(RacaVO raca) {
		var entity = DozerConverter.parseObject(raca, Raca.class);
		var vo = DozerConverter.parseObject(repository.save(entity), RacaVO.class);
		return vo;
	}
	
	public Page<RacaVO> buscarTodosPorIdEspecie(long idEspecie, Pageable pageable){
		var page = repository.findAllByIdEspecie(idEspecie, pageable);
		return page.map(this::convertToRacaVO);
	}

	public Page<RacaVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToRacaVO);
	}

	private RacaVO convertToRacaVO(Raca entity) {
		return DozerConverter.parseObject(entity, RacaVO.class);
	}

	public RacaVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		return DozerConverter.parseObject(entity, RacaVO.class);
	}

	public void delete(Long id) {
		Raca entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		repository.delete(entity);
	}

	public RacaVO atualizar(RacaVO raca) {
		var entity = repository.findById(raca.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));

		entity.setNome(raca.getNome());

		var vo = DozerConverter.parseObject(repository.save(entity), RacaVO.class);
		return vo;

	}

}
