package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;
import com.indracompany.catalogo.to.TecnologiaTO;

/**
 * @author Luiz Pereira
 *
 */
public interface TecnologiaDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Tecnologia
	 */
	public List<TecnologiaTO> findAll() throws DAOException;
	
	public List<TecnologiaTO> findAllTecnologiaServico(String inFixa) throws DAOException;
	
	public void searchTecnologia(PesquisaIdNomeTO pesquisaTO) throws DAOException;
	
	public Tecnologia findById(Integer idTecnologia) throws DAOException;
	
}