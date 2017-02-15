package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.SegmentoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.SegmentoTO;

@Stateless
public class SegmentoDAOImpl implements SegmentoDAO {
	
	private static Logger logger = Logger.getLogger(SegmentoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	@SuppressWarnings("unchecked")
	public List<SegmentoTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		SegmentoTOBuilder segmentoTOBuilder = new SegmentoTOBuilder();
		List<SegmentoTO> plataformaList = null;
		
		try {
			Query query = em.createNamedQuery("Segmento.findAll");
			plataformaList = segmentoTOBuilder.createSegmentoTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return plataformaList;
	}
}
