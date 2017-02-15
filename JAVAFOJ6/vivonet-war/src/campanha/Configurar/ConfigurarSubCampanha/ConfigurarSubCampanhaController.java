package campanha.Configurar.ConfigurarSubCampanha;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ConfigurarSubCampanhaController extends AbstractAction {

	private static final long serialVersionUID = 1942860212368359578L;

	private String idCampanha = ConstantesCRM.SZERO;
	private String idSubCampanhaFixa = ConstantesCRM.SZERO;
	private String idSubCampanha = ConstantesCRM.SZERO;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.configurar.ConfigurarCampanha configCampanhaFacade;

	private String user = ConstantesCRM.SVAZIO;

	protected global.Global globalApp = new global.Global();

	private ConfigurarSubCampanhaActionForm configurarSubCampanhaActionForm = new ConfigurarSubCampanhaActionForm();

	public ConfigurarSubCampanhaActionForm getConfigurarSubCampanhaActionForm() {
		return configurarSubCampanhaActionForm;
	}

	private void loadPage(HttpServletRequest request) throws Exception {

		// String s = (String)request.getAttribute("idCampanha");
		// String s1 = (String)request.getAttribute("idSubCampanha");

		try {
			idCampanha = (String) request.getAttribute("idCampanha");
			idSubCampanha = (String) request.getAttribute("idSubCampanha");
			idSubCampanhaFixa = idCampanha;

			configurarSubCampanhaActionForm.setIdCampanha(idCampanha);
			configurarSubCampanhaActionForm.setIdSubCampanha(idSubCampanha);
			configurarSubCampanhaActionForm.setIdSubCampanhaFixa(idSubCampanhaFixa);
		} catch (Exception e) {
			throw new Exception("Código campanha e sub campanha inválidos", e);
		}

	}

	public void iniciaCombos(HttpServletRequest request) throws Exception {

		GrupoCampanhaApoioVO configTmp = null;
		/**
		 * Se for alteração, o serviço deverá trazer todos os registro para os tres combos
		 **/
		if (request.getSession().getAttribute("IDSTATUS") != null && request.getSession().getAttribute("IDSTATUS").toString() != "") {

			configTmp = configCampanhaFacade.getMotivoId(user, Integer.parseInt(idSubCampanha), Integer.parseInt(request.getSession().getAttribute("IDSTATUS").toString()), 0);
			configurarSubCampanhaActionForm.setMotivoDisp(configTmp.getSubGrupoApoioVOArray(0).getApoioVOArray());

			configurarSubCampanhaActionForm.setStatusDispSelecionado(request.getSession().getAttribute("IDSTATUS").toString());
		}
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="ConfigurarSubCampanhaAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		loadPage(request);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ConfigurarSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ConfigurarSubCampanhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConfigurarSubCampanhaActionForm form = (ConfigurarSubCampanhaActionForm) formParam;

		GrupoCampanhaApoioVO configCampanha = null;
		try {

			form.setStatusDisp(configurarSubCampanhaActionForm.getStatusDisp());
			form.setMotivoDisp(configurarSubCampanhaActionForm.getMotivoDisp());
			form.setSubMotivoDisp(configurarSubCampanhaActionForm.getSubMotivoDisp());
			form.setIdCampanha(configurarSubCampanhaActionForm.getIdCampanha());
			form.setIdSubCampanha(configurarSubCampanhaActionForm.getIdSubCampanha());
			configurarSubCampanhaActionForm = form;

			// configCampanha = configCampanhaFacade.getGrupoConfigurarCampanhaVO(user,
			// Integer.parseInt(configurarSubCampanhaActionForm.getIdCampanha()),Integer.parseInt(configurarSubCampanhaActionForm.getIdSubCampanha()),
			// 0, 0);

			// if ( "editar".equalsIgnoreCase(request.getParameter(ConstantesCRM.SACTION) ) )
			// {
			// Se for editar, traz todos os status cadastrados na tabela.
			// configCampanha = configCampanhaFacade.getGrupoConfigurarCampanhaVO(user,
			// Integer.parseInt(configurarSubCampanhaActionForm.getIdCampanha()),Integer.parseInt(configurarSubCampanhaActionForm.getIdSubCampanha()),
			// 1, 0);
			// }
			// else if ( "incluir".equalsIgnoreCase(request.getParameter(ConstantesCRM.SACTION) ) )
			// {
			// Se for incluir, só traz os que ainda não foram cadastrados para esta subcampanha
			// configCampanha = configCampanhaFacade.getGrupoConfigurarCampanhaVO(user,
			// Integer.parseInt(configurarSubCampanhaActionForm.getIdCampanha()),Integer.parseInt(configurarSubCampanhaActionForm.getIdSubCampanha()),
			// 1, 0);
			// }

			configCampanha = configCampanhaFacade.getGrupoConfigurarCampanhaVO(user, Integer.parseInt(configurarSubCampanhaActionForm.getIdCampanha()), Integer.parseInt(configurarSubCampanhaActionForm.getIdSubCampanha()), 1, 0);
			configurarSubCampanhaActionForm.setStatusDisp(configCampanha.getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			throw e;
		}
		iniciaCombos(request);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="fechaFrame" path="../../Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward salvarStatusMotivoSubMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ConfigurarSubCampanhaActionForm form = (ConfigurarSubCampanhaActionForm) formParam;

		try {

			configCampanhaFacade.addGrupoConfigurarCampanhaVO(user, Integer.parseInt(form.getIdSubCampanha()), form.getStatusDispSelecionado(), form.getMotivoDispSelecionado(), form.getSubMotivoDispSelecionado());
			// request.setAttribute("tipo", "1");
			request.setAttribute("tipo", ConstantesCRM.SFOUR);
		} catch (Exception e) {
			/*
			 * Estou com nojo de alter no momento o metodo private void loadPage() throws Exception mas sei que ele
			 * precisa desse atributos no Request. Para garantir ao invés de sair sapiando estou inserido as seguintes
			 * linhas abaixo.
			 */
			request.setAttribute("idCampanha", request.getSession().getAttribute("idCampanha"));
			request.setAttribute("idSubCampanha", request.getSession().getAttribute("idSubCampanha"));
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechaFrame");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ObtemCampanhaListaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * ESTE METODO SERA APAGADO EM BREVE
		 */
		/**************** Carrega listas que dependem de parametros **************************************************************************/

		ConfigurarSubCampanhaActionForm form = (ConfigurarSubCampanhaActionForm) formParam;

		try {

			/*************** Salva as listas carregadas para o formulario atual *************************/
			form.setStatusDisp(configurarSubCampanhaActionForm.getStatusDisp());
			form.setMotivoDisp(configurarSubCampanhaActionForm.getMotivoDisp());
			form.setSubMotivoDisp(configurarSubCampanhaActionForm.getSubMotivoDisp());

			configurarSubCampanhaActionForm = form;

			if ("status".equals(request.getParameter(ConstantesCRM.SACTION))) {
				configurarSubCampanhaActionForm.setMotivoDispSelecionado(ConstantesCRM.SVAZIO);
				configurarSubCampanhaActionForm.setSubMotivoDispSelecionado(ConstantesCRM.SVAZIO);

			} else if ("motivo".equals(request.getParameter(ConstantesCRM.SACTION))) {
				configurarSubCampanhaActionForm.setSubMotivoDispSelecionado(ConstantesCRM.SVAZIO);
			}

			int iIdStatus = 0;

			GrupoCampanhaApoioVO configMotivo = null;

			if ((request.getParameter("idStatus") != null) && (!request.getParameter("idStatus").equals(ConstantesCRM.SVAZIO))) {
				iIdStatus = Integer.parseInt(request.getParameter("idStatus"));

				/*********************** Lista de Motivo *******************************************************************************************/

				// if("editar".equals(request.getParameter(ConstantesCRM.SACTION)))
				// {
				// NO caso de alteração, traz todos os motivos disponiveis
				// configMotivo = configCampanhaFacade.getMotivoId(user, idCampanha, iIdStatus,1, 0);
				// }
				// else if("incluir".equals(request.getParameter(ConstantesCRM.SACTION)))
				// {
				// NO caso de inclusão, traz os motivos que ainda não foram cadastrados
				configMotivo = configCampanhaFacade.getMotivoId(user, Integer.parseInt(idSubCampanha), iIdStatus, 0);
				// }

				configurarSubCampanhaActionForm.setMotivoDisp(configMotivo.getSubGrupoApoioVOArray(0).getApoioVOArray());
			}

			int iIdMotivo = 0;
			GrupoCampanhaApoioVO configSubMotivo = null;

			if ((!"0".equals(this.idSubCampanha)) && (request.getParameter("idMotivo") != null) && (!request.getParameter("idMotivo").equals(""))) {
				iIdMotivo = Integer.parseInt(request.getParameter("idMotivo"));

				configSubMotivo = configCampanhaFacade.getSubMotivoId(user, Integer.parseInt(idSubCampanha), iIdStatus, iIdMotivo, 0);

				configurarSubCampanhaActionForm.setSubMotivoDisp(configSubMotivo.getSubGrupoApoioVOArray(0).getApoioVOArray());
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
			// throw e;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obterSubMotivos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**************** Carrega listas que dependem de parametros **************************************************************************/

		try {
			ConfigurarSubCampanhaActionForm form = (ConfigurarSubCampanhaActionForm) formParam;

			/*************** Salva as listas carregadas para o formulario atual *************************/
			form.setStatusDisp(configurarSubCampanhaActionForm.getStatusDisp());
			form.setMotivoDisp(configurarSubCampanhaActionForm.getMotivoDisp());
			form.setSubMotivoDisp(configurarSubCampanhaActionForm.getSubMotivoDisp());

			configurarSubCampanhaActionForm = form;

			int iIdStatus = Integer.parseInt(request.getParameter("idStatus"));
			int iIdMotivo = Integer.parseInt(request.getParameter("idMotivo"));

			GrupoCampanhaApoioVO configSubMotivo = configCampanhaFacade.getSubMotivoId(user, Integer.parseInt(idSubCampanha), iIdStatus, iIdMotivo, 0);

			configurarSubCampanhaActionForm.setSubMotivoDisp(configSubMotivo.getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
			// throw e;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obterMotivos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**************** Carrega listas que dependem de parametros **************************************************************************/

		try {
			ConfigurarSubCampanhaActionForm form = (ConfigurarSubCampanhaActionForm) formParam;

			/*************** Salva as listas carregadas para o formulario atual *************************/
			form.setStatusDisp(configurarSubCampanhaActionForm.getStatusDisp());
			form.setMotivoDisp(configurarSubCampanhaActionForm.getMotivoDisp());
			form.setSubMotivoDisp(configurarSubCampanhaActionForm.getSubMotivoDisp());

			configurarSubCampanhaActionForm = form;

			configurarSubCampanhaActionForm.setSubMotivoDispSelecionado(ConstantesCRM.SVAZIO);

			int iIdStatus = 0;

			GrupoCampanhaApoioVO configMotivo = null;

			iIdStatus = Integer.parseInt(request.getParameter("idStatus"));

			/*********************** Lista de Motivo *******************************************************************************************/

			// if("editar".equals(request.getParameter(ConstantesCRM.SACTION)))
			// {
			// NO caso de alteração, traz todos os motivos disponiveis
			// configMotivo = configCampanhaFacade.getMotivoId(user, idCampanha, iIdStatus,1, 0);
			// }
			// else if("incluir".equals(request.getParameter(ConstantesCRM.SACTION)))
			// {
			// NO caso de inclusão, traz os motivos que ainda não foram cadastrados
			configMotivo = configCampanhaFacade.getMotivoId(user, Integer.parseInt(idSubCampanha), iIdStatus, 0);
			// }

			configurarSubCampanhaActionForm.setMotivoDisp(configMotivo.getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
			// throw e;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../../Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 */
	protected ActionForward voltarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class ConfigurarSubCampanhaActionForm extends ActionForm {

		private static final long serialVersionUID = -6061706849579703375L;

		private String idSubCampanha;
		private String idSubCampanhaFixa;
		private String idCampanha;

		private ApoioVO[] statusDisp = new ApoioVO[0];
		private String statusDispSelecionado;

		// private ApoioVO[] statusUtil = new ApoioVO[0];
		// private String statusUtilSelecionado[];

		private ApoioVO[] motivoDisp = new ApoioVO[0];
		private String motivoDispSelecionado;
		// private ApoioVO[] motivoUtil = new ApoioVO[0];
		// private String motivoUtilSelecionado[];

		private ApoioVO[] subMotivoDisp = new ApoioVO[0];
		private String subMotivoDispSelecionado;

		// private ApoioVO[] subMotivoUtil = new ApoioVO[0];
		// private String subMotivoUtilSelecionado[];

		/***********************************************************************************************
		 * TIPO STATUS CAMPANHA
		 **********************************************************************************************/

		/********************* Status Disponiveis ******************************************/
		public ApoioVO[] getStatusDisp() {
			return statusDisp;
		}

		public void setStatusDisp(ApoioVO[] lista) {
			statusDisp = lista;
		}

		public String getStatusDispSelecionado() {
			return statusDispSelecionado;
		}

		public void setStatusDispSelecionado(String lista) {
			statusDispSelecionado = lista;
		}

		/***********************************************************************************************
		 * TIPO MOTIVO CAMPANHA
		 **********************************************************************************************/

		/********************* Motivos Disponiveis ******************************************/
		public ApoioVO[] getMotivoDisp() {
			return motivoDisp;
		}

		public void setMotivoDisp(ApoioVO[] lista) {
			motivoDisp = lista;
		}

		public String getMotivoDispSelecionado() {
			return motivoDispSelecionado;
		}

		public void setMotivoDispSelecionado(String lista) {
			motivoDispSelecionado = lista;
		}

		/***********************************************************************************************
		 * TIPO SUBMOTIVO CAMPANHA
		 **********************************************************************************************/

		/********************* SubMotivos Disponiveis ******************************************/
		public ApoioVO[] getSubMotivoDisp() {
			return subMotivoDisp;
		}

		public void setSubMotivoDisp(ApoioVO[] lista) {
			subMotivoDisp = lista;
		}

		public String getSubMotivoDispSelecionado() {
			return subMotivoDispSelecionado;
		}

		public void setSubMotivoDispSelecionado(String lista) {
			subMotivoDispSelecionado = lista;
		}

		/*********************************************************************************************************************/

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

		public void setIdSubCampanhaFixa(String idSubCampanhaFixa) {
			this.idSubCampanhaFixa = idSubCampanhaFixa;
		}

		public String getIdSubCampanhaFixa() {
			return this.idSubCampanhaFixa;
		}

	}
}