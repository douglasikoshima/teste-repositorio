package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;


/**
 * @author Luiz Pereira
 * 
 * Classe Responsável por delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class CategorizacaoAnaliseCreditoDelegate {
	
	private static Logger logger = Logger.getLogger(CategorizacaoAnaliseCreditoDelegate.class);

	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchServico(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) {
		
		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = null;
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoTOList = categorizacaoAnaliseCreditoBeanLocal.searchServico(categorizacaoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchServico]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return categorizacaoAnaliseCreditoTOList;
	}

	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchServicoConfig(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) {
		
		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = null;
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoTOList = categorizacaoAnaliseCreditoBeanLocal.searchServicoConfig(categorizacaoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchServico]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return categorizacaoAnaliseCreditoTOList;
	}
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchPlano(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) {
		
		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = null;
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoTOList = categorizacaoAnaliseCreditoBeanLocal.searchPlano(categorizacaoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchPlano]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return categorizacaoAnaliseCreditoTOList;
	}
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchPlanoConfig(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) {
		
		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = null;
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoTOList = categorizacaoAnaliseCreditoBeanLocal.searchPlanoConfig(categorizacaoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchPlanoConfig]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return categorizacaoAnaliseCreditoTOList;
	}
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServico(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) {
		
		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = null;
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoTOList = categorizacaoAnaliseCreditoBeanLocal.searchOfertaServico(categorizacaoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchOfertaServico]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return categorizacaoAnaliseCreditoTOList;
	}

	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServicoConfig(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) {
		
		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = null;
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoTOList = categorizacaoAnaliseCreditoBeanLocal.searchOfertaServicoConfig(categorizacaoAnaliseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchOfertaServico]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return categorizacaoAnaliseCreditoTOList;
	}
	
	public void createServicoCategoriaScore(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO, String user, List<Integer> ids) {
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoBeanLocal.createServicoCategoriaScore(categorizacaoAnaliseCreditoTO, user, ids);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createServicoCategoriaScore]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 */
	public void createPlanoCategoriaScore(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO, String user, List<Integer> ids) {
		
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoBeanLocal.createPlanoCategoriaScore(categorizacaoAnaliseCreditoTO, user, ids);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createPlanoCategoriaScore]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
	}
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 */
	public void createOfertaServicoCategoriaScore(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO, String user, List<Integer> ids) {
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoBeanLocal.createOfertaServicoCategoriaScore(categorizacaoAnaliseCreditoTO, user, ids);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createOfertaServicoCategoriaScore]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	/**
	 * @param ids
	 * @throws BusinessException 
	 */
	public void desassociarServicoCategoriaScore(List<Integer> ids) throws BusinessException {
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoBeanLocal.desassociarServicoCategoriaScore(ids);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [desassociarServicoCategoriaScore]", e);
		}
	}

	/**
	 * @param ids
	 * @throws BusinessException 
	 */
	public void desassociarPlanoCategoriaScore(List<Integer> ids) throws BusinessException {
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoBeanLocal.desassociarPlanoCategoriaScore(ids);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [desassociarPlanoCategoriaScore]", e);
		}
	}
	

	/**
	 * @param ids
	 * @throws BusinessException 
	 */
	public void desassociarOfertaServicoCategoriaScore(List<Integer> ids) throws BusinessException {
		
		try {
			CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
			categorizacaoAnaliseCreditoBeanLocal.desassociarOfertaServicoCategoriaScore(ids);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [desassociarOfertaServicoCategoriaScore]", e);
		}
	}

    public List<CategorizacaoAnaliseCreditoTO> searchServicoFixa(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) {
        List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = null;
        
        try {
            CategorizacaoAnaliseCreditoBeanLocal categorizacaoAnaliseCreditoBeanLocal = (CategorizacaoAnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategorizacaoAnaliseCreditoBeanLocal.JNDI_NAME);
            categorizacaoAnaliseCreditoTOList = categorizacaoAnaliseCreditoBeanLocal.searchServicoFixa(categorizacaoAnaliseCreditoTO);
            
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [searchServicoFixa]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        
        return categorizacaoAnaliseCreditoTOList;
    }
}
