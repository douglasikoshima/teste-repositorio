package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.SrvInteratividadeParamBaseTO;

/**
 * @author Luiz Pereira
 *
 */
public interface SrvInteratividadeParamBaseDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Parametro
	 */
	public List<SrvInteratividadeParamBaseTO> findAll() throws DAOException;
	
}
