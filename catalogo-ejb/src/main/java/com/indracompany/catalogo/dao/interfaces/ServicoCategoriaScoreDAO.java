package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoCategoriaScoreTO;

public interface ServicoCategoriaScoreDAO {

	public void removeByCategoriaScore(ServicoCategoriaScoreTO servicoCategoriaScoreTO) throws DAOException;	
	
	public void removeByServico(ServicoCategoriaScoreTO servicoCategoriaScoreTO) throws DAOException;
	
	public void createServicoCategoriaScore(ServicoCategoriaScoreTO servicoCategoriaScoreTO) throws DAOException;
}
