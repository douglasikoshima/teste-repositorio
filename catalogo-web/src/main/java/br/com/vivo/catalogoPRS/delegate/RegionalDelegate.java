package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.regional.RegionalBeanLocal;
import com.indracompany.catalogo.to.RegionalTO;

public class RegionalDelegate {

	private static Logger log = Logger.getLogger(RegionalDelegate.class);
	
	
	public List<RegionalTO> findAll(){
		
		try {
			log.info("begin");
			return ((RegionalBeanLocal)ServiceLocator.getInstance().getEJBLocal(RegionalBeanLocal.JNDI_NAME)).findAll();
		} catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [search]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        }
	}
}
