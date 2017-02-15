package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.planoservicoufrestricao.PlanoServicoUfRestricaoBeanLocal;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.UfTO;

public class PlanoServicoUfRestricaoDelegate {
	
	private Logger logger = Logger.getLogger(PlanoServicoUfRestricaoDelegate.class);
	
	public List<PlanoServicoUfRestricaoTO> searchPlano(PlanoServicoUfRestricaoTO to) throws BusinessException {
		
		logger.debug(">> searchPlano()");
		
		List<PlanoServicoUfRestricaoTO> list;
		
		try {
			PlanoServicoUfRestricaoBeanLocal planoServicoUfRestricaoBeanLocal = (PlanoServicoUfRestricaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlanoServicoUfRestricaoBeanLocal.JNDI_NAME);
			list = planoServicoUfRestricaoBeanLocal.searchPlano(to);
		} catch (ServiceLocatorException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços. Erro ao realizar o lookup de [searchPlano]", e);
		}
		
		logger.debug("<< searchPlano()");
		
		return list;
	}
	
	public List<PlanoServicoUfRestricaoTO> searchServico(PlanoServicoUfRestricaoTO to) throws BusinessException {
		
		logger.debug(">> searchServico()");
		
		List<PlanoServicoUfRestricaoTO> list;
		
		try {
			PlanoServicoUfRestricaoBeanLocal planoServicoUfRestricaoBeanLocal = (PlanoServicoUfRestricaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlanoServicoUfRestricaoBeanLocal.JNDI_NAME);
			list = planoServicoUfRestricaoBeanLocal.searchServico(to);
		} catch (ServiceLocatorException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços. Erro ao realizar o lookup de [searchServico]", e);
		}
		
		logger.debug("<< searchServico()");
		
		return list;
	}
	
	public List<UfTO> findAllUf() throws BusinessException {
		
		logger.debug(">> findAllUf()");
		
		List<UfTO> list;
		
		try {
			PlanoServicoUfRestricaoBeanLocal planoServicoUfRestricaoBeanLocal = (PlanoServicoUfRestricaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlanoServicoUfRestricaoBeanLocal.JNDI_NAME);
			list = planoServicoUfRestricaoBeanLocal.findAllUf();
		} catch (ServiceLocatorException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços. Erro ao realizar o lookup de [findAllUf]", e);
		}
		
		logger.debug("<< findAllUf()");
		
		return list;
		
	}
	
	public void configurarRestricaoPlano(List<PlanoServicoUfRestricaoTO> toList) throws BusinessException {
		logger.debug(">> configurarRestricaoPlano()");
		
		try {
			PlanoServicoUfRestricaoBeanLocal planoServicoUfRestricaoBeanLocal = (PlanoServicoUfRestricaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlanoServicoUfRestricaoBeanLocal.JNDI_NAME);
			planoServicoUfRestricaoBeanLocal.configurarRestricaoPlano(toList);
		} catch (ServiceLocatorException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços. Erro ao realizar o lookup de [configurarRestricaoPlano]", e);
		}
		
		logger.debug("<< configurarRestricaoPlano()");
	}
	
	public void configurarRestricaoServico(List<PlanoServicoUfRestricaoTO> toList) throws BusinessException {
		logger.debug(">> configurarRestricaoServico()");
		
		try {
			PlanoServicoUfRestricaoBeanLocal planoServicoUfRestricaoBeanLocal = (PlanoServicoUfRestricaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlanoServicoUfRestricaoBeanLocal.JNDI_NAME);
			planoServicoUfRestricaoBeanLocal.configurarRestricaoServico(toList);
		} catch (ServiceLocatorException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços. Erro ao realizar o lookup de [configurarRestricaoServico]", e);
		}
		
		logger.debug("<< configurarRestricaoServico()");
	}

}
