package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.MatrizOfertaServiceControl;
import br.com.vivo.sn.catalogoMatrizOferta.BuscarListaTipoMatrizRequestDocument;
import br.com.vivo.sn.catalogoMatrizOferta.BuscarListaTipoMatrizResponseDocument;
import br.com.vivo.sn.catalogoMatrizOferta.ResultadoBuscarListaTipoMatrizDocument.ResultadoBuscarListaTipoMatriz.ResultadoListaTipoMatriz.TipoMatriz;
*/
public class TipoMatrizService extends BaseService {
	
	private static TipoMatrizService instance;
	
	public TipoMatrizService() {}
	
	public static TipoMatrizService getInstance() {
		if(instance == null ) {
			instance = new TipoMatrizService();
		}
		return instance;
	}
	
/*	@SuppressWarnings("unchecked")
	public List<TipoMatriz> buscarListaTipoMatriz(MatrizOfertaServiceControl matrizOfertaServiceControl){
		Object cached = getCached("TipoMatriz", "buscarListaTipoMatriz");
		if(cached != null) {
			return (List<TipoMatriz>) cached;
		}
		
		BuscarListaTipoMatrizRequestDocument requestDocument = BuscarListaTipoMatrizRequestDocument.Factory.newInstance();
		requestDocument.addNewBuscarListaTipoMatrizRequest();
		
		BuscarListaTipoMatrizResponseDocument responseDocument = matrizOfertaServiceControl.buscarListaTipoMatriz(requestDocument);
		List<TipoMatriz> tipoMatrizList = responseDocument.getBuscarListaTipoMatrizResponse().getResultadoBuscarListaTipoMatriz().getResultadoListaTipoMatriz().getTipoMatrizList();
		doCache(tipoMatrizList, "TipoMatriz", "buscarListaTipoMatriz");
		return tipoMatrizList;
	}*/
}
