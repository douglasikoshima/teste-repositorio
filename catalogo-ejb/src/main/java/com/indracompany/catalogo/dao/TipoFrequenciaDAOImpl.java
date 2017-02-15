package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.TipoFrequenciaDAO;
import com.indracompany.catalogo.datalayer.TipoFrequencia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoFrequenciaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Servico Interatividade.
 */
@Stateless
public class TipoFrequenciaDAOImpl implements TipoFrequenciaDAO {
	
	private static Logger logger = Logger.getLogger(TipoFrequenciaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TipoFrequenciaDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<TipoFrequenciaTO> findAll() throws DAOException {
		
		List<TipoFrequenciaTO> list = null;
		
		try {
			
			Query query = em.createNamedQuery("TipoFrequencia.findAll");
			TipoFrequenciaTOBuilder tipoFrequenciaTOBuilder = new TipoFrequenciaTOBuilder();
			list = tipoFrequenciaTOBuilder.createTipoFrequenciaTOList(query.getResultList());
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
			
		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TipoFrequenciaDAO#createUpdateTipoFrequencia(com.indracompany.catalogo.to.TipoFrequenciaTO)
	 */
	public void createUpdateTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws DAOException {
		logger.debug("tipoFrequenciaTO: " + tipoFrequenciaTO);
		
		TipoFrequenciaTOBuilder tipoFrequenciaTOBuilder = new TipoFrequenciaTOBuilder();
		
		try {
			em.merge(tipoFrequenciaTOBuilder.createTipoFrequencia(tipoFrequenciaTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TipoFrequenciaDAO#findById(com.indracompany.catalogo.to.TipoFrequenciaTO)
	 */
	public TipoFrequenciaTO findById(TipoFrequenciaTO tipoFrequenciaTO) throws DAOException {
		logger.debug("tipoFrequenciaTO: " + tipoFrequenciaTO);
		
		TipoFrequenciaTO tipoFrequenciaResultTO = null;
		TipoFrequenciaTOBuilder tipoFrequenciaTOBuilder = new TipoFrequenciaTOBuilder();
		
		try {
			tipoFrequenciaResultTO = tipoFrequenciaTOBuilder.createTipoFrequenciaTO(em.find(TipoFrequencia.class, tipoFrequenciaTO.getIdTipoFrequencia()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return tipoFrequenciaResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TipoFrequenciaDAO#removeTipoFrequencia(com.indracompany.catalogo.to.TipoFrequenciaTO)
	 */
	public void removeTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws DAOException {
		logger.debug("tipoFrequenciaTO: " + tipoFrequenciaTO);
		
		try {
			em.remove(em.find(TipoFrequencia.class, tipoFrequenciaTO.getIdTipoFrequencia()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TipoFrequenciaDAO#existTipoFrequencia(com.indracompany.catalogo.to.TipoFrequenciaTO)
	 */
	public Boolean existTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws DAOException {
		logger.debug("tipoFrequenciaTO: " + tipoFrequenciaTO);
		
		Boolean retorno = Boolean.TRUE;
		
		try {
			
			Query query = em.createQuery("select tf from TipoFrequencia tf where upper(tf.nmTipoFrequencia) = upper(:nmTipoFrequencia)");
			query.setParameter("nmTipoFrequencia", tipoFrequenciaTO.getNmTipoFrequencia());
			query.getSingleResult();
			
		}  catch (NoResultException e) {	
			retorno = Boolean.FALSE;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return retorno;
	}
}
