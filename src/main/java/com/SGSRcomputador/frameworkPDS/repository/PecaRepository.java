package com.SGSRcomputador.frameworkPDS.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SGSRcomputador.frameworkPDS.models.Peca;


@Repository
public interface PecaRepository extends JpaRepository<Peca, Long>{

}
