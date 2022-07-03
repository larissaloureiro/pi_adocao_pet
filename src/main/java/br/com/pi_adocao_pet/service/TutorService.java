package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Endereco;
import br.com.pi_adocao_pet.domain.entity.Tutor;
import br.com.pi_adocao_pet.domain.vo.v1.TutorVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.TutorRepository;

@Service
public class TutorService {
	
	@Autowired
	TutorRepository repository;
	
	public TutorVO inserir(TutorVO tutor) {
		var entity = DozerConverter.parseObject(tutor, Tutor.class);
		if (entity.validaCpf() && entity.possuiIdadeMinima()) {
			var vo = DozerConverter.parseObject(repository.save(entity), TutorVO.class);
			return vo;
		}
	}
	
	public Page<TutorVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToTutorVO);
	}
	
	public TutorVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));		
		return DozerConverter.parseObject(entity, TutorVO.class);
	}
	
	public void delete(Long id) {
		Tutor entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));
		repository.delete(entity);
	}
	
	public TutorVO atualizar(TutorVO tutor) {
		var entity = repository.findById(tutor.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));
		
		entity.setEndereco(DozerConverter.parseObject(tutor.getEndereco(), Endereco.class));
		entity.setNome(tutor.getNome());
		entity.setTelefone(tutor.getTelefone());
		entity.setEmail(tutor.getEmail());
		entity.setRg(tutor.getRg());
		entity.setCpf(tutor.getCpf());
		
		entity.setDataNascimento(tutor.getDataNascimento());
		entity.setDataCadastro(tutor.getDataCadastro());
		if (entity.validaCpf() && entity.possuiIdadeMinima()) {
			var vo = DozerConverter.parseObject(repository.save(entity), TutorVO.class);
			return vo;
		}
		
	}
	
	
	private TutorVO convertToTutorVO(Tutor entity) {
		return DozerConverter.parseObject(entity, TutorVO.class);
	}

}
