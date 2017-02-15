package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.ValorCaracteristica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;

public interface ValorCaracteristicaDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os Valores Caracteristica da base dados.
	 */
	public List<ValorCaracteristicaTO> findAll() throws DAOException;
	
	/**
	 * @param valorCaracteristicaTO
	 * @throws DAOException
	 * 
	 * Método responsável em criar/editar um Valor Caracteristica na base de dados.
	 */
	public void createUpdateValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException;
	
	
	/**
	 * @param valorCaracteristicaTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter um Valor Caracteristica a partir de sua Primary Key
	 */
	public List<ValorCaracteristicaTO> findByCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException ;
	
	
	/**
	 * @param valorCaracteristicaTO
	 * @throws DAOException
	 * 
	 * Método responsável em deletar um Valor Caracteristica da base de dados a partir de sua Primary Key.
	 */
	public void deleteValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException;
	
	
	/**
	 * @param valorCaracteristicaTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em buscar Valor Caracteristica por um valor. 
	 */
	public ValorCaracteristicaTO findByValor(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException;
	
	
	/**
	 * @param valorCaracteristicaTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter um Valor Caracteristica a partir do seu ID.
	 */
	public ValorCaracteristicaTO find(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException;
	
	public ValorCaracteristica findById(Integer idValorCaracteristica) throws DAOException;
	
	public ValorCaracteristica findByNome(Integer idCaracteristica, String valor) throws DAOException;
	
	public void save(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException;
	
	public void save(ValorCaracteristica valorCaracteristica) throws DAOException;
	
}