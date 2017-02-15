package com.indracompany.catalogo.ejb.formapagamento;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.FormaPagamentoTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Forma Pagamento com o EJB.  
 */
@Local
public interface FormaPagamentoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/FormaPagamentoBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todas as Formas de Pagamento
	 */
	public List<FormaPagamentoTO> findAll() throws BusinessException;
	
	/**
	 * @param formaPagamentoTO
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em buscar Formas de Pagamento na base a partir dos Parametros informados.
	 */
	public List<FormaPagamentoTO> searchFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws BusinessException;
	
	
	/**
	 * @param formaPagamentoTO
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em obter uma Forma de Pagamento apartir do seu ID.
	 */
	public FormaPagamentoTO findById(FormaPagamentoTO formaPagamentoTO) throws BusinessException;
	
	
	/**
	 * @param formaPagamentoTO
	 * @throws BusinessException
	 * 
	 * Método responsável em criar ou atualizar uma Forma de Pagamento.
	 */
	public FormaPagamentoTO createUpdateFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws BusinessException;
	
	/**
	 * @param formaPagamentoTO
	 * @throws BusinessException
	 */
	public void removeFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws BusinessException;
	
	/**
	 * @param formaPagamentoTO
	 * @throws BusinessException
	 */
	public void createUpdateFormaPagtoCanalParam(FormaPagamentoTO formaPagamentoTO) throws BusinessException;
	
}
