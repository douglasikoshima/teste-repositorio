package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.PlanoCategoriaScoreDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlanoCategoriaScoreTO;


@Stateless
public class PlanoCategoriaScoreDAOImpl implements PlanoCategoriaScoreDAO{

	private static Logger logger = Logger.getLogger(PlanoCategoriaScoreDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	public void removeByCategoriaScore(PlanoCategoriaScoreTO planoCategoriaScoreTO) throws DAOException{
		logger.debug("planoCategoriaScoreTO: " + planoCategoriaScoreTO);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.PLANOCATEGORIASCORE WHERE IDCATEGORIASCORE = :idCategoriaScore");			
			query.setParameter("idCategoriaScore", planoCategoriaScoreTO.getCategoriaScoreTO().getIdCategoriaScore());
			query.executeUpdate();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeByPlano(PlanoCategoriaScoreTO planoCategoriaScoreTO) throws DAOException {
		logger.debug("planoCategoriaScoreTO: " + planoCategoriaScoreTO);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.PLANOCATEGORIASCORE WHERE IDPLANO = :idplano");
			query.setParameter("idplano", planoCategoriaScoreTO.getIdPlano());
			query.executeUpdate();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void createPlanoCategoriaScore(PlanoCategoriaScoreTO planoCategoriaScoreTO) throws DAOException {
		logger.debug("planoCategoriaScoreTO: " + planoCategoriaScoreTO);
		
		PlanoCategoriaScoreTOBuilder planoCategoriaScoreTOBuilder = new PlanoCategoriaScoreTOBuilder();
		
		try {
			em.persist(planoCategoriaScoreTOBuilder.createCategorizacaoAnaliseCredito(planoCategoriaScoreTO));
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
}
