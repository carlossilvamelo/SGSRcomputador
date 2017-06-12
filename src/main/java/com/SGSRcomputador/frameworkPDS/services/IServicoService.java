package com.SGSRcomputador.frameworkPDS.services;

import java.util.List;

import com.SGSRcomputador.frameworkPDS.models.Computador;
import com.SGSRcomputador.frameworkPDS.models.CheckIn;
import com.SGSRcomputador.frameworkPDS.models.Cliente;
import com.SGSRcomputador.frameworkPDS.models.Loja;
import com.SGSRcomputador.frameworkPDS.models.Servico;


public interface IServicoService {

	public List<Servico> buscarServicosPorIdContratante(String contratante);
	
	//acesso ao banco
	public void inserir(Servico servico);
	public void deletar(Servico servico);
	public Servico buscarPorId(Integer id);
	public List<Servico> buscarTodos();
	public List<Servico> buscarServicosPorContratante(Cliente contratante);
	public List<Servico> buscarServicosPorPrestadora(Loja prestadora);
	public void atualizarProduto(Computador produto);
	public void deletarTodos(Computador produto);
	public List<CheckIn> listarCheckIn(Integer id);
	public void verificarServico(Integer id);

	void proximoStatus(Integer idServico);

	void aprovarOrcamentoServico(Integer idServico);

	void vistoriaPendente(Integer idServico);

	void naoAutorizado(Integer idServico);

	void aguardandoPecas(Integer idServico);

	void aguardandoCliente(Integer idServico);

	void emAndamento(Integer idServico);

	void finalizado(Integer idServico);
	
	
	
}