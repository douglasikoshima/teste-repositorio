package com.indracompany.catalogo.dao;



import com.indracompany.catalogo.datalayer.ServicoIntCanal;
import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.to.ServicoIntCanalTO;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;

public class ServicoIntCanalTOBuilder {
	
	/**
	 * @param ServicoIntCanalTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	
	public ServicoIntCanal createServicoIntCanal (ServicoIntCanalTO servicoIntCanalTO) {
		ServicoIntCanal servicoIntCanal = new ServicoIntCanal();
		
		if (servicoIntCanalTO != null) {
			servicoIntCanal = new ServicoIntCanal();
			
			servicoIntCanal.setCanalAtendimento(servicoIntCanalTO.getCanalAtendimento());
			servicoIntCanal.setServicoInteratividade(new ServicoInteratividade(servicoIntCanalTO.getServicoInteratividadeTO().getIdServicoInteratividade()));
			
			servicoIntCanal.setDtCriacao(servicoIntCanalTO.getDtCriacao());
			servicoIntCanal.setNmUsuarioCriacao(servicoIntCanalTO.getNmUsuarioCriacao());			
		}
		
		return servicoIntCanal;		
	}
	
	/**
	 * @param servicoIntCanal
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	
	public ServicoIntCanalTO createServicoIntCanalTO(ServicoIntCanal servicoIntCanal) {
		
		ServicoIntCanalTO servicoIntCanalTO = null;
		
		if (servicoIntCanal != null) {
			servicoIntCanalTO = new ServicoIntCanalTO();
			servicoIntCanalTO.setIdServicoIntCanal(servicoIntCanal.getIdServicoIntCanal());
			servicoIntCanalTO.setCanalAtendimento(servicoIntCanal.getCanalAtendimento());
			servicoIntCanalTO.setServicoInteratividadeTO(new ServicoInteratividadeTO (servicoIntCanal.getServicoInteratividade().getIdServicoInteratividade()));
			
			servicoIntCanalTO.setDtCriacao(servicoIntCanal.getDtCriacao());
			servicoIntCanalTO.setNmUsuarioCriacao(servicoIntCanal.getNmUsuarioCriacao());
			
		}
		
		return servicoIntCanalTO;
	}
	
	
	
	
	
}
