package workflow.AtendimentoFila.MeuCliente;

import java.io.BufferedOutputStream;
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

import workflow.AtendimentoFila.MeuCliente.formBeans.FilaMeuClienteFiltrosForm;
import workflow.AtendimentoWorkflow.AtendimentoWorkflowController.AtendimentoForm;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.FOPageFlowBusinessLogger;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoFilaPesquisaVODocument.AtendimentoFilaPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoFilaVODocument.AtendimentoFilaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.AjaxError;
import commons.errors.FormError;

@SuppressWarnings({"rawtypes","unchecked","deprecation"})
public class MeuClienteController extends AbstractAction {

	private static final long serialVersionUID = -228785464973086415L;

	protected global.Global globalApp = new global.Global();

	private FilaMeuClienteFiltrosForm filaMeuClienteFiltrosForm;

	//private final int TIPO_ENCERRAMENTO_RETIDO_PORTABILIDADE = 12;
	//private final int TIPO_ENCERRAMENTO_NAO_RETIDO_PORTABILIDADE = 13;
	//private final int TIPO_ENCERRAMENTO_VAIPENSAR_PORTABILIDADE = 14;

	//private final int INICIAR_RETENCAO = 54;
	//private final int DEVOLVER_FILA_PORTOUT = 50;
	//private final int CLIENTE_NAO_ATENDEU = 59;

	private static final String RAIZ_PRINCIPAL = "PAI";

	@EJB
	private br.com.vivo.fo.ctrls.workflow.monitoramento.MonitoramentoFacade monitoramentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade atendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.AtendimentoWorkflowFacade atendimentoWorkflowFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	private FOPageFlowBusinessLogger businessLog = new FOPageFlowBusinessLogger();

	private transient Logger logger = new Logger("MeuCliente");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);

		} else if ("getUsuariosByIdGrupo".equals(mapping.getParameter())) {
			return getUsuariosByIdGrupo(mapping, form, request, response);

		} else if ("pesquisarProcessos".equals(mapping.getParameter())) {
			return pesquisarProcessos(mapping, form, request, response);

		} else if ("encaminharSuspeito".equals(mapping.getParameter())) {
			return encaminharSuspeito(mapping, form, request, response);

		} else if ("AtendimentoWorkflowDone".equals(mapping.getParameter())) {
			return AtendimentoWorkflowDone(mapping, form, request, response);

		} else if ("obterArvoreContato".equals(mapping.getParameter())) {
			return obterArvoreContato(mapping, form, request, response);

		} else if ("expandeArvoreContato".equals(mapping.getParameter())) {
			return expandeArvoreContato(mapping, form, request, response);

		} else if ("getAlerta".equals(mapping.getParameter())) {
			return getAlerta(mapping, form, request, response);

		} else {
			return begin(mapping, form, request, response);
		}
	}

	public FilaMeuClienteFiltrosForm getFilaMeuClienteFiltrosForm() {
		if (this.filaMeuClienteFiltrosForm == null) {
			this.filaMeuClienteFiltrosForm = new FilaMeuClienteFiltrosForm();
		}
		return this.filaMeuClienteFiltrosForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="success_360" path="index_360.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			//FilaMeuClienteFiltrosForm form = (FilaMeuClienteFiltrosForm) formParam;

			String destino = ConstantesCRM.SVAZIO;
			if (ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("ACESSO_EXTERNO"))) {
				destino = "success_360";
			} else {
				request.getSession().removeAttribute("ACESSO_EXTERNO");
				destino = ConstantesCRM.SUCCESS;
			}

			businessLog = new FOPageFlowBusinessLogger();
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			getFilaMeuClienteFiltrosForm().setInVoltar(false);

			// Para preenchimento inicial da tela caso parâmetros já estejam no bean
			// Exemplo: Botão voltar
			if (ConstantesCRM.SONE.equals(request.getParameter("voltar"))) {

				getFilaMeuClienteFiltrosForm().setInVoltar(true);
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("idRegional"))) {
					getFilaMeuClienteFiltrosForm().setIdRegional(request.getParameter("idRegional"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("idEstado"))) {
					getFilaMeuClienteFiltrosForm().setIdEstado(request.getParameter("idEstado"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("idGrupo"))) {
					getFilaMeuClienteFiltrosForm().setIdGrupo(request.getParameter("idGrupo"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("idSubestado"))) {
					getFilaMeuClienteFiltrosForm().setIdSubestado(request.getParameter("idSubestado"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("idUsuario"))) {
					getFilaMeuClienteFiltrosForm().setIdUsuario(request.getParameter("idUsuario"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("inPeriodo"))) {
					getFilaMeuClienteFiltrosForm().setInPeriodo(request.getParameter("inPeriodo"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.dtFechamentoFim"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setDtFechamentoFim(request.getParameter("filtrosPesquisa.dtFechamentoFim"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.nrLinha"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setNrLinha(request.getParameter("filtrosPesquisa.nrLinha"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.dtAberturaInicio"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setDtAberturaInicio(request.getParameter("filtrosPesquisa.dtAberturaInicio"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.idAtendimento"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setIdAtendimento(request.getParameter("filtrosPesquisa.idAtendimento"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.inFilaAbertos"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setInFilaAbertos(request.getParameter("filtrosPesquisa.inFilaAbertos"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.dtFechamentoInicio"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setDtFechamentoInicio(request.getParameter("filtrosPesquisa.dtFechamentoInicio"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.idContato"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setIdContato(request.getParameter("filtrosPesquisa.idContato"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.dtAberturaFim"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setDtAberturaFim(request.getParameter("filtrosPesquisa.dtAberturaFim"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.nrProtocolo"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setNrProtocolo(request.getParameter("filtrosPesquisa.nrProtocolo"));
				}
				if (!ConstantesCRM.SVAZIO.equals(request.getParameter("filtrosPesquisa.inPesquisaFullMC"))) {
					getFilaMeuClienteFiltrosForm().getFiltrosPesquisa().setInPesquisaFullMC(request.getParameter("filtrosPesquisa.inPesquisaFullMC"));
				}

			} else {
				this.filaMeuClienteFiltrosForm = new FilaMeuClienteFiltrosForm();
			}

			if (getFilaMeuClienteFiltrosForm().getFiltrosListas().getWFRegionalVOArray().length == 0) {
				MonitoramentoPesquisaVO monitoramentoPesquisaVO = monitoramentoFacade.getMonitoramentoPesquisaVO(user, true, true, true, true, true);
				getFilaMeuClienteFiltrosForm().setFiltrosListas(monitoramentoPesquisaVO);
				businessLog.addNewLoggerItem("getFilaMeuClienteFiltrosForm().getFiltrosListas()", monitoramentoPesquisaVO.xmlText());
			}

			if (getFilaMeuClienteFiltrosForm().getListaEstadosSubestados().getWFEstadoVOArray().length == 0) {
				WFEstadosVO WFEstadoVO = atendimentoFacade.getWFEstadosVO(user, false);
				getFilaMeuClienteFiltrosForm().setListaEstadosSubestados(WFEstadoVO);
				businessLog.addNewLoggerItem("getFilaMeuClienteFiltrosForm().getListaEstadosSubestados()", WFEstadoVO.xmlText());
			}

			request.setAttribute("debugMessage", businessLog);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
		    logger.error("begin::Exception", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward getUsuariosByIdGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//FilaMeuClienteFiltrosForm form = (FilaMeuClienteFiltrosForm) formParam;

		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		if (!ConstantesCRM.SVAZIO.equals(request.getParameter("idGrupoSelecionado"))) {

			AtendimentoInformacaoVO resultadoPesquisa = AtendimentoInformacaoVO.Factory.newInstance();
			resultadoPesquisa.setUsuarioVIVOArray(atendimentoWorkflowFacade.obtemUsuarioVIVO(idUsuario, request.getParameter("idGrupoSelecionado"), ConstantesCRM.SONE, null, true));

			getFilaMeuClienteFiltrosForm().setListaUsuarios(resultadoPesquisa.getUsuarioVIVOArray());

			String[] stringsToDelete = new String[1];
			stringsToDelete[0] = " xmlns=\"usuario.fo.vivo.com.br/vo\"";
			String retornoToXML = ClienteUtils.getCleanXMLFromXSD(resultadoPesquisa, stringsToDelete);

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			response.setContentType(ConstantesCRM.SContentType);
			bufferedOutputStream.write(retornoToXML.getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

		}

		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward pesquisarProcessos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			FilaMeuClienteFiltrosForm form = (FilaMeuClienteFiltrosForm) formParam;

			String user = ConstantesCRM.getCurrentUser(request.getSession());

			AtendimentoFilaPesquisaVO filtrosPesquisa = AtendimentoFilaPesquisaVO.Factory.newInstance();
			filtrosPesquisa = form.getFiltrosPesquisa();
			filtrosPesquisa.setInMeuCliente(1);
			filtrosPesquisa.addNewWFRegionalVO().setIdRegional(form.getIdRegional());

			if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoPessoa())) {
				filtrosPesquisa.addNewTipoPessoaVO().addIdtipopessoa(Integer.parseInt(form.getIdTipoPessoa()));
			}

			if ("ABERTURA".equals(form.getInPeriodo())) {
				filtrosPesquisa.setDtFechamentoInicio(null);
				filtrosPesquisa.setDtFechamentoFim(null);
			} else if ("FECHAMENTO".equals(form.getInPeriodo())) {
				filtrosPesquisa.setDtAberturaInicio(null);
				filtrosPesquisa.setDtAberturaFim(null);
			}

			// Quando pesquisa é feita por nrLinha, todos os outros filtros de listas são desnecessários
			if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosPesquisa().getNrLinha())) {
				form.getFiltrosPesquisa().setNrLinha(StringUtilStatic.unmask(form.getFiltrosPesquisa().getNrLinha(), "()-"));
			} else {
				if (!ConstantesCRM.SVAZIO.equals(form.getIdEstado())) {
					filtrosPesquisa.addNewWFEstadoVO().setIdEstado(form.getIdEstado());
				} else {
					if (filtrosPesquisa.getWFEstadoVO() != null) {
						filtrosPesquisa.unsetWFEstadoVO();
					}
				}
				if (!ConstantesCRM.SVAZIO.equals(form.getIdSubestado())) {
					filtrosPesquisa.addNewWFSubEstadoVO().setIdSubEstado(form.getIdSubestado());
				} else {
					if (filtrosPesquisa.getWFSubEstadoVO() != null) {
						filtrosPesquisa.unsetWFSubEstadoVO();
					}
				}
				if (!ConstantesCRM.SVAZIO.equals(form.getIdGrupo())) {
					filtrosPesquisa.addNewWFGrupoVO().setIdGrupo(form.getIdGrupo());
				} else {
					if (filtrosPesquisa.getWFGrupoVO() != null) {
						filtrosPesquisa.unsetWFGrupoVO();
					}
				}
				if (!ConstantesCRM.SVAZIO.equals(form.getIdUsuario())) {
					filtrosPesquisa.addNewUsuarioVIVO().setIdPessoaUsuario(form.getIdUsuario());
				} else {
					if (filtrosPesquisa.getUsuarioVIVO() != null) {
						filtrosPesquisa.unsetUsuarioVIVO();
					}
				}
			}

			getFilaMeuClienteFiltrosForm().setFiltrosPesquisa(filtrosPesquisa);
			AtendimentoInformacaoVO resultadoPesquisa = atendimentoFacade.obtemAtendimentoInformacaoVO(user, filtrosPesquisa);

			if (ConstantesCRM.SONE.equals(filtrosPesquisa.getInTotalRegistros())) {
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(resultadoPesquisa.getTotalRegistros().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				return null;
			
			} else {

				businessLog = new FOPageFlowBusinessLogger();
				businessLog.addNewLoggerItem("filtrosPesquisa", filtrosPesquisa.xmlText());
				request.setAttribute("debugMessage", businessLog);

				getFilaMeuClienteFiltrosForm().setResultadoPesquisa(resultadoPesquisa);

				request.setAttribute("filaMeuClienteFiltrosForm", getFilaMeuClienteFiltrosForm());
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

		} catch (Exception e) {
		    logger.error("pesquisarProcessos::Exception", e);
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="encaminhar" path="/workflow/AtendimentoWorkflow/listaEncaminhar.do"
	 * @jpf:forward name="suspeito" path="/workflow/AtendimentoWorkflow/listaSuspeito.do"
	 */
	protected ActionForward encaminharSuspeito(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FilaMeuClienteFiltrosForm form = (FilaMeuClienteFiltrosForm) formParam;

		String destino = request.getParameter("operacao");
		getFilaMeuClienteFiltrosForm().setIndexesProcessosSelecionados(form.getIndexesProcessosSelecionados());

		AtendimentoForm atendimentoForm = this.getAtendimentoForm();
		if ("suspeito".equals(destino)) {
			atendimentoForm.setIdAtividade(ConstantesCRM.SSIX);
		} else if ("encaminhar".equals(destino)) {
			atendimentoForm.setIdAtividade(ConstantesCRM.STHREE);
		}

		atendimentoForm.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/MeuCliente/begin.do");
		atendimentoForm.setInCRI(ConstantesCRM.SZERO);
		atendimentoForm.setInRC(ConstantesCRM.SZERO);

		request.setAttribute("atendimentoForm", atendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="voltarWorkflow.jsp"
	 */
	protected ActionForward AtendimentoWorkflowDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("voltar", ConstantesCRM.SONE);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	private AtendimentoForm getAtendimentoForm() throws Exception {

		Collection col = new Vector();
		AtendimentoFilaVO[] atendimentoFilaVO = getFilaMeuClienteFiltrosForm().getResultadoPesquisa().getAtendimentoFilaVOArray();

		for (int i = 0; i < getFilaMeuClienteFiltrosForm().getIndexesProcessosSelecionados().length; i++) {
			int index = Integer.parseInt(getFilaMeuClienteFiltrosForm().getIndexesProcessosSelecionados()[i]);
			col.add(atendimentoFilaVO[index].getAtendimentoVO());
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

		atendimentoForm.setIdAtividade("13");
		atendimentoForm.setFila("/FrontOfficeWeb/workflow/AtendimentoFila/MeuCliente/begin.do");
		atendimentoForm.setWFGruposVO(getFilaMeuClienteFiltrosForm().getResultadoPesquisa().getWFGrupoVOArray());

		return atendimentoForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iArvoreContato.jsp"
	 */
	protected ActionForward obterArvoreContato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

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
				dblClickScript = "";
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

		getFilaMeuClienteFiltrosForm().setScriptArvore(sbScript.toString());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iExpandeArvoreContatoFila.jsp"
	 */
	protected ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//FilaMeuClienteFiltrosForm form = (FilaMeuClienteFiltrosForm) formParam;

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
				dblClickScript = "";
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
		getFilaMeuClienteFiltrosForm().setScriptArvore(sbScript.toString());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="listaAlertas.jsp"
	 */
	protected ActionForward getAlerta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			//FilaMeuClienteFiltrosForm form = (FilaMeuClienteFiltrosForm) formParam;

			String user = ConstantesCRM.getCurrentUser(request.getSession());
			String idAtendimento = request.getParameter("idAtendimento");

			if (idAtendimento != null) {
				getFilaMeuClienteFiltrosForm().setListaAlertas(atendimentoFacade.obtemAlertaVO(user, idAtendimento, ConstantesCRM.SONE));
			}

		} catch (Exception e) {
		    logger.error("getAlerta::Exception", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("filaMeuClienteFiltrosForm", getFilaMeuClienteFiltrosForm());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

}