package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.Fabricante;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FabricanteTO;

/**
 * @author Luiz Pereira
 *
 */
public interface FabricanteDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Fabricante
	 */
	public List<FabricanteTO> findAll() throws DAOException;
	
	/**
	 * @param fabricanteTO
	 * @return
	 * @throws DAOException
	 */
	public List<FabricanteTO> search(FabricanteTO fabricanteTO) throws DAOException;
	
	public Fabricante findById(Integer idFabricante) throws DAOException;
	
}