package com.indracompany.catalogo.ejb.canal;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.CanalTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Canal com o EJB.  
 */
@Local
public interface CanalBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/CanalBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os Canais
	 */
	public List<CanalTO> findAll() throws BusinessException;
	
}
