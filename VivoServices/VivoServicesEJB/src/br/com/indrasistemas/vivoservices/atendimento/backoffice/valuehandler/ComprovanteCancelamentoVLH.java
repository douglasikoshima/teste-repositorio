package br.com.indrasistemas.vivoservices.atendimento.backoffice.valuehandler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.valuehandler.PaginatedCriteria;
import br.com.indrasistemas.framework.service.valuehandler.ValueListHandler;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.dao.ComprovanteCancelamentoDAO;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.to.ComprovanteCancelamentoTO;

public class ComprovanteCancelamentoVLH extends ValueListHandler {

	private static final Log logger = LogFactory
			.getLog(ComprovanteCancelamentoVLH.class);

	public ComprovanteCancelamentoVLH(PaginatedCriteria criteria) {
		super(criteria);
	}

	protected Long _getCount() throws DAOException {
		if (logger.isDebugEnabled()) {
			logger.debug("_getCount() - start");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("_getCount() - end");
		}
		return null;
	}

	protected List _getList() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("_getList() - start");
		}

		ComprovanteCancelamentoDAO comprovanteCancelamentoDAO = new ComprovanteCancelamentoDAO();
		List result = null;

		ComprovanteCancelamentoTO filtro = ((ComprovanteCancelamentoPagCriteria) this
				.getCriteria()).getFiltro();
		Integer first = this.getCriteria().getFirstResult();
		Integer total = this.getCriteria().getMaxResults();

		result = comprovanteCancelamentoDAO.buscarComprovanteCancelamento(
				filtro, first, total);

		return result;
	}

}
