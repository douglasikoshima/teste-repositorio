package com.indracompany.catalogo.ejb.classificacaocategoriascore;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.ClassificacaoCategoriaScoreTO;

@Local
public interface ClassificacaoCategoriaScoreBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/ClassificacaoCategoriaScoreBean";
	
	public List<ClassificacaoCategoriaScoreTO> findAll() throws BusinessException;

}
