package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.fornecedorsca.FornecedorSCABeanLocal;
import com.indracompany.catalogo.to.FornecedorSCATO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class FornecedorSCADelegate {
	
	private static Logger logger = Logger.getLogger(FornecedorSCADelegate.class);
	
	/**
	 * @return
	 */
	public List<FornecedorSCATO> findAll() {
		
		List<FornecedorSCATO> list = null;
		
		try {
			FornecedorSCABeanLocal fornecedorSCABeanLocal = (FornecedorSCABeanLocal) ServiceLocator.getInstance().getEJBLocal(FornecedorSCABeanLocal.JNDI_NAME);
			list = fornecedorSCABeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return list;
	}
}
