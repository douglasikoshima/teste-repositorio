package br.com.vivo.catalogoPRS.delegate;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.OfertaServicoCategoriaScore;
import com.indracompany.catalogo.ejb.ofertaservicocategoriascore.OfertaServicoCategoriaScoreBeanLocal;
import com.indracompany.catalogo.to.OfertaServicoCategoriaScoreTO;

public class OfertaServicoCategoriaScoreDelegate {

	private static Logger logger = Logger.getLogger(OfertaServicoCategoriaScore.class);	

	public void removeOfertaServicoCategoriaScore(OfertaServicoCategoriaScoreTO planoCategoriaScoreTO){

		try {
			OfertaServicoCategoriaScoreBeanLocal planoCategoriaScoreBeanLocal = (OfertaServicoCategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertaServicoCategoriaScoreBeanLocal.JNDI_NAME);
			planoCategoriaScoreBeanLocal.removeByCategoriaScore(planoCategoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [removeByCategoriaScore]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
	}
}
