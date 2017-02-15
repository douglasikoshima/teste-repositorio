package com.indracompany.catalogo.ejb.contratomatrizofertacritica;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.ContratoMatrizOfertaCriticaTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de ContratoMatrizOfertaCritica com o EJB.  
 */
@Local
public interface ContratoMatrizOfertaCriticaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ContratoMatrizOfertaCriticaBean";
	
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em buscar todas as criticas
	 */
	public List<ContratoMatrizOfertaCriticaTO> findAllCriticas() throws BusinessException;
	
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em exportar todas as criticas.
	 */
	public List<String> exportContratoMatrizOfertaCritica() throws BusinessException;
	
}
