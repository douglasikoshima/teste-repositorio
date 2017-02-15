package br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoAlterarSenhaTO;

public class AlterarSenhaOutJAXBTOBuilder {

	
	static public ResultadoAlterarSenhaTO buildResultadoAlterarSenhaTO(Object body) throws JAXBException {
		
		br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.saida.MsgBodyType out = (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.saida.MsgBodyType) body;
		
		ResultadoAlterarSenhaTO r = new ResultadoAlterarSenhaTO();
		
		r.setCdErro(out.getAlteraSenha().getStatusCode());
		r.setDsErro(out.getAlteraSenha().getStatusText());
		
		return r;
	}
	
}
