package com.indracompany.catalogo.ejb.parametro;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.SrvInteratividadeParamBaseTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Parametro com o EJB.  
 */
@Local
public interface SrvInteratividadeParamBaseBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/SrvInteratividadeParamBaseBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todas os Parametros
	 */
	public List<SrvInteratividadeParamBaseTO> findAll() throws BusinessException;
	
}
