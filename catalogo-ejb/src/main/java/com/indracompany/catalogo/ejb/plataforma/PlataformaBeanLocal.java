package com.indracompany.catalogo.ejb.plataforma;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.to.PlataformaTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Plataforma com o EJB.  
 */
@Local
public interface PlataformaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/PlataformaBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todas as Plataformas
	 */
	public List<PlataformaTO> findAll() throws BusinessException;
	
	/**
	 * @param idPlataformas
	 * @return
	 * @throws BusinessException
	 */
	public List<PlataformaTO> findAllWithExpections(Integer[] idPlataformas) throws BusinessException;

    List<PlataformaTO> findByIdCanalAtendimento(Integer idCanalAtendimento) throws BusinessException;
    
    public Plataforma findById(Integer idPlataforma) throws BusinessException;
     
}
