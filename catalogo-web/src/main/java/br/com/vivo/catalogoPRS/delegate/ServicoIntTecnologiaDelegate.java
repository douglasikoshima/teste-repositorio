package br.com.vivo.catalogoPRS.delegate;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.ServicoIntTecnologia;
import com.indracompany.catalogo.ejb.servicointtecnologia.ServicoIntTecnologiaBeanLocal;
import com.indracompany.catalogo.to.ServicoIntTecnologiaTO;

public class ServicoIntTecnologiaDelegate {
	
	private static Logger logger = Logger.getLogger(ServicoIntTecnologiaDelegate.class);
	
	
	public List<ServicoIntTecnologia> findByIdServicoInteratividade(Integer idServicoInteratividade) {
		logger.debug("idServicoInteratividade: " + idServicoInteratividade);
		
		List<ServicoIntTecnologia> servIntTecnoList = new ArrayList<ServicoIntTecnologia>();

		try {
			
			ServicoIntTecnologiaBeanLocal servicoIntTecnologiaBeanLocal = (ServicoIntTecnologiaBeanLocal)ServiceLocator.getInstance().getEJBLocal(ServicoIntTecnologiaBeanLocal.JNDI_NAME);
			servIntTecnoList = servicoIntTecnologiaBeanLocal.findByIdServicoInteratividade(idServicoInteratividade);
						
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByIdServicoInteratividade]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return servIntTecnoList;
	}
	
	public void createUpdateServicoIntTecnologia (ServicoIntTecnologiaTO servicoIntTecnologiaTO) {
		
		try {
			
			ServicoIntTecnologiaBeanLocal servicoIntTecnologiaBeanLocal = (ServicoIntTecnologiaBeanLocal)ServiceLocator.getInstance().getEJBLocal(ServicoIntTecnologiaBeanLocal.JNDI_NAME);
			servicoIntTecnologiaBeanLocal.createUpdateServicoIntTecnologia(servicoIntTecnologiaTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateServicoIntTecnologia]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}		
	}
	
	public void removeServIntTecnologiaById (Integer idServicoInteratividade) {
		logger.debug("idServicoInteratividade: " + idServicoInteratividade);
		
		try {
			
			ServicoIntTecnologiaBeanLocal servicoIntTecnologiaBeanLocal = (ServicoIntTecnologiaBeanLocal)ServiceLocator.getInstance().getEJBLocal(ServicoIntTecnologiaBeanLocal.JNDI_NAME);
			servicoIntTecnologiaBeanLocal.removeServIntTecnologiaById(idServicoInteratividade);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeServIntTecnologiaById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}	
		
	}
	
}
