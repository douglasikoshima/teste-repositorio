package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.RegionalDAO;
import com.indracompany.catalogo.to.RegionalTO;

@Stateless
public class RegionalDAOImpl implements RegionalDAO {
	
	private static Logger log = Logger.getLogger(RegionalDAOImpl.class);
	@PersistenceContext
    private EntityManager em;
	@SuppressWarnings("unchecked")
	public List<RegionalTO> findAll() {
		// TODO Auto-generated method stub
		log.debug("begin");
		return RegionalTOBuilder.createListTO(em.createNamedQuery("Regional.findAll").getResultList());
	}

/*	private Query getQuery(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);
        for (String param : params.keySet()) { 
            Object paramValue = params.get(param);
            query.setParameter(param, params.get(param));
            log.debug(String.format("%s=%s", param, paramValue));
        }
        return query;
    }*/
}
