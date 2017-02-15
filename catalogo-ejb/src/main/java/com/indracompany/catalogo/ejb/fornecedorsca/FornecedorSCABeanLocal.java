package com.indracompany.catalogo.ejb.fornecedorsca;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.FornecedorSCATO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Fornecedor SCA com o EJB.  
 */
@Local
public interface FornecedorSCABeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/FornecedorSCABean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os fornecedores da SCA
	 */
	public List<FornecedorSCATO> findAll() throws BusinessException;
	
}
