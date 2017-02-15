package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AcaoProdutoTO;

public interface AcaoProdutoDAO {
	
	/**
	 * @param acaoProdutoTO
	 * @throws DAOException
	 */
	public void createUpdateAcaoProduto(AcaoProdutoTO acaoProdutoTO) throws DAOException;
	
	/**
	 * @param acaoProdutoTO
	 * @throws DAOException
	 */
	public void removeAcaoProduto(AcaoProdutoTO acaoProdutoTO) throws DAOException;

	/**
	 * @param acaoProdutoTO
	 * @throws DAOException
	 */
	public void removeAssociationAcaoProduto(AcaoProdutoTO acaoProdutoTO) throws DAOException;
	
}
