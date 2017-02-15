package com.indracompany.catalogo.ejb.produto;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.AssociacaoAcaoProdutoTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.ProdutoTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Tipo Produto com o EJB.  
 */
@Local
public interface ProdutoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ProdutoBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em buscar os Produtos a partir dos parametros informados.
	 */
	public List<ProdutoTO> search(ProdutoTO produtoTO) throws BusinessException;
	
	/**
	 * @param produtoTO
	 * @param idAcao
	 * @return
	 * @throws BusinessException
	 */
	public List<AssociacaoAcaoProdutoTO> searchSemAssociacaoAcao(ProdutoTO produtoTO, Integer idAcao) throws BusinessException;
	
	/**
	 * @param idAcao
	 * @return
	 * @throws BusinessException
	 */
	public List<AssociacaoAcaoProdutoTO> searchComAssociacaoAcao(Integer idAcao) throws BusinessException;
	
	/**
	 * 
	 * @param pp
	 * @return
	 */
	public List<ParametrizacaoProdutosTO> pesquisarParametrizacaoProdutos(ParametrizacaoProdutosTO pp) throws BusinessException;
	
	public ParametrizacaoProdutosTO consultar(ProdutoTO produto) throws BusinessException;
	
	public boolean atualizarProduto(ParametrizacaoProdutosTO pp) throws BusinessException;
	
	public List<MultimidiaTO> consultarMultimidida(ParametrizacaoProdutosTO pp) throws BusinessException;
}
