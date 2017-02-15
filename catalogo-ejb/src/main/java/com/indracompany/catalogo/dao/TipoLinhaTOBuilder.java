package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TipoLinha;
import com.indracompany.catalogo.to.TipoLinhaTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class TipoLinhaTOBuilder {
	
	/**
	 * @param tipoLinhaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public TipoLinha createTipoLinha(TipoLinhaTO tipoLinhaTO) {
		
		TipoLinha tipoLinha = null;
		
		if (tipoLinhaTO != null) {
			tipoLinha = new TipoLinha();
			tipoLinha.setDscTipoLinha(tipoLinhaTO.getDscTipoLinha());
			tipoLinha.setDtCriacao(tipoLinhaTO.getDtCriacao());
			tipoLinha.setDtUltimaAlteracao(tipoLinhaTO.getDtUltimaAlteracao());
			tipoLinha.setIdTipoLinha(tipoLinhaTO.getIdTipoLinha());
			tipoLinha.setNmUsuarioAlteracao(tipoLinhaTO.getNmUsuarioAlteracao());
			tipoLinha.setNmUsuarioCriacao(tipoLinhaTO.getNmUsuarioCriacao());
			tipoLinha.setSgTipoLinha(tipoLinhaTO.getSgTipoLinha());
		}
		
		return tipoLinha;
	}
	
	/**
	 * @param tipoLinha
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public TipoLinhaTO createTipoLinhaTO(TipoLinha tipoLinha) {
		
		TipoLinhaTO tipoLinhaTO = null;
		
		if (tipoLinha != null) {
			tipoLinhaTO = new TipoLinhaTO();
			tipoLinhaTO.setDscTipoLinha(tipoLinha.getDscTipoLinha());
			tipoLinhaTO.setDtCriacao(tipoLinha.getDtCriacao());
			tipoLinhaTO.setDtUltimaAlteracao(tipoLinha.getDtUltimaAlteracao());
			tipoLinhaTO.setIdTipoLinha(tipoLinha.getIdTipoLinha());
			tipoLinhaTO.setNmUsuarioAlteracao(tipoLinha.getNmUsuarioAlteracao());
			tipoLinhaTO.setNmUsuarioCriacao(tipoLinha.getNmUsuarioCriacao());
			tipoLinhaTO.setSgTipoLinha(tipoLinha.getSgTipoLinha());
		}
		
		return tipoLinhaTO;
	}
	
	/**
	 * @param tipoLinhaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<TipoLinhaTO> createTipoLinhaTOList(List<TipoLinha> tipoLinhaList) {
		
		List<TipoLinhaTO> list = new ArrayList<TipoLinhaTO>();
		
		if (tipoLinhaList != null && tipoLinhaList.size() > 0) {
			for (TipoLinha tipoLinha : tipoLinhaList) {
				list.add(createTipoLinhaTO(tipoLinha));
			}
		}
		
		return list;
	}
}
