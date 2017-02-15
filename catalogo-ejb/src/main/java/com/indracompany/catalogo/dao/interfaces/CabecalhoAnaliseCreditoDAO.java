package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.dao.CabecalhoAnaliseCreditoTOBuilder;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO;

public interface CabecalhoAnaliseCreditoDAO {
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 * 
	 * Método responsável em pesquisar na base uma ou mais CabecalhoAnaliseCreditos.
	 */
	public List<CabecalhoAnaliseCreditoTO> searchCabecalhoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException;
		
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * 
	 * Método responsável em criar/editar uma CabecalhoAnaliseCredito na base.
	 */
	public CabecalhoAnaliseCreditoTO createUpdateCabecalhoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException;
 	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma CabecalhoAnaliseCredito na base.
	 */
	public CabecalhoAnaliseCreditoTO findById(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @param cabecalhoAnaliseCreditoTOBuilder
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma CabecalhoAnaliseCredito na base.
	 */
	public CabecalhoAnaliseCreditoTO findById(
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO,
			CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder)
			throws DAOException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover uma CabecalhoAnaliseCredito na base
	 */
	public void removeCabecalhoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException;
	
	
	/**
	 * @return
	 * @throws DAOException
	 */
	public List<CabecalhoAnaliseCreditoTO> findAll() throws DAOException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @throws DAOException
	 * 
	 * Método responsável em criar um novo registro usando outro de uma CabecalhoAnaliseCredito na base.
	 */
	public CabecalhoAnaliseCreditoTO copiarCabecalhoAnaliseCredito(Integer idCabecalhoAnaliseCredito) throws DAOException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 * @throws DAOException
	 */
	public CabecalhoAnaliseCreditoTO findByName(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException;
	
	/**
	 * @return
	 * @throws DAOException
	 */
	public List<CabecalhoAnaliseCreditoTO> findAllNoChild() throws DAOException;
	
	/**
	 * @return
	 * @throws DAOException
	 */
	public CabecalhoAnaliseCreditoTO findByIdNoChild(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException;
	
}
