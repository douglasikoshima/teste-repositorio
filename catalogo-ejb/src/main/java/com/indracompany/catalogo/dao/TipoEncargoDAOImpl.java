package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.TipoEncargoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoEncargoTO;

@Stateless
public class TipoEncargoDAOImpl implements TipoEncargoDAO{

	private static Logger logger = Logger.getLogger(TipoEncargoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	public List<TipoEncargoTO> findAll() throws DAOException {
		logger.debug("Inicio: TipoEncargoDAOImpl.findAll()");
		Query query = em.createNamedQuery("TipoEncargo.findAll");
		@SuppressWarnings("unchecked")
		List<TipoEncargoTO> result = new TipoEncargoTOBuilder().createTOList(query.getResultList());
		logger.debug("Fim: TipoEncargoDAOImpl.findAll()");
		return result;
	}
}
