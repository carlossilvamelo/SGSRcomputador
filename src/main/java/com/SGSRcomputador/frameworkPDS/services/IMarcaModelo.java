package com.SGSRcomputador.frameworkPDS.services;


import java.util.List;

import com.SGSRcomputador.frameworkPDS.models.MarcaModelo;

public interface IMarcaModelo {

	public MarcaModelo buscarPorMarcaModelo(String marca, String modelo);
	public List<String> buscarModelosPorMarca(String marca);
}
