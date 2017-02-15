package fidelizacao.Configurar.ConfigurarMatrizAparelho;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.commons.utils.StringUtil;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.Fidelizacao;
import br.com.vivo.fo.ctrls.fidelizacao.configurar.MatrizAparelhoFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.AparelhoCorVODocument.AparelhoCorVO;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.DescontoVODocument.DescontoVO;
import br.com.vivo.fo.fidelizacao.vo.DetalheAparelhoVODocument.DetalheAparelhoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Selecionado;
import br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO;
import br.com.vivo.fo.fidelizacao.vo.ListaDDDVODocument.ListaDDDVO;
import br.com.vivo.fo.fidelizacao.vo.ParcelaVODocument.ParcelaVO;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ConfigurarMatrizAparelhoController extends AbstractAction {

	@EJB
	public Fidelizacao fidelizacao;

	private static final long serialVersionUID = 7305120691225403562L;
	private transient Logger log = new Logger("Fidelizacao");

	private ActionMatrizAparelhoForm actionMatrizAparelhoForm = new ActionMatrizAparelhoForm();
	private ShowDadosAparelhoForm dadosAparelhoForm = new ShowDadosAparelhoForm();
	private AparelhoForm aparelhoForm = new AparelhoForm();

	public ArrayListaGeralVO aparelhos = null;

	protected global.Global globalApp = new global.Global();

	public ShowDadosAparelhoForm getDadosAparelhoForm() {
		return this.dadosAparelhoForm;
	}

	public ActionMatrizAparelhoForm getActionMatrizAparelhoForm() {
		return this.actionMatrizAparelhoForm;
	}

	public AparelhoForm getAparelhoForm() {
		if (this.aparelhoForm == null) {
			this.aparelhoForm = new AparelhoForm();
		}
		return this.aparelhoForm;
	}

	@EJB
	private MatrizAparelhoFacade matrizFac;

	private String idUsuario;

	private String getIdUsuario(HttpServletRequest request) {
		if (idUsuario == null) {
			idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		}
		return idUsuario;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("montarArvore".equals(mapping.getParameter())) {
			return montarArvore(mapping, form, request, response);
		} else if ("VuelveConsMatriz".equals(mapping.getParameter())) {
			return VuelveConsMatriz(mapping, form, request, response);
		} else if ("abaFiltro".equals(mapping.getParameter())) {
			return abaFiltro(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("gravarAparelhos".equals(mapping.getParameter())) {
			return gravarAparelhos(mapping, form, request, response);
		} else if ("ActionMatrizAparelho".equals(mapping.getParameter())) {
			return ActionMatrizAparelho(mapping, form, request, response);
		} else if ("ActionCarregaDadosIniciais".equals(mapping.getParameter())) {
			return ActionCarregaDadosIniciais(mapping, form, request, response);
		} else if ("showMatrizAparelhos".equals(mapping.getParameter())) {
			return showMatrizAparelhos(mapping, form, request, response);
		} else if ("showDadosAparelho".equals(mapping.getParameter())) {
			return showDadosAparelho(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ConsultaMatrizAparelho.jsp"
	 * @jpf:forward name="success1" path="iframeArvore.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	private ActionForward montarArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ActionMatrizAparelhoForm form = (ActionMatrizAparelhoForm) formParam;
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			// recupera nós da árvore
			ItemArvoreVO itemArvoreVO = matrizFac.getArvoreMatriz(getIdUsuario(request), getIdPaiXMLParam(request));
			// monta árvore e envia por request
			request.setAttribute("arvore", getArvore(itemArvoreVO.getItemArvoreVOArray(), request));
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(request.getParameter("idPai") == null ? ConstantesCRM.SUCCESS : "success1");
	}

	/**
	 * Retorna string XML com os parametros necessários para o tuxedo mostrar a
	 * árvore
	 *
	 * @return String
	 */
	private String getIdPaiXMLParam(HttpServletRequest request) {
		StringBuffer xmlParam = new StringBuffer(ConstantesCRM.SVAZIO); // variável para montar a
														// String XML.
		String[] splitIdPai = request.getParameter("idPai") != null && !request.getParameter("idPai").equals(ConstantesCRM.SVAZIO) ? request.getParameter("idPai").split("X")
		// separa os ids que veem do request.
				: null; // retorna null caso não exista ids na url.
		String[] tagsIdPai = new String[] { "<idUF>#</idUF>", "<idGrupo>#</idGrupo>", "<idTipoPessoa>#</idTipoPessoa>",
				// "<idTipoLinha>#</idTipoLinha>",
				"<idSegmentacao>#</idSegmentacao>" }; // templates para mosntar
														// a String XML.
		// verifica se existem ids pai para montar a String XML
		while (splitIdPai != null) {
			for (int t = 0; t < splitIdPai.length; t++) {
				xmlParam.append(tagsIdPai[t].replaceAll("#", splitIdPai[t])); // substitui
																				// o
																				// "#"
																				// do
																				// template
																				// pelo
																				// id
				// pai.
			}
			splitIdPai = null;
		}
		tagsIdPai = null;
		return xmlParam.toString(); // retorna a string XML montada.
	}

	/**
	 * Retorna JavaScript que monta a árvore do lado do cliente
	 *
	 * @return String
	 */
	private String getArvore(ItemArvoreVO[] itemArvore, HttpServletRequest request) {
		StringUtil strUtil = new StringUtil(); // objeto que contém método para
												// scape de caracteres especiais
		StringBuffer scriptJS = new StringBuffer(); // variável para armazenar
													// árvore
		String[] icones = new String[] { "bt_icon_regional.gif", // array com
																	// icones
																	// dos nós
				"bt_icon_relac.gif", "bt_icon_perfil.gif",
				// "bt_icon_edit_usuario.gif",
				"bt_icon_segmentacao.gif", "icon_celular.gif" };
		String idPai = request.getParameter("idPai") != null && !request.getParameter("idPai").equals(ConstantesCRM.SVAZIO) ? request.getParameter("idPai") + "X" // recupera
				// id
				// dos
				// Pais
				// do
				// nó
		: ConstantesCRM.SVAZIO;
		// variáveis auxiliares para montar o scriptJS
		String icone = ConstantesCRM.SVAZIO;
		String parent = ConstantesCRM.SVAZIO;
		String getSelected = ConstantesCRM.SVAZIO;
		String finalizaJS = ConstantesCRM.SVAZIO;
		String function = ConstantesCRM.SVAZIO;
		int nivel = 0;

		if (itemArvore != null && itemArvore.length > 0) {
			nivel = itemArvore[0].getNivel(); // recupera o nível dos nós na
												// árvore
			icone = "/FrontOfficeWeb/resources/images/" + icones[nivel - 1];// recupera
																			// o
																			// ícone
																			// específico
																			// para
																			// o
																			// nó
			function = nivel < 5 ? "expandirNo" : "abreGeral"; // recupera a
																// função JS que
																// será
																// executada
																// quando o
			// usuário clicar no nó
			parent = nivel > 1 ? "parent." : ConstantesCRM.SVAZIO; // retorna "parent." se a
													// chamada for pelo "iFrame"
			getSelected = nivel > 1 ? ".getSelected()" : ConstantesCRM.SVAZIO; // retorna
																// ".getSelected"
																// se a chamada
																// for pelo
																// "iFrame"
			finalizaJS = nivel > 1 ? "parent.treeAparelhos.getSelected().setAddEnabled(false);\n\nparent.treeAparelhos.getSelected().expand()\n\n" // chamada
			// do
			// "iFrame"
					: "\ndocument.write(treeAparelhos);}\n\n";// chamada da
																// página
																// principal,
																// primeiro
																// nível da
																// árvore

			scriptJS.append(nivel == 1 ? "if (document.getElementById) { var treeAparelhos = new WebFXTree('Matriz de Aparelhos');\n" // se
			// for
			// o
			// primeiro
			// nível
			// da
			// árvore
					: ConstantesCRM.SVAZIO);
			// percorre os itens da árvore
			for (int i = 0; i < itemArvore.length; i++) {
				scriptJS.append(parent + "tmpArvore = new " + parent + "WebFXTreeItem(") // cria
																							// objeto
																							// JS
																							// para
																							// o
																							// item
						// da árvore
						.append("'" + strUtil.escapaComillasJS(itemArvore[i].getDescricao()) + "',")// informa
																									// o
																									// nome
																									// do
						// item
						.append("\"Javascript:" + function + "(") // informa a
																	// função
																	// que será
																	// executada
																	// qdo o
																	// usuário
						// clicar no item da árvore
						.append("'" + idPai + itemArvore[i].getId() + "')\",") // informa
																				// os
																				// ids
																				// dos
																				// pais
																				// do
																				// item
																				// da
						// árvore
						.append("'','" + icone + "','" + icone + "');\n") // informa
																			// os
																			// icones
																			// que
																			// representarão
																			// o
																			// item
						// da árvore
						.append(parent + "treeAparelhos" + getSelected + ".add(" + parent + "tmpArvore);\n\n"); // adiciona
				// item
				// à
				// árvore
			}
			scriptJS.append(finalizaJS); // adiciona itens à árvore existente,
											// ou exibe árvore na tela
		}
		// zera variáveis para melhor coleção da garbage
		strUtil = null;
		icones = null;
		idPai = null;
		icone = null;
		parent = null;
		getSelected = null;
		finalizaJS = null;
		function = null;
		return scriptJS.toString(); // retorna o script para montar a árvore
	}

	protected void montaArrvore(ActionMatrizAparelhoForm actionMatrizAparelhoForm, HttpServletRequest request) throws Exception {
		ItemArvoreVO item = matrizFac.getArvoreMatriz(getIdUsuario(request), ConstantesCRM.SVAZIO);
		String script = "if (document.getElementById) {var tree = new WebFXTree('Matriz de Aparelhos');tree.setBehavior('classic');";
		String corpoArvore = VerificaItemMatriz(item, "tree");
		script = script + corpoArvore + "document.write(tree);}";
		request.getSession().setAttribute("script", script);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="matrizAparelhos.jsp"
	 */
	protected ActionForward VuelveConsMatriz(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private String VerificaItemMatriz(ItemArvoreVO iten, String tree) {
		String node = ConstantesCRM.SVAZIO;
		if (iten.getItemArvoreVOArray().length == 0) {
			return ConstantesCRM.SVAZIO;
		} else {
			for (int x = 0; x < iten.getItemArvoreVOArray().length; x++) {
				ItemArvoreVO nol = iten.getItemArvoreVOArray(x);
				String icon = "";
				String function = "''";
				switch (nol.getNivel()) {
				case 1:
					icon = "'/FrontOfficeWeb/resources/images/bt_icon_regional.gif'";
					break;
				case 2:
					icon = "'/FrontOfficeWeb/resources/images/bt_icon_relac.gif'";
					break;
				case 3:
					icon = "'/FrontOfficeWeb/resources/images/bt_icon_perfil.gif'";
					break;
				/*
				 * case 4 : icon =
				 * "'/FrontOfficeWeb/resources/images/bt_icon_edit_usuario.gif'"
				 * ; break;
				 */
				case 5:
					icon = "'/FrontOfficeWeb/resources/images/bt_icon_segmentacao.gif'";
					break;
				case 6:
					icon = "'/FrontOfficeWeb/resources/images/icon_celular.gif'";
					function = "'javascript:abreGeral(" + nol.getId() + ");'";
					break;
				default:
					icon = null;
					break;
				}
				node += "var " + tree + String.valueOf(x) + " = new WebFXTreeItem('" + StringEscapeUtils.escapeJavaScript(nol.getDescricao()) + "'," + function + ",''," + icon + "," + icon + ");";
				node += VerificaItemMatriz(nol, tree + String.valueOf(x));
				node += tree + ".add(" + tree + String.valueOf(x) + "); ";
			}
		}
		return node;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="ActionCarregaDadosIniciais.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		actionMatrizAparelhoForm = new ActionMatrizAparelhoForm(matrizFac.getDadosIniciais(getIdUsuario(request)));
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ConfigurarMatrizAparelhoDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="camposPesquisa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward abaFiltro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		AparelhoForm form = (AparelhoForm) formParam;
		try {
			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
			xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
			// BREDES COMENTOU
			// xml.getFiltro().getCombos().addNmSelect("TPLINHA");
			xml.getFiltro().getCombos().addNmSelect("GRUPOS");
			xml.getFiltro().getCombos().addNmSelect("SEGMENTACAO");

			FidelizacaoVO listas = fidelizacao.getListas(getIdUsuario(request), xml);
			getAparelhoForm().setFidelizacaoVO(listas);
			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getAparelhoForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getAparelhoForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());

				}
				// BREDES COMENTOU
				// else
				// if("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())){
				// getAparelhoForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());

				// }
				else if ("GRUPOS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getAparelhoForm().setListaGrupos(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getAparelhoForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}
			if (getAparelhoForm().getListaGrupos() == null) {
				getAparelhoForm().setListaGrupos(Disponivel.Factory.newInstance());
			}

			getAparelhoForm().setListaSelRegional(Selecionado.Factory.newInstance());
			getAparelhoForm().setListaSelClientes(Selecionado.Factory.newInstance());
			// BREDES COMENTOU
			// getAparelhoForm().setListaSelLinhas(Selecionado.Factory.newInstance());
			getAparelhoForm().setListaSelGrupos(Selecionado.Factory.newInstance());
			getAparelhoForm().setListaSelSegmentacao(Selecionado.Factory.newInstance());
		} catch (Exception e) {
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configAparelhos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		AparelhoForm form = (AparelhoForm) formParam;
		try {
			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("CFGAPARELHOS");
			xml.setTpProcesso("PSQ");

			Lista lista = xml.addNewListasVO().addNewLista();
			lista.setNmSelect("REGIONAL");
			Selecionado selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelRegional().length; i++) {
				selecao.addNewIt().setId(form.getIdSelRegional()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("TPCLIENTE");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelTpCliente().length; i++) {
				selecao.addNewIt().setId(form.getIdSelTpCliente()[i]);
			}
			// BREDES COMENTOU
			// lista = xml.getListasVO().addNewLista();
			// lista.setNmSelect("TPLINHA");
			// selecao = lista.addNewSelecionado();
			// for(int i=0; i<form.getIdSelTpLinha().length;i++){
			// selecao.addNewIt().setId(form.getIdSelTpLinha()[i]);
			// }

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("GRUPOS");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelGrupos().length; i++) {
				selecao.addNewIt().setId(form.getIdSelGrupos()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("SEGMENTACAO");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelSegmentacao().length; i++) {
				selecao.addNewIt().setId(form.getIdSelSegmentacao()[i]);
			}

			getAparelhoForm().setFidelizacaoVO(xml);

			FidelizacaoVO listas = fidelizacao.getListas(getIdUsuario(request), xml);
			if (listas.getListasVO() == null) {
				listas.addNewListasVO().addNewLista();
			}
			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("APARELHOS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getAparelhoForm().setListaAparelhos(listas.getListasVO().getListaArray(i).getDisponivel());
					getAparelhoForm().setListaSelAparelhos(listas.getListasVO().getListaArray(i).getSelecionado());
				}
			}
			if (getAparelhoForm().getListaAparelhos() == null) {
				getAparelhoForm().setListaAparelhos(Disponivel.Factory.newInstance());
			}
			if (getAparelhoForm().getListaSelAparelhos() == null) {
				getAparelhoForm().setListaSelAparelhos(Selecionado.Factory.newInstance());
			}

		} catch (Exception e) {
			if (getAparelhoForm().getListaAparelhos() == null) {
				getAparelhoForm().setListaAparelhos(Disponivel.Factory.newInstance());
			}
			if (getAparelhoForm().getListaSelAparelhos() == null) {
				getAparelhoForm().setListaSelAparelhos(Selecionado.Factory.newInstance());
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaFiltro.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward gravarAparelhos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		AparelhoForm form = (AparelhoForm) formParam;
		try {
			FidelizacaoVO xml = getAparelhoForm().getFidelizacaoVO();

			xml.setNmProcesso("CFGAPARELHOS");
			xml.setTpProcesso("ALT");

			if (xml.getListasVO() == null) {
				xml.addNewListasVO();
			}

			Lista lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("APARELHOS");
			Selecionado selecao = lista.addNewSelecionado();

			FidelizacaoVO conf = FidelizacaoVO.Factory.newInstance();
			int processId = (int) (Math.random() * 10000) + (int) (Math.random() * 10000);
			xml.setCodError(String.valueOf(processId));
			xml.setMsgError(String.valueOf(form.getIdSelAparelhos().length));
			// BREDES COMENTEI PARA TESTE
			// for(int i=0; i<form.getIdSelAparelhos().length;i++){
			// selecao.addNewIt().setId(form.getIdSelAparelhos()[i]);
			// conf = fidelizacao.setParam(getIdUsuario(request), xml);
			// int tam = selecao.getItArray().length;
			// for(int x=0;x<tam;x++) selecao.removeIt(0);
			// }
			// FIM COMENTARIO

			// BREDES TRECHO NOVO
			for (int i = 0; i < form.getIdSelAparelhos().length; i++) {
				selecao.addNewIt().setId(form.getIdSelAparelhos()[i]);

			}
			conf = fidelizacao.setParam(getIdUsuario(request), xml);
			// FIM TRECHO NOVO

			if (!ConstantesCRM.SONE.equals(conf.getCodError())) {
				request.setAttribute("msgError", "A Matriz de Aparelhos foi processada com sucesso.");
			}
		} catch (Exception e) {
			log.error("gravarAparelhos()::" + e.getMessage(), e);
			request.setAttribute("msgError", "Erro ao gravar a Matriz de Aparelhos: \\n" + e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="sair" path="done.do"
	 * @jpf:forward name="success" path="matrizAparelhos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ActionMatrizAparelho(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ActionMatrizAparelhoForm form = (ActionMatrizAparelhoForm) formParam;
		try {
			if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("salvar")) {
				matrizFac.setAparelhos(getIdUsuario(request), form.getRegionalSel(), form.getSegmentacaoSel(), form.getTipoClienteSel(), form.getAparelhosSelecionados(), form.getIdGrupo());
				aparelhos = matrizFac.getAparelhos(getIdUsuario(request), form.getRegionalSel(), form.getSegmentacaoSel(), form.getTipoClienteSel(), form.getIdGrupo());
				actionMatrizAparelhoForm.setAparelhosDisp(aparelhos.getFidelizacaoListaGeralVOArray(0).getItemListaVOArray());
				actionMatrizAparelhoForm.setAparelhosSel(aparelhos.getFidelizacaoListaGeralVOArray(1).getItemListaVOArray());
				actionMatrizAparelhoForm.setRegionalSel(form.getRegionalSel());
				actionMatrizAparelhoForm.setIdGrupo(form.getIdGrupo());
				actionMatrizAparelhoForm.setSegmentacaoSel(form.getSegmentacaoSel());
				actionMatrizAparelhoForm.setTipoClienteSel(form.getTipoClienteSel());

			} else if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("consultar")) {
				aparelhos = matrizFac.getAparelhos(getIdUsuario(request), form.getRegionalSel(), form.getSegmentacaoSel(), form.getTipoClienteSel(), form.getIdGrupo());
				actionMatrizAparelhoForm = new ActionMatrizAparelhoForm(matrizFac.getDadosIniciais(getIdUsuario(request)));
				actionMatrizAparelhoForm.setAparelhosDisp(aparelhos.getFidelizacaoListaGeralVOArray(0).getItemListaVOArray());
				actionMatrizAparelhoForm.setAparelhosSel(aparelhos.getFidelizacaoListaGeralVOArray(1).getItemListaVOArray());
				actionMatrizAparelhoForm.setRegionalSel(form.getRegionalSel());
				actionMatrizAparelhoForm.setIdGrupo(form.getIdGrupo());
				actionMatrizAparelhoForm.setSegmentacaoSel(form.getSegmentacaoSel());
				actionMatrizAparelhoForm.setTipoClienteSel(form.getTipoClienteSel());
			}
		} catch (TuxedoWarningException twe) {
			log.warn("ConfigurarMatrizAparelhoController:ActionMatrizAparelho(" + twe.getMessage() + ")");
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("actionMatrizAparelhoForm", actionMatrizAparelhoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="matrizAparelhos.jsp"
	 */
	protected ActionForward ActionCarregaDadosIniciais(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ConsultaMatrizAparelho.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward showMatrizAparelhos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			montaArrvore(actionMatrizAparelhoForm, request);
		} catch (TuxedoWarningException twe) {
			log.warn("ConfigurarMatrizAparelhoController:showMatrizAparelhos(" + twe.getMessage() + ")");
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
	 * @jpf:forward name="success" path="dadosAparelho.jsp"
	 * @jpf:forward name="fecha" path="../fechaFrame.html"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward showDadosAparelho(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ShowDadosAparelhoForm form = (ShowDadosAparelhoForm) formParam;
		try {
			if (request.getParameter(ConstantesCRM.SACTION) == null) {
				String idMatrizAparelho = request.getParameter("id");
				DetalheAparelhoVO detalheAparelho = matrizFac.getDetalheAparelho(getIdUsuario(request), idMatrizAparelho);
				this.dadosAparelhoForm = new ShowDadosAparelhoForm(detalheAparelho);
				form.setIdAparelho(dadosAparelhoForm.getIdAparelho());
				form.setValorMulta(dadosAparelhoForm.getValorMulta());
				// Se a operação for 0 , traz todas as parcelas cadastradas**/
				FidelizacaoListaGeralVO parcelas = matrizFac.getParcelas(getIdUsuario(request), idMatrizAparelho, ConstantesCRM.SZERO);
				this.dadosAparelhoForm.setParcelas(parcelas.getItemListaVOArray());

			} else {
				/*************************** Modificado por Marta Montiel 01/10/2004 *********************************************************************************/
				if (form.getDescontosSelecionados()[0] == null) {
					form.getDescontosSelecionados()[0] = new String(ConstantesCRM.SVAZIO);
				}
				if (form.getParcelasSelecionadas()[0] == null) {
					form.getParcelasSelecionadas()[0] = new String(ConstantesCRM.SVAZIO);
				}
				/************* Enviado coleção alterada pelo usuário *******************************************************************************************************************************************************************/
				String user = getIdUsuario(request);
				matrizFac.setDetAparelho(user, form.getIdAparelho(), form.getValorMulta(), dadosAparelhoForm.getArrayCor(), dadosAparelhoForm.getListaDDD(), form.getDescontosSelecionados(),
						form.getParcelasSelecionadas(), dadosAparelhoForm.getRegional(), dadosAparelhoForm.getInChipAvulso(), dadosAparelhoForm.getInChipPreProgramado());
				/*************************** Fim Modificacao ***************************************************************************************************************************************************************************/
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("fecha");
			}
		} catch (TuxedoWarningException twe) {
			log.warn("ConfigurarMatrizAparelhoController:showDadosAparelho(" + twe.getMessage() + ")");
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ActionMatrizAparelhoForm extends ActionForm {
		private static final long serialVersionUID = 1L;
		private String[] aparelhosDispSelecionados;
		private String[] aparelhosSelecionados;
		private String segmentacaoSel;
		private String tipoClienteSel;
		private String idGrupo;
		private String regionalSel;
		private ItemListaVO[] regionais = new ItemListaVO[0];
		private ItemListaVO[] tipoCliente = new ItemListaVO[0];
		private ItemListaVO[] segmentacao = new ItemListaVO[0];
		private ItemListaVO[] grupos = new ItemListaVO[0];
		private ItemListaVO[] aparelhosDisp = new ItemListaVO[0];
		private ItemListaVO[] aparelhosSel = new ItemListaVO[0];

		// construtores
		public ActionMatrizAparelhoForm() {
		}

		public ActionMatrizAparelhoForm(ArrayListaGeralVO arrayLista) {
			this.regionais = arrayLista.getFidelizacaoListaGeralVOArray(0).getItemListaVOArray();
			this.tipoCliente = arrayLista.getFidelizacaoListaGeralVOArray(1).getItemListaVOArray();
			this.segmentacao = arrayLista.getFidelizacaoListaGeralVOArray(2).getItemListaVOArray();
			this.grupos = arrayLista.getFidelizacaoListaGeralVOArray(3).getItemListaVOArray();
		}

		// metodos geters e seters
		public ItemListaVO[] getRegionais() {
			return this.regionais;
		}

		public void setRegionais(ItemListaVO[] regionais) {
			this.regionais = regionais;
		}

		public ItemListaVO[] getGrupos() {
			return this.grupos;
		}

		public void setGrupos(ItemListaVO[] grupos) {
			this.grupos = grupos;
		}

		public String getIdGrupo() {
			return this.idGrupo;
		}

		public void setIdGrupo(String idGrupo) {
			this.idGrupo = idGrupo;
		}

		public ItemListaVO[] getTipoCliente() {
			return this.tipoCliente;
		}

		public void setTipoCliente(ItemListaVO[] tipoCliente) {
			this.tipoCliente = tipoCliente;
		}

		public ItemListaVO[] getSegmentacao() {
			return this.segmentacao;
		}

		public void setSegmentacao(ItemListaVO[] segmentacao) {
			this.segmentacao = segmentacao;
		}

		public ItemListaVO[] getAparelhosDisp() {
			return this.aparelhosDisp;
		}

		public void setAparelhosDisp(ItemListaVO[] aparelhosDisp) {
			this.aparelhosDisp = aparelhosDisp;
		}

		public ItemListaVO[] getAparelhosSel() {
			return this.aparelhosSel;
		}

		public void setAparelhosSel(ItemListaVO[] aparelhosSel) {
			this.aparelhosSel = aparelhosSel;
		}

		public void setRegionalSel(String regioanlSel) {
			this.regionalSel = regioanlSel;
		}

		public String getRegionalSel() {
			return this.regionalSel;
		}

		public void setTipoClienteSel(String tipoClienteSel) {
			this.tipoClienteSel = tipoClienteSel;
		}

		public String getTipoClienteSel() {
			return this.tipoClienteSel;
		}

		public void setSegmentacaoSel(String segmentacaoSel) {
			this.segmentacaoSel = segmentacaoSel;
		}

		public String getSegmentacaoSel() {
			return this.segmentacaoSel;
		}

		public void setAparelhosSelecionados(String[] aparelhosSelecionados) {
			this.aparelhosSelecionados = aparelhosSelecionados;
		}

		public String[] getAparelhosSelecionados() {
			if (this.aparelhosSelecionados == null || this.aparelhosSelecionados.length == 0) {
				this.aparelhosSelecionados = new String[1];
			}
			return this.aparelhosSelecionados;
		}

		public void setAparelhosDispSelecionados(String[] aparelhosDispSelecionados) {
			this.aparelhosDispSelecionados = aparelhosDispSelecionados;
		}

		public String[] getAparelhosDispSelecionados() {
			if (this.aparelhosDispSelecionados == null || this.aparelhosDispSelecionados.length == 0) {
				this.aparelhosDispSelecionados = new String[1];
			}
			return this.aparelhosDispSelecionados;
		}
	}

	public static String formatarDecimal(String valor) {
		// se a variável estiver nula, retorna vazio
		if (valor == null) {
			valor = ConstantesCRM.SZERO;
		}

		DecimalFormatSymbols dSymbols = new DecimalFormatSymbols();
		dSymbols.setDecimalSeparator(',');
		dSymbols.setGroupingSeparator('.');

		DecimalFormat dFormat = new DecimalFormat("00", dSymbols);

		dFormat.isDecimalSeparatorAlwaysShown();
		dFormat.applyPattern("###,##0.00;(###,##0.00)");
		return dFormat.format(new Double(valor));
	}

	public static class ShowDadosAparelhoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String inChipPreProgramado;
		private String inChipAvulso;
		private ListaDDDVO[] listaDDD;
		private String regional;
		private String[] arrayIdAparelhoCor;
		// private String idAparelhoCor;
		private String[] lixoDescontos;
		private String[] lixoParcelas;
		private String[] arrayIdEstoque;
		private String[] arrayCodigoSAP;
		private String[] arrayQtdeEstoque;
		private String[] arrayPreco;
		private String[] descontosSelecionados;
		private String[] parcelasSelecionadas;
		private String valorMulta;
		private String modelo;
		private String marca;
		private String codigoSAP;
		private String idAparelho;
		private DetalheAparelhoVO detalheAparelho;
		private AparelhoCorVO[] arrayCor;
		private DescontoVO[] descontosSel;
		private ParcelaVO[] parcelasSel;
		private ItemListaVO[] parcelas = new ItemListaVO[0];

		// metodos construtores
		public ShowDadosAparelhoForm() {
		}

		public ShowDadosAparelhoForm(DetalheAparelhoVO detalheAparelho) {
			// Value objects do form
			this.detalheAparelho = detalheAparelho;
			this.arrayCor = detalheAparelho.getAparelhoCorVOArray();
			String sPrecoRpl = ConstantesCRM.SVAZIO;

			for (int i = 0; i < detalheAparelho.getAparelhoCorVOArray().length; i++) {
				sPrecoRpl = detalheAparelho.getAparelhoCorVOArray(i).getPreco();
				if ((sPrecoRpl != null) && (!sPrecoRpl.equals(ConstantesCRM.SVAZIO))) {
					// sPrecoRpl =
					// sPrecoRpl.replace(',','\u0000').replace('.',',');
					detalheAparelho.getAparelhoCorVOArray(i).setPreco(formatarDecimal(sPrecoRpl));
				}
			}

			this.listaDDD = detalheAparelho.getListaDDDVOArray();
			this.parcelasSel = detalheAparelho.getParcelaVOArray();
			this.descontosSel = detalheAparelho.getDescontoVOArray();
			this.arrayIdEstoque = new String[detalheAparelho.getAparelhoCorVOArray().length];
			for (int i = 0; i < detalheAparelho.getAparelhoCorVOArray().length; i++) {
				arrayIdEstoque[i] = detalheAparelho.getAparelhoCorVOArray(i).getIdEstoque();
			}

			this.arrayIdAparelhoCor = new String[detalheAparelho.getAparelhoCorVOArray().length];
			for (int i = 0; i < detalheAparelho.getAparelhoCorVOArray().length; i++) {
				arrayIdAparelhoCor[i] = detalheAparelho.getAparelhoCorVOArray(i).getIdAparelhoCor();
			}

			// outras variaveis
			this.marca = detalheAparelho.getMarca();
			this.regional = detalheAparelho.getIdUfoperadora();
			this.modelo = detalheAparelho.getModelo();
			/********************** Modificado por Decio JR 03/11/2004 ********************************/
			this.valorMulta = formatarDecimal(detalheAparelho.getValorMulta());
			/********************** Fim Modificacao ***************************************************/
			this.idAparelho = detalheAparelho.getIdAparelho();
		}

		// metodos geters e seters
		public ItemListaVO[] getParcelas() {
			return this.parcelas;
		}

		public void setParcelas(ItemListaVO[] parcelas) {
			this.parcelas = parcelas;
		}

		public void setParcelasSel(ParcelaVO[] parcelasSel) {
			this.parcelasSel = parcelasSel;
		}

		public ParcelaVO[] getParcelasSel() {
			if (this.parcelasSel == null) {
				this.parcelasSel = new ParcelaVO[0];
			}
			return this.parcelasSel;
		}

		public void setDescontosSel(DescontoVO[] descontosSel) {
			this.descontosSel = descontosSel;
		}

		public DescontoVO[] getDescontosSel() {
			if (this.descontosSel == null) {
				this.descontosSel = new DescontoVO[0];
			}
			return this.descontosSel;
		}

		public void setDetalheAparelho(DetalheAparelhoVO detalheAparelho) {
			this.detalheAparelho = detalheAparelho;
		}

		public DetalheAparelhoVO getDetalheAparelho() {
			return this.detalheAparelho;
		}

		public void setArrayCor(AparelhoCorVO[] array) {
			this.arrayCor = array;
		}

		public AparelhoCorVO[] getArrayCor() {
			return this.arrayCor;
		}

		public void setIdAparelho(String idAparelho) {
			this.idAparelho = idAparelho;
		}

		public String getIdAparelho() {
			return this.idAparelho;
		}

		public void setCodigoSAP(String codigoSAP) {
			this.codigoSAP = codigoSAP;
		}

		public String getCodigoSAP() {
			return this.codigoSAP;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getMarca() {
			return this.marca;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public String getModelo() {
			return this.modelo;
		}

		public void setValorMulta(String valorMulta) {
			this.valorMulta = valorMulta;
		}

		public String getValorMulta() {
			return this.valorMulta;
		}

		public void setParcelasSelecionadas(String[] parcelasSelecionadas) {
			this.parcelasSelecionadas = parcelasSelecionadas;
		}

		public String[] getParcelasSelecionadas() {
			if (this.parcelasSelecionadas == null || this.parcelasSelecionadas.length == 0) {
				this.parcelasSelecionadas = new String[1];
			}
			return this.parcelasSelecionadas;
		}

		public void setDescontosSelecionados(String[] descontosSelecionados) {
			this.descontosSelecionados = descontosSelecionados;
		}

		public String[] getDescontosSelecionados() {
			if (this.descontosSelecionados == null || this.descontosSelecionados.length == 0) {
				this.descontosSelecionados = new String[1];
			}
			return this.descontosSelecionados;
		}

		public void setArrayPreco(String[] arrayPreco) {
			this.arrayPreco = arrayPreco;
		}

		public String[] getArrayPreco() {
			if (this.arrayPreco == null || this.arrayPreco.length == 0) {
				this.arrayPreco = new String[1];
			}
			return this.arrayPreco;
		}

		public void setArrayQtdeEstoque(String[] arrayQtdeEstoque) {
			this.arrayQtdeEstoque = arrayQtdeEstoque;
		}

		public String[] getArrayQtdeEstoque() {
			if (this.arrayQtdeEstoque == null || this.arrayQtdeEstoque.length == 0) {
				this.arrayQtdeEstoque = new String[1];
			}
			return this.arrayQtdeEstoque;
		}

		public void setArrayCodigoSAP(String[] arrayCodigoSAP) {
			this.arrayCodigoSAP = arrayCodigoSAP;
		}

		public String[] getArrayCodigoSAP() {
			if (this.arrayCodigoSAP == null || this.arrayCodigoSAP.length == 0) {
				this.arrayCodigoSAP = new String[1];
			}
			return this.arrayCodigoSAP;
		}

		public void setArrayIdEstoque(String[] arrayIdEstoque) {
			this.arrayIdEstoque = arrayIdEstoque;
		}

		public String[] getArrayIdEstoque() {
			if (this.arrayIdEstoque == null || this.arrayIdEstoque.length == 0) {
				this.arrayIdEstoque = new String[1];
			}
			return this.arrayIdEstoque;
		}

		public void setLixoParcelas(String[] lixoParcelas) {
			this.lixoParcelas = lixoParcelas;
		}

		public String[] getLixoParcelas() {
			if (this.lixoParcelas == null || this.lixoParcelas.length == 0) {
				this.lixoParcelas = new String[1];
			}
			return this.lixoParcelas;
		}

		public void setLixoDescontos(String[] lixoDescontos) {
			this.lixoDescontos = lixoDescontos;
		}

		public String[] getLixoDescontos() {
			if (this.lixoDescontos == null || this.lixoDescontos.length == 0) {
				this.lixoDescontos = new String[1];
			}
			return this.lixoDescontos;
		}

		public void setArrayIdAparelhoCor(String[] arrayIdAparelhoCor) {
			this.arrayIdAparelhoCor = arrayIdAparelhoCor;
		}

		public String[] getArrayIdAparelhoCor() {
			if (this.arrayIdAparelhoCor == null || this.arrayIdAparelhoCor.length == 0) {
				this.arrayIdAparelhoCor = new String[1];
			}
			return this.arrayIdAparelhoCor;
		}

		public void setRegional(String regional) {
			this.regional = regional;
		}

		public String getRegional() {
			return this.regional;
		}

		public void setListaDDD(ListaDDDVO[] listaDDD) {
			this.listaDDD = listaDDD;
		}

		public ListaDDDVO[] getListaDDD() {
			return this.listaDDD;
		}

		public void setInChipAvulso(String inChipAvulso) {
			this.inChipAvulso = inChipAvulso;
		}

		public String getInChipAvulso() {
			if (this.inChipAvulso == null) {
				return ConstantesCRM.SZERO;
			} else {
				return this.inChipAvulso;
			}
		}

		public void setInChipPreProgramado(String inChipPreProgramado) {
			this.inChipPreProgramado = inChipPreProgramado;
		}

		public String getInChipPreProgramado() {
			if (this.inChipPreProgramado == null) {
				return ConstantesCRM.SZERO;
			} else {
				return this.inChipPreProgramado;
			}
		}
	}

	public static class AparelhoForm extends ActionForm {

		private static final long serialVersionUID = -8757915236350221938L;

		private FidelizacaoVO fidelizacaoVO = FidelizacaoVO.Factory.newInstance();
		private Disponivel listaRegional;
		private Disponivel listaClientes;
		private Disponivel listaLinhas;
		private Disponivel listaSegmentacao;
		private Disponivel listaGrupos;
		private Disponivel listaAparelhos;

		private Selecionado listaSelRegional;
		private Selecionado listaSelClientes;
		private Selecionado listaSelLinhas;
		private Selecionado listaSelSegmentacao;
		private Selecionado listaSelGrupos;
		private Selecionado listaSelAparelhos;

		private String[] idSelRegional = new String[0];
		private String[] idSelTpCliente = new String[0];
		private String[] idSelTpLinha = new String[0];
		private String[] idSelSegmentcao = new String[0];
		private String[] idSelGrupos = new String[0];
		private String[] idSelAparelhos = new String[0];

		public FidelizacaoVO getFidelizacaoVO() {
			return this.fidelizacaoVO;
		}

		public void setFidelizacaoVO(FidelizacaoVO fidelizacaoVO) {
			this.fidelizacaoVO = fidelizacaoVO;
		}

		public void setListaRegional(Disponivel listaRegional) {
			this.listaRegional = listaRegional;
		}

		public Disponivel getListaRegional() {
			return this.listaRegional;
		}

		public void setListaClientes(Disponivel listaClientes) {
			this.listaClientes = listaClientes;
		}

		public Disponivel getListaClientes() {
			return this.listaClientes;
		}

		public void setListaLinhas(Disponivel listaLinhas) {
			this.listaLinhas = listaLinhas;
		}

		public Disponivel getListaLinhas() {
			return this.listaLinhas;
		}

		public void setListaSegmentacao(Disponivel listaSegmentacao) {
			this.listaSegmentacao = listaSegmentacao;
		}

		public Disponivel getListaSegmentacao() {
			return this.listaSegmentacao;
		}

		public void setListaGrupos(Disponivel listaGrupos) {
			this.listaGrupos = listaGrupos;
		}

		public Disponivel getListaGrupos() {
			return this.listaGrupos;
		}

		public void setListaAparelhos(Disponivel listaAparelhos) {
			this.listaAparelhos = listaAparelhos;
		}

		public Disponivel getListaAparelhos() {
			return this.listaAparelhos;
		}

		public void setListaSelRegional(Selecionado listaSelRegional) {
			this.listaSelRegional = listaSelRegional;
		}

		public Selecionado getListaSelRegional() {
			return this.listaSelRegional;
		}

		public void setListaSelClientes(Selecionado listaSelClientes) {
			this.listaSelClientes = listaSelClientes;
		}

		public Selecionado getListaSelClientes() {
			return this.listaSelClientes;
		}

		public void setListaSelLinhas(Selecionado listaSelLinhas) {
			this.listaSelLinhas = listaSelLinhas;
		}

		public Selecionado getListaSelLinhas() {
			return this.listaSelLinhas;
		}

		public void setListaSelSegmentacao(Selecionado listaSelSegmentacao) {
			this.listaSelSegmentacao = listaSelSegmentacao;
		}

		public Selecionado getListaSelSegmentacao() {
			return this.listaSelSegmentacao;
		}

		public void setListaSelGrupos(Selecionado listaSelGrupos) {
			this.listaSelGrupos = listaSelGrupos;
		}

		public Selecionado getListaSelGrupos() {
			return this.listaSelGrupos;
		}

		public void setListaSelAparelhos(Selecionado listaSelAparelhos) {
			this.listaSelAparelhos = listaSelAparelhos;
		}

		public Selecionado getListaSelAparelhos() {
			return this.listaSelAparelhos;
		}

		public void setIdSelRegional(String[] idSelRegional) {
			this.idSelRegional = idSelRegional;
		}

		public String[] getIdSelRegional() {
			return this.idSelRegional;
		}

		public void setIdSelTpCliente(String[] idSelTpCliente) {
			this.idSelTpCliente = idSelTpCliente;
		}

		public String[] getIdSelTpCliente() {
			return this.idSelTpCliente;
		}

		public void setIdSelTpLinha(String[] idSelTpLinha) {
			this.idSelTpLinha = idSelTpLinha;
		}

		public String[] getIdSelTpLinha() {
			return this.idSelTpLinha;
		}

		public void setIdSelSegmentacao(String[] idSelSegmentcao) {
			this.idSelSegmentcao = idSelSegmentcao;
		}

		public String[] getIdSelSegmentacao() {
			return this.idSelSegmentcao;
		}

		public void setIdSelGrupos(String[] idSelGrupos) {
			this.idSelGrupos = idSelGrupos;
		}

		public String[] getIdSelGrupos() {
			return this.idSelGrupos;
		}

		public void setIdSelAparelhos(String[] idSelAparelhos) {
			this.idSelAparelhos = idSelAparelhos;
		}

		public String[] getIdSelAparelhos() {
			return this.idSelAparelhos;
		}
	}
}
