package com.SGSRcomputador.frameworkPDS.services;

import java.util.List;

import com.SGSRcomputador.frameworkPDS.models.CheckIn;


public interface ICheckInService {

	public void inserir(CheckIn checkIn);

	List<CheckIn> buscarCheckInPorId(Integer idServico);

}
