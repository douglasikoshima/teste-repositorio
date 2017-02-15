package br.com.indrasistemas.vivoservices.tracking.valuehandler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.valuehandler.PaginatedCriteria;
import br.com.indrasistemas.framework.service.valuehandler.ValueListHandler;
import br.com.indrasistemas.vivoservices.tracking.dao.TrackingAparelhosDAO;
import br.com.indrasistemas.vivoservices.tracking.to.DetalhePedidoTO;

public class TrackingAparelhosDetalhesPedidoVLH extends ValueListHandler {

	private static final Log logger = LogFactory
			.getLog(TrackingAparelhosDetalhesPedidoVLH.class);

	public TrackingAparelhosDetalhesPedidoVLH(PaginatedCriteria criteria) {
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

		DetalhePedidoTO filtro = ((TrackingAparelhosDetalhesPedidoCriteria) this
				.getCriteria()).getFiltro();

		Integer first = this.getCriteria().getFirstResult();
		Integer total = this.getCriteria().getMaxResults();

		result = trackingAparelhosDAO
				.buscarDetalhesPedido(filtro, first, total);

		if (logger.isDebugEnabled()) {
			logger.debug("_getList() - end");
		}

		return result;
	}
}
