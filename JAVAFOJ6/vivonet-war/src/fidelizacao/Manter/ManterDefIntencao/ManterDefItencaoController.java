package fidelizacao.Manter.ManterDefIntencao;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.manter.ManterDefinicaoFacade;
import br.com.vivo.fo.ctrls.fidelizacao.manter.ManterDefinicaoFacadeImpl;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterAlteraVODocument.FidelizacaoManterAlteraVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterDelVODocument.FidelizacaoManterDelVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterInVODocument.FidelizacaoManterInVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterVODocument.FidelizacaoManterVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterDefItencaoController extends AbstractAction {

	private static final long serialVersionUID = 2564515140008541L;

	@EJB
	private ManterDefinicaoFacade destinoFac = new ManterDefinicaoFacadeImpl();

	private static transient Logger log = new Logger("Fidelizacao");
	
	public ListaDefIntencaoForm listaDefIntencaoForm = new ListaDefIntencaoForm();

	public ListaDefIntencaoForm getListaDefIntencaoForm() {
		return listaDefIntencaoForm;
	}

	public void setListaDefIntencaoForm(ListaDefIntencaoForm listaDefIntencaoForm) {
		this.listaDefIntencaoForm = listaDefIntencaoForm;
	}

	private String idUsuario;
	private String operacao;
	protected FidelizacaoListaGeralVO listaIntencaoVO;
	protected String[] labels;

	/**
	 * Popula operacao;
	 */
	private void setOperacao(String operacao) {
		// inclui debug no log
		log.debug("ManterDefItencaoController:setOperacao");
		this.operacao = operacao != null && !operacao.equals(ConstantesCRM.SVAZIO) ? operacao : ConstantesCRM.SONE;
	}

	/**
	 * Recupera tipo de operacao [1 = intencao, 2 = destino]
	 * 
	 * @return String
	 */
	private String getOperacao() {
		// inclui debug no log
		log.debug("ManterDefItencaoController:getOperacao");
		if (this.operacao == null) {
			this.operacao = ConstantesCRM.SONE;
		}

		return this.operacao;
	}

	/**
	 * Recupera tipo de operacao para exclusao [0 = intencao, 1 = destino]
	 * 
	 * @return String
	 */
	private String getOperacaoExcluir() {
		// inclui debug no log
		log.debug("ManterDefItencaoController:getOperacaoExcluir");

		return ConstantesCRM.SONE.equals(this.operacao) ? ConstantesCRM.SZERO : ConstantesCRM.SONE;
	}

	/**
	 * Popula labels
	 */
	private void setLabels() {
		// inclui debug no log
		log.debug("ManterDefItencaoController:setLabels");
		this.labels = ConstantesCRM.SONE.equals(this.operacao) ? new String[] { "Intenção de Cancelamento", "Alterar Intenção", "Excluir Intenção" } : new String[] { "Destino Previsto", "Alterar Destino",
		"Excluir Destino" };
	}

	/**
	 * Recupera labels
	 * 
	 * @return String
	 */
	public String[] getLabels() {
		// inclui debug no log
		log.debug("ManterDefItencaoController:getLabels");
		if (this.labels == null) {
			setLabels();
		}
		return this.labels;
	}

	/**
	 * Recupera lista de intencoes
	 * 
	 * @return FidelizacaoListaGeralVO
	 */
	public FidelizacaoListaGeralVO getListaIntencaoVO() {
		// inclui debug no log
		log.debug("ManterDefItencaoController:getIdUsuario");
		if (this.listaIntencaoVO == null) {
			this.listaIntencaoVO = FidelizacaoListaGeralVO.Factory.newInstance();
		}
		return this.listaIntencaoVO;
	}

	/**
	 * Recupera id do usuario logado no sistema
	 * 
	 * @return String
	 */
	protected String getIdUsuario(HttpServletRequest request) {
		// inclui debug no log
		log.debug("ManterDefItencaoController:getIdUsuario");
		if (this.idUsuario == null) {
			this.idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		}
		return this.idUsuario;
	}

	/**
	 * popula lista de intencoes
	 */
	protected void buscarListaIntencao(String intencao, HttpServletRequest request) throws Exception {
		// inclui debug no log
		log.debug("ManterDefItencaoController:buscarListaIntencao");
		// recupera dados para efetuar busca de intencoes
		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		FidelizacaoManterVO fidelizacaoManterVO = FidelizacaoManterVO.Factory.newInstance();
		fidelizacaoManterVO.setTexto(intencao == null ? ConstantesCRM.SVAZIO : intencao);
		fidelizacaoManterVO.setIdperg(getOperacao());
		fidelizacaoManterVO.setIdTipoLinha(ConstantesCRM.SONE);
		try {
			// efetua busca e popula lista de intencoes
			this.listaIntencaoVO = destinoFac.getDescricao(getIdUsuario(request), fidelizacaoManterVO);
		} catch (Exception e) {
			// inclui erro no log
			log.error("Exception - ManterDefItencaoController:buscarListaIntencao", e);
			throw e;
		} finally {
			fidelizacaoManterVO = null;
		}
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("listarIntencoes".equals(mapping.getParameter())) {
			return listarIntencoes(mapping, form, request, response);
		} else if ("editarIntencao".equals(mapping.getParameter())) {
			return editarIntencao(mapping, form, request, response);
		} else if ("persistirIntencao".equals(mapping.getParameter())) {
			return persistirIntencao(mapping, form, request, response);
		} else if ("excluirIntencao".equals(mapping.getParameter())) {
			return excluirIntencao(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="defIntencao.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ListaDefIntencaoForm form = (ListaDefIntencaoForm) formParam;
		setOperacao(request.getParameter("operacao")); // recupera operacao 1 =
		// intencao 2 = destinos
		setLabels(); // atribui labels de acordo com a operacao
		this.listaIntencaoVO = FidelizacaoListaGeralVO.Factory.newInstance(); // zera lista de intencao

		request.setAttribute("actionForm", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ManterDefItencaoDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	// ppaula 06/11/04

	/**
	 * Carrega a lista de intencoes e apresenta na tela
	 * 
	 * @param form
	 *            ListaDefIntencaoForm
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="defIntencao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward listarIntencoes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ListaDefIntencaoForm form = (ListaDefIntencaoForm) formParam;
		// inclui debug no log
		log.debug("ManterDefItencaoController:listarIntencoes");
		try {
			// popula lista de intencoes / destinos
			buscarListaIntencao(form.getDescricao(), request);
			form.setDescricao(ConstantesCRM.SVAZIO);
		} catch (Exception e) {
			// inclui erro no log
			log.error("Exception - ManterDefItencaoController:listarIntencoes", e);
			// Observação: Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("actionForm", getListaIntencaoVO());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Edita uma intencao e carrega na tela a lista atualizada de intencoes
	 * 
	 * @param form
	 *            ListaDefIntencaoForm
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="defIntencao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward editarIntencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ListaDefIntencaoForm form = (ListaDefIntencaoForm) formParam;
		// inclui debug no log
		log.debug("ManterDefItencaoController:editarIntencao");
		// popula formulário com os dados da intencao/destino a ser editado
		form.setDescricao(request.getParameter("descricao"));
		form.setId(request.getParameter("id"));
		form.setIsEdit(true);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * Persisti na base de dados uma intencao [inclusao ou alteracao] e carrega
	 * na tela a lista atualizada de intencoes
	 * 
	 * @param form
	 *            ListaDefIntencaoForm
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="defIntencao.jsp"
	 * @jpf:forward name="success1" path="defIntencao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward persistirIntencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaDefIntencaoForm form = (ListaDefIntencaoForm) formParam;
		// inclui debug no log
		log.debug("ManterDefItencaoController:persistirIntencao");
		// instancia variaveis
		FidelizacaoManterAlteraVO fidelizacaoManterAlteraVO = null;
		FidelizacaoManterInVO fidelizacaoManterInVO = null;
		RetornoVO retornoVO = null;

		try {
			if (form.isEdit) {
				// altera intencao
				fidelizacaoManterAlteraVO = FidelizacaoManterAlteraVO.Factory.newInstance();
				fidelizacaoManterAlteraVO.setIdperg(getOperacao());
				fidelizacaoManterAlteraVO.setId(form.getId() != null ? form.getId() : ConstantesCRM.SVAZIO);
				fidelizacaoManterAlteraVO.setDescricao(form.getDescricao() != null ? form.getDescricao() : null);
				retornoVO = destinoFac.setDescricao(getIdUsuario(request), fidelizacaoManterAlteraVO);
			} else {
				// inclui intencao
				fidelizacaoManterInVO = FidelizacaoManterInVO.Factory.newInstance();
				fidelizacaoManterInVO.setIdperg(getOperacao());
				fidelizacaoManterInVO.setDescricao(form.getDescricao() != null ? form.getDescricao() : ConstantesCRM.SVAZIO);
				retornoVO = destinoFac.addDescricao(getIdUsuario(request), fidelizacaoManterInVO);
			}
			// verifica se o servico retornou que a intencao já existe na base
			if ("-1".equals(retornoVO.getValor())) {
				request.setAttribute("msgErro", retornoVO.getDescricao());
			}
		} catch (Exception e) {
			// inclui debug no log
			log.error("Exception - ManterDefItencaoController:persistirIntencao");
			// Observação: Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} finally {
			// limpa variaveis
			form.setIsEdit(false);
			form.setId(ConstantesCRM.SVAZIO);
			form.setDescricao(ConstantesCRM.SVAZIO);
			buscarListaIntencao(ConstantesCRM.SVAZIO, request);
			retornoVO = null;
			fidelizacaoManterAlteraVO = null;
			fidelizacaoManterInVO = null;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * Exclui uma intencao e carrega na tela a lista atualizada de intencoes
	 * 
	 * @param form
	 *            ListaDefIntencaoForm
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="defIntencao.jsp"
	 * @jpf:forward name="success1" path="defIntencao.jsp?acao=erroMatriz"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward excluirIntencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaDefIntencaoForm form = (ListaDefIntencaoForm) formParam;
		// inclui debug no log
		log.debug("ManterDefItencaoController:excluirIntencao");

		// instancia variaveis
		FidelizacaoManterDelVO fidelizacaoManterDelVO = null;
		RetornoVO retornoVO = null;

		try {

			// recupera dados para delecao
			fidelizacaoManterDelVO = FidelizacaoManterDelVO.Factory.newInstance();
			fidelizacaoManterDelVO.setId(request.getParameter("id"));
			fidelizacaoManterDelVO.setOperacao(getOperacaoExcluir());
			// efetua delecao
			retornoVO = destinoFac.delDescricao(getIdUsuario(request), fidelizacaoManterDelVO);
			// verifica se o tuxerdo retornou algum erro
			if ("-1".equals(retornoVO.getValor())) {
				request.setAttribute("msgErro", retornoVO.getDescricao());
			}
		} catch (Exception e) {
			// inclui debug no log
			log.error("Exception - ManterDefItencaoController:excluirIntencao");
			// Observação: Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} finally {
			// limpa variaveis
			buscarListaIntencao(ConstantesCRM.SVAZIO, request);
			fidelizacaoManterDelVO = null;
			retornoVO = null;
			form.setDescricao(ConstantesCRM.SVAZIO);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	// fim ppaula

	protected global.Global globalApp = new global.Global();

	protected boolean alwaysTrackPreviousPage() {
		return true;
	}

	protected boolean alwaysTrackPreviousAction() {
		return true;
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class ListaDefIntencaoForm extends ActionForm {
		private String inMsgRetorno;

		private String textoProcura;

		private String[] intencao;

		private String id;

		private String descricao;

		private boolean isEdit;

		public void setIsEdit(boolean edit) {
			this.isEdit = edit;
		}

		public boolean getIsEdit() {
			return this.isEdit;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return this.descricao;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setIntencao(String[] intencao) {
			this.intencao = intencao;
		}

		public String[] getIntencao() {

			this.intencao = new String[2];

			this.intencao[0] = this.id;
			this.intencao[1] = this.descricao;

			return this.intencao;
		}

		public void setInMsgRetorno(String inMsgRetorno) {
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno() {
			return this.inMsgRetorno;
		}
	}
}
