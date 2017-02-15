package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.indracompany.catalogo.dao.interfaces.GrupoProdutoTecnFreqVlDAO;
import com.indracompany.catalogo.datalayer.GrupoProdutoTecnFreqVl;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoProdutoTecnFreqVlTO;

@Stateless
public class GrupoProdutoTecnFreqVlDAOImpl implements GrupoProdutoTecnFreqVlDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(GrupoProdutoTecnFreqVlTO grupoProdutoTecnFreqVlTO) throws DAOException {
		try {
			GrupoProdutoTecnFreqVlTOBuilder grupoProdutoTecnFreqVlTOBuilder = new GrupoProdutoTecnFreqVlTOBuilder();
			GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl = grupoProdutoTecnFreqVlTOBuilder.createGrupoProdutoTecnFreqVl(grupoProdutoTecnFreqVlTO);
			save(grupoProdutoTecnFreqVl);
			grupoProdutoTecnFreqVlTO.setIdGrupoProdutoTecnfreqVl(grupoProdutoTecnFreqVl.getIdGrupoProdutoTecnfreqVl());
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void save(GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl) throws DAOException {
		try {
			em.persist(grupoProdutoTecnFreqVl);
			em.flush();
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void remove(GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl) throws DAOException {
		try {
			em.remove(grupoProdutoTecnFreqVl);
			em.flush();
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [remove]", e);
		}
	}
	
}