package br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway;

import javax.xml.bind.JAXBException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BaseTransferObject;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.TuxedoServiceGateway;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO;

public class FechaProtocoloGateway extends TuxedoServiceGateway {

    private static final Log logger = LogFactory.getLog(FechaProtocoloGateway.class);

    public FechaProtocoloGateway() {
        super();
    }

    /**
     * Transforma um objeto do tipo Transfer Object para um objeto que retorne
     * um objeto de uma classe JAXB.
     */
    public Object createRequestBody(BaseTransferObject to) throws ServiceGatewayException {

        Object result = null;
        try{
            if(logger.isDebugEnabled()){
                logger.debug("ProtocoloGateway::createRequestBody - Iniciando chamada Tuxedo");
            }
            result = FechaProtocoloInJAXBTOBuilder.buildATDFECHAPROTxmlIn((ProtocoloTO) to);
            if(logger.isDebugEnabled()){
                logger.debug("ProtocoloGateway::createRequestBody - Chamada Tuxedo OK (" + result.toString() + ")");
            }
        }catch(JAXBException e){
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
        try{
            result = FechaProtocoloOutJAXBTOBuilder.buildProtocoloTO(body);
        }catch(JAXBException e){
            throw new ServiceGatewayException(e);
        }
        return result;
    }
}
