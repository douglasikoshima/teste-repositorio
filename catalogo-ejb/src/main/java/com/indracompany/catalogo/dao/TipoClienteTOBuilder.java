package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TipoCliente;
import com.indracompany.catalogo.to.TipoClienteTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class TipoClienteTOBuilder {
	
	/**
	 * @param tipoClienteTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public TipoCliente createTipoCliente(TipoClienteTO tipoClienteTO) {
		
		TipoCliente tipoCliente = null;
		
		if (tipoClienteTO != null) {
			tipoCliente = new TipoCliente();
			tipoCliente.setDtCriacao(tipoClienteTO.getDtCriacao());
			tipoCliente.setDtUltimaAlteracao(tipoClienteTO.getDtUltimaAlteracao());
			tipoCliente.setIdTipoCliente(tipoClienteTO.getIdTipoCliente());
			tipoCliente.setNmTipoCliente(tipoClienteTO.getNmTipoCliente());
			tipoCliente.setNmUsuarioAlteracao(tipoClienteTO.getNmUsuarioAlteracao());
			tipoCliente.setNmUsuarioCriacao(tipoClienteTO.getNmUsuarioCriacao());
			
		}
		
		return tipoCliente;
	}
	
	/**
	 * @param tipoCliente
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public TipoClienteTO createTipoClienteTO(TipoCliente tipoCliente) {
		
		TipoClienteTO tipoClienteTO = null;
		
		if (tipoCliente != null) {
			tipoClienteTO = new TipoClienteTO();
			tipoClienteTO.setDtCriacao(tipoCliente.getDtCriacao());
			tipoClienteTO.setDtUltimaAlteracao(tipoCliente.getDtUltimaAlteracao());
			tipoClienteTO.setIdTipoCliente(tipoCliente.getIdTipoCliente());
			tipoClienteTO.setNmTipoCliente(tipoCliente.getNmTipoCliente());
			tipoClienteTO.setNmUsuarioAlteracao(tipoCliente.getNmUsuarioAlteracao());
			tipoClienteTO.setNmUsuarioCriacao(tipoCliente.getNmUsuarioCriacao());
		}
		
		return tipoClienteTO;
	}
	
	/**
	 * @param tipoClienteList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<TipoClienteTO> createTipoClienteTOList(List<TipoCliente> tipoClienteList) {
		
		List<TipoClienteTO> list = new ArrayList<TipoClienteTO>();
		
		if (tipoClienteList != null && tipoClienteList.size() > 0) {
			for (TipoCliente tipoCliente : tipoClienteList) {
				list.add(createTipoClienteTO(tipoCliente));
			}
		}
		
		return list;
	}
}
