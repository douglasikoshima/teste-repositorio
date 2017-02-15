package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ContratoMatrizOfertaCritica;
import com.indracompany.catalogo.to.ContratoMatrizOfertaCriticaTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class ContratoMatrizOfertaCriticaTOBuilder {
	
	private ContratoMatrizOfertaTOBuilder contratoMatrizOfertaTOBuilder = new ContratoMatrizOfertaTOBuilder();
	private MatrizOfertaContratoCriticaTOBuilder matrizOfertaContratoCriticaTOBuilder = new MatrizOfertaContratoCriticaTOBuilder();
	
	/**
	 * @param contratoMatrizOfertaCriticaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public ContratoMatrizOfertaCritica createContratoMatrizOfertaCritica(ContratoMatrizOfertaCriticaTO contratoMatrizOfertaCriticaTO) {
		
		ContratoMatrizOfertaCritica contratoMatrizOfertaCritica = null;
		
		if (contratoMatrizOfertaCriticaTO != null) {
			contratoMatrizOfertaCritica = new ContratoMatrizOfertaCritica();
			contratoMatrizOfertaCritica.setContratoMatrizOferta(contratoMatrizOfertaTOBuilder.createContratoMatrizOferta(contratoMatrizOfertaCriticaTO.getContratoMatrizOfertaTO()));
			contratoMatrizOfertaCritica.setIdContratoMatrizOfertaCritica(contratoMatrizOfertaCriticaTO.getIdContratoMatrizOfertaCritica());
			contratoMatrizOfertaCritica.setMatrizOfertaContratoCritica(matrizOfertaContratoCriticaTOBuilder.createMatrizOfertaContratoCritica(contratoMatrizOfertaCriticaTO.getMatrizOfertaContratoCriticaTO()));
			contratoMatrizOfertaCritica.setNumeroLinhaErro(contratoMatrizOfertaCriticaTO.getNumeroLinhaErro());
		}
		
		return contratoMatrizOfertaCritica;
	}
	
	/**
	 * @param contratoMatrizOfertaCritica
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public ContratoMatrizOfertaCriticaTO createContratoMatrizOfertaCriticaTO(ContratoMatrizOfertaCritica contratoMatrizOfertaCritica) {
		
		ContratoMatrizOfertaCriticaTO contratoMatrizOfertaCriticaTO = null;
		
		if (contratoMatrizOfertaCritica != null) {
			contratoMatrizOfertaCriticaTO = new ContratoMatrizOfertaCriticaTO();
			contratoMatrizOfertaCriticaTO.setContratoMatrizOfertaTO(contratoMatrizOfertaTOBuilder.createContratoMatrizOfertaTO(contratoMatrizOfertaCritica.getContratoMatrizOferta()));
			contratoMatrizOfertaCriticaTO.setIdContratoMatrizOfertaCritica(contratoMatrizOfertaCritica.getIdContratoMatrizOfertaCritica());
			contratoMatrizOfertaCriticaTO.setMatrizOfertaContratoCriticaTO(matrizOfertaContratoCriticaTOBuilder.createMatrizOfertaContratoCriticaTO(contratoMatrizOfertaCritica.getMatrizOfertaContratoCritica()));
			contratoMatrizOfertaCriticaTO.setNumeroLinhaErro(contratoMatrizOfertaCritica.getNumeroLinhaErro());
		}
		
		return contratoMatrizOfertaCriticaTO;
	}
	
	/**
	 * @param contratoMatrizOfertaCriticaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<ContratoMatrizOfertaCriticaTO> createContratoMatrizOfertaCriticaTOList(List<ContratoMatrizOfertaCritica> contratoMatrizOfertaCriticaList) {
		
		List<ContratoMatrizOfertaCriticaTO> list = null;
		
		if (contratoMatrizOfertaCriticaList != null && contratoMatrizOfertaCriticaList.size() > 0) {
			list = new ArrayList<ContratoMatrizOfertaCriticaTO>();
			for (ContratoMatrizOfertaCritica contratoMatrizOfertaCritica : contratoMatrizOfertaCriticaList) {
				list.add(createContratoMatrizOfertaCriticaTO(contratoMatrizOfertaCritica));
			}
		}
		
		return list;
	}
}
