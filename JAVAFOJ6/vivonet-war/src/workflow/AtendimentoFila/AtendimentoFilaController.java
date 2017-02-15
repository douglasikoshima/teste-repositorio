package workflow.AtendimentoFila;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import workflow.AtendimentoWorkflow.AtendimentoWorkflowController.AtendimentoForm;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoValorVODocument.FormularioCampoValorVO;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterGrupo.ManterGrupoFacade;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO;
import br.com.vivo.fo.usuario.vo.GruposUsuarioVODocument.GruposUsuarioVO;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoFilaPesquisaVODocument.AtendimentoFilaPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoFilaVODocument.AtendimentoFilaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFEstadoVODocument.WFEstadoVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFPesquisaAvancadaComparacaoVODocument.WFPesquisaAvancadaComparacaoVO;
import br.com.vivo.fo.workflow.vo.WFPesquisaAvancadaVODocument.WFPesquisaAvancadaVO;
import br.com.vivo.fo.workflow.vo.WFRegionalVODocument.WFRegionalVO;
import br.com.vivo.fo.workflow.vo.WFSubEstadoVODocument.WFSubEstadoVO;
import br.com.vivo.fo.workflow.vo.impl.WFGrupoVODocumentImpl.WFGrupoVOImpl;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class AtendimentoFilaController extends AbstractAction {

	private static final long serialVersionUID = -3328115297117225477L;

	private static Logger log = new Logger("workfila");

	private AtendimentoFilaForm atendimentoFilaForm = new AtendimentoFilaForm();
	private static final int MINUTOS_ATUALIZACAO = 5;
	private static final String RAIZ_PRINCIPAL = "PAI";

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade atendimentoFacade;

	@EJB
	private ManterGrupoFacade controlGrupo;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.monitoramento.MonitoramentoFacade manFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.AtendimentoWorkflowFacade atendimentoWorkflowFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("beginResp".equals(mapping.getParameter())) {
			return beginResp(mapping, form, request, response);
		} else if ("beginCRI".equals(mapping.getParameter())) {
			return beginCRI(mapping, form, request, response);
		} else if ("pesquisarGrupos".equals(mapping.getParameter())) {
			return pesquisarGrupos(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("pesquisarAvancado".equals(mapping.getParameter())) {
			return pesquisarAvancado(mapping, form, request, response);
		} else if ("adicionarItem".equals(mapping.getParameter())) {
			return adicionarItem(mapping, form, request, response);
		} else if ("excluirItem".equals(mapping.getParameter())) {
			return excluirItem(mapping, form, request, response);
		} else if ("limparPesquisaAvancada".equals(mapping.getParameter())) {
			return limparPesquisaAvancada(mapping, form, request, response);
		} else if ("obterUsuario".equals(mapping.getParameter())) {
			return obterUsuario(mapping, form, request, response);
		} else if ("obterSubEstado".equals(mapping.getParameter())) {
			return obterSubEstado(mapping, form, request, response);
		} else if ("obterCampo".equals(mapping.getParameter())) {
			return obterCampo(mapping, form, request, response);
		} else if ("obterValoresDominio".equals(mapping.getParameter())) {
			return obterValoresDominio(mapping, form, request, response);
		} else if ("encaminhar".equals(mapping.getParameter())) {
			return encaminhar(mapping, form, request, response);
		} else if ("trocar".equals(mapping.getParameter())) {
			return trocar(mapping, form, request, response);
		} else if ("suspeito".equals(mapping.getParameter())) {
			return suspeito(mapping, form, request, response);
		} else if ("obterAlerta".equals(mapping.getParameter())) {
			return obterAlerta(mapping, form, request, response);
		} else if ("obterArvoreContato".equals(mapping.getParameter())) {
			return obterArvoreContato(mapping, form, request, response);
		} else if ("expandeArvoreContato".equals(mapping.getParameter())) {
			return expandeArvoreContato(mapping, form, request, response);
		} else if ("obterComboCampos".equals(mapping.getParameter())) {
			return obterComboCampos(mapping, form, request, response);
		} else if ("gerarDetalhe".equals(mapping.getParameter())) {
			return gerarDetalhe(mapping, form, request, response);
		} else if ("AtendimentoDetalheDone".equals(mapping.getParameter())) {
			return AtendimentoDetalheDone(mapping, form, request, response);
		} else if ("AtendimentoWorkflowDone".equals(mapping.getParameter())) {
			return AtendimentoWorkflowDone(mapping, form, request, response);
		} else if ("contarTotalProcessos".equals(mapping.getParameter())) {
			return contarTotalProcessos(mapping, form, request, response);
		} else if ("pesquisarTotalResultados".equals(mapping.getParameter())) {
			return pesquisarTotalResultados(mapping, form, request, response);
		} else if ("pesquisarAvancadoTotalResultados".equals(mapping.getParameter())) {
			return pesquisarAvancadoTotalResultados(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward beginCRI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoFilaController:beginCRI.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("inCRI",ConstantesCRM.STWO);
		log.debug("AtendimentoFilaController:beginCRI.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward beginResp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoFilaController:beginResp.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));

		if ("inBoxResp".equals(request.getParameter("source"))) {
			atendimentoFilaForm = new AtendimentoFilaForm();
			if (request.getParameter("adq_tipoPesquisa").equals("NOME") || request.getParameter("adq_tipoPesquisa").equals("RAZAO")) {
				atendimentoFilaForm.getRwfInboxPesquisaVO().setInTipoPesquisa(ConstantesCRM.STHREE);
				atendimentoFilaForm.getRwfInboxPesquisaVO().setDocumento(request.getParameter("adq_valorPesquisa"));

			} else if (request.getParameter("adq_tipoPesquisa").equals("CPF") || request.getParameter("adq_tipoPesquisa").equals("CNPJ")) {
				atendimentoFilaForm.getRwfInboxPesquisaVO().setInTipoPesquisa(ConstantesCRM.SFOUR);
				atendimentoFilaForm.getRwfInboxPesquisaVO().setTipoDocumento(request.getParameter("adq_tipoPesquisa"));

				if (request.getParameter("adq_tipoPesquisa").equals("CPF")) {
					atendimentoFilaForm.getRwfInboxPesquisaVO().setDocumento(request.getParameter("adq_valorPesquisa").replaceAll("[\\.\\.\\-\\/]*", ConstantesCRM.SVAZIO));

				} else {
					atendimentoFilaForm.getRwfInboxPesquisaVO().setDocumento(request.getParameter("adq_valorPesquisa").replaceAll("[\\.\\.\\/\\-\\/]*", ConstantesCRM.SVAZIO));
				}

			} else {
				atendimentoFilaForm.getRwfInboxPesquisaVO().setInTipoPesquisa(request.getParameter("adq_tipoPesquisa"));
				atendimentoFilaForm.getRwfInboxPesquisaVO().setDocumento(request.getParameter("adq_valorPesquisa").replaceAll("[\\(\\)\\.\\-\\/]*", ConstantesCRM.SVAZIO));
			}

			log.debug("AtendimentoInBoxRespostaController:beginResp.do - [inTipoPesquisa = " + atendimentoFilaForm.getRwfInboxPesquisaVO().getInTipoPesquisa() + "]");
			log.debug("AtendimentoInBoxRespostaController:beginResp.do - [documento = " + atendimentoFilaForm.getRwfInboxPesquisaVO().getDocumento() + "]");
			request.getSession().setAttribute("inPesquisaFila", ConstantesCRM.SONE);
		}

		f.addParameter("inResp",ConstantesCRM.SONE);

		log.debug("AtendimentoFilaController:beginResp.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarGrupos.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:begin.do - Inicio do Metodo]");
		log.debug("AtendimentoInBoxRespostaController:begin.do - [inTipoPesquisa = " + atendimentoFilaForm.getRwfInboxPesquisaVO().getInTipoPesquisa() + "]");

		if (!ConstantesCRM.SONE.equals(request.getSession().getAttribute("inPesquisaFila"))) {
			atendimentoFilaForm = new AtendimentoFilaForm();
		}

		if (atendimentoFilaForm.getOptGrpSel() == null) {
			atendimentoFilaForm.setOptGrpSel(ConstantesCRM.SZERO);
		}

		String inCRI = form.getInCRI();
		inCRI = inCRI == null ? ConstantesCRM.SZERO : inCRI;
		inCRI = inCRI == ConstantesCRM.SVAZIO ? ConstantesCRM.SZERO : inCRI;
		form.setInCRI(inCRI);
		log.debug("AtendimentoInBoxRespostaController:begin.do - [inCRI = " + form.getInCRI() + "]");

		String inResp = form.getInResp();
		inResp = inResp == null ? ConstantesCRM.SZERO : inResp;
		inResp = inResp == ConstantesCRM.SVAZIO ? ConstantesCRM.SZERO : inResp;
		form.setInResp(inResp);
		log.debug("AtendimentoInBoxRespostaController:begin.do - [inResp = " + form.getInResp() + "]");

		this.atendimentoFilaForm.setInResp(form.getInResp());
		this.atendimentoFilaForm.setInCRI(form.getInCRI());
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));

		if (request.getParameter("voltar") != null && request.getParameter("voltar").equals(ConstantesCRM.SONE)) {

			int nCampos = (form.getPsqIdCampo() != null ? form.getPsqIdCampo().length : 0);
			if (nCampos > 0) {
				WFPesquisaAvancadaVO pesquisaAvancadaVO = WFPesquisaAvancadaVO.Factory.newInstance();
				pesquisaAvancadaVO.setNrCampos(String.valueOf(nCampos));
				for (int i = 0; i < nCampos; i++) {
					WFPesquisaAvancadaComparacaoVO compVO = pesquisaAvancadaVO.addNewWFPesquisaAvancadaComparacaoVO();
					compVO.setDsComparacao(ConstantesCRM.SVAZIO);
					compVO.setIdCampo(form.getPsqIdCampo()[i]);
					compVO.setNmCampo(form.getPsqNmCampo()[i]);
					compVO.setIdFormularioCampoValor(form.getPsqIdFormularioCampoValor()[i]);
					compVO.setTpComparacao(form.getPsqTpComparacao()[i]);
					compVO.setDsComparacao(form.getPsqDsComparacao()[i]);
					compVO.setValor(form.getPsqValor()[i]);
				}
				atendimentoFilaForm.getAtdFilaPesqVO().setWFPesquisaAvancadaVO(pesquisaAvancadaVO);
			}

			f.addParameter("voltar",ConstantesCRM.SONE);
		}
		log.debug("AtendimentoFilaController:begin.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);		
		return this.pesquisarGrupos(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="WorkflowFila.jsp"
	 * @jpf:forward name="successFromInBox" path="WorkflowFilaInboxRC.jsp"
	 */
	protected ActionForward pesquisarGrupos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:pesquisarGrupos.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:pesquisarGrupos.do - [user = " + user + "]");

		WFSubEstadoVO wFSubEstadosVO[];
		UsuarioVIVO usuariosVIVO[];

		if (this.atendimentoFilaForm.atendimentoInformacaoVO != null) {
			// Guarda os arrays de SubEstados e UsuariosVIVO anteriores, pois não serão retornados na pesquisa a seguir
			wFSubEstadosVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getWFSubEstadoVOArray();
			usuariosVIVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getUsuarioVIVOArray();
		} else {
			wFSubEstadosVO = new WFSubEstadoVO[0];
			usuariosVIVO = new UsuarioVIVO[0];
		}
		// Atribui para os objetos a seleçao do usuário
		if (request.getParameter("source") == null) {
			this.atribuiAtdFilaPesqVO(form);
		}
		WFGrupoVO[] grupos = new WFGrupoVOImpl[0];
		WFRegionalVO[] regionais = new WFRegionalVO[0];
		MonitoramentoPesquisaVO monitoramentoPesquisaVO;
		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			// Pega os dados do Grupo a ser pesquisado
			GrupoUsuarioVO grupoPesquisa = GrupoUsuarioVO.Factory.newInstance();
			grupoPesquisa.setIdGrupo(ConstantesCRM.SVAZIO);
			grupoPesquisa.setDsGrupo(ConstantesCRM.SVAZIO);
			grupoPesquisa.setIdTipoGrupoSelecionado(ConstantesCRM.SVAZIO);
			grupoPesquisa.setDsTipoGrupoSelecionado("CRI");
			grupoPesquisa.setIdUsuario(ConstantesCRM.getCurrentUser(request.getSession()));
			// Busca a lista de grupos que atendem a pesquisa feita
			GruposUsuarioVO arrayGruposVO = controlGrupo.pesquisaGrupo(grupoPesquisa, user);
			grupos = null;
			grupos = new WFGrupoVOImpl[arrayGruposVO.getGrupoUsuarioVOArray().length];
			for (int i = 0; i < arrayGruposVO.getGrupoUsuarioVOArray().length; i++) {
				grupos[i] = WFGrupoVO.Factory.newInstance();
				grupos[i].setDsGrupo(arrayGruposVO.getGrupoUsuarioVOArray(i).getDsGrupo());
				grupos[i].setIdGrupo(arrayGruposVO.getGrupoUsuarioVOArray(i).getIdGrupo());
			}
			monitoramentoPesquisaVO = MonitoramentoPesquisaVO.Factory.newInstance();
			monitoramentoPesquisaVO = manFacade.obtemGrupos(user);
			regionais = monitoramentoPesquisaVO.getWFRegionalVOArray();
			this.atendimentoFilaForm.setAtendimentoInformacaoVO(AtendimentoInformacaoVO.Factory.newInstance());
			this.atendimentoFilaForm.atendimentoInformacaoVO.setWFGrupoVOArray(grupos);
		} else if (form.getInResp().equals(ConstantesCRM.SONE)) {
			monitoramentoPesquisaVO = MonitoramentoPesquisaVO.Factory.newInstance();
			monitoramentoPesquisaVO = manFacade.obtemGruposRC(user, 0);
			grupos = null;
			grupos = monitoramentoPesquisaVO.getWFGrupoVOArray();
			this.atendimentoFilaForm.setAtendimentoInformacaoVO(AtendimentoInformacaoVO.Factory.newInstance());
			this.atendimentoFilaForm.atendimentoInformacaoVO.setWFGrupoVOArray(grupos);
			regionais = monitoramentoPesquisaVO.getWFRegionalVOArray();
		} else {
			monitoramentoPesquisaVO = MonitoramentoPesquisaVO.Factory.newInstance();
			monitoramentoPesquisaVO = manFacade.obtemGrupos(user);
			grupos = null;
			grupos = monitoramentoPesquisaVO.getWFGrupoVOArray();
			this.atendimentoFilaForm.setAtendimentoInformacaoVO(AtendimentoInformacaoVO.Factory.newInstance());
			this.atendimentoFilaForm.atendimentoInformacaoVO.setWFGrupoVOArray(grupos);
			regionais = monitoramentoPesquisaVO.getWFRegionalVOArray();
		}
		this.atendimentoFilaForm.atendimentoInformacaoVO.setWFRegionalVOArray(regionais);
		// atribui o conteúdo do combo sub-estado que havia sido salvo antes da chamada
		this.atendimentoFilaForm.atendimentoInformacaoVO.setWFSubEstadoVOArray(wFSubEstadosVO);
		// atribui o conteúdo do combo usuario que havia sido salvo antes da chamada
		this.atendimentoFilaForm.atendimentoInformacaoVO.setUsuarioVIVOArray(usuariosVIVO);
		this.atendimentoFilaForm.setAtualizacao(MINUTOS_ATUALIZACAO);
		// chama serviço para efetuar trazer os valores da pesquisa avançada
		this.atendimentoFilaForm.setFormularioVO(atendimentoFacade.obtemFormularioVO(user));
		this.atendimentoFilaForm.setInCRI(form.getInCRI());
		log.debug("AtendimentoInBoxRespostaController:pesquisarGrupos.do - [inCRI = " + form.getInCRI() + "]");
		this.atendimentoFilaForm.setInResp(form.getInResp());
		log.debug("AtendimentoInBoxRespostaController:pesquisarGrupos.do - [inResp = " + form.getInResp() + "]");
		
		if ((request.getParameter("source") != null
				&& request.getParameter("source").toString().equalsIgnoreCase("inBoxResp"))
				|| ConstantesCRM.SONE.equals(form.getInResp())) {

			request.getSession().removeAttribute("inOrigemFila");
			log.debug("AtendimentoFilaController:pesquisarGrupos.do - Fim do Metodo]");

			request.getSession().setAttribute("atendimentoFilaForm", this.atendimentoFilaForm);

			ActionRedirect ar = new ActionRedirect("/workflow/AtendimentoFila/WorkflowFilaInboxRC.jsp");
			return ar;

		} else {
			request.getSession().setAttribute("inOrigemFila", ConstantesCRM.SONE);
			log.debug("AtendimentoFilaController:pesquisarGrupos.do - Fim do Metodo]");
			LoadEstados(form, request);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RFPesquisa.jsp"
	 * @jpf:forward name="successInbox" path="RFPesquisaMirrorInBox.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:pesquisar.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:pesquisar.do - [user = " + user + "]");

		WFSubEstadoVO wFSubEstadosVO[];
		UsuarioVIVO usuariosVIVO[];

		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			form.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/beginCRI.do");
		} else if (ConstantesCRM.SONE.equals(form.getInResp())) {
			form.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/beginResp.do");
			form.setInResp(ConstantesCRM.SONE);
			atendimentoFilaForm.setInResp(ConstantesCRM.SONE);
		} else {
			form.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/begin.do");
		}

		if (this.atendimentoFilaForm.atendimentoInformacaoVO != null) {
			// Guarda os arrays de SubEstados e UsuariosVIVO anteriores, pois não serão retornados na pesquisa a seguir
			wFSubEstadosVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getWFSubEstadoVOArray();
			usuariosVIVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getUsuarioVIVOArray();
		} else {
			wFSubEstadosVO = new WFSubEstadoVO[0];
			usuariosVIVO = new UsuarioVIVO[0];
		}

		// Guarda o formularioVO já obtido do serviço
		form.setFormularioVO(this.atendimentoFilaForm.getFormularioVO());
		// atribui 0 (zero) para o número de campos da pesquisa avançada
		atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO().setNrCampos(ConstantesCRM.SZERO);
		form.getAtdFilaPesqVO().setWFPesquisaAvancadaVO(atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO());

		if (!ConstantesCRM.SONE.equals(request.getSession().getAttribute("inPesquisaFila"))) {
			this.atendimentoFilaForm = form;
			// Atribui para os objetos a seleçao do usuário
			this.atribuiAtdFilaPesqVO(form);
		} else {
			request.getSession().removeAttribute("inPesquisaFila");
		}

		// chama servico para efetuar a pesquisa da fila
		boolean refinarPesquisa = false;

		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			this.atendimentoFilaForm.setAtendimentoInformacaoVO(atendimentoFacade.psqFilaCRI(user, this.getAtendimentoFilaForm().getAtdFilaPesqVO()));
		} else if (form.getInResp() != null && form.getInResp().equals(ConstantesCRM.SONE)) {
			if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
				this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInPesqUsuario(ConstantesCRM.SONE);
			}
			this.atendimentoFilaForm.setAtendimentoInformacaoVO(atendimentoFacade.psqFilaResp(user, this.getAtendimentoFilaForm().getRwfInboxPesquisaVO()));
		} else {
			try {
				this.atendimentoFilaForm.setAtendimentoInformacaoVO(atendimentoFacade.obtemAtendimentoInformacaoVO(user, this.getAtendimentoFilaForm().getAtdFilaPesqVO()));
			} catch (FacadeException fcdEx) {
				this.atendimentoFilaForm.setAtendimentoInformacaoVO(AtendimentoInformacaoVO.Factory.newInstance());
				this.atendimentoFilaForm.getAtendimentoInformacaoVO().setTotalRegistros(ConstantesCRM.SZERO);
				this.atendimentoFilaForm.getAtendimentoInformacaoVO().setNrRegistros(0);
				refinarPesquisa = true;
			}
		}

		// atribui o conteúdo do combo sub-estado que havia sido salvo antes da chamada
		this.atendimentoFilaForm.atendimentoInformacaoVO.setWFSubEstadoVOArray(wFSubEstadosVO);
		// atribui o conteúdo do combo usuario que havia sido salvo antes da chamada
		this.atendimentoFilaForm.atendimentoInformacaoVO.setUsuarioVIVOArray(usuariosVIVO);
		this.atendimentoFilaForm.getAtendimentoInformacaoVO().setAtualizacao(Integer.toString(MINUTOS_ATUALIZACAO) + " min.");
		this.atendimentoFilaForm.setAtualizacao(MINUTOS_ATUALIZACAO);

		if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
			log.debug("AtendimentoFilaController:pesquisar.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("successInbox");
		} else {
			ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
			if (refinarPesquisa) {
				f.addParameter("erro","Quantidade de processos muito grande!\\nRefine a pesquisa.");
			}
			log.debug("AtendimentoFilaController:pesquisar.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			//return f;
			
			request.setAttribute("erro","Quantidade de processos muito grande!\\nRefine a pesquisa.");			
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RFPesquisa.jsp"
	 */
	protected ActionForward pesquisarAvancado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:pesquisarAvancado.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFSubEstadoVO wFSubEstadosVO[];
		UsuarioVIVO usuariosVIVO[];

		if (this.atendimentoFilaForm.atendimentoInformacaoVO != null) {
			// Guarda o array de SubEstados anterior, pois não será retornado na pesquisa a seguir
			wFSubEstadosVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getWFSubEstadoVOArray();
			usuariosVIVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getUsuarioVIVOArray();
		} else {
			wFSubEstadosVO = new WFSubEstadoVO[0];
			usuariosVIVO = new UsuarioVIVO[0];
		}

		// Retém o conteúdo de valores associado ao campo de multipla escolha selecionado
		form.setCamposValorVO(this.atendimentoFilaForm.getCamposValorVO());

		// Guarda o formularioVO já obtido do serviço
		form.setFormularioVO(this.atendimentoFilaForm.getFormularioVO());

		// Guarda a seleção feita da pesquisa avançada
		form.getAtdFilaPesqVO().setWFPesquisaAvancadaVO(this.atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO());
		form.getAtdFilaPesqVO().getWFPesquisaAvancadaVO().setNrCampos(String.valueOf(atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO().getWFPesquisaAvancadaComparacaoVOArray().length));

		this.atendimentoFilaForm = form;

		// Atribui para os objetos a seleçao do usuário
		this.atribuiAtdFilaPesqVO(form);

		// chamada do servico de pesquisa de fila
		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			// chama a pesquisa avançada de Fila CRI
			this.atendimentoFilaForm.setAtendimentoInformacaoVO(atendimentoFacade.psqFilaCRI(user, this.getAtendimentoFilaForm().getAtdFilaPesqVO()));
		} else {
			// chama a pesquisa avançada de Fila de Processos
			this.atendimentoFilaForm.setAtendimentoInformacaoVO(atendimentoFacade.obtemAtendimentoInformacaoVO(user, this.atendimentoFilaForm.getAtdFilaPesqVO()));
		}

		// atribui o conteúdo do combo sub-estado que havia sido salvo antes da chamada
		this.atendimentoFilaForm.atendimentoInformacaoVO.setWFSubEstadoVOArray(wFSubEstadosVO);

		// atribui o conteúdo do combo usuario que havia sido salvo antes da chamada
		this.atendimentoFilaForm.atendimentoInformacaoVO.setUsuarioVIVOArray(usuariosVIVO);

		this.atendimentoFilaForm.getAtendimentoInformacaoVO().setAtualizacao(Integer.toString(MINUTOS_ATUALIZACAO) + " min.");
		this.atendimentoFilaForm.setAtualizacao(MINUTOS_ATUALIZACAO);

		log.debug("AtendimentoFilaController:pesquisarAvancado.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RFCampos.jsp"
	 * @jpf:forward name="failure" path="WorkflowFila.jsp"
	 */
	protected ActionForward adicionarItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:adicionarItem.do - Inicio do Metodo]");
		// Retém a seleção da pesquisa avançada
		this.atendimentoFilaForm.setFormularioCampoSel(form.getFormularioCampoSel());
		this.atendimentoFilaForm.setComparacaoSel(form.getComparacaoSel());
		this.atendimentoFilaForm.setValorSel(form.getValorSel());
		this.atendimentoFilaForm.setValor(form.getValor());
		this.atendimentoFilaForm.setAbaSelecionada(form.getAbaSelecionada());

		// Obtém o objeto da pesquisa avançada
		WFPesquisaAvancadaVO pesquisaAvancadaVO = this.atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO();

		WFPesquisaAvancadaComparacaoVO[] itensPesquisa = pesquisaAvancadaVO.getWFPesquisaAvancadaComparacaoVOArray();

		// Verifica se campo já existe na seleção
		for (int i = 0; itensPesquisa != null && i < itensPesquisa.length; i++) {
			if (itensPesquisa[i].getIdCampo().equals(form.getFormularioCampoSel())) {
				LoadEstados(form, request);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("failure");
			}
		}

		// cria novo item na coleção
		WFPesquisaAvancadaComparacaoVO compVO = pesquisaAvancadaVO.addNewWFPesquisaAvancadaComparacaoVO();
		compVO.setIdCampo(form.getFormularioCampoSel());
		compVO.setTpComparacao(form.getComparacaoSel());

		// Obtém a coleção de itens da seleção do usuário
		FormularioCampoVO[] camposVO = this.atendimentoFilaForm.getFormularioVO().getFormularioCampoVOArray();

		// obtém a descriçao do campo
		for (int i = 0; camposVO != null && i < camposVO.length; i++) {
			if (camposVO[i].getIdCampo() == Integer.parseInt(form.getFormularioCampoSel())) {
				compVO.setNmCampo(camposVO[i].getNmCampo());
				break;
			}
		}

		// seta a descrição do tipo de comparação para "="
		if (form.getComparacaoSel().equals(ConstantesCRM.SZERO)) {
			compVO.setDsComparacao("=");
		}

		// seta a descrição do tipo de comparação para "<>"
		if (form.getComparacaoSel().equals(ConstantesCRM.SONE)) {
			compVO.setDsComparacao("<>");
		}

		// seta a descrição do tipo de comparação para "contém"
		if (form.getComparacaoSel().equals(ConstantesCRM.STWO)) {
			compVO.setDsComparacao("contém");
		}

		// obtém a descrição do valor se o campo selecionado utiliza a combo de valores
		FormularioCampoValorVO[] camposValorVO = this.atendimentoFilaForm.getCamposValorVO();
		if (camposValorVO != null && camposValorVO.length > 0) {
			// neste caso, seta o id do valor para o campo do formulario
			compVO.setIdFormularioCampoValor(form.getValorSel());

			for (int i = 0; i < camposValorVO.length; i++) {
				if (camposValorVO[i].getIdFormularioCampoValor().trim().equals(form.getValorSel().trim())) {
					compVO.setValor(camposValorVO[i].getValor());
					compVO.setTpComparacao(ConstantesCRM.SZERO);
					compVO.setDsComparacao("=");
					break;
				}
			}
		} else {
			// obtém a descriçao do valor, do valor informado no campo text
			compVO.setValor(form.getValor());
		}

		// atribui o número de campos
		pesquisaAvancadaVO.setNrCampos(String.valueOf(pesquisaAvancadaVO.sizeOfWFPesquisaAvancadaComparacaoVOArray()));

		// inicializa os campos da seleção da pesquisa
		this.atendimentoFilaForm.setFormularioCampoSel("-1");
		this.atendimentoFilaForm.setComparacaoSel(ConstantesCRM.SZERO);
		this.atendimentoFilaForm.setValorSel("-1");
		this.atendimentoFilaForm.setValor(ConstantesCRM.SVAZIO);
		this.atendimentoFilaForm.setCamposValorVO(new FormularioCampoValorVO[0]);
		log.debug("AtendimentoFilaController:adicionarItem.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RFCampos.jsp"
	 */
	protected ActionForward excluirItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:excluirItem.do - Inicio do Metodo]");
		// Retém a seleção da pesquisa avançada
		this.atendimentoFilaForm.setFormularioCampoSel(form.getFormularioCampoSel());
		this.atendimentoFilaForm.setComparacaoSel(form.getComparacaoSel());
		this.atendimentoFilaForm.setValorSel(form.getValorSel());
		this.atendimentoFilaForm.setValor(form.getValor());
		this.atendimentoFilaForm.setAbaSelecionada(form.getAbaSelecionada());

		String item = request.getParameter("itemExcluir");

		if (item != null) {
			WFPesquisaAvancadaVO pesquisaAvancadaVO = this.atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO();
			// Exclui item selecionado
			pesquisaAvancadaVO.removeWFPesquisaAvancadaComparacaoVO(Integer.parseInt(item));
			// seta o número de campos
			pesquisaAvancadaVO.setNrCampos(String.valueOf(pesquisaAvancadaVO.sizeOfWFPesquisaAvancadaComparacaoVOArray()));
		}
		log.debug("AtendimentoFilaController:excluirItem.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RFCampos.jsp"
	 */
	protected ActionForward limparPesquisaAvancada(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoFilaController:limparPesquisaAvancada.do - Inicio do Metodo]");
		// inicializa os campos da seleção da pesquisa
		this.atendimentoFilaForm.setFormularioCampoSel("-1");
		this.atendimentoFilaForm.setComparacaoSel(ConstantesCRM.SZERO);
		this.atendimentoFilaForm.setValorSel("-1");
		this.atendimentoFilaForm.setValor(ConstantesCRM.SVAZIO);
		this.atendimentoFilaForm.getAtdFilaPesqVO().setWFPesquisaAvancadaVO(WFPesquisaAvancadaVO.Factory.newInstance());
		this.atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO().setWFPesquisaAvancadaComparacaoVOArray(new WFPesquisaAvancadaComparacaoVO[0]);
		log.debug("AtendimentoFilaController:limparPesquisaAvancada.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iUsuario.jsp"
	 */
	protected ActionForward obterUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:obterUsuario.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		if (form.getGrupoSel().equals("-1")) {
			// Se selecionou o grupo em branco, inicializa o combo de usuarios
			this.atendimentoFilaForm.getAtendimentoInformacaoVO().setUsuarioVIVOArray(new UsuarioVIVO[0]);
		} else {
			// Obtém os usuarios para o grupo selecionado
			this.atendimentoFilaForm.getAtendimentoInformacaoVO().setUsuarioVIVOArray(atendimentoWorkflowFacade.obtemUsuarioVIVO(user, form.getGrupoSel(), ConstantesCRM.SONE, null, false));
		}
		log.debug("AtendimentoFilaController:obterUsuario.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iWorkflowSubEstado.jsp"
	 */
	protected ActionForward obterSubEstado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:obterSubEstado.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		if (form.estadoSel.equals("-1")) {
			// Se selecionou o estado em branco, inicializa o combo de sub-estados
			this.atendimentoFilaForm.getAtendimentoInformacaoVO().setWFSubEstadoVOArray(new WFSubEstadoVO[0]);
		} else {
			// Obtém os sub-estados para o estado selecionado
			this.atendimentoFilaForm.getAtendimentoInformacaoVO().setWFSubEstadoVOArray(atendimentoFacade.obtemSubEstadoVO(user, this.getEstadoVO(form)));
		}
		log.debug("AtendimentoFilaController:obterSubEstado.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iCampoValor.jsp"
	 */
	protected ActionForward obterCampo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:obterCampo.do - Inicio do Metodo]");
		FormularioCampoVO[] formularioCampos = atendimentoFilaForm.getFormularioVO().getFormularioCampoVOArray();
		for (int i = 0; i < formularioCampos.length; i++) {
			if (formularioCampos[i].getIdCampo() == Integer.parseInt(form.getFormularioCampoSel())) {
				if (formularioCampos[i].getIdContatoFolhaCampo() == 0) {
					// Se selecionou o formularioCampo em branco, inicializa o combo de valor ou o text
					this.atendimentoFilaForm.setCamposValorVO(new FormularioCampoValorVO[0]);
					this.atendimentoFilaForm.setTipoCampo("S");
				} else {
					this.atendimentoFilaForm.setTipoCampo("D");
				}
				break;
			}
		}
		log.debug("AtendimentoFilaController:obterCampo.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iValoresDominio.jsp"
	 */
	protected ActionForward obterValoresDominio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:obterValoresDominio.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FormularioVO formulario = atendimentoFacade.obtemValoresDominio(user, form.getFormularioCampoSel(), form.getTipoLinhaSel(), form.getRegionalSel());

		if ((formulario != null) && (formulario.getFormularioCampoVOArray().length > 0)) {
			atendimentoFilaForm.setCamposValorVO(formulario.getFormularioCampoVOArray()[0].getFormularioCampoValorVOArray());
		} else {
			atendimentoFilaForm.setCamposValorVO(new FormularioCampoValorVO[0]);
		}
		log.debug("AtendimentoFilaController:obterValoresDominio.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoWorkflow/listaEncaminhar.do"
	 * @jpf:forward name="failure" path="failure.jsp"
	 */
	protected ActionForward encaminhar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:encaminhar.do - Inicio do Metodo]");
		AtendimentoForm atendimentoForm = this.getAtendimentoForm(request);
		atendimentoForm.setIdAtividade(ConstantesCRM.STHREE);
		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			atendimentoForm.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/beginCRI.do");
			atendimentoForm.setIdAtividade("26");
			atendimentoForm.setInCRI(ConstantesCRM.STWO);
		} else if (ConstantesCRM.SONE.equals(form.getInResp())) {
			atendimentoForm.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/beginResp.do");
			atendimentoForm.setIdAtividade(ConstantesCRM.STHREE);
			atendimentoForm.setInRC(ConstantesCRM.SONE);
		} else {
			atendimentoForm.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/begin.do");
			atendimentoForm.setInCRI(ConstantesCRM.SZERO);
		}
		if (atendimentoForm.getAtendimentosVO() != null && atendimentoForm.getAtendimentosVO().length > 0) {
			log.debug("AtendimentoFilaController:encaminhar.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);	
			request.getSession().setAttribute("atendimentoForm", atendimentoForm);
			ActionRedirect action = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
			action.addParameter("fila",atendimentoForm.getFila());
			action.addParameter("idAtividade", atendimentoForm.getIdAtividade());
			action.addParameter("inCRI", atendimentoForm.getInCRI());
			action.addParameter("inCR", atendimentoForm.getInRC());								
			return action;
		} else {
			log.debug("AtendimentoFilaController:encaminhar.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);			
			ActionRedirect action = new ActionRedirect(mapping.findForward("failure"));
			return action;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoWorkflow/listaTrocar.do"
	 * @jpf:forward name="failure" path="failure.jsp"
	 */
	protected ActionForward trocar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:trocar.do - Inicio do Metodo]");
		AtendimentoForm atendimentoForm = this.getAtendimentoForm(request);
		atendimentoForm.setIdAtividade("25");
		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			atendimentoForm.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/beginCRI.do");
			atendimentoForm.setInCRI(ConstantesCRM.STWO);
		} else {
			atendimentoForm.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/begin.do");
			atendimentoForm.setInCRI(ConstantesCRM.SZERO);
		}
		if (atendimentoForm.getAtendimentosVO() != null && atendimentoForm.getAtendimentosVO().length > 0) {
			log.debug("AtendimentoFilaController:trocar.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} else {
			log.debug("AtendimentoFilaController:trocar.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoWorkflow/listaSuspeito.do"
	 * @jpf:forward name="failure" path="failure.jsp"
	 */
	protected ActionForward suspeito(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:suspeito.do - Inicio do Metodo]");
		AtendimentoForm atendimentoForm = this.getAtendimentoForm(request);
		atendimentoForm.setIdAtividade(ConstantesCRM.SSIX);
		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			atendimentoForm.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/beginCRI.do");
		} else {
			atendimentoForm.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/begin.do");
		}
		if (atendimentoForm.getAtendimentosVO() != null && atendimentoForm.getAtendimentosVO().length > 0) {
			log.debug("AtendimentoFilaController:suspeito.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			ActionRedirect action = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
			action.addParameter("idAtividade", atendimentoForm.getIdAtividade());
			action.addParameter("idAtendimento", atendimentoForm.getIdAtendimento());
			action.addParameter("inCRI", atendimentoForm.getInCRI());
			request.getSession().setAttribute("atendimentoForm", atendimentoForm);
			return action;
		} else {
			log.debug("AtendimentoFilaController:suspeito.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iAlertas.jsp"
	 */
	protected ActionForward obterAlerta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoFilaController:obterAlerta.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// obtém o idAtendimento para pesquisar os alertas
		String idAtendimento = request.getParameter("idAtendimento");

		if (idAtendimento != null) {
			// Obtém os alertas para o idAtendimento
			this.atendimentoFilaForm.setAlertasVO(atendimentoFacade.obtemAlertaVO(user, idAtendimento, ConstantesCRM.SONE));
		}
		log.debug("AtendimentoFilaController:obterAlerta.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iArvoreContato.jsp"
	 */
	protected ActionForward obterArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoFilaController:obterArvoreContato.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, null);

		if (admContatoFolhaVO == null) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		StringBuffer sbScript = new StringBuffer(ConstantesCRM.SVAZIO);
		sbScript.append("if (document.getElementById) {\n\nvar tree = new WebFXTree('");
		sbScript.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getNmContato()));
		sbScript.append("',\"Javascript:selecionaContato('");
		sbScript.append(admContatoFolhaVO.getIdContato());
		sbScript.append("', '");
		sbScript.append("','','" + RAIZ_PRINCIPAL + "');\",'classic');\n\n");

		StringBuffer sbNode = new StringBuffer(1024);

		for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {
			sbNode.setLength(0);
			String idContato = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato();
			String sInFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha();
			String nmContato = StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato());
			String dsPath = StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath());
			String ico1 = ConstantesCRM.SVAZIO;
			String ico2 = ConstantesCRM.SVAZIO;
			String dblClickScript = "Javascript:itemDblClick('" + idContato + "', '" + sInFolha + "', '" + dsPath + "')";

			if (!admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals(ConstantesCRM.SONE)) {
				ico1 = "/FrontOfficeWeb/resources/images/foldericon.png";
				ico2 = "/FrontOfficeWeb/resources/images/openfoldericon.png";
				dblClickScript = ConstantesCRM.SVAZIO;
			}

			sbNode.append("var arvore = new WebFXTreeItem('");
			sbNode.append(nmContato);
			sbNode.append("',\"Javascript:selecionaContato('");
			sbNode.append(idContato);
			sbNode.append("','");
			sbNode.append(sInFolha);
			sbNode.append("','");
			sbNode.append(dsPath);
			sbNode.append("','");
			sbNode.append("');\",'','").append(ico1).append("','").append(ico2).append("','',\"");
			sbNode.append(dblClickScript).append("\");\n");

			sbScript.append(sbNode.toString());
			sbScript.append("tree.add(arvore);\n");
		}
		sbScript.append("document.write(tree);\n}\n");

		AtendimentoFilaForm form = new AtendimentoFilaForm();
		form.setScriptArvore(sbScript.toString());
		this.atendimentoFilaForm.setScriptArvore(sbScript.toString());
		log.debug("AtendimentoFilaController:obterArvoreContato.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iExpandeArvoreContatoFila.jsp"
	 */
	protected ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoFilaController:expandeArvoreContato.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		StringBuffer sbScript = new StringBuffer(1024);
		StringBuffer sbNode = new StringBuffer(1024);

		AtendimentoArvoreFiltrosVO atFiltros = AtendimentoArvoreFiltrosVO.Factory.newInstance();
		atFiltros.setIdContato(request.getParameter("IDCONTATO"));
		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, atFiltros);

		for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {
			sbNode.setLength(0);
			String idContato = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato();
			String sInFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha();
			String nmContato = StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato());
			String dsPath = StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath());
			String ico1 = ConstantesCRM.SVAZIO;
			String ico2 = ConstantesCRM.SVAZIO;
			String dblClickScript = "Javascript:itemDblClick('" + idContato + "', '" + sInFolha + "', '" + dsPath + "')";

			if (!admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals(ConstantesCRM.SONE)) {
				ico1 = "/FrontOfficeWeb/resources/images/foldericon.png";
				ico2 = "/FrontOfficeWeb/resources/images/openfoldericon.png";
				dblClickScript = ConstantesCRM.SVAZIO;
			}

			sbNode.append("var arvore = new parent.WebFXTreeItem('");
			sbNode.append(nmContato);
			sbNode.append("',\"Javascript:selecionaContato('");
			sbNode.append(idContato);
			sbNode.append("','");
			sbNode.append(sInFolha);
			sbNode.append("','");
			sbNode.append(dsPath);
			sbNode.append("','");
			sbNode.append("');\",'','").append(ico1).append("','").append(ico2).append("','',\"");
			sbNode.append(dblClickScript).append("\");");

			sbScript.append(sbNode.toString());
			sbScript.append("parent.tree.getSelected().add(arvore);\n\n");
		}

		sbScript.append("parent.tree.getSelected().setAddEnabled(false);\n\n");
		sbScript.append("parent.tree.getSelected().expand();\n\n");

		this.atendimentoFilaForm.setScriptArvore(sbScript.toString());
		log.debug("AtendimentoFilaController:expandeArvoreContato.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="comboCampos.jsp"
	 */
	protected ActionForward obterComboCampos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoFilaController:obterComboCampos.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		// chama serviço para efetuar trazer os valores da pesquisa avançada
		this.atendimentoFilaForm.setFormularioVO(atendimentoFacade.obtemFormularioVO(user));
		log.debug("AtendimentoFilaController:obterComboCampos.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected void LoadEstados(AtendimentoFilaForm form, HttpServletRequest request) throws Exception {
		log.debug("AtendimentoFilaController:loadEstados.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		this.atendimentoFilaForm.setEstadoVO(atendimentoFacade.getWFEstadosVO(user, false));
		log.debug("AtendimentoFilaController:loadEstados.do - Fim do Metodo]");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoDetalhe/AtendimentoDetalheController.jpf"
	 */
	protected ActionForward gerarDetalhe(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoFilaController:gerarDetalhe.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("idAtendimento",request.getParameter("idAtendimento"));
		f.addParameter("fila",request.getParameter("fila"));
		log.debug("AtendimentoFilaController:gerarDetalhe.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward AtendimentoDetalheDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoFilaController:atendimentoDetalheDone.do - Metodo]");
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("voltar",ConstantesCRM.SONE);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="voltarWorkflow.jsp"
	 */
	protected ActionForward AtendimentoWorkflowDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoFilaController:atendimentoWorkflowDone.do - Metodo]");
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("voltar",ConstantesCRM.SONE);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="contadorProcessos.jsp"
	 */
	protected ActionForward contarTotalProcessos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoFilaController:contarTotalProcessos.do - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="contadorProcessos.jsp"
	 */
	protected ActionForward pesquisarTotalResultados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:pesquisarTotalResultados.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFSubEstadoVO wFSubEstadosVO[];
		UsuarioVIVO usuariosVIVO[];

		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			form.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/beginCRI.do");
		} else if (ConstantesCRM.SONE.equals(form.getInResp())) {
			form.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/beginResp.do");
			form.setInResp(ConstantesCRM.SONE);
			atendimentoFilaForm.setInResp(ConstantesCRM.SONE);
		} else {
			form.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/begin.do");
		}
		if (this.atendimentoFilaForm.atendimentoInformacaoVO != null) {
			// Guarda os arrays de SubEstados e UsuariosVIVO anteriores, pois não serão retornados na pesquisa a seguir
			wFSubEstadosVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getWFSubEstadoVOArray();
			usuariosVIVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getUsuarioVIVOArray();
		} else {
			wFSubEstadosVO = new WFSubEstadoVO[0];
			usuariosVIVO = new UsuarioVIVO[0];
		}

		// Guarda o formularioVO já obtido do serviço
		form.setFormularioVO(this.atendimentoFilaForm.getFormularioVO());

		// atribui 0 (zero) para o número de campos da pesquisa avançada
		atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO().setNrCampos(ConstantesCRM.SZERO);

		form.getAtdFilaPesqVO().setWFPesquisaAvancadaVO(atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO());

		if (!ConstantesCRM.SONE.equals(request.getSession().getAttribute("inPesquisaFila"))) {
			this.atendimentoFilaForm = form;
			// Atribui para os objetos a seleçao do usuário
			this.atribuiAtdFilaPesqVO(form);
		} else {

			request.getSession().removeAttribute("inPesquisaFila");
		}

		boolean refinarPesquisa = false;

		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			AtendimentoInformacaoVO atendimento = AtendimentoInformacaoVO.Factory.newInstance();
			this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInTotalRegistros(ConstantesCRM.SONE);
			atendimento = atendimentoFacade.psqFilaCRI(user, this.getAtendimentoFilaForm().getAtdFilaPesqVO());
			this.atendimentoFilaForm.getAtendimentoInformacaoVO().setTotalRegistros(atendimento.getTotalRegistros());
			this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInTotalRegistros(ConstantesCRM.SVAZIO);
		} else if (form.getInResp() != null && form.getInResp().equals(ConstantesCRM.SONE)) {
			if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
				this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInPesqUsuario(ConstantesCRM.SONE);
			}
			this.atendimentoFilaForm.setAtendimentoInformacaoVO(atendimentoFacade.psqFilaResp(user, this.getAtendimentoFilaForm().getRwfInboxPesquisaVO()));
		} else {
			try {
				AtendimentoInformacaoVO atendimento = AtendimentoInformacaoVO.Factory.newInstance();
				this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInTotalRegistros(ConstantesCRM.SONE);
				atendimento = atendimentoFacade.obtemAtendimentoInformacaoVO(user, this.getAtendimentoFilaForm().getAtdFilaPesqVO());
				this.atendimentoFilaForm.getAtendimentoInformacaoVO().setTotalRegistros(atendimento.getTotalRegistros());
				this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInTotalRegistros(ConstantesCRM.SVAZIO);
			} catch (FacadeException fcdEx) {
				this.atendimentoFilaForm.setAtendimentoInformacaoVO(AtendimentoInformacaoVO.Factory.newInstance());
				this.atendimentoFilaForm.getAtendimentoInformacaoVO().setTotalRegistros(ConstantesCRM.SZERO);
				this.atendimentoFilaForm.getAtendimentoInformacaoVO().setNrRegistros(0);
				refinarPesquisa = true;
			}
		}

		if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
			log.debug("AtendimentoFilaController:pesquisarTotalResultados.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("successInbox");
		} else {
			ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
			if (refinarPesquisa) {
				f.addParameter("erro","Quantidade de processos muito grande!\\nRefine a pesquisa.");
			}
			log.debug("AtendimentoFilaController:pesquisarTotalResultados.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return f;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="contadorProcessos.jsp"
	 */
	protected ActionForward pesquisarAvancadoTotalResultados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFilaForm form = (AtendimentoFilaForm) formParam;

		log.debug("AtendimentoFilaController:pesquisarAvancadoTotalResultados.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFSubEstadoVO wFSubEstadosVO[];
		UsuarioVIVO usuariosVIVO[];

		if (this.atendimentoFilaForm.atendimentoInformacaoVO != null) {
			// Guarda o array de SubEstados anterior, pois não será retornado na pesquisa a seguir
			wFSubEstadosVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getWFSubEstadoVOArray();
			usuariosVIVO = this.atendimentoFilaForm.atendimentoInformacaoVO.getUsuarioVIVOArray();
		} else {
			wFSubEstadosVO = new WFSubEstadoVO[0];
			usuariosVIVO = new UsuarioVIVO[0];
		}

		// Retém o conteúdo de valores associado ao campo de multipla escolha selecionado
		form.setCamposValorVO(this.atendimentoFilaForm.getCamposValorVO());

		// Guarda o formularioVO já obtido do serviço
		form.setFormularioVO(this.atendimentoFilaForm.getFormularioVO());

		// Guarda a seleção feita da pesquisa avançada
		form.getAtdFilaPesqVO().setWFPesquisaAvancadaVO(this.atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO());
		form.getAtdFilaPesqVO().getWFPesquisaAvancadaVO().setNrCampos(String.valueOf(atendimentoFilaForm.getAtdFilaPesqVO().getWFPesquisaAvancadaVO().getWFPesquisaAvancadaComparacaoVOArray().length));

		this.atendimentoFilaForm = form;

		// Atribui para os objetos a seleçao do usuário
		this.atribuiAtdFilaPesqVO(form);

		// chamada do servico de pesquisa de fila
		if (form.getInCRI() != null && form.getInCRI().equals(ConstantesCRM.STWO)) {
			AtendimentoInformacaoVO atendimento = AtendimentoInformacaoVO.Factory.newInstance();

			this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInTotalRegistros(ConstantesCRM.SONE);
			atendimento = atendimentoFacade.psqFilaCRI(user, this.getAtendimentoFilaForm().getAtdFilaPesqVO());
			this.atendimentoFilaForm.getAtendimentoInformacaoVO().setTotalRegistros(atendimento.getTotalRegistros());
			this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInTotalRegistros(ConstantesCRM.SVAZIO);
		} else {
			AtendimentoInformacaoVO atendimento = AtendimentoInformacaoVO.Factory.newInstance();
			this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInTotalRegistros(ConstantesCRM.SONE);
			// chama a pesquisa avançada de Fila de Processos
			atendimento = atendimentoFacade.psqFilaCRI(user, this.getAtendimentoFilaForm().getAtdFilaPesqVO());
			this.atendimentoFilaForm.getAtendimentoInformacaoVO().setTotalRegistros(atendimento.getTotalRegistros());
			this.getAtendimentoFilaForm().getAtdFilaPesqVO().setInTotalRegistros(ConstantesCRM.SVAZIO);
		}
		log.debug("AtendimentoFilaController:pesquisarAvancadoTotalResultados.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AtendimentoFilaForm extends ActionForm {

		private static final long serialVersionUID = -2609792420893102967L;

		private RWFInBoxPesquisaVO rwfInboxPesquisaVO;
		private String erro;
		private String inResp;
		private String inCRI;
		private String[] psqNmCampo;
		private String[] psqDsComparacao;
		private String[] psqIdFormularioCampoValor;
		private String[] psqTpComparacao;
		private String[] psqIdCampo;
		private String[] psqValor;
		private int atualizacao;
		private WFEstadosVO estadoVO;
		private String regionalSel;
		private String tipoLinhaSel;
		private String tipoCampo;

		public AtendimentoFilaForm() {
			// inicicializa objeto atendimentoInformacaoVO
			this.atendimentoInformacaoVO = AtendimentoInformacaoVO.Factory.newInstance();
			this.atendimentoInformacaoVO.setWFGrupoVOArray(new WFGrupoVO[0]);
			this.atendimentoInformacaoVO.setWFEstadoVOArray(new WFEstadoVO[0]);
			this.atendimentoInformacaoVO.setWFSubEstadoVOArray(new WFSubEstadoVO[0]);
			this.atendimentoInformacaoVO.setUsuarioVIVOArray(new UsuarioVIVO[0]);
			this.atendimentoInformacaoVO.setWFRegionalVOArray(new WFRegionalVO[0]);
			// inicicializa objeto atdFilaPesqVO
			this.atdFilaPesqVO = AtendimentoFilaPesquisaVO.Factory.newInstance();
			this.atdFilaPesqVO.setWFEstadoVO(WFEstadoVO.Factory.newInstance());
			this.atdFilaPesqVO.getWFEstadoVO().setIdEstado(ConstantesCRM.SVAZIO);
			this.atdFilaPesqVO.setWFSubEstadoVO(WFSubEstadoVO.Factory.newInstance());
			this.atdFilaPesqVO.getWFSubEstadoVO().setIdSubEstado(ConstantesCRM.SVAZIO);
			this.atdFilaPesqVO.setWFGrupoVO(WFGrupoVO.Factory.newInstance());
			this.atdFilaPesqVO.getWFGrupoVO().setIdGrupo(ConstantesCRM.SVAZIO);
			this.atdFilaPesqVO.setWFRegionalVO(WFRegionalVO.Factory.newInstance());
			this.atdFilaPesqVO.getWFRegionalVO().setIdRegional(ConstantesCRM.SVAZIO);
			this.atdFilaPesqVO.setUsuarioVIVO(UsuarioVIVO.Factory.newInstance());
			this.atdFilaPesqVO.getUsuarioVIVO().setIdPessoaUsuario(ConstantesCRM.SVAZIO);
			this.atdFilaPesqVO.setWFPesquisaAvancadaVO(WFPesquisaAvancadaVO.Factory.newInstance());
			this.atdFilaPesqVO.getWFPesquisaAvancadaVO().setWFPesquisaAvancadaComparacaoVOArray(new WFPesquisaAvancadaComparacaoVO[0]);

			// inicicializa objeto formularioVO
			this.formularioVO = FormularioVO.Factory.newInstance();
			this.formularioVO.setFormularioCampoVOArray(new FormularioCampoVO[0]);
			// inicializa objeto camposValorVO
			this.camposValorVO = new FormularioCampoValorVO[0];
			// inicicializa objeto alertaVO
			this.alertasVO = new AlertaVO[0];
		}

		// abaSelecionada
		private String abaSelecionada = ConstantesCRM.SONE;

		public String getAbaSelecionada() {
			return this.abaSelecionada;
		}

		public void setAbaSelecionada(String abaSelecionada) {
			this.abaSelecionada = abaSelecionada;
		}

		// abaSelecionada
		private String fila = ConstantesCRM.SVAZIO;

		public String getFila() {
			return this.fila;
		}

		public void setFila(String fila) {
			this.fila = fila;
		}

		// optGrpSel
		private String optGrpSel;

		public String getOptGrpSel() {
			return this.optGrpSel;
		}

		public void setOptGrpSel(String optGrpSel) {
			this.optGrpSel = optGrpSel;
			if (ConstantesCRM.SZERO.equals(this.optGrpSel)) {
				this.getAtdFilaPesqVO().setInFilaAbertos(ConstantesCRM.SONE);
			}/*
			 * else if(ConstantesCRM.SONE.equals(this.optGrpSel)){ this.getAtdFilaPesqVO().setInFilaAbertos(ConstantesCRM.SZERO); }
			 */
		}

		// grupoSel
		private String grupoSel = "-1"; // -1 é o item em branco default

		public String getGrupoSel() {
			return this.grupoSel;
		}

		public void setGrupoSel(String grupoSel) {
			this.grupoSel = grupoSel;
		}

		// usuarioSel
		private String usuarioSel = "-1"; // -1 é o item em branco default

		public String getUsuarioSel() {
			return this.usuarioSel;
		}

		public void setUsuarioSel(String usuarioSel) {
			this.usuarioSel = usuarioSel;
		}

		// estadoSel
		private String estadoSel = "-1"; // -1 é o item em branco default

		public String getEstadoSel() {
			return this.estadoSel;
		}

		public void setEstadoSel(String estadoSel) {
			this.estadoSel = estadoSel;
		}

		// subEstadoSel
		private String subEstadoSel = "-1"; // -1 é o item em branco default

		public String getSubEstadoSel() {
			return this.subEstadoSel;
		}

		public void setSubEstadoSel(String subEstadoSel) {
			this.subEstadoSel = subEstadoSel;
		}

		private AtendimentoInformacaoVO atendimentoInformacaoVO;

		public void setAtendimentoInformacaoVO(AtendimentoInformacaoVO atendimentoInformacaoVO) {
			this.atendimentoInformacaoVO = atendimentoInformacaoVO;
		}

		public AtendimentoInformacaoVO getAtendimentoInformacaoVO() {
			return this.atendimentoInformacaoVO;
		}

		// formularioVO
		private FormularioVO formularioVO;

		public void setFormularioVO(FormularioVO formularioVO) {
			this.formularioVO = formularioVO;
		}

		public FormularioVO getFormularioVO() {
			return this.formularioVO;
		}

		// atdFilaPesqVO
		private AtendimentoFilaPesquisaVO atdFilaPesqVO;

		public void setAtdFilaPesqVO(AtendimentoFilaPesquisaVO atdFilaPesqVO) {
			this.atdFilaPesqVO = atdFilaPesqVO;
		}

		public AtendimentoFilaPesquisaVO getAtdFilaPesqVO() {
			return this.atdFilaPesqVO;
		}

		// alertasVO
		private AlertaVO[] alertasVO;

		public void setAlertasVO(AlertaVO[] alertasVO) {
			this.alertasVO = alertasVO;
		}

		public AlertaVO[] getAlertasVO() {
			return this.alertasVO;
		}

		// camposValorVO
		private FormularioCampoValorVO[] camposValorVO;

		public void setCamposValorVO(FormularioCampoValorVO[] camposValorVO) {
			this.camposValorVO = camposValorVO;
		}

		public FormularioCampoValorVO[] getCamposValorVO() {
			return this.camposValorVO;
		}

		// formularioCampoSel
		private String formularioCampoSel = "-1"; // -1 é o item em branco default

		public String getFormularioCampoSel() {
			return this.formularioCampoSel;
		}

		public void setFormularioCampoSel(String formularioCampoSel) {
			this.formularioCampoSel = formularioCampoSel;
		}

		// comparacaoSel
		private String comparacaoSel = ConstantesCRM.SZERO;

		public String getComparacaoSel() {
			return this.comparacaoSel;
		}

		public void setComparacaoSel(String comparacaoSel) {
			this.comparacaoSel = comparacaoSel;
		}

		// valorSel
		private String valorSel = "-1"; // -1 é o item em branco default

		public String getValorSel() {
			return this.valorSel;
		}

		public void setValorSel(String valorSel) {
			this.valorSel = valorSel;
		}

		// valor
		private String valor = ConstantesCRM.SVAZIO; // -1 é o item em branco default

		public String getValor() {
			return this.valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}

		// scriptArvore
		private String scriptArvore = ConstantesCRM.SVAZIO;

		public String getScriptArvore() {
			return this.scriptArvore;
		}

		public void setScriptArvore(String scriptArvore) {
			this.scriptArvore = scriptArvore;
		}

		// textoContato
		private String textoContato = ConstantesCRM.SVAZIO;

		public String getTextoContato() {
			return this.textoContato;
		}

		public void setTextoContato(String textoContato) {
			this.textoContato = textoContato;
		}

		public void setTipoCampo(String tipoCampo) {
			this.tipoCampo = tipoCampo;
		}

		public String getTipoCampo() {
			return this.tipoCampo;
		}

		public void setTipoLinhaSel(String tipoLinhaSel) {
			this.tipoLinhaSel = tipoLinhaSel;
		}

		public String getTipoLinhaSel() {
			return this.tipoLinhaSel;
		}

		public void setRegionalSel(String regionalSel) {
			this.regionalSel = regionalSel;
		}

		public String getRegionalSel() {
			return this.regionalSel;
		}

		public void setEstadoVO(WFEstadosVO estadoVO) {
			this.estadoVO = estadoVO;
		}

		public WFEstadosVO getEstadoVO() {
			return this.estadoVO;
		}

		public void setAtualizacao(int atualizacao) {
			this.atualizacao = atualizacao;
		}

		public int getAtualizacao() {
			return this.atualizacao;
		}

		public void setPsqValor(String[] psqValor) {
			this.psqValor = psqValor;
		}

		public String[] getPsqValor() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.psqValor == null || this.psqValor.length == 0) {
				this.psqValor = new String[1];
			}

			return this.psqValor;
		}

		public void setPsqIdCampo(String[] psqIdCampo) {
			this.psqIdCampo = psqIdCampo;
		}

		public String[] getPsqIdCampo() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			// if(this.psqIdCampo == null || this.psqIdCampo.length == 0)
			// {
			// this.psqIdCampo = new String[1];
			// }

			return this.psqIdCampo;
		}

		public void setPsqTpComparacao(String[] psqTpComparacao) {
			this.psqTpComparacao = psqTpComparacao;
		}

		public String[] getPsqTpComparacao() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			// if(this.psqTpComparacao == null || this.psqTpComparacao.length == 0)
			// {
			// this.psqTpComparacao = new String[1];
			// }

			return this.psqTpComparacao;
		}

		public void setPsqIdFormularioCampoValor(String[] psqIdFormularioCampoValor) {
			this.psqIdFormularioCampoValor = psqIdFormularioCampoValor;
		}

		public String[] getPsqIdFormularioCampoValor() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			// if(this.psqIdFormularioCampoValor == null || this.psqIdFormularioCampoValor.length == 0)
			// {
			// this.psqIdFormularioCampoValor = new String[1];
			// }

			return this.psqIdFormularioCampoValor;
		}

		public void setPsqDsComparacao(String[] psqDsComparacao) {
			this.psqDsComparacao = psqDsComparacao;
		}

		public String[] getPsqDsComparacao() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			// if(this.psqDsComparacao == null || this.psqDsComparacao.length == 0)
			// {
			// this.psqDsComparacao = new String[1];
			// }

			return this.psqDsComparacao;
		}

		public void setPsqNmCampo(String[] psqNmCampo) {
			this.psqNmCampo = psqNmCampo;
		}

		public String[] getPsqNmCampo() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			// if(this.psqNmCampo == null || this.psqNmCampo.length == 0)
			// {
			// this.psqNmCampo = new String[1];
			// }

			return this.psqNmCampo;
		}

		public void setInCRI(String inCRI) {
			this.inCRI = inCRI;
		}

		public String getInCRI() {
			return this.inCRI;
		}

		public void setInResp(String inResp) {
			this.inResp = inResp;
		}

		public String getInResp() {
			return this.inResp;
		}

		public void setRwfInboxPesquisaVO(RWFInBoxPesquisaVO rwfInboxPesquisaVO) {
			this.rwfInboxPesquisaVO = rwfInboxPesquisaVO;
		}

		public RWFInBoxPesquisaVO getRwfInboxPesquisaVO() {
			if (this.rwfInboxPesquisaVO == null) {
				rwfInboxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();
			}
			return this.rwfInboxPesquisaVO;
		}

		public void setErro(String erro) {
			this.erro = erro;
		}

		public String getErro() {
			return this.erro;
		}

		private String regionalSelecionada = "-1";

		public void setRegionalSelecionada(String regionalSelecionada) {
			this.regionalSelecionada = regionalSelecionada;
		}

		public String getRegionalSelecionada() {
			return this.regionalSelecionada;
		}
	}

	/**
	 * Getter para o FormBean
	 */
	public AtendimentoFilaForm getAtendimentoFilaForm() {
		return this.atendimentoFilaForm;
	}

	private void atribuiAtdFilaPesqVO(AtendimentoFilaForm form) throws Exception {
		this.atendimentoFilaForm.getAtdFilaPesqVO().setWFGrupoVO(WFGrupoVO.Factory.newInstance());
		this.atendimentoFilaForm.getAtdFilaPesqVO().setUsuarioVIVO(UsuarioVIVO.Factory.newInstance());
		this.atendimentoFilaForm.getAtdFilaPesqVO().setWFEstadoVO(WFEstadoVO.Factory.newInstance());
		this.atendimentoFilaForm.getAtdFilaPesqVO().setWFSubEstadoVO(WFSubEstadoVO.Factory.newInstance());
		this.atendimentoFilaForm.getAtdFilaPesqVO().setWFRegionalVO(WFRegionalVO.Factory.newInstance());

		if (this.atendimentoFilaForm.getAtdFilaPesqVO().getNrLinha() != null) {
			String nrLinha = StringUtilStatic.unmask(this.atendimentoFilaForm.getAtdFilaPesqVO().getNrLinha(), "()- ");
			if (nrLinha.length() < 10 && nrLinha.length() > 8) {
				nrLinha = ConstantesCRM.SZERO + nrLinha;
			}
			this.atendimentoFilaForm.getAtdFilaPesqVO().setNrLinha(nrLinha);
		}

		if (!form.getGrupoSel().equals("-1")) {
			this.atendimentoFilaForm.getAtdFilaPesqVO().getWFGrupoVO().setIdGrupo(form.getGrupoSel());
		}
		if (!form.getUsuarioSel().equals("-1")) {
			this.atendimentoFilaForm.getAtdFilaPesqVO().getUsuarioVIVO().setIdPessoaUsuario(form.getUsuarioSel());
		}
		if (!form.getEstadoSel().equals("-1")) {
			this.atendimentoFilaForm.getAtdFilaPesqVO().getWFEstadoVO().setIdEstado(form.getEstadoSel());
		}
		if (!form.getSubEstadoSel().equals("-1")) {
			this.atendimentoFilaForm.getAtdFilaPesqVO().getWFSubEstadoVO().setIdSubEstado(form.getSubEstadoSel());
		}

		if (!form.getRegionalSelecionada().equals("-1")) {
			this.atendimentoFilaForm.getAtdFilaPesqVO().getWFRegionalVO().setIdRegional(form.getRegionalSelecionada());
		}
	}

	private WFEstadoVO getEstadoVO(AtendimentoFilaForm form) throws Exception {

		WFEstadoVO wFEstadoVO = WFEstadoVO.Factory.newInstance();
		wFEstadoVO.setIdEstado(form.getEstadoSel());
		return wFEstadoVO;
	}

	private AtendimentoForm getAtendimentoForm(HttpServletRequest request) throws Exception {

		Collection col = new Vector();
		AtendimentoFilaVO[] atendimentoFilaVO = this.atendimentoFilaForm.getAtendimentoInformacaoVO().getAtendimentoFilaVOArray();

		for (int i = 0; i < atendimentoFilaVO.length; i++) {

			String parameter = "atdFilaVO[" + i + "].operacaoWorkflow";

			if (request.getParameter(parameter) != null) {
				col.add(atendimentoFilaVO[i].getAtendimentoVO());
			}

		}

		AtendimentoForm atendimentoForm = new AtendimentoForm();
		if (!col.isEmpty()) {

			AtendimentoVO[] atendimentosVO = new AtendimentoVO[col.size()];

			int j = 0;
			Iterator it = col.iterator();

			while (it.hasNext()) {
				atendimentosVO[j++] = (AtendimentoVO) it.next();
			}

			atendimentoForm.setAtendimentosVO(atendimentosVO);

		}
		// Atribui o idAtividade
		atendimentoForm.setIdAtividade("13");

		// Atribui telaDestino
		atendimentoForm.setFila(this.atendimentoFilaForm.getFila());

		// Atribui o vetor de grupo
		atendimentoForm.setWFGruposVO(this.atendimentoFilaForm.getAtendimentoInformacaoVO().getWFGrupoVOArray());

		return atendimentoForm;
	}

}
