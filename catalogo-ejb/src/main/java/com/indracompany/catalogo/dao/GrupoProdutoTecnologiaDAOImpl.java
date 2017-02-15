package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.GrupoProdutoTecnologiaDAO;
import com.indracompany.catalogo.datalayer.GrupoProdutoTecnologia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoProdutoTecnologiaTO;

@Stateless
public class GrupoProdutoTecnologiaDAOImpl implements GrupoProdutoTecnologiaDAO {
	
	private static Logger logger = Logger.getLogger(GrupoProdutoTecnologiaDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	public void save(GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO) throws DAOException {
		logger.debug("grupoProdutoTecnologiaTO: " + grupoProdutoTecnologiaTO);
		try {
			GrupoProdutoTecnologiaTOBuilder grupoProdutoTecnologiaTOBuilder = new GrupoProdutoTecnologiaTOBuilder();
			GrupoProdutoTecnologia grupoProdutoTecnologia = grupoProdutoTecnologiaTOBuilder.createGrupoProdutoTecnologia(grupoProdutoTecnologiaTO);
			save(grupoProdutoTecnologia);
			grupoProdutoTecnologiaTO.setIdGrupoProdutoTecn(grupoProdutoTecnologia.getIdGrupoProdutoTecn());
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void save(GrupoProdutoTecnologia grupoProdutoTecnologia) throws DAOException {
		try {
			em.persist(grupoProdutoTecnologia);
			em.flush();
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void remove(GrupoProdutoTecnologia grupoProdutoTecnologia) throws DAOException {
		try {
			em.remove(grupoProdutoTecnologia);
			em.flush();
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [remove]", e);
		}
	}
	
}