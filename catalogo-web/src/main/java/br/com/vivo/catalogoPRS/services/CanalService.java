package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.CanalServiceControl;
import br.com.vivo.sn.catalogoCanal.BuscarListaCanalRequestDocument;
import br.com.vivo.sn.catalogoCanal.ResultadoListaCanalDocument.ResultadoListaCanal.Canal;
*/
public class CanalService extends BaseService {

	private static CanalService instance;

	private CanalService() {
		// singleton
	}

	public static CanalService getInstance() {
		if (instance == null) {
			instance = new CanalService();
		}
		return instance;
	}

/*	@SuppressWarnings("unchecked")
	public List<Canal> buscarListaCanal(CanalServiceControl canalSoapServiceControl){
		Object cached = getCached("Canal", "buscarListaCanal");
		if(cached != null )
			return (List<Canal>) cached;
		
		BuscarListaCanalRequestDocument canalRequestDocument = BuscarListaCanalRequestDocument.Factory.newInstance();
		canalRequestDocument.addNewBuscarListaCanalRequest();
		
		List<Canal> toCache = canalSoapServiceControl.buscarListaCanal(canalRequestDocument).getBuscarListaCanalResponse().getResultadoListaCanal().getCanalList();
		doCache(toCache, "Canal", "buscarListaCanal");
		return toCache;
	}*/
}
