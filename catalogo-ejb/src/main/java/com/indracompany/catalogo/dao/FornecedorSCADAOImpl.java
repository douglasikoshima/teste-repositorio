package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.FornecedorSCADAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FornecedorSCATO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Fornecedor SCA.
 */
@Stateless
public class FornecedorSCADAOImpl implements FornecedorSCADAO {
	
	private static Logger logger = Logger.getLogger(FornecedorSCADAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FornecedorSCADAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<FornecedorSCATO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		FornecedorSCATOBuilder fornecedorSCATOBuilder = new FornecedorSCATOBuilder();
		List<FornecedorSCATO> list = null;
		
		try {
			Query query = em.createNamedQuery("FornecedorSCA.findAll");
			list = fornecedorSCATOBuilder.createFornecedorSCATOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return list;
	}
}
