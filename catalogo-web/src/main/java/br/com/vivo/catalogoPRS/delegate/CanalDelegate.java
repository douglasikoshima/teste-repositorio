package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.canal.CanalBeanLocal;
import com.indracompany.catalogo.to.CanalTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class CanalDelegate {
	
	private static Logger logger = Logger.getLogger(CanalDelegate.class);
	
	/**
	 * @return
	 */
	public List<CanalTO> findAll() {
		
		List<CanalTO> canalTOList = null;
		
		try {
			CanalBeanLocal canalBeanLocal = (CanalBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalBeanLocal.JNDI_NAME);
			canalTOList = canalBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return canalTOList;
	}
}
