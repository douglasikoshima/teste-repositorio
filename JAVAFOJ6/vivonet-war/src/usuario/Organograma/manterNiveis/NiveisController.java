package usuario.Organograma.manterNiveis;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.organograma.Organograma;
import br.com.vivo.fo.ctrls.usuario.organograma.OrganogramaImpl;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.AdmOrgNivelContainerVODocument.AdmOrgNivelContainerVO;
import br.com.vivo.fo.usuario.vo.CargoVODocument.CargoVO;
import br.com.vivo.fo.usuario.vo.NivelCargoRelacionamentoVODocument.NivelCargoRelacionamentoVO;
import br.com.vivo.fo.usuario.vo.NivelOrganogramaVODocument.NivelOrganogramaVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class NiveisController extends AbstractAction {

	@EJB
	private Organograma controlOrganograma;

	public FormNivel formNivel = new FormNivel();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	private String trataStringRetorno(String strParam) {
		if (strParam == null)
			return null;

		strParam = strParam.replaceAll("'", "&#39;");
		strParam = strParam.replaceAll("\"", "&#34;");
		strParam = strParam.replaceAll("\\\\", "\\\\\\\\");
		strParam = strParam.replaceAll("Ç", "&#199;");
		strParam = strParam.replaceAll("ç", "&#231;");
		return strParam;

	}

	private String trataStringRetorno1(String strParam) {
		if (strParam == null)
			return null;

		strParam = strParam.replaceAll("'", "'+String.fromCharCode(39)+'");
		strParam = strParam.replaceAll("\"", "'+String.fromCharCode(34)+'");
		strParam = strParam.replaceAll("\\\\",
				"'+String.fromCharCode(92)+String.fromCharCode(92)+'");
		strParam = strParam.replaceAll("Ç", "'+String.fromCharCode(199)+'");
		strParam = strParam.replaceAll("ç", "'+String.fromCharCode(231)+'");
		return strParam;

	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("removeItem".equals(mapping.getParameter())) {
			return removeItem(mapping, form, request, response);
		} else if ("gravaItem".equals(mapping.getParameter())) {
			return gravaItem(mapping, form, request, response);
		} else if ("alteraItem".equals(mapping.getParameter())) {
			return alteraItem(mapping, form, request, response);
		} else if ("editaRelCargo".equals(mapping.getParameter())) {
			return editaRelCargo(mapping, form, request, response);
		} else if ("salvaRelacion".equals(mapping.getParameter())) {
			return salvaRelacion(mapping, form, request, response);
		} else if ("alterarNivel".equals(mapping.getParameter())) {
			return alterarNivel(mapping, form, request, response);
		} else if ("manterDescricao".equals(mapping.getParameter())) {
			return manterDescricao(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="nivelHieraquico.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("NiveisController:begin" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formNivel == null) {
			formNivel = new FormNivel();
			formNivel.setMsgError(ConstantesCRM.SVAZIO);
		}
		try {
			formNivel = geraArvore(formNivel, request);
			String script = "if (document.getElementById) {var tree = new WebFXTree('"
					+ trataStringRetorno(formNivel.getArvoreNivel()
							.getNivelOrganogramaVO().getDsNivel())
					+ "',\"Javascript:capturaParametros('"
					+ formNivel.getArvoreNivel().getNivelOrganogramaVO()
							.getIdNivel()
					+ "','"
					+ formNivel.getArvoreNivel().getNivelOrganogramaVO()
							.getIdNivelPai()
					+ "','"
					+ trataStringRetorno1(formNivel.getArvoreNivel()
							.getNivelOrganogramaVO().getDsNivel())
					+ "','2"
					+ "');\",'classic');";
			script = script
					+ montaArvore(formNivel.getArvoreNivel()
							.getNivelOrganogramaVO(), "tree")
					+ "document.write(tree);}";
			if (request.getSession().getAttribute("arvore") != null)
				request.getSession().removeAttribute("arvore");
			request.getSession().setAttribute("arvore", script);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaCargoController:begin" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " ) --> MENSAGEM --> "
					+ twe.getXmlHeader().getStatusText());
			formNivel.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {

			log.error(
					"NiveisController:begin" + "( "
							+ ConstantesCRM.getCurrentUser(request.getSession())
							+ " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private FormNivel geraArvore(FormNivel formNivel, HttpServletRequest request)
			throws TuxedoWarningException, Exception {
		formNivel.setArvoreNivel(criaArvoreNivelContainer(request));
		return formNivel;
	}

	private AdmOrgNivelContainerVO criaArvoreNivelContainer(HttpServletRequest request)
			throws TuxedoWarningException, Exception {
		NivelOrganogramaVO nivelOrganogramaVO = NivelOrganogramaVO.Factory
				.newInstance();
		nivelOrganogramaVO.setIdNivel(ConstantesCRM.SVAZIO);
		AdmOrgNivelContainerVO admOrgNivelContainerVO = controlOrganograma
				.cargaArvoreNivel(nivelOrganogramaVO,
						ConstantesCRM.getCurrentUser(request.getSession()));
		return admOrgNivelContainerVO;
	}

	private String montaArvore(NivelOrganogramaVO itens, String tree) {

		String node = ConstantesCRM.SVAZIO;
		String folder = ConstantesCRM.SVAZIO;
		String folder2 = ConstantesCRM.SVAZIO;

		if (itens.getCargoVOArray().length > 0) {
			for (int x = 0; x < itens.getCargoVOArray().length; x++) {

				if (itens.getCargoVOArray().length == 0)
					folder2 = "',\"JavaScript:capturaParametros('";
				else
					folder2 = "',\"JavaScript:capturaParametros('";

				node = node
						+ "var "
						+ tree
						+ String.valueOf(x)
						+ " = new WebFXTreeItem('"
						+ trataStringRetorno(itens.getCargoVOArray(x)
								.getNmCargo())
						+ folder2
						+ itens.getCargoVOArray(x).getIdCargo()
						+ "','"
						+ "','"
						+ trataStringRetorno1(itens.getCargoVOArray(x)
								.getNmCargo())
						+ "','0"
						+ "');\""

						+ ",'','/FrontOfficeWeb/resources/images/cargo.gif','/FrontOfficeWeb/resources/images/cargo.gif');";

				node = node + tree + ".add(" + tree + String.valueOf(x)
						+ ");\n";
			}
		}

		if (itens.getNivelOrganogramaVOArray().length > 0) {

			for (int x = 0; x < itens.getNivelOrganogramaVOArray().length; x++) {

				if (itens.getNivelOrganogramaVOArray(x)
						.getNivelOrganogramaVOArray().length == 0)
					folder = "',\"JavaScript:capturaParametros('";
				else
					folder = "',\"JavaScript:capturaParametros('";

				if (itens.getNivelOrganogramaVOArray(x)
						.getNivelOrganogramaVOArray().length > 0) {
					node = node
							+ "var "
							+ tree
							+ String.valueOf(x)
							+ " = new WebFXTreeItem('"
							+ trataStringRetorno(itens
									.getNivelOrganogramaVOArray(x).getDsNivel())
							+ folder
							+ itens.getNivelOrganogramaVOArray(x).getIdNivel()
							+ "','"
							+ itens.getNivelOrganogramaVOArray(x)
									.getIdNivelPai()
							+ "','"
							+ trataStringRetorno1(itens
									.getNivelOrganogramaVOArray(x).getDsNivel())
							+ "');\",'','/FrontOfficeWeb/resources/images/cargoniveis.gif','/FrontOfficeWeb/resources/images/cargoniveis.gif');";

					node = node
							+ montaArvore(itens.getNivelOrganogramaVOArray(x),
									tree + String.valueOf(x));
					node = node + tree + ".add(" + tree + String.valueOf(x)
							+ ");\n";
				} else {
					node = node
							+ "var "
							+ tree
							+ String.valueOf(x)
							+ " = new WebFXTreeItem('"
							+ trataStringRetorno(itens
									.getNivelOrganogramaVOArray(x).getDsNivel())
							+ folder
							+ itens.getNivelOrganogramaVOArray(x).getIdNivel()
							+ "','"
							+ itens.getNivelOrganogramaVOArray(x)
									.getIdNivelPai()
							+ "','"
							+ trataStringRetorno1(itens
									.getNivelOrganogramaVOArray(x).getDsNivel())
							+ "');\",'','/FrontOfficeWeb/resources/images/cargoniveis.gif','/FrontOfficeWeb/resources/images/cargoniveis.gif');";
					node = node
							+ montaArvore(itens.getNivelOrganogramaVOArray(x),
									tree + String.valueOf(x));
					node = node + tree + ".add(" + tree + String.valueOf(x)
							+ ");\n";
				}
			}

		}

		return node;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormNivel form = (FormNivel) formParam;

		log.debug("NiveisController:removeItem" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formNivel == null) {
			formNivel = new FormNivel();
		}

		formNivel.setMsgError(ConstantesCRM.SVAZIO);

		try {

			AdmOrgNivelContainerVO nivelContainer = AdmOrgNivelContainerVO.Factory
					.newInstance();
			formNivel.setFlag("no");
			nivelContainer.setIdNivel(form.getIdNivel());
			nivelContainer.setIdNivelPai(form.getIdNivelPai());
			controlOrganograma.excluiNivel(nivelContainer,
					ConstantesCRM.getCurrentUser(request.getSession()));
			formNivel.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("NiveisController:removeItem" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " ) --> MENSAGEM --> "
					+ twe.getXmlHeader().getStatusText());
			formNivel.setMsgError(twe.getMessage().substring(
					twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error(
					"NiveisController:removeItem" + "( "
							+ ConstantesCRM.getCurrentUser(request.getSession())
							+ " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
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
	public ActionForward gravaItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormNivel form = (FormNivel) formParam;

		log.debug("NiveisController:gravaItem" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formNivel == null) {
			formNivel = new FormNivel();
		}
		formNivel.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmOrgNivelContainerVO nivelContainer = AdmOrgNivelContainerVO.Factory
					.newInstance();

			nivelContainer.setIdNivel(ConstantesCRM.SVAZIO);
			nivelContainer.setIdNivelPai(form.getIdNivel());
			nivelContainer.setDsNivel(form.getDsNivel());

			controlOrganograma.gravaNivel(nivelContainer,
					ConstantesCRM.getCurrentUser(request.getSession()));
			formNivel.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {

			log.warn("NiveisController:gravaItem" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " ) --> MENSAGEM --> "
					+ twe.getXmlHeader().getStatusText());
			formNivel.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error(
					"NiveisController:gravaItem" + "( "
							+ ConstantesCRM.getCurrentUser(request.getSession())
							+ " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
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
	public ActionForward alteraItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormNivel form = (FormNivel) formParam;

		log.debug("NiveisController:alteraItem" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formNivel == null) {
			formNivel = new FormNivel();
		}

		formNivel.setMsgError(ConstantesCRM.SVAZIO);

		try {

			AdmOrgNivelContainerVO nivelContainer = AdmOrgNivelContainerVO.Factory
					.newInstance();
			nivelContainer.setIdNivel(form.getIdNivel());
			nivelContainer.setDsNivel(form.getDsNivel());
			controlOrganograma.alteraNivel(nivelContainer,
					ConstantesCRM.getCurrentUser(request.getSession()));
			formNivel.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("NiveisController:alteraItem" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " ) --> MENSAGEM --> "
					+ twe.getXmlHeader().getStatusText());
			formNivel.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {

			log.error(
					"NiveisController:alteraItem" + "( "
							+ ConstantesCRM.getCurrentUser(request.getSession())
							+ " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarCargo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaRelCargo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormNivel form = (FormNivel) formParam;

		log.debug("NiveisController:editaRelCargo" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formNivel == null) {
			formNivel = new FormNivel();
		}
		formNivel.setMsgError(ConstantesCRM.SVAZIO);
		try {
			String nivelId = request.getParameter("idNivel");
			if (nivelId == null)
				nivelId = form.getIdNivel();
			String nivelDs = request.getParameter("dsNivel");
			if (nivelDs == null)
				nivelDs = form.getDsNivel();
			if (request.getAttribute("dsNivel") != null)
				request.removeAttribute("dsNivel");
			request.setAttribute("dsNivel", nivelDs);

			NivelOrganogramaVO nivel = NivelOrganogramaVO.Factory.newInstance();// formNivel.
																				// .getCargoVOById(cargoID,arrayCargos);
			nivel.setIdNivel(nivelId);

			NivelCargoRelacionamentoVO nivelCargoRel = NivelCargoRelacionamentoVO.Factory
					.newInstance();
			nivelCargoRel = controlOrganograma.listaRelacionarNivelCargo(nivel,
					ConstantesCRM.getCurrentUser(request.getSession()));

			formNivel.setIdNivel(nivelId);
			formNivel.setDsNivel(nivelDs);
			formNivel.setArrayCargosExistentesVO(nivelCargoRel.getExistentes()
					.getCargoVOArray());
			formNivel.setArrayCargosRelacionadosVO(nivelCargoRel
					.getRelacionados().getCargoVOArray());
			formNivel.setFlag(ConstantesCRM.SYES);

		} catch (TuxedoWarningException twe) {

			log.warn("NiveisController:editaRelCargo" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " ) --> MENSAGEM --> "
					+ twe.getXmlHeader().getStatusText());
			formNivel.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error(
					"NiveisController:editaRelCargo" + "( "
							+ ConstantesCRM.getCurrentUser(request.getSession())
							+ " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
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
	public ActionForward salvaRelacion(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormNivel form = (FormNivel) formParam;

		log.debug("NiveisController:salvaRelacion" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formNivel == null) {
			formNivel = new FormNivel();
		}

		formNivel.setMsgError(ConstantesCRM.SVAZIO);

		try {

			String lista[] = form.getArrayCargosRelacionados();
			NivelCargoRelacionamentoVO nivelCargoRelVO = NivelCargoRelacionamentoVO.Factory
					.newInstance();
			if (lista.length > 0)
				nivelCargoRelVO.addNewRelacionados();
			for (int i = 0; i < form.getArrayCargosRelacionados().length; i++) {
				if (lista[i] != null)
					if (!lista[i].trim().equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
						CargoVO cargoVO = CargoVO.Factory.newInstance();
						cargoVO.setIdCargo(lista[i]);
						cargoVO.setNmCargo(ConstantesCRM.SVAZIO);
						nivelCargoRelVO.getRelacionados().insertNewCargoVO(i);
						nivelCargoRelVO.getRelacionados().setCargoVOArray(i,
								cargoVO);
					}
			}
			nivelCargoRelVO.setIdNivel(form.getIdNivel());
			NivelCargoRelacionamentoVO nivelCargoRel = NivelCargoRelacionamentoVO.Factory
					.newInstance();
			nivelCargoRel = controlOrganograma.salvaNivelCargoRelacionados(
					nivelCargoRelVO,
					ConstantesCRM.getCurrentUser(request.getSession()));

			formNivel.setIdNivel(form.getIdNivel());
			formNivel.setDsNivel(form.getDsNivel());
			formNivel.setArrayCargosExistentesVO(nivelCargoRel.getExistentes()
					.getCargoVOArray());
			formNivel.setArrayCargosRelacionadosVO(nivelCargoRel
					.getRelacionados().getCargoVOArray());
			formNivel.setFlag(ConstantesCRM.SYES);
			// formNivel = geraArvore(form);
			formNivel.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {

			log.warn("NiveisController:salvaRelacion" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " ) --> MENSAGEM --> "
					+ twe.getXmlHeader().getStatusText());
			formNivel.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error(
					"NiveisController:salvaRelacion" + "( "
							+ ConstantesCRM.getCurrentUser(request.getSession())
							+ " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alterarNivel.jsp"
	 */
	public ActionForward alterarNivel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormNivel form = (FormNivel) formParam;
	
		log.debug("NiveisController:alterarNivel" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formNivel.setIdNivel(request.getParameter("idNivel"));
		formNivel.setIdNivelPai(request.getParameter("idNivelPai"));
		formNivel.setDsNivel(request.getParameter("dsNivel"));
		formNivel.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterDescricao.jsp"
	 */
	public ActionForward manterDescricao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormNivel form = (FormNivel) formParam;
		
		log.debug("NiveisController:manterDescricao" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formNivel.setIdNivel(request.getParameter("idNivel"));
		formNivel.setIdNivelPai(request.getParameter("idNivelPai"));
		formNivel.setDsNivel(request.getParameter("dsNivel"));
		formNivel.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormNivel extends ActionForm {
		private String msgError = ConstantesCRM.SVAZIO;

		private String[] arrayCargosRelacionados;

		private String[] arrayCargosExistentes;

		private String flag;

		private CargoVO[] arrayCargosRelacionadosVO;

		private CargoVO[] arrayCargosExistentesVO;

		private String dsNivel;

		private String idNivelPai;

		private AdmOrgNivelContainerVO arvoreNivel;

		private String idNivel;

		public FormNivel() {
			flag = ConstantesCRM.SVAZIO;
		}

		public void setIdNivel(String idNivel) {
			this.idNivel = idNivel;
		}

		public String getIdNivel() {
			return this.idNivel;
		}

		public void setArvoreNivel(AdmOrgNivelContainerVO arvoreNivel) {
			this.arvoreNivel = arvoreNivel;
		}

		public AdmOrgNivelContainerVO getArvoreNivel() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize arvorNivel if it is null.
			// if(this.arvorNivel == null)
			// {
			// this.arvorNivel = new NivelOrganogramaVO(?);
			// }

			return this.arvoreNivel;
		}

		public void setIdNivelPai(String idNivelPai) {
			this.idNivelPai = idNivelPai;
		}

		public String getIdNivelPai() {
			return this.idNivelPai;
		}

		public void setDsNivel(String dsNivel) {
			this.dsNivel = dsNivel;
		}

		public String getDsNivel() {
			return this.dsNivel;
		}

		public void setArrayCargosExistentesVO(CargoVO[] arrayCargosExistentesVO) {
			this.arrayCargosExistentesVO = arrayCargosExistentesVO;
		}

		public CargoVO[] getArrayCargosExistentesVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize arrayCargosExistentesVO if it is null or length
			// == 0.
			// if(this.arrayCargosExistentesVO == null ||
			// this.arrayCargosExistentesVO.length == 0)
			// {
			// this.arrayCargosExistentesVO = new CargoVO[1];
			// this.arrayCargosExistentesVO[0] = new CargoVO(?);
			// }

			return this.arrayCargosExistentesVO;
		}

		public void setArrayCargosRelacionadosVO(
				CargoVO[] arrayCargosRelacionadosVO) {
			this.arrayCargosRelacionadosVO = arrayCargosRelacionadosVO;
		}

		public CargoVO[] getArrayCargosRelacionadosVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize arrayCargosRelacionadosVO if it is null or
			// length == 0.
			// if(this.arrayCargosRelacionadosVO == null ||
			// this.arrayCargosRelacionadosVO.length == 0)
			// {
			// this.arrayCargosRelacionadosVO = new CargoVO[1];
			// this.arrayCargosRelacionadosVO[0] = new CargoVO(?);
			// }

			return this.arrayCargosRelacionadosVO;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public String getFlag() {
			return this.flag;
		}

		public void setArrayCargosExistentes(String[] arrayCargosExistentes) {
			this.arrayCargosExistentes = arrayCargosExistentes;
		}

		public String[] getArrayCargosExistentes() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayCargosExistentes == null
					|| this.arrayCargosExistentes.length == 0) {
				this.arrayCargosExistentes = new String[1];
			}

			return this.arrayCargosExistentes;
		}

		public void setArrayCargosRelacionados(String[] arrayCargosRelacionados) {
			this.arrayCargosRelacionados = arrayCargosRelacionados;
		}

		public String[] getArrayCargosRelacionados() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayCargosRelacionados == null
					|| this.arrayCargosRelacionados.length == 0) {
				this.arrayCargosRelacionados = new String[1];
			}

			return this.arrayCargosRelacionados;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public FormNivel getFormNivel() {
		return (this.formNivel);
	}
}
