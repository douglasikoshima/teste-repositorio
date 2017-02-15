package usuario.Organograma.manterUnidadeOrganograma;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.organograma.Organograma;
import br.com.vivo.fo.ctrls.usuario.organograma.OrganogramaImpl;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UnidadeOrganogramaVODocument;
import br.com.vivo.fo.usuario.vo.UnidadeOrganogramaVODocument.UnidadeOrganogramaVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class OrganogramaUnidadeController extends AbstractAction {

	private static final long serialVersionUID = 8776533682670819103L;

	/**
	 * @common:control
	 */
	@EJB
	private Organograma controlOrganograma;

	public UnidadeOrganogramaVO unidadeVO = UnidadeOrganogramaVO.Factory.newInstance();

	public ManterUnidadeForm manterUnidadeForm = new ManterUnidadeForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaUnidade".equals(mapping.getParameter())) {
			return salvaUnidade(mapping, form, request, response);
		} else if ("removeUnidade".equals(mapping.getParameter())) {
			return removeUnidade(mapping, form, request, response);
		} else if ("limpaUnidade".equals(mapping.getParameter())) {
			return limpaUnidade(mapping, form, request, response);
		} else if ("listaUnidade".equals(mapping.getParameter())) {
			return listaUnidade(mapping, form, request, response);
		} else if ("alteraIncluiUnidade".equals(mapping.getParameter())) {
			return alteraIncluiUnidade(mapping, form, request, response);
		} else if ("incluiUnidade".equals(mapping.getParameter())) {
			return incluiUnidade(mapping, form, request, response);
		} 
		
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("OrganogramaUnidadeController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterUnidadeForm = new ManterUnidadeForm();
		manterUnidadeForm.setMsgError(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterUnidadeForm form = (ManterUnidadeForm) formParam;

		log.debug("OrganogramaUnidadeController:salvaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterUnidadeForm == null) {
			manterUnidadeForm = new ManterUnidadeForm();
		}
		manterUnidadeForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			UnidadeOrganogramaVO unidadeVO = UnidadeOrganogramaVO.Factory.newInstance();
			unidadeVO.setNmUnidade(form.getNmUnidade());

			String tipo = ConstantesCRM.SVAZIO;
			String idUnidade = form.getIdUnidade();

			if ((idUnidade.equals(ConstantesCRM.SVAZIO)) || (idUnidade == null)) {
				unidadeVO.setIdUnidade(ConstantesCRM.SVAZIO);
				tipo = "novo";
			} else {
				unidadeVO.setIdUnidade(idUnidade);
				tipo = "edicao";
			}
			manterUnidadeForm.setListaUnidadeVO(controlOrganograma.insertUnidade(unidadeVO, tipo, ConstantesCRM.getCurrentUser(request.getSession())).getUnidadeOrganogramaVOArray());
			manterUnidadeForm.setListaUnidadeLength(String.valueOf(manterUnidadeForm.getListaUnidadeVO().length));
			manterUnidadeForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaUnidadeController:salvaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterUnidadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganogramaUnidadeController:salvaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		manterUnidadeForm.setIdUnidade(ConstantesCRM.SVAZIO);
		manterUnidadeForm.setNmUnidade(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterUnidadeForm form = (ManterUnidadeForm) formParam;

		log.debug("OrganogramaUnidadeController:removeUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterUnidadeForm == null) {
			manterUnidadeForm = new ManterUnidadeForm();
		}
		manterUnidadeForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			UnidadeOrganogramaVO unidadeVO = UnidadeOrganogramaVO.Factory.newInstance();
			unidadeVO.setIdUnidade(form.getIdUnidade());
			unidadeVO.setNmUnidade(manterUnidadeForm.getNmUnidade());

			manterUnidadeForm.setListaUnidadeVO(controlOrganograma.removeUnidade(unidadeVO, ConstantesCRM.getCurrentUser(request.getSession())).getUnidadeOrganogramaVOArray());
			manterUnidadeForm.setListaUnidadeLength(String.valueOf(manterUnidadeForm.getListaUnidadeVO().length));
			manterUnidadeForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaUnidadeController:removeUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterUnidadeForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("OrganogramaUnidadeController:removeUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		manterUnidadeForm.setIdUnidade(ConstantesCRM.SVAZIO);
		manterUnidadeForm.setNmUnidade(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 */
	public ActionForward limpaUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterUnidadeForm form = (ManterUnidadeForm) formParam;

		log.debug("OrganogramaUnidadeController:limpaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterUnidadeForm == null) {
			manterUnidadeForm = new ManterUnidadeForm();
		}
		manterUnidadeForm.setNmUnidade(ConstantesCRM.SVAZIO);
		manterUnidadeForm.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterUnidadeForm form = (ManterUnidadeForm) formParam;

		log.debug("OrganogramaUnidadeController:listaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterUnidadeForm == null) {
			manterUnidadeForm = new ManterUnidadeForm();
		}
		manterUnidadeForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			UnidadeOrganogramaVO unidadeVO = UnidadeOrganogramaVO.Factory.newInstance();
			if (form.getNmUnidade() != null) {
				unidadeVO.setNmUnidade(form.getNmUnidade());
			} else
				unidadeVO.setNmUnidade(manterUnidadeForm.getNmUnidade());

			unidadeVO.setIdUnidade(ConstantesCRM.SVAZIO);
			manterUnidadeForm.setNmUnidade(unidadeVO.getNmUnidade());
			manterUnidadeForm.setListaUnidadeVO(controlOrganograma.getListaUnidade(unidadeVO, ConstantesCRM.getCurrentUser(request.getSession())).getUnidadeOrganogramaVOArray());
			manterUnidadeForm.setListaUnidadeLength(String.valueOf(manterUnidadeForm.getListaUnidadeVO().length));

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaUnidadeController:listaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterUnidadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganogramaUnidadeController:listaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		manterUnidadeForm.setIdUnidade(ConstantesCRM.SVAZIO);
		manterUnidadeForm.setNmUnidade(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alteraIncluiUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alteraIncluiUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterUnidadeForm form = (ManterUnidadeForm) formParam;

		log.debug("OrganogramaUnidadeController:alteraIncluiUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterUnidadeForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Recupero o ID do usuario clicado e converto p/ BigInteger
			String idUnidade = request.getParameter("idUnidade");

			// Recupero o array de usuarios do Form
			UnidadeOrganogramaVO[] arrayUnidadeOrganograma = manterUnidadeForm.getListaUnidadeVO();

			// Procura o unidadeOrganogramaVO correspondente ao id recebido
			UnidadeOrganogramaVO unidadeOrganogramaVO = manterUnidadeForm.getUnidadeOrganogramaVOById(idUnidade, arrayUnidadeOrganograma);

			// Popula a lista para a tela
			manterUnidadeForm.setListaUnidadeVO(arrayUnidadeOrganograma);

			// Popula dados para a tela
			manterUnidadeForm.setIdUnidade(unidadeOrganogramaVO.getIdUnidade());
			manterUnidadeForm.setNmUnidade(unidadeOrganogramaVO.getNmUnidade());
			manterUnidadeForm.setMsgError(ConstantesCRM.SSucesso);
		} catch (Exception e) {
			log.error("OrganogramaUnidadeController:alteraIncluiUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="alteraIncluiUnidade.jsp"
	 */
	public ActionForward incluiUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterUnidadeForm form = (ManterUnidadeForm) formParam;

		log.debug("OrganogramaUnidadeController:incluiUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterUnidadeForm.setIdUnidade(ConstantesCRM.SVAZIO);
		manterUnidadeForm.setNmUnidade(ConstantesCRM.SVAZIO);
		manterUnidadeForm.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ManterUnidadeForm extends ActionForm {
		private String msgError = ConstantesCRM.SVAZIO;

		private String nmUnidade;

		private String listaUnidade;

		private String listaUnidadeLength;

		private UnidadeOrganogramaVO[] UnidadeOrganogramaVO;

		private UnidadeOrganogramaVO[] listaUnidadeVO;

		private String idUnidade;

		public UnidadeOrganogramaVO getUnidadeOrganogramaVOById(String id, UnidadeOrganogramaVO[] organograma) {
			for (int i = 0; i < organograma.length; i++) {
				if (organograma[i].getIdUnidade().equals(id)) {
					return organograma[i];
				}
			}
			return null;
		}

		public ManterUnidadeForm() {
			listaUnidadeVO = new UnidadeOrganogramaVO[0];
			UnidadeOrganogramaVO = new UnidadeOrganogramaVO[0];
			nmUnidade = ConstantesCRM.SVAZIO;
		}

		public void setIdUnidade(String idUnidade) {
			this.idUnidade = idUnidade;
		}

		public String getIdUnidade() {
			return this.idUnidade;
		}

		public void setListaUnidadeVO(UnidadeOrganogramaVO[] listaUnidadeVO) {
			this.listaUnidadeVO = listaUnidadeVO;
		}

		public UnidadeOrganogramaVO[] getListaUnidadeVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize listaUnidadeVO if it is null or length == 0.
			// if(this.listaUnidadeVO == null || this.listaUnidadeVO.length ==
			// 0)
			// {
			// this.listaUnidadeVO = new UnidadeVO[1];
			// this.listaUnidadeVO[0] = new UnidadeVO(?);
			// }

			return this.listaUnidadeVO;
		}

		public void setUnidadeVO(UnidadeOrganogramaVO[] unidadeVO) {
			this.UnidadeOrganogramaVO = unidadeVO;
		}

		public UnidadeOrganogramaVO[] getUnidadeVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize UnidadeVO if it is null.
			// if(this.UnidadeVO == null)
			// {
			// this.UnidadeVO = new UnidadeVO(?);
			// }

			return this.UnidadeOrganogramaVO;
		}

		public void setListaUnidadeLength(String listaUnidadeLength) {
			this.listaUnidadeLength = listaUnidadeLength;
		}

		public String getListaUnidadeLength() {
			return this.listaUnidadeLength;
		}

		public void setListaUnidade(String listaUnidade) {
			this.listaUnidade = listaUnidade;
		}

		public String getListaUnidade() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.

			return this.listaUnidade;
		}

		public void setNmUnidade(String nmUnidade) {
			this.nmUnidade = nmUnidade;
		}

		public String getNmUnidade() {
			return this.nmUnidade;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

	}

	public ManterUnidadeForm getManterUnidadeForm() {
		return this.manterUnidadeForm;
	}
}
