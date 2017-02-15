package admsistemas.admArvoreConsulta;

import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AdmArvoreConsultaController extends AbstractAction {

	private static final long serialVersionUID = 1661898376513402948L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.configArvoreContato.ArvoreContato controlArvoreConsulta;

	// Para montagem da arvore.
	private static final String FOLHA = "1";
	private static final String DISPONIVEL = "1";

	// Definição constantes chamado de serviço.
	private static final String COMBO = "1";
	private static final String ARVORE_FILTRADA = "2";
	private static final String GRID = "3";

	// Para geraçao de arquivo CSV.
	public static final String COLUNA = ",";
	public static final String CONSTANTE_SEPARA = "/";

	public static final int TOTAL_PAGINA = 50;

	private static transient Logger log = new Logger("admsistemas");

	private FormArvoreConsulta formArvoreConsulta = null;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("carregaFiltro".equals(mapping.getParameter())) {
			return carregaFiltro(mapping, form, request, response);
		} else if ("montaArvore".equals(mapping.getParameter())) {
			return montaArvore(mapping, form, request, response);
		} else if ("montaArvoreParte".equals(mapping.getParameter())) {
			return montaArvoreParte(mapping, form, request, response);
		} else if ("montaLista".equals(mapping.getParameter())) {
			return montaLista(mapping, form, request, response);
		} else if ("exportaRetorno".equals(mapping.getParameter())) {
			return exportaRetorno(mapping, form, request, response);
		} else if ("filtraArvore".equals(mapping.getParameter())) {
			return filtraArvore(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configArvoreConsulta.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreConsultaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="filtraArvoreConsulta.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaFiltro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormArvoreConsulta form = (FormArvoreConsulta) formParam;
		log.debug("AdmArvoreConsultaController:carregaFiltro" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (getFormArvoreConsulta().getContainerCombos() == null || "limpa".equals(form.getLimpaForm())) {
			AdmArvoreParametroVO admContainerParametro = AdmArvoreParametroVO.Factory.newInstance();
			admContainerParametro.addNewDadosAtuais();

			admContainerParametro.setInServico(COMBO);
			admContainerParametro = controlArvoreConsulta.getArvoreConsulta(admContainerParametro, ConstantesCRM.getCurrentUser(request.getSession()));
			admContainerParametro.setAdmArvoreParametroCombosVO(testNull(admContainerParametro.getAdmArvoreParametroCombosVO()));

			getFormArvoreConsulta().setContainerCombos(admContainerParametro.getAdmArvoreParametroCombosVO());

			populaCombos(getFormArvoreConsulta().getContainerCombos(), form);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void populaCombos(AdmArvoreParametroCombosVO total, FormArvoreConsulta form) {

		getFormArvoreConsulta().setContainerCombosAssoc(AdmArvoreParametroCombosVO.Factory.newInstance());
		getFormArvoreConsulta().setContainerCombosExist(AdmArvoreParametroCombosVO.Factory.newInstance());

		// Globais.
		int countAssoc = 0;
		int countExist = 0;
		int i = 0;
		int x = 0;
		String selectedArray[] = null;
		boolean flgExiste = false;

		// *****************************************************************
		// SEGMENTACAO
		// *****************************************************************
		selectedArray = form.getIdSegmentacaoAssocArray();
		for (i = 0; i < total.getAdmTipoSegmentacaoVOArray().length; i++) {
			String segmentacao = (total.getAdmTipoSegmentacaoVOArray(i).getIdSegmentacao() != null) ? total.getAdmTipoSegmentacaoVOArray(i).getIdSegmentacao() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (segmentacao.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreConsulta().getContainerCombosAssoc().addNewAdmTipoSegmentacaoVO();
				getFormArvoreConsulta().getContainerCombosAssoc().setAdmTipoSegmentacaoVOArray(countAssoc, total.getAdmTipoSegmentacaoVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreConsulta().getContainerCombosExist().addNewAdmTipoSegmentacaoVO();
				getFormArvoreConsulta().getContainerCombosExist().setAdmTipoSegmentacaoVOArray(countExist, total.getAdmTipoSegmentacaoVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// CARTEIRIZAÇÃO.
		// *****************************************************************
		selectedArray = form.getIdTipoCarteiraAssocArray();
		for (i = 0; i < total.getAdmTipoCarteiraSimplVOArray().length; i++) {
			String gAbertura = (total.getAdmTipoCarteiraSimplVOArray(i).getIdTipoCarteira() != null) ? total.getAdmTipoCarteiraSimplVOArray(i).getIdTipoCarteira() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (gAbertura.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreConsulta().getContainerCombosAssoc().addNewAdmTipoCarteiraSimplVO();
				getFormArvoreConsulta().getContainerCombosAssoc().setAdmTipoCarteiraSimplVOArray(countAssoc, total.getAdmTipoCarteiraSimplVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreConsulta().getContainerCombosExist().addNewAdmTipoCarteiraSimplVO();
				getFormArvoreConsulta().getContainerCombosExist().setAdmTipoCarteiraSimplVOArray(countExist, total.getAdmTipoCarteiraSimplVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// PROCEDENCIA.
		// *****************************************************************
		selectedArray = form.getIdProcedenciaAssocArray();
		for (i = 0; i < total.getAdmProcedenciaVOArray().length; i++) {
			String procedencia = (total.getAdmProcedenciaVOArray(i).getIdProcedencia() != null) ? total.getAdmProcedenciaVOArray(i).getIdProcedencia() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (procedencia.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreConsulta().getContainerCombosAssoc().addNewAdmProcedenciaVO();
				getFormArvoreConsulta().getContainerCombosAssoc().setAdmProcedenciaVOArray(countAssoc, total.getAdmProcedenciaVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreConsulta().getContainerCombosExist().addNewAdmProcedenciaVO();
				getFormArvoreConsulta().getContainerCombosExist().setAdmProcedenciaVOArray(countExist, total.getAdmProcedenciaVOArray(i));
				++countExist;
			}
		}

		selectedArray = null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="admArvoreConsulta.jsp"
	 */

	public ActionForward montaArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreConsultaController:montaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		getFormArvoreConsulta().setMsgError("");
		try {
			AdmArvoreParametroVO admContainerParametro = AdmArvoreParametroVO.Factory.newInstance();
			admContainerParametro.addNewDadosAtuais();

			// parametro tipo açao serviço.
			admContainerParametro.setInServico(ARVORE_FILTRADA);
			admContainerParametro = controlArvoreConsulta.getArvoreParametro(admContainerParametro, ConstantesCRM.getCurrentUser(request.getSession()));

			AdmContatoFolhaVO admContatoFolhaVO = admContainerParametro.getAdmContatoFolhaVO();

			StringBuffer script = new StringBuffer();
			StringBuffer node = new StringBuffer();
			String folder = "";

			script.append("if (document.getElementById) { var tree = new WebFXTree('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getNmContato())).append("',\"Javascript:document.getElementById('btnSelect').style.display = 'none';capturaParametrosContato('").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getNmContato()))
			.append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getIdContatoPai()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getDsPath())).append("'").append(");\",'','');");

			for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray().length; i++) {

				boolean inFolha = false;

				// Se Folha.
				if (admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInFolha().trim().equals(FOLHA)) {
					folder = "',\"Javascript:document.getElementById('btnSelect').style.display = 'block';capturaParametrosContato('";
					inFolha = true;
				} else {
					folder = "',\"Javascript:document.getElementById('btnSelect').style.display = 'none';expandirNo('";
				}

				// Se folha
				if (inFolha) {
					node.append("tmpArvore = new WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getDsPath())).append("','");

					// Se Folha Disponivel.
					if (admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInDisponibilidade().equals(DISPONIVEL)) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');");
					}

				} else {
					node.append("tmpArvore = new WebFXTreeItem('" + StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getDsPath())).append("','");

					// Se Pasta Disponivel.
					if (admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInDisponibilidade().equals(DISPONIVEL)) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/folder.gif','/FrontOfficeWeb/resources/images/folderopen.gif');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');");
					}
				}

				script.append(node).append("tree.add(tmpArvore);\n\n");

			}

			script.append("\ndocument.write(tree);}\n\n");

			request.setAttribute("arvore", String.valueOf(script));
		}

		catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreConsultaController:montaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			getFormArvoreConsulta().setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreConsultaController:montaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// testa se existem de objetos nulos.
	private AdmArvoreParametroCombosVO testNull(AdmArvoreParametroCombosVO admCombos) {
		if (admCombos == null) {
			return null;
		}

		if (admCombos.getAdmTipoCarteiraSimplVOArray() == null) {
			admCombos.addNewAdmTipoCarteiraSimplVO();
		}

		if (admCombos.getAdmTipoSegmentacaoVOArray() == null) {
			admCombos.addNewAdmTipoSegmentacaoVO();
		}

		if (admCombos.getAdmProcedenciaVOArray() == null) {
			admCombos.addNewAdmProcedenciaVO();
		}

		return admCombos;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iframeArvoreConsulta.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaArvoreParte(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreConsultaController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormArvoreConsulta form = (FormArvoreConsulta) formParam;
		try {
			AdmArvoreParametroVO admContainerParametro = AdmArvoreParametroVO.Factory.newInstance();
			if (getFormArvoreConsulta().getDadosAtuaisAssoc() != null) {
				admContainerParametro.setDadosAtuais(getFormArvoreConsulta().getDadosAtuaisAssoc());
			} else {
				admContainerParametro.addNewDadosAtuais();
			}

			if (getFormArvoreConsulta() != null && getFormArvoreConsulta().getDadosAtuaisAssoc() != null) {
				admContainerParametro.setDadosAtuais(getFormArvoreConsulta().getDadosAtuaisAssoc());
			}

			admContainerParametro.getDadosAtuais().setIdContato(form.getIdContato());

			admContainerParametro.setInServico(ARVORE_FILTRADA);

			admContainerParametro = controlArvoreConsulta.getArvoreParametro(admContainerParametro, ConstantesCRM.getCurrentUser(request.getSession()));

			AdmContatoFolhaVO admContatoFolhaVO = admContainerParametro.getAdmContatoFolhaVO();

			StringBuffer script = new StringBuffer();
			StringBuffer node = new StringBuffer();
			String folder = "";

			for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray().length; i++) {

				boolean inFolha = false;

				// Se Folha.
				if (admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInFolha().trim().equals(FOLHA)) {
					folder = "',\"Javascript:document.getElementById('btnSelect').style.display = 'block';capturaParametrosContato('";
					inFolha = true;

				} else {
					folder = "',\"Javascript:document.getElementById('btnSelect').style.display = 'none';expandirNo('";
				}

				if (inFolha) {
					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getDsPath())).append("','");

					if (admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals(DISPONIVEL)) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');");
					}

				} else {
					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getDsPath())).append("','");

					if (admContatoFolhaVO.getAdmContatoFolhaVOArray(0).getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals(DISPONIVEL)) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/folder.gif','/FrontOfficeWeb/resources/images/folderopen.gif');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');");
					}
				}

				script.append(node).append("parent.tree.getSelected().add(parent.tmpArvore);\n\n");

			}

			script.append("parent.tree.getSelected().setAddEnabled(false);\n\n").append("parent.tree.getSelected().expand()\n\n");

			request.setAttribute("arvoreParte", String.valueOf(script));

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreConsultaController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			getFormArvoreConsulta().setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreConsultaController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="AdmArovoreConsultaRetorno.jsp"
	 */
	public ActionForward montaLista(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreConsultaController:montaLista" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormArvoreConsulta form = (FormArvoreConsulta) formParam;
		getFormArvoreConsulta().setMsgError(ConstantesCRM.SVAZIO);

		try {
			AdmArvoreParametroVO admContainerParametro = AdmArvoreParametroVO.Factory.newInstance();
			admContainerParametro.addNewDadosAtuais();

			admContainerParametro.getDadosAtuais().setIdProcedenciaArray1Array(getFormArvoreConsulta().getDadosAtuaisAssoc().getIdProcedenciaArray1Array());
			admContainerParametro.getDadosAtuais().setIdTipoCarteiraArray1Array(getFormArvoreConsulta().getDadosAtuaisAssoc().getIdTipoCarteiraArray1Array());
			admContainerParametro.getDadosAtuais().setIdSegmentacaoArray1Array(getFormArvoreConsulta().getDadosAtuaisAssoc().getIdSegmentacaoArray1Array());
			admContainerParametro.getDadosAtuais().setIdContatoArray1Array(form.getIdContatoArray());
			// Retorno GRID.
			admContainerParametro.setInServico(GRID);

			if (form.getPaginaSeleciona() == null || form.getPaginaSeleciona().equals(ConstantesCRM.SVAZIO)) {
				admContainerParametro.setTotalPesquisa(ConstantesCRM.SZERO);
				getFormArvoreConsulta().setPaginaSeleciona(ConstantesCRM.SZERO);

			} else {
				admContainerParametro.setTotalPesquisa(form.getPaginaSeleciona());
				getFormArvoreConsulta().setPaginaSeleciona(form.getPaginaSeleciona());
			}

			admContainerParametro = controlArvoreConsulta.getArvoreConsulta(admContainerParametro, ConstantesCRM.getCurrentUser(request.getSession()));

			// Retorno Pesquisa.
			getFormArvoreConsulta().setDadosRetorno(admContainerParametro.getDadosRetornoVOArray());
			if (admContainerParametro.getNumTotalRegistros() != null) {
				getFormArvoreConsulta().setNumTotalRegistro(admContainerParametro.getNumTotalRegistros());
			}

			int total = Integer.parseInt(getFormArvoreConsulta().getNumTotalRegistro());

			int divTotal = total / TOTAL_PAGINA;

			int mod = total % TOTAL_PAGINA;
			if (mod > 0) {
				++divTotal;
			}

			if (Integer.parseInt(getFormArvoreConsulta().getPaginaSeleciona() + 1) == divTotal) {
				getFormArvoreConsulta().setInProximo("proximo");
			} else {
				getFormArvoreConsulta().setInProximo(ConstantesCRM.SVAZIO);
			}

			request.setAttribute("totalPagina", new Integer(divTotal));
			request.setAttribute("paginaSelecionada", new Integer(getFormArvoreConsulta().getPaginaSeleciona()));

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreConsultaController:montaLista" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			getFormArvoreConsulta().setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreConsultaController:montaLista" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="admArvoreConsulta.jsp"
	 */
	public ActionForward exportaRetorno(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AdmArvoreConsultaController:exportaRetorno" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormArvoreConsulta form = (FormArvoreConsulta) formParam;
		StringBuffer sbDados = new StringBuffer();

		AdmArvoreParametroVO admContainerParametro = AdmArvoreParametroVO.Factory.newInstance();

		admContainerParametro.addNewDadosAtuais();
		admContainerParametro.getDadosAtuais().setIdProcedenciaArray1Array(getFormArvoreConsulta().getDadosAtuaisAssoc().getIdProcedenciaArray1Array());
		admContainerParametro.getDadosAtuais().setIdTipoCarteiraArray1Array(getFormArvoreConsulta().getDadosAtuaisAssoc().getIdTipoCarteiraArray1Array());
		admContainerParametro.getDadosAtuais().setIdSegmentacaoArray1Array(getFormArvoreConsulta().getDadosAtuaisAssoc().getIdSegmentacaoArray1Array());
		admContainerParametro.getDadosAtuais().setIdContatoArray1Array(form.getIdContatoArray());

		// Retorno GRID.
		admContainerParametro.setInServico(GRID);

		admContainerParametro = controlArvoreConsulta.getArvoreConsulta(admContainerParametro, ConstantesCRM.getCurrentUser(request.getSession()));

		if (admContainerParametro.getNumTotalRegistros() != null) {
			getFormArvoreConsulta().setNumTotalRegistro(admContainerParametro.getNumTotalRegistros());
		}

		int total = Integer.parseInt(getFormArvoreConsulta().getNumTotalRegistro());

		int divTotal = total / TOTAL_PAGINA;

		int mod = total % TOTAL_PAGINA;
		if (mod > 0) {
			++divTotal;
		}

		// Cabeçalho.
		for (int subItem = 0; subItem < admContainerParametro.getDadosRetornoVOArray().length; subItem++) {
			if (subItem == 0) {
				sbDados.append("Contato(").append(admContainerParametro.getDadosRetornoVOArray(subItem).getAdmContatoVO().getNota()).append(")").append(COLUNA).append("Valor").append(COLUNA).append("Carteira(").append(admContainerParametro.getDadosRetornoVOArray(subItem).getAdmTipoCarteiraSimplVO().getNota()).append(")").append(COLUNA).append("Valor").append(COLUNA).append("Segmentação(").append(admContainerParametro.getDadosRetornoVOArray(subItem).getAdmTipoSegmentacaoVO().getNota())
				.append(")").append(COLUNA).append("Valor").append(COLUNA).append("Procedência(").append(admContainerParametro.getDadosRetornoVOArray(subItem).getAdmProcedenciaVO().getNota()).append(")").append(COLUNA).append("Valor").append(COLUNA).append("Total").append("\n");
				break;
			}
		}

		for (int x = 0; x < divTotal; x++) {
			if (x > 0) {
				admContainerParametro.setTotalPesquisa(String.valueOf(x));
				admContainerParametro = controlArvoreConsulta.getArvoreConsulta(admContainerParametro, ConstantesCRM.getCurrentUser(request.getSession()));
			}

			// Linhas
			for (int item = 0; item < admContainerParametro.getDadosRetornoVOArray().length; item++) {
				sbDados.append(admContainerParametro.getDadosRetornoVOArray(item).getAdmContatoVO().getNmContato()).append(COLUNA).append(admContainerParametro.getDadosRetornoVOArray(item).getAdmContatoVO().getTotal()).append(COLUNA).append(admContainerParametro.getDadosRetornoVOArray(item).getAdmTipoCarteiraSimplVO().getDsTipoCarteira()).append(COLUNA).append(admContainerParametro.getDadosRetornoVOArray(item).getAdmTipoCarteiraSimplVO().getTotal()).append(COLUNA)
				.append(admContainerParametro.getDadosRetornoVOArray(item).getAdmTipoSegmentacaoVO().getDsSegmentacao()).append(COLUNA).append(admContainerParametro.getDadosRetornoVOArray(item).getAdmTipoSegmentacaoVO().getTotal()).append(COLUNA).append(admContainerParametro.getDadosRetornoVOArray(item).getAdmProcedenciaVO().getNmProcedencia()).append(COLUNA).append(admContainerParametro.getDadosRetornoVOArray(item).getAdmProcedenciaVO().getTotal()).append(COLUNA)
				.append(admContainerParametro.getDadosRetornoVOArray(item).getTotal()).append("\n");
			}

		}

		// Escrenvendo arquivo.
		if (sbDados != null && sbDados.toString().length() > 0) {
			response.addHeader("Content-Disposition", "attachment; filename=ConsultaContato.csv");
			response.addHeader("Content-Type", "application/force-download");
			response.addHeader("Content-Transfer-Encoding", "binary");
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Expires", "0");

			PrintWriter out = response.getWriter();
			out.println(sbDados.toString());
			out.flush();
			out.close();
			return null;

		}// Fim escrita

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="montaArvore.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward filtraArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AdmArvoreConsultaController:filtraArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormArvoreConsulta form = (FormArvoreConsulta) formParam;
		// if(getFormArvoreConsulta().getDadosAtuaisAssoc() == null)
		getFormArvoreConsulta().setDadosAtuaisAssoc(DadosAtuais.Factory.newInstance());

		// if(getFormArvoreConsulta().getDadosAtuaisExist() == null)
		getFormArvoreConsulta().setDadosAtuaisExist(DadosAtuais.Factory.newInstance());

		// Armazena dados de filtro, para todas consulta, em montaArvoreParte.do
		getFormArvoreConsulta().getDadosAtuaisAssoc().setIdSegmentacaoArray1Array(form.getIdSegmentacaoAssocArray());
		getFormArvoreConsulta().getDadosAtuaisAssoc().setIdTipoCarteiraArray1Array(form.getIdTipoCarteiraAssocArray());
		getFormArvoreConsulta().getDadosAtuaisAssoc().setIdProcedenciaArray1Array(form.getIdProcedenciaAssocArray());
		// fim Armazena.

		// Setando de volta para o formulario, Associados.
		// Variaveis com prefixo id, são de Filtro.
		getFormArvoreConsulta().setIdSegmentacaoAssocArray(form.getIdSegmentacaoAssocArray());
		getFormArvoreConsulta().setIdTipoCarteiraAssocArray(form.getIdTipoCarteiraAssocArray());
		getFormArvoreConsulta().setIdProcedenciaAssocArray(form.getIdProcedenciaAssocArray());

		// Existentes.
		getFormArvoreConsulta().setIdSegmentacaoExistArray(form.getIdSegmentacaoExistArray());
		getFormArvoreConsulta().setIdTipoCarteiraExistArray(form.getIdTipoCarteiraExistArray());
		getFormArvoreConsulta().setIdProcedenciaExistArray(form.getIdProcedenciaExistArray());
		// Variaveis com prefixo IN, são de visualização.

		populaCombos(getFormArvoreConsulta().getContainerCombos(), form);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormArvoreConsulta extends ActionForm {

		private static final long serialVersionUID = -8765549686502073069L;

		private String inProximo;
		private String paginaSeleciona;
		private String totalPagina;
		private String numTotalRegistro;
		private String nmContato;
		private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosRetornoVO[] dadosRetorno;
		private String inFolha;
		private String inDisponibilidade;
		private String dsPath;
		private String idContatoPai;
		private AdmArvoreParametroCombosVO containerCombosAssoc;
		private AdmArvoreParametroCombosVO containerCombosExist;
		private AdmArvoreParametroCombosVO containerCombos;
		private String limpaForm;
		private String[] idContatoArray;
		private String idContato;
		private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisExist;
		private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisAssoc;
		private String[] idTipoCarteiraExistArray;
		private String[] idTipoCarteiraAssocArray;
		private String[] idSegmentacaoExistArray;
		private String[] idSegmentacaoAssocArray;
		private String[] idProcedenciaExistArray;
		private String[] idProcedenciaAssocArray;
		private String msgError;

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setIdProcedenciaAssocArray(String[] idProcedenciaAssocArray) {
			this.idProcedenciaAssocArray = idProcedenciaAssocArray;
		}

		public String[] getIdProcedenciaAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idProcedenciaAssocArray == null || this.idProcedenciaAssocArray.length == 0) {
				this.idProcedenciaAssocArray = new String[1];
			}

			return this.idProcedenciaAssocArray;
		}

		public void setIdProcedenciaExistArray(String[] idProcedenciaExistArray) {
			this.idProcedenciaExistArray = idProcedenciaExistArray;
		}

		public String[] getIdProcedenciaExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idProcedenciaExistArray == null || this.idProcedenciaExistArray.length == 0) {
				this.idProcedenciaExistArray = new String[1];
			}

			return this.idProcedenciaExistArray;
		}

		public void setIdSegmentacaoAssocArray(String[] idSegmentacaoAssocArray) {
			this.idSegmentacaoAssocArray = idSegmentacaoAssocArray;
		}

		public String[] getIdSegmentacaoAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idSegmentacaoAssocArray == null || this.idSegmentacaoAssocArray.length == 0) {
				this.idSegmentacaoAssocArray = new String[1];
			}

			return this.idSegmentacaoAssocArray;
		}

		public void setIdSegmentacaoExistArray(String[] idSegmentacaoExistArray) {
			this.idSegmentacaoExistArray = idSegmentacaoExistArray;
		}

		public String[] getIdSegmentacaoExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idSegmentacaoExistArray == null || this.idSegmentacaoExistArray.length == 0) {
				this.idSegmentacaoExistArray = new String[1];
			}

			return this.idSegmentacaoExistArray;
		}

		public void setIdTipoCarteiraAssocArray(String[] idTipoCarteiraAssocArray) {
			this.idTipoCarteiraAssocArray = idTipoCarteiraAssocArray;
		}

		public String[] getIdTipoCarteiraAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idTipoCarteiraAssocArray == null || this.idTipoCarteiraAssocArray.length == 0) {
				this.idTipoCarteiraAssocArray = new String[1];
			}

			return this.idTipoCarteiraAssocArray;
		}

		public void setIdTipoCarteiraExistArray(String[] idTipoCarteiraExistArray) {
			this.idTipoCarteiraExistArray = idTipoCarteiraExistArray;
		}

		public String[] getIdTipoCarteiraExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idTipoCarteiraExistArray == null || this.idTipoCarteiraExistArray.length == 0) {
				this.idTipoCarteiraExistArray = new String[1];
			}

			return this.idTipoCarteiraExistArray;
		}

		public void setDadosAtuaisAssoc(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisAssoc) {
			this.dadosAtuaisAssoc = dadosAtuaisAssoc;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais getDadosAtuaisAssoc() {
			if (this.dadosAtuaisAssoc == null) {
				this.dadosAtuaisAssoc = DadosAtuais.Factory.newInstance();
			}

			return this.dadosAtuaisAssoc;
		}

		public void setDadosAtuaisExist(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisExist) {
			this.dadosAtuaisExist = dadosAtuaisExist;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais getDadosAtuaisExist() {
			if (this.dadosAtuaisExist == null) {
				this.dadosAtuaisExist = DadosAtuais.Factory.newInstance();
			}

			return this.dadosAtuaisExist;
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setIdContatoArray(String[] idContatoArray) {
			this.idContatoArray = idContatoArray;
		}

		public String[] getIdContatoArray() {
			if (this.idContatoArray == null || this.idContatoArray.length == 0) {
				this.idContatoArray = new String[1];
			}

			return this.idContatoArray;
		}

		public void setLimpaForm(String limpaForm) {
			this.limpaForm = limpaForm;
		}

		public String getLimpaForm() {
			return this.limpaForm;
		}

		public void setContainerCombos(AdmArvoreParametroCombosVO containerCombos) {
			this.containerCombos = containerCombos;
		}

		public AdmArvoreParametroCombosVO getContainerCombos() {
			return this.containerCombos;
		}

		public void setContainerCombosExist(AdmArvoreParametroCombosVO containerCombosExist) {
			this.containerCombosExist = containerCombosExist;
		}

		public AdmArvoreParametroCombosVO getContainerCombosExist() {

			if (this.containerCombosExist == null) {
				this.containerCombosExist = AdmArvoreParametroCombosVO.Factory.newInstance();
			}

			return this.containerCombosExist;
		}

		public void setContainerCombosAssoc(AdmArvoreParametroCombosVO containerCombosAssoc) {
			this.containerCombosAssoc = containerCombosAssoc;
		}

		public AdmArvoreParametroCombosVO getContainerCombosAssoc() {

			if (this.containerCombosAssoc == null) {
				this.containerCombosAssoc = AdmArvoreParametroCombosVO.Factory.newInstance();
			}

			return this.containerCombosAssoc;
		}

		public void setIdContatoPai(String idContatoPai) {
			this.idContatoPai = idContatoPai;
		}

		public String getIdContatoPai() {
			return this.idContatoPai;
		}

		public void setDsPath(String dsPath) {
			this.dsPath = dsPath;
		}

		public String getDsPath() {
			return this.dsPath;
		}

		public void setInDisponibilidade(String inDisponibilidade) {
			this.inDisponibilidade = inDisponibilidade;
		}

		public String getInDisponibilidade() {
			return this.inDisponibilidade;
		}

		public void setInFolha(String inFolha) {
			this.inFolha = inFolha;
		}

		public String getInFolha() {
			return this.inFolha;
		}

		public void setDadosRetorno(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosRetornoVO[] dadosRetorno) {
			this.dadosRetorno = dadosRetorno;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosRetornoVO[] getDadosRetorno() {
			return this.dadosRetorno;
		}

		public void setNmContato(String nmContato) {
			this.nmContato = nmContato;
		}

		public String getNmContato() {
			return this.nmContato;
		}

		public void setNumTotalRegistro(String numTotalRegistro) {
			this.numTotalRegistro = numTotalRegistro;
		}

		public String getNumTotalRegistro() {
			return this.numTotalRegistro;
		}

		public void setTotalPagina(String totalPagina) {
			this.totalPagina = totalPagina;
		}

		public String getTotalPagina() {
			return this.totalPagina;
		}

		public void setPaginaSeleciona(String paginaSeleciona) {
			this.paginaSeleciona = paginaSeleciona;
		}

		public String getPaginaSeleciona() {
			return this.paginaSeleciona;
		}

		public void setInProximo(String inProximo) {
			this.inProximo = inProximo;
		}

		public String getInProximo() {
			return this.inProximo;
		}
	}

	public FormArvoreConsulta getFormArvoreConsulta() {
		if (this.formArvoreConsulta == null) {
			this.formArvoreConsulta = new FormArvoreConsulta();
		}

		return this.formArvoreConsulta;
	}

	public void setFormArvoreConsulta(FormArvoreConsulta formParam) {
		this.formArvoreConsulta = formParam;
	}

}
