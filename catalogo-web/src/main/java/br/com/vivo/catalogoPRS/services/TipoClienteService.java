package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.TipoClienteSoapServiceControl;
import br.com.vivo.sn.catalogoTipoCliente.BuscarListaTipoClienteRequestDocument;
import br.com.vivo.sn.catalogoTipoCliente.ResultadoBuscarListaTipoClienteDocument.ResultadoBuscarListaTipoCliente.TipoCliente;
*/
public class TipoClienteService extends BaseService {

	private static TipoClienteService instance;

	private TipoClienteService() {
		// singleton
	}

	public static TipoClienteService getInstance() {
		if (instance == null) {
			instance = new TipoClienteService();
		}
		return instance;
	}

/*	@SuppressWarnings("unchecked")
	public List<TipoCliente> buscarListaTipoCliente(TipoClienteSoapServiceControl tipoClienteSoapServiceControl){
		Object cached = getCached("TipoCliente", "buscarListaTipoCliente");
		if(cached != null )
			return (List<TipoCliente>) cached;
		
		BuscarListaTipoClienteRequestDocument buscarListaTipoClienteRequestDocument = BuscarListaTipoClienteRequestDocument.Factory.newInstance();
		buscarListaTipoClienteRequestDocument.addNewBuscarListaTipoClienteRequest();
		
		List<TipoCliente> toCache = tipoClienteSoapServiceControl.buscarListaTipoCliente(buscarListaTipoClienteRequestDocument).getBuscarListaTipoClienteResponse().getResultadoBuscarListaTipoCliente().getTipoClienteList();
		doCache(toCache, "TipoCliente", "buscarListaTipoCliente");
		return toCache;
	}*/
}
