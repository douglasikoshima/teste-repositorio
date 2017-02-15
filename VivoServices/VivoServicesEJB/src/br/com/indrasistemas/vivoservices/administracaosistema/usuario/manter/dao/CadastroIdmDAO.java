package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.ParametrosTO;
import br.com.indrasistemas.vivoservices.portabilidade.status.to.RespostaTO;

public class CadastroIdmDAO extends HibernateBaseDAO {

	private static final Log logger = LogFactory.getLog(CadastroIdmDAO.class);

	public RespostaTO cadastrar(RequestInfoTO requestInfo, ParametrosTO parametros) throws DAOException {

		RespostaTO respostaTO = new RespostaTO();

		return respostaTO;
	}
}