package com.indracompany.catalogo.ejb.meiopagamento;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.MeioPagamentoTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Meio Pagamento com o EJB.  
 */
@Local
public interface MeioPagamentoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/MeioPagamentoBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os Meios de Pagamento
	 */
	public List<MeioPagamentoTO> findAll() throws BusinessException;
	
}
