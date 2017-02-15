package admsistemas.admArvoreContato.dadosBasicos.abaOperadoras;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmContatoUFOperadoraVODocument.AdmContatoUFOperadoraVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AbaOperadorasController extends AbstractAction {

	private static final long serialVersionUID = -7294780769284043716L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.operadoras.Operadoras controlOperadoras;

	private FormOperadora formOperadora;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("editaOperadora".equals(mapping.getParameter())) {
			return editaOperadora(mapping, form, request, response);
		} else if ("removeOperadora".equals(mapping.getParameter())) {
			return removeOperadora(mapping, form, request, response);
		} else if ("salvaOperadora".equals(mapping.getParameter())) {
			return salvaOperadora(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularOperadoras.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AbaOperadorasController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormOperadora form = (FormOperadora) formParam;
		try {
			formOperadora = new FormOperadora();
			formOperadora.setIdContato(form.getIdContato());

			AdmIdContatoVO admIdContatoVO = AdmIdContatoVO.Factory.newInstance();
			admIdContatoVO.setIdContato(formOperadora.getIdContato());

			AdmOperadorasVO admOperadorasVO = AdmOperadorasVO.Factory.newInstance();
			admOperadorasVO = controlOperadoras.listaOperadoras(admIdContatoVO, ConstantesCRM.getCurrentUser(request.getSession()));
			montaOperadora(admOperadorasVO);

			if (request.getSession().getAttribute("idContato") == null) {
				request.getSession().setAttribute("idContato", request.getParameter("idContato"));
			} else {
				request.getSession().removeAttribute("idContato");
				request.getSession().setAttribute("idContato", request.getParameter("idContato"));
			}
			formOperadora.setMsgError(ConstantesCRM.SVAZIO);
		} catch (TuxedoWarningException twe) {
			log.warn("AbaOperadorasController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOperadora.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AbaOperadorasController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void montaOperadora(AdmOperadorasVO admOperadorasVO) {
		formOperadora.setDtFimVigencia(admOperadorasVO.getDtFimVigencia());
		formOperadora.setDtInicioVigencia(admOperadorasVO.getDtInicioVigencia());
		formOperadora.setIndeterminado(admOperadorasVO.getIndeterminado());
		formOperadora.setOperadorasAssociadasVO(admOperadorasVO.getOperadorasAssociadas().getAdmContatoUFOperadoraVOArray());
		formOperadora.setOperadorasExistentesVO(admOperadorasVO.getOperadorasExistentes().getAdmContatoUFOperadoraVOArray());
		formOperadora.setListaOperadoras(admOperadorasVO.getAdmContatoUFOperadorasVO().getAdmContatoUFOperadoraVOArray());
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaOperadora(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AbaOperadorasController:editaOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormOperadora form = (FormOperadora) formParam;
		formOperadora.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmContatoUFOperadoraVO[] admContatoUFOperadoraVO = new AdmContatoUFOperadoraVO[1];
			admContatoUFOperadoraVO[0] = AdmContatoUFOperadoraVO.Factory.newInstance();
			admContatoUFOperadoraVO[0].setIdContato(form.getIdContato());
			admContatoUFOperadoraVO[0].setIdUFOperadora(form.getIdUFOperadora());
			admContatoUFOperadoraVO[0].setDtFimVigencia(form.getDtFimVigencia());
			admContatoUFOperadoraVO[0].setDtInicioVigencia(form.getDtInicioVigencia());
			if (admContatoUFOperadoraVO[0].getDtFimVigencia() == null || admContatoUFOperadoraVO[0].getDtFimVigencia().equals("")) {
				admContatoUFOperadoraVO[0].setInDisponibilidade(ConstantesCRM.SZERO);
				admContatoUFOperadoraVO[0].setDtFimVigencia(ConstantesCRM.SVAZIO);
			} else {
				admContatoUFOperadoraVO[0].setInDisponibilidade(ConstantesCRM.SONE);
			}

			AdmOperadorasVO admOperadorasVO = AdmOperadorasVO.Factory.newInstance();
			admOperadorasVO.addNewOperadorasAssociadas().setAdmContatoUFOperadoraVOArray(admContatoUFOperadoraVO);

			AdmOperadorasVO newAdmOperadorasVO = AdmOperadorasVO.Factory.newInstance();
			controlOperadoras.listaOperadoraEditada(admOperadorasVO, ConstantesCRM.getCurrentUser(request.getSession()));

			AdmIdContatoVO admIdContatoVO = AdmIdContatoVO.Factory.newInstance();
			admIdContatoVO.setIdContato(form.getIdContato());

			newAdmOperadorasVO = controlOperadoras.listaOperadoras(admIdContatoVO, ConstantesCRM.getCurrentUser(request.getSession()));
			montaOperadora(newAdmOperadorasVO);

			if (request.getSession().getAttribute("idContato") == null) {
				request.getSession().setAttribute("idContato", request.getParameter("idContato"));
			}

		} catch (TuxedoWarningException twe) {
			log.warn("AbaOperadorasController:editaOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOperadora.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AbaOperadorasController:editaOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="vincularOperadoras.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeOperadora(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AbaOperadorasController:removeOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormOperadora form = (FormOperadora) formParam;
		formOperadora.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmOperadorasVO admOperadorasVO = AdmOperadorasVO.Factory.newInstance();
			AdmContatoUFOperadoraVO[] admContatoUFOperadoraVO = new AdmContatoUFOperadoraVO[form.getArrayOperadoraAssociada().length];
			String[] operadoraAssociada = new String[form.getArrayOperadoraAssociada().length];
			operadoraAssociada = form.getArrayOperadoraAssociada();

			for (int i = 0; i < operadoraAssociada.length; i++) {
				if (!form.getIdUFOperadoraEditado().equals(operadoraAssociada[i])) {
					admContatoUFOperadoraVO[i] = AdmContatoUFOperadoraVO.Factory.newInstance();
					if (form.getIndeterminado().equals(ConstantesCRM.SONE)) {
						admContatoUFOperadoraVO[i].setDtFimVigencia(form.getDtFimVigencia());
					} else {
						admContatoUFOperadoraVO[i].setDtFimVigencia(ConstantesCRM.SVAZIO);
					}
					admContatoUFOperadoraVO[i].setDtInicioVigencia(form.getDtInicioVigencia());
					admContatoUFOperadoraVO[i].setIdContato(form.getIdContato());
					admContatoUFOperadoraVO[i].setIdUFOperadora(operadoraAssociada[i]);
					admContatoUFOperadoraVO[i].setInDisponibilidade(form.getIndeterminado());
				}
			}

			admOperadorasVO.setIdContato(form.getIdContato());
			admOperadorasVO.setDtFimVigencia(form.getDtFimVigencia());
			admOperadorasVO.setDtInicioVigencia(form.getDtInicioVigencia());
			admOperadorasVO.setIndeterminado(form.getIndeterminado());

			admOperadorasVO.addNewOperadorasAssociadas().setAdmContatoUFOperadoraVOArray(admContatoUFOperadoraVO);

			AdmOperadorasVO newAdmOperadorasVO = AdmOperadorasVO.Factory.newInstance();
			newAdmOperadorasVO = controlOperadoras.salvaOperadoras(admOperadorasVO, ConstantesCRM.getCurrentUser(request.getSession()));
			limpaForm();
			montaOperadoras(newAdmOperadorasVO, form);
			formOperadora.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("AbaOperadorasController:removeOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOperadora.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AbaOperadorasController:removeOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="vincularOperadoras.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaOperadora(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AbaOperadorasController:salvaOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormOperadora form = (FormOperadora) formParam;
		formOperadora.setMsgError(ConstantesCRM.SVAZIO);
		try {
			AdmOperadorasVO admOperadorasVO = AdmOperadorasVO.Factory.newInstance();
			AdmContatoUFOperadoraVO[] admContatoUFOperadoraVO = new AdmContatoUFOperadoraVO[form.getArrayOperadoraAssociada().length];
			String[] operadoraAssociada = new String[form.getArrayOperadoraAssociada().length];
			operadoraAssociada = form.getArrayOperadoraAssociada();

			for (int i = 0; i < operadoraAssociada.length; i++) {
				admContatoUFOperadoraVO[i] = AdmContatoUFOperadoraVO.Factory.newInstance();
				if (form.getIndeterminado().equals(ConstantesCRM.SONE)) {
					admContatoUFOperadoraVO[i].setDtFimVigencia(form.getDtFimVigencia());
				} else {
					admContatoUFOperadoraVO[i].setDtFimVigencia(ConstantesCRM.SVAZIO);
				}

				admContatoUFOperadoraVO[i].setDtInicioVigencia(form.getDtInicioVigencia());
				admContatoUFOperadoraVO[i].setIdContato(form.getIdContato());
				admContatoUFOperadoraVO[i].setIdUFOperadora(operadoraAssociada[i]);
				admContatoUFOperadoraVO[i].setInDisponibilidade(form.getIndeterminado());
			}

			admOperadorasVO.setIdContato(form.getIdContato());
			admOperadorasVO.setDtFimVigencia(form.getDtFimVigencia());
			admOperadorasVO.setDtInicioVigencia(form.getDtInicioVigencia());
			admOperadorasVO.setIndeterminado(form.getIndeterminado());

			admOperadorasVO.addNewOperadorasAssociadas().setAdmContatoUFOperadoraVOArray(admContatoUFOperadoraVO);

			AdmOperadorasVO newAdmOperadorasVO = AdmOperadorasVO.Factory.newInstance();
			newAdmOperadorasVO = controlOperadoras.salvaOperadoras(admOperadorasVO, ConstantesCRM.getCurrentUser(request.getSession()));
			limpaForm();
			montaOperadoras(newAdmOperadorasVO, form);
			formOperadora.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("AbaOperadorasController:removeOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formOperadora.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AbaOperadorasController:removeOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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

	private void limpaForm() {
		formOperadora = new FormOperadora();
	}

	private void montaOperadoras(AdmOperadorasVO admOperadorasVO, FormOperadora form) {
		formOperadora.setIdContato(form.getIdContato());
		formOperadora.setOperadorasAssociadasVO(admOperadorasVO.getOperadorasAssociadas().getAdmContatoUFOperadoraVOArray());
		formOperadora.setOperadorasExistentesVO(admOperadorasVO.getOperadorasExistentes().getAdmContatoUFOperadoraVOArray());
		formOperadora.setListaOperadoras(admOperadorasVO.getAdmContatoUFOperadorasVO().getAdmContatoUFOperadoraVOArray());

	}

	public static class FormOperadora extends ActionForm {

		private static final long serialVersionUID = 7403909302553450614L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String idUFOperadora;
		private AdmContatoUFOperadoraVO admContatoUFOperadora;
		private String idUFOperadoraEditado;
		private AdmContatoUFOperadoraVO[] listaOperadoras;
		private String[] a;
		private String[] arrayOperadoraAssociada;
		private String indeterminado;
		private String dtFimVigencia;
		private String dtInicioVigencia;
		private AdmContatoUFOperadoraVO[] operadorasAssociadasVO;
		private AdmContatoUFOperadoraVO[] operadorasExistentesVO;
		private String idContato;

		public FormOperadora() {
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setOperadorasExistentesVO(AdmContatoUFOperadoraVO[] operadorasExistentesVO) {
			this.operadorasExistentesVO = operadorasExistentesVO;
		}

		public AdmContatoUFOperadoraVO[] getOperadorasExistentesVO() {
			return this.operadorasExistentesVO;
		}

		public void setOperadorasAssociadasVO(AdmContatoUFOperadoraVO[] operadorasAssociadasVO) {
			this.operadorasAssociadasVO = operadorasAssociadasVO;
		}

		public AdmContatoUFOperadoraVO[] getOperadorasAssociadasVO() {
			return this.operadorasAssociadasVO;
		}

		public void setDtInicioVigencia(String dtInicioVigencia) {
			this.dtInicioVigencia = dtInicioVigencia;
		}

		public String getDtInicioVigencia() {
			return this.dtInicioVigencia;
		}

		public void setDtFimVigencia(String dtFimVigencia) {
			this.dtFimVigencia = dtFimVigencia;
		}

		public String getDtFimVigencia() {
			return this.dtFimVigencia;
		}

		public void setIndeterminado(String indeterminado) {
			this.indeterminado = indeterminado;
		}

		public String getIndeterminado() {
			return this.indeterminado;
		}

		public void setArrayOperadoraAssociada(String[] arrayOperadoraAssociada) {
			this.arrayOperadoraAssociada = arrayOperadoraAssociada;
		}

		public String[] getArrayOperadoraAssociada() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayOperadoraAssociada == null || this.arrayOperadoraAssociada.length == 0) {
				this.arrayOperadoraAssociada = new String[1];
			}

			return this.arrayOperadoraAssociada;
		}

		public void setA(String[] a) {
			this.a = a;
		}

		public String[] getA() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.a == null || this.a.length == 0) {
				this.a = new String[1];
			}

			return this.a;
		}

		public void setListaOperadoras(AdmContatoUFOperadoraVO[] listaOperadoras) {
			this.listaOperadoras = listaOperadoras;
		}

		public AdmContatoUFOperadoraVO[] getListaOperadoras() {
			return this.listaOperadoras;
		}

		public void setIdUFOperadoraEditado(String idUFOperadoraEditado) {
			this.idUFOperadoraEditado = idUFOperadoraEditado;
		}

		public String getIdUFOperadoraEditado() {
			return this.idUFOperadoraEditado;
		}

		public void setAdmContatoUFOperadora(AdmContatoUFOperadoraVO admContatoUFOperadora) {
			this.admContatoUFOperadora = admContatoUFOperadora;
		}

		public AdmContatoUFOperadoraVO getAdmContatoUFOperadora() {
			return this.admContatoUFOperadora;
		}

		public void setIdUFOperadora(String idUFOperadora) {
			this.idUFOperadora = idUFOperadora;
		}

		public String getIdUFOperadora() {
			return this.idUFOperadora;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public FormOperadora getFormOperadora() {
		return this.formOperadora;
	}
}
