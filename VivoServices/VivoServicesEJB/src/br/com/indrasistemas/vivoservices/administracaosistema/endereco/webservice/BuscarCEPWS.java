package br.com.indrasistemas.vivoservices.administracaosistema.endereco.webservice;

import br.com.indrasistemas.vivoservices.administracaosistema.endereco.webservice.to.ResultadoPesquisaEnderecoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;

/**
 * 
 */
public interface BuscarCEPWS {

	public ResultadoPesquisaEnderecoTO buscarEnderecoPorCEP(RequestInfoWSTO requestInfo, String cep, Integer pagina, Integer qtdeRegistros);

}
