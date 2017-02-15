package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.dao.TipoProdutoTOBuilder;
import com.indracompany.catalogo.datalayer.TipoProduto;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FabricanteTO;
import com.indracompany.catalogo.to.TipoProdutoTO;

/**
 * @author Luiz Pereira
 *
 */
public interface TipoProdutoDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Fabricante
	 */
	public List<TipoProdutoTO> findAll() throws DAOException;
	
	public List<TipoProdutoTO> findAll(TipoProdutoTOBuilder tipoProdutoTOBuilder) throws DAOException;
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os registros de Fabricante
	 */
	public List<TipoProdutoTO> findAllButSimCards() throws DAOException;
	
	/**
	 * @param tipoProdutoTO
	 * @return
	 * @throws DAOException
	 */
	public TipoProdutoTO findById(TipoProdutoTO tipoProdutoTO) throws DAOException;
	
	public TipoProduto findById(Integer idTipoProduto) throws DAOException;
	
	public List<FabricanteTO> findByIdTipoProduto(Integer idTipoProduto) throws DAOException;
	
}