package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlanoCategoriaScoreTO;

public interface PlanoCategoriaScoreDAO {
	
	public void removeByCategoriaScore(PlanoCategoriaScoreTO planoCategoriaScoreTO) throws DAOException;
	
	public void removeByPlano(PlanoCategoriaScoreTO planoCategoriaScoreTO) throws DAOException;
	
	public void createPlanoCategoriaScore(PlanoCategoriaScoreTO planoCategoriaScoreTO) throws DAOException;

}
