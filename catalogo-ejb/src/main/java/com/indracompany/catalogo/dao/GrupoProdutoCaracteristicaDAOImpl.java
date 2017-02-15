package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.GrupoProdutoCaracteristicaDAO;
import com.indracompany.catalogo.datalayer.GrupoProdutoCaracteristica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoProdutoCaracteristicaTO;

@Stateless
public class GrupoProdutoCaracteristicaDAOImpl implements GrupoProdutoCaracteristicaDAO {
	
	private static Logger logger = Logger.getLogger(GrupoProdutoCaracteristicaDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	public void save(GrupoProdutoCaracteristicaTO grupoProdutoCaracteristicaTO) throws DAOException {
		logger.debug("grupoProdutoCaracteristicaTO: " + grupoProdutoCaracteristicaTO);
		try {
			GrupoProdutoCaracteristicaTOBuilder grupoProdutoCaracteristicaTOBuilder = new GrupoProdutoCaracteristicaTOBuilder();
			GrupoProdutoCaracteristica grupoProdutoCaracteristica = grupoProdutoCaracteristicaTOBuilder.createGrupoProdutoCaracteristica(grupoProdutoCaracteristicaTO);
			save(grupoProdutoCaracteristica);
			grupoProdutoCaracteristicaTO.setIdGrupoProdutoCaracteristica(grupoProdutoCaracteristica.getIdGrupoProdutoCaracteristica());
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void save(GrupoProdutoCaracteristica grupoProdutoCaracteristica) throws DAOException {
		try {
			em.persist(grupoProdutoCaracteristica);
			em.flush();
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void remove(GrupoProdutoCaracteristica grupoProdutoCaracteristica) throws DAOException {
		try {
			em.remove(grupoProdutoCaracteristica);
			em.flush();
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [remove]", e);
		}
	}
	
}