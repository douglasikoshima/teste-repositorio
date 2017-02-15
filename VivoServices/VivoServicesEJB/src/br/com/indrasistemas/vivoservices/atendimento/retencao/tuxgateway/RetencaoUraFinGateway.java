package br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway;

import javax.xml.bind.JAXBException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BaseTransferObject;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.TuxedoServiceGateway;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;

public class RetencaoUraFinGateway extends TuxedoServiceGateway {

    private static final Log logger = LogFactory.getLog(RetencaoUraFinGateway.class);

    public RetencaoUraFinGateway() {
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
                logger.debug("RetencaoUraFinGateway::createRequestBody - Montando XML Entrada");
            }
            result = RetencaoUraFinInJAXBTOBuilder.buildFINALRETENCAOxmlIn((RetencaoTO) to);
            if(logger.isDebugEnabled()){
                logger.debug("RetencaoUraFinGateway::createRequestBody - Montado XML (" + result.toString() + ")");
            }
        }catch(JAXBException e){
            logger.error("RetencaoUraFinGateway::createRequestBody - ERROR ", e);
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
            if(logger.isDebugEnabled()){
                logger.debug("RetencaoUraFinGateway::createResponseBody - Montando Objeto de Saida");
            }
            result = RetencaoUraFinOutJAXBTOBuilder.buildFINALRETENCAOxmlOut(body);
            if(logger.isDebugEnabled()){
                logger.debug("RetencaoUraFinGateway::createResponseBody - Montado Objeto (" + result.toString() + ")");
            }
        }catch(JAXBException e){
            logger.error("RetencaoUraFinGateway::createResponseBody - ERROR ", e);
            throw new ServiceGatewayException(e);
        }
        return result;

    }
}
