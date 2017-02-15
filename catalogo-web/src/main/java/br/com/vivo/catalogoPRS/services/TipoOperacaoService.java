package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.TipoOperacaoSoapServiceControl;
import br.com.vivo.sn.catalogoTipoOperacao.BuscarListaTipoOperacaoRequestDocument;
import br.com.vivo.sn.catalogoTipoOperacao.BuscarListaTipoOperacaoResponseDocument;
import br.com.vivo.sn.catalogoTipoOperacao.ResultadoBuscarListaTipoOperacaoDocument.ResultadoBuscarListaTipoOperacao.ListaTipoOperacao.TipoOperacao;
*/
public class TipoOperacaoService extends BaseService {

	private static TipoOperacaoService instance;
	
	public TipoOperacaoService() {}
	
	public static TipoOperacaoService getInstance() {
		if(instance == null) {
			instance = new TipoOperacaoService();
		}
		return instance;
	}
	
/*	@SuppressWarnings("unchecked")
	public List<TipoOperacao> buscarListaTipoOperacao(TipoOperacaoSoapServiceControl tipoOperacaoServiceControl){
		Object cached = getCached("TipoOperacao", "buscarListaTipoOperacao");
		if(cached != null) {
			return (List<TipoOperacao>) cached;
		}
		
		BuscarListaTipoOperacaoRequestDocument requestDocument = BuscarListaTipoOperacaoRequestDocument.Factory.newInstance();
		requestDocument.addNewBuscarListaTipoOperacaoRequest();
		
		BuscarListaTipoOperacaoResponseDocument responseDocument = tipoOperacaoServiceControl.buscarListaTipoOperacao(requestDocument);
		List<TipoOperacao> tipoOperacaoList = responseDocument.getBuscarListaTipoOperacaoResponse().getResultadoBuscarListaTipoOperacao().getListaTipoOperacao().getTipoOperacaoList();
		doCache(tipoOperacaoList, "TipoOperacao", "buscarListaTipoOperacao");
		return tipoOperacaoList;
	}*/
	
}
