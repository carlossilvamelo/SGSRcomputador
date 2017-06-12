package com.SGSRcomputador.frameworkPDS.services;

import java.util.List;

import com.SGSRcomputador.frameworkPDS.models.Computador;
import com.SGSRcomputador.frameworkPDS.models.MarcaModelo;


public interface IComputadorService {
	
	//acesso ao banco
	public void inserir(Computador produto);
	
	public void deletar(Computador produto);
	public void deletePorId(Integer idProduto);
	
	public Computador buscarPorId(Integer id);
	public List<Computador> buscarTodos();

	public void deleteAlertasProduto(Computador produto);

	public void deletarTodos(Computador produto);
	
	public List<String> buscarModelosPorMarca(String marca);
	
	public MarcaModelo buscarPorMarcaModelo(String marca, String modelo);
	
	public List<String> buscarMarcas();
	

	
	
	
}
