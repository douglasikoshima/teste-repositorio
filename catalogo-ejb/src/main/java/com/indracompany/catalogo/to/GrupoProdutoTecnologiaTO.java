package com.indracompany.catalogo.to;

import java.io.Serializable;

public class GrupoProdutoTecnologiaTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idGrupoProdutoTecn;
	private GrupoProdutoTO grupoProdutoTO;
	private TecnologiaTO tecnologiaTO;
	
	public GrupoProdutoTecnologiaTO() {
	}
	
	public GrupoProdutoTecnologiaTO(Integer idGrupoProdutoTecn) {
		this.idGrupoProdutoTecn = idGrupoProdutoTecn;
	}
	
	public GrupoProdutoTecnologiaTO(GrupoProdutoTO grupoProdutoTO, TecnologiaTO tecnologiaTO) {
		this.grupoProdutoTO = grupoProdutoTO;
		this.tecnologiaTO = tecnologiaTO;
	}
	
	public Integer getIdGrupoProdutoTecn() {
		return idGrupoProdutoTecn;
	}
	
	public void setIdGrupoProdutoTecn(Integer idGrupoProdutoTecn) {
		this.idGrupoProdutoTecn = idGrupoProdutoTecn;
	}
	
	public GrupoProdutoTO getGrupoProdutoTO() {
		return grupoProdutoTO;
	}
	
	public void setGrupoProdutoTO(GrupoProdutoTO grupoProdutoTO) {
		this.grupoProdutoTO = grupoProdutoTO;
	}
	
	public TecnologiaTO getTecnologiaTO() {
		return tecnologiaTO;
	}
	
	public void setTecnologiaTO(TecnologiaTO tecnologiaTO) {
		this.tecnologiaTO = tecnologiaTO;
	}
	
}