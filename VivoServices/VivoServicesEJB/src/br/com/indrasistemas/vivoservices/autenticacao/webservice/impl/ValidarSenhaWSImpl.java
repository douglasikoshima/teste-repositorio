package br.com.indrasistemas.vivoservices.autenticacao.webservice.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoBusinessException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.autenticacao.delegate.AutenticacaoBD;
import br.com.indrasistemas.vivoservices.autenticacao.to.DadosValidaSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoValidarSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.webservice.ValidarSenhaWS;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.exception.ParametrosInvalidosException;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ValidarSenhaWSImpl implements ValidarSenhaWS {
	
	private static final Log logger = LogFactory.getLog(ValidarSenhaWSImpl.class);

	public ResultadoValidarSenhaTO validarSenha(RequestInfoTO requestInfo, Integer cdDDD, Integer nrTelefone, String senha) {
		ResultadoValidarSenhaTO to = new ResultadoValidarSenhaTO();

		try {
			logger.info("VALIDAR_SENHA - INICIO");
			
			AutenticacaoBD bd = new AutenticacaoBD();
			DadosValidaSenhaTO dados = new DadosValidaSenhaTO();

			logger.info("VALIDAR_SENHA - DDD: " + cdDDD + " TELEFONE: " + nrTelefone + " SENHA: " + senha);			
			
			dados.setCdDDD(cdDDD);
			dados.setCdNumTelefone(nrTelefone);
			dados.setSenha(senha);
			
			logger.info("VALIDAR_SENHA - PREPARANDO PARA CHAMAR SERVICO TUXEDO");
			to = bd.validarSenha(requestInfo, dados);
			
			logger.info("VALIDAR_SENHA - CODIGO_RETORNO: " + to.getCdCodigoRetorno());
			logger.info("VALIDAR_SENHA - REASON: " + to.getReason());
			logger.info("VALIDAR_SENHA - DATA NASCIMENTO: " + to.getDtNasc());
			logger.info("VALIDAR_SENHA - EMAIL: " + to.getE_mail());
			logger.info("VALIDAR_SENHA - NOME: " + to.getNome());
			logger.info("VALIDAR_SENHA - PRIMEIRO NOME: " + to.getPrimeiroNome());
			logger.info("VALIDAR_SENHA - SEXO: " + to.getSexo());
			logger.info("VALIDAR_SENHA - STATUS: " + to.getStatus());
			
		} catch (ParametrosInvalidosException e) {
            logger.info("Exception::ParametrosInvalidosException - (" + e.getMessage() + ")");
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setCdCodigoRetorno("99");
			to.setReason(e.getMessage());
		} catch (BusinessDelegateException e) {
			logger.info("Exception::BusinessDelegateException - (" + e.getMessage() + ")");            
            to.setStatus(RespostaWSTO.NAO_OK);
            to.setCdCodigoRetorno("99");
            to.setReason(e.getMessage());								
		} catch (BusinessException e) {
            logger.info("Exception::BusinessException - (" + e.getMessage() + ")");         
            to.setStatus(RespostaWSTO.NAO_OK);
            to.setCdCodigoRetorno("99");
            to.setReason(e.getMessage());            
		}
		logger.info("VALIDAR_SENHA - FIM");
		return to;
	}

}
