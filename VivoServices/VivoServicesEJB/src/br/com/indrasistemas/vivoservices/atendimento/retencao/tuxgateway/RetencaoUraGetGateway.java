package br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway;

import javax.xml.bind.JAXBException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BaseTransferObject;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.TuxedoServiceGateway;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;

public class RetencaoUraGetGateway extends TuxedoServiceGateway {

    private static final Log logger = LogFactory.getLog(RetencaoUraGetGateway.class);

    public RetencaoUraGetGateway() {
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
                logger.debug("RetencaoUraGetGateway::createRequestBody - Montando XML Entrada");
            }
            result = RetencaoUraGetInJAXBTOBuilder.buildGETBONUSxmlIn((RetencaoTO) to);
            if(logger.isDebugEnabled()){
                logger.debug("RetencaoUraGetGateway::createRequestBody - Montado XML (" + result.toString() + ")");
            }
        }catch(JAXBException e){
            logger.error("RetencaoUraGetGateway::createRequestBody - ERROR ", e);
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
                logger.debug("RetencaoUraGetGateway::createResponseBody - Montando Objeto de Saida");
            }
            result = RetencaoUraGetOutJAXBTOBuilder.buildGETBONUSxmlOut(body);
            if(logger.isDebugEnabled()){
                logger.debug("RetencaoUraGetGateway::createResponseBody - Montado Objeto (" + result.toString() + ")");
            }
        }catch(JAXBException e){
            logger.error("RetencaoUraGetGateway::createResponseBody - ERROR ", e);
            throw new ServiceGatewayException(e);
        }
        return result;

    }
}
