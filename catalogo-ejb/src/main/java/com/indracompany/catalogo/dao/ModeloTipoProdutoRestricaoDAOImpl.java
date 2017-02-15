package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ModeloTipoProdutoRestricaoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ModeloTipoProdutoRestricaoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Modelo Tipo Produto Restricao.
 */
@Stateless
public class ModeloTipoProdutoRestricaoDAOImpl implements ModeloTipoProdutoRestricaoDAO {
	
	private static Logger logger = Logger.getLogger(ModeloTipoProdutoRestricaoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ModeloTipoProdutoRestricaoDAO#createUpdateAcao(com.indracompany.catalogo.to.ModeloTipoProdutoRestricaoTO)
	 */
	public ModeloTipoProdutoRestricaoTO createUpdateModeloTipoProdutoRestricao(ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoTO) throws DAOException {
		logger.debug("modeloTipoProdutoRestricaoTO: " + modeloTipoProdutoRestricaoTO);
		
		ModeloTipoProdutoRestricaoTOBuilder modeloTipoProdutoRestricaoTOBuilder = new ModeloTipoProdutoRestricaoTOBuilder();
		ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoResultTO = null;
		
		try {
			modeloTipoProdutoRestricaoResultTO = modeloTipoProdutoRestricaoTOBuilder.createModeloTipoProdutoRestricaoTO(em.merge(modeloTipoProdutoRestricaoTOBuilder.createModeloTipoProdutoRestricao(modeloTipoProdutoRestricaoTO)));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return modeloTipoProdutoRestricaoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ModeloTipoProdutoRestricaoDAO#removeModeloTipoProdutoRestricaoByGrupoProduto(java.lang.Integer)
	 */
	public void removeModeloTipoProdutoRestricaoByGrupoProduto(Integer idGrupoProduto) throws DAOException {
		logger.debug("idGrupoProduto: " + idGrupoProduto);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.MODELOTIPOPRODUTORESTRICAO WHERE IDGRUPOPRODUTO = :idGrupoProduto ");
			query.setParameter("idGrupoProduto", idGrupoProduto);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
