package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.FormaPagamentoSoapServiceControl;
import br.com.vivo.sn.catalogoFormaPagamento.BuscarListaFormaPagamentoRequestDocument;
import br.com.vivo.sn.catalogoFormaPagamento.ResultadoBuscarListaFormaCondPagtoDocument.ResultadoBuscarListaFormaCondPagto;
import br.com.vivo.sn.catalogoFormaPagamento.ResultadoBuscarListaFormaCondPagtoDocument.ResultadoBuscarListaFormaCondPagto.FormaPagamento;
*/
public class FormaPagamentoService extends BaseService {

	private static FormaPagamentoService instance;

	private FormaPagamentoService() {
		// singleton
	}

	public static FormaPagamentoService getInstance() {
		if (instance == null) {
			instance = new FormaPagamentoService();
		}
		return instance;
	}

/*	@SuppressWarnings("unchecked")
	public List<FormaPagamento> buscarListaFormaPagamento(FormaPagamentoSoapServiceControl formaPagamentoSoapServiceControl){
		Object cached = getCached("FormaPagamento", "buscarListaFormaPagamento");
		if(cached != null )
			return (List<FormaPagamento>) cached;
		
		BuscarListaFormaPagamentoRequestDocument requestDocument = BuscarListaFormaPagamentoRequestDocument.Factory.newInstance();
		requestDocument.addNewBuscarListaFormaPagamentoRequest();
		
		ResultadoBuscarListaFormaCondPagto resultadoBuscarListaFormaCondPagto = formaPagamentoSoapServiceControl.buscarListaFormaPagamento(requestDocument).getBuscarListaFormaPagamentoResponse().getResultadoBuscarListaFormaCondPagto();
		if(resultadoBuscarListaFormaCondPagto != null){
			List<FormaPagamento> toCache = resultadoBuscarListaFormaCondPagto.getFormaPagamentoList();
			doCache(toCache, "FormaPagamento", "buscarListaFormaPagamento");
			return toCache;
		}
		return null;
	}*/
}
