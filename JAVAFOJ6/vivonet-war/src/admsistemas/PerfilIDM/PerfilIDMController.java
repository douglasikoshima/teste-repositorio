package admsistemas.PerfilIDM;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO;
import br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO.ListasVO.Lista.Selecionado;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class PerfilIDMController extends AbstractAction {

	private static final long serialVersionUID = -7787229793607108474L;

	@EJB
	public br.com.vivo.fo.ctrls.admsistemas.perfilIDM.PerfilIDM perfilIDMFacade;

	protected global.Global globalApp = new global.Global();

	private PerfilIdmForm perfilIdmForm = null;

	private transient Logger log = new Logger("admsistemas");

	private String user = ConstantesCRM.SVAZIO;

	public PerfilIdmForm getPerfilIdmForm(){
		if(this.perfilIdmForm==null){
			this.perfilIdmForm = new PerfilIdmForm();
		}
		return this.perfilIdmForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("relGrupo".equals(mapping.getParameter())) {
			return relGrupo(mapping, form, request, response);
		} else if ("gravarGrupo".equals(mapping.getParameter())) {
			return gravarGrupo(mapping, form, request, response);
		} else if ("relPerfil".equals(mapping.getParameter())) {
			return relPerfil(mapping, form, request, response);
		} else if ("gravarPerfil".equals(mapping.getParameter())) {
			return gravarPerfil(mapping, form, request, response);
		} else if ("relItemMenu".equals(mapping.getParameter())) {
			return relItemMenu(mapping, form, request, response);
		} else if ("gravarItem".equals(mapping.getParameter())) {
			return gravarItem(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
		try{
			PerfilIdmForm form = (PerfilIdmForm) formParam;

			user = ConstantesCRM.getCurrentUser(request.getSession());

			IDMPerfilVO xml = IDMPerfilVO.Factory.newInstance();
			xml.addNewFiltro().addNewCombos().addNmSelect("PerfilIDM");

			IDMPerfilVO listas = perfilIDMFacade.getDadosPerfilIDM(user, xml);

			getPerfilIdmForm().setIsFirst("T");
			getPerfilIdmForm().setAction(ConstantesCRM.SVAZIO);
			getPerfilIdmForm().setIdPerfil(ConstantesCRM.SVAZIO);
			getPerfilIdmForm().setNmPerfil(ConstantesCRM.SVAZIO);
			getPerfilIdmForm().setArrDisponiveis(new String[0]);
			getPerfilIdmForm().setArrSelecionados(new String[0]);
			getPerfilIdmForm().setLstPerfisIdm(listas.getListasVO().getListaArray(0).getDisponivel());

			getPerfilIdmForm().setCodError(form.getCodError());
			getPerfilIdmForm().setMsgError(form.getMsgError());

			getPerfilIdmForm().setLstDisponiveis(Disponivel.Factory.newInstance());
			getPerfilIdmForm().setLstSelecionados(Selecionado.Factory.newInstance());

		}catch(Exception e){
			log.error("PerfilIDMController::begin", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relaciona.jsp"
	 */
	public ActionForward relGrupo(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
		try{
			PerfilIdmForm form = (PerfilIdmForm) formParam;

			getPerfilIdmForm().setAction("gravarGrupo.do");
			getPerfilIdmForm().setIdPerfil(form.getIdPerfil());
			getPerfilIdmForm().setNmPerfil(form.getNmPerfil());

			getPerfilIdmForm().setArrDisponiveis(new String[0]);
			getPerfilIdmForm().setArrSelecionados(new String[0]);

			user = ConstantesCRM.getCurrentUser(request.getSession());

			IDMPerfilVO xml = IDMPerfilVO.Factory.newInstance();
			if(form.getIdPerfil()!=null && (!ConstantesCRM.SVAZIO.equals(form.getIdPerfil()) || !ConstantesCRM.SZERO.equals(form.getIdPerfil()))){
				xml.setIdPerfil(form.getIdPerfil());
			}
			xml.addNewFiltro().addNewCombos().addNmSelect("Grupo");

			IDMPerfilVO listas = perfilIDMFacade.getDadosPerfilIDM(user, xml);

			getPerfilIdmForm().setLstDispGrupo(listas.getListasVO().getListaArray(0).getDisponivel());
			getPerfilIdmForm().setLstSeleGrupo(listas.getListasVO().getListaArray(0).getSelecionado());

			xml = IDMPerfilVO.Factory.newInstance();
			if(form.getIdPerfil()!=null && (!ConstantesCRM.SVAZIO.equals(form.getIdPerfil()) || !ConstantesCRM.SZERO.equals(form.getIdPerfil()))){
				xml.setIdPerfil(form.getIdPerfil());
			}
			xml.addNewFiltro().addNewCombos().addNmSelect("Operadora");

			listas = perfilIDMFacade.getDadosPerfilIDM(user, xml);

			getPerfilIdmForm().setLstDisponiveis(listas.getListasVO().getListaArray(0).getDisponivel());
			getPerfilIdmForm().setLstSelecionados(listas.getListasVO().getListaArray(0).getSelecionado());

		}catch(Exception e){
			log.error("PerfilIDMController::relGrupo", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	public ActionForward gravarGrupo(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {
		try{
			PerfilIdmForm form = (PerfilIdmForm) formParam;

			user = ConstantesCRM.getCurrentUser(request.getSession());

			Selecionado selGrupo = Selecionado.Factory.newInstance();
			for(int i=0;i<form.getArrSeleGrupos().length;i++){
				selGrupo.addNewIt().setId(form.getArrSeleGrupos()[i]);
			}

			Selecionado selOpera = Selecionado.Factory.newInstance();
			for(int i=0;i<form.getArrSelecionados().length;i++){
				selOpera.addNewIt().setId(form.getArrSelecionados()[i]);
			}

			IDMPerfilVO xml = IDMPerfilVO.Factory.newInstance();
			xml.setIdPerfil(form.getIdPerfil());
			xml.setNmPerfil(form.getNmPerfil());
			xml.addNewListasVO().addNewLista().setNmSelect("Grupo");
			xml.getListasVO().getListaArray(0).setSelecionado(selGrupo);
			xml.getListasVO().addNewLista().setNmSelect("Operadora");
			xml.getListasVO().getListaArray(1).setSelecionado(selOpera);

			IDMPerfilVO retorno = perfilIDMFacade.setDadosPerfilIDM(user, xml);
			if(retorno!=null && retorno.getCodError()!=null && !ConstantesCRM.SZERO.equals(retorno.getCodError())){
				form.setCodError(retorno.getCodError());
				form.setMsgError(retorno.getMsgError());
			}
			form.setIdPerfil(retorno.getIdPerfil());
			getPerfilIdmForm().setIdPerfil(retorno.getIdPerfil());

		}catch(Exception e){
			log.error("PerfilIDMController::gravarGrupo", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relaciona1.jsp"
	 */
	public ActionForward relPerfil(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
		try{
			PerfilIdmForm form = (PerfilIdmForm) formParam;

			getPerfilIdmForm().setNmTela("Relacionar Perfil");
			getPerfilIdmForm().setAction("gravarPerfil.do");
			getPerfilIdmForm().setIdPerfil(form.getIdPerfil());
			getPerfilIdmForm().setNmPerfil(form.getNmPerfil());
			getPerfilIdmForm().setArrDisponiveis(new String[0]);
			getPerfilIdmForm().setArrSelecionados(new String[0]);

			user = ConstantesCRM.getCurrentUser(request.getSession());

			IDMPerfilVO xml = IDMPerfilVO.Factory.newInstance();
			if(form.getIdPerfil()!=null && (!ConstantesCRM.SVAZIO.equals(form.getIdPerfil()) || !ConstantesCRM.SZERO.equals(form.getIdPerfil()))){
				xml.setIdPerfil(form.getIdPerfil());
			}
			xml.addNewFiltro().addNewCombos().addNmSelect("Perfil");

			IDMPerfilVO listas = perfilIDMFacade.getDadosPerfilIDM(user, xml);

			if(listas.getListasVO().getListaArray().length>0){
				getPerfilIdmForm().setLstDisponiveis(listas.getListasVO().getListaArray(0).getDisponivel());
				getPerfilIdmForm().setLstSelecionados(listas.getListasVO().getListaArray(0).getSelecionado());
			}else{
				getPerfilIdmForm().setLstDisponiveis(Disponivel.Factory.newInstance());
				getPerfilIdmForm().setLstSelecionados(Selecionado.Factory.newInstance());
			}

		}catch(Exception e){
			log.error("PerfilIDMController::relPerfil", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	public ActionForward gravarPerfil(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
		try{
			PerfilIdmForm form = (PerfilIdmForm) formParam;

			user = ConstantesCRM.getCurrentUser(request.getSession());

			Selecionado selPerfil = Selecionado.Factory.newInstance();
			for(int i=0;i<form.getArrSelecionados().length;i++){
				selPerfil.addNewIt().setId(form.getArrSelecionados()[i]);
			}

			IDMPerfilVO xml = IDMPerfilVO.Factory.newInstance();
			xml.setIdPerfil(form.getIdPerfil());
			xml.setNmPerfil(form.getNmPerfil());
			xml.addNewListasVO().addNewLista().setNmSelect("Perfil");
			xml.getListasVO().getListaArray(0).setSelecionado(selPerfil);

			IDMPerfilVO retorno = perfilIDMFacade.setDadosPerfilIDM(user, xml);
			if(retorno!=null && retorno.getCodError()!=null && !ConstantesCRM.SZERO.equals(retorno.getCodError())){
				form.setCodError(retorno.getCodError());
				form.setMsgError(retorno.getMsgError());
			}
			form.setIdPerfil(retorno.getIdPerfil());
			getPerfilIdmForm().setIdPerfil(retorno.getIdPerfil());

		}catch(Exception e){
			log.error("PerfilIDMController::gravarPerfil", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relaciona1.jsp"
	 */
	public ActionForward relItemMenu(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
		try{
			PerfilIdmForm form = (PerfilIdmForm) formParam;

			getPerfilIdmForm().setNmTela("Relacionar Item de Menu");
			getPerfilIdmForm().setAction("gravarItem.do");
			getPerfilIdmForm().setIdPerfil(form.getIdPerfil());
			getPerfilIdmForm().setNmPerfil(form.getNmPerfil());
			getPerfilIdmForm().setArrDisponiveis(new String[0]);
			getPerfilIdmForm().setArrSelecionados(new String[0]);

			user = ConstantesCRM.getCurrentUser(request.getSession());

			IDMPerfilVO xml = IDMPerfilVO.Factory.newInstance();
			if(form.getIdPerfil()!=null && (!ConstantesCRM.SVAZIO.equals(form.getIdPerfil()) || !ConstantesCRM.SZERO.equals(form.getIdPerfil()))){
				xml.setIdPerfil(form.getIdPerfil());
			}
			xml.addNewFiltro().addNewCombos().addNmSelect("ItemMenu");

			IDMPerfilVO listas = perfilIDMFacade.getDadosPerfilIDM(user, xml);

			if(listas.getListasVO().getListaArray().length>0){
				getPerfilIdmForm().setLstDisponiveis(listas.getListasVO().getListaArray(0).getDisponivel());
				getPerfilIdmForm().setLstSelecionados(listas.getListasVO().getListaArray(0).getSelecionado());
			}else{
				getPerfilIdmForm().setLstDisponiveis(Disponivel.Factory.newInstance());
				getPerfilIdmForm().setLstSelecionados(Selecionado.Factory.newInstance());
			}

		}catch(Exception e){
			log.error("PerfilIDMController::relItemMenu", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	public ActionForward gravarItem(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
		try{
			PerfilIdmForm form = (PerfilIdmForm) formParam;

			user = ConstantesCRM.getCurrentUser(request.getSession());

			Selecionado selItem = Selecionado.Factory.newInstance();
			for(int i=0;i<form.getArrSelecionados().length;i++){
				selItem.addNewIt().setId(form.getArrSelecionados()[i]);
			}

			IDMPerfilVO xml = IDMPerfilVO.Factory.newInstance();
			xml.setIdPerfil(form.getIdPerfil());
			xml.setNmPerfil(form.getNmPerfil());
			xml.addNewListasVO().addNewLista().setNmSelect("ItemMenu");
			xml.getListasVO().getListaArray(0).setSelecionado(selItem);

			IDMPerfilVO retorno = perfilIDMFacade.setDadosPerfilIDM(user, xml);
			if(retorno!=null && retorno.getCodError()!=null && !ConstantesCRM.SZERO.equals(retorno.getCodError())){
				form.setCodError(retorno.getCodError());
				form.setMsgError(retorno.getMsgError());
			}
			form.setIdPerfil(retorno.getIdPerfil());
			getPerfilIdmForm().setIdPerfil(retorno.getIdPerfil());

		}catch(Exception e){
			log.error("PerfilIDMController::gravarItem", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class PerfilIdmForm extends ActionForm {

		private static final long serialVersionUID = -6016624864087357768L;

		private String codError;
		private String msgError = ConstantesCRM.SVAZIO;
		private String isFirst  = ConstantesCRM.SVAZIO;
		private String nmTela   = ConstantesCRM.SVAZIO;

		private String action   = ConstantesCRM.SVAZIO;
		private String idPerfil = ConstantesCRM.SVAZIO;
		private String nmPerfil = ConstantesCRM.SVAZIO;
		private String[] arrSelecionados;
		private String[] arrSeleGrupos;
		private String[] arrDisponiveis;

		private Disponivel  lstPerfisIdm;

		private Disponivel  lstDispGrupo;
		private Selecionado lstSeleGrupo;

		private Disponivel  lstDisponiveis;
		private Selecionado lstSelecionados;

		public Disponivel getLstPerfisIdm(){
			return this.lstPerfisIdm;
		}

		public void setLstPerfisIdm(Disponivel lstPerfisIdm){
			this.lstPerfisIdm = lstPerfisIdm;
		}

		public Disponivel getLstDispGrupo(){
			return this.lstDispGrupo;
		}

		public void setLstDispGrupo(Disponivel lstDispGrupo){
			this.lstDispGrupo = lstDispGrupo;
		}

		public Selecionado getLstSeleGrupo(){
			return this.lstSeleGrupo;
		}

		public void setLstSeleGrupo(Selecionado lstSeleGrupo){
			this.lstSeleGrupo = lstSeleGrupo;
		}

		public Disponivel getLstDisponiveis(){
			return this.lstDisponiveis;
		}

		public void setLstDisponiveis(Disponivel lstDisponiveis){
			this.lstDisponiveis = lstDisponiveis;
		}

		public Selecionado getLstSelecionados(){
			return this.lstSelecionados;
		}

		public void setLstSelecionados(Selecionado lstSelecionados){
			this.lstSelecionados = lstSelecionados;
		}

		public void setAction(String action){
			this.action = action;
		}

		public String getAction(){
			return this.action;
		}

		public void setArrDisponiveis(String[] arrDisponiveis){
			this.arrDisponiveis = arrDisponiveis;
		}

		public String[] getArrDisponiveis(){
			if(this.arrDisponiveis == null){
				this.arrDisponiveis = new String[0];
			}
			return this.arrDisponiveis;
		}

		public void setArrSelecionados(String[] arrSelecionados){
			this.arrSelecionados = arrSelecionados;
		}

		public String[] getArrSelecionados(){
			if(this.arrSelecionados == null){
				this.arrSelecionados = new String[0];
			}
			return this.arrSelecionados;
		}

		public void setArrSeleGrupos(String[] arrSeleGrupos){
			this.arrSeleGrupos = arrSeleGrupos;
		}

		public String[] getArrSeleGrupos(){
			if(this.arrSeleGrupos == null){
				this.arrSeleGrupos = new String[0];
			}
			return this.arrSeleGrupos;
		}

		public void setNmPerfil(String nmPerfil){
			this.nmPerfil = nmPerfil;
		}

		public String getNmPerfil(){
			return this.nmPerfil;
		}

		public void setIdPerfil(String idPerfil){
			this.idPerfil = idPerfil;
		}

		public String getIdPerfil(){
			return this.idPerfil;
		}

		public void setNmTela(String nmTela){
			this.nmTela = nmTela;
		}

		public String getNmTela(){
			return this.nmTela;
		}

		public void setIsFirst(String isFirst){
			this.isFirst = isFirst;
		}

		public String getIsFirst(){
			return this.isFirst;
		}

		public void setMsgError(String msgError){
			this.msgError = msgError;
		}

		public String getMsgError(){
			return this.msgError;
		}

		public void setCodError(String codError){
			this.codError = codError;
		}

		public String getCodError(){
			return this.codError;
		}
	}
}
