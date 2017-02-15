package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FormaPagtoCanalAtndParamTO;

/**
 * @author Luiz Pereira
 *
 */
public interface FormaPagtoCanalAtndParamDAO {
	

	/**
	 * @param formaPagtoCanalAtndParamTO
	 * @throws DAOException
	 */
	public void createUpdateFormaPagtoCanalParam(FormaPagtoCanalAtndParamTO formaPagtoCanalAtndParamTO) throws DAOException;
	
}
