package br.com.indrasistemas.vivoservices.atendimento.backoffice.application;

import javax.ejb.SessionContext;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.framework.service.valuehandler.ValueListHandlerException;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.exception.CNPJNaoInformadoException;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.to.ComprovanteCancelamentoTO;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.valuehandler.ComprovanteCancelamentoPagCriteria;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.valuehandler.ComprovanteCancelamentoVLH;

public class ComprovanteCancelamentoAS extends BaseApplicationService {

	public ComprovanteCancelamentoAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public ValueList buscarComprovanteCancelamento(
			ComprovanteCancelamentoTO filtro, Integer pagina,
			Integer qtdeRegistros) throws ApplicationServiceException,
			BusinessException {
		ValueList result = null;
		int first = 0;
		int max = ComprovanteCancelamentoTO.RESULTADO_MAXIMO;
		if (filtro.getNrDocumento().length() == 0) {
			throw new CNPJNaoInformadoException(
					"Número do CNPJ não informado.");
		}

		if (pagina != null) {
			first = ((pagina.intValue() - 1) * ComprovanteCancelamentoTO.RESULTADO_MAXIMO);
		}
		if (qtdeRegistros != null) {
			max = first + qtdeRegistros.intValue();
		}

		if (max - first > ComprovanteCancelamentoTO.RESULTADO_MAXIMO) {
			max = first + ComprovanteCancelamentoTO.RESULTADO_MAXIMO;
		}
		try {

			ComprovanteCancelamentoPagCriteria criteria = new ComprovanteCancelamentoPagCriteria(
					new Integer(first), new Integer(max));
			criteria.setFiltro(filtro);
			ComprovanteCancelamentoVLH vlh = new ComprovanteCancelamentoVLH(
					criteria);
			vlh.ignoreCache();
			result = vlh.getList();
		} catch (ValueListHandlerException ex) {
			getSessionContext().setRollbackOnly();
			throw new ApplicationServiceException(ex);
		}
		return result;
	}

}
