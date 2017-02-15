package com.indracompany.catalogo.ejb.condicapagamento;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.CondicaoPagamentoTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Plataforma com o EJB.  
 */
@Local
public interface CondicaoPagamentoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/CondicaoPagamentoBean";
	
	/**
	 * @param condicaoPagamentoTO
	 * @throws BusinessException
	 */
	public void createUpdateCondicaoPagamentoList(List<CondicaoPagamentoTO> condicaoPagamentoTOList) throws BusinessException;
	
}
