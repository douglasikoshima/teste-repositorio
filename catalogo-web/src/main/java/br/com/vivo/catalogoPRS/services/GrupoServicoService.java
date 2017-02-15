package br.com.vivo.catalogoPRS.services;

import java.util.ArrayList;
import java.util.List;

/*import br.com.vivo.catalogoPRS.controls.GrupoServicoServiceControl;
import br.com.vivo.catalogoPRS.util.PlataformaEnum;
import br.com.vivo.sn.catalogoGrupoServico.BuscarListaGrupoServicoRequestDocument;
import br.com.vivo.sn.catalogoGrupoServico.BuscarListaGrupoServicoResponseDocument;
import br.com.vivo.sn.catalogoGrupoServico.ParametrosListarGrupoServicoDocument.ParametrosListarGrupoServico;
import br.com.vivo.sn.catalogoGrupoServico.ResultadoListarGrupoServicoDocument.ResultadoListarGrupoServico;
import br.com.vivo.sn.catalogoGrupoServico.ResultadoListarGrupoServicoDocument.ResultadoListarGrupoServico.Categoria;
*/
public class GrupoServicoService extends BaseService {

	private static GrupoServicoService instance;

	private GrupoServicoService() {
		// singleton
	}

	public static GrupoServicoService getInstance() {
		if (instance == null) {
			instance = new GrupoServicoService();
		}
		return instance;
	}

/*	public List<Categoria> buscarListaGrupoServico(GrupoServicoServiceControl grupoServicoSoapServiceControl, Long idPlataforma, Long idSistema) {
		BuscarListaGrupoServicoRequestDocument document = BuscarListaGrupoServicoRequestDocument.Factory.newInstance();
		ParametrosListarGrupoServico parametrosListarGrupoServico = document.addNewBuscarListaGrupoServicoRequest().addNewParametrosListarGrupoServico();
		
		if(PlataformaEnum.PREPAGO.equals(idPlataforma)){
			parametrosListarGrupoServico.setIndCatalogo(new Long(2));
		}else if(PlataformaEnum.POSPAGO.equals(idPlataforma)){
			parametrosListarGrupoServico.setIndCatalogo(new Long(1));
		}else if(PlataformaEnum.CONTROLE.equals(idPlataforma)){
			if(idSistema == null)
				return new ArrayList<Categoria>();
			if(idSistema == 3)
				parametrosListarGrupoServico.setIndCatalogo(new Long(2));
			if(idSistema == 2)
				parametrosListarGrupoServico.setIndCatalogo(new Long(1));
		}

		BuscarListaGrupoServicoResponseDocument responseDocument = grupoServicoSoapServiceControl.buscarListaGrupoServico(document);
		ResultadoListarGrupoServico resultadoListarGrupoServico = responseDocument.getBuscarListaGrupoServicoResponse().getResultadoListarGrupoServico();
		if (resultadoListarGrupoServico == null)
			return new ArrayList<Categoria>();
		else
			return resultadoListarGrupoServico.getCategoriaList();
	}*/
}
