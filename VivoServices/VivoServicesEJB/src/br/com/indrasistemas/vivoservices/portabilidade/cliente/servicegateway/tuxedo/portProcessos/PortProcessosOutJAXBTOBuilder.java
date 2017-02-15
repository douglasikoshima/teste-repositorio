package br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portProcessos;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portProcessos.saida.MsgBodyType;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.RespostaManutencaoProcessoTO;

public class PortProcessosOutJAXBTOBuilder {

	static public RespostaManutencaoProcessoTO buildManutencaoProcessoTO(
			Object body) throws JAXBException {

		MsgBodyType out = (MsgBodyType) body;

		RespostaManutencaoProcessoTO parametros = new RespostaManutencaoProcessoTO();

		parametros
				.setStatus((out.getPortabilidadeOutTO().getCdError() == 0) ? "OK"
						: "NOK");
		parametros.setNrProtocolo(Long.valueOf(out.getPortabilidadeOutTO()
				.getNrProtocolo()));
		parametros.setReason(out.getPortabilidadeOutTO().getMsgError());

		return parametros;

	}
}
