package VOLTAV.manterBanner;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import br.com.vivo.fo.campanha.vo.CampanhaVODocument.CampanhaVO;
import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.commons.utils.properties.LoadProperties;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.campanhas.CampanhasFacade;
import br.com.vivo.fo.ctrls.VOLTAV.manterBanner.ManterBannerFacade;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO;
import br.com.vivo.tav.banner.VOLUploadBannerVODocument.VOLUploadBannerVO;

import com.indracompany.actions.AbstractAction;

public class ManterBannerController extends AbstractAction {

	private static final long serialVersionUID = 7661198442659280910L;

	@EJB
	private ManterBannerFacade controlManterBanner;

	@EJB
	private CampanhasFacade campanhasFacade;

	protected global.Global globalApp = new global.Global();

	FormUploadBanner formUploadBanner = null;

	private static  transient Logger log = new Logger("vol");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluirBanner".equals(mapping.getParameter())) {
			return incluirBanner(mapping, form, request, response);
		} else if ("carregaAssociarBanner".equals(mapping.getParameter())) {
			return carregaAssociarBanner(mapping, form, request, response);
		} else if ("carregaIncluirBanner".equals(mapping.getParameter())) {
			return carregaIncluirBanner(mapping, form, request, response);
		} else if ("associarBanner".equals(mapping.getParameter())) {
			return associarBanner(mapping, form, request, response);
		} else if ("desassociarBanner".equals(mapping.getParameter())) {
			return desassociarBanner(mapping, form, request, response);
		} else if ("carregaImagemBanner".equals(mapping.getParameter())) {
			return carregaImagemBanner(mapping, form, request, response);
		} else if ("pesquisarBanner".equals(mapping.getParameter())) {
			return pesquisarBanner(mapping, form, request, response);
		} else if ("paginarPesquisa".equals(mapping.getParameter())) {
			return paginarPesquisa(mapping, form, request, response);
		} else if ("getListaCampanhas".equals(mapping.getParameter())) {
			return getListaCampanhas(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterBanner.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception {

		String xmlIn = ConstantesCRM.SVAZIO;

		VOLTAVManterBannerVO voltavManterBannerVO = controlManterBanner.getParametroBuscaBanner(xmlIn , ConstantesCRM.getCurrentUser(request.getSession()));

		getFormUploadBanner().setComboFiltro(voltavManterBannerVO.getBannerListaVO());
		
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}


	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterBanner.jsp"
	 */
	protected ActionForward incluirBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception{

		FormUploadBanner form = (FormUploadBanner)formParam;

		LoadProperties properties = new LoadProperties(request);

		InputStream stream = form.getFileBanner().getInputStream();

		StringBuffer strNome = null;

		String strNomeAtual = form.getFileBanner().getFileName();

		Random ran = new Random();

		int size = form.getFileBanner().getFileSize();

		if(size == 0)
		{
			request.setAttribute("msgStatus","Arquivo selecionado inválido, tamanho igual a zero.");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		// set Novo Nome arquivo
		GregorianCalendar calendario = new GregorianCalendar();
		strNome = new StringBuffer();
		strNome.append("A").append(form.getIdArea()).append("_")
		.append(calendario.get(GregorianCalendar.YEAR))
		.append(calendario.get(GregorianCalendar.MONTH))
		.append(calendario.get(GregorianCalendar.DAY_OF_MONTH))
		.append(calendario.get(GregorianCalendar.MINUTE))
		.append(calendario.get(GregorianCalendar.MILLISECOND))
		.append(String.valueOf(ran.nextInt(99999999)))
		.append(strNomeAtual.substring(strNomeAtual.length()-4 , strNomeAtual.length()));

		// Imagem permitida até 1MB;
		if ((form.getFileBanner().getFileSize() < (50000))){

			OutputStream bos = new FileOutputStream(properties.get(ConstantesCRM.PARAM_PATH_FILE_FO) + strNome);

			byte[] buffer = new byte[size];
			int bytesRead = 0;
			while ((bytesRead = stream.read(buffer, 0, size)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.close();
			stream.close();

		}else
		{
			request.setAttribute("msgStatus","Arquivo selecionado inválido, tamanho superior a 250KB.");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		//INICIO GET IP
		String IP = request.getHeader("X-Forwarded-For");

		if(IP == null){
			IP = request.getRemoteAddr();
			log.debug("IP from RemoteAddr = " + IP);
		}else{
			log.debug("HEADER_IP from X-Forwarded-For = " + IP);
		}

		InetAddress ipAddr;

		ipAddr = InetAddress.getByName(IP);
		IP = ipAddr.getHostAddress();
		//FIM GET IP

		//StringBuffer xmlIn = new StringBuffer();

		VOLUploadBannerVO upload = VOLUploadBannerVO.Factory.newInstance();

		upload.setIdAreaBanner(form.getIdArea());
		upload.setDsBanner(form.getDsBanner());
		upload.setIp(IP);
		upload.setLogin(ConstantesCRM.getCurrentUser(request.getSession()));
		upload.setNmBanner(strNome.toString());
		upload.setOperacao(ConstantesCRM.STHREE);

		/*xmlIn.append("<idAreaBanner>").append(form.getIdArea()).append("</idAreaBanner>");
        xmlIn.append("<dsBanner>").append(form.getDsBanner()).append("</dsBanner>");
        xmlIn.append("<ip>").append(IP).append("</ip>");
        xmlIn.append("<login>").append(ConstantesCRM.getCurrentUser(request.getSession())).append("</login>");
        xmlIn.append("<nmBanner>").append(strNome).append("</nmBanner>");
        xmlIn.append("<operacao>3</operacao>");*/

		if("on".equals(form.getInCampanha())){
			upload.setIdCampanha(form.getIdCampanha());
			upload.setIdTipoBanner(ConstantesCRM.STWO);
			//xmlIn.append("<idCampanha>").append(form.getIdCampanha()).append("</idCampanha>");
			//xmlIn.append("<idTipoBanner>2</idTipoBanner>");
		} else {
			upload.setUrlBanner(form.getNmLink());
			upload.setIdTipoBanner(form.getIdTipoImagem());
			//xmlIn.append("<urlBanner>").append(form.getNmLink()).append("</urlBanner>");
			//xmlIn.append("<idTipoBanner>").append(form.getIdTipoImagem()).append("</idTipoBanner>");
		}

		controlManterBanner.incluirBanner(upload.xmlText().replaceAll("ban:",""), ConstantesCRM.getCurrentUser(request.getSession()));

		request.setAttribute("msgStatus","Inclusão de banner efetuada com sucesso.\\nA exibição deste Banner no VOL e no Vivo Net poderá levar alguns minutos até ser efetivada.");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="associarBanner.jsp"
	 */
	protected ActionForward carregaAssociarBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		FormUploadBanner form = (FormUploadBanner)formParam;

		StringBuffer xmlIn = new StringBuffer();
		xmlIn.append("<idAreaBanner>").append(form.getIdArea()).append("</idAreaBanner>");

		VOLTAVManterBannerVO voltavManterBannerVO = controlManterBanner.getBanner(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

		getFormUploadBanner().setBannerVO(voltavManterBannerVO.getBannerVOArray());

		getFormUploadBanner().setIdGrupoBannerArray(form.getIdGrupoBannerArray());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirBanner.jsp"
	 */
	protected ActionForward carregaIncluirBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)
	{

		getFormUploadBanner().setLogin(ConstantesCRM.getCurrentUser(request.getSession()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserListaBanner.jsp"
	 */
	protected ActionForward associarBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception
	{

		FormUploadBanner form = (FormUploadBanner)formParam;

		StringBuffer xmlIn =  new StringBuffer();

		String[] arrayIdGrupoBanner = null;

		if(form.getIdGrupoBannerArray() != null){

			arrayIdGrupoBanner = form.getIdGrupoBannerArray().split(";");

			for(int i = 0; i < arrayIdGrupoBanner.length;i++){
				xmlIn.append("<GrupoBanner>");
				xmlIn.append("<idGrupoBanner>").append(arrayIdGrupoBanner[i]).append("</idGrupoBanner>");
				xmlIn.append("<idBanner>").append(form.getIdBanner()).append("</idBanner>");

				if(form.getNmLink() != null && !ConstantesCRM.SVAZIO.equals(form.getNmLink())){
					xmlIn.append("<nmLink>").append(form.getNmLink()).append("</nmLink>");
				}

				xmlIn.append("</GrupoBanner>");

			}

			//INICIO GET IP
			String IP = request.getHeader("X-Forwarded-For");

			if(IP == null){
				IP = request.getRemoteAddr();
				log.debug("IP from RemoteAddr = " + IP);
			}else{
				log.debug("HEADER_IP from X-Forwarded-For = " + IP);
			}

			InetAddress ipAddr;

			ipAddr = InetAddress.getByName(IP);
			IP = ipAddr.getHostAddress();
			//FIM GET IP


			// LOGIN e IP prar LOG.
			xmlIn.append("<ip>").append(IP).append("</ip>");
			xmlIn.append("<login>").append(ConstantesCRM.getCurrentUser(request.getSession())).append("</login>");

			// operaçao 1, de associação de banner
			xmlIn.append("<operacao>").append(ConstantesCRM.SONE).append("</operacao>");

			controlManterBanner.incluirBanner(xmlIn.toString(),ConstantesCRM.getCurrentUser(request.getSession()));

			StringBuffer strFiltros = new StringBuffer(getFormUploadBanner().getStrFiltro());
			strFiltros.append("<pagina>").append(form.getPaginaAtual()).append("</pagina>");

			VOLTAVManterBannerVO voltavManterBannerVO = controlManterBanner.pesquisarBanner(strFiltros.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

			getFormUploadBanner().setResultadoPesquisaBanner(voltavManterBannerVO.getBannerResultadoPesquisaVOArray());

			getFormUploadBanner().setPaginaAtual(form.getPaginaAtual());

			form.setIdGrupoBannerArray(null);

			request.setAttribute("msgStatus","Associação de banner efetuada com sucesso.");

		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}


	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserListaBanner.jsp"
	 */
	protected ActionForward desassociarBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		FormUploadBanner form = (FormUploadBanner)formParam;

		StringBuffer xmlIn =  new StringBuffer();

		String[] arrayIdGrupoBanner = null;

		if(form.getIdGrupoBannerArray() != null){

			arrayIdGrupoBanner = form.getIdGrupoBannerArray().split(";");

			for(int i = 0; i < arrayIdGrupoBanner.length;i++){
				xmlIn.append("<GrupoBanner>");
				xmlIn.append("<idGrupoBanner>").append(arrayIdGrupoBanner[i]).append("</idGrupoBanner>");
				xmlIn.append("</GrupoBanner>");

			}

			//INICIO GET IP
			String IP = request.getHeader("X-Forwarded-For");

			if(IP == null){
				IP = request.getRemoteAddr();
				log.debug("IP from RemoteAddr = " + IP);
			}else{
				log.debug("HEADER_IP from X-Forwarded-For = " + IP);
			}

			InetAddress ipAddr;

			ipAddr = InetAddress.getByName(IP);
			IP = ipAddr.getHostAddress();
			//FIM GET IP

			// LOGIN e IP prar LOG.
			xmlIn.append("<ip>").append(IP).append("</ip>");
			xmlIn.append("<login>").append(ConstantesCRM.getCurrentUser(request.getSession())).append("</login>");


			//Operamção de desassociação de banner
			xmlIn.append("<operacao>2</operacao>");

		}

		controlManterBanner.incluirBanner(xmlIn.toString(),ConstantesCRM.getCurrentUser(request.getSession()));


		StringBuffer strFiltros = new StringBuffer(getFormUploadBanner().getStrFiltro());
		strFiltros.append("<pagina>").append(form.getPaginaAtual()).append("</pagina>");

		VOLTAVManterBannerVO voltavManterBannerVO = controlManterBanner.pesquisarBanner(strFiltros.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

		getFormUploadBanner().setResultadoPesquisaBanner(voltavManterBannerVO.getBannerResultadoPesquisaVOArray());

		getFormUploadBanner().setPaginaAtual(form.getPaginaAtual());

		form.setIdGrupoBannerArray(null);

		request.setAttribute("msgStatus","Desassociação de banner efetuada com sucesso.");


		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}



	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="imagemBanner.jsp"
	 */
	protected ActionForward carregaImagemBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)
	{
		FormUploadBanner form = (FormUploadBanner)formParam;

		getFormUploadBanner().setIdArea(form.getIdArea());
		getFormUploadBanner().setDsTipoBanner(form.getDsTipoBanner());
		getFormUploadBanner().setNmImagem(form.getNmImagem());
		
		String bannerPath = ConstantesCRM.SVAZIO;
		try {
			LoadProperties properties = new LoadProperties(request);
			bannerPath = properties.get(ConstantesCRM.PARAM_PATH_FILE_FO);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("[ManterNBannerController.begin] Erro : ", e);
		}
		log.debug("[ManterNBannerController.begin] bannerPath: " +bannerPath);
		request.getSession().setAttribute("bannerPath", bannerPath);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserListaBanner.jsp"
	 */
	protected ActionForward pesquisarBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		FormUploadBanner form = (FormUploadBanner)formParam;

		StringBuffer xmlIn = new StringBuffer();

		/* Se UF foi selecionado, carrega id para consulta */
		if(form.getUfArray() != null && form.getUfArray().length > 0){
			String arrayUf[] = form.getUfArray();
			for(int i = 0; i < arrayUf.length; i++){
				xmlIn.append("<idUF>").append(arrayUf[i]).append("</idUF>");
			}
		}else{
			xmlIn.append("<idUF/>");
		}

		/* Se Area Banner foi selecionado, carrega id para consulta */
		if(form.getAreaBannerArray() != null && form.getAreaBannerArray().length > 0){
			String arrayAreaBanner[] = form.getAreaBannerArray();
			for(int i = 0; i < arrayAreaBanner.length; i++){
				xmlIn.append("<idArea>").append(arrayAreaBanner[i]).append("</idArea>");
			}
		}else{
			xmlIn.append("<idArea/>");
		}

		/* Se Tipo Linha foi selecionado, carrega id para consulta */
		if(form.getTipoLinhaArray() != null && form.getTipoLinhaArray().length > 0){
			String arrayTipoLinha[] = form.getTipoLinhaArray();
			for(int i = 0; i < arrayTipoLinha.length; i++){
				xmlIn.append("<idTipoLinha>").append(arrayTipoLinha[i]).append("</idTipoLinha>");
			}
		}else{
			xmlIn.append("<idTipoLinha/>");
		}

		/* Se Grupo Usuario foi selecionado, carrega id para consulta */
		if(form.getGrupoUsuarioArray() != null && form.getGrupoUsuarioArray().length > 0){
			String arrayGrupo[] = form.getGrupoUsuarioArray();
			for(int i = 0; i < arrayGrupo.length; i++){
				xmlIn.append("<idGrupo>").append(arrayGrupo[i]).append("</idGrupo>");
			}
		}else{
			xmlIn.append("<idGrupo/>");
		}

		xmlIn.append("<registros>50</registros>");

		getFormUploadBanner().setStrFiltro(xmlIn.toString());

		xmlIn.append("<pagina>0</pagina>");
		getFormUploadBanner().setPaginaAtual("0");

		VOLTAVManterBannerVO voltavManterBannerVO = controlManterBanner.pesquisarBanner(xmlIn.toString() , ConstantesCRM.getCurrentUser(request.getSession()));

		getFormUploadBanner().setResultadoPesquisaBanner(voltavManterBannerVO.getBannerResultadoPesquisaVOArray());
		getFormUploadBanner().setListaLength(String.valueOf(voltavManterBannerVO.getBannerResultadoPesquisaVOArray().length));

		getFormUploadBanner().setTotalPaginas(voltavManterBannerVO.getTotal());

		form.setIdGrupoBannerArray(null);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserListaBanner.jsp"
	 */
	protected ActionForward paginarPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response )throws Exception
	{
		FormUploadBanner form = (FormUploadBanner)formParam;

		StringBuffer strFiltros = new StringBuffer(getFormUploadBanner().getStrFiltro());
		strFiltros.append("<pagina>").append(form.getPaginaAtual()).append("</pagina>");

		VOLTAVManterBannerVO voltavManterBannerVO = controlManterBanner.pesquisarBanner(strFiltros.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

		getFormUploadBanner().setResultadoPesquisaBanner(voltavManterBannerVO.getBannerResultadoPesquisaVOArray());

		getFormUploadBanner().setPaginaAtual(form.getPaginaAtual());

		form.setIdGrupoBannerArray(null);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserListaBanner.jsp"
	 */
	protected ActionForward getListaCampanhas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception {

		StringBuffer xmlDados = new StringBuffer();
		String user           = ConstantesCRM.getCurrentUser(request.getSession());

		FormUploadBanner form = (FormUploadBanner)formParam;

		try {

			CampanhaVO campanhaVO = CampanhaVO.Factory.newInstance();
			campanhaVO            = campanhasFacade.getCampanhaVO(user, "CAMPFILTRO", campanhaVO);

			xmlDados.append(campanhaVO.xmlText().replaceAll("vo:",""));
			log.debug("ManterBannerController:getListaCampanhas(" + user + ") >> FINALIZADO");

		} catch(Exception e) {

			log.error("ManterBannerController:getListaCampanhas"+"( " + user + " )", e);

			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage("Houve um erro durante processamento da lista de Campanhas.");

			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} finally {
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			return null;
		}
	}

	public static class FormUploadBanner extends ActionForm{

		private String dsTipoBanner;
		private String nmImagem;
		private String idBanner;
		private String idGrupoBannerArray;
		private br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.BannerVO[] bannerVO;
		private String login;
		private String paginaAtual;
		private String totalPaginas;
		private br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.BannerResultadoPesquisaVO[] resultadoPesquisaBanner;
		private String strFiltro;
		private String listaLength;
		private br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.BannerListaVO comboFiltro;
		private String idTipoImagem;
		private String nmLink;
		private String idArea;
		private String dsBanner;
		private FormFile fileBanner;
		private String[] grupoUsuarioArray;
		private String[] tipoLinhaArray;
		private String[] ufArray;
		private String[] areaBannerArray;
		private FormFile imagemUpload;
		private String inCampanha;
		private String idCampanha;

		public void setAreaBannerArray(String[] areaBannerArray){
			this.areaBannerArray = areaBannerArray;
		}

		public String[] getAreaBannerArray(){
			if(this.areaBannerArray == null){
				this.areaBannerArray = new String[0];
			}

			return this.areaBannerArray;
		}

		public void setUfArray(String[] ufArray){
			this.ufArray = ufArray;
		}

		public String[] getUfArray(){
			if(this.ufArray == null){
				this.ufArray = new String[0];
			}

			return this.ufArray;
		}

		public void setTipoLinhaArray(String[] tipoLinhaArray){
			this.tipoLinhaArray = tipoLinhaArray;
		}

		public String[] getTipoLinhaArray(){
			if(this.tipoLinhaArray == null){
				this.tipoLinhaArray = new String[0];
			}
			return this.tipoLinhaArray;
		}

		public void setGrupoUsuarioArray(String[] grupoUsuarioArray){
			this.grupoUsuarioArray = grupoUsuarioArray;
		}

		public String[] getGrupoUsuarioArray(){
			if(this.grupoUsuarioArray == null){
				this.grupoUsuarioArray = new String[0];
			}
			return this.grupoUsuarioArray;
		}

		public void setFileBanner(FormFile fileBanner){
			this.fileBanner = fileBanner;
		}

		public FormFile getFileBanner(){
			return this.fileBanner;
		}

		public void setDsBanner(String dsBanner){
			this.dsBanner = dsBanner;
		}

		public String getDsBanner(){
			if(this.dsBanner == null){
				this.dsBanner = ConstantesCRM.SVAZIO;
			}
			return this.dsBanner;
		}

		public void setIdArea(String idArea){
			this.idArea = idArea;
		}

		public String getIdArea(){
			if(this.idArea == null){
				this.idArea = ConstantesCRM.SVAZIO;
			}
			return this.idArea;
		}

		public void setInCampanha(String inCampanha){
			this.inCampanha = inCampanha;
		}

		public String getInCampanha(){
			if(this.inCampanha == null){
				this.inCampanha = ConstantesCRM.SVAZIO;
			}
			return this.inCampanha;
		}

		public void setIdCampanha(String idCampanha){
			this.idCampanha = idCampanha;
		}

		public String getIdCampanha(){
			if(this.idCampanha == null){
				this.idCampanha = ConstantesCRM.SVAZIO;
			}
			return this.idCampanha;
		}

		public void setNmLink(String nmLink){
			this.nmLink = nmLink;
		}

		public String getNmLink(){
			if(this.nmLink == null){
				this.nmLink = ConstantesCRM.SVAZIO;
			}
			return this.nmLink;
		}

		public void setIdTipoImagem(String idTipoImagem){
			this.idTipoImagem = idTipoImagem;
		}

		public String getIdTipoImagem(){
			if(this.idTipoImagem == null){
				this.idTipoImagem = ConstantesCRM.SVAZIO;
			}
			return this.idTipoImagem;
		}

		public void setComboFiltro(br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.BannerListaVO comboFiltro)
		{
			this.comboFiltro = comboFiltro;
		}

		public br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.BannerListaVO getComboFiltro()
		{
			if(this.comboFiltro == null)
			{
				this.comboFiltro = br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.Factory.newInstance().addNewBannerListaVO();
			}

			return this.comboFiltro;
		}

		public void setListaLength(String listaLength){
			this.listaLength = listaLength;
		}

		public String getListaLength(){
			if(this.listaLength == null || this.listaLength.equals(ConstantesCRM.SVAZIO)){
				this.listaLength = ConstantesCRM.SZERO;
			}
			return this.listaLength;
		}

		public void setStrFiltro(String strFiltro){
			this.strFiltro = strFiltro;
		}

		public String getStrFiltro(){
			if(this.strFiltro == null){
				this.strFiltro = ConstantesCRM.SVAZIO;
			}
			return this.strFiltro;
		}

		public void setResultadoPesquisaBanner(br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.BannerResultadoPesquisaVO[] resultadoPesquisaBanner){
			this.resultadoPesquisaBanner = resultadoPesquisaBanner;
		}

		public br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.BannerResultadoPesquisaVO[] getResultadoPesquisaBanner(){
			if(this.resultadoPesquisaBanner == null){
				this.resultadoPesquisaBanner[0] = br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.Factory.newInstance().addNewBannerResultadoPesquisaVO();
			}

			return this.resultadoPesquisaBanner;
		}

		public void setTotalPaginas(String totalPaginas){
			this.totalPaginas = totalPaginas;
		}

		public String getTotalPaginas(){
			if(this.totalPaginas == null){
				this.totalPaginas = ConstantesCRM.SVAZIO;
			}

			if(!this.totalPaginas.equals(ConstantesCRM.SVAZIO)){
				int result = Integer.parseInt(totalPaginas) / 50;
				int resto = Integer.parseInt(totalPaginas) % 50;
				if(resto > 0 ) {
					result++;
				}
				return String.valueOf(result);
			}

			return this.totalPaginas;
		}

		public void setPaginaAtual(String paginaAtual){
			this.paginaAtual = paginaAtual;
		}

		public String getPaginaAtual(){
			if(this.paginaAtual == null){
				this.paginaAtual = ConstantesCRM.SVAZIO;
			}

			return this.paginaAtual;
		}

		public void setLogin(String login){
			this.login = login;
		}

		public String getLogin(){
			if(this.login == null){
				this.login = ConstantesCRM.SVAZIO;
			}
			return this.login;
		}

		public void setBannerVO(br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.BannerVO[] bannerVO){
			this.bannerVO = bannerVO;
		}

		public br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO.BannerVO[] getBannerVO(){
			if(this.bannerVO == null)
			{
				this.bannerVO[0] = br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.Factory.newInstance().addNewVOLTAVManterBannerVO().addNewBannerVO();
			}

			return this.bannerVO;
		}

		public void setIdGrupoBannerArray(String idGrupoBannerArray){
			this.idGrupoBannerArray = idGrupoBannerArray;
		}

		public String getIdGrupoBannerArray(){
			if(this.idGrupoBannerArray == null){
				this.idGrupoBannerArray = ConstantesCRM.SVAZIO;
			}
			return this.idGrupoBannerArray;
		}

		public void setIdBanner(String idBanner){
			this.idBanner = idBanner;
		}

		public String getIdBanner(){
			if(this.idBanner == null){
				this.idBanner = ConstantesCRM.SVAZIO;
			}
			return this.idBanner;
		}

		public void setNmImagem(String nmImagem){
			this.nmImagem = nmImagem;
		}

		public String getNmImagem(){
			if(this.nmImagem == null){
				this.nmImagem = ConstantesCRM.SVAZIO;
			}
			return this.nmImagem;
		}

		public void setDsTipoBanner(String dsTipoBanner){
			this.dsTipoBanner = dsTipoBanner;
		}

		public String getDsTipoBanner(){
			if(this.dsTipoBanner == null){
				this.dsTipoBanner = ConstantesCRM.SVAZIO;
			}
			return this.dsTipoBanner;
		}
	}

	public void setFormUploadBanner(FormUploadBanner form){
		this.formUploadBanner = form;
	}

	public FormUploadBanner getFormUploadBanner(){
		if(this.formUploadBanner == null){
			this.formUploadBanner = new FormUploadBanner();
		}
		return this.formUploadBanner;
	}
}