package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ContratoMatrizOferta;
import com.indracompany.catalogo.to.ContratoMatrizOfertaTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class ContratoMatrizOfertaTOBuilder {
	
	/**
	 * @param contratoMatrizOfertaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public ContratoMatrizOferta createContratoMatrizOferta(ContratoMatrizOfertaTO contratoMatrizOfertaTO) {
		
		ContratoMatrizOferta contratoMatrizOferta = null;
		
		if (contratoMatrizOfertaTO != null) {
			contratoMatrizOferta = new ContratoMatrizOferta();
			contratoMatrizOferta.setCodigoPlano(contratoMatrizOfertaTO.getCodigoPlano());
			contratoMatrizOferta.setCodigoServico(contratoMatrizOfertaTO.getCodigoServico());
			contratoMatrizOferta.setDtCriacao(contratoMatrizOfertaTO.getDtCriacao());
			contratoMatrizOferta.setNmUsuarioCriacao(contratoMatrizOfertaTO.getNmUsuarioCriacao());
			contratoMatrizOferta.setSiglaCsa(contratoMatrizOfertaTO.getSiglaCsa());
			contratoMatrizOferta.setValorContrato(contratoMatrizOfertaTO.getValorContrato());
			contratoMatrizOferta.setNumeroLinha(contratoMatrizOfertaTO.getNumeroLinha());
			
		}
		return contratoMatrizOferta;
	}
	
	/**
	 * @param contratoMatrizOferta
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public ContratoMatrizOfertaTO createContratoMatrizOfertaTO(ContratoMatrizOferta contratoMatrizOferta) {
		
		ContratoMatrizOfertaTO contratoMatrizOfertaTO = null;
		
		if (contratoMatrizOferta != null) {
			contratoMatrizOfertaTO = new ContratoMatrizOfertaTO();
			contratoMatrizOfertaTO.setCodigoPlano(contratoMatrizOferta.getCodigoPlano());
			contratoMatrizOfertaTO.setCodigoServico(contratoMatrizOferta.getCodigoServico());
			contratoMatrizOfertaTO.setDtCriacao(contratoMatrizOferta.getDtCriacao());
			contratoMatrizOfertaTO.setNmUsuarioCriacao(contratoMatrizOferta.getNmUsuarioCriacao());
			contratoMatrizOfertaTO.setSiglaCsa(contratoMatrizOferta.getSiglaCsa());
			contratoMatrizOfertaTO.setValorContrato(contratoMatrizOferta.getValorContrato());
			contratoMatrizOfertaTO.setIdContratoMatriz(contratoMatrizOferta.getIdContratoMatriz());
			contratoMatrizOfertaTO.setNumeroLinha(contratoMatrizOferta.getNumeroLinha());
		}
		
		return contratoMatrizOfertaTO;
	}
	
	/**
	 * @param contratoMatrizOfertaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<ContratoMatrizOfertaTO> createContratoMatrizOfertaTOList(List<ContratoMatrizOferta> contratoMatrizOfertaList) {
		
		List<ContratoMatrizOfertaTO> list = new ArrayList<ContratoMatrizOfertaTO>();
		
		if (contratoMatrizOfertaList != null && contratoMatrizOfertaList.size() > 0) {
			for (ContratoMatrizOferta contratoMatrizOferta : contratoMatrizOfertaList) {
				list.add(createContratoMatrizOfertaTO(contratoMatrizOferta));
			}
		}
		
		return list;
	}
}
