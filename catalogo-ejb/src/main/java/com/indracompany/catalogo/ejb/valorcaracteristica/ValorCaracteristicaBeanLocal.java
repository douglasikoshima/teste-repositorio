package com.indracompany.catalogo.ejb.valorcaracteristica;

import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.ValorCaracteristicaTO;

public interface ValorCaracteristicaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ValorCaracteristicaBean";
	
	/**
	 * @return
	 * @throws BusinessException
	 */
	public List<ValorCaracteristicaTO> listarValorCaracteristica() throws BusinessException;
	
	/**
	 * @param valorCaracteristicaTO
	 * @throws BusinessException
	 */
	public void createUpdateValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException;
	
	/**
	 * @param valorCaracteristicaTO
	 * @return
	 * @throws BusinessException
	 */
	public List<ValorCaracteristicaTO> findByCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException;	
	
	/**
	 * @param valorCaracteristicaTO
	 * @throws BusinessException
	 */
	public void deleteValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException ;
	
	/**
	 * @param valorCaracteristicaTO
	 * @return
	 * @throws BusinessException
	 */
	public ValorCaracteristicaTO find(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException;
	
	public void saveValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException;
	
}