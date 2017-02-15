package usuario.Organograma.manterAtividadeOrganograma;

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
import br.com.vivo.fo.usuario.vo.AtividadeVODocument.AtividadeVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class OrganogramaAtividadeController extends AbstractAction {

	private static final long serialVersionUID = 4283137520861751394L;

	@EJB
	private Organograma controlOrganograma;

	public AtividadeVO atividadeVO;

	public ManterAtividadeForm manterAtividadeForm = new ManterAtividadeForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaAtividade".equals(mapping.getParameter())) {
			return salvaAtividade(mapping, form, request, response);
		} else if ("removeAtividade".equals(mapping.getParameter())) {
			return removeAtividade(mapping, form, request, response);
		} else if ("limpaAtividade".equals(mapping.getParameter())) {
			return limpaAtividade(mapping, form, request, response);
		} else if ("listaAtividade".equals(mapping.getParameter())) {
			return listaAtividade(mapping, form, request, response);
		} else if ("alteraIncluiAtividade".equals(mapping.getParameter())) {
			return alteraIncluiAtividade(mapping, form, request, response);
		} else if ("incluiAtividade".equals(mapping.getParameter())) {
			return incluiAtividade(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterAtividade.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("OrganogramaAtividadeController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterAtividadeForm = new ManterAtividadeForm();
		manterAtividadeForm.setMsgError(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterAtividade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaAtividade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterAtividadeForm form = (ManterAtividadeForm) formParam;

		log.debug("OrganogramaAtividadeController:salvaAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterAtividadeForm == null) {
			manterAtividadeForm = new ManterAtividadeForm();
		}
		manterAtividadeForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AtividadeVO atividadeVO = AtividadeVO.Factory.newInstance();
			atividadeVO.setNmAtividade(form.getNmAtividade());
			String idAtividade = form.getIdAtividade();

			if ((idAtividade.equals(ConstantesCRM.SVAZIO)) || (idAtividade == null)) {
				atividadeVO.setIdAtividade(ConstantesCRM.SVAZIO);
				manterAtividadeForm.setListaAtividadeVO(controlOrganograma.insertAtividade(atividadeVO, ConstantesCRM.getCurrentUser(request.getSession())).getAtividadeVOArray());
			} else {
				atividadeVO.setIdAtividade(idAtividade);
				manterAtividadeForm.setListaAtividadeVO(controlOrganograma.alteraAtividade(atividadeVO, ConstantesCRM.getCurrentUser(request.getSession())).getAtividadeVOArray());
			}
			manterAtividadeForm.setListaAtividadeLength(String.valueOf(manterAtividadeForm.getListaAtividadeVO().length));
			manterAtividadeForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaAtividadeController:salvaAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterAtividadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganogramaAtividadeController:salvaAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		manterAtividadeForm.setIdAtividade(ConstantesCRM.SVAZIO);
		manterAtividadeForm.setNmAtividade(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterAtividade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeAtividade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterAtividadeForm form = (ManterAtividadeForm) formParam;

		log.debug("OrganogramaAtividadeController:removeAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterAtividadeForm == null) {
			manterAtividadeForm = new ManterAtividadeForm();
		}

		manterAtividadeForm.setMsgError(ConstantesCRM.SVAZIO);

		try {

			AtividadeVO atividadeVO = AtividadeVO.Factory.newInstance();
			atividadeVO.setIdAtividade(form.getIdAtividade());
			atividadeVO.setNmAtividade(manterAtividadeForm.getNmAtividade());
			manterAtividadeForm.setListaAtividadeVO(controlOrganograma.removeAtividade(atividadeVO, ConstantesCRM.getCurrentUser(request.getSession())).getAtividadeVOArray());
			manterAtividadeForm.setListaAtividadeLength(String.valueOf(manterAtividadeForm.getListaAtividadeVO().length));
			manterAtividadeForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaAtividadeController:removeAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterAtividadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganogramaAtividadeController:removeAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		manterAtividadeForm.setIdAtividade(ConstantesCRM.SVAZIO);
		manterAtividadeForm.setNmAtividade(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterAtividade.jsp"
	 */
	public ActionForward limpaAtividade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("OrganogramaAtividadeController:limpaAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterAtividadeForm == null) {
			manterAtividadeForm = new ManterAtividadeForm();
		}
		manterAtividadeForm.setMsgError(ConstantesCRM.SVAZIO);
		manterAtividadeForm.setNmAtividade(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterAtividade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaAtividade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterAtividadeForm form = (ManterAtividadeForm) formParam;

		log.debug("OrganogramaAtividadeController:listaAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterAtividadeForm == null) {
			manterAtividadeForm = new ManterAtividadeForm();
		}
		manterAtividadeForm.setMsgError(ConstantesCRM.SVAZIO);
		try {

			AtividadeVO atividadeVO = AtividadeVO.Factory.newInstance();
			atividadeVO.setNmAtividade(form.getNmAtividade());

			if (form.getNmAtividade() != null) {
				atividadeVO.setNmAtividade(form.getNmAtividade());
			} else {
				atividadeVO.setNmAtividade(manterAtividadeForm.getNmAtividade());
			}

			atividadeVO.setIdAtividade(ConstantesCRM.SVAZIO);
			manterAtividadeForm.setNmAtividade(form.getNmAtividade());
			manterAtividadeForm.setNmAtividade(form.getNmAtividade());
			manterAtividadeForm.setListaAtividadeVO(controlOrganograma.getListaAtividade(atividadeVO, ConstantesCRM.getCurrentUser(request.getSession())).getAtividadeVOArray());
			manterAtividadeForm.setListaAtividadeLength(String.valueOf(manterAtividadeForm.getListaAtividadeVO().length));

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaAtividadeController:listaAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterAtividadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganogramaAtividadeController:listaAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		manterAtividadeForm.setIdAtividade(ConstantesCRM.SVAZIO);
		manterAtividadeForm.setNmAtividade(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alteraIncluiAtividade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alteraIncluiAtividade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("OrganogramaAtividadeController:alteraIncluiAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterAtividadeForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Recupero o ID do usuario clicado e converto p/ BigInteger
			String idAtividade = request.getParameter("idAtividade");

			// Recupero o array de usuarios do Form
			AtividadeVO[] arrayAtividade = manterAtividadeForm.getListaAtividadeVO();

			// Procura o AtividadeVO correspondente ao id recebido
			AtividadeVO AtividadeVO = manterAtividadeForm.getAtividadeVOById(idAtividade, arrayAtividade);

			// Popula a lista para a tela
			manterAtividadeForm.setListaAtividadeVO(arrayAtividade);

			// Popula dados para a tela
			manterAtividadeForm.setIdAtividade(AtividadeVO.getIdAtividade());
			manterAtividadeForm.setNmAtividade(AtividadeVO.getNmAtividade());

		} catch (Exception e) {
			log.error("OrganogramaAtividadeController:alteraIncluiAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="alteraIncluiAtividade.jsp"
	 */
	public ActionForward incluiAtividade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("OrganogramaAtividadeController:incluiAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterAtividadeForm.setIdAtividade(ConstantesCRM.SVAZIO);
		manterAtividadeForm.setNmAtividade(ConstantesCRM.SVAZIO);
		manterAtividadeForm.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ManterAtividadeForm extends ActionForm {

		private static final long serialVersionUID = -2547605002137057119L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String nmAtividade;
		private String listaAtividade;
		private String listaAtividadeLength;
		private AtividadeVO[] AtividadeVO;
		private AtividadeVO[] listaAtividadeVO;
		private String idAtividade;

		public AtividadeVO getAtividadeVOById(String id, AtividadeVO[] listaAtividadeVO) {
			for (int i = 0; i < listaAtividadeVO.length; i++) {
				if (listaAtividadeVO[i].getIdAtividade().equals(id)) {
					return listaAtividadeVO[i];
				}
			}
			return null;
		}

		public ManterAtividadeForm() {
			listaAtividadeVO = new AtividadeVO[0];
			AtividadeVO = new AtividadeVO[0];
		}

		public void setIdAtividade(String idAtividade) {
			this.idAtividade = idAtividade;
		}

		public String getIdAtividade() {
			return this.idAtividade;
		}

		public void setListaAtividadeVO(AtividadeVO[] listaAtividadeVO) {
			this.listaAtividadeVO = listaAtividadeVO;
		}

		public AtividadeVO[] getListaAtividadeVO() {
			return this.listaAtividadeVO;
		}

		public void setAtividadeVO(AtividadeVO[] AtividadeVO) {
			this.AtividadeVO = AtividadeVO;
		}

		public AtividadeVO[] getAtividadeVO() {
			return this.AtividadeVO;
		}

		public void setListaAtividadeLength(String listaAtividadeLength) {
			this.listaAtividadeLength = listaAtividadeLength;
		}

		public String getListaAtividadeLength() {
			return this.listaAtividadeLength;
		}

		public void setListaAtividade(String listaAtividade) {
			this.listaAtividade = listaAtividade;
		}

		public String getListaAtividade() {
			return this.listaAtividade;
		}

		public void setNmAtividade(String nmAtividade) {
			this.nmAtividade = nmAtividade;
		}

		public String getNmAtividade() {
			return this.nmAtividade;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public ManterAtividadeForm getManterAtividadeForm() {
		return this.manterAtividadeForm;
	}
}
