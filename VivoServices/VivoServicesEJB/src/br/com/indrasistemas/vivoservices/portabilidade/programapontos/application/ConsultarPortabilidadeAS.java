package br.com.indrasistemas.vivoservices.portabilidade.programapontos.application;

import javax.ejb.SessionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.dao.ConsultarPortabilidadeDAO;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice.to.RespostaStatusPortabilidadeTO;

public class ConsultarPortabilidadeAS extends BaseApplicationService {

	private static final Log logger = LogFactory.getLog(ConsultarPortabilidadeAS.class);

	public ConsultarPortabilidadeAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public RespostaStatusPortabilidadeTO consultarStatusPortabilidade( RequestInfoTO requestInfo, String nrLinha)
			throws ApplicationServiceException, BusinessException {

		RespostaStatusPortabilidadeTO resultDAO = null;

		try {
			ConsultarPortabilidadeDAO portabilidadeProgramaPontosDAO = new ConsultarPortabilidadeDAO();
			resultDAO = portabilidadeProgramaPontosDAO.consultarStatusPortabilidade(requestInfo, nrLinha);
			
			if(resultDAO.getInAcao()<0){
				String acao   = resultDAO.getDsAcao().toUpperCase().trim();
				String estado = resultDAO.getDsEstado().toUpperCase().trim();
	
				boolean teste1 = !"CONCLUÍDO".equals(estado) && !"CANCELADO".equals(estado);
				boolean teste2 = !"DESCONEXÃO CANCELADA".equals(estado); 
	
				if("PORTOUT".equals(acao) && teste1){
					resultDAO.setInAcao(1);
	
				}else if("WINBACK".equals(acao) && teste1 && teste2){
					resultDAO.setInAcao(2);
	
				}else if("FRAUDE".equals(acao) && teste1){
					resultDAO.setInAcao(3);
	
				}else if("PORTIN".equals(acao) && teste1 && teste2){
					resultDAO.setInAcao(4);

				}else{
					resultDAO.setInAcao(0);
				}
			}

		} catch (DAOException ex) {
			getSessionContext().setRollbackOnly();
			logger.error("", ex);
			throw new ApplicationServiceException(LinhaPUPWSTO.CD_ERRO_BASE + "|Erro de acesso à base de dados.");
		}
		return resultDAO;
	}

}
