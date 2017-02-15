package workflow.AtendimentoInBoxResposta;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import workflow.AtendimentoInBox.AtendimentoInBoxController.ExpandeArvoreContatoForm;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument.RWFAtendimentosVO;
import br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO;
import br.com.vivo.fo.workflow.vo.RWFInboxUserVODocument.RWFInboxUserVO;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;
import cliente.AssociarGestor.AssociarCRController.GrupoCRIForm;

import com.indracompany.actions.AbstractAction;

public class AtendimentoInBoxRespostaController extends AbstractAction {

	private static final long serialVersionUID = 3141757915466586466L;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.monitoramento.MonitoramentoFacade manFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.RAtendimento.RAtendimento rAtendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade atendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	protected global.Global globalApp = new global.Global();

	private static Logger log = new Logger("workinbox");

	private AtendimentoInBoxCRIForm atendimentoInBoxCRIForm = null;
	private GrupoCRIForm grupoCRIForm = new GrupoCRIForm();
	// private AtendimentoInBoxCRIForm atendimentoInBoxCRIFormGrupos = null;
	// private AtendimentoFilaForm atendimentoFilaForm = new AtendimentoFilaForm();
	private static final String RAIZ_PRINCIPAL = "PAI";

	public void setGrupoCRIForm(GrupoCRIForm grupoCRIForm) {
		this.grupoCRIForm = grupoCRIForm;
	}

	public GrupoCRIForm getGrupoCRIForm() {
		return this.grupoCRIForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("getGrupos".equals(mapping.getParameter())) {
			return getGrupos(mapping, form, request, response);
		} else if ("pesquisarProcessos".equals(mapping.getParameter())) {
			return pesquisarProcessos(mapping, form, request, response);
		} else if ("pesquisarMensagens".equals(mapping.getParameter())) {
			return pesquisarMensagens(mapping, form, request, response);
		} else if ("pesquisarProcessosAdq".equals(mapping.getParameter())) {
			return pesquisarProcessosAdq(mapping, form, request, response);
		} else if ("analistaDisponivel".equals(mapping.getParameter())) {
			return analistaDisponivel(mapping, form, request, response);
		} else if ("alerta".equals(mapping.getParameter())) {
			return alerta(mapping, form, request, response);
		} else if ("irPesquisaNome".equals(mapping.getParameter())) {
			return irPesquisaNome(mapping, form, request, response);
		} else if ("pesquisarCRI".equals(mapping.getParameter())) {
			return pesquisarCRI(mapping, form, request, response);
		} else if ("AtendimentoWorkflowDone".equals(mapping.getParameter())) {
			return AtendimentoWorkflowDone(mapping, form, request, response);
		} else if ("obterArvoreContato".equals(mapping.getParameter())) {
			return obterArvoreContato(mapping, form, request, response);
		} else if ("expandeArvoreContato".equals(mapping.getParameter())) {
			return expandeArvoreContato(mapping, form, request, response);
		} else if ("detalheNota".equals(mapping.getParameter())) {
			return detalheNota(mapping, form, request, response);
		} else if ("AtendimentoDetalheDone".equals(mapping.getParameter())) {
			return AtendimentoDetalheDone(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="WorkflowInBoxRC.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxRespostaController:begin.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:begin.do - [user = " + user + "]");
		atendimentoInBoxCRIForm = new AtendimentoInBoxCRIForm();

		// traz Grupos
		WFGrupoVO[] grupos = manFacade.obtemGruposRC(user, 0).getWFGrupoVOArray();
		this.atendimentoInBoxCRIForm.setGruposVO(grupos);

		// traz alertas
		AlertaVO[] vAlertas = atendimentoFacade.obtemPrazos(user);
		this.atendimentoInBoxCRIForm.setAlertasVO(vAlertas);

		this.atendimentoInBoxCRIForm.setMotivosVO(rAtendimentoFacade.getWFMotivosVO(user, "<idTabelaMotivo>50</idTabelaMotivo>").getWFAtdNotaVOArray(0).getWFMotivoVOArray());

		this.atendimentoInBoxCRIForm.setAcoesVO(rAtendimentoFacade.getOperacaoNota(user).getWFAtdNotaVOArray(0).getWFAcaoVOArray());

		// traz estados
		this.atendimentoInBoxCRIForm.setEstadosVO(atendimentoFacade.getWFEstadosVO(user, false));

		// seta analista disponivel
		rAtendimentoFacade.analistaDisponivelRC(user, ConstantesCRM.SZERO);

		// traz dados do usuário
		RWFInboxUserVO userVO = rAtendimentoFacade.getUsuarioCampanha(user);
		this.atendimentoInBoxCRIForm.setNmLoginUsuario(userVO.getNmLoginUsuario());
		log.debug("AtendimentoInBoxRespostaController:begin.do - [nmLoginUsuario = " + this.atendimentoInBoxCRIForm.getNmLoginUsuario() + "]");
		this.atendimentoInBoxCRIForm.setInDisponivelWF(userVO.getInDisponivelWF());
		log.debug("AtendimentoInBoxRespostaController:begin.do - [inDisponivelWF = " + this.atendimentoInBoxCRIForm.getInDisponivelWF() + "]");
		this.atendimentoInBoxCRIForm.setRetornoWFCTIVO(userVO.getRetornoWFCTIVOArray());

		if (request.getSession().getAttribute("inRC") != null) {
			request.getSession().removeAttribute("inRC");
		}
		log.debug("AtendimentoInBoxRespostaController:begin.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	private RWFInBoxPesquisaVO construyePesquisaVO(AtendimentoInBoxCRIForm form, HttpServletRequest request) {

		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - Inicio do Metodo]");
		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();
		// rwfInBoxPesquisaVO.setTabPausa(form.getInAbaPesquisa());
		rwfInBoxPesquisaVO.setInAbaPesquisa(form.getInAbaPesquisa());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [inAbaPesquisa = " + form.getInAbaPesquisa() + "]");
		rwfInBoxPesquisaVO.setDtAberturaFim(form.getDtAberturaFim());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [dtAberturaFim = " + form.getDtAberturaFim() + "]");
		rwfInBoxPesquisaVO.setIdGrupo(form.getGrupoSel());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [idGrupo = " + form.getGrupoSel() + "]");
		rwfInBoxPesquisaVO.setDtAberturaInicio(form.getDtAberturaInicio());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [dtAberturaInicio = " + form.getDtAberturaInicio() + "]");
		if (form.getDtFechamentoFim() != null && form.getDtFechamentoFim().length() != 0) {
			rwfInBoxPesquisaVO.setDtFechamentoFim(form.getDtFechamentoFim());
			log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [dtFechamentoFim = " + form.getDtFechamentoFim() + "]");
		}
		if (form.getDtFechamentoInicio() != null && form.getDtFechamentoInicio().length() != 0) {
			rwfInBoxPesquisaVO.setDtFechamentoInicio(form.getDtFechamentoInicio());
			log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [dtFechamentoInicio = " + form.getDtFechamentoInicio() + "]");
		}
		rwfInBoxPesquisaVO.setIdAtendimento(form.getIdAtendimento());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [idAtendimento = " + form.getIdAtendimento() + "]");
		rwfInBoxPesquisaVO.setIdContato(form.getIdContato());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [idContato = " + form.getIdContato() + "]");
		rwfInBoxPesquisaVO.setIdEstado(form.getEstadoSel());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [idEstado = " + form.getEstadoSel() + "]");
		rwfInBoxPesquisaVO.setIdSubEstado(form.getSubEstadoSel());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [idSubEstado = " + form.getSubEstadoSel() + "]");
		if (form.getNrLinha() != null) {
			rwfInBoxPesquisaVO.setNrLinha(form.getNrLinha().replaceAll("[\\(\\)-]*", ""));
		} else {
			rwfInBoxPesquisaVO.setNrLinha(null);
		}
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [nrLinha = " + rwfInBoxPesquisaVO.getNrLinha() + "]");
		if (!("".equals(form.getDocumento()))) {
			String strOptDocSel = "" + form.getOptDocSel();
			if ("CPF".equals(strOptDocSel)) {
				rwfInBoxPesquisaVO.setTipoDocumento("CPF");
				log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [tipoDocumento = " + rwfInBoxPesquisaVO.getTipoDocumento() + "]");
			} else if ("CNPJ".equals(strOptDocSel)) {
				rwfInBoxPesquisaVO.setTipoDocumento("CNPJ");
				log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [tipoDocumento = " + rwfInBoxPesquisaVO.getTipoDocumento() + "]");
			}

			rwfInBoxPesquisaVO.setDocumento(form.getDocumento().replaceAll("[\\.\\-\\/]*", ""));
		}

		if (!("".equals(form.getTipoPesquisa()))) {
			rwfInBoxPesquisaVO.setInTipoPesquisa(form.getTipoPesquisa());
			log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [inTipoPessoa = " + form.getTipoPesquisa() + "]");
			rwfInBoxPesquisaVO.setDocumento(form.getValorPesquisa().replaceAll("[\\(\\)\\.\\-\\/]*", ""));
			if (form.getInAbaPesquisa().equals(ConstantesCRM.SSIX)) {
				String strTipoPesquisa = "" + form.getTipoPesquisa();
				if (strTipoPesquisa.equals("CPF") || strTipoPesquisa.equals("CNPJ")) {
					rwfInBoxPesquisaVO.setInTipoPesquisa(ConstantesCRM.SFOUR);
					rwfInBoxPesquisaVO.setTipoDocumento(strTipoPesquisa);
					log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [inTipoPesquisa = " + rwfInBoxPesquisaVO.getInTipoPesquisa() + "]");
					log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [tipoDocumento = " + rwfInBoxPesquisaVO.getTipoDocumento() + "]");
				} else if (strTipoPesquisa.equals("NOME") || strTipoPesquisa.equals("RAZAO")) {
					rwfInBoxPesquisaVO.setInTipoPesquisa(ConstantesCRM.STHREE);
					rwfInBoxPesquisaVO.setDocumento(request.getParameter("idPessoa"));
					log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [inTipoPesquisa = " + rwfInBoxPesquisaVO.getInTipoPesquisa() + "]");
					log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [documento = " + rwfInBoxPesquisaVO.getDocumento() + "]");
				}
			}
		}

		rwfInBoxPesquisaVO.setIdAlerta(form.getPrazoSel());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [idAlerta = " + form.getPrazoSel() + "]");
		rwfInBoxPesquisaVO.setTextoContato(form.getTextoContato());
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - [textoContato = " + form.getTextoContato() + "]");
		log.debug("AtendimentoInBoxRespostaController:construyePesquisaVO - Fim do Metodo]");
		return rwfInBoxPesquisaVO;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="blank" path="blank.jsp"
	 * @jpf:forward name="success" path="innerGrupos.jsp"
	 */
	protected ActionForward getGrupos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxRespostaController:getGrupos.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:getGrupos.do - [user = " + user + "]");

		atendimentoInBoxCRIForm = new AtendimentoInBoxCRIForm();
		WFGrupoVO[] grupos;

		if (ConstantesCRM.SONE.equals(request.getParameter("inFila"))) {
			grupos = manFacade.obtemGruposRC(user, 1).getWFGrupoVOArray();
			this.atendimentoInBoxCRIForm.setGruposVO(grupos);
		} else if (ConstantesCRM.SZERO.equals(request.getParameter("inFila"))) {
			grupos = manFacade.obtemGruposRC(user, 0).getWFGrupoVOArray();
			this.atendimentoInBoxCRIForm.setGruposVO(grupos);
		} else {
			log.debug("AtendimentoInBoxRespostaController:getGrupos.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("blank");
		}
		log.debug("AtendimentoInBoxRespostaController:getGrupos.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iMeusProcessos.jsp"
	 */
	protected ActionForward pesquisarProcessos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoInBoxCRIForm form = (AtendimentoInBoxCRIForm) formParam;

		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessos.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessos.do - [user = " + user + "]");

		// Obtém a lista de ramais para o callcenter
		if (form.getInDisponivelWF().equals(ConstantesCRM.SONE)) {
			rAtendimentoFacade.obtemProximoProcesso(user, "<inRC>1</inRC>");
		}

		// Inicializacion de Atualizacao en el formulario de retorno
		if (this.atendimentoInBoxCRIForm == null) {

			atendimentoInBoxCRIForm = new AtendimentoInBoxCRIForm();

			AlertaVO[] vAlertas = atendimentoFacade.obtemPrazos(user);
			this.atendimentoInBoxCRIForm.setAlertasVO(vAlertas);
			this.atendimentoInBoxCRIForm.setMotivosVO(rAtendimentoFacade.getWFMotivosVO(user, "<idTabelaMotivo>50</idTabelaMotivo>").getWFAtdNotaVOArray(0).getWFMotivoVOArray());
			this.atendimentoInBoxCRIForm.setAcoesVO(rAtendimentoFacade.getOperacaoNota(user).getWFAtdNotaVOArray(0).getWFAcaoVOArray());
			this.atendimentoInBoxCRIForm.setEstadosVO(atendimentoFacade.getWFEstadosVO(user, false));
			rAtendimentoFacade.analistaDisponivelRC(user, "0");
			RWFInboxUserVO userVO = rAtendimentoFacade.getUsuarioCampanha(user);
			this.atendimentoInBoxCRIForm.setNmLoginUsuario(userVO.getNmLoginUsuario());
			log.debug("AtendimentoInBoxRespostaController:pesquisarProcessos.do - [nmLoginUsuario = " + userVO.getNmLoginUsuario() + "]");
			this.atendimentoInBoxCRIForm.setInDisponivelWF(userVO.getInDisponivelWF());
			log.debug("AtendimentoInBoxRespostaController:pesquisarProcessos.do - [inDisponivelWF = " + this.atendimentoInBoxCRIForm.getInDisponivelWF() + "]");
			this.atendimentoInBoxCRIForm.setRetornoWFCTIVO(userVO.getRetornoWFCTIVOArray());
		}
		this.atendimentoInBoxCRIForm.setAtualizacao(5);

		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = this.construyePesquisaVO(form, request);
		RWFAtendimentosVO atds = rAtendimentoFacade.getInboxRC(user, rwfInBoxPesquisaVO);
		this.atendimentoInBoxCRIForm.setNrRegistros(atds.getNrRegistros());
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessos.do - [nrRegistros = " + atds.getNrRegistros() + "]");
		this.atendimentoInBoxCRIForm.setTotalRegistros(atds.getTotalRegistros());
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessos.do - [totalRegistros = " + atds.getTotalRegistros() + "]");
		this.atendimentoInBoxCRIForm.setRwfAtendimentosVO(atds);
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessos.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iMensagens.jsp"
	 */
	protected ActionForward pesquisarMensagens(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoInBoxCRIForm form = (AtendimentoInBoxCRIForm) formParam;

		log.debug("AtendimentoInBoxRespostaController:pesquisarMensagens.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:pesquisarMensagens.do - [user = " + user + "]");
		if (this.atendimentoInBoxCRIForm == null) {
			atendimentoInBoxCRIForm = new AtendimentoInBoxCRIForm();
		}

		// Inicializacion de Atualizacao en el formulario de retorno
		this.atendimentoInBoxCRIForm.setAtualizacao(5);
		log.debug("AtendimentoInBoxRespostaController:pesquisarMensagens.do - [user = " + this.atendimentoInBoxCRIForm.getAtualizacao() + "]");

		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = this.construyePesquisaVO(form, request);
		RWFAtendimentosVO atds = rAtendimentoFacade.getMsgInboxRC(user, rwfInBoxPesquisaVO);

		this.atendimentoInBoxCRIForm.setNrRegistros(atds.getNrRegistros());
		log.debug("AtendimentoInBoxRespostaController:pesquisarMensagens.do - [nrRegistros = " + atds.getNrRegistros() + "]");
		this.atendimentoInBoxCRIForm.setTotalRegistros(atds.getTotalRegistros());
		log.debug("AtendimentoInBoxRespostaController:pesquisarMensagens.do - [totalRegistros = " + atds.getTotalRegistros() + "]");
		this.atendimentoInBoxCRIForm.setRwfAtendimentosVO(atds);
		log.debug("AtendimentoInBoxRespostaController:pesquisarMensagens.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iMeusProcessos.jsp"
	 */
	protected ActionForward pesquisarProcessosAdq(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoInBoxCRIForm form = (AtendimentoInBoxCRIForm) formParam;

		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessosAdq.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessosAdq.do - [user = " + user + "]");
		// Inicializacion de Atualizacao en el formulario de retorno
		this.atendimentoInBoxCRIForm.setAtualizacao(5);
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessosAdq.do - [user = " + this.atendimentoInBoxCRIForm.getAtualizacao() + "]");
		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();

		if (form.getAdq_tipoPesquisa().equals("NOME") || form.getAdq_tipoPesquisa().equals("RAZAO")) {
			rwfInBoxPesquisaVO.setInTipoPesquisa(ConstantesCRM.STHREE);
			rwfInBoxPesquisaVO.setDocumento(form.getAdq_valorPesquisa());
		} else if (form.getAdq_tipoPesquisa().equals("CPF") || form.getAdq_tipoPesquisa().equals("CNPJ")) {
			rwfInBoxPesquisaVO.setInTipoPesquisa(ConstantesCRM.SFOUR);
			rwfInBoxPesquisaVO.setTipoDocumento(form.getAdq_tipoPesquisa());
			log.debug("AtendimentoInBoxRespostaController:pesquisarProcessosAdq.do - [tipoDocumento = " + rwfInBoxPesquisaVO.getTipoDocumento() + "]");
			rwfInBoxPesquisaVO.setDocumento(form.getAdq_valorPesquisa());
		} else {
			rwfInBoxPesquisaVO.setInTipoPesquisa(form.getAdq_tipoPesquisa());
			rwfInBoxPesquisaVO.setDocumento(form.getAdq_valorPesquisa().replaceAll("[\\(\\)\\.\\-\\/]*", ""));
		}
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessosAdq.do - [inTipoPesquisa = " + rwfInBoxPesquisaVO.getInTipoPesquisa() + "]");
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessosAdq.do - [documento = " + rwfInBoxPesquisaVO.getDocumento() + "]");
		RWFAtendimentosVO atds = rAtendimentoFacade.getInboxCRI(user, rwfInBoxPesquisaVO);

		this.atendimentoInBoxCRIForm.setNrRegistros(atds.getNrRegistros());
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessosAdq.do - [nrRegistros = " + this.atendimentoInBoxCRIForm.getNrRegistros() + "]");
		this.atendimentoInBoxCRIForm.setTotalRegistros(atds.getTotalRegistros());
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessosAdq.do - [totalRegistros = " + this.atendimentoInBoxCRIForm.getTotalRegistros() + "]");
		this.atendimentoInBoxCRIForm.setRwfAtendimentosVO(atds);
		log.debug("AtendimentoInBoxRespostaController:pesquisarProcessosAdq.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/*
	 * private void atribuiAtdFilaPesqVO(AtendimentoFilaForm form) throws Exception {
	 * log.debug("AtendimentoInBoxRespostaController:atribuiAtdFilaPesqVO - Inicio do Metodo]");
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().setWFGrupoVO(WFGrupoVO.Factory.newInstance());
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().setUsuarioVIVO(UsuarioVIVO.Factory.newInstance());
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().setWFEstadoVO(WFEstadoVO.Factory.newInstance());
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().setWFSubEstadoVO(WFSubEstadoVO.Factory.newInstance());
	 * 
	 * if(this.atendimentoFilaForm.getAtdFilaPesqVO().getNrLinha() != null){ String nrLinha =
	 * StringUtilStatic.unmask(this.atendimentoFilaForm.getAtdFilaPesqVO().getNrLinha(), "()- "); if(nrLinha.length() <
	 * 10 && nrLinha.length() > 8) nrLinha = "0" + nrLinha;
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().setNrLinha(nrLinha);
	 * log.debug("AtendimentoInBoxRespostaController:atribuiAtdFilaPesqVO - [nrLinha = " +
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().getNrLinha() + "]"); }
	 * 
	 * if(!form.getGrupoSel().equals("-1")) {
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().getWFGrupoVO().setIdGrupo(form.getGrupoSel());
	 * log.debug("AtendimentoInBoxRespostaController:atribuiAtdFilaPesqVO - [idGrupo = " +
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().getWFGrupoVO().getIdGrupo() + "]"); }
	 * if(!form.getUsuarioSel().equals("-1")) {
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().getUsuarioVIVO().setIdPessoaUsuario(form.getUsuarioSel());
	 * log.debug("AtendimentoInBoxRespostaController:atribuiAtdFilaPesqVO - [idPessoaUsuario = "
	 * +this.atendimentoFilaForm.getAtdFilaPesqVO().getUsuarioVIVO().getIdPessoaUsuario() + "]"); }
	 * if(!form.getEstadoSel().equals("-1")) {
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().getWFEstadoVO().setIdEstado(form.getEstadoSel());
	 * log.debug("AtendimentoInBoxRespostaController:atribuiAtdFilaPesqVO - [idEstado = "
	 * +this.atendimentoFilaForm.getAtdFilaPesqVO().getWFEstadoVO().getIdEstado() + "]"); }
	 * if(!form.getSubEstadoSel().equals("-1")) {
	 * this.atendimentoFilaForm.getAtdFilaPesqVO().getWFSubEstadoVO().setIdSubEstado(form.getSubEstadoSel());
	 * log.debug("AtendimentoInBoxRespostaController:atribuiAtdFilaPesqVO - [idSubEstado = "
	 * +this.atendimentoFilaForm.getAtdFilaPesqVO().getWFSubEstadoVO().getIdSubEstado() + "]"); }
	 * log.debug("AtendimentoInBoxRespostaController:atribuiAtdFilaPesqVO - Fim do Metodo]"); }
	 */

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RWFIndisponivel.jsp"
	 */
	protected ActionForward analistaDisponivel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoInBoxRespostaController:analistaDisponivel.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:analistaDisponivel.do - [user = " + user + "]");
		String indisponivel = request.getParameter("disp");
		log.debug("AtendimentoInBoxRespostaController:analistaDisponivel.do - [indisponivel = " + indisponivel + "]");
		if (ConstantesCRM.SONE.equals(indisponivel)) {
			rAtendimentoFacade.analistaDisponivelRC2(user, indisponivel);
		} else {
			rAtendimentoFacade.analistaDisponivelRC(user, indisponivel);
		}
		log.debug("AtendimentoInBoxRespostaController:analistaDisponivel.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="iAlerta.jsp" name="success"
	 */
	protected ActionForward alerta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoInBoxRespostaController:alerta.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:alerta.do - [user = " + user + "]");
		String idAtendimento = request.getParameter("idAtendimento");
		log.debug("AtendimentoInBoxRespostaController:alerta.do - [idAtendimento = " + idAtendimento + "]");

		if (idAtendimento != null) {
			AlertaVO[] vAlertas = atendimentoFacade.obtemAlertaVO(user, idAtendimento, ConstantesCRM.SONE);

			this.atendimentoInBoxCRIForm.setAlertasVO(vAlertas);

		}
		log.debug("AtendimentoInBoxRespostaController:alerta.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="popupPesquisaCliente.jsp"
	 */
	protected ActionForward irPesquisaNome(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxRespostaController:irPesquisaNome.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:irPesquisaNome.do - [user = " + user + "]");
		grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().setSgTipoPesquisa(request.getParameter("tipoPesquisa"));
		log.debug("AtendimentoInBoxRespostaController:irPesquisaNome.do - [sgTipoPesquisa = " + grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa() + "]");
		log.debug("AtendimentoInBoxRespostaController:irPesquisaNome.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="clientes" path="listaClientes.jsp"
	 */
	protected ActionForward pesquisarCRI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GrupoCRIForm form = (GrupoCRIForm) formParam;

		log.debug("AtendimentoInBoxRespostaController:pesquisarCRI.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:pesquisarCRI.do - [user = " + user + "]");
		String destino = ConstantesCRM.SVAZIO;
		GrupoCRIVO pesquisa = GrupoCRIVO.Factory.newInstance();
		if (form.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa() != null) {
			grupoCRIForm.getGrupoCRI().setPesquisa(form.getGrupoCRI().getPesquisa());
			log.debug("AtendimentoInBoxRespostaController:pesquisarCRI.do - [pesquisa = " + grupoCRIForm.getGrupoCRI().getPesquisa() + "]");
		}
		// pesquisa = relacionarGestorFacade.getPesquisaGruposCRI(user, grupoCRIForm.getGrupoCRI());
		pesquisa = rAtendimentoFacade.getPesquisaGruposCRI(user, grupoCRIForm.getGrupoCRI());
		if (grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa().equalsIgnoreCase("CELULAR") || grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa().equalsIgnoreCase("CONTA") || grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa().equalsIgnoreCase("CLIENTE")) {
			grupoCRIForm.getGrupoCRI().setLinhasArray(pesquisa.getLinhasArray());
			destino = "linhas";
		} else {
			if (pesquisa.getPesquisa().getInErro() != null) {
				pesquisa.getPesquisa().setInErro(null);
				grupoCRIForm.getGrupoCRI().getPesquisa().setInErro(ConstantesCRM.SONE);
				GrupoCRIVO novo = GrupoCRIVO.Factory.newInstance();
				novo.addNewPesquisa();
				pesquisa.getPesquisa().setPessoasArray(novo.getPesquisa().getPessoasArray());
			}
			grupoCRIForm.getGrupoCRI().getPesquisa().setPessoasArray(pesquisa.getPesquisa().getPessoasArray());
			destino = "clientes";
		}
		log.debug("AtendimentoInBoxRespostaController:pesquisarCRI.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="voltaWorkflow.jsp"
	 */
	protected ActionForward AtendimentoWorkflowDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoInBoxRespostaController:pesquisarCRI.do - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Obtiene el arvore de contatos.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="iArvoreContato.jsp"
	 */
	protected ActionForward obterArvoreContato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxRespostaController:obterArvoreContato.do - Inicio do Metodo]");
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

		request.setAttribute("scriptArvore", sbScript.toString());
		log.debug("AtendimentoInBoxRespostaController:obterArvoreContato.do - [Script da Arvore de Contato = " + sbScript.toString() + "]");
		log.debug("AtendimentoInBoxRespostaController:obterArvoreContato.do - Fim do Metodo]");
		// seta nulo no objeto

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Expande el arvore de contatos.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="iExpandeArvoreContatoInBox.jsp"
	 */
	protected ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExpandeArvoreContatoForm form = (ExpandeArvoreContatoForm) formParam;

		log.debug("AtendimentoInBoxRespostaController:expandeArvoreContato.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxRespostaController:expandeArvoreContato.do - [user = " + user + "]");
		StringBuffer sbScript = new StringBuffer(1024);
		StringBuffer sbNode = new StringBuffer(1024);

		AtendimentoArvoreFiltrosVO atFiltros = AtendimentoArvoreFiltrosVO.Factory.newInstance();
		atFiltros.setIdContato(form.getIdContato());
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

		request.setAttribute("scriptArvore", sbScript.toString());
		log.debug("AtendimentoInBoxRespostaController:expandeArvoreContato.do - [Script da Arvore de Contato = " + sbScript.toString() + "]");

		log.debug("AtendimentoInBoxRespostaController:expandeArvoreContato.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward detalheNota(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AtendimentoInBoxRespostaController:detalheNota.do - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward AtendimentoDetalheDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoInBoxRespostaController:AtendimentoDetalheDone.do - Metodo]");
		return null;
	}

	public static class AtendimentoInBoxCRIForm extends ActionForm {

		private static final long serialVersionUID = 660987365314433427L;

		private String adq_valorPesquisa;
		private String adq_tipoPesquisa;
		private String grupoSel;

		public AtendimentoInBoxCRIForm() {
		}

		private AlertaVO[] alertasVO;

		public void setAlertasVO(AlertaVO[] alertasVO) {
			this.alertasVO = alertasVO;
		}

		public AlertaVO[] getAlertasVO() {
			if (this.alertasVO == null || this.alertasVO.length == 0) {
				this.alertasVO[0] = AlertaVO.Factory.newInstance();
			}

			return this.alertasVO;
		}

		private String acaoSel = ConstantesCRM.SVAZIO;

		public String getAcaoSel() {
			return this.acaoSel;
		}

		public void setAcaoSel(String acaoSel) {
			this.acaoSel = acaoSel;
		}

		private String motivoSel = ConstantesCRM.SVAZIO;

		public String getMotivoSel() {
			return this.motivoSel;
		}

		public void setMotivoSel(String motivoSel) {
			this.motivoSel = motivoSel;
		}

		private WFMotivoVO[] wFMotivosVO;

		public void setMotivosVO(WFMotivoVO[] wFMotivosVO) {
			this.wFMotivosVO = wFMotivosVO;
		}

		public WFMotivoVO[] getMotivosVO() {
			if (this.wFMotivosVO == null || this.wFMotivosVO.length == 0) {
				this.wFMotivosVO[0] = WFMotivoVO.Factory.newInstance();
			}

			return this.wFMotivosVO;
		}

		private WFAcaoVO[] wFAcaoVO;

		public void setAcoesVO(WFAcaoVO[] wFAcaoVO) {
			this.wFAcaoVO = wFAcaoVO;
		}

		public WFAcaoVO[] getAcoesVO() {
			if (this.wFAcaoVO == null || this.wFAcaoVO.length == 0) {
				this.wFAcaoVO[0] = WFAcaoVO.Factory.newInstance();
			}

			return this.wFAcaoVO;
		}

		private WFAtdNotasVO wFAtdNotasVO;

		public void setNotasVO(WFAtdNotasVO wFAtdNotasVO) {
			this.wFAtdNotasVO = wFAtdNotasVO;
		}

		public WFAtdNotasVO getNotasVO() {
			if (this.wFAtdNotasVO == null) {
				this.wFAtdNotasVO = WFAtdNotasVO.Factory.newInstance();
			}
			return this.wFAtdNotasVO;
		}

		private RWFAtendimentosVO rwfAtendimentosVO;

		public void setRwfAtendimentosVO(RWFAtendimentosVO rwfAtendimentosVO) {
			this.rwfAtendimentosVO = rwfAtendimentosVO;
		}

		public RWFAtendimentosVO getRwfAtendimentosVO() {
			if (this.rwfAtendimentosVO == null) {
				this.rwfAtendimentosVO = RWFAtendimentosVO.Factory.newInstance();
			}
			return this.rwfAtendimentosVO;
		}

		private int nrRegistros = 0;

		public void setNrRegistros(int nrRegistros) {
			this.nrRegistros = nrRegistros;
		}

		public int getNrRegistros() {
			return this.nrRegistros;
		}

		private int totalRegistros = 0;

		public void setTotalRegistros(int totalRegistros) {
			this.totalRegistros = totalRegistros;
		}

		public int getTotalRegistros() {
			return this.totalRegistros;
		}

		private int atualizacao = 0;

		public void setAtualizacao(int atualizacao) {
			this.atualizacao = atualizacao;
		}

		public int getAtualizacao() {
			return this.atualizacao;
		}

		private String valorPesquisa = ConstantesCRM.SVAZIO;

		public void setValorPesquisa(String valorPesquisa) {
			this.valorPesquisa = valorPesquisa;
		}

		public String getValorPesquisa() {
			return this.valorPesquisa;
		}

		private String tipoPesquisa = ConstantesCRM.SVAZIO;

		public void setTipoPesquisa(String tipoPesquisa) {
			this.tipoPesquisa = tipoPesquisa;
		}

		public String getTipoPesquisa() {
			return this.tipoPesquisa;
		}

		private String inDisponivelWF = ConstantesCRM.SVAZIO;

		public void setInDisponivelWF(String inDisponivelWF) {
			this.inDisponivelWF = inDisponivelWF;
		}

		public String getInDisponivelWF() {
			return this.inDisponivelWF;
		}

		private String documento = ConstantesCRM.SVAZIO;

		public void setDocumento(String documento) {
			this.documento = documento;
		}

		public String getDocumento() {
			return this.documento;
		}

		private String inAbaPesquisa = ConstantesCRM.SVAZIO;

		public void setInAbaPesquisa(String inAbaPesquisa) {
			this.inAbaPesquisa = inAbaPesquisa;
		}

		public String getInAbaPesquisa() {
			return this.inAbaPesquisa;
		}

		private String nmLoginUsuario = ConstantesCRM.SVAZIO;

		public void setNmLoginUsuario(String nmLoginUsuario) {
			this.nmLoginUsuario = nmLoginUsuario;
		}

		public String getNmLoginUsuario() {
			return this.nmLoginUsuario;
		}

		private String prazoSel = ConstantesCRM.SVAZIO;

		public void setPrazoSel(String prazoSel) {
			this.prazoSel = prazoSel;
		}

		public String getPrazoSel() {
			return this.prazoSel;
		}

		private String estadoSel = ConstantesCRM.SVAZIO;

		public void setEstadoSel(String estadoSel) {
			this.estadoSel = estadoSel;
		}

		public String getEstadoSel() {
			return this.estadoSel;
		}

		private String subEstadoSel = ConstantesCRM.SVAZIO;

		public void setSubEstadoSel(String subEstadoSel) {
			this.subEstadoSel = subEstadoSel;
		}

		public String getSubEstadoSel() {
			return this.subEstadoSel;
		}

		private String idContato = ConstantesCRM.SVAZIO;

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		private String textoContato = ConstantesCRM.SVAZIO;

		public void setTextoContato(String textoContato) {
			this.textoContato = textoContato;
		}

		public String getTextoContato() {
			return this.textoContato;
		}

		private String dtFechamentoFim = ConstantesCRM.SVAZIO;

		public void setDtFechamentoFim(String dtFechamentoFim) {
			this.dtFechamentoFim = dtFechamentoFim;
		}

		public String getDtFechamentoFim() {
			return this.dtFechamentoFim;
		}

		private String dtFechamentoInicio = ConstantesCRM.SVAZIO;

		public void setDtFechamentoInicio(String dtFechamentoInicio) {
			this.dtFechamentoInicio = dtFechamentoInicio;
		}

		public String getDtFechamentoInicio() {
			return this.dtFechamentoInicio;
		}

		private String dtAberturaFim = ConstantesCRM.SVAZIO;

		public void setDtAberturaFim(String dtAberturaFim) {
			this.dtAberturaFim = dtAberturaFim;
		}

		public String getDtAberturaFim() {
			return this.dtAberturaFim;
		}

		private String dataFim = ConstantesCRM.SVAZIO;

		public void setDataFim(String dataFim) {
			this.dataFim = dataFim;
		}

		public String getDataFim() {
			return this.dataFim;
		}

		private String dataIni = ConstantesCRM.SVAZIO;

		public void setDataIni(String dataIni) {
			this.dataIni = dataIni;
		}

		public String getDataIni() {
			return this.dataIni;
		}

		private String dtAberturaInicio = ConstantesCRM.SVAZIO;

		public void setDtAberturaInicio(String dtAberturaInicio) {
			this.dtAberturaInicio = dtAberturaInicio;
		}

		public String getDtAberturaInicio() {
			return this.dtAberturaInicio;
		}

		private String nrLinha = ConstantesCRM.SVAZIO;

		public void setNrLinha(String nrLinha) {
			this.nrLinha = nrLinha;
		}

		public String getNrLinha() {
			return this.nrLinha;
		}

		private String idAtendimento = ConstantesCRM.SVAZIO;

		public void setIdAtendimento(String idAtendimento) {
			this.idAtendimento = idAtendimento;
		}

		public String getIdAtendimento() {
			return this.idAtendimento;
		}

		private int optCliTra = 0;

		public int getOptCliTra() {
			return this.optCliTra;
		}

		public void setOptCliTra(int optCliTra) {
			this.optCliTra = optCliTra;
		}

		private int optCliCon = 0;

		public int getOptCliCon() {
			return this.optCliCon;
		}

		public void setOptCliCon(int optCliCon) {
			this.optCliCon = optCliCon;
		}

		private String optGrpSel = ConstantesCRM.SZERO;

		public String getOptGrpSel() {
			return this.optGrpSel;
		}

		public void setOptGrpSel(String optGrpSel) {
			this.optGrpSel = optGrpSel;
		}

		private String optDocSel = "CPF";

		public String getOptDocSel() {
			return this.optDocSel;
		}

		public void setOptDocSel(String optDocSel) {
			this.optDocSel = optDocSel;
		}

		private String retorno = ConstantesCRM.SVAZIO;

		public void setRetorno(String retorno) {
			this.retorno = retorno;
		}

		public String getRetorno() {
			return this.retorno;
		}

		private WFGrupoVO[] gruposVO;

		public void setGruposVO(WFGrupoVO[] gruposVO) {
			this.gruposVO = gruposVO;
		}

		public WFGrupoVO[] getGruposVO() {
			return this.gruposVO;
		}

		private WFEstadosVO estadosVO;

		public void setEstadosVO(WFEstadosVO estadosVO) {
			this.estadosVO = estadosVO;
		}

		public WFEstadosVO getEstadosVO() {
			return this.estadosVO;
		}

		private RetornoWFCTIVO[] retornoWFCTIVO;

		public void setRetornoWFCTIVO(RetornoWFCTIVO[] retorno) {
			this.retornoWFCTIVO = retorno;
		}

		public RetornoWFCTIVO[] getRetornoWFCTIVO() {
			return this.retornoWFCTIVO;
		}

		public void setGrupoSel(String grupoSel) {
			this.grupoSel = grupoSel;
		}

		public String getGrupoSel() {
			return this.grupoSel;
		}

		public void setAdq_tipoPesquisa(String adq_tipoPesquisa) {
			this.adq_tipoPesquisa = adq_tipoPesquisa;
		}

		public String getAdq_tipoPesquisa() {
			return this.adq_tipoPesquisa;
		}

		public void setAdq_valorPesquisa(String adq_valorPesquisa) {
			this.adq_valorPesquisa = adq_valorPesquisa;
		}

		public String getAdq_valorPesquisa() {
			return this.adq_valorPesquisa;
		}
	}

	public AtendimentoInBoxCRIForm getAtendimentoInBoxCRIForm() {
		return this.atendimentoInBoxCRIForm;
	}
}
