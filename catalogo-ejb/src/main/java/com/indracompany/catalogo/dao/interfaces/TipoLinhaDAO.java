package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoLinhaTO;

/**
 * @author Luiz Pereira
 *
 */
public interface TipoLinhaDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Tipo Linha
	 */
	public List<TipoLinhaTO> findAll() throws DAOException;
}
