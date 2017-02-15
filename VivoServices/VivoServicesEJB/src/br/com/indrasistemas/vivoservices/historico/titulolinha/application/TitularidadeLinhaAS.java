package br.com.indrasistemas.vivoservices.historico.titulolinha.application;

import javax.ejb.SessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.dao.TitularidadeLinhaDAO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.to.ParametrosTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.to.TitularidadeLinhaTO;

public class TitularidadeLinhaAS extends BaseApplicationService {

	private static final Log logger = LogFactory.getLog(TitularidadeLinhaAS.class);

	public TitularidadeLinhaAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public TitularidadeLinhaTO pesquisarTitularidadeLinha(RequestInfoTO requestInfo, ParametrosTO parametros) throws ApplicationServiceException, BusinessException {

		TitularidadeLinhaTO result = new TitularidadeLinhaTO();

		if (logger.isDebugEnabled()) {
			logger.debug("TitularidadeLinhaAS::pesquisarTitularidadeLinha - Iniciando AS");
		}

		try {
			TitularidadeLinhaDAO dao = new TitularidadeLinhaDAO();
			result = dao.pesquisarTitularidadeLinha(parametros);

		} catch (DAOException e) {
			logger.error("TitularidadeLinhaAS::pesquisarTitularidadeLinha::DAOException", e);
			throw new BusinessException(e);
		}
		return result;
	}

}
