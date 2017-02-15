package com.indracompany.catalogo.ejb.bandeira;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.BandeiraTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Bandeira com o EJB.  
 */
@Local
public interface BandeiraBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/BandeiraBean";
	
	/**
	 * @param bandeiraTO
	 * @return
	 * 
	 * Método responsável em pesquisar uma Bandeira.
	 * @throws BusinessException 
	 */
	public List<BandeiraTO> searchBandeira(BandeiraTO bandeiraTO) throws BusinessException;
	
	/**
	 * @param bandeiraTO
	 * 
	 * Método responsável em criar ou editar uma Bandeira.
	 * @throws BusinessException 
	 */
	public void createUpdateBandeira(BandeiraTO bandeiraTO) throws BusinessException;
	
	/**
	 * @param bandeiraTO
	 * @return
	 * 
	 * Método responsável em retornar somente uma Bandeira.
	 */
	public BandeiraTO findById(BandeiraTO bandeiraTO) throws BusinessException;
	
	/**
	 * @param bandeiraTO
	 * @throws BusinessException
	 * 
	 * Método responsável em remover uma Bandeira a partir de um ID
	 */
	public void removeBandeira(BandeiraTO bandeiraTO) throws BusinessException;
	
	/**
	 * @return
	 * @throws BusinessException
	 */
	public List<BandeiraTO> findAll() throws BusinessException;
}
