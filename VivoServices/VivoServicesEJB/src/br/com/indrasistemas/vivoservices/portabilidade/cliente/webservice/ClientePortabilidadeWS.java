package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.ClientePFTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.ClientePJTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public interface ClientePortabilidadeWS {

	public RespostaWSTO manutencaoClientePortabilidade(
			RequestInfoTO requestInfo, int tpOperacao, String sgTipoPessoa,
			long nrLinha, String sgTipoLinha, long idPessoa,
			ClientePFTO clientePF, ClientePJTO clientePJ);

}