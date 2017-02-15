package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.TipoProdutoSoapServiceControl;
import br.com.vivo.sn.catalogoTipoProduto.BuscarListaTipoProdutoRequestDocument;
import br.com.vivo.sn.catalogoTipoProduto.ResultadoListarTipoProdutoDocument.ResultadoListarTipoProduto.TipoProduto;
*/
public class TipoProdutoService extends BaseService {

	private static TipoProdutoService instance;

	private TipoProdutoService() {
		// singleton
	}

	public static TipoProdutoService getInstance() {
		if (instance == null) {
			instance = new TipoProdutoService();
		}
		return instance;
	}

/*	@SuppressWarnings("unchecked")
	public List<TipoProduto> buscarListaTipoProduto(TipoProdutoSoapServiceControl tipoProdutoSoapServiceControl){
		
		Object cached = getCached("TipoProduto", "buscarListaTipoProduto");
		if(cached != null )
			return (List<TipoProduto>) cached;
		BuscarListaTipoProdutoRequestDocument buscarListaTipoProdutoRequestDocument = BuscarListaTipoProdutoRequestDocument.Factory.newInstance();
		buscarListaTipoProdutoRequestDocument.addNewBuscarListaTipoProdutoRequest();
		
		List<TipoProduto> toCache = tipoProdutoSoapServiceControl.buscarListaTipoProduto(buscarListaTipoProdutoRequestDocument).getBuscarListaTipoProdutoResponse().getResultadoListarTipoProduto().getTipoProdutoList();
		doCache(toCache, "TipoProduto", "buscarListaTipoProduto");
		return toCache;
	}*/
}
