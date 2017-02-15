package com.indracompany.catalogo.ejb.cor;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CorTO;

/**
 * @author 
 * 
 * Interface respons�vel por manter contrato localmente 
 * das funcionalidades de Cor com o EJB.  
 */
@Local
public interface CorBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/CorBean";
	
	/**
	 * @param corTO
	 * @return
	 * 
	 * M�todo respons�vel por trazer todas as cores da base.
	 * @throws DAOException 
	 */
	public List<CorTO> findAll() throws BusinessException;
	
	/**
	 * @param corTO
	 * 
	 * M�todo respons�vel por criar ou editar uma cor.
	 * @throws DAOException 
	 */
	public void createUpdateCor(CorTO corTO) throws BusinessException;
	
	
	/**
	 * @param corTO
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em buscar somente uma cor apartir do código da mesma.
	 */
	public CorTO findById(CorTO corTO) throws BusinessException;
}


