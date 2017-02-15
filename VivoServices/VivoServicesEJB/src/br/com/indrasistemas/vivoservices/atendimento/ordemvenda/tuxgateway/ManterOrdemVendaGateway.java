package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.tuxgateway;

import javax.xml.bind.JAXBException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BaseTransferObject;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.TuxedoServiceGateway;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.to.PalitagemTO;

public class ManterOrdemVendaGateway extends TuxedoServiceGateway {

    private static final Log logger = LogFactory.getLog(ManterOrdemVendaGateway.class);
    
    public ManterOrdemVendaGateway() {
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
                logger.debug("ManterOrdemVendaGateway::createRequestBody - Montando XML Entrada");
            }
            result = ManterOrdemVendaInJAXBTOBuilder.buildREGPALITAGEFOxmlIn((PalitagemTO) to);
            if (logger.isDebugEnabled()) {
                logger.debug("ManterOrdemVendaGateway::createRequestBody - Montado XML ("+result.toString()+")");
            }
        }catch(JAXBException e){
            logger.error("ManterOrdemVendaGateway::createRequestBody - ERROR ", e);
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
                logger.debug("ManterOrdemVendaGateway::createResponseBody - Montando Objeto de Saida");
            }
            result = ManterOrdemVendaOutJAXBTOBuilder.buildREGPALITAGEFOxmlOut(body);
            if (logger.isDebugEnabled()) {
                logger.debug("ManterOrdemVendaGateway::createResponseBody - Montado Objeto ("+result.toString()+")");
            }
        }catch(JAXBException e){
            logger.error("ManterOrdemVendaGateway::createResponseBody - ERROR ", e);
            throw new ServiceGatewayException(e);
        }
        return result;

    }
}
