package br.com.pi_adocao_pet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet.domain.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
	Page<Animal> findByDisponibilidade(Boolean disponibilidade, Pageable pageable);
	
}
