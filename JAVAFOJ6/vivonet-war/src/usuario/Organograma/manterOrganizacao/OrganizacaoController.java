package usuario.Organograma.manterOrganizacao;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.organograma.Organograma;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.AdmOrgOrganizacaoContainerVODocument.AdmOrgOrganizacaoContainerVO;
import br.com.vivo.fo.usuario.vo.CargoVODocument.CargoVO;
import br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument.ListaTipoOrganizacaoVO;
import br.com.vivo.fo.usuario.vo.OrganizacaoUnidadeRelacionamentoVODocument.OrganizacaoUnidadeRelacionamentoVO;
import br.com.vivo.fo.usuario.vo.OrganizacaoVODocument.OrganizacaoVO;
import br.com.vivo.fo.usuario.vo.TipoOrganizacaoVODocument.TipoOrganizacaoVO;
import br.com.vivo.fo.usuario.vo.UnidadeOrganogramaVODocument.UnidadeOrganogramaVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class OrganizacaoController extends AbstractAction {

	private static final long serialVersionUID = 8257065739188144995L;

	@EJB
	private Organograma controlOrganograma;

	@EJB
	public FormOrganizacao formOrganizacao;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("gravaItem".equals(mapping.getParameter())) {
			return gravaItem(mapping, form, request, response);
		} else if ("removeItem".equals(mapping.getParameter())) {
			return removeItem(mapping, form, request, response);
		} else if ("manter".equals(mapping.getParameter())) {
			return manter(mapping, form, request, response);
		} else if ("alteraItem".equals(mapping.getParameter())) {
			return alteraItem(mapping, form, request, response);
		} else if ("editaRelUnidade".equals(mapping.getParameter())) {
			return editaRelUnidade(mapping, form, request, response);
		} else if ("salvaRelacion".equals(mapping.getParameter())) {
			return salvaRelacion(mapping, form, request, response);
		} else if ("alterarOrganizacao".equals(mapping.getParameter())) {
			return alterarOrganizacao(mapping, form, request, response);
		} else if ("manterDescricao".equals(mapping.getParameter())) {
			return manterDescricao(mapping, form, request, response);
		} 
		
		return begin(mapping, form, request, response);
	}

	private String trataStringRetorno(String strParam) {

		if (strParam == null)
			return null;

		strParam = strParam.replaceAll("'", "&#39;");
		strParam = strParam.replaceAll("\"", "&#34;");
		strParam = strParam.replaceAll("\\\\", "\\\\\\\\");
		strParam = strParam.replaceAll(" ", "&#160;");
		strParam = strParam.replaceAll("¡", "&#161;");
		strParam = strParam.replaceAll("¢", "&#162;");
		strParam = strParam.replaceAll("£", "&#163;");
		strParam = strParam.replaceAll("¤", "&#164;");
		strParam = strParam.replaceAll("¥", "&#165;");
		strParam = strParam.replaceAll("¦", "&#166;");
		strParam = strParam.replaceAll("§", "&#167;");
		strParam = strParam.replaceAll("¨", "&#168;");
		strParam = strParam.replaceAll("©", "&#169;");
		strParam = strParam.replaceAll("ª", "&#170;");
		strParam = strParam.replaceAll("«", "&#171;");
		strParam = strParam.replaceAll("¬", "&#172;");
		strParam = strParam.replaceAll("­", "&#173;");
		strParam = strParam.replaceAll("®", "&#174;");
		strParam = strParam.replaceAll("¯", "&#175;");
		strParam = strParam.replaceAll("°", "&#176;");
		strParam = strParam.replaceAll("±", "&#177;");
		strParam = strParam.replaceAll("²", "&#178;");
		strParam = strParam.replaceAll("³", "&#179;");
		strParam = strParam.replaceAll("´", "&#180;");
		strParam = strParam.replaceAll("µ", "&#181;");
		strParam = strParam.replaceAll("¶", "&#182;");
		strParam = strParam.replaceAll("·", "&#183;");
		strParam = strParam.replaceAll("¸", "&#184;");
		strParam = strParam.replaceAll("¹", "&#185;");
		strParam = strParam.replaceAll("º", "&#186;");
		strParam = strParam.replaceAll("»", "&#187;");
		strParam = strParam.replaceAll("¼", "&#188;");
		strParam = strParam.replaceAll("½", "&#189;");
		strParam = strParam.replaceAll("¾", "&#190;");
		strParam = strParam.replaceAll("¿", "&#191;");
		strParam = strParam.replaceAll("À", "&#192;");
		strParam = strParam.replaceAll("Á", "&#193;");
		strParam = strParam.replaceAll("Â", "&#194;");
		strParam = strParam.replaceAll("Ã", "&#195;");
		strParam = strParam.replaceAll("Ä", "&#196;");
		strParam = strParam.replaceAll("Å", "&#197;");
		strParam = strParam.replaceAll("Æ", "&#198;");
		strParam = strParam.replaceAll("Ç", "&#199;");
		strParam = strParam.replaceAll("È", "&#200;");
		strParam = strParam.replaceAll("É", "&#201;");
		strParam = strParam.replaceAll("Ê", "&#202;");
		strParam = strParam.replaceAll("Ë", "&#203;");
		strParam = strParam.replaceAll("Ì", "&#204;");
		strParam = strParam.replaceAll("Í", "&#205;");
		strParam = strParam.replaceAll("Î", "&#206;");
		strParam = strParam.replaceAll("Ï", "&#207;");
		strParam = strParam.replaceAll("Ð", "&#208;");
		strParam = strParam.replaceAll("Ñ", "&#209;");
		strParam = strParam.replaceAll("Ò", "&#210;");
		strParam = strParam.replaceAll("Ó", "&#211;");
		strParam = strParam.replaceAll("Ô", "&#212;");
		strParam = strParam.replaceAll("Õ", "&#213;");
		strParam = strParam.replaceAll("Ö", "&#214;");
		strParam = strParam.replaceAll("×", "&#215;");
		strParam = strParam.replaceAll("Ø", "&#216;");
		strParam = strParam.replaceAll("Ù", "&#217;");
		strParam = strParam.replaceAll("Ú", "&#218;");
		strParam = strParam.replaceAll("Û", "&#219;");
		strParam = strParam.replaceAll("Ü", "&#220;");
		strParam = strParam.replaceAll("Ý", "&#221;");
		strParam = strParam.replaceAll("Þ", "&#222;");
		strParam = strParam.replaceAll("ß", "&#223;");
		strParam = strParam.replaceAll("à", "&#224;");
		strParam = strParam.replaceAll("á", "&#225;");
		strParam = strParam.replaceAll("â", "&#226;");
		strParam = strParam.replaceAll("ã", "&#227;");
		strParam = strParam.replaceAll("ä", "&#228;");
		strParam = strParam.replaceAll("å", "&#229;");
		strParam = strParam.replaceAll("æ", "&#230;");
		strParam = strParam.replaceAll("ç", "&#231;");
		strParam = strParam.replaceAll("è", "&#232;");
		strParam = strParam.replaceAll("é", "&#233;");
		strParam = strParam.replaceAll("ê", "&#234;");
		strParam = strParam.replaceAll("ë", "&#235;");
		strParam = strParam.replaceAll("ì", "&#236;");
		strParam = strParam.replaceAll("í", "&#237;");
		strParam = strParam.replaceAll("î", "&#238;");
		strParam = strParam.replaceAll("ï", "&#239;");
		strParam = strParam.replaceAll("ð", "&#240;");
		strParam = strParam.replaceAll("ñ", "&#241;");
		strParam = strParam.replaceAll("ò", "&#242;");
		strParam = strParam.replaceAll("ó", "&#243;");
		strParam = strParam.replaceAll("ô", "&#244;");
		strParam = strParam.replaceAll("õ", "&#245;");
		strParam = strParam.replaceAll("ö", "&#246;");
		strParam = strParam.replaceAll("÷", "&#247;");
		strParam = strParam.replaceAll("ø", "&#248;");
		strParam = strParam.replaceAll("ù", "&#249;");
		strParam = strParam.replaceAll("ú", "&#250;");
		strParam = strParam.replaceAll("û", "&#251;");
		strParam = strParam.replaceAll("ü", "&#252;");
		strParam = strParam.replaceAll("ý", "&#253;");
		strParam = strParam.replaceAll("þ", "&#254;");
		strParam = strParam.replaceAll("ÿ", "&#255;");

		return strParam;
	}

	private String trataStringRetorno1(String strParam) {
		if (strParam == null)
			return null;

		strParam = strParam.replaceAll("'", "'+String.fromCharCode(39)+'");
		strParam = strParam.replaceAll("\"", "'+String.fromCharCode(34)+'");
		strParam = strParam.replaceAll("\\\\", "'+String.fromCharCode(92)+String.fromCharCode(92)+'");
		/*
		 * strParam = strParam.replaceAll("!","'+String.fromCharCode(33)+'"); strParam =
		 * strParam.replaceAll("#","'+String.fromCharCode(35)+'"); strParam =
		 * strParam.replaceAll("$","'+String.fromCharCode(36)+'"); strParam =
		 * strParam.replaceAll("%","'+String.fromCharCode(37)+'"); strParam =
		 * strParam.replaceAll("&","'+String.fromCharCode(38)+'"); strParam =
		 * strParam.replaceAll("'","'+String.fromCharCode(39)+'"); strParam =
		 * strParam.replaceAll("(","'+String.fromCharCode(40)+'"); strParam =
		 * strParam.replaceAll(")","'+String.fromCharCode(41)+'"); strParam =
		 * strParam.replaceAll("*","'+String.fromCharCode(42)+'"); strParam =
		 * strParam.replaceAll("+","'+String.fromCharCode(43)+'"); strParam =
		 * strParam.replaceAll(",","'+String.fromCharCode(44)+'"); strParam =
		 * strParam.replaceAll("-","'+String.fromCharCode(45)+'"); strParam =
		 * strParam.replaceAll(".","'+String.fromCharCode(46)+'"); strParam =
		 * strParam.replaceAll("/","'+String.fromCharCode(47)+'"); strParam =
		 * strParam.replaceAll(":","'+String.fromCharCode(58)+'"); strParam =
		 * strParam.replaceAll(";","'+String.fromCharCode(59)+'"); strParam =
		 * strParam.replaceAll("<","'+String.fromCharCode(60)+'"); strParam =
		 * strParam.replaceAll("=","'+String.fromCharCode(61)+'"); strParam =
		 * strParam.replaceAll(">","'+String.fromCharCode(62)+'"); strParam =
		 * strParam.replaceAll("?","'+String.fromCharCode(63)+'"); strParam =
		 * strParam.replaceAll("@","'+String.fromCharCode(64)+'"); strParam =
		 * strParam.replaceAll("[","'+String.fromCharCode(91)+'"); strParam =
		 * strParam.replaceAll("]","'+String.fromCharCode(93)+'"); strParam =
		 * strParam.replaceAll("^","'+String.fromCharCode(94)+'"); strParam =
		 * strParam.replaceAll("_","'+String.fromCharCode(95)+'"); strParam =
		 * strParam.replaceAll("`","'+String.fromCharCode(96)+'"); strParam =
		 * strParam.replaceAll("{","'+String.fromCharCode(123)+'"); strParam =
		 * strParam.replaceAll("|","'+String.fromCharCode(124)+'"); strParam =
		 * strParam.replaceAll("}","'+String.fromCharCode(125)+'"); strParam =
		 * strParam.replaceAll("~","'+String.fromCharCode(126)+'");
		 */
		strParam = strParam.replaceAll(" ", "'+String.fromCharCode(160)+'");
		strParam = strParam.replaceAll("¡", "'+String.fromCharCode(161)+'");
		strParam = strParam.replaceAll("¢", "'+String.fromCharCode(162)+'");
		strParam = strParam.replaceAll("£", "'+String.fromCharCode(163)+'");
		strParam = strParam.replaceAll("¤", "'+String.fromCharCode(164)+'");
		strParam = strParam.replaceAll("¥", "'+String.fromCharCode(165)+'");
		strParam = strParam.replaceAll("¦", "'+String.fromCharCode(166)+'");
		strParam = strParam.replaceAll("§", "'+String.fromCharCode(167)+'");
		strParam = strParam.replaceAll("¨", "'+String.fromCharCode(168)+'");
		strParam = strParam.replaceAll("©", "'+String.fromCharCode(169)+'");
		strParam = strParam.replaceAll("ª", "'+String.fromCharCode(170)+'");
		strParam = strParam.replaceAll("«", "'+String.fromCharCode(171)+'");
		strParam = strParam.replaceAll("¬", "'+String.fromCharCode(172)+'");
		strParam = strParam.replaceAll("­", "'+String.fromCharCode(173)+'");
		strParam = strParam.replaceAll("®", "'+String.fromCharCode(174)+'");
		strParam = strParam.replaceAll("¯", "'+String.fromCharCode(175)+'");
		strParam = strParam.replaceAll("°", "'+String.fromCharCode(176)+'");
		strParam = strParam.replaceAll("±", "'+String.fromCharCode(177)+'");
		strParam = strParam.replaceAll("²", "'+String.fromCharCode(178)+'");
		strParam = strParam.replaceAll("³", "'+String.fromCharCode(179)+'");
		strParam = strParam.replaceAll("´", "'+String.fromCharCode(180)+'");
		strParam = strParam.replaceAll("µ", "'+String.fromCharCode(181)+'");
		strParam = strParam.replaceAll("¶", "'+String.fromCharCode(182)+'");
		strParam = strParam.replaceAll("·", "'+String.fromCharCode(183)+'");
		strParam = strParam.replaceAll("¸", "'+String.fromCharCode(184)+'");
		strParam = strParam.replaceAll("¹", "'+String.fromCharCode(185)+'");
		strParam = strParam.replaceAll("º", "'+String.fromCharCode(186)+'");
		strParam = strParam.replaceAll("»", "'+String.fromCharCode(187)+'");
		strParam = strParam.replaceAll("¼", "'+String.fromCharCode(188)+'");
		strParam = strParam.replaceAll("½", "'+String.fromCharCode(189)+'");
		strParam = strParam.replaceAll("¾", "'+String.fromCharCode(190)+'");
		strParam = strParam.replaceAll("¿", "'+String.fromCharCode(191)+'");
		strParam = strParam.replaceAll("À", "'+String.fromCharCode(192)+'");
		strParam = strParam.replaceAll("Á", "'+String.fromCharCode(193)+'");
		strParam = strParam.replaceAll("Â", "'+String.fromCharCode(194)+'");
		strParam = strParam.replaceAll("Ã", "'+String.fromCharCode(195)+'");
		strParam = strParam.replaceAll("Ä", "'+String.fromCharCode(196)+'");
		strParam = strParam.replaceAll("Å", "'+String.fromCharCode(197)+'");
		strParam = strParam.replaceAll("Æ", "'+String.fromCharCode(198)+'");
		strParam = strParam.replaceAll("Ç", "'+String.fromCharCode(199)+'");
		strParam = strParam.replaceAll("È", "'+String.fromCharCode(200)+'");
		strParam = strParam.replaceAll("É", "'+String.fromCharCode(201)+'");
		strParam = strParam.replaceAll("Ê", "'+String.fromCharCode(202)+'");
		strParam = strParam.replaceAll("Ë", "'+String.fromCharCode(203)+'");
		strParam = strParam.replaceAll("Ì", "'+String.fromCharCode(204)+'");
		strParam = strParam.replaceAll("Í", "'+String.fromCharCode(205)+'");
		strParam = strParam.replaceAll("Î", "'+String.fromCharCode(206)+'");
		strParam = strParam.replaceAll("Ï", "'+String.fromCharCode(207)+'");
		strParam = strParam.replaceAll("Ð", "'+String.fromCharCode(208)+'");
		strParam = strParam.replaceAll("Ñ", "'+String.fromCharCode(209)+'");
		strParam = strParam.replaceAll("Ò", "'+String.fromCharCode(210)+'");
		strParam = strParam.replaceAll("Ó", "'+String.fromCharCode(211)+'");
		strParam = strParam.replaceAll("Ô", "'+String.fromCharCode(212)+'");
		strParam = strParam.replaceAll("Õ", "'+String.fromCharCode(213)+'");
		strParam = strParam.replaceAll("Ö", "'+String.fromCharCode(214)+'");
		strParam = strParam.replaceAll("×", "'+String.fromCharCode(215)+'");
		strParam = strParam.replaceAll("Ø", "'+String.fromCharCode(216)+'");
		strParam = strParam.replaceAll("Ù", "'+String.fromCharCode(217)+'");
		strParam = strParam.replaceAll("Ú", "'+String.fromCharCode(218)+'");
		strParam = strParam.replaceAll("Û", "'+String.fromCharCode(219)+'");
		strParam = strParam.replaceAll("Ü", "'+String.fromCharCode(220)+'");
		strParam = strParam.replaceAll("Ý", "'+String.fromCharCode(221)+'");
		strParam = strParam.replaceAll("Þ", "'+String.fromCharCode(222)+'");
		strParam = strParam.replaceAll("ß", "'+String.fromCharCode(223)+'");
		strParam = strParam.replaceAll("à", "'+String.fromCharCode(224)+'");
		strParam = strParam.replaceAll("á", "'+String.fromCharCode(225)+'");
		strParam = strParam.replaceAll("â", "'+String.fromCharCode(226)+'");
		strParam = strParam.replaceAll("ã", "'+String.fromCharCode(227)+'");
		strParam = strParam.replaceAll("ä", "'+String.fromCharCode(228)+'");
		strParam = strParam.replaceAll("å", "'+String.fromCharCode(229)+'");
		strParam = strParam.replaceAll("æ", "'+String.fromCharCode(230)+'");
		strParam = strParam.replaceAll("ç", "'+String.fromCharCode(231)+'");
		strParam = strParam.replaceAll("è", "'+String.fromCharCode(232)+'");
		strParam = strParam.replaceAll("é", "'+String.fromCharCode(233)+'");
		strParam = strParam.replaceAll("ê", "'+String.fromCharCode(234)+'");
		strParam = strParam.replaceAll("ë", "'+String.fromCharCode(235)+'");
		strParam = strParam.replaceAll("ì", "'+String.fromCharCode(236)+'");
		strParam = strParam.replaceAll("í", "'+String.fromCharCode(237)+'");
		strParam = strParam.replaceAll("î", "'+String.fromCharCode(238)+'");
		strParam = strParam.replaceAll("ï", "'+String.fromCharCode(239)+'");
		strParam = strParam.replaceAll("ð", "'+String.fromCharCode(240)+'");
		strParam = strParam.replaceAll("ñ", "'+String.fromCharCode(241)+'");
		strParam = strParam.replaceAll("ò", "'+String.fromCharCode(242)+'");
		strParam = strParam.replaceAll("ó", "'+String.fromCharCode(243)+'");
		strParam = strParam.replaceAll("ô", "'+String.fromCharCode(244)+'");
		strParam = strParam.replaceAll("õ", "'+String.fromCharCode(245)+'");
		strParam = strParam.replaceAll("ö", "'+String.fromCharCode(246)+'");
		strParam = strParam.replaceAll("÷", "'+String.fromCharCode(247)+'");
		strParam = strParam.replaceAll("ø", "'+String.fromCharCode(248)+'");
		strParam = strParam.replaceAll("ù", "'+String.fromCharCode(249)+'");
		strParam = strParam.replaceAll("ú", "'+String.fromCharCode(250)+'");
		strParam = strParam.replaceAll("û", "'+String.fromCharCode(251)+'");
		strParam = strParam.replaceAll("ü", "'+String.fromCharCode(252)+'");
		strParam = strParam.replaceAll("ý", "'+String.fromCharCode(253)+'");
		strParam = strParam.replaceAll("þ", "'+String.fromCharCode(254)+'");
		strParam = strParam.replaceAll("ÿ", "'+String.fromCharCode(255)+'");

		return strParam;

	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="nivelOrganizacional.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("OrganizacaoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formOrganizacao == null) {
			formOrganizacao = new FormOrganizacao();
			formOrganizacao.setMsgError(ConstantesCRM.SVAZIO);
		}
		try {
			formOrganizacao.setListaOrganizacao(controlOrganograma.getListaTipoOrganizacaos(ConstantesCRM.getCurrentUser(request.getSession())).getTipoOrganizacaoVOArray());
			formOrganizacao.setArvoreOrganizacao(criaArvoreOrganizacao(request));

			String script = ConstantesCRM.SVAZIO;
			script = "if (document.getElementById) {var tree = new WebFXTree('" + trataStringRetorno(formOrganizacao.getArvoreOrganizacao().getOrganizacaoVO().getTipoOrganizacaoVO().getDsTipoOrganizacao()) + "',\"Javascript:capturaParametros('" + formOrganizacao.getArvoreOrganizacao().getOrganizacaoVO().getIdOrganizacao() + "','" + formOrganizacao.getArvoreOrganizacao().getOrganizacaoVO().getIdOrganizacaoPai() + "','"
					+ trataStringRetorno1(formOrganizacao.getArvoreOrganizacao().getOrganizacaoVO().getTipoOrganizacaoVO().getDsTipoOrganizacao()) + "','" + trataStringRetorno1(formOrganizacao.getArvoreOrganizacao().getOrganizacaoVO().getTipoOrganizacaoVO().getDsTipoOrganizacao()) + "','2" + "');\",'classic');";

			script = script + montaArvore(formOrganizacao.getArvoreOrganizacao().getOrganizacaoVO(), "tree") + "document.write(tree);}";

			// request.setAttribute("arvore",script);
			request.getSession().setAttribute("arvore", script);
		} catch (TuxedoWarningException twe) {

			log.warn("OrganizacaoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOrganizacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganizacaoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private AdmOrgOrganizacaoContainerVO criaArvoreOrganizacao(HttpServletRequest request) throws TuxedoWarningException, Exception {

		OrganizacaoVO organizacaoVO = OrganizacaoVO.Factory.newInstance();
		organizacaoVO.setIdOrganizacao(ConstantesCRM.SVAZIO);

		return controlOrganograma.cargaArvoreOrganizacao(organizacaoVO, ConstantesCRM.getCurrentUser(request.getSession()));

	}

	private String montaArvore(OrganizacaoVO itens, String tree) {

		String node = ConstantesCRM.SVAZIO;
		String folder = ConstantesCRM.SVAZIO;
		String folder2 = ConstantesCRM.SVAZIO;

		if (itens.getUnidadeOrganogramaVOArray().length > 0) {
			for (int x = 0; x < itens.getUnidadeOrganogramaVOArray().length; x++) {

				if (itens.getUnidadeOrganogramaVOArray().length == 0) {
					folder2 = "',\"JavaScript:document.getElementById('btrelcargo').style.display='none';document.getElementById('btexcluir').style.display='none';document.getElementById('btalterar').style.display='none';document.getElementById('btinclui').style.display='none';capturaParametros('";
					// folder2 = "',\"capturaParametros('";
				} else {
					folder2 = "',\"JavaScript:document.getElementById('btrelcargo').style.display='none';document.getElementById('btexcluir').style.display='none';document.getElementById('btalterar').style.display='none';document.getElementById('btinclui').style.display='none';capturaParametros('";
					// folder2 = "',\"capturaParametros('";
				}

				node = node + "var " + tree + String.valueOf(x) + " = new WebFXTreeItem('" + trataStringRetorno(itens.getUnidadeOrganogramaVOArray(x).getNmUnidade())

				+ folder2 + itens.getUnidadeOrganogramaVOArray(x).getIdUnidade() + "','" + "','" + trataStringRetorno1(itens.getUnidadeOrganogramaVOArray(x).getNmUnidade()) + "','0" + "');\""

				+ ",'','/FrontOfficeWeb/resources/images/departamento.gif','/FrontOfficeWeb/resources/images/departamento.gif');";

				// node = node + montaArvore( itens.getCargoVOArray(x),tree + String.valueOf(x) );
				node = node + tree + ".add(" + tree + String.valueOf(x) + ");\n";
			}
		}

		if (itens.getOrganizacaoVOArray().length > 0) {

			for (int x = 0; x < itens.getOrganizacaoVOArray().length; x++) {

				if (itens.getOrganizacaoVOArray(x).getOrganizacaoVOArray().length == 0) {
					folder = "',\"JavaScript:document.getElementById('btrelcargo').style.display='inline';document.getElementById('btexcluir').style.display='inline';document.getElementById('btalterar').style.display='inline';document.getElementById('btinclui').style.display='inline';capturaParametros('";
					// folder = "',\"capturaParametros('";
				} else {
					folder = "',\"JavaScript:document.getElementById('btrelcargo').style.display='inline';document.getElementById('btexcluir').style.display='inline';document.getElementById('btalterar').style.display='inline';document.getElementById('btinclui').style.display='inline';capturaParametros('";
					// folder = "',\"capturaParametros('";
				}

				if (itens.getOrganizacaoVOArray(x).getOrganizacaoVOArray().length > 0) {
					node = node + "var " + tree + String.valueOf(x) + " = new WebFXTreeItem('" + trataStringRetorno(itens.getOrganizacaoVOArray(x).getTipoOrganizacaoVO().getDsTipoOrganizacao()) + folder + itens.getOrganizacaoVOArray(x).getIdOrganizacao() + "','" + itens.getOrganizacaoVOArray(x).getIdOrganizacaoPai() + "','" + trataStringRetorno1(itens.getOrganizacaoVOArray(x).getTipoOrganizacaoVO().getDsTipoOrganizacao()) + "','"
							+ trataStringRetorno1(itens.getOrganizacaoVOArray(x).getTipoOrganizacaoVO().getDsTipoOrganizacao()) + "');\",'','/FrontOfficeWeb/resources/images/departamento_niveis.gif','/FrontOfficeWeb/resources/images/departamento_niveis.gif');";

					node = node + montaArvore(itens.getOrganizacaoVOArray(x), tree + String.valueOf(x));
					node = node + tree + ".add(" + tree + String.valueOf(x) + ");\n";
				} else {
					node = node + "var " + tree + String.valueOf(x) + " = new WebFXTreeItem('" + trataStringRetorno(itens.getOrganizacaoVOArray(x).getTipoOrganizacaoVO().getDsTipoOrganizacao()) + folder + itens.getOrganizacaoVOArray(x).getIdOrganizacao() + "','" + itens.getOrganizacaoVOArray(x).getIdOrganizacaoPai() + "','" + trataStringRetorno1(itens.getOrganizacaoVOArray(x).getTipoOrganizacaoVO().getDsTipoOrganizacao()) + "','"
							+ trataStringRetorno1(itens.getOrganizacaoVOArray(x).getTipoOrganizacaoVO().getDsTipoOrganizacao()) + "');\",'','/FrontOfficeWeb/resources/images/departamento_niveis.gif','/FrontOfficeWeb/resources/images/departamento_niveis.gif');";
					node = node + montaArvore(itens.getOrganizacaoVOArray(x), tree + String.valueOf(x));
					node = node + tree + ".add(" + tree + String.valueOf(x) + ");\n";
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
		FormOrganizacao form = (FormOrganizacao) formParam;

		log.debug("OrganizacaoController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formOrganizacao == null) {
			formOrganizacao = new FormOrganizacao();
		}
		formOrganizacao.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmOrgOrganizacaoContainerVO organizacaoContainer = AdmOrgOrganizacaoContainerVO.Factory.newInstance();
			organizacaoContainer.setIdOrganizacao(form.getIdOrganizacao());
			organizacaoContainer.setIdOrganizacaoPai(form.getIdOrganizacaoPai());
			formOrganizacao.setFlag("no");
			controlOrganograma.excluiOrganizacao(organizacaoContainer, ConstantesCRM.getCurrentUser(request.getSession()));
			formOrganizacao.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganizacaoController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOrganizacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganizacaoController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="manterDescricao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward manter(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormOrganizacao form = (FormOrganizacao) formParam;

		log.debug("OrganizacaoController:manter" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formOrganizacao == null) {
			formOrganizacao = new FormOrganizacao();
		}
		formOrganizacao.setMsgError(ConstantesCRM.SVAZIO);
		formOrganizacao.setIdOrganizacao(form.getIdOrganizacao());
		formOrganizacao.setIdOrganizacaoPai(form.getIdOrganizacaoPai());

		AdmOrgOrganizacaoContainerVO organizacaoContainer = AdmOrgOrganizacaoContainerVO.Factory.newInstance();
		organizacaoContainer.setIdOrganizacao(form.getIdOrganizacao());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gravaItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormOrganizacao form = (FormOrganizacao) formParam;

		log.debug("OrganizacaoController:gravaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formOrganizacao == null) {
			formOrganizacao = new FormOrganizacao();
		}

		formOrganizacao.setMsgError(ConstantesCRM.SVAZIO);

		try {
			AdmOrgOrganizacaoContainerVO organizacaoContainer = AdmOrgOrganizacaoContainerVO.Factory.newInstance();

			organizacaoContainer.setIdOrganizacao(ConstantesCRM.SVAZIO);
			organizacaoContainer.setIdOrganizacaoPai(form.getIdOrganizacao());

			if (form.getDsOrganizacao().trim().equalsIgnoreCase(ConstantesCRM.SVAZIO)) { // existente
				organizacaoContainer.setDsOrganizacao(ConstantesCRM.SVAZIO);
				organizacaoContainer.setIdTipoOrganizacao(form.getTipoOrganizacao());
			} else {
				organizacaoContainer.setDsOrganizacao(form.getDsOrganizacao());
				organizacaoContainer.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
			}
			controlOrganograma.gravaOrganizacao(organizacaoContainer, ConstantesCRM.getCurrentUser(request.getSession()));
			formOrganizacao.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {

			log.warn("OrganizacaoController:gravaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOrganizacao.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("OrganizacaoController:gravaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
		FormOrganizacao form = (FormOrganizacao) formParam;

		log.debug("OrganizacaoController:alteraItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formOrganizacao == null) {
			formOrganizacao = new FormOrganizacao();
		}
		formOrganizacao.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmOrgOrganizacaoContainerVO organizacaoContainer = AdmOrgOrganizacaoContainerVO.Factory.newInstance();
			String id = form.getIdOrganizacao();
			String dsc = form.getDsOrganizacao();
			if (dsc == null)
				organizacaoContainer.setDsOrganizacao(ConstantesCRM.SVAZIO);
			else
				organizacaoContainer.setDsOrganizacao(form.getDsOrganizacao());

			organizacaoContainer.setIdOrganizacao(form.getIdOrganizacao());
			controlOrganograma.alteraOrganizacao(organizacaoContainer, ConstantesCRM.getCurrentUser(request.getSession()));
			formOrganizacao.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {

			log.warn("OrganizacaoController:alteraItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOrganizacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganizacaoController:alteraItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="relacionarUnidades.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaRelUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormOrganizacao form = (FormOrganizacao) formParam;

		log.debug("OrganizacaoController:editaRelUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formOrganizacao == null) {
			formOrganizacao = new FormOrganizacao();
		}

		formOrganizacao.setMsgError(ConstantesCRM.SVAZIO);

		try {
			String organizacaoId = request.getParameter("idOrganizacao");
			if (organizacaoId == null)
				organizacaoId = form.getIdOrganizacao();

			String organizacaoDs = request.getParameter("dsOrganizacao");

			if (organizacaoDs == null)
				organizacaoDs = form.getDsOrganizacao();

			OrganizacaoVO organizacao = OrganizacaoVO.Factory.newInstance();// formOrganizacao.
																			// .getCargoVOById(cargoID,arrayCargos);
			organizacao.setIdOrganizacao(organizacaoId);

			OrganizacaoUnidadeRelacionamentoVO organizacaoUnidadeRel = OrganizacaoUnidadeRelacionamentoVO.Factory.newInstance();
			organizacaoUnidadeRel = controlOrganograma.listaRelacionarOrganizacaoUnidade(organizacao, ConstantesCRM.getCurrentUser(request.getSession()));
			formOrganizacao.setArrayUnidadesExistentesVO(organizacaoUnidadeRel.getExistentes().getUnidadeOrganogramaVOArray());

			formOrganizacao.setArrayUnidadesRelacionadosVO(organizacaoUnidadeRel.getRelacionados().getUnidadeOrganogramaVOArray());
			formOrganizacao.setIdOrganizacao(organizacaoId);
			formOrganizacao.setDsOrganizacao(organizacaoDs);

			formOrganizacao.setArrayUnidadesExistentesVO(organizacaoUnidadeRel.getExistentes().getUnidadeOrganogramaVOArray());
			formOrganizacao.setArrayUnidadesRelacionadosVO(organizacaoUnidadeRel.getRelacionados().getUnidadeOrganogramaVOArray());
			formOrganizacao.setFlag(ConstantesCRM.SYES);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganizacaoController:editaRelUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOrganizacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganizacaoController:editaRelUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
		FormOrganizacao form = (FormOrganizacao) formParam;

		log.debug("OrganizacaoController:salvaRelacion" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formOrganizacao == null) {
			formOrganizacao = new FormOrganizacao();
		}
		formOrganizacao.setMsgError(ConstantesCRM.SVAZIO);
		try {
			String lista[] = form.getArrayUnidadesRelacionados();
			OrganizacaoUnidadeRelacionamentoVO organizacaoUnidadeRelVO = OrganizacaoUnidadeRelacionamentoVO.Factory.newInstance();
			organizacaoUnidadeRelVO.addNewRelacionados();

			for (int i = 0; i < form.getArrayUnidadesRelacionados().length; i++) {

				if (lista[i] != null)
					if (!lista[i].trim().equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
						UnidadeOrganogramaVO unidadeOrganogramaVO = UnidadeOrganogramaVO.Factory.newInstance();
						unidadeOrganogramaVO.setIdUnidade(lista[i]);
						unidadeOrganogramaVO.setNmUnidade(ConstantesCRM.SVAZIO);
						organizacaoUnidadeRelVO.getRelacionados().insertNewUnidadeOrganogramaVO(i);
						organizacaoUnidadeRelVO.getRelacionados().setUnidadeOrganogramaVOArray(i, unidadeOrganogramaVO);
					}
			}

			organizacaoUnidadeRelVO.setIdOrganizacao(formOrganizacao.getIdOrganizacao());
			controlOrganograma.salvaOrganizacaoUnidadeRelacionados(organizacaoUnidadeRelVO, ConstantesCRM.getCurrentUser(request.getSession()));
			formOrganizacao.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganizacaoController:salvaRelacion" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOrganizacao.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("OrganizacaoController:salvaRelacion" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="alterarOrganizacao.jsp"
	 */
	public ActionForward alterarOrganizacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormOrganizacao form = (FormOrganizacao) formParam;

		log.debug("OrganizacaoController:alterarOrganizacao" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formOrganizacao.setIdOrganizacao(form.getIdOrganizacao());
		formOrganizacao.setIdOrganizacaoPai(form.getIdOrganizacaoPai());
		formOrganizacao.setDsOrganizacao(form.getDsOrganizacao());
		formOrganizacao.setMsgError(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterDescricao.jsp"
	 */
	public ActionForward manterDescricao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormOrganizacao form = (FormOrganizacao) formParam;

		log.debug("OrganizacaoController:manterDescricao" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formOrganizacao.setMsgError(ConstantesCRM.SVAZIO);
		formOrganizacao.setIdOrganizacao(form.getIdOrganizacao());
		formOrganizacao.setIdOrganizacaoPai(form.getIdOrganizacaoPai());
		formOrganizacao.setDsOrganizacao(form.getDsOrganizacao());
		formOrganizacao.setMsgError(ConstantesCRM.SSucesso);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormOrganizacao extends ActionForm {
		private static final long serialVersionUID = 4643540205477156635L;

		private String msgError = ConstantesCRM.SVAZIO;

		private String idTipoOrganizacao;

		private String tipoOrganizacao;

		private TipoOrganizacaoVO[] listaOrganizacao;

		private ListaTipoOrganizacaoVO[] arrayListaOrganizacao;

		private AdmOrgOrganizacaoContainerVO arvoreOrganizacao;

		private String idOrganizacaoPai;

		private String idOrganizacao;

		private String dsOrganizacao;

		private UnidadeOrganogramaVO[] arrayUnidadesRelacionadosVO;

		private String[] arrayUnidadesRelacionados;

		private UnidadeOrganogramaVO[] arrayUnidadesExistentesVO;

		private String[] arrayUnidadesExistentes;

		private String flag;

		public FormOrganizacao() {
			flag = ConstantesCRM.SVAZIO;
			listaOrganizacao = new TipoOrganizacaoVO[0];
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public String getFlag() {
			return this.flag;
		}

		public void setArrayUnidadesExistentes(String[] arrayUnidadesExistentes) {
			this.arrayUnidadesExistentes = arrayUnidadesExistentes;
		}

		public String[] getArrayUnidadesExistentes() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayUnidadesExistentes == null || this.arrayUnidadesExistentes.length == 0) {
				this.arrayUnidadesExistentes = new String[1];
			}

			return this.arrayUnidadesExistentes;
		}

		public void setArrayUnidadesExistentesVO(UnidadeOrganogramaVO[] arrayUnidadesExistentesVO) {
			this.arrayUnidadesExistentesVO = arrayUnidadesExistentesVO;
		}

		public UnidadeOrganogramaVO[] getArrayUnidadesExistentesVO() {
			return this.arrayUnidadesExistentesVO;
		}

		public void setArrayUnidadesRelacionados(String[] arrayUnidadesRelacionados) {
			this.arrayUnidadesRelacionados = arrayUnidadesRelacionados;
		}

		public String[] getArrayUnidadesRelacionados() {
			if (this.arrayUnidadesRelacionados == null || this.arrayUnidadesRelacionados.length == 0) {
				this.arrayUnidadesRelacionados = new String[1];
			}
			return this.arrayUnidadesRelacionados;
		}

		public void setArrayUnidadesRelacionadosVO(UnidadeOrganogramaVO[] arrayUnidadesRelacionadosVO) {
			this.arrayUnidadesRelacionadosVO = arrayUnidadesRelacionadosVO;
		}

		public UnidadeOrganogramaVO[] getArrayUnidadesRelacionadosVO() {
			return this.arrayUnidadesRelacionadosVO;
		}

		public void setDsOrganizacao(String dsOrganizacao) {
			this.dsOrganizacao = dsOrganizacao;
		}

		public String getDsOrganizacao() {
			return this.dsOrganizacao;
		}

		public void setIdOrganizacao(String idOrganizacao) {
			this.idOrganizacao = idOrganizacao;
		}

		public String getIdOrganizacao() {
			return this.idOrganizacao;
		}

		public void setIdOrganizacaoPai(String idOrganizacaoPai) {
			this.idOrganizacaoPai = idOrganizacaoPai;
		}

		public String getIdOrganizacaoPai() {
			return this.idOrganizacaoPai;
		}

		public void setArvoreOrganizacao(AdmOrgOrganizacaoContainerVO arvoreOrganizacao) {
			this.arvoreOrganizacao = arvoreOrganizacao;
		}

		public AdmOrgOrganizacaoContainerVO getArvoreOrganizacao() {
			return this.arvoreOrganizacao;
		}

		public void setArrayListaOrganizacao(ListaTipoOrganizacaoVO[] arrayListaOrganizacao) {
			this.arrayListaOrganizacao = arrayListaOrganizacao;
		}

		public ListaTipoOrganizacaoVO[] getArrayListaOrganizacao() {
			return this.arrayListaOrganizacao;
		}

		public void setListaOrganizacao(TipoOrganizacaoVO[] listaOrganizacao) {
			this.listaOrganizacao = listaOrganizacao;
		}

		public TipoOrganizacaoVO[] getListaOrganizacao() {
			return this.listaOrganizacao;
		}

		public void setTipoOrganizacao(String tipoOrganizacao) {
			this.tipoOrganizacao = tipoOrganizacao;
		}

		public String getTipoOrganizacao() {
			if (this.tipoOrganizacao == null) {
				this.tipoOrganizacao = new String();
			}
			return this.tipoOrganizacao;
		}

		public void setIdTipoOrganizacao(String idTipoOrganizacao) {
			this.idTipoOrganizacao = idTipoOrganizacao;
		}

		public String getIdTipoOrganizacao() {
			return this.idTipoOrganizacao;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public FormOrganizacao getformOrganizacao() {
		return (this.formOrganizacao);
	}
}
