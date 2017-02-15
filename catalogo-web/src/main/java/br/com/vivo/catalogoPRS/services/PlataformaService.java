package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.PlataformaSoapServiceControl;
import br.com.vivo.sn.catalogoPlataforma.BuscarListaPlataformaRequestDocument;
import br.com.vivo.sn.catalogoPlataforma.ResultadoBuscarListaPlataformaDocument.ResultadoBuscarListaPlataforma.Plataforma;
*/
public class PlataformaService extends BaseService {

	private static PlataformaService instance;

	private PlataformaService() {
		// singleton
	}

	public static PlataformaService getInstance() {
		if (instance == null) {
			instance = new PlataformaService();
		}
		return instance;
	}

/*	@SuppressWarnings("unchecked")
	public List<Plataforma> buscarListaPlataforma(PlataformaSoapServiceControl plataformaSoapServiceControl){
		Object cached = getCached("Plataforma", "buscarListaPlataforma");
		if(cached != null )
			return (List<Plataforma>) cached;
		
		BuscarListaPlataformaRequestDocument plataformaRequestDocument = BuscarListaPlataformaRequestDocument.Factory.newInstance();
		plataformaRequestDocument.addNewBuscarListaPlataformaRequest();
		
		List<Plataforma> toCache = plataformaSoapServiceControl.buscarListaPlataforma(plataformaRequestDocument).getBuscarListaPlataformaResponse().getResultadoBuscarListaPlataforma().getPlataformaList();
		doCache(toCache, "Plataforma", "buscarListaPlataforma");
		return toCache;
	}*/
}
