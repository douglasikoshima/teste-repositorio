package VOLTAV.manterMenu;

import java.net.InetAddress;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.manterMenu.ManterMenuFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.voltav.vo.VOLTAVManterMenuVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVManterMenuVODocument.VOLTAVManterMenuVO;
import br.com.vivo.fo.voltav.vo.VOLTAVManterMenuVODocument.VOLTAVManterMenuVO.ResultadoMenuVO;

import com.indracompany.actions.AbstractAction;

public class ManterMenuController extends AbstractAction{

	@EJB
	private ManterMenuFacade controlManterMenu;

	protected global.Global globalApp = new global.Global();

	private static NonCatalogLogger log = new NonCatalogLogger("Controller");

	public static final String LISTAR        = "0";
	public static final String FILTRAR       = "1";
	public static final String PESQUISAR     = "2";

	FormManterMenu formManterMenu = null;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("beginManterMenu".equals(mapping.getParameter())) {
			return beginManterMenu(mapping, form, request, response);
		} else if ("filtrarPesquisa".equals(mapping.getParameter())) {
			return filtrarPesquisa(mapping, form, request, response);
		} else if ("alteraStatusItemMenu".equals(mapping.getParameter())) {
			return alteraStatusItemMenu(mapping, form, request, response);
		} else if ("paginarPesquisa".equals(mapping.getParameter())) {
			return paginarPesquisa(mapping, form, request, response);
		} else if ("filtraItemMenu".equals(mapping.getParameter())) {
			return filtraItemMenu(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configManterMenu.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){

		log.debug("ManterMenuController:begin();");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaManterMenu.jsp"
	 */
	protected ActionForward beginManterMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception{

		log.debug("ManterMenuController:beginManterMenu(); started");

		//VOLTAVManterMenuVO tavManterMenu = VOLTAVManterMenuVO.Factory.newInstance();

		String xmlIn = "<operacao>consultarFiltros</operacao>";

		/* Chamada serviço para carga de combos necessários. */
		VOLTAVManterMenuVO tavManterMenuFiltros = controlManterMenu.getManterMenu(xmlIn, ConstantesCRM.getCurrentUser(request.getSession()));

		getFormManterMenu().setMenuComboVO(tavManterMenuFiltros.getMenuListaFiltrosVO());

		log.debug("ManterMenuController:beginManterMenu(); end");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserListaMenu.jsp"
	 */
	protected ActionForward filtrarPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception{
		FormManterMenu form = (FormManterMenu)formParam;
		log.debug("ManterMenuController:filtrarPesquisa(); started");

		StringBuffer xmlIn = new StringBuffer();

		// Tipo operação.
		xmlIn.append("<operacao>consultarMenu</operacao>");

		//Página Atual.
		getFormManterMenu().setPaginaAtual(ConstantesCRM.SONE);

		/* Se CANAL foi selecionado, carrega id para consulta */
		if(form.getIdCanalAssocArray() != null && form.getIdCanalAssocArray().length > 0){
			String arrayCanal[] = form.getIdCanalAssocArray();
			for(int i = 0; i < arrayCanal.length;i++){
				xmlIn.append("<idCanal>").append(arrayCanal[i]).append("</idCanal>");
			}
		}

		/* Se GRUPO foi selecionado, carrega id para consulta */
		if(form.getIdGrupoAssocArray() != null && form.getIdGrupoAssocArray().length > 0){
			String arrayGrupo[] = form.getIdGrupoAssocArray();
			for(int i = 0; i < arrayGrupo.length; i++){
				xmlIn.append("<idGrupo>").append(arrayGrupo[i]).append("</idGrupo>");
			}
		}


		/* Se ITEMMENU foi selecionado, carrega id para consulta */
		if(form.getIdItemMenuAssocArray() != null && form.getIdItemMenuAssocArray().length > 0){
			String arrayItem[] = form.getIdItemMenuAssocArray();
			for(int i = 0; i < arrayItem.length ; i++)
			{
				xmlIn.append("<idItemMenu>").append(arrayItem[i]).append("</idItemMenu>");
			}
		}

		/* Se UF foi selecionado, carrega id para consulta */
		if(form.getIdUFAssocArray() != null && form.getIdUFAssocArray().length > 0){
			String arrayUf[] = form.getIdUFAssocArray();
			for(int i = 0; i < arrayUf.length; i++){
				xmlIn.append("<idUF>").append(arrayUf[i]).append("</idUF>");
			}
		}

		/* Se TIPOLINHA foi selecionado, carrega id para consulta */
		if(form.getIdTipoLinhaAssocArray() != null && form.getIdTipoLinhaAssocArray().length > 0){
			String arrayTipoLinha[] = form.getIdTipoLinhaAssocArray();
			for(int i =0; i < arrayTipoLinha.length ; i++)
			{
				xmlIn.append("<idTipoLinha>").append(arrayTipoLinha[i]).append("</idTipoLinha>");
			}
		}

		getFormManterMenu().setStrFiltros(xmlIn.toString());

		xmlIn.append("<nrPagina>1</nrPagina>");

		/* Parametros necessarios para LOG no serviço */
		//tavManterMenu.addNewManterMenuLogVO();

		//tavManterMenu.getManterMenuLogVO().setIpUsuario(request.getRemoteHost());
		//tavManterMenu.getManterMenuLogVO().setLogin((String)request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN));

		VOLTAVManterMenuVO tavManterMenuResultado = controlManterMenu.getManterMenu(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));
		getFormManterMenu().setTotalPaginas(tavManterMenuResultado.getTotalPaginas());

		getFormManterMenu().setResultadoPesquisaVO(tavManterMenuResultado.getResultadoMenuVOArray());

		log.debug("ManterMenuController:resultadoPesquisa(); end");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserListaMenu.jsp"
	 */
	protected ActionForward alteraStatusItemMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception{
		FormManterMenu form = (FormManterMenu)formParam;
		log.debug("ManterMenuController:alteraStatusItemMenu(); started");
		boolean  erro = false;

		StringBuffer xmlIn = new StringBuffer();

		xmlIn.append("<operacao>alterarMenu</operacao>");

		xmlIn.append("<idUf>").append(form.getIdUf()).append("</idUf>");
		xmlIn.append("<idCanal>").append(form.getIdCanal()).append("</idCanal>");
		xmlIn.append("<idTipoLinha>").append(form.getIdTipoLinha()).append("</idTipoLinha>");
		xmlIn.append("<idGrupo>").append(form.getIdGrupo()).append("</idGrupo>");
		xmlIn.append("<idItemMenu>").append(form.getIdItemMenu()).append("</idItemMenu>");
		xmlIn.append("<inAtivo>").append(form.getInAtivo()).append("</inAtivo>");

		ResultadoMenuVO[] resultados =  this.getFormManterMenu().getResultadoPesquisaVO();
		ResultadoMenuVO resultado = resultados[Integer.parseInt(form.getIdx())];

		xmlIn.append("<nmItemMenu>/");

		xmlIn.append(resultado.getNmItemPai());

		if(resultado.getNmItem() != null && !ConstantesCRM.SVAZIO.equals(resultado.getNmItem()))
		{
			xmlIn.append("/"+resultado.getNmItem());
		}

		xmlIn.append("</nmItemMenu>");

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


		// Parâmetros necessários para LOG na alteração.
		xmlIn.append("<login>").append(ConstantesCRM.getCurrentUser(request.getSession())).append("</login>");
		xmlIn.append("<ipUsuario>").append(IP).append("</ipUsuario>");

		try{

			VOLTAVManterMenuVO tavManterMenuResultado = controlManterMenu.getManterMenu(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

		}catch(TuxedoWarningException twe)
		{
			erro = true;
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());

		}catch(Exception e)
		{
			erro = true;
			request.setAttribute("msgStatus", "Não foi possivel alterar o item selecionado. Tente novamente.");
		}

		StringBuffer xmlINPesquisa = new StringBuffer();
		xmlINPesquisa.append(getFormManterMenu().getStrFiltros());
		xmlINPesquisa.append("<nrPagina>").append(form.getPaginaAtual()).append("</nrPagina>");

		VOLTAVManterMenuVO tavManterMenuResultado = controlManterMenu.getManterMenu(xmlINPesquisa.toString(), ConstantesCRM.getCurrentUser(request.getSession()));
		getFormManterMenu().setTotalPaginas(tavManterMenuResultado.getTotalPaginas());

		getFormManterMenu().setResultadoPesquisaVO(tavManterMenuResultado.getResultadoMenuVOArray());


		if(!erro) {
			request.setAttribute("msgStatus", "Alteração realizada com sucesso.");
		}


		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserListaMenu.jsp"
	 */
	protected ActionForward paginarPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		FormManterMenu form = (FormManterMenu)formParam;
		log.debug("ManterMenuController:paginarPesquisa(); started");

		StringBuffer strFiltros = new StringBuffer(getFormManterMenu().getStrFiltros());
		strFiltros.append("<nrPagina>").append(form.getPaginaAtual()).append("</nrPagina>");

		VOLTAVManterMenuVO tavManterMenuResultado = controlManterMenu.getManterMenu(strFiltros.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

		getFormManterMenu().setResultadoPesquisaVO(tavManterMenuResultado.getResultadoMenuVOArray());

		getFormManterMenu().setPaginaAtual(form.getPaginaAtual());

		log.debug("ManterMenuController:paginarPesquisa(); end");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserItemMenu.jsp"
	 */
	protected ActionForward filtraItemMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		FormManterMenu form = (FormManterMenu)formParam;
		log.debug("ManterMenuController:filtraItemMenu(); started");
		StringBuffer xmlIn = new StringBuffer();

		xmlIn.append("<operacao>consultarFiltros</operacao>");

		/* Se CANAL foi selecionado, carrega id para consulta */
		if(form.getIdCanalAssocArray() != null && form.getIdCanalAssocArray().length > 0){
			String arrayCanal[] = form.getIdCanalAssocArray();
			for(int i = 0; i < arrayCanal.length;i++){
				xmlIn.append("<idCanal>").append(arrayCanal[i]).append("</idCanal>");
			}
		}

		/* Se TIPOLINHA foi selecionado, carrega id para consulta */
		if(form.getIdTipoLinhaAssocArray() != null && form.getIdTipoLinhaAssocArray().length > 0){
			String arrayTipoLinha[] = form.getIdTipoLinhaAssocArray();
			for(int i =0; i < arrayTipoLinha.length ; i++)
			{
				xmlIn.append("<idTipoLinha>").append(arrayTipoLinha[i]).append("</idTipoLinha>");
			}
		}


		/* Chamada serviço para carga do combo Item Menu. */
		VOLTAVManterMenuVO tavManterMenuFiltros = controlManterMenu.getManterMenu(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

		getFormManterMenu().setMenuComboVO(tavManterMenuFiltros.getMenuListaFiltrosVO());

		log.debug("ManterMenuController:filtraItemMenu(); end");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	public static class FormManterMenu extends ActionForm{
		private String idx;

		private String idItemMenu;

		private String inAtivo;

		private String idGrupo;

		private String idTipoLinha;

		private String idCanal;

		private String idUf;

		private String paginaAtual;

		private String totalPaginas;

		private String strFiltros;

		private VOLTAVManterMenuVODocument.VOLTAVManterMenuVO.ResultadoMenuVO[] resultadoPesquisaVO;


		private VOLTAVManterMenuVODocument.VOLTAVManterMenuVO.MenuListaFiltrosVO menuComboVO;

		private String[] idItemMenuAssocArray;

		private String[] idItemMenuExistArray;

		private String[] idCanalAssocArray;

		private String[] idCanalExistArray;

		private String[] idUFAssocArray;

		private String[] idUFExistArray;

		private String[] idTipoLinhaAssocArray;

		private String[] idTipoLinhaExistArray;

		private String[] idGrupoAssocArray;

		private String[] idGrupoExistArray;

		public void setIdGrupoExistArray(String[] idGrupoExistArray){
			this.idGrupoExistArray = idGrupoExistArray;
		}

		public String[] getIdGrupoExistArray(){
			if(this.idGrupoExistArray == null){
				this.idGrupoExistArray = new String[0];
			}
			return this.idGrupoExistArray;
		}

		public void setIdGrupoAssocArray(String[] idGrupoAssocArray){
			this.idGrupoAssocArray = idGrupoAssocArray;
		}

		public String[] getIdGrupoAssocArray(){
			if(this.idGrupoAssocArray == null){
				this.idGrupoAssocArray = new String[0];
			}
			return this.idGrupoAssocArray;
		}

		public void setIdTipoLinhaExistArray(String[] idTipoLinhaExistArray){
			this.idTipoLinhaExistArray = idTipoLinhaExistArray;
		}

		public String[] getIdTipoLinhaExistArray(){
			if(this.idTipoLinhaExistArray == null){
				this.idTipoLinhaExistArray = new String[0];
			}

			return this.idTipoLinhaExistArray;
		}

		public void setIdTipoLinhaAssocArray(String[] idTipoLinhaAssocArray){
			this.idTipoLinhaAssocArray = idTipoLinhaAssocArray;
		}

		public String[] getIdTipoLinhaAssocArray(){
			if(this.idTipoLinhaAssocArray == null)
			{
				this.idTipoLinhaAssocArray = new String[0];
			}

			return this.idTipoLinhaAssocArray;
		}

		public void setIdUFExistArray(String[] idUFExistArray)
		{
			this.idUFExistArray = idUFExistArray;
		}

		public String[] getIdUFExistArray(){
			if(this.idUFExistArray == null){
				this.idUFExistArray = new String[0];
			}
			return this.idUFExistArray;
		}

		public void setIdUFAssocArray(String[] idUFAssocArray){
			this.idUFAssocArray = idUFAssocArray;
		}

		public String[] getIdUFAssocArray(){
			if(this.idUFAssocArray == null){
				this.idUFAssocArray = new String[0];
			}
			return this.idUFAssocArray;
		}

		public void setIdCanalExistArray(String[] idCanalExistArray)
		{
			this.idCanalExistArray = idCanalExistArray;
		}

		public String[] getIdCanalExistArray(){
			if(this.idCanalExistArray == null){
				this.idCanalExistArray = new String[0];
			}
			return this.idCanalExistArray;
		}

		public void setIdCanalAssocArray(String[] idCanalAssocArray){
			this.idCanalAssocArray = idCanalAssocArray;
		}

		public String[] getIdCanalAssocArray(){
			if(this.idCanalAssocArray == null){
				this.idCanalAssocArray = new String[0];
			}
			return this.idCanalAssocArray;
		}

		public void setIdItemMenuExistArray(String[] idItemMenuExistArray)
		{
			this.idItemMenuExistArray = idItemMenuExistArray;
		}

		public String[] getIdItemMenuExistArray(){
			if(this.idItemMenuExistArray == null){
				this.idItemMenuExistArray = new String[0];
			}

			return this.idItemMenuExistArray;
		}

		public void setIdItemMenuAssocArray(String[] idItemMenuAssocArray)
		{
			this.idItemMenuAssocArray = idItemMenuAssocArray;
		}

		public String[] getIdItemMenuAssocArray(){
			if(this.idItemMenuAssocArray == null){
				this.idItemMenuAssocArray = new String[0];
			}

			return this.idItemMenuAssocArray;
		}

		public void setMenuComboVO(VOLTAVManterMenuVODocument.VOLTAVManterMenuVO.MenuListaFiltrosVO menuComboVO){
			this.menuComboVO = menuComboVO;
		}

		public VOLTAVManterMenuVODocument.VOLTAVManterMenuVO.MenuListaFiltrosVO getMenuComboVO(){
			// if(this.menuComboVO == null){
			//      this.menuComboVO = TAVManterMenuVO.Factory.newInstance().addNewManterMenuListaVO();
			//  }

			return this.menuComboVO;
		}

		public void setResultadoPesquisaVO(VOLTAVManterMenuVODocument.VOLTAVManterMenuVO.ResultadoMenuVO[] resultadoPesquisaVO)
		{
			this.resultadoPesquisaVO = resultadoPesquisaVO;
		}

		public VOLTAVManterMenuVODocument.VOLTAVManterMenuVO.ResultadoMenuVO[] getResultadoPesquisaVO(){
			//if(this.resultadoPesquisaVO == null){
			//    this.resultadoPesquisaVO = new ManterMenuResultadoVO.Factory.newInstance();
			//}

			return this.resultadoPesquisaVO;
		}

		public void setStrFiltros(String strFiltros){
			this.strFiltros = strFiltros;
		}

		public String getStrFiltros(){
			if(this.strFiltros == null){
				this.strFiltros = ConstantesCRM.SVAZIO;
			}
			return this.strFiltros;
		}

		public void setTotalPaginas(String totalPaginas){
			this.totalPaginas = totalPaginas;
		}

		public String getTotalPaginas(){
			if(this.totalPaginas == null){
				this.totalPaginas = ConstantesCRM.SVAZIO;
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

		public void setIdUf(String idUf){
			this.idUf = idUf;
		}

		public String getIdUf(){
			if(this.idUf == null){
				this.idUf = ConstantesCRM.SVAZIO;
			}
			return this.idUf;
		}

		public void setIdCanal(String idCanal){
			this.idCanal = idCanal;
		}

		public String getIdCanal(){
			if(this.idCanal == null){
				this.idCanal = ConstantesCRM.SVAZIO;
			}
			return this.idCanal;
		}

		public void setIdTipoLinha(String idTipoLinha){
			this.idTipoLinha = idTipoLinha;
		}

		public String getIdTipoLinha(){
			if(this.idTipoLinha == null){
				this.idTipoLinha = ConstantesCRM.SVAZIO;
			}
			return this.idTipoLinha;
		}

		public void setIdGrupo(String idGrupo){
			this.idGrupo = idGrupo;
		}

		public String getIdGrupo(){
			if(this.idGrupo == null){
				this.idGrupo = ConstantesCRM.SVAZIO;
			}
			return this.idGrupo;
		}

		public void setInAtivo(String inAtivo){
			this.inAtivo = inAtivo;
		}

		public String getInAtivo(){
			if(this.inAtivo ==  null){
				this.inAtivo = "";
			}
			return this.inAtivo;
		}

		public void setIdItemMenu(String idItemMenu){
			this.idItemMenu = idItemMenu;
		}

		public String getIdItemMenu(){
			if(this.idItemMenu == null){
				this.idItemMenu = ConstantesCRM.SVAZIO;
			}
			return this.idItemMenu;
		}

		public void setIdx(String idx)
		{
			this.idx = idx;
		}

		public String getIdx()
		{
			if(this.idx == null){
				this.idx = ConstantesCRM.SVAZIO;
			}

			return this.idx;
		}
	}

	public void setFormManterMenu(FormManterMenu form){
		this.formManterMenu = form;
	}

	public FormManterMenu getFormManterMenu(){
		if(this.formManterMenu == null){
			this.formManterMenu = new FormManterMenu();
		}

		return this.formManterMenu;
	}

}
