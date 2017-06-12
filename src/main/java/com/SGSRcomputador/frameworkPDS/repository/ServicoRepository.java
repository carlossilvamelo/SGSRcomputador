package com.SGSRcomputador.frameworkPDS.repository;


import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SGSRcomputador.frameworkPDS.models.Computador;
import com.SGSRcomputador.frameworkPDS.models.Cliente;
import com.SGSRcomputador.frameworkPDS.models.Loja;
import com.SGSRcomputador.frameworkPDS.models.Servico;




@Repository
@Transactional
public interface ServicoRepository extends JpaRepository<Servico, Integer>{

	@Query("SELECT s FROM  Servico s WHERE s.cliente = :cliente")
	ArrayList<Servico> listarServicoPorContratante(@Param("cliente") Cliente contratante);
	
	@Query("SELECT s FROM  Servico s WHERE s.loja = :loja")
	ArrayList<Servico> listarServicoPorPrestadora(@Param("loja") Loja prestadora);
	
	@Modifying
	@Query("UPDATE Servico s SET s.computador = '' WHERE s.computador =:computador")
	ArrayList<Servico> updateCelular(@Param("computador") Computador computador);
	
}

