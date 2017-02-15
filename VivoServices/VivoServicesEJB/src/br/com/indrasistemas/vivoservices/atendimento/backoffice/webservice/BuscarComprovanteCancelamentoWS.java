package br.com.indrasistemas.vivoservices.atendimento.backoffice.webservice;

import br.com.indrasistemas.vivoservices.atendimento.backoffice.webservice.to.ResultadoComprovanteCancelamentoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;

/**
 * 
 */
public interface BuscarComprovanteCancelamentoWS {

	public ResultadoComprovanteCancelamentoTO buscarComprovanteCancelamentoPorDoc(RequestInfoWSTO requestInfo, String nrDocumento, Integer pagina, Integer qtdeRegistros);

}
