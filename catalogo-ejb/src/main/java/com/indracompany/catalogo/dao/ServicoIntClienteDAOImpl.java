package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoIntClienteDAO;
import com.indracompany.catalogo.datalayer.ServicoIntCliente;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntClienteTO;

@Stateless
public class ServicoIntClienteDAOImpl implements ServicoIntClienteDAO {

	
	private static Logger logger = Logger.getLogger(ServicoIntClienteDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	@SuppressWarnings("unchecked")
	public List<ServicoIntCliente> findByIdServicoInteratividade (Integer idServicoInteratividade) throws DAOException {
		logger.debug("idServicoInteratividade" + idServicoInteratividade);
		
		List<ServicoIntCliente> servIntClienteList = new ArrayList<ServicoIntCliente>();

		try {
			Query query = em.createNamedQuery("ServicoIntCliente.findByIdServicoInteratividade").setParameter("idServicoInteratividade",idServicoInteratividade);
			servIntClienteList = query.getResultList();

		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findByIdServicoInteratividade]", e);
		}

		return servIntClienteList;
	}
	
	public void createUpdateServicoIntCliente(ServicoIntClienteTO servicoIntClienteTO) throws DAOException {
		
		ServicoIntClienteTOBuilder  servicoIntClienteTOBuilder = new ServicoIntClienteTOBuilder();
		
		try {
			em.merge(servicoIntClienteTOBuilder.createServicoIntCliente(servicoIntClienteTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeServIntClienteById (Integer idServicoInteratividade) throws DAOException {		
		logger.debug("idServicoInteratividade: " + idServicoInteratividade);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SERVICOINTCLIENTE where IDSERVICOINTERATIVIDADE = :idServicoInteratividade ");
			query.setParameter("idServicoInteratividade", idServicoInteratividade);
			query.executeUpdate();
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
} 

