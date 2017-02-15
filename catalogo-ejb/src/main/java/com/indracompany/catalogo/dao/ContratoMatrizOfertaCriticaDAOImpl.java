package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaCriticaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ContratoMatrizOfertaCriticaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de ContratoMatrizOfertaCritica.
 */
@Stateless
public class ContratoMatrizOfertaCriticaDAOImpl implements ContratoMatrizOfertaCriticaDAO {
	
	private static Logger logger = Logger.getLogger(ContratoMatrizOfertaCriticaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaCriticaDAO#findAllCriticas()
	 */
	@SuppressWarnings("unchecked")
	public List<ContratoMatrizOfertaCriticaTO> findAllCriticas() throws DAOException {
		logger.info("findAllCriticas");
		
		List<ContratoMatrizOfertaCriticaTO> list = null;
		
		ContratoMatrizOfertaCriticaTOBuilder contratoMatrizOfertaCriticaTOBuilder = new ContratoMatrizOfertaCriticaTOBuilder();
		
		try {
			
			Query query = em.createNamedQuery("ContratoMatrizOfertaCritica.findAll");
			query.setMaxResults(1000);
			list = contratoMatrizOfertaCriticaTOBuilder.createContratoMatrizOfertaCriticaTOList(query.getResultList());
		
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaCriticaDAO#findAllCriticas()
	 */
	public void removeAllCriticas() throws DAOException {
		logger.info("findAllCriticas");
		
		try {
			
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.CONTRATOMATRIZOFERTACRITICA");
			query.executeUpdate();
		
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
}
