package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FuncionalidadeTO;

/**
 * @author Luiz Pereira
 *
 */
public interface FuncionalidadeDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Funcionalidade da base.
	 */
	public List<FuncionalidadeTO> findAll() throws DAOException;
}
