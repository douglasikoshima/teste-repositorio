package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;

/**
 * @author Luiz Pereira
 *
 */
public interface CondicaoPagamentoDAO {
	
	/**
	 * @param condicaoPagamentoTO
	 * @throws DAOException
	 */
	public CondicaoPagamentoTO createUpdateCondicaoPagamento(CondicaoPagamentoTO condicaoPagamentoTO) throws DAOException;
	
	/**
	 * @param condicaoPagamentoTO
	 * @throws DAOException
	 */
	public void removeCondicaoPagamento(CondicaoPagamentoTO condicaoPagamentoTO) throws DAOException;
	
	/**
	 * @param condicaoPagamentoTO
	 * @throws DAOException
	 */
	public void removeTipoProdutoCondPagto(CondicaoPagamentoTO condicaoPagamentoTO) throws DAOException;

	public List<CondicaoPagamentoTO> search(CondicaoPagamentoTO condicaoPagamentoTO) throws DAOException;

	public void remove(Integer id) throws DAOException;

	public CondicaoPagamentoTO findById(Integer id) throws DAOException;

    public CondicaoPagamentoTO findByName(String nmCondicaoPagamento) throws DAOException;
}
