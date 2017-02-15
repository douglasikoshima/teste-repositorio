package admsistemas.admArvoreBaixa;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaConsultaVODocument.AdmArvoreBaixaConsultaVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmAtualizacaoArvoreBaixaVODocument.AdmAtualizacaoArvoreBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmComunicacaoBaixaVODocument.AdmComunicacaoBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdBaixaVODocument.AdmIdBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmIndicadorAnatelVODocument.AdmIndicadorAnatelVO;
import br.com.vivo.fo.admsistemas.vo.AdmIndicadoresAnatelContainerVODocument.AdmIndicadoresAnatelContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO;
import br.com.vivo.fo.admsistemas.vo.AdmMensagemBaixaVODocument.AdmMensagemBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmNomeBaixaVODocument.AdmNomeBaixaVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.configArvoreBaixa.ArvoreBaixa;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AdmArvoreBaixaController extends AbstractAction {

	private static final long serialVersionUID = 5178535542248544103L;

	@EJB
	private ArvoreBaixa controlBaixa;

	private String idBaixa = ConstantesCRM.SVAZIO;
	private String idBaixaPai = ConstantesCRM.SVAZIO;
	private String idNomeBaixa = ConstantesCRM.SVAZIO;
	private String nmBaixa = ConstantesCRM.SVAZIO;
	private String dsPath = ConstantesCRM.SVAZIO;
	private String inDisponibilidade = ConstantesCRM.SVAZIO;
	private String inFolha = ConstantesCRM.SVAZIO;
	private String indicador = ConstantesCRM.SVAZIO;
	private int contador = 0;
	
	private boolean exibeMsg=false;
	private int cont =0;

	private FormArvoreBaixa formArvoreBaixa;
	private static FormInsereBaixa formInsereBaixa;
	private static FormEditaBaixa formEditaBaixa;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("removeItem".equals(mapping.getParameter())) {
			return removeItem(mapping, form, request, response);
		} else if ("montaInsereItem".equals(mapping.getParameter())) {
			return montaInsereItem(mapping, form, request, response);
		} else if ("carregaDadosInsereItem".equals(mapping.getParameter())) {
			return carregaDadosInsereItem(mapping, form, request, response);
		} else if ("montaEditarItem".equals(mapping.getParameter())) {
			return montaEditarItem(mapping, form, request, response);
		} else if ("carregaDadosEditaItem".equals(mapping.getParameter())) {
			return carregaDadosEditaItem(mapping, form, request, response);
		} else if ("salvaItemEditado".equals(mapping.getParameter())) {
			return salvaItemEditado(mapping, form, request, response);
		} else if ("montaDetalharItem".equals(mapping.getParameter())) {
			return montaDetalharItem(mapping, form, request, response);
		} else if ("carregaDadosDetalha".equals(mapping.getParameter())) {
			return carregaDadosDetalha(mapping, form, request, response);
		} else if ("salvaItemInserido".equals(mapping.getParameter())) {
			return salvaItemInserido(mapping, form, request, response);
		} else if ("habilitaDesabilitaArvore".equals(mapping.getParameter())) {
			return habilitaDesabilitaArvore(mapping, form, request, response);
		} else if ("montaArvoreParte".equals(mapping.getParameter())) {
			return montaArvoreParte(mapping, form, request, response);
		} else if ("getNome".equals(mapping.getParameter())) {
			return getNome(mapping, form, request, response);
		} else if ("criarItemBaixa".equals(mapping.getParameter())) {
			return criarItemBaixa(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterArvoreBaixa.jsp"
	 * @jpf:forward name="success1" path="carregaDadosInsereItem.do"
	 * @jpf:forward name="editar" path="carregaDadosEditaItem.do"
	 * @jpf:forward path="carregaDadosDetalha.do" name="detalhar"
	 * @jpf:forward name="error" path="/error.jsp"
	 */

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			
			if (formArvoreBaixa == null) {
				formArvoreBaixa = new FormArvoreBaixa();
			}
			
			if(exibeMsg){
				exibeMsg=false;
			}else{
				if(cont==1){
					cont=0;
				}else{
					formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);	
				}
			}
			
			if (request.getAttribute("msgError") != null && !ConstantesCRM.SVAZIO.equals(request.getAttribute("msgError"))) {
				formArvoreBaixa.setMsgError((String)request.getAttribute("msgError"));
			}

			log.debug("AdmArvoreBaixaController:begin" + "( Referer " + request.getHeader("referer") + "): MSG " + formArvoreBaixa.getMsgError());
			if (request.getHeader("referer").indexOf("inicio.jsp") > 0) {
				formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);
			}
			request.removeAttribute("flag");

			formArvoreBaixa.setArvoreBaixa(chamaServicoArvore(request));
			formArvoreBaixa.setAdmFolhaBaixaVO(formArvoreBaixa.getAdmFolhaBaixaVO());

			if (formArvoreBaixa.getAdmIndicadoresAnatelAssociadosVO() != null && formArvoreBaixa.getAdmIndicadoresAnatelAssociadosVO().length > 0) {
				formArvoreBaixa.setAdmIndicadoresAnatelAssociadosVO(formArvoreBaixa.getAdmIndicadoresAnatelAssociadosVO());
			}

			if (formArvoreBaixa.getAdmIndicadoresAnatelExistentesVO() != null && formArvoreBaixa.getAdmIndicadoresAnatelExistentesVO().length > 0) {
				formArvoreBaixa.setAdmIndicadoresAnatelExistentesVO(formArvoreBaixa.getAdmIndicadoresAnatelExistentesVO());
			}

			if (formArvoreBaixa.getArvoreBaixa().getAdmMensagemAvisoVOArray() != null) {
				formArvoreBaixa.setMensagens(formArvoreBaixa.getArvoreBaixa().getAdmMensagemAvisoVOArray());
			} else {
				formArvoreBaixa.setMensagens(new AdmMensagemAvisoVO[0]);
			}

			montaArvore(formArvoreBaixa.getArvoreBaixa(), request);

			formArvoreBaixa.setAdmComunicacaoBaixaVO(formArvoreBaixa.getArvoreBaixa().getAdmComunicacaoBaixaVOArray());

		} catch (Exception e) {
			log.error("AdmArvoreBaixaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		if (indicador.equals(ConstantesCRM.SVAZIO)) {
			
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} else if (indicador.equals("sucess1")) {
			indicador = ConstantesCRM.SVAZIO;
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return this.carregaDadosInsereItem(mapping, form, request, response);

		} else if (indicador.equals("detalhar")) {
			indicador = ConstantesCRM.SVAZIO;
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return this.carregaDadosDetalha(mapping, form, request, response);
		} else {
			indicador = ConstantesCRM.SVAZIO;
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return this.carregaDadosEditaItem(mapping, form, request, response);
		}

	}

	private AdmArvoreBaixaContainerVO chamaServicoArvore(HttpServletRequest request) throws Exception {

		AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = AdmArvoreBaixaContainerVO.Factory.newInstance();
		AdmIdBaixaVO admIdBaixaVO = AdmIdBaixaVO.Factory.newInstance();
		admIdBaixaVO.setIdBaixa(ConstantesCRM.SVAZIO);
		admArvoreBaixaContainerVO = controlBaixa.carregaArvoreBaixa(admIdBaixaVO, ConstantesCRM.getCurrentUser(request.getSession()));

		return admArvoreBaixaContainerVO;
	}

	private void montaArvore(AdmArvoreBaixaContainerVO admArvoreBaixaVO, HttpServletRequest request) throws Exception {

		request.setCharacterEncoding(ConstantesCRM.SISO);

		StringBuffer script = new StringBuffer();
		StringBuffer node = new StringBuffer();
		String folder = ConstantesCRM.SVAZIO;

		script.append("if (document.getElementById) { var tree = new WebFXTree('").append(StringUtilStatic.escapaComillasJS(admArvoreBaixaVO.getAdmFolhaBaixaVO().getNmBaixa())).append("',\"Javascript:capturaParametrosRaiz('").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getIdBaixa()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getIdBaixaPai()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getIdNomeBaixa()).append("','")
		.append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getNmBaixa())).append("','").append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getDsPath())).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getInDisponibilidade()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getInFolha()).append("','").append("');").append("\",'','');");

		for (int i = 0; i < admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray().length; i++) {

			boolean inFolha = false;

			if (admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha().trim().equals("1")) {
				folder = "',\"Javascript:capturaParametrosContato('";
			} else {
				folder = "',\"Javascript:expandirNo('";
			}

			if (admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha().trim().equals("1")) {
				inFolha = true;
			}

			if (inFolha) {
				node.append("tmpArvore = new WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa())).append(folder).append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixaPai()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdNomeBaixa())
				.append("','").append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa())).append("','").append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getDsPath())).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade()).append("','")
				.append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha()).append("','");

				if (admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade().trim().equals("1")) {
					node.append("');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");
				} else {
					node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');");
				}

			} else {
				node.append("tmpArvore = new WebFXTreeItem('" + StringUtilStatic.escapaComillasJS(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa())).append(folder).append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixaPai()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdNomeBaixa()).append("','")
				.append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa())).append("','").append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getDsPath())).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha())
				.append("','");

				if (admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade().trim().equals("1")) {
					node.append("');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');");
				} else {
					node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');");
				}

			}
			script.append(node).append("tree.add(tmpArvore);\n\n");

		}
		script.append("\ndocument.write(tree);}\n\n");

		request.setAttribute("arvore", String.valueOf(script));
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		FormArvoreBaixa form = (FormArvoreBaixa) formParam;

		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmFolhaBaixaVO admFolhaBaixaVO = AdmFolhaBaixaVO.Factory.newInstance();
			admFolhaBaixaVO.setIdBaixa(form.getIdBaixa());
			controlBaixa.removeItemBaixa(admFolhaBaixaVO, ConstantesCRM.getCurrentUser(request.getSession()));
			indicador = ConstantesCRM.SVAZIO;
			formArvoreBaixa.setMsgError(ConstantesCRM.SSucesso);
			exibeMsg=true;
			cont=1;
		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreBaixaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getMessage());
			
			if (twe.getMessage().indexOf("14W0001") >= 0 ) {

				request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 2));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return this.begin(mapping, form, request, response);
			}

			formArvoreBaixa.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			if (e.getMessage().length() > 11 && e.getMessage().substring(11, 12).equalsIgnoreCase("1")) {
				log.warn("AdmArvoreBaixaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));

				request.setAttribute("msgError", e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return this.begin(mapping, form, request, response);

			}

			log.error("AdmArvoreBaixaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return this.begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaInsereItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:montaInsereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		FormArvoreBaixa form = (FormArvoreBaixa) formParam;

		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);

		idBaixa = form.getIdBaixa();
		idBaixaPai = form.getIdBaixa();
		idNomeBaixa = form.getIdNomeBaixa();
		nmBaixa = form.getNmBaixa();
		dsPath = form.getDsPath();
		inDisponibilidade = form.getInDisponibilidade();
		inFolha = form.getInFolha();
		indicador = "sucess1";

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return this.begin(mapping, form, request, response);
	}
	
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="criarItemBaixa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward criarItemBaixa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:criarItemBaixa" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}	
	
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="editarItemBaixa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editarItemBaixa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:editarItemBaixa" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}		
	
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="detalheBaixa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward detalheBaixa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:detalheBaixa" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}		

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterArvoreBaixa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaDadosInsereItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:carregaDadosInsereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmArvoreBaixaConsultaVO admArvoreBaixaConsultaVO = AdmArvoreBaixaConsultaVO.Factory.newInstance();
			admArvoreBaixaConsultaVO.setIdBaixa(ConstantesCRM.SZERO);
			admArvoreBaixaConsultaVO.setIdBaixaPai(idBaixaPai);
			admArvoreBaixaConsultaVO.setIdNomeBaixa(idNomeBaixa);
			admArvoreBaixaConsultaVO.setNmBaixa(nmBaixa);
			admArvoreBaixaConsultaVO.setInEditar(ConstantesCRM.SONE);

			AdmIndicadoresAnatelContainerVO admIndicadoresAnatelContainerVO = AdmIndicadoresAnatelContainerVO.Factory.newInstance();
			admIndicadoresAnatelContainerVO.addNewIndicadoresAssociados();
			admIndicadoresAnatelContainerVO.addNewIndicadoresExistentes();
			AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = controlBaixa.carregaDadosTelaInserir(admArvoreBaixaConsultaVO, ConstantesCRM.getCurrentUser(request.getSession()));
			formInsereBaixa = new FormInsereBaixa();

			if (admArvoreBaixaContainerVO.getAdmMensagemAvisoVOArray() != null) {
				formInsereBaixa.setMensagens(admArvoreBaixaContainerVO.getAdmMensagemAvisoVOArray());
			} else {
				formInsereBaixa.setMensagens(new AdmMensagemAvisoVO[0]);
			}

			if (admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmIndicadoresAnatelContainerVO() != null) {
				formInsereBaixa.setAdmIndicadorAnatelAssociadoVO(admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmIndicadoresAnatelContainerVO().getIndicadoresAssociados().getAdmIndicadorAnatelVOArray());
			} else {
				formInsereBaixa.setAdmIndicadorAnatelAssociadoVO(new AdmIndicadorAnatelVO[0]);
			}

			if (admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmIndicadoresAnatelContainerVO() != null) {
				formInsereBaixa.setAdmIndicadorAnatelExistenteVO(admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmIndicadoresAnatelContainerVO().getIndicadoresExistentes().getAdmIndicadorAnatelVOArray());
			} else {
				formInsereBaixa.setAdmIndicadorAnatelExistenteVO(new AdmIndicadorAnatelVO[0]);
			}
			formInsereBaixa.setAdmNomeBaixaVO(admArvoreBaixaContainerVO.getAdmNomeBaixaVOArray());

			AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO = new AdmComunicacaoBaixaVO[admArvoreBaixaContainerVO.getAdmNomeBaixaVOArray().length];

			String[] arrayIdTipoComunicao = new String[admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length];
			String[] arrayDsTipoComunicao = new String[admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length];
			for (int i = 0; i < admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length; i++) {
				arrayIdTipoComunicao[i] = admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray(i).getIdTipoComunicacao();
				arrayDsTipoComunicao[i] = admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray(i).getDsTipoComunicacao();
				contador = i + 1;
			}

			request.getSession().setAttribute("idTipoComunicacao", arrayIdTipoComunicao);
			request.getSession().setAttribute("dsTipoComunicacao", arrayDsTipoComunicao);
			request.setAttribute("flag", "insere");			
			request.getSession().setAttribute("dsPath", dsPath);
			formInsereBaixa.setAdmComunicacaoBaixaVO(admComunicacaoBaixaVO);
			formInsereBaixa.setNmBaixa(nmBaixa);
			formInsereBaixa.setDsPath(dsPath);

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreBaixaController:carregaDadosInsereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreBaixa.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreBaixaController:carregaDadosInsereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);		
		ActionRedirect action = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		action.addParameter("flag", "insere");
		return action;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaEditarItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:montaEditarItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormEditaBaixa form = (FormEditaBaixa) formParam;
		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);
		try {
			idBaixa = form.getIdBaixa();
			idBaixaPai = form.getIdBaixaPai();
			idNomeBaixa = form.getIdNomeBaixa();
			nmBaixa = form.getNmBaixa();
			inDisponibilidade = form.getInDisponibilidade();
			inFolha = form.getInFolha();
			indicador = "editar";

		} catch (Exception e) {
			log.error("AdmArvoreBaixaController:montaEditarItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return this.begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterArvoreBaixa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaDadosEditaItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:carregaDadosEditaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormEditaBaixa form = (FormEditaBaixa) formParam;
		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmArvoreBaixaConsultaVO admArvoreBaixaConsultaVO = AdmArvoreBaixaConsultaVO.Factory.newInstance();
			admArvoreBaixaConsultaVO.setIdBaixa(idBaixa);
			admArvoreBaixaConsultaVO.setIdBaixaPai(idBaixaPai);
			admArvoreBaixaConsultaVO.setIdNomeBaixa(idNomeBaixa);
			admArvoreBaixaConsultaVO.setNmBaixa(nmBaixa);
			admArvoreBaixaConsultaVO.setInEditar(ConstantesCRM.SONE);

			AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = controlBaixa.carregaDadosTelaEditar(admArvoreBaixaConsultaVO, ConstantesCRM.getCurrentUser(request.getSession()));
			formEditaBaixa = new FormEditaBaixa();

			if (admArvoreBaixaContainerVO.getAdmMensagemAvisoVOArray() != null) {
				formEditaBaixa.setMensagens(admArvoreBaixaContainerVO.getAdmMensagemAvisoVOArray());
			} else {
				formEditaBaixa.setMensagens(new AdmMensagemAvisoVO[0]);
			}

			if (admArvoreBaixaContainerVO.getAdmNomeBaixaVOArray() != null) {
				formEditaBaixa.setAdmNomeBaixaVO(admArvoreBaixaContainerVO.getAdmNomeBaixaVOArray());
			}

			formEditaBaixa.setAdmIndicadorAnatelAssociadoVO(admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmIndicadoresAnatelContainerVO().getIndicadoresAssociados().getAdmIndicadorAnatelVOArray());
			formEditaBaixa.setAdmIndicadorAnatelExistenteVO(admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmIndicadoresAnatelContainerVO().getIndicadoresExistentes().getAdmIndicadorAnatelVOArray());
			formEditaBaixa.setIdBaixa(idBaixa);
			formEditaBaixa.setIdBaixaPai(idBaixaPai);
			formEditaBaixa.setIdNomeBaixa(idNomeBaixa);
			formEditaBaixa.setIdNomeBaixaExistente(idNomeBaixa);
			formEditaBaixa.setNmBaixa(nmBaixa);
			formEditaBaixa.setInDisponibilidade(inDisponibilidade);
			formEditaBaixa.setInFolha(inFolha);

			formEditaBaixa.setDsPath(form.getDsPath());

			String[] idBaixaMensagem = new String[admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length];
			String[] dsMensagem = new String[admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length];
			String[] idTipoComunicacao = new String[admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length];
			String[] dsTipoComunicacao = new String[admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length];

			String[] idTipoComunicacaoTemp = new String[admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length];
			String[] dsTipoComunicacaoTemp = new String[admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length];

			for (int i = 0; i < admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length; i++) {
				dsMensagem[i] = admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray(i).getAdmMensagemAvisoVO().getDsMensagemAviso();
				idBaixaMensagem[i] = admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray(i).getAdmMensagemAvisoVO().getIdMensagemAviso();
				idTipoComunicacao[i] = admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray(i).getAdmComunicacaoBaixaVO().getIdTipoComunicacao();
				dsTipoComunicacao[i] = admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray(i).getAdmComunicacaoBaixaVO().getDsTipoComunicacao();
			}

			for (int i = 0; i < admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length; i++) {
				idTipoComunicacaoTemp[i] = admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray(i).getIdTipoComunicacao();
				dsTipoComunicacaoTemp[i] = admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray(i).getDsTipoComunicacao();
			}

			for (int j = 0; j < admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length; j++) {

				for (int i = 0; i < admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length; i++) {
					if (idTipoComunicacao[j].equals(idTipoComunicacaoTemp[i])) {
						idTipoComunicacaoTemp[i] = idTipoComunicacaoTemp[i].replaceAll(idTipoComunicacaoTemp[i], "");
						dsTipoComunicacaoTemp[i] = dsTipoComunicacaoTemp[i].replaceAll(dsTipoComunicacaoTemp[i], "");
					}
				}
			}

			contador = admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length;

			request.getSession().setAttribute("idTipoComunicacaoLista", idTipoComunicacaoTemp);
			request.getSession().setAttribute("dsTipoComunicacaoLista", dsTipoComunicacaoTemp);

			request.getSession().setAttribute("idTipoComunicacao", idTipoComunicacao);
			request.getSession().setAttribute("dsTipoComunicacao", dsTipoComunicacao);

			request.getSession().setAttribute("idBaixaMensagem", idBaixaMensagem);
			request.getSession().setAttribute("dsMensagem", dsMensagem);

			request.setAttribute("flag", "edita");

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreBaixaController:carregaDadosEditaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreBaixa.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreBaixaController:carregaDadosEditaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		ActionRedirect action = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		action.addParameter("flag", "edita");
		return action;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaItemEditado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:salvaItemEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormEditaBaixa form = (FormEditaBaixa) formParam;
		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);
		try {
			if (request.getParameter("aux") != null && !request.getParameter("aux").equals(form.getNmBaixa())) {
				form.setIdNomeBaixa(null);
				idNomeBaixa = ConstantesCRM.SVAZIO;
			}

			String[] indicadorAnatel = new String[form.getArrayAdmIndicadorAnatelAssociado().length];
			indicadorAnatel = form.getArrayAdmIndicadorAnatelAssociado();
			AdmIndicadorAnatelVO[] admIndicadorAnatelVO = new AdmIndicadorAnatelVO[form.getArrayAdmIndicadorAnatelAssociado().length];

			for (int i = 0; i < indicadorAnatel.length; i++) {
				admIndicadorAnatelVO[i] = AdmIndicadorAnatelVO.Factory.newInstance();
				admIndicadorAnatelVO[i].setIdIndicador(indicadorAnatel[i]);
			}

			AdmIndicadoresAnatelContainerVO admIndicadoresAnatelContainerVO = AdmIndicadoresAnatelContainerVO.Factory.newInstance();
			admIndicadoresAnatelContainerVO.setIdBaixa(form.getIdBaixa());
			admIndicadoresAnatelContainerVO.addNewIndicadoresAssociados().setAdmIndicadorAnatelVOArray(admIndicadorAnatelVO);

			AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO = new AdmComunicacaoBaixaVO[contador];
			AdmMensagemBaixaVO[] admMensagemBaixaVO = new AdmMensagemBaixaVO[contador];

			int count = 0;

			for (int i = 0; i < contador; i++) {
				try {

					if (request.getParameter("kenai" + String.valueOf(i + 100)) != null && request.getParameter("kenai" + String.valueOf(i + 100)).equals("1")) {
						admComunicacaoBaixaVO[count] = AdmComunicacaoBaixaVO.Factory.newInstance();
						admComunicacaoBaixaVO[count].setIdTipoComunicacao(request.getParameter("koda" + String.valueOf(i + 100)));

						admMensagemBaixaVO[count] = AdmMensagemBaixaVO.Factory.newInstance();
						admMensagemBaixaVO[count].addNewAdmMensagemAvisoVO();

						admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setDsMensagemAviso(request.getParameter("dsMensagem" + String.valueOf(i + 100)));
						admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setIdMensagemAviso("");

						admMensagemBaixaVO[count].setAdmComunicacaoBaixaVO(admComunicacaoBaixaVO[count]);
						count++;

					} else if (request.getParameter("kenai" + String.valueOf(i + 100)) != null && request.getParameter("kenai" + String.valueOf(i + 100)).equals("2")) {
						admComunicacaoBaixaVO[count] = AdmComunicacaoBaixaVO.Factory.newInstance();
						admComunicacaoBaixaVO[count].setIdTipoComunicacao(request.getParameter("koda" + String.valueOf(i + 100)));
						admComunicacaoBaixaVO[count].setDsTipoComunicacao(ConstantesCRM.SVAZIO);
						admComunicacaoBaixaVO[count].setSgTipoComunicacao(ConstantesCRM.SVAZIO);

						admMensagemBaixaVO[count] = AdmMensagemBaixaVO.Factory.newInstance();
						admMensagemBaixaVO[count].addNewAdmMensagemAvisoVO();

						admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setIdMensagemAviso(request.getParameter("idMensagemAtual" + String.valueOf(i + 100)));
						admMensagemBaixaVO[count].setAdmComunicacaoBaixaVO(admComunicacaoBaixaVO[count]);
						count++;

					}

					if (request.getParameter("kenai" + String.valueOf(i)) != null && request.getParameter("kenai" + String.valueOf(i)).equals("1")) {
						admComunicacaoBaixaVO[count] = AdmComunicacaoBaixaVO.Factory.newInstance();
						admComunicacaoBaixaVO[count].setIdTipoComunicacao(request.getParameter("koda" + String.valueOf(i)));
						admComunicacaoBaixaVO[count].setDsTipoComunicacao(ConstantesCRM.SVAZIO);
						admComunicacaoBaixaVO[count].setSgTipoComunicacao(ConstantesCRM.SVAZIO);

						admMensagemBaixaVO[count] = AdmMensagemBaixaVO.Factory.newInstance();
						admMensagemBaixaVO[count].addNewAdmMensagemAvisoVO();

						admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setDsMensagemAviso(request.getParameter("dsMensagem" + String.valueOf(i)));
						admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setIdMensagemAviso(ConstantesCRM.SVAZIO);

						admMensagemBaixaVO[count].setAdmComunicacaoBaixaVO(admComunicacaoBaixaVO[count]);
						count++;

					} else if (request.getParameter("kenai" + String.valueOf(i)) != null && request.getParameter("kenai" + String.valueOf(i)).equals(ConstantesCRM.STWO)) {
						admComunicacaoBaixaVO[count] = AdmComunicacaoBaixaVO.Factory.newInstance();
						admComunicacaoBaixaVO[count].setIdTipoComunicacao(request.getParameter("koda" + String.valueOf(i)));
						admComunicacaoBaixaVO[count].setDsTipoComunicacao(ConstantesCRM.SVAZIO);
						admComunicacaoBaixaVO[count].setSgTipoComunicacao(ConstantesCRM.SVAZIO);

						admMensagemBaixaVO[count] = AdmMensagemBaixaVO.Factory.newInstance();
						admMensagemBaixaVO[count].addNewAdmMensagemAvisoVO();

						admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setIdMensagemAviso(request.getParameter("idMensagemAtual" + String.valueOf(i)));
						admMensagemBaixaVO[count].setAdmComunicacaoBaixaVO(admComunicacaoBaixaVO[count]);

						count++;

					}
				} catch (NullPointerException e) {
					admComunicacaoBaixaVO[0] = AdmComunicacaoBaixaVO.Factory.newInstance();
				}

			}

			AdmFolhaBaixaVO admFolhaBaixaVO = AdmFolhaBaixaVO.Factory.newInstance();
			admFolhaBaixaVO.setIdBaixa(idBaixa);
			admFolhaBaixaVO.setIdBaixaPai(idBaixaPai);

			// msg existente
			if (request.getParameter("idBaixaNome") != null && request.getParameter("idBaixaNome").equals(ConstantesCRM.STWO)) {
				admFolhaBaixaVO.setIdNomeBaixa(form.getIdNomeBaixaExistente());
				admFolhaBaixaVO.setNmBaixa(ConstantesCRM.SVAZIO);

			} else if (request.getParameter("idBaixaNome") != null && request.getParameter("idBaixaNome").equals(ConstantesCRM.SONE)) {
				admFolhaBaixaVO.setIdNomeBaixa(form.getIdNomeBaixa());
				admFolhaBaixaVO.setNmBaixa(ConstantesCRM.SVAZIO);

				// msg nova.
			} else if (request.getParameter("idBaixaNome") != null && request.getParameter("idBaixaNome").equals(ConstantesCRM.SZERO))

			{
				admFolhaBaixaVO.setIdNomeBaixa(ConstantesCRM.SVAZIO);
				admFolhaBaixaVO.setNmBaixa(form.getNmBaixaNovo());
			}

			// if(form.getNmBaixa().equals("")){
			// admFolhaBaixaVO.setIdNomeBaixa(form.getIdNomeBaixa());
			// admFolhaBaixaVO.setNmBaixa(nmBaixa);
			// }else{
			// admFolhaBaixaVO.setIdNomeBaixa(idNomeBaixa);
			// admFolhaBaixaVO.setNmBaixa(form.getNmBaixa());
			// }
			//

			admFolhaBaixaVO.addNewAdmIndicadoresAnatelContainerVO();
			admFolhaBaixaVO.setAdmIndicadoresAnatelContainerVO(admIndicadoresAnatelContainerVO);

			for (int i = 0; i < count; i++) {
				admFolhaBaixaVO.insertNewAdmMensagemBaixaVO(i);
				admFolhaBaixaVO.setAdmMensagemBaixaVOArray(i, admMensagemBaixaVO[i]);
			}
			if (count > 0) {
				admFolhaBaixaVO.setInFolha(ConstantesCRM.SONE);
			} else {
				admFolhaBaixaVO.setInFolha(ConstantesCRM.SZERO);
			}

			AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = AdmArvoreBaixaContainerVO.Factory.newInstance();
			admArvoreBaixaContainerVO.addNewAdmFolhaBaixaVO();
			admArvoreBaixaContainerVO.setAdmFolhaBaixaVO(admFolhaBaixaVO);

			admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().setInDisponibilidade(form.getInDisponibilidade());

			controlBaixa.salvaItemBaixaEditado(admArvoreBaixaContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));

			request.setAttribute("flag", "nao");
			indicador = ConstantesCRM.SVAZIO;
			idBaixa = ConstantesCRM.SVAZIO;
			idBaixaPai = ConstantesCRM.SVAZIO;
			idNomeBaixa = ConstantesCRM.SVAZIO;
			nmBaixa = ConstantesCRM.SVAZIO;
			dsPath = ConstantesCRM.SVAZIO;
			contador = 0;

			try {
				request.getSession().removeAttribute("idTipoComunicacaoLista");
				request.getSession().removeAttribute("dsTipoComunicacaoLista");
				request.getSession().removeAttribute("idTipoComunicacao");
				request.getSession().removeAttribute("dsTipoComunicacao");
				request.getSession().removeAttribute("idBaixaMensagem");
				request.getSession().removeAttribute("dsPath");

			} catch (Exception ex) {
				log.error("AdmArvoreBaixaController:carregaDadosEditaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", ex);
			}

			formArvoreBaixa.setMsgError(ConstantesCRM.SSucesso);
			exibeMsg=true;
			cont=1;
		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreBaixaController:carregaDadosEditaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreBaixa.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());
			indicador = ConstantesCRM.SVAZIO;

		} catch (Exception e) {

			if (e.getMessage().length() > 11 && e.getMessage().substring(11, 12).equalsIgnoreCase(ConstantesCRM.SONE)) {
				log.warn("AdmArvoreBaixaController:carregaDadosEditaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute("msgError", e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				indicador = ConstantesCRM.SVAZIO;
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return this.begin(mapping, form, request, response);
			}

			log.error("AdmArvoreBaixaController:carregaDadosEditaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return this.begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaDetalharItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:montaDetalharItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormEditaBaixa form = (FormEditaBaixa) formParam;
		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);
		try {
			idBaixa = form.getIdBaixa();
			idBaixaPai = form.getIdBaixaPai();
			idNomeBaixa = form.getIdNomeBaixa();
			nmBaixa = form.getNmBaixa();
			indicador = "detalhar";

		} catch (Exception e) {
			log.error("AdmArvoreBaixaController:montaDetalharItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return this.begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterArvoreBaixa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaDadosDetalha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:carregaDadosDetalha" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormEditaBaixa form = (FormEditaBaixa) formParam;
		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);

		try {
			AdmArvoreBaixaConsultaVO admArvoreBaixaConsultaVO = AdmArvoreBaixaConsultaVO.Factory.newInstance();
			admArvoreBaixaConsultaVO.setIdBaixa(idBaixa);
			admArvoreBaixaConsultaVO.setIdBaixaPai(idBaixaPai);
			admArvoreBaixaConsultaVO.setIdNomeBaixa(idNomeBaixa);
			admArvoreBaixaConsultaVO.setNmBaixa(nmBaixa);
			admArvoreBaixaConsultaVO.setInEditar(ConstantesCRM.SONE);

			// System.out.println(idBaixa);

			AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = controlBaixa.carregaDadosTelaEditar(admArvoreBaixaConsultaVO, ConstantesCRM.getCurrentUser(request.getSession()));
			formEditaBaixa = new FormEditaBaixa();

			formEditaBaixa.setAdmIndicadorAnatelAssociadoVO(admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmIndicadoresAnatelContainerVO().getIndicadoresAssociados().getAdmIndicadorAnatelVOArray());
			formEditaBaixa.setAdmIndicadorAnatelExistenteVO(admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmIndicadoresAnatelContainerVO().getIndicadoresExistentes().getAdmIndicadorAnatelVOArray());
			formEditaBaixa.setIdBaixa(idBaixa);
			formEditaBaixa.setIdBaixaPai(idBaixaPai);
			formEditaBaixa.setIdNomeBaixa(idNomeBaixa);
			formEditaBaixa.setNmBaixa(nmBaixa);

			formEditaBaixa.setDsPath(form.getDsPath());

			String[] idBaixaMensagem = new String[admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length];
			String[] dsMensagem = new String[admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length];
			String[] idTipoComunicacao = new String[admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length];
			String[] dsTipoComunicacao = new String[admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length];

			String[] idTipoComunicacaoTemp = new String[admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length];
			String[] dsTipoComunicacaoTemp = new String[admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length];

			for (int i = 0; i < admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length; i++) {
				// idBaixaMensagem[i] =
				// admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray(i).getIdBaixaMensagem();
				// System.out.println(idBaixaMensagem[i]);
				dsMensagem[i] = admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray(i).getAdmMensagemAvisoVO().getDsMensagemAviso();

				// System.out.println(dsMensagem[i]);
				idTipoComunicacao[i] = admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray(i).getAdmComunicacaoBaixaVO().getIdTipoComunicacao();
				// System.out.println(idTipoComunicacao[i]);
				dsTipoComunicacao[i] = admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray(i).getAdmComunicacaoBaixaVO().getDsTipoComunicacao();
				// System.out.println(dsTipoComunicacao[i]);
			}

			for (int i = 0; i < admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length; i++) {
				idTipoComunicacaoTemp[i] = admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray(i).getIdTipoComunicacao();
				dsTipoComunicacaoTemp[i] = admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray(i).getDsTipoComunicacao();
			}

			for (int j = 0; j < admArvoreBaixaContainerVO.getAdmFolhaBaixaVO().getAdmMensagemBaixaVOArray().length; j++) {

				for (int i = 0; i < admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length; i++) {
					if (idTipoComunicacao[j].equals(idTipoComunicacaoTemp[i])) {

						idTipoComunicacaoTemp[i] = idTipoComunicacaoTemp[i].replaceAll(idTipoComunicacaoTemp[i], "");
						dsTipoComunicacaoTemp[i] = dsTipoComunicacaoTemp[i].replaceAll(dsTipoComunicacaoTemp[i], "");

					}

				}
			}
			contador = admArvoreBaixaContainerVO.getAdmComunicacaoBaixaVOArray().length;

			request.getSession().setAttribute("idTipoComunicacaoLista", idTipoComunicacaoTemp);
			request.getSession().setAttribute("dsTipoComunicacaoLista", dsTipoComunicacaoTemp);

			request.getSession().setAttribute("idTipoComunicacao", idTipoComunicacao);
			request.getSession().setAttribute("dsTipoComunicacao", dsTipoComunicacao);

			request.getSession().setAttribute("idBaixaMensagem", idBaixaMensagem);
			request.getSession().setAttribute("dsMensagem", dsMensagem);

			request.setAttribute("flag", "detalha");

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreBaixaController:carregaDadosDetalha" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreBaixa.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreBaixaController:carregaDadosDetalha" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		ActionRedirect action = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		action.addParameter("flag", "detalha");
		return action;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaItemInserido(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreBaixaController:salvaItemInserido" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormInsereBaixa form = (FormInsereBaixa) formParam;
		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);

		try {
			String[] indicadorAnatel = new String[form.getArrayAdmIndicadorAnatelAssociado().length];
			indicadorAnatel = form.getArrayAdmIndicadorAnatelAssociado();
			AdmIndicadorAnatelVO[] admIndicadorAnatelVO = new AdmIndicadorAnatelVO[form.getArrayAdmIndicadorAnatelAssociado().length];

			for (int i = 0; i < indicadorAnatel.length; i++) {
				admIndicadorAnatelVO[i] = AdmIndicadorAnatelVO.Factory.newInstance();
				admIndicadorAnatelVO[i].setIdIndicador(indicadorAnatel[i]);
			}

			AdmIndicadoresAnatelContainerVO admIndicadoresAnatelContainerVO = AdmIndicadoresAnatelContainerVO.Factory.newInstance();
			admIndicadoresAnatelContainerVO.setIdBaixa(ConstantesCRM.SVAZIO);
			admIndicadoresAnatelContainerVO.addNewIndicadoresAssociados().setAdmIndicadorAnatelVOArray(admIndicadorAnatelVO);

			AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO = new AdmComunicacaoBaixaVO[contador];
			AdmMensagemBaixaVO[] admMensagemBaixaVO = new AdmMensagemBaixaVO[contador];

			int count = 0;
			for (int i = 0; i < contador; i++) {
				// mensagem nova.
				if (request.getParameter("kenai" + String.valueOf(i)).equals(ConstantesCRM.SONE)) {
					admComunicacaoBaixaVO[count] = AdmComunicacaoBaixaVO.Factory.newInstance();
					admComunicacaoBaixaVO[count].setIdTipoComunicacao(request.getParameter("koda" + String.valueOf(i)));
					admComunicacaoBaixaVO[count].setDsTipoComunicacao(ConstantesCRM.SVAZIO);
					admComunicacaoBaixaVO[count].setSgTipoComunicacao(ConstantesCRM.SVAZIO);

					AdmMensagemBaixaVO mensagemBaixaVO = AdmMensagemBaixaVO.Factory.newInstance();
					mensagemBaixaVO.setAdmMensagemAvisoVO(AdmMensagemAvisoVO.Factory.newInstance());

					admMensagemBaixaVO[count] = mensagemBaixaVO;
					admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setDsMensagemAviso(request.getParameter("dsMensagem" + String.valueOf(i)));
					admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setIdMensagemAviso(ConstantesCRM.SVAZIO);
					admMensagemBaixaVO[count].setAdmComunicacaoBaixaVO(admComunicacaoBaixaVO[count]);
					count++;

					// mensagem existente.
				} else if (request.getParameter("kenai" + String.valueOf(i)).equals(ConstantesCRM.STWO)) {
					admComunicacaoBaixaVO[count] = AdmComunicacaoBaixaVO.Factory.newInstance();
					admComunicacaoBaixaVO[count].setIdTipoComunicacao(request.getParameter("koda" + String.valueOf(i)));
					admComunicacaoBaixaVO[count].setDsTipoComunicacao(ConstantesCRM.SVAZIO);
					admComunicacaoBaixaVO[count].setSgTipoComunicacao(ConstantesCRM.SVAZIO);

					AdmMensagemBaixaVO mensagemBaixaVO = AdmMensagemBaixaVO.Factory.newInstance();
					mensagemBaixaVO.setAdmMensagemAvisoVO(AdmMensagemAvisoVO.Factory.newInstance());

					admMensagemBaixaVO[count] = mensagemBaixaVO;
					admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setDsMensagemAviso(ConstantesCRM.SVAZIO);
					admMensagemBaixaVO[count].getAdmMensagemAvisoVO().setIdMensagemAviso(request.getParameter("idMensagemAtual" + String.valueOf(i)));
					admMensagemBaixaVO[count].setAdmComunicacaoBaixaVO(admComunicacaoBaixaVO[count]);
					count++;
				}
			}

			AdmFolhaBaixaVO admFolhaBaixaVO = AdmFolhaBaixaVO.Factory.newInstance();
			admFolhaBaixaVO.setIdBaixa(ConstantesCRM.SVAZIO);
			admFolhaBaixaVO.setIdBaixaPai(idBaixaPai);
			admFolhaBaixaVO.setDsPath(dsPath);
			admFolhaBaixaVO.setInDisponibilidade(form.getInDisponibilidade());
			admFolhaBaixaVO.setInFolha(form.getInFolha());

			if (form.getNmBaixaEscolhido().equals(ConstantesCRM.SVAZIO)) {
				admFolhaBaixaVO.setIdNomeBaixa(form.getIdNomeBaixa());
				admFolhaBaixaVO.setNmBaixa(ConstantesCRM.SVAZIO);
			} else {
				admFolhaBaixaVO.setIdNomeBaixa(ConstantesCRM.SVAZIO);
				admFolhaBaixaVO.setNmBaixa(form.getNmBaixaEscolhido());
			}
			//
			admFolhaBaixaVO.addNewAdmIndicadoresAnatelContainerVO();
			admFolhaBaixaVO.setAdmIndicadoresAnatelContainerVO(admIndicadoresAnatelContainerVO);
			// count = admMensagemBaixaVO.length;
			// System.out.println(admMensagemBaixaVO.length);
			for (int i = 0; i < count; i++) {
				admFolhaBaixaVO.insertNewAdmMensagemBaixaVO(i);
				admFolhaBaixaVO.setAdmMensagemBaixaVOArray(i, admMensagemBaixaVO[i]);
			}
			AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = AdmArvoreBaixaContainerVO.Factory.newInstance();
			admArvoreBaixaContainerVO.addNewAdmFolhaBaixaVO();
			admArvoreBaixaContainerVO.setAdmFolhaBaixaVO(admFolhaBaixaVO);
			controlBaixa.insereItemBaixa(admArvoreBaixaContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));

			request.setAttribute("flag", "nao");
			indicador = ConstantesCRM.SVAZIO;
			idBaixa = ConstantesCRM.SVAZIO;
			idBaixaPai = ConstantesCRM.SVAZIO;
			idNomeBaixa = ConstantesCRM.SVAZIO;
			nmBaixa = ConstantesCRM.SVAZIO;
			dsPath = ConstantesCRM.SVAZIO;
			contador = 0;

			try {
				request.getSession().removeAttribute("idTipoComunicacao");
				request.getSession().removeAttribute("dsTipoComunicacao");
				request.getSession().removeAttribute("dsPath");

			} catch (Exception ex) {
				log.error("AdmArvoreBaixaController:salvaItemInserido" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", ex);
			}
			formArvoreBaixa.setMsgError(ConstantesCRM.SSucesso);
			exibeMsg=true;
			cont=1;

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreBaixaController:salvaItemInserido" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreBaixa.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());
			indicador = ConstantesCRM.SVAZIO;

		} catch (Exception e) {
			if (e.getMessage().length() > 11 && e.getMessage().substring(11, 12).equalsIgnoreCase("1")) {
				log.warn("AdmArvoreBaixaController:salvaItemInserido" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute("msgError", e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				indicador = ConstantesCRM.SVAZIO;
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return this.begin(mapping, form, request, response);
			}

			log.error("AdmArvoreBaixaController:salvaItemInserido" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return this.begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward habilitaDesabilitaArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:habilitaDesabilitaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormInsereBaixa form = (FormInsereBaixa) formParam;

		try {
			formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);
			AdmFolhaBaixaVO baixaFolhaVO = AdmFolhaBaixaVO.Factory.newInstance();

			// Se habilitado --> Desebilita.
			if (form.getInDisponibilidade() != null && form.getInDisponibilidade().trim().equals(ConstantesCRM.SONE)) {
				baixaFolhaVO.setInDisponibilidade(ConstantesCRM.SZERO);
			} else if (form.getInDisponibilidade() != null && form.getInDisponibilidade().trim().equals(ConstantesCRM.SZERO)) {
				baixaFolhaVO.setInDisponibilidade(ConstantesCRM.SONE);
			}

			baixaFolhaVO.setIdBaixa(form.getIdBaixa());

			AdmAtualizacaoArvoreBaixaVO admAtualizacaoArvoreBaixaVO = this.controlBaixa.habilitaArvoreBaixa(baixaFolhaVO, ConstantesCRM.getCurrentUser(request.getSession()));

			if (admAtualizacaoArvoreBaixaVO.getIdBaixaArray().length >= 1 && !ConstantesCRM.SZERO.equals(admAtualizacaoArvoreBaixaVO.getIdBaixaArray(0))) {
				this.controlBaixa.habilitaArvoreBaixaUpdate(admAtualizacaoArvoreBaixaVO, ConstantesCRM.getCurrentUser(request.getSession()));
			}

			formArvoreBaixa.setMsgError(ConstantesCRM.SSucesso);
			exibeMsg=true;
			cont=1;

		} catch (TuxedoWarningException twe) {

			log.warn("AdmArvoreBaixaController:habilitaDesabilitaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreBaixa.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {

			log.error("AdmArvoreBaixaController:habilitaDesabilitaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);

		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return this.begin(mapping, form, request, response);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iframeArvoreBaixa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaArvoreParte(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormArvoreBaixa form = (FormArvoreBaixa) formParam;
		formArvoreBaixa.setMsgError(ConstantesCRM.SVAZIO);

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);

			AdmIdBaixaVO admIdBaixaVO = AdmIdBaixaVO.Factory.newInstance();
			admIdBaixaVO.setIdBaixa(form.getIdBaixa());

			AdmArvoreBaixaContainerVO admArvoreBaixaVO = AdmArvoreBaixaContainerVO.Factory.newInstance();
			admArvoreBaixaVO = controlBaixa.carregaArvoreBaixa(admIdBaixaVO, ConstantesCRM.getCurrentUser(request.getSession()));

			StringBuffer script = new StringBuffer();
			StringBuffer node = new StringBuffer();
			String folder = ConstantesCRM.SVAZIO;

			for (int i = 0; i < admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray().length; i++) {

				boolean inFolha = false;

				if (admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha().trim().equals(ConstantesCRM.SONE)) {
					folder = "',\"Javascript:capturaParametrosContato('";
				} else {
					folder = "',\"Javascript:expandirNo('";
				}

				if (admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha().trim().equals(ConstantesCRM.SONE)) {
					inFolha = true;
				}

				if (inFolha) {
					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa())).append(folder).append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixaPai()).append("','")
					.append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdNomeBaixa()).append("','").append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa())).append("','").append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getDsPath())).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade())
					.append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha()).append("','");

					if (admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade().trim().equals(ConstantesCRM.SONE)) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');");
					}

				} else {
					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa())).append(folder).append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixaPai()).append("','")
					.append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdNomeBaixa()).append("','").append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa())).append("','").append(StringUtilStatic.escapaComillasJS2(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getDsPath())).append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade())
					.append("','").append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha()).append("','");

					if (admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade().trim().equals(ConstantesCRM.SONE)) {
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
			log.warn("AdmArvoreBaixaController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvoreBaixa.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreBaixaController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="innerBrowserBaixa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getNome(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreBaixaController:getNome" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormArvoreBaixa form = (FormArvoreBaixa) formParam;
		try {
			if (formEditaBaixa == null) {
				formEditaBaixa = new FormEditaBaixa();
			}

			AdmIdBaixaVO admIdBaixaVO = AdmIdBaixaVO.Factory.newInstance();
			admIdBaixaVO.setNmBaixa(form.getDescPesquisa());

			formEditaBaixa.setAdmNomeBaixaVO(this.controlBaixa.getNome(admIdBaixaVO, ConstantesCRM.getCurrentUser(request.getSession())).getAdmNomeBaixaVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreBaixaController:getNome" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formEditaBaixa.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AdmArvoreBaixaController:getNome" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormArvoreBaixa extends ActionForm {

		private static final long serialVersionUID = 162504035457345208L;

		private String descPesquisa;
		private String msgError = ConstantesCRM.SVAZIO;
		private String inDisponibilidade;
		private String inFolha;
		private String[] mensagensAtuais;
		private AdmMensagemAvisoVO[] mensagens;
		private String dsPath;
		private String sgTipoComunicacao;
		private String nmBaixa;
		private String dsTipoComunicacao;
		private String dsMensagem;
		private String idTipoComunicacao;
		private String idBaixaMensagem;
		private String idNomeBaixa;
		private String idBaixaPai;
		private String[] arrayIndicadoresAnatelAssociados;
		private String[] arrayIndicadoresAnatelExistentes;
		private AdmIndicadorAnatelVO[] admIndicadoresAnatelAssociadosVO;
		private AdmIndicadorAnatelVO[] admIndicadoresAnatelExistentesVO;
		private String[] arrayAdmComunicacaoBaixaVO;
		private AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO;
		private String[] arrayAdmMensagemBaixa;
		private AdmMensagemBaixaVO[] admMensagemBaixaVO;
		private AdmFolhaBaixaVO[] admFolhaBaixaVO;
		private AdmArvoreBaixaContainerVO arvoreBaixa;
		private String idBaixa;

		public FormArvoreBaixa() {
			sgTipoComunicacao = ConstantesCRM.SVAZIO;
			nmBaixa = ConstantesCRM.SVAZIO;
			dsTipoComunicacao = ConstantesCRM.SVAZIO;
			dsMensagem = ConstantesCRM.SVAZIO;
			idTipoComunicacao = ConstantesCRM.SVAZIO;
			idBaixaMensagem = ConstantesCRM.SVAZIO;
			idNomeBaixa = ConstantesCRM.SVAZIO;
			idBaixaPai = ConstantesCRM.SVAZIO;
			idBaixa = ConstantesCRM.SVAZIO;
			dsPath = ConstantesCRM.SVAZIO;
			arrayIndicadoresAnatelAssociados = new String[0];
			arrayIndicadoresAnatelExistentes = new String[0];
			arrayAdmComunicacaoBaixaVO = new String[0];
			arrayAdmMensagemBaixa = new String[0];
			admIndicadoresAnatelAssociadosVO = new AdmIndicadorAnatelVO[0];
			admIndicadoresAnatelExistentesVO = new AdmIndicadorAnatelVO[0];
			admComunicacaoBaixaVO = new AdmComunicacaoBaixaVO[0];
			admMensagemBaixaVO = new AdmMensagemBaixaVO[0];
			arvoreBaixa = AdmArvoreBaixaContainerVO.Factory.newInstance();
		}

		public void setIdBaixa(String idBaixa) {
			this.idBaixa = idBaixa;
		}

		public String getIdBaixa() {
			return this.idBaixa;
		}

		public void setArvoreBaixa(AdmArvoreBaixaContainerVO arvoreBaixa) {
			this.arvoreBaixa = arvoreBaixa;
		}

		public AdmArvoreBaixaContainerVO getArvoreBaixa() {
			return this.arvoreBaixa;
		}

		public void setAdmFolhaBaixaVO(AdmFolhaBaixaVO[] admFolhaBaixaVO) {
			this.admFolhaBaixaVO = admFolhaBaixaVO;
		}

		public AdmFolhaBaixaVO[] getAdmFolhaBaixaVO() {
			return this.admFolhaBaixaVO;
		}

		public void setAdmMensagemBaixaVO(AdmMensagemBaixaVO[] admMensagemBaixaVO) {
			this.admMensagemBaixaVO = admMensagemBaixaVO;
		}

		public AdmMensagemBaixaVO[] getAdmMensagemBaixaVO() {
			return this.admMensagemBaixaVO;
		}

		public void setArrayAdmMensagemBaixa(String[] arrayAdmMensagemBaixa) {
			this.arrayAdmMensagemBaixa = arrayAdmMensagemBaixa;
		}

		public String[] getArrayAdmMensagemBaixa() {
			if (this.arrayAdmMensagemBaixa == null || this.arrayAdmMensagemBaixa.length == 0) {
				this.arrayAdmMensagemBaixa = new String[1];
			}
			return this.arrayAdmMensagemBaixa;
		}

		public void setAdmComunicacaoBaixaVO(AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO) {
			this.admComunicacaoBaixaVO = admComunicacaoBaixaVO;
		}

		public AdmComunicacaoBaixaVO[] getAdmComunicacaoBaixaVO() {
			return this.admComunicacaoBaixaVO;
		}

		public void setArrayAdmComunicacaoBaixaVO(String[] arrayAdmComunicacaoBaixaVO) {
			this.arrayAdmComunicacaoBaixaVO = arrayAdmComunicacaoBaixaVO;
		}

		public String[] getArrayAdmComunicacaoBaixaVO() {
			if (this.arrayAdmComunicacaoBaixaVO == null || this.arrayAdmComunicacaoBaixaVO.length == 0) {
				this.arrayAdmComunicacaoBaixaVO = new String[1];
			}
			return this.arrayAdmComunicacaoBaixaVO;
		}

		public void setAdmIndicadoresAnatelExistentesVO(AdmIndicadorAnatelVO[] admIndicadoresAnatelExistentesVO) {
			this.admIndicadoresAnatelExistentesVO = admIndicadoresAnatelExistentesVO;
		}

		public AdmIndicadorAnatelVO[] getAdmIndicadoresAnatelExistentesVO() {
			return this.admIndicadoresAnatelExistentesVO;
		}

		public void setAdmIndicadoresAnatelAssociadosVO(AdmIndicadorAnatelVO[] admIndicadoresAnatelAssociadosVO) {
			this.admIndicadoresAnatelAssociadosVO = admIndicadoresAnatelAssociadosVO;
		}

		public AdmIndicadorAnatelVO[] getAdmIndicadoresAnatelAssociadosVO() {
			return this.admIndicadoresAnatelAssociadosVO;
		}

		public void setArrayIndicadoresAnatelExistentes(String[] arrayIndicadoresAnatelExistentes) {
			this.arrayIndicadoresAnatelExistentes = arrayIndicadoresAnatelExistentes;
		}

		public String[] getArrayIndicadoresAnatelExistentes() {
			if (this.arrayIndicadoresAnatelExistentes == null || this.arrayIndicadoresAnatelExistentes.length == 0) {
				this.arrayIndicadoresAnatelExistentes = new String[1];
			}

			return this.arrayIndicadoresAnatelExistentes;
		}

		public void setArrayIndicadoresAnatelAssociados(String[] arrayIndicadoresAnatelAssociados) {
			this.arrayIndicadoresAnatelAssociados = arrayIndicadoresAnatelAssociados;
		}

		public String[] getArrayIndicadoresAnatelAssociados() {
			if (this.arrayIndicadoresAnatelAssociados == null || this.arrayIndicadoresAnatelAssociados.length == 0) {
				this.arrayIndicadoresAnatelAssociados = new String[1];
			}

			return this.arrayIndicadoresAnatelAssociados;
		}

		public void setIdBaixaPai(String idBaixaPai) {
			this.idBaixaPai = idBaixaPai;
		}

		public String getIdBaixaPai() {
			return this.idBaixaPai;
		}

		public void setIdNomeBaixa(String idNomeBaixa) {
			this.idNomeBaixa = idNomeBaixa;
		}

		public String getIdNomeBaixa() {
			return this.idNomeBaixa;
		}

		public void setIdBaixaMensagem(String idBaixaMensagem) {
			this.idBaixaMensagem = idBaixaMensagem;
		}

		public String getIdBaixaMensagem() {
			return this.idBaixaMensagem;
		}

		public void setIdTipoComunicacao(String idTipoComunicacao) {
			this.idTipoComunicacao = idTipoComunicacao;
		}

		public String getIdTipoComunicacao() {
			return this.idTipoComunicacao;
		}

		public void setDsMensagem(String dsMensagem) {
			this.dsMensagem = dsMensagem;
		}

		public String getDsMensagem() {
			return this.dsMensagem;
		}

		public void setDsTipoComunicacao(String dsTipoComunicacao) {
			this.dsTipoComunicacao = dsTipoComunicacao;
		}

		public String getDsTipoComunicacao() {
			return this.dsTipoComunicacao;
		}

		public void setNmBaixa(String nmBaixa) {
			this.nmBaixa = nmBaixa;
		}

		public String getNmBaixa() {
			return this.nmBaixa;
		}

		public void setSgTipoComunicacao(String sgTipoComunicacao) {
			this.sgTipoComunicacao = sgTipoComunicacao;
		}

		public String getSgTipoComunicacao() {
			return this.sgTipoComunicacao;
		}

		public void setDsPath(String dsPath) {
			this.dsPath = dsPath;
		}

		public String getDsPath() {
			return this.dsPath;
		}

		public void setMensagens(AdmMensagemAvisoVO[] mensagens) {
			this.mensagens = mensagens;
		}

		public AdmMensagemAvisoVO[] getMensagens() {
			return this.mensagens;
		}

		public void setMensagensAtuais(String[] mensagensAtuais) {
			this.mensagensAtuais = mensagensAtuais;
		}

		public String[] getMensagensAtuais() {
			if (this.mensagensAtuais == null || this.mensagensAtuais.length == 0) {
				this.mensagensAtuais = new String[1];
			}
			return this.mensagensAtuais;
		}

		public void setInFolha(String inFolha) {
			this.inFolha = inFolha;
		}

		public String getInFolha() {
			return this.inFolha;
		}

		public void setInDisponibilidade(String inDisponibilidade) {
			this.inDisponibilidade = inDisponibilidade;
		}

		public String getInDisponibilidade() {
			return this.inDisponibilidade;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setDescPesquisa(String descPesquisa) {
			this.descPesquisa = descPesquisa;
		}

		public String getDescPesquisa() {
			return this.descPesquisa;
		}
	}

	public FormArvoreBaixa getFormArvoreBaixa() {
		return this.formArvoreBaixa;
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class FormInsereBaixa extends ActionForm {

		private static final long serialVersionUID = 3235907156052266047L;

		private String descPesquisa;
		private String idMensagemAtual;
		private String inDisponibilidade;
		private String inFolha;
		private AdmMensagemAvisoVO[] mensagens;
		private String dsPath;
		private String idNomeBaixa;
		private String nmBaixaEscolhido;
		private String nmBaixa;
		private AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO;
		private String[] arrayAdmIndicadorAnatelExistente;
		private String[] arrayAdmIndicadorAnatelAssociado;
		private AdmIndicadorAnatelVO[] admIndicadorAnatelAssociadoVO;
		private AdmIndicadorAnatelVO[] admIndicadorAnatelExistenteVO;
		private AdmNomeBaixaVO[] admNomeBaixaVO;
		private String idBaixa;

		public void setIdBaixa(String idBaixa) {
			this.idBaixa = idBaixa;
		}

		public String getIdBaixa() {
			return this.idBaixa;
		}

		public void setAdmNomeBaixaVO(AdmNomeBaixaVO[] admNomeBaixaVO) {
			this.admNomeBaixaVO = admNomeBaixaVO;
		}

		public AdmNomeBaixaVO[] getAdmNomeBaixaVO() {
			return this.admNomeBaixaVO;
		}

		public void setAdmIndicadorAnatelExistenteVO(AdmIndicadorAnatelVO[] admIndicadorAnatelExistenteVO) {
			this.admIndicadorAnatelExistenteVO = admIndicadorAnatelExistenteVO;
		}

		public AdmIndicadorAnatelVO[] getAdmIndicadorAnatelExistenteVO() {
			return this.admIndicadorAnatelExistenteVO;
		}

		public void setAdmIndicadorAnatelAssociadoVO(AdmIndicadorAnatelVO[] admIndicadorAnatelAssociadoVO) {
			this.admIndicadorAnatelAssociadoVO = admIndicadorAnatelAssociadoVO;
		}

		public AdmIndicadorAnatelVO[] getAdmIndicadorAnatelAssociadoVO() {
			return this.admIndicadorAnatelAssociadoVO;
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

		public void setAdmComunicacaoBaixaVO(AdmComunicacaoBaixaVO[] admComunicacaoBaixaVO) {
			this.admComunicacaoBaixaVO = admComunicacaoBaixaVO;
		}

		public AdmComunicacaoBaixaVO[] getAdmComunicacaoBaixaVO() {
			return this.admComunicacaoBaixaVO;
		}

		public void setNmBaixa(String nmBaixa) {
			this.nmBaixa = nmBaixa;
		}

		public String getNmBaixa() {
			return this.nmBaixa;
		}

		public void setNmBaixaEscolhido(String nmBaixaEscolhido) {
			this.nmBaixaEscolhido = nmBaixaEscolhido;
		}

		public String getNmBaixaEscolhido() {
			return this.nmBaixaEscolhido;
		}

		public void setIdNomeBaixa(String idNomeBaixa) {
			this.idNomeBaixa = idNomeBaixa;
		}

		public String getIdNomeBaixa() {
			return this.idNomeBaixa;
		}

		public void setDsPath(String dsPath) {
			this.dsPath = dsPath;
		}

		public String getDsPath() {
			return this.dsPath;
		}

		public void setInFolha(String inFolha) {
			this.inFolha = inFolha;
		}

		public String getInFolha() {
			return this.inFolha;
		}

		public void setInDisponibilidade(String inDisponibilidade) {
			this.inDisponibilidade = inDisponibilidade;
		}

		public String getInDisponibilidade() {
			return this.inDisponibilidade;
		}

		public void setMensagens(AdmMensagemAvisoVO[] mensagens) {
			this.mensagens = mensagens;
		}

		public AdmMensagemAvisoVO[] getMensagens() {
			return this.mensagens;
		}

		public void setIdMensagemAtual(String idMensagemAtual) {
			this.idMensagemAtual = idMensagemAtual;
		}

		public String getIdMensagemAtual() {
			return this.idMensagemAtual;
		}

		public void setDescPesquisa(String descPesquisa) {
			this.descPesquisa = descPesquisa;
		}

		public String getDescPesquisa() {
			return this.descPesquisa;
		}
	}

	public FormInsereBaixa getFormInsereBaixa() {
		return this.formInsereBaixa;
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class FormEditaBaixa extends ActionForm {

		private static final long serialVersionUID = 7311337038676580288L;

		private String nmBaixaNovo;
		private String msgError;
		private String descPesquisa;
		private String idNomeBaixaExistente;
		private AdmNomeBaixaVO[] admNomeBaixaVO;
		private String inDisponibilidade;
		private String inFolha;
		private String idMensagemAtual;
		private br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO[] mensagens;
		private String dsPath;
		private String[] arrayAdmIndicadorAnatelExistente;
		private String[] arrayAdmIndicadorAnatelAssociado;
		private AdmIndicadorAnatelVO[] admIndicadorAnatelExistenteVO;
		private AdmIndicadorAnatelVO[] admIndicadorAnatelAssociadoVO;
		private String idNomeBaixa;
		private String nmBaixa;
		private String idBaixaPai;
		private String idBaixa;

		public void setIdBaixa(String idBaixa) {
			this.idBaixa = idBaixa;
		}

		public String getIdBaixa() {
			return this.idBaixa;
		}

		public void setIdBaixaPai(String idBaixaPai) {
			this.idBaixaPai = idBaixaPai;
		}

		public String getIdBaixaPai() {
			return this.idBaixaPai;
		}

		public void setNmBaixa(String nmBaixa) {
			this.nmBaixa = nmBaixa;
		}

		public String getNmBaixa() {
			return this.nmBaixa;
		}

		public void setIdNomeBaixa(String idNomeBaixa) {
			this.idNomeBaixa = idNomeBaixa;
		}

		public String getIdNomeBaixa() {
			return this.idNomeBaixa;
		}

		public void setAdmIndicadorAnatelAssociadoVO(AdmIndicadorAnatelVO[] admIndicadorAnatelAssociadoVO) {
			this.admIndicadorAnatelAssociadoVO = admIndicadorAnatelAssociadoVO;
		}

		public AdmIndicadorAnatelVO[] getAdmIndicadorAnatelAssociadoVO() {
			return this.admIndicadorAnatelAssociadoVO;
		}

		public void setAdmIndicadorAnatelExistenteVO(AdmIndicadorAnatelVO[] admIndicadorAnatelExistenteVO) {
			this.admIndicadorAnatelExistenteVO = admIndicadorAnatelExistenteVO;
		}

		public AdmIndicadorAnatelVO[] getAdmIndicadorAnatelExistenteVO() {
			return this.admIndicadorAnatelExistenteVO;
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

		public void setDsPath(String dsPath) {
			this.dsPath = dsPath;
		}

		public String getDsPath() {
			return this.dsPath;
		}

		public void setMensagens(br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO[] mensagens) {
			this.mensagens = mensagens;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO[] getMensagens() {
			return this.mensagens;
		}

		public void setIdMensagemAtual(String idMensagemAtual) {
			this.idMensagemAtual = idMensagemAtual;
		}

		public String getIdMensagemAtual() {
			return this.idMensagemAtual;
		}

		public void setInFolha(String inFolha) {
			this.inFolha = inFolha;
		}

		public String getInFolha() {
			return this.inFolha;
		}

		public void setInDisponibilidade(String inDisponibilidade) {
			this.inDisponibilidade = inDisponibilidade;
		}

		public String getInDisponibilidade() {
			return this.inDisponibilidade;
		}

		public void setAdmNomeBaixaVO(AdmNomeBaixaVO[] admNomeBaixaVO) {
			this.admNomeBaixaVO = admNomeBaixaVO;
		}

		public AdmNomeBaixaVO[] getAdmNomeBaixaVO() {
			return this.admNomeBaixaVO;
		}

		public void setIdNomeBaixaExistente(String idNomeBaixaExistente) {
			this.idNomeBaixaExistente = idNomeBaixaExistente;
		}

		public String getIdNomeBaixaExistente() {
			return this.idNomeBaixaExistente;
		}

		public void setDescPesquisa(String descPesquisa) {
			this.descPesquisa = descPesquisa;
		}

		public String getDescPesquisa() {
			return this.descPesquisa;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setNmBaixaNovo(String nmBaixaNovo) {
			this.nmBaixaNovo = nmBaixaNovo;
		}

		public String getNmBaixaNovo() {
			return this.nmBaixaNovo;
		}
	}

	public FormEditaBaixa getFormEditaBaixa() {
		return this.formEditaBaixa;
	}

}
