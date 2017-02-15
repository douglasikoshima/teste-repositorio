package br.com.vivo.catalogoPRS.delegate;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.ServicoIntCliente;
import com.indracompany.catalogo.ejb.servicointcliente.ServicoIntClienteBeanLocal;
import com.indracompany.catalogo.to.ServicoIntClienteTO;

public class ServicoIntClienteDelegate {
	
	private static Logger logger = Logger.getLogger(ServicoIntClienteDelegate.class);
	
	
	public List<ServicoIntCliente> findByIdServicoInteratividade(Integer idServicoInteratividade) {
	
		List<ServicoIntCliente> servIntClienteList =  new ArrayList<ServicoIntCliente>();
		
		try {
			ServicoIntClienteBeanLocal servicoIntClienteBeanLocal = (ServicoIntClienteBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoIntClienteBeanLocal.JNDI_NAME);
			servIntClienteList = servicoIntClienteBeanLocal.findByIdServicoInteratividade(idServicoInteratividade);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByIdServicoInteratividade]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return servIntClienteList; 
	}
	
	public void createUpdateServicoIntCliente(ServicoIntClienteTO servicoIntClienteTO) {
		
		try {
			ServicoIntClienteBeanLocal servicoIntClienteBeanLocal = (ServicoIntClienteBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoIntClienteBeanLocal.JNDI_NAME);
			servicoIntClienteBeanLocal.createUpdateServicoIntCliente(servicoIntClienteTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateServicoIntCliente]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	public void removeServIntClienteById (Integer idServicoInteratividade) {
		
		try {
			ServicoIntClienteBeanLocal servicoIntClienteBeanLocal = (ServicoIntClienteBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoIntClienteBeanLocal.JNDI_NAME);
			servicoIntClienteBeanLocal.removeServIntClienteById(idServicoInteratividade);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateServicoIntCliente]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
}
