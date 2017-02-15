package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.SrvInteratividadeParamBaseDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.SrvInteratividadeParamBaseTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Parametro.
 */
@Stateless
public class SrvInteratividadeParamBaseDAOImpl implements SrvInteratividadeParamBaseDAO {
	
	private static Logger logger = Logger.getLogger(SrvInteratividadeParamBaseDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.SrvInteratividadeParamBaseDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<SrvInteratividadeParamBaseTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		SrvInteratividadeParamBaseTOBuilder parametroTOBuilder = new SrvInteratividadeParamBaseTOBuilder();
		List<SrvInteratividadeParamBaseTO> parametroTOList = null;
		
		try {
			Query query = em.createNamedQuery("SrvInteratividadeParamBase.findAll");
			parametroTOList = parametroTOBuilder.createSrvInteratividadeParamBaseTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return parametroTOList;
	}

}
