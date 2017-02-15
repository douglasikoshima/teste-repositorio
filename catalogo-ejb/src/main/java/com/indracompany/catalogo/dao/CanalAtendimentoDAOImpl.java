package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CanalAtendimentoDAO;
import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalAtendimentoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Canal Atendimento.
 */
@Stateless
public class CanalAtendimentoDAOImpl implements CanalAtendimentoDAO {

	private static Logger logger = Logger.getLogger(CanalAtendimentoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CanalAtendimentoDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<CanalAtendimentoTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		CanalAtendimentoTOBuilder canalAtendimentoTOBuilder = new CanalAtendimentoTOBuilder();
		List<CanalAtendimentoTO> canalAtendimentoList = null;
		
		try {
			Query query = em.createNamedQuery("CanalAtendimento.findAll");
			canalAtendimentoList = canalAtendimentoTOBuilder.createCanalAtendimentoTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return canalAtendimentoList;
	}
	
	public CanalAtendimento findById(Integer idCanal) throws DAOException {
		logger.debug("[findById]");
		CanalAtendimento canal = new CanalAtendimento();
		
		try {
			canal = em.find(CanalAtendimento.class, idCanal);
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);			
		}			
		
		return canal;
	}
	
}
