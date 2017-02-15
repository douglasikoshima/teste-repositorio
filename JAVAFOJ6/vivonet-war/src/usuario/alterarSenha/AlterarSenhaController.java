package usuario.alterarSenha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netscape.ldap.LDAPException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.acesso.senha.Senha;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.ldap.LDAPUtil;

import com.indracompany.actions.AbstractAction;

public class AlterarSenhaController extends AbstractAction {

	private static final long serialVersionUID = -2806741659862358509L;

	private AlterarSenhaForm alterarSenhaForm = new AlterarSenhaForm();

	private transient Logger log = new Logger("usuario");

	public static final String MSG_SUCESSO          = "Senha Alterada com Sucesso. Volte a realizar o login.";
	public static final String MSG_SENHA_INVALIDA   = "Senha Atual informada inválida, tente novamente.";
	public static final String MSG_LOGIN_INVALIDA   = "Login de Usuário informado não existe.";
	public static final String MSG_ERRO_LDAP_ADM    = "Não foi possível realizar a alteração da Senha, consulte o Administrador de LDAP.";
	public static final String MSG_ALTERACAO_ULTIMA = "Esta senha já foi usada anteriormente, tente outra novamente.";
	public static final String MSG_ERRO_SENHA_FRACA = "Senha inválida.\\nNão é permitido compor a senha utilizando o nome, login ou parte deles para compor sua senha, bem como numeros em sequência.";
	public static final String MSG_STATUS           = "msgStatus";

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("alterarSenha".equals(mapping.getParameter())) {
			return alterarSenha(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alteracaoSenha.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AlterarSenhaForm form = (AlterarSenhaForm) formParam;

		log.debug("AlterarSenhaController:begin"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		// Instancia FormBean.
		alterarSenhaForm = new AlterarSenhaForm();

		//Limpa Msg.
		alterarSenhaForm.setMsgStatus(ConstantesCRM.SVAZIO);
		
		//Limpa Form.
		alterarSenhaForm.setSenhaNova(ConstantesCRM.SVAZIO);
		alterarSenhaForm.setSenhaNovaConfirma(ConstantesCRM.SVAZIO);

		if(form.getInMenu() != null) {
			alterarSenhaForm.setInMenu(form.getInMenu());
		}

		if(form.getInVoltar() != null) {
			alterarSenhaForm.setInVoltar(form.getInVoltar());
		}

		//Seta Login.
		if(form.getLogin() != null && !form.getLogin().equals(ConstantesCRM.SVAZIO)) {
			alterarSenhaForm.setLogin(form.getLogin());
		} else {
			alterarSenhaForm.setLogin((String)request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN));
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alteracaoSenha.jsp"
	 * @jpf:forward name="erro" path="alteracaoSenha.jsp"
	 */
	public ActionForward alterarSenha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AlterarSenhaController:alterarSenha"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		AlterarSenhaForm form = (AlterarSenhaForm) formParam;
		
		Senha checaSenha = new Senha();
		String usuario=ConstantesCRM.SVAZIO;
		
		alterarSenhaForm.setMsgStatus(ConstantesCRM.SVAZIO);
		if(request.getSession().getAttribute(ConstantesCRM.USUARIO_NOME)!=null){
			usuario=(String)request.getSession().getAttribute(ConstantesCRM.USUARIO_NOME);
		}
		try{
			LDAPUtil ldap = new LDAPUtil(request);
			if(!checaSenha.verificaLogin(form.getSenhaNova().toLowerCase(),usuario.toLowerCase())){
				if(!checaSenha.verificaLogin(form.getSenhaNova().toLowerCase(),alterarSenhaForm.getLogin().toLowerCase())){
					if(!checaSenha.verificaAscIgual(form.getSenhaNova().toLowerCase())){
						if(!checaSenha.verificaSeqCresDecresAscii(form.getSenhaNova().toLowerCase())){
							try{
								log.debug("AlterarSenhaController:alterarSenha"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
								// Modifica a senha do usuário no LDAP.
								ldap.modificaPropriaSenha(alterarSenhaForm.getLogin() , form.getSenhaAntiga(), form.getSenhaNova());
								try{
									// Modifica Atributo title do Ldap, utizado como "flag" para a troca da senha no primeiro login
									ldap.modificaAtributo(alterarSenhaForm.getLogin(), "title", "false");
								}catch(Exception e){
									log.error("AlterarSenhaController:alterarSenha"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
								}
								// MSG SUCESSO.
								request.setAttribute("msgStatus",MSG_SUCESSO);
								alterarSenhaForm.setIsSucesso("true");
							}catch(LDAPException ldape){
								switch (ldape.getLDAPResultCode()){
								case LDAPException.NO_SUCH_OBJECT:
									log.debug("AlterarSenhaController:alterarSenha"+"( "+ request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)+ " ) => LOGIN INVALIDA.");
									request.setAttribute(MSG_STATUS, MSG_LOGIN_INVALIDA);
									break;

								case LDAPException.INVALID_CREDENTIALS:
									log.debug("AlterarSenhaController:alterarSenha"+"( "+ request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)+ " ) => SENHA INVALIDA.");
									request.setAttribute(MSG_STATUS, MSG_SENHA_INVALIDA);
									break;

								case LDAPException.CONSTRAINT_VIOLATION:
									log.debug("AlterarSenhaController:alterarSenha"+"( "+ request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)+ " ) => Esta senha já foi usada anteriormente.");
									request.setAttribute(MSG_STATUS, MSG_ALTERACAO_ULTIMA);
									break;

								default:
									log.error("AlterarSenhaController:alterarSenha"+"( "+ request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)+ " ) => Erro Generico -> cod. = "+ldape.getLDAPResultCode(), ldape);
									request.setAttribute(MSG_STATUS, MSG_ERRO_LDAP_ADM);
									break;
								}
							}catch(Exception e){
								log.error("AlterarSenhaController:alterarSenha"+"( "+ request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)+ " )", e);
								alterarSenhaForm.setMsgStatus(MSG_ERRO_LDAP_ADM);
							}
						}else{
							request.setAttribute("msgStatus",MSG_ERRO_SENHA_FRACA);
						}
					}else{
						request.setAttribute("msgStatus",MSG_ERRO_SENHA_FRACA);
					}
				}else{
					request.setAttribute("msgStatus",MSG_ERRO_SENHA_FRACA);
				}
			}else{
				request.setAttribute("msgStatus",MSG_ERRO_SENHA_FRACA);
			}
		}catch(Exception excs){
			log.error("AlterarSenhaController:alterarSenha"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )", excs);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AlterarSenhaForm extends ActionForm {

		private static final long serialVersionUID = -2800706615978880600L;

		private String isSucesso;
		private String inVoltar;
		private String inMenu;
		private String senhaAntiga;
		private String msgStatus;
		private String senhaNovaConfirma;
		private String senhaNova;
		private String login;
		private String idUser;

		public void setIdUser(String idUser){
			this.idUser = idUser;
		}

		public String getIdUser(){
			if(this.idUser == null) {
				this.idUser = ConstantesCRM.SVAZIO;
			}

			return this.idUser;
		}

		public void setLogin(String login){
			this.login = login;
		}

		public String getLogin(){
			if(this.login == null) {
				this.login = ConstantesCRM.SVAZIO;
			}
			return this.login;
		}

		public void setSenhaNova(String senhaNova){
			this.senhaNova = senhaNova;
		}

		public String getSenhaNova(){
			if(this.senhaNova == null) {
				this.senhaNova = ConstantesCRM.SVAZIO;
			}
			return this.senhaNova;
		}

		public void setSenhaNovaConfirma(String senhaNovaConfirma){
			this.senhaNovaConfirma = senhaNovaConfirma;
		}

		public String getSenhaNovaConfirma(){
			if(this.senhaNovaConfirma == null) {
				this.senhaNovaConfirma = ConstantesCRM.SVAZIO;
			}
			return this.senhaNovaConfirma;
		}

		public void setMsgStatus(String msgStatus){
			this.msgStatus = msgStatus;
		}

		public String getMsgStatus(){
			if(this.msgStatus == null) {
				this.msgStatus = ConstantesCRM.SVAZIO;
			}
			return this.msgStatus;
		}

		public void setSenhaAntiga(String senhaAntiga){
			this.senhaAntiga = senhaAntiga;
		}

		public String getSenhaAntiga(){
			if(this.senhaAntiga == null) {
				this.senhaAntiga = ConstantesCRM.SVAZIO;
			}
			return this.senhaAntiga;
		}

		public void setInMenu(String inMenu){
			this.inMenu = inMenu;
		}

		public String getInMenu(){
			if(this.inMenu == null) {
				this.inMenu = ConstantesCRM.SVAZIO;
			}
			return this.inMenu;
		}

		public void setInVoltar(String inVoltar){
			this.inVoltar = inVoltar;
		}

		public String getInVoltar(){
			if(this.inVoltar == null) {
				this.inVoltar = ConstantesCRM.SVAZIO;
			}
			return this.inVoltar;
		}

		public void setIsSucesso(String isSucesso){
			this.isSucesso = isSucesso;
		}

		public String getIsSucesso(){
			if(this.isSucesso == null) {
				this.isSucesso = ConstantesCRM.SVAZIO;
			}
			return this.isSucesso;
		}
	}

	public AlterarSenhaForm getAlterarSenhaForm(){
		return this.alterarSenhaForm;
	}

	public void setAlterarSenhaForm(AlterarSenhaForm form){
		this.alterarSenhaForm = form;
	}
}
