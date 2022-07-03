package br.com.pi_adocao_pet.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet.domain.entity.Medicacao;

@Repository
public interface MedicacaoRepository extends JpaRepository<Medicacao, Long> {

    @Query("Select m from Medicacao m where m.idAnimal.id= :id")
	Page<Medicacao> findAllByIdAnimal(@Param("id") Long id, Pageable pageable);

}
