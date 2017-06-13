package com.SGSRcomputador.frameworkPDS.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGSRcomputador.frameworkPDS.models.CheckIn;
import com.SGSRcomputador.frameworkPDS.models.Cliente;
import com.SGSRcomputador.frameworkPDS.models.Computador;
import com.SGSRcomputador.frameworkPDS.models.Loja;
import com.SGSRcomputador.enumeracoes.EnumStatus;
import com.SGSRcomputador.frameworkPDS.models.Servico;
import com.SGSRcomputador.frameworkPDS.repository.CheckInRepository;
import com.SGSRcomputador.frameworkPDS.repository.ServicoRepository;


@Service
public class ServicoService implements IServicoService{

	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private CheckInRepository checkInRepository;

	
	/**
	 * Requisito gera uma atualização do status do serviço
	 * 
	 * 
	 * @param idServico
	 */
	
	@Override
	public List<Servico> buscarServicosPorContratante(Cliente contratante) {
		return servicoRepository.listarServicoPorContratante(contratante);
	}
	
	@Override
	public List<Servico> buscarServicosPorPrestadora(Loja prestadora) {
		return servicoRepository.listarServicoPorPrestadora(prestadora);
	}

	@Override
	public void inserir(Servico servico) {
		servicoRepository.save(servico);
	}

	@Override
	public void deletar(Servico servico) {
		servicoRepository.delete(servico);
	}
	
	

	@Override
	public Servico buscarPorId(Integer id) {
		return servicoRepository.getOne(id);
	}

	@Override
	public List<Servico> buscarTodos() {
		return servicoRepository.findAll();
	}


	@Override
	public List<Servico> buscarServicosPorIdContratante(String idCliente) {
		return null;
	}
	
	@Override
	public void atualizarProduto(Computador produto) {
		servicoRepository.updateComputador(produto);
	}

	@Override
	public List<CheckIn> listarCheckIn(Integer id) {
		// TODO Auto-generated method stub
		return checkInRepository.listarCheckInPorServico(id);
	}
	
	@Override
	public void verificarServico(Integer id){
		
		/*Servico servi = servicoRepository.findOne(id);
		
		for (CheckIn checkin : servi.getCheckIns()) {
			
			if(checkin.getClassficacao() == 0){
				servicoRepository.delete(servi);
			}
			
		}*/
	}

	@Override
	public void deletarTodos(Computador produto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void proximoStatus(Integer idServico) {
		Servico ServicoAtualizado = buscarPorId(idServico);
		int status = ServicoAtualizado.getStatus().ordinal() + 1;
		ServicoAtualizado.setStatus(EnumStatus.fromInteger(status));
		inserir(ServicoAtualizado);
	}
	
	@Override
	public void aprovarOrcamentoServico(Integer idServico){
		Servico ServicoAtualizado = buscarPorId(idServico);
		ServicoAtualizado.setStatus(EnumStatus.SERVICO_AUTORIZADO);
		inserir(ServicoAtualizado);
	}
	
	@Override
	public void elaboracaoOrcamento(Integer idServico) {
		Servico servicoAtualizado = buscarPorId(idServico);
		servicoAtualizado.setStatus(EnumStatus.ELABORACAO_ORCAMENTO);
		inserir(servicoAtualizado);
	}
	
	@Override
	public void cancelado(Integer idServico) {
		Servico servicoAtualizado = buscarPorId(idServico);
		servicoAtualizado.setStatus(EnumStatus.CANCELADO);
		inserir(servicoAtualizado);
	}
	
	@Override
	public void aguardandoPecas(Integer idServico) {
		Servico servicoAtualizado = buscarPorId(idServico);
		servicoAtualizado.setStatus(EnumStatus.AGUARDANDO_PECAS);
		inserir(servicoAtualizado);
	}

	@Override
	public void aguardandoCliente(Integer idServico) {
		Servico servicoAtualizado = buscarPorId(idServico);
		servicoAtualizado.setStatus(EnumStatus.AGUARDANDO_CLIENTE);
		inserir(servicoAtualizado);
	}

	@Override
	public void emAndamento(Integer idServico) {
		Servico servicoAtualizado = buscarPorId(idServico);
		servicoAtualizado.setStatus(EnumStatus.EM_ANDAMENTO);
		inserir(servicoAtualizado);
	}

	@Override
	public void finalizado(Integer idServico) {
		Servico servicoAtualizado = buscarPorId(idServico);
		servicoAtualizado.setStatus(EnumStatus.FINALIZADO);
		inserir(servicoAtualizado);
	}
	
}