package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Especie;
import br.com.pi_adocao_pet.domain.vo.v1.EspecieVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.EspecieRepository;

@Service
public class EspecieService {
	
	@Autowired

	EspecieRepository repository;

	public EspecieVO inserir(EspecieVO especie) {
		var entity = DozerConverter.parseObject(especie, Especie.class);
		var vo = DozerConverter.parseObject(repository.save(entity), EspecieVO.class);
		return vo;
	}

	public Page<Especie> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page;
	}
	
	private EspecieVO convertToEspecieVO(Especie entity) {
		return DozerConverter.parseObject(entity, EspecieVO.class);
	}


	public EspecieVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		return DozerConverter.parseObject(entity, EspecieVO.class);
	}
	

	public EspecieVO atualizar(EspecieVO especie) {
		var entity = repository.findById(especie.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));

		entity.setNome(especie.getNome());

		var vo = DozerConverter.parseObject(repository.save(entity), EspecieVO.class);
		return vo;

	}

	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);

	}

	

}



