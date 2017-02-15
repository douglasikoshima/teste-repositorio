package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.indracompany.catalogo.dao.interfaces.RegraPrioridadeAltaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.RegraPrioridadeAltaTO;

@Stateless
public class RegraPrioridadeAltaDAOImpl implements RegraPrioridadeAltaDAO{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<RegraPrioridadeAltaTO> buscar() throws DAOException {
		List<RegraPrioridadeAltaTO> result = new ArrayList<RegraPrioridadeAltaTO>();
		
		try {
			result = new RegraPrioridadeAltaTOBuilder().createTOList(em.createNamedQuery("RegraPrioridadeAlta.buscar").getResultList());
		}catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}
	
	
}
