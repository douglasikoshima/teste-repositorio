package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.TipoClienteDAO;
import com.indracompany.catalogo.datalayer.TipoCliente;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoClienteTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Parametro.
 */
@Stateless
public class TipoClienteDAOImpl implements TipoClienteDAO {
	
	private static Logger logger = Logger.getLogger(TipoClienteDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TipoClienteDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<TipoClienteTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		TipoClienteTOBuilder tipoClienteTOBuilder = new TipoClienteTOBuilder();
		List<TipoClienteTO> tipoClienteTOList = null;
		
		try {
			Query query = em.createNamedQuery("TipoCliente.findAll");
			tipoClienteTOList = tipoClienteTOBuilder.createTipoClienteTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return tipoClienteTOList;
	}
	
	public TipoCliente findById(Integer idTipoCliente) throws DAOException {
		logger.debug("[findAll]");
	
		TipoCliente tpCliente = new TipoCliente();
		
		try {
			tpCliente = em.find(TipoCliente.class, idTipoCliente);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
						
		return tpCliente; 
	}
}
