package com.SGSRcomputador.enumeracoes;
public enum TipoAlerta {
	
		REVISAO,TROCAPNEU,TROCAOLEO, ALINHAMENTO_BALANCEAMENTO;
	
	public Integer getId(){
		return this.ordinal();
	}
}
