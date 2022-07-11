package br.com.pi_adocao_pet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet.domain.entity.Raca;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Long> {
	
	
	@Query("Select m from Adocao m where m.idTutor.id= :id")
	Page<Raca> findAllByIdEspecie(@Param("id") Long id, Pageable pageable);
}
