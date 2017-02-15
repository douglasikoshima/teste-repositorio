package com.indracompany.catalogo.dao;


import com.indracompany.catalogo.datalayer.ServicoIntTecnologia;
import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.to.ServicoIntTecnologiaTO;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;

public class ServicoIntTecnologiaTOBuilder {
	
	/**
	 * @param servicoIntTecnologiaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	
	public ServicoIntTecnologia createServicoIntTecnologia (ServicoIntTecnologiaTO servicoIntTecnologiaTO) {
		
		ServicoIntTecnologia servicoIntTecnologia = new ServicoIntTecnologia();
		
		if (servicoIntTecnologiaTO != null) {
			servicoIntTecnologia = new ServicoIntTecnologia();
			servicoIntTecnologia.setTecnologia(servicoIntTecnologiaTO.getTecnologia());
			servicoIntTecnologia.setServicoInteratividade(new ServicoInteratividade(servicoIntTecnologiaTO.getServicoInteratividadeTO().getIdServicoInteratividade()));
			
			servicoIntTecnologia.setDtCriacao(servicoIntTecnologiaTO.getDtCriacao());
			servicoIntTecnologia.setNmUsuarioCriacao(servicoIntTecnologiaTO.getNmUsuarioCriacao());
			
		}
		
		return servicoIntTecnologia; 
	}
	
	/**
	 * @param ServicoIntTecnologia
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public ServicoIntTecnologiaTO createServicoIntTecnologiaTO (ServicoIntTecnologia ServicoIntTecnologia) {
		
		ServicoIntTecnologiaTO servicoIntTecnologiaTO = null;
		
		if (ServicoIntTecnologia != null) {
			servicoIntTecnologiaTO = new ServicoIntTecnologiaTO();
			
			servicoIntTecnologiaTO.setIdServicoIntTecnologia(ServicoIntTecnologia.getIdServicoIntTec());
			servicoIntTecnologiaTO.setTecnologia(ServicoIntTecnologia.getTecnologia());
			servicoIntTecnologiaTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(ServicoIntTecnologia.getServicoInteratividade().getIdServicoInteratividade()));
			
			servicoIntTecnologiaTO.setDtCriacao(servicoIntTecnologiaTO.getDtCriacao());
			servicoIntTecnologiaTO.setNmUsuarioCriacao(servicoIntTecnologiaTO.getNmUsuarioCriacao());	
			
		}
		
		return servicoIntTecnologiaTO;			
	}
	
}
