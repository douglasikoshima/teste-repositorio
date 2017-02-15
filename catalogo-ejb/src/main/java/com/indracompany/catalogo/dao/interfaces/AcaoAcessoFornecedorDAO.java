package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AcaoAcessoFornecedorTO;

/**
 * @author Luiz Pereira
 *
 */
public interface AcaoAcessoFornecedorDAO {
	
	/**
	 * @param acaoAcessoFornecedorTO
	 * @throws DAOException
	 */
	public void createUpdateAcaoAcessoFornecedor(AcaoAcessoFornecedorTO acaoAcessoFornecedorTO) throws DAOException;
	
	/**
	 * @param acaoAcessoFornecedorTO
	 * @throws DAOException
	 */
	public void removeAcaoAcessoFornecedor(AcaoAcessoFornecedorTO acaoAcessoFornecedorTO) throws DAOException;
	
	/**
	 * @param acaoAcessoFornecedorTO
	 * @return
	 * @throws DAOException
	 */
	public List<Integer> findFornecedorByAcao(AcaoAcessoFornecedorTO acaoAcessoFornecedorTO) throws DAOException;
	
	/**
	 * @param acaoAcessoFornecedorTO
	 * @return
	 * @throws DAOException
	 */
	public List<Integer> findPerfilByAcao(AcaoAcessoFornecedorTO acaoAcessoFornecedorTO) throws DAOException;
}
