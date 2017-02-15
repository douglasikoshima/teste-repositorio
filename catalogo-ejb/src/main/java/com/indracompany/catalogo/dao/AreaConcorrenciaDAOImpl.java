package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.AreaConcorrenciaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AreaConcorrenciaTO;

@Stateless
public class AreaConcorrenciaDAOImpl implements AreaConcorrenciaDAO{
	private static Logger logger = Logger.getLogger(AreaConcorrenciaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<AreaConcorrenciaTO> findAll() throws DAOException {
		List<AreaConcorrenciaTO> result = null;
		
		try {
			Query query = em.createNamedQuery("AreaConcorrencia.findAll");
			result = new AreaConcorrenciaTOBuilder().createTOList(query.getResultList());
		} catch(Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<AreaConcorrenciaTO> findAllNotInIdList(List<Long> idList) throws DAOException {
		logger.debug("areaConcorrenciaIdList:" + idList);
		List<AreaConcorrenciaTO> result = null;
		AreaConcorrenciaTOBuilder builder = new AreaConcorrenciaTOBuilder();
		
		idList.add(new Long(0));
		StringBuffer queryStr = new StringBuffer(
				" select a from AreaConcorrencia a where " +
				" a.idAreaConcorrencia not in (:idList) " +
				" order by a.nmAreaConcorrencia "
		);
		
		try {
			
			Query query = em.createQuery(queryStr.toString());
			query.setParameter("idList", idList);
			
			result = builder.createTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}



	@SuppressWarnings("unchecked")
	public List<AreaConcorrenciaTO> findAllInIdList(List<Long> idList) throws DAOException {
		logger.debug("areaConcorrenciaIdList:" + idList);
		List<AreaConcorrenciaTO> result = null;
		AreaConcorrenciaTOBuilder builder = new AreaConcorrenciaTOBuilder();
		
		StringBuffer queryStr = new StringBuffer(
				" select a from AreaConcorrencia a where " +
				" a.idAreaConcorrencia in (:idList) " +
				" order by a.nmAreaConcorrencia "
		);
		
		try {
			idList.add(new Long(0));
			Query query = em.createQuery(queryStr.toString());
			query.setParameter("idList", idList);
			
			result = builder.createTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}
}
