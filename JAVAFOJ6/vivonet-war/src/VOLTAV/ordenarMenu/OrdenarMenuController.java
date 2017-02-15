package VOLTAV.ordenarMenu;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.ordenarMenu.OrdenarMenuFacade;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ItemMenuVODocument.ItemMenuVO;
import br.com.vivo.fo.voltav.vo.ListaMenuVODocument.ListaMenuVO;

import com.indracompany.actions.AbstractAction;


public class OrdenarMenuController extends AbstractAction {

	private static final long serialVersionUID = 3259085265109858019L;

	protected global.Global globalApp = new global.Global();

	private static Logger log = new Logger("prepago");

	@EJB
	private OrdenarMenuFacade ordenarMenuFacade;

	private ListaMenuVO comboSubSistema;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("gravar".equals(mapping.getParameter())) {
			return gravar(mapping, form, request, response);
		} else if ("pesquisarMenu".equals(mapping.getParameter())) {
			return pesquisarMenu(mapping, form, request, response);	
		} else if ("selecionarSubSistema".equals(mapping.getParameter())) {
			return selecionarSubSistema(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		PesquisarMenuForm form = (PesquisarMenuForm)formParam;
		limpar(form);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Obtem os itens para montagem do combo de sub sistemas
	 */
	public ListaMenuVO getComboSubSistema() {
		return this.comboSubSistema;
	}
	public void setComboSubSistema(ListaMenuVO value) {
		this.comboSubSistema = value;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward pesquisarMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		PesquisarMenuForm form = (PesquisarMenuForm) formParam;
		try {
			ListaMenuVO listaMenus = ordenarMenuFacade.pesquisarMenu(user, form.getIdCanal(), form.getIdSubSistema());
			request.setAttribute("listaMenus", listaMenus);
		} catch (FacadeException e) {
			log.error("OrdenarMenuController:pesquisarMenu(" + user + ")", e);
			request.setAttribute("msgError", e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected void limpar(PesquisarMenuForm form) {
		form.setIdCanal(ConstantesCRM.SZERO);
		setComboSubSistema(ListaMenuVO.Factory.newInstance());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward gravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		PesquisarMenuForm form = (PesquisarMenuForm)formParam;
		try {
			ListaMenuVO listaMenu = ListaMenuVO.Factory.newInstance();
			String[] itens = request.getParameterValues("idItemMenu");
			String[] seqs = request.getParameterValues("sqSequencia");
			for(int i=0; i<itens.length; i++) {
				ItemMenuVO vo = listaMenu.addNewItemMenuVO();
				vo.setIdItemMenu(itens[i]);
				vo.setSqSequencia(seqs[i]);
			}
			ordenarMenuFacade.gravar(user, listaMenu);

			return pesquisarMenu(mapping, formParam, request, response);

		} catch (FacadeException e) {
			log.error("OrdenarMenuController:gravar(" + user + ")", e);
			request.setAttribute("msgError", e.getMessage());
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward selecionarSubSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		PesquisarMenuForm form = (PesquisarMenuForm)formParam;
		try {
			setComboSubSistema(ordenarMenuFacade.obterComboSubSistema(user, form.getIdCanal()));
			form.setIdSubSistema(ConstantesCRM.SZERO);
		} catch (FacadeException e) {
			log.error("OrdenarMenuController:selecionarSubSistema(" + user + ")", e);
			request.setAttribute("msgError", e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class PesquisarMenuForm extends ActionForm {

		private String idItemMenu;
		private String idSubSistema;
		private String idCanal;

		public void setIdCanal(String idCanal)
		{
			this.idCanal = idCanal;
		}

		public String getIdCanal()
		{
			return this.idCanal;
		}

		public void setIdSubSistema(String idSubSistema)
		{
			this.idSubSistema = idSubSistema;
		}

		public String getIdSubSistema()
		{
			return this.idSubSistema;
		}

		public void setIdItemMenu(String idItemMenu)
		{
			this.idItemMenu = idItemMenu;
		}

		public String getIdItemMenu()
		{
			return this.idItemMenu;
		}
	}

}
