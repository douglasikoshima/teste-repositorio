package br.com.vivo.catalogoPRS.delegate;

import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

import com.indracompany.catalogo.ejb.financiamento.FinanciamentoBeanLocal;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;

public class FinanciamentoDelegate {
	private static Logger logger = Logger.getLogger(FinanciamentoDelegate.class);

	public List<CondicaoPagamentoTO> search(CondicaoPagamentoTO condicaoPagamentoTO) {
		List<CondicaoPagamentoTO> financiamentoTOList = null;
		try {
			FinanciamentoBeanLocal financiamentoBeanLocal = ((FinanciamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FinanciamentoBeanLocal.JNDI_NAME));
			financiamentoTOList = financiamentoBeanLocal.search(condicaoPagamentoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [search]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return financiamentoTOList;
	}
	
	public CondicaoPagamentoTO insertUpdate(CondicaoPagamentoTO condicaoPagamentoTO) throws CatalogoPRSException {
		try {
			condicaoPagamentoTO = ((FinanciamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FinanciamentoBeanLocal.JNDI_NAME)).insertUpdate(updateDataToSave(condicaoPagamentoTO));
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [insertUpdate]", e);
		} catch (BusinessException e) {
			logger.error(e);
			condicaoPagamentoTO = null;
		}
		return condicaoPagamentoTO;
	}

	private CondicaoPagamentoTO updateDataToSave(CondicaoPagamentoTO condicaoPagamentoTO) {
		CondicaoPagamentoTO condicaoPagamentoTOSave;
		if (condicaoPagamentoTO.getIdCondicaoPagamento() != null && condicaoPagamentoTO.getIdCondicaoPagamento() > 0) {
			condicaoPagamentoTOSave = this.findById(condicaoPagamentoTO.getIdCondicaoPagamento());
			condicaoPagamentoTOSave.setNmUsuarioAlteracao(condicaoPagamentoTO.getUsuarioForm());
			condicaoPagamentoTOSave.setDtUltimaAlteracao(new Date());
			condicaoPagamentoTOSave.setNmCondicaoPagamento(condicaoPagamentoTO.getNmCondicaoPagamento());
			condicaoPagamentoTOSave.setQtParcelas(condicaoPagamentoTO.getQtParcelas());
			condicaoPagamentoTOSave.setTxJuroParcela(condicaoPagamentoTO.getTxJuroParcela());
		} else {
			condicaoPagamentoTOSave = new CondicaoPagamentoTO();
			condicaoPagamentoTOSave.setNmUsuarioCriacao(condicaoPagamentoTO.getUsuarioForm());
			condicaoPagamentoTOSave.setDtCriacao(new Date());
			condicaoPagamentoTOSave.setInCriacaoCatalogo(true);
			condicaoPagamentoTOSave.setInDisponivel(true);
			condicaoPagamentoTOSave.setInFixa(true);
		}
		condicaoPagamentoTOSave.setNmCondicaoPagamento(condicaoPagamentoTO.getNmCondicaoPagamento());
		condicaoPagamentoTOSave.setQtParcelas(condicaoPagamentoTO.getQtParcelas());
		condicaoPagamentoTOSave.setTxJuroParcela(condicaoPagamentoTO.getTxJuroParcela());
		return condicaoPagamentoTOSave;
	}
	
	public void remove(Integer id) throws CatalogoPRSException {
		try {
			FinanciamentoBeanLocal financiamentoBeanLocal = ((FinanciamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FinanciamentoBeanLocal.JNDI_NAME));
			if(!financiamentoBeanLocal.findById(id).getInCriacaoCatalogo()) {
				throw new CatalogoPRSException("Este financiamento pertence ao cadastro do legado");
			}
			financiamentoBeanLocal.remove(id);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [remove]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	public CondicaoPagamentoTO findById(Integer id) {
		CondicaoPagamentoTO condicaoPagamentoTO = null;
		try {
			condicaoPagamentoTO = ((FinanciamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FinanciamentoBeanLocal.JNDI_NAME)).findById(id);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}		
		return condicaoPagamentoTO;
	}

	public void changeStatus(Integer idCondicaoPagamento) throws CatalogoPRSException {
        CondicaoPagamentoTO condicaoPagamentoTO = this.findById(idCondicaoPagamento);
		condicaoPagamentoTO.setInDisponivel(!condicaoPagamentoTO.getInDisponivel());
		condicaoPagamentoTO.setDtUltimaAlteracao(new Date());
		try {
			condicaoPagamentoTO = ((FinanciamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FinanciamentoBeanLocal.JNDI_NAME)).insertUpdate(condicaoPagamentoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [changeStatus]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}		
	}
}
