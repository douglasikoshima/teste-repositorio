package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.TecnologiaSoapServiceControl;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaRequestDocument;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaResponseDocument;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaSimplesRequestDocument;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaSimplesResponseDocument;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaTipoFrequenciaRequestDocument;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaTipoFrequenciaResponseDocument;
import br.com.vivo.sn.catalogoTecnologia.ListaTecnologiaDocument.ListaTecnologia;
import br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaDocument.ResultadoBuscarListaTecnologia;
import br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaDocument.ResultadoBuscarListaTecnologia.Tecnologia;
import br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaSimplesDocument.ResultadoBuscarListaTecnologiaSimples;
*/
public class TecnologiaService extends BaseService {

	private static TecnologiaService instance;

	private TecnologiaService() {
		// singleton
	}

	public static TecnologiaService getInstance() {
		if (instance == null) {
			instance = new TecnologiaService();
		}
		return instance;
	}
	
/*	public List<Tecnologia> buscarListaTecnologia(TecnologiaSoapServiceControl tecnologiaSoapServiceControl, boolean resetCache) {
		if (resetCache){
			resetCache("Tecnologia", "buscarListaTecnologia");
		}
		return buscarListaTecnologia(tecnologiaSoapServiceControl);
	}
	
	public List<br.com.vivo.sn.catalogoTecnologia.ListaTecnologiaDocument.ListaTecnologia.Tecnologia> buscarListaTecnologiaTipoFrequencia(TecnologiaSoapServiceControl tecnologiaSoapServiceControl, boolean resetCache) {
		if (resetCache){
			resetCache("Tecnologia", "buscarListaTecnologiaTipoFrequencia");
		}
		return  buscarListaTecnologiaTipoFrequencia(tecnologiaSoapServiceControl);
	}
	
	@SuppressWarnings("unchecked")
	public List<br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaSimplesDocument.ResultadoBuscarListaTecnologiaSimples.Tecnologia> buscarListaTecnologiaSimples(TecnologiaSoapServiceControl tecnologiaSoapServiceControl, boolean resetCache) {
		
		if (resetCache){
			resetCache("Tecnologia", "buscarListaTecnologiaSimples");
		}
		Object cached = getCached("Tecnologia", "buscarListaTecnologiaSimples");
		if(cached != null )
			return (List<br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaSimplesDocument.ResultadoBuscarListaTecnologiaSimples.Tecnologia>) cached;
		
		BuscarListaTecnologiaSimplesRequestDocument document = BuscarListaTecnologiaSimplesRequestDocument.Factory.newInstance();
		document.addNewBuscarListaTecnologiaSimplesRequest();
		BuscarListaTecnologiaSimplesResponseDocument buscarListaTecnologiaSimplesResponseDocument = tecnologiaSoapServiceControl.buscarListaTecnologiaSimples(document);
		ResultadoBuscarListaTecnologiaSimples resultadoBuscarListaTecnologia = buscarListaTecnologiaSimplesResponseDocument.getBuscarListaTecnologiaSimplesResponse().getResultadoBuscarListaTecnologiaSimples();

		if (resultadoBuscarListaTecnologia != null) {
			List<br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaSimplesDocument.ResultadoBuscarListaTecnologiaSimples.Tecnologia> toCache = resultadoBuscarListaTecnologia.getTecnologiaList();
			doCache(toCache, "Tecnologia", "buscarListaTecnologiaSimples");
			return toCache;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Tecnologia> buscarListaTecnologia(TecnologiaSoapServiceControl tecnologiaSoapServiceControl) {
		
		Object cached = getCached("Tecnologia", "buscarListaTecnologia");
		if(cached != null )
			return (List<Tecnologia>) cached;
		
		BuscarListaTecnologiaRequestDocument document = BuscarListaTecnologiaRequestDocument.Factory.newInstance();
		document.addNewBuscarListaTecnologiaRequest();
		BuscarListaTecnologiaResponseDocument buscarListaTecnologiaResponseDocument = tecnologiaSoapServiceControl.buscarListaTecnologia(document);
		ResultadoBuscarListaTecnologia resultadoBuscarListaTecnologia = buscarListaTecnologiaResponseDocument.getBuscarListaTecnologiaResponse().getResultadoBuscarListaTecnologia();
		if (resultadoBuscarListaTecnologia != null) {
			List<Tecnologia> toCache =  resultadoBuscarListaTecnologia.getTecnologiaList();
			doCache(toCache, "Tecnologia", "buscarListaTecnologia");
			return toCache;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<br.com.vivo.sn.catalogoTecnologia.ListaTecnologiaDocument.ListaTecnologia.Tecnologia> buscarListaTecnologiaTipoFrequencia(TecnologiaSoapServiceControl tecnologiaSoapServiceControl) {
		
		Object cached = getCached("Tecnologia", "buscarListaTecnologiaTipoFrequencia");
		if(cached != null )
			return (List<br.com.vivo.sn.catalogoTecnologia.ListaTecnologiaDocument.ListaTecnologia.Tecnologia>) cached;
		
		BuscarListaTecnologiaTipoFrequenciaRequestDocument document = BuscarListaTecnologiaTipoFrequenciaRequestDocument.Factory.newInstance();
		document.addNewBuscarListaTecnologiaTipoFrequenciaRequest();
		BuscarListaTecnologiaTipoFrequenciaResponseDocument buscarListaTecnologiaTipoFrequenciaResponseDocument = tecnologiaSoapServiceControl.buscarListaTecnologiaTipoFrequencia(document);
		ListaTecnologia listaTecnologia = buscarListaTecnologiaTipoFrequenciaResponseDocument.getBuscarListaTecnologiaTipoFrequenciaResponse().getResultadoBuscarListaTecnologiaTipoFrequencia().getListaTecnologia();
		if (listaTecnologia != null) {
			List<br.com.vivo.sn.catalogoTecnologia.ListaTecnologiaDocument.ListaTecnologia.Tecnologia> toCache = listaTecnologia.getTecnologiaList();
			doCache(toCache, "Tecnologia", "buscarListaTecnologiaTipoFrequencia");
			return toCache;
		}
		return null;
	}*/
}
