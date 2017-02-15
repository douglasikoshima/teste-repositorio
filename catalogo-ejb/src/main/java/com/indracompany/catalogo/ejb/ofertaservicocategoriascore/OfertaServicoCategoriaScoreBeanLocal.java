package com.indracompany.catalogo.ejb.ofertaservicocategoriascore;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.OfertaServicoCategoriaScoreTO;

@Local
public interface OfertaServicoCategoriaScoreBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/OfertaServicoCategoriaScoreBean";
	
	public void removeByCategoriaScore(OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) throws BusinessException;
	
}
