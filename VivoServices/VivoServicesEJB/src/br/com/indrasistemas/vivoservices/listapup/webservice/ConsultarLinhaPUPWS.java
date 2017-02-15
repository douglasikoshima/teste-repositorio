package br.com.indrasistemas.vivoservices.listapup.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO;

public interface ConsultarLinhaPUPWS {

	public ResultadoLinhaPUPTO consultarLinhaPUP(RequestInfoTO requestInfo,
			Integer cdCanal, Integer cdProcedencia, String sgCanal,
			String sgProcedencia, Integer cdDDD, Integer nrTelefone);

}
