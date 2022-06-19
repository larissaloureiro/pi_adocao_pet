package br.com.pi_adocao_pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet.domain.entity.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

}
