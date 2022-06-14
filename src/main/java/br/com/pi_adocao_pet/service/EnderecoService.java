package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Endereco;
import br.com.pi_adocao_pet.domain.vo.v1.EnderecoVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository repository;

	public EnderecoVO inserir(EnderecoVO endereco) {
		var entity = DozerConverter.parseObject(endereco, Endereco.class);
		var vo = DozerConverter.parseObject(repository.save(entity), EnderecoVO.class);
		return vo;
	}

	public Page<EnderecoVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToEnderecoVO);
	}

	private EnderecoVO convertToEnderecoVO(Endereco entity) {
		return DozerConverter.parseObject(entity, EnderecoVO.class);
	}

	public EnderecoVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		return DozerConverter.parseObject(entity, EnderecoVO.class);
	}

	public void delete(Long id) {
		Endereco entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		repository.delete(entity);
	}

	public EnderecoVO atualizar(EnderecoVO endereco) {
		var entity = repository.findById(endereco.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		entity.setLogradouro(endereco.getLogradouro());
		entity.setCep(endereco.getCep());
		entity.setNumero(endereco.getNumero());
		entity.setComplemento(endereco.getComplemento());
		entity.setReferencia(endereco.getReferencia());

		var vo = DozerConverter.parseObject(repository.save(entity), EnderecoVO.class);
		return vo;
	}

}