package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.TipoProdutoDAO;
import com.indracompany.catalogo.datalayer.TipoProduto;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FabricanteTO;
import com.indracompany.catalogo.to.TipoProdutoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Tipo Produto.
 */
@Stateless
public class TipoProdutoDAOImpl implements TipoProdutoDAO {
	
	private static Logger logger = Logger.getLogger(TipoProdutoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TipoProdutoDAO#findAll()
	 */
	public List<TipoProdutoTO> findAll() throws DAOException {
	    return findAll(new TipoProdutoTOBuilder());
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoProdutoTO> findAll(TipoProdutoTOBuilder tipoProdutoTOBuilder) throws DAOException {
		logger.debug("[findAll]");
		
		List<TipoProdutoTO> tipoProdutoTOList = null;
		
		try {
			Query query = em.createNamedQuery("TipoProduto.findAll");
			tipoProdutoTOList = tipoProdutoTOBuilder.createTipoProdutoTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return tipoProdutoTOList;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TipoProdutoDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<TipoProdutoTO> findAllButSimCards() throws DAOException {
		logger.debug("[findAllButSimCards]");
		
		TipoProdutoTOBuilder tipoProdutoTOBuilder = new TipoProdutoTOBuilder();
		List<TipoProdutoTO> tipoProdutoTOList = null;
		
		try {
			Query query = em.createNamedQuery("TipoProduto.findAllButSimCards");
			tipoProdutoTOList = tipoProdutoTOBuilder.createTipoProdutoTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return tipoProdutoTOList;
	}
	
	public TipoProdutoTO findById(TipoProdutoTO tipoProdutoTO) throws DAOException {
		logger.debug("tipoProdutoTO: " + tipoProdutoTO);
		
		TipoProdutoTOBuilder tipoProdutoTOBuilder = new TipoProdutoTOBuilder();
		TipoProdutoTO tipoProdutoResultTO = null;
		
		try {
			tipoProdutoResultTO = tipoProdutoTOBuilder.createTipoProdutoTO(findById(tipoProdutoTO.getIdTipoProduto()));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
	    return tipoProdutoResultTO;
	}
	
	public TipoProduto findById(Integer idTipoProduto) throws DAOException {
		TipoProduto tipoProduto = null;
		try {
			tipoProduto = em.find(TipoProduto.class, idTipoProduto);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return tipoProduto;
	}

	public List<FabricanteTO> findByIdTipoProduto(Integer idTipoProduto) throws DAOException {
		List<FabricanteTO> result = new ArrayList<FabricanteTO>();
		
		try {
			result = new FabricanteTOBuilder().createFabricanteTOList(em.find(TipoProduto.class, idTipoProduto).getFabricanteList());
		} catch(Exception e){
			throw new DAOException("Erro ao executar o DAO [findByIdTipoProduto]", e);
		}
		
		return result;
	}
	
	
}
