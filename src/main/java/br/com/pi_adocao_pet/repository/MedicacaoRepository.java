package br.com.pi_adocao_pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet.domain.entity.Medicacao;

@Repository
public interface MedicacaoRepository extends JpaRepository<Medicacao, Long> {

}
