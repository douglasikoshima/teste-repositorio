package br.com.vivo.catalogoPRS.pageflows.param.produtos.associacaoTecnologiaFrequencia;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.AssociacaoFrequenciaForm;
import br.com.vivo.catalogoPRS.services.BaseService;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorTecnologiaTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorTecnologiaTipoFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaTipoFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.FrequenciaPTProxy;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaParametrosVlFrequenciaIn;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaModeloPorTecnologiaTipoFrequenciaModeloPorTecnologiaTpFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaVlfrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoListarValorFrequenciaVlfrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloPorTecnologiaTpFrequenciaVlResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AlterarTecnologiaTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AssociarTecnologiaTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AssociarTecnologiaTipoFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.AssociarTecnologiaTipoFrequenciaValorRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarAssociacaoTecnologiaTpFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarAssociacaoTecnologiaTpFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaSimplesRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaSimplesResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaTipoFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.DesassociarListaTecnologiaTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.DesassociarTecnologiaTpFrequenciaVlRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAlterarTecnologiaTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaValor;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequenciaParametrosBuscarAssociacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarTecnologiaTpFrequenciaVl;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarAssociacaoTecnologiaTpFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaSimplesTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortTypeProxy;
import edu.emory.mathcs.backport.java.util.Arrays;


public class AssociacaoTecnologiaFrequenciaAction extends BaseMappingDispatchAction  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String SUCCESS = "success";
	private String DEFAULT = "default";


	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssociacaoFrequenciaForm associacaoFrequenciaForm = (AssociacaoFrequenciaForm)form;
		
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();
		
		BuscarListaTecnologiaSimplesRequest buscarListaTecnologiaSimplesRequest = new BuscarListaTecnologiaSimplesRequest();
		BuscarListaTecnologiaSimplesResponse buscarListaTecnologiaSimplesResponse = new BuscarListaTecnologiaSimplesResponse();
		
		BuscarListaTipoFrequenciaRequest buscarListaTipoFrequenciaRequest = new BuscarListaTipoFrequenciaRequest();
		BuscarListaTipoFrequenciaResponse buscarListaTipoFrequenciaResponse = new BuscarListaTipoFrequenciaResponse();
		
		BuscarListaTecnologiaTipoFrequenciaRequest buscarListaTecnologiaTipoFrequenciaRequest = new BuscarListaTecnologiaTipoFrequenciaRequest();
		BuscarListaTecnologiaTipoFrequenciaResponse buscarListaTecnologiaTipoFrequenciaResponse = new BuscarListaTecnologiaTipoFrequenciaResponse();
		
		try {
			buscarListaTecnologiaSimplesResponse = tecnologiaPortTypeProxy.buscarListaTecnologiaSimples(buscarListaTecnologiaSimplesRequest, this.getUserName(), this.getPassword());
			buscarListaTipoFrequenciaResponse = frequenciaPTProxy.buscarListaTipoFrequencia(buscarListaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
			buscarListaTecnologiaTipoFrequenciaResponse = tecnologiaPortTypeProxy.buscarListaTecnologiaTipoFrequencia(buscarListaTecnologiaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
			return null;
		}	

		List<ResultadoBuscarListaTecnologiaSimplesTecnologia> tecnologiaList = Arrays.asList(buscarListaTecnologiaSimplesResponse.getResultadoBuscarListaTecnologiaSimples());
        request.setAttribute("comboTecnologias", tecnologiaList);
        //associacaoFrequenciaForm.setTecnologiaList(tecnologiaList);
        
        List<ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao> tipoFrequenciaCriacaoList = Arrays.asList(buscarListaTipoFrequenciaResponse.getResultadoBuscarListaTipoFrequencia());
		request.setAttribute("tiposFrequencia", tipoFrequenciaCriacaoList);
		//associacaoFrequenciaForm.setTipoFrequenciaCriacaoList(tipoFrequenciaCriacaoList);

		List<ListaTecnologiaTecnologia> tecnologiaTipoFrequenciaList = Arrays.asList(buscarListaTecnologiaTipoFrequenciaResponse.getResultadoBuscarListaTecnologiaTipoFrequencia().getListaTecnologia());
		//request.setAttribute("tecnologias", tecnologiaTipoFrequenciaList);
		associacaoFrequenciaForm.setTecnologiaTipoFrequenciaList(tecnologiaTipoFrequenciaList);
		
		request.setAttribute("size_tecnologia", tecnologiaTipoFrequenciaList==null?0:tecnologiaTipoFrequenciaList.size());

		this.cleanHeader(response);
		return mapping.findForward(DEFAULT);
	}

	public ActionForward apagarAssociacaoTecnologiaFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssociacaoFrequenciaForm associacaoFrequenciaForm = (AssociacaoFrequenciaForm)form;

		DesassociarListaTecnologiaTipoFrequenciaRequest desassociarListaTecnologiaTipoFrequenciaRequest = new DesassociarListaTecnologiaTipoFrequenciaRequest();
		ParametrosDesassociarListaTecnologiaTipoFrequencia parametrosDesassociarListaTecnologiaTipoFrequencia =new ParametrosDesassociarListaTecnologiaTipoFrequencia();
		
		ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao parametros 
		= new ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao();
		
		ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao[] parametrosArray 
		= new ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao[1];
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();

		parametros.setIdTecnologiaTpFrequencia(associacaoFrequenciaForm.getIdEntidade());
		if(associacaoFrequenciaForm.getJustificativa()!=null && !associacaoFrequenciaForm.getJustificativa().trim().equalsIgnoreCase("")){
			parametros.setJustificativa(associacaoFrequenciaForm.getJustificativa());
		}
		
		parametrosArray[0] = parametros;
		
		parametrosDesassociarListaTecnologiaTipoFrequencia.setListaTecnologiaTipoFrequenciaDesassociacao(parametrosArray);
		desassociarListaTecnologiaTipoFrequenciaRequest.setParametrosDesassociarListaTecnologiaTipoFrequencia(parametrosDesassociarListaTecnologiaTipoFrequencia);
		
		try {
			tecnologiaPortTypeProxy.desassociarListaTecnologiaTipoFrequencia(desassociarListaTecnologiaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
			return null;
		}

		this.cleanHeader(response);
		return this.begin(mapping, form, request, response);
	}

	public ActionForward popupApagarTecnologiaFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssociacaoFrequenciaForm associacaoFrequenciaForm = (AssociacaoFrequenciaForm)form;

		String idtecnologia = request.getParameter("id_tecnologia");
		String idtipofrequencia = request.getParameter("id_tipoFrequencia");

		BuscarAssociacaoTecnologiaTpFrequenciaRequest buscarAssociacaoTecnologiaTpFrequenciaRequest = new BuscarAssociacaoTecnologiaTpFrequenciaRequest();
		BuscarAssociacaoTecnologiaTpFrequenciaResponse buscarAssociacaoTecnologiaTpFrequenciaResponse = new BuscarAssociacaoTecnologiaTpFrequenciaResponse();
		ParametrosBuscarAssociacaoTecnologiaTpFrequencia parametrosBuscarAssociacaoTecnologiaTpFrequencia = new ParametrosBuscarAssociacaoTecnologiaTpFrequencia();
		ParametrosBuscarAssociacaoTecnologiaTpFrequenciaParametrosBuscarAssociacaoIn parametrosBuscarAssociacaoIn = new ParametrosBuscarAssociacaoTecnologiaTpFrequenciaParametrosBuscarAssociacaoIn();
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		
		BuscarListaModeloPorTecnologiaTipoFrequenciaRequest buscarListaModeloPorTecnologiaTipoFrequenciaRequest = new BuscarListaModeloPorTecnologiaTipoFrequenciaRequest();
		BuscarListaModeloPorTecnologiaTipoFrequenciaResponse buscarListaModeloPorTecnologiaTipoFrequenciaResponse = new BuscarListaModeloPorTecnologiaTipoFrequenciaResponse();
		ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia parametrosBuscarListaModeloPorTecnologiaTipoFrequencia = new ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia();
		ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn parametrosModeloPorTecnologiaTpFrequenciaIn 
		 = new ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn();
		FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();
		
		parametrosBuscarAssociacaoIn.setIdTecnologia(Long.valueOf(idtecnologia));
		parametrosBuscarAssociacaoIn.setIdTipoFrequencia(Long.valueOf(idtipofrequencia));
		parametrosBuscarAssociacaoTecnologiaTpFrequencia.setParametrosBuscarAssociacaoIn(parametrosBuscarAssociacaoIn);
		buscarAssociacaoTecnologiaTpFrequenciaRequest.setParametrosBuscarAssociacaoTecnologiaTpFrequencia(parametrosBuscarAssociacaoTecnologiaTpFrequencia);
		
		parametrosModeloPorTecnologiaTpFrequenciaIn.setIdTecnologia(Long.valueOf(idtecnologia));
		parametrosModeloPorTecnologiaTpFrequenciaIn.setIdTipoFrequencia(Long.valueOf(idtipofrequencia));
		parametrosBuscarListaModeloPorTecnologiaTipoFrequencia.setParametrosModeloPorTecnologiaTpFrequenciaIn(parametrosModeloPorTecnologiaTpFrequenciaIn);
		buscarListaModeloPorTecnologiaTipoFrequenciaRequest.setParametrosBuscarListaModeloPorTecnologiaTipoFrequencia(parametrosBuscarListaModeloPorTecnologiaTipoFrequencia);
				

		try {
			buscarAssociacaoTecnologiaTpFrequenciaResponse = tecnologiaPortTypeProxy.buscarAssociacaoTecnologiaTpFrequencia(buscarAssociacaoTecnologiaTpFrequenciaRequest, this.getUserName(), this.getPassword());
			buscarListaModeloPorTecnologiaTipoFrequenciaResponse = frequenciaPTProxy.buscarListaModeloPorTecnologiaTipoFrequencia(buscarListaModeloPorTecnologiaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			//this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
			//return null;
			ex.printStackTrace();
		}
		
		ResultadoBuscarAssociacaoTecnologiaTpFrequencia resultadoBuscarAssociacaoTecnologiaTpFrequencia = buscarAssociacaoTecnologiaTpFrequenciaResponse.getResultadoBuscarAssociacaoTecnologiaTpFrequencia();
		request.setAttribute("id_tecnologia_tipo_frequencia", resultadoBuscarAssociacaoTecnologiaTpFrequencia.getIdTecnologiaTpFrequencia());

		if (buscarListaModeloPorTecnologiaTipoFrequenciaResponse != null && buscarListaModeloPorTecnologiaTipoFrequenciaResponse.getResultadoBuscarListaModeloPorTecnologiaTipoFrequencia() != null
				&& buscarListaModeloPorTecnologiaTipoFrequenciaResponse.getResultadoBuscarListaModeloPorTecnologiaTipoFrequencia().length > 0) {
			List<ResultadoBuscarListaModeloPorTecnologiaTipoFrequenciaModeloPorTecnologiaTpFrequencia> resultadoBuscarListaModeloPorTecnologiaTipoFrequencia = 
					Arrays.asList(buscarListaModeloPorTecnologiaTipoFrequenciaResponse.getResultadoBuscarListaModeloPorTecnologiaTipoFrequencia());
			//request.setAttribute("modelos_afetados", resultadoBuscarListaModeloPorTecnologiaTipoFrequencia);
			associacaoFrequenciaForm.setApagarModeloAfetados(resultadoBuscarListaModeloPorTecnologiaTipoFrequencia);
		}

		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward addAssociacaoTecnologiaFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssociacaoFrequenciaForm associacaoFrequenciaForm = (AssociacaoFrequenciaForm)form;
		
		AssociarTecnologiaTipoFrequenciaRequest associarTecnologiaTipoFrequenciaRequest = new AssociarTecnologiaTipoFrequenciaRequest();
		AssociarTecnologiaTipoFrequenciaResponse associarTecnologiaTipoFrequenciaResponse = new AssociarTecnologiaTipoFrequenciaResponse();
		ParametrosAssociarTecnologiaTipoFrequencia parametrosAssociarTecnologiaTipoFrequencia = new ParametrosAssociarTecnologiaTipoFrequencia();
		ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao tecnologiaTipoFrequenciaAssociacao = new ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao();
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		

		BuscarListaTecnologiaSimplesRequest buscarListaTecnologiaSimplesRequest = new BuscarListaTecnologiaSimplesRequest();
		BuscarListaTecnologiaSimplesResponse buscarListaTecnologiaSimplesResponse = new BuscarListaTecnologiaSimplesResponse();
		
		FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();
		BuscarListaTipoFrequenciaRequest buscarListaTipoFrequenciaRequest = new BuscarListaTipoFrequenciaRequest();
		BuscarListaTipoFrequenciaResponse buscarListaTipoFrequenciaResponse = new BuscarListaTipoFrequenciaResponse();
		
		BuscarListaTecnologiaTipoFrequenciaRequest buscarListaTecnologiaTipoFrequenciaRequest = new BuscarListaTecnologiaTipoFrequenciaRequest();
		BuscarListaTecnologiaTipoFrequenciaResponse buscarListaTecnologiaTipoFrequenciaResponse = new BuscarListaTecnologiaTipoFrequenciaResponse();		
		
		tecnologiaTipoFrequenciaAssociacao.setIdTecnologia(associacaoFrequenciaForm.getIdTecnologia());
		tecnologiaTipoFrequenciaAssociacao.setIdTipoFrequencia(associacaoFrequenciaForm.getIdTipoFrequencia());
		
		parametrosAssociarTecnologiaTipoFrequencia.setTecnologiaTipoFrequenciaAssociacao(tecnologiaTipoFrequenciaAssociacao);
		associarTecnologiaTipoFrequenciaRequest.setParametrosAssociarTecnologiaTipoFrequencia(parametrosAssociarTecnologiaTipoFrequencia);

		try {
			associarTecnologiaTipoFrequenciaResponse = tecnologiaPortTypeProxy.associarTecnologiaTipoFrequencia(associarTecnologiaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
			buscarListaTecnologiaSimplesResponse = tecnologiaPortTypeProxy.buscarListaTecnologiaSimples(buscarListaTecnologiaSimplesRequest, this.getUserName(), this.getPassword());
			buscarListaTipoFrequenciaResponse = frequenciaPTProxy.buscarListaTipoFrequencia(buscarListaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
			buscarListaTecnologiaTipoFrequenciaResponse = tecnologiaPortTypeProxy.buscarListaTecnologiaTipoFrequencia(buscarListaTecnologiaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
			return null;
		}
		
		request.setAttribute("id_nova_associacao", associarTecnologiaTipoFrequenciaResponse.getResultadoAssociarTecnologiaTipoFrequencia().getIdTecnologiaTpFrequencia());
		
		associacaoFrequenciaForm.setIdTecnologia(null);
		associacaoFrequenciaForm.setIdTipoFrequencia(null);

		List<ResultadoBuscarListaTecnologiaSimplesTecnologia> tecnologiaList = Arrays.asList(buscarListaTecnologiaSimplesResponse.getResultadoBuscarListaTecnologiaSimples());
		request.setAttribute("comboTecnologias", tecnologiaList);
		//associacaoFrequenciaForm.setTecnologiaList(tecnologiaList);
		
		List<ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao> tipoFrequenciaCriacaoList = Arrays.asList(buscarListaTipoFrequenciaResponse.getResultadoBuscarListaTipoFrequencia());
		request.setAttribute("tiposFrequencia", tipoFrequenciaCriacaoList);
		//associacaoFrequenciaForm.setTipoFrequenciaCriacaoList(tipoFrequenciaCriacaoList);
		
		List<ListaTecnologiaTecnologia> tecnologiaTipoFrequenciaList = Arrays.asList(buscarListaTecnologiaTipoFrequenciaResponse.getResultadoBuscarListaTecnologiaTipoFrequencia().getListaTecnologia());
		//request.setAttribute("tecnologias", tecnologiaTipoFrequenciaList);
		associacaoFrequenciaForm.setTecnologiaTipoFrequenciaList(tecnologiaTipoFrequenciaList);
		
		request.setAttribute("size_tecnologia", tecnologiaTipoFrequenciaList==null?0:tecnologiaTipoFrequenciaList.size());

		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward valoresFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssociacaoFrequenciaForm associacaoFrequenciaForm = (AssociacaoFrequenciaForm)form;

		forwardParameter(request, "id_tipo_frequencia");
		forwardParameter(request, "id_tecnologia");
		forwardParameter(request, "id_tecnologia_tipo_frequencia");
		forwardParameter(request, "qtFrequencia");

		BuscarListaValorFrequenciaRequest  buscarListaValorFrequenciaRequest = new BuscarListaValorFrequenciaRequest();
		BuscarListaValorFrequenciaResponse buscarListaValorFrequenciaResponse = new BuscarListaValorFrequenciaResponse();
		FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();
		
		String idTecnologia = request.getParameter("id_tecnologia");
		String idTipoFrequencia = request.getParameter("id_tipo_frequencia");

		BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest = new BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest();
		BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse = new BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse();
		ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia = new ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia();
		ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaParametrosVlFrequenciaIn parametrosVlFrequenciaIn = new ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaParametrosVlFrequenciaIn();
		
		parametrosVlFrequenciaIn.setIdTecnologia(Long.valueOf(idTecnologia));
		parametrosVlFrequenciaIn.setIdTipoFrequencia(Long.valueOf(idTipoFrequencia));
		
		parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia.setParametrosVlFrequenciaIn(parametrosVlFrequenciaIn);
		buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest.setParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia(parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia);
		
		try {
			buscarListaValorFrequenciaResponse = frequenciaPTProxy.buscarListaValorFrequencia(buscarListaValorFrequenciaRequest, this.getUserName(), this.getPassword());
			buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse = frequenciaPTProxy.buscarListaValorFrequenciaPorTecnologiaTipoFrequencia(buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			//this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
			//return null;
		}

		List<ResultadoListarValorFrequenciaVlfrequencia> todosVlfrequenciaList = Arrays.asList(buscarListaValorFrequenciaResponse.getResultadoListarValorFrequencia());
		//request.setAttribute("vlFrequencia", todosVlfrequenciaList);
		associacaoFrequenciaForm.setTodosVlfrequenciaList(todosVlfrequenciaList);

		Long[] frequenciasSelecionadas = new Long[] {};
		Map<Long, Long> idsTecnologiaTpFrequenciaVl = new HashMap<Long, Long>();

		if(buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse !=null && buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse.getResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia() !=null){
			
			List<ResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaVlfrequencia> resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia 
			= Arrays.asList(buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaResponse.getResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia());
			
			if (resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia != null) {
				frequenciasSelecionadas = new Long[resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia.size()];

				int i = 0;
				for (ResultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaVlfrequencia vlfrequencia : resultadoBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia) {
					frequenciasSelecionadas[i++] = vlfrequencia.getIdVlFrequencia();
					idsTecnologiaTpFrequenciaVl.put(vlfrequencia.getIdVlFrequencia(), vlfrequencia.getIdTecnologiaTpFrequenciaVl());
				}
				associacaoFrequenciaForm.setFrequenciasSelecionadas(frequenciasSelecionadas);
			}
		}
		request.setAttribute("ids_tecnologia_tipo_frequencia_valor", idsTecnologiaTpFrequenciaVl);
		
		//this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward alterarFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssociacaoFrequenciaForm associacaoFrequenciaForm = (AssociacaoFrequenciaForm)form;

		AlterarTecnologiaTipoFrequenciaRequest alterarTecnologiaTipoFrequenciaRequest = new AlterarTecnologiaTipoFrequenciaRequest();
		ParametrosAlterarTecnologiaTipoFrequencia parametrosAlterarTecnologiaTipoFrequencia = new ParametrosAlterarTecnologiaTipoFrequencia();
		ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao parametrosTecnologiaTipoFrequenciaAlteracao = new ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao();
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		
		parametrosTecnologiaTipoFrequenciaAlteracao.setIdTecnologia(associacaoFrequenciaForm.getIdTecnologia());
		parametrosTecnologiaTipoFrequenciaAlteracao.setIdTecnologiaTpFrequencia(associacaoFrequenciaForm.getIdTecnologiaTipoFrequencia());
		parametrosTecnologiaTipoFrequenciaAlteracao.setIdTipoFrequencia(associacaoFrequenciaForm.getIdTipoFrequencia());
		//parametrosTecnologiaTipoFrequenciaAlteracao.setJustificativa(associacaoFrequenciaForm.getJustificativa());

		parametrosAlterarTecnologiaTipoFrequencia.setParametrosTecnologiaTipoFrequenciaAlteracao(parametrosTecnologiaTipoFrequenciaAlteracao);
		alterarTecnologiaTipoFrequenciaRequest.setParametrosAlterarTecnologiaTipoFrequencia(parametrosAlterarTecnologiaTipoFrequencia);

		try {
			tecnologiaPortTypeProxy.alterarTecnologiaTipoFrequencia(alterarTecnologiaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
			return null;
		}

		request.setAttribute("id_nova_associacao", associacaoFrequenciaForm.getIdTecnologiaTipoFrequencia());

		//this.cleanHeader(response);
		return this.begin(mapping, form, request, response);
	}

	public ActionForward carregarAlterarTecnologiaFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssociacaoFrequenciaForm associacaoFrequenciaForm = (AssociacaoFrequenciaForm)form;
		
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();
		BuscarListaTecnologiaSimplesRequest buscarListaTecnologiaSimplesRequest = new BuscarListaTecnologiaSimplesRequest();
		BuscarListaTecnologiaSimplesResponse buscarListaTecnologiaSimplesResponse = new BuscarListaTecnologiaSimplesResponse();
		
		BuscarListaTipoFrequenciaRequest buscarListaTipoFrequenciaRequest = new BuscarListaTipoFrequenciaRequest();
		BuscarListaTipoFrequenciaResponse buscarListaTipoFrequenciaResponse = new BuscarListaTipoFrequenciaResponse();
		
		BuscarAssociacaoTecnologiaTpFrequenciaRequest buscarAssociacaoTecnologiaTpFrequenciaRequest = new BuscarAssociacaoTecnologiaTpFrequenciaRequest();
		BuscarAssociacaoTecnologiaTpFrequenciaResponse buscarAssociacaoTecnologiaTpFrequenciaResponse = new BuscarAssociacaoTecnologiaTpFrequenciaResponse();
		ParametrosBuscarAssociacaoTecnologiaTpFrequencia parametrosBuscarAssociacaoTecnologiaTpFrequencia = new ParametrosBuscarAssociacaoTecnologiaTpFrequencia();
		ParametrosBuscarAssociacaoTecnologiaTpFrequenciaParametrosBuscarAssociacaoIn parametrosBuscarAssociacaoIn = new ParametrosBuscarAssociacaoTecnologiaTpFrequenciaParametrosBuscarAssociacaoIn();
		
		String idtecnologia = request.getParameter("id_tecnologia");
		String idtipofrequencia = request.getParameter("id_tipoFrequencia");

		parametrosBuscarAssociacaoIn.setIdTecnologia(Long.valueOf(idtecnologia));
		parametrosBuscarAssociacaoIn.setIdTipoFrequencia(Long.valueOf(idtipofrequencia));
		
		parametrosBuscarAssociacaoTecnologiaTpFrequencia.setParametrosBuscarAssociacaoIn(parametrosBuscarAssociacaoIn);
		buscarAssociacaoTecnologiaTpFrequenciaRequest.setParametrosBuscarAssociacaoTecnologiaTpFrequencia(parametrosBuscarAssociacaoTecnologiaTpFrequencia);
		
		try {
			buscarListaTecnologiaSimplesResponse = tecnologiaPortTypeProxy.buscarListaTecnologiaSimples(buscarListaTecnologiaSimplesRequest, this.getUserName(), this.getPassword());
			buscarListaTipoFrequenciaResponse = frequenciaPTProxy.buscarListaTipoFrequencia(buscarListaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
			buscarAssociacaoTecnologiaTpFrequenciaResponse = tecnologiaPortTypeProxy.buscarAssociacaoTecnologiaTpFrequencia(buscarAssociacaoTecnologiaTpFrequenciaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
			return null;
		}
		
		List<ResultadoBuscarListaTecnologiaSimplesTecnologia> tecnologiaList = Arrays.asList(buscarListaTecnologiaSimplesResponse.getResultadoBuscarListaTecnologiaSimples());
		request.setAttribute("comboTecnologias", tecnologiaList);

		List<ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao> tipoFrequenciaCriacaoList = Arrays.asList(buscarListaTipoFrequenciaResponse.getResultadoBuscarListaTipoFrequencia());
		request.setAttribute("tiposFrequencia", tipoFrequenciaCriacaoList);

		ResultadoBuscarAssociacaoTecnologiaTpFrequencia resultadoBuscarAssociacaoTecnologiaTpFrequencia = buscarAssociacaoTecnologiaTpFrequenciaResponse.getResultadoBuscarAssociacaoTecnologiaTpFrequencia();
		request.setAttribute("tecnologiaTpFrequencia", resultadoBuscarAssociacaoTecnologiaTpFrequencia);
		
		associacaoFrequenciaForm.setIdTecnologia(resultadoBuscarAssociacaoTecnologiaTpFrequencia.getIdTecnologia());
		associacaoFrequenciaForm.setIdTipoFrequencia(resultadoBuscarAssociacaoTecnologiaTpFrequencia.getIdTipoFrequencia());
		
	
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward alterarValorTecnologiaTipoFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		AssociacaoFrequenciaForm associacaoFrequenciaForm = (AssociacaoFrequenciaForm)form; 

		String idTecnologiaTipoFrequenciaString = request.getParameter("id_tecnologia_tipo_frequencia");
		String idValorFrequenciaString = request.getParameter("id_valor_frequencia");
		String checked = request.getParameter("checked");

		//Limpa o cache para garantir atualizacao na associacao com modelo
		BaseService.resetCache("Tecnologia", "buscarListaTecnologia");
		
		if ("S".equalsIgnoreCase(checked)) {
			AssociarTecnologiaTipoFrequenciaValorRequest associarTecnologiaTipoFrequenciaValorRequest = new AssociarTecnologiaTipoFrequenciaValorRequest();
			ParametrosAssociarTecnologiaTipoFrequenciaValor parametrosAssociarTecnologiaTipoFrequenciaValor = new ParametrosAssociarTecnologiaTipoFrequenciaValor();
			ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao tecnologiaTpFrequenciaVlAssociacao = new ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao();
			TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
			
			tecnologiaTpFrequenciaVlAssociacao.setIdTecnologiaTpFrequencia(Long.valueOf(idTecnologiaTipoFrequenciaString));
			tecnologiaTpFrequenciaVlAssociacao.setIdVlFrequencia(Long.valueOf(idValorFrequenciaString));
			parametrosAssociarTecnologiaTipoFrequenciaValor.setTecnologiaTpFrequenciaVlAssociacao(tecnologiaTpFrequenciaVlAssociacao);
			associarTecnologiaTipoFrequenciaValorRequest.setParametrosAssociarTecnologiaTipoFrequenciaValor(parametrosAssociarTecnologiaTipoFrequenciaValor);
			
			try {
				tecnologiaPortTypeProxy.associarTecnologiaTipoFrequenciaValor(associarTecnologiaTipoFrequenciaValorRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
				return null;
			}
			
			PrintWriter writer = null;
			try {
				response.setContentType("text/javascript");
				writer = response.getWriter();
				writer.println("$('link_valores_frequencia_"+ idTecnologiaTipoFrequenciaString + "').onclick()");
				writer.flush();
			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				writer.close();
			}
			return null;
		} else {

			forwardParameter(request, "id_valor_frequencia");
			forwardParameter(request, "id_tecnologia_tipo_frequencia_vl");
			
			String idTecnologiaTipoFrequenciaValorString = request.getParameter("id_tecnologia_tipo_frequencia_vl");

			BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest buscarListaModeloPorTecnologiaTpFrequenciaVlRequest = new BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest();
			BuscarListaModeloPorTecnologiaTpFrequenciaVlResponse buscarListaModeloPorTecnologiaTpFrequenciaVlResponse = new BuscarListaModeloPorTecnologiaTpFrequenciaVlResponse();
			ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl = new ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl();
			ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn parametrosModelosPorVlFrequenciaIn = new ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn();
			ModeloPortTypeProxy modeloPortTypeProxy = new ModeloPortTypeProxy();
	
			parametrosModelosPorVlFrequenciaIn.setIdTecnologiaTpFrequenciaVl(Long.valueOf(idTecnologiaTipoFrequenciaValorString));
			parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl.setParametrosModelosPorVlFrequenciaIn(parametrosModelosPorVlFrequenciaIn);
			buscarListaModeloPorTecnologiaTpFrequenciaVlRequest.setParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl(parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl);
			
			try {
				buscarListaModeloPorTecnologiaTpFrequenciaVlResponse = modeloPortTypeProxy.buscarListaModeloPorTecnologiaTpFrequenciaVl(buscarListaModeloPorTecnologiaTpFrequenciaVlRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
				return null;
			}			

			List<ResultadoBuscarListaModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo> listaModelosPorIdModeloOut = Arrays.asList(buscarListaModeloPorTecnologiaTpFrequenciaVlResponse.getResultadoBuscarListaModeloPorTecnologiaTpFrequenciaVl().getListaModelosPorIdModeloOut());
			if (listaModelosPorIdModeloOut != null) {
				//request.setAttribute("modelos_afetados", listaModelosPorIdModeloOut);
				associacaoFrequenciaForm.setAlterarModeloAfetados(listaModelosPorIdModeloOut);
			}

			this.cleanHeader(response);
			return mapping.findForward(SUCCESS);
		}
	}

	public ActionForward confirmarValoresTecnologiaTipoFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward(SUCCESS);
	}

	public ActionForward desassociarValorFrequenciaTecnologiaTipoFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {

		AssociacaoFrequenciaForm associacaoFrequenciaForm = (AssociacaoFrequenciaForm)form;
		
		DesassociarTecnologiaTpFrequenciaVlRequest desassociarTecnologiaTpFrequenciaVlRequest = new DesassociarTecnologiaTpFrequenciaVlRequest();
		ParametrosDesassociarTecnologiaTpFrequenciaVl parametrosDesassociarTecnologiaTpFrequenciaVl = new ParametrosDesassociarTecnologiaTpFrequenciaVl();
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		
		parametrosDesassociarTecnologiaTpFrequenciaVl.setIdTecnologiaTpFrequenciaVl(associacaoFrequenciaForm.getIdTecnologiaTipoFrequenciaVl());
		
		if(associacaoFrequenciaForm.getJustificativa() != null && !associacaoFrequenciaForm.getJustificativa().trim().equalsIgnoreCase("")){
			parametrosDesassociarTecnologiaTpFrequenciaVl.setJustificativa(associacaoFrequenciaForm.getJustificativa());
		}

		if (associacaoFrequenciaForm.getIdsModelos() != null) {
			int i = 0;
			ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto[] parametrosDispGrupoProduto = new ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto[associacaoFrequenciaForm.getIdsModelos().length];
			for (Long idModelo : associacaoFrequenciaForm.getIdsModelos()) {
				parametrosDispGrupoProduto[i++].setIdGrupoProduto(idModelo);
				parametrosDispGrupoProduto[i++].setInDisponivel("N");
			}
			parametrosDesassociarTecnologiaTpFrequenciaVl.setParametrosDispGrupoProduto(parametrosDispGrupoProduto);
		}
		
		desassociarTecnologiaTpFrequenciaVlRequest.setParametrosDesassociarTecnologiaTpFrequenciaVl(parametrosDesassociarTecnologiaTpFrequenciaVl);
		
		try {
			tecnologiaPortTypeProxy.desassociarTecnologiaTpFrequenciaVl(desassociarTecnologiaTpFrequenciaVlRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, AssociacaoTecnologiaFrequenciaAction.class.getName(), ex.getMessage(), associacaoFrequenciaForm, response );
			return null;
		}		

		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('freq_cb_" + associacaoFrequenciaForm.getIdValorFrequencia() + "').checked=!$('freq_cb_" + associacaoFrequenciaForm.getIdValorFrequencia() + "').checked;");
			writer.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return null;
	}
}