package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Medicamento;
import br.com.pi_adocao_pet.domain.vo.v1.MedicamentoVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.MedicamentoRepository;

@Service
public class MedicamentoService {
	
	@Autowired
	MedicamentoRepository repository;
	

	private MedicamentoVO convertToMedicamentoVO(Medicamento entity) {
		return DozerConverter.parseObject(entity, MedicamentoVO.class);
	}

	private Medicamento convertToMedicamento(MedicamentoVO vo) {
		return DozerConverter.parseObject(vo, Medicamento.class);
	}

	public MedicamentoVO inserir(MedicamentoVO medicamento) {
		var entity = convertToMedicamento(medicamento);
		var vo = convertToMedicamentoVO(repository.save(entity));
		return vo;
	}

	public Page<MedicamentoVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToMedicamentoVO);
	}

	public MedicamentoVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		return convertToMedicamentoVO(entity);
	}

	public void delete(Long id) {
		Medicamento entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		repository.delete(entity);
	}

	public MedicamentoVO atualizar(MedicamentoVO medicamento) {
		var entity = repository.findById(medicamento.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id."));
		entity.setTipo(medicamento.getTipo());
		entity.setFabricanteMedicamento(medicamento.getFabricanteMedicamento());
		var vo = convertToMedicamentoVO(repository.save(entity));
		return vo;
	}
}