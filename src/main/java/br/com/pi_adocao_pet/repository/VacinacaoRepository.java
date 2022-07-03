package br.com.pi_adocao_pet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.pi_adocao_pet.domain.entity.Vacinacao;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {
	@Query("SELECT id_vacina " + "FROM vacina " + "JOIN vacinacao" + "  ON vacina.id_vacina = vacinacao.id_vacina "
			+ "JOIN animal" + "  ON vacinacao.id_animal = animal.id_animal " + "WHERE id_animal = :id")
	Page<Vacinacao> findByIdAnimal(@Param("id") Long idAnimal, Pageable pageable);

}
