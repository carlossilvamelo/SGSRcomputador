package com.SGSRcomputador.frameworkPDS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGSRcomputador.frameworkPDS.models.MarcaModelo;
import com.SGSRcomputador.frameworkPDS.repository.MarcaModeloRepository;

@Service
public class MarcaModeloService implements IMarcaModelo{

	@Autowired
	private MarcaModeloRepository marcaModeloRepository;
	@Override
	public MarcaModelo buscarPorMarcaModelo(String marca, String modelo) {
		
		return marcaModeloRepository.buscarPorMarcaModelo(marca, modelo);
	}
	@Override
	public List<String> buscarModelosPorMarca(String marca) {
		// TODO Auto-generated method stub
		return marcaModeloRepository.buscarModelosPorMarca(marca);
	}
	
}
