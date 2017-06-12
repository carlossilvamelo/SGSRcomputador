package com.SGSRcomputador.frameworkPDS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGSRcomputador.frameworkPDS.models.Loja;


@Repository
public interface LojaRepository extends JpaRepository<Loja, String>{

}
