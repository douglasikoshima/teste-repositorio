package br.com.indrasistemas.vivoservices.autenticacao.tuxgateway;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.framework.service.BaseTransferObject;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.TuxedoServiceGateway;
import br.com.indrasistemas.vivoservices.autenticacao.to.DadosValidaSenhaTO;

public class ValidarSenhaGateway extends TuxedoServiceGateway {

	protected Object createRequestBody(BaseTransferObject to) throws ServiceGatewayException {

		Object result = null;
		try {
			result = ValidarSenhaInJAXBTOBuilder.buildXmlIn((DadosValidaSenhaTO) to);
		} catch (JAXBException e) {
			throw new ServiceGatewayException(e);
		}
		return result;		
		
		
	}

	protected BaseTransferObject createResponseBody(Object body)
			throws ServiceGatewayException {

	    BaseTransferObject result = null;
		try {
			result = ValidarSenhaOutJAXBTOBuilder.buildResultadoValidarSenhaTO(body);
		} catch (JAXBException e) {
			throw new ServiceGatewayException(e);
		}
		return result;

	}

		
}
