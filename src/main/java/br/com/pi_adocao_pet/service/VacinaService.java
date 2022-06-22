package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Vacina;
import br.com.pi_adocao_pet.domain.vo.v1.VacinaVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.VacinaRepository;

@Service
public class VacinaService {

	@Autowired
	VacinaRepository repository;

	public VacinaVO inserir(VacinaVO vacina) {
		var entity = DozerConverter.parseObject(vacina, Vacina.class);
		var vo = DozerConverter.parseObject(repository.save(entity), VacinaVO.class);
		return vo;
	}

	public Page<VacinaVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToVacinaVO);
	}

	private VacinaVO convertToVacinaVO(Vacina entity) {
		return DozerConverter.parseObject(entity, VacinaVO.class);
	}

	public VacinaVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return DozerConverter.parseObject(entity, VacinaVO.class);
	}

	public VacinaVO atualizar(VacinaVO vacina) {
		var entity = repository.findById(vacina.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));

		entity.setId(vacina.getKey());
		entity.setTipo(vacina.getTipo());
		entity.setFabricanteVacina(vacina.getFabricante());

		var vo = DozerConverter.parseObject(repository.save(entity), VacinaVO.class);
		return vo;
	}

	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);
	}
	private VacinaVO convertToPessoaVO(Vacina entity) {
        return DozerConverter.parseObject(entity, VacinaVO.class);
}
}
