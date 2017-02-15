package br.com.vivo.catalogoPRS.pageflows.param.planoServico.cdAnaliseCredito;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
/*import br.com.vivo.catalogoPRS.services.PlataformaService;
import br.com.vivo.catalogoPRS.services.TipoClienteService;
import br.com.vivo.catalogoPRS.services.UFService;
import br.com.vivo.catalogoPRS.util.WebServicesConfiguration;
import br.com.vivo.sn.catalogoPlataforma.ResultadoBuscarListaPlataformaDocument.ResultadoBuscarListaPlataforma.Plataforma;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaRequestDocument;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaResponseDocument;
import br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaDocument.ResultadoBuscarListaTecnologia.Tecnologia;
import br.com.vivo.sn.catalogoTipoCliente.ResultadoBuscarListaTipoClienteDocument.ResultadoBuscarListaTipoCliente.TipoCliente;
import br.com.vivo.sn.catalogoUF.ResultadoBuscarListaUFDocument.ResultadoBuscarListaUF.UF;
*/
public class AtribuicaoCodigoAnaliseCreditoController extends BaseMappingDispatchAction {
/*	private static final long serialVersionUID = 1L;
	private String[] ufsSelecionados;
	private String[] dddsSelecionados;
	
	@Control
	private PlataformaSoapServiceControl plataformaSoapServiceControl;
	
	@Control
	private TipoClienteSoapServiceControl tipoClienteSoapServiceControl;

	@Control
	private TecnologiaSoapServiceControl tecnologiaSoapServiceControl;
	
	@Control
	private UFSoapServiceControl ufSoapServiceControl;
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "atribuicaoCodigoAnaliseCredito.jsp") })
	public Forward begin() {
		
		// Preencher o Combo de Plataformas
		prepareServiceControl(plataformaSoapServiceControl);
		List<Plataforma> plataformaLista = PlataformaService.getInstance().buscarListaPlataforma(plataformaSoapServiceControl);
		this.getRequest().setAttribute("plataformas", plataformaLista);
		
		// Preencher o Combo de Tipo de Clientes
		prepareServiceControl(tipoClienteSoapServiceControl);
		List<TipoCliente> tipoClienteLista = TipoClienteService.getInstance().buscarListaTipoCliente(tipoClienteSoapServiceControl);
		this.getRequest().setAttribute("tipoCliente", tipoClienteLista);
		
		// Preencher o Combo de Tecnologias
		BuscarListaTecnologiaRequestDocument tecnologiaRequestDocument = BuscarListaTecnologiaRequestDocument.Factory.newInstance();
		tecnologiaRequestDocument.addNewBuscarListaTecnologiaRequest();
		prepareServiceControl(tecnologiaSoapServiceControl);
		BuscarListaTecnologiaResponseDocument tecnologiaResponseDocument = tecnologiaSoapServiceControl.buscarListaTecnologia(tecnologiaRequestDocument);
		List<Tecnologia> tecnologiaLista = tecnologiaResponseDocument.getBuscarListaTecnologiaResponse().getResultadoBuscarListaTecnologia().getTecnologiaList();
		this.getRequest().setAttribute("tecnologias", tecnologiaLista);
		
		List<Serasa> serasaLista = new ArrayList<Serasa>();
		for(int i = 1; i <= 10; i++) {
			Serasa serasa = new Serasa();
			serasa.setCdSerasa(i);
			serasaLista.add(serasa);
		}
		this.getRequest().setAttribute("serasaLista", serasaLista);

		return new Forward("success");
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaCodigoAnaliseCredito.jsp") })
	public Forward pesquisarCodigoAnaliseCredito(br.com.vivo.catalogoPRS.pageflows.param.planoServico.cdAnaliseCredito.AtribuicaoCodigoAnaliseCreditoController.CodigoAnaliseCreditoFormBean form) {
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupUnidadeFederal.jsp") })
	public Forward listarUnidadesFederativa() {
		String ufsJSon = getRequest().getParameter("hidden_ufs");
		if (ufsJSon != null) {
			List<String> chaves = new ArrayList<String>();
			List<String> valores = new ArrayList<String>();
			extrairJSONChaveValor(ufsJSon, chaves, valores );
			this.ufsSelecionados = chaves.toArray(new String[]{});
			this.dddsSelecionados = valores.toArray(new String[]{});
		}
		prepareServiceControl(ufSoapServiceControl);
		List<UF> ufs = UFService.getInstance().buscarListaUFComDDD(ufSoapServiceControl);
		this.getRequest().setAttribute("ufs", ufs);
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Override
	protected void onCreate() {
		plataformaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.PLATAFORMA));
		tipoClienteSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TIPO_CLIENTE));
		tecnologiaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TECNOLOGIA));
		ufSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.UF));
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}

	@Jpf.FormBean
	public static class CodigoAnaliseCreditoFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		private Long paginaSolicitada;
		private Long idPlataforma;
		private Long idTipoCliente;
		private Long idTecnologia;
		private Long cdSerasa;
		private String nmComercial;
		private String nmTecnico;
		private String idsUF;
		
		public Long getIdPlataforma() {
			return idPlataforma;
		}
		public void setIdPlataforma(Long idPlataforma) {
			this.idPlataforma = idPlataforma;
		}
		
		public Long getPaginaSolicitada() {
			return paginaSolicitada;
		}
		public void setPaginaSolicitada(Long paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}
		
		public String getNmComercial() {
			return nmComercial;
		}
		public void setNmComercial(String nmComercial) {
			this.nmComercial = nmComercial;
		}
		public String getNmTecnico() {
			return nmTecnico;
		}
		public void setNmTecnico(String nmTecnico) {
			this.nmTecnico = nmTecnico;
		}
		public Long getIdTipoCliente() {
			return idTipoCliente;
		}
		public void setIdTipoCliente(Long idTipoCliente) {
			this.idTipoCliente = idTipoCliente;
		}
		public Long getIdTecnologia() {
			return idTecnologia;
		}
		public void setIdTecnologia(Long idTecnologia) {
			this.idTecnologia = idTecnologia;
		}
		public String getIdsUF() {
			return idsUF;
		}
		public void setIdsUF(String idsUF) {
			this.idsUF = idsUF;
		}
		public Long getCdSerasa() {
			return cdSerasa;
		}
		public void setCdSerasa(Long cdSerasa) {
			this.cdSerasa = cdSerasa;
		}
		
	
	}
	
	public String[] getDddsSelecionados() {
		return dddsSelecionados;
	}
	public void setDddsSelecionados(String[] dddsSelecionados) {
		this.dddsSelecionados = dddsSelecionados;
	}
	public String[] getUfsSelecionados() {
		return ufsSelecionados;
	}
	public void setUfsSelecionados(String[] ufsSelecionados) {
		this.ufsSelecionados = ufsSelecionados;
	}
	
	public class Serasa {
		private Integer cdSerasa;
		public Integer getCdSerasa() {
			return cdSerasa;
		}
		public void setCdSerasa(Integer cdSerasa) {
			this.cdSerasa = cdSerasa;
		}
		
	}*/

}