package com.SGSRcomputador.enumeracoes;
public enum EnumStatus {
	PRE_DIAGNOSTICO("Pré-diagnostico"),//status 0
	ELABORACAO_ORCAMENTO("Elaboracao orçamento"),//status 1 
	AUTORIZACAO_PENDENTE("Autorizacao pendente"),//status 2 
	SERVICO_AUTORIZADO("Serviço autorizado"), //status 3
	AGUARDANDO_PECAS("Aguardando peças"), //status 5
	AGUARDANDO_CLIENTE("Aguardando cliente"), //status 6
	EM_ANDAMENTO("Em andamento"),//status 7
	FINALIZADO("Finalizado"),//status 9
	CANCELADO("Cancelado");// status 10
	
	private String titulo;
	
	EnumStatus(String titulo){
		this.titulo = titulo;
	}

	public static String getTitulo(Integer id) {
		
		for (EnumStatus marca : EnumStatus.values()) {
			
			System.out.println(marca.ordinal());
			
			if(marca.ordinal() == id){
				return EnumStatus.valueOf(marca.toString()).titulo;
			}
		}
		return "--";
	}

	public Integer getId(){
		return this.ordinal();
	}
	
	public static EnumStatus fromInteger(int status){
		switch (status) {
		case 0:
			return PRE_DIAGNOSTICO;
		case 1:
			return ELABORACAO_ORCAMENTO;
		case 2:
			return AUTORIZACAO_PENDENTE;
		case 3:
			return SERVICO_AUTORIZADO;
		case 4:
			return CANCELADO;
		case 5:
			return AGUARDANDO_PECAS;
		case 6:
			return AGUARDANDO_CLIENTE;
		case 7:
			return EM_ANDAMENTO;
		case 9:
			return FINALIZADO;
		default:
			return AUTORIZACAO_PENDENTE;
		}
	}
	
}
