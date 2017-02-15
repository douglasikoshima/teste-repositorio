package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Canal;
import com.indracompany.catalogo.datalayer.CondicaoPagamento;
import com.indracompany.catalogo.datalayer.DescontoCondPagto;
import com.indracompany.catalogo.to.DescontoCondPagtoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class DescontoCondPagtoTOBuilder {
	
	/**
	 * @param descontoCondPagtoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public DescontoCondPagto createDescontoCondPagto(DescontoCondPagtoTO descontoCondPagtoTO) {
		
		DescontoCondPagto descontoCondPagto = null;
		
		if (descontoCondPagtoTO != null) {
			descontoCondPagto = new DescontoCondPagto();
			descontoCondPagto.setCanal(new Canal(descontoCondPagtoTO.getCanalTO().getIdCanal()));
			descontoCondPagto.setCondicaoPagamento(new CondicaoPagamento(descontoCondPagtoTO.getCondicaoPagamentoTO().getIdCondicaoPagamento()));
			descontoCondPagto.setFatorPreco(descontoCondPagtoTO.getFatorPreco());
		}
		
		return descontoCondPagto;
	}
	
	/**
	 * @param descontoCondPagto
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public DescontoCondPagtoTO createDescontoCondPagtoTO(DescontoCondPagto descontoCondPagto) {
		
		DescontoCondPagtoTO descontoCondPagtoTO = null;
		
		if (descontoCondPagto != null) {
			descontoCondPagtoTO = new DescontoCondPagtoTO();
			
		}
		
		return descontoCondPagtoTO;
	}
	
	/**
	 * @param descontoCondPagtoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<DescontoCondPagtoTO> createDescontoCondPagtoTOList(List<DescontoCondPagto> descontoCondPagtoList) {
		
		List<DescontoCondPagtoTO> list = new ArrayList<DescontoCondPagtoTO>();
		
		if (descontoCondPagtoList != null && descontoCondPagtoList.size() > 0) {
			for (DescontoCondPagto descontoCondPagto : descontoCondPagtoList) {
				list.add(createDescontoCondPagtoTO(descontoCondPagto));
			}
		}
		
		return list;
	}
	
	/**
	 * @param descontoCondPagtoTOList
	 * @return
	 */
	public List<DescontoCondPagto> createDescontoCondPagtoList(List<DescontoCondPagtoTO> descontoCondPagtoTOList) {
		
		List<DescontoCondPagto> list = new ArrayList<DescontoCondPagto>();
		
		if (descontoCondPagtoTOList != null && descontoCondPagtoTOList.size() > 0) {
			for (DescontoCondPagtoTO descontoCondPagtoTO : descontoCondPagtoTOList) {
				list.add(createDescontoCondPagto(descontoCondPagtoTO));
			}
		}
		
		return list;
	}
}
