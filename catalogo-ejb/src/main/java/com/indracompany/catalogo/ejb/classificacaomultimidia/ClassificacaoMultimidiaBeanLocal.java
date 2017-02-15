package com.indracompany.catalogo.ejb.classificacaomultimidia;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;

/**
 * @author 
 * 
 * Interface responsável por manter contrato localmente 
 * das funcionalidades de ClassificacaoMultimidia com o EJB.  
 */
@Local
public interface ClassificacaoMultimidiaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ClassificacaoMultimidiaBean";
	
	/**
	 * @return
	 * 
	 * Método responsável por trazer todas as classificações de multimídia da base.
	 * @throws DAOException 
	 */
	public List<ClassificacaoMultimidiaTO> findAll() throws BusinessException;
	
}