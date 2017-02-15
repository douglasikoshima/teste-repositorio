package br.com.indrasistemas.vivoservices.atendimento.gestor.application;

import javax.ejb.SessionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.gestor.dao.AlertaSaidaDAO;
import br.com.indrasistemas.vivoservices.atendimento.gestor.to.AlertaSaidaTO;

public class AlertaSaidaAS extends BaseApplicationService {

    private static final Log logger = LogFactory.getLog(AlertaSaidaAS.class);

    public AlertaSaidaAS(SessionContext sessionContext) {
        super(sessionContext);
    }

    public AlertaSaidaTO consultar(RequestInfoTO requestInfo, Long cdAreaRegistro, Long nrLinha) throws ApplicationServiceException, BusinessException {
        AlertaSaidaTO ret = new AlertaSaidaTO();
        if(logger.isDebugEnabled()){
            logger.debug("RetencaoUraAS::consultarOfertas - Iniciando AS");
        }
        if(cdAreaRegistro == null || Long.valueOf("0").equals(cdAreaRegistro)){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else if(nrLinha == null || Long.valueOf("0").equals(nrLinha)){
            throw new ApplicationServiceException("Parametro nrOperacao é Obrigatório.");
        }
        try{
            AlertaSaidaDAO dao = new AlertaSaidaDAO();
            ret = dao.consultar(requestInfo, cdAreaRegistro, nrLinha);
        }catch(DAOException e){
            logger.error("RetencaoUraAS::consultarOfertas::TuxedoBusinessException", e);
            throw new BusinessException(e);
        }
        
        return ret;
    }
}
