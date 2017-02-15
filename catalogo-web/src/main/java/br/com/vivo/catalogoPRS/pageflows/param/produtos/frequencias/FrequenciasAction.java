package br.com.vivo.catalogoPRS.pageflows.param.produtos.frequencias;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.FrequenciaForm;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorValorFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorValorFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaValorFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.CriarValorFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ExcluirValorFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.FrequenciaPTProxy;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ListaVlFrequenciaVlfrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosExcluirValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaModeloPorValorFrequenciaModeloPorValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoListarValorFrequenciaVlfrequencia;
import edu.emory.mathcs.backport.java.util.Arrays;

public class FrequenciasAction extends BaseMappingDispatchAction implements Serializable{
	private static final long serialVersionUID = 1L;
	private String SUCCESS = "success";
	private String DEFAULT = "default";

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FrequenciaForm frequenciaForm = (FrequenciaForm)form;
		FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();
		BuscarListaValorFrequenciaRequest buscarListaValorFrequenciaRequest = new BuscarListaValorFrequenciaRequest();
		BuscarListaValorFrequenciaResponse buscarListaValorFrequenciaResponse = null;
				
		try {
			buscarListaValorFrequenciaResponse = frequenciaPTProxy.buscarListaValorFrequencia(buscarListaValorFrequenciaRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, FrequenciasAction.class.getName(), ex.getMessage(), frequenciaForm, response );
			return null;
		}					
				
		List<ResultadoListarValorFrequenciaVlfrequencia> vlfrequenciaList = Arrays.asList(buscarListaValorFrequenciaResponse.getResultadoListarValorFrequencia());

		//request.setAttribute("frequencias", vlfrequenciaList);
		frequenciaForm.setVlfrequenciaList(vlfrequenciaList);
		
		request.setAttribute("size_frequencias", vlfrequenciaList.size());
		
		this.cleanHeader(response);
		return mapping.findForward(DEFAULT);
	}

	public ActionForward addFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FrequenciaForm frequenciaForm = (FrequenciaForm)form;
		
		if (frequenciaForm.getValorFrequencia() != null) {

			FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();
			CriarValorFrequenciaRequest criarValorFrequenciaRequest = new CriarValorFrequenciaRequest();
			ParametrosCriarValorFrequencia parametrosCriarValorFrequencia = new ParametrosCriarValorFrequencia();
			ListaVlFrequenciaVlfrequencia VlFrequenciaVlfrequencia = new ListaVlFrequenciaVlfrequencia();
			ListaVlFrequenciaVlfrequencia[] listaVlFrequenciaVlfrequencia = new ListaVlFrequenciaVlfrequencia[1];

			VlFrequenciaVlfrequencia.setVlfrequencia(frequenciaForm.getValorFrequencia());
			listaVlFrequenciaVlfrequencia[0] = VlFrequenciaVlfrequencia;
			
			parametrosCriarValorFrequencia.setListaVlFrequencia(listaVlFrequenciaVlfrequencia);
			criarValorFrequenciaRequest.setParametrosCriarValorFrequencia(parametrosCriarValorFrequencia);

			try {
				frequenciaPTProxy.criarValorFrequencia(criarValorFrequenciaRequest,this.getUserName(),this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, FrequenciasAction.class.getName(), ex.getMessage(), frequenciaForm, response );
				return null;
			}
		}

		return this.begin(mapping, form, request, response);
	}

	public ActionForward popupApagarFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FrequenciaForm frequenciaForm = (FrequenciaForm)form;

		String idFrequenciaString = (String) request.getParameter("id_frequencia");
		request.setAttribute("id_frequencia", idFrequenciaString);

		if (idFrequenciaString != null && !idFrequenciaString.trim().equals("")) {
			Long idFrequencia = Long.valueOf(idFrequenciaString);

			FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();
			BuscarListaModeloPorValorFrequenciaRequest buscarListaModeloPorValorFrequenciaRequest = new BuscarListaModeloPorValorFrequenciaRequest();
			BuscarListaModeloPorValorFrequenciaResponse buscarListaModeloPorValorFrequenciaResponse = null;
			ParametrosBuscarListaModeloPorValorFrequencia parametrosBuscarListaModeloPorValorFrequencia = new ParametrosBuscarListaModeloPorValorFrequencia();
			ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn parametrosModeloPorValorFrequenciaIn = new ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn();
			
			parametrosModeloPorValorFrequenciaIn.setIdValorFrequencia(idFrequencia);
			parametrosBuscarListaModeloPorValorFrequencia.setParametrosModeloPorValorFrequenciaIn(parametrosModeloPorValorFrequenciaIn);
			buscarListaModeloPorValorFrequenciaRequest.setParametrosBuscarListaModeloPorValorFrequencia(parametrosBuscarListaModeloPorValorFrequencia);
			
			try {
				buscarListaModeloPorValorFrequenciaResponse= frequenciaPTProxy.buscarListaModeloPorValorFrequencia(buscarListaModeloPorValorFrequenciaRequest,this.getUserName(),this.getPassword());
			} catch (AxisFault ex) {
				ex.printStackTrace();
			}

			if(buscarListaModeloPorValorFrequenciaResponse !=null && buscarListaModeloPorValorFrequenciaResponse.getResultadoBuscarListaModeloPorValorFrequencia() !=null){
				frequenciaForm.setModeloPorValorFrequenciaList(Arrays.asList(buscarListaModeloPorValorFrequenciaResponse.getResultadoBuscarListaModeloPorValorFrequencia()));
			}
		}
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward apagarFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FrequenciaForm frequenciaForm = (FrequenciaForm)form;
		
		long idFrequencia = frequenciaForm.getIdEntidade();

		FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();
		ExcluirValorFrequenciaRequest excluirValorFrequenciaRequest = new ExcluirValorFrequenciaRequest();
		ParametrosExcluirValorFrequencia parametrosExcluirValorFrequencia = new ParametrosExcluirValorFrequencia();
		
		parametrosExcluirValorFrequencia.setIdValorFrequencia(idFrequencia);
		
		if(frequenciaForm.getJustificativa() != null && !frequenciaForm.getJustificativa().trim().equalsIgnoreCase("")){
			parametrosExcluirValorFrequencia.setJustificativa(frequenciaForm.getJustificativa());
		}
		excluirValorFrequenciaRequest.setParametrosExcluirValorFrequencia(parametrosExcluirValorFrequencia);

		try {
			frequenciaPTProxy.excluirValorFrequencia(excluirValorFrequenciaRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, FrequenciasAction.class.getName(), ex.getMessage(), frequenciaForm, response );
			return null;
		}
		
		return this.begin(mapping, form, request, response);
	}
}