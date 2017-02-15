package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

public interface AnaliseCreditoDAO {
	
	/**
	 * @param analiseCreditoTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma AnaliseCredito na base.
	 */
	public AnaliseCreditoTO findById(AnaliseCreditoTO analiseCreditoTO) throws DAOException;
	
	/**
	 * @return
	 * @throws DAOException
	 */
	public List<AnaliseCreditoTO> findAll() throws DAOException;
	
}
