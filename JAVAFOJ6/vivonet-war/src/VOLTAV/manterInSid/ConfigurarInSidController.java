package VOLTAV.manterInSid;

import java.util.TreeMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.manterInSid.ConfigurarInSidFacade;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.voltav.vo.ListaFuncionalidadeInSidVODocument.ListaFuncionalidadeInSidVO;
import br.com.vivo.fo.voltav.vo.ListaPesquisaInSidVODocument.ListaPesquisaInSidVO;
import br.com.vivo.fo.voltav.vo.VOLTAVListaRegionalVODocument.VOLTAVListaRegionalVO;

import com.indracompany.actions.AbstractAction;

public class ConfigurarInSidController extends AbstractAction {

	private static final long serialVersionUID = -6189138205311757894L;

	protected global.Global globalApp = new global.Global();

	private ListaFuncionalidadeInSidVO comboListaInSid;

	private VOLTAVListaRegionalVO comboRegional;
	private ListaFuncionalidadeInSidVO comboFuncionalidade;

	private static Logger log = new Logger("prepago");

	public VOLTAVListaRegionalVO getComboRegional() {
		return this.comboRegional;
	}

	public ListaFuncionalidadeInSidVO getComboFuncionalidade() {
		return this.comboFuncionalidade;
	}

	@EJB
	private ConfigurarInSidFacade configurarInSidFacade;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("limpar".equals(mapping.getParameter())) {
			return limpar(mapping, form, request, response);
		} else if ("pesquisarInSid".equals(mapping.getParameter())) {
			return pesquisarInSid(mapping, form, request, response);
		} else if ("alterarSid".equals(mapping.getParameter())) {
			return alterarSid(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ConfigurarInSidForm form = (ConfigurarInSidForm) formParam;
		limpar(mapping, form, request, response);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward limpar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ConfigurarInSidForm form = (ConfigurarInSidForm) formParam;
		form.setFuncionalidade(null);
		form.setIdOperadora(null);
		form.setIdCanal(null);
		carregarComboOperadora(form, request);
		carregarComboFuncionalidade(form, request);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward pesquisarInSid(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		ConfigurarInSidForm form = (ConfigurarInSidForm) formParam;
		try {
			carregarComboOperadora(form, request);
			carregarComboFuncionalidade(form, request);
			ListaPesquisaInSidVO lista = configurarInSidFacade.pesquisarInSid(user, form.getIdCanal(), form.getIdOperadora(), form.getIdFuncionalidade());
			request.setAttribute("listaInSid", lista);
		} catch (FacadeException e) {
			log.error("carregarComboOperadora:(" + user + ") - [" + e.getMessage() + "]", e);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void carregarComboOperadora(ConfigurarInSidForm form, HttpServletRequest request) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			this.comboRegional = configurarInSidFacade.obterRegional(user);
		} catch (FacadeException e) {
			log.error("carregarComboOperadora:(" + user + ") - [" + e.getMessage() + "]", e);
		}
	}

	private void carregarComboFuncionalidade(ConfigurarInSidForm form, HttpServletRequest request) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			this.comboFuncionalidade = configurarInSidFacade.obterFuncionalidades(user);
		} catch (FacadeException e) {
			log.error("carregarComboOperadora:(" + user + ") - [" + e.getMessage() + "]", e);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="erro" path="index.jsp"
	 */
	protected ActionForward alterarSid(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		ConfigurarInSidForm form = (ConfigurarInSidForm) formParam;
		try {
			String[] selecionados = request.getParameterValues("selecionados");
			configurarInSidFacade.alterarSid(user, selecionados, form.getNrSid());

			request.setAttribute("msgError", "Alterações realizadas com sucesso.");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (FacadeException e) {
			log.error("ConfigurarInSidController:alterarSid(" + user + ")", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}
	}

	public static class ConfigurarInSidForm extends ActionForm {

		private static final long serialVersionUID = 234808096776122668L;

		private String nrSid;
		private String idFuncionalidade;
		private String idOperadora;
		private String idCanal;
		private String idGrupoOperadora;
		private String strFuncionalidade;
		private TreeMap operadoras;

		public void setIdCanal(String idCanal) {
			this.idCanal = idCanal;
		}

		public String getIdCanal() {
			return this.idCanal;
		}

		public void setFuncionalidade(String strFuncionalidade) {
			this.strFuncionalidade = strFuncionalidade;
		}

		public String getFuncionalidade() {
			return this.strFuncionalidade;
		}

		public void setIdOperadora(String idOperadora) {
			this.idOperadora = idOperadora;
		}

		public String getIdOperadora() {
			return this.idOperadora;
		}

		public void setIdFuncionalidade(String idFuncionalidade) {
			this.idFuncionalidade = idFuncionalidade;
		}

		public String getIdFuncionalidade() {
			return this.idFuncionalidade;
		}

		public void setNrSid(String nrSid) {
			this.nrSid = nrSid;
		}

		public String getNrSid() {
			return this.nrSid;
		}
	}

}