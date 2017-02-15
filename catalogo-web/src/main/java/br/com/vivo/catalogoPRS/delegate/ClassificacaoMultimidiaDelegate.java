package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.classificacaomultimidia.ClassificacaoMultimidiaBeanLocal;
import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;


/**
 * @author 
 * 
 * Classe Responsável por delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class ClassificacaoMultimidiaDelegate {
	
	private static Logger logger = Logger.getLogger(ClassificacaoMultimidiaDelegate.class);
	
	/**
	 * @return
	 */
	public List<ClassificacaoMultimidiaTO> findAll() {
		
		List<ClassificacaoMultimidiaTO> classificacaoMultimidiaList = null;
		
		try {
			ClassificacaoMultimidiaBeanLocal corBeanLocal = (ClassificacaoMultimidiaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ClassificacaoMultimidiaBeanLocal.JNDI_NAME);
			classificacaoMultimidiaList = corBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return classificacaoMultimidiaList;
	}
	
}