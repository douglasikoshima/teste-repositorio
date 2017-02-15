package br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to.PalitoParamWSTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to.PalitoWSTO;

public interface PalitagemWS {

	public PalitoWSTO registrarPalito(RequestInfoTO requestInfo, PalitoParamWSTO palitoParam);

}
