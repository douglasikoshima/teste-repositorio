package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.AnaliseCredito;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

/**
 * @author Luiz Pereira
 *
 * Classe respons�vel em transformar TO em Entity e Entiy em TO.
 */
public class AnaliseCreditoTOBuilder {
	
	/**
	 * @param analiseCredito
	 * @return
	 * 
	 * Metodo responsavel em transformar um Entity em um TO.
	 */
	public AnaliseCreditoTO createAnaliseCreditoTO(AnaliseCredito analiseCredito) {
		
		AnaliseCreditoTO analiseCreditoTO = null;
		
		if (analiseCredito != null) {
			analiseCreditoTO = new AnaliseCreditoTO();
			analiseCreditoTO.setDtCriacao(analiseCredito.getDtCriacao());
			analiseCreditoTO.setDtUltimaAlteracao(analiseCredito.getDtUltimaAlteracao());
			analiseCreditoTO.setIdAnaliseCredito(analiseCredito.getIdAnaliseCredito());
			analiseCreditoTO.setNmAnaliseCredito(analiseCredito.getNmAnaliseCredito());
			analiseCreditoTO.setNmUsuarioCriacao(analiseCredito.getNmUsuarioCriacao());
			analiseCreditoTO.setNmUsuarioAlteracao(analiseCredito.getNmUsuarioAlteracao());
			analiseCreditoTO.setCdCor(analiseCredito.getCdCor());
		}
		
		return analiseCreditoTO;
	}
	
	/**
	 * @param analiseCreditoList
	 * @return
	 * 
	 * M�todo respons�vel em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<AnaliseCreditoTO> createAnaliseCreditoTOList(List<AnaliseCredito> analiseCreditoList) {
		
		List<AnaliseCreditoTO> list = new ArrayList<AnaliseCreditoTO>();
		
		if (analiseCreditoList != null && analiseCreditoList.size() > 0) {
			for (AnaliseCredito analiseCredito : analiseCreditoList) {
				list.add(createAnaliseCreditoTO(analiseCredito));
			}
		}
		
		return list;
	}
	
	
}
