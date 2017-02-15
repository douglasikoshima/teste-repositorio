package br.com.indrasistemas.vivoservices.autenticacao.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoAlterarSenhaTO;

public interface AlterarSenhaWS {
	public ResultadoAlterarSenhaTO alterarSenha(RequestInfoTO requestInfo, String nrTelefone, String senha);
}
