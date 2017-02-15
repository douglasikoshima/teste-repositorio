package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.TipoCliente;
import com.indracompany.catalogo.ejb.tipocliente.TipoClienteBeanLocal;
import com.indracompany.catalogo.to.TipoClienteTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class TipoClienteDelegate {
	
	private static Logger logger = Logger.getLogger(TipoClienteDelegate.class);
	
	/**
	 * @return
	 */
	public List<TipoClienteTO> findAll() {
		
		List<TipoClienteTO> tipoClienteTOList = null;
		
		try {
			TipoClienteBeanLocal tipoClienteBeanLocal = (TipoClienteBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoClienteBeanLocal.JNDI_NAME);
			tipoClienteTOList = tipoClienteBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return tipoClienteTOList;
	}
	
	public TipoCliente findById(Integer idTipoCliente) {
		
		TipoCliente tpCliente = new TipoCliente();
		
		
		try {
			TipoClienteBeanLocal tipoClienteBeanLocal = (TipoClienteBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoClienteBeanLocal.JNDI_NAME);
			tpCliente = tipoClienteBeanLocal.findById(idTipoCliente);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return tpCliente;
	}

}
