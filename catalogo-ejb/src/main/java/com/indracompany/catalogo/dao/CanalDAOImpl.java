package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CanalDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Canal.
 */
@Stateless
public class CanalDAOImpl implements CanalDAO {
	
	private static Logger logger = Logger.getLogger(CanalDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CanalDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<CanalTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		CanalTOBuilder canalTOBuilder = new CanalTOBuilder();
		List<CanalTO> plataformaList = null;
		
		try {
			Query query = em.createNamedQuery("Canal.findAll");
			plataformaList = canalTOBuilder.createCanalTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return plataformaList;
	}
}
