package br.com.vivo.catalogoPRS.delegate;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.detalheservicofixa.DetalheServicoFixaBeanLocal;
import com.indracompany.catalogo.to.DetalheServicoFixaTO;

public class DetalheServicoFixaDelegate {

	private static Logger logger = Logger.getLogger(DetalheServicoFixaDelegate.class);
	
	public DetalheServicoFixaTO findDetalheServicoFixaById(DetalheServicoFixaTO detalheServicoFixaTO){
		DetalheServicoFixaTO result = new DetalheServicoFixaTO();
		try {
			DetalheServicoFixaBeanLocal detalheServicoFixaBeanLocal = (DetalheServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(DetalheServicoFixaBeanLocal.JNDI_NAME);
			result = detalheServicoFixaBeanLocal.findDetalheServicoFixaById(detalheServicoFixaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findDetalheServicoFixaById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return result;
	}

	public void updateDetalheServicoFixaTO(DetalheServicoFixaTO detalheServicoFixaTO) throws BusinessException {
		try {
			DetalheServicoFixaBeanLocal detalheServicoFixaBeanLocal = (DetalheServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(DetalheServicoFixaBeanLocal.JNDI_NAME);
			detalheServicoFixaBeanLocal.updateDetalheServicoFixaTO(detalheServicoFixaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [updateDetalheServicoFixaTO]", e);
		}
	}
	
}
