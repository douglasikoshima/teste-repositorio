package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.indracompany.catalogo.dao.interfaces.MatrizOfertaItemPrecoHistDAO;
import com.indracompany.catalogo.datalayer.MatrizOfertaItemPrecoHist;
import com.indracompany.catalogo.exception.DAOException;

@Stateless
public class MatrizOfertaItemPrecoHistDAOImpl implements MatrizOfertaItemPrecoHistDAO{

	@PersistenceContext
	private EntityManager em;

	public void remove(Long id) throws DAOException {
		try {
			em.remove(em.find(MatrizOfertaItemPrecoHist.class, id));
		} catch(Exception e){
			throw new DAOException(e);
		}
	}
}
