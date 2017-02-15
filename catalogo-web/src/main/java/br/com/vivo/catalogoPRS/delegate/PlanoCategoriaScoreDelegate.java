package br.com.vivo.catalogoPRS.delegate;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.PlanoCategoriaScore;
import com.indracompany.catalogo.ejb.planocategoriascore.PlanoCategoriaScoreBeanLocal;
import com.indracompany.catalogo.to.PlanoCategoriaScoreTO;

public class PlanoCategoriaScoreDelegate {

	private static Logger logger = Logger.getLogger(PlanoCategoriaScore.class);	
	
	public void removePlanoCategoriaScore(PlanoCategoriaScoreTO planoCategoriaScoreTO){
		
		try {
			PlanoCategoriaScoreBeanLocal planoCategoriaScoreBeanLocal = (PlanoCategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlanoCategoriaScoreBeanLocal.JNDI_NAME);
			planoCategoriaScoreBeanLocal.removeByCategoriaScore(planoCategoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [removeByCategoriaScore]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
	}
}
