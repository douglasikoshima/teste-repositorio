package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ClassificacaoMultimidiaDAO;
import com.indracompany.catalogo.datalayer.ClassificacaoMultimidia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;

@Stateless
public class ClassificacaoMultimidiaDAOImpl implements ClassificacaoMultimidiaDAO {
	
	private static Logger logger = Logger.getLogger(ClassificacaoMultimidiaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ClassificacaoMultimidiaDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ClassificacaoMultimidiaTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		List<ClassificacaoMultimidiaTO> list = null;
		StringBuffer queryStr = new StringBuffer();
		
		try {
			
			queryStr.append("select c from ClassificacaoMultimidia c order by c.nomeClassificacao");
			Query query = em.createQuery(queryStr.toString());
			
			ClassificacaoMultimidiaTOBuilder classificacaoMultimidiaTOBuilder = new ClassificacaoMultimidiaTOBuilder();
			list = classificacaoMultimidiaTOBuilder.createClassificacaoMultimidiaTOList(query.getResultList());
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return list;
	}
	
	public ClassificacaoMultimidia findById(Integer idClassificacao) throws DAOException {
		ClassificacaoMultimidia classificacao = null;
		try {
			classificacao = em.find(ClassificacaoMultimidia.class, idClassificacao);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return classificacao;
	}
	
}