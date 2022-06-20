package br.com.pi_adocao_pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pi_adocao_pet.domain.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository <Login, Long>{
	
	@Query("SELECT u FROM User u WHERE u.username =: username")
	Login findByUsername(@Param("username") String username);
}
