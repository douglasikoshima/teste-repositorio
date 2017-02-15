package br.com.vivo.catalogoPRS.delegate;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.ServicoIntPlataforma;
import com.indracompany.catalogo.ejb.servicointplataforma.ServicoIntPlataformaBeanLocal;
import com.indracompany.catalogo.to.ServicoIntPlataformaTO;

public class ServicoIntPlataformaDelegate {

	private static Logger logger = Logger.getLogger(ServicoIntPlataformaDelegate.class);
	
	public List<ServicoIntPlataforma> findByIdServicoInteratividade (Integer idServicoInteratividade ) {
		
		List<ServicoIntPlataforma> servIntPlataformList = new ArrayList<ServicoIntPlataforma>();
		
		try {
			ServicoIntPlataformaBeanLocal servicoIntPlataformaBeanLocal = (ServicoIntPlataformaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoIntPlataformaBeanLocal.JNDI_NAME);
			servIntPlataformList = servicoIntPlataformaBeanLocal.findByIdServicoInteratividade(idServicoInteratividade);			
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByIdServicoInteratividade]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
				
		return servIntPlataformList;
	}
	
	public void createUpdateServicoIntPlataforma (ServicoIntPlataformaTO servicoIntPlataformaTO) {
		
		try {
			
			ServicoIntPlataformaBeanLocal servicoIntPlataformaBeanLocal = (ServicoIntPlataformaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoIntPlataformaBeanLocal.JNDI_NAME);
			servicoIntPlataformaBeanLocal.createUpdateServicoIntPlataforma(servicoIntPlataformaTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateServicoIntPlataforma]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}	
	}
	
	public void removeSrvIntPlataformaById (Integer idServicoInteratividade) {
		
		try {
			ServicoIntPlataformaBeanLocal servicoIntPlataformaBeanLocal = (ServicoIntPlataformaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoIntPlataformaBeanLocal.JNDI_NAME);
			servicoIntPlataformaBeanLocal.removeSrvIntPlataformaById(idServicoInteratividade);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeSrvIntPlataformaById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
}
