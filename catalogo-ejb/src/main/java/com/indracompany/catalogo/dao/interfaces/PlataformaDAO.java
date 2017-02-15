package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlataformaTO;

/**
 * @author Luiz Pereira
 *
 */
public interface PlataformaDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Plataforma
	 */
	public List<PlataformaTO> findAll() throws DAOException;
	
	/**
	 * @param idPlataformas
	 * @return
	 * @throws DAOException
	 */
	public List<PlataformaTO> findAllWithExpections(Integer[] idPlataformas) throws DAOException;
	
	public PlataformaTO findByIdPlataforma(Integer idPlataforma) throws DAOException; 

    List<PlataformaTO> findByIdCanalAtendimento(Integer idCanalAtendimento) throws DAOException;
    
    public Plataforma findById(Integer idPlataforma) throws DAOException;
}
