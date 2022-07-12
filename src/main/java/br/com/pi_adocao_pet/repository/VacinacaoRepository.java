package br.com.pi_adocao_pet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet.domain.entity.Vacinacao;

@Repository
public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {
	
	@Query("Select v from Vacinacao v where v.idAnimal.id= :id")
	Page<Vacinacao> findByIdAnimal(@Param("id") Long idAnimal, Pageable pageable);

}
