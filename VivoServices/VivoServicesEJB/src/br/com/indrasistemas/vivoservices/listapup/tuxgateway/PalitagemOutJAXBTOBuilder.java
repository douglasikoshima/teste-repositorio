package br.com.indrasistemas.vivoservices.listapup.tuxgateway;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.listapup.to.ParametrosLinhaTO;

public class PalitagemOutJAXBTOBuilder {

	static public ParametrosLinhaTO buildParametrosLinhaTO(Object body)
			throws JAXBException {

		br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgBodyType out = (br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgBodyType) body;

		ParametrosLinhaTO parametros = new ParametrosLinhaTO();
		parametros.setIdAtendimento(new Long(out.getAtendimentoVO()
				.getIdAtendimento()));

		return parametros;

	}

}
