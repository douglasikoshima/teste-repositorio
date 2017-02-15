package com.indracompany.catalogo.ejb.categoriascore;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.CategoriaScoreTOBuilder;
import com.indracompany.catalogo.to.CategoriaScoreTO;

@Local
public interface CategoriaScoreBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/CategoriaScoreBean";
	
	public void createUpdateCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws BusinessException;
	
	public List<CategoriaScoreTO> findAll() throws BusinessException;
	
	public List<CategoriaScoreTO> findAll(CategoriaScoreTOBuilder categoriaScoreTOBuilder) throws BusinessException;
 	
	public CategoriaScoreTO findById(CategoriaScoreTO categoriaScoreTO) throws BusinessException;
 
	public List<CategoriaScoreTO> searchCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws BusinessException;
	
	public void removeCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws BusinessException;
	
	public void removeAssociation(CategoriaScoreTO categoriaScoreTO) throws BusinessException;
	
	public List<String> findAssociation(CategoriaScoreTO categoriaScoreTO) throws BusinessException;
	
	public boolean existCdCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws BusinessException;
	
	public CategoriaScoreTO findByCdCategoria(CategoriaScoreTO categoriaScoreTO) throws BusinessException;
	
}
