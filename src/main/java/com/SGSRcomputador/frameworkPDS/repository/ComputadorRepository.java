package com.SGSRcomputador.frameworkPDS.repository;

import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SGSRcomputador.frameworkPDS.models.Computador;


@Transactional
@Repository
public interface ComputadorRepository extends JpaRepository<Computador, Integer>{
	
	
	@Query("SELECT p FROM  Computador p WHERE p.id = :id")
	ArrayList<Computador> listarPorId(@Param("id") Integer id);
	
	@Modifying
	@Query("DELETE FROM Computador p WHERE p.id = :id")
	void deletePorId(@Param("id") Integer id);

}
