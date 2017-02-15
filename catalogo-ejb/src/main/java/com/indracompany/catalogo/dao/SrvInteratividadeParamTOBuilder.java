package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.datalayer.SrvInteratividadeParam;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;
import com.indracompany.catalogo.to.SrvInteratividadeParamTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class SrvInteratividadeParamTOBuilder {
	
	/**
	 * @param srvInteratividadeParametroTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public SrvInteratividadeParam createSrvInteratividadeParametro(SrvInteratividadeParamTO srvInteratividadeParametroTO) {
		
		SrvInteratividadeParam srvInteratividadeParametro = new SrvInteratividadeParam();
		
		if (srvInteratividadeParametroTO != null) {
			srvInteratividadeParametro = new SrvInteratividadeParam();
			srvInteratividadeParametro.setIdSrvInteratividadeParam(srvInteratividadeParametroTO.getIdSrvInteratividadeParam());
			srvInteratividadeParametro.setSrvInteratividadeParamBase(new SrvInteratividadeParamBaseTOBuilder().createSrvInteratividadeParamBase(srvInteratividadeParametroTO.getParametroTO()));
			
			ServicoInteratividade servicoInteratividade = new ServicoInteratividade();
			servicoInteratividade.setIdServicoInteratividade(srvInteratividadeParametroTO.getServicoInteratividadeTO().getIdServicoInteratividade());
			srvInteratividadeParametro.setServicoInteratividade(servicoInteratividade);
			
			srvInteratividadeParametro.setVlParametro(srvInteratividadeParametroTO.getVlParametro());
			
		}
		return srvInteratividadeParametro;
	}
	
	/**
	 * @param srvInteratividadeParametro
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public SrvInteratividadeParamTO createSrvInteratividadeParametroTO(SrvInteratividadeParam srvInteratividadeParametro) {
		
		SrvInteratividadeParamTO srvInteratividadeParametroTO = null;
		
		if (srvInteratividadeParametro != null) {
			srvInteratividadeParametroTO = new SrvInteratividadeParamTO();
			srvInteratividadeParametroTO.setIdSrvInteratividadeParam(srvInteratividadeParametro.getIdSrvInteratividadeParam());
			srvInteratividadeParametroTO.setParametroTO(new SrvInteratividadeParamBaseTOBuilder().createSrvInteratividadeParamBaseTO(srvInteratividadeParametro.getSrvInteratividadeParamBase()));
			
			ServicoInteratividadeTO servicoInteratividadeTO = new ServicoInteratividadeTO();
			servicoInteratividadeTO.setIdServicoInteratividade(srvInteratividadeParametro.getServicoInteratividade().getIdServicoInteratividade());
			srvInteratividadeParametroTO.setServicoInteratividadeTO(servicoInteratividadeTO);
			
			srvInteratividadeParametroTO.setVlParametro(srvInteratividadeParametro.getVlParametro());
		}
		
		return srvInteratividadeParametroTO;
	}
	
	/**
	 * @param srvInteratividadeParametroList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<SrvInteratividadeParamTO> createSrvInteratividadeParametroTOList(List<SrvInteratividadeParam> srvInteratividadeParametroList) {
		
		List<SrvInteratividadeParamTO> srvInteratividadeParametroTOList = new ArrayList<SrvInteratividadeParamTO>();
		
		if (srvInteratividadeParametroList != null && srvInteratividadeParametroList.size() > 0) {
			for (SrvInteratividadeParam srvInteratividadeParametro : srvInteratividadeParametroList) {
				srvInteratividadeParametroTOList.add(createSrvInteratividadeParametroTO(srvInteratividadeParametro));
			}
		}
		
		return srvInteratividadeParametroTOList;
	}
}
