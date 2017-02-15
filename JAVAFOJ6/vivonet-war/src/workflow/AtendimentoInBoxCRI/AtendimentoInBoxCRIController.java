package workflow.AtendimentoInBoxCRI;

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
import br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;
import cliente.AssociarGestor.AssociarCRController.GrupoCRIForm;
import com.indracompany.actions.AbstractAction;

public class AtendimentoInBoxCRIController extends AbstractAction {

	private static final long serialVersionUID = 3671261076170578383L;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.RAtendimento.RAtendimento rAtendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade atendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	private static Logger log = new Logger("workinboxcri");
	private static final String RAIZ_PRINCIPAL = "PAI";

	protected global.Global globalApp = new global.Global();

	private AtendimentoInBoxCRIForm atendimentoInBoxCRIForm = null;
	private GrupoCRIForm grupoCRIForm = new GrupoCRIForm();

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
		} else if ("pesquisarProcessos".equals(mapping.getParameter())) {
			return pesquisarProcessos(mapping, form, request, response);
		} else if ("notaUra".equals(mapping.getParameter())) {
			return notaUra(mapping, form, request, response);
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
	 * @jpf:forward name="success" path="WorkflowInBoxCRI.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxCRIController:begin.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:begin.do - [user = " + user + "]");

		atendimentoInBoxCRIForm = new AtendimentoInBoxCRIForm();

		// traz alertas
		AlertaVO[] vAlertas = atendimentoFacade.obtemPrazosCRI(user);
		if (vAlertas == null) {
			vAlertas = new AlertaVO[0];
		}
		this.atendimentoInBoxCRIForm.setAlertasVO(vAlertas);

		WFMotivoVO[] motivos = rAtendimentoFacade.getWFMotivosVO(user, "<idTabelaMotivo>50</idTabelaMotivo>").getWFAtdNotaVOArray(0).getWFMotivoVOArray();
		if (motivos == null) {
			motivos = new WFMotivoVO[0];
		}
		this.atendimentoInBoxCRIForm.setMotivosVO(motivos);
		this.atendimentoInBoxCRIForm.setAcoesVO(rAtendimentoFacade.getOperacaoNota(user).getWFAtdNotaVOArray(0).getWFAcaoVOArray());
		this.atendimentoInBoxCRIForm.setEstadosVO(atendimentoFacade.getWFEstadosVO(user, false));

		// seta analista disponivel
		if (request.getParameter("voltar") != null && request.getParameter("voltar").equals(ConstantesCRM.SONE)) {
			request.setAttribute("voltar", ConstantesCRM.SONE);
		} else {
			rAtendimentoFacade.analistaDisponivel(user, ConstantesCRM.SZERO, 1);
		}

		// traz dados do usuário
		RWFInboxUserVO userVO = rAtendimentoFacade.getUsuarioCampanha(user);
		this.atendimentoInBoxCRIForm.setNmLoginUsuario(userVO.getNmLoginUsuario());
		log.debug("AtendimentoInBoxCRIController:begin.do - [nmLoginUsuario = " + this.atendimentoInBoxCRIForm.getNmLoginUsuario() + "]");
		this.atendimentoInBoxCRIForm.setInDisponivelWF(userVO.getInDisponivelWF());
		log.debug("AtendimentoInBoxCRIController:begin.do - [inDisponivelWF = " + this.atendimentoInBoxCRIForm.getInDisponivelWF() + "]");
		this.atendimentoInBoxCRIForm.setRetornoWFCTIVO(userVO.getRetornoWFCTIVOArray());
		log.debug("AtendimentoInBoxCRIController:begin.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private RWFInBoxPesquisaVO construyePesquisaVO(AtendimentoInBoxCRIForm form, HttpServletRequest request) {

		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - Inicio do Metodo]");
		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();
		rwfInBoxPesquisaVO.setTabPausa(form.getInAbaPesquisa());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [tabPausa = " + form.getInAbaPesquisa() + "]");
		rwfInBoxPesquisaVO.setInAbaPesquisa(form.getInAbaPesquisa());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [inAbaPesquisa = " + form.getInAbaPesquisa() + "]");
		rwfInBoxPesquisaVO.setDtAberturaFim(form.getDtAberturaFim());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [dtAberturaFim = " + form.getDtAberturaFim() + "]");
		rwfInBoxPesquisaVO.setDtAberturaInicio(form.getDtAberturaInicio());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [dtAberturaInicio = " + form.getDtAberturaInicio() + "]");
		if (form.getDtFechamentoFim() != null && form.getDtFechamentoFim().length() != 0) {
			rwfInBoxPesquisaVO.setDtFechamentoFim(form.getDtFechamentoFim());
			log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [dtFechamentoFim = " + form.getDtFechamentoFim() + "]");
		}
		if (form.getDtFechamentoInicio() != null && form.getDtFechamentoInicio().length() != 0) {
			rwfInBoxPesquisaVO.setDtFechamentoInicio(form.getDtFechamentoInicio());
			log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [dtFechamentoInicio = " + form.getDtFechamentoInicio() + "]");
		}
		rwfInBoxPesquisaVO.setIdAtendimento(form.getIdAtendimento());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		rwfInBoxPesquisaVO.setIdContato(form.getIdContato());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [idContato = " + form.getIdContato() + "]");
		rwfInBoxPesquisaVO.setIdEstado(form.getEstadoSel());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [idEstado = " + form.getEstadoSel() + "]");
		rwfInBoxPesquisaVO.setIdSubEstado(form.getSubEstadoSel());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [idSubEstado = " + form.getSubEstadoSel() + "]");
		if (form.getNrLinha() != null) {
			rwfInBoxPesquisaVO.setNrLinha(form.getNrLinha().replaceAll("[\\(\\)-]*", ""));
		} else {
			rwfInBoxPesquisaVO.setNrLinha(null);
		}
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [nrLinha = " + rwfInBoxPesquisaVO.getNrLinha() + "]");

		if (!("".equals(form.getDocumento()))) {
			String strOptDocSel = "" + form.getOptDocSel();
			if ("CPF".equals(strOptDocSel)) {
				rwfInBoxPesquisaVO.setTipoDocumento("CPF");
				log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [tipoDocumento = " + rwfInBoxPesquisaVO.getTipoDocumento() + "]");
			} else if ("CNPJ".equals(strOptDocSel)) {
				rwfInBoxPesquisaVO.setTipoDocumento("CNPJ");
				log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [tipoDocumento = " + rwfInBoxPesquisaVO.getTipoDocumento() + "]");
			}

			rwfInBoxPesquisaVO.setDocumento(form.getDocumento().replaceAll("[\\.\\-\\/]*", ""));
		}

		if (!("".equals(form.getTipoPesquisa()))) {
			rwfInBoxPesquisaVO.setInTipoPesquisa(form.getTipoPesquisa());
			log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [inTipoPesquisa = " + rwfInBoxPesquisaVO.getInTipoPesquisa() + "]");
			rwfInBoxPesquisaVO.setDocumento(form.getValorPesquisa().replaceAll("[\\(\\)\\.\\-\\/]*", ""));
			log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [documento = " + rwfInBoxPesquisaVO.getDocumento() + "]");
			if (form.getInAbaPesquisa().equals(ConstantesCRM.SSIX)) {
				String strTipoPesquisa = "" + form.getTipoPesquisa();
				if (strTipoPesquisa.equals("CPF") || strTipoPesquisa.equals("CNPJ")) {
					rwfInBoxPesquisaVO.setInTipoPesquisa(ConstantesCRM.SFOUR);
					rwfInBoxPesquisaVO.setTipoDocumento(strTipoPesquisa);
				} else if (strTipoPesquisa.equals("NOME") || strTipoPesquisa.equals("RAZAO")) {
					rwfInBoxPesquisaVO.setInTipoPesquisa(ConstantesCRM.STHREE);
					rwfInBoxPesquisaVO.setDocumento(request.getParameter("idPessoa"));
				}
			}
		}

		rwfInBoxPesquisaVO.setIdAlerta(form.getPrazoSel());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [idAlerta = " + form.getPrazoSel() + "]");
		rwfInBoxPesquisaVO.setTextoContato(form.getTextoContato());
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - [textoContato = " + form.getTextoContato() + "]");
		log.debug("AtendimentoInBoxCRIController:construyePesquisaVO.do - Fim do Metodo]");
		return rwfInBoxPesquisaVO;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iMeusProcessos.jsp"
	 */
	protected ActionForward pesquisarProcessos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoInBoxCRIForm form = (AtendimentoInBoxCRIForm) formParam;

		log.debug("AtendimentoInBoxCRIController:pesquisarProcessos.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessos.do - [user = " + user + "]");
		// Obtém a lista de ramais para o callcenter
		if (form.getInDisponivelWF().equals(ConstantesCRM.SONE)) {
			rAtendimentoFacade.obtemProximoProcesso(user, "<inCRI>1</inCRI>");
		}

		// Inicializacion de Atualizacao en el formulario de retorno
		this.atendimentoInBoxCRIForm.setAtualizacao(5);
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessos.do - [atualizacao = " + this.atendimentoInBoxCRIForm.getAtualizacao() + "]");

		RWFInBoxPesquisaVO rwfInBoxPesquisaVO;
		if (form.getInCliqueAba().equals(ConstantesCRM.SVAZIO) || form.getInCliqueAba().equals(ConstantesCRM.SONE)) {
			rwfInBoxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();
			rwfInBoxPesquisaVO.setTabPausa(form.getInAbaPesquisa());
			log.debug("AtendimentoInBoxCRIController:pesquisarProcessos.do - [tabPausa = " + form.getInAbaPesquisa() + "]");
			rwfInBoxPesquisaVO.setInAbaPesquisa(form.getInAbaPesquisa());
			log.debug("AtendimentoInBoxCRIController:pesquisarProcessos.do - [inAbaPesquisa = " + form.getInAbaPesquisa() + "]");
		} else {
			rwfInBoxPesquisaVO = this.construyePesquisaVO(form, request);
		}

		RWFAtendimentosVO atds = rAtendimentoFacade.getInboxCRI(user, rwfInBoxPesquisaVO);
		this.atendimentoInBoxCRIForm.setNrRegistros(atds.getNrRegistros());
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessos.do - [nrRegistros = " + this.atendimentoInBoxCRIForm.getNrRegistros() + "]");
		this.atendimentoInBoxCRIForm.setTotalRegistros(atds.getTotalRegistros());
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessos.do - [totalRegistros = " + this.atendimentoInBoxCRIForm.getTotalRegistros() + "]");
		this.atendimentoInBoxCRIForm.setRwfAtendimentosVO(atds);
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessos.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iNotasUra.jsp"
	 */
	protected ActionForward notaUra(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoInBoxCRIForm form = (AtendimentoInBoxCRIForm) formParam;

		log.debug("AtendimentoInBoxCRIController:notaURA.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [user = " + user + "]");
		// Inicializacion de Atualizacao en el formulario de retorno
		this.atendimentoInBoxCRIForm.setAtualizacao(1);

		WFAtdNotaVO wfPsqNota = WFAtdNotaVO.Factory.newInstance();
		wfPsqNota.setCliContatado(form.getOptCliCon());
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [cliContatado = " + wfPsqNota.getCliContatado() + "]");
		wfPsqNota.setCliTransferido(form.getOptCliTra());
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [cliTransferido = " + wfPsqNota.getCliTransferido() + "]");
		wfPsqNota.setDtAberturaFim(form.getDataFim());
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [dtAberturaFim = " + wfPsqNota.getDtAberturaFim() + "]");
		wfPsqNota.setDtAberturaIni(form.getDataIni());
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [dtAberturaIni = " + wfPsqNota.getDtAberturaIni() + "]");
		wfPsqNota.setIdMotivo(form.getMotivoSel());
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [idMotivo = " + wfPsqNota.getIdMotivo() + "]");
		wfPsqNota.setIdOperacao(form.getAcaoSel());
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [idOperacao = " + wfPsqNota.getIdOperacao() + "]");
		wfPsqNota.setFlgInbox("1");
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [flgInBox = " + wfPsqNota.getFlgInbox() + "]");
		wfPsqNota.setIdUsuario(user);
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [idUsuario = " + wfPsqNota.getIdUsuario() + "]");
		WFAtdNotasVO wFAtdNotasVO = rAtendimentoFacade.getWFAtdNotasVO(user, wfPsqNota);

		this.atendimentoInBoxCRIForm.setNrRegistros(wFAtdNotasVO.getNrRegistros());
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [nrRegistros = " + this.atendimentoInBoxCRIForm.getNrRegistros() + "]");
		this.atendimentoInBoxCRIForm.setTotalRegistros(wFAtdNotasVO.getTotalRegistros());
		log.debug("AtendimentoInBoxCRIController:notaURA.do - [totalRegistros = " + this.atendimentoInBoxCRIForm.getTotalRegistros() + "]");
		this.atendimentoInBoxCRIForm.setNotasVO(wFAtdNotasVO);
		log.debug("AtendimentoInBoxCRIController:notaURA.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iMensagens.jsp"
	 */
	protected ActionForward pesquisarMensagens(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoInBoxCRIForm form = (AtendimentoInBoxCRIForm) formParam;

		log.debug("AtendimentoInBoxCRIController:pesquisarMensagens.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:pesquisarMensagens.do - [user = " + user + "]");

		// Inicializacion de Atualizacao en el formulario de retorno
		this.atendimentoInBoxCRIForm.setAtualizacao(5);
		log.debug("AtendimentoInBoxCRIController:pesquisarMensagens.do - [atualizacao = " + this.atendimentoInBoxCRIForm.getAtualizacao() + "]");

		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = this.construyePesquisaVO(form, request);
		RWFAtendimentosVO atds = rAtendimentoFacade.getMsgInboxCRI(user, rwfInBoxPesquisaVO);

		this.atendimentoInBoxCRIForm.setNrRegistros(atds.getNrRegistros());
		log.debug("AtendimentoInBoxCRIController:pesquisarMensagens.do - [nrRegistros = " + this.atendimentoInBoxCRIForm.getNrRegistros() + "]");
		this.atendimentoInBoxCRIForm.setTotalRegistros(atds.getTotalRegistros());
		log.debug("AtendimentoInBoxCRIController:pesquisarMensagens.do - [totalRegistros = " + this.atendimentoInBoxCRIForm.getTotalRegistros() + "]");
		this.atendimentoInBoxCRIForm.setRwfAtendimentosVO(atds);
		log.debug("AtendimentoInBoxCRIController:pesquisarMensagens.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iMeusProcessos.jsp"
	 */
	protected ActionForward pesquisarProcessosAdq(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoInBoxCRIForm form = (AtendimentoInBoxCRIForm) formParam;

		log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - [user = " + user + "]");
		// Inicializacion de Atualizacao en el formulario de retorno
		this.atendimentoInBoxCRIForm.setAtualizacao(5);
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - [atualizacao = " + this.atendimentoInBoxCRIForm.getAtualizacao() + "]");
		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();

		if ("1".equals(request.getParameter("inPesquisa"))) {
			rwfInBoxPesquisaVO.setTabPausa(form.getInAbaPesquisa());
			log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - [tabPausa = " + rwfInBoxPesquisaVO.getTabPausa() + "]");
			rwfInBoxPesquisaVO.setInAbaPesquisa(form.getInAbaPesquisa());
			log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - [inAbaPesquisa = " + rwfInBoxPesquisaVO.getInAbaPesquisa() + "]");
			if (form.getTipoPesquisa().equals("NOME") || form.getTipoPesquisa().equals("RAZAO")) {
				rwfInBoxPesquisaVO.setInTipoPesquisa("3");
				rwfInBoxPesquisaVO.setDocumento(request.getParameter("idPessoa"));
			} else if (form.getTipoPesquisa().equals("CPF") || form.getTipoPesquisa().equals("CNPJ")) {
				rwfInBoxPesquisaVO.setInTipoPesquisa("4");
				rwfInBoxPesquisaVO.setTipoDocumento(form.getTipoPesquisa());
				if (form.getTipoPesquisa().equals("CPF")) {
					rwfInBoxPesquisaVO.setDocumento(form.getValorPesquisa().replaceAll("[\\.\\.\\-\\/]*", ""));
				} else {
					rwfInBoxPesquisaVO.setDocumento(form.getValorPesquisa().replaceAll("[\\.\\.\\/\\-\\/]*", ""));
				}
			} else {
				rwfInBoxPesquisaVO.setInTipoPesquisa(form.getTipoPesquisa());
				rwfInBoxPesquisaVO.setDocumento(form.getValorPesquisa().replaceAll("[\\(\\)\\.\\-\\/]*", ""));
			}
			log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - [inTipoPesquisa = " + rwfInBoxPesquisaVO.getInTipoPesquisa() + "]");
			log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - [documento = " + rwfInBoxPesquisaVO.getDocumento() + "]");
		}

		RWFAtendimentosVO atds = rAtendimentoFacade.getInboxCRI(user, rwfInBoxPesquisaVO);

		this.atendimentoInBoxCRIForm.setNrRegistros(atds.getNrRegistros());
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - [nrRegistros = " + this.atendimentoInBoxCRIForm.getNrRegistros() + "]");
		this.atendimentoInBoxCRIForm.setTotalRegistros(atds.getTotalRegistros());
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - [totalRegistros = " + this.atendimentoInBoxCRIForm.getTotalRegistros() + "]");
		this.atendimentoInBoxCRIForm.setRwfAtendimentosVO(atds);
		log.debug("AtendimentoInBoxCRIController:pesquisarProcessosAdq.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RWFIndisponivel.jsp"
	 */
	protected ActionForward analistaDisponivel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxCRIController:analistaDisponivel.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:analistaDisponivel.do - [user = " + user + "]");
		String indisponivel = request.getParameter("disp");
		log.debug("AtendimentoInBoxCRIController:analistaDisponivel.do - [indisponivel = " + indisponivel + "]");
		if ("1".equals(indisponivel)) {
			// T ODO: Se necesita un servicio al que pasandole el user y el indisponivel actualice el valor
			rAtendimentoFacade.analistaDisponivel2(user, indisponivel, 1);

		} else {
			// T ODO: Se necesita un servicio al que pasandole el user y el indisponivel actualice el valor
			rAtendimentoFacade.analistaDisponivel(user, indisponivel, 1);

		}

		log.debug("AtendimentoInBoxCRIController:analistaDisponivel.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="iAlerta.jsp" name="success"
	 */
	protected ActionForward alerta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoInBoxCRIController:alerta.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:alerta.do - [user = " + user + "]");
		String idAtendimento = request.getParameter("idAtendimento");
		log.debug("AtendimentoInBoxCRIController:alerta.do - [idAtendimento = " + user + "]");

		if (idAtendimento != null) {
			AlertaVO[] vAlertas = atendimentoFacade.obtemAlertaVO(user, idAtendimento, ConstantesCRM.SONE);
			this.atendimentoInBoxCRIForm.setAlertasVO(vAlertas);
		}

		log.debug("AtendimentoInBoxCRIController:alerta.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="popupPesquisaCliente.jsp"
	 */
	protected ActionForward irPesquisaNome(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxCRIController:irPesquisaNome.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:irPesquisaNome.do - [user = " + user + "]");
		grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().setSgTipoPesquisa(request.getParameter("tipoPesquisa"));
		log.debug("AtendimentoInBoxCRIController:irPesquisaNome.do - [sgTipoPesquisa = " + grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa() + "]");
		log.debug("AtendimentoInBoxCRIController:irPesquisaNome.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="clientes" path="listaClientes.jsp"
	 */
	protected ActionForward pesquisarCRI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GrupoCRIForm form = (GrupoCRIForm) formParam;

		log.debug("AtendimentoInBoxCRIController:pesquisarCRI.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:pesquisarCRI.do - [user = " + user + "]");
		String destino = ConstantesCRM.SVAZIO;
		GrupoCRIVO pesquisa = GrupoCRIVO.Factory.newInstance();
		if (form.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa() != null) {
			grupoCRIForm.getGrupoCRI().setPesquisa(form.getGrupoCRI().getPesquisa());
			log.debug("AtendimentoInBoxCRIController:pesquisarCRI.do - [pesquisa = " + grupoCRIForm.getGrupoCRI().getPesquisa() + "]");
		}
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
		log.debug("AtendimentoInBoxCRIController:pesquisarCRI.do - [destino = " + destino + "]");
		log.debug("AtendimentoInBoxCRIController:pesquisarCRI.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="voltaWorkflow.jsp"
	 */
	protected ActionForward AtendimentoWorkflowDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoInBoxCRIController:AtendimentoWorkflowDone.do - Metodo]");
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

		log.debug("AtendimentoInBoxCRIController:obterArvoreContato.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:obterArvoreContato.do - [user = " + user + "]");

		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, null);

		if (admContatoFolhaVO == null) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		StringBuffer sbScript = new StringBuffer("");
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
		log.debug("AtendimentoInBoxCRIController:obterArvoreContato.do - [Script da Arvore de Contato = " + sbScript.toString() + "]");
		// seta nulo no objeto
		log.debug("AtendimentoInBoxCRIController:obterArvoreContato.do - Fim do Metodo]");
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

		log.debug("AtendimentoInBoxCRIController:expandeArvoreContato.do - Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxCRIController:expandeArvoreContato.do - [user = " + user + "]");

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

			if (!admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals("1")) {
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
		log.debug("AtendimentoInBoxCRIController:expandeArvoreContato.do - [Script da Arvore de Contato = " + sbScript.toString() + "]");
		log.debug("AtendimentoInBoxCRIController:expandeArvoreContato.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward detalheNota(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoInBoxCRIController:detalheNota.do - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward AtendimentoDetalheDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoInBoxCRIController:AtendimentoDetalheDone.do - Metodo]");
		return null;
	}

	public static class AtendimentoInBoxCRIForm extends ActionForm {

		private static final long serialVersionUID = -4082818042041417535L;

		// inCliqueAba define se o usuario clicou na Aba ou no botao Pesquisar
		private String inCliqueAba;

		public AtendimentoInBoxCRIForm() {
		}

		private AlertaVO[] alertasVO;

		public void setAlertasVO(AlertaVO[] alertasVO) {
			this.alertasVO = alertasVO;
		}

		public AlertaVO[] getAlertasVO() {
			if (this.alertasVO == null || this.alertasVO.length == 0) {
				this.alertasVO = new AlertaVO[1];
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
			return this.wFMotivosVO;
		}

		private WFAcaoVO[] wFAcaoVO;

		public void setAcoesVO(WFAcaoVO[] wFAcaoVO) {
			this.wFAcaoVO = wFAcaoVO;
		}

		public WFAcaoVO[] getAcoesVO() {
			if (this.wFAcaoVO == null || this.wFAcaoVO.length == 0) {
				this.wFAcaoVO = new WFAcaoVO[1]; 
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

		private String nrProtocolo = ConstantesCRM.SVAZIO;

		public void setNrProtocolo(String nrProtocolo) {
			this.nrProtocolo = nrProtocolo;
		}

		public String getNrProtocolo() {
			return this.nrProtocolo;
		}

		private int optCliTra = -1;

		public int getOptCliTra() {
			return this.optCliTra;
		}

		public void setOptCliTra(int optCliTra) {
			this.optCliTra = optCliTra;
		}

		private int optCliCon = -1;

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

		public void setInCliqueAba(String inCliqueAba) {
			this.inCliqueAba = inCliqueAba;
		}

		public String getInCliqueAba() {
			if (this.inCliqueAba == null) {
				this.inCliqueAba = ConstantesCRM.SVAZIO;
			}
			return this.inCliqueAba;
		}
	}

	public AtendimentoInBoxCRIForm getAtendimentoInBoxCRIForm() {
		return this.atendimentoInBoxCRIForm;
	}
}
