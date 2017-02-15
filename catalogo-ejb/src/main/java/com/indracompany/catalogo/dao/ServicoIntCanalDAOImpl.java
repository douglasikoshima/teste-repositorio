package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoIntCanalDAO;
import com.indracompany.catalogo.datalayer.ServicoIntCanal;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntCanalTO;


@Stateless
public class ServicoIntCanalDAOImpl implements ServicoIntCanalDAO {
	
	private static Logger logger = Logger.getLogger(ServicoIntCanalDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	@SuppressWarnings("unchecked")
	public List<ServicoIntCanal> findById (Integer idServicoInteratividade) throws DAOException {
		logger.debug("idServicoInteratividade" + idServicoInteratividade);
		
		List<ServicoIntCanal> servIntCanalList = new ArrayList<ServicoIntCanal>();
		
		try {
			
			Query query = em.createNamedQuery("ServicoIntCanal.findById").setParameter("idServicoInteratividade", idServicoInteratividade);
			
			servIntCanalList = query.getResultList();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);			
		}	
		
		return servIntCanalList;
	}
	
	public void createUpdateServicoIntCanal(ServicoIntCanalTO servicoIntCanalTO) throws DAOException {
		logger.debug("servicoIntCanalTO" + servicoIntCanalTO);
		
		ServicoIntCanalTOBuilder servicoIntCanalTOBuilder = new ServicoIntCanalTOBuilder();
		
		try {
			em.merge(servicoIntCanalTOBuilder.createServicoIntCanal(servicoIntCanalTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
	public void removeSrvIntCanalById (Integer idServicoInteratividade) throws DAOException {
		logger.debug("idServicoInteratividade: " + idServicoInteratividade);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SERVICOINTCANAL where IDSERVICOINTERATIVIDADE = :idServicoInteratividade ");
			query.setParameter("idServicoInteratividade", idServicoInteratividade);
			query.executeUpdate();
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
			
	}


}
