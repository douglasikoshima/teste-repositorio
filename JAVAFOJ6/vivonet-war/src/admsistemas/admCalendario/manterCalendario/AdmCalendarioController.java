package admsistemas.admCalendario.manterCalendario;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmDescricaoFeriadoVODocument.AdmDescricaoFeriadoVO;
import br.com.vivo.fo.admsistemas.vo.AdmFeriadoVODocument.AdmFeriadoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoFeriadoVODocument.AdmTipoFeriadoVO;
import br.com.vivo.fo.admsistemas.vo.MunicipioVODocument.MunicipioVO;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.calendario.CalendarioFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings("rawtypes")
public class AdmCalendarioController extends AbstractAction {

	private static final long serialVersionUID = 6526012322913369820L;

	@EJB
	private CalendarioFacade controlCalendario;

	private FormCalendario formCalendario = null;
	private AdmCalendarioContainerVO admCalendarioContainerVOVoltar;

	protected global.Global globalApp = new global.Global();

	private static Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisarFeriado".equals(mapping.getParameter())) {
			return pesquisarFeriado(mapping, form, request, response);
		} else if ("montaListaUF".equals(mapping.getParameter())) {
			return montaListaUF(mapping, form, request, response);
		} else if ("carregaInseri".equals(mapping.getParameter())) {
			return carregaInseri(mapping, form, request, response);
		} else if ("incluirFeriado".equals(mapping.getParameter())) {
			return incluirFeriado(mapping, form, request, response);
		} else if ("removerFeriado".equals(mapping.getParameter())) {
			return removerFeriado(mapping, form, request, response);
		} else if ("salvarAlterarFeriado".equals(mapping.getParameter())) {
			return salvarAlterarFeriado(mapping, form, request, response);
		} else if ("carregarParametrosMunicipal".equals(mapping.getParameter())) {
			return carregarParametrosMunicipal(mapping, form, request, response);
		} else if ("carregarParametrosUF".equals(mapping.getParameter())) {
			return carregarParametrosUF(mapping, form, request, response);
		} else if ("gravaManutencaoUF".equals(mapping.getParameter())) {
			return gravaManutencaoUF(mapping, form, request, response);
		} else if ("carregaUFRelacionada".equals(mapping.getParameter())) {
			return carregaUFRelacionada(mapping, form, request, response);
		} else if ("gravaManutencaoMunicipal".equals(mapping.getParameter())) {
			return gravaManutencaoMunicipal(mapping, form, request, response);
		} else if ("retornoTelaMunicipio".equals(mapping.getParameter())) {
			return retornoTelaMunicipio(mapping, form, request, response);
		} else if ("carregaEditar".equals(mapping.getParameter())) {
			return carregaEditar(mapping, form, request, response);
		} else if ("retornoTelaInicial".equals(mapping.getParameter())) {
			return retornoTelaInicial(mapping, form, request, response);
		} else if ("limpaForm".equals(mapping.getParameter())) {
			return limpaForm(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:begin()");
		try {
			if (formCalendario == null || request.getParameter("isMenu") == null) {
				formCalendario = new FormCalendario();
				formCalendario.setContadorAdmFeriadoLenght("-1");
				formCalendario.setIndicativoUF("-1");
			} else if (request.getParameter("isMenu").equalsIgnoreCase("false")) {
				formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SONE);
				formCalendario.setMsgError(ConstantesCRM.SVAZIO);
			}
			AdmCalendarioContainerVO calendario = controlCalendario.carregaTelaInsere(ConstantesCRM.getCurrentUser(request.getSession()));

			formCalendario.setAdmCalendarioContainerVO(calendario);
			formCalendario.setAdmDescricaoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmDescricaoFeriadoVOArray());
			formCalendario.setAdmTipoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmTipoFeriadoVOArray());

			formCalendario.setCmbCombo(calendario);
			// formCalendario.setListaMunicipiosVO(formCalendario.getAdmCalendarioContainerVO().getMunicipioVOArray());
			formCalendario.setListaUFs(formCalendario.getAdmCalendarioContainerVO().getUFVOArray());
			// formCalendario.setListaAdmFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="pesquisarFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisarFeriado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:pesquisarFeriado()");
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();
		FormCalendario form = (FormCalendario) formParam;
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);

		try {

			formCalendario.setAdmCalendarioContainerVO(controlCalendario.carregaTelaInsere(ConstantesCRM.getCurrentUser(request.getSession())));
			formCalendario.setAdmDescricaoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmDescricaoFeriadoVOArray());
			formCalendario.setAdmTipoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmTipoFeriadoVOArray());
			// formCalendario.setListaMunicipiosVO(formCalendario.getAdmCalendarioContainerVO().getMunicipioVOArray());
			formCalendario.setListaUFs(formCalendario.getAdmCalendarioContainerVO().getUFVOArray());
			// formCalendario.setListaAdmFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());
			formCalendario.setContadorAdmFeriadoLenght("-1");
			formCalendario.setIndicativoUF("-1");

			formCalendario.setIdTipoFeriado(form.getAdmTipoFeriado());
			formCalendario.setIndMovel(form.getIndMovel());
			formCalendario.setDsFeriado(form.getDsFeriado());

			if (request.getParameter("dtFeriado") != null) {
				formCalendario.setDtFeriado(request.getParameter("dtFeriado"));
			}

			formCalendario.setInRelatorio(form.getInRelatorio());
			formCalendario.setIdUF(form.getIdUF());
			formCalendario.setIdMunicipio(form.getIdMunicipio());
			formCalendario.setAdmTipoFeriado(form.getAdmTipoFeriado());
			formCalendario.setIndicativoUF(ConstantesCRM.SONE);

			admFeriadoVO.setDsFeriado(form.getDsFeriado());
			if (!form.getAdmTipoFeriado().equalsIgnoreCase("000")) {
				admFeriadoVO.setIdTipoFeriado(form.getAdmTipoFeriado());
			} else {
				admFeriadoVO.setIdTipoFeriado(ConstantesCRM.SVAZIO);
			}
			if (form.getDtFeriado() != null && !form.getDtFeriado().equalsIgnoreCase("000")) {
				admFeriadoVO.setDtFeriado(form.getDtFeriado());
			}
			if (form.getIndMovel() != null && !form.getIndMovel().equalsIgnoreCase("000")) {
				admFeriadoVO.setIndMovel(form.getIndMovel());
			}
			if (form.getInRelatorio() != null && !form.getInRelatorio().equalsIgnoreCase("000"))
			{
				admFeriadoVO.setInRelatorio(form.getInRelatorio());
				// if(!form.getIdUF().equalsIgnoreCase("000")){
				// admFeriadoVO.setIdUf(form.getIdUF());
				// formCalendario.setIdUF("000");
				// if(!form.getIdMunicipio().equalsIgnoreCase("000")){
				// admFeriadoVO.setIdMunicipio(form.getIdMunicipio());
				// MunicipioVO[] municipioVO = new MunicipioVO[0];
				// formCalendario.setListaMunicipiosVO(municipioVO);
				// }else{
				// admFeriadoVO.setIdMunicipio(ConstantesCRM.SVAZIO);
				// }
				// }else{
				// admFeriadoVO.setIdUf(ConstantesCRM.SVAZIO);
				// admFeriadoVO.setIdMunicipio(ConstantesCRM.SVAZIO);
				// }
			}

			// if(!form.getIndMovel().equalsIgnoreCase("000")){
			// admFeriadoVO.setIndMovel(form.getIndMovel());
			// }else{
			// admFeriadoVO.setIndMovel(ConstantesCRM.SVAZIO);
			// }

			// if(!form.getInRelatorio().equalsIgnoreCase("000")){
			// admFeriadoVO.setInRelatorio(form.getInRelatorio());
			// }else{
			// admFeriadoVO.setInRelatorio(ConstantesCRM.SVAZIO);
			// }

			if (form.getIdMunicipio() == null || form.getIdMunicipio().equals("000")) {
				form.setIdMunicipio(ConstantesCRM.SVAZIO);
				formCalendario.setIdMunicipio(ConstantesCRM.SVAZIO);
			}

			admCalendarioContainerVO.addNewMunicipioVO();
			admCalendarioContainerVO.getMunicipioVOArray(0).setIdMunicipio(form.getIdMunicipio());

			admCalendarioContainerVO.addNewCmbCombo();
			admCalendarioContainerVO.getCmbComboArray(0).setDescricao(form.getAno());

			formCalendario.setAno(form.getAno());
			formCalendario.setIdMunicipio(form.getIdMunicipio());

			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);
			// formCalendario = new FormCalendario();

			// *********************************************

			// ArrayList listFeriado = new ArrayList();

			formCalendario.setListFeriado(controlCalendario.listaFeriados(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));

			// *********************************************

			// formCalendario.setAdmCalendarioContainerVO(controlCalendario.listaFeriados(admCalendarioContainerVO,ConstantesCRM.getCurrentUser(request.getSession()));
			// admCalendarioContainerVOVoltar=formCalendario.getAdmCalendarioContainerVO();
			// formCalendario.setListaAdmFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());

			if (formCalendario.getListFeriado().size() > 0) {
				formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SONE);
			} else {
				formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SZERO);
			}
			formCalendario.setIndicativoUF(ConstantesCRM.SONE);

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:pesquisarFeriado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:pesquisarFeriado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="pesquisarFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaListaUF(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:montaListaUF()");
		FormCalendario form = (FormCalendario) formParam;
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();

		try {

			// formCalendario = new FormCalendario();
			formCalendario.setAdmCalendarioContainerVO(controlCalendario.carregaTelaInsere(ConstantesCRM.getCurrentUser(request.getSession())));
			formCalendario.setAdmDescricaoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmDescricaoFeriadoVOArray());
			formCalendario.setAdmTipoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmTipoFeriadoVOArray());
			// formCalendario.setListaMunicipiosVO(formCalendario.getAdmCalendarioContainerVO().getMunicipioVOArray());
			formCalendario.setListaUFs(formCalendario.getAdmCalendarioContainerVO().getUFVOArray());
			// formCalendario.setListaAdmFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());
			formCalendario.setContadorAdmFeriadoLenght("-1");
			formCalendario.setIndicativoUF("-1");

			formCalendario.setAno(form.getAno());
			formCalendario.setDtFeriado(form.getDtFeriado());

			admFeriadoVO.setIdUf(form.getIdUF());
			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);
			formCalendario.setAdmCalendarioContainerVO(controlCalendario.montaListaUF(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formCalendario.setListaMunicipiosVO(formCalendario.getAdmCalendarioContainerVO().getMunicipioVOArray());
			formCalendario.setDtFeriado(form.getDtFeriado());
			formCalendario.setDsFeriado(form.getDsFeriado());
			formCalendario.setIdTipoFeriado(form.getAdmTipoFeriado());
			formCalendario.setIndMovel(form.getIndMovel());
			formCalendario.setInRelatorio(form.getInRelatorio());
			formCalendario.setAdmTipoFeriado(form.getAdmTipoFeriado());
			formCalendario.setIdUF(form.getIdUF());
			formCalendario.setIndicativoUF(ConstantesCRM.SONE);

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:montaListaUF(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:montaListaUF(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="incluirFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaInseri(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		log.debug("AdmCalendarioController:carregaInseri()");
		FormCalendario form = (FormCalendario) formParam;
		try {
			formCalendario = form;
			formCalendario.setAdmCalendarioContainerVO(controlCalendario.carregaTelaInsere(ConstantesCRM.getCurrentUser(request.getSession())));
			formCalendario.setAdmDescricaoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmDescricaoFeriadoVOArray());
			formCalendario.setAdmTipoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmTipoFeriadoVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:carregaInseri(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:carregaInseri(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="pesquisarFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirFeriado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:incluirFeriado()");
		FormCalendario form = (FormCalendario) formParam;
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();

		try {
			admFeriadoVO.setDtFeriado(form.getDtFeriado());
			if (form.getAdmDescricaoExistente().equals("000")) {
				admFeriadoVO.setDsFeriado(form.getAdmDescricaoNova());
			} else {
				admFeriadoVO.setIdDsFeriado(form.getAdmDescricaoExistente());
			}

			admFeriadoVO.setIndMovel(form.getIndMovel());
			admFeriadoVO.setIdTipoFeriado(form.getAdmTipoFeriado());
			admFeriadoVO.setInRelatorio(form.getInRelatorio());
			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);
			// formCalendario = new FormCalendario();
			formCalendario.setAdmCalendarioContainerVO(controlCalendario.salvaFeriado(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formCalendario.setListaAdmFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());
			formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SONE);
			formCalendario.setIndicativoUF(ConstantesCRM.SONE);

			AdmCalendarioContainerVO calendario = controlCalendario.carregaTelaInsere(ConstantesCRM.getCurrentUser(request.getSession()));
			formCalendario.setCmbCombo(calendario);

			formCalendario.getAdmCalendarioContainerVO().setUFVOArray(calendario.getUFVOArray());
			formCalendario.setListaUFs(formCalendario.getAdmCalendarioContainerVO().getUFVOArray());

			formCalendario.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:incluirFeriado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:incluirFeriado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="retornoTelaInicial.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removerFeriado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:removerFeriado()");

		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();
		try {
			if (request.getParameter("idFeriado") != null) {
				admFeriadoVO.setIdFeriado(request.getParameter("idFeriado"));
			}

			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);

			controlCalendario.removeFeriado(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));

			formCalendario.getAdmCalendarioContainerVO().setAdmFeriadoVOArray(formCalendario.getListaAdmFeriadoVO());
			formCalendario.setListaAdmFeriadoVO(revomeFeriadoLista(admFeriadoVO.getIdFeriado(), formCalendario.getAdmCalendarioContainerVO()));
			formCalendario.getAdmCalendarioContainerVO().setAdmFeriadoVOArray(formCalendario.getListaAdmFeriadoVO());

			formCalendario.setAdmDescricaoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmDescricaoFeriadoVOArray());
			formCalendario.setAdmTipoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmTipoFeriadoVOArray());
			formCalendario.setListaUFs(formCalendario.getAdmCalendarioContainerVO().getUFVOArray());

			if (formCalendario.getListaAdmFeriadoVO().length != 0) {
				formCalendario.setContadorAdmFeriadoLenght(String.valueOf(formCalendario.getListaAdmFeriadoVO().length));
			} else {
				formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SVAZIO);
			}

			formCalendario.setIndicativoUF(String.valueOf(formCalendario.getListaAdmFeriadoVO().length));
			formCalendario.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:removerFeriado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:removerFeriado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="success" path="retornoTelaInicial.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarAlterarFeriado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:salvarAlterarFeriado()");
		FormCalendario form = (FormCalendario) formParam;
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();

		try {
			String radioNovo = (request.getParameter("descr") != null) ? request.getParameter("descr") : ConstantesCRM.SVAZIO;
			if (request.getParameter("idFeriado") != null) {
				admFeriadoVO.setIdFeriado(request.getParameter("idFeriado"));
			}

			admFeriadoVO.setDtFeriado(form.getDtFeriado());

			if (radioNovo.equals(ConstantesCRM.SZERO)) {
				admFeriadoVO.setDsFeriado(form.getAdmDescricaoNova());
			} else if (radioNovo.equals(ConstantesCRM.SONE)) {
				admFeriadoVO.setIdDsFeriado(form.getAdmDescricaoExistente());
			}
			admFeriadoVO.setIndMovel(form.getIndMovel());
			admFeriadoVO.setIdTipoFeriado(form.getAdmTipoFeriado());
			admFeriadoVO.setInRelatorio(form.getInRelatorio());
			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);
			// formCalendario = new FormCalendario();
			formCalendario.setAdmCalendarioContainerVO(controlCalendario.salvaFeriadoEditado(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formCalendario.setListaAdmFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());
			formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SONE);
			formCalendario.setIndicativoUF(ConstantesCRM.SONE);
			formCalendario.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:salvarAlterarFeriado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:salvarAlterarFeriado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="manterParametros.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregarParametrosMunicipal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:carregarParametrosMunicipal()");
		FormCalendario form = (FormCalendario) formParam;
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		formCalendario.setIdUF(ConstantesCRM.SVAZIO);
		try {

			AdmFeriadoVO feriado = getFeriadoById(form.getIdFeriadoParam(), formCalendario.getListFeriado());

			admFeriadoVO.setIdDsFeriado(feriado.getIdDsFeriado());
			admFeriadoVO.setDsFeriado(feriado.getDsFeriado());
			admFeriadoVO.setIdTipoFeriado(feriado.getIdTipoFeriado());
			admFeriadoVO.setInRelatorio(feriado.getInRelatorio());
			admFeriadoVO.setIndMovel(feriado.getIndMovel());
			admFeriadoVO.setDsTipoFeriado(feriado.getDsTipoFeriado());
			admFeriadoVO.setIdFeriado(feriado.getIdFeriado());
			admFeriadoVO.setDtFeriado(feriado.getDtFeriado());

			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);

			formCalendario.setIdDsFeriadoParam(feriado.getIdDsFeriado());
			formCalendario.setDsFeriadoParam(feriado.getDsFeriado());
			formCalendario.setIdTipoFeriadoParam(feriado.getIdTipoFeriado());
			formCalendario.setInRelatorioParam(feriado.getInRelatorio());
			formCalendario.setIndMovelParam(feriado.getIndMovel());
			formCalendario.setDsTipoFeriadoParam(feriado.getDsTipoFeriado());
			formCalendario.setIdFeriadoParam(feriado.getIdFeriado());
			formCalendario.setDtFeriadoParam(feriado.getDtFeriado());
			formCalendario.setIndicativoMunicipal(ConstantesCRM.SZERO);

			formCalendario.setAdmCalendarioContainerVO(controlCalendario.carregaTelaInsere(ConstantesCRM.getCurrentUser(request.getSession())));

			if (formCalendario.getAdmCalendarioContainerVO().getUFVOArray() != null) {
				formCalendario.setListaUFs(formCalendario.getAdmCalendarioContainerVO().getUFVOArray());
			} else {
				formCalendario.setListaUFs(new UFVO[0]);
			}

			if (formCalendario.getListaUfsVOExistentes() == null) {
				formCalendario.setListaUfsVOExistentes(new UFVO[0]);
			}

			if (formCalendario.getListaUfsVORelacionadas() == null) {
				formCalendario.setListaUfsVORelacionadas(new UFVO[0]);
			}

			formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SZERO);

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:carregarParametrosMunicipal(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:carregarParametrosMunicipal(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="success" path="manterParametros.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregarParametrosUF(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:carregarParametrosUF()");
		FormCalendario form = (FormCalendario) formParam;
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmFeriadoVO feriado = getFeriadoById(form.getIdFeriadoParam(), formCalendario.getListFeriado());

			admFeriadoVO.setIdDsFeriado(feriado.getIdDsFeriado());
			admFeriadoVO.setDsFeriado(feriado.getDsFeriado());
			admFeriadoVO.setIdTipoFeriado(feriado.getIdTipoFeriado());
			admFeriadoVO.setInRelatorio(feriado.getInRelatorio());
			admFeriadoVO.setIndMovel(feriado.getIndMovel());
			admFeriadoVO.setDsTipoFeriado(feriado.getDsTipoFeriado());
			admFeriadoVO.setIdFeriado(feriado.getIdFeriado());
			admFeriadoVO.setDtFeriado(feriado.getDtFeriado());
			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);

			formCalendario.setIdDsFeriadoParam(feriado.getIdDsFeriado());
			formCalendario.setDsFeriadoParam(feriado.getDsFeriado());
			formCalendario.setIdTipoFeriadoParam(feriado.getIdTipoFeriado());
			formCalendario.setInRelatorioParam(feriado.getInRelatorio());
			formCalendario.setIndMovelParam(feriado.getIndMovel());
			formCalendario.setDsTipoFeriadoParam(feriado.getDsTipoFeriado());
			formCalendario.setIdFeriadoParam(feriado.getIdFeriado());
			formCalendario.setDtFeriadoParam(feriado.getDtFeriado());
			formCalendario.setAdmCalendarioContainerVO(controlCalendario.carregaUFsPorID(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formCalendario.setListaUFVOExistente(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(0).getExistentes().getUFVOArray());
			formCalendario.setListaUFVORelacionados(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(0).getRelacionados().getUFVOArray());
			formCalendario.setIndicativoMunicipal("1");

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:carregarParametrosUF(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:carregarParametrosUF(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="success" path="manterParametros.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gravaManutencaoUF(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:gravaManutencaoUF()");
		FormCalendario form = (FormCalendario) formParam;
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();

		try {
			UFVO uFVO[] = new UFVO[form.getArrayUFVORelacionado().length];
			String[] uf = new String[form.getArrayUFVORelacionado().length];
			uf = form.getArrayUFVORelacionado();
			formCalendario.setMsgError(ConstantesCRM.SVAZIO);
			admFeriadoVO.setIdDsFeriado(formCalendario.getIdDsFeriadoParam());
			admFeriadoVO.setDsFeriado(formCalendario.getDsFeriadoParam());
			admFeriadoVO.setIdTipoFeriado(formCalendario.getIdTipoFeriadoParam());
			admFeriadoVO.setInRelatorio(formCalendario.getInRelatorioParam());
			admFeriadoVO.setIndMovel(formCalendario.getIndMovelParam());
			admFeriadoVO.setDsTipoFeriado(formCalendario.getDsTipoFeriadoParam());
			admFeriadoVO.setIdFeriado(formCalendario.getIdFeriadoParam());
			admFeriadoVO.setDtFeriado(formCalendario.getDtFeriadoParam());

			for (int i = 0; i < form.getArrayUFVORelacionado().length; i++) {
				uFVO[i] = UFVO.Factory.newInstance();
				uFVO[i].setIdUF(uf[i]);

			}
			admFeriadoVO.addNewRelacionados();
			admFeriadoVO.getRelacionados().setUFVOArray(uFVO);
			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);

			formCalendario.setAdmCalendarioContainerVO(controlCalendario.salvaRelacionamentoPorUF(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			controlCalendario.salvaRelacionamentoPorUF(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));
			formCalendario.setListaUFVOExistente(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(0).getExistentes().getUFVOArray());
			formCalendario.setListaUFVORelacionados(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(0).getRelacionados().getUFVOArray());
			formCalendario.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:gravaManutencaoUF(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:gravaManutencaoUF(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="success" path="manterParametros.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaUFRelacionada(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:carregaUFRelacionada()");
		FormCalendario form = (FormCalendario) formParam;
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		try {
			formCalendario.setIdUF(form.getIdUF());
			admFeriadoVO.setIdUf(form.getIdUF());
			admFeriadoVO.setIdFeriado(formCalendario.getIdFeriadoParam());
			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);
			formCalendario.setAdmCalendarioContainerVO(controlCalendario.carregaMunicipiosPorID(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formCalendario.setListaMunicipiosExistentesVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(0).getExistentes().getMunicipioVOArray());
			formCalendario.setListaMunicipiosRelacionadosVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(0).getRelacionados().getMunicipioVOArray());
			formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SONE);

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:carregaUFRelacionada(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:carregaUFRelacionada(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="success" path="manterParametros.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gravaManutencaoMunicipal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		log.debug("AdmCalendarioController:gravaManutencaoMunicipal()");
		FormCalendario form = (FormCalendario) formParam;
		AdmFeriadoVO admFeriadoVO = AdmFeriadoVO.Factory.newInstance();
		AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();
		try {
			MunicipioVO municipioVO[] = new MunicipioVO[form.getArrayMunicipioRelacionado().length];
			String[] municipio = new String[form.getArrayMunicipioRelacionado().length];
			municipio = form.getArrayMunicipioRelacionado();
			formCalendario.setMsgError(ConstantesCRM.SVAZIO);
			admFeriadoVO.setIdDsFeriado(formCalendario.getIdDsFeriadoParam());
			admFeriadoVO.setDsFeriado(formCalendario.getDsFeriadoParam());
			admFeriadoVO.setIdTipoFeriado(formCalendario.getIdTipoFeriadoParam());
			admFeriadoVO.setInRelatorio(formCalendario.getInRelatorioParam());
			admFeriadoVO.setIndMovel(formCalendario.getIndMovelParam());
			admFeriadoVO.setDsTipoFeriado(formCalendario.getDsTipoFeriadoParam());
			admFeriadoVO.setIdFeriado(formCalendario.getIdFeriadoParam());
			admFeriadoVO.setDtFeriado(formCalendario.getDtFeriadoParam());
			admFeriadoVO.setIdUf(formCalendario.getIdUF());

			for (int i = 0; i < form.getArrayMunicipioRelacionado().length; i++) {
				municipioVO[i] = MunicipioVO.Factory.newInstance();
				municipioVO[i].setIdMunicipio(municipio[i]);

			}
			admFeriadoVO.addNewRelacionados();
			admFeriadoVO.getRelacionados().setMunicipioVOArray(municipioVO);
			admCalendarioContainerVO.insertNewAdmFeriadoVO(0);
			admCalendarioContainerVO.setAdmFeriadoVOArray(0, admFeriadoVO);

			formCalendario.setAdmCalendarioContainerVO(controlCalendario.salvaRelacionamentoPorMunicipio(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));

			formCalendario.setListaMunicipiosExistentesVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(0).getExistentes().getMunicipioVOArray());
			formCalendario.setListaMunicipiosRelacionadosVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(0).getRelacionados().getMunicipioVOArray());
			formCalendario.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:gravaManutencaoMunicipal(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:gravaManutencaoMunicipal(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="success" path="pesquisarFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward retornoTelaMunicipio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);
		log.debug("AdmCalendarioController:retornoTelaMunicipio()");
		try {
			formCalendario.setAdmCalendarioContainerVO(admCalendarioContainerVOVoltar);
			formCalendario.setListaAdmFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());
			// formCalendario.setAdmDescricaoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmDescricaoFeriadoVOArray());
			// formCalendario.setAdmTipoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmTipoFeriadoVOArray());
			// formCalendario.setListaUFs(formCalendario.getAdmCalendarioContainerVO().getUFVOArray());

			if (formCalendario.getListaAdmFeriadoVO().length > 0) {
				formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SONE);
			} else {
				formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SZERO);
			}
			formCalendario.setIndicativoUF(ConstantesCRM.SONE);

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:retornoTelaMunicipio(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="success" path="incluirFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaEditar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:carregaEditar()");
		FormCalendario form = (FormCalendario) formParam;
		formCalendario.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// formCalendario = form;
			formCalendario.setIdFeriado(form.getIdFeriado());
			AdmFeriadoVO feriado = getFeriadoById(form.getIdFeriado(), formCalendario.getListFeriado());

			request.setAttribute("idFeriado", feriado.getIdFeriado());
			request.setAttribute("idDsFeriado", feriado.getIdDsFeriado());
			request.setAttribute("dsFeriado", feriado.getDsFeriado());
			request.setAttribute("dtFeriado", feriado.getDtFeriado());
			request.setAttribute("idTipoFeriado", feriado.getIdTipoFeriado());
			request.setAttribute("inRelatorio", feriado.getInRelatorio());
			request.setAttribute("indMovel", feriado.getIndMovel());
			request.setAttribute("dsTipoFeriado", feriado.getDsTipoFeriado());

			formCalendario.setAdmCalendarioContainerVO(controlCalendario.carregaTelaInsere(ConstantesCRM.getCurrentUser(request.getSession())));
			formCalendario.setAdmDescricaoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmDescricaoFeriadoVOArray());
			formCalendario.setAdmTipoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmTipoFeriadoVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:carregaEditar(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:carregaEditar(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="success" path="pesquisarFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward retornoTelaInicial(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmCalendarioController:retornoTelaInicial()");
		try {
			formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SONE);
			AdmCalendarioContainerVO calendario = controlCalendario.carregaTelaInsere(ConstantesCRM.getCurrentUser(request.getSession()));

			formCalendario.setAdmCalendarioContainerVO(calendario);
			formCalendario.setAdmDescricaoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmDescricaoFeriadoVOArray());
			formCalendario.setAdmTipoFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmTipoFeriadoVOArray());

			formCalendario.setCmbCombo(calendario);
			// formCalendario.setListaMunicipiosVO(formCalendario.getAdmCalendarioContainerVO().getMunicipioVOArray());
			formCalendario.setListaUFs(formCalendario.getAdmCalendarioContainerVO().getUFVOArray());
			// formCalendario.setListaAdmFeriadoVO(formCalendario.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - AdmCalendarioController:retornoTelaInicial(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formCalendario.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("Exception - AdmCalendarioController:retornoTelaInicial(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="success" path="pesquisarFeriado.jsp"
	 */
	public ActionForward limpaForm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		formCalendario.setDsFeriado(ConstantesCRM.SVAZIO);
		formCalendario.setAno(ConstantesCRM.SVAZIO);
		formCalendario.setIndMovel(ConstantesCRM.SVAZIO);
		formCalendario.setInRelatorio(ConstantesCRM.SVAZIO);
		formCalendario.setIdUF(ConstantesCRM.SVAZIO);
		formCalendario.setIdMunicipio(ConstantesCRM.SVAZIO);
		formCalendario.setIndicativoUF(ConstantesCRM.SZERO);
		formCalendario.setContadorAdmFeriadoLenght(ConstantesCRM.SVAZIO);
		formCalendario.setAdmTipoFeriado(ConstantesCRM.SVAZIO);

		formCalendario.setListaAdmFeriadoVO(null);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormCalendario extends ActionForm {

		private static final long serialVersionUID = 1415488106029596371L;

		private ArrayList listFeriado;
		private AdmCalendarioContainerVO cmbCombo;
		private String ano;
		private String msgError = ConstantesCRM.SVAZIO;
		private UFVO[] listaUFVORelacionados;
		private UFVO[] listaUFVOExistente;
		private String indicativoMunicipal;
		private String dtFeriadoParam;
		private String idFeriadoParam;
		private String dsTipoFeriadoParam;
		private String indMovelParam;
		private String inRelatorioParam;
		private String idTipoFeriadoParam;
		private String dsFeriadoParam;
		private String idDsFeriadoParam;
		private String dsTipoFeriado;
		private String admDescricaoExistente;
		private String admDescricaoNova;
		private String admTipoFeriado;
		private String indicativoUF;
		private String idUF;
		private UFVO[] listaUFs;
		private String idMunicipio;
		private String[] arrayAnoCopia;
		private String[] arrayAnoBase;
		private String[] arrayMunicipioRelacionado;
		private String[] arrayMunicipioExistente;
		private String[] arrayUFVORelacionado;
		private String[] arrayUFVOExistente;
		private String contadorAdmFeriadoLenght;
		private String indMovel;
		private String inRelatorio;
		private String dsFeriado;
		private String idDsFeriado;
		private String dtFeriado;
		private String idFeriado;
		private MunicipioVO[] listaMunicipiosRelacionadosVO;
		private MunicipioVO[] listaMunicipiosExistentesVO;
		private AdmFeriadoVO[] listaAdmFeriadoVO;
		private MunicipioVO[] municipioVO;
		private UFVO[] uFVO;
		private AdmTipoFeriadoVO[] admTipoFeriadoVO;
		private AdmDescricaoFeriadoVO[] admDescricaoFeriadoVO;
		private AdmCalendarioContainerVO admCalendarioContainerVO;
		private UFVO[] listaUfsVORelacionadas;
		private UFVO[] listaUfsVOExistentes;
		private String idTipoFeriado;
		private MunicipioVO[] listaMunicipiosVO;

		public FormCalendario() {
			listaUfsVOExistentes = new UFVO[0];
			listaUfsVORelacionadas = new UFVO[0];
			listaMunicipiosVO = new MunicipioVO[0];
			listaUFs = new UFVO[0];
			listaAdmFeriadoVO = new AdmFeriadoVO[0];
			admTipoFeriadoVO = new AdmTipoFeriadoVO[0];
			listaMunicipiosVO = new MunicipioVO[0];
			listaMunicipiosRelacionadosVO = new MunicipioVO[0];
			listaMunicipiosExistentesVO = new MunicipioVO[0];
		}

		public void setListaMunicipiosVO(MunicipioVO[] listaMunicipiosVO) {

			this.listaMunicipiosVO = listaMunicipiosVO;
		}

		public MunicipioVO[] getListaMunicipiosVO() {
			if (this.listaMunicipiosVO == null || this.listaMunicipiosVO.length == 0) {
				this.listaMunicipiosVO = new MunicipioVO[1];
				this.listaMunicipiosVO[0] = MunicipioVO.Factory.newInstance();
			}
			return this.listaMunicipiosVO;
		}

		public void setListaUfsVOExistentes(UFVO[] listaUfsVOExistentes) {
			this.listaUfsVOExistentes = listaUfsVOExistentes;
		}

		public UFVO[] getListaUfsVOExistentes() {
			return this.listaUfsVOExistentes;
		}

		public void setListaUfsVORelacionadas(UFVO[] listaUfsVORelacionadas) {

			this.listaUfsVORelacionadas = listaUfsVORelacionadas;
		}

		public UFVO[] getListaUfsVORelacionadas() {
			return this.listaUfsVORelacionadas;
		}

		public void setAdmCalendarioContainerVO(AdmCalendarioContainerVO admCalendarioContainerVO) {
			this.admCalendarioContainerVO = admCalendarioContainerVO;
		}

		public AdmCalendarioContainerVO getAdmCalendarioContainerVO() {
			return this.admCalendarioContainerVO;
		}

		public void setAdmDescricaoFeriadoVO(AdmDescricaoFeriadoVO[] admDescricaoFeriadoVO) {
			this.admDescricaoFeriadoVO = admDescricaoFeriadoVO;
		}

		public AdmDescricaoFeriadoVO[] getAdmDescricaoFeriadoVO() {
			return this.admDescricaoFeriadoVO;
		}

		public void setAdmTipoFeriadoVO(AdmTipoFeriadoVO[] admTipoFeriadoVO) {
			this.admTipoFeriadoVO = admTipoFeriadoVO;
		}

		public AdmTipoFeriadoVO[] getAdmTipoFeriadoVO() {
			return this.admTipoFeriadoVO;
		}

		public void setuFVO(UFVO[] uFVO) {
			this.uFVO = uFVO;
		}

		public UFVO[] getuFVO() {
			return this.uFVO;
		}

		public void setMunicipioVO(MunicipioVO[] municipioVO) {
			this.municipioVO = municipioVO;
		}

		public MunicipioVO[] getMunicipioVO() {
			return this.municipioVO;
		}

		public void setListaAdmFeriadoVO(AdmFeriadoVO[] listaAdmFeriadoVO) {
			this.listaAdmFeriadoVO = listaAdmFeriadoVO;
		}

		public AdmFeriadoVO[] getListaAdmFeriadoVO() {
			if (this.listaAdmFeriadoVO == null) {
				this.listaAdmFeriadoVO = new AdmFeriadoVO[0];
			}

			return this.listaAdmFeriadoVO;
		}

		public void setListaMunicipiosExistentesVO(MunicipioVO[] listaMunicipiosExistentesVO) {
			this.listaMunicipiosExistentesVO = listaMunicipiosExistentesVO;
		}

		public MunicipioVO[] getListaMunicipiosExistentesVO() {
			return this.listaMunicipiosExistentesVO;
		}

		public void setListaMunicipiosRelacionadosVO(MunicipioVO[] listaMunicipiosRelacionadosVO) {
			this.listaMunicipiosRelacionadosVO = listaMunicipiosRelacionadosVO;
		}

		public MunicipioVO[] getListaMunicipiosRelacionadosVO() {
			return this.listaMunicipiosRelacionadosVO;
		}

		public void setIdFeriado(String idFeriado) {
			this.idFeriado = idFeriado;
		}

		public String getIdFeriado() {
			return this.idFeriado;
		}

		public void setDtFeriado(String dtFeriado) {
			this.dtFeriado = dtFeriado;
		}

		public String getDtFeriado() {
			return this.dtFeriado;
		}

		public void setIdDsFeriado(String idDsFeriado) {
			this.idDsFeriado = idDsFeriado;
		}

		public String getIdDsFeriado() {
			return this.idDsFeriado;
		}

		public void setDsFeriado(String dsFeriado) {
			this.dsFeriado = dsFeriado;
		}

		public String getDsFeriado() {
			return this.dsFeriado;
		}

		public void setIdTipoFeriado(String idTipoFeriado) {
			this.idTipoFeriado = idTipoFeriado;
		}

		public String getIdTipoFeriado() {
			return this.idTipoFeriado;
		}

		public void setInRelatorio(String inRelatorio) {
			this.inRelatorio = inRelatorio;
		}

		public String getInRelatorio() {
			return this.inRelatorio;
		}

		public void setIndMovel(String indMovel) {
			this.indMovel = indMovel;
		}

		public String getIndMovel() {
			return this.indMovel;
		}

		public void setContadorAdmFeriadoLenght(String contadorAdmFeriadoLenght) {
			this.contadorAdmFeriadoLenght = contadorAdmFeriadoLenght;
		}

		public String getContadorAdmFeriadoLenght() {
			return this.contadorAdmFeriadoLenght;
		}

		public void setArrayUFVOExistente(String[] arrayUFVOExistente) {
			this.arrayUFVOExistente = arrayUFVOExistente;
		}

		public String[] getArrayUFVOExistente() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayUFVOExistente == null || this.arrayUFVOExistente.length == 0) {
				this.arrayUFVOExistente = new String[1];
			}

			return this.arrayUFVOExistente;
		}

		public void setArrayUFVORelacionado(String[] arrayUFVORelacionado) {
			this.arrayUFVORelacionado = arrayUFVORelacionado;
		}

		public String[] getArrayUFVORelacionado() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayUFVORelacionado == null || this.arrayUFVORelacionado.length == 0) {
				this.arrayUFVORelacionado = new String[1];
			}

			return this.arrayUFVORelacionado;
		}

		public void setArrayMunicipioExistente(String[] arrayMunicipioExistente) {
			this.arrayMunicipioExistente = arrayMunicipioExistente;
		}

		public String[] getArrayMunicipioExistente() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayMunicipioExistente == null || this.arrayMunicipioExistente.length == 0) {
				this.arrayMunicipioExistente = new String[1];
			}

			return this.arrayMunicipioExistente;
		}

		public void setArrayMunicipioRelacionado(String[] arrayMunicipioRelacionado) {
			this.arrayMunicipioRelacionado = arrayMunicipioRelacionado;
		}

		public String[] getArrayMunicipioRelacionado() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayMunicipioRelacionado == null || this.arrayMunicipioRelacionado.length == 0) {
				this.arrayMunicipioRelacionado = new String[1];
			}

			return this.arrayMunicipioRelacionado;
		}

		public void setArrayAnoBase(String[] arrayAnoBase) {
			this.arrayAnoBase = arrayAnoBase;
		}

		public String[] getArrayAnoBase() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayAnoBase == null || this.arrayAnoBase.length == 0) {
				this.arrayAnoBase = new String[1];
			}

			return this.arrayAnoBase;
		}

		public void setArrayAnoCopia(String[] arrayAnoCopia) {
			this.arrayAnoCopia = arrayAnoCopia;
		}

		public String[] getArrayAnoCopia() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayAnoCopia == null || this.arrayAnoCopia.length == 0) {
				this.arrayAnoCopia = new String[1];
			}

			return this.arrayAnoCopia;
		}

		public void setIdMunicipio(String idMunicipio) {
			this.idMunicipio = idMunicipio;
		}

		public String getIdMunicipio() {
			return this.idMunicipio;
		}

		public void setListaUFs(UFVO[] listaUFs) {
			this.listaUFs = listaUFs;
		}

		public UFVO[] getListaUFs() {
			return this.listaUFs;
		}

		public void setIdUF(String idUF) {
			this.idUF = idUF;
		}

		public String getIdUF() {
			return this.idUF;
		}

		public void setIndicativoUF(String indicativoUF) {
			this.indicativoUF = indicativoUF;
		}

		public String getIndicativoUF() {
			return this.indicativoUF;
		}

		public void setAdmTipoFeriado(String admTipoFeriado) {
			this.admTipoFeriado = admTipoFeriado;
		}

		public String getAdmTipoFeriado() {
			return this.admTipoFeriado;
		}

		public void setAdmDescricaoNova(String admDescricaoNova) {
			this.admDescricaoNova = admDescricaoNova;
		}

		public String getAdmDescricaoNova() {
			return this.admDescricaoNova;
		}

		public void setAdmDescricaoExistente(String admDescricaoExistente) {
			this.admDescricaoExistente = admDescricaoExistente;
		}

		public String getAdmDescricaoExistente() {
			return this.admDescricaoExistente;
		}

		public void setDsTipoFeriado(String dsTipoFeriado) {
			this.dsTipoFeriado = dsTipoFeriado;
		}

		public String getDsTipoFeriado() {
			return this.dsTipoFeriado;
		}

		public void setIdDsFeriadoParam(String idDsFeriadoParam) {
			this.idDsFeriadoParam = idDsFeriadoParam;
		}

		public String getIdDsFeriadoParam() {
			return this.idDsFeriadoParam;
		}

		public void setDsFeriadoParam(String dsFeriadoParam) {
			this.dsFeriadoParam = dsFeriadoParam;
		}

		public String getDsFeriadoParam() {
			return this.dsFeriadoParam;
		}

		public void setIdTipoFeriadoParam(String idTipoFeriadoParam) {
			this.idTipoFeriadoParam = idTipoFeriadoParam;
		}

		public String getIdTipoFeriadoParam() {
			return this.idTipoFeriadoParam;
		}

		public void setInRelatorioParam(String inRelatorioParam) {
			this.inRelatorioParam = inRelatorioParam;
		}

		public String getInRelatorioParam() {
			return this.inRelatorioParam;
		}

		public void setIndMovelParam(String indMovelParam) {
			this.indMovelParam = indMovelParam;
		}

		public String getIndMovelParam() {
			return this.indMovelParam;
		}

		public void setDsTipoFeriadoParam(String dsTipoFeriadoParam) {
			this.dsTipoFeriadoParam = dsTipoFeriadoParam;
		}

		public String getDsTipoFeriadoParam() {
			return this.dsTipoFeriadoParam;
		}

		public void setIdFeriadoParam(String idFeriadoParam) {
			this.idFeriadoParam = idFeriadoParam;
		}

		public String getIdFeriadoParam() {
			return this.idFeriadoParam;
		}

		public void setDtFeriadoParam(String dtFeriadoParam) {
			this.dtFeriadoParam = dtFeriadoParam;
		}

		public String getDtFeriadoParam() {
			return this.dtFeriadoParam;
		}

		public void setIndicativoMunicipal(String indicativoMunicipal) {
			this.indicativoMunicipal = indicativoMunicipal;
		}

		public String getIndicativoMunicipal() {
			return this.indicativoMunicipal;
		}

		public void setListaUFVOExistente(UFVO[] listaUFVOExistente) {
			this.listaUFVOExistente = listaUFVOExistente;
		}

		public UFVO[] getListaUFVOExistente() {
			return this.listaUFVOExistente;
		}

		public void setListaUFVORelacionados(UFVO[] listaUFVORelacionados) {
			this.listaUFVORelacionados = listaUFVORelacionados;
		}

		public UFVO[] getListaUFVORelacionados() {
			return this.listaUFVORelacionados;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setAno(String ano) {
			this.ano = ano;
		}

		public String getAno() {
			if (this.ano == null) {
				this.ano = ConstantesCRM.SVAZIO;
			}
			return this.ano;
		}

		public void setCmbCombo(AdmCalendarioContainerVO cmbCombo) {
			this.cmbCombo = cmbCombo;
		}

		public AdmCalendarioContainerVO getCmbCombo() {
			if (this.cmbCombo == null) {
				this.cmbCombo = AdmCalendarioContainerVO.Factory.newInstance();
			}
			return this.cmbCombo;
		}

		public void setListFeriado(ArrayList listFeriado) {
			this.listFeriado = listFeriado;
		}

		public ArrayList getListFeriado() {
			return this.listFeriado;
		}
	}

	public FormCalendario getFormCalendario() {
		return this.formCalendario;
	}

	public AdmFeriadoVO getFeriadoById(String id, ArrayList listFeriado) {

		for (int x = 0; x < listFeriado.size(); x++) {
			AdmCalendarioContainerVO contaner = (AdmCalendarioContainerVO) listFeriado.get(x);

			AdmFeriadoVO[] feriado = contaner.getAdmFeriadoVOArray();

			for (int i = 0; i < feriado.length; i++) {
				if (feriado[i].getIdFeriado().equals(id)) {
					return feriado[i];
				}
			}

		}
		return null;
	}

	public AdmFeriadoVO[] revomeFeriadoLista(String id, AdmCalendarioContainerVO obj) {
		if (obj != null && obj.getAdmFeriadoVOArray() != null) {
			for (int i = 0; i < obj.getAdmFeriadoVOArray().length; i++) {
				if (id.equals(obj.getAdmFeriadoVOArray(i).getIdFeriado())) {
					obj.removeAdmFeriadoVO(i);
					break;
				}
			}
		}

		return obj.getAdmFeriadoVOArray();
	}
}
