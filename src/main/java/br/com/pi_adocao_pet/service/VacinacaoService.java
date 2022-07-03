package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Vacinacao;
import br.com.pi_adocao_pet.domain.vo.v1.VacinacaoVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.VacinacaoRepository;


public class VacinacaoService {

	@Autowired
	VacinacaoRepository repository;

	public VacinacaoVO registrar(VacinacaoVO vacinacao) {
		var entity = DozerConverter.parseObject(vacinacao, Vacinacao.class);
		var vo = DozerConverter.parseObject(repository.save(entity), VacinacaoVO.class);
		return vo;
	}
	
	public Page<VacinacaoVO> consultarVacinacao(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToVacinacaoVO);
	}

	public VacinacaoVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return DozerConverter.parseObject(entity, VacinacaoVO.class);

	}
	
	public Page<VacinacaoVO> mostrarVacinacaoPorIdAnimal(Long idAnimal, Pageable pageable){
		var page = repository.findByIdAnimal(idAnimal, pageable);
		return page.map(this::convertToVacinacaoVO);

	}
	
	private VacinacaoVO convertToVacinacaoVO(Vacinacao entity) {
		return DozerConverter.parseObject(entity, VacinacaoVO.class);
	}

}
