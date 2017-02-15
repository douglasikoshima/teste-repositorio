package admsistemas.admArvoreContato.dadosBasicos.abaLinkInfo;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmListaLinkVODocument.AdmListaLinkVO;
import br.com.vivo.fo.admsistemas.vo.AdmObjetoLinkVODocument.AdmObjetoLinkVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoClienteVODocument.AdmTipoClienteVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaVODocument.AdmTipoLinhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoOperadoraVODocument.AdmTipoOperadoraVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AbaLinkInfoController extends AbstractAction {

	private static final long serialVersionUID = -5019009850659584439L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.link.Link controlLink;

	private FormLink formLink;
	private String litleFlag = "true";

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("removeLink".equals(mapping.getParameter())) {
			return removeLink(mapping, form, request, response);
		} else if ("insereLink".equals(mapping.getParameter())) {
			return insereLink(mapping, form, request, response);
		} else if ("salvaEditaLink".equals(mapping.getParameter())) {
			return salvaEditaLink(mapping, form, request, response);
		} else if ("carregaAltera".equals(mapping.getParameter())) {
			return carregaAltera(mapping, form, request, response);
		} else if ("carregaAltera".equals(mapping.getParameter())) {
			return carregaAltera(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularLinkInfo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AbaLinkInfoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			AdmIdContatoVO admIdContatoVO = AdmIdContatoVO.Factory.newInstance();
			AdmListaLinkVO admListaLinkVO = AdmListaLinkVO.Factory.newInstance();

			formLink = new FormLink();
			formLink.setIdContato(request.getParameter("idContato"));
			formLink.setNmContato(request.getParameter("nmContato"));
			admIdContatoVO.setIdContato(formLink.getIdContato());
			admListaLinkVO = controlLink.montaTelaLink(admIdContatoVO, ConstantesCRM.getCurrentUser(request.getSession()));

			montaTelaLink(admListaLinkVO);
			request.setAttribute("litleFlag", litleFlag);

		} catch (TuxedoWarningException twe) {
			log.warn("AbaLinkInfoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formLink.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AbaLinkInfoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void montaTelaLink(AdmListaLinkVO admListaLinkVO) {

		// Lista de Objetos relacionados.
		formLink.setAdmObjetoLinkVO(admListaLinkVO.getListaAssociada().getAdmObjetoLinkVOArray());
		if (admListaLinkVO.getListaAssociada().getAdmObjetoLinkVOArray() != null) {
			formLink.setListaLinkLegth(String.valueOf(admListaLinkVO.getListaAssociada().getAdmObjetoLinkVOArray().length));
		}

		// Clientes
		formLink.setAdmTipoClienteDisponivelVO(admListaLinkVO.getDisponiveis().getAdmTipoClienteVOArray());
		formLink.setAdmTipoClienteSelecionadoVO(admListaLinkVO.getSelecionados().getAdmTipoClienteVOArray());
		// Linhas
		formLink.setAdmTipoLinhaDisponivelVO(admListaLinkVO.getDisponiveis().getAdmTipoLinhaVOArray());
		formLink.setAdmTipoLinhaSelecionadoVO(admListaLinkVO.getSelecionados().getAdmTipoLinhaVOArray());

		if (admListaLinkVO.getSelecionados().getAdmTipoLinhaVOArray().length > 0) {
			litleFlag = "false";
		}
		// Operadoras
		formLink.setAdmTipoOperadoraDisponivelVO(admListaLinkVO.getDisponiveis().getAdmTipoOperadoraVOArray());
		formLink.setAdmTipoOperadoraSelecionadaVO(admListaLinkVO.getSelecionados().getAdmTipoOperadoraVOArray());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularLinkInfo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeLink(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AbaLinkInfoController:removeLink" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formLink.setMsgError(ConstantesCRM.SVAZIO);
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			AdmObjetoLinkVO admObjetoLinkVO = AdmObjetoLinkVO.Factory.newInstance();
			admObjetoLinkVO.setIdContatoInformacao(request.getParameter("idContatoInformacao"));
			admObjetoLinkVO.setIdContato(request.getParameter("idContato"));
			AdmListaLinkVO admListaLinkVO = AdmListaLinkVO.Factory.newInstance();
			admListaLinkVO = controlLink.removeLink(admObjetoLinkVO, ConstantesCRM.getCurrentUser(request.getSession()));

			montaTelaLink(admListaLinkVO);
			request.setAttribute("litleFlag", "false");
			formLink.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("AbaLinkInfoController:removeLink" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formLink.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AbaLinkInfoController:removeLink" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="vincularLinkInfo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward insereLink(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AbaLinkInfoController:insereLink" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormLink form = (FormLink) formParam;
		formLink.setMsgError(ConstantesCRM.SVAZIO);
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);

			AdmListaLinkVO admListaLinkVO = AdmListaLinkVO.Factory.newInstance();
			AdmListaLinkVO admListaLinkNovoVO = AdmListaLinkVO.Factory.newInstance();

			AdmTipoLinhaVO[] admTipoLinhaVO = new AdmTipoLinhaVO[form.getArrayTipoLinhaSelecionado().length];
			String[] linhaSelecionada = new String[form.getArrayTipoLinhaSelecionado().length];
			linhaSelecionada = form.getArrayTipoLinhaSelecionado();

			AdmTipoClienteVO[] admTipoClienteVO = new AdmTipoClienteVO[form.getArrayAdmTipoClienteSelecionado().length];
			String[] tipoClienteSelecionado = new String[form.getArrayAdmTipoClienteSelecionado().length];
			tipoClienteSelecionado = form.getArrayAdmTipoClienteSelecionado();

			AdmTipoOperadoraVO[] admTipoOperadoraVO = new AdmTipoOperadoraVO[form.getArrayAdmTipoOperadoraSelecionada().length];
			String[] tipoOperadoraSelecionada = new String[form.getArrayAdmTipoOperadoraSelecionada().length];
			tipoOperadoraSelecionada = form.getArrayAdmTipoOperadoraSelecionada();

			for (int i = 0; i < linhaSelecionada.length; i++) {
				admTipoLinhaVO[i] = AdmTipoLinhaVO.Factory.newInstance();
				admTipoLinhaVO[i].setIdTipoLinha(linhaSelecionada[i]);
			}
			admListaLinkVO.addNewSelecionados();
			admListaLinkVO.getSelecionados().setAdmTipoLinhaVOArray(admTipoLinhaVO);

			for (int i = 0; i < tipoClienteSelecionado.length; i++) {
				admTipoClienteVO[i] = AdmTipoClienteVO.Factory.newInstance();
				admTipoClienteVO[i].setIdTipoCliente(tipoClienteSelecionado[i]);
			}

			admListaLinkVO.getSelecionados().setAdmTipoClienteVOArray(admTipoClienteVO);

			for (int i = 0; i < tipoOperadoraSelecionada.length; i++) {
				admTipoOperadoraVO[i] = AdmTipoOperadoraVO.Factory.newInstance();
				admTipoOperadoraVO[i].setIdUFOperadora(tipoOperadoraSelecionada[i]);
			}

			admListaLinkVO.getSelecionados().setAdmTipoOperadoraVOArray(admTipoOperadoraVO);

			admListaLinkVO.getSelecionados().setIdContato(formLink.getIdContato());
			if (form.getNmLink().equals(ConstantesCRM.SVAZIO) || form.getNmLink().equals(null)) {
				form.setNmLink(formLink.getAdmObjetoLinkVO()[0].getNmLink());
			}
			admListaLinkVO.getSelecionados().setNmLink(form.getNmLink());
			admListaLinkNovoVO = controlLink.inserirLink(admListaLinkVO, ConstantesCRM.getCurrentUser(request.getSession()));

			montaTelaLink(admListaLinkNovoVO);
			request.setAttribute("litleFlag", "false");
			formLink.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("AbaLinkInfoController:insereLink" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formLink.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AbaLinkInfoController:insereLink" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="vincularLinkInfo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaEditaLink(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AbaLinkInfoController:salvaEditaLink" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormLink form = (FormLink) formParam;
		formLink.setMsgError(ConstantesCRM.SVAZIO);

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);

			AdmObjetoLinkVO admObjetoLinkVO = AdmObjetoLinkVO.Factory.newInstance();

			admObjetoLinkVO.setIdContato(form.getIdContato());
			admObjetoLinkVO.setIdContatoInformacao(form.getIdContatoInformacao());
			admObjetoLinkVO.setIdTipoLinha(form.getIdTipoLinha());
			admObjetoLinkVO.setIdTipoRelacionamento(form.getIdTipoRelacionamento());
			admObjetoLinkVO.setIdUFOperadora(form.getIdUFOperadora());
			admObjetoLinkVO.setNmLink(form.getNmLink());
			admObjetoLinkVO.setNmTipoLinha(form.getNmTipoLinha());
			admObjetoLinkVO.setNmTipoRelacionamento(form.getNmTipoLinha());
			admObjetoLinkVO.setNmUFOperadora(form.getNmUfOperadora());

			AdmListaLinkVO admListaLinkVO = AdmListaLinkVO.Factory.newInstance();

			admListaLinkVO = controlLink.salvaLinkEditado(admObjetoLinkVO, ConstantesCRM.getCurrentUser(request.getSession()));

			form.setNmLink(ConstantesCRM.SVAZIO);
			montaTelaLink(admListaLinkVO);

			request.setAttribute("litleFlag", "false");
			formLink.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("AbaLinkInfoController:salvaEditaLink" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formLink.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AbaLinkInfoController:salvaEditaLink" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="alteraLink.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaAltera(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AbaLinkInfoController:carregaAltera" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormLink form = (FormLink) formParam;
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);

			AdmObjetoLinkVO admObjetoLinkVO = getLinkById(form.getIdContatoInformacao(), formLink.getAdmObjetoLinkVO());

			formLink.setIdContatoInformacao(form.getIdContatoInformacao());
			formLink.setIdContato(admObjetoLinkVO.getIdContato());
			formLink.setNmLink(admObjetoLinkVO.getNmLink());
			formLink.setNmTipoRelacionamento(admObjetoLinkVO.getNmTipoRelacionamento());
			formLink.setIdUFOperadoraEditado(admObjetoLinkVO.getIdUFOperadora());
			formLink.setIdTipoLinha(admObjetoLinkVO.getIdTipoLinha());
			formLink.setNmLinkEditado(admObjetoLinkVO.getNmLink());
			formLink.setNmTipoLinha(admObjetoLinkVO.getNmTipoLinha());
			formLink.setIdUFOperadora(form.getIdUFOperadora());
			formLink.setIdTipoRelacionamento(admObjetoLinkVO.getIdTipoRelacionamento());
			formLink.setNmUfOperadora(admObjetoLinkVO.getNmUFOperadora());

		} catch (Exception e) {
			log.error("AbaLinkInfoController:carregaAltera" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormLink extends ActionForm {

		private static final long serialVersionUID = 1976450546758693731L;

		private String idTipoLinha;
		private String idTipoRelacionamento;
		private String idUFOperadora;
		// private String idUfOperadora;
		private String nmUfOperadora;
		private String nmTipoLinha;
		private String nmTipoRelacionamento;
		private String msgError = "";
		private String idContatoInformacao;
		private String listaLinkLegth;
		private String idTipoLinhaEditado;
		private String idUFOperadoraEditado;
		private String idTipoRelacionamentoEditado;
		private String idContatoEditado;
		private String nmLinkEditado;
		private String[] arrayAdmTipoClienteSelecionado;
		private String nmContato;
		private String[] arrayAdmLinkObjeto;
		private AdmObjetoLinkVO[] admObjetoLinkVO;
		private String nmLink;
		private String[] arrayAdmTipoOperadoraSelecionada;
		private String[] arrayAdmTipoOperadoraDisponivel;
		private AdmTipoOperadoraVO[] admTipoOperadoraSelecionadaVO;
		private AdmTipoOperadoraVO[] admTipoOperadoraDisponivelVO;
		private String[] arrayAdmTipoClienteDisponivel;
		private AdmTipoClienteVO[] admTipoClienteSelecionadoVO;
		private AdmTipoClienteVO[] admTipoClienteDisponivelVO;
		private String[] arrayTipoLinhaSelecionado;
		private String[] arrayTipoLinhaDisponivel;
		private AdmTipoLinhaVO[] admTipoLinhaSelecionadoVO;
		private AdmTipoLinhaVO[] admTipoLinhaDisponivelVO;
		private String idContato;

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setAdmTipoLinhaDisponivelVO(AdmTipoLinhaVO[] admTipoLinhaDisponivelVO) {
			this.admTipoLinhaDisponivelVO = admTipoLinhaDisponivelVO;
		}

		public AdmTipoLinhaVO[] getAdmTipoLinhaDisponivelVO() {
			return this.admTipoLinhaDisponivelVO;
		}

		public void setAdmTipoLinhaSelecionadoVO(AdmTipoLinhaVO[] admTipoLinhaSelecionadoVO) {
			this.admTipoLinhaSelecionadoVO = admTipoLinhaSelecionadoVO;
		}

		public AdmTipoLinhaVO[] getAdmTipoLinhaSelecionadoVO() {
			return this.admTipoLinhaSelecionadoVO;
		}

		public void setArrayTipoLinhaDisponivel(String[] arrayTipoLinhaDisponivel) {
			this.arrayTipoLinhaDisponivel = arrayTipoLinhaDisponivel;
		}

		public String[] getArrayTipoLinhaDisponivel() {
			if (this.arrayTipoLinhaDisponivel == null || this.arrayTipoLinhaDisponivel.length == 0) {
				this.arrayTipoLinhaDisponivel = new String[1];
			}
			return this.arrayTipoLinhaDisponivel;
		}

		public void setArrayTipoLinhaSelecionado(String[] arrayTipoLinhaSelecionado) {
			this.arrayTipoLinhaSelecionado = arrayTipoLinhaSelecionado;
		}

		public String[] getArrayTipoLinhaSelecionado() {
			if (this.arrayTipoLinhaSelecionado == null || this.arrayTipoLinhaSelecionado.length == 0) {
				this.arrayTipoLinhaSelecionado = new String[1];
			}
			return this.arrayTipoLinhaSelecionado;
		}

		public void setAdmTipoClienteDisponivelVO(AdmTipoClienteVO[] admTipoClienteDisponivelVO) {
			this.admTipoClienteDisponivelVO = admTipoClienteDisponivelVO;
		}

		public AdmTipoClienteVO[] getAdmTipoClienteDisponivelVO() {
			return this.admTipoClienteDisponivelVO;
		}

		public void setAdmTipoClienteSelecionadoVO(AdmTipoClienteVO[] admTipoClienteSelecionadoVO) {
			this.admTipoClienteSelecionadoVO = admTipoClienteSelecionadoVO;
		}

		public AdmTipoClienteVO[] getAdmTipoClienteSelecionadoVO() {
			return this.admTipoClienteSelecionadoVO;
		}

		public void setArrayAdmTipoClienteDisponivel(String[] arrayAdmTipoClienteDisponivel) {
			this.arrayAdmTipoClienteDisponivel = arrayAdmTipoClienteDisponivel;
		}

		public String[] getArrayAdmTipoClienteDisponivel() {
			if (this.arrayAdmTipoClienteDisponivel == null || this.arrayAdmTipoClienteDisponivel.length == 0) {
				this.arrayAdmTipoClienteDisponivel = new String[1];
			}
			return this.arrayAdmTipoClienteDisponivel;
		}

		public void setAdmTipoOperadoraDisponivelVO(AdmTipoOperadoraVO[] admTipoOperadoraDisponivelVO) {
			this.admTipoOperadoraDisponivelVO = admTipoOperadoraDisponivelVO;
		}

		public AdmTipoOperadoraVO[] getAdmTipoOperadoraDisponivelVO() {
			return this.admTipoOperadoraDisponivelVO;
		}

		public void setAdmTipoOperadoraSelecionadaVO(AdmTipoOperadoraVO[] admTipoOperadoraSelecionadaVO) {
			this.admTipoOperadoraSelecionadaVO = admTipoOperadoraSelecionadaVO;
		}

		public AdmTipoOperadoraVO[] getAdmTipoOperadoraSelecionadaVO() {
			return this.admTipoOperadoraSelecionadaVO;
		}

		public void setArrayAdmTipoOperadoraDisponivel(String[] arrayAdmTipoOperadoraDisponivel) {
			this.arrayAdmTipoOperadoraDisponivel = arrayAdmTipoOperadoraDisponivel;
		}

		public String[] getArrayAdmTipoOperadoraDisponivel() {
			if (this.arrayAdmTipoOperadoraDisponivel == null || this.arrayAdmTipoOperadoraDisponivel.length == 0) {
				this.arrayAdmTipoOperadoraDisponivel = new String[1];
			}
			return this.arrayAdmTipoOperadoraDisponivel;
		}

		public void setArrayAdmTipoOperadoraSelecionada(String[] arrayAdmTipoOperadoraSelecionada) {
			this.arrayAdmTipoOperadoraSelecionada = arrayAdmTipoOperadoraSelecionada;
		}

		public String[] getArrayAdmTipoOperadoraSelecionada() {
			if (this.arrayAdmTipoOperadoraSelecionada == null || this.arrayAdmTipoOperadoraSelecionada.length == 0) {
				this.arrayAdmTipoOperadoraSelecionada = new String[1];
			}
			return this.arrayAdmTipoOperadoraSelecionada;
		}

		public void setNmLink(String nmLink) {
			this.nmLink = nmLink;
		}

		public String getNmLink() {
			return this.nmLink;
		}

		public void setAdmObjetoLinkVO(AdmObjetoLinkVO[] admObjetoLinkVO) {
			this.admObjetoLinkVO = admObjetoLinkVO;
		}

		public AdmObjetoLinkVO[] getAdmObjetoLinkVO() {
			return this.admObjetoLinkVO;
		}

		public void setArrayAdmLinkObjeto(String[] arrayAdmLinkObjeto) {
			this.arrayAdmLinkObjeto = arrayAdmLinkObjeto;
		}

		public String[] getArrayAdmLinkObjeto() {
			if (this.arrayAdmLinkObjeto == null || this.arrayAdmLinkObjeto.length == 0) {
				this.arrayAdmLinkObjeto = new String[1];
			}
			return this.arrayAdmLinkObjeto;
		}

		public void setNmContato(String nmContato) {
			this.nmContato = nmContato;
		}

		public String getNmContato() {
			return this.nmContato;
		}

		public void setArrayAdmTipoClienteSelecionado(String[] arrayAdmTipoClienteSelecionado) {
			this.arrayAdmTipoClienteSelecionado = arrayAdmTipoClienteSelecionado;
		}

		public String[] getArrayAdmTipoClienteSelecionado() {
			if (this.arrayAdmTipoClienteSelecionado == null || this.arrayAdmTipoClienteSelecionado.length == 0) {
				this.arrayAdmTipoClienteSelecionado = new String[1];
			}
			return this.arrayAdmTipoClienteSelecionado;
		}

		public void setNmLinkEditado(String nmLinkEditado) {
			this.nmLinkEditado = nmLinkEditado;
		}

		public String getNmLinkEditado() {
			return this.nmLinkEditado;
		}

		public void setIdContatoEditado(String idContatoEditado) {
			this.idContatoEditado = idContatoEditado;
		}

		public String getIdContatoEditado() {
			return this.idContatoEditado;
		}

		public void setIdTipoRelacionamentoEditado(String idTipoRelacionamentoEditado) {
			this.idTipoRelacionamentoEditado = idTipoRelacionamentoEditado;
		}

		public String getIdTipoRelacionamentoEditado() {
			return this.idTipoRelacionamentoEditado;
		}

		public void setIdUFOperadoraEditado(String idUFOperadoraEditado) {
			this.idUFOperadoraEditado = idUFOperadoraEditado;
		}

		public String getIdUFOperadoraEditado() {
			return this.idUFOperadoraEditado;
		}

		public void setIdTipoLinhaEditado(String idTipoLinhaEditado) {
			this.idTipoLinhaEditado = idTipoLinhaEditado;
		}

		public String getIdTipoLinhaEditado() {
			return this.idTipoLinhaEditado;
		}

		public void setListaLinkLegth(String listaLinkLegth) {
			this.listaLinkLegth = listaLinkLegth;
		}

		public String getListaLinkLegth() {
			return this.listaLinkLegth;
		}

		public void setIdContatoInformacao(String idContatoInformacao) {
			this.idContatoInformacao = idContatoInformacao;
		}

		public String getIdContatoInformacao() {
			return this.idContatoInformacao;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setNmTipoRelacionamento(String nmTipoRelacionamento) {
			this.nmTipoRelacionamento = nmTipoRelacionamento;
		}

		public String getNmTipoRelacionamento() {
			return this.nmTipoRelacionamento;
		}

		public void setNmTipoLinha(String nmTipoLinha) {
			this.nmTipoLinha = nmTipoLinha;
		}

		public String getNmTipoLinha() {
			return this.nmTipoLinha;
		}

		public void setNmUfOperadora(String nmUfOperadora) {
			this.nmUfOperadora = nmUfOperadora;
		}

		public String getNmUfOperadora() {
			return this.nmUfOperadora;
		}

		public void setIdUFOperadora(String idUFOperadora) {
			this.idUFOperadora = idUFOperadora;
		}

		public String getIdUFOperadora() {
			return this.idUFOperadora;
		}

		public void setIdTipoRelacionamento(String idTipoRelacionamento) {
			this.idTipoRelacionamento = idTipoRelacionamento;
		}

		public String getIdTipoRelacionamento() {
			return this.idTipoRelacionamento;
		}

		public void setIdTipoLinha(String idTipoLinha) {
			this.idTipoLinha = idTipoLinha;
		}

		public String getIdTipoLinha() {
			return this.idTipoLinha;
		}
	}

	public FormLink getFormLink() {
		return this.formLink;
	}

	public AdmObjetoLinkVO getLinkById(String id, AdmObjetoLinkVO[] links) {
		for (int i = 0; i < links.length; i++) {
			if (links[i].getIdContatoInformacao().equals(id)) {
				return links[i];
			}
		}
		return null;
	}

}
