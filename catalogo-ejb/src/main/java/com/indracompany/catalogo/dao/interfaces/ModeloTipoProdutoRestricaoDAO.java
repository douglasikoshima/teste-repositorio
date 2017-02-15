package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ModeloTipoProdutoRestricaoTO;

public interface ModeloTipoProdutoRestricaoDAO {
	
	/**
	 * @param acaoTO
	 * 
	 * Método responsável em criar/editar um  Modelo Tipo Produto Restricao na base.
	 */
	public ModeloTipoProdutoRestricaoTO createUpdateModeloTipoProdutoRestricao(ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoTO) throws DAOException;
 
	/**
	 * @param idGrupoProduto
	 * @throws DAOException
	 * 
	 * Método Responsável em deletar todos os Tipo Produto Restricao de um determinado Grupo Produto.
	 */
	public void removeModeloTipoProdutoRestricaoByGrupoProduto(Integer idGrupoProduto) throws DAOException;
}
