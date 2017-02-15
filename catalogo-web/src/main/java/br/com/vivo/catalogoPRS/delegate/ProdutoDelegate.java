package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.produto.ProdutoBeanLocal;
import com.indracompany.catalogo.to.AssociacaoAcaoProdutoTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.ProdutoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class ProdutoDelegate {
	
	private static Logger logger = Logger.getLogger(ProdutoDelegate.class);
	
	/**
	 * @return
	 */
	public List<ProdutoTO> search(ProdutoTO produtoTO) {
		
		List<ProdutoTO> produtoTOList = null;
		
		try {
			ProdutoBeanLocal produtoBeanLocal = (ProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ProdutoBeanLocal.JNDI_NAME);
			produtoTOList = produtoBeanLocal.search(produtoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [search]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return produtoTOList;
	}
	
	/**
	 * @return
	 */
	public List<AssociacaoAcaoProdutoTO> searchComAssociacaoAcao(Integer idAcao) {
		
		List<AssociacaoAcaoProdutoTO> produtoTOList = null;
		
		try {
			ProdutoBeanLocal produtoBeanLocal = (ProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ProdutoBeanLocal.JNDI_NAME);
			produtoTOList = produtoBeanLocal.searchComAssociacaoAcao(idAcao);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchComAssociacaoAcao]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return produtoTOList;
	}
	
	/**
	 * @return
	 */
	public List<AssociacaoAcaoProdutoTO> searchSemAssociacaoAcao(ProdutoTO produtoTO, Integer idAcao) {
		
		List<AssociacaoAcaoProdutoTO> produtoTOList = null;
		
		try {
			ProdutoBeanLocal produtoBeanLocal = (ProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ProdutoBeanLocal.JNDI_NAME);
			produtoTOList = produtoBeanLocal.searchSemAssociacaoAcao(produtoTO, idAcao);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchSemAssociacaoAcao]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return produtoTOList;
	}
	
	public List<ParametrizacaoProdutosTO> pesquisarParametrizacaoProdutos(ParametrizacaoProdutosTO pp){
		
		logger.debug("inicio - pesquisarParametrizacaoProdutos ");
		List<ParametrizacaoProdutosTO> produtos = null;
		try {

			produtos = ((ProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ProdutoBeanLocal.JNDI_NAME)).pesquisarParametrizacaoProdutos(pp);
		}catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchSemAssociacaoAcao]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return produtos;
	}
	
	public ParametrizacaoProdutosTO consultar(ProdutoTO produto){
		
		logger.debug("inicio - pesquisarParametrizacaoProdutos ");
		ParametrizacaoProdutosTO pp = null;
		try {

			pp = ((ProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ProdutoBeanLocal.JNDI_NAME)).consultar(produto);
		}catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchSemAssociacaoAcao]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return pp;
	}

	public boolean atualizarProduto(ParametrizacaoProdutosTO pp) throws BusinessException {
		
		try {
			return ((ProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ProdutoBeanLocal.JNDI_NAME)).atualizarProduto(pp);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<MultimidiaTO> consultarMultimidida(ParametrizacaoProdutosTO pp){
		
		try {
			return ((ProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ProdutoBeanLocal.JNDI_NAME)).consultarMultimidida(pp);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
