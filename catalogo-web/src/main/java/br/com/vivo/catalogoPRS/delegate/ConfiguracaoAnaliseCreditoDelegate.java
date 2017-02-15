package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.analisecredito.configuracao.ConfiguracaoAnaliseCreditoBeanLocal;
import com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsavel em delegar a funcao para um EJB,
 * fazendo lookup do mesmo.
 */
public class ConfiguracaoAnaliseCreditoDelegate {
	
	private static Logger logger = Logger.getLogger(ConfiguracaoAnaliseCreditoDelegate.class);
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 */
	public List<CabecalhoAnaliseCreditoTO> searchAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) {
		
		List<CabecalhoAnaliseCreditoTO> analiseCreditoList = null;
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoList = analiseCreditoBeanLocal.searchAnaliseCredito(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchAnaliseCredito]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return analiseCreditoList;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 */
	public CabecalhoAnaliseCreditoTO createUpdateAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = new CabecalhoAnaliseCreditoTO();
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			cabecalhoAnaliseCreditoResultTO = analiseCreditoBeanLocal.createUpdateAnaliseCredito(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateAnaliseCredito]", e);
		}
		
		return cabecalhoAnaliseCreditoResultTO;
	}

	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 */
	public CabecalhoAnaliseCreditoTO findById(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) {
	
		CabecalhoAnaliseCreditoTO analiseCreditoTOResult = null;
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoTOResult = analiseCreditoBeanLocal.findById(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return analiseCreditoTOResult;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 */
	public void removeAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoBeanLocal.removeAnaliseCredito(cabecalhoAnaliseCreditoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeAnaliseCredito]", e);
		}
	}

	/**
	 * @return
	 */
	public List<CabecalhoAnaliseCreditoTO> findAll() {
		
		List<CabecalhoAnaliseCreditoTO> list = null;
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			list = analiseCreditoBeanLocal.findAll();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return list;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @throws BusinessException
	 */
	public void desativarAtivar(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoBeanLocal.desativarAtivar(cabecalhoAnaliseCreditoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [desativarAtivar]", e);
		}
	}
	
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @param idCategoriaScore
	 */
	public CabecalhoAnaliseCreditoTO createUpdatePlanoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = new CabecalhoAnaliseCreditoTO();
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal configuracaoAnaliseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			cabecalhoAnaliseCreditoResultTO = configuracaoAnaliseCreditoBeanLocal.createUpdatePlanoAnaliseCredito(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdatePlanoAnaliseCredito]", e);
		}
		
		return cabecalhoAnaliseCreditoResultTO;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @param idCategoriaScore
	 */
	public CabecalhoAnaliseCreditoTO createUpdateServicoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = new CabecalhoAnaliseCreditoTO();
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal configuracaoAnaliseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			cabecalhoAnaliseCreditoResultTO = configuracaoAnaliseCreditoBeanLocal.createUpdateServicoAnaliseCredito(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateServicoAnaliseCredito]", e);
		}
		
		return cabecalhoAnaliseCreditoResultTO;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @param idCategoriaScore
	 */
	public CabecalhoAnaliseCreditoTO createUpdateOfertaAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = new CabecalhoAnaliseCreditoTO();
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal configuracaoAnaliseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			cabecalhoAnaliseCreditoResultTO = configuracaoAnaliseCreditoBeanLocal.createUpdateOfertaAnaliseCredito(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateOfertaAnaliseCredito]", e);
		}
		
		return cabecalhoAnaliseCreditoResultTO;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 */
	public CabecalhoAnaliseCreditoTO findByIdWithPlanos(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) {
	
		CabecalhoAnaliseCreditoTO analiseCreditoTOResult = null;
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoTOResult = analiseCreditoBeanLocal.findByIdWithPlanos(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByIdWithPlanos]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return analiseCreditoTOResult;
	}
	
	/**
	 * 
	 * @param cabecalhoAnaliseCreditoTO
	 * @param categorizacaoAnaliseCreditoTO
	 * @return cabecalhoAnaliseCreditoTO
	 */
	public CabecalhoAnaliseCreditoTO findByIdWithPlanos(
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO,
			CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) {
		CabecalhoAnaliseCreditoTO analiseCreditoTOResult = null;
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoTOResult = analiseCreditoBeanLocal.findByIdWithPlanos(cabecalhoAnaliseCreditoTO, categorizacaoAnaliseCreditoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByIdWithPlanos]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return analiseCreditoTOResult;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 */
	public CabecalhoAnaliseCreditoTO findByIdWithServicos(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) {
	
		CabecalhoAnaliseCreditoTO analiseCreditoTOResult = null;
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoTOResult = analiseCreditoBeanLocal.findByIdWithServicos(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByIdWithServicos]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return analiseCreditoTOResult;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 */
	public CabecalhoAnaliseCreditoTO findByIdWithOfertas(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) {
	
		CabecalhoAnaliseCreditoTO analiseCreditoTOResult = null;
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoTOResult = analiseCreditoBeanLocal.findByIdWithOfertas(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByIdWithOfertas]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return analiseCreditoTOResult;
	}
	
	/**
	 * @return
	 */
	public List<CabecalhoAnaliseCreditoTO> findAllNoChild() {
		
		List<CabecalhoAnaliseCreditoTO> list = null;
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal confAnaliseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			list = confAnaliseCreditoBeanLocal.findAllNoChild();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAllNoChild]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return list;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 */
	public CabecalhoAnaliseCreditoTO findByIdNoChild(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) {
	
		CabecalhoAnaliseCreditoTO analiseCreditoTOResult = null;
		
		try {
			ConfiguracaoAnaliseCreditoBeanLocal analiseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoTOResult = analiseCreditoBeanLocal.findByIdNoChild(cabecalhoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByIdNoChild]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return analiseCreditoTOResult;
	}
	
	public void recriaServicoConfiguracaoAnaliseCredito(List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTOList, List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTORemoveList ) {
		try {
			ConfiguracaoAnaliseCreditoBeanLocal configuracaoAnaliseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			configuracaoAnaliseCreditoBeanLocal.recriaServicoConfiguracaoAnaliseCredito(servicoConfiguracaoScoreTOList, servicoConfiguracaoScoreTORemoveList);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [recriaServicoConfiguracaoAnaliseCredito]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}

	public void recriaOfertaServicoConfiguracaoAnaliseCredito(List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTOList, List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTORemoveList) {
		try {
			ConfiguracaoAnaliseCreditoBeanLocal configuracaoAnaliseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			configuracaoAnaliseCreditoBeanLocal.recriaOfertaServicoConfiguracaoAnaliseCredito(ofServicoConfiguracaoScoreTOList, ofServicoConfiguracaoScoreTORemoveList);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [recriaOfertaServicoConfiguracaoAnaliseCredito]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	public List<CanalAtendimentoTO> getCanaisConfiguraveisPorRegional() {
		List<CanalAtendimentoTO> result = null;
		try {
			ConfiguracaoAnaliseCreditoBeanLocal configuracaoAnaliseCreditoBeanLocal = (ConfiguracaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ConfiguracaoAnaliseCreditoBeanLocal.JNDI_NAME);
			result = configuracaoAnaliseCreditoBeanLocal.getCanaisConfiguraveisPorRegional();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [getCanaisConfiguraveisPorRegional]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return result;
	}
}
