package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.GrupoProduto;
import com.indracompany.catalogo.datalayer.GrupoProdutoTecnologia;
import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.GrupoProdutoTecnologiaTO;
import com.indracompany.catalogo.to.TecnologiaTO;

/**
 * @author gmuniz
 * 
 */
public class GrupoProdutoTecnologiaTOBuilder {
	
	private boolean criarGrupoProduto;
	private boolean criarTecnologia;
	
	public GrupoProdutoTecnologiaTOBuilder() {
		this(true);
	}
	
	public GrupoProdutoTecnologiaTOBuilder(boolean criar) {
		criarGrupoProduto = criar;
		criarTecnologia = criar;
	}
	
	/**
	 * @param grupoProdutoTecnologiaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public GrupoProdutoTecnologia createGrupoProdutoTecnologia(GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO) {
		GrupoProdutoTecnologia grupoProdutoTecnologia = null;
		if (grupoProdutoTecnologiaTO != null) {
			
			grupoProdutoTecnologia = new GrupoProdutoTecnologia();
			
			GrupoProdutoTO grupoProdutoTO = grupoProdutoTecnologiaTO.getGrupoProdutoTO();
			if (grupoProdutoTO != null) {
				grupoProdutoTecnologia.setGrupoProduto(new GrupoProduto(grupoProdutoTO.getIdGrupoProduto()));
			}
			
			TecnologiaTO tecnologiaTO = grupoProdutoTecnologiaTO.getTecnologiaTO();
			if (tecnologiaTO != null) {
				grupoProdutoTecnologia.setTecnologia(new Tecnologia(tecnologiaTO.getIdTecnologia()));
			}
		}
		return grupoProdutoTecnologia;
	}
	
	/**
	 * @param grupoProdutoTecnologia
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public GrupoProdutoTecnologiaTO createGrupoProdutoTecnologiaTO(GrupoProdutoTecnologia grupoProdutoTecnologia) {
		GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO = null;
		
		if (grupoProdutoTecnologia != null) {
			grupoProdutoTecnologiaTO = new GrupoProdutoTecnologiaTO();
			grupoProdutoTecnologiaTO.setIdGrupoProdutoTecn(grupoProdutoTecnologia.getIdGrupoProdutoTecn());
			
			if (criarGrupoProduto) {
				GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder(false);
				GrupoProduto grupoProduto = grupoProdutoTecnologia.getGrupoProduto();
				GrupoProdutoTO grupoProdutoTO = grupoProdutoTOBuilder.createGrupoProdutoTO(grupoProduto);
				grupoProdutoTecnologiaTO.setGrupoProdutoTO(grupoProdutoTO);
			}
			
			if (criarTecnologia) {
				TecnologiaTOBuilder tecnologiaTOBuilder = new TecnologiaTOBuilder();
				Tecnologia tecnologia = grupoProdutoTecnologia.getTecnologia();
				TecnologiaTO tecnologiaTO = tecnologiaTOBuilder.createTecnologiaTO(tecnologia);
				grupoProdutoTecnologiaTO.setTecnologiaTO(tecnologiaTO);
			}
		}
		
		return grupoProdutoTecnologiaTO;
	}
	
	/**
	 * @param grupoProdutoTecnologiaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<GrupoProdutoTecnologiaTO> createGrupoProdutoTecnologiaTOList(List<GrupoProdutoTecnologia> grupoProdutoTecnologiaList) {
		List<GrupoProdutoTecnologiaTO> list = new ArrayList<GrupoProdutoTecnologiaTO>();
		
		if (grupoProdutoTecnologiaList != null && !grupoProdutoTecnologiaList.isEmpty()) {
			for (GrupoProdutoTecnologia grupoProdutoTecnologia : grupoProdutoTecnologiaList) {
				list.add(createGrupoProdutoTecnologiaTO(grupoProdutoTecnologia));
			}
		}
		
		return list;
	}
	
	public boolean isCriarGrupoProduto() {
		return criarGrupoProduto;
	}
	
	public void setCriarGrupoProduto(boolean criarGrupoProduto) {
		this.criarGrupoProduto = criarGrupoProduto;
	}
	
	public boolean isCriarTecnologia() {
		return criarTecnologia;
	}
	
	public void setCriarTecnologia(boolean criarTecnologia) {
		this.criarTecnologia = criarTecnologia;
	}
	
}