package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.webservice;

import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.webservice.to.DesbloqueioGsmWSTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.webservice.to.ResultadoDesbloqueioWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;

/**
 * 
 */
public interface DesbloqueioGsmWS {

	public ResultadoDesbloqueioWSTO desbloquearAparelho(RequestInfoWSTO requestInfo, DesbloqueioGsmWSTO desbloqueio);

}
