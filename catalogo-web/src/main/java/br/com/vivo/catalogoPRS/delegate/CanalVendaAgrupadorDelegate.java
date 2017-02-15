package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.canalvendaagrupador.CanalVendaAgrupadorBeanLocal;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.EpsTO;

public class CanalVendaAgrupadorDelegate {
	private static Logger logger = Logger.getLogger(CanalVendaAgrupadorDelegate.class);

	public List<EpsTO> getEpsList() {
		List<EpsTO> epsTOList = null;
		
		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			epsTOList = canalVendaAgrupadorBeanLocal.getEpsList();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [getEpsList]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return epsTOList;
	}

	public List<CanalVendaTO> pesquisar(CanalVendaTO canalVendaTO) {
		List<CanalVendaTO> canalVendaTOList = null;
		
		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			canalVendaTOList = canalVendaAgrupadorBeanLocal.pesquisar(canalVendaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisar(CanalVendaTO)]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return canalVendaTOList;
	}
	
	public CanalVendaTO createUpdate(CanalVendaTO canalVendaTO) throws BusinessException{
		
		CanalVendaTO to = null;
		
		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			to = canalVendaAgrupadorBeanLocal.merge(canalVendaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdate]", e);
		}
		
		return to;
	}
	
	public void remover(CanalVendaTO canalVendaTO) throws BusinessException{

		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			canalVendaAgrupadorBeanLocal.remover(canalVendaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [remover(CanalVendaTO)]", e);
		}
	}
	
	public void alternarInDisponivel(CanalVendaTO canalVendaTO){

		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			canalVendaAgrupadorBeanLocal.alternarInDisponivel(canalVendaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [alternarInDisponivel]",e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	public CanalVendaTO pesquisarPorId(CanalVendaTO canalVendaTO){

		CanalVendaTO retorno = null;
		
		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			retorno = canalVendaAgrupadorBeanLocal.pesquisarPorId(canalVendaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarPorId]",e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return retorno;
	}

	public void associarAgrupador(List<Long> canalVendaIdList, Integer idEps){
		
		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			canalVendaAgrupadorBeanLocal.associarAgrupador(canalVendaIdList, idEps);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [associarAgrupador]",e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}

	public void desassociarAgrupador(List<Long> canalVendaIdList){
		
		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			canalVendaAgrupadorBeanLocal.desassociarAgrupador(canalVendaIdList);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [desassociarAgrupador]",e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	public List<EpsTO> pesquisar(EpsTO epsTO) {
		List<EpsTO> epsTOList = null;
		
		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			epsTOList = canalVendaAgrupadorBeanLocal.pesquisar(epsTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisar(EpsTO)]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return epsTOList;
	}
	
	public void remover(EpsTO epsTO) throws BusinessException {
		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			canalVendaAgrupadorBeanLocal.remover(epsTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [remover(EpsTO)]", e);
		}
	}
	
	public void merge(EpsTO epsTO) throws BusinessException{
		try {
			CanalVendaAgrupadorBeanLocal canalVendaAgrupadorBeanLocal = (CanalVendaAgrupadorBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalVendaAgrupadorBeanLocal.JNDI_NAME);
			canalVendaAgrupadorBeanLocal.merge(epsTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [merge(EpsTO)]", e);
		}
	}
}
