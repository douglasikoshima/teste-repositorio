package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Cor;
import com.indracompany.catalogo.to.CorTO;

public class CorTOBuilder {
	
	/**
	 * @param CorTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Cor createCor(CorTO corTO) {
		
		Cor cor = null;
		
		if(corTO != null) {
			cor = new Cor();
			cor.setIdCor(corTO.getIdCor());
			cor.setRgb(corTO.getRgb());
			cor.setNmCor(corTO.getNmCor());
			cor.setDtCriacao(corTO.getDtCriacao());
			cor.setNmUsuarioCriacao(corTO.getNmUsuarioCriacao());
			cor.setDtAlteracao(corTO.getDtAlteracao());
			cor.setNmUsuarioAlteracao(corTO.getNmUsuarioAlteracao());
		}
		
		return cor;
	}
	
	
	/**
	 * @param Cor
	 * @return
	 * 
	 * Método responsável por transformar um Entity em um TO.
	 */
	public CorTO createCorTO(Cor cor) {
		
		CorTO corTO = null;
		
		if(cor != null) {
			corTO = new CorTO();
			corTO.setIdCor(cor.getIdCor());
			corTO.setRgb(cor.getRgb());
			corTO.setNmCor(cor.getNmCor());
			corTO.setDtCriacao(cor.getDtCriacao());
			corTO.setNmUsuarioCriacao(cor.getNmUsuarioCriacao());
			corTO.setDtAlteracao(cor.getDtAlteracao());
			corTO.setNmUsuarioAlteracao(cor.getNmUsuarioAlteracao());
		}
		
		return corTO;
	}
	
	
	/**
	 * @param Cor
	 * @return
	 * 
	 * Método responsável por transformar uma lista de Entity em uma Lista de TO
	 */
	public List<CorTO> createCorTOList(List<Cor> corList) {
		
		List<CorTO> corTOList = new ArrayList<CorTO>();
		
		if(corList != null){
			for(Cor cor : corList) {
				corTOList.add(createCorTO(cor));
			}
		}
		return corTOList;
	}

}
