package workflow.AtendimentoDetalhe;

import java.io.BufferedOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import workflow.AtendimentoFila.Portabilidade.formBeans.GestorGerenteContaForm;
import br.com.vivo.fo.admsistemas.vo.ArvoreAtendimentoVODocument.ArvoreAtendimentoVO;
import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.EncerramentoVODocument.EncerramentoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioProcessoVODocument.FormularioProcessoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.cliente.vo.EnderecoBaseVODocument.EnderecoBaseVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.TelaInicialVODocument.TelaInicialVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.workflow.RAtendimento.RAtendimento;
import br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoDetalheVODocument.AtendimentoDetalheVO;
import br.com.vivo.fo.workflow.vo.AtendimentoHistoricoVODocument.AtendimentoHistoricoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoTipoComunicacaoVODocument.AtendimentoTipoComunicacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO.Contas;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowAgendamentoVODocument.AtendimentoWorkflowAgendamentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowComumVODocument.AtendimentoWorkflowComumVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowEncerramentoVODocument.AtendimentoWorkflowEncerramentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowInsistenciaVODocument.AtendimentoWorkflowInsistenciaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowPesquisaVODocument.AtendimentoWorkflowPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoVODocument.AtendimentoWorkflowTecnicoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowVODocument.AtendimentoWorkflowVO;
import br.com.vivo.fo.workflow.vo.ContaVODocument.ContaVO;
import br.com.vivo.fo.workflow.vo.FaseVODocument.FaseVO;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;
import br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO;
import br.com.vivo.fo.workflow.vo.RDContatoVODocument.RDContatoVO;
import br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO;
import br.com.vivo.fo.workflow.vo.StringComumVODocument.StringComumVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFEstadoVODocument.WFEstadoVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;
import br.com.vivo.fo.workflow.vo.WFSubEstadoVODocument.WFSubEstadoVO;
import br.com.vivo.ws.gestorconta.GestorContaBindingStub;
import br.com.vivo.ws.gestorconta.GestorContaLocator;
import br.com.vivo.ws.gestorconta.GestorContaPortType;
import br.com.vivo.ws.gestorconta.GestorContaPortTypeProxy;
import br.com.vivo.ws.gestorconta.ParametrosBuscarListaGestores;
import br.com.vivo.ws.gestorconta.ParametrosBuscarListaGestoresCdTipoOperacao;
import br.com.vivo.ws.gestorconta.ParametrosBuscarListaGestoresCdTipoRelacionamento;
import br.com.vivo.ws.gestorconta.ParametrosManterGestorContaEmpresa;
import br.com.vivo.ws.gestorconta.ParametrosManterGestorContaEmpresaCdSistemaOrigem;
import br.com.vivo.ws.gestorconta.ParametrosManterGestorContaEmpresaCdTipoGestor;
import br.com.vivo.ws.gestorconta.ParametrosManterGestorContaEmpresaCdTipoOperacao;
import br.com.vivo.ws.gestorconta.bean.geral.ErroInfo;
import br.com.vivo.ws.gestorconta.bean.pessoa.Gestor;
import br.com.vivo.ws.gestorconta.bean.pessoa.Gestores;

import com.indracompany.actions.AbstractAction;

public class AtendimentoDetalheController extends AbstractAction {

	private static final long serialVersionUID = 9060706169567066780L;

	private static Logger log = new Logger("workflow");
	private AtendimentoDetalheForm atendimentoDetalheForm = new AtendimentoDetalheForm();
	private RWFAlertaForm rWFAlertaForm = new RWFAlertaForm();
	private RWFAcaoForm rWFAcaoForm = new RWFAcaoForm();
	private RWFNivelProcesoForm rWFNivelProcesoForm = new RWFNivelProcesoForm();
	private RWFLinhasAssocForm rWFLinhasAssocForm = new RWFLinhasAssocForm();
	private RWFPessoaForm rWFPessoaForm = new RWFPessoaForm();
	private RWFContatoForm rWFContatoForm = new RWFContatoForm();
	private RWFFormularioForm rWFFormularioForm = new RWFFormularioForm();
	private RWFHistoricoForm rWFHistoricoForm = new RWFHistoricoForm();
	private RWFDetalheForm rWFDetalheForm = new RWFDetalheForm();
	private RWFEncerramentoForm rWFEncerramentoForm = new RWFEncerramentoForm();
	private GestorGerenteContaForm gestorGerenteContaForm;

	public GestorGerenteContaForm getGestorGerenteContaForm() {
		if (this.gestorGerenteContaForm == null) {
			this.gestorGerenteContaForm = new GestorGerenteContaForm();
		}
		return this.gestorGerenteContaForm;
	}

	@EJB
	private TelaInicialFacade telaInicialFacade;

	@EJB
	private RAtendimento rAtendimentoFacade;

	@EJB
	private AtendimentoFacade atendimentoFacade;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);

		} else if ("begin2".equals(mapping.getParameter())) {
			return begin2(mapping, form, request, response);

		} else if ("begin3".equals(mapping.getParameter())) {
			return begin3(mapping, form, request, response);

		} else if ("RDAlerta".equals(mapping.getParameter())) {
			return RDAlerta(mapping, form, request, response);

		} else if ("RDAlertaEx".equals(mapping.getParameter())) {
			return RDAlertaEx(mapping, form, request, response);

		} else if ("RDHistorico".equals(mapping.getParameter())) {
			return RDHistorico(mapping, form, request, response);

		} else if ("RDHistoricoEx".equals(mapping.getParameter())) {
			return RDHistoricoEx(mapping, form, request, response);

		} else if ("RDComentarioHistorico".equals(mapping.getParameter())) {
			return RDComentarioHistorico(mapping, form, request, response);

		} else if ("abaVoltar".equals(mapping.getParameter())) {
			return abaVoltar(mapping, form, request, response);

		} else if ("abaVoltarSolicitante".equals(mapping.getParameter())) {
			return abaVoltarSolicitante(mapping, form, request, response);

		} else if ("abaVoltarSolicitanteEx".equals(mapping.getParameter())) {
			return abaVoltarSolicitanteEx(mapping, form, request, response);

		} else if ("RDArvoreAtendimento".equals(mapping.getParameter())) {
			return RDArvoreAtendimento(mapping, form, request, response);

		} else if ("RDArvoreAtendimentoEx".equals(mapping.getParameter())) {
			return RDArvoreAtendimentoEx(mapping, form, request, response);

		} else if ("RDNivelProcesso".equals(mapping.getParameter())) {
			return RDNivelProcesso(mapping, form, request, response);

		} else if ("RDNivelProcessoEx".equals(mapping.getParameter())) {
			return RDNivelProcessoEx(mapping, form, request, response);

		} else if ("RDNivelProcessoEx".equals(mapping.getParameter())) {
			return RDNivelProcessoEx(mapping, form, request, response);

		} else if ("RDLinhasAssoc".equals(mapping.getParameter())) {
			return RDLinhasAssoc(mapping, form, request, response);

		} else if ("RDLinhasAssocEx".equals(mapping.getParameter())) {
			return RDLinhasAssocEx(mapping, form, request, response);

		} else if ("RDSolicitante".equals(mapping.getParameter())) {
			return RDSolicitante(mapping, form, request, response);

		} else if ("RDSolicitanteEx".equals(mapping.getParameter())) {
			return RDSolicitanteEx(mapping, form, request, response);

		} else if ("RDUser".equals(mapping.getParameter())) {
			return RDUser(mapping, form, request, response);

		} else if ("RDUserEx".equals(mapping.getParameter())) {
			return RDUserEx(mapping, form, request, response);

		} else if ("RDProcesso".equals(mapping.getParameter())) {
			return RDProcesso(mapping, form, request, response);

		} else if ("RDProcessoEx".equals(mapping.getParameter())) {
			return RDProcessoEx(mapping, form, request, response);

		} else if ("RDContato".equals(mapping.getParameter())) {
			return RDContato(mapping, form, request, response);

		} else if ("RDContatoEx".equals(mapping.getParameter())) {
			return RDContatoEx(mapping, form, request, response);

		} else if ("RDEncerramento".equals(mapping.getParameter())) {
			return RDEncerramento(mapping, form, request, response);

		} else if ("RDOperacionAbaRelacionamento".equals(mapping.getParameter())) {
			return RDOperacionAbaRelacionamento(mapping, form, request, response);

		} else if ("RDOperacionAbaRelacionamentoEx".equals(mapping.getParameter())) {
			return RDOperacionAbaRelacionamentoEx(mapping, form, request, response);

		} else if ("getGestorGerenteConta".equals(mapping.getParameter())) {
			return getGestorGerenteConta(mapping, form, request, response);

		} else if ("verificaNumeroVivo".equals(mapping.getParameter())) {
			return verificaNumeroVivo(mapping, form, request, response);

		} else if ("setGestorGerenteConta".equals(mapping.getParameter())) {
			return setGestorGerenteConta(mapping, form, request, response);

		} else if ("RDOperacion".equals(mapping.getParameter())) {
			return RDOperacion(mapping, form, request, response);

		} else if ("LoadEstados".equals(mapping.getParameter())) {
			return LoadEstados(mapping, form, request, response);

		} else if ("LoadEstadosEx".equals(mapping.getParameter())) {
			return LoadEstadosEx(mapping, form, request, response);

		} else if ("atendimentoDetalheVoltar".equals(mapping.getParameter())) {
			return atendimentoDetalheVoltar(mapping, form, request, response);

		} else if ("acaoWorkflow".equals(mapping.getParameter())) {
			return acaoWorkflow(mapping, form, request, response);

		} else if ("AtendimentoWorkflowDone".equals(mapping.getParameter())) {
			return AtendimentoWorkflowDone(mapping, form, request, response);

		} else if ("RegistrarContatoDone".equals(mapping.getParameter())) {
			return RegistrarContatoDone(mapping, form, request, response);

		} else if ("telaInicial".equals(mapping.getParameter())) {
			return telaInicial(mapping, form, request, response);

		} else {
			return begin(mapping, form, request, response);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDetalhe.jsp"
	 * @jpf:forward name="success_360" path="RDetalhe_360.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destino = ConstantesCRM.SVAZIO;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtendimento");
		String inCRI = ConstantesCRM.SVAZIO;
		String inRC = ConstantesCRM.SVAZIO;
		boolean meuCliente = ConstantesCRM.SONE.equals(request.getParameter("inMeuCliente")) ? true : false;
		String inSupervisor = ConstantesCRM.SONE.equals(request.getSession().getAttribute("inOrigemFila")) ? ConstantesCRM.SONE : ConstantesCRM.SZERO;

		log.debug("AtendimentoDetalheController:begin.do - [idAtendimento = " + idAtendimento + "]");

		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		String idGrupo = ConstantesCRM.SVAZIO;
		if (parametros != null) {
			idGrupo = parametros.getIdGrupo();
		}

		if (ConstantesCRM.STRUE.equals(request.getSession().getAttribute("ACESSO_EXTERNO"))) {
			destino = "success_360";
			WFAcaoVO[] wfAcao = rAtendimentoFacade.getWFAcaoVO(user, idAtendimento, idGrupo, inCRI, inRC, inSupervisor);
			rWFDetalheForm.setAcaoVO(wfAcao);
		} else {
			destino = ConstantesCRM.SUCCESS;
		}

		log.debug("AtendimentoDetalheController:begin.do - [Detalhe de Atendimento - Inicio do Metodo]");

		request.getSession().removeAttribute("inAdimplente");
		request.getSession().removeAttribute(ConstantesCRM.SACTION);
		request.getSession().removeAttribute("dsIntencao");
		request.getSession().removeAttribute("idRetencao");

		String inDisponivel = request.getParameter("inDisponivel");
		log.debug("AtendimentoDetalheController:begin.do - [inDisponivel = " + inDisponivel + "]");

		if (request.getSession().getAttribute("inRC") != null) {
			inRC = (String) request.getSession().getAttribute("inRC");
			log.debug("AtendimentoDetalheController:begin.do - [inRC = " + inRC + "]");
			request.getSession().removeAttribute("inRC");
		} else {
			inRC = (request.getParameter("inRC") != null ? request.getParameter("inRC") : ConstantesCRM.SZERO);
			if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
				inRC = ConstantesCRM.STWO;
			}
		}
		inRC = inRC.equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SZERO : inRC;
		if (inDisponivel != null &&  !ConstantesCRM.SVAZIO.equals(inDisponivel)) {
			request.getSession().setAttribute("inDisponivel", inDisponivel);
		}

		String fila = request.getParameter("fila");
		log.debug("AtendimentoDetalheController:begin.do - [fila = " + fila + "]");

		inCRI = (request.getParameter("inCRI") != null ? request.getParameter("inCRI") : ConstantesCRM.SZERO);
		inCRI = inCRI.equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SZERO : inCRI;

		// Jogado em sessao para retorno da tela inicial (Lupa na Tela Detalhe)
		if (ConstantesCRM.SONE.equals(inRC) || ConstantesCRM.STWO.equals(inRC)) {
			request.getSession().setAttribute("inRC", inRC);
		}
		log.debug("AtendimentoDetalheController:begin.do - [AtendimentoFacade.getDetalheAtendimento Envio = user = " + user + " idAtendimento= " + idAtendimento + "]");
		RWFAtendimentoVO avo = rAtendimentoFacade.getDetalheAtendimento(user, idAtendimento);
		log.debug("AtendimentoDetalheController:begin.do - [AtendimentoFacade.getDetalheAtendimento Retorno = RWFAtendimentoVO = " + avo.toString() + "]");
		rWFDetalheForm.setAtendimentoVO(avo);
		rWFDetalheForm.setFila(fila);
		rWFDetalheForm.setInCRI(inCRI);
		rWFDetalheForm.setInRC(inRC);
		rWFDetalheForm.setMeuCliente(meuCliente);

		request.getSession().setAttribute("atdVO", avo);

		rWFDetalheForm.setQtdProcessosPai(avo.getNrAtendimentosOrigem());
		LoadEstados(mapping, rWFDetalheForm, request, response);
		log.debug("AtendimentoDetalheController:begin.do - [Detalhe de Atendimento - Fim do Metodo]");

		request.setAttribute("rWFDetalheForm", rWFDetalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);

	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="RDetalheAbaRel.jsp"
	 */
	protected ActionForward begin2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:begin.do - [Detalhe de Atendimento - Inicio do Metodo]");
		String inCRI = ConstantesCRM.SVAZIO;
		String inRC = ConstantesCRM.SVAZIO;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtendimento");
		log.debug("AtendimentoDetalheController:begin.do - [idAtendimento = " + idAtendimento + "]");
		String inDisponivel = request.getParameter("inDisponivel");
		log.debug("AtendimentoDetalheController:begin.do - [inDisponivel = " + inDisponivel + "]");
		if (request.getSession().getAttribute("inRC") != null) {
			inRC = (String) request.getSession().getAttribute("inRC");
			log.debug("AtendimentoDetalheController:begin.do - [inRC = " + inRC + "]");
			request.getSession().removeAttribute("inRC");
		} else {
			inRC = (request.getParameter("inRC") != null ? request.getParameter("inRC") : ConstantesCRM.SZERO);
			if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
				inRC = ConstantesCRM.STWO;
			}
		}
		inRC = inRC.equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SZERO : inRC;
		if (inDisponivel != null && !ConstantesCRM.SVAZIO.equals(inDisponivel)) {
			request.getSession().setAttribute("inDisponivel", inDisponivel);
		}
		String fila = request.getParameter("fila");
		log.debug("AtendimentoDetalheController:begin.do - [fila = " + fila + "]");
		inCRI = (request.getParameter("inCRI") != null ? request.getParameter("inCRI") : ConstantesCRM.SZERO);
		inCRI = inCRI.equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SZERO : inCRI;
		// Jogado em sessao para retorno da tela inicial (Lupa na Tela Detalhe)
		if (inRC.equals(ConstantesCRM.SONE) || inRC.equals(ConstantesCRM.STWO)) {
			request.getSession().setAttribute("inRC", inRC);
		}
		log.debug("AtendimentoDetalheController:begin.do - [AtendimentoFacade.getDetalheAtendimento Envio = user = " + user + " idAtendimento= " + idAtendimento + "]");
		RWFAtendimentoVO avo = rAtendimentoFacade.getDetalheAtendimento(user, idAtendimento);
		log.debug("AtendimentoDetalheController:begin.do - [AtendimentoFacade.getDetalheAtendimento Retorno = RWFAtendimentoVO = " + avo.toString() + "]");
		rWFDetalheForm.setAtendimentoVO(avo);
		rWFDetalheForm.setFila(fila);
		rWFDetalheForm.setInCRI(inCRI);
		rWFDetalheForm.setInRC(inRC);

		request.getSession().setAttribute("atdVO", avo);

		rWFDetalheForm.setQtdProcessosPai(avo.getNrAtendimentosOrigem());
		LoadEstados(mapping, rWFDetalheForm, request, response);
		log.debug("AtendimentoDetalheController:begin.do - [Detalhe de Atendimento - Fim do Metodo]");

		request.setAttribute("rWFDetalheForm", rWFDetalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="RDetalheAbaRelHist.jsp"
	 */
	protected ActionForward begin3(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:begin.do - [Detalhe de Atendimento - Inicio do Metodo]");
		String inCRI = ConstantesCRM.SVAZIO;
		String inRC = ConstantesCRM.SVAZIO;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtendimento");
		log.debug("AtendimentoDetalheController:begin.do - [idAtendimento = " + idAtendimento + "]");
		String inDisponivel = request.getParameter("inDisponivel");
		log.debug("AtendimentoDetalheController:begin.do - [inDisponivel = " + inDisponivel + "]");

		if (request.getSession().getAttribute("inRC") != null) {
			inRC = (String) request.getSession().getAttribute("inRC");
			log.debug("AtendimentoDetalheController:begin.do - [inRC = " + inRC + "]");
			request.getSession().removeAttribute("inRC");
		} else {
			inRC = (request.getParameter("inRC") != null ? request.getParameter("inRC") : ConstantesCRM.SZERO);
			if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
				inRC = ConstantesCRM.STWO;
			}
		}
		inRC = inRC.equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SZERO : inRC;
		if (inDisponivel != null && !ConstantesCRM.SVAZIO.equals(inDisponivel)) {
			request.getSession().setAttribute("inDisponivel", inDisponivel);
		}

		String fila = request.getParameter("fila");
		log.debug("AtendimentoDetalheController:begin.do - [fila = " + fila + "]");
		inCRI = (request.getParameter("inCRI") != null ? request.getParameter("inCRI") : ConstantesCRM.SZERO);
		inCRI = inCRI.equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SZERO : inCRI;
		// Jogado em sessao para retorno da tela inicial (Lupa na Tela Detalhe)
		if (inRC.equals(ConstantesCRM.SONE) || inRC.equals(ConstantesCRM.STWO)) {
			request.getSession().setAttribute("inRC", inRC);
		}
		log.debug("AtendimentoDetalheController:begin.do - [AtendimentoFacade.getDetalheAtendimento Envio = user = " + user + " idAtendimento= " + idAtendimento + "]");

		RWFAtendimentoVO avo = rAtendimentoFacade.getDetalheAtendimentoEx(user, idAtendimento, false);

		log.debug("AtendimentoDetalheController:begin.do - [AtendimentoFacade.getDetalheAtendimento Retorno = RWFAtendimentoVO = " + avo.toString() + "]");
		rWFDetalheForm.setAtendimentoVO(avo);
		rWFDetalheForm.setFila(fila);
		rWFDetalheForm.setInCRI(inCRI);
		rWFDetalheForm.setInRC(inRC);

		request.getSession().setAttribute("atdVO", avo);

		rWFDetalheForm.setQtdProcessosPai(avo.getNrAtendimentosOrigem());
		LoadEstadosEx(mapping, rWFDetalheForm, request, response);
		log.debug("AtendimentoDetalheController:begin.do - [Detalhe de Atendimento - Fim do Metodo]");

		request.setAttribute("rWFDetalheForm", rWFDetalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDAlerta.jsp"
	 */
	protected ActionForward RDAlerta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDAlerta.do - [Detalhe de Atendimento - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDAlerta.do - [idAtendimento = " + idAtendimento + "]");

		if (idAtendimento != null) {
			log.debug("AtendimentoDetalheController:RDAlerta.do - [AtendimentoFacade.obtemAlertaVO =>Envio=>user = " + user + " idAtendimento = " + idAtendimento + " simplificado = 1 ]");
			// Peticion a Tux de las alertas asociadas a un usuario y un
			// atendimiento
			AlertaVO[] vAlertas = atendimentoFacade.obtemAlertaVO(user, idAtendimento, ConstantesCRM.SONE);
			// asignacion al formulario de alertas de la peticion realizada a
			// Tux
			rWFAlertaForm.setAlertaVO(vAlertas);
		}
		log.debug("AtendimentoDetalheController:RDAlerta.do - [Detalhe de Atendimento - Fim do Metodo]");
		request.setAttribute("rWFAlertaForm", rWFAlertaForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDAlerta.jsp"
	 */
	protected ActionForward RDAlertaEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDAlerta.do - [Detalhe de Atendimento - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDAlerta.do - [idAtendimento = " + idAtendimento + "]");

		if (idAtendimento != null) {
			log.debug("AtendimentoDetalheController:RDAlerta.do - [AtendimentoFacade.obtemAlertaVO =>Envio=>user = " + user + " idAtendimento = " + idAtendimento + " simplificado = 1 ]");
			// Peticion a Tux de las alertas asociadas a un usuario y un
			// atendimiento
			AlertaVO[] vAlertas = atendimentoFacade.obtemAlertaVOEx(user, idAtendimento, ConstantesCRM.SONE);
			// asignacion al formulario de alertas de la peticion realizada a
			// Tux
			rWFAlertaForm.setAlertaVO(vAlertas);
		}
		log.debug("AtendimentoDetalheController:RDAlerta.do - [Detalhe de Atendimento - Fim do Metodo]");
		request.setAttribute("rWFAlertaForm", rWFAlertaForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDHistorico.jsp"
	 * @jpf:forward name="success_360" path="RDHistorico_360.jsp"
	 */
	protected ActionForward RDHistorico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoDetalheController:RDHistorico.do - [Detalhe de Atendimento - Inicio do Metodo]");

		String destino = ConstantesCRM.SVAZIO;
		if (ConstantesCRM.STRUE.equals(request.getSession().getAttribute("ACESSO_EXTERNO"))) {
			destino = "success_360";
			if (this.rWFDetalheForm.getEstadoVO() == null) {
				LoadEstados(mapping, this.rWFDetalheForm, request, response);
			}
			this.rWFHistoricoForm.setEstadoVO(this.rWFDetalheForm.getEstadoVO());
		} else {
			destino = ConstantesCRM.SUCCESS;
		}

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		String estado = request.getParameter("est");
		String subestado = request.getParameter("subest");
		String indUp = request.getParameter("indUp");
		String inCRI = request.getParameter("inCRI");
		String inRC = request.getParameter("inRC");
		if (estado == null || estado.equals(ConstantesCRM.SVAZIO)) {
			estado = "-1";
		}
		if (subestado == null || subestado.equals(ConstantesCRM.SVAZIO)) {
			subestado = "-1";
		}
		log.debug("AtendimentoDetalheController:RDHistorico.do - [idAtendimento = " + idAtendimento + " estado = " + estado + " subEstado = " + subestado + " indUp = " + indUp + " inCRI = " + inCRI + " inRC = " + inRC + "]");
		AtendimentoHistoricoVO[] hvo = atendimentoFacade.obtemAtendimentoHistoricoVO(user, idAtendimento, estado, subestado, inCRI, inRC);

		for (int i = 0; i < hvo.length; i++) {
			hvo[i].setDsComentario(hvo[i].getDsComentario().replaceAll("\r", "\n").replaceAll("\n", " "));
		}

		rWFHistoricoForm.setHistoricoVO(hvo);
		if ((indUp != null) && (indUp.equals(ConstantesCRM.SONE))) {
			rWFHistoricoForm.setAltura("278");
		} else {
			rWFHistoricoForm.setAltura("105");
		}
		log.debug("AtendimentoDetalheController:RDHistorico.do - [Detalhe de Atendimento - Fim do Metodo]");

		request.setAttribute("rWFHistoricoForm", rWFHistoricoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDHistorico.jsp"
	 * @jpf:forward name="success_360" path="RDHistorico_360.jsp"
	 */
	protected ActionForward RDHistoricoEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoDetalheController:RDHistorico.do - [Detalhe de Atendimento - Inicio do Metodo]");

		String destino = ConstantesCRM.SVAZIO;
		if (ConstantesCRM.STRUE.equals(request.getSession().getAttribute("ACESSO_EXTERNO"))) {
			destino = "success_360";
		} else {
			destino = ConstantesCRM.SUCCESS;
		}

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		String estado = request.getParameter("est");
		String subestado = request.getParameter("subest");
		String indUp = request.getParameter("indUp");
		String inCRI = request.getParameter("inCRI");
		String inRC = request.getParameter("inRC");
		if (estado == null || estado.equals(ConstantesCRM.SVAZIO)) {
			estado = "-1";
		}
		if (subestado == null || subestado.equals(ConstantesCRM.SVAZIO)) {
			subestado = "-1";
		}
		log.debug("AtendimentoDetalheController:RDHistorico.do - [idAtendimento = " + idAtendimento + " estado = " + estado + " subEstado = " + subestado + " indUp = " + indUp + " inCRI = " + inCRI + " inRC = " + inRC + "]");
		AtendimentoHistoricoVO[] hvo = atendimentoFacade.obtemAtendimentoHistoricoVOEx(user, idAtendimento, estado, subestado, inCRI, inRC);

		for (int i = 0; i < hvo.length; i++) {
			hvo[i].setDsComentario(hvo[i].getDsComentario().replaceAll("\r", "\n").replaceAll("\n", " "));
		}

		rWFHistoricoForm.setHistoricoVO(hvo);
		if ((indUp != null) && (indUp.equals(ConstantesCRM.SONE))) {
			rWFHistoricoForm.setAltura("278");
		} else {
			rWFHistoricoForm.setAltura("105");
		}
		log.debug("AtendimentoDetalheController:RDHistorico.do - [Detalhe de Atendimento - Fim do Metodo]");

		request.setAttribute("rWFHistoricoForm", rWFHistoricoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDComentarioHistorico.jsp"
	 */
	protected ActionForward RDComentarioHistorico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDComentarioHistorico.do - [Detalhe de Atendimento - Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		String idAtdHco = request.getParameter("idAtdHco");

		log.debug("AtendimentoDetalheController:RDComentarioHistorico.do - [idAtdHco = " + idAtdHco + "]");

		StringComumVO scvo = rAtendimentoFacade.getComentarioHistorico(user, idAtdHco);

		request.setAttribute("coment", scvo.getStringComum());

		log.debug("AtendimentoDetalheController:RDComentarioHistorico.do - [coment = " + scvo.getStringComum() + "]");

		log.debug("AtendimentoDetalheController:RDComentarioHistorico.do - [Detalhe de Atendimento - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward return-action="retornoAba" name="retornoAba"
	 */
	protected ActionForward abaVoltar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		ActionRedirect action = new ActionRedirect(mapping.findForward("retornoAba"));
		return action;
	}

	/**
	 * @jpf:action
	 * @jpf:forward return-action="retornoAbaSolicitante" name="retornoAba"
	 */
	protected ActionForward abaVoltarSolicitante(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

				
		ActionRedirect action = new ActionRedirect(mapping.findForward("retornoAba"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return action;
	}

	/**
	 * @jpf:action
	 * @jpf:forward return-action="retornoAbaSolicitanteEx" name="retornoAba"
	 */
	protected ActionForward abaVoltarSolicitanteEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward f = mapping.findForward("retornoAba");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDArvoreAtendimento.jsp"
	 */
	protected ActionForward RDArvoreAtendimento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [Arvore de Atendimento - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [idAtendimento = " + idAtendimento + "]");

		StringComumVO scvo = rAtendimentoFacade.getDescricaoArvoreAtendimento(user, idAtendimento);
		String palitagem = new String(scvo.getStringComum().getBytes(ConstantesCRM.SISO));
        System.out.println("palitagem: "+palitagem);

		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [palitagem = " + palitagem + "]");
		palitagem = palitagem.startsWith("/") ? palitagem.replaceFirst("/", ConstantesCRM.SVAZIO).trim() : palitagem;

		StringBuffer sbScript = new StringBuffer();
		if (palitagem != null) {
			String[] itens = palitagem.split("\\s*/\\s*");
			sbScript.append("<table width='100%' border='0' cellpadding='0' cellspacing='0'>\n");
			if (itens.length == 1) {
				sbScript.append("    <td width='20px'><img src='../../resources/images/page.gif' border='0'></td>\n");
				sbScript.append("    <td>").append(itens[0].trim()).append("</td>\n");
			} else {
				for (int i = 0; i < itens.length; i++) {
					sbScript.append("<tr>\n");

					if (i == 0) {
						sbScript.append("    <td width='20px'><img src='../../resources/images/folderopen.gif'></td>\n");
						sbScript.append("    <td colspan='").append(itens.length).append("'>").append(itens[i].trim()).append("</td>\n");
					} else if (i == itens.length - 1) {
						for (int j = 0; i > 1 && j < i - 1; j++) {
							sbScript.append("    <td>&nbsp;</td>\n");
						}
						sbScript.append("    <td width='20'><img src='../../resources/images/joinbottom.gif' border='0'></td>\n");
						sbScript.append("    <td width='1%'><img src='../../resources/images/page.gif' border='0'></td>\n");
						sbScript.append("    <td width='99%'>").append(itens[i].trim()).append("</td>\n");
					} else {
						for (int j = 0; i > 1 && j < i - 1; j++) {
							sbScript.append("    <td></td>\n");
						}
						sbScript.append("    <td width='20'><img src='../../resources/images/joinbottom.gif'></td>\n");
						sbScript.append("    <td width='20'><img src='../../resources/images/folderopen.gif'></td>\n");
						sbScript.append("    <td colspan='").append(itens.length - i).append("'>").append(itens[i].trim()).append("</td>\n");
					}
					sbScript.append("</tr>\n");
				}
			}
			sbScript.append("</table>\n");
			// script += "</table>";
		}
		request.setAttribute("scrPalitagem", sbScript.toString());
		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [Javascript da Arvore de Atendimento = " + sbScript.toString() + "]");
		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [Arvore de Atendimento - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		//ActionRedirect action = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		//action.addParameter("scrPalitagem", sbScript.toString());
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDArvoreAtendimento.jsp"
	 */
	protected ActionForward RDArvoreAtendimentoEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [Arvore de Atendimento - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [idAtendimento = " + idAtendimento + "]");

		StringComumVO scvo = rAtendimentoFacade.getDescricaoArvoreAtendimentoEx(user, idAtendimento);
		String palitagem = new String(scvo.getStringComum());

		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [palitagem = " + palitagem + "]");
		palitagem = palitagem.startsWith("/") ? palitagem.replaceFirst("/", ConstantesCRM.SVAZIO).trim() : palitagem;

		StringBuffer sbScript = new StringBuffer();
		
		if (palitagem != null) {
			String[] itens = palitagem.split("\\s*/\\s*");
			sbScript.append("<table width='100%' border='0' cellpadding='0' cellspacing='0'>\n");
			
			if (itens.length == 1) {
				sbScript.append("    <td width='20px'><img src='../../resources/images/page.gif' border='0'></td>\n");
				sbScript.append("    <td>").append(itens[0].trim()).append("</td>\n");
			
			} else {
				for (int i = 0; i < itens.length; i++) {
					sbScript.append("<tr>\n");

					if (i == 0) {
						sbScript.append("    <td width='20px'><img src='../../resources/images/folderopen.gif'></td>\n");
						sbScript.append("    <td colspan='").append(itens.length).append("'>").append(itens[i].trim()).append("</td>\n");
					
					} else if (i == itens.length - 1) {
						for (int j = 0; i > 1 && j < i - 1; j++) {
							sbScript.append("    <td>&nbsp;</td>\n");
						}
						sbScript.append("    <td width='20'><img src='../../resources/images/joinbottom.gif' border='0'></td>\n");
						sbScript.append("    <td width='1%'><img src='../../resources/images/page.gif' border='0'></td>\n");
						sbScript.append("    <td width='99%'>").append(itens[i].trim()).append("</td>\n");
					
					} else {
						for (int j = 0; i > 1 && j < i - 1; j++) {
							sbScript.append("    <td></td>\n");
						}
						sbScript.append("    <td width='20'><img src='../../resources/images/joinbottom.gif'></td>\n");
						sbScript.append("    <td width='20'><img src='../../resources/images/folderopen.gif'></td>\n");
						sbScript.append("    <td colspan='").append(itens.length - i).append("'>").append(itens[i].trim()).append("</td>\n");
					}
					sbScript.append("</tr>\n");
				}
			}
			sbScript.append("</table>\n");
			// script += "</table>";
		}
		request.setAttribute("scrPalitagem", sbScript.toString());
		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [Javascript da Arvore de Atendimento = " + sbScript.toString() + "]");
		log.debug("AtendimentoDetalheController:RDArvoreAtendimento.do - [Arvore de Atendimento - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDNivelProcesso.jsp"
	 */
	protected ActionForward RDNivelProcesso(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDNivelProcesso.do - [Nivel do Processo - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDNivelProcesso.do - [idAtendimento = " + idAtendimento + "]");
		FaseVO[] faseVO = rAtendimentoFacade.getFasesNiveisAtendimento(user, idAtendimento);
		rWFNivelProcesoForm.setFaseVO(faseVO);
		log.debug("AtendimentoDetalheController:RDNivelProcesso.do - [Nivel do Processo - Fim do Metodo]");
		request.setAttribute("rWFNivelProcesoForm", rWFNivelProcesoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDNivelProcesso.jsp"
	 */
	protected ActionForward RDNivelProcessoEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDNivelProcesso.do - [Nivel do Processo - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDNivelProcesso.do - [idAtendimento = " + idAtendimento + "]");
		FaseVO[] faseVO = rAtendimentoFacade.getFasesNiveisAtendimentoEx(user, idAtendimento);
		rWFNivelProcesoForm.setFaseVO(faseVO);
		log.debug("AtendimentoDetalheController:RDNivelProcesso.do - [Nivel do Processo - Fim do Metodo]");
		request.setAttribute("rWFNivelProcesoForm", rWFNivelProcesoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDLinhasAssoc.jsp"
	 */
	protected ActionForward RDLinhasAssoc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDLinhasAssoc.do - [Linhas Associadas - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDLinhasAssoc.do - [idAtendimento = " + idAtendimento + "]");

		ListaDadosVO listaDadosVO = atendimentoFacade.obtemLinhasAssociadas(user, idAtendimento, ConstantesCRM.SVAZIO);
		rWFLinhasAssocForm.setLinhasAssoc(listaDadosVO);

		log.debug("AtendimentoDetalheController:RDLinhasAssoc.do - [Nivel do Processo - Fim do Metodo]");

		request.setAttribute("rWFLinhasAssocForm", rWFLinhasAssocForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDLinhasAssoc.jsp"
	 */
	protected ActionForward RDLinhasAssocEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDLinhasAssoc.do - [Linhas Associadas - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDLinhasAssoc.do - [idAtendimento = " + idAtendimento + "]");

		ListaDadosVO listaDadosVO = atendimentoFacade.obtemLinhasAssociadasEx(user, idAtendimento, ConstantesCRM.SVAZIO);
		rWFLinhasAssocForm.setLinhasAssoc(listaDadosVO);

		log.debug("AtendimentoDetalheController:RDLinhasAssoc.do - [Nivel do Processo - Fim do Metodo]");

		request.setAttribute("rWFLinhasAssocForm", rWFLinhasAssocForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDSolicitante.jsp"
	 */
	protected ActionForward RDSolicitante(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDSolicitante.do - [Solicitante - Inicio do Metodo]");
		atendimentoDetalheForm.setIdAtendimento(request.getParameter("idAtd"));
		log.debug("AtendimentoDetalheController:RDSolicitante.do - [idAtendimento = " + atendimentoDetalheForm.getIdAtendimento() + "]");
		atendimentoDetalheForm.setInResponsavel(request.getParameter("inResp"));
		log.debug("AtendimentoDetalheController:RDSolicitante.do - [inResp = " + atendimentoDetalheForm.getInResponsavel() + "]");
		log.debug("AtendimentoDetalheController:RDSolicitante.do - [Solicitante - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);

		ActionRedirect action = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		action.addParameter("idAtd", request.getParameter("idAtd"));
		action.addParameter("inResp", request.getParameter("inResp"));
		return action;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDSolicitanteEx.jsp"
	 */
	protected ActionForward RDSolicitanteEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDSolicitante.do - [Solicitante - Inicio do Metodo]");
		atendimentoDetalheForm.setIdAtendimento(request.getParameter("idAtd"));
		log.debug("AtendimentoDetalheController:RDSolicitante.do - [idAtendimento = " + atendimentoDetalheForm.getIdAtendimento() + "]");
		atendimentoDetalheForm.setInResponsavel(request.getParameter("inResp"));
		log.debug("AtendimentoDetalheController:RDSolicitante.do - [inResp = " + atendimentoDetalheForm.getInResponsavel() + "]");
		log.debug("AtendimentoDetalheController:RDSolicitante.do - [Solicitante - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="cliente" path="RDSCliente.jsp"
	 * @jpf:forward name="usuario" path="RDSUsuario.jsp"
	 * @jpf:forward name="prospect" path="RDSProspect.jsp"
	 */
	protected ActionForward RDUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDUser.do - [RDUser - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDUser.do - [idAtendimento = " + idAtendimento + "]");
		PessoaVO pvo = rAtendimentoFacade.getDadosPessoa(user, idAtendimento);
		log.debug("AtendimentoDetalheController:RDUser.do - [AtendimentoFacade.getDadosPessoa = " + pvo.toString() + "]");
		rWFPessoaForm.setPessoaVO(pvo);
		String inResp = request.getParameter("inResp");
		int resp = 0;
		try {
			resp = Integer.parseInt(inResp);
		} catch (Exception ex) {
			resp = 2;
		}
		log.debug("AtendimentoDetalheController:RDUser.do - [RDUser - Fim do Metodo]");
		switch (resp) {
		case 1:
			log.debug("<< RDUser : USUARIO");
			request.setAttribute("rWFPessoaForm", rWFPessoaForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("usuario");
		case 2:
			log.debug("<< RDUser : CLIENTE");
			request.setAttribute("rWFPessoaForm", rWFPessoaForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("cliente");
		case 6:
			log.debug("<< RDUser : PROSPECT");
			request.setAttribute("rWFPessoaForm", rWFPessoaForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("prospect");
		default:
			log.debug("<< RDUser : DEFAULT");
			request.setAttribute("rWFPessoaForm", rWFPessoaForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("prospect");
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="cliente" path="RDSClienteEx.jsp"
	 * @jpf:forward name="usuario" path="RDSUsuarioEx.jsp"
	 * @jpf:forward name="prospect" path="RDSProspectEx.jsp"
	 */
	protected ActionForward RDUserEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDUser.do - [RDUser - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDUser.do - [idAtendimento = " + idAtendimento + "]");
		PessoaVO pvo = rAtendimentoFacade.getDadosPessoaEx(user, idAtendimento);
		log.debug("AtendimentoDetalheController:RDUser.do - [AtendimentoFacade.getDadosPessoa = " + pvo.toString() + "]");
		if (!pvo.isSetCarterizacaoVO()) {
			pvo.addNewCarterizacaoVO();
		}
		if (!pvo.isSetSegmentacaoVO()) {
			pvo.addNewSegmentacaoVO();
		}
		rWFPessoaForm.setPessoaVO(pvo);
		String inResp = request.getParameter("inResp");
		int resp = 0;
		try {
			resp = Integer.parseInt(inResp);
		} catch (Exception ex) {
			resp = 2;
		}
		log.debug("AtendimentoDetalheController:RDUser.do - [RDUser - Fim do Metodo]");
		switch (resp) {
		case 1:
			log.debug("<< RDUser : USUARIO");
			request.setAttribute("rWFPessoaForm", rWFPessoaForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("usuario");
		case 2:
			log.debug("<< RDUser : CLIENTE");
			request.setAttribute("rWFPessoaForm", rWFPessoaForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("cliente");
		case 6:
			log.debug("<< RDUser : PROSPECT");
			request.setAttribute("rWFPessoaForm", rWFPessoaForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("prospect");
		default:
			log.debug("<< RDUser : DEFAULT");
			request.setAttribute("rWFPessoaForm", rWFPessoaForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("prospect");
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDProcesso.jsp"
	 */
	protected ActionForward RDProcesso(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDProcesso.do - [RDProcesso - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDProcesso.do - [idAtendimento = " + idAtendimento + "]");
		FormularioProcessoVO fpvo = rAtendimentoFacade.getFormularioProcessoVO(user, idAtendimento);
		// FormularioProcessoVO fpvo =
		// (FormularioProcessoVO)request.getAttribute("RDProcessoAba");
		rWFFormularioForm.setFormularioProcessoVO(fpvo);
		log.debug("AtendimentoDetalheController:RDProcesso.do - [RDProcesso - Fim do Metodo]");
		request.setAttribute("rWFFormularioForm", rWFFormularioForm);		
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDProcesso.jsp"
	 */
	protected ActionForward RDProcessoEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDProcesso.do - [RDProcesso - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDProcesso.do - [idAtendimento = " + idAtendimento + "]");
		FormularioProcessoVO fpvo = rAtendimentoFacade.getFormularioProcessoVOEx(user, idAtendimento);
		// FormularioProcessoVO fpvo =
		// (FormularioProcessoVO)request.getAttribute("RDProcessoAba");
		rWFFormularioForm.setFormularioProcessoVO(fpvo);
		log.debug("AtendimentoDetalheController:RDProcesso.do - [RDProcesso - Fim do Metodo]");
		request.setAttribute("rWFFormularioForm", rWFFormularioForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDContato.jsp"
	 */
	protected ActionForward RDContato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDContato.do - [RDContato - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDContato.do - [idAtendimento = " + idAtendimento + "]");
		RDContatoVO cvo = rAtendimentoFacade.getDadosContato(user, idAtendimento);
		// RDContatoVO cvo =
		// (RDContatoVO)request.getAttribute("RDContatoAba");
		// formata os números de linhas
		cvo.setNrTelefone(ConstantesCRM.formatPhoneNumber(cvo.getNrTelefone()));
		cvo.setNrLinha(ConstantesCRM.formatPhoneNumber(cvo.getNrLinha()));
		log.debug("AtendimentoDetalheController:RDContato.do - [NrTelefone = " + ConstantesCRM.formatPhoneNumber(cvo.getNrTelefone()) + "]");
		log.debug("AtendimentoDetalheController:RDContato.do - [NrLinha = " + ConstantesCRM.formatPhoneNumber(cvo.getNrLinha()) + "]");
		for (int linha = 0; linha < cvo.getWFAtendimentoContatoComunicVOArray().length; linha++) {
			// cvo.getWFAtendimentoContatoComunicVOArray(linha).setDsComunicacao((StringUtilStatic.mask(cvo.getWFAtendimentoContatoComunicVOArray(linha).getDsComunicacao(),
			// "(##) ####-####")));
			cvo.getWFAtendimentoContatoComunicVOArray(linha).setDsComunicacao(cvo.getWFAtendimentoContatoComunicVOArray(linha).getDsComunicacao());
		}
		rWFContatoForm.setrDContato(cvo);
		log.debug("AtendimentoDetalheController:RDContato.do - [RDContato - Fim do Metodo]");
		request.setAttribute("rWFContatoForm", rWFContatoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDContatoEx.jsp"
	 */
	protected ActionForward RDContatoEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDContato.do - [RDContato - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDContato.do - [idAtendimento = " + idAtendimento + "]");
		RDContatoVO cvo = rAtendimentoFacade.getDadosContatoEx(user, idAtendimento);
		// RDContatoVO cvo =
		// (RDContatoVO)request.getAttribute("RDContatoAba");
		// formata os números de linhas
		cvo.setNrTelefone(ConstantesCRM.formatPhoneNumber(cvo.getNrTelefone()));
		cvo.setNrLinha(ConstantesCRM.formatPhoneNumber(cvo.getNrLinha()));
		log.debug("AtendimentoDetalheController:RDContato.do - [NrTelefone = " + ConstantesCRM.formatPhoneNumber(cvo.getNrTelefone()) + "]");
		log.debug("AtendimentoDetalheController:RDContato.do - [NrLinha = " + ConstantesCRM.formatPhoneNumber(cvo.getNrLinha()) + "]");
		for (int linha = 0; linha < cvo.getWFAtendimentoContatoComunicVOArray().length; linha++) {
			// cvo.getWFAtendimentoContatoComunicVOArray(linha).setDsComunicacao((StringUtilStatic.mask(cvo.getWFAtendimentoContatoComunicVOArray(linha).getDsComunicacao(),
			// "(##) ####-####")));
			cvo.getWFAtendimentoContatoComunicVOArray(linha).setDsComunicacao(cvo.getWFAtendimentoContatoComunicVOArray(linha).getDsComunicacao());
		}
		rWFContatoForm.setrDContato(cvo);
		log.debug("AtendimentoDetalheController:RDContato.do - [RDContato - Fim do Metodo]");
		request.setAttribute("rWFContatoForm", rWFContatoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDEncerramento.jsp"
	 */
	protected ActionForward RDEncerramento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * TO DO: Hacer la llamada al nuevo servicio y revisar el formulario de vuelta.
		 */
		log.debug("AtendimentoDetalheController:RDEncerramento.do - [RDEncerramento - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDEncerramento.do - [idAtendimento = " + idAtendimento + "]");
		// TO DO: Borrar
		// String palitagem =
		// this.atendimentoDetalheForm.getAtendimentoDetalheVO().getAtendimentoVO().getEncerramentoVO().getDsBaixa().trim();
		EncerramentoVO evo = rAtendimentoFacade.getEncerramentoVO(user, idAtendimento);
		rWFEncerramentoForm.setEncerramentoVO(evo);
		StringBuffer sbScript = new StringBuffer(ConstantesCRM.SVAZIO);
		String palitagem = evo.getDsBaixa();
		palitagem = palitagem.startsWith("/") ? palitagem.replaceFirst("/", ConstantesCRM.SVAZIO).trim() : palitagem;
		if (palitagem != null) {
			String[] itens = palitagem.split("\\s*/\\s*");

			sbScript.append("<table width='100%' cellpadding='0' cellspacing='0'>");

			if (itens.length == 1) {
				sbScript.append("    <td width='20px'><img src='../../resources/images/page.gif' border='0'></td>");
				sbScript.append("    <td>");
				sbScript.append(itens[0].trim());
				sbScript.append("</td>");

			} else {

				for (int i = 0; i < itens.length; i++) {

					sbScript.append("<tr>");

					if (i == 0) {
						sbScript.append("    <td width='20px'><img src='../../resources/images/folderopen.gif'></td>");
						sbScript.append("    <td colspan='");
						sbScript.append(itens.length);
						sbScript.append("'>");
						sbScript.append(itens[i].trim());
						sbScript.append("</td>");
					} else if (i == itens.length - 1) {
						for (int j = 0; i > 1 && j < i - 1; j++) {
							sbScript.append("    <td>&nbsp;</td>");
						}
						sbScript.append("    <td width='20px'><img src='../../resources/images/joinbottom.gif' border='0'></td>");
						sbScript.append("    <td width='20px'><img src='../../resources/images/page.gif' border='0'></td>");
						sbScript.append("    <td>");
						sbScript.append(itens[i].trim());
						sbScript.append("</td>");
					} else {
						for (int j = 0; i > 1 && j < i - 1; j++) {
							sbScript.append("    <td>&nbsp;</td>");
						}
						sbScript.append("    <td width='20px'><img src='../../resources/images/joinbottom.gif'></td>");
						sbScript.append("    <td width='20px'><img src='../../resources/images/folderopen.gif'></td>");
						sbScript.append("    <td colspan='");
						sbScript.append(itens.length);
						sbScript.append("'>");
						sbScript.append(itens[i].trim());
						sbScript.append("</td>");
					}
				}
				sbScript.append("</tr>");
			}
			sbScript.append("</table>");
			log.debug("AtendimentoDetalheController:RDEncerramento.do - [Arvore de Encerramento = " + sbScript.toString() + "]");
		}
		// this.atendimentoDetalheForm.setScriptPalitagemEncerramento(sbScript.toString());
		request.setAttribute("scrPalitagem", sbScript.toString());
		log.debug("AtendimentoDetalheController:RDEncerramento.do - [RDEncerramento - Fim do Metodo]");
		request.setAttribute("rWFEncerramentoForm", rWFEncerramentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDOperacion.jsp"
	 */
	protected ActionForward RDOperacionAbaRelacionamento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDOperacion.do - [RDOperacion - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String inSupervisor = ConstantesCRM.SZERO;
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDOperacion.do - [idAtendimento = " + idAtendimento + "]");
		String inCRI = request.getParameter("inCRI") != null ? request.getParameter("inCRI") : ConstantesCRM.SZERO;
		log.debug("AtendimentoDetalheController:RDOperacion.do - [inCRI = " + inCRI + "]");
		String inRC = request.getParameter("inRC") != null ? request.getParameter("inRC") : ConstantesCRM.SZERO;
		log.debug("AtendimentoDetalheController:RDOperacion.do - [inRC = " + inRC + "]");
		if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("inOrigemFila")) && inCRI.equals(ConstantesCRM.SZERO)) {
			inSupervisor = ConstantesCRM.SONE;
		}
		if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
			inRC = ConstantesCRM.STWO;
		}
		ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		if ((rWFDetalheForm.getFila() == null) || (rWFDetalheForm.getFila().equals(ConstantesCRM.SVAZIO))) {
			parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if (parametros != null) {
				idGrupo = parametros.getIdGrupo();
			}
		}
		if (idAtendimento != null) {
			// Peticion a Tux de las alertas asociadas a un usuario y un
			// atendimiento
			WFAcaoVO[] wfAcao = rAtendimentoFacade.getWFAcaoAbaRelacionamentoVO(user, idAtendimento, idGrupo, inCRI, inRC, inSupervisor);
			// asignacion al formulario de alertas de la peticion realizada a
			// Tux
			rWFAcaoForm.setWFAcaoVO(wfAcao);
		}
		log.debug("AtendimentoDetalheController:RDOperacion.do - [RDOperacion - Fim do Metodo]");
		request.setAttribute("rWFAcaoForm", rWFAcaoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDOperacion.jsp"
	 */
	protected ActionForward RDOperacionAbaRelacionamentoEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDOperacion.do - [RDOperacion - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String inSupervisor = ConstantesCRM.SZERO;
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDOperacion.do - [idAtendimento = " + idAtendimento + "]");
		String inCRI = request.getParameter("inCRI") != null ? request.getParameter("inCRI") : ConstantesCRM.SZERO;
		log.debug("AtendimentoDetalheController:RDOperacion.do - [inCRI = " + inCRI + "]");
		String inRC = request.getParameter("inRC") != null ? request.getParameter("inRC") : ConstantesCRM.SZERO;
		log.debug("AtendimentoDetalheController:RDOperacion.do - [inRC = " + inRC + "]");
		if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("inOrigemFila")) && inCRI.equals(ConstantesCRM.SZERO)) {
			inSupervisor = ConstantesCRM.SONE;
		}
		if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
			inRC = ConstantesCRM.STWO;
		}
		ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		if ((rWFDetalheForm.getFila() == null) || (rWFDetalheForm.getFila().equals(ConstantesCRM.SVAZIO))) {
			parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if (parametros != null) {
				idGrupo = parametros.getIdGrupo();
			}
		}
		if (idAtendimento != null) {
			// Peticion a Tux de las alertas asociadas a un usuario y un
			// atendimiento
			WFAcaoVO[] wfAcao = rAtendimentoFacade.getWFAcaoAbaRelacionamentoVOEx(user, idAtendimento, idGrupo, inCRI, inRC, inSupervisor);
			// asignacion al formulario de alertas de la peticion realizada a
			// Tux
			rWFAcaoForm.setWFAcaoVO(wfAcao);
		}
		log.debug("AtendimentoDetalheController:RDOperacion.do - [RDOperacion - Fim do Metodo]");
		request.setAttribute("rWFAcaoForm", rWFAcaoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="detalheProcessoGestorGerente" path="detalheProcessoGestorGerenteConta.jsp"
	 * @jpf:forward name="mensagemGestor" path="mensagemGestor.jsp"
	 * @jpf:forward name="incluirAlterarGestorGerente" path="incluirAlterarGestorGerente.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward getGestorGerenteConta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String headLog = "AtendimentoDetalheController::getGestorGerenteConta::";
		String forward = "detalheProcessoGestorGerente";
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		RWFAtendimentoVO avo = (RWFAtendimentoVO) request.getSession().getAttribute("atdVO");
		String nrLinha = avo.getNrTelefone();

		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute("parametros");

		if (!isClientePJ(parametros.getIdLinha(), request)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("mensagemGestor");
		}

		if ("getInclusaoAlteracaoForm".equals(request.getParameter("operacao"))) {
			forward = "incluirAlterarGestorGerente";
			this.gestorGerenteContaForm = new GestorGerenteContaForm();
			if ("gestor".equalsIgnoreCase(request.getParameter("inGestorGerente"))) {
				getGestorGerenteContaForm().setGestor(true);
				getGestorGerenteContaForm().setGerente(false);
			} else {
				getGestorGerenteContaForm().setGestor(false);
				getGestorGerenteContaForm().setGerente(true);
			}
			if (request.getParameter("idGestorGerente") != null && !ConstantesCRM.SVAZIO.equals(request.getParameter("idGestorGerente"))) {
				int j = getGestorGerenteContaForm().isGestor() ? 0 : 1;
				this.gestorGerenteContaForm = getAtendimentoDetalheForm().getGestorGerenteContaForm()[j];
			}
		}
		// Carregamento inicial (Lista com gestor e gerente, caso existentes)
		else {
			log.debug(headLog + "Busca Gestores");
			getAtendimentoDetalheForm().setGestorGerenteContaForm(new GestorGerenteContaForm[2]);

			avo = (RWFAtendimentoVO) request.getSession().getAttribute("atdVO");

			GetParametro parametro = GetParametro.getInstace();
			ParametroVO apoioParam = parametro.getParametroVO(idUsuario, "PARAMETRO_ENDPOINT_MANGESTOR");

			String endPoint = apoioParam.getDsValorParametro();
			log.debug(headLog + "endpoint = " + endPoint);
			String idAtend = String.valueOf(avo.getIdAtendimento());

			ParametrosBuscarListaGestores param = new ParametrosBuscarListaGestores();
			param.setCdTipoOperacao(ParametrosBuscarListaGestoresCdTipoOperacao.value1);
			log.debug(headLog + "CdTipoOperacao = " + ParametrosBuscarListaGestoresCdTipoOperacao.value3.toString());
			param.setIdAtendimento(idAtend);
			log.debug(headLog + "IdAtendimento = " + idAtend);

			GestorContaPortTypeProxy proxy = new GestorContaPortTypeProxy();
			proxy.setEndpoint(endPoint);

			GestorContaPortType type = proxy.getGestorContaPortType();
			((GestorContaBindingStub) type).setTimeout(20 * 1000);

			Gestores gestorConta = null;
			Gestores gerenteConta = null;
			try {
				// Busca Gestor de Conta
				log.debug(headLog + "Buscando Gestor de Conta");
				param.setCdTipoRelacionamento(ParametrosBuscarListaGestoresCdTipoRelacionamento.GC);
				log.debug(headLog + "CdTipoRelacionamento = " + ParametrosBuscarListaGestoresCdTipoRelacionamento.GC.toString());
				gestorConta = type.buscarListaGestores(param);
				if (gestorConta != null && gestorConta.getGestor() != null) {
					setDadosGestorGerenteContas(0, gestorConta.getGestor(0));
					log.debug(headLog + "gestorConta.getGestor().length = " + gestorConta.getGestor().length);
				} else {
					log.debug(headLog + "Nenhum gestor retornado.");
				}
			} catch (ErroInfo e) {
				log.error(headLog + "ErroInfo::" + e.getCodigo());
				log.error(headLog + "ErroInfo::" + e.getDescricao());
			} catch (Exception e) {
				log.error(headLog + "Exception::" + e.getMessage());
				log.error(headLog + "Exception::", e);
			}

			try {
				// Busca Gerente de Contas
				log.debug(headLog + "Buscando Gerente de Empresa");
				param.setCdTipoRelacionamento(ParametrosBuscarListaGestoresCdTipoRelacionamento.GRC);
				log.debug(headLog + "CdTipoRelacionamento = " + ParametrosBuscarListaGestoresCdTipoRelacionamento.GRC.toString());
				gerenteConta = type.buscarListaGestores(param);
				if (gerenteConta != null && gerenteConta.getGestor() != null) {
					setDadosGestorGerenteContas(1, gerenteConta.getGestor(0));
					log.debug(headLog + "gerenteConta.getGestor().length = " + gerenteConta.getGestor().length);
				} else {
					log.debug(headLog + "Nenhum gerente retornado.");
				}
			} catch (ErroInfo e) {
				log.error(headLog + "ErroInfo::" + e.getCodigo());
				log.error(headLog + "ErroInfo::" + e.getDescricao());
			} catch (Exception e) {
				log.error(headLog + "Exception::" + e.getMessage());
				log.error(headLog + "Exception::", e);
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(forward);
	}

	private boolean isClientePJ(String nrLinha, HttpServletRequest request) {
		boolean isPJ = false;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			if (nrLinha != null && nrLinha.length() > 0) {
				nrLinha = nrLinha.replaceAll("[^0-9]", ConstantesCRM.SVAZIO);
			}

			ParametrosVO resultadoVO = telaInicialFacade.getParametrosVO(user, nrLinha, ConstantesCRM.STWO);
			if (resultadoVO != null && "PJ".equals(resultadoVO.getInTipoPessoa()) && (ConstantesCRM.SONE.equals(resultadoVO.getIdTipoLinha()) || ConstantesCRM.SFIVE.equals(resultadoVO.getIdTipoLinha()))) {
				isPJ = true;
			} else {
				isPJ = false;
			}
		} catch (Exception e) {
		}
		return isPJ;
	}

	private void setDadosGestorGerenteContas(int x, Gestor gestor) {

		GestorGerenteContaForm[] gestorForm = getAtendimentoDetalheForm().getGestorGerenteContaForm();
		gestorForm[x] = new GestorGerenteContaForm();

		if (x == 0) {
			gestorForm[x].setGerente(false);
			gestorForm[x].setGestor(true);
			gestorForm[x].setTpRelacao("GT");
		} else {
			gestorForm[x].setGerente(true);
			gestorForm[x].setGestor(false);
			gestorForm[x].setTpRelacao("GR");
		}
		gestorForm[x].setTpOperacao("A");
		gestorForm[x].setIdGestorGerente(gestor.getCodigo() != null ? gestor.getCodigo().longValue() : 0);
		gestorForm[x].setPrimeiroNome(gestor.getPrimeiroNome());
		gestorForm[x].setSobrenome(gestor.getSobrenome());

		if (gestor.getDocumentos() != null && gestor.getDocumentos().getDocumento() != null && gestor.getDocumentos().getDocumento().length > 0) {
			String nrCPF = gestor.getDocumentos().getDocumento(0).getNumeroDocumento();
			gestorForm[x].setNrCPF(StringUtilStatic.mask(nrCPF, "###.###.###-##"));
		}

		String nrTelefone = ConstantesCRM.SVAZIO;
		String nrTelefoneContato = ConstantesCRM.SVAZIO;
		String dsEmail = ConstantesCRM.SVAZIO;

		if (gestor.getContatos() != null && gestor.getContatos().getContato() != null && gestor.getContatos().getContato().length > 0) {
			nrTelefone = gestor.getContatos().getContato(0).getTextoContato();
			if (gestor.getContatos().getContato().length >= 2) {
				nrTelefoneContato = gestor.getContatos().getContato(1).getTextoContato();
				if (gestor.getContatos().getContato().length > 2) {
					dsEmail = gestor.getContatos().getContato(2).getTextoContato();
				}
			}
		}

		gestorForm[x].setNrTelefone(ConstantesCRM.formatPhoneNumber(nrTelefone));
		gestorForm[x].setNrTelefoneContato(ConstantesCRM.formatPhoneNumber(nrTelefoneContato));
		gestorForm[x].setDsEmail(dsEmail);
		gestorForm[x].setDtAlteracao((new SimpleDateFormat("dd/MM/yyyy")).format(gestor.getDataUltimaAlteracao().getTime()));
		gestorForm[x].setDsLoginResponsavel(gestor.getUsuarioManutencao().getLogin());
		gestorForm[x].setIdContact(gestor.getCodigoGestor());

		getAtendimentoDetalheForm().setGestorGerenteContaForm(gestorForm);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward verificaNumeroVivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String retorno = ConstantesCRM.SVAZIO;
		String cdErro = ConstantesCRM.SZERO;
		String msErro = ConstantesCRM.SVAZIO;
		//AtendimentoDetalheForm form = (AtendimentoDetalheForm) formParam;

		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			String nrLinha = request.getParameter("nrLinha");
			if (nrLinha != null && nrLinha.length() > 0) {
				nrLinha = nrLinha.replaceAll("[^0-9]", ConstantesCRM.SVAZIO);
			}

			ParametrosVO resultadoVO = telaInicialFacade.getParametrosVO(user, nrLinha, ConstantesCRM.STWO);
			if (resultadoVO != null && resultadoVO.getIdLinha() != null && !ConstantesCRM.SVAZIO.equals(resultadoVO.getIdLinha().trim())) {
				retorno = ConstantesCRM.SOK;
			} else {
				retorno = ConstantesCRM.SNOK;
			}

		} catch (Exception e) {
			retorno = ConstantesCRM.SNOK;
			cdErro = ConstantesCRM.SONE;
			msErro = e.getMessage();

		} finally {
			try {
				String xmlOUT = "<msg><retorno>" + retorno + "</retorno><cdErro>" + cdErro + "</cdErro><msErro>" + msErro + "</msErro></msg>";
				response.setContentType("text/plain;charset=ISO-8859-1");
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(xmlOUT.getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * @jpf:action
	 */
	@SuppressWarnings("unused")
	protected ActionForward setGestorGerenteConta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GestorGerenteContaForm form = (GestorGerenteContaForm) formParam;

		String headLog = "AtendimentoDetalheController::setGestorGerenteConta::";
		String cdErro = ConstantesCRM.SVAZIO;
		String msErro = ConstantesCRM.SVAZIO;
		//String forward = "detalheProcessoGestorGerente";
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		log.debug(headLog + "Gravando dados de Gestor/Gerente");

		log.debug(headLog + "Buscando dados do atendimento pelo obj na Sessão atdVO");
		RWFAtendimentoVO avo = (RWFAtendimentoVO) request.getSession().getAttribute("atdVO");

		String nrLinha = avo.getLinhaPesquisa();
		log.debug(headLog + "nrLinha = " + nrLinha);

		log.debug(headLog + "Buscando dados da Linha pelo CarregaTI");
		TelaInicialVO telaInicial = telaInicialFacade.getTelaInicial(user, nrLinha);
		log.debug(headLog + "CarregaTI - retorno OK");

		GetParametro parametro = GetParametro.getInstace();
		ParametroVO apoioParam = parametro.getParametroVO(user, "PARAMETRO_ENDPOINT_MANGESTOR");

		String endPoint = apoioParam.getDsValorParametro();
		log.debug(headLog + "endPoint = " + endPoint);
		String nrConta = avo.getContaPesquisa();
		log.debug(headLog + "nrConta = " + nrConta);
		String nrCNPJ = telaInicial.getTIDocumento().getNrDocumento();
		log.debug(headLog + "nrCNPJ = " + nrCNPJ);

		GestorContaLocator locator = new GestorContaLocator();
		GestorContaPortType type = locator.getGestorContaPort(new URL(endPoint));

		ParametrosManterGestorContaEmpresa param = new ParametrosManterGestorContaEmpresa();
		param.setNrConta(nrConta);
		param.setNrDocumentoClientePj(nrCNPJ);

		if ("GT".equals(form.getTpRelacao())) {
			param.setCdTipoGestor(ParametrosManterGestorContaEmpresaCdTipoGestor.E);
			log.debug(headLog + "CdTipoGestor = " + ParametrosManterGestorContaEmpresaCdTipoGestor.E.toString());
		} else {
			param.setCdTipoGestor(ParametrosManterGestorContaEmpresaCdTipoGestor.A);
			log.debug(headLog + "CdTipoGestor = " + ParametrosManterGestorContaEmpresaCdTipoGestor.A.toString());
		}

		param.setCdSistemaOrigem(ParametrosManterGestorContaEmpresaCdSistemaOrigem.FO);
		log.debug(headLog + "CdSistemaOrigem = " + ParametrosManterGestorContaEmpresaCdSistemaOrigem.FO.toString());

		if (form.getIdGestorGerente() != 0) { // Alteração
			param.setCdTipoOperacao(ParametrosManterGestorContaEmpresaCdTipoOperacao.value2);
			log.debug(headLog + "CdTipoOperacao = " + ParametrosManterGestorContaEmpresaCdTipoOperacao.value2.toString());
			param.setIdPessoa(new BigDecimal(form.getIdGestorGerente()));
			log.debug(headLog + "IdPessoa = " + form.getIdGestorGerente());
		} else {// Inclusão
			param.setCdTipoOperacao(ParametrosManterGestorContaEmpresaCdTipoOperacao.value1);
			log.debug(headLog + "CdTipoOperacao = " + ParametrosManterGestorContaEmpresaCdTipoOperacao.value1.toString());
		}

		param.setNmNome(form.getPrimeiroNome());
		log.debug(headLog + "Nome = " + form.getPrimeiroNome());
		param.setNmNomeMeio(form.getNomeMeio());
		log.debug(headLog + "NmNomeMeio = " + form.getNomeMeio());
		param.setNmSobrenome(form.getSobrenome());
		log.debug(headLog + "NmSobrenome = " + form.getSobrenome());

		String nome = form.getPrimeiroNome() + " " + form.getNomeMeio() + (!ConstantesCRM.SVAZIO.equals(form.getNomeMeio()) ? " " : ConstantesCRM.SVAZIO) + form.getSobrenome();

		param.setNmNomeCompleto(nome);
		log.debug(headLog + "NmNomeCompleto = " + nome);

		param.setNrDocumentoGestor(form.getNrCPF().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
		log.debug(headLog + "NrDocumentoGestor = " + form.getNrCPF().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
		param.setNrTelefoneCelular(form.getNrTelefone().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
		log.debug(headLog + "NrTelefoneCelular = " + form.getNrTelefone().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
		param.setNrTelefoneContato(form.getNrTelefoneContato().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
		log.debug(headLog + "NrTelefoneContato = " + form.getNrTelefoneContato().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
		param.setNmEmail(form.getDsEmail());
		log.debug(headLog + "NmEmail = " + form.getDsEmail());

		if (telaInicial != null && telaInicial.getEnderecoBaseVO() != null) {
			log.debug(headLog + "Dados de Endereço");
			EnderecoBaseVO end = telaInicial.getEnderecoBaseVO();
			param.setNmLogradouro(end.getDsEndereco());
			log.debug(headLog + "NmLogradouro = " + end.getDsEndereco());
			param.setNmCidade(end.getDsCidade());
			log.debug(headLog + "NmCidade = " + end.getDsCidade());
			param.setNmBairro(end.getDsBairro());
			log.debug(headLog + "NmBairro = " + end.getDsBairro());
			param.setCdEstado(end.getUFVO().getSgUF());
			log.debug(headLog + "CdEstado = " + end.getUFVO().getSgUF());
			param.setNrCep(end.getNrCEP());
			log.debug(headLog + "NrCep = " + end.getNrCEP());
			param.setNrLogradouro(end.getNrEndereco());
			log.debug(headLog + "NrLogradouro = " + end.getNrEndereco());
			param.setDsComplemento(end.getDsComplemento());
			log.debug(headLog + "DsComplemento = " + end.getDsComplemento());
		}
		param.setIdUsuarioAlteracao(new BigDecimal(user));
		log.debug(headLog + "IdUsuarioAlteracao = " + user);
		param.setCnctId(form.getIdContact());
		log.debug(headLog + "CnctId = " + form.getIdContact());

		boolean retorno = true;
		try {
			log.debug(headLog + "Chamando manterGestorContaEmpresa ");
			((GestorContaBindingStub) type).setTimeout(20 * 1000);
			Gestor gestor = type.manterGestorContaEmpresa(param);
		} catch (ErroInfo e) {
			retorno = false;
			cdErro = e.getCodigo();
			msErro = e.getDescricao();
			log.error(headLog + "ErroInfo::" + e.getCodigo());
			log.error(headLog + "ErroInfo::" + e.getDescricao());
		} catch (Exception e) {
			retorno = false;
			cdErro = "99";
			msErro = e.getMessage();
			log.error(headLog + "Exception::" + e.getMessage());
			log.error(headLog + "Exception::", e);
		}

		String xmlOUT = "<msg><retorno>" + retorno + "</retorno><cdErro>" + cdErro + "</cdErro><msErro>" + msErro + "</msErro></msg>";
		response.setContentType("text/plain;charset=ISO-8859-1");
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		bufferedOutputStream.write(xmlOUT.getBytes(ConstantesCRM.SISO));
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		return null;

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RDOperacion.jsp"
	 */
	protected ActionForward RDOperacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:RDOperacion.do - [RDOperacion - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String inSupervisor = ConstantesCRM.SZERO;
		String idAtendimento = request.getParameter("idAtd");
		log.debug("AtendimentoDetalheController:RDOperacion.do - [idAtendimento = " + idAtendimento + "]");
		String inCRI = request.getParameter("inCRI") != null ? request.getParameter("inCRI") : ConstantesCRM.SZERO;
		log.debug("AtendimentoDetalheController:RDOperacion.do - [inCRI = " + inCRI + "]");
		String inRC = request.getParameter("inRC") != null ? request.getParameter("inRC") : ConstantesCRM.SZERO;
		log.debug("AtendimentoDetalheController:RDOperacion.do - [inRC = " + inRC + "]");
		if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("inOrigemFila")) && inCRI.equals(ConstantesCRM.SZERO)) {
			inSupervisor = ConstantesCRM.SONE;
		}
		if (ConstantesCRM.SONE.equals(request.getParameter("inMirrorRC"))) {
			inRC = ConstantesCRM.STWO;
		}
		ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		if ((rWFDetalheForm.getFila() == null) || (rWFDetalheForm.getFila().equals(ConstantesCRM.SVAZIO))) {
			parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if (parametros != null) {
				idGrupo = parametros.getIdGrupo();
			}
		}
		if (idAtendimento != null) {
			// Peticion a Tux de las alertas asociadas a un usuario y un
			// atendimiento
			WFAcaoVO[] wfAcao = rAtendimentoFacade.getWFAcaoVO(user, idAtendimento, idGrupo, inCRI, inRC, inSupervisor);
			// asignacion al formulario de alertas de la peticion realizada a
			// Tux
			rWFAcaoForm.setWFAcaoVO(wfAcao);
		}
		log.debug("AtendimentoDetalheController:RDOperacion.do - [RDOperacion - Fim do Metodo]");
		request.setAttribute("rWFAcaoForm", rWFAcaoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward LoadEstados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//RWFDetalheForm form = (RWFDetalheForm) formParam;

		log.debug("AtendimentoDetalheController:LoadEstados.do - [LoadEstados - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		this.rWFDetalheForm.setEstadoVO(atendimentoFacade.getWFEstadosVO(user, false));
		log.debug("AtendimentoDetalheController:LoadEstados.do - [LoadEstados - Fim do Metodo]");
		return null;
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward LoadEstadosEx(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//RWFDetalheForm form = (RWFDetalheForm) formParam;

		log.debug("AtendimentoDetalheController:LoadEstados.do - [LoadEstados - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		this.rWFDetalheForm.setEstadoVO(atendimentoFacade.getWFEstadosVOEx(user, false));
		log.debug("AtendimentoDetalheController:LoadEstados.do - [LoadEstados - Fim do Metodo]");
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward return-action="AtendimentoDetalheDone" name="AtendimentoDetalheDone"
	 */
	protected ActionForward atendimentoDetalheVoltar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoDetalheController:atendimentoDetalheVoltar.do - [atendimentoDetalheVoltar - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("AtendimentoDetalheDone");
	}

	public RWFDetalheForm getRWFDetalheForm() {
		return this.rWFDetalheForm;
	}

	public void setRWFDetalheForm(RWFDetalheForm rWFDetalheForm) {
		this.rWFDetalheForm = rWFDetalheForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoWorkflow/AtendimentoWorkflowController.jpf"
	 */
	protected ActionForward acaoWorkflow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoDetalheController:acaoWorkflow.do - [acaoWorkflow - Metodo]");
		ActionForward f = mapping.findForward(ConstantesCRM.SUCCESS);
		// TODO: REFACTORING
		// f.setQueryString(request.getQueryString());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="atendimentoDetalheVoltar.do"
	 */
	protected ActionForward AtendimentoWorkflowDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoDetalheController:AtendimentoWorkflowDone.do - [AtendimentoWorkflowDone - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="atendimentoDetalheVoltar.do"
	 */
	protected ActionForward RegistrarContatoDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoDetalheController:RegistrarContatoDone.do - [RegistrarContatoDone - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="/cliente/TelaInicial/begin.do" name="success"
	 */
	protected ActionForward telaInicial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheController:telaInicial.do - [telaInicial - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AtendimentoDetalheForm extends ActionForm {

		private static final long serialVersionUID = 3235999742436903790L;

		// private int docTec;

		/**
		 * Antiguo formulario => eliminar cuando todo este funcionando.
		 */
		public AtendimentoDetalheForm() {

			// inicicializa objeto atendimentoVO
			this.atendimentoDetalheVO = AtendimentoDetalheVO.Factory.newInstance();
			this.atendimentoDetalheVO.setAtendimentoVO(AtendimentoVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().setAtendimentoWorkflowVO(AtendimentoWorkflowVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO().setAtendimentoWorkflowAgendamentoVO(AtendimentoWorkflowAgendamentoVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO().setAtendimentoWorkflowComumVO(AtendimentoWorkflowComumVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().setWFGrupoVOArray(new WFGrupoVO[0]);
			this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().setWFMotivoVOArray(new WFMotivoVO[0]);
			this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO().setAtendimentoWorkflowEncerramentoVO(AtendimentoWorkflowEncerramentoVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO().setAtendimentoWorkflowInsistenciaVO(AtendimentoWorkflowInsistenciaVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO().setAtendimentoWorkflowPesquisaVO(AtendimentoWorkflowPesquisaVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO().setAtendimentoWorkflowTecnicoVO(AtendimentoWorkflowTecnicoVO.Factory.newInstance());

			this.atendimentoDetalheVO.getAtendimentoVO().setPessoaVO(PessoaVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getPessoaVO().setCarterizacaoVO(CarterizacaoVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getPessoaVO().setSegmentacaoVO(SegmentacaoVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().setWFEstadoVO(WFEstadoVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().setWFSubEstadoVO(WFSubEstadoVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().setAlertaVOArray(new AlertaVO[0]);

			this.atendimentoDetalheVO.getAtendimentoVO().setContas(Contas.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getContas().setContaVOArray(new ContaVO[0]);

			this.atendimentoDetalheVO.getAtendimentoVO().getPessoaVO().setAtendimentoTipoComunicacaoVOArray(new AtendimentoTipoComunicacaoVO[0]);

			this.atendimentoDetalheVO.getAtendimentoVO().setArvoreAtendimentoVO(ArvoreAtendimentoVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getArvoreAtendimentoVO().setFormularioVO(FormularioVO.Factory.newInstance());
			this.atendimentoDetalheVO.getAtendimentoVO().getArvoreAtendimentoVO().getFormularioVO().setFormularioCampoVOArray(new FormularioCampoVO[0]);

			this.atendimentoDetalheVO.setWFEstadoVOArray(new WFEstadoVO[0]);
			this.atendimentoDetalheVO.setWFSubEstadoVOArray(new WFSubEstadoVO[0]);

		}

		private GestorGerenteContaForm[] gestorGerenteContaForm;

		public GestorGerenteContaForm[] getGestorGerenteContaForm() {
			if (this.gestorGerenteContaForm == null) {
				this.gestorGerenteContaForm = new GestorGerenteContaForm[2];
			}
			return this.gestorGerenteContaForm;
		}

		public void setGestorGerenteContaForm(GestorGerenteContaForm[] arg) {
			this.gestorGerenteContaForm = arg;
		}

		// fila
		private String fila = ConstantesCRM.SVAZIO;

		public String getFila() {
			return this.fila;
		}

		public void setFila(String fila) {
			this.fila = fila;
		}

		// inMenu - indica se a tela de Detalhe exibirá ou não o menu e o botão
		// voltar
		private String inMenu = null;

		public void setInMenu(String inMenu) {
			this.inMenu = inMenu;
		}

		public String getInMenu() {
			return this.inMenu;
		}

		// idAtendimento
		private String idAtendimento = ConstantesCRM.SONE;

		public void setIdAtendimento(String idAtendimento) {
			this.idAtendimento = idAtendimento;
		}

		public String getIdAtendimento() {
			return this.idAtendimento;
		}

		// idAtendimento
		private String inResponsavel = ConstantesCRM.SONE;

		public void setInResponsavel(String inResponsavel) {
			this.inResponsavel = inResponsavel;
		}

		public String getInResponsavel() {
			return this.inResponsavel;
		}

		// motivoSel
		private String motivoSel = "-1"; // -1 é o item em branco default

		public String getMotivoSel() {
			return this.motivoSel;
		}

		public void setMotivoSel(String motivoSel) {
			this.motivoSel = motivoSel;
		}

		// tipoRetornoSel
		private String tipoRetornoSel = "-1"; // -1 é o item em branco default

		public String getTipoRetornoSel() {
			return this.tipoRetornoSel;
		}

		public void setTipoRetornoSel(String tipoRetornoSel) {
			this.tipoRetornoSel = tipoRetornoSel;
		}

		// retornoSel
		private String retornoSel = "-1"; // -1 é o item em branco default

		public String getRetornoSel() {
			return this.retornoSel;
		}

		public void setRetornoSel(String retornoSel) {
			this.retornoSel = retornoSel;
		}

		// estadoSel
		private String estadoSel = ConstantesCRM.SVAZIO;

		public void setEstadoSel(String estadoSel) {
			this.estadoSel = estadoSel;
		}

		public String getEstadoSel() {
			return this.estadoSel;
		}

		// subEstadoSel
		private String subEstadoSel = ConstantesCRM.SVAZIO;

		public void setSubEstadoSel(String subEstadoSel) {
			this.subEstadoSel = subEstadoSel;
		}

		public String getSubEstadoSel() {
			return this.subEstadoSel;
		}

		// contaFormatada
		private String contaFormatada = ConstantesCRM.SVAZIO;

		public void setContaFormatada(String contaFormatada) {
			this.contaFormatada = contaFormatada;
		}

		public String getContaFormatada() {
			return this.contaFormatada;
		}

		// atendimentoDetalheVO
		private AtendimentoDetalheVO atendimentoDetalheVO;

		public void setAtendimentoDetalheVO(AtendimentoDetalheVO atendimentoDetalheVO) {
			this.atendimentoDetalheVO = atendimentoDetalheVO;
		}

		public AtendimentoDetalheVO getAtendimentoDetalheVO() {
			return this.atendimentoDetalheVO;
		}

		// comentarioHistorico
		private String comentarioHistorico;

		public void setComentarioHistorico(String comentarioHistorico) {
			this.comentarioHistorico = comentarioHistorico;
		}

		public String getComentarioHistorico() {
			return this.comentarioHistorico;
		}

		// linhaFormatada
		private String linhaFormatada;

		public void setLinhaFormatada(String linhaFormatada) {
			this.linhaFormatada = linhaFormatada;
		}

		public String getLinhaFormatada() {
			return this.linhaFormatada;
		}

		// scriptPalitagem
		private String scriptPalitagem = ConstantesCRM.SVAZIO;

		public void setScriptPalitagem(String scriptPalitagem) {
			this.scriptPalitagem = scriptPalitagem;
		}

		public String getScriptPalitagem() {
			return this.scriptPalitagem;
		}

		// scriptPalitagemEncerramento
		private String scriptPalitagemEncerramento = ConstantesCRM.SVAZIO;

		public void setScriptPalitagemEncerramento(String scriptPalitagemEncerramento) {
			this.scriptPalitagemEncerramento = scriptPalitagemEncerramento;
		}

		public String getScriptPalitagemEncerramento() {
			return this.scriptPalitagemEncerramento;
		}

		// acao
		private String acaoSel;

		public void setAcaoSel(String acaoSel) {
			this.acaoSel = acaoSel;
		}

		public String getAcaoSel() {
			return this.acaoSel;
		}
	}

	public AtendimentoDetalheForm getAtendimentoDetalheForm() {
		return this.atendimentoDetalheForm;
	}

	/*
	 * private WFEstadoVO getEstadoVO(AtendimentoDetalheForm form) throws Exception { WFEstadoVO wFEstadoVO =
	 * WFEstadoVO.Factory.newInstance(); wFEstadoVO.setIdEstado(form.getEstadoSel()); return wFEstadoVO; }
	 */
	/*
	 * private void inicializaCamposFormatados() { String contaFormatada = ConstantesCRM.SVAZIO; String linhaFormatada = ConstantesCRM.SVAZIO; ContaVO[]
	 * contasVO = this.atendimentoDetalheForm .getAtendimentoDetalheVO().getAtendimentoVO().getContas
	 * ().getContaVOArray(); if(contasVO != null && contasVO.length > 0 && contasVO[0].getNrConta()!=null &&
	 * contasVO[0].getNrDigitoConta()!=null ) { contaFormatada = contasVO[0].getNrConta() + "-" +
	 * contasVO[0].getNrDigitoConta(); LinhaVO[] linhasVO = contasVO[0].getLinhaVOArray(); if(linhasVO != null &&
	 * linhasVO.length > 0) { String area = "000" + linhasVO[0].getCdAreaRegistro(); area = area.substring(area.length()
	 * - 3, area.length()); String fone = "        " + linhasVO[0].getNrLinha(); fone = fone.substring(fone.length() -
	 * 8, fone.length()); linhaFormatada = StringUtilStatic.mask(area + fone, "(###) ####-####"); } }
	 * this.atendimentoDetalheForm.setContaFormatada(contaFormatada);
	 * this.atendimentoDetalheForm.setLinhaFormatada(linhaFormatada); }
	 */

	/*
	 * private void inicializaScriptPalitagem() { String script = ConstantesCRM.SVAZIO; String palitagem =
	 * this.atendimentoDetalheForm.getAtendimentoDetalheVO().getAtendimentoVO
	 * ().getArvoreAtendimentoVO().getDescricaoCompleta().trim(); palitagem = palitagem.startsWith("/") ?
	 * palitagem.replaceFirst("/", ConstantesCRM.SVAZIO).trim() : palitagem; if(palitagem != null) {
	 */
	// String[] itens = palitagem.split("\\s*/\\s*");
	/*
	 * script = "<table width='100%' cellpadding='0' cellspacing='0'>"; for(int i = 0; i < itens.length; i++) { script
	 * += "<tr>"; if(i == 0) { script += "    <td width='20px'><img src='../../resources/images/folderopen.gif'></td>" ;
	 * script += "    <td colspan='" + itens.length + "'>" + itens[i].trim() + "</td>"; } else if(i == itens.length - 1)
	 * { for(int j = 0; i > 1 && j < i - 1; j++) { script += "    <td>&nbsp;</td>"; } script +=
	 * "    <td width='20px'><img src='../../resources/images/joinbottom.gif' border='0'></td>" ; script +=
	 * "    <td width='20px'><img src='../../resources/images/page.gif' border='0'></td>" ; script += "    <td>" +
	 * itens[i].trim() + "</td>"; } else { for(int j = 0; i > 1 && j < i - 1; j++) { script += "    <td>&nbsp;</td>"; }
	 * script += "    <td width='20px'><img src='../../resources/images/joinbottom.gif'></td>" ; script +=
	 * "    <td width='20px'><img src='../../resources/images/folderopen.gif'></td>" ; script += "    <td colspan='" +
	 * itens.length + "'>" + itens[i].trim() + "</td>"; } script += "</tr>"; } script += "</table>"; }
	 * this.atendimentoDetalheForm.setScriptPalitagem(script); }
	 */

	/*
	 * private void inicializaScriptPalitagemEncerramento() { if(this.atendimentoDetalheForm
	 * .getAtendimentoDetalheVO().getAtendimentoVO().getEncerramentoVO() == null) return; String script = ConstantesCRM.SVAZIO; String
	 * palitagem = this.atendimentoDetalheForm .getAtendimentoDetalheVO().getAtendimentoVO().getEncerramentoVO
	 * ().getDsBaixa().trim(); palitagem = palitagem.startsWith("/") ? palitagem.replaceFirst("/", ConstantesCRM.SVAZIO).trim() :
	 * palitagem; if(palitagem != null) {
	 */
	// String[] itens = palitagem.split("\\s*/\\s*");
	/*
	 * script = "<table width='100%' cellpadding='0' cellspacing='0'>"; for(int i = 0; i < itens.length; i++) { script
	 * += "<tr>"; if(i == 0) { script += "    <td width='20px'><img src='../../resources/images/folderopen.gif'></td>" ;
	 * script += "    <td colspan='" + itens.length + "'>" + itens[i].trim() + "</td>"; } else if(i == itens.length - 1)
	 * { for(int j = 0; i > 1 && j < i - 1; j++) { script += "    <td>&nbsp;</td>"; } script +=
	 * "    <td width='20px'><img src='../../resources/images/joinbottom.gif' border='0'></td>" ; script +=
	 * "    <td width='20px'><img src='../../resources/images/page.gif' border='0'></td>" ; script += "    <td>" +
	 * itens[i].trim() + "</td>"; } else { for(int j = 0; i > 1 && j < i - 1; j++) { script += "    <td>&nbsp;</td>"; }
	 * script += "    <td width='20px'><img src='../../resources/images/joinbottom.gif'></td>" ; script +=
	 * "    <td width='20px'><img src='../../resources/images/folderopen.gif'></td>" ; script += "    <td colspan='" +
	 * itens.length + "'>" + itens[i].trim() + "</td>"; } script += "</tr>"; } script += "</table>"; }
	 * this.atendimentoDetalheForm.setScriptPalitagemEncerramento(script); }
	 */

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFAlertaForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private AlertaVO[] alertaVO;

		public void setAlertaVO(AlertaVO[] alertaVO) {
			this.alertaVO = alertaVO;
		}

		public AlertaVO[] getAlertaVO() {
			if (this.alertaVO == null) {
				AlertaVO avo = AlertaVO.Factory.newInstance();
				avo.setDsMensagem("-- --");
				avo.setIdAlerta(-1);
				AlertaVO[] array = new AlertaVO[1];
				array[0] = avo;
				this.alertaVO = array;
			}
			return this.alertaVO;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFAcaoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] WFAcaoVO;

		public void setWFAcaoVO(br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] WFAcaoVO) {
			this.WFAcaoVO = WFAcaoVO;
		}

		public br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] getWFAcaoVO() {
			return this.WFAcaoVO;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFNivelProcesoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private FaseVO[] faseVO;

		public void setFaseVO(FaseVO[] faseVO) {
			this.faseVO = faseVO;
		}

		public FaseVO[] getFaseVO() {
			return this.faseVO;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFLinhasAssocForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private ListaDadosVO listaDadosVO;

		public void setLinhasAssoc(ListaDadosVO listaDadosVO) {
			this.listaDadosVO = listaDadosVO;
		}

		public ListaDadosVO getLinhasAssoc() {
			return this.listaDadosVO;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFPessoaForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private PessoaVO pessoaVO;

		public void setPessoaVO(PessoaVO pessoaVO) {
			this.pessoaVO = pessoaVO;
		}

		public PessoaVO getPessoaVO() {
			return this.pessoaVO;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFContatoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private RDContatoVO rDContato;

		public void setrDContato(RDContatoVO rDContato) {
			this.rDContato = rDContato;
		}

		public RDContatoVO getrDContato() {
			return this.rDContato;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFFormularioForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private FormularioVO formularioVO;

		public void setFormularioVO(FormularioVO formularioVO) {
			this.formularioVO = formularioVO;
		}

		public FormularioVO getFormularioVO() {
			return this.formularioVO;
		}

		private FormularioProcessoVO formularioProcessoVO;

		public void setFormularioProcessoVO(FormularioProcessoVO formularioProcessoVO) {
			this.formularioProcessoVO = formularioProcessoVO;
		}

		public FormularioProcessoVO getFormularioProcessoVO() {
			return this.formularioProcessoVO;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFHistoricoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private AtendimentoHistoricoVO[] historicoVO;
		private String altura = ConstantesCRM.SVAZIO;;
		private WFEstadosVO estadoVO;

		public void setEstadoVO(WFEstadosVO estadoVO) {
			this.estadoVO = estadoVO;
		}

		public WFEstadosVO getEstadoVO() {
			return this.estadoVO;
		}

		public String getAltura() {
			return this.altura;
		}

		public void setAltura(String altura) {
			this.altura = altura;
		}

		public void setHistoricoVO(AtendimentoHistoricoVO[] historicoVO) {
			this.historicoVO = historicoVO;
		}

		public AtendimentoHistoricoVO[] getHistoricoVO() {
			return this.historicoVO;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFDetalheForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String inRC;
		private boolean meuCliente;
		private String[] qtdProcessosPai = new String[0];
		private WFEstadosVO estadoVO;
		private RWFAtendimentoVO atendimentoVO;
		private WFAcaoVO[] acaoVO;

		public void setAcaoVO(WFAcaoVO[] arg) {
			this.acaoVO = arg;
		}

		public WFAcaoVO[] getAcaoVO() {
			return this.acaoVO;
		}

		public void setAtendimentoVO(RWFAtendimentoVO atendimentoVO) {
			this.atendimentoVO = atendimentoVO;
		}

		public RWFAtendimentoVO getAtendimentoVO() {
			return this.atendimentoVO;
		}

		public void setEstadoVO(WFEstadosVO estadoVO) {
			this.estadoVO = estadoVO;
		}

		public WFEstadosVO getEstadoVO() {
			return this.estadoVO;
		}

		// fila
		private String fila = ConstantesCRM.SVAZIO;

		public String getFila() {
			return this.fila;
		}

		public void setFila(String fila) {
			this.fila = fila;
		}

		// CRI
		private String inCRI = ConstantesCRM.SVAZIO;

		public String getInCRI() {
			return this.inCRI;
		}

		public void setInCRI(String inCRI) {
			this.inCRI = inCRI;
		}

		public void setQtdProcessosPai(String qtd) {
			this.qtdProcessosPai = new String[Integer.parseInt(qtd)];
		}

		public String[] getQtdProcessosPai() {
			return this.qtdProcessosPai;
		}

		public void setInRC(String inRC) {
			this.inRC = inRC;
		}

		public String getInRC() {
			return this.inRC;
		}

		public void setMeuCliente(boolean arg) {
			this.meuCliente = arg;
		}

		public boolean isMeuCliente() {
			return this.meuCliente;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RWFEncerramentoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private EncerramentoVO encerramentoVO;

		public void setEncerramentoVO(EncerramentoVO encerramentoVO) {
			this.encerramentoVO = encerramentoVO;
		}

		public EncerramentoVO getEncerramentoVO() {
			return this.encerramentoVO;
		}
	}

}
