package usuario.manterSistema.manterServidor;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterSistema.ManterSistemaFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ListaServidoresVODocument.ListaServidoresVO;
import br.com.vivo.fo.usuario.vo.SistemaServidorExistenteUsuarioVODocument.SistemaServidorExistenteUsuarioVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterServidorController extends AbstractAction {

	private static final long serialVersionUID = 7463096749627664632L;

	@EJB
	private ManterSistemaFacade controlSistema;

	private ManterServidorForm manterServidorForm = new ManterServidorForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaServidor".equals(mapping.getParameter())) {
			return salvaServidor(mapping, form, request, response);
		} else if ("removeServidor".equals(mapping.getParameter())) {
			return removeServidor(mapping, form, request, response);
		} else if ("limpaServidor".equals(mapping.getParameter())) {
			return limpaServidor(mapping, form, request, response);
		} else if ("listaServidorAction".equals(mapping.getParameter())) {
			return listaServidorAction(mapping, form, request, response);
		} else if ("carregaEdita".equals(mapping.getParameter())) {
			return carregaEdita(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterServidor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterServidorController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			manterServidorForm = new ManterServidorForm();
			manterServidorForm.setListaServidorLength(ConstantesCRM.SZERO);
			manterServidorForm.setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("ManterServidorController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterServidor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaServidor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterServidorForm form = (ManterServidorForm) formParam;
		log.debug("ManterServidorController:salvaServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		manterServidorForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			SistemaServidorExistenteUsuarioVO servidorSalvar = SistemaServidorExistenteUsuarioVO.Factory.newInstance();

			if (form.getDsServidor() != null) {
				servidorSalvar.setDsServidor(form.getDsServidor().trim());
			}

			String tipo = ConstantesCRM.SVAZIO;
			String idServidor = form.getIdServidor();

			// Verifica se é uma insercao ou uma edicao de servidor
			if (idServidor == null || idServidor.equals(ConstantesCRM.SVAZIO)) {
				servidorSalvar.setIdServidor(ConstantesCRM.SVAZIO);
				tipo = "novo";
			} else {
				servidorSalvar.setIdServidor(idServidor);
				tipo = "edicao";
			}

			ListaServidoresVO listaServidores = null;

			// Salva Servidor.
			listaServidores = controlSistema.salvaServidor(servidorSalvar, tipo, ConstantesCRM.getCurrentUser(request.getSession()));
			manterServidorForm.setListaServidorVO(listaServidores.getSistemaServidorExistenteUsuarioVOArray());
			manterServidorForm.setListaServidorLength(String.valueOf(listaServidores.getSistemaServidorExistenteUsuarioVOArray().length));
			manterServidorForm.setDsServidor(ConstantesCRM.SVAZIO);
			manterServidorForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupamentoController:salvaServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterServidorForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterServidorController:salvaServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterServidor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeServidor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterServidorController:removeServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		manterServidorForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			SistemaServidorExistenteUsuarioVO servidorRemover = SistemaServidorExistenteUsuarioVO.Factory.newInstance();
			String servidorId = request.getParameter("servidorId");
			servidorRemover.setIdServidor(servidorId);

			// Remove Servidor.
			ListaServidoresVO listaServidores = controlSistema.removeServidor(servidorRemover, ConstantesCRM.getCurrentUser(request.getSession()));

			manterServidorForm.setListaServidorVO(listaServidores.getSistemaServidorExistenteUsuarioVOArray());
			manterServidorForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupamentoController:removeServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterServidorForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterServidorController:removeServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterServidor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward limpaServidor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterServidorController:limpaServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		manterServidorForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			manterServidorForm.setDsServidor(ConstantesCRM.SVAZIO);
			manterServidorForm.setIdServidor(ConstantesCRM.SVAZIO);
			manterServidorForm.setServidorVO(SistemaServidorExistenteUsuarioVO.Factory.newInstance());
			manterServidorForm.setListaServidorLength(ConstantesCRM.SZERO);

		} catch (Exception e) {
			log.error("ManterServidorController:limpaServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterServidor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaServidorAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterServidorForm form = (ManterServidorForm) formParam;
		log.debug("ManterServidorController:listaServidorAction" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		manterServidorForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			SistemaServidorExistenteUsuarioVO servidorVO = SistemaServidorExistenteUsuarioVO.Factory.newInstance();
			servidorVO.setDsServidor(form.getDsServidor());

			// Lista Servidores.
			ListaServidoresVO listaServidoresVO = controlSistema.listaServidores(servidorVO, ConstantesCRM.getCurrentUser(request.getSession()));

			getManterServidorForm().setListaServidorVO(listaServidoresVO.getSistemaServidorExistenteUsuarioVOArray());

			// Se existem registros.
			if (listaServidoresVO.getSistemaServidorExistenteUsuarioVOArray().length > 0) {
				getManterServidorForm().setListaServidorLength(String.valueOf(listaServidoresVO.getSistemaServidorExistenteUsuarioVOArray().length));
			} else {
				getManterServidorForm().setListaServidorLength("-1");
			}

			getManterServidorForm().setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("ManterServidorController:listaServidorAction" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alteraIncluiServidor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaEdita(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterServidorForm form = (ManterServidorForm) formParam;
		String operacao = (String) request.getParameter("operacao");
		
		log.debug("ManterServidorController:carregaEdita" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )" + operacao);

		try {
			if(!"incluir".equalsIgnoreCase(operacao) && manterServidorForm != null && manterServidorForm.getListaServidorVO().length > 0){
				SistemaServidorExistenteUsuarioVO servidor = getServidorById(form.getIdServidor(), manterServidorForm.getListaServidorVO());
				manterServidorForm.setIdServidor(form.getIdServidor());
				manterServidorForm.setDsServidor(servidor.getDsServidor());
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ManterServidorController:carregaEdita" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public static class ManterServidorForm extends ActionForm {

		private static final long serialVersionUID = 6498417316544910723L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String listaServidorLength;
		private String dsServidor;
		private String idServidor;
		private String[] listaServidor;
		private SistemaServidorExistenteUsuarioVO[] listaServidorVO;
		private SistemaServidorExistenteUsuarioVO servidorVO;

		public ManterServidorForm() {
			servidorVO = SistemaServidorExistenteUsuarioVO.Factory.newInstance();
			listaServidor = new String[0];
			listaServidorVO = new SistemaServidorExistenteUsuarioVO[0];
			idServidor = ConstantesCRM.SVAZIO;
			dsServidor = ConstantesCRM.SVAZIO;
			listaServidorLength = "-1";
		}

		public void setServidorVO(SistemaServidorExistenteUsuarioVO servidorVO) {

			this.servidorVO = servidorVO;
		}

		public SistemaServidorExistenteUsuarioVO getServidorVO() {
			return this.servidorVO;
		}

		public void setListaServidorVO(SistemaServidorExistenteUsuarioVO[] listaServidorVO) {

			this.listaServidorVO = listaServidorVO;
		}

		public SistemaServidorExistenteUsuarioVO[] getListaServidorVO() {
			return this.listaServidorVO;
		}

		public void setListaServidor(String[] listaServidor) {

			this.listaServidor = listaServidor;
		}

		public String[] getListaServidor() {
			if (this.listaServidor == null || this.listaServidor.length == 0) {
				this.listaServidor = new String[1];
			}

			return this.listaServidor;
		}

		public void setIdServidor(String idServidor) {
			this.idServidor = idServidor;
		}

		public String getIdServidor() {
			return this.idServidor;
		}

		public void setDsServidor(String dsServidor) {
			this.dsServidor = dsServidor;
		}

		public String getDsServidor() {
			return this.dsServidor;
		}

		public void setListaServidorLength(String listaServidorLength) {
			this.listaServidorLength = listaServidorLength;
		}

		public String getListaServidorLength() {
			return this.listaServidorLength;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

	}

	public SistemaServidorExistenteUsuarioVO getServidorById(String id, SistemaServidorExistenteUsuarioVO[] servidores) {
		for (int i = 0; i < servidores.length; i++) {
			if (servidores[i].getIdServidor().equals(id)) {
				return servidores[i];
			}
		}
		return null;
	}

	public ManterServidorForm getManterServidorForm() {
		return this.manterServidorForm;
	}

}
