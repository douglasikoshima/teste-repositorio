package br.com.indrasistemas.vivoservices.atendimento.gestor.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.gestor.webservice.to.AlertaSaidaWSTO;

public interface AlertaSaidaWS {

	public AlertaSaidaWSTO consultarLinha(RequestInfoTO requestInfo, Long cdAreaRegistro, Long nrLinha);

}
