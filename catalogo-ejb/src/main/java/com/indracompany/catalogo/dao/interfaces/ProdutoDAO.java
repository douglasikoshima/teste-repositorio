package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AssociacaoAcaoProdutoTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.ProdutoTO;

/**
 * @author Luiz Pereira
 *
 */
public interface ProdutoDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em buscar Produtos conforme parametros.
	 */
	public List<ProdutoTO> search(ProdutoTO produtoTO) throws DAOException;
	
	
	/**
	 * @param produtoTO
	 * @param idAcao
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em buscar Produtos que não estão relacionados a Acao informada.
	 */
	public List<AssociacaoAcaoProdutoTO> searchSemAssociacaoAcao(ProdutoTO produtoTO, Integer idAcao) throws DAOException;

	/**
	 * @param idAcao
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em buscar Produtos que estão relacionados a Acao informada.
	 */
	public List<AssociacaoAcaoProdutoTO> searchComAssociacaoAcao(Integer idAcao) throws DAOException;
	
	/**
	 * 
	 * @param ParametrizacaoProdutosTO pp
	 * @return List<ParametrizacaoProdutosTO>
	 * 
	 * Metodo utilizado na tela Parametrizacao -> Produtos -> Produtos
	 */
	public List<ParametrizacaoProdutosTO> pesquisarParametrizacaoProdutos(ParametrizacaoProdutosTO pp);
	
	public ParametrizacaoProdutosTO consultar(ProdutoTO produto);
	
	public boolean atualizarProduto(ParametrizacaoProdutosTO pp);
}
