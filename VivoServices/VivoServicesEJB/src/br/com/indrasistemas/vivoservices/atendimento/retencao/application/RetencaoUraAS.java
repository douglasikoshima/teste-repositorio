package br.com.indrasistemas.vivoservices.atendimento.retencao.application;

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
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.RetencaoUraFinGateway;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.RetencaoUraGetGateway;

public class RetencaoUraAS extends BaseApplicationService {

    private static final Log logger = LogFactory.getLog(RetencaoUraAS.class);

    public RetencaoUraAS(SessionContext sessionContext) {
        super(sessionContext);
    }

    public RetencaoTO consultarOfertas(RequestInfoTO requestInfo, RetencaoTO filtro) throws ApplicationServiceException, BusinessException {
        RetencaoTO ret = new RetencaoTO();
        if(logger.isDebugEnabled()){
            logger.debug("RetencaoUraAS::consultarOfertas - Iniciando AS");
        }
        if(filtro == null){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else{
            if(filtro.getNrOperacao()==null){
                throw new ApplicationServiceException("Parametro nrOperacao é Obrigatório.");
            }
        }
        try{
            RetencaoUraGetGateway gateway = new RetencaoUraGetGateway();
            ret = (RetencaoTO) gateway.call(requestInfo, filtro);
        }catch(TuxedoBusinessException e){
            logger.error("RetencaoUraAS::consultarOfertas::TuxedoBusinessException", e);
            throw new BusinessException(e);

        }catch(ServiceGatewayException e){
            logger.error("RetencaoUraAS::consultarOfertas::ServiceGatewayException", e);
            throw new ApplicationServiceException(e);
            
        }catch(TuxedoException e){
            logger.error("RetencaoUraAS::consultarOfertas::TuxedoException", e);
            throw new BusinessException(e);
            
        }catch(XMLParserException e){
            logger.error("RetencaoUraAS::consultarOfertas::XMLParserException", e);
            throw new ApplicationServiceException(e);
        }
        
        return ret;
    }

    public RetencaoTO aceitarOfertas(RequestInfoTO requestInfo, RetencaoTO filtro) throws ApplicationServiceException, BusinessException {
        RetencaoTO ret = new RetencaoTO();
        if(logger.isDebugEnabled()){
            logger.debug("RetencaoUraAS::aceitarOfertas - Iniciando AS");
        }
        if(filtro == null){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else{
            if(filtro.getNrOperacao()==null){
                throw new ApplicationServiceException("Parametro nrOperacao é Obrigatório.");
            }
        }

        try{
            RetencaoUraFinGateway gateway = new RetencaoUraFinGateway();
            ret = (RetencaoTO) gateway.call(requestInfo, filtro);
        }catch(TuxedoBusinessException e){
            logger.error("RetencaoUraAS::aceitarOfertas::TuxedoBusinessException", e);
            throw new BusinessException(e);

        }catch(ServiceGatewayException e){
            logger.error("RetencaoUraAS::aceitarOfertas::ServiceGatewayException", e);
            throw new ApplicationServiceException(e);
            
        }catch(TuxedoException e){
            logger.error("RetencaoUraAS::aceitarOfertas::TuxedoException", e);
            throw new BusinessException(e);
            
        }catch(XMLParserException e){
            logger.error("RetencaoUraAS::aceitarOfertas::XMLParserException", e);
            throw new ApplicationServiceException(e);
        }
        return ret;
    }

    public RetencaoTO recusarOfertas(RequestInfoTO requestInfo, RetencaoTO filtro) throws ApplicationServiceException, BusinessException {
        RetencaoTO ret = new RetencaoTO();
        if(logger.isDebugEnabled()){
            logger.debug("RetencaoUraAS::recusarOfertas - Iniciando AS");
        }
        if(filtro == null){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else{
            if(filtro.getNrOperacao()==null){
                throw new ApplicationServiceException("Parametro nrOperacao é Obrigatório.");
            }
        }

        try{
            RetencaoUraFinGateway gateway = new RetencaoUraFinGateway();
            ret = (RetencaoTO) gateway.call(requestInfo, filtro);
        }catch(TuxedoBusinessException e){
            logger.error("RetencaoUraAS::recusarOfertas::TuxedoBusinessException", e);
            throw new BusinessException(e);

        }catch(ServiceGatewayException e){
            logger.error("RetencaoUraAS::recusarOfertas::ServiceGatewayException", e);
            throw new ApplicationServiceException(e);
            
        }catch(TuxedoException e){
            logger.error("RetencaoUraAS::recusarOfertas::TuxedoException", e);
            throw new BusinessException(e);
            
        }catch(XMLParserException e){
            logger.error("RetencaoUraAS::recusarOfertas::XMLParserException", e);
            throw new ApplicationServiceException(e);
        }
        return ret;
    }

}
