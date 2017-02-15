package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.StatusArquivoImportacaoTO;

/**
 * @author equipe Catalogo
 *
 */
public interface MatrizOfertaStatusImportacaoDAO {
	
	/**
	 * Método responsável em retornar todos os status possiveis preenchidos.
	 */	
	public List<StatusArquivoImportacaoTO> findAll() throws DAOException;
}
