package com.indracompany.catalogo.to;

import java.io.Serializable;

public class GrupoProdutoTecnFreqVlTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idGrupoProdutoTecnfreqVl;
	private GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO;
	private TecnologiaTpFrequenciaVlTO tecnologiaTpFrequenciaVlTO;
	
	public GrupoProdutoTecnFreqVlTO() {
	}
	
	public GrupoProdutoTecnFreqVlTO(Integer idGrupoProdutoTecnfreqVl) {
		this.idGrupoProdutoTecnfreqVl = idGrupoProdutoTecnfreqVl;
	}
	
	public GrupoProdutoTecnFreqVlTO(GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO, TecnologiaTpFrequenciaVlTO tecnologiaTpFrequenciaVlTO) {
		this.grupoProdutoTecnologiaTO = grupoProdutoTecnologiaTO;
		this.tecnologiaTpFrequenciaVlTO = tecnologiaTpFrequenciaVlTO;
	}
	
	public Integer getIdGrupoProdutoTecnfreqVl() {
		return idGrupoProdutoTecnfreqVl;
	}
	
	public void setIdGrupoProdutoTecnfreqVl(Integer idGrupoProdutoTecnfreqVl) {
		this.idGrupoProdutoTecnfreqVl = idGrupoProdutoTecnfreqVl;
	}
	
	public GrupoProdutoTecnologiaTO getGrupoProdutoTecnologiaTO() {
		return grupoProdutoTecnologiaTO;
	}
	
	public void setGrupoProdutoTecnologiaTO(
			GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO) {
		this.grupoProdutoTecnologiaTO = grupoProdutoTecnologiaTO;
	}
	
	public TecnologiaTpFrequenciaVlTO getTecnologiaTpFrequenciaVlTO() {
		return tecnologiaTpFrequenciaVlTO;
	}
	
	public void setTecnologiaTpFrequenciaVlTO(
			TecnologiaTpFrequenciaVlTO tecnologiaTpFrequenciaVlTO) {
		this.tecnologiaTpFrequenciaVlTO = tecnologiaTpFrequenciaVlTO;
	}
	
}