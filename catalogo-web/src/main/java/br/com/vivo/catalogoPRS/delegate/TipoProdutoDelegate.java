package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.TipoProdutoTOBuilder;
import com.indracompany.catalogo.ejb.tipoproduto.TipoProdutoBeanLocal;
import com.indracompany.catalogo.to.TipoProdutoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class TipoProdutoDelegate {
	
	private static Logger logger = Logger.getLogger(TipoProdutoDelegate.class);
	
	/**
	 * @return
	 */
	public List<TipoProdutoTO> findAll() {
		
		List<TipoProdutoTO> tipoProdutoTOList = null;
		
		try {
			TipoProdutoBeanLocal tipoProdutoBeanLocal = (TipoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoProdutoBeanLocal.JNDI_NAME);
			tipoProdutoTOList = tipoProdutoBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return tipoProdutoTOList;
	}
	
	/**
	 * @return
	 */
	public List<TipoProdutoTO> findAll(TipoProdutoTOBuilder tipoProdutoTOBuilder) {
		
		List<TipoProdutoTO> tipoProdutoTOList = null;
		
		try {
			TipoProdutoBeanLocal tipoProdutoBeanLocal = (TipoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoProdutoBeanLocal.JNDI_NAME);
			tipoProdutoTOList = tipoProdutoBeanLocal.findAll(tipoProdutoTOBuilder);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return tipoProdutoTOList;
	}
	
	/**
	 * @return
	 */
	public List<TipoProdutoTO> findAllButSimCards() {
		
		List<TipoProdutoTO> tipoProdutoTOList = null;
		
		try {
			TipoProdutoBeanLocal tipoProdutoBeanLocal = (TipoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoProdutoBeanLocal.JNDI_NAME);
			tipoProdutoTOList = tipoProdutoBeanLocal.findAllButSimCards();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return tipoProdutoTOList;
	}
	
	public TipoProdutoTO findById(TipoProdutoTO tipoProdutoTO) {
		
		TipoProdutoTO tipoProdutoResultTO = null;
		
		try {
			TipoProdutoBeanLocal tipoProdutoBeanLocal = (TipoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoProdutoBeanLocal.JNDI_NAME);
			tipoProdutoResultTO = tipoProdutoBeanLocal.findById(tipoProdutoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return tipoProdutoResultTO;
	}
}
