package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FormaPagamentoTO;

/**
 * @author Luiz Pereira
 *
 */
public interface FormaPagamentoDAO {

	/**
	 * @param formaPagamentoTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em buscar todas as Formas de Pagamento
	 */
	public List<FormaPagamentoTO> findAll() throws DAOException;
	
	/**
	 * @param formaPagamentoTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em procurar na base Formas de Pagamento a partir dos Parametros informados
	 */
	public List<FormaPagamentoTO> searchFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws DAOException;
	
	/**
	 * @param formaPagamentoTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter uma Forma de Pagamento a apartir de seu ID.
	 */
	public FormaPagamentoTO findById(FormaPagamentoTO formaPagamentoTO) throws DAOException;
	
	/**
	 * @param formaPagamentoTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em persistir os dados de Forma Pagamento na base.
	 */
	public FormaPagamentoTO createUpdateFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws DAOException;

	/**
	 * @param formaPagamentoTO
	 * @throws DAOException
	 */
	public void removeFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws DAOException;
	
	/**
	 * @param formaPagamentoTO
	 * @throws DAOException
	 */
	public void removeFormaPagtoCanalParam(FormaPagamentoTO formaPagamentoTO) throws DAOException;
	
	/**
	 * @param formaPagamentoTO
	 * @throws DAOException
	 */
	public void removeFormaPagtoCanalAtndParam(FormaPagamentoTO formaPagamentoTO) throws DAOException;
}
