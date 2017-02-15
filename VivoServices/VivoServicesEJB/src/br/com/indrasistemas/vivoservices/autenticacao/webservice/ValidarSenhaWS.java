package br.com.indrasistemas.vivoservices.autenticacao.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoValidarSenhaTO;

public interface ValidarSenhaWS {
	public ResultadoValidarSenhaTO validarSenha(RequestInfoTO requestInfo, Integer cdDDD, Integer nrTelefone, String senha);
}
