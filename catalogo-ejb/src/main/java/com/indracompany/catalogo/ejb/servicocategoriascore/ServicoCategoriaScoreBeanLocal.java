package com.indracompany.catalogo.ejb.servicocategoriascore;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.ServicoCategoriaScoreTO;

@Local
public interface ServicoCategoriaScoreBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/ServicoCategoriaScoreBean";
	
	public void removeByCategoriaScore(ServicoCategoriaScoreTO servicoCategoriaScoreTO) throws BusinessException;
	
}
