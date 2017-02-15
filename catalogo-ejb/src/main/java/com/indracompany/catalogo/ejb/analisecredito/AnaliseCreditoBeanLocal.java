package com.indracompany.catalogo.ejb.analisecredito;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.AnaliseCreditoTO;
import com.indracompany.catalogo.to.EpsTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Analise de Credito com o EJB.  
 */
@Local
public interface AnaliseCreditoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/AnaliseCreditoBean";
	

	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 * 
	 * Método responsável em retornar somente uma Analise de Credito.
	 */
	public AnaliseCreditoTO findById(AnaliseCreditoTO analiseCreditoTO) throws BusinessException;
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os Cabecalhos de Analise de Credito
	 */
	public List<AnaliseCreditoTO> findAll() throws BusinessException;	
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os EPS
	 */	
	public List<EpsTO> listEpsTO() throws BusinessException;
		

}
