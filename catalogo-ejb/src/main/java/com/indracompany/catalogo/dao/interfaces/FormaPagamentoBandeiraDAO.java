package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FormaPagamentoBandeiraTO;

/**
 * @author Luiz Pereira
 *
 */
public interface FormaPagamentoBandeiraDAO {
	
	/**
	 * @param formaPagamentoBandeiraTO
	 * @throws DAOException
	 */
	public void createUpdateFormaPagamentoBandeira(FormaPagamentoBandeiraTO formaPagamentoBandeiraTO) throws DAOException;
	
	/**
	 * @param formaPagamentoBandeiraTO
	 * @throws DAOException
	 */
	public void removeFormaPagamentoBandeira(FormaPagamentoBandeiraTO formaPagamentoBandeiraTO) throws DAOException;
}
