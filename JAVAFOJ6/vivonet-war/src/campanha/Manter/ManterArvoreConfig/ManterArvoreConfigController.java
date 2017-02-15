package campanha.Manter.ManterArvoreConfig;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.ItemArvoreVODocument.ItemArvoreVO;
import br.com.vivo.fo.commons.utils.StringUtil;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({ "unused", "rawtypes", "deprecation", "unchecked" })
public class ManterArvoreConfigController extends AbstractAction {

	private static final long serialVersionUID = 2069890781503828126L;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.ApoioFacade apoioFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.arvore.ArvoreCampanhaFacade arvoreFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.questionario.QuestionarioCampanhaFacade questionarioFacade;

	private ScriptCampanhaActionForm scriptCampanhaActionForm;
	protected global.Global globalApp = new global.Global();
	private String idPergunta = ConstantesCRM.SZERO;
	private String idStatus = ConstantesCRM.SZERO;
	private String idPai = ConstantesCRM.SZERO;
	private String idMotivo = ConstantesCRM.SZERO;
	private String idCanalSelecionado = ConstantesCRM.SZERO;
	private String idCampanhaSelecionada = ConstantesCRM.SVAZIO;
	private String idSubCampanhaSelecionada = ConstantesCRM.SVAZIO;
	private String dsCanalSelecionado = ConstantesCRM.SVAZIO;
	private StringBuffer nodeSelecionado;
	private String idUsuario;
	private static final int MAX_ARVORE_SIZE = 80;
	private static final String MSG_ERROR_VIEW_SCRIPT = "msgErrorViewScript";
	private Map canais = new HashMap();

	private String getIdUsuario(HttpServletRequest request) {
		if (this.idUsuario == null) {
			this.idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		}
		return this.idUsuario;
	}

	private StringBuffer getNodeSelecionado() {
		if (this.nodeSelecionado == null) {
			this.nodeSelecionado = new StringBuffer();
		}
		return this.nodeSelecionado;
	}

	private void setNodeSelecionado(StringBuffer nodeSelecionado) {
		this.nodeSelecionado = nodeSelecionado;
	}

	public ScriptCampanhaActionForm getScriptCampanhaActionForm() {
		if (this.scriptCampanhaActionForm == null) {
			this.scriptCampanhaActionForm = new ScriptCampanhaActionForm();
		}
		return this.scriptCampanhaActionForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("ScriptCampanhaAction".equals(mapping.getParameter())) {
			return ScriptCampanhaAction(mapping, form, request, response);
		} else if ("ObtemListaAction".equals(mapping.getParameter())) {
			return ObtemListaAction(mapping, form, request, response);
		} else if ("obterVersao".equals(mapping.getParameter())) {
			return obterVersao(mapping, form, request, response);
		} else if ("montarArvore".equals(mapping.getParameter())) {
			return montarArvore(mapping, form, request, response);
		} else if ("CarregarAction".equals(mapping.getParameter())) {
			return CarregarAction(mapping, form, request, response);
		} else if ("incluirPergunta".equals(mapping.getParameter())) {
			return incluirPergunta(mapping, form, request, response);
		} else if ("incluirResposta".equals(mapping.getParameter())) {
			return incluirResposta(mapping, form, request, response);
		} else if ("incluirStatus".equals(mapping.getParameter())) {
			return incluirStatus(mapping, form, request, response);
		} else if ("incluirSubCampanha".equals(mapping.getParameter())) {
			return incluirSubCampanha(mapping, form, request, response);
		} else if ("editarSubCampanha".equals(mapping.getParameter())) {
			return editarSubCampanha(mapping, form, request, response);
		} else if ("editarPergunta".equals(mapping.getParameter())) {
			return editarPergunta(mapping, form, request, response);
		} else if ("editarResposta".equals(mapping.getParameter())) {
			return editarResposta(mapping, form, request, response);
		} else if ("editarStatus".equals(mapping.getParameter())) {
			return editarStatus(mapping, form, request, response);
		} else if ("deleteSubCampanha".equals(mapping.getParameter())) {
			return deleteSubCampanha(mapping, form, request, response);
		} else if ("deleteResposta".equals(mapping.getParameter())) {
			return deleteResposta(mapping, form, request, response);
		} else if ("deleteCanal".equals(mapping.getParameter())) {
			return deleteCanal(mapping, form, request, response);
		} else if ("deletePergunta".equals(mapping.getParameter())) {
			return deletePergunta(mapping, form, request, response);
		} else if ("deleteStatus".equals(mapping.getParameter())) {
			return deleteStatus(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="ScriptCampanhaAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("idCampanha");
		request.getSession().removeAttribute("idSubCampanhaFixa");
		request.getSession().removeAttribute("idSubCampanha");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ScriptCampanhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			ScriptCampanhaActionForm form = (ScriptCampanhaActionForm) formParam;
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			GrupoCampanhaApoioVO grupoCampanhaApoioVO = apoioFacade.getApoioCampanha(user);

			/*********************** Lista de Campanhas *******************************************************************/
			form.setListaCampanha(grupoCampanhaApoioVO.getSubGrupoApoioVOArray(0).getApoioVOArray());

			String _idCampanha = (String) request.getSession().getAttribute("idCampanha");

			/*
			 * Se ja existir uma campanha seleciona anteriormente
			 */
			if ((_idCampanha != null) && (!_idCampanha.equals(ConstantesCRM.SVAZIO))) {
				grupoCampanhaApoioVO = apoioFacade.getApoioSubCampanha(user, _idCampanha);
				form.setListaSubCampanha(grupoCampanhaApoioVO.getSubGrupoApoioVOArray(0).getApoioVOArray());
				form.setCampanhaSelecionada(_idCampanha);
				String _idSubCampanhaFixa = (String) request.getSession().getAttribute("idSubCampanhaFixa");

				/*
				 * Se ja existir uma subcampanhafixa seleciona anteriormente
				 */
				if ((_idSubCampanhaFixa != null) && (!_idSubCampanhaFixa.equals(ConstantesCRM.SVAZIO))) {
					grupoCampanhaApoioVO = apoioFacade.getApoioVersao(user, _idSubCampanhaFixa);
					form.setListaVersao((grupoCampanhaApoioVO.getSubGrupoApoioVOArray(0).getApoioVOArray()));
					form.setSubCampanhaSelecionada(_idSubCampanhaFixa);
					String _idSubCampanha = (String) request.getSession().getAttribute("idSubCampanha");
					/*
					 * Se ja existir uma subcampanhahistorico seleciona anteriormente
					 */
					if ((_idSubCampanha != null) && (!_idSubCampanha.equals(ConstantesCRM.SVAZIO))) {
						form.setVersaoSelecionada(_idSubCampanha);
					}
				}
			}
			this.scriptCampanhaActionForm = form;

		} catch (Exception e) {
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
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ObtemListaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * Nesta action carrega sub campanha, se possível versão!
		 */
		ScriptCampanhaActionForm form = (ScriptCampanhaActionForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			/*************** Salva as listas carregadas para o formulario atual *************************/
			form.setListaCampanha(getScriptCampanhaActionForm().getListaCampanha());
			// form.setListaSubCampanha(getScriptCampanhaActionForm().getListaSubCampanha());
			this.scriptCampanhaActionForm = form;
			String _idCampanha = form.getCampanhaSelecionada();

			if (ConstantesCRM.SVAZIO.equals(_idCampanha)) {
				_idCampanha = (String) request.getSession().getAttribute("idCampanha");
			} else {
				request.getSession().setAttribute("idCampanha", _idCampanha);
			}

			// GrupoCampanhaApoioVO grupoCampanha;

			if ((_idCampanha != null) && (!_idCampanha.equals(ConstantesCRM.SVAZIO))) {
				// Mantem campanha selecionada
				getScriptCampanhaActionForm().setCampanhaSelecionada(_idCampanha);
				// Carrega sub campanha
				GrupoCampanhaApoioVO grupoCampanha = apoioFacade.getApoioSubCampanha(user, _idCampanha);
				// seta lista de sub campanha
				getScriptCampanhaActionForm().setListaSubCampanha(grupoCampanha.getSubGrupoApoioVOArray(0).getApoioVOArray());
				getScriptCampanhaActionForm().setSubCampanhaSelecionada(ConstantesCRM.SVAZIO);
				request.getSession().removeAttribute("idSubCampanhaFixa");
			}

		} catch (Exception e) {
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
	 * @jpf:forward name="success" path="IFrameVersao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obterVersao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ScriptCampanhaActionForm form = (ScriptCampanhaActionForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			/*************** Salva as listas carregadas para o formulario atual *************************/
			form.setListaCampanha(getScriptCampanhaActionForm().getListaCampanha());
			form.setListaSubCampanha(getScriptCampanhaActionForm().getListaSubCampanha());
			this.scriptCampanhaActionForm = form;

			/*
			 * Como tenho que carregar uma lista de versão zero as variaveis da versão anterior selecionada
			 */
			getScriptCampanhaActionForm().setVersaoSelecionada(ConstantesCRM.SVAZIO);
			request.getSession().removeAttribute("idSubCampanha");
			String _idSubCampanhaFixa = form.getSubCampanhaSelecionada();

			if (ConstantesCRM.SVAZIO.equals(_idSubCampanhaFixa)) {
				_idSubCampanhaFixa = (String) request.getSession().getAttribute("idSubCampanhaFixa");
				getScriptCampanhaActionForm().setListaVersao(new ApoioVO[0]);
				getScriptCampanhaActionForm().setSubCampanhaSelecionada(ConstantesCRM.SVAZIO);
			} else {
				if (_idSubCampanhaFixa != null && !ConstantesCRM.SVAZIO.equals(_idSubCampanhaFixa)) {
					// Carrega lista de versões
					GrupoCampanhaApoioVO grupoSubCampanhaApoio = apoioFacade.getApoioVersao(user, _idSubCampanhaFixa);
					getScriptCampanhaActionForm().setListaVersao((grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray()));
				} else {
					/*
					 * Se não consegui recuperar um idSubCampanhaFixa desmarco a sub campanha selecionada, assim
					 * forçando o usuário ter que selecionar novamente, para conseguir popular o combo de versão!
					 */
					getScriptCampanhaActionForm().setSubCampanhaSelecionada(ConstantesCRM.SVAZIO);
				}
			}

		} catch (Exception e) {
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
	 * @jpf:forward name="success" path="ViewScriptCampanha.jsp"
	 * @jpf:forward name="success1" path="iframeArvore.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward montarArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		
		ArvoreForm form = (ArvoreForm) formParam;
		boolean isResposta = false;
		
		if (form.getIdCampanha() == null && request.getAttribute("idCampanha") != null && !"null".equalsIgnoreCase((String)request.getAttribute("idCampanha"))) {
			form.setIdCampanha((String)request.getAttribute("idCampanha"));
		}
		if (form.getIdSubCampanha() == null && request.getAttribute("idSubCampanha") != null && !"null".equalsIgnoreCase((String)request.getAttribute("idSubCampanha"))) {
			form.setIdSubCampanha((String)request.getAttribute("idSubCampanha"));
		}

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			if (form.getXmlParam() != null && form.getXmlParam().indexOf("<idCanal>") > 0) {
				request.getSession().setAttribute("idCanalCampanha", form.getXmlParam().substring(form.getXmlParam().indexOf("<idCanal>") + 9, form.getXmlParam().indexOf("</idCanal>")));
				if (form.getInResposta().equals("true")) {
					isResposta = true;
				}
				request.getSession().setAttribute("dsCanalCampanha", form.getDsCanalCampanha());
			}

			if ((form.getIdCampanha() != null
					&& !form.getIdCampanha().equalsIgnoreCase("null"))
					&& (form.getIdSubCampanha() != null
					&& !form.getIdSubCampanha().equalsIgnoreCase("null"))) {
				ItemArvoreVO itemArvore = arvoreFacade.getArvoreCampanha(getIdUsuario(request), form.getIdCampanha(), form.getIdSubCampanha(), form.getIdCanalCampanha(), form.xmlParam);
				if ((itemArvore.getCodigo() != null && itemArvore.getItemArvoreVOArray(0).getTipo().equalsIgnoreCase("2")) || (itemArvore.getCodigo() != null && itemArvore.getItemArvoreVOArray(0).getTipo().equalsIgnoreCase("4"))) {
					for (int i = 0; i < itemArvore.getItemArvoreVOArray().length; i++) {
						this.setMapCanais(itemArvore.getItemArvoreVOArray(i).getCodigo(), itemArvore.getItemArvoreVOArray(i).getDescricao());
					}
					request.getSession().setAttribute("canais", canais);
				}
				setArvoreForm(form);
				if (!isResposta) {
					String arvore = getArvore(itemArvore, form.getXmlParam());
					request.setAttribute(ConstantesCRM.CT_ARVORE_CAMPANHA, arvore);
					request.getSession().setAttribute(ConstantesCRM.CT_ARVORE_CAMPANHA, arvore);
				}
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		request.getSession().setAttribute("ArvoreForm", getArvoreForm());		
		return mapping.findForward(form.getXmlParam() == null || ConstantesCRM.SVAZIO.equals(form.getXmlParam()) ? ConstantesCRM.SUCCESS : "success1");
	}

	/**
	 * Retorna JavaScript que monta a árvore do lado do cliente
	 *
	 * @return String
	 */
	private String getArvore(ItemArvoreVO itemArvore, String param) {
		StringUtil strUtil = new StringUtil();
		StringBuffer scriptJS = new StringBuffer();
		StringBuffer inicio = new StringBuffer();

		String parent = ConstantesCRM.SVAZIO;
		String getSelected = ConstantesCRM.SVAZIO;
		String finalizaJS = ConstantesCRM.SVAZIO;
		String function = ConstantesCRM.SVAZIO;

		int descLength = 0;

		String xmlParam = param != null && !ConstantesCRM.SVAZIO.equals(param) ? param : ConstantesCRM.SVAZIO;

		if (itemArvore.getItemArvoreVOArray() != null && itemArvore.getItemArvoreVOArray().length > 0) {
			function = !xmlParam.equals(ConstantesCRM.SVAZIO) ? "expandirNo" : "abreGeral"; // recupera a função JS que
			// será executada quando o
			// usuário clicar no nó
			parent = !xmlParam.equals(ConstantesCRM.SVAZIO) ? "parent." : ConstantesCRM.SVAZIO; // retorna "parent." se
			// a chamada for pelo
			// "iFrame"
			getSelected = !xmlParam.equals(ConstantesCRM.SVAZIO) ? ".getSelected()" : ConstantesCRM.SVAZIO; // retorna
			// ".getSelected"
			// se a
			// chamada
			// for pelo
			// "iFrame"
			finalizaJS = !xmlParam.equals(ConstantesCRM.SVAZIO) ? "parent.tree.getSelected().setAddEnabled(true);\n\nparent.tree.getSelected().expand();\n\n" // chamada
					// do
					// "iFrame"
					: "\ndocument.write(tree);}\n\n";// chamada da página principal, primeiro nível da árvore

			if (itemArvore.getItemArvoreVOArray(0).getTipo().equalsIgnoreCase(ConstantesCRM.STHREE) || itemArvore.getItemArvoreVOArray(0).getTipo().equalsIgnoreCase(ConstantesCRM.SFOUR) || itemArvore.getItemArvoreVOArray(0).getTipo().equalsIgnoreCase(ConstantesCRM.SFIVE)) {
				finalizaJS = !xmlParam.equals(ConstantesCRM.SVAZIO) ? "parent.tree.getSelected().setAddEnabled(false);\n\nparent.tree.getSelected().expand();\n\n" // chamada
						// do
						// "iFrame"
						: "\ndocument.write(tree);}\n\n";// chamada da página principal, primeiro nível da árvore
			}

			inicio.append("if (document.getElementById) {var tree = new WebFXTree('").append(StringEscapeUtils.escapeJavaScript(trataDescricao(itemArvore.getDescricao(), MAX_ARVORE_SIZE))).append("', 'javascript: SetCodigoCamp()', '','/FrontOfficeWeb/resources/images/icon_subcamp_close.png', '/FrontOfficeWeb/resources/images/icon_subcamp_open.png');\ntree.setBehavior('classic');\n");

			scriptJS.append(xmlParam.equals(ConstantesCRM.SVAZIO) ? inicio.toString() // se for o primeiro nível da
					// árvore
					: "parent.tree.getSelected().removeChildNodes();\n\n"); // removo os filhos

			// percorre os itens da árvore
			for (int i = 0; i < itemArvore.getItemArvoreVOArray().length; i++) {

				switch (retornarInteiro(itemArvore.getItemArvoreVOArray()[i].getTipo())) {
				case 3:
					idPergunta = String.valueOf(itemArvore.getItemArvoreVOArray()[i].getCodigo());
					idPai = idPergunta;
					break;
				case 5:
					idStatus = String.valueOf(itemArvore.getItemArvoreVOArray()[i].getCodigo());
					idPai = idStatus;
					break;
				case 6:
					idMotivo = String.valueOf(itemArvore.getItemArvoreVOArray()[i].getCodigo());
					break;
				}

				descLength = itemArvore.getItemArvoreVOArray()[i].getDescricao().length();

				scriptJS.append(parent).append("tmpArvore = new ").append(parent).append("WebFXTreeItem(") // cria
				// objeto JS
				// para o
				// item da
				// árvore
				.append("'").append(strUtil.escapaComillasJS(trataDescricao(itemArvore.getItemArvoreVOArray()[i].getDescricao(), MAX_ARVORE_SIZE)))// informa
				// o
				// nome
				// do
				// item
				.append("',").append(getMetodoNode(retornarInteiro(itemArvore.getItemArvoreVOArray()[i].getTipo()), // função
						// javascript
						// a
						// ser
						// executada
						// ao
						// clickar
						// no
						// node
						retornarInteiro(itemArvore.getItemArvoreVOArray()[i].getCodigo()), idMotivo, idPai, itemArvore.getItemArvoreVOArray()[i].getSgTipoApresentacaoPergunta(), xmlParam)).append(",'','").append(getImgNodeClose(retornarInteiro(itemArvore.getItemArvoreVOArray()[i].getTipo()), // icone
								// fechado
								retornarInteiro(itemArvore.getItemArvoreVOArray()[i].getInFinal()), retornarInteiro(itemArvore.getItemArvoreVOArray()[i].getIdTipoApresentacao()))).append("','").append(getImgNodeOpen(retornarInteiro(itemArvore.getItemArvoreVOArray()[i].getTipo()), // icone
										// aberto
										retornarInteiro(itemArvore.getItemArvoreVOArray()[i].getInFinal()), retornarInteiro(itemArvore.getItemArvoreVOArray()[i].getIdTipoApresentacao()))).append("');\n").append(parent).append("tmpArvore.setToolTip('" + strUtil.escapaComillasJS((descLength - 20) > MAX_ARVORE_SIZE ? trataDescricao(itemArvore.getItemArvoreVOArray()[i].getDescricao(), MAX_ARVORE_SIZE).replaceAll("\"", "\\n") : "") + "');\n").append(parent) // adiciona
										// item
										// à
										// árvore
										.append("tree").append(getSelected).append(".add(").append(parent).append("tmpArvore);\n\n");
			}
			scriptJS.append(finalizaJS); // adiciona itens à árvore existente, ou exibe árvore na tela
		}
		return scriptJS.toString(); // retorna o script para montar a árvore
	}

	private int retornarInteiro(String valor) {
		return Integer.parseInt(valor == null || valor.equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SZERO : valor);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ViewScriptCampanha.jsp"
	 * @jpf:forward name="success1" path="montarArvore.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward CarregarAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ScriptCampanhaActionForm form = (ScriptCampanhaActionForm) formParam;
			arvoreForm = new ArvoreForm();
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			getNodeSelecionado().delete(0, getNodeSelecionado().length()); // Limpa no selecionado

			if ((request.getParameter("idCanalCampanha") != null) && (!request.getParameter("idCanalCampanha").equals(ConstantesCRM.SVAZIO))) {
				idCanalSelecionado = request.getParameter("idCanalCampanha");
				dsCanalSelecionado = request.getParameter("dsCanalCampanha");

				request.setAttribute("idCanalCampanha", idCanalSelecionado);
				request.setAttribute("dsCanalCampanha", dsCanalSelecionado);

				getScriptCampanhaActionForm().setIdCanalCampanha(idCanalSelecionado);
				getScriptCampanhaActionForm().setDsCanalCampanha(dsCanalSelecionado);

				request.getSession().setAttribute("idCanalCampanha", idCanalSelecionado);

				if ((dsCanalSelecionado != null) && (!dsCanalSelecionado.equals(ConstantesCRM.SVAZIO))) {
					request.getSession().setAttribute("dsCanalCampanha", dsCanalSelecionado);
				}

				form.setIdCanalCampanha(idCanalSelecionado);

			} else {
				form.setIdCampanha(form.getCampanhaSelecionada());
				form.setIdSubCampanha(form.getVersaoSelecionada());

				request.getSession().setAttribute("idCampanha", form.getCampanhaSelecionada());
				request.getSession().setAttribute("idSubCampanha", form.getVersaoSelecionada());
				request.getSession().setAttribute("idSubCampanhaFixa", form.getSubCampanhaSelecionada());
				request.setAttribute("idCampanha", form.getCampanhaSelecionada());
				request.setAttribute("idSubCampanha", form.getVersaoSelecionada());
				request.setAttribute("idSubCampanhaFixa", form.getSubCampanhaSelecionada());
			}

			form.setListaCampanha(getScriptCampanhaActionForm().getListaCampanha());
			form.setListaSubCampanha(getScriptCampanhaActionForm().getListaSubCampanha());
			this.scriptCampanhaActionForm = form;

			getArvoreForm().setIdCampanha(form.getIdCampanha());
			getArvoreForm().setIdSubCampanha(form.getIdSubCampanha());
			getArvoreForm().setIdCanalCampanha(form.getIdCanalCampanha());

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("success1");
	}

	protected String getMapCanais(String id, HttpServletRequest request) {

		Map canais = (HashMap) request.getSession().getAttribute("canais");

		return (String) canais.get(id);
	}

	protected void setMapCanais(String chave, String valor) {

		canais.put(chave, valor);
	}

	private String VerificaItemMenu(ItemArvoreVO itens, String tree) {
		StringBuffer node = new StringBuffer();

		if (itens.getItemArvoreVOArray().length == 0) {
			return ConstantesCRM.SVAZIO;
		} else {

			for (int x = 0; x < itens.getItemArvoreVOArray().length; x++) {
				if (itens.getItemArvoreVOArray(x) != null) {
					ItemArvoreVO item = itens.getItemArvoreVOArray(x);

					/************* Guarda Código da Pergunta caso seja uma resposta **************************/
					switch (Integer.parseInt(item.getTipo())) {
					case 3:
						idPergunta = String.valueOf(item.getCodigo());
						idPai = idPergunta;
						break;
					case 5:
						idStatus = String.valueOf(item.getCodigo());
						idPai = idStatus;
						break;
					case 6:
						idMotivo = String.valueOf(item.getCodigo());
						break;
					}

					/*
					 * Em teoria deveriamos usar somente por sigla, porém para assegurar, caso a sigla não seja enviada
					 * estou setando com idTipoApresentação, pois antes não dava erro De momento acho que os únicos que
					 * usam ou deveria talvez usar este é pergunta e resposta!
					 */
					if (item.getSgTipoApresentacaoPergunta() == null || item.getSgTipoApresentacaoPergunta().equals(ConstantesCRM.SVAZIO)) {
						item.setSgTipoApresentacaoPergunta(item.getIdTipoApresentacao());
					}
					node.append("var " + tree + String.valueOf(x));
					node.append(" = new WebFXTreeItem('");
					node.append(StringEscapeUtils.escapeJavaScript(trataDescricao(item.getDescricao(), MAX_ARVORE_SIZE)));
					node.append("',");
					node.append(getMetodoNode(retornarInteiro(item.getTipo()), retornarInteiro(item.getCodigo()), idMotivo, idPai, item.getSgTipoApresentacaoPergunta(), ConstantesCRM.SVAZIO));
					node.append(", '','");

					if (item.getInFinal() == null) {
						item.setInFinal(ConstantesCRM.SZERO);
					}
					node.append(getImgNodeClose(retornarInteiro(item.getTipo()), retornarInteiro(item.getInFinal()), retornarInteiro(item.getIdTipoApresentacao())));
					node.append("','");
					node.append(getImgNodeOpen(retornarInteiro(item.getTipo()), retornarInteiro(item.getInFinal()), retornarInteiro(item.getIdTipoApresentacao())));
					node.append("');");

					/************ Guarda Código do Canal Campanha ***************************************************/
					if ("2".equals(itens.getItemArvoreVOArray(x).getTipo())) {
						if (getScriptCampanhaActionForm().getIdCanalCampanha() != null) {
							if (itens.getItemArvoreVOArray(x).getCodigo().equalsIgnoreCase(getScriptCampanhaActionForm().getIdCanalCampanha())) {
								getNodeSelecionado().append(tree + String.valueOf(x) + ".select();");
								getNodeSelecionado().append(tree + String.valueOf(x) + ".expand();");
							}
						}
					}
				}

				node.append(VerificaItemMenu(itens.getItemArvoreVOArray(x), tree + String.valueOf(x)));
				node.append(tree + ".add(" + tree + String.valueOf(x) + ");\n");
			}

		}
		return node.toString();
	}

	private String trataDescricao(String value, int iTam) {
		String newValue = value;

		if (value != null) {
			newValue = newValue.replaceAll("\n", " ");
			newValue = newValue.replaceAll("'", "\"");
		}
		return newValue;
	}

	private String getMetodoNode(int iTipo, int id, String idAux, String idAux2, String sgTipoApresentacao, String xmlParam) {
		StringBuffer sMetodo = new StringBuffer();
		switch (iTipo) {
		/******************************************* CAMPANHA **************************************************************************************************************************************************/
		case 0:
			sMetodo.append("'javascript: ViewAction();'");
			break;
			/******************************************* CAMPANHA **************************************************************************************************************************************************/
		case 1:
			sMetodo.append("'javascript: ViewAction();'");
			break;
			/******************************************* CANAL CAMPANHA **************************************************************************************************************************************************/
		case 2:
			sMetodo.append("'javascript: SetCodigoCanal(");
			sMetodo.append(id);
			sMetodo.append(");'");
			break;
			/******************************************* PERGUNTA **************************************************************************************************************************************************/
		case 3:
			sMetodo.append("\"javascript: SetCodigoPerg(");
			sMetodo.append(id);
			sMetodo.append(", '");
			sMetodo.append(sgTipoApresentacao);
			sMetodo.append("', '");
			sMetodo.append(xmlParam);
			sMetodo.append("');\"");
			break;
			/******************************************* RESPOSTA **************************************************************************************************************************************************/
		case 4:
			sMetodo.append("\"javascript: SetCodigoResp(");
			sMetodo.append(id);
			sMetodo.append(",");
			sMetodo.append(getArvoreForm().getIdPergunta());
			sMetodo.append(", '");
			sMetodo.append(xmlParam);
			sMetodo.append("');\"");
			// sMetodo.append(sgTipoApresentacao);
			// sMetodo.append("');\"");
			break;
			/******************************************* STATUS **************************************************************************************************************************************************/
		case 5:
			sMetodo.append("\"javascript: SetCodigoStatus(");
			sMetodo.append(id);
			sMetodo.append(", '");
			sMetodo.append(xmlParam);
			sMetodo.append("');\"");
			break;
			/******************************************* MOTIVO **************************************************************************************************************************************************/
		case 6:
			sMetodo.append("\"javascript: SetCodigoMotivo(");
			sMetodo.append(id);
			sMetodo.append(",");
			// sMetodo.append(idAux2);
			sMetodo.append(getArvoreForm().getIdStatus());
			sMetodo.append(", '");
			sMetodo.append(xmlParam);
			sMetodo.append("');\"");
			break;
			/******************************************* SUBMOTIVO **************************************************************************************************************************************************/
		case 7:
			sMetodo.append("'javascript: SetCodigoSubMotivo(");
			sMetodo.append(id);
			sMetodo.append(",");
			// sMetodo.append(idAux);
			sMetodo.append(getArvoreForm().getIdMotivo());
			sMetodo.append(", ");
			// sMetodo.append(idAux2);
			sMetodo.append(getArvoreForm().getIdStatus());
			sMetodo.append(");'");
			break;
		default:
			sMetodo.append("''");
			break;
		}
		return sMetodo.toString();
	}

	private String getImgNodeClose(int iTipo, int iFinal, int idApres) {
		String sImgClose = ConstantesCRM.SVAZIO;

		/******************************************* RESPOSTAS COM DESVIO **************************************************************************************************************************************************/
		if (idApres == 8) {
			return "/FrontOfficeWeb/resources/images/icon_resp_salto.gif";
		}
		switch (iTipo) {
		/******************************************* STATUS - MOTIVO - SUBMOTIVO **************************************************************************************************************************************************/
		case 0:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_stmtsb_close.png";
			break;
			/******************************************* CAMPANHA **************************************************************************************************************************************************/
		case 1:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_subcamp_close.png";
			break;
			/******************************************* CANAL CAMPANHA **************************************************************************************************************************************************/
		case 2:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_canal_close.png";
			break;
			/******************************************* PERGUNTA **************************************************************************************************************************************************/
		case 3:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_perg_indic.gif";
			if ((idApres == 5) || (idApres == 3)) {
				sImgClose = "/FrontOfficeWeb/resources/images/icon_perg_texto.gif";
			}
			break;
			/******************************************* RESPOSTA **************************************************************************************************************************************************/
		case 4:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_resp_nrml.gif";
			break;
			/******************************************* STATUS **************************************************************************************************************************************************/
		case 5:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_st.gif";
			break;
			/******************************************* MOTIVO **************************************************************************************************************************************************/
		case 6:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_mt.gif";
			break;
			/******************************************* SUBMOTIVO **************************************************************************************************************************************************/
		case 7:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_sb.gif";
			break;
		default:
		}
		return sImgClose;
	}

	private String getImgNodeOpen(int iTipo, int iFinal, int idApres) {
		/******************************************* RESPOSTAS COM DESVIO **************************************************************************************************************************************************/
		if (idApres == 8) {
			return "/FrontOfficeWeb/resources/images/icon_resp_salto.gif";
		}
		String sImgClose = ConstantesCRM.SVAZIO;
		switch (iTipo) {
		/******************************************* STATUS - MOTIVO - SUBMOTIVO **************************************************************************************************************************************************/
		case 0:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_stmtsb_open.png";
			break;
			/******************************************* CAMPANHA **************************************************************************************************************************************************/
		case 1:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_subcamp_open.png";
			break;
			/******************************************* CANAL CAMPANHA **************************************************************************************************************************************************/
		case 2:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_canal_open.png";
			break;
			/******************************************* PERGUNTA **************************************************************************************************************************************************/
		case 3:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_perg_indic.gif";
			if ((idApres == 5) || (idApres == 3)) {
				sImgClose = "/FrontOfficeWeb/resources/images/icon_perg_texto.gif";
			}
			break;
			/******************************************* RESPOSTA **************************************************************************************************************************************************/
		case 4:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_resp_nrml.gif";
			break;
			/******************************************* STATUS **************************************************************************************************************************************************/
		case 5:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_st.gif";
			break;
			/******************************************* MOTIVO **************************************************************************************************************************************************/
		case 6:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_mt.gif";
			break;
			/******************************************* SUBMOTIVO **************************************************************************************************************************************************/
		case 7:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_sb.gif";
			break;
		default:
		}
		return sImgClose;
	}

	public void getDadosCampanha(HttpServletRequest request) throws Exception {
		try {
			idCanalSelecionado = (String) request.getSession().getAttribute("idCanalCampanha");

			dsCanalSelecionado = getMapCanais(idCanalSelecionado, request);

			request.setAttribute("idCanalCampanha", idCanalSelecionado);
			request.setAttribute("dsCanalCampanha", dsCanalSelecionado);

			getScriptCampanhaActionForm().setIdCanalCampanha(idCanalSelecionado);
			getScriptCampanhaActionForm().setDsCanalCampanha(dsCanalSelecionado);

			/********************* Campanha e SubCampanha **************************************/
			idCampanhaSelecionada = (String) request.getSession().getAttribute("idCampanha");
			idSubCampanhaSelecionada = (String) request.getSession().getAttribute("idSubCampanha");

			request.setAttribute("idCampanha", idCampanhaSelecionada);
			request.setAttribute("idSubCampanha", idSubCampanhaSelecionada);

			getScriptCampanhaActionForm().setIdCampanha(idCampanhaSelecionada);
			getScriptCampanhaActionForm().setIdSubCampanha(idSubCampanhaSelecionada);

			String sDescricao = (request.getParameter("Desc") != null) ? request.getParameter("Desc") : ConstantesCRM.SVAZIO;
			request.setAttribute("Descricao", sDescricao);

		} catch (Exception e) {
			throw e;
		}
	}

	/************************************************ INCLUIR *****************************************************************************************/

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../ManterPergunta/begin.do?acao=incluir"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluirPergunta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String sIndexPergunta = (request.getParameter("indexPergunta") != null) ? request.getParameter("indexPergunta") : ConstantesCRM.SVAZIO;
			request.setAttribute("idPergunta", sIndexPergunta);
			getDadosCampanha(request);
		} catch (Exception e) {
			throw e;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../ManterResposta/begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluirResposta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding(ConstantesCRM.SISO);

		String sIndexPergunta = (request.getParameter("indexPergunta") != null) ? request.getParameter("indexPergunta") : ConstantesCRM.SVAZIO;

		if (sIndexPergunta.equals(ConstantesCRM.SVAZIO)) {
			throw new Exception("Codigo pergunta invalido");
		}
		request.setAttribute("idPergunta", sIndexPergunta);
		request.setAttribute("idResposta", ConstantesCRM.SZERO);
		getDadosCampanha(request);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../../Configurar/ConfigurarSubCampanha/begin.do?acao=incluir"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluirStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String sIndexStatus = (request.getParameter("indexStatus") != null) ? request.getParameter("indexStatus") : ConstantesCRM.SVAZIO;
			request.getSession().setAttribute("IDSTATUS", sIndexStatus);
			getDadosCampanha(request);
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../ManterSubCampanha/begin.do?acao=incluir"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluirSubCampanha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			getDadosCampanha(request);
		} catch (Exception e) {
			throw e;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/************************************************ EDITAR *****************************************************************************************/

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../ManterSubCampanha/manterSubCampanhaActionAlterar.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward editarSubCampanha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			getDadosCampanha(request);
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../ManterPergunta/begin.do?acao=editar"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward editarPergunta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			String sIndexPergunta = (request.getParameter("indexPergunta") != null) ? request.getParameter("indexPergunta") : "";
			request.setAttribute("idPergunta", sIndexPergunta);
			getDadosCampanha(request);
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../ManterResposta/manterRespostaAlterar.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward editarResposta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sIndexResposta = (request.getParameter("indexResposta") != null) ? request.getParameter("indexResposta") : ConstantesCRM.SVAZIO;
		request.setAttribute("idPergunta", ConstantesCRM.SZERO);
		request.setAttribute("idResposta", sIndexResposta);
		getDadosCampanha(request);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../../Configurar/ConfigurarSubCampanha/begin.do?acao=editar"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward editarStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String sIndexStatus = (request.getParameter("indexStatus") != null) ? request.getParameter("indexStatus") : ConstantesCRM.SVAZIO;
			request.getSession().setAttribute("IDSTATUS", sIndexStatus);
			getDadosCampanha(request);
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/************************************************ APAGAR *****************************************************************************************/

	/**
	 * @jpf:action
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward deleteSubCampanha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		/*
		 * tipo = 2, deleção de sub campanha, se tiver erro exibe mensagem, caso contrario recarrega!
		 */
		request.setAttribute("tipo", ConstantesCRM.STWO);
		try {
			String _idSubCampanhaFixa = (request.getParameter("idCampanha") != null) ? request.getParameter("idCampanha") : ConstantesCRM.SVAZIO;
			String _idSubCampanhaHistorico = (request.getParameter("idSubCampanha") != null) ? request.getParameter("idSubCampanha") : ConstantesCRM.SVAZIO;

			if ((!_idSubCampanhaFixa.equals(ConstantesCRM.SVAZIO)) && (!_idSubCampanhaHistorico.equals(ConstantesCRM.SVAZIO))) {
				arvoreFacade.delArvoreSubCampanha(user, _idSubCampanhaFixa, _idSubCampanhaHistorico);
			}
		} catch (TuxedoWarningException e) {
			request.setAttribute(MSG_ERROR_VIEW_SCRIPT, e.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("fechar");
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechar");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward deleteResposta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		request.setAttribute("tipo", ConstantesCRM.SSIX);

		try {
			String _idResposta = (request.getParameter("idResposta") != null) ? request.getParameter("idResposta") : ConstantesCRM.SVAZIO;
			if (!ConstantesCRM.SVAZIO.equals(_idResposta)) {
				RetornoVO retorno = questionarioFacade.delRespostaCampanha(user, _idResposta);
				trataErro(retorno, request);
			}
		} catch (TuxedoWarningException e) {
			request.setAttribute(MSG_ERROR_VIEW_SCRIPT, e.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("fechar");
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechar");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="CarregarAction.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward deleteCanal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			String _idCanalCampanha = (request.getParameter("idCanalCampanha") != null) ? request.getParameter("idCanalCampanha") : ConstantesCRM.SVAZIO;
			if (!ConstantesCRM.SVAZIO.equals(_idCanalCampanha)) {
				RetornoVO retorno = arvoreFacade.delArvoreCanalCampanha(user, _idCanalCampanha);
				trataErro(retorno, request);
			}
		} catch (TuxedoException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward deletePergunta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			String _idCanalCampanha = (request.getParameter("idCanalCampanha") != null) ? request.getParameter("idCanalCampanha") : ConstantesCRM.SVAZIO;
			String _idPergunta = (request.getParameter("idPergunta") != null) ? request.getParameter("idPergunta") : ConstantesCRM.SVAZIO;

			if (!ConstantesCRM.SVAZIO.equals(_idPergunta) && !ConstantesCRM.SVAZIO.equals(_idCanalCampanha)) {

				RetornoVO retorno = arvoreFacade.delArvorePergunta(user, _idCanalCampanha, _idPergunta);

				if (retorno != null && "2".equals(retorno.getValor())) {
					request.setAttribute(MSG_ERROR_VIEW_SCRIPT, retorno.getDescricao());
				}
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("tipo", ConstantesCRM.SSIX);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechar");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="CarregarAction.do"
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward deleteStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			idSubCampanhaSelecionada = (String) request.getSession().getAttribute("idSubCampanha");

			String _idStatus = (request.getParameter("idStatusCampanha") != null) ? request.getParameter("idStatusCampanha") : ConstantesCRM.SVAZIO;

			if (!ConstantesCRM.SVAZIO.equals(_idStatus) && idSubCampanhaSelecionada != null && !idSubCampanhaSelecionada.equals(ConstantesCRM.SVAZIO)) {
				RetornoVO retorno = arvoreFacade.delArvoreMotivoCampanha(user, idSubCampanhaSelecionada, _idStatus);
				trataErro(retorno, request);
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("tipo", ConstantesCRM.SSIX);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechar");
	}

	public void trataErro(RetornoVO retorno, HttpServletRequest request) {
		if (ConstantesCRM.SZERO.equals(retorno.getValor())) {
			request.setAttribute(MSG_ERROR_VIEW_SCRIPT, "Não foi possível excluir pois existem dependências!");
		}
	}

	public static class ScriptCampanhaActionForm extends ActionForm {

		private static final long serialVersionUID = -4600500511724654212L;

		private String versaoSelecionada = ConstantesCRM.SVAZIO;

		private ApoioVO[] listaVersao;
		private ApoioVO[] listaCampanha;
		private ApoioVO[] listaSubCampanha;

		private String idCanalCampanha;
		private String idSubCampanha;
		private String idCampanha;
		private String dsCanalCampanha = ConstantesCRM.SVAZIO;
		private String dsPergunta = ConstantesCRM.SVAZIO;
		private String subCampanhaSelecionada = ConstantesCRM.SVAZIO;
		private String campanhaSelecionada = ConstantesCRM.SVAZIO;
		private String sMsg = ConstantesCRM.SVAZIO;
		private String scriptArvore = ConstantesCRM.SVAZIO;

		public String getScriptArvore() {
			return scriptArvore;
		}

		public void setScritpArvore(String s) {
			scriptArvore = s;
		}

		public void setIdCampanha(String idCampanha) {
			this.idCampanha = idCampanha;
		}

		public String getIdCampanha() {
			return this.idCampanha;
		}

		public void setIdSubCampanha(String idSubCampanha) {
			this.idSubCampanha = idSubCampanha;
		}

		public String getIdSubCampanha() {
			return this.idSubCampanha;
		}

		public void setIdCanalCampanha(String idCanalCampanha) {
			this.idCanalCampanha = idCanalCampanha;
		}

		public String getIdCanalCampanha() {
			return this.idCanalCampanha;
		}

		public void setListaCampanha(ApoioVO[] lista) {
			if (this.listaCampanha == null) {
				this.listaCampanha = new ApoioVO[0];
			}
			this.listaCampanha = lista;
		}

		public ApoioVO[] getListaCampanha() {
			return this.listaCampanha;
		}

		public String getCampanhaSelecionada() {
			return campanhaSelecionada;
		}

		public void setCampanhaSelecionada(String s) {
			campanhaSelecionada = s;
		}

		public void setListaSubCampanha(ApoioVO[] listaSubCampanha) {
			this.listaSubCampanha = listaSubCampanha;
		}

		public ApoioVO[] getListaSubCampanha() {
			if (this.listaSubCampanha == null) {
				this.listaSubCampanha = new ApoioVO[0];
			}
			return this.listaSubCampanha;
		}

		public String getSubCampanhaSelecionada() {
			return subCampanhaSelecionada;
		}

		public void setSubCampanhaSelecionada(String s) {
			subCampanhaSelecionada = s;
		}

		public String getSMsg() {
			return sMsg;
		}

		public void setSMsg(String s) {
			this.sMsg = s;
		}

		public String getDsCanalCampanha() {
			return dsCanalCampanha;
		}

		public void setDsCanalCampanha(String s) {
			this.dsCanalCampanha = s;
		}

		public String getDsPergunta() {
			return dsPergunta;
		}

		public void setDsPergunta(String s) {
			this.dsPergunta = s;
		}

		public void setListaVersao(ApoioVO[] listaVersao) {
			this.listaVersao = listaVersao;
		}

		public ApoioVO[] getListaVersao() {
			if (this.listaVersao == null) {
				this.listaVersao = new ApoioVO[0];
			}

			return this.listaVersao;
		}

		public void setVersaoSelecionada(String versaoSelecionada) {
			this.versaoSelecionada = versaoSelecionada;
		}

		public String getVersaoSelecionada() {
			return this.versaoSelecionada;
		}
	}

	private ArvoreForm arvoreForm;

	public ArvoreForm getArvoreForm() {
		if (arvoreForm == null) {
			arvoreForm = new ArvoreForm();
		}
		return arvoreForm;
	}

	private void setArvoreForm(ArvoreForm arvoreForm) {
		this.arvoreForm = arvoreForm;
	}

	public static class ArvoreForm extends ActionForm {

		private static final long serialVersionUID = -3750181091990271647L;

		private String idCampanha;
		private String idSubCampanha;
		private String idCanalCampanha;
		private String dsCanalCampanha;
		private String xmlParam;

		private String idCanal;
		private String idPergunta;
		private String idResposta;
		private String idStatus;
		private String idMotivo;
		private String idSubMotivo;
		private String inResposta;

		public String getIdCampanha() {
			return idCampanha;
		}

		public void setIdCampanha(String idCampanha) {
			this.idCampanha = idCampanha;
		}

		public String getIdSubCampanha() {
			return this.idSubCampanha;
		}

		public void setIdSubCampanha(String idSubCampanha) {
			this.idSubCampanha = idSubCampanha;
		}

		public void setIdCanalCampanha(String idCanalCampanha) {
			this.idCanalCampanha = idCanalCampanha;
		}

		public String getIdCanalCampanha() {
			return this.idCanalCampanha;
		}

		public void setDsCanalCampanha(String dsCanalCampanha) {
			this.dsCanalCampanha = dsCanalCampanha;
		}

		public String getDsCanalCampanha() {
			return this.dsCanalCampanha;
		}

		public String getXmlParam() {
			return this.xmlParam;
		}

		public void setXmlParam(String xmlParam) {
			this.xmlParam = xmlParam;
		}

		public String getIdCanal() {
			return idCanal;
		}

		public void setIdCanal(String idCanal) {
			this.idCanal = idCanal;
		}

		public String getIdPergunta() {
			return idPergunta;
		}

		public void setIdPergunta(String idPergunta) {
			this.idPergunta = idPergunta;
		}

		public String getInResposta() {
			return inResposta;
		}

		public void setInResposta(String inResposta) {
			this.inResposta = inResposta;
		}

		public String getIdResposta() {
			return idResposta;
		}

		public void setIdResposta(String idResposta) {
			this.idResposta = idResposta;
		}

		public String getIdStatus() {
			return idStatus;
		}

		public void setIdStatus(String idStatus) {
			this.idStatus = idStatus;
		}

		public String getIdMotivo() {
			return idMotivo;
		}

		public void setIdMotivo(String idMotivo) {
			this.idMotivo = idMotivo;
		}

		public String getIdSubMotivo() {
			return idSubMotivo;
		}

		public void setIdSubMotivo(String idSubMotivo) {
			this.idSubMotivo = idSubMotivo;
		}
	}
}