package br.com.pi_adocao_pet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet.domain.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

	
	List<Animal> findAllByDisponibilidade(boolean b);

	@Query("Select a from Animal a where a.idRaca.idEspecie.id= :id")
	List<Animal> findByIdEspecie(@Param("id") Long id);
	
	
}
