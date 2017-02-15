package br.com.indrasistemas.vivoservices.administracaosistema.endereco.valuehandler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.valuehandler.PaginatedCriteria;
import br.com.indrasistemas.framework.service.valuehandler.ValueListHandler;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.dao.EnderecoDAO;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.PesquisaEnderecoTO;

public class PesquisaEnderecoVLH extends ValueListHandler {

	private static final Log logger = LogFactory.getLog(PesquisaEnderecoVLH.class);
	
	public PesquisaEnderecoVLH(PaginatedCriteria criteria) {
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

		EnderecoDAO enderecoDAO = new EnderecoDAO();
		List result = null;

		PesquisaEnderecoTO filtro = ((PesquisaEnderecoPagCriteria) this.getCriteria()).getFiltro();
		Integer first = this.getCriteria().getFirstResult();
		Integer total = this.getCriteria().getMaxResults();

		result = enderecoDAO.buscarEndereco(filtro, first, total);

		return result;
	}

}
