package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.tipolinha.TipoLinhaBeanLocal;
import com.indracompany.catalogo.to.TipoLinhaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class TipoLinhaDelegate {
	
	private static Logger logger = Logger.getLogger(TipoLinhaDelegate.class);
	
	/**
	 * @return
	 */
	public List<TipoLinhaTO> findAll() {
		
		List<TipoLinhaTO> tipoLinhaTOList = null;
		
		try {
			TipoLinhaBeanLocal tipoLinhaBeanLocal = (TipoLinhaBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoLinhaBeanLocal.JNDI_NAME);
			tipoLinhaTOList = tipoLinhaBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return tipoLinhaTOList;
	}
}
