package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.GrupoProdutoTecnFreqVl;
import com.indracompany.catalogo.datalayer.GrupoProdutoTecnologia;
import com.indracompany.catalogo.datalayer.TecnologiaTpFrequenciaVl;
import com.indracompany.catalogo.to.GrupoProdutoTecnFreqVlTO;
import com.indracompany.catalogo.to.GrupoProdutoTecnologiaTO;
import com.indracompany.catalogo.to.TecnologiaTpFrequenciaVlTO;

/**
 * @author gmuniz
 * 
 */
public class GrupoProdutoTecnFreqVlTOBuilder {
	
	private boolean criarGrupoProdutoTecnologia;
	private boolean criarTecnologiaTpFrequenciaVl;
	
	public GrupoProdutoTecnFreqVlTOBuilder() {
		this(true);
	}
	
	public GrupoProdutoTecnFreqVlTOBuilder(boolean criar) {
		criarGrupoProdutoTecnologia = criar;
		criarTecnologiaTpFrequenciaVl = criar;
	}
	
	/**
	 * @param grupoProdutoTecnFreqVlTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public GrupoProdutoTecnFreqVl createGrupoProdutoTecnFreqVl(GrupoProdutoTecnFreqVlTO grupoProdutoTecnFreqVlTO) {
		GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl = null;
		if (grupoProdutoTecnFreqVlTO != null) {
			
			grupoProdutoTecnFreqVl = new GrupoProdutoTecnFreqVl();
			
			GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO = grupoProdutoTecnFreqVlTO.getGrupoProdutoTecnologiaTO();
			if (grupoProdutoTecnologiaTO != null) {
				grupoProdutoTecnFreqVl.setGrupoProdutoTecnologia(new GrupoProdutoTecnologia(grupoProdutoTecnologiaTO.getIdGrupoProdutoTecn()));
			}
			
			TecnologiaTpFrequenciaVlTO tecnologiaTpFrequenciaVlTO = grupoProdutoTecnFreqVlTO.getTecnologiaTpFrequenciaVlTO();
			if (tecnologiaTpFrequenciaVlTO != null) {
				grupoProdutoTecnFreqVl.setTecnologiaTpFrequenciaVl(new TecnologiaTpFrequenciaVl(tecnologiaTpFrequenciaVlTO.getIdTecnologiaTpFrequenciaVl()));
			}
		}
		return grupoProdutoTecnFreqVl;
	}
	
	/**
	 * @param grupoProdutoTecnFreqVl
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public GrupoProdutoTecnFreqVlTO createGrupoProdutoTecnFreqVlTO(GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl) {
		GrupoProdutoTecnFreqVlTO grupoProdutoTecnFreqVlTO = null;
		
		if (grupoProdutoTecnFreqVl != null) {
			grupoProdutoTecnFreqVlTO = new GrupoProdutoTecnFreqVlTO();
			grupoProdutoTecnFreqVlTO.setIdGrupoProdutoTecnfreqVl(grupoProdutoTecnFreqVl.getIdGrupoProdutoTecnfreqVl());
			
			if (criarGrupoProdutoTecnologia) {
				GrupoProdutoTecnologiaTOBuilder grupoProdutoTecnologiaTOBuilder = new GrupoProdutoTecnologiaTOBuilder();
				GrupoProdutoTecnologia grupoProdutoTecnologia = grupoProdutoTecnFreqVl.getGrupoProdutoTecnologia();
				GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO = grupoProdutoTecnologiaTOBuilder.createGrupoProdutoTecnologiaTO(grupoProdutoTecnologia);
				grupoProdutoTecnFreqVlTO.setGrupoProdutoTecnologiaTO(grupoProdutoTecnologiaTO);
			}
			
			if (criarTecnologiaTpFrequenciaVl) {
				TecnologiaTpFrequenciaVlTOBuilder tecnologiaTpFrequenciaVlTOBuilder = new TecnologiaTpFrequenciaVlTOBuilder();
				TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl = grupoProdutoTecnFreqVl.getTecnologiaTpFrequenciaVl();
				TecnologiaTpFrequenciaVlTO tecnologiaTpFrequenciaVlTO = tecnologiaTpFrequenciaVlTOBuilder.createTecnologiaTpFrequenciaVlTO(tecnologiaTpFrequenciaVl);
				grupoProdutoTecnFreqVlTO.setTecnologiaTpFrequenciaVlTO(tecnologiaTpFrequenciaVlTO);
			}
		}
		
		return grupoProdutoTecnFreqVlTO;
	}
	
	/**
	 * @param grupoProdutoTecnFreqVlList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<GrupoProdutoTecnFreqVlTO> createGrupoProdutoTecnFreqVlTOList(List<GrupoProdutoTecnFreqVl> grupoProdutoTecnFreqVlList) {
		List<GrupoProdutoTecnFreqVlTO> list = new ArrayList<GrupoProdutoTecnFreqVlTO>();
		
		if (grupoProdutoTecnFreqVlList != null && !grupoProdutoTecnFreqVlList.isEmpty()) {
			for (GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl : grupoProdutoTecnFreqVlList) {
				list.add(createGrupoProdutoTecnFreqVlTO(grupoProdutoTecnFreqVl));
			}
		}
		
		return list;
	}
	
	public boolean isCriarGrupoProdutoTecnologia() {
		return criarGrupoProdutoTecnologia;
	}
	
	public void setCriarGrupoProdutoTecnologia(boolean criarGrupoProduto) {
		this.criarGrupoProdutoTecnologia = criarGrupoProduto;
	}
	
	public boolean isCriarTecnologiaTpFrequenciaVl() {
		return criarTecnologiaTpFrequenciaVl;
	}
	
	public void setCriarTecnologiaTpFrequenciaVl(boolean criarTecnologia) {
		this.criarTecnologiaTpFrequenciaVl = criarTecnologia;
	}
	
}