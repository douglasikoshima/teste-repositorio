package usuario.Organograma.manterTipoOrganizacao;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usuario.Organograma.manterOrganizacao.OrganizacaoController.FormOrganizacao;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.organogramaTO.OrganogramaTO;
import br.com.vivo.fo.ctrls.usuario.organogramaTO.OrganogramaTOImpl;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.TipoOrganizacaoVODocument.TipoOrganizacaoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class TipoOrganizacaoController extends AbstractAction {

	private static final long serialVersionUID = 5809843646794400497L;

	@EJB
	private OrganogramaTO controlOrganograma;

	@EJB
	public TipoOrganizacaoVO tipoOrganizacaoVO;

	@EJB
	public ManterTipoOrganizacaoForm manterTipoOrganizacaoForm;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaTipoOrganizacao".equals(mapping.getParameter())) {
			return salvaTipoOrganizacao(mapping, form, request, response);
		} else if ("removeTipoOrganizacao".equals(mapping.getParameter())) {
			return removeTipoOrganizacao(mapping, form, request, response);
		} else if ("limpaTipoOrganizacao".equals(mapping.getParameter())) {
			return limpaTipoOrganizacao(mapping, form, request, response);
		} else if ("listaTipoOrganizacao".equals(mapping.getParameter())) {
			return listaTipoOrganizacao(mapping, form, request, response);
		} else if ("alteraIncluiTipoOrganizacao".equals(mapping.getParameter())) {
			return alteraIncluiTipoOrganizacao(mapping, form, request, response);
		} else if ("incluiTipoOrganizacao".equals(mapping.getParameter())) {
			return incluiTipoOrganizacao(mapping, form, request, response);
		} 
		
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterTipoOrganizacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormOrganizacao form = (FormOrganizacao) formParam;

		log.debug("TipoOrganizacaoController:begin" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterTipoOrganizacaoForm = new ManterTipoOrganizacaoForm();
		manterTipoOrganizacaoForm.setMsgError(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterTipoOrganizacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaTipoOrganizacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterTipoOrganizacaoForm form = (ManterTipoOrganizacaoForm) formParam;

		log.debug("TipoOrganizacaoController:begin" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterTipoOrganizacaoForm == null) {
			manterTipoOrganizacaoForm = new ManterTipoOrganizacaoForm();
		}
		manterTipoOrganizacaoForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			TipoOrganizacaoVO tipoOrganizacaoVO = TipoOrganizacaoVO.Factory
			.newInstance();
			tipoOrganizacaoVO.setDsTipoOrganizacao(form.getDsTipoOrganizacao());

			String tipo = ConstantesCRM.SVAZIO;
			String idTipoOrganizacao = form.getIdTipoOrganizacao();

			if ((idTipoOrganizacao.equals(ConstantesCRM.SVAZIO)) || (idTipoOrganizacao == null)) {
				tipoOrganizacaoVO.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
				manterTipoOrganizacaoForm
				.setListaTipoOrganizacaoVO(controlOrganograma
						.insertTipoOrganizacao(
								tipoOrganizacaoVO,
								tipo,
								ConstantesCRM.getCurrentUser(request.getSession()))
										.getTipoOrganizacaoVOArray());
			} else {
				tipoOrganizacaoVO.setIdTipoOrganizacao(idTipoOrganizacao);
				manterTipoOrganizacaoForm
				.setListaTipoOrganizacaoVO(controlOrganograma
						.editarTipoOrganizacao(
								tipoOrganizacaoVO,
								ConstantesCRM.getCurrentUser(request.getSession()))
										.getTipoOrganizacaoVOArray());
			}

			manterTipoOrganizacaoForm.setListaTipoOrganizacaoLength(String
					.valueOf(manterTipoOrganizacaoForm
							.getListaTipoOrganizacaoVO().length));
			manterTipoOrganizacaoForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("TipoOrganizacaoController:begin" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " ) --> MENSAGEM --> "
					+ twe.getXmlHeader().getStatusText());
			manterTipoOrganizacaoForm.setMsgError(twe.getMessage().substring(
					twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error(
					"TipoOrganizacaoController:begin" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		manterTipoOrganizacaoForm.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
		manterTipoOrganizacaoForm.setDsTipoOrganizacao(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterTipoOrganizacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeTipoOrganizacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterTipoOrganizacaoForm form = (ManterTipoOrganizacaoForm) formParam;

		log.debug("TipoOrganizacaoController:removeTipoOrganizacao" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterTipoOrganizacaoForm == null) {
			manterTipoOrganizacaoForm = new ManterTipoOrganizacaoForm();
		}
		manterTipoOrganizacaoForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			TipoOrganizacaoVO tipoOrganizacaoVO = TipoOrganizacaoVO.Factory
			.newInstance();
			tipoOrganizacaoVO.setIdTipoOrganizacao(form.getIdTipoOrganizacao());
			tipoOrganizacaoVO.setDsTipoOrganizacao(manterTipoOrganizacaoForm
					.getDsTipoOrganizacao());

			manterTipoOrganizacaoForm
			.setListaTipoOrganizacaoVO(controlOrganograma
					.removeTipoOrganizacao(
							tipoOrganizacaoVO,
							ConstantesCRM.getCurrentUser(request.getSession()))
							.getTipoOrganizacaoVOArray());
			manterTipoOrganizacaoForm.setListaTipoOrganizacaoLength(String
					.valueOf(manterTipoOrganizacaoForm
							.getListaTipoOrganizacaoVO().length));
			manterTipoOrganizacaoForm.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {

			log.warn("TipoOrganizacaoController:removeTipoOrganizacao" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " ) --> MENSAGEM --> "
					+ twe.getXmlHeader().getStatusText());
			manterTipoOrganizacaoForm.setMsgError(twe.getMessage().substring(
					twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("TipoOrganizacaoController:removeTipoOrganizacao" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		manterTipoOrganizacaoForm.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
		manterTipoOrganizacaoForm.setDsTipoOrganizacao(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterTipoOrganizacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward limpaTipoOrganizacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterTipoOrganizacaoForm form = (ManterTipoOrganizacaoForm) formParam;

		log.debug("TipoOrganizacaoController:limpaTipoOrganizacao" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterTipoOrganizacaoForm == null) {
			manterTipoOrganizacaoForm = new ManterTipoOrganizacaoForm();
		}
		manterTipoOrganizacaoForm.setMsgError(ConstantesCRM.SVAZIO);
		manterTipoOrganizacaoForm.setDsTipoOrganizacao(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterTipoOrganizacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaTipoOrganizacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterTipoOrganizacaoForm form = (ManterTipoOrganizacaoForm) formParam;

		log.debug("TipoOrganizacaoController:listaTipoOrganizacao" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterTipoOrganizacaoForm == null) {
			manterTipoOrganizacaoForm = new ManterTipoOrganizacaoForm();
		}
		manterTipoOrganizacaoForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			TipoOrganizacaoVO tipoOrganizacaoVO = TipoOrganizacaoVO.Factory
			.newInstance();
			tipoOrganizacaoVO.setDsTipoOrganizacao(form.getDsTipoOrganizacao());
			tipoOrganizacaoVO.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
			manterTipoOrganizacaoForm.setDsTipoOrganizacao(form
					.getDsTipoOrganizacao());
			manterTipoOrganizacaoForm
			.setListaTipoOrganizacaoVO(controlOrganograma
					.getListaTipoOrganizacao(
							tipoOrganizacaoVO,
							ConstantesCRM.getCurrentUser(request.getSession()))
							.getTipoOrganizacaoVOArray());
			manterTipoOrganizacaoForm.setListaTipoOrganizacaoLength(String
					.valueOf(manterTipoOrganizacaoForm
							.getListaTipoOrganizacaoVO().length));

		} catch (TuxedoWarningException twe) {

			log.warn("TipoOrganizacaoController:listaTipoOrganizacao" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession())
					+ " ) --> MENSAGEM --> "
					+ twe.getXmlHeader().getStatusText());
			manterTipoOrganizacaoForm.setMsgError(twe.getXmlHeader()
					.getStatusText());

		} catch (Exception e) {
			log.error("TipoOrganizacaoController:listaTipoOrganizacao" + "( "
					+ ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		manterTipoOrganizacaoForm.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
		manterTipoOrganizacaoForm.setDsTipoOrganizacao(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alteraIncluiTipoOrganizacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alteraIncluiTipoOrganizacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterTipoOrganizacaoForm form = (ManterTipoOrganizacaoForm) formParam;

		log.debug("TipoOrganizacaoController:alteraIncluiTipoOrganizacao"
				+ "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterTipoOrganizacaoForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Recupero o ID do usuario clicado e converto p/ BigInteger
			String idUnidade = request.getParameter("idTipoOrganizacao");

			// Recupero o array de usuarios do Form
			TipoOrganizacaoVO[] arrayTipoOrganizacao = manterTipoOrganizacaoForm
			.getListaTipoOrganizacaoVO();

			// Procura o TipoOrganizacaoVO correspondente ao id recebido
			TipoOrganizacaoVO TipoOrganizacaoVO = manterTipoOrganizacaoForm
			.getTipoOrganizacaoVOById(idUnidade, arrayTipoOrganizacao);

			// Popula a lista para a tela
			manterTipoOrganizacaoForm
			.setListaTipoOrganizacaoVO(arrayTipoOrganizacao);

			// Popula dados para a tela
			manterTipoOrganizacaoForm.setIdTipoOrganizacao(TipoOrganizacaoVO
					.getIdTipoOrganizacao());
			manterTipoOrganizacaoForm.setDsTipoOrganizacao(TipoOrganizacaoVO
					.getDsTipoOrganizacao());

		} catch (Exception e) {
			log.error("TipoOrganizacaoController:alteraIncluiTipoOrganizacao"
					+ "( " + ConstantesCRM.getCurrentUser(request.getSession())
					+ " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alteraIncluiTipoOrganizacao.jsp"
	 */
	public ActionForward incluiTipoOrganizacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterTipoOrganizacaoForm form = (ManterTipoOrganizacaoForm) formParam;

		log.debug("TipoOrganizacaoController:incluiTipoOrganizacao" + "( "
				+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterTipoOrganizacaoForm.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
		manterTipoOrganizacaoForm.setDsTipoOrganizacao(ConstantesCRM.SVAZIO);
		manterTipoOrganizacaoForm.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ManterTipoOrganizacaoForm extends ActionForm {
		private String msgError = ConstantesCRM.SVAZIO;

		private String dsTipoOrganizacao;

		private String listaTipoOrganizacao;

		private String listaTipoOrganizacaoLength;

		private TipoOrganizacaoVO[] TipoOrganizacaoVO;

		private TipoOrganizacaoVO[] listaTipoOrganizacaoVO;

		private String listaLength;

		private String[] lista;

		private String idTipoOrganizacao;

		public TipoOrganizacaoVO getTipoOrganizacaoVOById(String id,
				TipoOrganizacaoVO[] listaTipoOrganizacaoVO) {
			for (int i = 0; i < listaTipoOrganizacaoVO.length; i++) {
				if (listaTipoOrganizacaoVO[i].getIdTipoOrganizacao().equals(id)) {
					return listaTipoOrganizacaoVO[i];
				}
			}
			return null;
		}

		public ManterTipoOrganizacaoForm() {
			listaTipoOrganizacaoVO = new TipoOrganizacaoVO[0];
			TipoOrganizacaoVO = new TipoOrganizacaoVO[0];
			dsTipoOrganizacao = ConstantesCRM.SVAZIO;
		}

		public void setIdTipoOrganizacao(String idTipoOrganizacao) {
			this.idTipoOrganizacao = idTipoOrganizacao;
		}

		public String getIdTipoOrganizacao() {
			return this.idTipoOrganizacao;
		}

		public void setListaTipoOrganizacaoVO(
				TipoOrganizacaoVO[] listaTipoOrganizacaoVO) {
			this.listaTipoOrganizacaoVO = listaTipoOrganizacaoVO;
		}

		public TipoOrganizacaoVO[] getListaTipoOrganizacaoVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize listaTipoOrganizacaoVO if it is null or length
			// == 0.
			// if(this.listaTipoOrganizacaoVO == null ||
			// this.listaTipoOrganizacaoVO.length == 0)
			// {
			// this.listaTipoOrganizacaoVO = new TipoOrganizacaoVO[1];
			// this.listaTipoOrganizacaoVO[0] = new TipoOrganizacaoVO(?);
			// }

			return this.listaTipoOrganizacaoVO;
		}

		public void setTipoOrganizacaoVO(TipoOrganizacaoVO[] TipoOrganizacaoVO) {
			this.TipoOrganizacaoVO = TipoOrganizacaoVO;
		}

		public TipoOrganizacaoVO[] getTipoOrganizacaoVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize TipoOrganizacaoVO if it is null.
			// if(this.TipoOrganizacaoVO == null)
			// {
			// this.TipoOrganizacaoVO = new TipoOrganizacaoVO(?);
			// }

			return this.TipoOrganizacaoVO;
		}

		public void setListaTipoOrganizacaoLength(
				String listaTipoOrganizacaoLength) {
			this.listaTipoOrganizacaoLength = listaTipoOrganizacaoLength;
		}

		public String getListaTipoOrganizacaoLength() {
			return this.listaTipoOrganizacaoLength;
		}

		public void setListaTipoOrganizacao(String listaTipoOrganizacao) {
			this.listaTipoOrganizacao = listaTipoOrganizacao;
		}

		public String getListaTipoOrganizacao() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.

			return this.listaTipoOrganizacao;
		}

		public void setDsTipoOrganizacao(String dsTipoOrganizacao) {
			this.dsTipoOrganizacao = dsTipoOrganizacao;
		}

		public String getDsTipoOrganizacao() {
			return this.dsTipoOrganizacao;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public ManterTipoOrganizacaoForm getManterTipoOrganizacaoForm() {
		return this.manterTipoOrganizacaoForm;
	}
}
