package com.indracompany.catalogo.ejb.frequencia;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.FrequenciaTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente das funcionalidades de
 * Frequencia com o EJB.
 */
@Local
public interface FrequenciaBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/FrequenciaBean";

	/**
	 * @param frequenciaTO
	 * @return
	 * 
	 * Método responsável em obter todas as Frequencias
	 * @throws BusinessException
	 */
	public List<FrequenciaTO> findAll() throws BusinessException;

	/**
	 * @param frequenciaTO
	 * 
	 * Método responsável em criar ou editar uma Frequecia.
	 * @throws BusinessException
	 */
	public void createUpdateFrequencia(FrequenciaTO frequenciaTO) throws BusinessException;

	/**
	 * @param frequenciaTO
	 * @return
	 * 
	 * Método responsável em retornar somente uma Frequecia.
	 */
	public FrequenciaTO findById(FrequenciaTO frequenciaTO) throws BusinessException;

	/**
	 * @param frequenciaTO
	 * @throws BusinessException
	 * 
	 * Método responsável em remover uma Frequecia a partir de um ID
	 */
	public void removeFrequencia(FrequenciaTO frequenciaTO) throws BusinessException;
}
