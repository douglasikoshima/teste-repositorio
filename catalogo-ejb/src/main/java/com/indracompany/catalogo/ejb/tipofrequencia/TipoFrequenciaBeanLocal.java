package com.indracompany.catalogo.ejb.tipofrequencia;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.TipoFrequenciaTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente das funcionalidades de
 * Frequencia com o EJB.
 */
@Local
public interface TipoFrequenciaBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/TipoFrequenciaBean";

	/**
	 * @param tipoFrequenciaTO
	 * @return
	 * 
	 * Método responsável em obter todas os Tipos de Frequencia
	 * @throws BusinessException
	 */
	public List<TipoFrequenciaTO> findAll() throws BusinessException;

	/**
	 * @param tipoFrequenciaTO
	 * 
	 * Método responsável em criar ou editar um Tipo Frequencia.
	 * @throws BusinessException
	 */
	public void createUpdateTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws BusinessException;

	/**
	 * @param tipoFrequenciaTO
	 * @return
	 * 
	 * Método responsável em retornar somente um Tipo Frequencia.
	 */
	public TipoFrequenciaTO findById(TipoFrequenciaTO tipoFrequenciaTO) throws BusinessException;

	/**
	 * @param tipoFrequenciaTO
	 * @throws BusinessException
	 * 
	 * Método responsável em remover um Tipo Frequencia a partir de um ID
	 */
	public void removeTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws BusinessException;
	
}
