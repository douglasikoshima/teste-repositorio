package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ClassificacaoCategoriaScoreDAO;
import com.indracompany.catalogo.datalayer.ClassificacaoCategoriaScore;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ClassificacaoCategoriaScoreTO;


@Stateless
public class ClassificacaoCategoriaScoreDAOImpl implements ClassificacaoCategoriaScoreDAO{

	private static Logger logger = Logger.getLogger(ClassificacaoCategoriaScoreDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	@SuppressWarnings("unchecked")
    public List<ClassificacaoCategoriaScoreTO> findAll() throws DAOException {
		
		List<ClassificacaoCategoriaScoreTO> classificacaoCategoriaScoreTOList = null;
		List<ClassificacaoCategoriaScore> classificacaoCategoriaScoreList = null;
		ClassificacaoCategoriaScoreTOBuilder classificacaoCategoriaScoreTOBuilder = new ClassificacaoCategoriaScoreTOBuilder();
		try {
			Query query = em.createNamedQuery("ClassificacaoCategoriaScore.findAll");
			classificacaoCategoriaScoreList = query.getResultList();
			classificacaoCategoriaScoreTOList = classificacaoCategoriaScoreTOBuilder.createClassificacaoCategoriaScoreTOList(classificacaoCategoriaScoreList);
		} catch(Exception e){
            logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		
		return classificacaoCategoriaScoreTOList;
	}
}
