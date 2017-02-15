package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.EspServicoPlanoMinutoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.EspServicoPlanoMinutoTO;

@Stateless
public class EspServicoPlanoMinutoDAOImpl implements EspServicoPlanoMinutoDAO{
		
	private static Logger logger = Logger.getLogger(EspServicoPlanoMinutoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
    public List<EspServicoPlanoMinutoTO> findAllNotInIdList(List<Integer> idList, Integer idSistema) throws DAOException {
		logger.debug("espServicoPlanoMinutoTOList:" + idList);
		List<EspServicoPlanoMinutoTO> result = null;
		EspServicoPlanoMinutoTOBuilder builder = new EspServicoPlanoMinutoTOBuilder();
		idList.add(new Integer(0));
		StringBuffer queryStr = new StringBuffer();
        queryStr.append("select epm from EspServicoPlanoMinuto epm ");
        queryStr.append(" join epm.servico s ");
        queryStr.append(" where epm.idServico not in (:idList) ");
        queryStr.append(" and s.sistemaServico.sistema.idSistema = :idSistema ");
        queryStr.append(" order by s.nmComercial ");
		
		try {
			Query query = em.createQuery(queryStr.toString());
			query.setParameter("idList", idList);
            query.setParameter("idSistema", idSistema);
			
			result = builder.createTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
    public List<EspServicoPlanoMinutoTO> findAllInIdList(List<Integer> idList) throws DAOException {
		logger.debug("espServicoPlanoMinutoTOList:" + idList);
		List<EspServicoPlanoMinutoTO> result = null;
		EspServicoPlanoMinutoTOBuilder builder = new EspServicoPlanoMinutoTOBuilder();
		idList.add(new Integer(0));
		StringBuffer queryStr = new StringBuffer(
				" select epm from EspServicoPlanoMinuto epm " +
				" join epm.servico s " +
				" where epm.idServico in (:idList) " +
				" order by s.nmComercial "
		);
		
		try {
			Query query = em.createQuery(queryStr.toString());
			query.setParameter("idList", idList);
			
			result = builder.createTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;	}
}
