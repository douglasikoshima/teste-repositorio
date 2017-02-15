package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ContratoMatrizOfertaCriticaTO;

/**
 * @author Luiz Pereira
 *
 */
public interface ContratoMatrizOfertaCriticaDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todas as criticas.
	 */
	public List<ContratoMatrizOfertaCriticaTO> findAllCriticas() throws DAOException;
	
	
	/**
	 * @throws DAOException
	 * 
	 * Método responsável em remover todas as criticas.
	 */
	public void removeAllCriticas() throws DAOException;
	
}
