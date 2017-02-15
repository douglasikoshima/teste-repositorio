package admsistemas.admArvoreVendas;

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
import br.com.vivo.fo.admsistemas.vo.AdmGrupoVODocument.AdmGrupoVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmIndicadorAnatelVODocument.AdmIndicadorAnatelVO;
import br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO;
import br.com.vivo.fo.admsistemas.vo.AdmNomeContatoVODocument.AdmNomeContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoArvoreVODocument.AdmTipoArvoreVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoFechamentoVODocument.AdmTipoFechamentoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoProcessoVODocument.AdmTipoProcessoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoRetornoContatoVODocument.AdmTipoRetornoContatoVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AdmArvoreVendasController extends AbstractAction {

	private static final long serialVersionUID = 1331538352306116954L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.configArvoreContato.ArvoreContato controlArvore;

	private FormArvoreContato formArvoreContato = null;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	private String idTipoArvore = null;

	private static String ARVORE_DE_VENDAS = "ÁRVORE DE VENDAS";

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
		} else if ("criarInserirItemContato".equals(mapping.getParameter())) {
			return criarInserirItemContato(mapping, form, request, response);
		} else if ("habilitaDesabilitaArvore".equals(mapping.getParameter())) {
			return habilitaDesabilitaArvore(mapping, form, request, response);
		} else if ("copiaArvore".equals(mapping.getParameter())) {
			return copiaArvore(mapping, form, request, response);
		} else if ("getNome".equals(mapping.getParameter())) {
			return getNome(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
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

			AdmTipoArvoreVO admTipoArvore = controlArvore.obterTipoArvore(ConstantesCRM.getCurrentUser(request.getSession()));

			for (int i = 0; i < admTipoArvore.getTipoArvoreArray().length; i++) {
				if (ARVORE_DE_VENDAS.equals(admTipoArvore.getTipoArvoreArray(i).getDsTipoArvore())) {
					idTipoArvore = admTipoArvore.getTipoArvoreArray(i).getIdTipoArvore();
					break;
				}
			}

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

		formArvoreContato.setListaFechamento(admSelectsContatoFolhaVO.getAdmTipoFechamentoVOArray());
		formArvoreContato.setListaMensagem(admSelectsContatoFolhaVO.getAdmMensagemAvisoVOArray());
		formArvoreContato.setListaProcesso(admSelectsContatoFolhaVO.getAdmTipoProcessoVOArray());
		formArvoreContato.setListaRetorno(admSelectsContatoFolhaVO.getAdmTipoRetornoContatoVOArray());
		formArvoreContato.setAdmIndicadoresAnatelExistentesVO(admSelectsContatoFolhaVO.getFolha().getAdmIndicadoresAnatelContatoContainerVO().getIndicadoresExistentes().getAdmIndicadorAnatelVOArray());
		formArvoreContato.setAdmIndicadoresAnatelAssociadosVO(admSelectsContatoFolhaVO.getFolha().getAdmIndicadoresAnatelContatoContainerVO().getIndicadoresAssociados().getAdmIndicadorAnatelVOArray());
		// formArvoreContato.setAdmGruposExistentesVO(admSelectsContatoFolhaVO.getFolha().getAdmGrupoContatoContainerVO().getGruposExistentes().getAdmGrupoVOArray());
		// formArvoreContato.setAdmGruposAssociadosVO(admSelectsContatoFolhaVO.getFolha().getAdmGrupoContatoContainerVO().getGruposAssociados().getAdmGrupoVOArray());

		if (contatoId.equals(ConstantesCRM.SZERO) || contatoId == null || contatoId.equals(ConstantesCRM.SVAZIO) || inPasta.equals(ConstantesCRM.SZERO)) {
			formArvoreContato.setInFolha(ConstantesCRM.SONE);
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
			formArvoreContato.setInFolha(ConstantesCRM.SZERO);
		}

		formArvoreContato.setIdContato(request.getParameter("idContato"));
		formArvoreContato.setNmContato(request.getParameter("nmContato"));

		if (request.getParameter("nmContato") != null) {
			request.setAttribute("nmContato", StringUtilStatic.escapaComillasJS2(request.getParameter("nmContato")));
		}

		formArvoreContato.setIdContatoPai(request.getParameter("idContatoPai"));
		formArvoreContato.setInDisponibilidade(request.getParameter("inDisponibilidade"));
		formArvoreContato.setIdNomeContato(request.getParameter("idNomeContato"));
		formArvoreContato.setNrNivel(request.getParameter("nrNivel"));
		formArvoreContato.setInFolha(request.getParameter("inFolha"));
		formArvoreContato.setDsPath(request.getParameter("dsPath"));

		if (request.getParameter("dsPath") != null) {
			request.setAttribute("dsPath", StringUtilStatic.escapaComillasJS2(request.getParameter("dsPath")));
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterArvoreVendas.jsp"
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
			admidContato.setIdTipoArvore(idTipoArvore);

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

			formArvoreContato.setAdmNomeContatoVO(admContatoFolhaVO.getAdmNomeContatoVOArray());
			formArvoreContato.setArvoreContato(admContatoFolhaVO);
			StringBuffer script = new StringBuffer();
			StringBuffer node = new StringBuffer();
			String folder = ConstantesCRM.SVAZIO;
			script.append("if (document.getElementById) { var tree = new WebFXTree('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getNmContato())).append("',\"Javascript:capturaParametrosContatoRaiz('").append(admContatoFolhaVO.getAdmContatoFolhaVO().getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getNmContato())).append("','")
			.append(admContatoFolhaVO.getAdmContatoFolhaVO().getIdContatoPai()).append("','").append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getIdNomeContato()).append("','").append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getDsPath().toString())).append("','").append("');").append("\",'','');");
			for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray().length; i++) {
				boolean inFolha = false;
				if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha().trim().equals("1")) {
					folder = "',\"Javascript:capturaParametrosContato('";
				} else {
					folder = "',\"Javascript:expandirNo('";
				}
				if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha().trim().equals("1")) {
					inFolha = true;
				}
				if (inFolha) {
					node.append("tmpArvore = new WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdNomeContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNrNivel()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getDsPath().toString())).append("','");
					if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals("1")) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');");
					}
				} else {
					node.append("tmpArvore = new WebFXTreeItem('" + StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdNomeContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNrNivel()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getDsPath().toString())).append("','");
					if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals("1")) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');");
					}
				}
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

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iframeArvoreVendas.jsp"
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
			admidContato.setIdTipoArvore(idTipoArvore);

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
			StringBuffer node = new StringBuffer();
			String folder = ConstantesCRM.SVAZIO;

			for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray().length; i++) {

				boolean inFolha = false;

				if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha().trim().equals(ConstantesCRM.SONE)) {
					folder = "',\"Javascript:capturaParametrosContato('";
				} else {
					folder = "',\"Javascript:expandirNo('";
				}

				if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha().trim().equals(ConstantesCRM.SONE)) {
					inFolha = true;
				}

				if (inFolha) {
					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdNomeContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNrNivel()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getDsPath())).append("','");

					if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals("1")) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');");
					}

				} else {
					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdNomeContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNrNivel()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getDsPath())).append("','");

					if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals("1")) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');");
					}
				}
				script.append(node).append("parent.tree.getSelected().add(parent.tmpArvore);\n\n");
			}

			script.append("parent.tree.getSelected().setAddEnabled(false);\n\n").append("parent.tree.getSelected().expand()\n\n");
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
		log.debug("AdmArvoreContatoController:removeItem(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		FormArvoreContato form = (FormArvoreContato) formParam;
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
			// REGEX.
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
			admContatoFolhaInserirVO.setIdTipoArvore(idTipoArvore);

			log.debug("insereItem(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") idContato = " + form.getIdContato());

			admContatoFolhaInserirVO.setInDisponibilidade(form.getInDisponibilidadeNovo());
			admContatoFolhaInserirVO.setNrNivel(form.getNrNivel());
			admContatoFolhaInserirVO.setIdContato(form.getIdContato());

			admContatoFolhaInserirVO.addNewSmpVO().addNewCanais();
			admContatoFolhaInserirVO.getSmpVO().addNewSms().setInSMS(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInProtocolo(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInRelacionamento(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInExibeProtocolo(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInProtocolo(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInComprovanteCancelamento(ConstantesCRM.SZERO);

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
				// admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoRetornoAtual(form.getIdTipoRetornoContatoAtual());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoRetornoAtual(ConstantesCRM.SZERO);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdMensagemAviso(form.getMensagem());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setNmMensagemAviso(form.getMensagemNova());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoFechamentoAtual(form.getFechamento());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoProcessoAtual(form.getProcessoTec());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setInProcessoTecnico(ConstantesCRM.SZERO);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setQtDiasPrazoContato(form.getQtDiasPrazoContato());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setVlPrazoAnatel(form.getQtDiasPrazoAnatel());
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

		log.debug("AdmArvoreContatoController:salvaItemEditado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		try {
			FormArvoreContato form = (FormArvoreContato) formParam;
			request.setCharacterEncoding(ConstantesCRM.SISO);
			AdmContatoFolhaVO admContatoFolhaInserirVO = AdmContatoFolhaVO.Factory.newInstance();

			admContatoFolhaInserirVO.setIdContatoPai(form.getIdContatoPai());
			admContatoFolhaInserirVO.setInDisponibilidade(form.getInDisponibilidadeNovo());
			admContatoFolhaInserirVO.setNrNivel(form.getNrNivel());
			admContatoFolhaInserirVO.setIdContato(form.getIdContato());

			admContatoFolhaInserirVO.addNewSmpVO().addNewCanais();
			admContatoFolhaInserirVO.getSmpVO().addNewSms().setInSMS(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInProtocolo(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInRelacionamento(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInExibeProtocolo(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInProtocolo(ConstantesCRM.SZERO);
			admContatoFolhaInserirVO.getSmpVO().getCanais().setInComprovanteCancelamento(ConstantesCRM.SZERO);

			admContatoFolhaInserirVO.setIdTipoArvore(idTipoArvore);

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
			 * == null || request.getParameter("NomeContatoEscolhido").equals(""))) {
			 * admContatoFolhaInserirVO.setIdNomeContato(form.getIdNomeContato());
			 * admContatoFolhaInserirVO.setNmContato("");
			 * 
			 * }else if((request.getParameter("NomeContatoEscolhido")).equals("")) {
			 * admContatoFolhaInserirVO.setIdNomeContato(form.getIdNomeContatoEscolhido());
			 * admContatoFolhaInserirVO.setNmContato(""); }else { admContatoFolhaInserirVO.setIdNomeContato("");
			 * admContatoFolhaInserirVO.setNmContato(request.getParameter("NomeContatoEscolhido")); }
			 */

			admContatoFolhaInserirVO.setInFolha(form.getInFolha());

			if (form.getInFolha().equals(ConstantesCRM.SONE)) {
				admContatoFolhaInserirVO.addNewFolha().addNewAdmDadosBasicosVO();
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdPaginaAtual(form.getIdPagina());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoRetornoAtual(ConstantesCRM.SZERO);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdMensagemAviso(form.getMensagem());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setNmMensagemAviso(form.getMensagemNova());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoFechamentoAtual(form.getFechamento());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoProcessoAtual(form.getProcessoTec());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setInProcessoTecnico(ConstantesCRM.SZERO);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setQtDiasPrazoContato(form.getQtDiasPrazoContato());
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setVlPrazoAnatel(form.getQtDiasPrazoAnatel());
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
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoRetornoAtual(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdMensagemAviso(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdPaginaAtual(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoFechamentoAtual(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.getFolha().getAdmDadosBasicosVO().setIdTipoProcessoAtual(ConstantesCRM.SONE);
				admContatoFolhaInserirVO.addNewFolha().addNewAdmIndicadoresAnatelContatoContainerVO().addNewIndicadoresAssociados();
				admContatoFolhaInserirVO.addNewFolha().addNewAdmIndicadoresAnatelContatoContainerVO().addNewIndicadoresExistentes();
				admContatoFolhaInserirVO.addNewFolha().addNewAdmGrupoContatoContainerVO().addNewGruposAssociados();
				admContatoFolhaInserirVO.addNewFolha().addNewAdmGrupoContatoContainerVO().addNewGruposExistentes();
			}

			/*
			 * controlArvore.salvaItemArvoreContato(admContatoFolhaInserirVO,user); limpaCampos(); return
			 * mapping.findForward(ConstantesCRM.SUCCESS);
			 * 
			 * AdmContatoFolhaVO admContatoFolhaEditarVO = AdmContatoFolhaVO.Factory.newInstance();
			 * admContatoFolhaEditarVO.setIdContatoPai(form.getIdContatoPai());
			 * admContatoFolhaEditarVO.setIdContato(form.getIdContato());
			 * admContatoFolhaEditarVO.setInDisponibilidade(form.getInDisponibilidade());
			 * admContatoFolhaEditarVO.setNmContato(form.getNmContato());
			 * admContatoFolhaEditarVO.setIdNomeContato(form.getIdNomeContato());
			 * admContatoFolhaEditarVO.setNrNivel(form.getNrNivel());
			 * admContatoFolhaEditarVO.setInFolha(form.getInFolha());
			 */
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
	 * @jpf:forward name="success" path="criarEditarItemVendas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward criarEditarItemContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreContatoController:criarEditarItemContato(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		formArvoreContato.setMsgError(ConstantesCRM.SVAZIO);

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			String contatoId = request.getParameter("idContato");
			String salvaedita = request.getParameter("salvaedita");
			String inPasta = request.getParameter("inFolha");

			formArvoreContato.setSalvaedita(salvaedita);
			selectsFolhaContato(contatoId, inPasta, request);

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:criarEditarItemContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreContato.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:criarEditarItemContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="criarEditarItemVendas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward criarInserirItemContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreContatoController:criarInserirItemContato(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);

			formArvoreContato.setIdContato(ConstantesCRM.SVAZIO);
			formArvoreContato.setInFolha(ConstantesCRM.SVAZIO);
			formArvoreContato.setNmContato(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdContato(ConstantesCRM.SVAZIO);
			formArvoreContato.setInDisponibilidade(ConstantesCRM.SVAZIO);
			formArvoreContato.setNrNivel(ConstantesCRM.SVAZIO);
			formArvoreContato.setNomeTipo(ConstantesCRM.SVAZIO);
			formArvoreContato.setMsg(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdNomeContato(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdMensagemAtual(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdTipoRetornoContatoAtual(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdNomeContatoEscolhido("-1");
			formArvoreContato.setInDisponibilidadeNovo(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdTipoRetornoContatoAtual(ConstantesCRM.SVAZIO);
			formArvoreContato.setMensagem(ConstantesCRM.SVAZIO);
			formArvoreContato.setMensagemNova(ConstantesCRM.SVAZIO);
			formArvoreContato.setIdPagina(ConstantesCRM.SVAZIO);
			formArvoreContato.setFechamento(ConstantesCRM.SVAZIO);
			formArvoreContato.setQtDiasPrazoContato(ConstantesCRM.SVAZIO);
			formArvoreContato.setQtDiasPrazoAnatel(ConstantesCRM.SVAZIO);
			formArvoreContato.setProcessoTec(ConstantesCRM.SVAZIO);
			formArvoreContato.setVlPesoContato(ConstantesCRM.SVAZIO);
			formArvoreContato.setMensagem(ConstantesCRM.SVAZIO);

			request.setAttribute("dsPath", StringUtilStatic.escapaComillasJS2(request.getParameter("dsPath")));
			request.setAttribute("nmContato", StringUtilStatic.escapaComillasJS2(request.getParameter("nmContato")));

			// String contatoId = request.getParameter("idContato");
			String salvaedita = request.getParameter("salvaedita");
			// String inPasta = request.getParameter("inFolha");

			selectsFolhaContato(ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, request);

			formArvoreContato.setSalvaedita(salvaedita);
			// selectsFolhaContato(contatoId,"");

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:criarInserirItemContato'" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
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
	public ActionForward habilitaDesabilitaArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreContatoController:habilitaDesabilitaArvore(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormArvoreContato form = (FormArvoreContato) formParam;
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

			// Monta URL de retorno e desvia para o erro
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

		try {
			FormArvoreContato form = (FormArvoreContato) formParam;
			AdmArvoreContatoCopiaVO admArvoreContatoCopiaVO = AdmArvoreContatoCopiaVO.Factory.newInstance();
			admArvoreContatoCopiaVO.setIdContatoDestino(form.getIdContatoDestino());
			admArvoreContatoCopiaVO.setIdContatoOrigem(form.getIdContatoOrigem());
			admArvoreContatoCopiaVO.setQtdCopia(form.getQtdCopia());

			admArvoreContatoCopiaVO.setIdTipoArvore(idTipoArvore);

			this.controlArvore.copiaArvoreContato(admArvoreContatoCopiaVO, ConstantesCRM.getCurrentUser(request.getSession()));

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:copiaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreContato.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AdmArvoreContatoController:copiaArvore'" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="innerBrowserVendas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getNome(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreContatoController:getNome(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		FormArvoreContato form = (FormArvoreContato) formParam;
		try {
			AdmIdContatoVO admIdContato = AdmIdContatoVO.Factory.newInstance();
			admIdContato.setNmContato(form.getDescPesquisa());

			admIdContato.setIdTipoArvore(idTipoArvore);

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
		private String idContatoDestino;
		private String idContatoOrigem;
		private String msgError = "";
		private String mensagemNova;
		private String salvaedita;
		private String idPagina;
		private String dsPath;
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
		private AdmNomeContatoVO[] admNomeContatoVO;
		private String idContato;
		private AdmArvoreContainerVO arvoreContato;

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
			return this.msg;
		}

		public void setNomeTipo(String nomeTipo) {
			this.nomeTipo = nomeTipo;
		}

		public String getNomeTipo() {
			return this.nomeTipo;
		}

		public void setInFolha(String inFolha) {
			this.inFolha = inFolha;
		}

		public String getInFolha() {
			return this.inFolha;
		}

		public void setIdTipoRetornoContatoAtual(String idTipoRetornoContatoAtual) {
			this.idTipoRetornoContatoAtual = idTipoRetornoContatoAtual;
		}

		public String getIdTipoRetornoContatoAtual() {
			return this.idTipoRetornoContatoAtual;
		}

		public void setIdMensagemAtual(String idMensagemAtual) {
			this.idMensagemAtual = idMensagemAtual;
		}

		public String getIdMensagemAtual() {
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
			return this.dsPath;
		}

		public void setIdPagina(String idPagina) {
			this.idPagina = idPagina;
		}

		public String getIdPagina() {
			return this.idPagina;
		}

		public void setSalvaedita(String salvaedita) {
			this.salvaedita = salvaedita;
		}

		public String getSalvaedita() {
			return this.salvaedita;
		}

		public void setMensagemNova(String mensagemNova) {
			this.mensagemNova = mensagemNova;
		}

		public String getMensagemNova() {
			return this.mensagemNova;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setIdContatoOrigem(String idContatoOrigem) {
			this.idContatoOrigem = idContatoOrigem;
		}

		public String getIdContatoOrigem() {
			return this.idContatoOrigem;
		}

		public void setIdContatoDestino(String idContatoDestino) {
			this.idContatoDestino = idContatoDestino;
		}

		public String getIdContatoDestino() {
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

		public void setQtdCopia(String qtdCopia) {
			this.qtdCopia = qtdCopia;
		}

		public String getQtdCopia() {
			return this.qtdCopia;
		}

		public void setDescPesquisa(String descPesquisa) {
			this.descPesquisa = descPesquisa;
		}

		public String getDescPesquisa() {
			if (this.descPesquisa == null) {
				this.descPesquisa = ConstantesCRM.SVAZIO;
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
	}

	public FormArvoreContato getFormArvoreContato() {
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