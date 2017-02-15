package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FormaPagtoCanalParamTO;

/**
 * @author Luiz Pereira
 *
 */
public interface FormaPagtoCanalParamDAO {
	

	/**
	 * @param formaPagtoCanalParamTO
	 * @throws DAOException
	 */
	public FormaPagtoCanalParamTO createUpdateFormaPagtoCanalParam(FormaPagtoCanalParamTO formaPagtoCanalParamTO) throws DAOException;
	
}
