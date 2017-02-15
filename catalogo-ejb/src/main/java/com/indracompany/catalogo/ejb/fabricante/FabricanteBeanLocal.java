package com.indracompany.catalogo.ejb.fabricante;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.FabricanteTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Fabricante com o EJB.  
 */
@Local
public interface FabricanteBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/FabricanteBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os Fabricantes
	 */
	public List<FabricanteTO> findAll() throws BusinessException;
	
	/**
	 * @param fabricanteTO
	 * @return
	 * @throws BusinessException
	 */
	public List<FabricanteTO> search(FabricanteTO fabricanteTO) throws BusinessException;
	
}
