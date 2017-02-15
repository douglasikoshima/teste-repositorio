package br.com.vivo.catalogoPRS.delegate;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.ServicoCategoriaScore;
import com.indracompany.catalogo.ejb.servicocategoriascore.ServicoCategoriaScoreBeanLocal;
import com.indracompany.catalogo.to.ServicoCategoriaScoreTO;

public class ServicoCategoriaScoreDelegate {

	private static Logger logger = Logger.getLogger(ServicoCategoriaScore.class);	
	
	public void removeServicoCategoriaScore(ServicoCategoriaScoreTO servicoCategoriaScoreTO){
		
		try {
			ServicoCategoriaScoreBeanLocal servicoCategoriaScoreBeanLocal = (ServicoCategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoCategoriaScoreBeanLocal.JNDI_NAME);
			servicoCategoriaScoreBeanLocal.removeByCategoriaScore(servicoCategoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [removeByCategoriaScore]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
	}	
	
}
