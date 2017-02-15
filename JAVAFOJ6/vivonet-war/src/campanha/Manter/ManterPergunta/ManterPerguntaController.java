package campanha.Manter.ManterPergunta;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioCanalVODocument.ApoioCanalVO;
import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.CadastroPerguntaVODocument.CadastroPerguntaVO;
import br.com.vivo.fo.campanha.vo.CampanhaPerguntaVODocument.CampanhaPerguntaVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.ListaCampanhaPerguntaVODocument.ListaCampanhaPerguntaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ManterPerguntaController extends AbstractAction {

	private static final long serialVersionUID = -7772627557424986488L;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.questionario.QuestionarioCampanhaFacade questFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.CampanhaFacade campanhaFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.ApoioFacade apoioFacade;

	private ListaCampanhaPerguntaVO listaPergunta = null;
	private GrupoCampanhaApoioVO grupoCampanhaApoio = null;
	public HashMap hPerguntas = new HashMap();
	public HashMap hApresentacao = new HashMap();
	public HashMap hCanal = new HashMap();
	private static transient Logger log = new Logger("campanha");

	private AdminPerguntaActionForm adminPerguntaActionForm = new AdminPerguntaActionForm();

	private String user = ConstantesCRM.SVAZIO;

	/******************************************** PARAMETROS RECEBIDOS EXTERNAMENTE *******************************************************/
	private String idCampanha = ConstantesCRM.SZERO;
	private String idSubCampanha = ConstantesCRM.SZERO;
	private String idSubCampanhaFixa = ConstantesCRM.SZERO;

	private String idCanalCampanha = ConstantesCRM.SZERO;
	private long idPergunta = 0;
	private String sCampanha = ConstantesCRM.SVAZIO;
	private String sSubCampanha = ConstantesCRM.SVAZIO;
	private String sDsCanalCampanha = ConstantesCRM.SVAZIO;

	private String mensagemRetorno = ConstantesCRM.SVAZIO;

	protected global.Global globalApp = new global.Global();

	public String getMensagemRetorno() {
		return this.mensagemRetorno;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("AdminPerguntaAction".equals(mapping.getParameter())) {
			return AdminPerguntaAction(mapping, form, request, response);
		} else if ("voltarAction".equals(mapping.getParameter())) {
			return voltarAction(mapping, form, request, response);
		} else if ("incluirPergunta".equals(mapping.getParameter())) {
			return incluirPergunta(mapping, form, request, response);
		} else if ("alterarPergunta".equals(mapping.getParameter())) {
			return alterarPergunta(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="AdminPerguntaAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		loadPage(request);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="begin"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AdminPerguntaCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward AdminPerguntaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		AdminPerguntaActionForm form = (AdminPerguntaActionForm) formParam;

		RetornoVO retorno = null;
		try {

			if ("editar".equals(request.getParameter(ConstantesCRM.SACTION))) {

				form.setiAcao(1);
				form.setIdPergunta(this.idPergunta);
				form.setIdCanalCampanha(idCanalCampanha);

			} else if (form.getiAcao() != 1) {
				form.clear();
				form.setiAcao(0);
				form.setiStatus(ConstantesCRM.SONE);
				/****************** Retorna inNegative = 1 (Indica retornar todos canais campanha não associados) *****************************************************/
				adminPerguntaActionForm.setListaCanalDisp(apoioFacade.getApoioCanalCampanha(user, idSubCampanha, 0).getSubGrupoApoioVOArray(0).getApoioVOArray());
			}

			/************************* Lista de Tipo Apresentação de Perguntas **********************************/
			GrupoCampanhaApoioVO listaApresentacao = questFacade.getTipoApresPrg(user);
			adminPerguntaActionForm.setListaApresentacao(listaApresentacao.getSubGrupoApoioVOArray(0).getApoioVOArray());

			if (form.getIdPergunta() != 0) {

				CadastroPerguntaVO campanhaPergunta = questFacade.getPergunta(user, Integer.parseInt(form.getIdCanalCampanha()), form.getIdPergunta());

				form.setCadastroPerguntaFormVO(campanhaPergunta);

				form.setiAcao(1);
				form.FormLoad();
			}

			form.setListaCanalDisp(adminPerguntaActionForm.getListaCanalDisp());
			form.setListaApresentacao(adminPerguntaActionForm.getListaApresentacao());
			form.setListaPergunta(adminPerguntaActionForm.getListaPergunta());

			/*** Pega a informação do request ***/
			// form.setsSgCanal(request.getParameter("dsCanalCampanha"));

			adminPerguntaActionForm = form;
		} catch (Exception e) {

			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public ArrayList getListaModificacoes(String[] listaBase, String[] listaAtual) {
		try {
			ArrayList aRetorno = new ArrayList();
			if ((listaBase == null) || (listaAtual == null)) {
				/*********************** Listas Null ***********************/
				throw (new Exception("Listas vazia"));
			}

			for (int i = 0; i < listaBase.length; i++) {
				for (int j = 0; j < listaAtual.length; j++) {
					if (listaBase[i].equalsIgnoreCase(listaAtual[j])) {
						aRetorno.add(listaBase[i]);
					}
				}
			}

			return aRetorno;

		} catch (Exception e) {
			return new ArrayList();
		}
	}

	protected void loadPage(HttpServletRequest request) throws Exception {

		String _idCampanha = (String) request.getAttribute("idCampanha");
		String _idSubCampanha = (String) request.getAttribute("idSubCampanha");
		String _idCanalCampanha = (String) request.getAttribute("idCanalCampanha");
		String _idPergunta = (String) request.getAttribute("idPergunta");

		if (_idPergunta != null && !_idPergunta.equals(ConstantesCRM.SVAZIO)) {
			if (_idCanalCampanha == null || _idCanalCampanha.equals(ConstantesCRM.SVAZIO) || _idCanalCampanha.equals(ConstantesCRM.SZERO)) {
				throw new Exception("Código canal atendimento inválido");
			} else {
				idCanalCampanha = _idCanalCampanha;
				idPergunta = Long.parseLong(_idPergunta);
			}
		}

		try {

			idCampanha = _idCampanha;
			idSubCampanha = _idSubCampanha;
			idSubCampanhaFixa = idCampanha;
		} catch (Exception e) {
			throw new Exception("Código campanha e sub campanha inválidos", e);
		}
	}

	public AdminPerguntaActionForm getAdminPerguntaActionForm() {
		return adminPerguntaActionForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 */
	protected ActionForward voltarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AdminPerguntaCampanha.jsp"
	 * @jpf:forward name="fechaFrame" path="../ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluirPergunta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AdminPerguntaActionForm form = (AdminPerguntaActionForm) formParam;

			/**************************************** INCLUIR PERGUNTA **********************************************************************************************/
			RetornoVO retorno = questFacade.addPerguntaCampanha(user, form.getPerguntaFormVO());
			if (retorno != null && "-1".equals(retorno.getValor()) && retorno.getDescricao() != null) {
				this.mensagemRetorno = retorno.getDescricao();
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			sDsCanalCampanha = (String) request.getSession().getAttribute("dsCanalCampanha");
			// request.getSession().setAttribute("dsCanalCampanha",sDsCanalCampanha);
			form.clear();
		} catch (Exception e) {

			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("tipo", ConstantesCRM.SSEVEN);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechaFrame");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AdminPerguntaCampanha.jsp"
	 * @jpf:forward name="fechaFrame" path="../ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward alterarPergunta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AdminPerguntaActionForm form = (AdminPerguntaActionForm) formParam;

			/**************************************** ALTERA PERGUNTA **********************************************************************************************************************************/
			RetornoVO retorno = questFacade.setPerguntaCampanha(user, form.getPerguntaFormVO());
			if (retorno != null && "-1".equals(retorno.getValor()) && retorno.getDescricao() != null) {
				this.mensagemRetorno = retorno.getDescricao();
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
			}
			adminPerguntaActionForm.setListaPergunta(adminPerguntaActionForm.getListaPergunta());

			sDsCanalCampanha = (String) request.getSession().getAttribute("dsCanalCampanha");
			// request.getSession().setAttribute("dsCanalCampanha",sDsCanalCampanha);
			form.clear();
		} catch (Exception e) {

			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute("tipo", ConstantesCRM.SEIGHT);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechaFrame");
	}

	public static class AdminPerguntaActionForm extends ActionForm {

		private static final long serialVersionUID = -3258549170174247987L;

		private String iTipoApresentacao;
		private String iObrigatoria;
		private String iStatus;
		private String iEncerramento;
		private Integer iSeqApresentacao;
		private String idCanalCampanha;
		private long idPergunta;
		private String idSubCampanha;
		private String idCampanha;
		private long iAcao = 0; // Inclusão

		private String sDsTipoApresentacao;
		private String sDsScript;
		private String sDsPergunta;
		private String sSgCanal;

		private CampanhaPerguntaVO perguntaFormVO = CampanhaPerguntaVO.Factory.newInstance();

		private CadastroPerguntaVO cadastroPerguntaFormVO = CadastroPerguntaVO.Factory.newInstance();

		private ApoioVO[] listaCanalDisp = new ApoioVO[0];
		private String[] canalDispSelecionado = new String[0];

		private ApoioVO[] listaCanalUtil = new ApoioVO[0];
		private String[] canalUtilSelecionado = new String[0];

		private CampanhaPerguntaVO[] listaPergunta = new CampanhaPerguntaVO[0];
		private String perguntaSelecionada = ConstantesCRM.SVAZIO;

		private ApoioVO[] listaApresentacao = new ApoioVO[0];
		private String tipoApresSelecionado = ConstantesCRM.SVAZIO;

		/************************************* Canal Disponiveis ************************************************************/
		public ApoioVO[] getListaCanalDisp() {
			return listaCanalDisp;
		}

		public void setListaCanalDisp(ApoioVO[] lista) {
			listaCanalDisp = lista;
		}

		public void setCanalDispSelecionado(String[] s) {
			canalDispSelecionado = s;
		}

		public String[] getCanalDispSelecionado() {
			return canalDispSelecionado;
		}

		/************************************* Canal Utilizados ************************************************************/
		public ApoioVO[] getListaCanalUtil() {
			return listaCanalUtil;
		}

		public void setListaCanalUtil(ApoioVO[] lista) {
			listaCanalUtil = lista;
		}

		public void setCanalUtilSelecionado(String[] s) {
			canalUtilSelecionado = s;
		}

		public String[] getCanalUtilSelecionado() {
			return canalUtilSelecionado;
		}

		/************************************* Lista Perguntas ************************************************************/
		public CampanhaPerguntaVO[] getListaPergunta() {
			return listaPergunta;
		}

		public void setListaPergunta(CampanhaPerguntaVO[] lista) {
			listaPergunta = lista;
		}

		public void setPerguntaSelecionada(String s) {
			perguntaSelecionada = s;
		}

		public String getPerguntaSelecionada() {
			return perguntaSelecionada;
		}

		/*********************************** Lista de Tipo apresentação ****************************************************/
		public ApoioVO[] getListaApresentacao() {
			return listaApresentacao;
		}

		public void setListaApresentacao(ApoioVO[] lista) {
			listaApresentacao = lista;
		}

		public String getTipoApresSelecionado() {
			return tipoApresSelecionado;
		}

		public void setTipoApresSelecionado(String s) {
			tipoApresSelecionado = s;
		}

		/************************************* Pergunta ************************************************************/
		public void setPerguntaFormVO(CampanhaPerguntaVO pergunta) {
			this.perguntaFormVO = pergunta;
		}

		// public CampanhaPerguntaVO getPerguntaFormVO(ArrayList aCanalAtivo, ArrayList aCanalInativo)
		public CampanhaPerguntaVO getPerguntaFormVO() throws Exception {
			/********* Preenche o VO com os dados do formulario para input no BD *******/

			this.perguntaFormVO.setIdApresentacao(tipoApresSelecionado);
			this.perguntaFormVO.setInObrigatoria(iObrigatoria);
			this.perguntaFormVO.setInDisponibilidade(iStatus);
			this.perguntaFormVO.setInEncerramento(iEncerramento);
			int idSqApresentacao = 0;
			try {
				idSqApresentacao = Integer.parseInt(perguntaSelecionada);
				idSqApresentacao++;
			} catch (Exception e) {
				idSqApresentacao = 0;
			}
			this.perguntaFormVO.setSqApresentacao(String.valueOf(idSqApresentacao));
			this.perguntaFormVO.setIdPergunta(String.valueOf(idPergunta));
			this.perguntaFormVO.setDsScriptPergunta(sDsScript);
			this.perguntaFormVO.setDsPergunta(sDsPergunta);

			if (iAcao == 1) {
				/************** Alteração ********************************************************/
				this.perguntaFormVO.addNewCanalUtil();
				this.perguntaFormVO.getCanalUtil().addNewApoioCanalVO();
				this.perguntaFormVO.getCanalUtil().getApoioCanalVOArray(0).setIdCanalCampanha(idCanalCampanha);
			} else if (iAcao == 0) {
				/*************** Inclusão nova Pergunta ************************************************************/

				/**************************** Canal de Atendimento *********************************************************/
				try {
					int i = 0;

					if (canalUtilSelecionado.length > 0) {
						this.perguntaFormVO.addNewCanalUtil();
					}

					for (i = 0; i < canalUtilSelecionado.length; i++) {

						ApoioCanalVO canalVO = ApoioCanalVO.Factory.newInstance();

						canalVO.setCnlcmpinAtivo(ConstantesCRM.SONE);
						String sId = canalUtilSelecionado[i];

						canalVO.setIdCanalCampanha(sId);

						this.perguntaFormVO.getCanalUtil().addNewApoioCanalVO();
						this.perguntaFormVO.getCanalUtil().setApoioCanalVOArray(i, canalVO);

					}

				} catch (Exception e) {
					throw e;
				}

			}

			return this.perguntaFormVO;
		}

		public CadastroPerguntaVO getCadastroPerguntaFormVO() {
			return cadastroPerguntaFormVO;
		}

		public void setCadastroPerguntaFormVO(CadastroPerguntaVO pergunta) {
			cadastroPerguntaFormVO = pergunta;
		}

		private void FormLoad() {

			if (this.cadastroPerguntaFormVO != null) {
				this.iTipoApresentacao = this.cadastroPerguntaFormVO.getIdTipoApresentacao();
				this.tipoApresSelecionado = String.valueOf(this.cadastroPerguntaFormVO.getIdTipoApresentacao());
				this.iObrigatoria = this.cadastroPerguntaFormVO.getInObrigatoria();
				this.iStatus = this.cadastroPerguntaFormVO.getInDisponibilidade();
				this.iEncerramento = this.cadastroPerguntaFormVO.getInEncerramento();
				this.iSeqApresentacao = new Integer(this.cadastroPerguntaFormVO.getSqApresentacao());
				iSeqApresentacao = new Integer(iSeqApresentacao.intValue() - 1);
				this.perguntaSelecionada = String.valueOf(iSeqApresentacao);
				this.idPergunta = Long.parseLong(this.cadastroPerguntaFormVO.getIdPergunta());

				this.sDsScript = this.cadastroPerguntaFormVO.getDsScriptPergunta();
				this.sDsPergunta = this.cadastroPerguntaFormVO.getDsPergunta();

				/**********************
				 * Operação de alteração de pergunta será selecionado o CanalCampanha específico: VO sempre retorna um
				 * unico CanalCampanha
				 ******************/
				this.sSgCanal = this.cadastroPerguntaFormVO.getCanalCampanhaArray(0).getNmCanal();
				this.idCanalCampanha = this.cadastroPerguntaFormVO.getCanalCampanhaArray(0).getIdCanalCampanha();

				this.iAcao = 1;

			}

		}

		public void setIdCampanha(String i) {
			this.idCampanha = i;
		}

		public String getIdCampanha() {
			return this.idCampanha;
		}

		public void setIdSubCampanha(String i) {
			this.idSubCampanha = i;
		}

		public String getIdSubCampanha() {
			return this.idSubCampanha;
		}

		public void setIdPergunta(long _idPergunta) {
			this.idPergunta = _idPergunta;
		}

		public long getIdPergunta() {
			return this.idPergunta;
		}

		public void setIdCanalCampanha(String i) {
			this.idCanalCampanha = i;
		}

		public String getIdCanalCampanha() {
			return this.idCanalCampanha;
		}

		public void setsSgCanal(String sSgCanal) {
			this.sSgCanal = sSgCanal;
		}

		public String getsSgCanal() {
			return this.sSgCanal;
		}

		public void setsDsPergunta(String sDsPergunta) {
			this.sDsPergunta = sDsPergunta;
		}

		public String getsDsPergunta() {
			return this.sDsPergunta;
		}

		public void setsDsScript(String sDsScript) {
			this.sDsScript = sDsScript;
		}

		public String getsDsScript() {
			return this.sDsScript;
		}

		public void setiSeqApresentacao(Integer iSeqApresentacao) {
			this.iSeqApresentacao = iSeqApresentacao;
		}

		public Integer getiSeqApresentacao() {
			return this.iSeqApresentacao;
		}

		public void setiEncerramento(String iEncerramento) {
			this.iEncerramento = iEncerramento;
		}

		public String getiEncerramento() {
			return this.iEncerramento;
		}

		public void setiStatus(String iStatus) {
			this.iStatus = iStatus;
		}

		public String getiStatus() {
			return this.iStatus;
		}

		public void setiObrigatoria(String iObrigatoria) {
			this.iObrigatoria = iObrigatoria;
		}

		public String getiObrigatoria() {
			return this.iObrigatoria;
		}

		public void setiTipoApresentacao(String iTipoApresentacao) {
			this.iTipoApresentacao = iTipoApresentacao;
		}

		public String getiTipoApresentacao() {
			return this.iTipoApresentacao;
		}

		public void setsDsTipoApresentacao(String sDsTipoApresentacao) {
			this.sDsTipoApresentacao = sDsTipoApresentacao;
		}

		public String getsDsTipoApresentacao() {
			return this.sDsTipoApresentacao;
		}

		public long getiAcao() {
			return iAcao;
		}

		public void setiAcao(long _iAcao) {
			this.iAcao = _iAcao;
		}

		public void clear() {
			this.iTipoApresentacao = ConstantesCRM.SZERO;
			this.iObrigatoria = ConstantesCRM.SONE;
			this.iStatus = ConstantesCRM.SONE;
			this.iEncerramento = ConstantesCRM.SZERO;
			this.iSeqApresentacao = new Integer(0);
			this.idCanalCampanha = ConstantesCRM.SZERO;
			this.idPergunta = 0;
			this.idSubCampanha = ConstantesCRM.SZERO;
			this.idCampanha = ConstantesCRM.SZERO;

			this.sDsTipoApresentacao = ConstantesCRM.SVAZIO;
			this.sDsScript = ConstantesCRM.SVAZIO;
			this.sDsPergunta = ConstantesCRM.SVAZIO;
			this.sSgCanal = ConstantesCRM.SVAZIO;
			this.iAcao = 0; // Inclusão

		}
	}
}
