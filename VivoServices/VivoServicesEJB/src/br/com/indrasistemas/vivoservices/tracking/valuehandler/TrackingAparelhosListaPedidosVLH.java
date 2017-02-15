package br.com.indrasistemas.vivoservices.tracking.valuehandler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.valuehandler.PaginatedCriteria;
import br.com.indrasistemas.framework.service.valuehandler.ValueListHandler;
import br.com.indrasistemas.vivoservices.tracking.dao.TrackingAparelhosDAO;
import br.com.indrasistemas.vivoservices.tracking.to.PedidoTO;

public class TrackingAparelhosListaPedidosVLH extends ValueListHandler {

	private static final Log logger = LogFactory
			.getLog(TrackingAparelhosListaPedidosVLH.class);

	public TrackingAparelhosListaPedidosVLH(PaginatedCriteria criteria) {
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

		TrackingAparelhosDAO trackingAparelhosDAO = new TrackingAparelhosDAO();
		List result = null;

		PedidoTO filtro = ((TrackingAparelhosListaPedidosCriteria) this
				.getCriteria()).getFiltro();
		Integer first = this.getCriteria().getFirstResult();
		Integer total = this.getCriteria().getMaxResults();

		result = trackingAparelhosDAO.buscarListaPedidos(filtro, first, total);

		return result;
	}

}
