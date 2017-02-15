package usuario.Organograma.manterCargoOrganograma;

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
import br.com.vivo.fo.usuario.vo.CargoAtividadeRelacionamentoVODocument.CargoAtividadeRelacionamentoVO;
import br.com.vivo.fo.usuario.vo.CargoVODocument.CargoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;


public class OrganogramaCargoController extends AbstractAction {

	private static final long serialVersionUID = -5970815543030166309L;

	@EJB
	private Organograma controlOrganograma;

	public CargoVO cargoVO;

	public ManterCargoForm manterCargoForm = new ManterCargoForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaCargo".equals(mapping.getParameter())) {
			return salvaCargo(mapping, form, request, response);
		} else if ("removeCargo".equals(mapping.getParameter())) {
			return removeCargo(mapping, form, request, response);
		} else if ("limpaCargo".equals(mapping.getParameter())) {
			return limpaCargo(mapping, form, request, response);
		} else if ("listaCargo".equals(mapping.getParameter())) {
			return listaCargo(mapping, form, request, response);
		} else if ("editaRelCargo".equals(mapping.getParameter())) {
			return editaRelCargo(mapping, form, request, response);
		} else if ("salvaRelacion".equals(mapping.getParameter())) {
			return salvaRelacion(mapping, form, request, response);
		} else if ("alteraIncluiCargo".equals(mapping.getParameter())) {
			return alteraIncluiCargo(mapping, form, request, response);
		} else if ("relacionarCargoAtividade".equals(mapping.getParameter())) {
			return relacionarCargoAtividade(mapping, form, request, response);
		}

		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterCargo.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("OrganogramaCargoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {

			if (request.getParameter("isMenu") == null || manterCargoForm == null) {
				manterCargoForm = new ManterCargoForm();
			}

			manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);
			manterCargoForm.setIdCargo(ConstantesCRM.SVAZIO);
			manterCargoForm.setNmCargo(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("OrganogramaCargoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="manterCargo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaCargo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterCargoForm form = (ManterCargoForm) formParam;

		log.debug("OrganogramaCargoController:salvaCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterCargoForm == null) {
			manterCargoForm = new ManterCargoForm();
		}
		manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);
		cargoVO = CargoVO.Factory.newInstance();

		try {

			cargoVO.setIdCargo(form.getIdCargo());
			cargoVO.setNmCargo(form.getNmCargo());
			String idCargo = cargoVO.getIdCargo();

			if ((idCargo.equals(ConstantesCRM.SVAZIO)) || (idCargo == null)) {
				cargoVO.setIdCargo(ConstantesCRM.SVAZIO);
				manterCargoForm.setListaCargoVO(controlOrganograma.insertCargo(cargoVO, ConstantesCRM.getCurrentUser(request.getSession())).getCargoVOArray());
			} else {
				cargoVO.setIdCargo(idCargo);
				manterCargoForm.setListaCargoVO(controlOrganograma.alteraCargo(cargoVO, ConstantesCRM.getCurrentUser(request.getSession())).getCargoVOArray());
			}
			manterCargoForm.setListaCargoLength(String.valueOf(manterCargoForm.getListaCargoVO().length));
			manterCargoForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaCargoController:salvaCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterCargoForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("OrganogramaCargoController:salvaCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		manterCargoForm.setIdCargo(ConstantesCRM.SVAZIO);
		manterCargoForm.setNmCargo(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterCargo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeCargo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterCargoForm form = (ManterCargoForm) formParam;

		log.debug("OrganogramaCargoController:removeCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterCargoForm == null) {
			manterCargoForm = new ManterCargoForm();
		}

		manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);
		cargoVO = CargoVO.Factory.newInstance();

		try {

			cargoVO.setIdCargo(form.getIdCargo());
			cargoVO.setNmCargo(manterCargoForm.getNmCargo());
			manterCargoForm.setListaCargoVO(controlOrganograma.removeCargo(cargoVO, ConstantesCRM.getCurrentUser(request.getSession())).getCargoVOArray());
			manterCargoForm.setListaCargoLength(String.valueOf(manterCargoForm.getListaCargoVO().length));
			manterCargoForm.setIdCargo(ConstantesCRM.SVAZIO);
			manterCargoForm.setNmCargo(ConstantesCRM.SVAZIO);
			manterCargoForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaCargoController:removeCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterCargoForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganogramaCargoController:removeCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		manterCargoForm.setIdCargo(ConstantesCRM.SVAZIO);
		manterCargoForm.setNmCargo(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterCargo.jsp"
	 */
	public ActionForward limpaCargo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("OrganogramaCargoController:limpaCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterCargoForm == null) {
			manterCargoForm = new ManterCargoForm();
		}

		manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);
		manterCargoForm.setNmCargo(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterCargo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaCargo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterCargoForm form = (ManterCargoForm) formParam;

		log.debug("OrganogramaCargoController:listaCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterCargoForm == null) {
			manterCargoForm = new ManterCargoForm();
		}
		manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);
		cargoVO = CargoVO.Factory.newInstance();

		try {

			cargoVO.setNmCargo(form.getNmCargo());
			if (form.getNmCargo() != null) {
				cargoVO.setNmCargo(form.getNmCargo());
			} else {
				cargoVO.setNmCargo(manterCargoForm.getNmCargo());
			}

			manterCargoForm.setNmCargo(form.getNmCargo());
			cargoVO.setIdCargo(ConstantesCRM.SVAZIO);
			manterCargoForm.setListaCargoVO(controlOrganograma.getListaCargo(cargoVO, ConstantesCRM.getCurrentUser(request.getSession())).getCargoVOArray());
			manterCargoForm.setListaCargoLength(String.valueOf(manterCargoForm.getListaCargoVO().length));

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaCargoController:listaCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterCargoForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("OrganogramaCargoController:listaCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		manterCargoForm.setIdCargo(ConstantesCRM.SVAZIO);
		manterCargoForm.setNmCargo(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="editaRelCargo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaRelCargo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterCargoForm form = (ManterCargoForm) formParam;

		log.debug("OrganogramaCargoController:editaRelCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterCargoForm == null) {
			manterCargoForm = new ManterCargoForm();
		}
		manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Recupero o ID do usuario clicado e converto p/ BigInteger
			String idCargo = request.getParameter("idCargo");

			// Recupero o array de usuarios do Form
			CargoVO[] arrayCargo = manterCargoForm.getListaCargoVO();

			// Procura o CargoVO correspondente ao id recebido
			CargoVO cargoVO = manterCargoForm.getCargoVOById(idCargo, arrayCargo);

			// Popula a lista para a tela
			manterCargoForm.setListaCargoVO(arrayCargo);

			// Popula dados para a tela
			manterCargoForm.setIdCargo(cargoVO.getIdCargo());
			manterCargoForm.setNmCargo(cargoVO.getNmCargo());

			manterCargoForm.setListaCargoVO(controlOrganograma.getListaCargo(cargoVO, ConstantesCRM.getCurrentUser(request.getSession())).getCargoVOArray());
			manterCargoForm.setCargoVO(manterCargoForm.getListaCargoVO());
			manterCargoForm.setListaCargoLength(String.valueOf(manterCargoForm.getListaCargoVO().length));

			CargoAtividadeRelacionamentoVO cargoAtividadeRel = CargoAtividadeRelacionamentoVO.Factory.newInstance();
			cargoAtividadeRel = controlOrganograma.listaRelacionarCargoAtividade(cargoVO, ConstantesCRM.getCurrentUser(request.getSession()));
			manterCargoForm.setArrayAtividadesExistentesVO(cargoAtividadeRel.getExistentes().getAtividadeVOArray());
			manterCargoForm.setArrayAtividadesRelacionadosVO(cargoAtividadeRel.getRelacionados().getAtividadeVOArray());

			form.setArrayAtividadesExistentesVO(cargoAtividadeRel.getExistentes().getAtividadeVOArray());
			form.setArrayAtividadesRelacionadosVO(cargoAtividadeRel.getRelacionados().getAtividadeVOArray());

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaCargoController:editaRelCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterCargoForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganogramaCargoController:editaRelCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarCargoAtividade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaRelacion(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterCargoForm form = (ManterCargoForm) formParam;

		log.debug("OrganogramaCargoController:salvaRelacion" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterCargoForm == null) {
			manterCargoForm = new ManterCargoForm();
		}
		manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);

		try {

			String lista[] = form.getArraySelecaoRelacionados();
			CargoAtividadeRelacionamentoVO cargoAtividadeRelVO = CargoAtividadeRelacionamentoVO.Factory.newInstance();
			if (lista.length > 0) {
				cargoAtividadeRelVO.addNewRelacionados();
			}

			for (int i = 0; i < form.getArraySelecaoRelacionados().length; i++) {
				if (lista[i] != null) {
					if (!lista[i].trim().equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
						AtividadeVO atividadeVO = AtividadeVO.Factory.newInstance();
						atividadeVO.setIdAtividade(lista[i]);
						atividadeVO.setNmAtividade(ConstantesCRM.SVAZIO);
						cargoAtividadeRelVO.getRelacionados().insertNewAtividadeVO(i);
						cargoAtividadeRelVO.getRelacionados().setAtividadeVOArray(i, atividadeVO);
					}
				}
			}

			cargoAtividadeRelVO.setIdCargo(manterCargoForm.getIdCargo());
			CargoAtividadeRelacionamentoVO relacionamento = CargoAtividadeRelacionamentoVO.Factory.newInstance();
			relacionamento = controlOrganograma.salvaCargoAtividadeRelacionados(cargoAtividadeRelVO, ConstantesCRM.getCurrentUser(request.getSession()));
			manterCargoForm.setArrayAtividadesExistentesVO(relacionamento.getExistentes().getAtividadeVOArray());
			manterCargoForm.setArrayAtividadesRelacionadosVO(relacionamento.getRelacionados().getAtividadeVOArray());
			manterCargoForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaCargoController:salvaRelacion" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterCargoForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganogramaCargoController:salvaRelacion" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="alteraIncluiCargo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alteraIncluiCargo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("OrganogramaCargoController:alteraIncluiCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Recupero o ID do usuario clicado e converto p/ BigInteger
			String idCargo = request.getParameter("idCargo");

			// Recupero o array de usuarios do Form
			CargoVO[] arrayCargo = manterCargoForm.getListaCargoVO();

			// Procura o CargoVO correspondente ao id recebido
			CargoVO cargoVO = manterCargoForm.getCargoVOById(idCargo, arrayCargo);

			// Popula a lista para a tela
			manterCargoForm.setListaCargoVO(arrayCargo);

			// Popula dados para a tela
			manterCargoForm.setIdCargo(cargoVO.getIdCargo());
			manterCargoForm.setNmCargo(cargoVO.getNmCargo());

		} catch (Exception e) {
			log.error("OrganogramaCargoController:alteraIncluiCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="relacionarCargoAtividade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward relacionarCargoAtividade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterCargoForm form = (ManterCargoForm) formParam;

		log.debug("OrganogramaCargoController:relacionarCargoAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (manterCargoForm == null) {
			manterCargoForm = new ManterCargoForm();
		}

		manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			String cargoId = request.getParameter("idCargo");

			if (cargoId == null) {
				cargoId = form.getIdCargo();
			}

			String cargoNm = request.getParameter("nmCargo");

			if (cargoNm == null) {
				cargoNm = form.getNmCargo();
			}
			manterCargoForm.setIdCargo(cargoId);

			manterCargoForm.setNmCargo(cargoNm);
			CargoVO cargoVO = CargoVO.Factory.newInstance();
			cargoVO.setIdCargo(cargoId);
			cargoVO.setNmCargo(cargoNm);

			manterCargoForm.setListaCargoVO(controlOrganograma.getListaCargo(cargoVO, ConstantesCRM.getCurrentUser(request.getSession())).getCargoVOArray());
			manterCargoForm.setCargoVO(manterCargoForm.getListaCargoVO());
			manterCargoForm.setListaCargoLength(String.valueOf(manterCargoForm.getListaCargoVO().length));

			CargoAtividadeRelacionamentoVO cargoAtividadeRel = CargoAtividadeRelacionamentoVO.Factory.newInstance();
			cargoAtividadeRel = controlOrganograma.listaRelacionarCargoAtividade(cargoVO, ConstantesCRM.getCurrentUser(request.getSession()));
			manterCargoForm.setArrayAtividadesExistentesVO(cargoAtividadeRel.getExistentes().getAtividadeVOArray());
			manterCargoForm.setArrayAtividadesRelacionadosVO(cargoAtividadeRel.getRelacionados().getAtividadeVOArray());

			form.setArrayAtividadesExistentesVO(cargoAtividadeRel.getExistentes().getAtividadeVOArray());
			form.setArrayAtividadesRelacionadosVO(cargoAtividadeRel.getRelacionados().getAtividadeVOArray());

		} catch (TuxedoWarningException twe) {

			log.warn("OrganogramaCargoController:relacionarCargoAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			manterCargoForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("OrganogramaCargoController:relacionarCargoAtividade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="alteraIncluiCargo.jsp"
	 */
	public ActionForward incluiCargo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("OrganogramaCargoController:incluiCargo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterCargoForm.setIdCargo(ConstantesCRM.SVAZIO);
		manterCargoForm.setNmCargo(ConstantesCRM.SVAZIO);
		manterCargoForm.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ManterCargoForm extends ActionForm {

		private static final long serialVersionUID = -4203322320389345497L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String[] arraySelecaoRelacionados;
		private String[] arraySelecaoExistentes;
		private AtividadeVO[] arrayAtividadesRelacionadosVO;
		private AtividadeVO[] arrayAtividadesExistentesVO;
		private String indicativoOperacao;
		private String nmCargo;
		private String listaCargo;
		private String listaCargoLength;
		private CargoVO[] cargoVO;
		private CargoVO[] listaCargoVO;
		private String idCargo;

		public CargoVO getCargoVOById(String id, CargoVO[] listaCargoVO) {
			for (int i = 0; i < listaCargoVO.length; i++) {
				if (listaCargoVO[i].getIdCargo().equals(id)) {
					return listaCargoVO[i];
				}
			}
			return null;
		}

		public ManterCargoForm() {
			listaCargoVO = new CargoVO[0];
			cargoVO = new CargoVO[0];
			nmCargo = ConstantesCRM.SVAZIO;
		}

		public void setIdCargo(String idCargo) {
			this.idCargo = idCargo;
		}

		public String getIdCargo() {
			return this.idCargo;
		}

		public void setListaCargoVO(CargoVO[] listaCargoVO) {
			this.listaCargoVO = listaCargoVO;
		}

		public CargoVO[] getListaCargoVO() {
			return this.listaCargoVO;
		}

		public void setCargoVO(CargoVO[] cargoVO) {
			this.cargoVO = cargoVO;
		}

		public CargoVO[] getCargoVO() {
			return this.cargoVO;
		}

		public void setListaCargoLength(String listaCargoLength) {
			this.listaCargoLength = listaCargoLength;
		}

		public String getListaCargoLength() {
			return this.listaCargoLength;
		}

		public void setListaCargo(String listaCargo) {
			this.listaCargo = listaCargo;
		}

		public String getListaCargo() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.

			return this.listaCargo;
		}

		public void setNmCargo(String nmCargo) {
			this.nmCargo = nmCargo;
		}

		public String getNmCargo() {
			return this.nmCargo;
		}

		public void setIndicativoOperacao(String indicativoOperacao) {
			this.indicativoOperacao = indicativoOperacao;
		}

		public String getIndicativoOperacao() {
			return this.indicativoOperacao;
		}

		public void setArrayAtividadesExistentesVO(AtividadeVO[] arrayAtividadesExistentesVO) {
			this.arrayAtividadesExistentesVO = arrayAtividadesExistentesVO;
		}

		public AtividadeVO[] getArrayAtividadesExistentesVO() {
			return this.arrayAtividadesExistentesVO;
		}

		public void setArrayAtividadesRelacionadosVO(AtividadeVO[] arrayAtividadesRelacionadosVO) {
			this.arrayAtividadesRelacionadosVO = arrayAtividadesRelacionadosVO;
		}

		public AtividadeVO[] getArrayAtividadesRelacionadosVO() {
			return this.arrayAtividadesRelacionadosVO;
		}

		public void setArraySelecaoExistentes(String[] arraySelecaoExistentes) {
			this.arraySelecaoExistentes = arraySelecaoExistentes;
		}

		public String[] getArraySelecaoExistentes() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arraySelecaoExistentes == null || this.arraySelecaoExistentes.length == 0) {
				this.arraySelecaoExistentes = new String[1];
			}

			return this.arraySelecaoExistentes;
		}

		public void setArraySelecaoRelacionados(String[] arraySelecaoRelacionados) {
			this.arraySelecaoRelacionados = arraySelecaoRelacionados;
		}

		public String[] getArraySelecaoRelacionados() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arraySelecaoRelacionados == null || this.arraySelecaoRelacionados.length == 0) {
				this.arraySelecaoRelacionados = new String[1];
			}

			return this.arraySelecaoRelacionados;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public ManterCargoForm getManterCargoForm() {
		return this.manterCargoForm;
	}
}
