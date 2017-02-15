package com.indracompany.catalogo.ejb.funcionalidade;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.FuncionalidadeTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de "Funcionalidade" com o EJB.  
 */
@Local
public interface FuncionalidadeBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/FuncionalidadeBean";
	
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todas as Funcionalidades
	 */
	public List<FuncionalidadeTO> findAll() throws BusinessException;
}
