package br.com.vivo.catalogoPRS.services;

import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.FabricanteServiceControl;
import br.com.vivo.sn.catalogoFabricante.BuscarListaFabricantePorTipoProdutoRequestDocument;
import br.com.vivo.sn.catalogoFabricante.BuscarListaFabricantePorTipoProdutoResponseDocument;
import br.com.vivo.sn.catalogoFabricante.BuscarListaFabricantePorTipoProdutoRequestDocument.BuscarListaFabricantePorTipoProdutoRequest;
import br.com.vivo.sn.catalogoFabricante.ParametrosBuscarListaFabricantePorTipoProdutoDocument.ParametrosBuscarListaFabricantePorTipoProduto;
import br.com.vivo.sn.catalogoFabricante.ResultadoBuscarListaFabricantePorTipoProdutoDocument.ResultadoBuscarListaFabricantePorTipoProduto;
import br.com.vivo.sn.catalogoFabricante.ResultadoBuscarListaFabricantePorTipoProdutoDocument.ResultadoBuscarListaFabricantePorTipoProduto.Fabricante;
*/
public class FabricanteService extends BaseService {

	private static FabricanteService instance;

	private FabricanteService() {
		// singleton
	}

	public static FabricanteService getInstance() {
		if (instance == null) {
			instance = new FabricanteService();
		}
		return instance;
	}

/*	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarListaFabricantePorTipoProduto(FabricanteServiceControl fabricanteSoapServiceControl, Long idTipoProduto){
		Object cached = getCached("Fabricante", "buscarListaFabricantePorTipoProduto"+idTipoProduto);
		if(cached != null )
			return (List<Fabricante>) cached;
		
		BuscarListaFabricantePorTipoProdutoRequestDocument fabricantePorTipoProdutoRequestDocument = BuscarListaFabricantePorTipoProdutoRequestDocument.Factory.newInstance();
		BuscarListaFabricantePorTipoProdutoRequest listaFabricantePorTipoProdutoRequest = fabricantePorTipoProdutoRequestDocument.addNewBuscarListaFabricantePorTipoProdutoRequest();
		ParametrosBuscarListaFabricantePorTipoProduto buscarListaFabricantePorTipoProduto = listaFabricantePorTipoProdutoRequest.addNewParametrosBuscarListaFabricantePorTipoProduto();
		buscarListaFabricantePorTipoProduto.setIdTipoProduto(idTipoProduto);
		
		BuscarListaFabricantePorTipoProdutoResponseDocument fabricantePorTipoProdutoResponseDocument = fabricanteSoapServiceControl.buscarListaFabricantePorTipoProduto(fabricantePorTipoProdutoRequestDocument);
		ResultadoBuscarListaFabricantePorTipoProduto resultadoBuscarListaFabricantePorTipoProduto = fabricantePorTipoProdutoResponseDocument.getBuscarListaFabricantePorTipoProdutoResponse().getResultadoBuscarListaFabricantePorTipoProduto();
		if(resultadoBuscarListaFabricantePorTipoProduto != null && resultadoBuscarListaFabricantePorTipoProduto.getFabricanteList().size() > 0){
			List<Fabricante> toCache = resultadoBuscarListaFabricantePorTipoProduto.getFabricanteList();
			doCache(toCache, "Fabricante", "buscarListaFabricantePorTipoProduto"+idTipoProduto);
			return toCache;
		}
		return null;
	}*/
}
