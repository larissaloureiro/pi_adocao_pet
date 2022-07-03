package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Medicacao;
import br.com.pi_adocao_pet.domain.vo.v1.MedicacaoVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.MedicacaoRepository;

@Service
public class MedicacaoService {
	
	@Autowired
	MedicacaoRepository repository;
	

	private MedicacaoVO convertToMedicacaoVO(Medicacao entity) {
		return DozerConverter.parseObject(entity, MedicacaoVO.class);
	}

	private Medicacao convertToMedicacao(MedicacaoVO vo) {
		return DozerConverter.parseObject(vo, Medicacao.class);
	}

	public MedicacaoVO inserir(MedicacaoVO medicacao) {
		var entity = convertToMedicacao(medicacao);
		var vo = convertToMedicacaoVO(repository.save(entity));
		return vo;
	}

	public Page<MedicacaoVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToMedicacaoVO);
	}

	public Page<MedicacaoVO> buscarTodosPorIdAnimal(long idAnimal, Pageable pageable){
		var page = repository.findAllByIdAnimal(idAnimal, pageable);
		return page.map(this::convertToMedicacaoVO);
	}

	public MedicacaoVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		return convertToMedicacaoVO(entity);
	}

	public void delete(Long id) {
		Medicacao entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		repository.delete(entity);
	}

	public MedicacaoVO atualizar(MedicacaoVO medicacao) {
		var entity = repository.findById(medicacao.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		entity.setIdAnimal(medicacao.getIdAnimal());
		entity.setIdMedicamento(medicacao.getIdMedicamento());
		entity.setIdFuncionario(medicacao.getIdFuncionario());
		entity.setDataMedicacao(medicacao.getDataMedicacao());
		entity.setDosagem(medicacao.getDosagem());
		var vo = convertToMedicacaoVO(repository.save(entity));
		return vo;
	}
}