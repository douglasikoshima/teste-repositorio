package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.Cor;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CorTO;

public interface CorDAO {

	/**
	 * @param corTO
	 * @return void
	 * 
	 * Método responsável por obter todas as cores disponíveis na base.
	 */	
	public List<CorTO> findAll() throws DAOException;
	
	/**
	 * @param corTO
	 * @throws DAOException
	 * 
	 * Método responsável em criar/editar uma Cor na base de dados.
	 */
	public void createUpdateCor(CorTO corTO) throws DAOException;
	
	/**
	 * @param corTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável obter uma cor a partir de sua Primary Key.
	 */
	public CorTO findById(CorTO corTO) throws DAOException;
	
	public Cor findById(Integer idCor) throws DAOException;
	
}