package usuario.manterLote;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import br.com.vivo.fo.commons.utils.properties.LoadProperties;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterLote.ManterLoteFacade;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.LoteStatusUsuarioVODocument.LoteStatusUsuarioVO;

import com.bea.common.security.xacml.IOException;
import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterLoteController extends AbstractAction {

	private static final long serialVersionUID = 3579990460963803618L;

	@EJB
	private ManterLoteFacade manterLoteTux;

	protected global.Global globalApp = new global.Global();
	private ManterLoteFormBean manterLoteFormBean = new ManterLoteFormBean();
	private String user = ConstantesCRM.SVAZIO;

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("beginUpload".equals(mapping.getParameter())) {
			return beginUpload(mapping, form, request, response);
		} else if ("beginDownload".equals(mapping.getParameter())) {
			return beginDownload(mapping, form, request, response);
		} else if ("uploadArquivo".equals(mapping.getParameter())) {
			return uploadArquivo(mapping, form, request, response);
		} else if ("gerarListaArquivos".equals(mapping.getParameter())) {
			return gerarListaArquivos(mapping, form, request, response);
		} else if ("downloadArquivo".equals(mapping.getParameter())) {
			return downloadArquivo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="upload" path="beginUpload.do"
	 * @jpf:forward name="download" path="beginDownload.do"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(request.getParameter("action"));
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="uploadArquivos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward beginUpload(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("ManterLoteController:beginUpload" + "( " + user + " )");
		try {
			manterLoteFormBean = new ManterLoteFormBean();
			getManterLoteFormBean().setListaIdDescricaoVO(manterLoteTux.getListaIdDescricaoVO(user).getItemListaIdDescricaoVOArray());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			log.error("ManterLoteController:beginUpload" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="downloadArquivos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward beginDownload(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterLoteController:beginDownload" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		try {
			manterLoteFormBean = new ManterLoteFormBean();
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			log.error("ManterLoteController:beginDownload" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="beginUpload.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward uploadArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception, FileNotFoundException, IOException {
		ManterLoteFormBean form = (ManterLoteFormBean) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		String nmArquivo = form.getFile().getFileName();

		LoadProperties properties = new LoadProperties(request);

		InputStream stream = form.getFile().getInputStream();

		int size = form.getFile().getFileSize();
		if (size == 0) {
			request.setAttribute("msgStatus", "Arquivo selecionado inválido.");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		nmArquivo = nmArquivo.substring(0, nmArquivo.length() - 4) + "." + form.getIdStatusLote() + "." + user + nmArquivo.substring(nmArquivo.length() - 4, nmArquivo.length());

		String path = properties.get("USUARIO_MANTER_LOTE_ENTRADA");
		if (path.charAt(path.length() - 1) != '/') {
			path += "/";
		}

		OutputStream bos = new FileOutputStream(path + nmArquivo.toUpperCase());

		byte[] buffer = new byte[size];
		int bytesRead = 0;
		while ((bytesRead = stream.read(buffer, 0, size)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}

		bos.close();
		stream.close();

		request.setAttribute("msgStatus", "Arquivo enviado com sucesso.");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="downloadArquivos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gerarListaArquivos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception, FileNotFoundException, IOException {
		ManterLoteFormBean form = (ManterLoteFormBean) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		LoadProperties properties = new LoadProperties(request);
		String dir = properties.get("USUARIO_MANTER_LOTE_SAIDA");

		int j = 0;

		String dtProcessamento, cdSD, dsStatus, nmArquivo, nmArquivoOriginal = ConstantesCRM.SVAZIO;

		File diretorio = new File(dir);
		String[] arquivos = diretorio.list();

		LoteStatusUsuarioVO[] loteStatusUsuarioVO = new LoteStatusUsuarioVO[arquivos.length];

		for (int i = 0; i < arquivos.length; i++) {
			nmArquivoOriginal = arquivos[i];
			dsStatus = arquivos[i].substring(arquivos[i].lastIndexOf(".") + 1, arquivos[i].length());
			if ("Concluido".equals(dsStatus) || "Processamento".equals(dsStatus) || "Pendente".equals(dsStatus)) {
				nmArquivo = arquivos[i].substring(0, arquivos[i].lastIndexOf("."));
				dtProcessamento = arquivos[i].substring(16, 18) + "/" + arquivos[i].substring(14, 16) + "/" + arquivos[i].substring(10, 14);
				cdSD = arquivos[i].substring(21, 28);
				if (validarDataPorPeriodo(form.getDtInicial(), form.getDtFinal(), dtProcessamento)) {
					loteStatusUsuarioVO[j] = LoteStatusUsuarioVO.Factory.newInstance();
					loteStatusUsuarioVO[j].setNmArquivo(nmArquivo);
					loteStatusUsuarioVO[j].setDtProcessamento(dtProcessamento);
					loteStatusUsuarioVO[j].setCdSD(cdSD);
					loteStatusUsuarioVO[j].setNmArquivoOriginal(nmArquivoOriginal);
					loteStatusUsuarioVO[j].setDsStatus(dsStatus);
					j++;
				}
			}
		}

		if (j == 0) {
			request.setAttribute("msgStatus", "Nenhum registro encontrado.");
			getManterLoteFormBean().setLoteStatusUsuarioVO(null);

		} else {
			LoteStatusUsuarioVO[] loteStatusUsuarioVOFinal = new LoteStatusUsuarioVO[j];
			for (j = 0; j < loteStatusUsuarioVOFinal.length; j++) {
				if (loteStatusUsuarioVO[j] != null) {
					loteStatusUsuarioVOFinal[j] = LoteStatusUsuarioVO.Factory.newInstance();
					loteStatusUsuarioVOFinal[j].setNmArquivo(loteStatusUsuarioVO[j].getNmArquivo());
					loteStatusUsuarioVOFinal[j].setDtProcessamento(loteStatusUsuarioVO[j].getDtProcessamento());
					loteStatusUsuarioVOFinal[j].setCdSD(loteStatusUsuarioVO[j].getCdSD());
					loteStatusUsuarioVOFinal[j].setNmArquivoOriginal(loteStatusUsuarioVO[j].getNmArquivoOriginal());
					loteStatusUsuarioVOFinal[j].setDsStatus(loteStatusUsuarioVO[j].getDsStatus());
				}
			}
			getManterLoteFormBean().setLoteStatusUsuarioVO(loteStatusUsuarioVOFinal);
		}
		getManterLoteFormBean().setDtInicial(form.getDtInicial());
		getManterLoteFormBean().setDtFinal(form.getDtFinal());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="downloadArquivos.jsp"
	 */
	public ActionForward downloadArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {

		LoadProperties properties = new LoadProperties(request);
		String dir = properties.get("USUARIO_MANTER_LOTE_SAIDA");

		File diretorio = new File(dir);
		File[] arquivos = diretorio.listFiles();

		for (int i = 0; i < arquivos.length; i++) {
			if (arquivos[i].getName().equals(request.getParameter("idxArquivo"))) {
				response.addHeader("Content-Disposition", "attachment; filename=" + arquivos[i].getName().substring(0, arquivos[i].getName().lastIndexOf(".")));
				response.addHeader("Content-Type", "application/force-download");
				response.addHeader("Content-Transfer-Encoding", "binary");
				response.addHeader("Pragma", "no-cache");
				response.addHeader("Expires", ConstantesCRM.SZERO);

				FileInputStream fis = new FileInputStream(arquivos[i]);
				PrintWriter pw = response.getWriter();
				int c = -1;
				while ((c = fis.read()) != -1) {
					pw.print((char) c);
				}
				fis.close();
				pw.flush();
				pw = null;
				return null;
			}
		}
		return null;
	}

	public static byte[] read(File file) throws IOException {
		byte[] content = null;
		int fileLength = (int) file.length();
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);

			BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
			content = new byte[fileLength];
			bufferedInput.read(content, 0, fileLength);
			bufferedInput.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();

		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (java.io.IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}

	/**
	 * Funcao utilizada para validar se uma especifica data enviada encontra-se no periodo solicitado.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @param dataInput
	 */
	private boolean validarDataPorPeriodo(String dataInicial, String dataFinal, String dataInput) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dtInicial = df.parse(dataInicial);
		Date dtFinal = df.parse(dataFinal);
		Date dtInput = df.parse(dataInput);
		if ((dtInicial.before(dtInput) && dtFinal.after(dtInput)) || dtInput.equals(dtInicial) || dtInput.equals(dtFinal)) {
			return true;
		} else {
			return false;
		}
	}

	public ManterLoteFormBean getManterLoteFormBean() {
		if (this.manterLoteFormBean == null) {
			return new ManterLoteFormBean();
		} else {
			return this.manterLoteFormBean;
		}
	}

	public static class ManterLoteFormBean extends ActionForm {

		private static final long serialVersionUID = 3487162554743394179L;

		private br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO.ItemListaIdDescricaoVO[] listaIdDescricaoVO;
		private LoteStatusUsuarioVO[] loteStatusUsuarioVO;
		private String dtFinal;
		private String dtInicial;
		private String idStatusLote;
		private FormFile file;

		public void setFile(FormFile file) {
			this.file = file;
		}

		public FormFile getFile() {
			return this.file;
		}

		public void setIdStatusLote(String idStatusLote) {
			this.idStatusLote = idStatusLote;
		}

		public String getIdStatusLote() {
			if (this.idStatusLote == null) {
				return ConstantesCRM.SVAZIO;
			} else {
				return this.idStatusLote;
			}
		}

		public void setDtInicial(String dtInicial) {
			this.dtInicial = dtInicial;
		}

		public String getDtInicial() {
			return this.dtInicial;
		}

		public void setDtFinal(String dtFinal) {
			this.dtFinal = dtFinal;
		}

		public String getDtFinal() {
			return this.dtFinal;
		}

		public void setLoteStatusUsuarioVO(LoteStatusUsuarioVO[] loteStatusUsuarioVO) {
			this.loteStatusUsuarioVO = loteStatusUsuarioVO;
		}

		public LoteStatusUsuarioVO[] getLoteStatusUsuarioVO() {
			return this.loteStatusUsuarioVO;
		}

		public void setListaIdDescricaoVO(br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO.ItemListaIdDescricaoVO[] listaIdDescricaoVO) {
			this.listaIdDescricaoVO = listaIdDescricaoVO;
		}

		public br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO.ItemListaIdDescricaoVO[] getListaIdDescricaoVO() {
			return this.listaIdDescricaoVO;
		}
	}
}
