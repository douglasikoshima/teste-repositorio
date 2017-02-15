package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.webservice.impl;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.dao.DesbloqueioDAO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.delegate.DesbloqueioGsmBD;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.to.DesbloqueioGsmTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.webservice.DesbloqueioGsmWS;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.webservice.to.DesbloqueioGsmWSTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.webservice.to.ResultadoDesbloqueioWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class DesbloqueioGsmWSImpl implements DesbloqueioGsmWS {

	public DesbloqueioGsmWSImpl() {
		// Constructor
	}

	public ResultadoDesbloqueioWSTO desbloquearAparelho(RequestInfoWSTO requestInfo, DesbloqueioGsmWSTO desbloqueio) {

	    ResultadoDesbloqueioWSTO to = new ResultadoDesbloqueioWSTO();

		try {
		    DesbloqueioGsmTO toPesquisa = new DesbloqueioGsmTO();

		    toPesquisa.setNrIMEI(desbloqueio.getNrIMEI());
		    toPesquisa.setNrIP(desbloqueio.getNrIP());
		    toPesquisa.setIdTipoRelacionamento(desbloqueio.getIdTipoRelacionamento());
		    toPesquisa.setIdGrupoAbertura(desbloqueio.getIdGrupoAbertura());
		    toPesquisa.setCdAreaRegistro(desbloqueio.getCdAreaRegistro());
		    toPesquisa.setNrLinha(desbloqueio.getNrLinha());
		    toPesquisa.setIdPessoa(desbloqueio.getIdPessoa());
		    
		    

		    
		    DesbloqueioGsmBD bd = new DesbloqueioGsmBD();
		    DesbloqueioGsmTO toRetorno = bd.desbloquearAparelho(requestInfo, toPesquisa);

		    if("OK".equals(toRetorno.getStatusConsulta())){
	            to.setStatus(RespostaWSTO.OK);
		    }else{
		        to.setStatus(RespostaWSTO.NAO_OK);
		    }
			to.setNrSimLock(toRetorno.getNrSimLock());
			to.setStatusConsulta(toRetorno.getStatusConsulta());
			to.setErrorCode(toRetorno.getErrorCode());
			to.setErrorDescription(toRetorno.getErrorDescription());
            to.setReason(toRetorno.getErrorDescription());
			
		} catch (BusinessDelegateException e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			String msgRetorno = e.getMessage();
			if (e.getMessage().indexOf("|") > 0) {
	            to.setErrorCode("1");
				to.setErrorDescription(msgRetorno.split("\\|")[0]);
				to.setReason(msgRetorno.split("\\|")[1]);
			} else {
                to.setErrorCode("1");
				to.setErrorDescription(msgRetorno);
				to.setReason(e.getMessage());
			}

		} catch (BusinessException e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			String msgRetorno = e.getMessage();
			if (e.getMessage().indexOf("|") > 0) {
                to.setErrorCode("1");
				to.setErrorDescription(msgRetorno.split("\\|")[0]);
				to.setReason(msgRetorno.split("\\|")[1]);
			} else {
                to.setErrorCode("1");
                to.setErrorDescription(msgRetorno);
                to.setReason(e.getMessage());
			}
		}
		return to;
	}
}