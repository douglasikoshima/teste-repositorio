package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.indracompany.catalogo.dao.interfaces.OrganizacaoVendaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.OrganizacaoVendaTO;

@Stateless
public class OrganizacaoVendaDAOImpl implements OrganizacaoVendaDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<OrganizacaoVendaTO> findAll() throws DAOException {
		List<OrganizacaoVendaTO> result = null;
		
		try {
			Query query = em.createNamedQuery("OrganizacaoVenda.findAll");
			result = new OrganizacaoVendaTOBuilder().createTOList(query.getResultList());
		} catch(Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}

}
