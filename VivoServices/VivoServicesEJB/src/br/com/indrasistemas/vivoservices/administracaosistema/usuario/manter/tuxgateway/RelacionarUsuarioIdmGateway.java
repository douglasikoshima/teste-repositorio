package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.framework.service.BaseTransferObject;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.TuxedoServiceGateway;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RelacionaUsuarioTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.entrada.grvidm.GrvIdmJAXBTOBuilder;

public class RelacionarUsuarioIdmGateway extends TuxedoServiceGateway {

	public RelacionarUsuarioIdmGateway() {
		super();
	}

	/**
	 * Transforma um objeto do tipo Transfer Object para um objeto que retorne
	 * um objeto de uma classe JAXB.
	 */
	public Object createRequestBody(BaseTransferObject to) throws ServiceGatewayException {

		Object result = null;
		try {
			result = GrvIdmJAXBTOBuilder.buildXmlIn((RelacionaUsuarioTO) to);
		
		} catch (JAXBException e) {
			throw new ServiceGatewayException(e);
		}
		return result;
	}

	
	
	/**
	 * Transforma um objeto de uma classe JAXB para um objeto do tipo Transfer
	 * Object.
	 */
	public BaseTransferObject createResponseBody(Object body) throws ServiceGatewayException {

	    BaseTransferObject result = null;
		try {
			result = GrvIdmJAXBTOBuilder.buildXmlOut(body);
		
		} catch (JAXBException e) {
			throw new ServiceGatewayException(e);
		}
		return result;

	}
}
