package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoIntTecnologiaDAO;
import com.indracompany.catalogo.datalayer.ServicoIntTecnologia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntTecnologiaTO;


@Stateless
public class ServicoIntTecnologiaDAOImpl implements ServicoIntTecnologiaDAO {
	
	private static Logger logger = Logger.getLogger(ServicoIntTecnologiaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ServicoIntTecnologia> findByIdServicoInteratividade (Integer idServicoInteratividade) throws DAOException {
		logger.debug("idServicoInteratividade" + idServicoInteratividade);
		
		List<ServicoIntTecnologia> servIntTecnoList = new ArrayList<ServicoIntTecnologia>();
		
		try {
			
			Query query = em.createNamedQuery("ServicoIntTecnologia.findByIdServicoInteratividade").setParameter("idServicoInteratividade", idServicoInteratividade);
			servIntTecnoList = query.getResultList();
			
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findByIdServicoInteratividade]", e);
		}
		
		return servIntTecnoList;
		
	}
	
	public void createUpdateServicoIntTecnologia (ServicoIntTecnologiaTO servicoIntTecnologiaTO) throws DAOException {
		
		ServicoIntTecnologiaTOBuilder servicoIntTecnologiaTOBuilder = new ServicoIntTecnologiaTOBuilder();
		
		try {
			em.merge(servicoIntTecnologiaTOBuilder.createServicoIntTecnologia(servicoIntTecnologiaTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
	public void removeServIntTecnologiaById (Integer idServicoInteratividade) throws DAOException {
		logger.debug("idServicoInteratividade: " + idServicoInteratividade);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SERVICOINTTECNOLOGIA where IDSERVICOINTERATIVIDADE = :idServicoInteratividade ");
			query.setParameter("idServicoInteratividade", idServicoInteratividade);
			query.executeUpdate();
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}				
	}	
}
