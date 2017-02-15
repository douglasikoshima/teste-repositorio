package usuario.manterSistema.editarParSistema.manterSubSistema;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterSistema.ManterSistemaFacade;
import br.com.vivo.fo.exception.FOException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterSubSistemaController extends AbstractAction {

	private static final long serialVersionUID = 2699322082649294656L;

	@EJB
	private ManterSistemaFacade controlSistema;

	// atributos e objetos
	private SubSistemasForm subSistemasForm = new SubSistemasForm();

	private static transient Logger log = new Logger("usuario");

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluirAlterarSubsistema".equals(mapping.getParameter())) {
			return incluirAlterarSubsistema(mapping, form, request, response);	
		} else if ("salvaSubSistema".equals(mapping.getParameter())) {
			return salvaSubSistema(mapping, form, request, response);
		} else if ("removeSubSistema".equals(mapping.getParameter())) {
			return removeSubSistema(mapping, form, request, response);
		} else if ("listaSubSistema".equals(mapping.getParameter())) {
			return listaSubSistema(mapping, form, request, response);
		} else if ("salvaEditaSubSistema".equals(mapping.getParameter())) {
			return salvaEditaSubSistema(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="manterSubSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			log.debug("ManterSubSistemaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

			subSistemasForm = new SubSistemasForm();
			subSistemasForm.setMsgError(ConstantesCRM.SVAZIO);

			// Recupera o id do sistema
			subSistemasForm.setIdSistema(request.getParameter("sistemaId"));

			subSistemasForm.setListaObjSubSistemas(new SubSistemaUsuarioVO[0]);
			subSistemasForm.setListaSubSistemaLength(ConstantesCRM.SZERO);

		} catch (Exception e) {
			log.error("ManterSubSistemaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
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
	 * @jpf:forward name="success" path="manterSubSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaSubSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SubSistemasForm form = (SubSistemasForm) formParam;
		log.debug("ManterSubSistemaController:salvaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		subSistemasForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			SubSistemasUsuarioVO listaSubSistemas = null;
			SubSistemasUsuarioVO subSistemaSalvar = SubSistemasUsuarioVO.Factory.newInstance();

			// Pega os dados do SubSistema a ser inserido ou editado
			SubSistemaUsuarioVO subSistema = SubSistemaUsuarioVO.Factory.newInstance();
			subSistema.setDsSubSistema(form.getDsSubSistema());
			subSistema.setIdSubSistema(form.getIdSubSistema());

			// Recupera o respectivo sistema
			SistemaIDVO sistemaIDVO = SistemaIDVO.Factory.newInstance();
			sistemaIDVO.setIdSistema(subSistemasForm.getIdSistema());

			// Grava no objeto correto os dados do subsistema e seu respectivo sistema
			subSistemaSalvar.setSistemaIDVO(sistemaIDVO);
			subSistemaSalvar.insertNewSubSistemaUsuarioVO(0);
			subSistemaSalvar.setSubSistemaUsuarioVOArray(0, subSistema);

			String tipo = request.getParameter("tipo");
			listaSubSistemas = controlSistema.salvaSubSistema(subSistemaSalvar, tipo, ConstantesCRM.getCurrentUser(request.getSession()));

			if (listaSubSistemas.getSubSistemaUsuarioVOArray().length > 0) {
				subSistemasForm.setListaObjSubSistemas(listaSubSistemas.getSubSistemaUsuarioVOArray());
			}
			subSistemasForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPaginaController:salvaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			subSistemasForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterSubSistemaController:salvaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="manterSubSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeSubSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterSubSistemaController:removeSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		subSistemasForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			SubSistemasUsuarioVO listaSubSistemas = null;
			String idSubSistema = request.getParameter("idSubSistema");
			SubSistemasUsuarioVO subSistemaComp = SubSistemasUsuarioVO.Factory.newInstance();

			// Grava o id do Sistema
			SistemaIDVO sistemaIDVO = SistemaIDVO.Factory.newInstance();
			sistemaIDVO.setIdSistema(subSistemasForm.getIdSistema());
			subSistemaComp.setSistemaIDVO(sistemaIDVO);

			// Pega os dados do SubSistema a ser inserido ou editado
			SubSistemaUsuarioVO subSistema = SubSistemaUsuarioVO.Factory.newInstance();
			subSistema.setIdSubSistema(idSubSistema);

			subSistemaComp.insertNewSubSistemaUsuarioVO(0);
			subSistemaComp.setSubSistemaUsuarioVOArray(0, subSistema);

			// Testa se existe o id do SubSistema
			if ((idSubSistema != ConstantesCRM.SVAZIO) && (idSubSistema != null)) {

				listaSubSistemas = controlSistema.removeSubSistema(subSistemaComp, ConstantesCRM.getCurrentUser(request.getSession()));

			} else {
				throw new FOException("ID do Sub-Sistema está vazio");
			}

			if (listaSubSistemas.getSubSistemaUsuarioVOArray().length > 0) {
				subSistemasForm.setListaObjSubSistemas(listaSubSistemas.getSubSistemaUsuarioVOArray());
			} else {
				subSistemasForm.setListaObjSubSistemas(new SubSistemaUsuarioVO[0]);
				subSistemasForm.setListaSubSistemaLength("-1");
			}
			subSistemasForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPaginaController:removeSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			subSistemasForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterSubSistemaController:removeSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="manterSubSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaSubSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SubSistemasForm form = (SubSistemasForm) formParam;

		log.debug("ManterSubSistemaController:listaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		subSistemasForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			SubSistemaUsuarioVO subSistemaUsuarioVO = SubSistemaUsuarioVO.Factory.newInstance();

			// subSistemaUsuarioVO.setIdSistema(request.getParameter("idSistema"));
			subSistemaUsuarioVO.setIdSistema(form.getIdSistema());
			subSistemaUsuarioVO.setDsSubSistema(form.getDsSubSistema());

			// busca todos os Sub-sistemas relacionados ao id digitado e ao parametro passado.
			SubSistemasUsuarioVO listaObjSubSistemas = controlSistema.listaSubSistemasPorSistemaPorSubSistemaPar(subSistemaUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// se algum resultado foi retornado, guarda os valores no formulario
			if (listaObjSubSistemas.getSubSistemaUsuarioVOArray().length > 0) {
				subSistemasForm.setListaObjSubSistemas(listaObjSubSistemas.getSubSistemaUsuarioVOArray());
				subSistemasForm.setListaSubSistemaLength(String.valueOf(listaObjSubSistemas.getSubSistemaUsuarioVOArray().length));
			} else {
				subSistemasForm.setListaSubSistemaLength("-1");
			}

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPaginaController:listaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			subSistemasForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {

			log.error("ManterSubSistemaController:listaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
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
	 * @jpf:forward name="success" path="incluirAlterarSubsistema.jsp"
	 */
	public ActionForward salvaEditaSubSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SubSistemasForm form = (SubSistemasForm) formParam;

		log.debug("ManterSubSistemaController:salvaEditaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		subSistemasForm.setMsgError(ConstantesCRM.SVAZIO);
		SubSistemaUsuarioVO subSistema = getSistemaById(form.getIdSubSistema(), subSistemasForm.getListaObjSubSistemas());

		subSistemasForm.setIdSubSistema(form.getIdSubSistema());
		subSistemasForm.setDsSubSistema(subSistema.getDsSubSistema());
		subSistemasForm.setMsgError(ConstantesCRM.SSucesso);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}
	
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarSubsistema.jsp"
	 */
	public ActionForward incluirAlterarSubsistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterSubSistemaController:incluirAlterarSubsistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		subSistemasForm.setMsgError(ConstantesCRM.SVAZIO);

		subSistemasForm.setIdSubSistema(ConstantesCRM.SVAZIO);
		subSistemasForm.setDsSubSistema(ConstantesCRM.SVAZIO);
		subSistemasForm.setMsgError(ConstantesCRM.SSucesso);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}


	public static class SubSistemasForm extends ActionForm {

		private static final long serialVersionUID = -2019624008724675741L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String listaSubSistemaLength;
		private String idSistema;
		private String dsSubSistema;
		private String idSubSistema;
		private SubSistemaUsuarioVO subSistema;
		private String[] listaStrSubSistemas;
		private SubSistemaUsuarioVO[] listaObjSubSistemas;

		public SubSistemasForm() {
			idSistema = ConstantesCRM.SVAZIO;
			dsSubSistema = ConstantesCRM.SVAZIO;
			idSubSistema = ConstantesCRM.SVAZIO;
			subSistema = SubSistemaUsuarioVO.Factory.newInstance();
			listaStrSubSistemas = new String[0];
			listaObjSubSistemas = new SubSistemaUsuarioVO[0];
		}

		public void setListaObjSubSistemas(SubSistemaUsuarioVO[] listaObjSubSistemas) {

			this.listaObjSubSistemas = listaObjSubSistemas;
		}

		public SubSistemaUsuarioVO[] getListaObjSubSistemas() {

			return this.listaObjSubSistemas;
		}

		public void setListaStrSubSistemas(String[] listaStrSubSistemas) {

			this.listaStrSubSistemas = listaStrSubSistemas;
		}

		public String[] getListaStrSubSistemas() {

			if (this.listaStrSubSistemas == null || this.listaStrSubSistemas.length == 0) {
				this.listaStrSubSistemas = new String[1];
			}

			return this.listaStrSubSistemas;
		}

		public void setSubSistema(SubSistemaUsuarioVO subSistema) {
			this.subSistema = subSistema;
		}

		public SubSistemaUsuarioVO getSubSistema() {

			return this.subSistema;
		}

		public void setIdSistema(String idSistema) {

			this.idSistema = idSistema;
		}

		public String getIdSistema() {

			return this.idSistema;
		}

		public void setIdSubSistema(String idSubSistema) {
			this.idSubSistema = idSubSistema;
		}

		public String getIdSubSistema() {
			return this.idSubSistema;
		}

		public void setDsSubSistema(String dsSubSistema) {
			this.dsSubSistema = dsSubSistema;
		}

		public String getDsSubSistema() {
			return this.dsSubSistema;
		}

		public void setListaSubSistemaLength(String listaSubSistemaLength) {
			this.listaSubSistemaLength = listaSubSistemaLength;
		}

		public String getListaSubSistemaLength() {
			return this.listaSubSistemaLength;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public SubSistemasForm getSubSistemasForm() {

		return (this.subSistemasForm);
	}

	public SubSistemaUsuarioVO getSistemaById(String id, SubSistemaUsuarioVO[] listaSubSistemas) {

		for (int i = 0; i < listaSubSistemas.length; i++) {
			if (listaSubSistemas[i].getIdSubSistema() != null && listaSubSistemas[i].getIdSubSistema().equals(id)) {
				return listaSubSistemas[i];
			}
		}
		return null;
	}
	

}
