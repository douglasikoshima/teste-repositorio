package br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class PortClienteOutJAXBTOBuilder {

	static public RespostaWSTO buildParametrosLinhaTO(Object body)
			throws JAXBException {

		MsgBodyType out = (MsgBodyType) body;

		RespostaWSTO parametros = new RespostaWSTO();

		parametros
				.setStatus((out.getPortabilidadeOutTO().getCdError() == 0) ? "OK"
						: "NOK");
		parametros.setReason(out.getPortabilidadeOutTO().getMsgError());

		return parametros;
	}
}
