package workflow.Relacionamento;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO.GrupoPesquisa;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO.WFEstados;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO.WFSubEstados;
import br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentoVODocument.AtendimentoRelacionamentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentosVODocument.AtendimentoRelacionamentosVO;
import br.com.vivo.fo.workflow.vo.LinhaVODocument.LinhaVO;
import br.com.vivo.fo.workflow.vo.WFEstadoVODocument.WFEstadoVO;
import br.com.vivo.fo.workflow.vo.WFSubEstadoVODocument.WFSubEstadoVO;
import br.com.vivo.fo.workflow.vo.impl.WFEstadoVODocumentImpl.WFEstadoVOImpl;
import br.com.vivo.fo.workflow.vo.impl.WFSubEstadoVODocumentImpl.WFSubEstadoVOImpl;

import com.indracompany.actions.AbstractAction;

public class RelacionamentoController extends AbstractAction {

	private static final long serialVersionUID = 864318457715769553L;

	private static Logger log = new Logger("workflow");

	@EJB
	private br.com.vivo.fo.ctrls.workflow.relacionamento.RelacionamentoFacade relacionamentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	// variáveis e objetos
	private RelacionamentoForm relacionamentoForm;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);

		} else if ("carregarEstadosProcesso".equals(mapping.getParameter())) {
			return carregarEstadosProcesso (mapping, form, request, response);

		} else if ("pesquisarSubEstado".equals(mapping.getParameter())) {
			return pesquisarSubEstado (mapping, form, request, response);

		} else if ("pesquisarRelacionamento".equals(mapping.getParameter())) {
			return pesquisarRelacionamento (mapping, form, request, response);

		} else if ("obterArvoreContato".equals(mapping.getParameter())) {
			return obterArvoreContato (mapping, form, request, response);

		} else if ("expandeArvoreContato".equals(mapping.getParameter())) {
			return expandeArvoreContato (mapping, form, request, response);

		}else{
			return begin(mapping, form, request, response);
		}
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="Relacionamento.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelacionamentoForm form = (RelacionamentoForm) formParam;

		log.debug("RelacionamentoController:begin.do - Relacionamento- Inicio do Metodo]");
		String user =  ConstantesCRM.getCurrentUser(request.getSession());
		relacionamentoForm = new RelacionamentoForm();

		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		if(parametros != null){
			//*******VERIFICAR QUAL ID TENHO QUE PASSAR
			relacionamentoForm.setIdPessoaDePara(parametros.getIdClienteDePara() == null?"21":parametros.getIdClienteDePara());
			relacionamentoForm.setTpRelacionamento((parametros.getIdTipoRelacionamento() == null?ConstantesCRM.SONE:parametros.getIdTipoRelacionamento()));
			relacionamentoForm.setIdPessoaLinhaHistorico((parametros.getIdPessoaLinhaHistorico() == null) || (parametros.getIdPessoaLinhaHistorico().equals(ConstantesCRM.SVAZIO))?ConstantesCRM.SONE:parametros.getIdPessoaLinhaHistorico());
			relacionamentoForm.setUser((ConstantesCRM.getCurrentUser(request.getSession())));
		}else{
			relacionamentoForm.setIdPessoaDePara("21");
			relacionamentoForm.setTpRelacionamento(ConstantesCRM.SONE);
			relacionamentoForm.setIdPessoaLinhaHistorico(ConstantesCRM.SONE);
			relacionamentoForm.setUser(( ConstantesCRM.getCurrentUser(request.getSession())));
		}

		log.debug("RelacionamentoController:begin.do - [idPessoaDePara = " + relacionamentoForm.getIdPessoaDePara() + "]");
		log.debug("RelacionamentoController:begin.do - [tpRelacionamento = " + relacionamentoForm.getTpRelacionamento() + "]");
		log.debug("RelacionamentoController:begin.do - [idPessoaLinhaHistorico = " + relacionamentoForm.getIdPessoaLinhaHistorico() + "]");

		relacionamentoForm.setWfEstados(relacionamentoFacade.obterEstados(user).getWFEstados());
		relacionamentoForm.setInPrimeiraChamada(ConstantesCRM.SONE);

		if (parametros.getIdTipoRelacionamento() ==  null || parametros.getIdTipoRelacionamento().equals(ConstantesCRM.SVAZIO) || parametros.getIdTipoRelacionamento().equals(ConstantesCRM.SZERO) ) {
			if (!new String(ConstantesCRM.SVAZIO).equals(parametros.getNrLinha())) {
				relacionamentoForm.setTipoPesquisa(1);
				relacionamentoForm.setTpPesquisa(ConstantesCRM.STWO);
			} else {
				relacionamentoForm.setTipoPesquisa(0);
				relacionamentoForm.setTpPesquisa(ConstantesCRM.STHREE);
			}

		} else {
			if (parametros.getIdTipoRelacionamento().matches("[12]")) {
				relacionamentoForm.setTipoPesquisa(1);
				relacionamentoForm.setTpPesquisa(ConstantesCRM.STWO);
			} else if (parametros.getIdTipoRelacionamento().equals(ConstantesCRM.SSIX)) {
				relacionamentoForm.setTipoPesquisa(0);
				relacionamentoForm.setTpPesquisa(ConstantesCRM.STHREE);
			}
		}

		if(relacionamentoForm.getTpPesquisa() != null) {
			pesquisarRelacionamento(mapping, relacionamentoForm, request, response);
		} else {
			relacionamentoForm.setAtendimentoRelacionamentosVO(null);
		}

		relacionamentoForm.setInPrimeiraChamada(ConstantesCRM.SZERO);
		log.debug("RelacionamentoController:begin.do - Relacionamento- Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward carregarEstadosProcesso(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("RelacionamentoController:carregarEstadosProcesso.do - Relacionamento- Metodo]");
		String user =  ConstantesCRM.getCurrentUser(request.getSession());
		this.relacionamentoForm.setWfEstados(relacionamentoFacade.obterEstados(user).getWFEstados());
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iSubEstado.jsp"
	 */
	protected ActionForward pesquisarSubEstado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelacionamentoForm form = (RelacionamentoForm) formParam;
		log.debug("RelacionamentoController:pesquisarSubEstado.do - Relacionamento- Metodo]");
		relacionamentoForm = form;
		String user =  ConstantesCRM.getCurrentUser(request.getSession());
		relacionamentoForm.setAtendimentoPesquisaVO(relacionamentoFacade.pesquisarSubEstado(user, form.getEstadoSelecionado()));
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iRelacionamento.jsp"
	 */
	protected ActionForward pesquisarRelacionamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelacionamentoForm form = (RelacionamentoForm) formParam;

		log.debug("RelacionamentoController:pesquisarRelacionamento.do - Relacionamento- Inicio do Metodo]");
		relacionamentoForm = form;
		String user =  ConstantesCRM.getCurrentUser(request.getSession());
		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

		AtendimentoPesquisaVO atPesquisaVOgravar = AtendimentoPesquisaVO.Factory.newInstance();
		atPesquisaVOgravar.setInPrimeiraChamada(form.inPrimeiraChamada);
		log.debug("RelacionamentoController:begin.do - [inPrimeiraChamada = " + form.inPrimeiraChamada + "]");
		atPesquisaVOgravar.setDtAberturaInicio(form.getDtAberturaInicio());
		log.debug("RelacionamentoController:begin.do - [dtAberturaInicio = " + form.getDtAberturaInicio() + "]");
		atPesquisaVOgravar.setDtAberturaFim(form.getDtAberturaFim());
		log.debug("RelacionamentoController:begin.do - [dtAberturaFim = " + form.getDtAberturaFim() + "]");
		atPesquisaVOgravar.setDtFechamentoInicio(form.getDtFechamentoInicio());
		log.debug("RelacionamentoController:begin.do - [dtFechamentoInicio = " + form.getDtFechamentoInicio() + "]");
		atPesquisaVOgravar.setDtFechamentoFim(form.getDtFechamentoFim());
		log.debug("RelacionamentoController:begin.do - [dtFechamentoFim = " + form.getDtFechamentoFim() + "]");
		atPesquisaVOgravar.setIdAtendimento(form.getIdAtendimento());
		log.debug("RelacionamentoController:begin.do - [idAtendimento = " + form.getIdAtendimento() + "]");

		GrupoPesquisa grupoPesquisa = GrupoPesquisa.Factory.newInstance();
		grupoPesquisa.setLinhaVO(LinhaVO.Factory.newInstance());
		if(parametros != null){
			//*******VERIFICAR QUAL ID TENHO QUE PASSAR
			grupoPesquisa.setTpRelacionamento(parametros.getIdTipoRelacionamento() == null?ConstantesCRM.SONE:parametros.getIdTipoRelacionamento());
			if ((parametros.getIdTipoRelacionamento() == null) || (parametros.getIdTipoRelacionamento().equals(ConstantesCRM.SONE))) {
				grupoPesquisa.setIdPessoaDePara(parametros.getIdUsuarioDePara() == null?"21":parametros.getIdUsuarioDePara());
			} else {
				grupoPesquisa.setIdPessoaDePara(parametros.getIdClienteDePara() == null?"21":parametros.getIdClienteDePara());
			}
		} else {
			grupoPesquisa.setTpRelacionamento(ConstantesCRM.SONE);
		}
		log.debug("RelacionamentoController:begin.do - [tpRelacionamento = " + grupoPesquisa.getTpRelacionamento() + "]");
		grupoPesquisa.getLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(form.getIdPessoaLinhaHistorico()));
		log.debug("RelacionamentoController:begin.do - [idPessoaLinhaHistorico = " + form.getIdPessoaLinhaHistorico().toString() + "]");

		atPesquisaVOgravar.setGrupoPesquisa(grupoPesquisa);
		if(form.getTpPesquisa() != null) {
			atPesquisaVOgravar.getGrupoPesquisa().setTpPesquisa(form.getTpPesquisa());
		}

		//atPesquisaVOgravar.setTextoContato(form.getContato());
		atPesquisaVOgravar.setIdContato(form.getIdContato());
		log.debug("RelacionamentoController:begin.do - [idContato = " + form.getIdContato() + "]");

		atPesquisaVOgravar.setWFEstados(WFEstados.Factory.newInstance());
		WFEstadoVO[] wfEstado = new WFEstadoVO[1];
		wfEstado[0] = WFEstadoVO.Factory.newInstance();
		atPesquisaVOgravar.getWFEstados().setWFEstadoVOArray(wfEstado);
		atPesquisaVOgravar.getWFEstados().getWFEstadoVOArray(0).setIdEstado(form.getEstadoSelecionado());
		log.debug("RelacionamentoController:begin.do - [idEstado = " + form.getEstadoSelecionado() + "]");

		atPesquisaVOgravar.setWFSubEstados(WFSubEstados.Factory.newInstance());
		WFSubEstadoVO[] wfSubEstado = new WFSubEstadoVO[1];
		wfSubEstado[0] = WFSubEstadoVO.Factory.newInstance();
		atPesquisaVOgravar.getWFSubEstados().setWFSubEstadoVOArray(wfSubEstado);
		atPesquisaVOgravar.getWFSubEstados().getWFSubEstadoVOArray(0).setIdSubEstado(form.getSubEstadoSelecionado());

		relacionamentoForm.setAtendimentoRelacionamentosVO(relacionamentoFacade.pesquisarRelacionamento(user, atPesquisaVOgravar));
		if(relacionamentoForm.getAtendimentoRelacionamentosVO() != null){
			for(int cont=0; cont < relacionamentoForm.getAtendimentoRelacionamentosVO().getAtendimentoRelacionamentoVOArray().length; cont++){
				if(relacionamentoForm.getAtendimentoRelacionamentosVO().getAtendimentoRelacionamentoVOArray(cont).getAtendimentoVO().getNrTelefone() == null || relacionamentoForm.getAtendimentoRelacionamentosVO().getAtendimentoRelacionamentoVOArray(cont).getAtendimentoVO().getNrTelefone().equals("")){
					relacionamentoForm.getAtendimentoRelacionamentosVO().getAtendimentoRelacionamentoVOArray(cont).getAtendimentoVO().setNrTelefone("-");
				}
				if(relacionamentoForm.getAtendimentoRelacionamentosVO().getAtendimentoRelacionamentoVOArray(cont).getAtendimentoVO().getInACS().equals("S")) {
					relacionamentoForm.setFlagACS(true);
				} else if(relacionamentoForm.getAtendimentoRelacionamentosVO().getAtendimentoRelacionamentoVOArray(cont).getAtendimentoVO().getInACS().equals("N")) {
					relacionamentoForm.setFlagACS(false);
				}
			}
		}
		log.debug("RelacionamentoController:pesquisarRelacionamento.do - Relacionamento- Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iArvoreContato.jsp"
	 */
	protected ActionForward obterArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelacionamentoForm form = (RelacionamentoForm) formParam;

		log.debug("RelacionamentoController:obterArvoreContato.do - Relacionamento- Inicio do Metodo]");
		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(form.getUser(), null);

		if(admContatoFolhaVO == null) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
		}
		StringBuffer script =
			new StringBuffer("if (document.getElementById) {var tree = new WebFXTree('")
		.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getNmContato()))
		.append("',\"Javascript:selecionaContato('")
		.append(admContatoFolhaVO.getIdContato())
		.append("', '');\",'classic');");

		StringBuffer nodo;

		for( int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length;i++ ) {

			if(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals(ConstantesCRM.SONE)){
				nodo = new StringBuffer("var arvore")
				.append(i)
				.append(" = new WebFXTreeItem('")
				.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato()))
				.append("',\"Javascript:selecionaContato('")
				.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato())
				.append("', '")
				.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha())
				.append("', '")
				.append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath()))
				.append("', '');\",'','','');");
			} else {
				nodo = new StringBuffer("var arvore")
				.append(i)
				.append(" = new WebFXTreeItem('")
				.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato()))
				.append("',\"Javascript:selecionaContato('")
				.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato())
				.append("', '")
				.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha())
				.append("', '")
				.append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath()))
				.append("', '');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');");
			}
			script.append(nodo);
			script.append("tree.add(arvore")
			.append(i)
			.append(");\n\n");

		}

		script.append("document.write(tree);}");

		//RelacionamentoForm relacionamentoForm = this.relacionamentoForm;
		this.relacionamentoForm = new RelacionamentoForm();
		this.relacionamentoForm.setScriptArvore(script.toString());
		log.debug("RelacionamentoController:obterArvoreContato.do - [Arvore de Contato = " + script.toString() + "]");
		log.debug("RelacionamentoController:obterArvoreContato.do - Relacionamento- FIm do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iExpandeArvoreContatoRel.jsp"
	 */
	protected ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RelacionamentoForm form = (RelacionamentoForm) formParam;
		log.debug("RelacionamentoController:expandeArvoreContato.do - Relacionamento- Inicio do Metodo]");
		StringBuffer script = new StringBuffer();
		StringBuffer nodo;

		AtendimentoArvoreFiltrosVO atFiltros = AtendimentoArvoreFiltrosVO.Factory.newInstance();
		atFiltros.setIdContato(request.getParameter("IDCONTATO"));

		// Obtem idPessoaUsuario da Sessão
		String user =  ConstantesCRM.getCurrentUser(request.getSession());
		form.setUser(user);

		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(form.getUser(), atFiltros);
		for( int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length;i++ ) {

			if(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals(ConstantesCRM.SONE)){

				nodo = new StringBuffer("var arvore")
				.append(i)
				.append(" = new parent.WebFXTreeItem('")
				.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato()))
				.append("',\"Javascript:selecionaContato('")
				.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato())
				.append("', '")
				.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha())
				.append("', '")
				.append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath()))
				.append("', '');\",'','','');");
			} else {
				nodo = new StringBuffer("var arvore")
				.append(i)
				.append(" = new parent.WebFXTreeItem('")
				.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato()))
				.append("',\"Javascript:selecionaContato('")
				.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato())
				.append("', '")
				.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha())
				.append("', '")
				.append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath()))
				.append("', '');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');");
			}
			script.append(nodo);
			script.append("parent.tree.getSelected().add(arvore")
			.append(i)
			.append(");\n\n");

		}
		script.append("parent.tree.getSelected().setAddEnabled(false);\n\n");
		script.append("parent.tree.getSelected().expand();\n\n");

		this.relacionamentoForm.setScriptArvore(script.toString());
		log.debug("RelacionamentoController:expandeArvoreContato.do - [Expande Arvore de Contato = " + script.toString() + "]");
		log.debug("RelacionamentoController:expandeArvoreContato.do - Relacionamento- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RelacionamentoForm extends ActionForm {

		private static final long serialVersionUID = -3738552801360657012L;

		private int tipoPesquisa;

		// informações obtidas na URL
		private String idPessoaDePara;
		private String tpRelacionamento;
		private String idPessoaLinhaHistorico;
		private String idLinha;
		private String user;
		private String dtAberturaInicio;
		private String dtAberturaFim;
		private String dtFechamentoInicio;
		private String dtFechamentoFim;
		private String idAtendimento;
		private String nrProtocolo;
		private String contato;
		private String idContato;
		private String estadoSelecionado;
		private String subEstadoSelecionado;
		private String tpPesquisa;
		private boolean flagACS;
		// scriptArvore
		private String scriptArvore = ConstantesCRM.SVAZIO;
		private String inPrimeiraChamada;

		private boolean historicoRelacionamento = false;
		private boolean historicoRelacionamentoMG = false;

		//VOs
		private AtendimentoPesquisaVO atendimentoPesquisaVO;
		private WFEstados wfEstados;
		private WFSubEstados wfSubEstados;
		private AtendimentoRelacionamentosVO atendimentoRelacionamentosVO;

		private AtendimentoRelacionamentosVO processosDeProtocolo;

		public RelacionamentoForm(){
			atendimentoPesquisaVO = AtendimentoPesquisaVO.Factory.newInstance();
			atendimentoPesquisaVO.setWFEstados(WFEstados.Factory.newInstance());
			atendimentoPesquisaVO.getWFEstados().setWFEstadoVOArray(new WFEstadoVOImpl[0]);

			atendimentoPesquisaVO.setWFSubEstados(WFSubEstados.Factory.newInstance());
			atendimentoPesquisaVO.getWFSubEstados().setWFSubEstadoVOArray(new WFSubEstadoVOImpl[0]);

			wfEstados = WFEstados.Factory.newInstance();
			wfEstados.setWFEstadoVOArray(new WFEstadoVOImpl[0]);

			wfSubEstados = WFSubEstados.Factory.newInstance();
			wfSubEstados.setWFSubEstadoVOArray(new WFSubEstadoVOImpl[0]);

			AtendimentoRelacionamentoVO atendimentoRelacionamentoVO = AtendimentoRelacionamentoVO.Factory.newInstance();
			atendimentoRelacionamentosVO = AtendimentoRelacionamentosVO.Factory.newInstance();
			atendimentoRelacionamentosVO.setAtendimentoRelacionamentoVOArray(new AtendimentoRelacionamentoVO[]{atendimentoRelacionamentoVO});
			processosDeProtocolo = AtendimentoRelacionamentosVO.Factory.newInstance();
			processosDeProtocolo.setAtendimentoRelacionamentoVOArray(new AtendimentoRelacionamentoVO[]{atendimentoRelacionamentoVO});
		}

		public String getInPrimeiraChamada() {
			return this.inPrimeiraChamada;
		}

		public void setInPrimeiraChamada(String inPrimeiraChamada) {
			this.inPrimeiraChamada = inPrimeiraChamada;
		}

		public String getIdLinha() {
			return this.idLinha;
		}

		public void setIdLinha(String idLinha) {
			this.idLinha = idLinha;
		}

		public String getScriptArvore() {
			return this.scriptArvore;
		}

		public void setScriptArvore(String scriptArvore) {
			this.scriptArvore = scriptArvore;
		}

		public boolean getFlagACS() {
			return flagACS;
		}

		public String getTpPesquisa() {
			return tpPesquisa;
		}

		public AtendimentoRelacionamentosVO getAtendimentoRelacionamentosVO() {
			return atendimentoRelacionamentosVO;
		}

		public AtendimentoRelacionamentosVO getProcessosDeProtocolo() {
			return processosDeProtocolo;
		}

		public WFEstados getWfEstados() {
			return wfEstados;
		}

		public WFSubEstados getWfSubEstados() {
			return wfSubEstados;
		}

		public AtendimentoPesquisaVO getAtendimentoPesquisaVO() {
			return atendimentoPesquisaVO;
		}

		public String getIdPessoaDePara() {
			return idPessoaDePara;
		}

		public String getTpRelacionamento() {
			return tpRelacionamento;
		}

		public String getIdPessoaLinhaHistorico() {
			return idPessoaLinhaHistorico;
		}

		public String getUser() {
			return user;
		}

		public String getDtAberturaInicio() {
			return dtAberturaInicio;
		}

		public String getDtAberturaFim() {
			return dtAberturaFim;
		}

		public String getDtFechamentoInicio() {
			return dtFechamentoInicio;
		}

		public String getDtFechamentoFim() {
			return dtFechamentoFim;
		}

		public String getIdAtendimento() {
			return idAtendimento;
		}

		public String getNrProtocolo() {
			return nrProtocolo;
		}

		public String getIdContato() {
			return idContato;
		}

		public String getContato() {
			return contato;
		}

		public String getEstadoSelecionado() {
			return estadoSelecionado;
		}

		public String getSubEstadoSelecionado() {
			return subEstadoSelecionado;
		}

		public void setFlagACS(boolean flagACS) {
			this.flagACS = flagACS;
		}

		public void setTpPesquisa(String tpPesquisa) {
			this.tpPesquisa = tpPesquisa;
		}

		public void setWfSubEstados(WFSubEstados wfSubEstados) {
			this.wfSubEstados = wfSubEstados;
		}

		public void setAtendimentoRelacionamentosVO(AtendimentoRelacionamentosVO arg) {
			this.atendimentoRelacionamentosVO = arg;
		}

		public void setProcessosDeProtocolo(AtendimentoRelacionamentosVO arg) {
			this.processosDeProtocolo = arg;
		}

		public void setWfEstados(WFEstados wfEstados) {
			this.wfEstados = wfEstados;
		}

		public void setIdPessoaDePara(String idPessoaDePara) {
			this.idPessoaDePara = idPessoaDePara;
		}

		public void setTpRelacionamento(String tpRelacionamento) {
			this.tpRelacionamento = tpRelacionamento;
		}

		public void setIdPessoaLinhaHistorico(String idPessoaLinhaHistorico) {
			this.idPessoaLinhaHistorico = idPessoaLinhaHistorico;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public void setAtendimentoPesquisaVO(AtendimentoPesquisaVO atendimentoPesquisaVO) {
			this.atendimentoPesquisaVO = atendimentoPesquisaVO;
		}

		public void setDtAberturaInicio(String dtAberturaInicio) {
			this.dtAberturaInicio = dtAberturaInicio;
		}

		public void setDtAberturaFim(String dtAberturaFim) {
			this.dtAberturaFim = dtAberturaFim;
		}

		public void setDtFechamentoInicio(String dtFechamentoInicio) {
			this.dtFechamentoInicio = dtFechamentoInicio;
		}

		public void setDtFechamentoFim(String dtFechamentoFim) {
			this.dtFechamentoFim = dtFechamentoFim;
		}

		public void setIdAtendimento(String idAtendimento) {
			this.idAtendimento = idAtendimento;
		}

		public void setNrProtocolo(String nrProtocolo) {
			this.nrProtocolo = nrProtocolo;
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public void setContato(String contato) {
			this.contato = contato;
		}

		public void setEstadoSelecionado(String estadoSelecionado) {
			this.estadoSelecionado = estadoSelecionado;
		}

		public void setSubEstadoSelecionado(String subEstadoSelecionado) {
			this.subEstadoSelecionado = subEstadoSelecionado;
		}

		public void setTipoPesquisa(int tipoPesquisa)
		{
			this.tipoPesquisa = tipoPesquisa;
		}

		public int getTipoPesquisa()
		{
			return this.tipoPesquisa;
		}

		public void setHistoricoRelacionamento(boolean arg) {
			this.historicoRelacionamento = arg;
		}

		public boolean isHistoricoRelacionamento() {
			return this.historicoRelacionamento;
		}

		public void setHistoricoRelacionamentoMG(boolean arg) {
			this.historicoRelacionamentoMG = arg;
		}

		public boolean isHistoricoRelacionamentoMG() {
			return this.historicoRelacionamentoMG;
		}

	}
	/*
    public static class FormEntrada extends ActionForm {
        private String user;
        private String idPessoa;
        private String idPessoaDePara;
        private String idLinha;
        private String tpRelacionamento;
        private GrupoPesquisa grupoPesquisa = null;

        public String getUser(){
            return this.user;
        }

        public void setUser(String user){
            this.user = user;
        }

        public String getIdPessoa(){
            return this.idPessoa;
        }

        public void setIdPessoa(String idPessoa){
            this.idPessoa = idPessoa;
        }

        public GrupoPesquisa getGrupoPesquisa(){
            return this.grupoPesquisa;
        }

        public void setGrupoPesquisa(GrupoPesquisa grupoPesquisa){
            this.grupoPesquisa = grupoPesquisa;
        }
    }
	 */

	// getter para o ActionForm
	public RelacionamentoForm getRelacionamentoForm() {
		return this.relacionamentoForm;
	}
}
