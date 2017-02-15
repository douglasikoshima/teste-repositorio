package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.FrequenciaSoapServiceControl;
import br.com.vivo.sn.catalogoFrequencia.BuscarListaTipoFrequenciaRequestDocument;
import br.com.vivo.sn.catalogoFrequencia.BuscarListaTipoFrequenciaResponseDocument;
import br.com.vivo.sn.catalogoFrequencia.BuscarListaValorFrequenciaRequestDocument;
import br.com.vivo.sn.catalogoFrequencia.BuscarListaValorFrequenciaResponseDocument;
import br.com.vivo.sn.catalogoFrequencia.ResultadoBuscarListaTipoFrequenciaDocument.ResultadoBuscarListaTipoFrequencia;
import br.com.vivo.sn.catalogoFrequencia.ResultadoBuscarListaTipoFrequenciaDocument.ResultadoBuscarListaTipoFrequencia.TipoFrequenciaCriacao;
import br.com.vivo.sn.catalogoFrequencia.ResultadoListarValorFrequenciaDocument.ResultadoListarValorFrequencia;
import br.com.vivo.sn.catalogoFrequencia.ResultadoListarValorFrequenciaDocument.ResultadoListarValorFrequencia.Vlfrequencia;
*/
public class FrequenciaService {

	private static FrequenciaService instance;

	private FrequenciaService() {
		// singleton
	}

	public static FrequenciaService getInstance() {
		if (instance == null) {
			instance = new FrequenciaService();
		}
		return instance;
	}

/*	public List<Vlfrequencia> buscarListaValorFrequencia(FrequenciaSoapServiceControl frequenciaSoapServiceControl) {
		BuscarListaValorFrequenciaRequestDocument document = BuscarListaValorFrequenciaRequestDocument.Factory.newInstance();
		document.addNewBuscarListaValorFrequenciaRequest();
		BuscarListaValorFrequenciaResponseDocument buscarListaValorFrequenciaResponseDocument = frequenciaSoapServiceControl
				.buscarListaValorFrequencia(document);
		ResultadoListarValorFrequencia resultadoListarValorFrequencia = buscarListaValorFrequenciaResponseDocument.getBuscarListaValorFrequenciaResponse()
				.getResultadoListarValorFrequencia();
		if (resultadoListarValorFrequencia != null) {
			return resultadoListarValorFrequencia.getVlfrequenciaList();
		}
		return null;
	}
	
	public List<TipoFrequenciaCriacao> buscarListaTipoFrequencia(FrequenciaSoapServiceControl frequenciaSoapServiceControl) {
		
		BuscarListaTipoFrequenciaRequestDocument document = BuscarListaTipoFrequenciaRequestDocument.Factory.newInstance();
		document.addNewBuscarListaTipoFrequenciaRequest();
		
		BuscarListaTipoFrequenciaResponseDocument buscarListaTipoFrequenciaResponseDocument = frequenciaSoapServiceControl.buscarListaTipoFrequencia(document);
		ResultadoBuscarListaTipoFrequencia resultadoBuscarListaTipoFrequencia = buscarListaTipoFrequenciaResponseDocument.getBuscarListaTipoFrequenciaResponse().getResultadoBuscarListaTipoFrequencia();
		if (resultadoBuscarListaTipoFrequencia != null) {
			return resultadoBuscarListaTipoFrequencia.getTipoFrequenciaCriacaoList();
		}
		return null;
	}*/

}
