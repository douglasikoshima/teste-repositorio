package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.UFSoapServiceControl;
import br.com.vivo.sn.catalogoUF.BuscarListaUFComDDDRequestDocument;
import br.com.vivo.sn.catalogoUF.BuscarListaUFComDDDResponseDocument;
import br.com.vivo.sn.catalogoUF.BuscarListaUFRequestDocument;
import br.com.vivo.sn.catalogoUF.ResultadoBuscarListaUFDocument.ResultadoBuscarListaUF.UF;
*/
public class UFService extends BaseService {

	private static UFService instance;

	private UFService() {
		// singleton
	}

	public static UFService getInstance() {
		if (instance == null) {
			instance = new UFService();
		}
		return instance;
	}

/*	@SuppressWarnings("unchecked")
	public List<UF> buscarListaUF(UFSoapServiceControl ufSoapServiceControl){
		Object cached = getCached("UF", "buscarListaUF");
		if(cached != null )
			return (List<UF>) cached;
		
		BuscarListaUFRequestDocument listaUFRequestDocument = BuscarListaUFRequestDocument.Factory.newInstance();
		listaUFRequestDocument.addNewBuscarListaUFRequest();
		
		List<UF> toCache = ufSoapServiceControl.buscarListaUF(listaUFRequestDocument).getBuscarListaUFResponse().getResultadoBuscarListaUF().getUFList();
		doCache(toCache, "UF", "buscarListaUF");
		return toCache;
	}
	
	@SuppressWarnings("unchecked")
	public List<UF> buscarListaUFComDDD(UFSoapServiceControl ufSoapServiceControl){
		Object cached = getCached("UF", "buscarListaUFComDDD");
		if(cached != null )
			return (List<UF>) cached;
		
		BuscarListaUFComDDDRequestDocument listaUFComDDDRequestDocument = BuscarListaUFComDDDRequestDocument.Factory.newInstance();
		listaUFComDDDRequestDocument.addNewBuscarListaUFComDDDRequest();
		
		BuscarListaUFComDDDResponseDocument listaUFComDDDResponseDocument = ufSoapServiceControl.buscarListaUFComDDD(listaUFComDDDRequestDocument);
		List<UF> toCache = listaUFComDDDResponseDocument.getBuscarListaUFComDDDResponse().getResultadoBuscarListaUF().getUFList();
		doCache(toCache, "UF", "buscarListaUFComDDD");
		return toCache;
	}*/

}
