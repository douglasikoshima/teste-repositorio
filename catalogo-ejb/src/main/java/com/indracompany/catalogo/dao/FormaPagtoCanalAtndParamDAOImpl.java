package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.FormaPagtoCanalAtndParamDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FormaPagtoCanalAtndParamTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de FormaPagtoCanalAtndParam.
 */
@Stateless
public class FormaPagtoCanalAtndParamDAOImpl implements FormaPagtoCanalAtndParamDAO {
	
	private static Logger logger = Logger.getLogger(FormaPagtoCanalAtndParamDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagtoCanalParamDAO#createUpdateFormaPagtoCanalParam(com.indracompany.catalogo.to.FormaPagtoCanalParamTO)
	 */
	public void createUpdateFormaPagtoCanalParam(FormaPagtoCanalAtndParamTO formaPagtoCanalAtndParamTO) throws DAOException {
		logger.debug("formaPagtoCanalAtndParamTO: " + formaPagtoCanalAtndParamTO);
		
		FormaPagtoCanalAtndParamTOBuilder formaPagtoCanalAtndParamTOBuilder = new FormaPagtoCanalAtndParamTOBuilder();
		
		try {
			em.merge(formaPagtoCanalAtndParamTOBuilder.createFormaPagtoCanalAtndParam(formaPagtoCanalAtndParamTO));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [createUpdateFormaPagtoCanalParam]", e);
		}
	}
	
}
