package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Fabricante;
import com.indracompany.catalogo.to.FabricanteTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class FabricanteTOBuilder {
	
	/**
	 * @param fabricanteTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Fabricante createFabricante(FabricanteTO fabricanteTO) {
		
		Fabricante fabricante = null;
		
		if (fabricanteTO != null) {
			fabricante = new Fabricante();
			fabricante.setDtCriacao(fabricanteTO.getDtCriacao());
			fabricante.setDtUltimaAlteracao(fabricanteTO.getDtUltimaAlteracao());
			fabricante.setIdFabricante(fabricanteTO.getIdFabricante());
			fabricante.setNmFabricante(fabricanteTO.getNmFabricante());
			fabricante.setNmUsuarioCriacao(fabricanteTO.getNmUsuarioCriacao());
		}
		
		return fabricante;
	}
	
	/**
	 * @param fabricante
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public FabricanteTO createFabricanteTO(Fabricante fabricante) {
		
		FabricanteTO fabricanteTO = null;
		
		if (fabricante != null) {
			fabricanteTO = new FabricanteTO();
			fabricanteTO.setDtCriacao(fabricante.getDtCriacao());
			fabricanteTO.setDtUltimaAlteracao(fabricante.getDtUltimaAlteracao());
			fabricanteTO.setIdFabricante(fabricante.getIdFabricante());
			fabricanteTO.setNmFabricante(fabricante.getNmFabricante());
			fabricanteTO.setNmUsuarioCriacao(fabricante.getNmUsuarioCriacao());
		}
		
		return fabricanteTO;
	}
	
	/**
	 * @param fabricanteList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<FabricanteTO> createFabricanteTOList(List<Fabricante> fabricanteList) {
		
		List<FabricanteTO> list = new ArrayList<FabricanteTO>();
		
		if (fabricanteList != null && fabricanteList.size() > 0) {
			for (Fabricante fabricante : fabricanteList) {
				list.add(createFabricanteTO(fabricante));
			}
		}
		
		return list;
	}
}
