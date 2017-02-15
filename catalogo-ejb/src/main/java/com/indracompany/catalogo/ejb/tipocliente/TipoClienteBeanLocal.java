package com.indracompany.catalogo.ejb.tipocliente;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.TipoCliente;
import com.indracompany.catalogo.to.TipoClienteTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Tipo Cliente com o EJB.  
 */
@Local
public interface TipoClienteBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/TipoClienteBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todas as Plataformas
	 */
	public List<TipoClienteTO> findAll() throws BusinessException;
	
	public TipoCliente findById(Integer idTipoCliente)  throws BusinessException;
}
