package br.com.indrasistemas.vivoservices.autenticacao.application;

import javax.ejb.SessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.foundation.xml.XMLParserException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.DadosAlteraSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.DadosValidaSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoAlterarSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoValidarSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.ValidarSenhaGateway;
import br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.AlterarSenhaGateway;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.exception.ParametrosInvalidosException;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class AutenticacaoAS extends BaseApplicationService {

	private static final Log logger = LogFactory.getLog(AutenticacaoAS.class);
	
	public AutenticacaoAS(SessionContext sessionContext) {
		super(sessionContext);
	}	
		
	public ResultadoAlterarSenhaTO alteraSenha (RequestInfoTO requestInfo, DadosAlteraSenhaTO dados) throws ApplicationServiceException, BusinessException {

		if (logger.isInfoEnabled()) {
			logger.info("alteraSenha - Método iniciado.");
		}
		
		ResultadoAlterarSenhaTO r = new ResultadoAlterarSenhaTO();
		AlterarSenhaGateway gateway = new AlterarSenhaGateway();
		
		try {
			r = (ResultadoAlterarSenhaTO) gateway.call(requestInfo, dados);

			r.setStatus(RespostaWSTO.OK);
			r.setCdErro("00");
			
		} catch (TuxedoException ex) {			
			r.setCdErro(ex.getStatusCode());
			
			if (ex.getStatusCode().equals("07E0003")) {
				r.setCdErro("03");
			} else if (ex.getStatusCode().equals("07E0002")) {
				r.setCdErro("02");
			} else if (ex.getStatusCode().equals("07E0003")) {
				r.setCdErro("03");
			} else if (ex.getStatusCode().equals("07W0007")) {
				r.setCdErro("07");
			} else if (ex.getStatusCode().equals("07E0001")) {
				r.setCdErro("01");				
			} else {
				r.setCdErro("99");
			}			
				
			r.setDsErro(ex.getLocalizedMessage());
			r.setStatus(RespostaWSTO.NAO_OK);			
		} catch (ServiceGatewayException ex) {
			throw new ApplicationServiceException(ex);
		} catch (XMLParserException ex) {
			throw new ApplicationServiceException(ex);
		}				
		return r;
	}
	
	
	public ResultadoValidarSenhaTO validaSenha(RequestInfoTO requestInfo, DadosValidaSenhaTO dados) throws ApplicationServiceException,	BusinessException {
				
		if (logger.isInfoEnabled()) {
			logger.info("validaSenha - Método iniciado.");
		}
		
		ResultadoValidarSenhaTO r = new ResultadoValidarSenhaTO();
		ValidarSenhaGateway gateway = new ValidarSenhaGateway();
		
		try {
			r = (ResultadoValidarSenhaTO) gateway.call(requestInfo, dados);

			r.setCdCodigoRetorno("00");
			r.setStatus(RespostaWSTO.OK);

		} catch (TuxedoException ex) {
			
			if (ex.getStatusCode().equals("07W0001")) {
				r.setCdCodigoRetorno("01");
			} else if (ex.getStatusCode().equals("07W0002")) {
				r.setCdCodigoRetorno("02");
			} else if (ex.getStatusCode().equals("07W0003")) {
				r.setCdCodigoRetorno("03");
			} else if (ex.getStatusCode().equals("07W0004")) {
				r.setCdCodigoRetorno("04");
			} else if (ex.getStatusCode().equals("07W0005")) {
				r.setCdCodigoRetorno("05");
			} else if (ex.getStatusCode().equals("07W0006")) {
				r.setCdCodigoRetorno("06");
			} else if (ex.getStatusCode().equals("07W0010")) {
				r.setCdCodigoRetorno("10");
			} else if (ex.getStatusCode().equals("07W0011")) {
				r.setCdCodigoRetorno("11");
			} else {
				r.setCdCodigoRetorno("99");
			}

			r.setReason(ex.getLocalizedMessage());
			r.setStatus(RespostaWSTO.NAO_OK);
		} catch (ServiceGatewayException ex) {
			throw new ApplicationServiceException(ex);
		} catch (XMLParserException ex) {
			throw new ApplicationServiceException(ex);
		}
		return r;		
	}


}
