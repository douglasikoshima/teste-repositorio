package workflow.AtendimentoWorkflow;

import java.io.BufferedOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import workflow.RegistrarContato.RegistrarContatoController.RegistrarContatoForm;
import br.com.vivo.ctrls.cliente.contaonline.ws.MC.Geral.ErroInfo;
import br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument.AdmGrupoCamposDependentesVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO;
import br.com.vivo.fo.admsistemas.vo.AdmPerguntaVODocument.AdmPerguntaVO;
import br.com.vivo.fo.admsistemas.vo.AdmSatisfacaoContainerVODocument.AdmSatisfacaoContainerVO;
import br.com.vivo.fo.admsistemas.vo.ArvoreAtendimentoVODocument.ArvoreAtendimentoVO;
import br.com.vivo.fo.admsistemas.vo.ArvoreEncerramentoVODocument.ArvoreEncerramentoVO;
import br.com.vivo.fo.admsistemas.vo.EncerramentoVODocument.EncerramentoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoValorVODocument.FormularioCampoValorVO;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO;
import br.com.vivo.fo.admsistemas.vo.impl.AdmPerguntaVODocumentImpl.AdmPerguntaVOImpl;
import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.TipoEnderecoVODocument.TipoEnderecoVO;
import br.com.vivo.fo.commons.utils.EscapeUnescape;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.camposFormulario.CamposFormulario;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacadeImpl;
import br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.AtendimentoWorkflowFacade;
import br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaPortTypeProxy;
import br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfianca;
import br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfiancaTipoConsulta;
import br.com.vivo.fo.ctrls.workflow.parametros.Parametros;
import br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade;
import br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.DadosCliente;
import br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.OrdemVendaSAPPortTypeProxy;
import br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosAtualizarCadastroCliente;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AtdWFVODocument.AtdWFVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowAgendamentoVODocument.AtendimentoWorkflowAgendamentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowComumVODocument.AtendimentoWorkflowComumVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowEncerramentoVODocument.AtendimentoWorkflowEncerramentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowInsistenciaVODocument.AtendimentoWorkflowInsistenciaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowPesquisaVODocument.AtendimentoWorkflowPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoDocVODocument.AtendimentoWorkflowTecnicoDocVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoVODocument.AtendimentoWorkflowTecnicoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTesteVODocument.AtendimentoWorkflowTesteVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesHistVODocument.AtendimentoWorkflowTestesHistVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesQuestVODocument.AtendimentoWorkflowTestesQuestVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesVODocument.AtendimentoWorkflowTestesVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowVODocument.AtendimentoWorkflowVO;
import br.com.vivo.fo.workflow.vo.WFAcaoOrdemVendaVODocument.WFAcaoOrdemVendaVO;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFDocumentoTecnicoPesquisaVODocument.WFDocumentoTecnicoPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;

import com.indracompany.actions.AbstractAction;
import com.sun.org.apache.xerces.internal.util.URI;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class AtendimentoWorkflowController extends AbstractAction {

	private static final long serialVersionUID = -1445902266343397015L;

	private static Logger log = new Logger("workflow");
	private final String PARAMETRO_ENDPOINT_ATUALIZAR_OV_SAP = "PARAMETRO_ENDPOINT_ATUALIZAR_OV_SAP";
	private final String PARAMETRO_ENDPOINT_WEBSERVICE_NFE = "URL_WEBSERVICE_NFE";
	private final String PARAMETRO_TIPO_CONSULTA_WEBSERVICE_NFE = "PARAMETRO_TIPO_CONSULTA_WEBSERVICE_NFE";
	private final String PARAMETRO_ATIVAR_WEBSERVICE_SERASA = "PARAMETRO_ATIVAR_WEBSERVICE_SERASA";

	private HashMap admSatisVOHash = new HashMap();
	private ArrayList admSatisVOArray = new ArrayList();
	private ArrayList admSatisRespVOArray = new ArrayList();

	@EJB
	private RegistrarContatoFacade registrarContatoFacade;

	@EJB
	private CamposFormulario camposFormularioTux;

	@EJB
	private AtendimentoWorkflowFacade atendimentoWorkflowFacade;

	@EJB
	private Parametros parametrosFacade;

	@EJB
	private TelaInicialFacade telaInicialFacadeControl;

	private RegistrarContatoForm registrarContatoForm = new RegistrarContatoForm();
	private AtendimentoForm atendimentoForm = new AtendimentoForm();

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);

		} else if ("atualizarDadosOVBegin".equals(mapping.getParameter())) {
			return atualizarDadosOVBegin(mapping, form, request, response);

		} else if ("confirmaAtualizacaoDados".equals(mapping.getParameter())) {
			return confirmaAtualizacaoDados(mapping, form, request, response);

		} else if ("cancelarOVBegin".equals(mapping.getParameter())) {
			return cancelarOVBegin(mapping, form, request, response);

		} else if ("confirmaCancelamentoOV".equals(mapping.getParameter())) {
			return confirmaCancelamentoOV(mapping, form, request, response);

		} else if ("encaminharBegin".equals(mapping.getParameter())) {
			return encaminharBegin(mapping, form, request, response);

		} else if ("listaEncaminhar".equals(mapping.getParameter())) {
			return listaEncaminhar(mapping, form, request, response);

		} else if ("listaTrocar".equals(mapping.getParameter())) {
			return listaTrocar(mapping, form, request, response);

		} else if ("listaEncaminharGravar".equals(mapping.getParameter())) {
			return listaEncaminharGravar(mapping, form, request, response);

		} else if ("suspeitoBegin".equals(mapping.getParameter())) {
			return suspeitoBegin(mapping, form, request, response);

		} else if ("listaSuspeito".equals(mapping.getParameter())) {
			return listaSuspeito(mapping, form, request, response);

		} else if ("listaSuspeitoGravar".equals(mapping.getParameter())) {
			return listaSuspeitoGravar(mapping, form, request, response);

		} else if ("encerramentoBegin".equals(mapping.getParameter())) {
			return encerramentoBegin(mapping, form, request, response);

		} else if ("getValoresProximoNivel".equals(mapping.getParameter())) {
			return getValoresProximoNivel(mapping, form, request, response);

		} else if ("encerramentoMassaBegin".equals(mapping.getParameter())) {
			return encerramentoMassaBegin(mapping, form, request, response);

		} else if ("listaEncerrar".equals(mapping.getParameter())) {
			return listaEncerrar(mapping, form, request, response);

		} else if ("getDadosAparelhos".equals(mapping.getParameter())) {
			return getDadosAparelhos(mapping, form, request, response);

		} else if ("obtemArvoreBaixaParte".equals(mapping.getParameter())) {
			return obtemArvoreBaixaParte(mapping, form, request, response);

		} else if ("listaEncerrarGravar".equals(mapping.getParameter())) {
			return listaEncerrarGravar(mapping, form, request, response);

		} else if ("listaVoltar".equals(mapping.getParameter())) {
			return listaVoltar(mapping, form, request, response);

		} else if ("listaVoltarGravar".equals(mapping.getParameter())) {
			return listaVoltarGravar(mapping, form, request, response);

		} else if ("obterUsuario".equals(mapping.getParameter())) {
			return obterUsuario(mapping, form, request, response);

		} else if ("fechamentoBegin".equals(mapping.getParameter())) {
			return fechamentoBegin(mapping, form, request, response);

		} else if ("fechamentoGravar".equals(mapping.getParameter())) {
			return fechamentoGravar(mapping, form, request, response);

		} else if ("concluirBegin".equals(mapping.getParameter())) {
			return concluirBegin(mapping, form, request, response);

		} else if ("concluirGravar".equals(mapping.getParameter())) {
			return concluirGravar(mapping, form, request, response);

		} else if ("devolverBegin".equals(mapping.getParameter())) {
			return devolverBegin(mapping, form, request, response);

		} else if ("devolverGravar".equals(mapping.getParameter())) {
			return devolverGravar(mapping, form, request, response);

		} else if ("reaberturaBegin".equals(mapping.getParameter())) {
			return reaberturaBegin(mapping, form, request, response);

		} else if ("reaberturaBeginAbaRel".equals(mapping.getParameter())) {
			return reaberturaBeginAbaRel(mapping, form, request, response);

		} else if ("reaberturaGravar".equals(mapping.getParameter())) {
			return reaberturaGravar(mapping, form, request, response);

		} else if ("reaberturaGravarAbaRel".equals(mapping.getParameter())) {
			return reaberturaGravarAbaRel(mapping, form, request, response);

		} else if ("pausaBegin".equals(mapping.getParameter())) {
			return pausaBegin(mapping, form, request, response);

		} else if ("pausaGravar".equals(mapping.getParameter())) {
			return pausaGravar(mapping, form, request, response);

		} else if ("parametros".equals(mapping.getParameter())) {
			return parametros(mapping, form, request, response);

		} else if ("obterPesquisaFormulario".equals(mapping.getParameter())) {
			return obterPesquisaFormulario(mapping, form, request, response);

		} else if ("parContato".equals(mapping.getParameter())) {
			return parContato(mapping, form, request, response);

		} else if ("cancelarBegin".equals(mapping.getParameter())) {
			return cancelarBegin(mapping, form, request, response);

		} else if ("cancelarGravar".equals(mapping.getParameter())) {
			return cancelarGravar(mapping, form, request, response);

		} else if ("cancelarBeginAbaRel".equals(mapping.getParameter())) {
			return cancelarBeginAbaRel(mapping, form, request, response);

		} else if ("cancelarGravarAbaRel".equals(mapping.getParameter())) {
			return cancelarGravarAbaRel(mapping, form, request, response);

		} else if ("respostaCliente".equals(mapping.getParameter())) {
			return respostaCliente(mapping, form, request, response);

		} else if ("inserirComentario".equals(mapping.getParameter())) {
			return inserirComentario(mapping, form, request, response);

		} else if ("inserirComentarioAbaRel".equals(mapping.getParameter())) {
			return inserirComentarioAbaRel(mapping, form, request, response);

		} else if ("inserirComentarioGravar".equals(mapping.getParameter())) {
			return inserirComentarioGravar(mapping, form, request, response);

		} else if ("inserirComentarioGravarAbaRel".equals(mapping.getParameter())) {
			return inserirComentarioGravarAbaRel(mapping, form, request, response);

		} else if ("respostaClienteInRC".equals(mapping.getParameter())) {
			return respostaClienteInRC(mapping, form, request, response);

		} else if ("insistenciaBegin".equals(mapping.getParameter())) {
			return insistenciaBegin(mapping, form, request, response);

		} else if ("insistenciaGravar".equals(mapping.getParameter())) {
			return insistenciaGravar(mapping, form, request, response);

		} else if ("insistenciaBeginAbaRel".equals(mapping.getParameter())) {
			return insistenciaBeginAbaRel(mapping, form, request, response);

		} else if ("insistenciaGravarAbaRel".equals(mapping.getParameter())) {
			return insistenciaGravarAbaRel(mapping, form, request, response);

		} else if ("documentoAssociadoBegin".equals(mapping.getParameter())) {
			return documentoAssociadoBegin(mapping, form, request, response);

		} else if ("documentoAssociadoPesquisarTodos".equals(mapping.getParameter())) {
			return documentoAssociadoPesquisarTodos(mapping, form, request, response);

		} else if ("documentoAssociadoPesquisarAssociados".equals(mapping.getParameter())) {
			return documentoAssociadoPesquisarAssociados(mapping, form, request, response);

		} else if ("documentoAssociadoAssociar".equals(mapping.getParameter())) {
			return documentoAssociadoAssociar(mapping, form, request, response);

		} else if ("documentoAssociadoAbertura".equals(mapping.getParameter())) {
			return documentoAssociadoAbertura(mapping, form, request, response);

		} else if ("documentoAssociadoFechamento".equals(mapping.getParameter())) {
			return documentoAssociadoFechamento(mapping, form, request, response);

		} else if ("testesBegin".equals(mapping.getParameter())) {
			return testesBegin(mapping, form, request, response);

		} else if ("testesGravar".equals(mapping.getParameter())) {
			return testesGravar(mapping, form, request, response);

		} else if ("pesquisaSatisfacaoBegin".equals(mapping.getParameter())) {
			return pesquisaSatisfacaoBegin(mapping, form, request, response);

		} else if ("pesquisaSatisfacaoApresentacao".equals(mapping.getParameter())) {
			return pesquisaSatisfacaoApresentacao(mapping, form, request, response);

		} else if ("pesquisaSatisfacaoVoltar".equals(mapping.getParameter())) {
			return pesquisaSatisfacaoVoltar(mapping, form, request, response);

		} else if ("pesquisaSatisfacaoGravar".equals(mapping.getParameter())) {
			return pesquisaSatisfacaoGravar(mapping, form, request, response);

		} else if ("atendimentoWorkflowVoltar".equals(mapping.getParameter())) {
			return atendimentoWorkflowVoltar(mapping, form, request, response);

		} else if ("AtendimentoDetalheDone".equals(mapping.getParameter())) {
			return AtendimentoDetalheDone(mapping, form, request, response);

		} else if ("abaDetalhe".equals(mapping.getParameter())) {
			return abaDetalhe(mapping, form, request, response);

		} else if ("retornoAba".equals(mapping.getParameter())) {
			return retornoAba(mapping, form, request, response);

		} else if ("retornoAbaSolicitante".equals(mapping.getParameter())) {
			return retornoAbaSolicitante(mapping, form, request, response);

		} else if ("retornoAbaSolicitanteEx".equals(mapping.getParameter())) {
			return retornoAbaSolicitanteEx(mapping, form, request, response);

		} else if ("abaUser".equals(mapping.getParameter())) {
			return abaUser(mapping, form, request, response);

		} else if ("abaProcesso".equals(mapping.getParameter())) {
			return abaProcesso(mapping, form, request, response);

		} else if ("abaContato".equals(mapping.getParameter())) {
			return abaContato(mapping, form, request, response);

		} else if ("abaUserEx".equals(mapping.getParameter())) {
			return abaUserEx(mapping, form, request, response);

		} else if ("abaProcessoEx".equals(mapping.getParameter())) {
			return abaProcessoEx(mapping, form, request, response);

		} else if ("abaContatoEx".equals(mapping.getParameter())) {
			return abaContatoEx(mapping, form, request, response);

		} else if ("RegistrarContatoDone".equals(mapping.getParameter())) {
			return RegistrarContatoDone(mapping, form, request, response);

		} else if ("encaminharBeginCRI".equals(mapping.getParameter())) {
			return encaminharBeginCRI(mapping, form, request, response);

		} else if ("prosseguirBeginBko".equals(mapping.getParameter())) {
			return prosseguirBeginBko(mapping, form, request, response);

		} else if ("trocarBeginCri".equals(mapping.getParameter())) {
			return trocarBeginCri(mapping, form, request, response);

		} else if ("trocarBeginRc".equals(mapping.getParameter())) {
			return trocarBeginRc(mapping, form, request, response);

		} else if ("adquirirBegin".equals(mapping.getParameter())) {
			return adquirirBegin(mapping, form, request, response);

		} else if ("finalizarBegin".equals(mapping.getParameter())) {
			return finalizarBegin(mapping, form, request, response);

		} else if ("pesquisaFormulario".equals(mapping.getParameter())) {
			return pesquisaFormulario(mapping, form, request, response);

		} else if ("pesquisaSatisfacao".equals(mapping.getParameter())) {
			return pesquisaSatisfacao(mapping, form, request, response);

		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="encaminharBegin" path="encaminharBegin.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoWorkflowController:begin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		String acao = request.getParameter(ConstantesCRM.SACTION);
		log.debug("AtendimentoWorkflowController:begin.do - [acao = " + acao + "]");
		log.debug("AtendimentoWorkflowController:begin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		ActionForward f = null;
		log.debug("AtendimentoWorkflowController:begin.do - Fim do Metodo]");
		f = mapping.findForward(acao);
		return f;
	}

	// ******************************************************************************
	// ********** NFe - Inicio *****************************************************
	// ******************************************************************************

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="atualizarDadosOV.jsp"
	 */
	protected ActionForward atualizarDadosOVBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:atualizarDadosOVBegin.do - Inicio do Metodo]");
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:atualizarDadosOVBegin.do - [user = " + idPessoaUsuario + "]");

		log.debug("AtendimentoWorkflowController:atualizarDadosOVBegin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:atualizarDadosOVBegin.do - [idAtividade = " + form.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowContlistaEncaminharGravarroller:atualizarDadosOVBegin.do - [idMotivo = " + form.getMotivoSel() + "]");
		log.debug("AtendimentoWorkflowController:atualizarDadosOVBegin.do - [comentario = " + form.getComentario() + "]");
		log.debug("AtendimentoWorkflowController:atualizarDadosOVBegin.do - [idAtividade = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:atualizarDadosOVBegin.do - [inCRI = " + form.getInCRI() + "]");

		WFAcaoOrdemVendaVO ov = atendimentoWorkflowFacade.carregarOrdemVendaAtualizar(idPessoaUsuario, form.getIdAtendimento(), form.getIdAtividade());

		form.setWFAcaoOrdemVendaVO(ov);

		this.atendimentoForm = form;
		atendimentoForm.getAtendimentosVO();
		log.debug("AtendimentoWorkflowController:atualizarDadosOVBegin.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	protected String validaSerasa(String cnpj, String ie, String uf, HttpServletRequest request) throws Exception {
		log.debug("AtendimentoWorkflowController:validaSerasa.do - Inicio do Metodo]");

		// 000000 - SUCESSO
		// 150001 - CNPJ e IE <=========== VALIDAR
		// 150002 - CNPJ do destinatário inválido.
		// 150003 - IE do destinatário inválida.

		String retorno = "000000";
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		ParametroVO url = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_WEBSERVICE_NFE);
		ParametroVO tipoConsulta = GetParametro.getInstace().getParametroVO(user, PARAMETRO_TIPO_CONSULTA_WEBSERVICE_NFE);

		log.debug("AtendimentoWorkflowController:validaSerasa.do - End Point: " + url.getDsValorParametro());

		NotaFiscalEletronicaPortTypeProxy proxy = new NotaFiscalEletronicaPortTypeProxy();
		ParametrosConsultarInformacoesConfianca param = new ParametrosConsultarInformacoesConfianca();

		try {
			proxy.setEndpoint(url.getDsValorParametro());

			log.debug("AtendimentoWorkflowController:validaSerasa.do - Inscricao Estadual: " + ie);
			log.debug("AtendimentoWorkflowController:validaSerasa.do - Numero Documento: " + cnpj);
			log.debug("AtendimentoWorkflowController:validaSerasa.do - UF: " + uf);
			log.debug("AtendimentoWorkflowController:validaSerasa.do - Tipo Consulta: " + tipoConsulta.getDsValorParametro());

			param.setInscricaoEstadual(ie);
			param.setNumeroDocumento(cnpj);
			param.setUF(uf);

			if (tipoConsulta.getDsValorParametro().equals(ConstantesCRM.SONE)) {
				param.setTipoConsulta(ParametrosConsultarInformacoesConfiancaTipoConsulta.value1);
			} else if (tipoConsulta.getDsValorParametro().equals(ConstantesCRM.STWO)) {
				param.setTipoConsulta(ParametrosConsultarInformacoesConfiancaTipoConsulta.value2);
			} else if (tipoConsulta.getDsValorParametro().equals(ConstantesCRM.STHREE)) {
				param.setTipoConsulta(ParametrosConsultarInformacoesConfiancaTipoConsulta.value3);
			} else if (tipoConsulta.getDsValorParametro().equals(ConstantesCRM.SFOUR)) {
				param.setTipoConsulta(ParametrosConsultarInformacoesConfiancaTipoConsulta.value4);
			}

			proxy.consultarInformacoesConfianca(param);

		} catch (ErroInfo e) {
			log.error("AtendimentoWorkflowController:validaSerasa.do - Erro ao Validar CNPJ/IE Serasa: " + e.getCodigo() + " - " + e.getDescricao());
			retorno = e.getCodigo();
		}
		log.debug("AtendimentoWorkflowController:validaSerasa.do - Fim do Metodo]");
		return retorno;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 * @jpf:forward name="erro" return-to="currentPage"
	 */
	protected ActionForward confirmaAtualizacaoDados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Inicio do Metodo]");
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFAcaoOrdemVendaVO ov = WFAcaoOrdemVendaVO.Factory.newInstance();

		ov = form.getWFAcaoOrdemVendaVO();

		WFAcaoRetornoVO acao = WFAcaoRetornoVO.Factory.newInstance();

		/*
		 * try { // Parametro responsavel por Ativar(1)/Desativar(0) Validacao Externa SERASA ParametroVO
		 * parametroAtivarWebService = GetParametro.getInstace().getParametroVO(user,
		 * PARAMETRO_ATIVAR_WEBSERVICE_SERASA);
		 *
		 * log.debug(
		 * "AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Validacao Externa[Ativar(1)/Desativar(0)]: " +
		 * parametroAtivarWebService.getDsValorParametro());
		 *
		 * if (parametroAtivarWebService.getDsValorParametro().equals("1")) {
		 *
		 * log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - DOCUMENTO: " +
		 * ov.getDadosOrdemVendaVO().getCLIENTE().getDOCUMENTO());
		 * log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - INSCRICAO ESTADUAL: " +
		 * ov.getDadosOrdemVendaVO().getCLIENTE().getINSCRESTADUAL());
		 * log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - ESTADO CLIENTE: " +
		 * ov.getDadosOrdemVendaVO().getCLIENTE().getESTADOCLI());
		 *
		 * String retornoValidacaoSerasa = validaSerasa(ov.getDadosOrdemVendaVO().getCLIENTE().getDOCUMENTO(),
		 * ov.getDadosOrdemVendaVO().getCLIENTE().getINSCRESTADUAL(),
		 * ov.getDadosOrdemVendaVO().getCLIENTE().getESTADOCLI());
		 *
		 * log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - retornoValidacaoSerasa: " +
		 * retornoValidacaoSerasa);
		 *
		 * if (retornoValidacaoSerasa.equals("150001")) {
		 * log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - CNPJ e IE INVALIDOS");
		 * request.setAttribute("VALIDACAO_SERASA_CNPJ_IE",ConstantesCRM.STRUE); request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("erro"); }else if
		 * (retornoValidacaoSerasa.equals("150002")) {
		 * log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - CNPJ INVALIDO");
		 * request.setAttribute("VALIDACAO_SERASA_CNPJ",ConstantesCRM.STRUE); request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("erro"); } else if
		 * (retornoValidacaoSerasa.equals("150003")) {
		 * log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - IE INVALIDO");
		 * request.setAttribute("VALIDACAO_SERASA_IE",ConstantesCRM.STRUE); request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("erro"); } } } catch
		 * (Exception e) { log.error(
		 * "AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Erro durante tentativa de validacao SERASA: " +
		 * e); }
		 */
		/********************************************************************************************************
		 *********** Não será mais executada a chamada do serviço Tuxedo, somente WebService. *******************
		 ********************************************************************************************************
		 *
		 * if (ov.getDadosOrdemVendaVO().getCLIENTE().getTIPODOC().equals("CPM") ||
		 * ov.getDadosOrdemVendaVO().getCLIENTE().getTIPODOC().equals("CGC")) {
		 * log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Inicio da chamda do Serviço Tuxedo ");
		 * acao = atendimentoWorkflowFacade.atualizarOrdemVenda(idPessoaUsuario, ov, form.getIdAtendimento(),
		 * form.getIdAtividade()); } else {
		 */
		try {
			log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Preenchendo parametros para invocar WebService SAP [atualizarCadastroCliente]");

			log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Parametros NUMEROORDEMVENDA [" + ov.getDadosOrdemVendaVO().getORDEMVENDA() + "] " + " BAIRRO [" + ov.getDadosOrdemVendaVO().getCLIENTE().getBAIRROCLI() + "]" + " CODIGOEMPRESA [VIVO]" + " COMPLEMENTO [" + ov.getDadosOrdemVendaVO().getCLIENTE().getCOMPLCLI() + "]" + " EMAIL [" + ov.getDadosOrdemVendaVO().getCLIENTE().getEMAIL01() + "]" + " MUNICIPIO [" + ov.getDadosOrdemVendaVO().getCLIENTE().getCIDADECLI() + "]"
					+ " NOME [" + ov.getDadosOrdemVendaVO().getCLIENTE().getNOME() + "]" + " CEP [" + ov.getDadosOrdemVendaVO().getCLIENTE().getCEPCLI() + "]" + " NUMERODOCUMENTO [" + ov.getDadosOrdemVendaVO().getCLIENTE().getDOCUMENTO() + "]" + " NUMEROLOGRADOURO [" + ov.getDadosOrdemVendaVO().getCLIENTE().getNUMCLI() + "]" + " RUA [" + ov.getDadosOrdemVendaVO().getCLIENTE().getRUACLI() + "]" + " PAIS [" + ov.getDadosOrdemVendaVO().getCLIENTE().getPAISCLI() + "]" + " UF ["
					+ ov.getDadosOrdemVendaVO().getCLIENTE().getESTADOCLI() + "]" + " TIPODOCUMENTO [" + ov.getDadosOrdemVendaVO().getCLIENTE().getTIPODOC() + "]" + " INSCRICAOMUNICIPAL [" + ov.getDadosOrdemVendaVO().getCLIENTE().getINSCRMUNICIPAL() + "]" + " INSCRICAOESTADUAL [" + ov.getDadosOrdemVendaVO().getCLIENTE().getINSCRESTADUAL() + "]");

			ParametrosAtualizarCadastroCliente parametros = new ParametrosAtualizarCadastroCliente();
			parametros.setNumeroOrdemVenda(ov.getDadosOrdemVendaVO().getORDEMVENDA());
			DadosCliente dadosCliente = new DadosCliente();
			dadosCliente.setBairro(ov.getDadosOrdemVendaVO().getCLIENTE().getBAIRROCLI());
			dadosCliente.setCodigoEmpresa("VIVO");
			dadosCliente.setComplemento(ov.getDadosOrdemVendaVO().getCLIENTE().getCOMPLCLI());
			dadosCliente.setDsEmail(ov.getDadosOrdemVendaVO().getCLIENTE().getEMAIL01());
			dadosCliente.setInscrEstadual(ov.getDadosOrdemVendaVO().getCLIENTE().getINSCRESTADUAL());
			dadosCliente.setInscrMunicipal(ov.getDadosOrdemVendaVO().getCLIENTE().getINSCRMUNICIPAL());
			dadosCliente.setMunicipio(ov.getDadosOrdemVendaVO().getCLIENTE().getCIDADECLI());
			dadosCliente.setNomeCliente(ov.getDadosOrdemVendaVO().getCLIENTE().getNOME());
			dadosCliente.setNumeroCep(ov.getDadosOrdemVendaVO().getCLIENTE().getCEPCLI());
			dadosCliente.setNumeroDocumento(ov.getDadosOrdemVendaVO().getCLIENTE().getDOCUMENTO());
			dadosCliente.setNumeroLogradouro(ov.getDadosOrdemVendaVO().getCLIENTE().getNUMCLI());
			dadosCliente.setRuaLogradouro(ov.getDadosOrdemVendaVO().getCLIENTE().getRUACLI());
			dadosCliente.setSiglaPais(ov.getDadosOrdemVendaVO().getCLIENTE().getPAISCLI());
			dadosCliente.setSiglaUf(ov.getDadosOrdemVendaVO().getCLIENTE().getESTADOCLI());
			dadosCliente.setTipoDocumento(ov.getDadosOrdemVendaVO().getCLIENTE().getTIPODOC());
			parametros.setDadosCliente(dadosCliente);

			OrdemVendaSAPPortTypeProxy proxy = new OrdemVendaSAPPortTypeProxy();

			ParametroVO parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_ATUALIZAR_OV_SAP);

			log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - End Point Recuperado: " + parametro.getDsValorParametro());
			proxy.setEndpoint(parametro.getDsValorParametro());

			proxy.atualizarCadastroCliente(parametros);

			log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Ordem de Venda Atualizada com Sucesso. ");
			log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Inicio da chamda do Serviço Tuxedo - APOS WEBSERVICE SAP ");

			acao = atendimentoWorkflowFacade.atualizarOrdemVenda(idPessoaUsuario, ov, form.getIdAtendimento(), form.getIdAtividade());

		} catch (ErroInfo e) {
			log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Erro]" + e.getDescricao());
			acao.setMensagem("Não foi possível atualizar Ordem de Venda.");
			acao.setAcaoExecucao("N");
			e.printStackTrace();

		} catch (RemoteException e) {
			log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Erro]" + e.getMessage());
			acao.setMensagem("Não foi possível atualizar Ordem de Venda.");
			acao.setAcaoExecucao("N");
			e.printStackTrace();
		}

		log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Retorno:  AcaoExecucao[" + acao.getAcaoExecucao() + "] Mensagem [" + acao.getMensagem() + "] UrlDestino[" + acao.getUrlDestino() + "]");

		atendimentoForm.setWFAcaoRetornoVO(acao);
		atendimentoForm.setFila(form.getFila());

		log.debug("AtendimentoWorkflowController:confirmaAtualizacaoDados.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="encerramentoBegin.do"
	 */
	protected ActionForward cancelarOVBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoWorkflowController:cancelarOVBegin.do - Inicio do Metodo]");
		request.setAttribute("ENCERRAROV", ConstantesCRM.STRUE);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward confirmaCancelamentoOV(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:confirmaCancelamentoOV.do - Inicio do Metodo]");
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		// String idPessoaUsuario, String dsObservacao, String idBaixa, String dsComentario, int idAtendimento, String
		// idAtividade
		log.debug("AtendimentoWorkflowController:confirmaCancelamentoOV.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:confirmaCancelamentoOV.do - [idBaixa = " + form.getIdBaixa() + "]");
		log.debug("AtendimentoWorkflowController:confirmaCancelamentoOV.do - [idBaixaMensagem = " + form.getIdBaixaMensagem() + "]");
		log.debug("AtendimentoWorkflowController:confirmaCancelamentoOV.do - [dsComentario = " + form.getComentario() + "]");
		log.debug("AtendimentoWorkflowController:confirmaCancelamentoOV.do - [dsDocumentoAssociado = " + form.getDocumentoAssociado() + "]");

		WFAcaoRetornoVO acao = atendimentoWorkflowFacade.cancelarOrdemVenda(idPessoaUsuario, form.getComentario(), form.getIdBaixa(), form.getComentario(), form.getIdAtendimento(), form.getIdAtividade());

		atendimentoForm.setWFAcaoRetornoVO(acao);
		atendimentoForm.setFila(form.getFila());

		log.debug("AtendimentoWorkflowController:confirmaCancelamentoOV.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// ******************************************************************************
	// ********** NFe - Final ******************************************************
	// ******************************************************************************

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Encaminhar.jsp"
	 */
	protected ActionForward encaminharBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:encaminharBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:encaminharBegin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;

		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));
		atendimentoForm.getAtendimentosVO();
		log.debug("AtendimentoWorkflowController:encaminharBegin.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="WorkflowListaEncaminhar.jsp"
	 */
	protected ActionForward listaEncaminhar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoWorkflowController:listaEncaminhar.do - Inicio do Metodo]");
		AtendimentoForm form = (AtendimentoForm) request.getSession().getAttribute("atendimentoForm");
		request.getSession().removeAttribute("atendimentoForm");
		String fila = null;
		String idAtividade = null;
		String inCRI = null;
		String inCR = null;
		String idAtendimento = ConstantesCRM.SZERO;
		String idTabelaMotivo = null;
		
		if(form == null) {
			log.debug("form está nulo");
			fila = request.getParameter("fila");
			idAtividade = request.getParameter("idAtividade");
			inCRI = request.getParameter("inCRI");
			inCR = request.getParameter("inCR");
		} else {
			log.debug("form não existe");
			fila = form.getFila();
			idAtividade = form.getIdAtividade();
			inCRI = form.getInCRI();
			inCR = form.getInRC();
		}
		
		log.debug("====== DEBUG =========");
		log.debug("fila = " + fila);
		log.debug("idAtividade = " + idAtividade);
		log.debug("inCRI = " + inCRI);
		log.debug("inCR = " + inCR);
		log.debug("idAtendimento = " + idAtendimento);
		log.debug("idTabelaMotivo=" + idTabelaMotivo);
		log.debug("====== DEBUG FIM ======");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		log.debug("AtendimentoWorkflowController:listaEncaminhar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;

		// TO DO colocar valor correto de idTabelaMotivo

		if (ConstantesCRM.SONE.equals(form.getInRC())) {
			atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumInRCVO(idPessoaUsuario, idAtendimento, idAtividade, idTabelaMotivo, inCR));
		} else {
			atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, idAtendimento, idAtividade, idTabelaMotivo, inCRI));
		}
		log.debug("AtendimentoWorkflowController:listaEncaminhar.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="WorkflowListaTrocar.jsp"
	 */
	protected ActionForward listaTrocar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:listaTrocar.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:listaTrocar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		this.atendimentoForm = form;

		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), form.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));

		log.debug("AtendimentoWorkflowController:listaTrocar.do - Fim do Metodo]");

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward listaEncaminharGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:listaEncaminharGravar.do - Inicio do Metodo]");

		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:listaEncaminharGravar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:listaEncaminharGravar.do - [idAtividade = " + form.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:listaEncaminharGravar.do - [idMotivo = " + form.getMotivoSel() + "]");
		log.debug("AtendimentoWorkflowController:listaEncaminharGravar.do - [idGrupo = " + form.getGrupoSel() + "]");
		log.debug("AtendimentoWorkflowController:listaEncaminharGravar.do - [idUsuario = " + form.getUsuarioSel() + "]");
		log.debug("AtendimentoWorkflowController:listaEncaminharGravar.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:listaEncaminharGravar.do - [inCRI = " + form.getInCRI() + "]");
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), form.getGrupoSel(), form.getUsuarioSel(), form.getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().getDsObservacao(), form.getIdAtendimento(), form.getInCRI());

		if ("1".equals(atendimentoForm.getInRC())) {
			atendimentos[0].getWFAcaoVOArray()[0].setInRC(atendimentoForm.getInRC());
		}

		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.listaEncaminharGravar(idPessoaUsuario, atendimentos);
		// //passar tambem a String inRC - O facade chamado passa a ser listaEncaminharGravarInRC

		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);

		atendimentoForm.setFila(form.getFila());
		log.debug("AtendimentoWorkflowController:listaEncaminharGravar.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Suspeito.jsp"
	 */
	protected ActionForward suspeitoBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:suspeitoBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		this.atendimentoForm = form;
		log.debug("AtendimentoWorkflowController:suspeitoBegin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:suspeitoBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:suspeitoBegin.do - [idMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:suspeitoBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:suspeitoBegin.do - [inCRI = " + form.getInCRI() + "]");
		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));

		log.debug("AtendimentoWorkflowController:suspeitoBegin.do - Fim do Metodo]");

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="WorkflowListaSuspeito.jsp"
	 */
	protected ActionForward listaSuspeito(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtendimentoForm form = (AtendimentoForm) request.getSession().getAttribute("atendimentoForm");
		if (form == null) {
			form = (AtendimentoForm) formParam;
		}

		request.getSession().removeAttribute("atendimentoForm");
		log.debug("AtendimentoWorkflowController:listaSuspeito.do - Inicio do Metodo]");
		if (form != null) {
			if (form.getIdAtividade() == null) {
				form.setIdAtividade(request.getParameter("idAtividade"));
				if (form.getIdAtividade() == null) {
					form.setIdAtividade((String)request.getAttribute("idAtividade"));
				}
			}
			
			if (request.getParameter("idAtendimento") != null) {
				form.setIdAtendimento(request.getParameter("idAtendimento"));
			} else if (request.getAttribute("idAtendimento") != null) {
				form.setIdAtendimento(request.getAttribute("idAtendimento").toString());
			}
			
			if (request.getParameter("inCRI") != null) {
				form.setInCRI(request.getParameter("inCRI"));
			} else if (request.getAttribute("inCRI") != null) {
				form.setInCRI((String)request.getAttribute("inCRI"));
			}
		}

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		log.debug("AtendimentoWorkflowController:listaSuspeito.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;
		log.debug("AtendimentoWorkflowController:listaSuspeito.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:listaSuspeito.do - [idAtividade = " + form.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:listaSuspeito.do - [idMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:listaSuspeito.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:listaSuspeito.do - [inCRI = " + form.getInCRI() + "]");
		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), form.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));

		log.debug("AtendimentoWorkflowController:listaSuspeito.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward listaSuspeitoGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:listaSuspeitoGravar.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		log.debug("AtendimentoWorkflowController:listaSuspeitoGravar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:listaSuspeitoGravar.do - [idAtividade = " + form.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:listaSuspeitoGravar.do - [idMotivo = " + form.getMotivoSel() + "]");
		log.debug("AtendimentoWorkflowController:listaSuspeitoGravar.do - [comentario = " + form.getComentario() + "]");
		log.debug("AtendimentoWorkflowController:listaSuspeitoGravar.do - [idAtividade = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:listaSuspeitoGravar.do - [inCRI = " + form.getInCRI() + "]");

		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), null, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());

		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.listaSuspeitoGravar(idPessoaUsuario, atendimentos);

		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);

		atendimentoForm.setFila(form.getFila());

		log.debug("AtendimentoWorkflowController:listaSuspeitoGravar.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="arvoreBaixa.jsp"
	 */
	protected ActionForward encerramentoBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:encerramentoBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:encerramentoBegin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		int qtdeCamposGrupos = 0;

		atendimentoForm = form;

		// obter formulario dinamico
		ArvoreAtendimentoVO arvoreAtendimentoVO = atendimentoWorkflowFacade.obtemFormularioArvoreBaixa(idPessoaUsuario, form.getIdAtendimento(), "0");

		FormularioVO formulario = arvoreAtendimentoVO.getFormularioVO();
		atendimentoForm.arvoreEncerramentoVO = ArvoreEncerramentoVO.Factory.newInstance();
		atendimentoForm.arvoreEncerramentoVO.setFormularioVO(formulario);

		if (arvoreAtendimentoVO.getFormularioVO() != null && arvoreAtendimentoVO.getFormularioVO().getAdmGrupoCamposVOArray() != null) {

			AdmGrupoCamposVO[] grupos = arvoreAtendimentoVO.getFormularioVO().getAdmGrupoCamposVOArray();
			for (int i = 0; i < grupos.length; i++) {
				for (int j = 0; j < grupos[i].getFormularioCampoVOArray().length; j++) {
					if (ConstantesCRM.SZERO.equals(grupos[i].getFormularioCampoVOArray(j).getTipoCampoVO().getNrTamanho())) {
						grupos[i].getFormularioCampoVOArray(j).getTipoCampoVO().setNrTamanho("10");
					}
				}
			}
			atendimentoForm.setAdmGrupoCamposVO(grupos);
			for (int i = 0; i < arvoreAtendimentoVO.getFormularioVO().getAdmGrupoCamposVOArray().length; i++) {
				qtdeCamposGrupos += arvoreAtendimentoVO.getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray().length;
			}
		}

		atendimentoForm.setNumeroCampos(formulario.getFormularioCampoVOArray().length + qtdeCamposGrupos);

		atendimentoForm.setIdAtendimento(form.getIdAtendimento());
		log.debug("AtendimentoWorkflowController:encerramentoBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		atendimentoForm.setIdAtividade(form.getIdAtividade());
		log.debug("AtendimentoWorkflowController:encerramentoBegin.do - [idAtividade = " + form.getIdAtividade() + "]");
		// idiaz 18.11.04
		atendimentoForm.setIdContato((new Integer(arvoreAtendimentoVO.getIdContato())).toString());
		atendimentoForm.setIdTipoComunicacao(arvoreAtendimentoVO.getIdTipoComunicacao());
		atendimentoForm.setDsTipoComunicacao(arvoreAtendimentoVO.getDsTipoComunicacao());
		log.debug("AtendimentoWorkflowController:encerramentoBegin.do - [idContato = " + atendimentoForm.getIdContato() + "]");
		log.debug("AtendimentoWorkflowController:encerramentoBegin.do - [idTipoComunicacao = " + atendimentoForm.getIdTipoComunicacao() + "]");
		log.debug("AtendimentoWorkflowController:encerramentoBegin.do - [dsTipoComunicacao = " + atendimentoForm.getDsTipoComunicacao() + "]");

		// o atendimento tem doc. tecnico
		atendimentoForm.setDocTec(form.getDocTec());
		atendimentoForm.setNotaPesqSatisfa("teste de nested");
		montaValidacao(formulario);

		if ("ENDERECO".equals(formulario.getInFuncionalidade()) || "ENDERECOCOMPLETO".equals(formulario.getInFuncionalidade())) {
			request.getSession().setAttribute("formularioWF", atendimentoForm);

			if ("ENDERECOCOMPLETO".equals(formulario.getInFuncionalidade())) {
				atendimentoForm.setEnderecoVO(arvoreAtendimentoVO.getFormularioVO().getEnderecoVOArray() != null ? arvoreAtendimentoVO.getFormularioVO().getEnderecoVOArray(0) : atendimentoForm.getEnderecoVO());
				atendimentoForm.setSgUF(arvoreAtendimentoVO.getFormularioVO().getEnderecoVOArray(0).getUFVO().getSgUF());
				atendimentoForm.setNmPais(arvoreAtendimentoVO.getFormularioVO().getEnderecoVOArray(0).getPaisVO().getNmPais());
			}

		}
		log.debug("AtendimentoWorkflowController:encerramentoBegin.do - Fim do Metodo]");
		return listaEncerrar(mapping, atendimentoForm, request, response);
	}

	/**
	 * Método utilizado para carregamento dos valores do próximo nível de um determinado grupo de campos dependentes.
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="FormularioDinamico.jsp"
	 */
	protected ActionForward getValoresProximoNivel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		StringBuffer xmlDados = new StringBuffer();
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		request.setCharacterEncoding(ConstantesCRM.SISO);

		try {
			String idResposta = request.getParameter("idResposta");
			String[] dadosGrupo = request.getParameter("dadosGrupo").split("\\;");
			String nmPath = ConstantesCRM.SVAZIO;
			String idGrupo = dadosGrupo[1];
			String idCampo = dadosGrupo[2];
			String nrNivel = dadosGrupo[3];
			if (dadosGrupo.length == 5) {
				nmPath = dadosGrupo[4];
			}
			AdmGrupoCamposDependentesVO dadosCampoDependente = AdmGrupoCamposDependentesVO.Factory.newInstance();
			dadosCampoDependente.setNrNivel(nrNivel);
			dadosCampoDependente.setIdCampoDependente(idCampo);
			nmPath = EscapeUnescape.unescape(nmPath);
			dadosCampoDependente.setNmPath(nmPath);
			dadosCampoDependente.addNewAdmCampoVO().setIdCampo(idResposta);
			AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
			admCamposFormularioVO.setInOperacao("PROXIMONIVEL");
			admCamposFormularioVO.setIdGrupoCampos(idGrupo);
			admCamposFormularioVO.addNewAdmGruposCamposDependentesVO().addNewAdmGrupoCamposDependentesVO().set(dadosCampoDependente.copy());
			admCamposFormularioVO = camposFormularioTux.getCamposDependentes(admCamposFormularioVO, user);
			xmlDados.append(admCamposFormularioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
		} catch (Exception e) {

			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(ConstantesCRM.SVAZIO);

			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} finally {
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="arvoreBaixa.jsp"
	 */
	protected ActionForward encerramentoMassaBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:encerramentoMassaBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:encerramentoMassaBegin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		atendimentoForm = form;

		// obter formulario dinamico
		ArvoreAtendimentoVO arvoreAtendimentoVO = atendimentoWorkflowFacade.obtemFormularioArvoreBaixa(idPessoaUsuario, form.getIdAtendimento(), form.getIdContato());
		log.debug("AtendimentoWorkflowController:encerramentoMassaBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:encerramentoMassaBegin.do - [idContato = " + form.getIdContato() + "]");
		String idTipoContato = arvoreAtendimentoVO.getIdTipoContato();
		if ((idTipoContato != null) && (idTipoContato.equals(ConstantesCRM.STWO))) {
			FormularioVO formulario = arvoreAtendimentoVO.getFormularioVO();
			atendimentoForm.arvoreEncerramentoVO = ArvoreEncerramentoVO.Factory.newInstance();
			atendimentoForm.arvoreEncerramentoVO.setFormularioVO(formulario);
			atendimentoForm.setNumeroCampos(formulario.getFormularioCampoVOArray().length);

			montaValidacao(formulario);
		} else {
			atendimentoForm.setDocTec(0);
			String scriptValidacao = "function validaFormulario(){return true;}";
			registrarContatoForm.setScriptValidacao(scriptValidacao);
		}

		atendimentoForm.setIdContato(form.getIdContato());

		log.debug("AtendimentoWorkflowController:encerramentoMassaBegin.do - Fim do Metodo]");

		return listaEncerrar(mapping, atendimentoForm, request, response);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="arvoreBaixa.jsp"
	 */
	protected ActionForward listaEncerrar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:listaEncerrar.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:listaEncerrar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		atendimentoForm = form;

		if (atendimentoForm.getIdAtendimento().length() < 2) {

			// nao vem de detalhes, entao nao possue um idAtendimento.
			// se vem do Fechamento em Massa, possui array de atendimentoVO em form.

			AtendimentoVO[] atendimentos = new AtendimentoVO[form.getAtendimentosVO().length];

			// for (int i=0; i<form.getAtendimentosVO().length; i++)
			int i = 0;
			do {
				atendimentos[i] = AtendimentoVO.Factory.newInstance();
				atendimentos[i].setIdAtendimento(form.getAtendimentosVO()[i].getIdAtendimento());
				i++;
			} while (i < form.getAtendimentosVO().length);

			atendimentoForm.setAtendimentosVO(atendimentos);

		} else {
			AtendimentoVO[] atendimentos = new AtendimentoVO[1];
			atendimentos[0] = AtendimentoVO.Factory.newInstance();
			atendimentos[0].setIdAtendimento(form.getIdAtendimento());
			log.debug("AtendimentoWorkflowController:listaEncerrar.do - [idAtendimento = " + form.getIdAtendimento() + "]");
			atendimentoForm.setAtendimentosVO(atendimentos);
		}

		if (atendimentoForm.getScriptValidacao() == null) {
			atendimentoForm.setScriptValidacao("function validaFormulario(){ return true; }");
		}

		// idiaz 18.11.04
		if (form.scriptArvoreBaixa == null || (new String(ConstantesCRM.SVAZIO).equals(form.scriptArvoreBaixa))) {

			AdmFolhaBaixaVO admFolhaBaixaVO = atendimentoWorkflowFacade.obtemArvoreBaixaParte(idPessoaUsuario, atendimentoForm.getIdAtendimento(), new String(""), atendimentoForm.getIdContato(), atendimentoForm.getIdTipoComunicacao());
			log.debug("AtendimentoWorkflowController:listaEncerrar.do - [idContato = " + atendimentoForm.getIdContato() + "]");
			log.debug("AtendimentoWorkflowController:listaEncerrar.do - [idTipoComunicacao = " + atendimentoForm.getIdTipoComunicacao() + "]");
			/*
			 * String script ="if (document.getElementById) { var tree = new WebFXTree('" +
			 * strUtil.escapaComillasJS(admFolhaBaixaVO.getNmBaixa()) + "','','classic');\n";
			 */
			StringBuffer script = new StringBuffer("if (document.getElementById) { var tree = new WebFXTree('").append(StringUtilStatic.escapaComillasJS(admFolhaBaixaVO.getNmBaixa())).append("','','classic');\n");

			StringBuffer node = null;
			String dsMensagem = null;
			String idBaixaMensagem = null;
			String idTipoContato = null;
			// String dsTipoContato = null;

			for (int i = 0; i < admFolhaBaixaVO.getAdmFolhaBaixaVOArray().length; i++) {

				// boolean inFolha = false;

				if (admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray() != null && admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray().length > 0) {

					dsMensagem = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getAdmMensagemAvisoVO().getDsMensagemAviso();
					idBaixaMensagem = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getIdBaixaMensagem();
					idTipoContato = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getAdmComunicacaoBaixaVO().getIdTipoComunicacao();
					// dsTipoContato =
					// admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getAdmComunicacaoBaixaVO().getDsTipoComunicacao();

					// inFolha = true;

					// }

					// 18.11.04
					// if (inFolha) {
					node = new StringBuffer("tmpArvore = new WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getNmBaixa())).append("',\"Javascript:mostraDadosArvore('").append(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','").append(idBaixaMensagem).append("','").append(StringUtilStatic.escapaComillasJS2(dsMensagem)).append("','").append(idTipoContato).append("');\");\n");
				} else {
					node = new StringBuffer("tmpArvore = new WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getNmBaixa())).append("',\"Javascript:expandirNo('").append(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');\n");
				}
				script.append(node).append("tree.add(tmpArvore);\n\n");

			}

			script.append("\ndocument.write(tree);}\n\n");

			atendimentoForm.setScriptArvoreBaixa(script.toString());
			log.debug("AtendimentoWorkflowController:listaEncerrar.do - [Script da Arvore de Baixa = " + script.toString() + "]");
		}
		log.debug("AtendimentoWorkflowController:listaEncerrar.do - Fim do Metodo]");
		// strUtil = null;
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="FormularioDinamico.jsp"
	 */
	protected ActionForward getDadosAparelhos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		StringBuffer xmlDados = new StringBuffer();
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			LupaLinhaVO pesquisaLinhaVO = LupaLinhaVO.Factory.newInstance();
			pesquisaLinhaVO = telaInicialFacadeControl.consultaLinhaVOPorIdProcesso(user, request.getParameter("idAtendimento"));
			xmlDados.append(pesquisaLinhaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

		} catch (Exception e) {
			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(ConstantesCRM.SVAZIO);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} finally {
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iframeArvoreBaixa.jsp"
	 */
	protected ActionForward obtemArvoreBaixaParte(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:obtemArvoreBaixaParte.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:obtemArvoreBaixaParte.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		log.debug("AtendimentoWorkflowController:obtemArvoreBaixaParte.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:obtemArvoreBaixaParte.do - [idBaixa = " + form.getIdBaixa() + "]");
		log.debug("AtendimentoWorkflowController:obtemArvoreBaixaParte.do - [idContato = " + form.getIdContato() + "]");
		log.debug("AtendimentoWorkflowController:obtemArvoreBaixaParte.do - [idTipoComunicacao = " + form.getIdTipoComunicacao() + "]");
		AdmFolhaBaixaVO admFolhaBaixaVO = atendimentoWorkflowFacade.obtemArvoreBaixaParte(idPessoaUsuario, form.getIdAtendimento(), form.getIdBaixa(), form.getIdContato(), form.getIdTipoComunicacao());

		// StringBuffer script = null;
		StringBuffer node = null;
		String dsMensagem = ConstantesCRM.SVAZIO;
		String idBaixaMensagem = ConstantesCRM.SVAZIO;
		String idTipoContato = ConstantesCRM.SVAZIO;
		// String dsTipoContato = ConstantesCRM.SVAZIO;

		node = new StringBuffer();
		if (admFolhaBaixaVO.getAdmFolhaBaixaVOArray() != null) {

			for (int i = 0; i < admFolhaBaixaVO.getAdmFolhaBaixaVOArray().length; i++) {

				boolean inFolha = false;

				if (admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray() != null && admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray().length > 0) {

					dsMensagem = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getAdmMensagemAvisoVO().getDsMensagemAviso();
					idBaixaMensagem = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getIdBaixaMensagem();
					idTipoContato = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getAdmComunicacaoBaixaVO().getIdTipoComunicacao();
					// dsTipoContato =
					// admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getAdmComunicacaoBaixaVO().getDsTipoComunicacao();
					inFolha = true;

				}

				if (inFolha) {
					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getNmBaixa())).append("',").append("\"Javascript:mostraDadosArvore('").append(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','").append(idBaixaMensagem).append("','").append(StringUtilStatic.escapaComillasJS2(dsMensagem)).append("','").append(idTipoContato).append("');\");\n");
				} else {
					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getNmBaixa())).append("',").append("\"Javascript:expandirNo('").append(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');\n");
				}

				node.append("parent.tree.getSelected().add(parent.tmpArvore);\n\n");

			}
		}
		node.append("parent.tree.getSelected().setAddEnabled(false);\n\n");
		node.append("parent.tree.getSelected().expand()\n\n");

		atendimentoForm.setScriptArvoreBaixa(node.toString());
		log.debug("AtendimentoWorkflowController:obtemArvoreBaixaParte.do - [Script da Arvore de Baixa = " + node.toString() + "]");
		log.debug("AtendimentoWorkflowController:obtemArvoreBaixaParte.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void montaValidacao(FormularioVO formulario) {

		StringBuffer scriptValidacao = new StringBuffer("function validaFormulario(){");
		int tamanhoFormulario = formulario.getFormularioCampoVOArray().length;
		for (int i = 0; i < tamanhoFormulario; i++) {
			if (formulario.getFormularioCampoVOArray(i).getInObrigatorio().equals(ConstantesCRM.SZERO)) {
				continue;
			}
			if (formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_Text) || formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_TextArea)) {
				scriptValidacao.append("if(document.forms[0].all['valorCampo[").append(i).append("].valor'].value == ''){\n").append("alert('A questão \"").append(formulario.getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("return false;\n}");
			} else if (formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_Select)) {
				if (formulario.getFormularioCampoVOArray(i).getInPesquisa().equals("1")) {
					scriptValidacao.append("if(document.forms[0].all['valorCampo[").append(i).append("].valor'].value == ''){\n").append("alert('A questão \"").append(formulario.getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("return false;\n}");
				} else {
					if (formulario.getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
						scriptValidacao.append("if(document.forms[0].all['valorCampo[").append(i).append("].valor'].selectedIndex == 0){\n").append("alert('A questão \"").append(formulario.getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("return false;\n}");
					}
				}
			} else if (formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_Radio)) {
				if (formulario.getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
					scriptValidacao.append("respondida = false;\n").append("if(document.forms[0].all['valorCampo[").append(i).append("].valor'].length != null){\n").append("for(i=0;i<document.forms[0].all['valorCampo[").append(i).append("].valor'].length;i++){").append("if(document.forms[0].all['valorCampo[").append(i).append("].valor'][i].checked == true){\n").append("respondida = true;\n}\n}\n}\nelse{\n").append("if(document.forms[0].all['valorCampo[").append(i)
					.append("].valor'].checked == true){\n").append("respondida = true;\n}\n}\n").append("if(!respondida){").append("alert('A questão \"").append(formulario.getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("return false;\n}");

				}
			} else if (formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_CheckBox)) {
				if (formulario.getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
					scriptValidacao.append("respondida = false;\n").append("if(document.forms[0].all['valorCampo[").append(i).append("].valorArray'].length != null){\n").append("for(i=0;i<document.forms[0].all['valorCampo[").append(i).append("].valorArray'].length;i++){").append("if(document.forms[0].all['valorCampo[").append(i).append("].valorArray'][i].checked == true){\n").append("respondida = true;\n}\n}\n}\nelse{\n").append("if(document.forms[0].all['valorCampo[").append(i)
					.append("].valorArray'].checked == true){\n").append("respondida = true;\n}\n}\n").append("if(!respondida){").append("alert('A questão \"").append(formulario.getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("return false;\n}");
				}
			}
		}

		// Contador para manutenção da contagem após término de tratamento de campos dinâmicos
		int contadorContinuado = (tamanhoFormulario == 0) ? 0 : tamanhoFormulario - 1;

		// Tratamento da validação de campos de formulários membros de um determinado grupo (AdmGrupoCamposVO)
		for (int j = 0; j < formulario.getAdmGrupoCamposVOArray().length; j++) {

			for (int i = 0; i < formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray().length; i++) {

				if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getInObrigatorio().equals(ConstantesCRM.SZERO)) {
					contadorContinuado++;
					continue;
				}

				if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_Text) || formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_TextArea)) {
					scriptValidacao.append("if(document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'].value == ''){\n").append("alert('A questão \"").append(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("setTimeout(function(){document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'].focus()},300);\n").append("return false;\n}");
					contadorContinuado++;
				} else if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_Select)) {
					if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getInPesquisa().equals(ConstantesCRM.SONE)) {
						scriptValidacao.append("if(document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'].value == ''){\n").append("alert('A questão \"").append(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("setTimeout(function(){document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'].focus()},300);\n").append("return false;\n}");
						contadorContinuado++;
					} else {
						if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
							scriptValidacao.append("if(document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'].selectedIndex == 0){\n").append("alert('A questão \"").append(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("setTimeout(function(){document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'].focus()},300);\n").append("return false;\n}");
							contadorContinuado++;
						}
					}
				} else if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_Radio)) {
					if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
						scriptValidacao.append("respondida = false;\n").append("if(document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'].length != null){\n").append("for(i=0;i<document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'].length;i++){").append("if(document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'][i].checked == true){\n").append("respondida = true;\n}\n}\n}\nelse{\n")
						.append("if(document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valor'].checked == true){\n").append("respondida = true;\n}\n}\n").append("if(!respondida){").append("alert('A questão \"").append(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("return false;\n}");
						contadorContinuado++;

					}
				} else if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo().equals(ConstantesCRM.CT_CheckBox)) {
					if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
						scriptValidacao.append("respondida = false;\n").append("if(document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valorArray'].length != null){\n").append("for(i=0;i<document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valorArray'].length;i++){").append("if(document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valorArray'][i].checked == true){\n").append("respondida = true;\n}\n}\n}\nelse{\n")
						.append("if(document.forms[0].all['valorCampo[").append(contadorContinuado).append("].valorArray'].checked == true){\n").append("respondida = true;\n}\n}\n").append("if(!respondida){").append("alert('A questão \"").append(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo()).append("\" deve ser respondida!');\n").append("return false;\n}");
						contadorContinuado++;
					}
				}
			}
		}

		// Validacao de pesquisa de Endereço
		if ("ENDERECO".equalsIgnoreCase(formulario.getInFuncionalidade()) || "ENDERECOCOMPLETO".equals(formulario.getInFuncionalidade())) {
			scriptValidacao.append("if ($('nrCEP').value!=''){");
			scriptValidacao.append("   if ($('nrEndereco').value==''){alert('Por favor, digite o número do logradouro.');\n").append("setTimeout(function(){$('nrEndereco').focus()},300);\n").append("return false;}\n");
			scriptValidacao.append("}");
		}

		scriptValidacao.append("return true;}");
		atendimentoForm.setScriptValidacao(scriptValidacao.toString());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward listaEncerrarGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [idBaixa = " + form.getIdBaixa() + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [idBaixaMensagem = " + form.getIdBaixaMensagem() + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [dsComentario = " + form.getComentario() + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [dsDocumentoAssociado = " + form.getDocumentoAssociado() + "]");
		this.atendimentoForm.getArvoreEncerramentoVO().setEncerramentoVO(EncerramentoVO.Factory.newInstance());
		this.atendimentoForm.getArvoreEncerramentoVO().getEncerramentoVO().setIdBaixa(form.getIdBaixa());
		this.atendimentoForm.getArvoreEncerramentoVO().getEncerramentoVO().setIdBaixaMensagem(form.getIdBaixaMensagem());
		this.atendimentoForm.getArvoreEncerramentoVO().getEncerramentoVO().setDsComentario(form.getComentario());
		this.atendimentoForm.getArvoreEncerramentoVO().getEncerramentoVO().setDsDocumentoAssociado(form.getDocumentoAssociado());
		form.getAtendimentoWorkflowVO().getAtendimentoWorkflowEncerramentoVO().setArvoreEncerramentoVO(this.atendimentoForm.getArvoreEncerramentoVO());

		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), form.getGrupoSel(), form.getUsuarioSel(), form.getComentario(), form.getIdAtendimento(), form.getInCRI());

		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [idAtividade = " + form.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [idMotivo = " + form.getMotivoSel() + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [idGrupo = " + form.getGrupoSel() + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [idUsuario = " + form.getUsuarioSel() + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [comentario = " + form.getComentario() + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - [inCRI = " + form.getInCRI() + "]");

		atendimentos[0].getAtendimentoWorkflowVO().setAtendimentoWorkflowEncerramentoVO(form.getAtendimentoWorkflowVO().getAtendimentoWorkflowEncerramentoVO());

		if (atendimentoForm.arvoreEncerramentoVO.getFormularioVO() == null) {
			if (request.getSession().getAttribute("formularioWF") != null) {
				atendimentoForm = (AtendimentoForm) request.getSession().getAttribute("formularioWF");
			}
			request.getSession().removeAttribute("formularioWF");
		}

		// Processa formulario dinamico se ele existe
		if (atendimentoForm != null && atendimentoForm.arvoreEncerramentoVO != null && atendimentoForm.arvoreEncerramentoVO.getFormularioVO() != null) {

			FormularioVO formulario = gravaFormulario(form);
			formulario = addAdmGrupoCamposVO(formulario, form, request);

			for (int i = 0; i < atendimentos.length; i++) {
				atendimentos[i].getAtendimentoWorkflowVO().getAtendimentoWorkflowEncerramentoVO().getArvoreEncerramentoVO().setFormularioVO(formulario);
			}
		}

		// Chama serviço para efetuar a gravação dos atendimentos para encerrar
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.listaEncerrarGravar(idPessoaUsuario, atendimentos, form.getInTratamentoUsuario(), form.getFechamentoMassa());
		this.atendimentoForm.setFechamentoMassa(false);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);

		atendimentoForm.setFila(form.getFila());
		log.debug("AtendimentoWorkflowController:listaEncerrarGravar.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private FormularioVO addAdmGrupoCamposVO(FormularioVO formOriginal, AtendimentoForm form, HttpServletRequest request) {

		int tamanhoFormulario = formOriginal.getFormularioCampoVOArray().length;
		int contadorContinuado = tamanhoFormulario;

		// Tratamento dos campos pertencentes a grupos de campos
		FormularioCampoVO[] FormularioCampoGruposArray = new FormularioCampoVO[0];

		int qtdeGrupos = formOriginal.getAdmGrupoCamposVOArray().length;
		int qtdeLimiteCamposDependentes = 20;
		for (int j = 0; j < qtdeGrupos; j++) {

			// Apenas para grupos de campos normais e funcionalidades (Dados de Aparelho).
			// Esta condição inibe a utilização para Grupos de campos dependentes.
			if (!ConstantesCRM.STWO.equals(formOriginal.getAdmGrupoCamposVOArray(j).getIdTipoGrupo())) {

				FormularioCampoGruposArray = formOriginal.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray();
				for (int i = 0; i < FormularioCampoGruposArray.length; i++) {
					// se for CHECKBOX trata como Array de elementos selecionáveis
					if (FormularioCampoGruposArray[i].getTipoCampoVO().getSgLayoutApresentacaoCampo().equalsIgnoreCase(ConstantesCRM.CT_CheckBox)) {
						if (form.getValorCampo()[contadorContinuado].getValorArray() != null) {
							ArrayList list = new ArrayList();
							for (int k = 0; k < form.getValorCampo()[contadorContinuado].valorArray.length; k++) {
								FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
								formularioCampoValorVO.setValor(form.getValorCampo()[contadorContinuado].getValorArray()[k].trim());
								if (FormularioCampoGruposArray[i].getFormularioCampoValorVOArray() != null) {
									for (int l = 0; l < FormularioCampoGruposArray[i].getFormularioCampoValorVOArray().length; l++) {
										if (form.getValorCampo()[contadorContinuado].getValorArray()[k].equals(FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(l).getValor())) {
											formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(l).getIdFormularioCampoValor());
										}
									}
								}
								list.add(formularioCampoValorVO);
							}

							FormularioCampoGruposArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
						}
					} // Senão, efetua tratamento TEXT, TEXTAREA, SELECT e RADIO

					else {
						ArrayList list = new ArrayList();
						FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
						if (form.getValorCampo()[contadorContinuado].getValor() != null) {
							formularioCampoValorVO.setValor(form.getValorCampo()[contadorContinuado].getValor().trim());
							if (FormularioCampoGruposArray[i].getFormularioCampoValorVOArray() != null) {
								for (int k = 0; k < FormularioCampoGruposArray[i].getFormularioCampoValorVOArray().length; k++) {
									if ((FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(k).getValor().equals(form.getValorCampo()[contadorContinuado].getValor())) && (FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor() != null)) {
										formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor());
									}
								}
							}
						} else {
							formularioCampoValorVO.setValor(ConstantesCRM.SVAZIO);
							formularioCampoValorVO.setIdFormularioCampoValor(ConstantesCRM.SVAZIO);
						}
						list.add(formularioCampoValorVO);
						FormularioCampoGruposArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
					}
					contadorContinuado++;
				}
			}

			// Tratamento de grupos de campos dependentes
			else {

				String nmGrupoCamposDependentes = "grupoCamposDependentes";
				String idGrupoCamposDependentes = formOriginal.getAdmGrupoCamposVOArray(j).getIdGrupoCampos();
				String nmParametroRequest = ConstantesCRM.SVAZIO;
				String idCampo = ConstantesCRM.SVAZIO;
				String idResposta = ConstantesCRM.SVAZIO;
				// String nmPath = ConstantesCRM.SVAZIO;

				AdmGrupoCamposVO grupoTratamento = AdmGrupoCamposVO.Factory.newInstance();
				grupoTratamento.setIdTipoGrupo(ConstantesCRM.STWO);
				grupoTratamento.setNmGrupoCampos(formOriginal.getAdmGrupoCamposVOArray(j).getNmGrupoCampos());
				grupoTratamento.setIdGrupoCampos(formOriginal.getAdmGrupoCamposVOArray(j).getIdGrupoCampos());

				for (int i = 1; i <= qtdeLimiteCamposDependentes; i++) {
					nmParametroRequest = nmGrupoCamposDependentes + ";" + idGrupoCamposDependentes + ";" + i;
					if (request.getParameter(nmParametroRequest) != null) {

						String[] valoresCampo = request.getParameter(nmParametroRequest).split("\\|");
						idCampo = valoresCampo[0];
						idResposta = valoresCampo.length == 2 ? valoresCampo[1] : ConstantesCRM.SVAZIO;
						// nmPath = valoresCampo.length == 2 ? valoresCampo[1] : ConstantesCRM.SVAZIO;

						FormularioCampoVO campoDependente = FormularioCampoVO.Factory.newInstance();
						campoDependente.setNrNivel(Integer.toString(i));
						campoDependente.setIdContatoFolhaCampo(Integer.parseInt(form.getIdContato()));
						campoDependente.setIdCampo(Integer.parseInt(idCampo));
						if (!ConstantesCRM.SVAZIO.equals(idCampo) && !ConstantesCRM.SVAZIO.equals(idResposta)) {
							campoDependente.addNewFormularioCampoValorVO().setIdFormularioCampoValor(idResposta);
						}
						grupoTratamento.addNewFormularioCampoVO().set(campoDependente.copy());

					} else {
						break;
					}
				}
				formOriginal.getAdmGrupoCamposVOArray(j).set(grupoTratamento.copy());
			}
		}

		if (form.getEnderecoVO().getNrEndereco() != null && !ConstantesCRM.SVAZIO.equalsIgnoreCase(form.getEnderecoVO().getNrEndereco())) {
			EnderecoVO dadosEndereco = EnderecoVO.Factory.newInstance();
			dadosEndereco.set(form.getEnderecoVO().copy());
			dadosEndereco.addNewUFVO().setSgUF(form.getSgUF());
			dadosEndereco.addNewPaisVO().setNmPais(form.getNmPais());
			if (formOriginal.getEnderecoVOArray() != null && formOriginal.getEnderecoVOArray().length > 0) {
				formOriginal.getEnderecoVOArray(0).set(dadosEndereco.copy());
			} else {
				formOriginal.addNewEnderecoVO().set(dadosEndereco.copy());
			}

		}
		/* Fim de tratamento para grupos de campos de funcionalidades */
		return formOriginal;
	}

	// esse método é responsável em pegar o id da resposta que vem do form quando o usuário responde a uma pergunta
	// e colocar a descrição da resposta no VO. A descrição que é salva no banco
	private FormularioVO gravaFormulario(AtendimentoForm form) {

		FormularioVO formulario = (FormularioVO) atendimentoForm.arvoreEncerramentoVO.getFormularioVO().copy();

		// deixar tag nmCampo = "" para não dar erro.
		for (int i = 0; i < formulario.getFormularioCampoVOArray().length; i++) {
			formulario.getFormularioCampoVOArray(i).setNmCampo(ConstantesCRM.SVAZIO);
		}

		// interage no array de campos, atualizando com o valor preenchido
		FormularioCampoVO[] FormularioCampoArray = formulario.getFormularioCampoVOArray();
		// int contadorContinuado = FormularioCampoArray.length;
		for (int i = 0; i < FormularioCampoArray.length; i++) {
			// se for CHECKBOX trata como Array de elementos selecionáveis
			if (FormularioCampoArray[i].getTipoCampoVO().getSgLayoutApresentacaoCampo().equalsIgnoreCase(ConstantesCRM.CT_CheckBox)) {
				if (form.getValorCampo()[i].getValorArray() != null) {
					ArrayList list = new ArrayList();
					for (int j = 0; j < form.getValorCampo()[i].valorArray.length; j++) {
						FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
						formularioCampoValorVO.setValor(form.getValorCampo()[i].getValorArray()[j].trim());
						if (FormularioCampoArray[i].getFormularioCampoValorVOArray() != null) {
							for (int l = 0; l < FormularioCampoArray[i].getFormularioCampoValorVOArray().length; l++) {
								if (form.getValorCampo()[i].getValorArray()[j].equals(FormularioCampoArray[i].getFormularioCampoValorVOArray(l).getValor())) {
									formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoArray[i].getFormularioCampoValorVOArray(l).getIdFormularioCampoValor());
								}
							}
						}
						list.add(formularioCampoValorVO);
					}

					FormularioCampoArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
				}
			} // Senão, efetua tratamento TEXT, TEXTAREA, SELECT e RADIO

			else {
				ArrayList list = new ArrayList();
				FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
				if (form.getValorCampo()[i].getValor() != null) {
					formularioCampoValorVO.setValor(form.getValorCampo()[i].getValor().trim());
					if (FormularioCampoArray[i].getFormularioCampoValorVOArray() != null)
					{
						for (int k = 0; k < FormularioCampoArray[i].getFormularioCampoValorVOArray().length; k++)
						{
							if ((FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getValor().equals(form.getValorCampo()[i].getValor())) && (FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor() != null)) {
								formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor());
							}
							// if(FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getValor().equals(form.getValorCampo()[i].getValor()))
							// formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor());
						}
					}
				} else {
					formularioCampoValorVO.setValor(ConstantesCRM.SVAZIO);
					formularioCampoValorVO.setIdFormularioCampoValor(ConstantesCRM.SVAZIO);
				}
				list.add(formularioCampoValorVO);

				FormularioCampoArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));

			}
		}

		return formulario;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Voltar.jsp"
	 */
	protected ActionForward listaVoltar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoWorkflowController:listaVoltar.do - Inicio do Metodo]");

		AtendimentoForm form = (AtendimentoForm) formParam;

		if (request.getSession().getAttribute("atendimentoFormFechMassa") != null) {
			form = (AtendimentoForm) request.getSession().getAttribute("atendimentoFormFechMassa");
			request.getSession().removeAttribute("atendimentoFormFechMassa");
		}

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:listaVoltar.do - [user = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;

		log.debug("AtendimentoWorkflowController:listaVoltar.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:listaVoltar.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:listaVoltar.do - [idMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:listaVoltar.do - [inCRI = " + form.getInCRI() + "]");
		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));

		log.debug("AtendimentoWorkflowController:listaVoltar.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 * @jpf:forward name="success_360" path="RetornoAcao360.jsp"
	 */
	protected ActionForward listaVoltarGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:listaVoltarGravar.do - Inicio do Metodo]");

		String destino = ConstantesCRM.SVAZIO;
		EncerramentoVO encerramentoVO = null;
		if (ConstantesCRM.STRUE.equals(request.getSession().getAttribute("ACESSO_EXTERNO"))) {
			encerramentoVO = EncerramentoVO.Factory.newInstance();
			encerramentoVO.setIdBaixa(ConstantesCRM.SONE);
			encerramentoVO.setDsComentario(form.getComentario());
			destino = "success_360";
		} else {
			destino = ConstantesCRM.SUCCESS;
		}

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		log.debug("AtendimentoWorkflowController:listaVoltarGravar.do - [user = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:listaVoltarGravar.do - [idAtividade = " + form.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:listaVoltarGravar.do - [idMotivo = " + form.getMotivoSel() + "]");
		log.debug("AtendimentoWorkflowController:listaVoltarGravar.do - [comentario = " + form.getComentario() + "]");
		log.debug("AtendimentoWorkflowController:listaVoltarGravar.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:listaVoltarGravar.do - [inCRI = " + form.getInCRI() + "]");

		// Obtém um ArrayList dos atendimentos selecionados
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), null, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());

		if (ConstantesCRM.STRUE.equals(request.getSession().getAttribute("ACESSO_EXTERNO"))) {
			atendimentos[0].setEncerramentoVO(encerramentoVO);
		}

		if (request.getParameter("inRC") != null) {
			atendimentos[0].getWFAcaoVOArray(0).setInRC(ConstantesCRM.SONE);
		} else {
			atendimentos[0].getWFAcaoVOArray(0).setInRC(ConstantesCRM.SZERO);
		}

		log.debug("AtendimentoWorkflowController:listaVoltarGravar.do - [inTratamentoUsuario = " + form.getInTratamentoUsuario() + "]");
		// Chama serviço para efetuar a gravação dos atendimentos para encaminhar
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.listaVoltarGravar(idPessoaUsuario, atendimentos, form.getInTratamentoUsuario());
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		atendimentoForm.setFila(form.getFila());

		log.debug("AtendimentoWorkflowController:listaVoltarGravar.do - Fim do Metodo]");

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iUsuario.jsp"
	 */
	protected ActionForward obterUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:obterUsuario.do - Inicio do Metodo]");

		boolean meuCliente = ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("meuCliente")) ? true : false;

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:obterUsuario.do - [user = " + idPessoaUsuario + "]");

		if (form.getGrupoSel().equals("-1")) {
			// Se selecionou o grupo em branco, inicializa o combo de usuarios
			this.atendimentoForm.setUsuarioVIVO(new UsuarioVIVO[0]);
		} else {
			// Obtém os sub-estados para o estado selecionado
			this.atendimentoForm.setUsuarioVIVO(atendimentoWorkflowFacade.obtemUsuarioVIVO(idPessoaUsuario, form.getGrupoSel(), ConstantesCRM.SONE, String.valueOf(getAtendimentoForm().getIdAtendimento()), meuCliente));
		}

		this.atendimentoForm.setGrupoSel(form.getGrupoSel());
		log.debug("AtendimentoWorkflowController:obterUsuario.do - [idGrupo = " + form.getGrupoSel() + "]");

		log.debug("AtendimentoWorkflowController:obterUsuario.do - Fim do Metodo]");

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Fechamento.jsp"
	 */
	protected ActionForward fechamentoBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:fechamentoBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:fechamentoBegin.do - [user = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:fechamentoBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		this.atendimentoForm = form;

		// obter formulario dinamico
		ArvoreAtendimentoVO arvoreAtendimentoVO = atendimentoWorkflowFacade.obtemFormularioArvoreBaixa(idPessoaUsuario, form.getIdAtendimento(), "0");

		FormularioVO formulario = arvoreAtendimentoVO.getFormularioVO();
		atendimentoForm.arvoreEncerramentoVO = ArvoreEncerramentoVO.Factory.newInstance();
		atendimentoForm.arvoreEncerramentoVO.setFormularioVO(formulario);
		atendimentoForm.setNumeroCampos(formulario.getFormularioCampoVOArray().length);

		montaValidacao(formulario);
		if (atendimentoForm.getScriptValidacao() == null) {
			atendimentoForm.setScriptValidacao("function validaFormulario(){ return true; }");
		}
		log.debug("AtendimentoWorkflowController:fechamentoBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:fechamentoBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:fechamentoBegin.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:fechamentoBegin.do - [inCRI = " + form.getInCRI() + "]");
		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));
		atendimentoForm.setIdAtendimento(form.getIdAtendimento());
		log.debug("AtendimentoWorkflowController:fechamentoBegin.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaSatisfacaoBegin.do"
	 * @jpf:forward name="retorno" path="RetornoAcao.jsp"
	 */
	protected ActionForward fechamentoGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:fechamentoGravar.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:fechamentoGravar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		this.atendimentoForm.getArvoreEncerramentoVO().setEncerramentoVO(EncerramentoVO.Factory.newInstance());
		form.getAtendimentoWorkflowVO().getAtendimentoWorkflowEncerramentoVO().setArvoreEncerramentoVO(this.atendimentoForm.getArvoreEncerramentoVO());
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), null, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());
		atendimentos[0].getAtendimentoWorkflowVO().setAtendimentoWorkflowEncerramentoVO(form.getAtendimentoWorkflowVO().getAtendimentoWorkflowEncerramentoVO());
		// Processa formulario dinamico se ele existe
		if (atendimentoForm.arvoreEncerramentoVO.getFormularioVO() != null) {
			FormularioVO formulario = gravaFormulario(form);

			if (form.getEnderecoVO().getNrEndereco() != null && !ConstantesCRM.SVAZIO.equals(form.getEnderecoVO().getNrEndereco())) {
				EnderecoVO dadosEndereco = EnderecoVO.Factory.newInstance();
				dadosEndereco.set(form.getEnderecoVO().copy());
				dadosEndereco.addNewUFVO().setSgUF(form.getSgUF());
				dadosEndereco.addNewPaisVO().setNmPais(form.getNmPais());
				if (formulario.getEnderecoVOArray() == null) {
					formulario.addNewEnderecoVO().set(dadosEndereco.copy());
				} else {
					FormularioVO formEndereco = FormularioVO.Factory.newInstance();
					formEndereco.addNewEnderecoVO();
					formEndereco.getEnderecoVOArray(0).set(dadosEndereco.copy());
					formulario.setEnderecoVOArray(formEndereco.getEnderecoVOArray());
				}
			}

			for (int i = 0; i < atendimentos.length; i++) {
				atendimentos[i].getAtendimentoWorkflowVO().getAtendimentoWorkflowEncerramentoVO().getArvoreEncerramentoVO().setFormularioVO(formulario);
			}
		}
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.fechamentoGravar(idPessoaUsuario, atendimentos);
		atendimentoForm.setFila(form.getFila());
		atendimentoForm.setIdAtendimento(form.getIdAtendimento());
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		String ret = wFAcaoRetornoVO.getAcaoExecucao();
		request.setAttribute("form", form);
		if ((ret != null) && (ret.equals("N"))) {
			log.debug("AtendimentoWorkflowController:fechamentoGravar.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("retorno");
		}
		log.debug("AtendimentoWorkflowController:fechamentoGravar.do - Fim do Metodo (Pesq)]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Concluir.jsp"
	 */
	protected ActionForward concluirBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:concluirBegin.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:concluirBegin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		this.atendimentoForm = form;
		log.debug("AtendimentoWorkflowController:concluirBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:concluirBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:concluirBegin.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:concluirBegin.do - [inCRI = " + form.getInCRI() + "]");
		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));
		log.debug("AtendimentoWorkflowController:concluirBegin.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward concluirGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:concluirGravar.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:concluirGravar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		// Obtém um ArrayList dos atendimentos selecionados
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), null, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());

		// chamada do Facade
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.concluirGravar(idPessoaUsuario, atendimentos);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		atendimentoForm.setFila(form.getFila());

		log.debug("AtendimentoWorkflowController:concluirGravar.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="DevolverBKO.jsp"
	 * @jpf:forward name="successMeuCliente360" path="DevolverAnalistaMeuCliente.jsp"
	 */
	protected ActionForward devolverBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:devolverBegin.do - Inicio do Metodo]");

		String destino = ConstantesCRM.SVAZIO;
		if (ConstantesCRM.STRUE.equals(request.getSession().getAttribute("ACESSO_EXTERNO"))) {
			destino = "successMeuCliente360";
		} else {
			destino = ConstantesCRM.SUCCESS;
		}

		log.debug(new StringBuffer("\t").append(this.getClass().getName()).append(":devolverBegin()").toString());

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:devolverBegin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;

		log.debug("AtendimentoWorkflowController:devolverBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:devolverBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:devolverBegin.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:devolverBegin.do - [inCRI = " + form.getInCRI() + "]");

		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));

		log.debug("AtendimentoWorkflowController:devolverBegin.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward devolverGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:devolverGravar.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:devolverGravar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		// id atendimento fake
		atendimentoForm.setAtendimentosVO(new AtendimentoVO[1]);
		atendimentoForm.getAtendimentosVO()[0] = AtendimentoVO.Factory.newInstance();
		atendimentoForm.getAtendimentosVO()[0].setIdAtendimento("361");
		log.debug("AtendimentoWorkflowController:devolverGravar.do - [idAtendimento = 361]");

		configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), null, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());

		// WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.devolverGravar(idPessoaUsuario,atendimentos);
		// atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		atendimentoForm.setWFAcaoRetornoVO(WFAcaoRetornoVO.Factory.newInstance());
		atendimentoForm.setFila(form.getFila());

		log.debug("AtendimentoWorkflowController:devolverGravar.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// **--------------------------------------------------------------------------------------------------------------------**//
	// ** Duplicaçao dos Metodos para que haja diferenciação das chamadas através da Tela Inicial e da Fila do Workflow
	// **//
	// **--------------------------------------------------------------------------------------------------------------------**//

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Reabertura.jsp"
	 */
	protected ActionForward reaberturaBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:reaberturaBegin.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:reaberturaBegin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		String inGrupo = ConstantesCRM.SZERO;
		if ((form.getFila() != null) && (form.getFila().length() > 0)) {
			inGrupo = ConstantesCRM.SONE;
		}
		this.atendimentoForm = form;

		log.debug("AtendimentoWorkflowController:reaberturaBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:reaberturaBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:reaberturaBegin.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:reaberturaBegin.do - [inCRI = " + form.getInCRI() + "]");

		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowReaberturaVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), inGrupo));

		atendimentoForm.setNumGruposAbertura(String.valueOf(atendimentoForm.atendimentoWorkflowVO.getAtendimentoWorkflowComumVO().getWFGrupoVOArray().length));
		request.getSession().setAttribute("cbOperacao", atendimentoForm.getIdAtividade());
		log.debug("AtendimentoWorkflowController:reaberturaBegin.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ReaberturaAbaRel.jsp"
	 */
	protected ActionForward reaberturaBeginAbaRel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:reaberturaBeginAbaRel.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:reaberturaBeginAbaRel.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		String inGrupo = ConstantesCRM.SZERO;
		if ((form.getFila() != null) && (form.getFila().length() > 0)) {
			inGrupo = ConstantesCRM.SONE;
		}
		this.atendimentoForm = form;
		log.debug("AtendimentoWorkflowController:reaberturaBeginAbaRel.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:reaberturaBeginAbaRel.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:reaberturaBeginAbaRel.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:reaberturaBeginAbaRel.do - [inCRI = " + form.getInCRI() + "]");
		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowReaberturaVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), inGrupo));
		atendimentoForm.setNumGruposAbertura(String.valueOf(atendimentoForm.atendimentoWorkflowVO.getAtendimentoWorkflowComumVO().getWFGrupoVOArray().length));
		request.getSession().setAttribute("cbOperacao", atendimentoForm.getIdAtividade());
		log.debug("AtendimentoWorkflowController:reaberturaBeginAbaRel.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward reaberturaGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:reaberturaGravar.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:reaberturaGravar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		atendimentoForm.setFila(form.getFila());
		if ((atendimentoForm.getFila() == null) || (atendimentoForm.getFila().equals(ConstantesCRM.SVAZIO))) {
			parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if (parametros != null) {
				idGrupo = parametros.getIdGrupo();
			}
		} else {
			idGrupo = form.getGrupoSel();
		}
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.reaberturaGravar(idPessoaUsuario, atendimentos);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		request.getSession().setAttribute("cbOperacao", form.getIdAtividade());
		log.debug("AtendimentoWorkflowController:reaberturaGravar.do - Fim do Metodo]");
		request.setAttribute("OPER", "RA");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward reaberturaGravarAbaRel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:reaberturaGravarAbaRel.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:reaberturaGravarAbaRel.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		atendimentoForm.setFila(form.getFila());
		if ((atendimentoForm.getFila() == null) || (atendimentoForm.getFila().equals(ConstantesCRM.SVAZIO))) {
			parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if (parametros != null) {
				idGrupo = parametros.getIdGrupo();
			}
		} else {
			idGrupo = form.getGrupoSel();
		}
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.reaberturaGravarAbaRel(idPessoaUsuario, atendimentos);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		request.getSession().setAttribute("cbOperacao", atendimentoForm.getIdAtividade());
		log.debug("AtendimentoWorkflowController:reaberturaGravarAbaRel.do - Fim do Metodo]");
		request.setAttribute("OPER", "RA");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// **--------------------------------------------------------------------------------------------------------------------**//
	// ** Fim de Metodos Duplicados
	// **--------------------------------------------------------------------------------------------------------------------**//

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Pausa.jsp"
	 */
	protected ActionForward pausaBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:pausaBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:pausaBegin.do - [idPessoaUsuario = " + idPessoaUsuario + "]");
		this.atendimentoForm = form;

		log.debug("AtendimentoWorkflowController:pausaBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:pausaBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:pausaBegin.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:pausaBegin.do - [inCRI = " + form.getInCRI() + "]");

		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));

		log.debug("AtendimentoWorkflowController:pausaBegin.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward pausaGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:pausaGravar.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:pausaGravar.do - [idPessoaUsuario = " + idPessoaUsuario + "]");

		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), form.getGrupoSel(), form.getUsuarioSel(), form.getComentario(), form.getIdAtendimento(), form.getInCRI());

		// numero de insistencias VO
		atendimentos[0].getAtendimentoWorkflowVO().setAtendimentoWorkflowAgendamentoVO(form.getAtendimentoWorkflowVO().getAtendimentoWorkflowAgendamentoVO());

		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.pausaGravar(idPessoaUsuario, atendimentos);

		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);

		atendimentoForm.setFila(form.getFila());

		log.debug("AtendimentoWorkflowController:pausaGravar.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/RegistrarContato/RegistrarContatoController.jpf"
	 */
	protected ActionForward parametros(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:parametros.do - Inicio do Metodo]");

		ParametrosVO parametro = null;

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		parametro = parametrosFacade.retornarParametros(idPessoaUsuario, this.atendimentoForm.idAtendimento);
		parametro.setIdFase(ConstantesCRM.SONE);
		if ((form.getFila() != null) && (form.getFila().equals(ConstantesCRM.SVAZIO))) {
			form.setFila(ConstantesCRM.SONE);
			request.setAttribute("fila", ConstantesCRM.SONE);
		}
		request.getSession().setAttribute("parametrosWF", parametro);
		log.debug("AtendimentoWorkflowController:parametros.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iPesquisaFormulario.jsp"
	 */
	protected ActionForward obterPesquisaFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RegistrarContatoForm form = (RegistrarContatoForm) formParam;

		log.debug("AtendimentoWorkflowController:obterPesquisaFormulario.do - Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:obterPesquisaFormulario.do - [user = " + user + "]");

		// if( log.isDebugEnabled() )rf'
		log.debug("RegistrarContatoController:obterPesquisaFormulario()");

		registrarContatoForm = form;
		registrarContatoForm.setFormularioCampoVO(registrarContatoFacade.obtemPesquisaFormulario(user, form.getTextoPesquisa(), form.getIdCampo()));
		log.debug("AtendimentoWorkflowController:obterPesquisaFormulario.do - Fim do Metodo]");
		request.setAttribute("registrarContatoForm", registrarContatoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/RegistrarContato/beginTeste.do"
	 */
	protected ActionForward parContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:parContato.do - Inicio do Metodo]");
		ParametrosVO parametro = null;

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento   = request.getParameter("idAtendimento");
		log.debug("AtendimentoWorkflowController:parContato.do - [user = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:parContato.do - [idAtendimento = " + idAtendimento + "]");
		parametro = parametrosFacade.retornarParametros(idPessoaUsuario, idAtendimento);
		parametro.setIdAtendimento(idAtendimento);
		parametro.setIdFase(ConstantesCRM.SONE);
		if ((form.getFila() != null) && (form.getFila().equals(ConstantesCRM.SVAZIO))) {
			form.setFila(ConstantesCRM.SONE);
			request.setAttribute("fila", ConstantesCRM.SONE);
		}
		request.getSession().setAttribute("parametrosWF", parametro);
		log.debug("AtendimentoWorkflowController:parContato.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// **--------------------------------------------------------------------------------------------------------------------**//
	// ** Duplicaçao dos Metodos para que haja diferenciação das chamadas através da Tela Inicial e da Fila do Workflow
	// **//
	// **--------------------------------------------------------------------------------------------------------------------**//

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Cancelar.jsp"
	 */
	protected ActionForward cancelarBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:cancelarBegin.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:cancelarBegin.do - [user = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;
		String inFase = ConstantesCRM.SZERO;
		if ((atendimentoForm.getFila() == null) || (atendimentoForm.getFila().equals(ConstantesCRM.SVAZIO))) {
			inFase = ConstantesCRM.SONE;
		}
		log.debug("AtendimentoWorkflowController:cancelarBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:cancelarBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:cancelarBegin.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:cancelarBegin.do - [inFase = " + inFase + "]");
		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumCancelarVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), inFase));
		log.debug("AtendimentoWorkflowController:cancelarBegin.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward cancelarGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:cancelarGravar.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:cancelarGravar.do - [user = " + idPessoaUsuario + "]");
		// ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		String idFase = null;
		atendimentoForm.setFila(form.getFila());
		if ((atendimentoForm.getFila() == null) || (atendimentoForm.getFila().equals(ConstantesCRM.SVAZIO))) {
			idFase = ConstantesCRM.SONE;
		}
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());
		atendimentos[0].getAtendimentoWorkflowVO().setIdAgrupamentoEstadoTpfuturo(form.getIdAgrupamentoEstadoTpfuturo());
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.cancelarGravar(idPessoaUsuario, atendimentos, idFase);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		log.debug("AtendimentoWorkflowController:cancelarGravar.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="CancelarAbaRel.jsp"
	 */
	protected ActionForward cancelarBeginAbaRel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:cancelarBeginAbaRel.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:cancelarBegin.do - [user = " + idPessoaUsuario + "]");
		this.atendimentoForm = form;
		String inFase = ConstantesCRM.SZERO;
		if ((atendimentoForm.getFila() == null) || (atendimentoForm.getFila().equals(ConstantesCRM.SVAZIO))) {
			inFase = ConstantesCRM.SONE;
		}
		log.debug("AtendimentoWorkflowController:cancelarBeginAbaRel.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:cancelarBeginAbaRel.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:cancelarBeginAbaRel.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:cancelarBeginAbaRel.do - [inFase = " + inFase + "]");
		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumCancelarVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), inFase));
		log.debug("AtendimentoWorkflowController:cancelarBeginAbaRel.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward cancelarGravarAbaRel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:cancelarGravarAbaRel.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:cancelarGravarAbaRel.do - [user = " + idPessoaUsuario + "]");
		ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		String idFase = null;
		parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		if (parametros != null) {
			idGrupo = parametros.getIdGrupo();
		}
		atendimentoForm.setFila(form.getFila());
		if ((atendimentoForm.getFila() == null) || (atendimentoForm.getFila().equals(ConstantesCRM.SVAZIO))) {
			idFase = ConstantesCRM.SONE;
		}
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());
		atendimentos[0].getAtendimentoWorkflowVO().setIdAgrupamentoEstadoTpfuturo(form.getIdAgrupamentoEstadoTpfuturo());
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.cancelarGravarAbaRel(idPessoaUsuario, atendimentos, idFase);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		log.debug("AtendimentoWorkflowController:cancelarGravarAbaRel.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// **--------------------------------------------------------------------------------------------------------------------**//
	// ** Fim de Metodos Duplicados
	// **--------------------------------------------------------------------------------------------------------------------**//

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward respostaCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:respostaCliente.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:respostaCliente.do - [user = " + idPessoaUsuario + "]");

		// ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		String idFase = ConstantesCRM.SVAZIO;

		log.debug("AtendimentoWorkflowController:respostaCliente.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().getDsObservacao(), form.getIdAtendimento(), "");

		atendimentos[0].getAtendimentoWorkflowVO().setIdAgrupamentoEstadoTpfuturo(form.getMotivoSel());

		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.respostaCliente(idPessoaUsuario, atendimentos, idFase);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);

		log.debug("AtendimentoWorkflowController:respostaCliente.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// **--------------------------------------------------------------------------------------------------------------------**//
	// ** Duplicaçao dos Metodos para que haja diferenciação das chamadas através da Tela Inicial e da Fila do Workflow
	// **//
	// **--------------------------------------------------------------------------------------------------------------------**//

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="InserirComentario.jsp"
	 */
	protected ActionForward inserirComentario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:inserirComentario.do - Inicio do Metodo]");
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:inserirComentario.do - [user = " + idPessoaUsuario + "]");
		this.atendimentoForm = form;
		atendimentoForm.getAtendimentosVO();
		log.debug("AtendimentoWorkflowController:inserirComentario.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="InserirComentarioAbaRel.jsp"
	 */
	protected ActionForward inserirComentarioAbaRel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoWorkflowController:inserirComentarioAbaRel.do - Inicio do Metodo]");
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:inserirComentarioAbaRel.do - [user = " + idPessoaUsuario + "]");
		this.atendimentoForm = (AtendimentoForm) formParam;
		atendimentoForm.getAtendimentosVO();
		log.debug("AtendimentoWorkflowController:inserirComentarioAbaRel.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward inserirComentarioGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:inserirComentarioGravar.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:inserirComentarioGravar.do - [user = " + idPessoaUsuario + "]");
		// ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		// String idFase = ConstantesCRM.SVAZIO;
		log.debug("AtendimentoWorkflowController:inserirComentarioGravar.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().getDsObservacao(), form.getIdAtendimento(), form.getInCRI());
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.concluirGravar(idPessoaUsuario, atendimentos);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		log.debug("AtendimentoWorkflowController:inserirComentarioGravar.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward inserirComentarioGravarAbaRel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:inserirComentarioGravarAbaRel.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:inserirComentarioGravarAbaRel.do - [user = " + idPessoaUsuario + "]");
		ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		// String idFase = ConstantesCRM.SVAZIO;
		parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		if (parametros != null) {
			idGrupo = parametros.getIdGrupo();
		}
		log.debug("AtendimentoWorkflowController:inserirComentarioGravarAbaRel.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().getDsObservacao(), form.getIdAtendimento(), form.getInCRI());
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.concluirGravarAbaRel(idPessoaUsuario, atendimentos);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		log.debug("AtendimentoWorkflowController:inserirComentarioGravarAbaRel.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// **--------------------------------------------------------------------------------------------------------------------**//
	// ** Fim de Metodos Duplicados
	// **--------------------------------------------------------------------------------------------------------------------**//

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward respostaClienteInRC(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:respostaClienteInRC.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:respostaClienteInRC.do - [user = " + idPessoaUsuario + "]");

		// ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		// String idFase = ConstantesCRM.SVAZIO;

		request.setAttribute("inRespCliente", ConstantesCRM.SONE);
		log.debug("AtendimentoWorkflowController:respostaClienteInRC.do - [idAtendimento = " + form.getIdAtendimento() + "]");

		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().getDsObservacao(), form.getIdAtendimento(), ConstantesCRM.SVAZIO);
		atendimentos[0].getAtendimentoWorkflowVO().setIdAgrupamentoEstadoTpfuturo(form.getMotivoSel());
		atendimentos[0].getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().getWFMotivoVOArray(0).setDsMotivo(form.getDsMotivo());

		if (request.getSession().getAttribute("inOrigemFila") != null && ConstantesCRM.SONE.equals(request.getSession().getAttribute("inOrigemFila").toString())) {
			atendimentos[0].getWFAcaoVOArray(0).setInRC(ConstantesCRM.STWO);
			request.getSession().removeAttribute("inOrigemFila");
		} else {
			atendimentos[0].getWFAcaoVOArray(0).setInRC(ConstantesCRM.SONE);
		}

		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.gravaMotivoComentarioRespCliente(idPessoaUsuario, atendimentos);

		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);

		log.debug("AtendimentoWorkflowController:respostaClienteInRC.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// **--------------------------------------------------------------------------------------------------------------------**//
	// ** Duplicaçao dos Metodos para que haja diferenciação das chamadas através da Tela Inicial e da Fila do Workflow
	// **//
	// **--------------------------------------------------------------------------------------------------------------------**//

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Insistencia.jsp"
	 */
	protected ActionForward insistenciaBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:insistenciaBegin.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		// ((String) request.getSession().getAttribute(ConstantesCRM.USUARIO_IDENTIFICADOR_SESSION));
		this.atendimentoForm = form;
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));
		log.debug("AtendimentoWorkflowController:insistenciaBegin.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward insistenciaGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:insistenciaGravar.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:insistenciaGravar.do - [user = " + idPessoaUsuario + "]");
		// ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		atendimentoForm.setFila(form.getFila());
		log.debug("AtendimentoWorkflowController:insistenciaGravar.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.insistenciaGravar(idPessoaUsuario, atendimentos);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		log.debug("AtendimentoWorkflowController:insistenciaGravar.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="InsistenciaAbaRel.jsp"
	 */
	protected ActionForward insistenciaBeginAbaRel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:insistenciaBeginAbaRel.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		// ((String) request.getSession().getAttribute(ConstantesCRM.USUARIO_IDENTIFICADOR_SESSION));
		this.atendimentoForm = form;
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));
		log.debug("AtendimentoWorkflowController:insistenciaBeginAbaRel.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward insistenciaGravarAbaRel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:insistenciaGravarAbaRel.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:insistenciaGravarAbaRel.do - [user = " + idPessoaUsuario + "]");
		ParametrosVO parametros = null;
		String idGrupo = ConstantesCRM.SVAZIO;
		parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		if (parametros != null) {
			idGrupo = parametros.getIdGrupo();
		}
		atendimentoForm.setFila(form.getFila());
		log.debug("AtendimentoWorkflowController:insistenciaGravarAbaRel.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentoForm.atendimentosVO, form.getIdAtividade(), form.getMotivoSel(), idGrupo, null, form.getComentario(), form.getIdAtendimento(), form.getInCRI());
		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.insistenciaGravarAbaRelacionamento(idPessoaUsuario, atendimentos);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);
		log.debug("AtendimentoWorkflowController:insistenciaGravarAbaRel.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// **--------------------------------------------------------------------------------------------------------------------**//
	// ** Fim de Metodos Duplicados
	// **--------------------------------------------------------------------------------------------------------------------**//

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="DocumentoAssociado.jsp"
	 */
	protected ActionForward documentoAssociadoBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:documentoAssociadoBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		log.debug("AtendimentoWorkflowController:documentoAssociadoBegin.do - [user = " + idPessoaUsuario + "]");

		if (atendimentoForm.getIdAtendimento().length() < 2) {
			atendimentoForm.setIdAtendimento(form.getIdAtendimento());
		}

		// o atendimento tem doc. tecnico
		atendimentoForm.setDocTec(form.getDocTec());

		if (atendimentoForm.getIdAtendimento().length() > 0 && atendimentoForm.getAtendimentosVO().length == 0) {
			// se vem de detalhes, então possue um idAtendimento
			// senão, já existe atendimentoForm.atendimentosVO com os atendimentos selecionados
			AtendimentoVO[] atendimentos = new AtendimentoVO[1];
			atendimentos[0] = AtendimentoVO.Factory.newInstance();
			atendimentos[0].setIdAtendimento(form.getIdAtendimento());
			atendimentoForm.setAtendimentosVO(atendimentos);
		}

		AtendimentoWorkflowTecnicoVO atdTecnicoVO = atendimentoForm.getAtendimentoWorkflowVO().getAtendimentoWorkflowTecnicoVO();

		if (atdTecnicoVO == null) {
			atdTecnicoVO = AtendimentoWorkflowTecnicoVO.Factory.newInstance();
		}

		// sempre carregar aba associar vazia
		if (atdTecnicoVO.getAtendimentoWorkflowTecnicoDocVOArray().length > 0) {
			AtendimentoWorkflowTecnicoDocVO[] aux = new AtendimentoWorkflowTecnicoDocVO[0];
			atdTecnicoVO.setAtendimentoWorkflowTecnicoDocVOArray(aux);
		}

		if (atdTecnicoVO.getWFDocumentoTecnicoEstadoVOArray().length == 0) {
			AtendimentoWorkflowTecnicoVO atdWfTecnicoVO = atendimentoWorkflowFacade.obtemTiposEstadosDocTec(idPessoaUsuario);
			atdTecnicoVO.setWFDocumentoTecnicoEstadoVOArray(atdWfTecnicoVO.getWFDocumentoTecnicoEstadoVOArray());
			atdTecnicoVO.setWFDocumentoTecnicoTipoVOArray(atdWfTecnicoVO.getWFDocumentoTecnicoTipoVOArray());
			atdTecnicoVO.setProcedenciaVOArray(atdWfTecnicoVO.getProcedenciaVOArray());
		}

		log.debug("AtendimentoWorkflowController:documentoAssociadoBegin.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.getSession().setAttribute("atendimentoFormDocumento", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="DocumentoAssociadoIframeDocsTodos.jsp"
	 */
	protected ActionForward documentoAssociadoPesquisarTodos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:documentoAssociadoPesquisarTodos.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:documentoAssociadoPesquisarTodos.do - [user = " + idPessoaUsuario + "]");

		AtendimentoWorkflowTecnicoVO atdTecnicoVO = atendimentoForm.getAtendimentoWorkflowVO().getAtendimentoWorkflowTecnicoVO();

		atendimentoForm.setWFDocumentoTecnicoPesquisaVO(form.getWFDocumentoTecnicoPesquisaVO());

		// somente trazer registros se usuário clicou em pesquisar
		if ((request.getParameter("pesq") != null) && (request.getParameter("pesq").equals(ConstantesCRM.SONE))) {
			AtendimentoWorkflowTecnicoDocVO[] atdWfTecnicoDocVO = atendimentoWorkflowFacade.obtemAtendimentoWorkflowTecnicoDocVOArray(idPessoaUsuario, form.getWFDocumentoTecnicoPesquisaVO());

			atdTecnicoVO.setAtendimentoWorkflowTecnicoDocVOArray(atdWfTecnicoDocVO);
		}
		log.debug("AtendimentoWorkflowController:documentoAssociadoPesquisarTodos.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="DocumentoAssociadoIframeDocsAssociados.jsp"
	 */
	protected ActionForward documentoAssociadoPesquisarAssociados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:documentoAssociadoPesquisarAssociados.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:documentoAssociadoPesquisarAssociados.do - [user = " + idPessoaUsuario + "]");

		atendimentoForm.setFiltroAssociados(form.getFiltroAssociados());

		log.debug("AtendimentoWorkflowController:documentoAssociadoPesquisaAssociados.do - [idAtendimento = " + atendimentoForm.getIdAtendimento() + "]");
		form.getFiltroAssociados().setAssociados("" + atendimentoForm.getIdAtendimento());

		AtendimentoWorkflowTecnicoDocVO[] atdWfTecnicoDocVO = atendimentoWorkflowFacade.obtemAtendimentoWorkflowTecnicoDocVOArray(idPessoaUsuario, form.getFiltroAssociados());

		atendimentoForm.setDocumentosAssociados(atdWfTecnicoDocVO);

		log.debug("AtendimentoWorkflowController:documentoAssociadoPesquisarAssociados.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="documentoAssociadoBegin.do"
	 */
	protected ActionForward documentoAssociadoAssociar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:documentoAssociadoAssociar.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:documentoAssociadoAssociar.do - [user = " + idPessoaUsuario + "]");

		AtendimentoWorkflowTecnicoVO atdWfTecnicoVO = AtendimentoWorkflowTecnicoVO.Factory.newInstance();

		for (int i = 0; i < form.getDocumentosId().length; i++) {
			AtendimentoWorkflowTecnicoDocVO atdWfTecnicoDocVO = atdWfTecnicoVO.addNewAtendimentoWorkflowTecnicoDocVO();
			atdWfTecnicoDocVO.setIdDocumentoTecnico(form.getDocumentosId()[i]);
		}

		for (int i = 0; i < atendimentoForm.getAtendimentosVO().length; i++) {
			atendimentoForm.getAtendimentosVO()[i].setAtendimentoWorkflowVO(AtendimentoWorkflowVO.Factory.newInstance());
			atendimentoForm.getAtendimentosVO()[i].getAtendimentoWorkflowVO().setAtendimentoWorkflowTecnicoVO(atdWfTecnicoVO);
		}

		atendimentoForm.setWFAcaoRetornoVO(atendimentoWorkflowFacade.documentoAssociadoAssociar(idPessoaUsuario, atendimentoForm.getAtendimentosVO()));

		log.debug("AtendimentoWorkflowController:documentoAssociadoAssociar.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="DocumentoAssociadoRetono.jsp"
	 */
	protected ActionForward documentoAssociadoAbertura(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:documentoAssociadoAbertura.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:documentoAssociadoAbertura.do - [user = " + idPessoaUsuario + "]");

		form.getAtendimentoWorkflowTecnicoDocVO().setIdProcedencia(ConstantesCRM.SZERO);

		AtendimentoWorkflowTecnicoDocVO[] atdWfTecnicoDocVOArray = new AtendimentoWorkflowTecnicoDocVO[1];
		atdWfTecnicoDocVOArray[0] = form.getAtendimentoWorkflowTecnicoDocVO();

		AtendimentoWorkflowTecnicoVO atdWfTecnicoVO = AtendimentoWorkflowTecnicoVO.Factory.newInstance();
		atdWfTecnicoVO.setAtendimentoWorkflowTecnicoDocVOArray(atdWfTecnicoDocVOArray);

		atendimentoForm.setWFAcaoRetornoVO(atendimentoWorkflowFacade.documentoAssociadoAbertura(idPessoaUsuario, atdWfTecnicoVO));

		log.debug("AtendimentoWorkflowController:documentoAssociadoAbertura.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="DocumentoAssociadoRetono.jsp"
	 */
	protected ActionForward documentoAssociadoFechamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:documentoAssociadoFechamento.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:documentoAssociadoFechamento.do - [user = " + idPessoaUsuario + "]");

		form.getAtendimentoWorkflowTecnicoDocVO().setDtFechamento(new StringBuffer(form.getAtendimentoWorkflowTecnicoDocVO().getDtFechamento()).append(" ").append(form.getHora()).toString());

		AtendimentoWorkflowTecnicoDocVO[] atdWfTecnicoDocVOArray = new AtendimentoWorkflowTecnicoDocVO[1];
		atdWfTecnicoDocVOArray[0] = form.getAtendimentoWorkflowTecnicoDocVO();

		AtendimentoWorkflowTecnicoVO atdWfTecnicoVO = AtendimentoWorkflowTecnicoVO.Factory.newInstance();
		atdWfTecnicoVO.setAtendimentoWorkflowTecnicoDocVOArray(atdWfTecnicoDocVOArray);

		atendimentoForm.setWFAcaoRetornoVO(atendimentoWorkflowFacade.documentoAssociadoFechamento(idPessoaUsuario, atdWfTecnicoVO));

		log.debug("AtendimentoWorkflowController:documentoAssociadoFechamento.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Testes.jsp"
	 */
	protected ActionForward testesBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:testesBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:testesBegin.do - [user = " + idPessoaUsuario + "]");

		atendimentoForm.setAtendimentoWorkflowTestesVO(atendimentoWorkflowFacade.obtemTestes(idPessoaUsuario, String.valueOf(form.getIdAtendimento())));

		int i, j;
		if (atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray() != null && atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray().length > 0) {
			for (i = 0; i < atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray().length; i++) {
				for (j = 0; j < atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray(i).getAtendimentoWorkflowTesteVOArray().length; j++) {
					if (atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray(i).getAtendimentoWorkflowTesteVOArray(j) != null && atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray(i).getAtendimentoWorkflowTesteVOArray().length > 1
							&& atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray(i).getAtendimentoWorkflowTesteVOArray(j).getDsResposta().equals("S")) {
						atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray(i).getAtendimentoWorkflowTesteVOArray(j).setDsResposta(ConstantesCRM.SYES);
					} else if (atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray(i).getAtendimentoWorkflowTesteVOArray(j) != null && atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray(i).getAtendimentoWorkflowTesteVOArray().length > 1
							&& atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray(i).getAtendimentoWorkflowTesteVOArray(j).getDsResposta().equals("N")) {
						atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesHistVO().getAtendimentoWorkflowTestesQuestVOArray(i).getAtendimentoWorkflowTesteVOArray(j).setDsResposta("Não");
					}
				}
			}
		}

		atendimentoForm.setIdAtendimento(form.getIdAtendimento());
		atendimentoForm.setNumeroCampos(atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesQuestVO().getAtendimentoWorkflowTesteVOArray().length);
		form.setNumeroCampos(atendimentoForm.getAtendimentoWorkflowTestesVO().getAtendimentoWorkflowTestesQuestVO().getAtendimentoWorkflowTesteVOArray().length);

		log.debug("AtendimentoWorkflowController:testesBegin.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward testesGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:testesGravar.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:testesGravar.do - [user = " + idPessoaUsuario + "]");

		AtendimentoWorkflowTestesVO atdWFTestes = AtendimentoWorkflowTestesVO.Factory.newInstance();
		AtendimentoWorkflowTestesQuestVO testesVO = AtendimentoWorkflowTestesQuestVO.Factory.newInstance();

		for (int i = 0; i < form.getIdCampoTestes().length; i++) {
			AtendimentoWorkflowTesteVO aux = AtendimentoWorkflowTesteVO.Factory.newInstance();
			aux.setIdTeste(form.getIdCampoTestes()[i]);
			aux.setDsTeste(form.getDsCampoTestes()[i]);
			aux.setDsResposta(form.getValorCampo()[i].getValor());
			testesVO.addNewAtendimentoWorkflowTesteVO();
			testesVO.setAtendimentoWorkflowTesteVOArray(i, aux);
		}

		atdWFTestes.setAtendimentoWorkflowTestesQuestVO(testesVO);
		atdWFTestes.getAtendimentoWorkflowTestesQuestVO().setDsObservacao(form.getObservacaoTestes());
		log.debug("AtendimentoWorkflowController:testesGravar.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		atdWFTestes.getAtendimentoWorkflowTestesQuestVO().setIdAtendimento(String.valueOf(form.getIdAtendimento()));

		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.solucaoTecnicaGravar(idPessoaUsuario, atdWFTestes);
		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);

		log.debug("AtendimentoWorkflowController:testesGravar.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="realizaPesqSatisfa.jsp"
	 * @jpf:forward name="retorno" path="RetornoAcao.jsp"
	 */
	protected ActionForward pesquisaSatisfacaoBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoBegin.do - Inicio do Metodo]");
		AdmSatisfacaoContainerVO admSatisfacaoContainerVO;
		String idPergunta;
		String idAtendimento = ConstantesCRM.SVAZIO;
		int tamanho;
		
		if(form!=null && form.getIdAtendimento().length() < 2) {
		    idAtendimento = String.valueOf(form.getIdAtendimento());
		
		}else {
		    idAtendimento = String.valueOf(atendimentoForm.getIdAtendimento());
		}

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoBegin.do - [user = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");

		admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
		try {
	        admSatisfacaoContainerVO = atendimentoWorkflowFacade.obtemPesquisaSatisfacao(idPessoaUsuario, idAtendimento);

        } catch (Exception e) {
            admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
        }

		tamanho = admSatisfacaoContainerVO.getAdmPerguntaVOArray().length;
		for (int i = 0; i < tamanho; i++) {
			idPergunta = admSatisfacaoContainerVO.getAdmPerguntaVOArray(i).getIdPergunta();
			admSatisVOArray.add(i, idPergunta);
			admSatisVOHash.put(idPergunta, admSatisfacaoContainerVO.getAdmPerguntaVOArray(i));
		}

		form.setArrayIndex(0);
		atendimentoForm.setNumPergunta(-1);
		atendimentoForm.setTamanho(tamanho);

		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoBegin.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);

		if (tamanho > 0) {
			this.atendimentoForm.perguntas = new String[tamanho];
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            request.setAttribute("idAtendimento", String.valueOf(form.getIdAtendimento()));
    		request.setAttribute("tamanho", Integer.toString(tamanho));
            return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		request.setAttribute("idAtendimento", String.valueOf(form.getIdAtendimento()));
		request.setAttribute("tamanho", Integer.toString(tamanho));
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);

        return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	protected ActionForward pesquisaSatisfacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacao.do - Inicio do Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);

        AtendimentoForm form = (AtendimentoForm) formParam;
		atendimentoForm.setIdAtendimento(request.getParameter("idAtendimento"));
		atendimentoForm.setTamanho(Integer.parseInt((String)request.getParameter("tamanho")));

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute("idAtendimento", atendimentoForm.getIdAtendimento());
		request.setAttribute("tamanho", atendimentoForm.getTamanho());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iPesquisaRepresentante.jsp"
	 * @jpf:forward name="fimPesquisa" path="pesquisaRepresentanteFim.jsp"
	 */
	protected ActionForward pesquisaSatisfacaoApresentacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoApresentacao.do - Inicio do Metodo]");
		String idPerguntaAtual;
		// AdmPerguntaVO admPerguntaVO;
		AdmPerguntaVO admPerguntaVOForm;
		int tamanho;
		int numPergunta = atendimentoForm.getNumPergunta();
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoApresentacao.do - [numPergunta = " + atendimentoForm.getNumPergunta() + "]");
		admPerguntaVOForm = atendimentoForm.getAdmPerguntaVO();
		if (admPerguntaVOForm != null) {
			numPergunta++;
			String idPerguntaTratada = admPerguntaVOForm.getIdPergunta();
			atendimentoForm.perguntas[numPergunta] = idPerguntaTratada;
			atendimentoForm.setNumPergunta(numPergunta);
		}

		if (admPerguntaVOForm != null && admPerguntaVOForm.getAdmRespostaVOArray() != null && form.getRespostaPesqSatisfa() != null) {
			tamanho = atendimentoForm.getAdmPerguntaVO().getAdmRespostaVOArray().length;
			request.setAttribute("form", form);
			for (int i = 0; i < tamanho; i++) {
				// verifica se a resposta dada encerra o questionário
				if (admPerguntaVOForm.getAdmRespostaVOArray(i).getIdResposta().equals(form.getRespostaPesqSatisfa()[0]) && admPerguntaVOForm.getAdmRespostaVOArray(i).getInEncerramento().equals("1")) {
					acertaResposta(form);
					log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoApresentacao.do - Fim do Metodo]");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("fimPesquisa");
				}

				// verifica se a resposta dada tem salto e se ele está ativo
				if (admPerguntaVOForm.getAdmRespostaVOArray(i).getIdResposta().equals(form.getRespostaPesqSatisfa()[0]) && admPerguntaVOForm.getAdmRespostaVOArray(i).getAdmSaltoVO() != null && admPerguntaVOForm.getAdmRespostaVOArray(i).getAdmSaltoVO().getAtivo().equals("1")) {
					acertaResposta(form);
					String idPerguntaSalto = admPerguntaVOForm.getAdmRespostaVOArray(i).getAdmSaltoVO().getIdPergunta();
					atendimentoForm.setAdmPerguntaVO((AdmPerguntaVO) admSatisVOHash.get(idPerguntaSalto));
					form.setArrayIndex(admSatisVOArray.indexOf(idPerguntaSalto) + 1);
					log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoApresentacao.do - Fim do Metodo]");

					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			}
		}

		// verifica se a pergunta encerra o questionário
		request.setAttribute("atendimentoForm", form);

		if (atendimentoForm.getAdmPerguntaVO() != null && atendimentoForm.getAdmPerguntaVO().getInEncerramento().equals("1")) {
			acertaResposta(form);
			log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoApresentacao.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("fimPesquisa");
		}

		if (form.getArrayIndex() >= atendimentoForm.getTamanho()) {
			acertaResposta(form);
			log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoApresentacao.do - Fim do Metodo]");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("fimPesquisa");
		}

		acertaResposta(form);
		idPerguntaAtual = (String) admSatisVOArray.get(form.getArrayIndex());
		atendimentoForm.setAdmPerguntaVO((AdmPerguntaVO) admSatisVOHash.get(idPerguntaAtual));
		atendimentoForm.setArrayIndex(form.getArrayIndex() + 1);

		form.setAdmPerguntaVO(atendimentoForm.getAdmPerguntaVO());
		form.setArrayIndex(form.getArrayIndex() + 1);
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoApresentacao.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", form);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iPesquisaRepresentante.jsp"
	 */
	protected ActionForward pesquisaSatisfacaoVoltar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoVoltar.do - Inicio do Metodo]");
		String idPerguntaAtual;
		// AdmPerguntaVO admPerguntaVO;
		AdmPerguntaVO admPerguntaVOForm;
		String vlResposta[];
		int tamanho;
		int i = 0;

		idPerguntaAtual = atendimentoForm.perguntas[atendimentoForm.getNumPergunta()];
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoVoltar.do - [idPerguntaAtual = " + idPerguntaAtual + "]");

		admPerguntaVOForm = (AdmPerguntaVO) admSatisVOHash.get(idPerguntaAtual);
		if (admPerguntaVOForm != null) {
			admSatisRespVOArray.remove(atendimentoForm.getNumPergunta());
			vlResposta = admPerguntaVOForm.getVlRespostaArray();
			if (vlResposta != null) {
				tamanho = admPerguntaVOForm.getVlRespostaArray().length;
				while (i < tamanho) {
					admPerguntaVOForm.removeVlResposta(i);
					tamanho--;
				}
			}
		}

		atendimentoForm.setAdmPerguntaVO((AdmPerguntaVO) admSatisVOHash.get(idPerguntaAtual));
		form.setArrayIndex(admSatisVOArray.indexOf(idPerguntaAtual) + 1);

		int numPergunta = atendimentoForm.getNumPergunta();
		numPergunta--;

		atendimentoForm.setNumPergunta(numPergunta);

		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoVoltar.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	// esse método é responsável em pegar o id da resposta que vem do form quando o usuário responde a uma pergunta
	// e colocar a descrição da resposta no VO. A descrição que é salva no banco
	private void acertaResposta(AtendimentoForm form) {
		AdmPerguntaVO admPerguntaVO;
		int tamanho;
		if (atendimentoForm.getAdmPerguntaVO() != null) {
			admPerguntaVO = atendimentoForm.getAdmPerguntaVO();
			// caso seja um TEXT, TEXTAREA, SELECT, RADIO
			if (atendimentoForm.getAdmPerguntaVO().getAdmRespostaVOArray() == null || atendimentoForm.getAdmPerguntaVO().getAdmRespostaVOArray().length == 0) {
				admPerguntaVO.setVlRespostaArray(form.getRespostaPesqSatisfa());
			} else {
				if (form.getRespostaPesqSatisfa() != null) {
					String[] respostas = new String[form.getRespostaPesqSatisfa().length];
					tamanho = atendimentoForm.getAdmPerguntaVO().getAdmRespostaVOArray().length;
					for (int j = 0; j < respostas.length; j++) {
						for (int i = 0; i < tamanho; i++) {
							if (form.getRespostaPesqSatisfa()[j].equals(atendimentoForm.getAdmPerguntaVO().getAdmRespostaVOArray(i).getIdResposta())) {
								respostas[j] = atendimentoForm.getAdmPerguntaVO().getAdmRespostaVOArray(i).getDsResposta();
								admPerguntaVO.setVlRespostaArray(respostas);
							}
						}
					}
				}
			}
			admSatisRespVOArray.add(admPerguntaVO);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoAcao.jsp"
	 */
	protected ActionForward pesquisaSatisfacaoGravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;

		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoGravar.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoGravar.do - [user = " + idPessoaUsuario + "]");
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoGravar.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
		admSatisfacaoContainerVO.setIdAtendimento(String.valueOf(form.getIdAtendimento()));
		AdmPerguntaVO[] admPerguntaVOArray = new AdmPerguntaVOImpl[admSatisRespVOArray.size()];
		int i = 0;
		Iterator iterator = admSatisRespVOArray.iterator();
		while (iterator.hasNext()) {
			admPerguntaVOArray[i] = (AdmPerguntaVO) iterator.next();
			i++;
		}
		admSatisfacaoContainerVO.setAdmPerguntaVOArray(admPerguntaVOArray);

		admSatisfacaoContainerVO.setVlNota(form.getNotaPesqSatisfa());
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoGravar.do - [notaPesqSatisfacao = " + form.getNotaPesqSatisfa() + "]");

		admSatisfacaoContainerVO.setObservacao(form.getObservacaoPesqSatisfa());
		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoGravar.do - [observacao = " + form.getObservacaoPesqSatisfa() + "]");

		WFAcaoRetornoVO wFAcaoRetornoVO = atendimentoWorkflowFacade.pesquisaSatisfacaoGravar(idPessoaUsuario, admSatisfacaoContainerVO);

		atendimentoForm.setWFAcaoRetornoVO(wFAcaoRetornoVO);

		log.debug("AtendimentoWorkflowController:pesquisaSatisfacaoGravar.do - Fim do Metodo]");

		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-action="AtendimentoWorkflowDone"
	 */
	protected ActionForward atendimentoWorkflowVoltar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AtendimentoWorkflowController:atendimentoWorkflowVoltar.do - Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-action="AtendimentoWorkflowDone"
	 */
	protected ActionForward AtendimentoDetalheDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:atendimentoDetalheDone.do - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward abaDetalhe(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoWorkflowController:abaDetalhe.do - Inicio do Metodo]");
		String action = request.getParameter(ConstantesCRM.SACTION);

		atendimentoForm.setIdAtendimento(request.getParameter("idAtd"));
		log.debug("AtendimentoWorkflowController:abaDetalhe.do - [idAtendimento = " + atendimentoForm.getIdAtendimento() + "]");
		atendimentoForm.setInResponsavel(request.getParameter("inResp"));
		log.debug("AtendimentoWorkflowController:abaDetalhe.do - [inResp = " + atendimentoForm.getInResponsavel() + "]");

		ActionRedirect f = null;
		try {
			f = new ActionRedirect("/workflow/AtendimentoDetalhe/" + action);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		f.addParameter(request.getQueryString(),ConstantesCRM.SVAZIO);
		log.debug("AtendimentoWorkflowController:abaDetalhe.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward retornoAba(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="abaUser.do" name="user"
	 * @jpf:forward path="abaProcesso.do" name="processo"
	 * @jpf:forward path="abaContato.do" name="contato"
	 */
	protected ActionForward retornoAbaSolicitante(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AtendimentoWorkflowController:retornoAbaSolicitante.do - Inicio do Metodo]");
		ActionRedirect f = null;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("".equals(atendimentoForm.getAbaSolicitante())) {
			atendimentoForm.setAbaSolicitante("contato");
			f = new ActionRedirect(mapping.findForward("user"));
			return this.abaUser(mapping, form, request, response);
		} else if ("contato".equals(atendimentoForm.getAbaSolicitante())) {
			atendimentoForm.setAbaSolicitante("processo");
			f = new ActionRedirect(mapping.findForward("contato"));
			return this.abaContato(mapping, form, request, response);
		} else {
			atendimentoForm.setAbaSolicitante(ConstantesCRM.SVAZIO);
			f = new ActionRedirect(mapping.findForward("processo"));
			return this.abaProcesso(mapping, form, request, response);
		}
		// f.addParameter(request.getQueryString(),"");
		// log.debug("AtendimentoWorkflowController:abaSolicitante.do - Fim do Metodo]");

		//return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="abaUserEx.do" name="user"
	 * @jpf:forward path="abaProcessoEx.do" name="processo"
	 * @jpf:forward path="abaContatoEx.do" name="contato"
	 */
	protected ActionForward retornoAbaSolicitanteEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:retornoAbaSolicitante.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		if ("".equals(atendimentoForm.getAbaSolicitante())) {
			atendimentoForm.setAbaSolicitante("contato");
			f = new ActionRedirect(mapping.findForward("user"));

		} else if ("contato".equals(atendimentoForm.getAbaSolicitante())) {
			atendimentoForm.setAbaSolicitante("processo");
			f = new ActionRedirect(mapping.findForward("contato"));

		} else {
			atendimentoForm.setAbaSolicitante(ConstantesCRM.SVAZIO);
			f = new ActionRedirect(mapping.findForward("processo"));
		}
		f.addParameter(request.getQueryString(),ConstantesCRM.SVAZIO);
		log.debug("AtendimentoWorkflowController:abaSolicitante.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="/workflow/AtendimentoDetalhe/RDUser.do" name="success"
	 */
	protected ActionForward abaUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:abaUser.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect("/workflow/AtendimentoDetalhe/RDUser.do");
		f.addParameter("idAtd", String.valueOf(atendimentoForm.getIdAtendimento()));
		f.addParameter("inResp", atendimentoForm.getInResponsavel());

		log.debug("AtendimentoWorkflowController:abaUser.do - [idAtendimento = " + atendimentoForm.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:abaUser.do - [inResp = " + atendimentoForm.getInResponsavel() + "]");
		log.debug("AtendimentoWorkflowController:abaUser.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="/workflow/AtendimentoDetalhe/RDProcesso.do" name="success"
	 */
	protected ActionForward abaProcesso(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:abaProcesso.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect("/workflow/AtendimentoDetalhe/RDProcesso.do");
		f.addParameter("idAtd", String.valueOf(atendimentoForm.getIdAtendimento()));
		f.addParameter("inResp", atendimentoForm.getInResponsavel());

		log.debug("AtendimentoWorkflowController:abaProcesso.do - [idAtendimento = " + atendimentoForm.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:abaProcesso.do - [inResp = " + atendimentoForm.getInResponsavel() + "]");
		log.debug("AtendimentoWorkflowController:abaProcesso.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="/workflow/AtendimentoDetalhe/RDContato.do" name="success"
	 */
	protected ActionForward abaContato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:abaContato.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect("/workflow/AtendimentoDetalhe/RDContato.do");
		f.addParameter("idAtd", String.valueOf(atendimentoForm.getIdAtendimento()));
		f.addParameter("inResp", atendimentoForm.getInResponsavel());

		log.debug("AtendimentoWorkflowController:abaContato.do - [idAtendimento = " + atendimentoForm.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:abaContato.do - [inResp = " + atendimentoForm.getInResponsavel() + "]");
		log.debug("AtendimentoWorkflowController:abaContato.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="/workflow/AtendimentoDetalhe/RDUserEx.do" name="success"
	 */
	protected ActionForward abaUserEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:abaUser.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("idAtd", String.valueOf(atendimentoForm.getIdAtendimento()));
		f.addParameter("inResp", atendimentoForm.getInResponsavel());

		log.debug("AtendimentoWorkflowController:abaUser.do - [idAtendimento = " + atendimentoForm.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:abaUser.do - [inResp = " + atendimentoForm.getInResponsavel() + "]");
		log.debug("AtendimentoWorkflowController:abaUser.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="/workflow/AtendimentoWorkflow/pesquisaFormulario.jsp" name="success"
	 */
	protected ActionForward pesquisaFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:pesquisaFormulario.do - Inicio do Metodo]");
		AtendimentoForm form = (AtendimentoForm) formParam;
		request.setAttribute("atendimentoForm", atendimentoForm);
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="/workflow/AtendimentoDetalhe/RDProcessoEx.do" name="success"
	 */
	protected ActionForward abaProcessoEx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:abaProcesso.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("idAtd", String.valueOf(atendimentoForm.getIdAtendimento()));
		f.addParameter("inResp", atendimentoForm.getInResponsavel());

		log.debug("AtendimentoWorkflowController:abaProcesso.do - [idAtendimento = " + atendimentoForm.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:abaProcesso.do - [inResp = " + atendimentoForm.getInResponsavel() + "]");
		log.debug("AtendimentoWorkflowController:abaProcesso.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="/workflow/AtendimentoDetalhe/RDContatoEx.do" name="success"
	 */
	protected ActionForward abaContatoEx(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:abaContato.do - Inicio do Metodo]");
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("idAtd", String.valueOf(atendimentoForm.getIdAtendimento()));
		f.addParameter("inResp", atendimentoForm.getInResponsavel());

		log.debug("AtendimentoWorkflowController:abaContato.do - [idAtendimento = " + atendimentoForm.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:abaContato.do - [inResp = " + atendimentoForm.getInResponsavel() + "]");
		log.debug("AtendimentoWorkflowController:abaContato.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="atendimentoWorkflowVoltar.do"
	 */
	protected ActionForward RegistrarContatoDone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoWorkflowController:registrarContatoDone.do - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="EncaminharCRI.jsp"
	 */
	protected ActionForward encaminharBeginCRI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:encaminharBeginCRI.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:encaminharBeginCRI.do - [user = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;

		log.debug("AtendimentoWorkflowController:encaminharBeginCRI.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:encaminharBeginCRI.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:encaminharBeginCRI.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:encaminharBeginCRI.do - [inCRI = " + form.getInCRI() + "]");

		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));
		atendimentoForm.getAtendimentosVO();

		log.debug("AtendimentoWorkflowController:encaminharBeginCRI.do - Fim do Metodo]");

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ProsseguirBKO.jsp"
	 */
	protected ActionForward prosseguirBeginBko(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:prosseguirBeginBKO.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:prosseguirBeginBko.do - [user = " + idPessoaUsuario + "]");
		this.atendimentoForm = form;

		log.debug("AtendimentoWorkflowController:prosseguirBeginBko.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:prosseguirBeginBko.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:prosseguirBeginBko.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:prosseguirBeginBko.do - [inCRI = " + form.getInCRI() + "]");

		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));

		atendimentoForm.getAtendimentosVO();

		log.debug("AtendimentoWorkflowController:prosseguirBeginBKO.do - Fim do Metodo]");

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="TrocarCRI.jsp"
	 */
	protected ActionForward trocarBeginCri(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:trocarBeginCri.do - Inicio do Metodo]");
		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:trocarBeginCri.do - [user = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;

		log.debug("AtendimentoWorkflowController:trocarBeginCri.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:trocarBeginCri.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:trocarBeginCri.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:trocarBeginCri.do - [inCRI = " + form.getInCRI() + "]");

		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));
		atendimentoForm.getAtendimentosVO();

		log.debug("AtendimentoWorkflowController:trocarBeginCri.do - Fim do Metodo]");

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="TrocarRC.jsp"
	 */
	protected ActionForward trocarBeginRc(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:trocarBeginRC.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:trocarBeginRC.do - [user = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;
		log.debug("AtendimentoWorkflowController:trocarBeginRC.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:trocarBeginRC.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:trocarBeginRC.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:trocarBeginRC.do - [inCRI = " + form.getInCRI() + "]");
		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumInRCVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInRC()));
		atendimentoForm.getAtendimentosVO();

		log.debug("AtendimentoWorkflowController:trocarBeginRC.do - Fim do Metodo]");
		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AdquirirCRI.jsp"
	 * @jpf:forward name="successRC" path="AdquirirRC.jsp"
	 */
	protected ActionForward adquirirBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:adquirirBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:adquirirBegin.do - [user = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;

		if (request.getParameter("inRC") != null && (request.getParameter("inRC").toString().equals(ConstantesCRM.SZERO) || request.getParameter("inRC").toString().equals("1") || request.getParameter("inRC").toString().equals(ConstantesCRM.STWO))) {
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - [inCRI = " + form.getInCRI() + "]");
			atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumInRCVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInRC()));
			atendimentoForm.getAtendimentosVO();
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - Fim do Metodo]");
			request.setAttribute("atendimentoForm", atendimentoForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("successRC");
		} else {
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - [inCRI = " + form.getInCRI() + "]");
			atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));
			atendimentoForm.getAtendimentosVO();
			log.debug("AtendimentoWorkflowController:adquirirBegin.do - Fim do Metodo]");

			request.setAttribute("atendimentoForm", atendimentoForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="FinalizarCRI.jsp"
	 */
	protected ActionForward finalizarBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoForm form = (AtendimentoForm) formParam;
		log.debug("AtendimentoWorkflowController:finalizarBegin.do - Inicio do Metodo]");

		// busca idPessoaUsuario da sessão
		String idPessoaUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoWorkflowController:finalizarBegin.do - [user = " + idPessoaUsuario + "]");

		this.atendimentoForm = form;

		log.debug("AtendimentoWorkflowController:finalizarBegin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
		log.debug("AtendimentoWorkflowController:finalizarBegin.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		log.debug("AtendimentoWorkflowController:finalizarBegin.do - [idTabelaMotivo = " + atendimentoForm.getIdTabelaMotivo() + "]");
		log.debug("AtendimentoWorkflowController:finalizarBegin.do - [inCRI = " + form.getInCRI() + "]");

		// TO DO colocar valor correto de idTabelaMotivo
		atendimentoForm.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(atendimentoWorkflowFacade.obtemAtendimentoWorkflowComumVO(idPessoaUsuario, form.getIdAtendimento(), atendimentoForm.getIdAtividade(), atendimentoForm.getIdTabelaMotivo(), form.getInCRI()));
		atendimentoForm.getAtendimentosVO();

		log.debug("AtendimentoWorkflowController:finalizarBegin.do - Fim do Metodo]");

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AtendimentoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String idAgrupamentoEstadoTpfuturo;
		private String dsMotivo;
		// private String respostaPadrao;
		private int docTec;
		private String dsTipoComunicacao;
		private String idBaixaMensagem;
		private String scriptValidacao;
		private String idTipoComunicacao;
		private String idContato;
		private String hora;
		private boolean fechamentoMassa;
		private AdmGrupoCamposVO[] admGrupoCamposVO;

		// atributos que vem na URL -> idUsuario=x&idAtividade=y
		// idiaz 18.11.2004
		private String idAtendimento;
		private String idAtividade = ConstantesCRM.SVAZIO;
		private String inCRI = ConstantesCRM.SZERO;
		private String inRC = ConstantesCRM.SZERO;
		private String idTabelaMotivo = ConstantesCRM.SVAZIO;
		private String idProcedencia = ConstantesCRM.SVAZIO;
		private String qtInsistencia = ConstantesCRM.SVAZIO;
		private String numGruposAbertura = ConstantesCRM.SVAZIO;

		private String grupoSel = ConstantesCRM.SVAZIO;
		private String usuarioSel = ConstantesCRM.SVAZIO;
		private String motivoSel = ConstantesCRM.SVAZIO;
		private String comentario = ConstantesCRM.SVAZIO;
		private String scriptArvoreBaixa;
		private String observacaoTestes = ConstantesCRM.SVAZIO;

		private AtendimentoVO[] atendimentosVO;
		private WFGrupoVO[] wFGruposVO;
		private UsuarioVIVO[] usuarioVIVO;
		private WFMotivoVO[] wFMotivosVO;
		private WFAcaoRetornoVO wFAcaoRetornoVO;

		private WFDocumentoTecnicoPesquisaVO wFDocumentoTecnicoPesquisaVO;
		private AtendimentoWorkflowTecnicoDocVO atendimentoWorkflowTecnicoDocVO;
		private AtendimentoWorkflowTecnicoDocVO[] documentosAssociados;
		private WFDocumentoTecnicoPesquisaVO filtroAssociados;
		private String[] documentosId;

		private ArvoreEncerramentoVO arvoreEncerramentoVO;
		private AtendimentoWorkflowVO atendimentoWorkflowVO;
		private AtendimentoWorkflowTestesVO atendimentoWorkflowTestesVO;

		private EnderecoVO enderecoVO;
		private TipoEnderecoVO[] listaTipoEndereco;
		private String sgUF;
		private String nmPais;

		private int numeroCampos = 0;
		private int tamanho;
		private ValorCampo[] valorCampo;

		// armazena o indice do array do questionário da pesquisa de satisfação
		private int arrayIndex;
		private int numPergunta;
		private AdmPerguntaVO admPerguntaVO;
		private String[] respostaPesqSatisfa;
		private String[] perguntas;
		private String notaPesqSatisfa;
		private String observacaoPesqSatisfa;

		private String idBaixa;
		private String documentoAssociado;

		private String fila = ConstantesCRM.SVAZIO;

		private String textoPesquisa = null;
		private String idCampo = null;
		private String selecaoFormulario = null;

		private String[] idCampoTestes;

		// Ordem de Venda - NFe

		private WFAcaoOrdemVendaVO wFAcaoOrdemVendaVO;

		public void setWFAcaoOrdemVendaVO(WFAcaoOrdemVendaVO wFAcaoOrdemVendaVO) {
			this.wFAcaoOrdemVendaVO = wFAcaoOrdemVendaVO;
		}

		public WFAcaoOrdemVendaVO getWFAcaoOrdemVendaVO() {
			return this.wFAcaoOrdemVendaVO;
		}

		public void setIdCampoTestes(String[] idCampoTestes) {
			this.idCampoTestes = idCampoTestes;
		}

		public String[] getIdCampoTestes() {
			return this.idCampoTestes;
		}

		private String[] dsCampoTestes;

		public void setDsCampoTestes(String[] dsCampoTestes) {
			this.dsCampoTestes = dsCampoTestes;
		}

		public String[] getDsCampoTestes() {
			return this.dsCampoTestes;
		}

		public boolean getFechamentoMassa() {
			return this.fechamentoMassa;
		}

		public void setFechamentoMassa(boolean fechamentoMassa) {
			this.fechamentoMassa = fechamentoMassa;
		}

		public AtendimentoForm() {
			// inicicializa objeto atendimentosVO
			this.atendimentosVO = new AtendimentoVO[0];

			// inicicializa objeto arvoreEncerramentoVO
			this.arvoreEncerramentoVO = ArvoreEncerramentoVO.Factory.newInstance();
			this.arvoreEncerramentoVO.setEncerramentoVO(EncerramentoVO.Factory.newInstance());

			WFAcaoOrdemVendaVO wf = WFAcaoOrdemVendaVO.Factory.newInstance();
			wf.addNewDadosOrdemVendaVO();
			wf.getDadosOrdemVendaVO().addNewCLIENTE();
			// wf.getDadosOrdemVendaVO().addNewORDEMCAB();
			// wf.getDadosOrdemVendaVO().addNewORDEMITEM();
			this.wFAcaoOrdemVendaVO = wf;

			// inicializa VO para acoes com Documentos Associados
			this.wFDocumentoTecnicoPesquisaVO = WFDocumentoTecnicoPesquisaVO.Factory.newInstance();
			this.atendimentoWorkflowTecnicoDocVO = AtendimentoWorkflowTecnicoDocVO.Factory.newInstance();
			this.documentosAssociados = new AtendimentoWorkflowTecnicoDocVO[0];
			this.filtroAssociados = WFDocumentoTecnicoPesquisaVO.Factory.newInstance();
			this.filtroAssociados.setIdDocTecEstado(ConstantesCRM.SVAZIO);
			this.filtroAssociados.setComentario(ConstantesCRM.SVAZIO);
			this.filtroAssociados.setDtPeriodo1(ConstantesCRM.SVAZIO);
			this.filtroAssociados.setDtPeriodo2(ConstantesCRM.SVAZIO);
			this.filtroAssociados.setIdDocTecTipo(ConstantesCRM.SVAZIO);
			this.filtroAssociados.setNrDocumentoTecnico(ConstantesCRM.SVAZIO);
			this.filtroAssociados.setAssociados(ConstantesCRM.SVAZIO);

			// inicicializa objeto wFGruposVO
			this.wFGruposVO = new WFGrupoVO[0];

			// inicializa objeto wFMotivosVO;
			this.wFMotivosVO = new WFMotivoVO[0];

			// inicializa objeto wFAcaoRetornoVO
			this.wFAcaoRetornoVO = WFAcaoRetornoVO.Factory.newInstance();

			// inicicializa objeto usuarioVIVO
			this.usuarioVIVO = new UsuarioVIVO[0];

			this.listaTipoEndereco = new TipoEnderecoVO[0];
			this.enderecoVO = EnderecoVO.Factory.newInstance();

			// inicicializa objeto atendimentoWorkflowVO
			this.atendimentoWorkflowVO = AtendimentoWorkflowVO.Factory.newInstance();
			this.atendimentoWorkflowVO.setAtendimentoWorkflowAgendamentoVO(AtendimentoWorkflowAgendamentoVO.Factory.newInstance());
			this.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(AtendimentoWorkflowComumVO.Factory.newInstance());
			this.atendimentoWorkflowVO.setAtendimentoWorkflowEncerramentoVO(AtendimentoWorkflowEncerramentoVO.Factory.newInstance());
			this.atendimentoWorkflowVO.setAtendimentoWorkflowInsistenciaVO(AtendimentoWorkflowInsistenciaVO.Factory.newInstance());
			this.atendimentoWorkflowVO.setAtendimentoWorkflowPesquisaVO(AtendimentoWorkflowPesquisaVO.Factory.newInstance());
			this.atendimentoWorkflowVO.setAtendimentoWorkflowTecnicoVO(AtendimentoWorkflowTecnicoVO.Factory.newInstance());

			this.atendimentoWorkflowTestesVO = AtendimentoWorkflowTestesVO.Factory.newInstance();
			atendimentoWorkflowTestesVO.setAtendimentoWorkflowTestesHistVO(AtendimentoWorkflowTestesHistVO.Factory.newInstance());
			atendimentoWorkflowTestesVO.setAtendimentoWorkflowTestesQuestVO(AtendimentoWorkflowTestesQuestVO.Factory.newInstance());
		}

		public void setEnderecoVO(EnderecoVO enderecoVO) {
			this.enderecoVO = enderecoVO;
		}

		public EnderecoVO getEnderecoVO() {
			if (this.enderecoVO == null) {
				this.enderecoVO = EnderecoVO.Factory.newInstance();
			}
			return this.enderecoVO;
		}

		public void setListaTipoEndereco(TipoEnderecoVO[] listaTipoEndereco) {
			this.listaTipoEndereco = listaTipoEndereco;
		}

		public TipoEnderecoVO[] getListaTipoEndereco() {
			return this.listaTipoEndereco;
		}

		public void setSgUF(String sgUF) {
			this.sgUF = sgUF;
		}

		public void setNmPais(String nmPais) {
			this.nmPais = nmPais;
		}

		public String getNmPais() {
			if (this.nmPais == null) {
				this.nmPais = ConstantesCRM.SVAZIO;
			}
			return this.nmPais;
		}

		public String getSgUF() {
			if (this.sgUF == null) {
				this.sgUF = ConstantesCRM.SVAZIO;
			}
			return this.sgUF;
		}

		public void setAdmGrupoCamposVO(AdmGrupoCamposVO[] admGrupoCamposVO) {
			this.admGrupoCamposVO = admGrupoCamposVO;
		}

		public AdmGrupoCamposVO[] getAdmGrupoCamposVO() {
			if (this.admGrupoCamposVO == null) {
				this.admGrupoCamposVO = new AdmGrupoCamposVO[0];
			}
			return this.admGrupoCamposVO;
		}

		public void setIdAtendimento(String idAtendimento) {
			this.idAtendimento = idAtendimento;
		}

		public String getIdAtendimento() {
			return this.idAtendimento;
		}

		public void setIdAtividade(String idAtividade) {
			this.idAtividade = idAtividade;
		}

		private String inResponsavel = ConstantesCRM.SONE;

		public void setInResponsavel(String inResponsavel) {
			this.inResponsavel = inResponsavel;
		}

		public String getInResponsavel() {
			return this.inResponsavel;
		}

		public void setTextoPesquisa(String textoPesquisa) {
			this.textoPesquisa = textoPesquisa;
		}

		public String getTextoPesquisa() {
			return this.textoPesquisa;
		}

		public void setIdCampo(String idCampo) {
			this.idCampo = idCampo;
		}

		public String getIdCampo() {
			return this.idCampo;
		}

		public void setSelecaoFormulario(String selecaoFormulario) {
			this.selecaoFormulario = selecaoFormulario;
		}

		public String getSelecaoFormulario() {
			return this.selecaoFormulario;
		}

		public void setNumGruposAbertura(String numGruposAbertura) {
			this.numGruposAbertura = numGruposAbertura;
		}

		public String getNumGruposAbertura() {
			return this.numGruposAbertura;
		}

		private String inTratamentoUsuario = ConstantesCRM.SVAZIO;

		public void setInTratamentoUsuario(String inTratamentoUsuario) {
			this.inTratamentoUsuario = inTratamentoUsuario;
		}

		public String getInTratamentoUsuario() {
			return this.inTratamentoUsuario;
		}

		private String abaSolicitante = ConstantesCRM.SVAZIO;

		public void setAbaSolicitante(String abaSolicitante) {
			this.abaSolicitante = abaSolicitante;
		}

		public String getAbaSolicitante() {
			return this.abaSolicitante;
		}

		public String getIdAtividade() {
			return this.idAtividade;
		}

		public String getInCRI() {
			return this.inCRI;
		}

		public void setInCRI(String inCRI) {
			this.inCRI = inCRI;
		}

		public String getInRC() {
			return this.inRC;
		}

		public void setInRC(String inRC) {
			this.inRC = inRC;
		}

		public void setIdTabelaMotivo(String idTabelaMotivo) {
			this.idTabelaMotivo = idTabelaMotivo;
		}

		public String getIdTabelaMotivo() {
			return this.idTabelaMotivo;
		}

		public void setIdProcedencia(String idProcedencia) {
			this.idProcedencia = idProcedencia;
		}

		public String getIdProcedencia() {
			return this.idProcedencia;
		}

		public void setIdBaixa(String idBaixa) {
			this.idBaixa = idBaixa;
		}

		public String getIdBaixa() {
			return this.idBaixa;
		}

		public void setDocumentoAssociado(String documentoAssociado) {
			this.documentoAssociado = documentoAssociado;
		}

		public String getDocumentoAssociado() {
			return this.documentoAssociado;
		}

		public void setQtInsistencia(String qtInsistencia) {
			this.qtInsistencia = qtInsistencia;
		}

		public String getQtInsistencia() {
			return this.qtInsistencia;
		}

		public void setObservacaoTestes(String observacaoTestes) {
			this.observacaoTestes = observacaoTestes;
		}

		public String getObservacaoTestes() {
			return this.observacaoTestes;
		}

		public void setWFDocumentoTecnicoPesquisaVO(WFDocumentoTecnicoPesquisaVO wFDocumentoTecnicoPesquisaVO) {
			this.wFDocumentoTecnicoPesquisaVO = wFDocumentoTecnicoPesquisaVO;
		}

		public WFDocumentoTecnicoPesquisaVO getWFDocumentoTecnicoPesquisaVO() {
			return this.wFDocumentoTecnicoPesquisaVO;
		}

		public void setAtendimentoWorkflowTecnicoDocVO(AtendimentoWorkflowTecnicoDocVO atendimentoWorkflowTecnicoDocVO) {
			this.atendimentoWorkflowTecnicoDocVO = atendimentoWorkflowTecnicoDocVO;
		}

		public AtendimentoWorkflowTecnicoDocVO getAtendimentoWorkflowTecnicoDocVO() {
			return this.atendimentoWorkflowTecnicoDocVO;
		}

		public void setDocumentosAssociados(AtendimentoWorkflowTecnicoDocVO[] documentosAssociados) {
			this.documentosAssociados = documentosAssociados;
		}

		public AtendimentoWorkflowTecnicoDocVO[] getDocumentosAssociados() {
			return this.documentosAssociados;
		}

		public void setFiltroAssociados(WFDocumentoTecnicoPesquisaVO filtroAssociados) {
			this.filtroAssociados = filtroAssociados;
		}

		public WFDocumentoTecnicoPesquisaVO getFiltroAssociados() {
			return this.filtroAssociados;
		}

		public void setDocumentosId(String[] documentosId) {
			this.documentosId = documentosId;
		}

		public String[] getDocumentosId() {
			return this.documentosId;
		}

		public void setAtendimentoWorkflowTestesVO(AtendimentoWorkflowTestesVO atendimentoWorkflowTestesVO) {
			this.atendimentoWorkflowTestesVO = atendimentoWorkflowTestesVO;
		}

		public AtendimentoWorkflowTestesVO getAtendimentoWorkflowTestesVO() {
			return this.atendimentoWorkflowTestesVO;
		}

		public int getNumeroCampos() {
			return this.numeroCampos;
		}

		public void setNumeroCampos(int numeroCampos) {
			this.numeroCampos = numeroCampos;
		}

		public int getTamanho() {
			return this.tamanho;
		}

		public void setTamanho(int tamanho) {
			this.tamanho = tamanho;
		}

		public int getArrayIndex() {
			return this.arrayIndex;
		}

		public void setArrayIndex(int arrayIndex) {
			this.arrayIndex = arrayIndex;
		}

		public int getNumPergunta() {
			return this.numPergunta;
		}

		public void setNumPergunta(int numPergunta) {
			this.numPergunta = numPergunta;
		}

		public ValorCampo[] getValorCampo() {
			if (valorCampo == null) {
				int numeroCampos = this.getNumeroCampos();
				if (numeroCampos == 0) {numeroCampos = 100;}
				valorCampo = new ValorCampo[numeroCampos];
				for (int i = 0; i < valorCampo.length; i++) {
					valorCampo[i] = new ValorCampo();
					valorCampo[i].setValorArray(new String[0]);
				}
			}

			return (this.valorCampo);
		}

		public void setValorCampo(ValorCampo[] valorCampo) {
			this.valorCampo = valorCampo;
		}

		public void setGrupoSel(String grupoSel) {
			this.grupoSel = grupoSel;
		}

		public String getGrupoSel() {
			return this.grupoSel;
		}

		public void setUsuarioSel(String usuarioSel) {
			this.usuarioSel = usuarioSel;
		}

		public String getUsuarioSel() {
			return this.usuarioSel;
		}

		public void setMotivoSel(String motivoSel) {
			this.motivoSel = motivoSel;
		}

		public String getMotivoSel() {
			return this.motivoSel;
		}

		public String getComentario() {
			return this.comentario;
		}

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}

		public void setWFAcaoRetornoVO(WFAcaoRetornoVO wFAcaoRetornoVO) {
			this.wFAcaoRetornoVO = wFAcaoRetornoVO;
		}

		public WFAcaoRetornoVO getWFAcaoRetornoVO() {
			return this.wFAcaoRetornoVO;
		}

		public void setAtendimentosVO(AtendimentoVO[] atendimentosVO) {
			this.atendimentosVO = atendimentosVO;
		}

		public AtendimentoVO[] getAtendimentosVO() {
			return this.atendimentosVO;
		}

		public void setArvoreEncerramentoVO(ArvoreEncerramentoVO arvoreEncerramentoVO) {
			this.arvoreEncerramentoVO = arvoreEncerramentoVO;
		}

		public ArvoreEncerramentoVO getArvoreEncerramentoVO() {
			return this.arvoreEncerramentoVO;
		}

		public void setWFGruposVO(WFGrupoVO[] wFGruposVO) {
			this.wFGruposVO = wFGruposVO;
		}

		public WFGrupoVO[] getWFGruposVO() {
			return this.wFGruposVO;
		}

		public void setScriptArvoreBaixa(String scriptArvoreBaixa) {
			this.scriptArvoreBaixa = scriptArvoreBaixa;
		}

		public String getScriptArvoreBaixa() {
			return this.scriptArvoreBaixa;
		}

		public void setWFMotivosVO(WFMotivoVO[] wFMotivosVO) {
			this.wFMotivosVO = wFMotivosVO;
		}

		public WFMotivoVO[] getWFMotivosVO() {
			return this.wFMotivosVO;
		}

		public void setUsuarioVIVO(UsuarioVIVO[] usuarioVIVO) {
			this.usuarioVIVO = usuarioVIVO;
		}

		public UsuarioVIVO[] getUsuarioVIVO() {
			return this.usuarioVIVO;
		}

		public void setAtendimentoWorkflowVO(AtendimentoWorkflowVO atendimentoWorkflowVO) {
			this.atendimentoWorkflowVO = atendimentoWorkflowVO;
		}

		public AtendimentoWorkflowVO getAtendimentoWorkflowVO() {
			return this.atendimentoWorkflowVO;
		}

		public void setRespostaPesqSatisfa(String[] respostaPesqSatisfa) {
			this.respostaPesqSatisfa = respostaPesqSatisfa;
		}

		public String[] getRespostaPesqSatisfa() {
			return this.respostaPesqSatisfa;
		}

		public void setAdmPerguntaVO(AdmPerguntaVO admPerguntaVO) {
			this.admPerguntaVO = admPerguntaVO;
		}

		public AdmPerguntaVO getAdmPerguntaVO() {
			return this.admPerguntaVO;
		}

		public void setNotaPesqSatisfa(String notaPesqSatisfa) {
			this.notaPesqSatisfa = notaPesqSatisfa;
		}

		public String getNotaPesqSatisfa() {
			return this.notaPesqSatisfa;
		}

		public void setObservacaoPesqSatisfa(String observacaoPesqSatisfa) {
			this.observacaoPesqSatisfa = observacaoPesqSatisfa;
		}

		public String getObservacaoPesqSatisfa() {
			return this.observacaoPesqSatisfa;
		}

		public void setHora(String hora) {
			this.hora = hora;
		}

		public String getHora() {
			return this.hora;
		}

		public void setFila(String fila) {
			this.fila = fila;
		}

		public String getFila() {
			return this.fila;
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setIdTipoComunicacao(String idTipoComunicacao) {
			this.idTipoComunicacao = idTipoComunicacao;
		}

		public String getIdTipoComunicacao() {
			return this.idTipoComunicacao;
		}

		public void setScriptValidacao(String scriptValidacao) {
			this.scriptValidacao = scriptValidacao;
		}

		public String getScriptValidacao() {
			return this.scriptValidacao;
		}

		public void setIdBaixaMensagem(String idBaixaMensagem) {
			this.idBaixaMensagem = idBaixaMensagem;
		}

		public String getIdBaixaMensagem() {
			return this.idBaixaMensagem;
		}

		public void setDsTipoComunicacao(String dsTipoComunicacao) {
			this.dsTipoComunicacao = dsTipoComunicacao;
		}

		public String getDsTipoComunicacao() {
			return this.dsTipoComunicacao;
		}

		public void setDocTec(int docTec) {
			this.docTec = docTec;
		}

		public int getDocTec() {
			return this.docTec;
		}

		public void setDsMotivo(String dsMotivo) {
			this.dsMotivo = dsMotivo;
		}

		public String getDsMotivo() {
			if (dsMotivo == null) {
				dsMotivo = "";
			}
			return this.dsMotivo;
		}

		public void setIdAgrupamentoEstadoTpfuturo(String idAgrupamentoEstadoTpfuturo) {
			this.idAgrupamentoEstadoTpfuturo = idAgrupamentoEstadoTpfuturo;
		}

		public String getIdAgrupamentoEstadoTpfuturo() {
			if (this.idAgrupamentoEstadoTpfuturo == null) {
				this.idAgrupamentoEstadoTpfuturo = ConstantesCRM.SVAZIO;
			}
			return this.idAgrupamentoEstadoTpfuturo;
		}
	}

	public AtendimentoForm getAtendimentoForm() {
		return this.atendimentoForm;
	}

	/*
	 * private ArrayList getAtendimentosSel(AtendimentoForm atendimentoForm) throws Exception { AtendimentoVO[] atdsVO =
	 * atendimentoForm.getAtendimentosVO(); ArrayList atendimentos = new ArrayList(); for(int i = 0; i < atdsVO.length;
	 * i++) { atendimentos.add(new Integer(atdsVO[i].getIdAtendimento())); } return new ArrayList(atendimentos); }
	 */

	private AtendimentoVO[] configuraAtendimentos(AtendimentoVO[] atendimentosVO, String idAtividade, String idMotivo, String idGrupo, String idUsuarioDestino, String comentario, String idAtendimento, String inCRI) {

		AtendimentoVO[] atendimentos;
		atendimentos = new AtendimentoVO[1];

		AtdWFVO[] atdWf;

		AtendimentoWorkflowComumVO atdWFComumVO = AtendimentoWorkflowComumVO.Factory.newInstance();
		atdWFComumVO.setDsObservacao(comentario);

		if (atendimentosVO.length == 0) {
			// id atendimento fake
			atdWf = new AtdWFVO[1];
			atdWf[0] = AtdWFVO.Factory.newInstance();
			atdWf[0].setIdAtendimento(idAtendimento);
			// fake
		} else {
			atdWf = new AtdWFVO[atendimentosVO.length];
			for (int i = 0; i < atendimentosVO.length; i++) {
				atdWf[i] = AtdWFVO.Factory.newInstance();
				atdWf[i].setIdAtendimento(atendimentosVO[i].getIdAtendimento());
			}
		}

		if (idMotivo != null) {
			WFMotivoVO[] wfMotivoVO = new WFMotivoVO[1];
			wfMotivoVO[0] = WFMotivoVO.Factory.newInstance();
			wfMotivoVO[0].setIdMotivo(idMotivo);
			atdWFComumVO.setWFMotivoVOArray(wfMotivoVO);
		}

		if (idGrupo != null) {
			WFGrupoVO[] wFGrupoVO = new WFGrupoVO[1];
			wFGrupoVO[0] = WFGrupoVO.Factory.newInstance();
			wFGrupoVO[0].setIdGrupo(idGrupo);
			atdWFComumVO.setWFGrupoVOArray(wFGrupoVO);
		}

		if (idUsuarioDestino != null) {
			if (idUsuarioDestino.equals("-1")) {
				// nada
			} else {
				UsuarioVIVO[] usuarioVIVO = new UsuarioVIVO[1];
				usuarioVIVO[0] = UsuarioVIVO.Factory.newInstance();
				usuarioVIVO[0].setIdPessoaUsuario(idUsuarioDestino);
				atdWFComumVO.setUsuarioVIVOArray(usuarioVIVO);
			}
		}

		AtendimentoWorkflowVO atdWFVO = AtendimentoWorkflowVO.Factory.newInstance();
		atdWFVO.setAtendimentoWorkflowComumVO(atdWFComumVO);

		WFAcaoVO[] wFAcaoVO = new WFAcaoVO[1];
		wFAcaoVO[0] = WFAcaoVO.Factory.newInstance();
		wFAcaoVO[0].setIdAtividade(idAtividade);
		wFAcaoVO[0].setInCRI(inCRI);

		atendimentos[0] = AtendimentoVO.Factory.newInstance();
		atendimentos[0].setAtdWFVOArray(atdWf);
		atendimentos[0].setWFAcaoVOArray(wFAcaoVO);
		atendimentos[0].setAtendimentoWorkflowVO(atdWFVO);

		return atendimentos;
	}

	public static class ValorCampo implements Serializable {

		private static final long serialVersionUID = 1L;
		private String valor;
		private String[] valorArray;

		public String getValor() {
			return valor;
		}

		public String[] getValorArray() {
			return valorArray;
		}

		public void setValor(String strings) {
			valor = strings;
		}

		public void setValorArray(String[] string) {
			valorArray = string;
		}
	}

}
