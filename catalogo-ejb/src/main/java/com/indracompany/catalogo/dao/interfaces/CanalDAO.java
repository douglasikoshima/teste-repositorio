package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalTO;

/**
 * @author Luiz Pereira
 *
 */
public interface CanalDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Canal
	 */
	public List<CanalTO> findAll() throws DAOException;
}
