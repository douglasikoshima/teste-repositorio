package br.com.indrasistemas.vivoservices.vole.contato.webservice;

import br.com.indrasistemas.vivoservices.vole.contato.webservice.to.ResultadoPontoContatoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;

/**
 * 
 */
public interface BuscarPontoContatoWS {

	public ResultadoPontoContatoTO buscarPontoContatoPorCNPJ(
			RequestInfoWSTO requestInfo, String nrCnpj, String cdConta, Integer pagina, Integer qtdeRegistros);

}
