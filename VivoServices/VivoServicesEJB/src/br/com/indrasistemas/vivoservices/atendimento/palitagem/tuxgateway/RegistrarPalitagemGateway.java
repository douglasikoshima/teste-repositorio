package br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway;

import javax.xml.bind.JAXBException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BaseTransferObject;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.TuxedoServiceGateway;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.PalitagemTO;

public class RegistrarPalitagemGateway extends TuxedoServiceGateway {

    private static final Log logger = LogFactory.getLog(RegistrarPalitagemGateway.class);
    
    public RegistrarPalitagemGateway() {
        super();
    }

    /**
     * Transforma um objeto do tipo Transfer Object para um objeto que retorne
     * um objeto de uma classe JAXB.
     */
    public Object createRequestBody(BaseTransferObject to) throws ServiceGatewayException {

        Object result = null;
        try{
            if (logger.isDebugEnabled()) {
                logger.debug("RegistrarPalitagemGateway::createRequestBody - Montando XML Entrada");
            }
            result = PalitagemInJAXBTOBuilder.buildREGPALITAGEFOxmlIn((PalitagemTO) to);
            if (logger.isDebugEnabled()) {
                logger.debug("RegistrarPalitagemGateway::createRequestBody - Montado XML ("+result.toString()+")");
            }
        }catch(JAXBException e){
            logger.error("RegistrarPalitagemGateway::createRequestBody - ERROR ", e);
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
            if (logger.isDebugEnabled()) {
                logger.debug("RegistrarPalitagemGateway::createResponseBody - Montando Objeto de Saida");
            }
            result = PalitagemOutJAXBTOBuilder.buildREGPALITAGEFOxmlOut(body);
            if (logger.isDebugEnabled()) {
                logger.debug("RegistrarPalitagemGateway::createResponseBody - Montado Objeto ("+result.toString()+")");
            }
        }catch(JAXBException e){
            logger.error("RegistrarPalitagemGateway::createResponseBody - ERROR ", e);
            throw new ServiceGatewayException(e);
        }
        return result;

    }
}
