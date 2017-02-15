package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.dao.CategoriaScoreTOBuilder;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategoriaScoreTO;

public interface CategoriaScoreDAO {
	
	public void createUpdateCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException;
	
	public List<CategoriaScoreTO> findAll() throws DAOException;
	
	public List<CategoriaScoreTO> findAll(CategoriaScoreTOBuilder categoriaScoreTOBuilder) throws DAOException;
 	
	public CategoriaScoreTO findById(CategoriaScoreTO categoriaScoreTO) throws DAOException;

	public List<CategoriaScoreTO> searchCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException;

	public void removeCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException;
	
	public void removeAssociation(CategoriaScoreTO categoriaScoreTO) throws DAOException;
	
	public boolean existAssociationPlanoCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException;

	public boolean existAssociationServicoCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException;

	public boolean existAssociationOfertaServicoCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException;
	
	public boolean existCdCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException;
	
	public CategoriaScoreTO findByCdCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException;

}
