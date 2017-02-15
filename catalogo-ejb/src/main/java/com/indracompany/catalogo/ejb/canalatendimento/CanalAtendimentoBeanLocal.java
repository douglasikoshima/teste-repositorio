package com.indracompany.catalogo.ejb.canalatendimento;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.to.CanalAtendimentoTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Canal Atendimento com o EJB.  
 */
@Local
public interface CanalAtendimentoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/CanalAtendimentoBean";
	
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os Canal Atendimento
	 */
	public List<CanalAtendimentoTO> findAll() throws BusinessException;
	
	public CanalAtendimento findById(Integer canal) throws BusinessException;
}
