package br.com.indrasistemas.vivoservices.portabilidade.status.application;

import javax.ejb.SessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.status.dao.StatusSpnDAO;
import br.com.indrasistemas.vivoservices.portabilidade.status.to.RespostaTO;

public class StatusSpnAS extends BaseApplicationService {

	private static final Log logger = LogFactory.getLog(StatusSpnAS.class);
	
	public StatusSpnAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public RespostaTO validaStatusSpn(RequestInfoTO requestInfo, String nrLinha) throws ApplicationServiceException, BusinessException {
		RespostaTO respostaTO = new RespostaTO();
		try {
			if(nrLinha==null || "".equals(nrLinha)){
				throw new BusinessException("É necessário informar o número da linha");
			}
			String nrTelefone = nrLinha.replaceAll("[^0-9]", "");
			if(nrTelefone!=null && (nrTelefone.length()<10 || nrTelefone.length()>11)){
				throw new BusinessException("Número da linha é inválido.");
			}
			StatusSpnDAO statusSpnDAO = new StatusSpnDAO();
			respostaTO = statusSpnDAO.validaStatusSpn(requestInfo, nrLinha);
			
		} catch (DAOException ex) {
			logger.error("StatusSpnAS::validaStatusSpn::DAOException", ex);
			throw new ApplicationServiceException(ex);
		}
		return respostaTO;
	}
	
}
