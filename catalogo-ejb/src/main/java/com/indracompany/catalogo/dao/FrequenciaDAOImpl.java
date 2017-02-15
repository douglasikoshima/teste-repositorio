package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.FrequenciaDAO;
import com.indracompany.catalogo.datalayer.Frequencia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FrequenciaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Servico Interatividade.
 */
@Stateless
public class FrequenciaDAOImpl implements FrequenciaDAO {
	
	private static Logger logger = Logger.getLogger(FrequenciaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FrequenciaDAO#findAll(com.indracompany.catalogo.to.FrequenciaTO)
	 */
	@SuppressWarnings("unchecked")
	public List<FrequenciaTO> findAll() throws DAOException {
		
		List<FrequenciaTO> list = null;
		
		try {
			
			Query query = em.createNamedQuery("Frequencia.findAll");
			FrequenciaTOBuilder frequenciaTOBuilder = new FrequenciaTOBuilder();
			list = frequenciaTOBuilder.createFrequenciaTOList(query.getResultList());
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
			
		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FrequenciaDAO#createFrequencia(com.indracompany.catalogo.to.FrequenciaTO)
	 */
	public void createUpdateFrequencia(FrequenciaTO frequenciaTO) throws DAOException {
		logger.debug("frequenciaTO: " + frequenciaTO);
		
		FrequenciaTOBuilder frequenciaTOBuilder = new FrequenciaTOBuilder();
		
		try {
			em.merge(frequenciaTOBuilder.createFrequencia(frequenciaTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FrequenciaDAO#findById(com.indracompany.catalogo.to.FrequenciaTO)
	 */
	public FrequenciaTO findById(FrequenciaTO frequenciaTO) throws DAOException {
		logger.debug("frequenciaTO: " + frequenciaTO);
		
		FrequenciaTO frequenciaResultTO = null;
		FrequenciaTOBuilder frequenciaTOBuilder = new FrequenciaTOBuilder();
		
		try {
			frequenciaResultTO = frequenciaTOBuilder.createFrequenciaTO(em.find(Frequencia.class, frequenciaTO.getIdVlFrequencia()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return frequenciaResultTO;
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FrequenciaDAO#removeFrequencia(com.indracompany.catalogo.to.FrequenciaTO)
	 */
	public void removeFrequencia(FrequenciaTO frequenciaTO) throws DAOException {
		logger.debug("frequenciaTO: " + frequenciaTO);
		
		try {
			em.remove(em.find(Frequencia.class, frequenciaTO.getIdVlFrequencia()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
