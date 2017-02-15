package br.com.vivo.catalogoPRS.delegate;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;


import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.ServicoIntCanal;
import com.indracompany.catalogo.ejb.servicointcanal.ServicoIntCanalBeanLocal;
import com.indracompany.catalogo.to.ServicoIntCanalTO;

public class ServicoIntCanalDelegate {
	
	private static Logger logger = Logger.getLogger(ServicoIntCanalDelegate.class);
	
	
	public List<ServicoIntCanal> findById (Integer idServicoIntCanal) {
		
		List<ServicoIntCanal> servIntCanalList = new ArrayList<ServicoIntCanal>();
		
		try {
			ServicoIntCanalBeanLocal servicoIntCanalBeanLocal = (ServicoIntCanalBeanLocal)ServiceLocator.getInstance().getEJBLocal(ServicoIntCanalBeanLocal.JNDI_NAME);
			servIntCanalList = servicoIntCanalBeanLocal.findById(idServicoIntCanal);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return servIntCanalList;
	}
	
	public void createUpdateServicoIntCanal(ServicoIntCanalTO servicoIntCanalTO) {
		try {
			ServicoIntCanalBeanLocal servicoIntCanalBeanLocal = (ServicoIntCanalBeanLocal)ServiceLocator.getInstance().getEJBLocal(ServicoIntCanalBeanLocal.JNDI_NAME);
			servicoIntCanalBeanLocal.createUpdateServicoIntCanal(servicoIntCanalTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateServicoIntCanal]", e);		
		} catch (BusinessException e) {
			logger.error(e);
		}
		
	}
	
	public void removeSrvIntCanalById (Integer idServicoInteratividade) {
		
		try {
			ServicoIntCanalBeanLocal servicoIntCanalBeanLocal = (ServicoIntCanalBeanLocal)ServiceLocator.getInstance().getEJBLocal(ServicoIntCanalBeanLocal.JNDI_NAME);
			servicoIntCanalBeanLocal.removeSrvIntCanalById(idServicoInteratividade);			
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeSrvIntCanalById]", e);		
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
}
