package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.FabricanteDAO;
import com.indracompany.catalogo.datalayer.Fabricante;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FabricanteTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Fabricante.
 */
@Stateless
public class FabricanteDAOImpl implements FabricanteDAO {
	
	private static Logger logger = Logger.getLogger(FabricanteDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FabricanteDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<FabricanteTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		FabricanteTOBuilder fabricanteTOBuilder = new FabricanteTOBuilder();
		List<FabricanteTO> fabricanteTOList = null;
		
		try {
			Query query = em.createNamedQuery("Fabricante.findAll");
			fabricanteTOList = fabricanteTOBuilder.createFabricanteTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return fabricanteTOList;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FabricanteDAO#search(com.indracompany.catalogo.to.FabricanteTO)
	 */
	@SuppressWarnings("unchecked")
	public List<FabricanteTO> search(FabricanteTO fabricanteTO) throws DAOException {
		logger.debug("fabricanteTO: " + fabricanteTO);
		
		FabricanteTOBuilder fabricanteTOBuilder = new FabricanteTOBuilder();
		List<FabricanteTO> fabricanteTOList = null;
		
		try {
			
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("select ftp.fabricante from Fabricante f ");
			
			if (fabricanteTO.getIdTipoProduto() != null ) {
				queryStr.append(", FabricanteTipoProduto ftp ");
			}

			queryStr.append(" where ");
			queryStr.append(" f.dtCriacao is not null ");
			
			if (fabricanteTO.getIdTipoProduto() != null ) {
				queryStr.append(" and f.idFabricante = ftp.tipoProduto.idTipoProduto ");
				queryStr.append(" and ftp.tipoProduto.idTipoProduto = :idTipoProduto ");
			}
			
			Query query = em.createQuery(queryStr.toString());
			
			if (fabricanteTO.getIdTipoProduto() != null ) {
				query.setParameter("idTipoProduto", fabricanteTO.getIdTipoProduto());
			}
			
			fabricanteTOList = fabricanteTOBuilder.createFabricanteTOList(query.getResultList());
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [search]", e);
		}
	    return fabricanteTOList;
	}
	
	public Fabricante findById(Integer idFabricante) throws DAOException {
		Fabricante fabricante = null;
		try {
			fabricante = em.find(Fabricante.class, idFabricante);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return fabricante;
	}
	
}