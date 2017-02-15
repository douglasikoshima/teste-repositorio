package br.com.vivo.catalogoPRS.pageflows.param.produtos.variaveisModelo;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.services.TecnologiaService;
import br.com.vivo.catalogoPRS.services.TipoProdutoService;
import br.com.vivo.catalogoPRS.util.Variaveis;
import br.com.vivo.catalogoPRS.util.WebServicesConfiguration;
/*import br.com.vivo.sn.catalogoCanal.BuscarListaCanalRequestDocument;
import br.com.vivo.sn.catalogoCanal.BuscarListaCanalResponseDocument;
import br.com.vivo.sn.catalogoCanal.ResultadoListaCanalDocument.ResultadoListaCanal.Canal;
import br.com.vivo.sn.catalogoCarteira.BuscarListaCarteiraRequestDocument;
import br.com.vivo.sn.catalogoCarteira.BuscarListaCarteiraResponseDocument;
import br.com.vivo.sn.catalogoCarteira.ResultadoBuscarListaCarteiraDocument.ResultadoBuscarListaCarteira.ListaCarteira.Carteira;
import br.com.vivo.sn.catalogoSegmento.BuscarListaSegmentacaoRequestDocument;
import br.com.vivo.sn.catalogoSegmento.BuscarListaSegmentacaoResponseDocument;
import br.com.vivo.sn.catalogoSegmento.ResultadoBuscarListaSegmentoDocument.ResultadoBuscarListaSegmento.ListaSegmento.Segmento;
import br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaDocument.ResultadoBuscarListaTecnologia.Tecnologia;
import br.com.vivo.sn.catalogoTipoCliente.BuscarListaTipoClienteRequestDocument;
import br.com.vivo.sn.catalogoTipoCliente.BuscarListaTipoClienteResponseDocument;
import br.com.vivo.sn.catalogoTipoCliente.ResultadoBuscarListaTipoClienteDocument.ResultadoBuscarListaTipoCliente.TipoCliente;
import br.com.vivo.sn.catalogoTipoProduto.ResultadoListarTipoProdutoDocument.ResultadoListarTipoProduto.TipoProduto;
import br.com.vivo.sn.catalogoUF.BuscarListaUFRequestDocument;
import br.com.vivo.sn.catalogoUF.BuscarListaUFResponseDocument;
import br.com.vivo.sn.catalogoUF.ResultadoBuscarListaUFDocument.ResultadoBuscarListaUF.UF;
*/

public class AssociarVariaveisModeloController extends BaseMappingDispatchAction {
/*	private static final long serialVersionUID = 1L;
	private String[] tecnologiasSelecionadas;
	private String[] tipoClienteSelecionados;
	private String[] carteirasSelecionadas;
	private String[] segmentosSelecionadas;
	private String[] canaisSelecionadas;
	private String[] ufsSelecionadas;
	
	@Control
	private TipoProdutoSoapServiceControl tipoProdutoSoapServiceControl;
	
	@Control
	private TecnologiaSoapServiceControl tecnologiaSoapServiceControl;
	
	@Control
	private TipoClienteSoapServiceControl tipoClienteSoapServiceControl;
	
	@Control
	private CarteiraServiceControl carteiraServiceControl;
	
	@Control
	private SegmentoServiceControl segmentoServiceControl;
	
	@Control
	private CanalServiceControl canalServiceControl;
	
	@Control
	private UFSoapServiceControl UFSoapServiceControl;
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "associarVariaveisModelo.jsp") })
	public Forward begin(){
		HttpServletRequest request = this.getRequest();
		prepareServiceControl(tipoProdutoSoapServiceControl);
		List<TipoProduto> tipoProdutoLista = TipoProdutoService.getInstance().buscarListaTipoProduto(tipoProdutoSoapServiceControl);
		request.setAttribute("tipoProduto", tipoProdutoLista);
		
		return new Forward("success");
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupTecnologias.jsp") })
	public Forward listarTecnologias() {

		String tecnologiasCSV = getRequest().getParameter("hiddenTecnologias");
		if (tecnologiasCSV != null) {
			this.setTecnologiasSelecionadas(tecnologiasCSV.split(","));
		}
		prepareServiceControl(tecnologiaSoapServiceControl);
		List<Tecnologia> tecnologiaList = TecnologiaService.getInstance().buscarListaTecnologia(tecnologiaSoapServiceControl);
		HttpServletRequest request = getRequest();
		request.setAttribute("tecnologias", tecnologiaList);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "modelo", path = "resultadoPesquisaVariavesPorModelo.jsp"),
							 @Jpf.Forward(name = "variavel", path = "resultadoPesquisaVariavesPorVariavel.jsp")})
	public Forward pesquisarVariaveisDoModelo(br.com.vivo.catalogoPRS.pageflows.param.produtos.variaveisModelo.AssociarVariaveisModeloController.AssociarVariaveisModeloFormBean form) {
		String success = "";
		if(form.getTipoPesquisa() != null && form.getTipoPesquisa().equalsIgnoreCase("modelo")) {

			if(form.getIdTipoProduto() != null) {
				form.getIdTipoProduto();
			}
			
			if(form.getIdFabricante() != null) {
				form.getIdFabricante();
			}
			
			if(form.getNmModelo() != null && form.getNmModelo().trim().length() > 0) {
				form.getNmModelo();
			}
			
			if(form.getIdsTecnologia() != null && form.getIdsTecnologia().trim().length() > 0) {
				String[] tecnologiaArray = form.getIdsTecnologia().split(",");
				for (String idTecnologia : tecnologiaArray) {
					System.out.println(idTecnologia);
				}
			}
			
			List<Variaveis> variaveisLista = new ArrayList<Variaveis>();
			for(int i = 0; i < 150; i++) {
				Variaveis variaveis = new Variaveis();
				variaveis.setIdVariavel(Long.valueOf(i));
				variaveis.setModelo("Modelo_"+i);
				variaveis.setFabricante("Fabricante_"+i);
				variaveisLista.add(variaveis);
			}
			this.getRequest().setAttribute("variaveis", variaveisLista);
			success = "modelo";
		}
		if(form.getTipoPesquisa() != null && form.getTipoPesquisa().equalsIgnoreCase("variavel")) {

			if(form.getIdsTipoCliente() != null && form.getIdsTipoCliente().trim().length() > 0) {
				String[] tipoClienteArray = form.getIdsTipoCliente().split(",");
				for(String idTipoCliente : tipoClienteArray) {
					System.out.println(idTipoCliente);
				}
			}
			
			if(form.getSgCarteira() != null && form.getSgCarteira().trim().length() > 0) {
				String[] carteitaArray = form.getSgCarteira().split(",");
				for(String sgCarteria : carteitaArray) {
					System.out.println(sgCarteria);
				}
			}
			
			if(form.getSgSegmento() != null && form.getSgSegmento().trim().length() > 0) {
				String[] segmentoArray = form.getSgSegmento().split(",");
				for(String sgSegmento : segmentoArray) {
					System.out.println(sgSegmento);
				}
			}
			
			if(form.getIdsCanal() != null && form.getIdsCanal().trim().length() > 0) {
				String[] canalArray = form.getIdsCanal().split(",");
				for(String idCanal : canalArray) {
					System.out.println(idCanal);
				}
			}
			
			if(form.getIdsUf() != null && form.getIdsUf().trim().length() > 0) {
				String[] ufArray = form.getIdsUf().split(",");
				for(String idUf : ufArray) {
					System.out.println(idUf);
				}
			}

			List<Variaveis> variaveisLista = new ArrayList<Variaveis>();
			for(int i = 0; i < 150; i++) {
				Variaveis variaveis = new Variaveis();
				variaveis.setIdVariavel(Long.valueOf(i));
				variaveis.setModelo("Modelo_"+i);
				variaveis.setFabricante("Fabricante_"+i);
				variaveisLista.add(variaveis);
			}
			this.getRequest().setAttribute("variaveis", variaveisLista);
			success = "variavel";
		}
		
		
		Forward forward = new Forward(success);
		return forward;
	}

	@Jpf.Action( forwards = { @Jpf.Forward(name = "success", path = "popupListaTipoCliente.jsp") })
	public Forward buscarListaTipoCliente() {
		HttpServletRequest request = this.getRequest();
		String tipoCliente = request.getParameter("hidden_lista_tipo_cliente");
		if (tipoCliente != null && tipoCliente.trim().length() > 0) {
			this.setTipoClienteSelecionados(tipoCliente.split(","));
		}
		BuscarListaTipoClienteRequestDocument tipoClienteRequestDocument = BuscarListaTipoClienteRequestDocument.Factory.newInstance();
		tipoClienteRequestDocument.addNewBuscarListaTipoClienteRequest();
		prepareServiceControl(tipoClienteSoapServiceControl);
		BuscarListaTipoClienteResponseDocument tipoClienteResponseDocument = tipoClienteSoapServiceControl.buscarListaTipoCliente(tipoClienteRequestDocument);
		List<TipoCliente> tipoClienteLista = tipoClienteResponseDocument.getBuscarListaTipoClienteResponse().getResultadoBuscarListaTipoCliente().getTipoClienteList();
		request.setAttribute("tipoCliente", tipoClienteLista);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupCarteira.jsp") })
	public Forward buscarListaCarteira() {
		HttpServletRequest request = this.getRequest();
		String carteiras = request.getParameter("hidden_lista_carteira");
		if(carteiras != null && carteiras.trim().length() > 0) {
			this.setCarteirasSelecionadas(carteiras.split(","));
		}
		BuscarListaCarteiraRequestDocument carteiraRequestDocument = BuscarListaCarteiraRequestDocument.Factory.newInstance();
		carteiraRequestDocument.addNewBuscarListaCarteiraRequest().addNewParametrosBuscarListaCarteira();
		prepareServiceControl(carteiraServiceControl);
		BuscarListaCarteiraResponseDocument carteiraResponseDocument = carteiraServiceControl.buscarListaCarteira(carteiraRequestDocument);
		List<Carteira> carteiraLista = carteiraResponseDocument.getBuscarListaCarteiraResponse().getResultadoBuscarListaCarteira().getListaCarteira().getCarteiraList();
		request.setAttribute("carteiras", carteiraLista);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupSegmento.jsp") })
	public Forward buscarListaSegmento() {
		HttpServletRequest request = this.getRequest();
		String segmentos = request.getParameter("hidden_lista_segmento");
		if(segmentos != null && segmentos.trim().length() > 0) {
			this.setSegmentosSelecionadas(segmentos.split(","));
		}
		BuscarListaSegmentacaoRequestDocument segmentacaoRequestDocument = BuscarListaSegmentacaoRequestDocument.Factory.newInstance();
		segmentacaoRequestDocument.addNewBuscarListaSegmentacaoRequest();
		prepareServiceControl(segmentoServiceControl);
		BuscarListaSegmentacaoResponseDocument segmentacaoResponseDocument = segmentoServiceControl.buscarListaSegmentacao(segmentacaoRequestDocument);
		List<Segmento> segmentoLista = segmentacaoResponseDocument.getBuscarListaSegmentacaoResponse().getResultadoBuscarListaSegmento().getListaSegmento().getSegmentoList();
		request.setAttribute("segmentos", segmentoLista);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupCanal.jsp") })
	public Forward buscarListaCanal() {
		HttpServletRequest request = this.getRequest();
		String canais = request.getParameter("hidden_lista_canal");
		if(canais != null && canais.trim().length() > 0) {
			this.setCanaisSelecionadas(canais.split(","));
		}
		BuscarListaCanalRequestDocument canalRequestDocument = BuscarListaCanalRequestDocument.Factory.newInstance();
		canalRequestDocument.addNewBuscarListaCanalRequest();
		prepareServiceControl(canalServiceControl);
		BuscarListaCanalResponseDocument canalResponseDocument = canalServiceControl.buscarListaCanal(canalRequestDocument);
		List<Canal> canalLista = canalResponseDocument.getBuscarListaCanalResponse().getResultadoListaCanal().getCanalList();
		request.setAttribute("canais", canalLista);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupUfs.jsp") })
	public Forward buscarListaUf() {
		HttpServletRequest request = this.getRequest();
		String ufs = request.getParameter("hidden_lista_uf");
		if(ufs != null && ufs.trim().length() > 0) {
			this.setUfsSelecionadas(ufs.split(","));
		}
		BuscarListaUFRequestDocument ufRequestDocument = BuscarListaUFRequestDocument.Factory.newInstance();
		ufRequestDocument.addNewBuscarListaUFRequest();
		prepareServiceControl(UFSoapServiceControl);
		BuscarListaUFResponseDocument ufResponseDocument = UFSoapServiceControl.buscarListaUF(ufRequestDocument);
		List<UF> uflista = ufResponseDocument.getBuscarListaUFResponse().getResultadoBuscarListaUF().getUFList();
		request.setAttribute("ufs", uflista);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward associarVariaveisModelo(br.com.vivo.catalogoPRS.pageflows.param.produtos.variaveisModelo.AssociarVariaveisModeloController.AssociarVariaveisModeloFormBean form) {
		
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "listarModeloDisponivelParaAssociacao.jsp") })
	public Forward listarModeloDisponivalParaAssociacao() {
		Forward forward = new Forward("success");
		return forward;
	}

	@Override
	protected void onCreate() {
		super.onCreate();
		tipoProdutoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TIPO_PRODUTO));
		tecnologiaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TECNOLOGIA));
		tipoClienteSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TIPO_CLIENTE));
		carteiraServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.CARTEIRA));
		segmentoServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.SEGMENTO));
		canalServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.CANAL));
		UFSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.UF));
	}
	
	@Override
	protected void onDestroy(HttpSession session) {
	}

	@Jpf.FormBean
	public static class AssociarVariaveisModeloFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		private Long paginaSolicitada;
		private Long idTipoProduto;
		private Long idFabricante;
		private Long[] idsVariaveis;
		private String idsTipoCliente;
		private String idsCanal;
		private String idsUf;
		private String idsTecnologia;
		private String sgCarteira;
		private String sgSegmento;
		private String nmModelo;
		private String nomesTecnologias;
		private String tipoPesquisa;
		
		public String getNmModelo() {
			return nmModelo;
		}
		public void setNmModelo(String nmModelo) {
			this.nmModelo = nmModelo;
		}
		public Long getPaginaSolicitada() {
			return paginaSolicitada;
		}
		public void setPaginaSolicitada(Long paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}
		public String getNomesTecnologias() {
			return nomesTecnologias;
		}
		public void setNomesTecnologias(String nomesTecnologias) {
			this.nomesTecnologias = nomesTecnologias;
		}
		public String getSgCarteira() {
			return sgCarteira;
		}
		public void setSgCarteira(String sgCarteira) {
			this.sgCarteira = sgCarteira;
		}
		public String getSgSegmento() {
			return sgSegmento;
		}
		public void setSgSegmento(String sgSegmento) {
			this.sgSegmento = sgSegmento;
		}
		public Long getIdTipoProduto() {
			return idTipoProduto;
		}
		public void setIdTipoProduto(Long idTipoProduto) {
			this.idTipoProduto = idTipoProduto;
		}
		public Long getIdFabricante() {
			return idFabricante;
		}
		public void setIdFabricante(Long idFabricante) {
			this.idFabricante = idFabricante;
		}
		public String getIdsTecnologia() {
			return idsTecnologia;
		}
		public void setIdsTecnologia(String idsTecnologia) {
			this.idsTecnologia = idsTecnologia;
		}
		public String getTipoPesquisa() {
			return tipoPesquisa;
		}
		public void setTipoPesquisa(String tipoPesquisa) {
			this.tipoPesquisa = tipoPesquisa;
		}
		public String getIdsCanal() {
			return idsCanal;
		}
		public void setIdsCanal(String idsCanal) {
			this.idsCanal = idsCanal;
		}
		public String getIdsTipoCliente() {
			return idsTipoCliente;
		}
		public void setIdsTipoCliente(String idsTipoCliente) {
			this.idsTipoCliente = idsTipoCliente;
		}
		public String getIdsUf() {
			return idsUf;
		}
		public void setIdsUf(String idsUf) {
			this.idsUf = idsUf;
		}
		public Long[] getIdsVariaveis() {
			return idsVariaveis;
		}
		public void setIdsVariaveis(Long[] idsVariaveis) {
			this.idsVariaveis = idsVariaveis;
		}
	}

	public String[] getTecnologiasSelecionadas() {
		return tecnologiasSelecionadas;
	}
	public void setTecnologiasSelecionadas(String[] tecnologiasSelecionadas) {
		this.tecnologiasSelecionadas = tecnologiasSelecionadas;
	}
	public String[] getTipoClienteSelecionados() {
		return tipoClienteSelecionados;
	}
	public void setTipoClienteSelecionados(String[] tipoClienteSelecionados) {
		this.tipoClienteSelecionados = tipoClienteSelecionados;
	}
	public String[] getCarteirasSelecionadas() {
		return carteirasSelecionadas;
	}
	public void setCarteirasSelecionadas(String[] carteirasSelecionadas) {
		this.carteirasSelecionadas = carteirasSelecionadas;
	}
	public String[] getSegmentosSelecionadas() {
		return segmentosSelecionadas;
	}
	public void setSegmentosSelecionadas(String[] segmentosSelecionadas) {
		this.segmentosSelecionadas = segmentosSelecionadas;
	}
	public String[] getCanaisSelecionadas() {
		return canaisSelecionadas;
	}
	public void setCanaisSelecionadas(String[] canaisSelecionadas) {
		this.canaisSelecionadas = canaisSelecionadas;
	}
	public String[] getUfsSelecionadas() {
		return ufsSelecionadas;
	}
	public void setUfsSelecionadas(String[] ufsSelecionadas) {
		this.ufsSelecionadas = ufsSelecionadas;
	}*/
	
}