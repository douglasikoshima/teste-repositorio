package campanha.CarregarLista;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.ListaApoioVODocument.ListaApoioVO;
import br.com.vivo.fo.campanha.vo.ListaCampanhaApoioVODocument.ListaCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.ListaStatusCampanhaVODocument.ListaStatusCampanhaVO.StatusVO;
import br.com.vivo.fo.campanha.vo.ListasCampanhaVODocument.ListasCampanhaVO.ListaCampanhaVO;
import br.com.vivo.fo.commons.utils.properties.LoadProperties;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"deprecation"})
public class CarregarListaController extends AbstractAction {

	private static final long serialVersionUID = 53502441783035549L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.ApoioFacade apoioFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.lista.ListaCampanhaFacade listaCampanhaFacade;

	private String user = ConstantesCRM.SVAZIO;

	private int idSubCampanha = 0;
	private int idCanalCampanha = 0;

	private StatusVO[] listaStatusCampanha = null;
	private ListaCampanhaVO[] listasCampanha = null;
	private ListaCampanhaApoioVO listaCampanha = null;

	private PesquisarListaActionForm pesquisarListaActionForm = new PesquisarListaActionForm();
	private AssociarListaActionForm associarListaActionForm = new AssociarListaActionForm();

	public StatusVO[] getListaStatusCampanha() {
		if (this.listaStatusCampanha == null) {
			this.listaStatusCampanha = new StatusVO[0];
		}
		return this.listaStatusCampanha;
	}

	public ListaCampanhaVO[] getListasCampanha() {
		if (this.listasCampanha == null) {
			this.listasCampanha = new ListaCampanhaVO[0];
		}
		return this.listasCampanha;
	}

	public PesquisarListaActionForm getPesquisarListaActionForm() {
		return this.pesquisarListaActionForm;
	}

	public void initListaCanais() throws Exception {

		GrupoCampanhaApoioVO tmp = apoioFacade.getApoioCanalCampanha(user, String.valueOf(idSubCampanha), 0);
		associarListaActionForm.setListaCanal(tmp.getSubGrupoApoioVOArray(0).getApoioVOArray());

	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("CarregarListaAction".equals(mapping.getParameter())) {
			return CarregarListaAction(mapping, form, request, response);
		} else if ("AssociarListaAction".equals(mapping.getParameter())) {
			return AssociarListaAction(mapping, form, request, response);
		} else if ("carregarListaBegin".equals(mapping.getParameter())) {
			return carregarListaBegin(mapping, form, request, response);
		} else if ("pesquisarListaAction".equals(mapping.getParameter())) {
			return pesquisarListaAction(mapping, form, request, response);
		} else if ("redirecionaFiltrarListas".equals(mapping.getParameter())) {
			return redirecionaFiltrarListas(mapping, form, request, response);
		} else if ("deleteLista".equals(mapping.getParameter())) {
			return deleteLista(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="CarregarListaExterna.jsp"
	 * @jpf:forward name="associar" path="AssociarListaAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());

		pageLoad(request);
		initListaCanais();
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("associar");
		// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		// return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void pageLoad(HttpServletRequest request) throws Exception {

		//String _idCampanha = request.getParameter("idCampanha");
		String _idSubCampanha = request.getParameter("idSubCampanha");

		try {
			//this.idCampanha = Integer.parseInt(_idCampanha);
			this.idSubCampanha = Integer.parseInt(_idSubCampanha);
		} catch (Exception e) {
			throw new Exception("Código campanha e sub campanha inválidos", e);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="carregarLista" path="CarregarListaExterna.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:validation-error-forward name="failure" path="CarregarListaExterna.jsp"
	 */
	protected ActionForward CarregarListaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			CarregarListaActionForm form = (CarregarListaActionForm) formParam;

			user = ConstantesCRM.getCurrentUser(request.getSession());

			char[] charArr = new char[(form.theFile.getFileSize())];

			String idLista = ConstantesCRM.SVAZIO;
			InputStreamReader inr = new InputStreamReader(form.theFile.getInputStream());
			inr.read(charArr);
			inr.close();
			StringWriter strwr = new StringWriter();
			strwr.write(charArr);
			String strFileName = StringEscapeUtils.escapeXml(form.theFile.getFileName());
			String strFile = strwr.getBuffer().toString();

			/*
			 * Inicio o processo de upload junto com o serviço
			 */

			RetornoVO retorno = listaCampanhaFacade.checaUploadLista(user, form.getNmLista(), strFileName);
			if (retorno.getValor().equals("0")) {
				/*
				 * Caso o RetornoVO.valor seja igual a zero, não conseguiu inserir.
				 */
				ActionErrors erros = new ActionErrors();
				erros.add("nmLista", new ActionError("cmp.lista.arquivo.jaExiste"));
				saveErrors(request, erros);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("carregarLista");
			} else {
				idLista = retorno.getValor();
			}

			// InitialContext ctx = new InitialContext();
			// ctx.rebind("CAMPANHA_UPLOAD_LISTAPATH_TMP", "D:\\temp\\upload\\tmp");

			// String path =(String)ctx.lookup("CAMPANHA_UPLOAD_LISTAPATH_TMP");
			LoadProperties prop = new LoadProperties(request);
			String path = prop.get("CAMPANHA_UPLOAD_LISTAPATH_TMP");

			if (path == null || path.equals(ConstantesCRM.SVAZIO)) {
				FormError formError = globalApp.buildFormError(new IOException("CAMPANHA_UPLOAD_LISTAPATH_TMP vazio!"), "/FrontOfficeWeb/campanha/CarregarLista/CarregarListaExterna.jsp");
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}

			File fileTmp = new File(path + File.separator + strFileName);
			BufferedWriter out = new BufferedWriter(new FileWriter(fileTmp));
			out.write(strFile);
			out.close();
			strwr.close();
			// Movendo o arquivo
			// ctx.rebind("CAMPANHA_UPLOAD_LISTAPATH_INBOX", "D:\\temp\\upload\\inbox");
			// path =(String)ctx.lookup("CAMPANHA_UPLOAD_LISTAPATH_INBOX");
			path = prop.get("CAMPANHA_UPLOAD_LISTAPATH_INBOX");

			if (path == null || path.equals("")) {
				FormError formError = globalApp.buildFormError(new IOException("CAMPANHA_UPLOAD_LISTAPATH_INBOX vazio!"), "/FrontOfficeWeb/campanha/CarregarLista/CarregarListaExterna.jsp");
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}

			File fileInbox = new File(path + File.separator + strFileName);
			fileTmp.renameTo(fileInbox);
			// Envio para o serviço o nome da lista
			listaCampanhaFacade.addUploadLista(user, idLista);
			form.setNmLista(ConstantesCRM.SVAZIO);

			/*
			 * Carrego novamente a lista de status Pois tenho um novo status devido a carga da lista
			 */
			carregarListaStatusCampanha(request);

		} catch (FileNotFoundException e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/CarregarLista/CarregarListaExterna.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (IOException e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/CarregarLista/CarregarListaExterna.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/CarregarLista/CarregarListaExterna.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		/*
		 * Caso o upload tenha tido sucesso, salvo o mesmo e informo o usuário para aguardar.
		 */
		ActionMessages msgs = new ActionMessages();
		msgs.add("sucesso", new ActionMessage("cmp.lista.upload.sucesso"));
		saveMessages(request, msgs);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("carregarLista");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AssociarListaCampanha.jsp"
	 */
	protected ActionForward AssociarListaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AssociarListaActionForm form = (AssociarListaActionForm) formParam;

		if ((request.getParameter(ConstantesCRM.SACTION) != null) && (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("salvar"))) {
			try {
				idCanalCampanha = Integer.parseInt(form.getCanalSelecionado());
			} catch (Exception e) {
				throw new Exception("Código Canal Atendimento inválido", e);
			}

			listaCampanhaFacade.setListasAssociadasCampanhaVO(user, form.getListaSelecionadaUtil(), idCanalCampanha, 1);
		}

		form.setlistaAssociadaFormVO(listaCampanha);

		if ((form.getCanalSelecionado() != null) && (!form.getCanalSelecionado().equals(ConstantesCRM.SVAZIO))) {

			listaCampanha = listaCampanhaFacade.getListasAssociadasCampanhaVO(user, Integer.parseInt(form.getCanalSelecionado()), 1);
			form.setListaDisp(listaCampanha.getListaApoioVOArray());

			listaCampanha = listaCampanhaFacade.getListasAssociadasCampanhaVO(user, Integer.parseInt(form.getCanalSelecionado()), 0);
			form.setListaUtil(listaCampanha.getListaApoioVOArray());
		}
		form.setListaCanal(associarListaActionForm.getListaCanal());
		associarListaActionForm = form;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public AssociarListaActionForm getAssociarListaActionForm() {
		return this.associarListaActionForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="filtrarListas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward carregarListaBegin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		pesquisarListaActionForm.clear();
		try {
			carregarListaStatusCampanha(request);
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void carregarListaStatusCampanha(HttpServletRequest request) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		listaStatusCampanha = listaCampanhaFacade.obterStatusLista(user).getStatusVOArray();
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="redirecionaFiltrarListas.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward pesquisarListaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		PesquisarListaActionForm form = (PesquisarListaActionForm) formParam;

		user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			this.listasCampanha = listaCampanhaFacade.pesquisarListas(user, form.getNmLista(), form.getInStatusCarga()).getListaCampanhaVOArray();
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/CarregarLista/carregarListaBegin.do");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		pesquisarListaActionForm = form;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="filtrarListas.jsp"
	 */
	protected ActionForward redirecionaFiltrarListas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="filtrarListas.jsp"
	 */
	protected ActionForward deleteLista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			listaCampanhaFacade.delListaCamp(user, request.getParameter("idLista"));
			this.listasCampanha = listaCampanhaFacade.pesquisarListas(user, pesquisarListaActionForm.getNmLista(), pesquisarListaActionForm.getInStatusCarga()).getListaCampanhaVOArray();
		} catch (TuxedoWarningException e) {
			// pesquisarListaActionForm.setMsgError(e.getXmlHeader().getStatusText());
			request.setAttribute("isMsgErroCargaLista", e.getXmlHeader().getStatusText());
		} catch (Exception e) {
			// pesquisarListaActionForm.setMsgError(e.getMessage());
			request.setAttribute("isMsgErroCargaLista", e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AssociarListaActionForm extends ActionForm {

		private static final long serialVersionUID = -2079925050404460011L;

		private int idCanalCampanha;
		private ListaCampanhaApoioVO listaAssociadaFormVO = ListaCampanhaApoioVO.Factory.newInstance();
		private ListaApoioVO[] listaUtil = new ListaApoioVO[0];
		private String listaSelecionadaUtil[] = new String[0];
		private ListaApoioVO[] listaDisp = new ListaApoioVO[0];
		private String listaSelecionadaDisp[] = new String[0];
		private ApoioVO[] listaCanal = new ApoioVO[0];
		private String canalSelecionado;

		public ApoioVO[] getListaCanal() {
			return this.listaCanal;
		}

		public void setListaCanal(ApoioVO[] lista) {
			this.listaCanal = lista;
		}

		public String getCanalSelecionado() {
			return this.canalSelecionado;
		}

		public void setCanalSelecionado(String s) {
			this.canalSelecionado = s;
		}

		public void setListaDisp(ListaApoioVO[] aDisp) {
			this.listaDisp = aDisp;
		}

		public ListaApoioVO[] getListaDisp() {
			return this.listaDisp;
		}

		public String[] getListaSelecionadaDisp() {
			return this.listaSelecionadaDisp;
		}

		public void setListaSelecionadaDisp(String[] s) {
			this.listaSelecionadaDisp = s;
		}

		public void setListaUtil(ListaApoioVO[] aUtil) {
			this.listaUtil = aUtil;
		}

		public ListaApoioVO[] getListaUtil() {
			return this.listaUtil;
		}

		public String[] getListaSelecionadaUtil() {
			return this.listaSelecionadaUtil;
		}

		public void setListaSelecionadaUtil(String[] s) {
			this.listaSelecionadaUtil = s;
		}

		public void setlistaAssociadaFormVO(ListaCampanhaApoioVO lista) {
			this.listaAssociadaFormVO = lista;
		}

		public ListaCampanhaApoioVO getlistaAssociadaFormVO() {
			return this.listaAssociadaFormVO;
		}

		public void setIdCanalCampanha(int i) {
			this.idCanalCampanha = i;
		}

		public int getIdCanalCampanha() {
			return this.idCanalCampanha;
		}

		public void clear() {
			idCanalCampanha = -1;
		}

	}

	public static class CarregarListaActionForm extends ActionForm {

		private static final long serialVersionUID = 2514656675123293504L;

		private int idCanalCampanha;
		private FormFile theFile;
		private String nmLista = ConstantesCRM.SVAZIO;

		public void setNmLista(String nmLista) {
			this.nmLista = nmLista;
		}

		public String getNmLista() {
			return this.nmLista;
		}

		public void setTheFile(FormFile theFile) {
			this.theFile = theFile;
		}

		public FormFile getTheFile() {
			return this.theFile;
		}

		public void setIdCanalCampanha(int idCanalCampanha) {
			this.idCanalCampanha = idCanalCampanha;
		}

		public int getIdCanalCampanha() {
			return this.idCanalCampanha;
		}

		public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
			ActionErrors erros = new ActionErrors();
			if (nmLista == null || nmLista.trim().equals("")) {
				erros.add("nmLista", new ActionError("cmp.lista.nome.invalido"));
			}

			if (theFile == null || theFile.getFileName().trim().equals("") || theFile.getFileSize() == 0) {
				erros.add("theFile", new ActionError("cmp.lista.arquivo.invalido", this.theFile.getFileName()));
			}

			if (theFile != null && theFile.getFileName().length() > 180) {
				erros.add("theFile", new ActionError("cmp.lista.arquivo.tamanho.invalido", theFile.getFileName(), " 180 caracteres!"));
			}
			return erros;
		}
	}

	public static class PesquisarListaActionForm extends ActionForm {

		private static final long serialVersionUID = 3875374477775320572L;

		private String inStatusCarga;
		private String nmLista;

		public void setNmLista(String nmLista) {
			this.nmLista = nmLista;
		}

		public String getNmLista() {
			return this.nmLista;
		}

		public void setInStatusCarga(String inStatusCarga) {
			this.inStatusCarga = inStatusCarga;
		}

		public String getInStatusCarga() {
			return this.inStatusCarga;
		}

		public void clear() {
			this.inStatusCarga = ConstantesCRM.SVAZIO;
			this.nmLista = ConstantesCRM.SVAZIO;
		}
	}
}
