package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.application;

import javax.ejb.SessionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoBusinessException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.dao.DesbloqueioDAO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.to.DesbloqueioGsmTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.tuxgateway.DesbloqueioGsmGateway;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class DesbloqueioGsmAS extends BaseApplicationService {

	private static final Log logger = LogFactory.getLog(DesbloqueioGsmAS.class);

	public DesbloqueioGsmAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public DesbloqueioGsmTO desbloquearAparelho(RequestInfoTO requestInfo, DesbloqueioGsmTO filtro) throws ApplicationServiceException, BusinessException {

		if (logger.isInfoEnabled()) {
			logger.info("desbloquearAparelho - Método iniciado.");
		}

		if(filtro==null){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else{
            if(filtro.getNrIMEI()==null){
                throw new ApplicationServiceException("Parametro IMEI é Obrigatório.");
            }
        }

		
		DesbloqueioGsmTO to = new DesbloqueioGsmTO();
		
		
	    // Limitador de Desbloqueio Diario
	    // INICIO		    
	    try {
	    	DesbloqueioDAO dao = new DesbloqueioDAO();		    
	    	String numero = filtro.getCdAreaRegistro() + filtro.getNrLinha();		    	
			dao.inserirDesbloqueio(numero);				
			int parametro = dao.quantidadeDesbloqueio();				
			if (dao.verificaLimite(numero) >= parametro) {
                to.setErrorCode("1111");
				to.setErrorDescription("Limite de desbloqueio atingido");
				return to;
			}				
		} catch (DAOException e) {
			e.printStackTrace();
		}
		// FIM				
		
		
		
		try{
		    try{
    	        DesbloqueioGsmGateway gateway = new DesbloqueioGsmGateway();
    	        to = (DesbloqueioGsmTO) gateway.call(requestInfo, filtro);
		    }catch(TuxedoBusinessException e){
		        to.setErrorCode(e.getErrorCode());
		        to.setErrorDescription(e.getErrorDescription());
		        to.setStatusConsulta(e.getStatusCode());
		    }
	        if (logger.isInfoEnabled()) {
	            logger.info("desbloquearAparelho - Método finalizado.");
	        }
		}catch(Exception e){
		    throw new BusinessException(e);
		}
		return to;
	}
}
