package com.SGSRcomputador.frameworkPDS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGSRcomputador.frameworkPDS.models.Computador;
import com.SGSRcomputador.frameworkPDS.models.MarcaModelo;
import com.SGSRcomputador.frameworkPDS.repository.ComputadorRepository;
import com.SGSRcomputador.frameworkPDS.repository.MarcaModeloRepository;



@Service
public class ComputadorService implements IComputadorService{

	@Autowired
	private ComputadorRepository computadorRepository;
	
	@Autowired
	private MarcaModeloRepository modeloRepository;
	

	@Override
	public void inserir(Computador computador) {
		computadorRepository.save(computador);
	}

	@Override
	public Computador buscarPorId(Integer id) {
		return computadorRepository.findOne(id);
	}
	@Override
	public void deletarTodos(Computador produto) {
		
		/*for(Servico serv: produto.getServicos()){
			servicoRepository.delete(serv);
		}*/
		
	}
	@Override
	public List<Computador> buscarTodos() {
		return computadorRepository.findAll();
	}
	
	@Override
	public void deletar(Computador produto) {
		computadorRepository.delete(produto);
	}
	
	@Override
	public void deletePorId(Integer idProduto) {
		
		computadorRepository.deletePorId(idProduto);
	}
	
	@Override
	public void deleteAlertasProduto(Computador produto) {
		//TODO criar esse método
		
		//dataFacade.getAlertaRepository().deleteByTipoAlerta(veiculo);
	}

	@Override
	public List<String> buscarModelosPorMarca(String marca) {
		// TODO Auto-generated method stub
		return modeloRepository.buscarModelosPorMarca(marca);
	}

	@Override
	public MarcaModelo buscarPorMarcaModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return modeloRepository.buscarPorMarcaModelo(marca, modelo);
	}

	@Override
	public List<String> buscarMarcas() {
		// TODO Auto-generated method stub
		return modeloRepository.buscarMarcas();
	}

	
	

}
