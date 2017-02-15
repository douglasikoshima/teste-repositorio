package com.indracompany.catalogo.ejb.analisecredito.configuracao;

public enum CanalAtendimentoConfiguravelPorRegional {
	CRC(1), LJ(2), LJA(26), DEAREV(1262), TLVD(23611);
	private int idCanalAtendimento;
	
	CanalAtendimentoConfiguravelPorRegional(int idCanalAtendimento){
		this.idCanalAtendimento = idCanalAtendimento;
	}

	public int getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
}