package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.DescontoCondPagtoTO;

/**
 * @author Luiz Pereira
 *
 */
public interface DescontoCondPagtoDAO {
	
	/**
	 * @param descontoCondPagtoTO
	 * @throws DAOException
	 */
	public void createUpdateDescontoCondPagto(DescontoCondPagtoTO descontoCondPagtoTO) throws DAOException ;
	

	/**
	 * @param idFormaPagamento
	 * @throws DAOException
	 */
	public void removeDescontoCondPagtoByFormaPagamento(Integer idFormaPagamento) throws DAOException;
}
