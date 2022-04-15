package com.prueba.mercadoLibre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.mercadoLibre.model.Human;

@Repository
public interface IHumanRepo extends JpaRepository<Human, Long>{

	@Query("SELECT count(isMutant) FROM Human Where isMutant = :isMutant")
	long findByisMutant(@Param("isMutant") String isMutant);
}
