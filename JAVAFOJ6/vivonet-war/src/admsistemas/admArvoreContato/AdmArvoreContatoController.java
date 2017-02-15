package admsistemas.admArvoreContato;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreContainerVODocument.AdmArvoreContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreContatoCopiaVODocument.AdmArvoreContatoCopiaVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaRemoverVODocument.AdmContatoFolhaRemoverVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO.Palitagem;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoVODocument.AdmGrupoVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmIndicadorAnatelVODocument.AdmIndicadorAnatelVO;
import br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO;
import br.com.vivo.fo.admsistemas.vo.AdmNomeContatoVODocument.AdmNomeContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmClassificacaoSMSVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoFechamentoVODocument.AdmTipoFechamentoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoProcessoVODocument.AdmTipoProcessoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoRetornoContatoVODocument.AdmTipoRetornoContatoVO;
import br.com.vivo.fo.admsistemas.vo.SmpVODocument.SmpVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AdmArvoreContatoController extends AbstractAction {

	private static final long serialVersionUID = 1331538352306116954L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.configArvoreContato.ArvoreContato controlArvore;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.palitagem.PalitagemFacade palitagemFacade;

	private FormArvoreContato formArvoreContato = null;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("montaArvore".equals(mapping.getParameter())) {
			return montaArvore(mapping, form, request, response);
		} else if ("montaArvoreParte".equals(mapping.getParameter())) {
			return montaArvoreParte(mapping, form, request, response);
		} else if ("removeItem".equals(mapping.getParameter())) {
			return removeItem(mapping, form, request, response);
		} else if ("insereItem".equals(mapping.getParameter())) {
			return insereItem(mapping, form, request, response);
		} else if ("salvaItemEditado".equals(mapping.getParameter())) {
			return salvaItemEditado(mapping, form, request, response);
		} else if ("criarEditarItemContato".equals(mapping.getParameter())) {
			return criarEditarItemContato(mapping, form, request, response);
		} else if ("managePalitagem".equals(mapping.getParameter())) {
			return managePalitagem(mapping, form, request, response);
		} else if ("criarInserirItemContato".equals(mapping.getParameter())) {
			return criarInserirItemContato(mapping, form, request, response);
		} else if ("habilitaDesabilitaArvore".equals(mapping.getParameter())) {
			return habilitaDesabilitaArvore(mapping, form, request, response);
		} else if ("copiaArvore".equals(mapping.getParameter())) {
			return copiaArvore(mapping, form, request, response);
		} else if ("getNome".equals(mapping.getParameter())) {
			return getNome(mapping, form, request, response);
		} else {
			return begin(mapping, form, request, response);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="montaArvore.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreContatoController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			if (formArvoreContato == null) {
				formArvoreContato = new FormArvoreContato();
			}

			formArvoreContato.setInFolha(ConstantesCRM.SVAZIO);
			formArvoreContato.setNmContato(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdContato(ConstantesCRM.SVAZIO);
			formArvoreContato.setInDisponibilidade(ConstantesCRM.SVAZIO);
			formArvoreContato.setNrNivel(ConstantesCRM.SVAZIO);
			formArvoreContato.setNomeTipo(ConstantesCRM.SVAZIO);
			formArvoreContato.setMsg(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdNomeContato(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdNomeContatoEscolhido(ConstantesCRM.SVAZIO);
			selectsFolhaContato(ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, request);

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreContato.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void selectsFolhaContato(String contatoId, String inPasta, HttpServletRequest request) throws Exception {

		log.debug("AdmArvoreContatoController:selectsFolhaContato(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		log.debug("selectsFolhaContato(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") idContato = " + contatoId);

		AdmSelectsContatoFolhaVO admSelectsContatoFolhaVO = AdmSelectsContatoFolhaVO.Factory.newInstance();
		AdmContatoFolhaRemoverVO idAdmContatoFolha = AdmContatoFolhaRemoverVO.Factory.newInstance();
		idAdmContatoFolha.setIdContato(contatoId);
		admSelectsContatoFolhaVO = controlArvore.carregaSelectsContatoFolhaVO(idAdmContatoFolha, ConstantesCRM.getCurrentUser(request.getSession()));

		formArvoreContato.setAdmPrazo(admSelectsContatoFolhaVO.getAdmPrazoVOArray());
		formArvoreContato.setAdmPrazoAnatel(admSelectsContatoFolhaVO.getAdmPrazoAnatelVOArray());
		formArvoreContato.setAdmPrioridade(admSelectsContatoFolhaVO.getAdmPrioridadeVOArray());

		formArvoreContato.setListaClassificoesSMS(admSelectsContatoFolhaVO.getAdmClassificacaoSMSVOArray());
		formArvoreContato.setListaFechamento(admSelectsContatoFolhaVO.getAdmTipoFechamentoVOArray());
		formArvoreContato.setListaMensagem(admSelectsContatoFolhaVO.getAdmMensagemAvisoVOArray());
		formArvoreContato.setListaProcesso(admSelectsContatoFolhaVO.getAdmTipoProcessoVOArray());
		formArvoreContato.setListaRetorno(admSelectsContatoFolhaVO.getAdmTipoRetornoContatoVOArray());
		formArvoreContato.setAdmIndicadoresAnatelExistentesVO(admSelectsContatoFolhaVO.getFolha().getAdmIndicadoresAnatelContatoContainerVO().getIndicadoresExistentes().getAdmIndicadorAnatelVOArray());
		formArvoreContato.setAdmIndicadoresAnatelAssociadosVO(admSelectsContatoFolhaVO.getFolha().getAdmIndicadoresAnatelContatoContainerVO().getIndicadoresAssociados().getAdmIndicadorAnatelVOArray());

		if (ConstantesCRM.SZERO.equals(contatoId) || contatoId == null || ConstantesCRM.SVAZIO.equals(contatoId) || ConstantesCRM.SONE.equals(inPasta)) {
			formArvoreContato.setInFolha(ConstantesCRM.SZERO);
		} else {
			formArvoreContato.setIdPagina(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getIdPaginaAtual());
			formArvoreContato.setIdTipoRetornoContatoAtual(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getIdTipoRetornoAtual());
			formArvoreContato.setIdMensagemAtual(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getIdMensagemAviso());
			formArvoreContato.setMensagem(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getNmMensagemAviso());
			formArvoreContato.setFechamento(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getIdTipoFechamentoAtual());
			formArvoreContato.setProcessoTec(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getIdTipoProcessoAtual());
			formArvoreContato.setQtDiasPrazoContato(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getQtDiasPrazoContato());
			formArvoreContato.setQtDiasPrazoAnatel(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getVlPrazoAnatel());
			formArvoreContato.setVlPesoContato(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getVlPesoContato());
			formArvoreContato.setSgRegraEncaminhamento(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getSgRegraEncaminhamento());
			formArvoreContato.setSgFluxoAtendimento(admSelectsContatoFolhaVO.getFolha().getAdmDadosBasicosVO().getSgFluxoAtendimento());
			formArvoreContato.setInFolha(ConstantesCRM.SONE);
		}

		formArvoreContato.setIdContato(getFormArvoreContato().getIdContato());
		formArvoreContato.setNmContato(getFormArvoreContato().getNmContato());

		if (getFormArvoreContato().getNmContato() != null) {
			request.setAttribute("nmContato", StringUtilStatic.escapaComillasJS2(getFormArvoreContato().getNmContato()));
		}

		formArvoreContato.setIdContatoPai(getFormArvoreContato().getIdContatoPai());
		formArvoreContato.setInDisponibilidade(getFormArvoreContato().getInDisponibilidade());
		formArvoreContato.setIdNomeContato(getFormArvoreContato().getIdNomeContato());
		formArvoreContato.setNrNivel(getFormArvoreContato().getNrNivel());
		formArvoreContato.setInFolha(getFormArvoreContato().getInFolha());
		formArvoreContato.setDsPath(getFormArvoreContato().getDsPath());

		if (admSelectsContatoFolhaVO.getSmpVO() != null) {
			formArvoreContato.setInProtocolo(admSelectsContatoFolhaVO.getSmpVO().getCanais().getInProtocolo());
			formArvoreContato.setInComprovanteCancelamento(ConstantesCRM.SONE.equals(admSelectsContatoFolhaVO.getSmpVO().getCanais().getInComprovanteCancelamento()) ? true : false);
			formArvoreContato.setInRelacionamento(admSelectsContatoFolhaVO.getSmpVO().getCanais().getInRelacionamento());
			formArvoreContato.setDsContatoCanais(admSelectsContatoFolhaVO.getSmpVO().getCanais().getDsContatoCanais());
			formArvoreContato.setDsMsgExcecao(admSelectsContatoFolhaVO.getSmpVO().getCanais().getDsMsgExcecao());
			formArvoreContato.setInSMS(admSelectsContatoFolhaVO.getSmpVO().getSms().getInSMS());
			formArvoreContato.setIdClassificacaoSMS(admSelectsContatoFolhaVO.getSmpVO().getSms().getIdClassificacao());
		}

		if (getFormArvoreContato().getDsPath() != null) {
			request.setAttribute("dsPath", StringUtilStatic.escapaComillasJS2(getFormArvoreContato().getDsPath()));
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterArvoreContato.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreContatoController:montaArvore(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		formArvoreContato.setMsgError(ConstantesCRM.SVAZIO);
		int pageNumber = 0;
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			AdmIdContatoVO admidContato = AdmIdContatoVO.Factory.newInstance();
			admidContato.addNewPaginacao().setPageNumber(pageNumber);
			AdmArvoreContainerVO admContatoFolhaVO = controlArvore.carregaArvoreContato(admidContato, user);
			AdmArvoreContainerVO admContatoFolhaVOPaginacao = admContatoFolhaVO;

			AdmContatoFolhaVO masterContato = admContatoFolhaVO.getAdmContatoFolhaVO();

			while (admContatoFolhaVOPaginacao.getPaginacao().getHasNext() == 1) {
				pageNumber++;
				admidContato.getPaginacao().setPageNumber(pageNumber);
				admContatoFolhaVOPaginacao = controlArvore.carregaArvoreContato(admidContato, user);
				for (int i = 0; i < admContatoFolhaVOPaginacao.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray().length; i++) {
					masterContato.addNewAdmContatoFolhaVO().set(admContatoFolhaVOPaginacao.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).copy());
				}
			}
			formArvoreContato.setAdmNomeContatoVO(admContatoFolhaVO.getAdmNomeContatoVOArray());
			formArvoreContato.setArvoreContato(admContatoFolhaVO);
			StringBuffer script = new StringBuffer();

			// String folder= "";
			script.append("if (document.getElementById) {\nvar tree = new WebFXTree('").append(StringUtilStatic.escapaComillasJS(masterContato.getNmContato())).append("',\"javascript:capturaParametrosContatoRaiz('").append(masterContato.getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(masterContato.getNmContato())).append("','").append(masterContato.getIdContatoPai()).append("','','").append(masterContato.getIdNomeContato()).append("','','")
			.append(masterContato.getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(masterContato.getDsPath().toString())).append("','');\",'','');\n\n");

			for (int i = 0; i < masterContato.getAdmContatoFolhaVOArray().length; i++) {

				AdmContatoFolhaVO itemContato = masterContato.getAdmContatoFolhaVOArray(i);

				String sIcon = ConstantesCRM.SVAZIO;
				String sAction = ConstantesCRM.SVAZIO;

				boolean inFolha = false;
				if (ConstantesCRM.SONE.equals(itemContato.getInFolha().trim())) {
					inFolha = true;
					if (ConstantesCRM.SONE.equals(itemContato.getInDisponibilidade().trim())) {
						sIcon = "/FrontOfficeWeb/resources/images/file.png";
					} else {
						sIcon = "/FrontOfficeWeb/resources/images/image_delete_file.gif";
					}
				} else {
					inFolha = false;
					if ("1".equals(itemContato.getInDisponibilidade().trim())) {
						sIcon = "/FrontOfficeWeb/resources/images/foldericon.png";
					} else {
						sIcon = "/FrontOfficeWeb/resources/images/image_delete_folder.gif";
					}

				}
				sAction = getAction(inFolha, itemContato.getIdContato(), itemContato.getNmContato(), itemContato.getIdContatoPai(), itemContato.getInDisponibilidade(), itemContato.getIdNomeContato(), itemContato.getNrNivel(), itemContato.getInFolha(), itemContato.getDsPath().toString(), String.valueOf(itemContato.getPermitirParametrizacao()));
				String node = getWebFXTreeItem(false, StringUtilStatic.escapaComillasJS(itemContato.getNmContato()), sAction, "", sIcon, sIcon, "", "");

				script.append(node).append("tree.add(tmpArvore);\n\n");
				formArvoreContato.setAdmNomeContatoVO(formArvoreContato.getArvoreContato().getAdmNomeContatoVOArray());
			}
			script.append("\ndocument.write(tree);}\n\n");
			request.setAttribute("arvore", String.valueOf(script));

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:montaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreContato.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:montaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private String getAction(boolean folha, String idContato, String nmContato, String idContatoPai, String inDisp, String idNmContato, String nrNivel, String inFolha, String dsPath, String inPermParam) {
		StringBuffer sAction = new StringBuffer("javascript:");
		if (folha) {
			sAction.append("capturaParametrosContato('");
		} else {
			sAction.append("expandirNo('");
		}
		sAction.append(idContato).append("','").append(StringUtilStatic.escapaComillasJS2(nmContato)).append("','").append(idContatoPai).append("','").append(inDisp).append("','").append(idNmContato).append("','").append(nrNivel).append("','").append(inFolha).append("','").append(StringUtilStatic.escapaComillasJS2(dsPath)).append("','").append(inPermParam).append("');");
		return sAction.toString();
	}

	private String getWebFXTreeItem(boolean parent, String sText, String sAction, String eParent, String sIcon, String sOpenIcon, String sLink, String sDblAction) {
		StringBuffer itemContato = new StringBuffer(ConstantesCRM.SVAZIO);
		if (parent) {
			itemContato.append("parent.tmpArvore = new parent.");
		} else {
			itemContato.append("var tmpArvore = new ");
		}
		itemContato.append("WebFXTreeItem('").append(sText).append("',").append("\"").append(sAction).append("\",").append("'").append(eParent).append("',").append("'").append(sIcon).append("',").append("'").append(sOpenIcon).append("',").append("'").append(sLink).append("'").append(",'").append(sDblAction).append("'").append(");\n");
		return itemContato.toString();
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iframeArvoreContato.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaArvoreParte(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreContatoController:montaArvoreParte(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		FormArvoreContato form = (FormArvoreContato) formParam;
		formArvoreContato.setMsgError(ConstantesCRM.SVAZIO);
		int pageNumber = 0;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			AdmIdContatoVO admidContato = AdmIdContatoVO.Factory.newInstance();
			admidContato.addNewPaginacao().setPageNumber(pageNumber);
			admidContato.setIdContato(form.getIdContato());
			log.debug("montaArvoreParte(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") idContato = " + form.getIdContato());

			AdmArvoreContainerVO admContatoFolhaVO = controlArvore.carregaArvoreContato(admidContato, user);
			AdmArvoreContainerVO admContatoFolhaVOPaginacao = admContatoFolhaVO;

			while (admContatoFolhaVOPaginacao.getPaginacao().getHasNext() == 1) {
				pageNumber++;
				admidContato.getPaginacao().setPageNumber(pageNumber);
				admContatoFolhaVOPaginacao = controlArvore.carregaArvoreContato(admidContato, user);
				for (int i = 0; i < admContatoFolhaVOPaginacao.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray().length; i++) {
					admContatoFolhaVO.getAdmContatoFolhaVO().addNewAdmContatoFolhaVO().set(admContatoFolhaVOPaginacao.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).copy());
				}
			}
			StringBuffer script = new StringBuffer();

			for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray().length; i++) {

				AdmContatoFolhaVO itemContato = admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i);

				String sIcon = ConstantesCRM.SVAZIO;
				String sAction = ConstantesCRM.SVAZIO;

				boolean inFolha = false;
				if (ConstantesCRM.SONE.equals(itemContato.getInFolha().trim())) {
					inFolha = true;
					if (ConstantesCRM.SONE.equals(itemContato.getInDisponibilidade().trim())) {
						sIcon = "/FrontOfficeWeb/resources/images/file.png";
					} else {
						sIcon = "/FrontOfficeWeb/resources/images/image_delete_file.gif";
					}
				} else {
					inFolha = false;
					if (ConstantesCRM.SONE.equals(itemContato.getInDisponibilidade().trim())) {
						sIcon = "/FrontOfficeWeb/resources/images/foldericon.png";
					} else {
						sIcon = "/FrontOfficeWeb/resources/images/image_delete_folder.gif";
					}

				}
				sAction = getAction(inFolha, itemContato.getIdContato(), itemContato.getNmContato(), itemContato.getIdContatoPai(), itemContato.getInDisponibilidade(), itemContato.getIdNomeContato(), itemContato.getNrNivel(), itemContato.getInFolha(), itemContato.getDsPath().toString(), String.valueOf(itemContato.getPermitirParametrizacao()));
				String node = getWebFXTreeItem(true, StringUtilStatic.escapaComillasJS(itemContato.getNmContato()), sAction, ConstantesCRM.SVAZIO, sIcon, sIcon, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO);
				script.append(node).append("parent.tree.getSelected().add(parent.tmpArvore);\n\n");
			}
			script.append("parent.tree.getSelected().setAddEnabled(false);\n").append("parent.tree.getSelected().expand();\n\n");
			request.setAttribute("arvore", String.valueOf(script));

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreContato.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void limpaCampos(HttpServletRequest request) {
		log.debug("AdmArvoreContatoController:limpaCampos(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		formArvoreContato.setIdContato(ConstantesCRM.SVAZIO);
		formArvoreContato.setIdContatoPai(ConstantesCRM.SVAZIO);
		formArvoreContato.setIdNomeContato(ConstantesCRM.SVAZIO);
		formArvoreContato.setInDisponibilidade(ConstantesCRM.SVAZIO);
		formArvoreContato.setNmContato(ConstantesCRM.SVAZIO);
		formArvoreContato.setNrNivel(ConstantesCRM.SVAZIO);
		formArvoreContato.setArrayAdmNomeContato(new String[0]);
		formArvoreContato.setIdNomeContatoEscolhido(ConstantesCRM.SVAZIO);
		formArvoreContato.setInDisponibilidadeNovo(ConstantesCRM.SVAZIO);
		formArvoreContato.setInFolha(ConstantesCRM.SVAZIO);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		FormArvoreContato form = (FormArvoreContato) formParam;
		log.debug("AdmArvoreContatoController:removeItem(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		formArvoreContato.setMsgError(ConstantesCRM.SVAZIO);
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			AdmContatoFolhaRemoverVO admContatoFolhaRemoverVO = AdmContatoFolhaRemoverVO.Factory.newInstance();
			admContatoFolhaRemoverVO.setIdContato(form.getIdContato());
			log.debug("removeItem(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") idContato = " + form.getIdContato());
			controlArvore.removeItemArvoreContato(admContatoFolhaRemoverVO, ConstantesCRM.getCurrentUser(request.getSession()));
			limpaCampos(request);
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreContato.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			if (e.getMessage().matches("^.*14E0001.*$")) {
				log.warn("AdmArvoreContatoController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute("msgError", e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
			log.error("AdmArvoreContatoController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward insereItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		FormArvoreContato form = (FormArvoreContato) formParam;
		log.debug("AdmArvoreContatoController:insereItem(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			AdmContatoFolhaVO admContatoFolhaInserirVO = AdmContatoFolhaVO.Factory.newInstance();
			admContatoFolhaInserirVO.setIdContatoPai(form.getIdContato());
			log.debug("insereItem(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") idContato = " + form.getIdContato());
			admContatoFolhaInserirVO.setInDisponibilidade(form.getInDisponibilidadeNovo());
			admContatoFolhaInserirVO.setNrNivel(form.getNrNivel());
			admContatoFolhaInserirVO.setIdContato(form.getIdContato());

			if ((request.getParameter("NomeContatoEscolhido")).equals(ConstantesCRM.SVAZIO)) {
				admContatoFolhaInserirVO.setIdNomeContato(form.getIdNomeContatoEscolhido());
				// admContatoFolhaInserirVO.setNmContato(form.getNmContato());
			} else {
				admContatoFolhaInserirVO.setIdNomeContato(ConstantesCRM.SVAZIO);
				admContatoFolhaInserirVO.setNmContato(request.getParameter("NomeContatoEscolhido"));
			}
			admContatoFolhaInserirVO.setInFolha(form.getInFolha());

			if (form.getInFolha().equals(ConstantesCRM.SONE)) {

				admContatoFolhaInserirVO.addNewFolha().addNewAdmDadosBasicosVO();
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdPaginaAtual(form.getIdPagina());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoRetornoAtual("0");
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdMensagemAviso(form.getMensagem());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setNmMensagemAviso(form.getMensagemNova());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoFechamentoAtual(form.getFechamento());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoProcessoAtual(form.getProcessoTec());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setInProcessoTecnico("0");
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setQtDiasPrazoContato(form.getQtDiasPrazoContato());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setVlPrazoAnatel(form.getQtDiasPrazoAnatel());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setVlPesoContato(form.getVlPesoContato());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setSgRegraEncaminhamento(form.getSgRegraEncaminhamento());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setSgFluxoAtendimento(form.getSgFluxoAtendimento());

				String[] indicadorAnatel = new String[form.getArrayAdmIndicadorAnatelAssociado().length];
				indicadorAnatel = form.getArrayAdmIndicadorAnatelAssociado();
				AdmIndicadorAnatelVO[] admIndicadorAnatelVO = new AdmIndicadorAnatelVO[form.getArrayAdmIndicadorAnatelAssociado().length];
				for (int i = 0; i < indicadorAnatel.length; i++) {
					admIndicadorAnatelVO[i] = AdmIndicadorAnatelVO.Factory.newInstance();
					admIndicadorAnatelVO[i].setIdIndicador(indicadorAnatel[i]);
				}
				admContatoFolhaInserirVO.getFolha().addNewAdmIndicadoresAnatelContatoContainerVO().addNewIndicadoresAssociados().setAdmIndicadorAnatelVOArray(admIndicadorAnatelVO);

				String[] grupo = new String[form.getArrayAdmGruposAssociados().length];
				grupo = form.getArrayAdmGruposAssociados();
				AdmGrupoVO[] admGrupoVO = new AdmGrupoVO[form.getArrayAdmGruposAssociados().length];
				for (int j = 0; j < grupo.length; j++) {
					admGrupoVO[j] = AdmGrupoVO.Factory.newInstance();
					admGrupoVO[j].setIdGrupo(grupo[j]);
				}
				admContatoFolhaInserirVO.getFolha().addNewAdmGrupoContatoContainerVO().addNewGruposAssociados().setAdmGrupoVOArray(admGrupoVO);

			} else {
				admContatoFolhaInserirVO.addNewFolha().addNewAdmDadosBasicosVO();
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoRetornoAtual(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdMensagemAviso(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdPaginaAtual(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoFechamentoAtual(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoProcessoAtual(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.addNewFolha().addNewAdmIndicadoresAnatelContatoContainerVO().addNewIndicadoresAssociados();
				admContatoFolhaInserirVO.addNewFolha().addNewAdmIndicadoresAnatelContatoContainerVO().addNewIndicadoresExistentes();
			}

			if (form.getInSMS() != null) {
				SmpVO smp = admContatoFolhaInserirVO.addNewSmpVO();
				smp.addNewCanais();
				smp.getCanais().setInProtocolo(ConstantesCRM.SONE.equals(form.getInProtocolo()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
				smp.getCanais().setInRelacionamento(ConstantesCRM.SONE.equals(form.getInRelacionamento()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
				smp.getCanais().setDsContatoCanais(form.getDsContatoCanais());
				smp.getCanais().setInComprovanteCancelamento(form.getInComprovanteCancelamento() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
				smp.getCanais().setDsMsgExcecao(form.getDsMsgExcecao());
				smp.addNewSms();
				smp.getSms().setInSMS(form.getInSMS());
				smp.getSms().setIdClassificacao(form.getIdClassificacaoSMS());
			}
			controlArvore.salvaItemArvoreContato(admContatoFolhaInserirVO, ConstantesCRM.getCurrentUser(request.getSession()));
			limpaCampos(request);
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:insereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreContato.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:insereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaItemEditado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		FormArvoreContato form = (FormArvoreContato) formParam;
		log.debug("AdmArvoreContatoController:salvaItemEditado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			AdmContatoFolhaVO admContatoFolhaInserirVO = AdmContatoFolhaVO.Factory.newInstance();

			admContatoFolhaInserirVO.setIdContatoPai(form.getIdContatoPai());
			admContatoFolhaInserirVO.setInDisponibilidade(form.getInDisponibilidadeNovo());
			admContatoFolhaInserirVO.setNrNivel(form.getNrNivel());
			admContatoFolhaInserirVO.setIdContato(form.getIdContato());

			if (form.getNomeTipo().trim().equals(ConstantesCRM.SZERO)) {
				admContatoFolhaInserirVO.setIdNomeContato(ConstantesCRM.SVAZIO);
				admContatoFolhaInserirVO.setNmContato(request.getParameter("NomeContatoEscolhido"));
			} else if (form.getNomeTipo().trim().equals(ConstantesCRM.STHREE)) {
				admContatoFolhaInserirVO.setIdNomeContato(form.getIdNomeContato());
				admContatoFolhaInserirVO.setNmContato(ConstantesCRM.SVAZIO);
			} else if (form.getNomeTipo().trim().equals(ConstantesCRM.SONE)) {
				admContatoFolhaInserirVO.setIdNomeContato(form.getIdNomeContatoEscolhido());
				admContatoFolhaInserirVO.setNmContato(ConstantesCRM.SVAZIO);
			}
			/*
			 * if((request.getParameter("NomeContatoEscolhido") == null ||
			 * request.getParameter("NomeContatoEscolhido").equals("")) && (request.getParameter("NomeContatoEscolhido")
			 * == null || request.getParameter("NomeContatoEscolhido").equals(""))){
			 * admContatoFolhaInserirVO.setIdNomeContato(form.getIdNomeContato());
			 * admContatoFolhaInserirVO.setNmContato(""); }else
			 * if((request.getParameter("NomeContatoEscolhido")).equals("")){
			 * admContatoFolhaInserirVO.setIdNomeContato(form.getIdNomeContatoEscolhido());
			 * admContatoFolhaInserirVO.setNmContato(""); }else{ admContatoFolhaInserirVO.setIdNomeContato("");
			 * admContatoFolhaInserirVO.setNmContato(request.getParameter("NomeContatoEscolhido")); }
			 */
			admContatoFolhaInserirVO.setInFolha(form.getInFolha());

			if (form.getInFolha().equals(ConstantesCRM.SONE)) {

				admContatoFolhaInserirVO.addNewFolha().addNewAdmDadosBasicosVO();
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdPaginaAtual(form.getIdPagina());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoRetornoAtual("0");
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdMensagemAviso(form.getMensagem());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setNmMensagemAviso(form.getMensagemNova());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoFechamentoAtual(form.getFechamento());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoProcessoAtual(form.getProcessoTec());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setInProcessoTecnico("0");
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setQtDiasPrazoContato(form.getQtDiasPrazoContato());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setVlPrazoAnatel(form.getQtDiasPrazoAnatel());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setSgRegraEncaminhamento(form.getSgRegraEncaminhamento());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setSgFluxoAtendimento(form.getSgFluxoAtendimento());

				// admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setVlPrazoAnatel("0");
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setVlPesoContato(form.getVlPesoContato());

				String[] indicadorAnatel = new String[form.getArrayAdmIndicadorAnatelAssociado().length];
				indicadorAnatel = form.getArrayAdmIndicadorAnatelAssociado();
				AdmIndicadorAnatelVO[] admIndicadorAnatelVO = new AdmIndicadorAnatelVO[form.getArrayAdmIndicadorAnatelAssociado().length];
				for (int i = 0; i < indicadorAnatel.length; i++) {
					admIndicadorAnatelVO[i] = AdmIndicadorAnatelVO.Factory.newInstance();
					admIndicadorAnatelVO[i].setIdIndicador(indicadorAnatel[i]);
				}
				admContatoFolhaInserirVO.getFolha().addNewAdmIndicadoresAnatelContatoContainerVO().addNewIndicadoresAssociados().setAdmIndicadorAnatelVOArray(admIndicadorAnatelVO);

				String[] grupos = new String[form.getArrayAdmGruposAssociados().length];
				grupos = form.getArrayAdmGruposAssociados();
				AdmGrupoVO[] admGrupoVO = new AdmGrupoVO[form.getArrayAdmGruposAssociados().length];
				for (int j = 0; j < grupos.length; j++) {
					admGrupoVO[j] = AdmGrupoVO.Factory.newInstance();
					admGrupoVO[j].setIdGrupo(grupos[j]);
				}
				admContatoFolhaInserirVO.getFolha().addNewAdmGrupoContatoContainerVO().addNewGruposAssociados().setAdmGrupoVOArray(admGrupoVO);
			} else {
				admContatoFolhaInserirVO.addNewFolha().addNewAdmDadosBasicosVO();
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoRetornoAtual("1");
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdMensagemAviso("1");
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdPaginaAtual("1");
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoFechamentoAtual("1");
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoProcessoAtual("1");
				admContatoFolhaInserirVO.addNewFolha().addNewAdmIndicadoresAnatelContatoContainerVO().addNewIndicadoresAssociados();
				admContatoFolhaInserirVO.addNewFolha().addNewAdmIndicadoresAnatelContatoContainerVO().addNewIndicadoresExistentes();
				admContatoFolhaInserirVO.addNewFolha().addNewAdmGrupoContatoContainerVO().addNewGruposAssociados();
				admContatoFolhaInserirVO.addNewFolha().addNewAdmGrupoContatoContainerVO().addNewGruposExistentes();
			}

			if (form.getInSMS() != null) {
				SmpVO smp = admContatoFolhaInserirVO.addNewSmpVO();
				smp.addNewCanais();
				smp.getCanais().setInProtocolo(ConstantesCRM.SONE.equals(form.getInProtocolo()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
				smp.getCanais().setInComprovanteCancelamento(form.getInComprovanteCancelamento() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
				smp.getCanais().setInRelacionamento(ConstantesCRM.SONE.equals(form.getInRelacionamento()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
				smp.getCanais().setDsContatoCanais(form.getDsContatoCanais());
				smp.getCanais().setDsMsgExcecao(form.getDsMsgExcecao());
				smp.addNewSms();
				smp.getSms().setInSMS(form.getInSMS());
				smp.getSms().setIdClassificacao(form.getIdClassificacaoSMS());
			}

			controlArvore.salvaItemEditadoArvoreContato(admContatoFolhaInserirVO, ConstantesCRM.getCurrentUser(request.getSession()));
			limpaCampos(request);
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:salvaItemEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreContato.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:salvaItemEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="indexItemContato.jsp"
	 * @jpf:forward name="configuracaoFolha" path="criarEditarItemContato.jsp"
	 * @jpf:forward name="configuracaoPalitagem" path="manutencaoContatoPalitagem.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward criarEditarItemContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreContatoController:criarEditarItemContato(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		getFormArvoreContato().setMsgError(ConstantesCRM.SVAZIO);
		String destino = ConstantesCRM.SVAZIO;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		try {

			boolean inInicio = (request.getParameter("inInicio") != null) ? true : false;

			if (inInicio) {

				destino = ConstantesCRM.SUCCESS;

				getFormArvoreContato().setIdContato(request.getParameter("idContato"));
				getFormArvoreContato().setNmContato(request.getParameter("nmContato"));
				getFormArvoreContato().setIdContatoPai(request.getParameter("idContatoPai"));
				getFormArvoreContato().setInDisponibilidade(request.getParameter("inDisponibilidade"));
				getFormArvoreContato().setIdNomeContato(request.getParameter("idNomeContato"));
				getFormArvoreContato().setNrNivel(request.getParameter("nrNivel"));
				getFormArvoreContato().setInFolha(request.getParameter("inFolha"));
				getFormArvoreContato().setDsPath(request.getParameter("dsPath"));
				getFormArvoreContato().setSalvaedita(request.getParameter("salvaedita"));

				request.setAttribute("action", "criarEditarItemContato");

			} else {

				if (ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("getDadosPalitagem"))) {

					destino = "configuracaoPalitagem";

					AdmContatoPalitagemVO admContatoPalitagemVO = AdmContatoPalitagemVO.Factory.newInstance();
					admContatoPalitagemVO.setAcao("carregamentoInicial");
					admContatoPalitagemVO.setIdContato(getFormArvoreContato().getIdContato());

					admContatoPalitagemVO = palitagemFacade.getAdmContatoPalitagemVO(idUsuario, admContatoPalitagemVO);

					AdmContatoPalitagemVO admContatoPalitagemNewObj = AdmContatoPalitagemVO.Factory.newInstance();
					admContatoPalitagemNewObj.addNewPalitagem();
					admContatoPalitagemNewObj.getPalitagemArray(0).addNewAdmProcedenciaVO();
					admContatoPalitagemNewObj.getPalitagemArray(0).addNewServico();
					admContatoPalitagemNewObj.getPalitagemArray(0).addNewSistemaVO();

					getFormArvoreContato().setAdmContatoPalitagemVO(admContatoPalitagemVO);

					getFormArvoreContato().setDadosPalitagem(admContatoPalitagemNewObj.getPalitagemArray(0));

				} else {
					destino = "configuracaoFolha";
					request.setCharacterEncoding(ConstantesCRM.SISO);
					getFormArvoreContato().setSalvaedita(getFormArvoreContato().getSalvaedita());
					selectsFolhaContato(getFormArvoreContato().getIdContato(), (ConstantesCRM.SONE.equals(getFormArvoreContato().getInFolha())) ? ConstantesCRM.SZERO : ConstantesCRM.SONE, request);
				}

			}

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:criarEditarItemContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			getFormArvoreContato().setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:criarEditarItemContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoContatoPalitagem.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward managePalitagem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		FormArvoreContato form = (FormArvoreContato) formParam;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		AdmContatoPalitagemVO admContatoPalitagemVO = AdmContatoPalitagemVO.Factory.newInstance();
		admContatoPalitagemVO.setIdContato(getFormArvoreContato().getIdContato());
		String verbo = ConstantesCRM.SVAZIO;
		int indexPalitagem;

		try {

			if ("loadPalitagem".equals(form.getAdmContatoPalitagemVO().getAcao())) {
				indexPalitagem = Integer.parseInt(request.getParameter("indexPalitagem"));
				getFormArvoreContato().setDadosPalitagem(getFormArvoreContato().getAdmContatoPalitagemVO().getPalitagemArray(indexPalitagem));
				getFormArvoreContato().getAdmContatoPalitagemVO().setAcao("alteracao");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			} else if ("unsetPalitagem".equals(form.getAdmContatoPalitagemVO().getAcao())) {
				indexPalitagem = Integer.parseInt(request.getParameter("indexPalitagem"));
				admContatoPalitagemVO.setAcao("exclusao");
				admContatoPalitagemVO.addNewPalitagem();
				admContatoPalitagemVO.setPalitagemArray(0, getFormArvoreContato().getAdmContatoPalitagemVO().getPalitagemArray(indexPalitagem));
				request.setAttribute("msgRetorno", "Registro excluído com sucesso!");
			}

			// Inclusão e alteração
			else {
				admContatoPalitagemVO.setAcao(form.getAdmContatoPalitagemVO().getAcao());
				admContatoPalitagemVO.addNewPalitagem().set(form.getDadosPalitagem().copy());
				verbo = ("inclusao".equals(admContatoPalitagemVO.getAcao())) ? "inseridos" : "alterados";
				request.setAttribute("msgRetorno", "Dados " + verbo + " com sucesso!");
			}

			palitagemFacade.setAdmContatoPalitagemVO(idUsuario, admContatoPalitagemVO);

			// Recarregamento de dados após gravação ou exclusão de palitagem
			if (!form.getAdmContatoPalitagemVO().getAcao().equals("loadPalitagem")) {

				admContatoPalitagemVO = AdmContatoPalitagemVO.Factory.newInstance();
				admContatoPalitagemVO.setAcao("carregamentoInicial");
				admContatoPalitagemVO.setIdContato(getFormArvoreContato().getIdContato());

				admContatoPalitagemVO = palitagemFacade.getAdmContatoPalitagemVO(idUsuario, admContatoPalitagemVO);

				getFormArvoreContato().setAdmContatoPalitagemVO(admContatoPalitagemVO);
				getFormArvoreContato().getAdmContatoPalitagemVO().setAcao(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setDadosPalitagem(Palitagem.Factory.newInstance());
				getFormArvoreContato().getDadosPalitagem().addNewAdmProcedenciaVO();
				getFormArvoreContato().getDadosPalitagem().addNewServico();
				getFormArvoreContato().getDadosPalitagem().addNewSistemaVO();

			}

			getFormArvoreContato().setAdmContatoPalitagemVO(admContatoPalitagemVO);

		} catch (Exception e) {

			if (e.getMessage().matches("^.*14E0001.*$")) {
				request.setAttribute("msgRetorno", e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				getFormArvoreContato().setDadosPalitagem(admContatoPalitagemVO.getPalitagemArray(0));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			} else if (e.getMessage().matches("^.*86E0001.*$")) {
				request.setAttribute("msgRetorno", "Não é possível realizar a alteração pois o código do serviço já existe cadastrado para este sistema.");
				getFormArvoreContato().setDadosPalitagem(admContatoPalitagemVO.getPalitagemArray(0));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			request.removeAttribute("msgRetorno");
			log.error("AdmArvoreContatoController:managePalitagem" + "( " + idUsuario + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="indexItemContato.jsp"
	 * @jpf:forward name="configuracaoFolha" path="criarEditarItemContato.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward criarInserirItemContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreContatoController:criarInserirItemContato(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		String destino = ConstantesCRM.SVAZIO;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			boolean inInicio = (request.getParameter("inInicio") != null) ? true : false;

			if (inInicio) {
				destino = ConstantesCRM.SUCCESS;

				getFormArvoreContato().setIdContato(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setNomeTipo(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setMsg(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setNmContato(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setSgFluxoAtendimento(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setSgRegraEncaminhamento(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setInDisponibilidade(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setIdNomeContato(request.getParameter("idNomeContato"));
				getFormArvoreContato().setNrNivel(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setInFolha(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setIdMensagemAtual(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setIdTipoRetornoContatoAtual(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setIdNomeContatoEscolhido("-1");
				getFormArvoreContato().setInDisponibilidadeNovo(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setIdTipoRetornoContatoAtual(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setMensagem(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setMensagemNova(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setIdPagina(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setFechamento(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setQtDiasPrazoContato(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setQtDiasPrazoAnatel(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setProcessoTec(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setVlPesoContato(ConstantesCRM.SVAZIO);
				getFormArvoreContato().setIdContatoPai(request.getParameter("idContatoPai"));
				getFormArvoreContato().setIdContato(request.getParameter("idContato"));
				getFormArvoreContato().setDsPath(request.getParameter("dsPath"));
				getFormArvoreContato().setNmContato(request.getParameter("nmContato"));
				getFormArvoreContato().setSalvaedita(request.getParameter("salvaedita"));

				request.setAttribute("action", "criarInserirItemContato");

			} else {

				destino = "configuracaoFolha";
				request.setCharacterEncoding(ConstantesCRM.SISO);
				request.setAttribute("dsPath", StringUtilStatic.escapaComillasJS2(getFormArvoreContato().getDsPath()));
				request.setAttribute("nmContato", StringUtilStatic.escapaComillasJS2(getFormArvoreContato().getNmContato()));
				selectsFolhaContato(ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, request);

				// Inclusão de folha
				if (ConstantesCRM.SZERO.equals(getFormArvoreContato().getInFolha())) {
					getFormArvoreContato().setInFolha(ConstantesCRM.SVAZIO);
					getFormArvoreContato().setInDisponibilidade("null");
					getFormArvoreContato().setNrNivel("null");
				}
			}

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:criarInserirItemContato'( " + idUsuario + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward habilitaDesabilitaArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreContatoController:habilitaDesabilitaArvore(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		FormArvoreContato form = (FormArvoreContato) formParam;
		try {
			AdmContatoFolhaVO contatoFolhaVO = AdmContatoFolhaVO.Factory.newInstance();
			// Se habilitado --> Desebilita.
			if (form.getInDisponibilidade() != null && form.getInDisponibilidade().trim().equals(ConstantesCRM.SONE)) {
				contatoFolhaVO.setInDisponibilidade(ConstantesCRM.SZERO);
			} else if (form.getInDisponibilidade() != null && form.getInDisponibilidade().trim().equals(ConstantesCRM.SZERO)) {
				contatoFolhaVO.setInDisponibilidade(ConstantesCRM.SONE);
			}

			contatoFolhaVO.setIdContato(form.getIdContato());
			this.controlArvore.habilitaArvoreContato(contatoFolhaVO, ConstantesCRM.getCurrentUser(request.getSession()));
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:habilitaDesabilitaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreContato.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:habilitaDesabilitaArvore'" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward copiaArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreContatoController:copiaArvore(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		FormArvoreContato form = (FormArvoreContato) formParam;
		try {
			AdmArvoreContatoCopiaVO admArvoreContatoCopiaVO = AdmArvoreContatoCopiaVO.Factory.newInstance();
			admArvoreContatoCopiaVO.setIdContatoDestino(form.getIdContatoDestino());
			admArvoreContatoCopiaVO.setIdContatoOrigem(form.getIdContatoOrigem());
			admArvoreContatoCopiaVO.setQtdCopia(form.getQtdCopia());
			this.controlArvore.copiaArvoreContato(admArvoreContatoCopiaVO, ConstantesCRM.getCurrentUser(request.getSession()));

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:copiaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreContato.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:copiaArvore'" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserContato.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getNome(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreContatoController:getNome(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		FormArvoreContato form = (FormArvoreContato) formParam;
		try {
			AdmIdContatoVO admIdContato = AdmIdContatoVO.Factory.newInstance();
			admIdContato.setNmContato(form.getDescPesquisa());

			getFormArvoreContato().setAdmNomeContatoVO(this.controlArvore.getArvoreNome(admIdContato, ConstantesCRM.getCurrentUser(request.getSession())).getAdmNomeContatoVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:getNome" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreContato.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:getNome'" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class FormArvoreContato extends ActionForm {

		private static final long serialVersionUID = 3855531192250050213L;
		private String[] arrayAdmGruposExistentes;
		private String[] arrayAdmGruposAssociados;
		private AdmGrupoVO[] admGruposExistentesVO;
		private AdmGrupoVO[] admGruposAssociadosVO;
		private br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoAnatelVO[] admPrazoAnatel;
		private String qtDiasPrazoAnatel;
		// private String nomeContato;
		private String descPesquisa;
		private String qtdCopia;
		private br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrioridadeVO[] admPrioridade;
		private br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoVO[] admPrazo;
		private AdmClassificacaoSMSVO[] listaClassificoesSMS;
		private String idContatoDestino;
		private String idContatoOrigem;
		private String msgError = ConstantesCRM.SVAZIO;
		private String mensagemNova;
		private String salvaedita;
		private String idPagina;
		private String dsPath;
		private String inPermitirParametrizacao;
		private AdmTipoProcessoVO[] listaProcesso;
		private AdmTipoRetornoContatoVO[] listaRetorno;
		private String idMensagemAtual;
		private String idTipoRetornoContatoAtual;
		private String inFolha;
		private String nomeTipo;
		private String msg;
		private AdmIndicadorAnatelVO[] admIndicadoresAnatelExistentesVO;
		private AdmIndicadorAnatelVO[] admIndicadoresAnatelAssociadosVO;
		private String[] arrayAdmIndicadorAnatelExistente;
		private String[] arrayAdmIndicadorAnatelAssociado;
		private AdmTipoFechamentoVO[] listaFechamento;
		private AdmMensagemAvisoVO[] listaMensagem;
		private String vlPesoContato;
		private String qtDiasPrazoContato;
		private String idClassificacaoSMS;
		private String processoTec;
		private String mensagem;
		private String fechamento;
		private String inDisponibilidadeNovo;
		private String idNomeContatoEscolhido;
		private String[] arrayAdmNomeContato;
		private String nrNivel;
		private String inDisponibilidade;
		private String nmContato;
		private String idContatoPai;
		private String idNomeContato;
		private String sgRegraEncaminhamento;
		private String sgFluxoAtendimento;
		private AdmNomeContatoVO[] admNomeContatoVO;
		private String idContato;
		private AdmArvoreContainerVO arvoreContato;

		private String inProtocolo;
		private String inRelacionamento;
		private String dsContatoCanais;
		private String dsMsgExcecao;
		private String inSMS;
		private boolean inComprovanteCancelamento;

		private AdmContatoPalitagemVO admContatoPalitagemVO;
		private Palitagem dadosPalitagem;

		public void setInPermitirParametrizacao(String inPermitirParametrizacao) {
			this.inPermitirParametrizacao = inPermitirParametrizacao;
		}

		public String getInPermitirParametrizacao() {
			return this.inPermitirParametrizacao;
		}

		public void setInProtocolo(String inProtocolo) {
			this.inProtocolo = inProtocolo;
		}

		public String getInProtocolo() {
			return this.inProtocolo;
		}

		public void setInComprovanteCancelamento(boolean arg1) {
			this.inComprovanteCancelamento = arg1;
		}

		public boolean getInComprovanteCancelamento() {
			return this.inComprovanteCancelamento;
		}

		public void setInRelacionamento(String inRelacionamento) {
			this.inRelacionamento = inRelacionamento;
		}

		public String getInRelacionamento() {
			return this.inRelacionamento;
		}

		public void setDsContatoCanais(String dsContatoCanais) {
			this.dsContatoCanais = dsContatoCanais;
		}

		public String getDsContatoCanais() {
			return this.dsContatoCanais;
		}

		public void setDsMsgExcecao(String dsMsgExcecao) {
			this.dsMsgExcecao = dsMsgExcecao;
		}

		public String getDsMsgExcecao() {
			if (this.dsMsgExcecao == null) {
				this.dsMsgExcecao = ConstantesCRM.SVAZIO;
			}
			return this.dsMsgExcecao;
		}

		public void setInSMS(String inSMS) {
			this.inSMS = inSMS;
		}

		public String getInSMS() {
			return this.inSMS;
		}

		public FormArvoreContato() {
			arvoreContato = AdmArvoreContainerVO.Factory.newInstance();
		}

		public void setArvoreContato(AdmArvoreContainerVO arvoreContato) {
			this.arvoreContato = arvoreContato;
		}

		public AdmArvoreContainerVO getArvoreContato() {
			return this.arvoreContato;
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setAdmNomeContatoVO(AdmNomeContatoVO[] admNomeContatoVO) {
			this.admNomeContatoVO = admNomeContatoVO;
		}

		public AdmNomeContatoVO[] getAdmNomeContatoVO() {
			return this.admNomeContatoVO;
		}

		public void setIdNomeContato(String idNomeContato) {
			this.idNomeContato = idNomeContato;
		}

		public String getIdNomeContato() {
			return this.idNomeContato;
		}

		public void setSgRegraEncaminhamento(String arg) {
			this.sgRegraEncaminhamento = arg;
		}

		public String getSgRegraEncaminhamento() {
			if (this.sgRegraEncaminhamento == null) {
				this.sgRegraEncaminhamento = ConstantesCRM.SVAZIO;
			}
			return this.sgRegraEncaminhamento;
		}

		public void setSgFluxoAtendimento(String arg) {
			this.sgFluxoAtendimento = arg;
		}

		public String getSgFluxoAtendimento() {
			if (this.sgFluxoAtendimento == null) {
				this.sgFluxoAtendimento = ConstantesCRM.SVAZIO;
			}
			return this.sgFluxoAtendimento;
		}

		public void setIdContatoPai(String idContatoPai) {
			this.idContatoPai = idContatoPai;
		}

		public String getIdContatoPai() {
			return this.idContatoPai;
		}

		public void setNmContato(String nmContato) {
			this.nmContato = nmContato;
		}

		public String getNmContato() {
			return this.nmContato;
		}

		public void setInDisponibilidade(String inDisponibilidade) {
			this.inDisponibilidade = inDisponibilidade;
		}

		public String getInDisponibilidade() {
			return this.inDisponibilidade;
		}

		public void setNrNivel(String nrNivel) {
			this.nrNivel = nrNivel;
		}

		public String getNrNivel() {
			return this.nrNivel;
		}

		public void setArrayAdmNomeContato(String[] arrayAdmNomeContato) {
			this.arrayAdmNomeContato = arrayAdmNomeContato;
		}

		public String[] getArrayAdmNomeContato() {
			if (this.arrayAdmNomeContato == null || this.arrayAdmNomeContato.length == 0) {
				this.arrayAdmNomeContato = new String[1];
			}
			return this.arrayAdmNomeContato;
		}

		public void setIdNomeContatoEscolhido(String idNomeContatoEscolhido) {
			this.idNomeContatoEscolhido = idNomeContatoEscolhido;
		}

		public String getIdNomeContatoEscolhido() {
			return this.idNomeContatoEscolhido;
		}

		public void setInDisponibilidadeNovo(String inDisponibilidadeNovo) {
			this.inDisponibilidadeNovo = inDisponibilidadeNovo;
		}

		public String getInDisponibilidadeNovo() {
			return this.inDisponibilidadeNovo;
		}

		public void setFechamento(String fechamento) {
			this.fechamento = fechamento;
		}

		public String getFechamento() {
			return this.fechamento;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

		public String getMensagem() {
			return this.mensagem;
		}

		public void setProcessoTec(String processoTec) {
			this.processoTec = processoTec;
		}

		public String getProcessoTec() {
			return this.processoTec;
		}

		public void setQtDiasPrazoContato(String qtDiasPrazoContato) {
			this.qtDiasPrazoContato = qtDiasPrazoContato;
		}

		public String getQtDiasPrazoContato() {
			return this.qtDiasPrazoContato;
		}

		public void setIdClassificacaoSMS(String arg) {
			this.idClassificacaoSMS = arg;
		}

		public String getIdClassificacaoSMS() {
			if (this.idClassificacaoSMS == null) {
				this.idClassificacaoSMS = ConstantesCRM.SVAZIO;
			}
			return this.idClassificacaoSMS;
		}

		public void setVlPesoContato(String vlPesoContato) {
			this.vlPesoContato = vlPesoContato;
		}

		public String getVlPesoContato() {
			return this.vlPesoContato;
		}

		public void setListaMensagem(AdmMensagemAvisoVO[] listaMensagem) {
			this.listaMensagem = listaMensagem;
		}

		public AdmMensagemAvisoVO[] getListaMensagem() {
			return this.listaMensagem;
		}

		public void setListaFechamento(AdmTipoFechamentoVO[] listaFechamento) {
			this.listaFechamento = listaFechamento;
		}

		public AdmTipoFechamentoVO[] getListaFechamento() {
			return this.listaFechamento;
		}

		public void setArrayAdmIndicadorAnatelAssociado(String[] arrayAdmIndicadorAnatelAssociado) {
			this.arrayAdmIndicadorAnatelAssociado = arrayAdmIndicadorAnatelAssociado;
		}

		public String[] getArrayAdmIndicadorAnatelAssociado() {
			if (this.arrayAdmIndicadorAnatelAssociado == null || this.arrayAdmIndicadorAnatelAssociado.length == 0) {
				this.arrayAdmIndicadorAnatelAssociado = new String[1];
			}
			return this.arrayAdmIndicadorAnatelAssociado;
		}

		public void setArrayAdmIndicadorAnatelExistente(String[] arrayAdmIndicadorAnatelExistente) {
			this.arrayAdmIndicadorAnatelExistente = arrayAdmIndicadorAnatelExistente;
		}

		public String[] getArrayAdmIndicadorAnatelExistente() {
			if (this.arrayAdmIndicadorAnatelExistente == null || this.arrayAdmIndicadorAnatelExistente.length == 0) {
				this.arrayAdmIndicadorAnatelExistente = new String[1];
			}
			return this.arrayAdmIndicadorAnatelExistente;
		}

		public void setAdmIndicadoresAnatelAssociadosVO(AdmIndicadorAnatelVO[] admIndicadoresAnatelAssociadosVO) {
			this.admIndicadoresAnatelAssociadosVO = admIndicadoresAnatelAssociadosVO;
		}

		public AdmIndicadorAnatelVO[] getAdmIndicadoresAnatelAssociadosVO() {
			return this.admIndicadoresAnatelAssociadosVO;
		}

		public void setAdmIndicadoresAnatelExistentesVO(AdmIndicadorAnatelVO[] admIndicadoresAnatelExistentesVO) {
			this.admIndicadoresAnatelExistentesVO = admIndicadoresAnatelExistentesVO;
		}

		public AdmIndicadorAnatelVO[] getAdmIndicadoresAnatelExistentesVO() {
			return this.admIndicadoresAnatelExistentesVO;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			if (this.msg == null) {
				this.msg = ConstantesCRM.SVAZIO;
			}
			return this.msg;
		}

		public void setNomeTipo(String nomeTipo) {
			this.nomeTipo = nomeTipo;
		}

		public String getNomeTipo() {
			if (this.nomeTipo == null) {
				this.nomeTipo = ConstantesCRM.SVAZIO;
			}
			return this.nomeTipo;
		}

		public void setInFolha(String inFolha) {
			this.inFolha = inFolha;
		}

		public String getInFolha() {
			if (this.inFolha == null) {
				this.inFolha = ConstantesCRM.SVAZIO;
			}
			return this.inFolha;
		}

		public void setIdTipoRetornoContatoAtual(String idTipoRetornoContatoAtual) {
			this.idTipoRetornoContatoAtual = idTipoRetornoContatoAtual;
		}

		public String getIdTipoRetornoContatoAtual() {
			if (this.idTipoRetornoContatoAtual == null) {
				this.idTipoRetornoContatoAtual = ConstantesCRM.SVAZIO;
			}
			return this.idTipoRetornoContatoAtual;
		}

		public void setIdMensagemAtual(String idMensagemAtual) {
			this.idMensagemAtual = idMensagemAtual;
		}

		public String getIdMensagemAtual() {
			if (this.idMensagemAtual == null) {
				this.idMensagemAtual = ConstantesCRM.SVAZIO;
			}
			return this.idMensagemAtual;
		}

		public void setListaRetorno(AdmTipoRetornoContatoVO[] listaRetorno) {
			this.listaRetorno = listaRetorno;
		}

		public AdmTipoRetornoContatoVO[] getListaRetorno() {
			return this.listaRetorno;
		}

		public void setListaProcesso(AdmTipoProcessoVO[] listaProcesso) {
			this.listaProcesso = listaProcesso;
		}

		public AdmTipoProcessoVO[] getListaProcesso() {
			return this.listaProcesso;
		}

		public void setDsPath(String dsPath) {
			this.dsPath = dsPath;
		}

		public String getDsPath() {
			if (this.dsPath == null) {
				this.dsPath = ConstantesCRM.SVAZIO;
			}
			return this.dsPath;
		}

		public void setIdPagina(String idPagina) {
			this.idPagina = idPagina;
		}

		public String getIdPagina() {
			if (this.idPagina == null) {
				this.idPagina = ConstantesCRM.SVAZIO;
			}
			return this.idPagina;
		}

		public void setSalvaedita(String salvaedita) {
			this.salvaedita = salvaedita;
		}

		public String getSalvaedita() {
			if (this.salvaedita == null) {
				this.salvaedita = ConstantesCRM.SVAZIO;
			}
			return this.salvaedita;
		}

		public void setMensagemNova(String mensagemNova) {
			this.mensagemNova = mensagemNova;
		}

		public String getMensagemNova() {
			if (this.mensagemNova == null) {
				this.mensagemNova = ConstantesCRM.SVAZIO;
			}
			return this.mensagemNova;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			if (this.msgError == null) {
				this.msgError = ConstantesCRM.SVAZIO;
			}
			return this.msgError;
		}

		public void setIdContatoOrigem(String idContatoOrigem) {
			this.idContatoOrigem = idContatoOrigem;
		}

		public String getIdContatoOrigem() {
			if (this.idContatoOrigem == null) {
				this.idContatoOrigem = ConstantesCRM.SVAZIO;
			}
			return this.idContatoOrigem;
		}

		public void setIdContatoDestino(String idContatoDestino) {
			this.idContatoDestino = idContatoDestino;
		}

		public String getIdContatoDestino() {
			if (this.idContatoDestino == null) {
				this.idContatoDestino = ConstantesCRM.SVAZIO;
			}
			return this.idContatoDestino;
		}

		public void setAdmPrazo(br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoVO[] admPrazo) {
			this.admPrazo = admPrazo;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoVO[] getAdmPrazo() {
			return this.admPrazo;
		}

		public void setAdmPrioridade(br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrioridadeVO[] admPrioridade) {
			this.admPrioridade = admPrioridade;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrioridadeVO[] getAdmPrioridade() {
			return this.admPrioridade;
		}

		public void setListaClassificoesSMS(AdmClassificacaoSMSVO[] arg) {
			this.listaClassificoesSMS = arg;
		}

		public AdmClassificacaoSMSVO[] getListaClassificoesSMS() {
			if (this.listaClassificoesSMS == null) {
				this.listaClassificoesSMS = new AdmClassificacaoSMSVO[0];
			}
			return this.listaClassificoesSMS;
		}

		public void setQtdCopia(String qtdCopia) {
			this.qtdCopia = qtdCopia;
		}

		public String getQtdCopia() {
			if (this.qtdCopia == null) {
				this.qtdCopia = ConstantesCRM.SVAZIO;
			}
			return this.qtdCopia;
		}

		public void setDescPesquisa(String descPesquisa) {
			this.descPesquisa = descPesquisa;
		}

		public String getDescPesquisa() {
			if (this.descPesquisa == null) {
				this.descPesquisa = "";
			}
			return this.descPesquisa;
		}

		public void setQtDiasPrazoAnatel(String qtDiasPrazoAnatel) {
			this.qtDiasPrazoAnatel = qtDiasPrazoAnatel;
		}

		public String getQtDiasPrazoAnatel() {
			return this.qtDiasPrazoAnatel;
		}

		public void setAdmPrazoAnatel(br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoAnatelVO[] admPrazoAnatel) {
			this.admPrazoAnatel = admPrazoAnatel;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoAnatelVO[] getAdmPrazoAnatel() {
			return this.admPrazoAnatel;
		}

		public void setAdmGruposAssociadosVO(AdmGrupoVO[] admGruposAssociadosVO) {
			this.admGruposAssociadosVO = admGruposAssociadosVO;
		}

		public AdmGrupoVO[] getAdmGruposAssociadosVO() {
			return this.admGruposAssociadosVO;
		}

		public void setAdmGruposExistentesVO(AdmGrupoVO[] admGruposExistentesVO) {
			this.admGruposExistentesVO = admGruposExistentesVO;
		}

		public AdmGrupoVO[] getAdmGruposExistentesVO() {
			return this.admGruposExistentesVO;
		}

		public void setArrayAdmGruposAssociados(String[] arrayAdmGruposAssociados) {
			this.arrayAdmGruposAssociados = arrayAdmGruposAssociados;
		}

		public String[] getArrayAdmGruposAssociados() {
			if (this.arrayAdmGruposAssociados == null || this.arrayAdmGruposAssociados.length == 0) {
				this.arrayAdmGruposAssociados = new String[1];
			}
			return this.arrayAdmGruposAssociados;
		}

		public void setArrayAdmGruposExistentes(String[] arrayAdmGruposExistentes) {
			this.arrayAdmGruposExistentes = arrayAdmGruposExistentes;
		}

		public String[] getArrayAdmGruposExistentes() {
			if (this.arrayAdmGruposExistentes == null || this.arrayAdmGruposExistentes.length == 0) {
				this.arrayAdmGruposExistentes = new String[1];
			}
			return this.arrayAdmGruposExistentes;
		}

		public AdmContatoPalitagemVO getAdmContatoPalitagemVO() {
			if (this.admContatoPalitagemVO == null) {
				this.admContatoPalitagemVO = AdmContatoPalitagemVO.Factory.newInstance();
			}
			return this.admContatoPalitagemVO;
		}

		public void setAdmContatoPalitagemVO(AdmContatoPalitagemVO arg1) {
			this.admContatoPalitagemVO = arg1;
		}

		public Palitagem getDadosPalitagem() {
			if (this.dadosPalitagem == null) {
				this.dadosPalitagem = Palitagem.Factory.newInstance();
				this.dadosPalitagem.addNewAdmProcedenciaVO();
				this.dadosPalitagem.addNewSistemaVO();
				this.dadosPalitagem.addNewServico();
			}
			return this.dadosPalitagem;
		}

		public void setDadosPalitagem(Palitagem arg1) {
			this.dadosPalitagem = arg1;
		}

	}

	public FormArvoreContato getFormArvoreContato() {
		if (this.formArvoreContato == null) {
			this.formArvoreContato = new FormArvoreContato();
		}
		return this.formArvoreContato;
	}

	public AdmContatoFolhaVO getContatoById(String id, AdmContatoFolhaVO[] listaContato) {
		for (int i = 0; i < listaContato.length; i++) {
			if (listaContato[i].getIdContato().equals(id)) {
				return listaContato[i];
			}
		}
		return null;
	}
}
