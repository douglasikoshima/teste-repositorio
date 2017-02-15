package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoFrequenciaTO;

public interface TipoFrequenciaDAO {
	
	/**
	 * @return
	 * 
	 * Método responsável em buscar todos os registros de Tipo Frequencia da base.
	 */
	public List<TipoFrequenciaTO> findAll() throws DAOException;
		
	/**
	 * @param tipoFrequenciaTO
	 * 
	 * Método responsável em criar/editar um Tipo Frequencia na base.
	 */
	public void createUpdateTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws DAOException;
 	
	/**
	 * @param tipoFrequenciaTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar um Tipo Frequencia na base.
	 */
	public TipoFrequenciaTO findById(TipoFrequenciaTO tipoFrequenciaTO) throws DAOException;
	
	/**
	 * @param tipoFrequenciaTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover um Tipo frequencia da base a partir de sua Primary Key. 
	 */
	public void removeTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws DAOException;
	
	/**
	 * @param tipoFrequenciaTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em verificar se existe algum Tipo de Frequencia com o mesmo nome.
	 */
	public Boolean existTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws DAOException;
}
