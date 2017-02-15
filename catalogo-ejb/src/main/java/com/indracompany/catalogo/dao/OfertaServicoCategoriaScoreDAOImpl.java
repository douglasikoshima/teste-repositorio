package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.OfertaServicoCategoriaScoreDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.OfertaServicoCategoriaScoreTO;

@Stateless
public class OfertaServicoCategoriaScoreDAOImpl implements OfertaServicoCategoriaScoreDAO {

	private static Logger logger = Logger.getLogger(OfertaServicoCategoriaScoreDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	public void removeByCategoriaScore(OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) throws DAOException{
		logger.debug("ofertaServicoCategoriaScoreTO: " + ofertaServicoCategoriaScoreTO);
		
		try {	
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.OFERTASERVICOCATEGORIASCORE WHERE IDCATEGORIASCORE = :idCategoriaScore");			
			query.setParameter("idCategoriaScore", ofertaServicoCategoriaScoreTO.getCategoriaScoreTO().getIdCategoriaScore());
			query.executeUpdate();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeByOfertaServico(OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) throws DAOException{
		logger.debug("ofertaServicoCategoriaScoreTO: " + ofertaServicoCategoriaScoreTO);
		
		try {	
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.OFERTASERVICOCATEGORIASCORE WHERE IDOFERTASERVICO = :idOfertaServico");			
			query.setParameter("idOfertaServico", ofertaServicoCategoriaScoreTO.getIdOfertaServico());
			query.executeUpdate();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void createOfertaServicoCategoriaScore(OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) throws DAOException {
		logger.debug("ofertaServicoCategoriaScoreTO: " + ofertaServicoCategoriaScoreTO);
		
		OfertaServicoCategoriaScoreTOBuilder ofertaServicoCategoriaScoreTOBuilder = new OfertaServicoCategoriaScoreTOBuilder();
		
		try {	
			
			em.persist(ofertaServicoCategoriaScoreTOBuilder.createOfertaServicoCategoriaScore(ofertaServicoCategoriaScoreTO));
			
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
}
