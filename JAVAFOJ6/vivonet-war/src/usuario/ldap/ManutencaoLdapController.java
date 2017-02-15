package usuario.ldap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.LDAPVODocument.LDAPVO;
import br.com.vivo.fo.admsistemas.vo.ListaUsuarioLDAPVODocument.ListaUsuarioLDAPVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.ldap.LDAPUtil;

import com.indracompany.actions.AbstractAction;

public class ManutencaoLdapController extends AbstractAction {

	private static final long serialVersionUID = 7518565963524358285L;
	private FormLdap formLdap = new FormLdap();

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoLDAP.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (formLdap == null)
			formLdap = new FormLdap();
		formLdap.setArrayUsuarioLdap("-1");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoLDAP.jsp"
	 */
	public ActionForward pesquisaUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FormLdap form = (FormLdap) formParam;
		if (formLdap == null)
			formLdap = new FormLdap();
		try {
			ListaUsuarioLDAPVO usrLdapContainerVO = ListaUsuarioLDAPVO.Factory.newInstance();
			formLdap.setMsgErro(ConstantesCRM.SVAZIO);
			LDAPUtil ldap = new LDAPUtil(request);
			if (form.getFiltro().equalsIgnoreCase("login")) {
				usrLdapContainerVO = ldap.getListaUsuariosByLogin(form.getCampoPesquisa());
				formLdap.setLdapContainer(usrLdapContainerVO);
				if (formLdap.getLdapContainer().getLDAPVOArray().length > 0) {
					formLdap.setUsrUsuarioLdapVO(formLdap.getLdapContainer().getLDAPVOArray());
					formLdap.setArrayUsuarioLdap(ConstantesCRM.SONE);
				} else {
					formLdap.setArrayUsuarioLdap(ConstantesCRM.SZERO);
				}
			} else {
				usrLdapContainerVO = ldap.getListaUsuariosByName(form.getCampoPesquisa());
				formLdap.setLdapContainer(usrLdapContainerVO);
				if (formLdap.getLdapContainer().getLDAPVOArray().length > 0) {
					formLdap.setUsrUsuarioLdapVO(formLdap.getLdapContainer().getLDAPVOArray());
					formLdap.setArrayUsuarioLdap(ConstantesCRM.SONE);
				} else {
					formLdap.setArrayUsuarioLdap(ConstantesCRM.SZERO);
				}
			}
		} catch (Exception ex) {
			formLdap.setMsgErro("Falha na comunicação com host.[" + ex.getMessage() + "]");
			formLdap.setArrayUsuarioLdap("0");
			request.setAttribute("msgErro", "Falha na comunicação com host.[" + ex.getMessage() + "]");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoLDAP.jsp"
	 */
	public ActionForward bloqueiaUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormLdap form = (FormLdap) formParam;
		if (formLdap == null)
			formLdap = new FormLdap();
		formLdap.setMsgErro(ConstantesCRM.SVAZIO);
		try {
			LDAPUtil ldap = new LDAPUtil(request);
			ldap.modificaAtributo(form.getUid(), "nsaccountlock", "true");
			ldap.modificaAtributo(form.getUid(), "passwordRetryCount", ConstantesCRM.SZERO);
		} catch (Exception ex) {
			formLdap.setMsgErro("Falha na comunicação com host. [" + ex.getMessage() + "]");
			request.setAttribute("msgErro", "Falha na comunicação com host.[" + ex.getMessage() + "]");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoLDAP.jsp"
	 */
	public ActionForward desbloqueiaUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormLdap form = (FormLdap) formParam;
		if (formLdap == null)
			formLdap = new FormLdap();
		formLdap.setMsgErro(ConstantesCRM.SVAZIO);
		try {
			LDAPUtil ldap = new LDAPUtil(request);
			ldap.modificaAtributo(form.getUid(), "nsaccountlock", "false");
			ldap.modificaAtributo(form.getUid(), "passwordRetryCount", ConstantesCRM.SZERO);
		} catch (Exception ex) {
			formLdap.setMsgErro("Falha na comunicação com host.[" + ex.getMessage() + "]");
			request.setAttribute("msgErro", "Falha na comunicação com host.[" + ex.getMessage() + "]");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoLDAP.jsp"
	 */
	public ActionForward reinicializaSenha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormLdap form = (FormLdap) formParam;
		if (formLdap == null)
			formLdap = new FormLdap();
		formLdap.setMsgErro(ConstantesCRM.SVAZIO);
		LDAPUtil ldap = null;
		try {
			ldap = new LDAPUtil(request);
			ldap.modificaAtributo(form.getUid(), "userPassword", ConstantesCRM.PASSWORD_RESET);
			try {
				ldap.modificaAtributo(form.getUid(), "title", "true");
				ldap.modificaAtributo(form.getUid(), "passwordRetryCount", ConstantesCRM.SZERO);
			} catch (Exception e) {
				ldap.addAtributo(form.getUid(), "title", "true");
			}
		} catch (Exception ex) {
			formLdap.setMsgErro("Falha na comunicação com host.[" + ex.getMessage() + "]");
			request.setAttribute("msgErro", "Falha na comunicação com host.[" + ex.getMessage() + "]");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	protected void leArquivoConfigLdap() throws Exception {
		/*
		 * Properties p = new Properties(); try{ p.load(new
		 * FileInputStream(request.getRealPath("/resources/ini/ldap.ini"))); Ldap_Host = p.getProperty("LDAPHost");
		 * Ldap_Port = Integer.parseInt(p.getProperty("LDAPPort")); Ldap_CN = p.getProperty("LDAPCN"); Ldap_Password =
		 * p.getProperty("LDAPPassword"); Ldap_DN = p.getProperty("LDAPDn"); LdapDefaultPassword =
		 * p.getProperty("LDAPDefaultPassword"); Ldap_Algoritmo = p.getProperty("LDAPDAlgoritmo") ; }catch(Exception
		 * ex){ throw new Exception(ex); }
		 */
	}

	public static class FormLdap extends ActionForm {

		private static final long serialVersionUID = 8215940317558377540L;

		private String msgErro;
		private String uid;
		private String filtro;
		private String arrayUsuarioLdap;
		private LDAPVO[] usrUsuarioLdapVO;
		private ListaUsuarioLDAPVO ldapContainer;
		private String campoPesquisa;

		public FormLdap() {
			arrayUsuarioLdap = ConstantesCRM.SZERO;
		}

		public void setCampoPesquisa(String campoPesquisa) {
			this.campoPesquisa = campoPesquisa;
		}

		public String getCampoPesquisa() {
			return this.campoPesquisa;
		}

		public void setLdapContainer(ListaUsuarioLDAPVO ldapContainer) {
			this.ldapContainer = ldapContainer;
		}

		public ListaUsuarioLDAPVO getLdapContainer() {
			return this.ldapContainer;
		}

		public void setUsrUsuarioLdapVO(LDAPVO[] usrUsuarioLdapVO) {
			this.usrUsuarioLdapVO = usrUsuarioLdapVO;
		}

		public LDAPVO[] getUsrUsuarioLdapVO() {
			return this.usrUsuarioLdapVO;
		}

		public void setArrayUsuarioLdap(String arrayUsuarioLdap) {
			this.arrayUsuarioLdap = arrayUsuarioLdap;
		}

		public String getArrayUsuarioLdap() {
			return this.arrayUsuarioLdap;
		}

		public void setFiltro(String filtro) {
			this.filtro = filtro;
		}

		public String getFiltro() {
			return this.filtro;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getUid() {
			return this.uid;
		}

		public void setMsgErro(String msgErro) {
			this.msgErro = msgErro;
		}

		public String getMsgErro() {
			return this.msgErro;
		}
	}

	public FormLdap getFormLdap() {
		return this.formLdap;
	}
}
