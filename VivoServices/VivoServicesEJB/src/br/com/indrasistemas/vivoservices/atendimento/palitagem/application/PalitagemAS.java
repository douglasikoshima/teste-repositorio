package br.com.indrasistemas.vivoservices.atendimento.palitagem.application;

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
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.PalitagemTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.tuxgateway.RegistrarPalitagemGateway;

public class PalitagemAS extends BaseApplicationService {

	private static final Log logger = LogFactory.getLog(PalitagemAS.class);

	public PalitagemAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public PalitagemTO registrarPalito(RequestInfoTO requestInfo, PalitagemTO filtro) throws ApplicationServiceException, BusinessException {
	    PalitagemTO ret = new PalitagemTO();
		if (logger.isDebugEnabled()) {
            logger.debug("PalitagemAS::registrarPalito - Iniciando AS");
		}
		if(filtro==null){
		    throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
		}else{
            if(filtro.getNrProtocolo()==null || "".equals(filtro.getNrProtocolo())){
                throw new ApplicationServiceException("Parametro nrProtocolo é Obrigatório.");
		    }
		}
		    try{
            RegistrarPalitagemGateway gateway = new RegistrarPalitagemGateway();
            ret = (PalitagemTO) gateway.call(requestInfo, filtro);
            
        }catch(TuxedoBusinessException e){
            logger.error("PalitagemAS::registrarPalito::TuxedoBusinessException", e);
            throw new BusinessException(e);

        }catch(ServiceGatewayException e){
            logger.error("PalitagemAS::registrarPalito::ServiceGatewayException", e);
		        throw new ApplicationServiceException(e);

        }catch(TuxedoException e){
            logger.error("PalitagemAS::registrarPalito::TuxedoException", e);
			throw new BusinessException(e);
            
        }catch(XMLParserException e){
            logger.error("PalitagemAS::registrarPalito::XMLParserException", e);
            throw new ApplicationServiceException(e);
		}
		return ret;
	}
}
