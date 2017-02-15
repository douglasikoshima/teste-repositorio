package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ContratoMatrizOfertaLock;
import com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class ContratoMatrizOfertaLockTOBuilder {
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public ContratoMatrizOfertaLock createContratoMatrizOferta(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) {
		
		ContratoMatrizOfertaLock contratoMatrizOfertaLock = null;
		
		if (contratoMatrizOfertaLockTO != null) {
			contratoMatrizOfertaLock = new ContratoMatrizOfertaLock();
			contratoMatrizOfertaLock.setDtAlteracao(contratoMatrizOfertaLockTO.getDtAlteracao());
			contratoMatrizOfertaLock.setDtInclusao(contratoMatrizOfertaLockTO.getDtInclusao());
			contratoMatrizOfertaLock.setInStatus(contratoMatrizOfertaLockTO.getInStatus());
			contratoMatrizOfertaLock.setNmArquivo(contratoMatrizOfertaLockTO.getNmArquivo());
			contratoMatrizOfertaLock.setNmUsuarioAlteracao(contratoMatrizOfertaLockTO.getNmUsuarioAlteracao());
			contratoMatrizOfertaLock.setNmUsuarioCriacao(contratoMatrizOfertaLockTO.getNmUsuarioCriacao());
			contratoMatrizOfertaLock.setInLiberar(contratoMatrizOfertaLockTO.getInLiberar());
			contratoMatrizOfertaLock.setInImportacao(contratoMatrizOfertaLockTO.getInImportacao());
			contratoMatrizOfertaLock.setInValidado(contratoMatrizOfertaLockTO.getInValidado());
			
		}
		return contratoMatrizOfertaLock;
	}
	
	/**
	 * @param contratoMatrizOfertaLock
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public ContratoMatrizOfertaLockTO createContratoMatrizOfertaLockTO(ContratoMatrizOfertaLock contratoMatrizOfertaLock) {
		
		ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO = null;
		
		if (contratoMatrizOfertaLock != null) {
			contratoMatrizOfertaLockTO = new ContratoMatrizOfertaLockTO();
			contratoMatrizOfertaLockTO.setDtAlteracao(contratoMatrizOfertaLock.getDtAlteracao());
			contratoMatrizOfertaLockTO.setDtInclusao(contratoMatrizOfertaLock.getDtInclusao());
			contratoMatrizOfertaLockTO.setInStatus(contratoMatrizOfertaLock.getInStatus());
			contratoMatrizOfertaLockTO.setNmArquivo(contratoMatrizOfertaLock.getNmArquivo());
			contratoMatrizOfertaLockTO.setNmUsuarioAlteracao(contratoMatrizOfertaLock.getNmUsuarioAlteracao());
			contratoMatrizOfertaLockTO.setNmUsuarioCriacao(contratoMatrizOfertaLock.getNmUsuarioCriacao());
			contratoMatrizOfertaLockTO.setIdContratoMatrizOfertaLock(contratoMatrizOfertaLock.getIdContratoMatrizOfertaLock());
			contratoMatrizOfertaLockTO.setInLiberar(contratoMatrizOfertaLock.getInLiberar());
			contratoMatrizOfertaLockTO.setInImportacao(contratoMatrizOfertaLock.getInImportacao());
			contratoMatrizOfertaLockTO.setInValidado(contratoMatrizOfertaLock.getInValidado());

		}
		
		return contratoMatrizOfertaLockTO;
	}
	
	/**
	 * @param contratoMatrizOfertaLockList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<ContratoMatrizOfertaLockTO> createContratoMatrizOfertaTOList(List<ContratoMatrizOfertaLock> contratoMatrizOfertaLockList) {
		
		List<ContratoMatrizOfertaLockTO> list = new ArrayList<ContratoMatrizOfertaLockTO>();
		
		if (contratoMatrizOfertaLockList != null && contratoMatrizOfertaLockList.size() > 0) {
			for (ContratoMatrizOfertaLock contratoMatrizOfertaLock : contratoMatrizOfertaLockList) {
				list.add(createContratoMatrizOfertaLockTO(contratoMatrizOfertaLock));
			}
		}
		
		return list;
	}
}
