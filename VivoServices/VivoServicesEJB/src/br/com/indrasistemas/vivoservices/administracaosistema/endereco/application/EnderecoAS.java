package br.com.indrasistemas.vivoservices.administracaosistema.endereco.application;

import javax.ejb.SessionContext;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.framework.service.valuehandler.ValueListHandlerException;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.exception.CEPInvalidoException;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.PesquisaEnderecoTO;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.valuehandler.PesquisaEnderecoPagCriteria;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.valuehandler.PesquisaEnderecoVLH;

public class EnderecoAS extends BaseApplicationService {

	public EnderecoAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public ValueList buscarEndereco(PesquisaEnderecoTO filtro, Integer pagina,
			Integer qtdeRegistros) throws ApplicationServiceException,
			BusinessException {
		ValueList result = null;
		int first = 0;
		int max = PesquisaEnderecoTO.RESULTADO_MAXIMO;
		if (filtro.getCep().length() != 8)
			throw new CEPInvalidoException("CEP Inválido.");

		if (pagina != null) {
			first = ((pagina.intValue() - 1) * PesquisaEnderecoTO.RESULTADO_MAXIMO);
		}
		if (qtdeRegistros != null) {
			max = first + qtdeRegistros.intValue();
		}

		if (max - first > PesquisaEnderecoTO.RESULTADO_MAXIMO) {
			max = first + PesquisaEnderecoTO.RESULTADO_MAXIMO;
		}
		try {

			PesquisaEnderecoPagCriteria criteria = new PesquisaEnderecoPagCriteria(
					new Integer(first), new Integer(max));
			criteria.setFiltro(filtro);
			PesquisaEnderecoVLH vlh = new PesquisaEnderecoVLH(criteria);
			vlh.ignoreCache();
			result = vlh.getList();
		} catch (ValueListHandlerException ex) {
			getSessionContext().setRollbackOnly();
			throw new ApplicationServiceException(ex);
		}
		return result;
	}

}
