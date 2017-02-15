package br.com.vivo.catalogoPRS.pageflows.param.produtos.tipoFrequencia;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.CriarFrequenciaForm;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaModeloPorTipoFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.BuscarListaTipoFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.CriarTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ExcluirTipoFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.FrequenciaPTProxy;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequenciaParametrosModeloPorTipoFrequenciaIn;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosCriarTipoFrequenciaTipoFrequenciaCriacao;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosExcluirTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosExcluirTipoFrequenciaTipoFrequenciaExclusao;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaModeloPorTipoFrequenciaModeloPorTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao;
import edu.emory.mathcs.backport.java.util.Arrays;

public class TipoFrequenciaAction extends BaseMappingDispatchAction {
	private static final long serialVersionUID = 1L;
	FrequenciaPTProxy frequenciaPTProxy = new FrequenciaPTProxy();

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		CriarFrequenciaForm formulario = (CriarFrequenciaForm) form;
		BuscarListaTipoFrequenciaRequest buscarListaTipoFrequenciaRequest = new BuscarListaTipoFrequenciaRequest();
		
		BuscarListaTipoFrequenciaResponse tipoFrequenciaList = null;
		try {
			tipoFrequenciaList =  frequenciaPTProxy.buscarListaTipoFrequencia(buscarListaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, TipoFrequenciaAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}	
		ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao[] resultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao = tipoFrequenciaList.getResultadoBuscarListaTipoFrequencia();
		List<ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao> tipoFrequenciaLista = Arrays.asList(resultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao);
		request.setAttribute("listaTipoFrequencia", tipoFrequenciaLista);
		request.setAttribute("listaTipoFrequenciaSize", tipoFrequenciaLista==null?0:tipoFrequenciaLista.size());

		return mapping.findForward("default");

	}

	public ActionForward addTipoFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		CriarFrequenciaForm formulario = (CriarFrequenciaForm) form;
		Long qtdeFrequencia = formulario.getQtdeFrequencia();
		String tipoFrequencia = formulario.getTipoFrequencia();
		
		BuscarListaTipoFrequenciaRequest buscarListaTipoFrequenciaRequest = new BuscarListaTipoFrequenciaRequest();
		CriarTipoFrequenciaRequest criarTipoFrequenciaRequest = new CriarTipoFrequenciaRequest();
		ParametrosCriarTipoFrequencia parametrosCriarTipoFrequencia = new ParametrosCriarTipoFrequencia();
		ParametrosCriarTipoFrequenciaTipoFrequenciaCriacao tipoFrequenciaCriacao = new ParametrosCriarTipoFrequenciaTipoFrequenciaCriacao();
		tipoFrequenciaCriacao.setNmTipoFrequencia(tipoFrequencia);
		tipoFrequenciaCriacao.setQtFrequencia(BigInteger.valueOf(qtdeFrequencia));
		parametrosCriarTipoFrequencia.setTipoFrequenciaCriacao(tipoFrequenciaCriacao);
		criarTipoFrequenciaRequest.setParametrosCriarTipoFrequencia(parametrosCriarTipoFrequencia);
		try{
			frequenciaPTProxy.criarTipoFrequencia(criarTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, TipoFrequenciaAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}
		
		BuscarListaTipoFrequenciaResponse tipoFrequenciaListResult = null;
		try{
			tipoFrequenciaListResult = frequenciaPTProxy.buscarListaTipoFrequencia(buscarListaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, TipoFrequenciaAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}
		
		ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao[] resultadoBuscarListaTipoFrequencia = tipoFrequenciaListResult.getResultadoBuscarListaTipoFrequencia();
		List<ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao> tipoFrequenciaLista = Arrays.asList(resultadoBuscarListaTipoFrequencia);
		request.setAttribute("listaTipoFrequencia", tipoFrequenciaLista);
		request.setAttribute("listaTipoFrequenciaSize", tipoFrequenciaLista==null?0:tipoFrequenciaLista.size());
		formulario.setQtdeFrequencia(null);
		formulario.setTipoFrequencia(null);
		
		return mapping.findForward("success");
	}

	public ActionForward abrirPopupApagarTipoFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		String idTipoFrequenciaString = request.getParameter("id_tipo_frequencia");
		request.setAttribute("id_tipo_frequencia", idTipoFrequenciaString);

		if (idTipoFrequenciaString != null && !idTipoFrequenciaString.trim().equals("")) {
			Long idTipoFrequencia = Long.valueOf(idTipoFrequenciaString);

			BuscarListaModeloPorTipoFrequenciaRequest buscarListaModeloPorTipoFrequenciaRequest = new BuscarListaModeloPorTipoFrequenciaRequest();
			ParametrosBuscarListaModeloPorTipoFrequencia parametrosBuscarListaModeloPorTipoFrequencia = new ParametrosBuscarListaModeloPorTipoFrequencia();
			ParametrosBuscarListaModeloPorTipoFrequenciaParametrosModeloPorTipoFrequenciaIn parametrosBuscarListaModeloPorTipoFrequenciaIn = new ParametrosBuscarListaModeloPorTipoFrequenciaParametrosModeloPorTipoFrequenciaIn();
			parametrosBuscarListaModeloPorTipoFrequenciaIn.setIdTipoFrequencia(idTipoFrequencia);
			parametrosBuscarListaModeloPorTipoFrequencia.setParametrosModeloPorTipoFrequenciaIn(parametrosBuscarListaModeloPorTipoFrequenciaIn);
			buscarListaModeloPorTipoFrequenciaRequest.setParametrosBuscarListaModeloPorTipoFrequencia(parametrosBuscarListaModeloPorTipoFrequencia);
			
			BuscarListaModeloPorTipoFrequenciaResponse listaModeloPorTipoFrequencia= null;
			try{
				listaModeloPorTipoFrequencia = frequenciaPTProxy.buscarListaModeloPorTipoFrequencia(buscarListaModeloPorTipoFrequenciaRequest, this.getUserName(), this.getPassword());
			}catch(AxisFault ex){
				ex.printStackTrace();
			}
			
			ResultadoBuscarListaModeloPorTipoFrequenciaModeloPorTipoFrequencia[] modeloPorTipoFrequenciaList = null;
			if (listaModeloPorTipoFrequencia != null) {
				modeloPorTipoFrequenciaList  = listaModeloPorTipoFrequencia.getResultadoBuscarListaModeloPorTipoFrequencia();
				
			}
			
			
			request.setAttribute("modelos_afetados", modeloPorTipoFrequenciaList);
		}

		return mapping.findForward("success");
	}

	public ActionForward apagarTipoFrequencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		CriarFrequenciaForm formulario = (CriarFrequenciaForm) form; 
		Long idTipoFrequencia = formulario.getIdEntidade();
		ExcluirTipoFrequenciaRequest excluirTipoFrequenciaRequest = new ExcluirTipoFrequenciaRequest();
		ParametrosExcluirTipoFrequencia parametrosExcluirTipoFrequencia = new ParametrosExcluirTipoFrequencia();
		ParametrosExcluirTipoFrequenciaTipoFrequenciaExclusao parametrosExcluirTipoFrequenciaTipoFrequenciaExclusao = new ParametrosExcluirTipoFrequenciaTipoFrequenciaExclusao();
		parametrosExcluirTipoFrequenciaTipoFrequenciaExclusao.setIdTipoFrequencia(idTipoFrequencia);
		
		if(formulario.getJustificativa() != null && !formulario.getJustificativa().trim().equalsIgnoreCase(""))
			parametrosExcluirTipoFrequenciaTipoFrequenciaExclusao.setJustificativa(formulario.getJustificativa());
		
		parametrosExcluirTipoFrequencia.setTipoFrequenciaExclusao(parametrosExcluirTipoFrequenciaTipoFrequenciaExclusao);
		excluirTipoFrequenciaRequest.setParametrosExcluirTipoFrequencia(parametrosExcluirTipoFrequencia);
		
		try{
			frequenciaPTProxy.excluirTipoFrequencia(excluirTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			ex.printStackTrace();
		}
		BuscarListaTipoFrequenciaRequest buscarListaTipoFrequenciaRequest = new BuscarListaTipoFrequenciaRequest();
		BuscarListaTipoFrequenciaResponse tipoFrequenciaList = null;
		
		try {
			tipoFrequenciaList =  frequenciaPTProxy.buscarListaTipoFrequencia(buscarListaTipoFrequenciaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, TipoFrequenciaAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}	
		ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao[] resultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao = tipoFrequenciaList.getResultadoBuscarListaTipoFrequencia();
		List<ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao> tipoFrequenciaLista = Arrays.asList(resultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao);
		request.setAttribute("listaTipoFrequencia", tipoFrequenciaLista);
		request.setAttribute("listaTipoFrequenciaSize", tipoFrequenciaLista==null?0:tipoFrequenciaLista.size());

		return mapping.findForward("success");
	}
}
