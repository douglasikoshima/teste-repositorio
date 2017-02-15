package campanha.Configurar.ConfigurarPrioridade;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.CampanhaExecScriptVODocument.CampanhaExecScriptVO;
import br.com.vivo.fo.campanha.vo.CampanhaPerguntaVODocument.CampanhaPerguntaVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ConfigurarPrioridadeController extends AbstractAction {

	private static final long serialVersionUID = 4258259675142154334L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.ApoioFacade apoioFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.questionario.QuestionarioCampanhaFacade questFacade;

	private ConfiguraPrioridadeActionForm configuraPrioridadeActionForm = null;
	private int idCampanha = 0;

	private int idSubCampanha = 0;

	public ConfiguraPrioridadeActionForm getConfiguraPrioridadeActionForm() {
		if (this.configuraPrioridadeActionForm == null) {
			this.configuraPrioridadeActionForm = new ConfiguraPrioridadeActionForm();
		}
		return this.configuraPrioridadeActionForm;
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("ConfiguraPrioridadeAction".equals(mapping.getParameter())) {
			return ConfiguraPrioridadeAction(mapping, form, request, response);
		} else if ("ObtemListaAction".equals(mapping.getParameter())) {
			return ObtemListaAction(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="ConfigurarPrioridade.jsp"
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("valorDiv", "none");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		loadPage(request);
		getConfiguraPrioridadeActionForm().setIdSubCampanha(this.idSubCampanha);
		getConfiguraPrioridadeActionForm().setIdSubCampanhaFixa(this.idCampanha);
		getConfiguraPrioridadeActionForm().setIdAcao(0);

		GrupoCampanhaApoioVO grupoSubCampanhaApoio = apoioFacade.getApoioCanalCampanha(user, String.valueOf(idSubCampanha), 0);

		getConfiguraPrioridadeActionForm().setListaCanal(grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void loadPage(HttpServletRequest request) throws Exception {
		String _idCampanha = request.getParameter("idCampanha");
		String _idSubCampanha = request.getParameter("idSubCampanha");
		try {
			this.idCampanha = Integer.parseInt(_idCampanha);
			this.idSubCampanha = Integer.parseInt(_idSubCampanha);
		} catch (Exception e) {
			throw new Exception("Código campanha e sub campanha inválidos", e);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ConfigurarPrioridade.jsp"
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ConfiguraPrioridadeAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ConfiguraPrioridadeActionForm form = (ConfiguraPrioridadeActionForm) formParam;

			String user = ConstantesCRM.getCurrentUser(request.getSession());

			// Não entendi qual pq o código abaixo
			if ((idCampanha > 0) && ((request.getSession().getAttribute("idCampanha") == null) || (request.getSession().getAttribute("idCampanha").equals("")))) {
				request.getSession().setAttribute("idCampanha", String.valueOf(idCampanha));
			}
			// Não entendi qual pq o código abaixo
			if ((idSubCampanha > 0) && ((request.getSession().getAttribute("idSubCampanha") == null) || (request.getSession().getAttribute("idSubCampanha").equals("")))) {
				request.getSession().setAttribute("idSubCampanha", String.valueOf(idSubCampanha));
			}

			if ("carregar".equals(request.getParameter(ConstantesCRM.SACTION))) {
				// int idCanalCampanha = Integer.parseInt(form.getListaCanalSelecionado());
				CampanhaExecScriptVO listaPergunta = questFacade.getListaPergunta(user, Integer.parseInt(form.getListaCanalSelecionado()), 0, 0);
				form.setListaPergunta(listaPergunta.getCampanhaPerguntaVOArray());
				form.setIdAcao(1); // Mostrar
				form.setListaCanal(getConfiguraPrioridadeActionForm().getListaCanal());
			} else if ("salvar".equals(request.getParameter(ConstantesCRM.SACTION))) {
				questFacade.setPrioridadePerguntas(user, form.getCodigoSelecionado(), form.getOrdemSelecionada());
				form.setIdAcao(3);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("fechar");
			}
			this.configuraPrioridadeActionForm = form;
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
		ConfiguraPrioridadeActionForm form = (ConfiguraPrioridadeActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		/**************** Carrega listas que dependem de parametros **************************************************************************/
		try {
			/*************** Salva as listas carregadas para o formulario atual *************************/
			form.setListaPrioridade(getConfiguraPrioridadeActionForm().getListaPrioridade());
			this.configuraPrioridadeActionForm = form;
			if ("2".equals(request.getParameter("idPrioridade"))) {
				GrupoCampanhaApoioVO grupoSubCampanhaApoio = apoioFacade.getApoioCanalCampanha(user, String.valueOf(form.getIdSubCampanha()), 0);
				getConfiguraPrioridadeActionForm().setListaCanal(grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());
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
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class ConfiguraPrioridadeActionForm extends ActionForm {

		private static final long serialVersionUID = 359189220508062912L;

		private ApoioVO[] listaPrioridade = new ApoioVO[0];
		private ApoioVO[] listaCanal = null;
		private CampanhaPerguntaVO[] listaPergunta = null;
		private String listaSelecionada;
		private String listaCanalSelecionado;
		private int idSubCampanha = 0;
		private int idSubCampanhaFixa = 0;
		private String[] codigoSelecionado = null;
		private String[] ordemSelecionada = null;
		private int iTotalPerguntas = 0;
		private int idAcao = 0; // Não mostrar

		public int getITotalPerguntas() {
			return this.iTotalPerguntas;
		}

		public void setITotalPerguntas(int i) {
			this.iTotalPerguntas = i;
		}

		public void setCodigoSelecionado(String[] codigoSelecionado) {
			this.codigoSelecionado = codigoSelecionado;
		}

		public String[] getCodigoSelecionado() {
			if (this.codigoSelecionado == null) {
				this.codigoSelecionado = new String[0];
			}
			return this.codigoSelecionado;
		}

		public void setOrdemSelecionada(String[] ordemSelecionada) {
			this.ordemSelecionada = ordemSelecionada;
		}

		public String[] getOrdemSelecionada() {
			if (this.ordemSelecionada == null) {
				this.ordemSelecionada = new String[0];
			}
			return this.ordemSelecionada;
		}

		public CampanhaPerguntaVO[] getListaPergunta() {
			if (this.listaPergunta == null) {
				this.listaPergunta = new CampanhaPerguntaVO[0];
			}
			return this.listaPergunta;
		}

		public void setListaPergunta(CampanhaPerguntaVO[] lista) {
			this.listaPergunta = lista;
			this.iTotalPerguntas = lista.length;
		}

		public int getIdSubCampanha() {
			return this.idSubCampanha;
		}

		public void setIdSubCampanha(int idSubCampanha) {
			this.idSubCampanha = idSubCampanha;
		}

		public int getIdSubCampanhaFixa() {
			return this.idSubCampanhaFixa;
		}

		public void setIdSubCampanhaFixa(int idSubCampanhaFixa) {
			this.idSubCampanhaFixa = idSubCampanhaFixa;
		}

		public void setListaSelecionada(String listaSelecionada) {
			this.listaSelecionada = listaSelecionada;
		}

		public String getListaSelecionada() {
			return this.listaSelecionada;
		}

		public void setListaPrioridade(ApoioVO[] listaPrioridade) {
			this.listaPrioridade = listaPrioridade;
		}

		public ApoioVO[] getListaPrioridade() {
			if (this.listaPrioridade == null) {
				this.listaPrioridade = new ApoioVO[0];
			}
			return this.listaPrioridade;
		}

		public void setListaCanalSelecionado(String listaCanalSelecionado) {
			this.listaCanalSelecionado = listaCanalSelecionado;
		}

		public String getListaCanalSelecionado() {
			return this.listaCanalSelecionado;
		}

		public void setListaCanal(ApoioVO[] lista) {
			this.listaCanal = lista;
		}

		public ApoioVO[] getListaCanal() {
			if (this.listaCanal == null) {
				this.listaCanal = new ApoioVO[0];
			}
			return this.listaCanal;
		}

		public int getIdAcao() {
			return this.idAcao;
		}

		public void setIdAcao(int idAcao) {
			this.idAcao = idAcao;
		}

		public void clear() {
			this.idAcao = 0;
			this.listaSelecionada = ConstantesCRM.SVAZIO;
		}
	}
}
