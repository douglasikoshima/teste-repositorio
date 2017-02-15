package VOLTAV.iPhone;

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
import br.com.vivo.fo.ctrls.VOLTAV.iPhone.ManterIphoneFacade;
import br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB.LinhaIphone;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class IPhoneController extends AbstractAction {

	protected global.Global                   globalApp;
	private   IPhoneFormBean                  iPhoneFormBean = null;
	private   String                          user;
	private static transient NonCatalogLogger log = new NonCatalogLogger(IPhoneController.class.getName());
	private String REGISTROS_POR_PAGINA = "50";
	private int REGISTROS_POR_PAGINA_INT = 50;

	@EJB
	private ManterIphoneFacade manterIphone;

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IPhoneFormBean form = (IPhoneFormBean)formParam;

		String linhaParam = null;
		String ddd        = null;

		if("linha".equals(form.getDsTipoPesquisa())){
			linhaParam = form.getQueryPesquisa();
		}

		if("ddd".equals(form.getDsTipoPesquisa())){
			ddd = form.getQueryPesquisa();
		}

		//LinhaIphone[] linhasIphone = manterIphone.getPesquisarLinhas(ddd, linhaParam);

		this.getIPhoneFormBean().setLinhasIphone(manterIphone.getPesquisarLinhas(ddd, linhaParam));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("uploadArquivo".equals(mapping.getParameter())) {
			return uploadArquivo(mapping, form, request, response);
		} else if ("incluirLinha".equals(mapping.getParameter())) {
			return incluirLinha(mapping, form, request, response);
		} else if ("excluirLinha".equals(mapping.getParameter())) {
			return excluirLinha(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IPhoneFormBean form = (IPhoneFormBean)formParam;
		this.getIPhoneFormBean().setSemRegistros("false");

		String linhaParam = null;
		String ddd = null;
		String totalRegistro = ConstantesCRM.SZERO;

		this.getIPhoneFormBean().setDsTipoPesquisa(form.getDsTipoPesquisa());
		this.getIPhoneFormBean().setQueryPesquisa(form.getQueryPesquisa());

		if(form.getQueryPesquisa() != null && !ConstantesCRM.SVAZIO.equals(form.getQueryPesquisa()) && !ConstantesCRM.SZERO.equals(form.getQueryPesquisa())){
			if("linha".equals(form.getDsTipoPesquisa())){
				linhaParam = form.getQueryPesquisa();
			}
			if("ddd".equals(form.getDsTipoPesquisa())){
				ddd = form.getQueryPesquisa();
			}
		}

		LinhaIphone[] linhasIphone = manterIphone.getLinhasIphone(ConstantesCRM.SONE, REGISTROS_POR_PAGINA, ddd, linhaParam);

		this.getIPhoneFormBean().setLinhasIphone(linhasIphone);
		this.getIPhoneFormBean().setPaginaAtual(ConstantesCRM.SONE);

		LinhaIphone linha = manterIphone.getTotalLinhasIphone(ddd, linhaParam);
		totalRegistro = linha.getTotalRegistros();

		String totalPagina = String.valueOf((Integer.parseInt(totalRegistro) / Integer.parseInt(REGISTROS_POR_PAGINA) ));
		int mod = (Integer.parseInt(totalRegistro) % Integer.parseInt(REGISTROS_POR_PAGINA));
		if(mod > 0){
			totalPagina = String.valueOf((Integer.parseInt(totalPagina) + 1));
		}
		if(linhasIphone.length < 1){

			totalPagina = ConstantesCRM.SZERO;
			getIPhoneFormBean().setPaginaAtual(ConstantesCRM.SZERO);
		}
		this.getIPhoneFormBean().setTotalPagina(totalPagina);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}




	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward uploadArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception, FileNotFoundException, IOException {
		IPhoneFormBean form = (IPhoneFormBean)formParam;

		try {

			String nmArquivo = new StringBuffer("MAILING")
			.append(getData())
			.append("_")
			.append(this.getUser(request))
			.append(".txt")
			.toString();

			LoadProperties properties = new LoadProperties(request);
			InputStream stream = form.getFile().getInputStream();

			int size = form.getFile().getFileSize();
			if (size == 0) {
				request.setAttribute("msgStatus","Arquivo inválido.");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			String path = properties.get("PATH_VOLE_IPHONE");
			if(path.charAt(path.length()-1)!='/') {
				path += "/";
			}

			OutputStream bos = new FileOutputStream(path + nmArquivo);

			byte[] buffer = new byte[256*1024];
			int bytesRead = 0;
			while ((bytesRead = stream.read(buffer, 0, (256*1024))) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.close();
			stream.close();

			request.setAttribute("msgStatus","Arquivo enviado com sucesso.");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("IPhoneController:uploadArquivo(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward incluirLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		IPhoneFormBean form = (IPhoneFormBean)formParam;
		try{
			manterIphone.incluirLinha(form.getDdd(),form.getLinha());
			request.setAttribute("msgStatus", "Inclusão da Linha realizada com sucesso.");
		}catch(Exception e){
			if(e.getMessage().indexOf("ORA-00001") > 0){
				request.setAttribute("msgStatus", "Não foi possível incluir a linha solicitada. Registro duplicado.");
			}else if(e.getMessage().indexOf("00002") > 0){
				request.setAttribute("msgStatus", "Não foi possível incluir a linha solicitada. Linha inexistente.");
			}else{
				request.setAttribute("msgStatus", "Ocorreu um erro na inclusão da Linha, tente novamente.");
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward excluirLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)
	{
		IPhoneFormBean form = (IPhoneFormBean)formParam;
		log.info("IPhoneController.excluirLinha() BEGIN");
		try{
			String ddd   = form.getDddLinha().substring(0,2);
			String linha = form.getDddLinha().substring(2);

			manterIphone.excluirLinha(ddd,linha);

			request.setAttribute("msgStatus", "Exclusão da Linha realizada com sucesso.");
		}catch(Exception e){
			log.error("IPhoneController.excluirLinha", e);
			request.setAttribute("msgStatus", "Ocorreu um erro na exclusão da Linha, tente novamente.");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}








	public static class IPhoneFormBean extends ActionForm {

		private FormFile file;
		private String semRegistros;
		private String queryPesquisa;
		private String dsTipoPesquisa;
		private LinhaIphone[] linhasIphone;
		private String paginaAtual;
		private String totalPagina;
		private String dddLinha;
		private String ddd;
		private String linha;


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

		public void setSemRegistros(String semRegistros)
		{
			this.semRegistros = semRegistros;
		}

		public String getSemRegistros()
		{
			if(this.semRegistros == null){
				this.semRegistros = ConstantesCRM.SVAZIO;
			}
			return this.semRegistros;
		}

		public void setDsTipoPesquisa(String dsTipoPesquisa)
		{
			this.dsTipoPesquisa = dsTipoPesquisa;
		}

		public String getDsTipoPesquisa()
		{
			return this.dsTipoPesquisa;
		}

		public void setQueryPesquisa(String queryPesquisa)
		{
			this.queryPesquisa = queryPesquisa;
		}

		public String getQueryPesquisa()
		{
			return this.queryPesquisa;
		}

		public void setLinhasIphone(LinhaIphone[] linhasIphone)
		{
			this.linhasIphone = linhasIphone;
		}

		public LinhaIphone[] getLinhasIphone()
		{
			return this.linhasIphone;
		}
		public void setPaginaAtual(String paginaAtual)
		{
			this.paginaAtual = paginaAtual;
		}

		public String getPaginaAtual()
		{
			if(this.paginaAtual == null){
				this.paginaAtual = ConstantesCRM.SVAZIO;
			}
			return this.paginaAtual;
		}
		public void setTotalPagina(String totalPagina)
		{
			this.totalPagina = totalPagina;
		}

		public String getTotalPagina()
		{
			if(this.totalPagina == null){
				this.totalPagina = ConstantesCRM.SVAZIO;
			}
			return this.totalPagina;
		}
		public void setDddLinha(String dddLinha)
		{
			this.dddLinha = dddLinha;
		}

		public String getDddLinha()
		{
			return this.dddLinha;
		}
	}


	public void setIPhoneFormBean(IPhoneFormBean formParam){

		this.iPhoneFormBean = formParam;
	}

	public IPhoneFormBean getIPhoneFormBean(){

		if(this.iPhoneFormBean == null){
			this.iPhoneFormBean = new IPhoneFormBean();
		}

		return this.iPhoneFormBean;
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
		String DATE_FORMAT   = "yyyyMMddHHmm";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c1          = Calendar.getInstance();
		return sdf.format(c1.getTime());
	}

}
