package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MeioPagamentoTO;

/**
 * @author Luiz Pereira
 *
 */
public interface MeioPagamentoDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Meio Pagamento.
	 */
	public List<MeioPagamentoTO> findAll() throws DAOException;
}
