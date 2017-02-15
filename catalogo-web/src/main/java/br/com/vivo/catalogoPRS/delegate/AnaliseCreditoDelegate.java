package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.analisecredito.AnaliseCreditoBeanLocal;
import com.indracompany.catalogo.to.AnaliseCreditoTO;
import com.indracompany.catalogo.to.EpsTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class AnaliseCreditoDelegate {
	
	private static Logger logger = Logger.getLogger(AnaliseCreditoDelegate.class);
	

	/**
	 * @param analiseCreditoTO
	 * @return
	 */
	public AnaliseCreditoTO findById(AnaliseCreditoTO analiseCreditoTO) {
	
		AnaliseCreditoTO analiseCreditoTOResult = null;
		
		try {
			AnaliseCreditoBeanLocal analiseCreditoBeanLocal = (AnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AnaliseCreditoBeanLocal.JNDI_NAME);
			analiseCreditoTOResult = analiseCreditoBeanLocal.findById(analiseCreditoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return analiseCreditoTOResult;
	}
	

	/**
	 * @return
	 */
	public List<AnaliseCreditoTO> findAll() {
		
		List<AnaliseCreditoTO> list = null;
		
		try {
			AnaliseCreditoBeanLocal analiseCreditoBeanLocal = (AnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AnaliseCreditoBeanLocal.JNDI_NAME);
			list = analiseCreditoBeanLocal.findAll();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return list;
	}
	
    public List<EpsTO> listEpsTO() {
    	
    	List<EpsTO> list = null;
    	
        try {
        	AnaliseCreditoBeanLocal analiseCreditoBeanLocal = (AnaliseCreditoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AnaliseCreditoBeanLocal.JNDI_NAME);
        	list = analiseCreditoBeanLocal.listEpsTO();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [listEpsTO]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return list;
    }
	
}
