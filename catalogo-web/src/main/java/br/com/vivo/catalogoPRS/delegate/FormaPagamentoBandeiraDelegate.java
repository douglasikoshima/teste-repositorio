package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.formapagamentobandeira.FormaPagamentoBandeiraBeanLocal;
import com.indracompany.catalogo.to.FormaPagamentoBandeiraTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class FormaPagamentoBandeiraDelegate {
	
	private static Logger logger = Logger.getLogger(FormaPagamentoBandeiraDelegate.class);
	
	/**
	 * @return
	 */
	public void createUpdateFormaPagamentoBandeira(List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList) {
		
		try {
			
			FormaPagamentoBandeiraBeanLocal formaPagamentoBandeiraBeanLocal = (FormaPagamentoBandeiraBeanLocal) ServiceLocator.getInstance().getEJBLocal(FormaPagamentoBandeiraBeanLocal.JNDI_NAME);
			formaPagamentoBandeiraBeanLocal.createUpdateFormaPagamentoBandeira(formaPagamentoBandeiraTOList);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateFormaPagamentoBandeira]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
}
