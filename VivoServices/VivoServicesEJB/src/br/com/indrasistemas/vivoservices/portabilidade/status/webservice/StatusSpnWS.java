package br.com.indrasistemas.vivoservices.portabilidade.status.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.status.webservice.to.RespostaWSTO;

public interface StatusSpnWS {

	public RespostaWSTO validarStatusSpn(RequestInfoTO requestInfo, String nrLinha);

}