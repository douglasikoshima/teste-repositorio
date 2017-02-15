package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.UfDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.UfTO;

@Stateless
public class UfDAOImpl implements UfDAO {
	
	private static Logger logger = Logger.getLogger(UfDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<UfTO> findAll() throws DAOException {

		logger.debug(">> findAll()");
		
		UfTOBuilder ufTOBuilder = new UfTOBuilder();
		List<UfTO> ufTOList;
		
		try {
			Query query = em.createNamedQuery("Uf.findAll");
			ufTOList = ufTOBuilder.createTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
		
		logger.debug("<< findAll()");
		
		return ufTOList;
	}

}
