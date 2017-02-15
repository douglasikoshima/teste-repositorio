package br.com.indrasistemas.vivoservices.atendimento.protocolo.application;

import javax.ejb.SessionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.foundation.xml.XMLParserException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoBusinessException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.AbreProtocoloGateway;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.FechaProtocoloGateway;

public class ProtocoloAS extends BaseApplicationService {

    private static final Log logger = LogFactory.getLog(ProtocoloAS.class);

    public ProtocoloAS(SessionContext sessionContext) {
        super(sessionContext);
    }

    public ProtocoloTO aberturaProtocolo(RequestInfoTO requestInfo, ProtocoloTO filtro) throws ApplicationServiceException, BusinessException {
        ProtocoloTO ret = null;
        if (logger.isDebugEnabled()) {
            logger.debug("ProtocoloTO::aberturaProtocolo - Iniciando AS");
        }
        if(filtro==null){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else{
            if(filtro.getTpAbertura()==null || (new Integer(0)).equals(filtro.getTpAbertura())){
                throw new ApplicationServiceException("Parametro Tipo de Abertura é Obrigatório.");
            }
        }
        try{
            AbreProtocoloGateway gateway = new AbreProtocoloGateway();
            ret = (ProtocoloTO) gateway.call(requestInfo, filtro);
            
            if (logger.isDebugEnabled()) {
                logger.debug("ProtocoloTO::aberturaProtocolo - Finalizando AS");
            }
        }catch(TuxedoBusinessException e){
            logger.error("ProtocoloAS::aberturaProtocolo::TuxedoBusinessException", e);
            throw new BusinessException(e);

        }catch(ServiceGatewayException e){
            logger.error("ProtocoloAS::aberturaProtocolo::ServiceGatewayException", e);
            throw new ApplicationServiceException(e);
            
        }catch(TuxedoException e){
            logger.error("ProtocoloAS::aberturaProtocolo::TuxedoException", e);
            throw new BusinessException(e);
            
        }catch(XMLParserException e){
            logger.error("ProtocoloAS::aberturaProtocolo::XMLParserException", e);
            throw new ApplicationServiceException(e);
        }
        return ret;
    }

    public ProtocoloTO fechamentoProtocolo(RequestInfoTO requestInfo, ProtocoloTO filtro) throws ApplicationServiceException, BusinessException {
        ProtocoloTO ret = null;
        if (logger.isDebugEnabled()) {
            logger.debug("ProtocoloTO::fechamentoProtocolo - Iniciando AS");
        }
        if(filtro==null){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else{
            if(filtro.getNrProtocolo()==null || (new Long(0)).equals(filtro.getNrProtocolo())){
                throw new ApplicationServiceException("Parametro Tipo de NrProtocolo é Obrigatório.");
            }
            if(filtro.getIdSistema()==null || (new Long(0)).equals(filtro.getIdSistema())){
                throw new ApplicationServiceException("Parametro Tipo de NrProtocolo é Obrigatório.");
            }
        }
        try{
            FechaProtocoloGateway gateway = new FechaProtocoloGateway();
            ret = (ProtocoloTO) gateway.call(requestInfo, filtro);
            
            if (logger.isDebugEnabled()) {
                logger.debug("ProtocoloTO::fechamentoProtocolo - Finalizando AS");
            }
        }catch(TuxedoBusinessException e){
            logger.error("ProtocoloAS::fechamentoProtocolo::TuxedoBusinessException", e);
            throw new BusinessException(e);

        }catch(ServiceGatewayException e){
            logger.error("ProtocoloAS::fechamentoProtocolo::ServiceGatewayException", e);
            throw new ApplicationServiceException(e);
            
        }catch(TuxedoException e){
            logger.error("ProtocoloAS::fechamentoProtocolo::TuxedoException", e);
            throw new BusinessException(e);
            
        }catch(XMLParserException e){
            logger.error("ProtocoloAS::fechamentoProtocolo::XMLParserException", e);
            throw new ApplicationServiceException(e);
        }
        return ret;
    }
}
