package br.com.pi_adocao_pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pi_adocao_pet.adapter.DozerConverter;
import br.com.pi_adocao_pet.domain.entity.Endereco;
import br.com.pi_adocao_pet.domain.entity.Funcionario;
import br.com.pi_adocao_pet.domain.vo.v1.FuncionarioVO;
import br.com.pi_adocao_pet.exception.ResourceNotFoundException;
import br.com.pi_adocao_pet.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	FuncionarioRepository repository;

	public FuncionarioVO inserir(FuncionarioVO funcionario) {
		var entity = DozerConverter.parseObject(funcionario, Funcionario.class);
		if (entity.validaCpf()){
			var vo = DozerConverter.parseObject(repository.save(entity), FuncionarioVO.class);
			return vo;
		}
	}

	public Page<FuncionarioVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToFuncionarioVO);
	}

	public FuncionarioVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));
		return DozerConverter.parseObject(entity, FuncionarioVO.class);
	}

	public void delete(Long id) {
		Funcionario entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));
		repository.delete(entity);
	}

	public FuncionarioVO atualizar(FuncionarioVO funcionario) {
		var entity = repository.findById(funcionario.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse ID."));

		entity.setEndereco(DozerConverter.parseObject(funcionario.getEndereco(), Endereco.class));
		entity.setNome(funcionario.getNome());
		entity.setTelefone(funcionario.getTelefone());
		entity.setEmail(funcionario.getEmail());
		entity.setRg(funcionario.getRg());
		entity.setCpf(funcionario.getCpf());

		entity.setCargo(funcionario.getCargo());
		entity.setCarteiraTrabalho(funcionario.getCarteiraTrabalho());
		entity.setDataAdmissao(funcionario.getDataAdmissao());
		
		if (entity.validaCpf()){
			var vo = DozerConverter.parseObject(repository.save(entity), FuncionarioVO.class);
			return vo;
		}
	}

	private FuncionarioVO convertToFuncionarioVO(Funcionario entity) {
		return DozerConverter.parseObject(entity, FuncionarioVO.class);
	}

}
