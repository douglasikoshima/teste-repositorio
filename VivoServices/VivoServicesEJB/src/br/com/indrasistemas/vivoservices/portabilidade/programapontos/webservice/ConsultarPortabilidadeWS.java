package br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice.to.RespostaStatusPortabilidadeTO;

public interface ConsultarPortabilidadeWS {

	public RespostaStatusPortabilidadeTO consultarStatusPortabilidade(RequestInfoTO requestInfo, String nrLinha);

}