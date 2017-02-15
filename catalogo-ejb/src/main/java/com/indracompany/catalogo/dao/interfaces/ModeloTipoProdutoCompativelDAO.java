package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ModeloTipoProdutoCompativelTO;

public interface ModeloTipoProdutoCompativelDAO {
	
	/**
	 * @param acaoTO
	 * 
	 * Método responsável em criar/editar um Modelo Tipo Produto Compativel na base.
	 */
	public ModeloTipoProdutoCompativelTO createUpdateModeloTipoProdutoCompativel(ModeloTipoProdutoCompativelTO modeloTipoProdutoCompativelTO) throws DAOException;
	
	/**
	 * @param idGrupoProduto
	 * @throws DAOException
	 * 
	 * Método responsável em deletar todos os Modelo Tipo Produto Compativel de um determinado Grupo Produto.
	 */
	public void removeModeloTipoProdutoCompativel(Integer idGrupoProduto) throws DAOException;
}
