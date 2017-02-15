package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.indracompany.catalogo.dao.interfaces.OfertaSAPDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.OfertaSAPTO;

@Stateless
public class OfertaSAPDAOImpl implements OfertaSAPDAO {

	@PersistenceContext
	private EntityManager em; 
	
	@SuppressWarnings("unchecked")
	public List<OfertaSAPTO> findAll() throws DAOException {
		try {
			return new OfertaSAPTOBuilder().createTOList(em.createNamedQuery("OfertaSAP.findAll").getResultList());
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
}