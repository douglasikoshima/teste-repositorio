package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FrequenciaTO;

public interface FrequenciaDAO {
	
	/**
	 * @param frequenciaTO
	 * @return
	 * 
	 * Método responsável em buscar todos os registros de Frequencia da base.
	 */
	public List<FrequenciaTO> findAll() throws DAOException;
		
	/**
	 * @param frequenciaTO
	 * 
	 * Método responsável em criar/editar uma Frequencia na base.
	 */
	public void createUpdateFrequencia(FrequenciaTO frequenciaTO) throws DAOException;
 	
	/**
	 * @param frequenciaTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma Frequencia na base.
	 */
	public FrequenciaTO findById(FrequenciaTO frequenciaTO) throws DAOException;
	
	
	/**
	 * @param frequenciaTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover uma frequencia da base a partir de sua Primary Key. 
	 */
	public void removeFrequencia(FrequenciaTO frequenciaTO) throws DAOException;
}
