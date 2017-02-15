package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.SrvInteratividadeParamBase;
import com.indracompany.catalogo.to.SrvInteratividadeParamBaseTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class SrvInteratividadeParamBaseTOBuilder {
	
	/**
	 * @param parametroTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public SrvInteratividadeParamBase createSrvInteratividadeParamBase(SrvInteratividadeParamBaseTO parametroTO) {
		
		SrvInteratividadeParamBase parametro = null;
		
		if (parametroTO != null) {
			parametro = new SrvInteratividadeParamBase();
			parametro.setDsSrvInteratividadeParamBase(parametroTO.getDsSrvInteratividadeParamBase());
			parametro.setIdSrvInteratividadeParamBase(parametroTO.getIdSrvInteratividadeParamBase());
			parametro.setIndVisivel(parametroTO.getIndVisivel());
			parametro.setNmSrvInteratividadeParamBase(parametroTO.getNmSrvInteratividadeParamBase());
			parametro.setTpSrvInteratividadeParamBase(parametroTO.getTpSrvInteratividadeParamBase());
			parametro.setVlDefaultParam(parametroTO.getVlDefaultParam());
		}
		
		return parametro;
	}
	
	/**
	 * @param parametro
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public SrvInteratividadeParamBaseTO createSrvInteratividadeParamBaseTO(SrvInteratividadeParamBase parametro) {
		
		SrvInteratividadeParamBaseTO parametroTO = null;
		
		if (parametro != null) {
			parametroTO = new SrvInteratividadeParamBaseTO();
			parametroTO.setDsSrvInteratividadeParamBase(parametro.getDsSrvInteratividadeParamBase());
			parametroTO.setIdSrvInteratividadeParamBase(parametro.getIdSrvInteratividadeParamBase());
			parametroTO.setIndVisivel(parametro.getIndVisivel());
			parametroTO.setNmSrvInteratividadeParamBase(parametro.getNmSrvInteratividadeParamBase());
			parametroTO.setTpSrvInteratividadeParamBase(parametro.getTpSrvInteratividadeParamBase());
			parametroTO.setVlDefaultParam(parametro.getVlDefaultParam());
		}
		
		return parametroTO;
	}
	
	/**
	 * @param parametroList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<SrvInteratividadeParamBaseTO> createSrvInteratividadeParamBaseTOList(List<SrvInteratividadeParamBase> parametroList) {
		
		List<SrvInteratividadeParamBaseTO> list = new ArrayList<SrvInteratividadeParamBaseTO>();
		
		if (parametroList != null && parametroList.size() > 0) {
			for (SrvInteratividadeParamBase parametro : parametroList) {
				list.add(createSrvInteratividadeParamBaseTO(parametro));
			}
		}
		
		return list;
	}
}
