package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CorDAO;
import com.indracompany.catalogo.datalayer.Cor;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CorTO;

@Stateless
public class CorDAOImpl implements CorDAO {
	
	private static Logger logger = Logger.getLogger(CorDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CorDAO#createUpdateCor(com.indracompany.catalogo.to.CorTO)
	 */
	public void createUpdateCor(CorTO corTO) throws DAOException {
		logger.debug("corTO: " + corTO);
		
		CorTOBuilder corTOBuilder = new CorTOBuilder();
		
		try {
			em.merge(corTOBuilder.createCor(corTO));
		
		} catch (Exception e) {
			throw new DAOException(e);
		}	
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CorDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<CorTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		List<CorTO> list = null;
		StringBuffer queryStr = new StringBuffer();
		
		try {
		
			queryStr.append("select c from Cor c order by c.nmCor");
			Query query = em.createQuery(queryStr.toString());
			
			CorTOBuilder corTOBuilder = new CorTOBuilder();
			list = corTOBuilder.createCorTOList(query.getResultList());
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CorDAO#findById(com.indracompany.catalogo.to.CorTO)
	 */
	public CorTO findById(CorTO corTO) throws DAOException {
		logger.debug("corTO: " + corTO);
		
		CorTOBuilder corTOBuilder = new CorTOBuilder();
		CorTO corResultTO = null;
		
		try {
			corResultTO = corTOBuilder.createCorTO(em.find(Cor.class, corTO.getIdCor()));
		} catch (Exception e) {
			throw new DAOException(e);
		}	
		
		return corResultTO;
	}
	
	public Cor findById(Integer idCor) throws DAOException {
		Cor cor = null;
		try {
			cor = em.find(Cor.class, idCor);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return cor;
	}
	
}