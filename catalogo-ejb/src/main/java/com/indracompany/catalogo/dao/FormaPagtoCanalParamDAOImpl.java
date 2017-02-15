package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.FormaPagtoCanalParamDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FormaPagtoCanalParamTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de FormaPagtoCanalParam.
 */
@Stateless
public class FormaPagtoCanalParamDAOImpl implements FormaPagtoCanalParamDAO {
	
	private static Logger logger = Logger.getLogger(FormaPagtoCanalParamDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagtoCanalParamDAO#createUpdateFormaPagtoCanalParam(com.indracompany.catalogo.to.FormaPagtoCanalParamTO)
	 */
	public FormaPagtoCanalParamTO createUpdateFormaPagtoCanalParam(FormaPagtoCanalParamTO formaPagtoCanalParamTO) throws DAOException {
		logger.debug("formaPagtoCanalParamTO: " + formaPagtoCanalParamTO);
		
		FormaPagtoCanalParamTOBuilder formaPagtoCanalParamTOBuilder = new FormaPagtoCanalParamTOBuilder();
		FormaPagtoCanalParamTO formaPagtoCanalParamResultTO = null;
		try {
			formaPagtoCanalParamResultTO = formaPagtoCanalParamTOBuilder.createFormaPagtoCanalParamTO(em.merge(formaPagtoCanalParamTOBuilder.createFormaPagtoCanalParam(formaPagtoCanalParamTO)));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [createUpdateFormaPagtoCanalParam]", e);
		}
		
		return formaPagtoCanalParamResultTO;
	}
	
}
