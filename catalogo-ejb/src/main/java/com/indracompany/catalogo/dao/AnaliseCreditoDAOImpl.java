package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.AnaliseCreditoDAO;
import com.indracompany.catalogo.datalayer.AnaliseCredito;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco dos dados do Cabecalho de Analise de Credito
 */
@Stateless
public class AnaliseCreditoDAOImpl implements AnaliseCreditoDAO {
	
	private static Logger logger = Logger.getLogger(AnaliseCreditoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	

	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AnaliseCreditoDAO#findById(com.indracompany.catalogo.to.AnaliseCreditoTO)
	 */
	public AnaliseCreditoTO findById(AnaliseCreditoTO analiseCreditoTO) throws DAOException {
		logger.debug("analiseCreditoTO: " + analiseCreditoTO);
		
		AnaliseCreditoTO result = null;
		AnaliseCreditoTOBuilder analiseCreditoTOBuilder = new AnaliseCreditoTOBuilder();
		
		try {
			result = analiseCreditoTOBuilder.createAnaliseCreditoTO(em.find(AnaliseCredito.class, analiseCreditoTO.getIdAnaliseCredito()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<AnaliseCreditoTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		AnaliseCreditoTOBuilder analiseCreditoTOBuilder = new AnaliseCreditoTOBuilder();
		List<AnaliseCreditoTO> analiseCreditoTOList = null;
		
		try {
			Query query = em.createNamedQuery("AnaliseCredito.findAll");
			analiseCreditoTOList = analiseCreditoTOBuilder.createAnaliseCreditoTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return analiseCreditoTOList;
	}

}
