package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.indracompany.catalogo.dao.interfaces.TipoDocumentoDAO;
import com.indracompany.catalogo.datalayer.TipoDocumento;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoDocumentoTO;

@Stateless
public class TipoDocumentoDAOImpl implements TipoDocumentoDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings({ "unchecked" })
	public List<TipoDocumentoTO> findAll() throws DAOException {
		List<TipoDocumentoTO> result = new ArrayList<TipoDocumentoTO>();
		
		try {
			result = new TipoDocumentoTOBuilder().createTOList((List<TipoDocumento>) em.createNamedQuery("TipoDocumento.findAll").getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}
}