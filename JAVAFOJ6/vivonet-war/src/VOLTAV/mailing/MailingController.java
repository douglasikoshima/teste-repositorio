package VOLTAV.mailing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.commons.utils.properties.LoadProperties;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.mailing.MailingFacade;
import br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.AreaBanner;
import br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.MailingBanner;
import br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.MailingLinha;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class MailingController extends AbstractAction {

	private static final long serialVersionUID = -1462139570465346171L;

	protected global.Global globalApp = new global.Global();
	private MailingFormBean mailingFormBean = null;
	private String user;
	private static transient NonCatalogLogger log = new NonCatalogLogger(MailingController.class.getName());
	private String REGISTROS_POR_PAGINA = "50";
	private int REGISTROS_POR_PAGINA_INT = 50;

	@EJB
	private MailingFacade mailingFacade;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("uploadArquivo".equals(mapping.getParameter())) {
			return uploadArquivo(mapping, form, request, response);
		} else if ("exibeInclusaoMailing".equals(mapping.getParameter())) {
			return exibeInclusaoMailing(mapping, form, request, response);
		} else if ("exibeAlteracaoMailing".equals(mapping.getParameter())) {
			return exibeAlteracaoMailing(mapping, form, request, response);
		} else if ("carregaDadosMailing".equals(mapping.getParameter())) {
			return carregaDadosMailing(mapping, form, request, response);
		} else if ("incluirMailing".equals(mapping.getParameter())) {
			return incluirMailing(mapping, form, request, response);
		} else if ("excluirMailing".equals(mapping.getParameter())) {
			return excluirMailing(mapping, form, request, response);
		} else if ("alterarMailing".equals(mapping.getParameter())) {
			return alterarMailing(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MailingFormBean form = (MailingFormBean) formParam;

		String linhaParam = null;
		String ddd = null;
		MailingLinha[] mailingLinha = null;
		this.setForm(form);

		if (form.getIdMailingBannerSelecionado() == null || form.getQueryPesquisa() == null || form.getQueryPesquisa().length() == 0) {
			this.mailingFormBean.setMailingLinha(new MailingLinha[0]);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		if ("linha".equals(form.getDsTipoPesquisa())) {
			linhaParam = form.getQueryPesquisa();
			mailingLinha = this.mailingFacade.pesquisarPorLinha(form.getIdMailingBannerSelecionado(), linhaParam);
		}

		if ("ddd".equals(form.getDsTipoPesquisa())) {
			ddd = form.getQueryPesquisa();
			mailingLinha = this.mailingFacade.pesquisarPorDDD(form.getIdMailingBannerSelecionado(), ddd);
		}
		if (mailingLinha == null || mailingLinha.length == 0) {
			request.setAttribute("msgStatus", "Nenhuma linha foi encontrada.");
			this.mailingFormBean.setMailingLinha(new MailingLinha[0]);
		} else {
			this.mailingFormBean.setMailingLinha(mailingLinha);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	public void listaMailing() throws Exception {
		MailingBanner[] mailingBanner = this.mailingFacade.listarMailing();
		MailingBanner mailingBannerOption = new MailingBanner();
		mailingBannerOption.setIdMailingBanner(ConstantesCRM.SZERO);
		mailingBannerOption.setNmMailingBanner("Selecione uma opção");
		MailingBanner[] listaMailing = new MailingBanner[mailingBanner.length + 1];
		listaMailing[0] = mailingBannerOption;
		for (int i = 0; i < mailingBanner.length; i++) {
			listaMailing[i + 1] = mailingBanner[i];
		}
		this.getMailingFormBean().setMailingBanner(listaMailing);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MailingFormBean form = (MailingFormBean) formParam;
		this.mailingFormBean = null;
		this.getMailingFormBean().setSemRegistros("false");

		String linhaParam = null;
		String ddd = null;
		String totalRegistro = ConstantesCRM.SZERO;

		AreaBanner[] areaBanner = this.mailingFacade.listarAreaBanner();
		MailingBanner[] mailingBanner = this.mailingFacade.listarMailing();

		AreaBanner areaBannerOption = new AreaBanner();
		areaBannerOption.setDsAreaBanner("Selecione uma opção");
		areaBannerOption.setIdAreaBanner(ConstantesCRM.SZERO);

		MailingBanner mailingBannerOption = new MailingBanner();
		mailingBannerOption.setIdMailingBanner(ConstantesCRM.SZERO);
		mailingBannerOption.setNmMailingBanner("Selecione uma opção");

		AreaBanner[] listaAreaBanner = new AreaBanner[areaBanner.length + 1];
		listaAreaBanner[0] = areaBannerOption;
		for (int i = 0; i < areaBanner.length; i++) {
			listaAreaBanner[i + 1] = (areaBanner[i]);
		}
		MailingBanner[] listaMailing = new MailingBanner[mailingBanner.length + 1];
		listaMailing[0] = mailingBannerOption;
		for (int i = 0; i < mailingBanner.length; i++) {
			listaMailing[i + 1] = mailingBanner[i];
		}

		this.getMailingFormBean().setMailingLinha(new MailingLinha[0]);
		this.getMailingFormBean().setAcao("incluir");
		this.getMailingFormBean().setAreaBanner(listaAreaBanner);
		this.getMailingFormBean().setMailingBanner(listaMailing);
		this.getMailingFormBean().setLinhasMailing(new MailingLinha[] { new MailingLinha() });

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward uploadArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception, FileNotFoundException, IOException {
		MailingFormBean form = (MailingFormBean) formParam;
		try {

			String nmArquivo = new StringBuffer("MAILINGBANNER").append(this.getData()).append(new Random().nextInt(5000)).append(".txt").toString();

			LoadProperties properties = new LoadProperties(request);
			InputStream stream = form.getFile().getInputStream();

			int size = form.getFile().getFileSize();
			if (size == 0) {
				request.setAttribute("msgStatus", "Arquivo inválido.");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			String path = properties.get("PATH_VOL_MAILING");
			if (path.charAt(path.length() - 1) != '/') {
				path += "/";
			}

			OutputStream bos = new FileOutputStream(path + nmArquivo);

			byte[] buffer = new byte[256 * 1024];
			int bytesRead = 0;
			while ((bytesRead = stream.read(buffer, 0, (256 * 1024))) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.close();
			stream.close();

			request.setAttribute("msgStatus", "Arquivo enviado com sucesso.");

			// gravar nome no banco
			this.mailingFacade.incluirArquivo(nmArquivo, this.mailingFormBean.getIdMailingBannerSelecionado(), ConstantesCRM.TUX_USER_DEFAULT);
			this.mailingFormBean.setIdMailingBannerSelecionado("0");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("MailingController:uploadArquivo(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			this.mailingFormBean.setErroOracle(e.getMessage());
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward exibeInclusaoMailing(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		MailingFormBean form = (MailingFormBean) formParam;
		if (form.getAcao().equals(this.mailingFormBean.getAcao())) {
			this.setForm(form);
		} else {
			this.mailingFormBean.setAcao(form.getAcao());
			this.mailingFormBean.setDtVigenciaInicio(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setDtVigenciaFim(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setIdAreaBannerSelecionado(ConstantesCRM.SZERO);
			this.mailingFormBean.setIdMailingBannerSelecionado(ConstantesCRM.SZERO);
			this.mailingFormBean.setMailingLinha(new MailingLinha[0]);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public void setForm(MailingFormBean form) {
		this.mailingFormBean.setOperacao(form.getOperacao());
		this.mailingFormBean.setAcao(form.getAcao());
		this.mailingFormBean.setDtVigenciaFim(form.getDtVigenciaFim());
		this.mailingFormBean.setDtVigenciaInicio(form.getDtVigenciaInicio());
		this.mailingFormBean.setNmMailingBanner(form.getNmMailingBanner());
		this.mailingFormBean.setIdAreaBannerSelecionado(form.getIdAreaBannerSelecionado());
		this.mailingFormBean.setIdMailingBannerSelecionado(form.getIdMailingBannerSelecionado());
		this.mailingFormBean.setQueryPesquisa(form.getQueryPesquisa());
		this.mailingFormBean.setDsTipoPesquisa(form.getDsTipoPesquisa());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward exibeAlteracaoMailing(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MailingFormBean form = (MailingFormBean) formParam;
		if (form.getAcao().equals(this.mailingFormBean.getAcao())) {
			this.setForm(form);
		} else {
			this.mailingFormBean.setAcao(form.getAcao());
			this.mailingFormBean.setDtVigenciaInicio(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setDtVigenciaFim(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setIdAreaBannerSelecionado(ConstantesCRM.SZERO);
			this.mailingFormBean.setIdMailingBannerSelecionado(ConstantesCRM.SZERO);
			this.mailingFormBean.setMailingLinha(new MailingLinha[0]);
		}
		this.listaMailing();
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward carregaDadosMailing(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MailingFormBean form = (MailingFormBean) formParam;
		this.setForm(form);
		// limpar pesquisa
		this.mailingFormBean.setMailingLinha(new MailingLinha[0]);
		this.listaMailing();
		MailingBanner[] mailingBanner = this.mailingFormBean.getMailingBanner();
		for (int i = 0; i < mailingBanner.length; i++) {
			if (mailingBanner[i].getIdMailingBanner().equals(form.getIdMailingBannerSelecionado())) {
				this.mailingFormBean.setDtVigenciaInicio(mailingBanner[i].getDtVigenciaInicio());
				this.mailingFormBean.setDtVigenciaFim(mailingBanner[i].getDtVigenciaFim());
				AreaBanner[] areaBanner = this.mailingFormBean.getAreaBanner();
				for (int j = 0; j < areaBanner.length; j++) {
					if (areaBanner[j].getIdAreaBanner().equals(mailingBanner[i].getIdAreaBanner())) {
						this.mailingFormBean.setDsAreaBannerSelecionado(areaBanner[j].getDsAreaBanner());
					}
				}
				break;
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="upload" path="uploadArquivo.do"
	 */
	protected ActionForward incluirMailing(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MailingFormBean form = (MailingFormBean) formParam;
		this.setForm(form);
		try {
			String idMailingBanner = this.mailingFacade.incluirMailing(form.getNmMailingBanner(), form.getDtVigenciaInicio(), form.getDtVigenciaFim(), form.getIdAreaBannerSelecionado());
			this.listaMailing();
			request.setAttribute("msgStatus", "O Mailing foi incluído com sucesso.");
			// zerar formulario
			this.mailingFormBean.setAcao(form.getAcao());
			this.mailingFormBean.setDtVigenciaInicio(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setDtVigenciaFim(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setIdAreaBannerSelecionado(ConstantesCRM.SZERO);
			this.mailingFormBean.setIdMailingBannerSelecionado(idMailingBanner);
			this.mailingFormBean.setNmMailingBanner(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setMailingLinha(new MailingLinha[0]);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("upload");
		} catch (Exception ex) {
			request.setAttribute("msgStatus", "Ocorreu um erro na inclusão do Mailing, tente novamente.");
			this.log.error("Erro ao inserir mailing no VIVONET causa: " + ex.getMessage());
			this.mailingFormBean.setErroOracle(ex.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward excluirMailing(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MailingFormBean form = (MailingFormBean) formParam;
		this.setForm(form);
		try {
			this.mailingFacade.excluirMailing(form.getIdMailingBannerSelecionado());
			this.listaMailing();
			request.setAttribute("msgStatus", "O Mailing foi excluído com sucesso.");
			// zerar formulario
			this.mailingFormBean.setAcao(form.getAcao());
			this.mailingFormBean.setDtVigenciaInicio(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setDtVigenciaFim(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setIdAreaBannerSelecionado(ConstantesCRM.SZERO);
			this.mailingFormBean.setIdMailingBannerSelecionado(ConstantesCRM.SZERO);
			this.mailingFormBean.setNmMailingBanner(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setMailingLinha(new MailingLinha[0]);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception ex) {
			request.setAttribute("msgStatus", "Ocorreu um erro na exclusão do Mailing, tente novamente.");
			this.log.error("Erro ao excluir mailing no VIVONET causa: " + ex.getMessage());
			this.mailingFormBean.setErroOracle(ex.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="upload" path="uploadArquivo.do"
	 */
	protected ActionForward alterarMailing(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MailingFormBean form = (MailingFormBean) formParam;
		try {
			this.mailingFormBean.setOperacao(form.getOperacao());
			this.mailingFormBean.setAcao(form.getAcao());
			this.mailingFormBean.setIdAreaBannerSelecionado(form.getIdAreaBannerSelecionado());
			this.mailingFormBean.setIdMailingBannerSelecionado(form.getIdMailingBannerSelecionado());
			this.mailingFacade.alterarMailing(form.getIdMailingBannerSelecionado(), form.getDtVigenciaInicio(), form.getDtVigenciaFim());
			this.listaMailing();
			// zerar formulário
			request.setAttribute("msgStatus", "O Mailing foi alterado com sucesso.");
			this.mailingFormBean.setAcao(form.getAcao());
			this.mailingFormBean.setDtVigenciaInicio(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setDtVigenciaFim(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setIdAreaBannerSelecionado(ConstantesCRM.SZERO);
			// this.mailingFormBean.setIdMailingBannerSelecionado("0");
			this.mailingFormBean.setDsAreaBannerSelecionado(ConstantesCRM.SVAZIO);
			this.mailingFormBean.setMailingLinha(new MailingLinha[0]);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("upload");
		} catch (Exception ex) {
			request.setAttribute("msgStatus", "Ocorreu um erro na alteração do Mailing, tente novamente.");
			this.log.error("Erro ao inserir mailing no VIVONET causa: " + ex.getMessage());
			this.mailingFormBean.setErroOracle(ex.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward incluirLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		MailingFormBean form = (MailingFormBean) formParam;
		try {
			this.setForm(form);
			this.mailingFacade.incluirLinha(form.getDdd(), form.getLinha(), form.getIdMailingBannerSelecionado());
			request.setAttribute("msgStatus", "Inclusão da Linha realizada com sucesso.");
		} catch (Exception e) {
			if (e.getMessage().indexOf("ORA-00001") > 0) {
				request.setAttribute("msgStatus", "Não foi possível incluir a linha solicitada. Registro duplicado.");
			} else if (e.getMessage().indexOf("00002") > 0) {
				request.setAttribute("msgStatus", "Não foi possível incluir a linha solicitada. Linha inexistente.");
			} else {
				request.setAttribute("msgStatus", "Ocorreu um erro na inclusão da Linha, tente novamente.");
			}
			this.mailingFormBean.setErroOracle(e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 */
	protected ActionForward excluirLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		MailingFormBean form = (MailingFormBean) formParam;
		log.info("MailingController.excluirLinha() BEGIN");
		try {
			String ddd = form.getDddLinha().substring(0, 2);
			String linha = form.getDddLinha().substring(2);
			this.mailingFacade.excluirLinha(ddd, linha, form.getIdMailingBannerSelecionado());
			request.setAttribute("msgStatus", "Exclusão da Linha realizada com sucesso.");
		} catch (Exception e) {
			log.error("MailingController.excluirLinha", e);
			request.setAttribute("msgStatus", "Ocorreu um erro na exclusão da Linha, tente novamente.");
			this.mailingFormBean.setErroOracle(e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class MailingFormBean extends ActionForm {

		private FormFile file;
		private String semRegistros;
		private String queryPesquisa;
		private String dsTipoPesquisa;
		private MailingLinha[] linhasMailing;
		private String paginaAtual;
		private String totalPagina;
		private String dddLinha;
		private String ddd;
		private String linha;
		private MailingBanner[] mailingBanner;
		private AreaBanner[] areaBanner;
		private String acao;
		private String nmMailingBanner;
		private String dtVigenciaInicio;
		private String dtVigenciaFim;
		private String idAreaBannerSelecionado;
		private String idMailingBannerSelecionado;
		private String operacao;
		private String dsAreaBannerSelecionado;
		private String erroOracle;
		MailingLinha[] mailingLinha;

		public void setMailingLinha(MailingLinha[] mailingLinha) {
			this.mailingLinha = mailingLinha;
		}

		public MailingLinha[] getMailingLinha() {
			return this.mailingLinha;
		}

		public String getErroOracle() {
			return this.erroOracle;
		}

		public void setErroOracle(String erro) {
			this.erroOracle = erro;
		}

		public void setDsAreaBannerSelecionado(String dsAreaBannerSelecionado) {
			this.dsAreaBannerSelecionado = dsAreaBannerSelecionado;
		}

		public String getDsAreaBannerSelecionado() {
			return this.dsAreaBannerSelecionado;
		}

		public void setIdMailingBannerSelecionado(String idMailingBannerSelecionado) {
			this.idMailingBannerSelecionado = idMailingBannerSelecionado;
		}

		public String getIdMailingBannerSelecionado() {
			return this.idMailingBannerSelecionado;
		}

		public String getOperacao() {
			return this.operacao;
		}

		public void setOperacao(String operacao) {
			this.operacao = operacao;
		}

		public String getIdAreaBannerSelecionado() {
			return this.idAreaBannerSelecionado;
		}

		public void setIdAreaBannerSelecionado(String idAreaBannerSelecionado) {
			this.idAreaBannerSelecionado = idAreaBannerSelecionado;
		}

		public String getDtVigenciaFim() {
			return this.dtVigenciaFim;
		}

		public void setDtVigenciaFim(String dtVigenciaFim) {
			this.dtVigenciaFim = dtVigenciaFim;
		}

		public String getDtVigenciaInicio() {
			return this.dtVigenciaInicio;
		}

		public void setDtVigenciaInicio(String dtVigenciaInicio) {
			this.dtVigenciaInicio = dtVigenciaInicio;
		}

		public void setNmMailingBanner(String nmMailingBanner) {
			this.nmMailingBanner = nmMailingBanner;
		}

		public String getNmMailingBanner() {
			return this.nmMailingBanner;
		}

		public void setAcao(String acao) {
			this.acao = acao;
		}

		public String getAcao() {
			return this.acao;
		}

		public void setMailingBanner(MailingBanner[] mailingBanner) {
			this.mailingBanner = mailingBanner;
		}

		public MailingBanner[] getMailingBanner() {
			return this.mailingBanner;
		}

		public void setAreaBanner(AreaBanner[] areaBanner) {
			this.areaBanner = areaBanner;
		}

		public AreaBanner[] getAreaBanner() {
			return this.areaBanner;
		}

		public void setDdd(String ddd) {
			this.ddd = ddd;
		}

		public String getDdd() {
			return this.ddd;
		}

		public void setLinha(String linha) {
			this.linha = linha;
		}

		public String getLinha() {
			return this.linha;
		}

		public void setFile(FormFile file) {
			this.file = file;
		}

		public FormFile getFile() {
			return this.file;
		}

		public void setSemRegistros(String semRegistros) {
			this.semRegistros = semRegistros;
		}

		public String getSemRegistros() {
			if (this.semRegistros == null) {
				this.semRegistros = ConstantesCRM.SVAZIO;
			}
			return this.semRegistros;
		}

		public void setDsTipoPesquisa(String dsTipoPesquisa) {
			this.dsTipoPesquisa = dsTipoPesquisa;
		}

		public String getDsTipoPesquisa() {
			return this.dsTipoPesquisa;
		}

		public void setQueryPesquisa(String queryPesquisa) {
			this.queryPesquisa = queryPesquisa;
		}

		public String getQueryPesquisa() {
			return this.queryPesquisa;
		}

		public void setLinhasMailing(MailingLinha[] linhasMailing) {
			this.linhasMailing = linhasMailing;
		}

		public MailingLinha[] getLinhasMailing() {
			return this.linhasMailing;
		}

		public void setPaginaAtual(String paginaAtual) {
			this.paginaAtual = paginaAtual;
		}

		public String getPaginaAtual() {
			if (this.paginaAtual == null) {
				this.paginaAtual = ConstantesCRM.SVAZIO;
			}
			return this.paginaAtual;
		}

		public void setTotalPagina(String totalPagina) {
			this.totalPagina = totalPagina;
		}

		public String getTotalPagina() {
			if (this.totalPagina == null) {
				this.totalPagina = ConstantesCRM.SVAZIO;
			}
			return this.totalPagina;
		}

		public void setDddLinha(String dddLinha) {
			this.dddLinha = dddLinha;
		}

		public String getDddLinha() {
			return this.dddLinha;
		}
	}

	public void setMailingFormBean(MailingFormBean formParam) {

		this.mailingFormBean = formParam;
	}

	public MailingFormBean getMailingFormBean() {

		if (this.mailingFormBean == null) {
			this.mailingFormBean = new MailingFormBean();
		}

		return this.mailingFormBean;
	}

	public String getUser(HttpServletRequest request) {
		String sUser = ConstantesCRM.getCurrentUser(request.getSession());
		NumberFormat nf = new DecimalFormat(ConstantesCRM.SZERO);
		nf.setMinimumIntegerDigits(15);
		long l = Long.valueOf(sUser).longValue();
		sUser = nf.format(l);
		return sUser;
	}

	private String getData() {
		String DATE_FORMAT = "yyyyMMddHHmm";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		return sdf.format(c1.getTime());
	}
}
