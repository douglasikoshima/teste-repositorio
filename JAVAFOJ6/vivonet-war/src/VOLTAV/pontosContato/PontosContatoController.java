package VOLTAV.pontosContato;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import br.com.vivo.fo.ctrls.VOLTAV.pontosContato.PontosContatoFacade;
import br.com.vivo.fo.voltav.vo.PontosContatoVODocument.PontosContatoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class PontosContatoController extends AbstractAction {

	private static final long serialVersionUID = -560193577298613687L;
	private String user;
	protected global.Global globalApp = new global.Global();
	private PontosContatoFormBean pontosContatoFormBean;
	private static transient NonCatalogLogger log = new NonCatalogLogger(PontosContatoController.class.getName());

	@EJB
	private PontosContatoFacade pontosContatoFacade;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("uploadArquivo".equals(mapping.getParameter())) {
			return uploadArquivo(mapping, form, request, response);
		} else if ("consultarProcessamento".equals(mapping.getParameter())) {
			return consultarProcessamento(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward uploadArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception, FileNotFoundException, IOException {
		PontosContatoFormBean form = (PontosContatoFormBean) formParam;
		try {

			String nmArquivo = new StringBuffer("VOL_PCONTATO_").append(getData()).append("_").append(this.getUser(request)).append(".txt").toString();

			LoadProperties properties = new LoadProperties(request);
			InputStream stream = form.getFile().getInputStream();

			int size = form.getFile().getFileSize();
			if (size == 0) {
				request.setAttribute("msgStatus", "Arquivo inválido.");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			String path = properties.get("PATH_VOLE_PONTOCONTATO");
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
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("PontosContatoController:uploadArquivo(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultaProcessamento.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward consultarProcessamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			PontosContatoVO pontosContatoVO = PontosContatoVO.Factory.newInstance();

			try {
				pontosContatoVO = pontosContatoFacade.consultarProcessamento(this.getUser(request));
			} catch (Exception e) {
				request.setAttribute("msgStatus", "Houve um problema durante a consulta de processamento.");
			}

			pontosContatoFormBean = new PontosContatoFormBean();
			pontosContatoFormBean.setPontosContatoVO(pontosContatoVO);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("PontosContatoController:consultarProcessamento(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public PontosContatoFormBean getPontosContatoFormBean() {
		if (this.pontosContatoFormBean == null) {
			return new PontosContatoFormBean();
		} else {
			return this.pontosContatoFormBean;
		}
	}

	public String getUser(HttpServletRequest request) {
		String sUser = ConstantesCRM.getCurrentUser(request.getSession());
		NumberFormat nf = new DecimalFormat(ConstantesCRM.SZERO);
		nf.setMinimumIntegerDigits(15);
		long l = Long.valueOf(sUser).longValue();
		sUser = nf.format(l);
		return sUser;
	}

	public static class PontosContatoFormBean extends ActionForm {

		private FormFile file;
		private PontosContatoVO pontosContatoVO;

		public void setFile(FormFile file) {
			this.file = file;
		}

		public FormFile getFile() {
			return this.file;
		}

		public void setPontosContatoVO(PontosContatoVO pontosContatoVO) {
			this.pontosContatoVO = pontosContatoVO;
		}

		public PontosContatoVO getPontosContatoVO() {
			return this.pontosContatoVO;
		}

	}

	private String getData() {
		String DATE_FORMAT = "yyyyMMddHHmm";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		return sdf.format(c1.getTime());
	}

}