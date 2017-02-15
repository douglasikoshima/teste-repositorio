package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.ejb.cor.CorBeanLocal;
import com.indracompany.catalogo.to.CorTO;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;


/**
 * @author 
 * 
 * Classe Responsável por delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class CorDelegate {
	
	private static Logger logger = Logger.getLogger(CorDelegate.class);
	
	/**
	 * @param corTO
	 * @return
	 */
	public List<CorTO> findAll() {
		
		List<CorTO> corList = null;
		
		try {
			CorBeanLocal corBeanLocal = (CorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CorBeanLocal.JNDI_NAME);
			corList = corBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return corList;
	}
	
	/**
	 * @param corTO
	 */
	public void createUpdateCor(CorTO corTO) {
		
		try {
			CorBeanLocal corBeanLocal = (CorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CorBeanLocal.JNDI_NAME);
			corBeanLocal.createUpdateCor(corTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateCor]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	public CorTO findById(CorTO corTO) {
		
		CorTO corResultTO = null;
		
		try {
			CorBeanLocal corBeanLocal = (CorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CorBeanLocal.JNDI_NAME);
			corResultTO = corBeanLocal.findById(corTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return corResultTO;
	}
}
