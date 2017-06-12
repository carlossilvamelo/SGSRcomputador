package com.SGSRcomputador.frameworkPDS.services;

import java.util.List;

import com.SGSRcomputador.frameworkPDS.models.Peca;



public interface IPecaService {

	//acesso ao banco
	public void inserir(Peca peca);
	public void deletar(Peca peca);
	public List<Peca> buscarTodos();
	public Peca buscarPorId(Long id);
}
