package br.com.pi_adocao_pet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet.domain.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

	
	List<Animal> findAllByDisponibilidade(boolean b);

	@Query("Select m from Animal m where m.idEspecie.id= :id")
	Page<Animal> findAllByIdEspecie(@Param("id") Long id, Pageable pageable);
	
	
}
