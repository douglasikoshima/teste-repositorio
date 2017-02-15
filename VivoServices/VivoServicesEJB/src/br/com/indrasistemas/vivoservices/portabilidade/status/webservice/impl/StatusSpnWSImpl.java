package br.com.indrasistemas.vivoservices.portabilidade.status.webservice.impl;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.status.delegate.StatusSpnBD;
import br.com.indrasistemas.vivoservices.portabilidade.status.to.RespostaTO;
import br.com.indrasistemas.vivoservices.portabilidade.status.webservice.StatusSpnWS;
import br.com.indrasistemas.vivoservices.portabilidade.status.webservice.to.RespostaWSTO;

public class StatusSpnWSImpl implements StatusSpnWS {

	public StatusSpnWSImpl() {
	}

	public RespostaWSTO validarStatusSpn(RequestInfoTO requestInfo, String nrLinha) {
		RespostaWSTO to = new RespostaWSTO();
		try {
			StatusSpnBD bd = new StatusSpnBD();

			RespostaTO respostaTO = bd.validaStatusSpn(requestInfo, nrLinha);
			to.setNrDocumento(respostaTO.getNrDocumento());
			to.setStatusLinha(respostaTO.getStatusLinha());
			to.setTpDocumento(respostaTO.getTpDocumento());
			to.setTpLinha(respostaTO.getTpLinha());
			to.setStatus(RespostaWSTO.OK);
			to.setReason("");

		} catch (Exception e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setReason(e.getMessage());
		}
		return to;
	}
}