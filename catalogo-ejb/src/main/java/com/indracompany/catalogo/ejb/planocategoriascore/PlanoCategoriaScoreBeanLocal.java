package com.indracompany.catalogo.ejb.planocategoriascore;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.PlanoCategoriaScoreTO;

@Local
public interface PlanoCategoriaScoreBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/PlanoCategoriaScoreBean";
	
	public void removeByCategoriaScore(PlanoCategoriaScoreTO planoCategoriaScoreTO) throws BusinessException;
	
}
