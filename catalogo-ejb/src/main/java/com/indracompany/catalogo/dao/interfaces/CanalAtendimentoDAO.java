package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalAtendimentoTO;

/**
 * @author Luiz Pereira
 *
 */
public interface CanalAtendimentoDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os Canais de Atendimento da base.
	 */
	public List<CanalAtendimentoTO> findAll() throws DAOException;
	
	public CanalAtendimento findById(Integer idCanal) throws DAOException;
}
