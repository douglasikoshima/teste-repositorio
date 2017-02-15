package com.indracompany.catalogo.ejb.tipolinha;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.TipoLinhaTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Tipo Linha com o EJB.  
 */
@Local
public interface TipoLinhaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/TipoLinhaBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os tipos de linhas. 
	 */
	public List<TipoLinhaTO> findAll() throws BusinessException;
	
}
