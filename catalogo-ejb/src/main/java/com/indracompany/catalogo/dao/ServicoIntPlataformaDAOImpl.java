package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoIntPlataformaDAO;
import com.indracompany.catalogo.datalayer.ServicoIntPlataforma;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntPlataformaTO;


@Stateless
public class ServicoIntPlataformaDAOImpl implements ServicoIntPlataformaDAO {
	
	private static Logger logger = Logger.getLogger(ServicoIntPlataformaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	@SuppressWarnings("unchecked")
	public List<ServicoIntPlataforma> findByIdServicoInteratividade (Integer idServicoInteratividade) throws DAOException {
		logger.debug("idServicoInteratividade");
		
		List<ServicoIntPlataforma> servIntPlatList = new ArrayList<ServicoIntPlataforma>();
		
		try {
			
			Query query = em.createNamedQuery("ServicoIntCanal.findByIdServicoInteratividade").setParameter("idServicoInteratividade", idServicoInteratividade);
			servIntPlatList = query.getResultList();
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findByIdServicoInteratividade]", e);			
		}	
		
		return servIntPlatList;
	}
	
	public void createUpdateServicoIntPlataforma(ServicoIntPlataformaTO servicoIntPlataformaTO) throws DAOException {
		
		ServicoIntPlataformaTOBuilder servicoIntPlataformaTOBuilder = new ServicoIntPlataformaTOBuilder();
		
		try {
			
			em.merge(servicoIntPlataformaTOBuilder.createServicoIntPlataforma(servicoIntPlataformaTO));
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	public void removeSrvIntPlataformaById (Integer idServicoInteratividade) throws DAOException {
		logger.debug("idServicoInteratividade: " + idServicoInteratividade);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SERVICOINTPLATAFORMA where IDSERVICOINTERATIVIDADE = :idServicoInteratividade ");
			query.setParameter("idServicoInteratividade", idServicoInteratividade);
			query.executeUpdate();
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
}
