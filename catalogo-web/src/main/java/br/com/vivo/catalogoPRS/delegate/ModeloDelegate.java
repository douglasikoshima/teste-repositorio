package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.modelo.ModeloBeanLocal;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.PesquisaGrupoProdutoTO;

public class ModeloDelegate {

	private static Logger logger = Logger.getLogger(ModeloDelegate.class);

	public List<GrupoProdutoTO> searchModelo(GrupoProdutoTO grupoProdutoTO) {
		List<GrupoProdutoTO> result = null;
		try {
			ModeloBeanLocal modeloBeanLocal = (ModeloBeanLocal) ServiceLocator.getInstance().getEJBLocal(ModeloBeanLocal.JNDI_NAME);
			result = modeloBeanLocal.searchModelo(grupoProdutoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchModelo]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return result;
	}
	
	public void searchModelo(PesquisaGrupoProdutoTO pesquisaTO) throws BusinessException {
		try {
			ModeloBeanLocal modeloBeanLocal = (ModeloBeanLocal) ServiceLocator.getInstance().getEJBLocal(ModeloBeanLocal.JNDI_NAME);
			modeloBeanLocal.searchModelo(pesquisaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchModelo]", e);
		} catch (BusinessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public void save(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		try {
			ModeloBeanLocal modeloBeanLocal = (ModeloBeanLocal) ServiceLocator.getInstance().getEJBLocal(ModeloBeanLocal.JNDI_NAME);
			modeloBeanLocal.saveModelo(grupoProdutoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [save]",e);
		} catch (BusinessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public void update(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		try {
			ModeloBeanLocal modeloBeanLocal = (ModeloBeanLocal) ServiceLocator.getInstance().getEJBLocal(ModeloBeanLocal.JNDI_NAME);
			modeloBeanLocal.updateModelo(grupoProdutoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [update]",e);
		} catch (BusinessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public void remove(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		try {
			ModeloBeanLocal modeloBeanLocal = (ModeloBeanLocal) ServiceLocator.getInstance().getEJBLocal(ModeloBeanLocal.JNDI_NAME);
			modeloBeanLocal.removeModelo(grupoProdutoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeModelo]",e);
		} catch (BusinessException e){
			logger.error(e);
			throw e;
		}
	}
	
}