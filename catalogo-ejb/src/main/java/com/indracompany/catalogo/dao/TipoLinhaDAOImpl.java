package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.TipoLinhaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoLinhaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Tipo Linha.
 */
@Stateless
public class TipoLinhaDAOImpl implements TipoLinhaDAO {
	
	private static Logger logger = Logger.getLogger(TipoLinhaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TipoLinhaDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<TipoLinhaTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		TipoLinhaTOBuilder tipoLinhaTOBuilder = new TipoLinhaTOBuilder();
		List<TipoLinhaTO> tipoLinhaTOList = null;
		
		try {
			Query query = em.createNamedQuery("TipoLinha.findAll");
			tipoLinhaTOList = tipoLinhaTOBuilder.createTipoLinhaTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return tipoLinhaTOList;
	}
}
