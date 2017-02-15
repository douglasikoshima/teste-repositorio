package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.TipoCliente;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoClienteTO;

/**
 * @author Luiz Pereira
 *
 */
public interface TipoClienteDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Tipo Cliente
	 */
	public List<TipoClienteTO> findAll() throws DAOException;
	
	public TipoCliente findById(Integer idTipoCliente) throws DAOException;
	
}
