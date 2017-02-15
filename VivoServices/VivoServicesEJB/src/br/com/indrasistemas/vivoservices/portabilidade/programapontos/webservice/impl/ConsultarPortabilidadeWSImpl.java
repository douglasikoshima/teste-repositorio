package br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice.impl;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.delegate.ConsultarPortabilidadeBD;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice.ConsultarPortabilidadeWS;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice.to.RespostaStatusPortabilidadeTO;

public class ConsultarPortabilidadeWSImpl implements ConsultarPortabilidadeWS {

	public ConsultarPortabilidadeWSImpl() {
	}

	public RespostaStatusPortabilidadeTO consultarStatusPortabilidade(
			RequestInfoTO requestInfo, String nrLinha) {

		RespostaStatusPortabilidadeTO to = new RespostaStatusPortabilidadeTO();

		ConsultarPortabilidadeBD ppBD = new ConsultarPortabilidadeBD();

		try {
			to = ppBD.consultarStatusPortabilidade(requestInfo, nrLinha);
		} catch (Exception e) {
			to.setStatus("NOK");
			to.setReason(e.getMessage());
		}
		return to;
	}

}