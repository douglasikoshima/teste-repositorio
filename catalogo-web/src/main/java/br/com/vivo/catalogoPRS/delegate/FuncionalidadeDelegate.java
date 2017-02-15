package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.funcionalidade.FuncionalidadeBeanLocal;
import com.indracompany.catalogo.to.FuncionalidadeTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class FuncionalidadeDelegate {
	
	private static Logger logger = Logger.getLogger(FuncionalidadeDelegate.class);
	
	/**
	 * @return
	 */
	public List<FuncionalidadeTO> findAll() {
		
		List<FuncionalidadeTO> funcionalidadeTOList = null;
		
		try {
			FuncionalidadeBeanLocal funcionalidadeBeanLocal = (FuncionalidadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(FuncionalidadeBeanLocal.JNDI_NAME);
			funcionalidadeTOList = funcionalidadeBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return funcionalidadeTOList;
	}
}
