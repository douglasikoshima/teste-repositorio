package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ModeloTipoProdutoCompativelDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ModeloTipoProdutoCompativelTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Modelo Tipo Produto Compativel.
 */
@Stateless
public class ModeloTipoProdutoCompativelDAOImpl implements ModeloTipoProdutoCompativelDAO {
	
	private static Logger logger = Logger.getLogger(ModeloTipoProdutoCompativelDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ModeloTipoProdutoCompativelDAO#createUpdateAcao(com.indracompany.catalogo.to.ModeloTipoProdutoCompativelTO)
	 */
	public ModeloTipoProdutoCompativelTO createUpdateModeloTipoProdutoCompativel(ModeloTipoProdutoCompativelTO modeloTipoProdutoCompativelTO) throws DAOException {
		logger.debug("modeloTipoProdutoCompativelTO: " + modeloTipoProdutoCompativelTO);
		
		ModeloTipoProdutoCompativelTOBuilder modeloTipoProdutoCompativelTOBuilder = new ModeloTipoProdutoCompativelTOBuilder();
		ModeloTipoProdutoCompativelTO modeloTipoProdutoCompativelResultTO = null;
		
		try {
			modeloTipoProdutoCompativelResultTO = modeloTipoProdutoCompativelTOBuilder.createModeloTipoProdutoCompativelTO(em.merge(modeloTipoProdutoCompativelTOBuilder.createModeloTipoProdutoCompativel(modeloTipoProdutoCompativelTO)));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return modeloTipoProdutoCompativelResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ModeloTipoProdutoCompativelDAO#removeModeloTipoProdutoCompativel(java.lang.Integer)
	 */
	public void removeModeloTipoProdutoCompativel(Integer idGrupoProduto) throws DAOException {
		logger.debug("idGrupoProduto: " + idGrupoProduto);
		
		try {
			
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.MODELOTIPOPRODUTOCOMPATIVEL WHERE IDMODELOTIPOPRODUTORESTRICAO IN (SELECT IDMODELOTIPOPRODUTORESTRICAO FROM CATALOGOPRS_OW.MODELOTIPOPRODUTORESTRICAO WHERE IDGRUPOPRODUTO = :idGrupoProduto) ");
			query.setParameter("idGrupoProduto", idGrupoProduto);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
}
