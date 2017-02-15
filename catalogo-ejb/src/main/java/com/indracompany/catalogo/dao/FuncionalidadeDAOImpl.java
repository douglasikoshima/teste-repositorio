package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.FuncionalidadeDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FuncionalidadeTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Funcionalidade.
 */
@Stateless
public class FuncionalidadeDAOImpl implements FuncionalidadeDAO {

	private static Logger logger = Logger.getLogger(FuncionalidadeDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FuncionalidadeDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<FuncionalidadeTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		FuncionalidadeTOBuilder funcionalidadeTOBuilder = new FuncionalidadeTOBuilder();
		List<FuncionalidadeTO> funcionalidadeList = null;
		
		try {
			Query query = em.createNamedQuery("Funcionalidade.findAll");
			funcionalidadeList = funcionalidadeTOBuilder.createFuncionalidadeTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return funcionalidadeList;
	}
}
