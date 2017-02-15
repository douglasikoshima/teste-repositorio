package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.MeioPagamentoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MeioPagamentoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Meio De Pagamento.
 */
@Stateless
public class MeioPagamentoDAOImpl implements MeioPagamentoDAO {
	
	private static Logger logger = Logger.getLogger(MeioPagamentoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.MeioPagamentoDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<MeioPagamentoTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		MeioPagamentoTOBuilder meioPagamentoTOBuilder = new MeioPagamentoTOBuilder();
		List<MeioPagamentoTO> meioPagamentoTOList = null;
		
		try {
			Query query = em.createNamedQuery("MeioPagamento.findAll");
			meioPagamentoTOList = meioPagamentoTOBuilder.createMeioPagamentoTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return meioPagamentoTOList;
	}
}
