package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.parametro.SrvInteratividadeParamBaseBeanLocal;
import com.indracompany.catalogo.to.SrvInteratividadeParamBaseTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class SrvInteratividadeParamBaseDelegate {
	
	private static Logger logger = Logger.getLogger(SrvInteratividadeParamBaseDelegate.class);
	
	/**
	 * @return
	 */
	public List<SrvInteratividadeParamBaseTO> findAll() {
		
		List<SrvInteratividadeParamBaseTO> parametroTOList = null;
		
		try {
			SrvInteratividadeParamBaseBeanLocal parametroBeanLocal = (SrvInteratividadeParamBaseBeanLocal) ServiceLocator.getInstance().getEJBLocal(SrvInteratividadeParamBaseBeanLocal.JNDI_NAME);
			parametroTOList = parametroBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return parametroTOList;
	}

}
