package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoCategoriaScoreDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoCategoriaScoreTO;

@Stateless
public class ServicoCategoriaScoreDAOImpl implements ServicoCategoriaScoreDAO{

	private static Logger logger = Logger.getLogger(ServicoCategoriaScoreDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	public void removeByCategoriaScore(ServicoCategoriaScoreTO servicoCategoriaScoreTO) throws DAOException{
		logger.debug("servicoCategoriaScoreTO: " + servicoCategoriaScoreTO);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SERVICOCATEGORIASCORE WHERE IDCATEGORIASCORE = :idCategoriaScore");			
			query.setParameter("idCategoriaScore", servicoCategoriaScoreTO.getCategoriaScoreTO().getIdCategoriaScore());
			query.executeUpdate();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeByServico(ServicoCategoriaScoreTO servicoCategoriaScoreTO) throws DAOException {
		logger.debug("servicoCategoriaScoreTO: " + servicoCategoriaScoreTO);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SERVICOCATEGORIASCORE WHERE IDSERVICO = :idservico");			
			query.setParameter("idservico", servicoCategoriaScoreTO.getIdServico());
			query.executeUpdate();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void createServicoCategoriaScore(ServicoCategoriaScoreTO servicoCategoriaScoreTO) throws DAOException {
		logger.debug("servicoCategoriaScoreTO: " + servicoCategoriaScoreTO);
		
		ServicoCategoriaScoreTOBuilder servicoCategoriaScoreTOBuilder = new ServicoCategoriaScoreTOBuilder();
		
		try {
			em.persist(servicoCategoriaScoreTOBuilder.createCategorizacaoAnaliseCredito(servicoCategoriaScoreTO));
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
}
