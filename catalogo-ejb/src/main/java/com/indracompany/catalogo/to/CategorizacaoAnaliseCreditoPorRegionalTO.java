package com.indracompany.catalogo.to;

import java.util.Map;

public class CategorizacaoAnaliseCreditoPorRegionalTO extends
		CategorizacaoAnaliseCreditoTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6039329435553886088L;
	public RegionalTO regionalTO;
	
	public CategorizacaoAnaliseCreditoPorRegionalTO(Integer id, String nome, String nmPlataforma, Integer idPlataforma, String nmCategoria, Integer idCategoria, Integer[] idPlataformas, Integer[] idRegionais, Float valor, Float precoDe, Float precoAte, Integer idCategoriaScore, Integer idAnaliseCredito, Integer idCabecalhoAnaliseCredito, Map<String, Boolean> scoresConfigurados, RegionalTO regionalTO) {
		super(id, nome, nmPlataforma, idPlataforma,
				nmCategoria, idCategoria, idPlataformas, idRegionais, valor,
				precoDe, precoAte, idCategoriaScore, idAnaliseCredito,
				idCabecalhoAnaliseCredito, scoresConfigurados);
		this.regionalTO = regionalTO;
	}

	public RegionalTO getRegionalTO() {
		return regionalTO;
	}

	public void setRegionalTO(RegionalTO regionalTO) {
		this.regionalTO = regionalTO;
	}
}
