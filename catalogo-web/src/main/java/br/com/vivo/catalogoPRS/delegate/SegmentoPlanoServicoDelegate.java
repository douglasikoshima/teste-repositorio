package br.com.vivo.catalogoPRS.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.ejb.segmentoplanoservico.SegmentoPlanoServicoBean;
import com.indracompany.catalogo.ejb.segmentoplanoservico.SegmentoPlanoServicoBeanLocal;
import com.indracompany.catalogo.to.PlanoSegmentoTO;
import com.indracompany.catalogo.to.PlanoTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoSegmentoTO;

public class SegmentoPlanoServicoDelegate {
	private static Logger logger = Logger.getLogger(SolicitacaoComercial.class);
	
	public List<PlanoTO> searchPlano(PlanoTO planoTO){
		List<PlanoTO> result = new ArrayList<PlanoTO>();
		
		try {
			SegmentoPlanoServicoBeanLocal segmentoPlanoServicoBeanLocal = (SegmentoPlanoServicoBeanLocal) ServiceLocator.getInstance().getEJBLocal(SegmentoPlanoServicoBean.JNDI_NAME);
			result = segmentoPlanoServicoBeanLocal.searchPlano(planoTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchPlano]",e);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public Map<String, List> loadCombos(){
		Map<String, List> result = new HashMap<String, List>();
		
		try {
			SegmentoPlanoServicoBeanLocal segmentoPlanoServicoBeanLocal = (SegmentoPlanoServicoBeanLocal) ServiceLocator.getInstance().getEJBLocal(SegmentoPlanoServicoBean.JNDI_NAME);
			result = segmentoPlanoServicoBeanLocal.loadCombos();
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [loadCombos]",e);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public void savePlanoSegmento(PlanoSegmentoTO planoSegmentoTO){
		try {
			SegmentoPlanoServicoBeanLocal segmentoPlanoServicoBeanLocal = (SegmentoPlanoServicoBeanLocal) ServiceLocator.getInstance().getEJBLocal(SegmentoPlanoServicoBean.JNDI_NAME);
			segmentoPlanoServicoBeanLocal.savePlanoSegmento(planoSegmentoTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [savePlanoSegmento]",e);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void disassociatePlanoSegmento(PlanoSegmentoTO planoSegmentoTO){
		try {
			SegmentoPlanoServicoBeanLocal segmentoPlanoServicoBeanLocal = (SegmentoPlanoServicoBeanLocal) ServiceLocator.getInstance().getEJBLocal(SegmentoPlanoServicoBean.JNDI_NAME);
			segmentoPlanoServicoBeanLocal.disassociatePlanoSegmento(planoSegmentoTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [disassociatePlanoSegmento]",e);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
	}
	
	public List<ServicoFixaTO> searchServicoFixa(ServicoFixaTO servicoFixaTO){
		List<ServicoFixaTO> result = new ArrayList<ServicoFixaTO>();
		try {
			SegmentoPlanoServicoBeanLocal segmentoPlanoServicoBeanLocal = (SegmentoPlanoServicoBeanLocal) ServiceLocator.getInstance().getEJBLocal(SegmentoPlanoServicoBean.JNDI_NAME);
			result = segmentoPlanoServicoBeanLocal.searchServicoFixa(servicoFixaTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [disassociatePlanoSegmento]",e);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public void saveServicoSegmento(ServicoSegmentoTO servicoSegmentoTO){
		try {
			SegmentoPlanoServicoBeanLocal segmentoPlanoServicoBeanLocal = (SegmentoPlanoServicoBeanLocal) ServiceLocator.getInstance().getEJBLocal(SegmentoPlanoServicoBean.JNDI_NAME);
			segmentoPlanoServicoBeanLocal.saveServicoSegmento(servicoSegmentoTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [saveServicoSegmento]",e);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void disassociateServicoSegmento(ServicoSegmentoTO servicoSegmentoTO){
		try {
			SegmentoPlanoServicoBeanLocal segmentoPlanoServicoBeanLocal = (SegmentoPlanoServicoBeanLocal) ServiceLocator.getInstance().getEJBLocal(SegmentoPlanoServicoBean.JNDI_NAME);
			segmentoPlanoServicoBeanLocal.disassociateServicoSegmento(servicoSegmentoTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [disassociateServicoSegmento]",e);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
	}
}
