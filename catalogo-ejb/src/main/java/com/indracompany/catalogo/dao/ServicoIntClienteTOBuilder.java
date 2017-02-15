package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.ServicoIntCliente;
import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.to.ServicoIntClienteTO;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;

public class ServicoIntClienteTOBuilder {
	
	/**
	 * @param servicoIntClienteTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */

	public ServicoIntCliente createServicoIntCliente (ServicoIntClienteTO servicoIntClienteTO) {
		
		ServicoIntCliente servicoIntCliente = new ServicoIntCliente();
		
		if (servicoIntClienteTO != null) { 
			servicoIntCliente = new ServicoIntCliente();
			
			servicoIntCliente.setTipoCliente(servicoIntClienteTO.getTipoCliente());
			servicoIntCliente.setServicoInteratividade(new ServicoInteratividade(servicoIntClienteTO.getServicoInteratividadeTO().getIdServicoInteratividade()));
			
			servicoIntCliente.setDtCriacao(servicoIntClienteTO.getDtCriacao());
			servicoIntCliente.setNmUsuarioCriacao(servicoIntClienteTO.getNmUsuarioCriacao());
			
		}
		return servicoIntCliente;
	}
	
	/**
	 * @param servicoIntCliente
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	
	public ServicoIntClienteTO createServicoIntClienteTO(ServicoIntCliente ServicoIntCliente) {
		
		ServicoIntClienteTO servicoIntClienteTO = null;
		
		if (ServicoIntCliente != null) {
			servicoIntClienteTO = new ServicoIntClienteTO();
			
			servicoIntClienteTO.setIdServicoIntCliente(ServicoIntCliente.getIdServicoIntCliente());
			servicoIntClienteTO.setTipoCliente(ServicoIntCliente.getTipoCliente());
			servicoIntClienteTO.setServicoInteratividadeTO(new ServicoInteratividadeTO (ServicoIntCliente.getServicoInteratividade().getIdServicoInteratividade()));
			
			servicoIntClienteTO.setDtCriacao(servicoIntClienteTO.getDtCriacao());
			servicoIntClienteTO.setNmUsuarioCriacao(servicoIntClienteTO.getNmUsuarioCriacao());	
			
		}
		
		return servicoIntClienteTO;
	}
}
