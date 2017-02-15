package br.com.indrasistemas.vivoservices.administracaosistema.endereco.valuehandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.valuehandler.BaseCriteria;
import br.com.indrasistemas.framework.service.valuehandler.ValueHandler;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.dao.UnidadeFederativaDAO;

public class BuscaTodasUFVH extends ValueHandler {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BuscaTodasUFVH.class);

	public BuscaTodasUFVH(BaseCriteria criteria) {
		super(criteria);
	}

	protected Object _getValue() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("_getValue() - start");
		}

		UnidadeFederativaDAO dao = new UnidadeFederativaDAO();
		Object returnObject = dao.buscarTodas();

		if (logger.isDebugEnabled()) {
			logger.debug("_getValue() - end");
		}
		return returnObject;
	}

}
