package br.com.indrasistemas.vivoservices.autenticacao.webservice.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.autenticacao.delegate.AutenticacaoBD;
import br.com.indrasistemas.vivoservices.autenticacao.to.DadosAlteraSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoAlterarSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.webservice.AlterarSenhaWS;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.exception.ParametrosInvalidosException;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class AlterarSenhaWSImpl implements AlterarSenhaWS {

	private static final Log logger = LogFactory.getLog(AlterarSenhaWSImpl.class);
	
	public ResultadoAlterarSenhaTO alterarSenha(RequestInfoTO requestInfo, String nrTelefone, String senha) {
		
		ResultadoAlterarSenhaTO to = new ResultadoAlterarSenhaTO();

		try {
			AutenticacaoBD bd = new AutenticacaoBD();
			DadosAlteraSenhaTO dados = new DadosAlteraSenhaTO();
			
			dados.setNrTelefone(nrTelefone);
			dados.setSenha(senha);
			
			to = bd.alterarSenha(requestInfo, dados);
			
		} catch (ParametrosInvalidosException e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setCdErro("99");
			to.setReason(e.getMessage());
						
		} catch (BusinessDelegateException e) {		
			
            if (logger.isDebugEnabled()) {
                logger.debug("Exception::BusinessDelegateException - (" + e.getMessage() + ")");
            }
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setCdErro("99");
			to.setReason(e.getMessage());		
			
		} catch (BusinessException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception::BusinessException - (" + e.getMessage() + ")");
            }
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setCdErro("99");
			to.setReason(e.getMessage());
		}		
		
		return to;
	}

	
	
}
