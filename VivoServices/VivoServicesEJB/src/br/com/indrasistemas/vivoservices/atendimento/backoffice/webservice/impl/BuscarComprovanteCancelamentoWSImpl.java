/**
 * 
 */
package br.com.indrasistemas.vivoservices.atendimento.backoffice.webservice.impl;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.delegate.ComprovanteCancelamentoBD;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.to.ComprovanteCancelamentoTO;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.webservice.BuscarComprovanteCancelamentoWS;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.webservice.to.ResultadoComprovanteCancelamentoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

/**
 * @author a5013566
 * 
 */
public class BuscarComprovanteCancelamentoWSImpl implements BuscarComprovanteCancelamentoWS {

	/**
	 * 
	 */
	public BuscarComprovanteCancelamentoWSImpl() {

	}

	public ResultadoComprovanteCancelamentoTO buscarComprovanteCancelamentoPorDoc(RequestInfoWSTO requestInfo, String nrDocumento, Integer pagina, Integer qtdeRegistros) {
		ResultadoComprovanteCancelamentoTO to = new ResultadoComprovanteCancelamentoTO();

		try {

			ComprovanteCancelamentoTO toPesquisa = new ComprovanteCancelamentoTO();
			toPesquisa.setNrDocumento(nrDocumento);

			ComprovanteCancelamentoBD bd = new ComprovanteCancelamentoBD();
			ValueList listaComprovanteCancelamento = bd.buscarComprovanteCancelamento(requestInfo, toPesquisa, pagina, qtdeRegistros);

			to.setStatus(RespostaWSTO.OK);
			to.setComprovanteCancelamento(listaComprovanteCancelamento.getList());

		} catch (BusinessDelegateException e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setReason(e.getMessage());

		} catch (BusinessException e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setReason(e.getMessage());
		}

		return to;
	}

}
