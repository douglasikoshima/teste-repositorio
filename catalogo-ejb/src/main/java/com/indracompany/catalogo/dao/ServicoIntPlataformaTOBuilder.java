package com.indracompany.catalogo.dao;



import com.indracompany.catalogo.datalayer.ServicoIntPlataforma;
import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.to.ServicoIntPlataformaTO;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;

public class ServicoIntPlataformaTOBuilder {
	
	/**
	 * @param ServicoIntPlataformaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public ServicoIntPlataforma createServicoIntPlataforma (ServicoIntPlataformaTO servicoIntPlataformaTO) {
		
		ServicoIntPlataforma servicoIntPlataforma = new ServicoIntPlataforma();
		
		if (servicoIntPlataformaTO != null) {
			servicoIntPlataforma = new ServicoIntPlataforma();
			servicoIntPlataforma.setPlataforma(servicoIntPlataformaTO.getPlataforma());
			servicoIntPlataforma.setServicoInteratividade(new ServicoInteratividade(servicoIntPlataformaTO.getServicoInteratividadeTO().getIdServicoInteratividade()));
			
			servicoIntPlataforma.setDtCriacao(servicoIntPlataformaTO.getDtCriacao());
			servicoIntPlataforma.setNmUsuarioCriacao(servicoIntPlataformaTO.getNmUsuarioCriacao());	
						
		}
		return servicoIntPlataforma;
	}
	
	/**
	 * @param servicoIntPlataforma
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	
	public ServicoIntPlataformaTO createServicoIntPlataformaTO (ServicoIntPlataforma servicoIntPlataforma) {
		
		ServicoIntPlataformaTO ServicoIntPlataformaTO = null;
		
		if (servicoIntPlataforma != null) {
			ServicoIntPlataformaTO = new ServicoIntPlataformaTO();
			ServicoIntPlataformaTO.setIdServicoIntPlataforma(servicoIntPlataforma.getIdServicoIntPlataforma());
			ServicoIntPlataformaTO.setPlataforma(servicoIntPlataforma.getPlataforma());
			ServicoIntPlataformaTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(servicoIntPlataforma.getServicoInteratividade().getIdServicoInteratividade()));
			ServicoIntPlataformaTO.setDtCriacao(ServicoIntPlataformaTO.getDtCriacao());
			ServicoIntPlataformaTO.setNmUsuarioCriacao(ServicoIntPlataformaTO.getNmUsuarioCriacao());
		}
		
		return ServicoIntPlataformaTO;
		
		
	}
	
}
