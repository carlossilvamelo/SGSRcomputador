package com.SGSRcomputador.frameworkPDS.services;

import java.util.List;

import com.SGSRcomputador.frameworkPDS.models.Loja;


public interface ILojaService {
	public Loja buscarPorId(String id);
	public void inserir(Loja prestadora);
	public void deletar(Loja prestadora);
	public List<Loja> buscarTodos();
}
