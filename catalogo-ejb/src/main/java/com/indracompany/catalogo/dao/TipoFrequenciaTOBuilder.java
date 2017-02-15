package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TipoFrequencia;
import com.indracompany.catalogo.to.TipoFrequenciaTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class TipoFrequenciaTOBuilder {
	
	/**
	 * @param tipoFrequenciaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public TipoFrequencia createTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) {
		
		TipoFrequencia tipoFrequencia = null;
		
		if (tipoFrequenciaTO != null) {
			tipoFrequencia = new TipoFrequencia();
			tipoFrequencia.setDtCriacao(tipoFrequenciaTO.getDtCriacao());
			tipoFrequencia.setDtUltimaAlteracao(tipoFrequenciaTO.getDtUltimaAlteracao());
			tipoFrequencia.setIdTipoFrequencia(tipoFrequenciaTO.getIdTipoFrequencia());
			tipoFrequencia.setNmTipoFrequencia(tipoFrequenciaTO.getNmTipoFrequencia());
			tipoFrequencia.setNmUsuarioAlteracao(tipoFrequenciaTO.getNmUsuarioAlteracao());
			tipoFrequencia.setNmUsuarioCriacao(tipoFrequenciaTO.getNmUsuarioCriacao());
			tipoFrequencia.setQtFrequencia(tipoFrequenciaTO.getQtFrequencia());
		}
		return tipoFrequencia;
	}
	
	/**
	 * @param tipoFrequencia
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public TipoFrequenciaTO createTipoFrequenciaTO(TipoFrequencia tipoFrequencia) {
		
		TipoFrequenciaTO tipoFrequenciaTO = null;
		
		if (tipoFrequencia != null) {
			tipoFrequenciaTO = new TipoFrequenciaTO();
			tipoFrequenciaTO.setDtCriacao(tipoFrequencia.getDtCriacao());
			tipoFrequenciaTO.setDtUltimaAlteracao(tipoFrequencia.getDtUltimaAlteracao());
			tipoFrequenciaTO.setIdTipoFrequencia(tipoFrequencia.getIdTipoFrequencia());
			tipoFrequenciaTO.setNmTipoFrequencia(tipoFrequencia.getNmTipoFrequencia());
			tipoFrequenciaTO.setNmUsuarioAlteracao(tipoFrequencia.getNmUsuarioAlteracao());
			tipoFrequenciaTO.setNmUsuarioCriacao(tipoFrequencia.getNmUsuarioCriacao());
			tipoFrequenciaTO.setQtFrequencia(tipoFrequencia.getQtFrequencia());
			
		}
		
		return tipoFrequenciaTO;
	}
	
	/**
	 * @param tipoFrequenciaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<TipoFrequenciaTO> createTipoFrequenciaTOList(List<TipoFrequencia> tipoFrequenciaList) {
		
		List<TipoFrequenciaTO> list = new ArrayList<TipoFrequenciaTO>();
		
		if (tipoFrequenciaList != null && !tipoFrequenciaList.isEmpty()) {
			for (TipoFrequencia tipoFrequencia : tipoFrequenciaList) {
				list.add(createTipoFrequenciaTO(tipoFrequencia));
			}
		}
		
		return list;
	}
}
