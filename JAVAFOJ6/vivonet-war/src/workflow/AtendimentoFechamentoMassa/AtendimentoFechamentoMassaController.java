package workflow.AtendimentoFechamentoMassa;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import workflow.AtendimentoWorkflow.AtendimentoWorkflowController.AtendimentoForm;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoFechamentoMassaVODocument.AtendimentoFechamentoMassaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoDocVODocument.AtendimentoWorkflowTecnicoDocVO;
import br.com.vivo.fo.workflow.vo.WFDocumentoTecnicoPesquisaVODocument.WFDocumentoTecnicoPesquisaVO;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AtendimentoFechamentoMassaController extends AbstractAction {

	private static final long serialVersionUID = 480218390346877798L;

	private static Logger log = new Logger("workflow");
	private static final String RAIZ_PRINCIPAL = "PAI";

	private AtendimentoFechamentoMassaForm atendimentoFechamentoMassaForm = new AtendimentoFechamentoMassaForm();
	private AtendimentoForm atendimentoFormTemp;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade atendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.AtendimentoWorkflowFacade atendimentoWorkflowFacade;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("voltarFechamento".equals(mapping.getParameter())) {
			return voltarFechamento(mapping, form, request, response);
		} else if ("beginSemPesquisa".equals(mapping.getParameter())) {
			return beginSemPesquisa(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("obterAlerta".equals(mapping.getParameter())) {
			return obterAlerta(mapping, form, request, response);
		} else if ("obterDocAssociado".equals(mapping.getParameter())) {
			return obterDocAssociado(mapping, form, request, response);
		} else if ("encerrar".equals(mapping.getParameter())) {
			return encerrar(mapping, form, request, response);
		} else if ("encerrarGravar".equals(mapping.getParameter())) {
			return encerrarGravar(mapping, form, request, response);
		} else if ("voltar".equals(mapping.getParameter())) {
			return voltar(mapping, form, request, response);
		} else if ("obterArvoreContato".equals(mapping.getParameter())) {
			return obterArvoreContato(mapping, form, request, response);
		} else if ("expandeArvoreContato".equals(mapping.getParameter())) {
			return expandeArvoreContato(mapping, form, request, response);
		} else if ("AtendimentoDetalheDone".equals(mapping.getParameter())) {
			return AtendimentoDetalheDone(mapping, form, request, response);
		} else if ("AtendimentoWorkflowDone".equals(mapping.getParameter())) {
			return AtendimentoWorkflowDone(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 * @jpf:forward name="sem_pesquisa" path="beginSemPesquisa.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		AtendimentoFechamentoMassaForm form = (AtendimentoFechamentoMassaForm) formParam;

		log.debug("AtendimentoFechamentoMassaController:begin.do - Fechamento Massa- Inicio do Metodo]");
		// Monta o log da operação se possível
		log.debug("AtendimentoFechamentoMassaController:begin()");

		// String user = ConstantesCRM.getCurrentUser(request.getSession());

		// Retém o formbean
		atendimentoFechamentoMassaForm = form;
		log.debug("AtendimentoFechamentoMassaController:begin.do - Fechamento Massa- Fim do Metodo]");
		// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		// return mapping.findForward(ConstantesCRM.SUCCESS, atendimentoFechamentoMassaForm);
		request.setAttribute("atendimentoFechamentoMassaForm", atendimentoFechamentoMassaForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("sem_pesquisa");

	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 */
	protected ActionForward voltarFechamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		AtendimentoFechamentoMassaForm form = (AtendimentoFechamentoMassaForm) formParam;

		log.debug("AtendimentoFechamentoMassaController:voltarFechamento.do - Fechamento Massa- Inicio do Metodo]");
		// Monta o log da operação se possível
		log.debug("AtendimentoFechamentoMassaController:voltarFechamento()");

		// String user = ConstantesCRM.getCurrentUser(request.getSession());

		// Retém o formbean
		atendimentoFechamentoMassaForm = form;
		log.debug("AtendimentoFechamentoMassaController:voltarFechamento.do - Fechamento Massa- Fim do Metodo]");
		request.setAttribute("atendimentoFechamentoMassaForm", atendimentoFechamentoMassaForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 * @jpf:forward name="sem_pesquisa" path="FechamentoMassaEncerramento.jsp"
	 */
	protected ActionForward beginSemPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		
		AtendimentoFechamentoMassaForm form = (AtendimentoFechamentoMassaForm) formParam;

		// Monta o log da operação se possível
		log.debug("AtendimentoFechamentoMassaController:pesquisar.do - Fechamento Massa- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// form.setFila("/FrontOfficeWeb/workflow/AtendimentoFechamentoMassa/AtendimentoFechamentoMassaController.jpf");
		form.setFila("/FrontOfficeWeb/workflow/AtendimentoFechamentoMassa/voltarFechamento.do");
		this.atendimentoFechamentoMassaForm = form;

		// atribui o id do usuario atual para o objeto usuario do form
		this.atendimentoFechamentoMassaForm.getUsuarioVIVO().setIdPessoaUsuario(user);

		log.debug("AtendimentoDetalheController:begin.do - [idPessoaUsuario = " + user + "]");

		if (request.getParameter("voltar") != null && request.getParameter("voltar").equals(ConstantesCRM.SONE)) {
			request.setAttribute("voltar", ConstantesCRM.SONE);
		}

		AtendimentoInformacaoVO atd = AtendimentoInformacaoVO.Factory.newInstance();
		atd.setTotalRegistros(ConstantesCRM.SZERO);
		atd.setNrRegistros(0);
		this.atendimentoFechamentoMassaForm.setAtendimentoInformacaoVO(atd);

		log.debug("AtendimentoDetalheController:begin.do - [idContato = " + this.atendimentoFechamentoMassaForm.getIdContato() + " comentario = " + this.atendimentoFechamentoMassaForm.getComentario() + " login = " + this.atendimentoFechamentoMassaForm.getLogin() + " dtSuspeitoFim = " + this.atendimentoFechamentoMassaForm.getDtSuspeitoFim() + " dtSuspeitoInicio =  " + this.atendimentoFechamentoMassaForm.getDtSuspeitoInicio() + " usuarioVivo = "
				+ this.atendimentoFechamentoMassaForm.getUsuarioVIVO() + "]");

		// atribui o id do usuario atual para o objeto usuario da pesquisa
		this.atendimentoFechamentoMassaForm.getUsuarioVIVO().setIdPessoaUsuario(user);
		log.debug("AtendimentoFechamentoMassaController:pesquisar.do - Fechamento Massa- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);

		return mapping.findForward("sem_pesquisa");

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="FechamentoMassaEncerramento.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AtendimentoFechamentoMassaForm form = (AtendimentoFechamentoMassaForm) formParam;

		// Monta o log da operação se possível
		log.debug("AtendimentoFechamentoMassaController:pesquisar.do - Fechamento Massa- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// form.setFila("/FrontOfficeWeb/workflow/AtendimentoFechamentoMassa/AtendimentoFechamentoMassaController.jpf");
		form.setFila("/FrontOfficeWeb/workflow/AtendimentoFechamentoMassa/voltarFechamento.do");

		this.atendimentoFechamentoMassaForm = form;

		// atribui o id do usuario atual para o objeto usuario do form
		this.atendimentoFechamentoMassaForm.getUsuarioVIVO().setIdPessoaUsuario(user);

		log.debug("AtendimentoDetalheController:begin.do - [idPessoaUsuario = " + user + "]");

		if (request.getParameter("voltar") != null && request.getParameter("voltar").equals(ConstantesCRM.SONE)) {
			request.setAttribute("voltar", ConstantesCRM.SONE);
		}

		try {
			// chama servico para efetuar a pesquisa do inbox
			this.atendimentoFechamentoMassaForm.setAtendimentoInformacaoVO(atendimentoFacade.obtemAtendimentoInformacaoVOFechamentoMassa(user, this.atendimentoFechamentoMassaForm.getIdContato(), this.atendimentoFechamentoMassaForm.getComentario(), this.atendimentoFechamentoMassaForm.getLogin(), this.atendimentoFechamentoMassaForm.getDtSuspeitoFim(), this.atendimentoFechamentoMassaForm.getDtSuspeitoInicio(), this.atendimentoFechamentoMassaForm.getUsuarioVIVO()));
		} catch (FacadeException fcdEx) {
			this.atendimentoFechamentoMassaForm.getAtendimentoInformacaoVO().setTotalRegistros(ConstantesCRM.SZERO);
			this.atendimentoFechamentoMassaForm.getAtendimentoInformacaoVO().setNrRegistros(0);
			request.setAttribute("Exibir_Msg", "Quantidade de processos muito grande!\\nRefine a pesquisa.");
		}

		log.debug("AtendimentoDetalheController:begin.do - [idContato = " + this.atendimentoFechamentoMassaForm.getIdContato() + " comentario = " + this.atendimentoFechamentoMassaForm.getComentario() + " login = " + this.atendimentoFechamentoMassaForm.getLogin() + " dtSuspeitoFim = " + this.atendimentoFechamentoMassaForm.getDtSuspeitoFim() + " dtSuspeitoInicio =  " + this.atendimentoFechamentoMassaForm.getDtSuspeitoInicio() + " usuarioVivo = "
				+ this.atendimentoFechamentoMassaForm.getUsuarioVIVO() + "]");

		// Atribui o id do usuario atual para o objeto usuario da pesquisa
		this.atendimentoFechamentoMassaForm.getUsuarioVIVO().setIdPessoaUsuario(user);
		log.debug("AtendimentoFechamentoMassaController:pesquisar.do - Fechamento Massa- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		
		return mapping.findForward(ConstantesCRM.SUCCESS);
		
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iAlertas.jsp"
	 */
	protected ActionForward obterAlerta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoFechamentoMassaController:obterAlerta.do - Fechamento Massa- Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// obtém o idAtendimento para pesquisar os alertas
		String idAtendimento = request.getParameter("idAtendimento");

		log.debug("AtendimentoDetalheController:begin.do - [idAtendimento = " + idAtendimento + "]");

		if (idAtendimento != null) {
			// Obtém os alertas para o idAtendimento
			this.atendimentoFechamentoMassaForm.setAlertasVO(atendimentoFacade.obtemAlertaVO(user, idAtendimento, ConstantesCRM.SONE));
		}
		log.debug("AtendimentoFechamentoMassaController:obterAlerta.do - Fechamento Massa- Fim do Metodo]");

		request.setAttribute("atendimentoFechamentoMassaForm", atendimentoFechamentoMassaForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iDocsAssociado.jsp"
	 */
	protected ActionForward obterDocAssociado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoFechamentoMassaController:obterDocAssociado.do - Fechamento Massa- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// obtém o idAtendimento para pesquisar os documentos associados
		String idAtendimento = request.getParameter("idAtendimento");

		log.debug("AtendimentoDetalheController:begin.do - [idAtendimento = " + idAtendimento + "]");

		if (idAtendimento != null) {
			// Obtém os documentos associados para o idAtendimento
			// this.atendimentoFechamentoMassaForm.setAtdWFTecnicoDocsVO(atendimentoFacade.obtemAtendimentoWorkflowTecnicoDocVO(user,
			// idAtendimento));
			WFDocumentoTecnicoPesquisaVO atdWfTecDoc = WFDocumentoTecnicoPesquisaVO.Factory.newInstance();
			atdWfTecDoc.setAssociados(idAtendimento);
			this.atendimentoFechamentoMassaForm.setAtdWFTecnicoDocsVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowTecnicoDocVOArray(user, atdWfTecDoc));
		}
		log.debug("AtendimentoFechamentoMassaController:obterDocAssociado.do - Fechamento Massa- Fim do Metodo]");
		request.setAttribute("atendimentoFechamentoMassaForm", atendimentoFechamentoMassaForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="failure" path="failure.jsp"
	 * @jpf:forward name="success" path="/workflow/AtendimentoWorkflow/encerramentoMassaBegin.do"
	 */
	protected ActionForward encerrar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoFechamentoMassaForm form = (AtendimentoFechamentoMassaForm) formParam;

		log.debug("AtendimentoFechamentoMassaController:encerrar.do - Fechamento Massa- Inicio do Metodo]");

		AtendimentoForm atendimentoForm = this.getAtendimentoForm(request);
		// seta o idAtividade da ação encerrar
		atendimentoForm.setIdAtividade("23");
		atendimentoForm.setInTratamentoUsuario(ConstantesCRM.STWO);
		atendimentoForm.setIdAtendimento(ConstantesCRM.SZERO);
		atendimentoForm.setIdContato(form.getIdContatoWF());
		atendimentoForm.setDocTec(1);
		log.debug("AtendimentoDetalheController:encerrar.do - [idAtividade = " + atendimentoForm.getIdAtividade() + " inTratamentoUsuario = " + atendimentoForm.getInTratamentoUsuario() + " idAtendimento = " + atendimentoForm.getIdAtendimento() + " idContato = " + form.getIdContatoWF() + " docTec =  " + atendimentoForm.getDocTec() + "]");

		if (atendimentoForm.getAtendimentosVO() != null && atendimentoForm.getAtendimentosVO().length > 0) {
			// fake idAtendimento
			atendimentoForm.setFechamentoMassa(true);
			log.debug("AtendimentoFechamentoMassaController:encerrar.do - Fechamento Massa- Fim do Metodo]");

			request.setAttribute("atendimentoForm", atendimentoForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} else {
			log.debug("AtendimentoFechamentoMassaController:encerrar.do - Fechamento Massa- Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoWorkflow/encerramentoMassaBegin.do"
	 */
	protected ActionForward encerrarGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		// Monta o log da operação se possível
		log.debug("AtendimentoFechamentoMassaController:encerrarGravar.do - Fechamento Massa- Inicio do Metodo]");

		AtendimentoForm atendimentoForm = this.getAtendimentoForm(request);
		// seta o idAtividade da ação encerrar
		atendimentoForm.setIdAtividade("23");
		atendimentoForm.setInTratamentoUsuario(ConstantesCRM.STWO);
		atendimentoForm.setIdAtendimento(ConstantesCRM.SZERO);
		atendimentoForm.setComentario(form.getComentario());
		atendimentoForm.setDocumentoAssociado(form.getDocumentoAssociado());
		atendimentoForm.setIdBaixa(form.getIdBaixa());

		log.debug("AtendimentoDetalheController:encerrarGravar.do - [idAtividade = " + atendimentoForm.getIdAtividade() + " inTratamentoUsuario = " + atendimentoForm.getInTratamentoUsuario() + " idAtendimento = " + atendimentoForm.getIdAtendimento() + " comentario = " + atendimentoForm.getComentario() + " documentoAssociado = " + atendimentoForm.getDocumentoAssociado() + " idBaixa =  " + atendimentoForm.getIdBaixa() + "]");

		log.debug("AtendimentoFechamentoMassaController:encerrarGravar.do - Fechamento Massa- Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/*
	 * private String montaArvoreBaixa(AdmFolhaBaixaVO itens, String tree){
	 *
	 * StringBuffer node = new StringBuffer(); String dsMensagem = ""; String idBaixaMensagem = "";
	 *
	 * if( itens.getAdmFolhaBaixaVOArray().length == 0 ){ return ""; }else { for( int x = 0; x <
	 * itens.getAdmFolhaBaixaVOArray().length;x++ ) { if(itens.getAdmFolhaBaixaVOArray(x).getAdmMensagemBaixaVOArray()
	 * != null && itens.getAdmFolhaBaixaVOArray(x).getAdmMensagemBaixaVOArray().length > 0){ dsMensagem =
	 * itens.getAdmFolhaBaixaVOArray(x).getAdmMensagemBaixaVOArray(0).getAdmMensagemAvisoVO().getDsMensagemAviso();
	 * idBaixaMensagem = itens.getAdmFolhaBaixaVOArray(x).getAdmMensagemBaixaVOArray(0).getIdBaixaMensagem(); }
	 *
	 * node.append("var ") .append(tree) .append(x) .append(" = new WebFXTreeItem('")
	 * .append(itens.getAdmFolhaBaixaVOArray(x).getNmBaixa()) .append("',\"Javascript:mostraDadosArvore('")
	 * .append(itens.getAdmFolhaBaixaVOArray(x).getIdBaixa()) .append("', '") .append(dsMensagem)
	 * .append("', '');\",'','','');");
	 *
	 * dsMensagem = ""; idBaixaMensagem = ""; node.append(montaArvoreBaixa( itens.getAdmFolhaBaixaVOArray(x),tree+
	 * String.valueOf(x))); node.append(tree) .append(".add(") .append(tree) .append(x) .append(");\n"); } } return
	 * node.toString(); }
	 */

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoWorkflow/listaVoltar.do"
	 * @jpf:forward name="failure" path="failure.jsp"
	 */
	protected ActionForward voltar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoFechamentoMassaController:voltar.do - Fechamento Massa- Inicio do Metodo]");

		AtendimentoForm atendimentoForm = this.getAtendimentoForm(request);
		
		ActionRedirect ar = new ActionRedirect("/workflow/AtendimentoWorkflow/listaVoltar.do");
		
		ActionMapping r;
		
		// seta o idAtividade da ação encerrar
		atendimentoForm.setIdAtividade("22");
		atendimentoForm.setInTratamentoUsuario(ConstantesCRM.STWO);
		log.debug("AtendimentoDetalheController:voltar.do - [idAtividade = " + atendimentoForm.getIdAtividade() + " inTratamentoUsuario =  " + atendimentoForm.getInTratamentoUsuario() + "]");

		if (atendimentoForm.getAtendimentosVO() != null && atendimentoForm.getAtendimentosVO().length > 0) {
			log.debug("AtendimentoFechamentoMassaController:voltar.do - Fechamento Massa- Fim do Metodo]");
			request.setAttribute("atendimentoForm", atendimentoForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.getSession().setAttribute("atendimentoFormFechMassa", atendimentoForm);
			ar.setRedirect(true);
			return ar;
		} else {
			log.debug("AtendimentoFechamentoMassaController:voltar.do - Fechamento Massa- Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iArvoreContato.jsp"
	 */
	protected ActionForward obterArvoreContato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoFechamentoMassaController:obterArvoreContato.do - Fechamento Massa- Inicio do Metodo]");
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
		// sbScript.append("', '");
		// sbScript.append("');\",'classic');\n\n");

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

		AtendimentoFechamentoMassaForm form2 = this.atendimentoFechamentoMassaForm;
		form2.setScriptArvore(sbScript.toString());
		log.debug("AtendimentoDetalheController:obterArvoreContato.do - [Arvore de Contato = " + sbScript.toString() + "]");
		log.debug("AtendimentoFechamentoMassaController:obterArvoreContato.do - Fechamento Massa- Fim do Metodo]");

		request.setAttribute("form", form2);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iExpandeArvoreContatoFM.jsp"
	 */
	protected ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoFechamentoMassaController:expandeArvoreContato.do - Fechamento Massa- Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		StringBuffer sbScript = new StringBuffer(1024);
		StringBuffer sbNode = new StringBuffer(1024);

		AtendimentoArvoreFiltrosVO atFiltros = AtendimentoArvoreFiltrosVO.Factory.newInstance();
		atFiltros.setIdContato(request.getParameter("IDCONTATO"));

		AdmContatoFolhaVO admContatoFolhaVO;

		if (ConstantesCRM.SONE.equals(request.getParameter("IDCONTATO"))) {
			admContatoFolhaVO = AdmContatoFolhaVO.Factory.newInstance();
		} else {
			admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, atFiltros);
		}

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

		this.atendimentoFechamentoMassaForm.setScriptArvore(sbScript.toString());
		log.debug("AtendimentoDetalheController:obterArvoreContato.do - [Expande Arvore de Contato = " + sbScript.toString() + "]");
		log.debug("AtendimentoFechamentoMassaController:expandeArvoreContato.do - Fechamento Massa- Inicio do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward AtendimentoDetalheDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoFechamentoMassaController:AtendimentoDetalheDone.do - Fechamento Massa- Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="voltarWorkflow.jsp"
	 */
	protected ActionForward AtendimentoWorkflowDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoFechamentoMassaController:AtendimentoWorkflowDone.do - Fechamento Massa- Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AtendimentoFechamentoMassaForm extends ActionForm {

		private static final long serialVersionUID = 1L;

		public AtendimentoFechamentoMassaForm() {

			// inicicializa objeto atendimentoInformacaoVO
			this.atendimentoInformacaoVO = AtendimentoInformacaoVO.Factory.newInstance();

			// inicicializa objeto usuarioVIVO
			this.usuarioVIVO = UsuarioVIVO.Factory.newInstance();

			// inicicializa objeto alertasVO
			this.alertasVO = new AlertaVO[0];

			// inicicializa objeto atdWFTecnicoDocsVO
			atdWFTecnicoDocsVO = new AtendimentoWorkflowTecnicoDocVO[0];

		}

		private String scriptArvoreBaixa;

		public void setScriptArvoreBaixa(String scriptArvoreBaixa) {
			this.scriptArvoreBaixa = scriptArvoreBaixa;
		}

		public String getScriptArvoreBaixa() {
			return this.scriptArvoreBaixa;
		}

		// idContato
		private String idContato = ConstantesCRM.SVAZIO;

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		// idContato
		private String idContatoWF = ConstantesCRM.SVAZIO;

		public void setIdContatoWF(String idContatoWF) {
			this.idContatoWF = idContatoWF;
		}

		public String getIdContatoWF() {
			return this.idContatoWF;
		}

		// textoContato
		private String textoContato = ConstantesCRM.SVAZIO;

		public void setTextoContato(String textoContato) {
			this.textoContato = textoContato;
		}

		public String getTextoContato() {
			return this.textoContato;
		}

		// comentario
		private String comentario = ConstantesCRM.SVAZIO;

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}

		public String getComentario() {
			return this.comentario;
		}

		// login
		private String login = ConstantesCRM.SVAZIO;

		public void setLogin(String login) {
			this.login = login;
		}

		public String getLogin() {
			return this.login;
		}

		// dtSuspeitoInicio
		private String dtSuspeitoInicio = ConstantesCRM.SVAZIO;

		public void setDtSuspeitoInicio(String dtSuspeitoInicio) {
			this.dtSuspeitoInicio = dtSuspeitoInicio;
		}

		public String getDtSuspeitoInicio() {
			return this.dtSuspeitoInicio;
		}

		// dtSuspeitoFim
		private String dtSuspeitoFim = ConstantesCRM.SVAZIO;

		public void setDtSuspeitoFim(String dtSuspeitoFim) {
			this.dtSuspeitoFim = dtSuspeitoFim;
		}

		public String getDtSuspeitoFim() {
			return this.dtSuspeitoFim;
		}

		// atendimentoInformacaoVO
		private AtendimentoInformacaoVO atendimentoInformacaoVO;

		public void setAtendimentoInformacaoVO(AtendimentoInformacaoVO atendimentoInformacaoVO) {
			this.atendimentoInformacaoVO = atendimentoInformacaoVO;
		}

		public AtendimentoInformacaoVO getAtendimentoInformacaoVO() {
			return this.atendimentoInformacaoVO;
		}

		// usuarioVIVO
		private UsuarioVIVO usuarioVIVO;

		public void setUsuarioVIVO(UsuarioVIVO usuarioVIVO) {
			this.usuarioVIVO = usuarioVIVO;
		}

		public UsuarioVIVO getUsuarioVIVO() {
			return this.usuarioVIVO;
		}

		// alertasVO
		private AlertaVO[] alertasVO;

		public void setAlertasVO(AlertaVO[] alertasVO) {
			this.alertasVO = alertasVO;
		}

		public AlertaVO[] getAlertasVO() {
			return this.alertasVO;
		}

		// atdWFTecnicoDocsVO
		private AtendimentoWorkflowTecnicoDocVO[] atdWFTecnicoDocsVO;

		public void setAtdWFTecnicoDocsVO(AtendimentoWorkflowTecnicoDocVO[] atdWFTecnicoDocsVO) {
			this.atdWFTecnicoDocsVO = atdWFTecnicoDocsVO;
		}

		public AtendimentoWorkflowTecnicoDocVO[] getAtdWFTecnicoDocsVO() {
			return this.atdWFTecnicoDocsVO;
		}

		// scriptArvore
		private String scriptArvore = ConstantesCRM.SVAZIO;

		public String getScriptArvore() {
			return this.scriptArvore;
		}

		public void setScriptArvore(String scriptArvore) {
			this.scriptArvore = scriptArvore;
		}

		private String idAtividade;

		public void setIdAtividade(String idAtividade) {
			this.idAtividade = idAtividade;
		}

		public String getIdAtividade() {
			return this.idAtividade;
		}

		private String idBaixa;

		public void setIdBaixa(String idBaixa) {
			this.idBaixa = idBaixa;
		}

		public String getIdBaixa() {
			return this.idBaixa;
		}

		private String documentoAssociado;

		public void setDocumentoAssociado(String documentoAssociado) {
			this.documentoAssociado = documentoAssociado;
		}

		public String getDocumentoAssociado() {
			return this.documentoAssociado;
		}

		// abaSelecionada
		private String fila = ConstantesCRM.SVAZIO;

		public String getFila() {
			return this.fila;
		}

		public void setFila(String fila) {
			this.fila = fila;
		}
	}

	/**
	 * Getter para o FormBean
	 */
	public AtendimentoFechamentoMassaForm getAtendimentoFechamentoMassaForm() {
		return this.atendimentoFechamentoMassaForm;
	}

	public AtendimentoForm getAtendimentoFormTemp() {
		return this.atendimentoFormTemp;
	}

	private AtendimentoForm getAtendimentoForm(HttpServletRequest request) throws Exception {

		ArrayList col = new ArrayList();
		AtendimentoFechamentoMassaVO[] AtendimentoFechamentoMassaVO = this.atendimentoFechamentoMassaForm.getAtendimentoInformacaoVO().getAtendimentoFechamentoMassaVOArray();

		for (int i = 0; i < AtendimentoFechamentoMassaVO.length; i++) {

			String parameter = new StringBuffer("atdFechamentoMassaVO[").append(i).append("].operacaoWorkflow").toString();

			if (request.getParameter(parameter) != null) {
				col.add(AtendimentoFechamentoMassaVO[i].getAtendimentoVO());
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

		// Atribui telaDestino
		atendimentoForm.setFila(this.atendimentoFechamentoMassaForm.getFila());

		return atendimentoForm;
	}

	protected boolean alwaysTrackPreviousPage() {
		return true;
	}

	protected boolean alwaysTrackPreviousAction() {
		return true;
	}

}
