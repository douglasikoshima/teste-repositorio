package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.meiopagamento.MeioPagamentoBeanLocal;
import com.indracompany.catalogo.to.MeioPagamentoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class MeioPagamentoDelegate {
	
	private static Logger logger = Logger.getLogger(MeioPagamentoDelegate.class);
	
	/**
	 * @return
	 */
	public List<MeioPagamentoTO> findAll() {
		logger.debug("findAll");
		
		List<MeioPagamentoTO> meioPagamentoTOList = null;
		
		try {
			MeioPagamentoBeanLocal meioPagamentoBeanLocal = (MeioPagamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(MeioPagamentoBeanLocal.JNDI_NAME);
			meioPagamentoTOList = meioPagamentoBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return meioPagamentoTOList;
	}
}
