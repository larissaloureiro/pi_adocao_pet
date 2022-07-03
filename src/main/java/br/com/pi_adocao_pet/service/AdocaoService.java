package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Adocao;
import br.com.pi_adocao_pet.domain.vo.v1.AdocaoVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.AdocaoRepository;


public class AdocaoService {
	@Autowired
	AdocaoRepository repository;

	public AdocaoVO inserir(AdocaoVO endereco) {
		var entity = DozerConverter.parseObject(endereco, Adocao.class);
		var vo = DozerConverter.parseObject(repository.save(entity), AdocaoVO.class);
		return vo;
	}

	public Page<AdocaoVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToAdocaoVO);
	}

	private AdocaoVO convertToAdocaoVO(Adocao entity) {
		return DozerConverter.parseObject(entity, AdocaoVO.class);
	}

	public AdocaoVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		return DozerConverter.parseObject(entity, AdocaoVO.class);
	}

	public void delete(Long id) {
		Adocao entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		repository.delete(entity);
	}

	public AdocaoVO atualizar(AdocaoVO adocao) {
		var entity = repository.findById(adocao.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		entity.setStatus(adocao.getStatus());
		entity.setDataSolicitacao(adocao.getDataSolicitacao());
		entity.setDataAtualizacao(adocao.getDataAtualizacao());
		
		var vo = DozerConverter.parseObject(repository.save(entity), AdocaoVO.class);
		return vo;
	}

}

