package fidelizacao.Configurar.ConfigurarMatrizOferta;

import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.commons.utils.StringUtil;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.Fidelizacao;
import br.com.vivo.fo.ctrls.fidelizacao.configurar.MatrizOfertaFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Selecionado;
import br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoXMLINMatrizOfertasVODocument.RetencaoXMLINMatrizOfertasVO;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"rawtypes","unchecked","deprecation","unused"})
public class ConfigurarMatrizOfertaController extends AbstractAction {

	private static final long serialVersionUID = 5224279082159220892L;

	@EJB
	public MatrizOfertaFacade matrizOfertaFac;

	@EJB
	public Fidelizacao fidelizacao;

	private MatrizOfertaForm matrizForm = new MatrizOfertaForm();
	private OfertaForm ofertaForm = new OfertaForm();

	private transient Logger log = new Logger("Fidelizacao");

	private ArrayListaGeralVO arrayListaGeralVO = null;
	public HashMap regional = new HashMap();
	public HashMap segmentacao = new HashMap();
	public HashMap tipoCliente = new HashMap();
	public HashMap intencao = new HashMap();
	public HashMap destino = new HashMap();
	public FidelizacaoListaGeralVO listaGrupo;

	public FidelizacaoListaGeralVO listaTemp = null;
	public ArrayListaGeralVO array = null;

	private boolean editar = false;

	protected global.Global globalApp = new global.Global();

	public MatrizOfertaForm getMatrizForm() {
		return (this.matrizForm);
	}

	public void setMatrizForm(MatrizOfertaForm form) {
		this.matrizForm = form;
	}

	public OfertaForm getOfertaForm() {
		if (ofertaForm == null) {
			ofertaForm = new OfertaForm();
		}
		return this.ofertaForm;
	}

	public HashMap getRegional() {
		return this.regional;
	}

	public FidelizacaoListaGeralVO getListaGrupo() {
		return listaGrupo;
	}

	protected void initCombos(HttpServletRequest request) throws Exception {
		arrayListaGeralVO = matrizOfertaFac.getDadosIniciais(getIdUsuario(request));
		for (int i = 0; i < arrayListaGeralVO.getFidelizacaoListaGeralVOArray(0).getItemListaVOArray().length; i++) {
			regional.put(arrayListaGeralVO.getFidelizacaoListaGeralVOArray(0).getItemListaVOArray(i).getId(), arrayListaGeralVO.getFidelizacaoListaGeralVOArray(0).getItemListaVOArray(i)
					.getDescricao());
		}
		matrizForm.setRegionalArray(arrayListaGeralVO.getFidelizacaoListaGeralVOArray(0).getItemListaVOArray());
		for (int i = 0; i < arrayListaGeralVO.getFidelizacaoListaGeralVOArray(2).getItemListaVOArray().length; i++) {
			segmentacao.put(arrayListaGeralVO.getFidelizacaoListaGeralVOArray(2).getItemListaVOArray(i).getId(), arrayListaGeralVO.getFidelizacaoListaGeralVOArray(2).getItemListaVOArray(i)
					.getDescricao());
		}
		matrizForm.setSegmentacaoArray(arrayListaGeralVO.getFidelizacaoListaGeralVOArray(2).getItemListaVOArray());
		for (int i = 0; i < arrayListaGeralVO.getFidelizacaoListaGeralVOArray(1).getItemListaVOArray().length; i++) {
			tipoCliente.put(arrayListaGeralVO.getFidelizacaoListaGeralVOArray(1).getItemListaVOArray(i).getId(), arrayListaGeralVO.getFidelizacaoListaGeralVOArray(1).getItemListaVOArray(i)
					.getDescricao());
		}
		matrizForm.setTipoClienteArray(arrayListaGeralVO.getFidelizacaoListaGeralVOArray(1).getItemListaVOArray());
		for (int i = 0; i < arrayListaGeralVO.getFidelizacaoListaGeralVOArray(4).getItemListaVOArray().length; i++) {
			intencao.put(arrayListaGeralVO.getFidelizacaoListaGeralVOArray(4).getItemListaVOArray(i).getId(), arrayListaGeralVO.getFidelizacaoListaGeralVOArray(4).getItemListaVOArray(i)
					.getDescricao());
		}
		listaGrupo = arrayListaGeralVO.getFidelizacaoListaGeralVOArray(3);
		matrizForm.setIntencaoArray(arrayListaGeralVO.getFidelizacaoListaGeralVOArray(4).getItemListaVOArray());
		arrayListaGeralVO = null;
		ItemListaVO[] item = new ItemListaVO[0];
		matrizForm.setDestinoPrevistoArray(item);
	}

	protected String montaArvore(HttpServletRequest request) throws Exception {
		ItemArvoreVO item = matrizOfertaFac.getArvore(getIdUsuario(request));
		String script = "if (document.getElementById) {var tree = new WebFXTree('Matriz de Ofertas');tree.setBehavior('classic');";
		String corpoArvore = VerificaItemMatriz(item, "tree");
		script = script + corpoArvore + "document.write(tree);}";
		request.getSession().setAttribute("script", script);
		return script;
	}

	private String VerificaItemMatriz(ItemArvoreVO itens, String tree) {
		String node = ConstantesCRM.SVAZIO;
		if (itens.getItemArvoreVOArray().length == 0) {
			return ConstantesCRM.SVAZIO;
		} else {
			for (int x = 0; x < itens.getItemArvoreVOArray().length; x++) {
				String icon = "''";
				node = node + "var " + tree + String.valueOf(x) + " = new WebFXTreeItem('" + StringEscapeUtils.escapeJavaScript(itens.getItemArvoreVOArray(x).getDescricao()) + "','',''," + icon + ","
						+ icon + ");";
				node = node + VerificaItemMatriz(itens.getItemArvoreVOArray(x), tree + String.valueOf(x));
				node = node + tree + ".add(" + tree + String.valueOf(x) + ");\n";
			}
		}
		return node;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("abaFiltro".equals(mapping.getParameter())) {
			return abaFiltro(mapping, form, request, response);
		} else if ("pesquisarIntencao".equals(mapping.getParameter())) {
			return pesquisarIntencao(mapping, form, request, response);
		} else if ("pesquisarDestinos".equals(mapping.getParameter())) {
			return pesquisarDestinos(mapping, form, request, response);
		} else if ("pesquisarOfertas".equals(mapping.getParameter())) {
			return pesquisarOfertas(mapping, form, request, response);
		} else if ("gravarOfertas".equals(mapping.getParameter())) {
			return gravarOfertas(mapping, form, request, response);
		} else if ("montarArvore".equals(mapping.getParameter())) {
			return montarArvore(mapping, form, request, response);
		} else if ("ActionMatrizOferta".equals(mapping.getParameter())) {
			return ActionMatrizOferta(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configurarMatrizOferta.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.initCombos(request);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ConfigurarMatrizOfertaDone"
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
		OfertaForm form = (OfertaForm) formParam;
		try {
			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
			xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
			xml.getFiltro().getCombos().addNmSelect("TPLINHA");
			xml.getFiltro().getCombos().addNmSelect("GRUPOS");
			xml.getFiltro().getCombos().addNmSelect("SEGMENTACAO");

			FidelizacaoVO listas = fidelizacao.getListas(getIdUsuario(request), xml);
			getOfertaForm().setFidelizacaoVO(listas);
			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getOfertaForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getOfertaForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getOfertaForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("GRUPOS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getOfertaForm().setListaGrupos(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getOfertaForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}
			getOfertaForm().setListaSelRegional(Selecionado.Factory.newInstance());
			getOfertaForm().setListaSelClientes(Selecionado.Factory.newInstance());
			getOfertaForm().setListaSelLinhas(Selecionado.Factory.newInstance());
			getOfertaForm().setListaSelGrupos(Selecionado.Factory.newInstance());
			getOfertaForm().setListaSelSegmentacao(Selecionado.Factory.newInstance());
		} catch (Exception e) {
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configOfertas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward pesquisarIntencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		OfertaForm form = (OfertaForm) formParam;
		try {
			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("CFGOFERTAS");
			xml.setTpProcesso("PSQ");

			getOfertaForm().setIdIntencao(ConstantesCRM.SVAZIO);
			getOfertaForm().setInFidelizacao(ConstantesCRM.SZERO);

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

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("TPLINHA");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelTpLinha().length; i++) {
				selecao.addNewIt().setId(form.getIdSelTpLinha()[i]);
			}

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
			getOfertaForm().setFidelizacaoVO(xml);

			FidelizacaoVO listas = fidelizacao.getListas(getIdUsuario(request), xml);

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("INTENCAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getOfertaForm().setListaIntencao(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}

			if (getOfertaForm().getListaIntencao() == null) {
				getOfertaForm().setListaIntencao(Disponivel.Factory.newInstance());
			}

			getOfertaForm().setListaDestinos(Disponivel.Factory.newInstance());
			getOfertaForm().setListaSelDestinos(Selecionado.Factory.newInstance());
			getOfertaForm().setListaOfertas(Disponivel.Factory.newInstance());
			getOfertaForm().setListaSelOfertas(Selecionado.Factory.newInstance());

		} catch (Exception e) {
			log.error("pesquisarIntencao()::" + e.getMessage(), e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configOfertas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward pesquisarDestinos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		OfertaForm form = (OfertaForm) formParam;
		try {
			limparComboDestinosSelecionados();
			FidelizacaoVO xml = getOfertaForm().getFidelizacaoVO();
			xml.setNmProcesso("CFGOFERTAS");
			xml.setTpProcesso("DST");

			String inFidelizacao = ConstantesCRM.SONE.equals(form.getInFidelizacao()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO;
			if (xml.getManter() == null) {
				xml.addNewManter();
			}
			xml.getManter().setIdIntencao(form.getIdIntencao());
			xml.getManter().setInFidelizacao(inFidelizacao);

			getOfertaForm().setIdIntencao(form.getIdIntencao());
			getOfertaForm().setInFidelizacao(inFidelizacao);

			getOfertaForm().getFidelizacaoVO().getManter().setIdIntencao(form.getIdIntencao());
			getOfertaForm().getFidelizacaoVO().getManter().setInFidelizacao(inFidelizacao);

			FidelizacaoVO listas = fidelizacao.getListas(getIdUsuario(request), xml);

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("DESTINOS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getOfertaForm().setListaDestinos(listas.getListasVO().getListaArray(i).getDisponivel());
					getOfertaForm().setListaSelDestinos(listas.getListasVO().getListaArray(i).getSelecionado());
				}
			}
			if (getOfertaForm().getListaDestinos() == null) {
				getOfertaForm().setListaDestinos(Disponivel.Factory.newInstance());
			}
			if (getOfertaForm().getListaSelDestinos() == null) {
				getOfertaForm().setListaSelDestinos(Selecionado.Factory.newInstance());
			}

		} catch (Exception e) {
			log.error("pesquisarDestinos()::" + e.getMessage(), e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configOfertas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward pesquisarOfertas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		OfertaForm form = (OfertaForm) formParam;
		try {
			limparComboDestinosSelecionados();
			FidelizacaoVO xml = getOfertaForm().getFidelizacaoVO();
			xml.setNmProcesso("CFGOFERTAS");
			xml.setTpProcesso("CON");

			Lista lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("DESTINOS");
			Selecionado selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelDestinos().length; i++) {
				selecao.addNewIt().setId(form.getIdSelDestinos()[i]);
			}

			getOfertaForm().setFidelizacaoVO(xml);

			FidelizacaoVO listas = fidelizacao.getListas(getIdUsuario(request), xml);

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("OFERTAS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getOfertaForm().setListaOfertas(listas.getListasVO().getListaArray(i).getDisponivel());
					getOfertaForm().setListaSelOfertas(listas.getListasVO().getListaArray(i).getSelecionado());
				}
			}
			if (getOfertaForm().getListaOfertas() == null) {
				getOfertaForm().setListaOfertas(Disponivel.Factory.newInstance());
			}
			if (getOfertaForm().getListaSelOfertas() == null) {
				getOfertaForm().setListaSelOfertas(Selecionado.Factory.newInstance());
			}
			trataComboDestinosSelecionados();
		} catch (Exception e) {
			log.error("pesquisarOfertas()::" + e.getMessage(), e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaFiltro.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward gravarOfertas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		OfertaForm form = (OfertaForm) formParam;
		try {
			FidelizacaoVO xml = getOfertaForm().getFidelizacaoVO();
			xml.setNmProcesso("CFGOFERTAS");
			xml.setTpProcesso("ALT");

			Lista lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("OFERTAS");
			Selecionado selecao = lista.addNewSelecionado();

			FidelizacaoVO conf = FidelizacaoVO.Factory.newInstance();
			int processId = (int) (Math.random() * 10000) + (int) (Math.random() * 10000);
			xml.setCodError(String.valueOf(processId));
			xml.setMsgError(String.valueOf(form.getIdSelOfertas().length));

			if (form.getIdSelOfertas().length == 0) {
				conf = fidelizacao.setParam(getIdUsuario(request), xml);
			} else {
				for (int i = 0; i < form.getIdSelOfertas().length; i++) {
					selecao.addNewIt().setId(form.getIdSelOfertas()[i]);
					conf = fidelizacao.setParam(getIdUsuario(request), xml);
					int tam = selecao.getItArray().length;
					for (int x = 0; x < tam; x++) {
						selecao.removeIt(0);
					}
				}
			}
		} catch (Exception e) {
			log.error("gravarOfertas()::" + e.getMessage(), e);
			request.setAttribute("msgError", "Erro ao gravar a Matriz de Ofertas: \\n" + e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void trataComboDestinosSelecionados() {
		if (getOfertaForm().getListaSelDestinos() == null) {
			getOfertaForm().setListaSelDestinos(Selecionado.Factory.newInstance());
		}
		for (int i = 0; i < getOfertaForm().getFidelizacaoVO().getListasVO().getListaArray().length; i++) {
			if ("DESTINOS".equals(getOfertaForm().getFidelizacaoVO().getListasVO().getListaArray(i).getNmSelect())) {
				Selecionado selecionado = getOfertaForm().getFidelizacaoVO().getListasVO().getListaArray(i).getSelecionado();
				for (int j = 0; j < selecionado.getItArray().length; j++) {
					for (int x = 0; x < getOfertaForm().getListaDestinos().getItArray().length; x++) {
						if (selecionado.getItArray(j).getId().equals(getOfertaForm().getListaDestinos().getItArray(x).getId())) {
							Selecionado listaSel = getOfertaForm().getListaSelDestinos();
							int pos = listaSel.getItArray().length;
							listaSel.insertNewIt(pos);
							listaSel.setItArray(pos, getOfertaForm().getListaDestinos().getItArray(x));
						}
					}
				}
				break;
			}
		}
		for (int j = 0; j < getOfertaForm().getListaSelDestinos().getItArray().length; j++) {
			String id = getOfertaForm().getListaSelDestinos().getItArray(j).getId();
			for (int i = 0; i < getOfertaForm().getListaDestinos().getItArray().length; i++) {
				if (id.equals(getOfertaForm().getListaDestinos().getItArray(i).getId())) {
					getOfertaForm().getListaDestinos().removeIt(i);
					break;
				}
			}
		}
	}

	private void limparComboDestinosSelecionados() {
		for (int i = 0; i < getOfertaForm().getFidelizacaoVO().getListasVO().getListaArray().length; i++) {
			if ("DESTINOS".equals(getOfertaForm().getFidelizacaoVO().getListasVO().getListaArray(i).getNmSelect())) {
				getOfertaForm().getFidelizacaoVO().getListasVO().removeLista(i);
			}
		}
		getOfertaForm().setListaSelDestinos(Selecionado.Factory.newInstance());
	}

	private String idUsuario;

	private String getIdUsuario(HttpServletRequest request) {
		if (idUsuario == null) {
			idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		}
		return idUsuario;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultaMatrizOfertaCadastrada.jsp"
	 * @jpf:forward name="success1" path="iframeArvore.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	private ActionForward montarArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			ItemArvoreVO itemArvoreVO = matrizOfertaFac.getArvoreMatriz(getIdUsuario(request), getIdPaiXMLParam(request));
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
		StringBuffer xmlParam = new StringBuffer(""); // variável para montar a
														// String XML.
		String[] splitIdPai = request.getParameter("idPai") != null && !request.getParameter("idPai").equals("") ? request.getParameter("idPai").split("X") // separa
				// os
				// ids
				// que
				// veem
				// do
				// request.
				: null; // retorna null caso não exista ids na url.
		String[] tagsIdPai = new String[] { "<idUF>#</idUF>", "<idGrupo>#</idGrupo>", "<idTipoPessoa>#</idTipoPessoa>", "<idTipoLinha>#</idTipoLinha>", "<idSegmentacao>#</idSegmentacao>",
				"<idIntencao>#</idIntencao>", "<idDestino>#</idDestino>" }; // templates
		// para
		// mosntar
		// a
		// String
		// XML.
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
		String[] iconesCl = new String[] { "/FrontOfficeWeb/resources/images/foldericon.png", // array
																								// com
																								// icones
				// fechados
				"/FrontOfficeWeb/resources/images/file.png" };
		String[] iconesOp = new String[] { "/FrontOfficeWeb/resources/images/openfoldericon.png", // array
																									// com
																									// icones
				// abertos
				"/FrontOfficeWeb/resources/images/file.png" };
		String idPai = request.getParameter("idPai") != null && !request.getParameter("idPai").equals(ConstantesCRM.SVAZIO) ? request.getParameter("idPai") + "X" // recupera
				// id
				// dos
				// Pais
				// do
				// nó
		: ConstantesCRM.SVAZIO;
		// variáveis auxiliares para montar o scriptJS
		int icone = 0;
		String parent = ConstantesCRM.SVAZIO;
		String getSelected = ConstantesCRM.SVAZIO;
		String finalizaJS = ConstantesCRM.SVAZIO;
		String function = ConstantesCRM.SVAZIO;
		int nivel = 0;

		if (itemArvore != null && itemArvore.length > 0) {
			nivel = itemArvore[0].getNivel(); // recupera o nível dos nós na
												// árvore
			icone = nivel < 8 ? 0 : 1; // recupera o ícone específico para o nó
			function = nivel < 8 ? "expandirNo" : "none"; // recupera a função
															// JS que será
															// executada quando
															// o usuário
			// clicar no nó
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

			scriptJS.append(nivel == 1 ? "if (document.getElementById) { var treeAparelhos = new WebFXTree('Matriz de Ofertas');\n" // se
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
						.append("'','" + iconesCl[icone] + "','" + iconesOp[icone] + "');\n") // informa
																								// os
																								// icones
																								// que
						// representarão o item
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
		iconesCl = null;
		iconesOp = null;
		idPai = null;
		parent = null;
		getSelected = null;
		finalizaJS = null;
		function = null;
		return scriptJS.toString(); // retorna o script para montar a árvore
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configurarMatrizOferta.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ActionMatrizOferta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		MatrizOfertaForm form = (MatrizOfertaForm) formParam;
		try {
			matrizForm = form;
			ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("pesquisarDestino")) {
				try {
					RetencaoXMLINMatrizOfertasVO matrizOfertasVO = RetencaoXMLINMatrizOfertasVO.Factory.newInstance();
					matrizOfertasVO.setIdUfOperadora(form.getRegionalSel());
					matrizOfertasVO.setIdTipoPessoa(form.getTipoClienteSel());
					matrizOfertasVO.setIdIntencao(form.getIntencaoSel());
					matrizOfertasVO.setIdGrupo(form.getIdGrupo());
					matrizOfertasVO.setInFidelizacao(validarBoolean(form.getInFidelizacao()));
					matrizOfertasVO.setInConfiguracao(ConstantesCRM.SONE);
					matrizOfertasVO.addNewSegmentacao();
					// matrizOfertasVO.setIdTipoLinha(parametros.getIdTipoLinha());

					for (int s = 0; s < form.getSegmentacaoSel().length; s++) {
						matrizOfertasVO.getSegmentacao().addIdSegmentacao(form.getSegmentacaoSel()[s]);
					}
					listaTemp = matrizOfertaFac.getDestino(getIdUsuario(request), matrizOfertasVO);

					initCombos(request);
					for (int i = 0; i < listaTemp.getItemListaVOArray().length; i++) {
						destino.put(listaTemp.getItemListaVOArray(i).getId(), listaTemp.getItemListaVOArray(i).getDescricao());
					}
					matrizForm.setDestinoPrevistoArray(listaTemp.getItemListaVOArray());

				} catch (Exception e) {
					String s = e.getMessage();
					throw e;
				}
			} else if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("pesquisarOfertas")) {
				initCombos(request);
				RetencaoXMLINMatrizOfertasVO matrizOfertasVO = RetencaoXMLINMatrizOfertasVO.Factory.newInstance();
				matrizOfertasVO.setIdUfOperadora(form.getRegionalSel());
				matrizOfertasVO.setIdTipoPessoa(form.getTipoClienteSel());
				matrizOfertasVO.setIdIntencao(form.getIntencaoSel());
				matrizOfertasVO.setIdGrupo(form.getIdGrupo());
				matrizOfertasVO.setInConfiguracao(ConstantesCRM.SONE);
				matrizOfertasVO.setInFidelizacao(validarBoolean(form.getInFidelizacao()));
				matrizOfertasVO.addNewSegmentacao();
				matrizOfertasVO.addNewDestinoprevisto();
				// matrizOfertasVO.setIdTipoLinha(parametros.getIdTipoLinha());

				for (int s = 0; s < form.getSegmentacaoSel().length; s++) {
					matrizOfertasVO.getSegmentacao().addIdSegmentacao(form.getSegmentacaoSel()[s]);
				}

				for (int d = 0; d < form.getDestinoSel().length; d++) {
					matrizOfertasVO.getDestinoprevisto().addIdDestino(form.getDestinoSel()[d]);
				}

				try {
					listaTemp = matrizOfertaFac.getDestino(getIdUsuario(request), matrizOfertasVO);
					initCombos(request);
					for (int i = 0; i < listaTemp.getItemListaVOArray().length; i++) {
						destino.put(listaTemp.getItemListaVOArray(i).getId(), listaTemp.getItemListaVOArray(i).getDescricao());
					}
					matrizForm.setDestinoPrevistoArray(listaTemp.getItemListaVOArray());

					array = matrizOfertaFac.getOfertas(getIdUsuario(request), matrizOfertasVO);
					getMatrizForm().setOfertasDisp(array.getFidelizacaoListaGeralVOArray(0).getItemListaVOArray());
					getMatrizForm().setOfertasSel(array.getFidelizacaoListaGeralVOArray(1).getItemListaVOArray());

				} catch (Exception e) {
					String s = e.getMessage();
					throw e;
				}
				editar = true;

			} else if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("salvar")) {
				RetencaoXMLINMatrizOfertasVO matrizOfertasVO = RetencaoXMLINMatrizOfertasVO.Factory.newInstance();
				matrizOfertasVO.setIdUfOperadora(form.getRegionalSel());
				matrizOfertasVO.setIdTipoPessoa(form.getTipoClienteSel());
				matrizOfertasVO.setIdIntencao(form.getIntencaoSel());
				matrizOfertasVO.setIdGrupo(form.getIdGrupo());
				matrizOfertasVO.setInFidelizacao(validarBoolean(form.getInFidelizacao()));
				matrizOfertasVO.addNewSegmentacao();
				matrizOfertasVO.addNewDestinoprevisto();
				matrizOfertasVO.addNewOferta();

				for (int s = 0; s < form.getSegmentacaoSel().length; s++) {
					matrizOfertasVO.getSegmentacao().addIdSegmentacao(form.getSegmentacaoSel()[s]);
				}
				for (int d = 0; d < form.getDestinoSel().length; d++) {
					matrizOfertasVO.getDestinoprevisto().addIdDestino(form.getDestinoSel()[d]);
				}
				if (form.getOfertasSelecionadasSel() != null) {
					for (int o = 0; o < form.getOfertasSelecionadasSel().length; o++) {
						matrizOfertasVO.getOferta().addIdOferta(form.getOfertasSelecionadasSel()[o]);
					}
				}
				matrizOfertasVO.setQtSegmentacao(String.valueOf(matrizOfertasVO.getSegmentacao().sizeOfIdSegmentacaoArray()));
				matrizOfertasVO.setQtDestino(String.valueOf(matrizOfertasVO.getDestinoprevisto().sizeOfIdDestinoArray()));
				matrizOfertasVO.setQtOfertas(String.valueOf(matrizOfertasVO.getOferta().sizeOfIdOfertaArray()));
				if (editar == false) {
					try {
						initCombos(request);
						for (int i = 0; i < listaTemp.getItemListaVOArray().length; i++) {
							destino.put(listaTemp.getItemListaVOArray(i).getId(), listaTemp.getItemListaVOArray(i).getDescricao());
						}
						matrizForm.setDestinoPrevistoArray(listaTemp.getItemListaVOArray());
						matrizOfertaFac.addOferta(getIdUsuario(request), matrizOfertasVO);
					} catch (Exception e) {
						String s = e.getMessage();
						throw e;
					}
				} else {
					try {
						initCombos(request);
						for (int i = 0; i < listaTemp.getItemListaVOArray().length; i++) {
							destino.put(listaTemp.getItemListaVOArray(i).getId(), listaTemp.getItemListaVOArray(i).getDescricao());
						}
						matrizForm.setDestinoPrevistoArray(listaTemp.getItemListaVOArray());
						// Alteração de Emergencia para a chamada do serviço a
						// cada segmentação
						for (int s = 0; s < form.getSegmentacaoSel().length; s++) {
							RetencaoXMLINMatrizOfertasVO matrizOfertasVOTEMP = processaSalvarMatrizOferta(matrizOfertasVO);
							matrizOfertasVOTEMP.getSegmentacao().addIdSegmentacao(form.getSegmentacaoSel()[s]);
							// matrizOfertaFac.setOferta(getIdUsuario(request)
							// ,matrizOfertasVO);
							matrizOfertaFac.setOferta(getIdUsuario(request), matrizOfertasVOTEMP);
						}
						// Fim - Alteração Emergencia
						editar = false;
					} catch (Exception e) {
						String s = e.getMessage();
						throw e;
					}
				}
				// Pesquisando novamente os dados
				matrizOfertasVO = RetencaoXMLINMatrizOfertasVO.Factory.newInstance();
				matrizOfertasVO.setIdUfOperadora(form.getRegionalSel());
				matrizOfertasVO.setIdTipoPessoa(form.getTipoClienteSel());
				matrizOfertasVO.setIdIntencao(form.getIntencaoSel());
				matrizOfertasVO.setIdGrupo(form.getIdGrupo());
				matrizOfertasVO.setInFidelizacao(validarBoolean(form.getInFidelizacao()));
				matrizOfertasVO.addNewSegmentacao();
				matrizOfertasVO.addNewDestinoprevisto();
				for (int s = 0; s < form.getSegmentacaoSel().length; s++) {
					matrizOfertasVO.getSegmentacao().addIdSegmentacao(form.getSegmentacaoSel()[s]);
				}
				for (int d = 0; d < form.getDestinoSel().length; d++) {
					matrizOfertasVO.getDestinoprevisto().addIdDestino(form.getDestinoSel()[d]);
				}
				try {
					array = matrizOfertaFac.getOfertas(getIdUsuario(request), matrizOfertasVO);
					getMatrizForm().setOfertasDisp(array.getFidelizacaoListaGeralVOArray(0).getItemListaVOArray());
					getMatrizForm().setOfertasSel(array.getFidelizacaoListaGeralVOArray(1).getItemListaVOArray());
				} catch (Exception e) {
					String s = e.getMessage();
					throw e;
				}
				editar = true;
			}
		} catch (TuxedoWarningException twe) {
			log.warn("ConfigurarMatrizOfertaController:ActionMatrizOferta(" + twe.getMessage() + ")");

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private RetencaoXMLINMatrizOfertasVO processaSalvarMatrizOferta(RetencaoXMLINMatrizOfertasVO matrizOfertasVO) {
		RetencaoXMLINMatrizOfertasVO matrizOfertasVOTEMP = RetencaoXMLINMatrizOfertasVO.Factory.newInstance();
		matrizOfertasVOTEMP.setIdUfOperadora(matrizOfertasVO.getIdUfOperadora());
		matrizOfertasVOTEMP.setIdTipoPessoa(matrizOfertasVO.getIdTipoPessoa());
		matrizOfertasVOTEMP.setIdIntencao(matrizOfertasVO.getIdIntencao());
		matrizOfertasVOTEMP.setIdGrupo(matrizOfertasVO.getIdGrupo());
		matrizOfertasVOTEMP.setInFidelizacao(matrizOfertasVO.getInFidelizacao());
		matrizOfertasVOTEMP.setDestinoprevisto(matrizOfertasVO.getDestinoprevisto());
		matrizOfertasVOTEMP.setOferta(matrizOfertasVO.getOferta());
		matrizOfertasVOTEMP.setQtSegmentacao(ConstantesCRM.SONE);
		matrizOfertasVOTEMP.setQtDestino(String.valueOf(matrizOfertasVO.getDestinoprevisto().sizeOfIdDestinoArray()));
		matrizOfertasVOTEMP.setQtOfertas(String.valueOf(matrizOfertasVO.getOferta().sizeOfIdOfertaArray()));
		matrizOfertasVOTEMP.addNewSegmentacao();
		return matrizOfertasVOTEMP;
	}

	private String validarBoolean(boolean valor) {
		return valor ? ConstantesCRM.SONE : ConstantesCRM.SZERO;
	}

	public static class MatrizOfertaForm extends ActionForm {

		private static final long serialVersionUID = -7009854891970481551L;

		private br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] destinoPrevistoArray;
		private ItemListaVODocument destinoPrevisto;
		private br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] intencaoArray;
		private ItemListaVODocument intencao;
		private ItemListaVO[] segmentacaoArray;
		private ItemListaVODocument segmentacao;
		private ItemListaVO[] tipoClienteArray;
		private ItemListaVODocument tipoCliente;
		private ItemListaVO[] regionalArray;
		private ItemListaVODocument regional;
		private String[] ofertaDispSel;
		private String[] ofertaSel;
		private String[] ofertasSelecionadasSel;
		private String[] destinoSel;
		private String intencaoSel;
		private String tipoClienteSel;
		private String[] segmentacaoSel;
		private String regionalSel;
		private String idGrupo;
		private boolean inFidelizacao;
		private ItemListaVO[] ofertasDisp = new ItemListaVO[0];
		private ItemListaVO[] ofertasSel = new ItemListaVO[0];

		public String getIdGrupo() {
			return this.idGrupo;
		}

		public void setIdGrupo(String idGrupo) {
			this.idGrupo = idGrupo;
		}

		public boolean getInFidelizacao() {
			return this.inFidelizacao;
		}

		public void setInFidelizacao(boolean inFidelizacao) {
			this.inFidelizacao = inFidelizacao;
		}

		public void setOfertasDisp(ItemListaVO[] lista) {
			this.ofertasDisp = lista;
		}

		public ItemListaVO[] getOfertasDisp() {
			return this.ofertasDisp;
		}

		public void setOfertasSel(ItemListaVO[] lista) {
			this.ofertasSel = lista;
		}

		public ItemListaVO[] getOfertasSel() {
			return this.ofertasSel;
		}

		public void setRegionalSel(String regionalSel) {
			this.regionalSel = regionalSel;
		}

		public String getRegionalSel() {
			return this.regionalSel;
		}

		public void setSegmentacaoSel(String[] segmentacaoSel) {
			this.segmentacaoSel = segmentacaoSel;
		}

		public String[] getSegmentacaoSel() {
			return this.segmentacaoSel;
		}

		public void setTipoClienteSel(String tipoClienteSel) {
			this.tipoClienteSel = tipoClienteSel;
		}

		public String getTipoClienteSel() {
			return this.tipoClienteSel;
		}

		public void setIntencaoSel(String intencaoSel) {
			this.intencaoSel = intencaoSel;
		}

		public String getIntencaoSel() {
			return this.intencaoSel;
		}

		public void setDestinoSel(String[] destinoSel) {
			this.destinoSel = destinoSel;
		}

		public String[] getDestinoSel() {
			return this.destinoSel;
		}

		public void setOfertasSelecionadasSel(String[] ofertasSelecionadasSel) {
			this.ofertasSelecionadasSel = ofertasSelecionadasSel;
		}

		public String[] getOfertasSelecionadasSel() {
			return this.ofertasSelecionadasSel;
		}

		public void setOfertaSel(String[] ofertaSel) {
			this.ofertaSel = ofertaSel;
		}

		public String[] getOfertaSel() {
			return this.ofertaSel;
		}

		public void setOfertaDispSel(String[] ofertaDispSel) {
			this.ofertaDispSel = ofertaDispSel;
		}

		public String[] getOfertaDispSel() {
			if (this.ofertaDispSel == null || this.ofertaDispSel.length == 0) {
				this.ofertaDispSel = new String[1];
			}
			return this.ofertaDispSel;
		}

		public void setRegional(ItemListaVODocument regional) {
			this.regional = regional;
		}

		public ItemListaVODocument getRegional() {
			return this.regional;
		}

		public void setRegionalArray(ItemListaVO[] regionalArray) {
			this.regionalArray = regionalArray;
		}

		public ItemListaVO[] getRegionalArray() {
			return this.regionalArray;
		}

		public void setTipoCliente(ItemListaVODocument tipoCliente) {
			this.tipoCliente = tipoCliente;
		}

		public ItemListaVODocument getTipoCliente() {
			return this.tipoCliente;
		}

		public void setTipoClienteArray(ItemListaVO[] tipoClienteArray) {
			this.tipoClienteArray = tipoClienteArray;
		}

		public ItemListaVO[] getTipoClienteArray() {
			return this.tipoClienteArray;
		}

		public void setSegmentacao(ItemListaVODocument segmentacao) {
			this.segmentacao = segmentacao;
		}

		public ItemListaVODocument getSegmentacao() {
			return this.segmentacao;
		}

		public void setSegmentacaoArray(ItemListaVO[] segmentacaoArray) {
			this.segmentacaoArray = segmentacaoArray;
		}

		public ItemListaVO[] getSegmentacaoArray() {
			return this.segmentacaoArray;
		}

		public void setIntencao(ItemListaVODocument intencao) {
			this.intencao = intencao;
		}

		public ItemListaVODocument getIntencao() {
			return this.intencao;
		}

		public void setIntencaoArray(br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] intencaoArray) {
			this.intencaoArray = intencaoArray;
		}

		public br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] getIntencaoArray() {
			return this.intencaoArray;
		}

		public void setDestinoPrevisto(ItemListaVODocument destinoPrevisto) {
			this.destinoPrevisto = destinoPrevisto;
		}

		public ItemListaVODocument getDestinoPrevisto() {
			return this.destinoPrevisto;
		}

		public void setDestinoPrevistoArray(br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] destinoPrevistoArray) {
			this.destinoPrevistoArray = destinoPrevistoArray;
		}

		public br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] getDestinoPrevistoArray() {
			return this.destinoPrevistoArray;
		}
	}

	public static class OfertaForm extends ActionForm {

		private static final long serialVersionUID = 8803266580854688776L;

		private FidelizacaoVO fidelizacaoVO = FidelizacaoVO.Factory.newInstance();

		private String idIntencao = ConstantesCRM.SVAZIO;
		private String inFidelizacao = ConstantesCRM.SVAZIO;

		private Disponivel listaRegional;
		private Disponivel listaClientes;
		private Disponivel listaLinhas;
		private Disponivel listaSegmentacao;
		private Disponivel listaGrupos;
		private Disponivel listaIntencao;
		private Disponivel listaDestinos;
		private Disponivel listaOfertas;

		private Selecionado listaSelRegional;
		private Selecionado listaSelClientes;
		private Selecionado listaSelLinhas;
		private Selecionado listaSelSegmentacao;
		private Selecionado listaSelGrupos;
		private Selecionado listaSelDestinos;
		private Selecionado listaSelOfertas;

		private String[] idSelRegional = new String[0];
		private String[] idSelTpCliente = new String[0];
		private String[] idSelTpLinha = new String[0];
		private String[] idSelSegmentcao = new String[0];
		private String[] idSelGrupos = new String[0];
		private String[] idSelDestinos = new String[0];
		private String[] idSelOfertas = new String[0];

		public FidelizacaoVO getFidelizacaoVO() {
			return this.fidelizacaoVO;
		}

		public void setFidelizacaoVO(FidelizacaoVO fidelizacaoVO) {
			this.fidelizacaoVO = fidelizacaoVO;
		}

		public void setIdIntencao(String idIntencao) {
			this.idIntencao = idIntencao;
		}

		public String getIdIntencao() {
			return this.idIntencao;
		}

		public void setInFidelizacao(String inFidelizacao) {
			this.inFidelizacao = inFidelizacao;
		}

		public String getInFidelizacao() {
			return this.inFidelizacao;
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

		public void setListaIntencao(Disponivel listaIntencao) {
			this.listaIntencao = listaIntencao;
		}

		public Disponivel getListaIntencao() {
			return this.listaIntencao;
		}

		public void setListaOfertas(Disponivel listaOfertas) {
			this.listaOfertas = listaOfertas;
		}

		public Disponivel getListaOfertas() {
			return this.listaOfertas;
		}

		public void setListaDestinos(Disponivel listaDestinos) {
			this.listaDestinos = listaDestinos;
		}

		public Disponivel getListaDestinos() {
			return this.listaDestinos;
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

		public void setListaSelDestinos(Selecionado listaSelDestinos) {
			this.listaSelDestinos = listaSelDestinos;
		}

		public Selecionado getListaSelDestinos() {
			return this.listaSelDestinos;
		}

		public void setListaSelOfertas(Selecionado listaSelOfertas) {
			this.listaSelOfertas = listaSelOfertas;
		}

		public Selecionado getListaSelOfertas() {
			return this.listaSelOfertas;
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

		public void setIdSelDestinos(String[] idSelDestinos) {
			this.idSelDestinos = idSelDestinos;
		}

		public String[] getIdSelDestinos() {
			return this.idSelDestinos;
		}

		public void setIdSelOfertas(String[] idSelOfertas) {
			this.idSelOfertas = idSelOfertas;
		}

		public String[] getIdSelOfertas() {
			return this.idSelOfertas;
		}
	}
}
