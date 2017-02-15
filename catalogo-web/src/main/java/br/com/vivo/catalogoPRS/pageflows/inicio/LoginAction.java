package br.com.vivo.catalogoPRS.pageflows.inicio;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import pt.ptinovacao.sca.SCAException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.exception.UsuarioInvalidoException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.LoginForm;
import br.com.vivo.catalogoPRS.services.BaseService;
import br.com.vivo.catalogoPRS.util.CookieUtil;
import br.com.vivo.catalogoPRS.util.SCAUtils;


public class LoginAction extends BaseMappingDispatchAction implements Serializable{
	private static final long serialVersionUID = 1L;
	private String SUCCESS = "success";


	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SCAUtils.liberaSessaoSCA(CookieUtil.getDados(request));
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward doLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LoginForm formulario = (LoginForm)form;
		MessageResources resources = getResources(request, "messages" );

		
		String message = "";
		String usuario = formulario.getUsuario();
		String senha = formulario.getSenha();
		formulario.setUsuario(null);
		formulario.setSenha(null);
		request.setAttribute("alterar_senha_username", usuario);
		
		if(usuario == null || usuario.trim().equals("")){
			if(senha == null || senha.trim().equals("")){
				message = "Usu&aacute;rio, Senha";
			}else{
				message = "Usu&aacute;rio";
			}
		}else if(senha == null || senha.trim().equals("")){
			message = "Senha";
		}
		
		if(!message.equals("")){			
			addActionErrorExpression("campoObrigatorio", resources.getMessage("erro.campo.necessario", message), new Object[]{message}, request);
			return mapping.findForward("login");
		}
			
		UserCatalogo userCatalogo;
		String clientIP = "";
		String clientHostname = "";
		
		java.net.InetAddress inetAddress  = null;

		try{
			if (request.getHeader("x-forwarded-for") != null) {
				inetAddress = java.net.InetAddress.getByName(request.getHeader("x-forwarded-for"));
	        }
			else{
				inetAddress = java.net.InetAddress.getByName(request.getRemoteAddr());
			}
		    clientIP = inetAddress.getHostAddress();
		    clientHostname = inetAddress.getHostName();
	      }catch (Exception e){}
		
		try {
			userCatalogo = SCAUtils.authenticate(usuario, senha, clientHostname , clientIP);
		} catch (LoginException e) {
			if (e instanceof SCAException) {
				SCAException ex = (SCAException)e;
				if(tratarSCAException(ex,request)){
					return mapping.findForward("loginNaoSucedido");
				}
			}
			addActionErrorExpression("usuarioInvalido", resources.getMessage("login.invalido"), new Object[]{}, request);
			request.setAttribute("usuarioInvalido","Usu&aacute;rio ou senha Inv&aacute;lidos. Tente novamente ou entre em contato com o coordenador de seguran&ccedil;a da informa&ccedil;&atilde;o.");
			return mapping.findForward("loginNaoSucedido");
		}
		catch (UsuarioInvalidoException e) {
			addActionErrorExpression("erroLogin", e.getMessage(), new Object[]{}, request);
			request.getSession().setAttribute("erroLogin", e.getMessage());
			request.setAttribute("erroLogin", Boolean.TRUE);
			return mapping.findForward("loginNaoSucedido");
		}
		if(userCatalogo != null){
			
			// Verifica se esta dentro do horario de utilizacao (caso usuario tenha essa configuracao)
			if (!SCAUtils.checkHorario(userCatalogo, request.getSession())){
				request.setAttribute("erroLogin", Boolean.TRUE);
				try{
					SCAUtils.logout(userCatalogo);
				}
				catch(LoginException le){};
				addActionErrorExpression("erroLogin", "Acesso negado, pois est&aacute; fora do hor&aacute;rio de utiliza&ccedil;&atilde;o permitido para o grupo associado.",  new Object[]{}, request);
				request.setAttribute("erroLogin", Boolean.TRUE);
				return mapping.findForward("loginNaoSucedido");
			}
			
			Long diasAteExpiracao = SCAUtils.checkExpiracaoSenha(userCatalogo);
			if( diasAteExpiracao != null){
				request.setAttribute("senhaQuaseExpirando", diasAteExpiracao);
			}
			request.getSession().setAttribute("logged_user", userCatalogo);
			try {
		        Date date = new Date(userCatalogo.getUser().getLoginStats("Catalogo").getLastLogin().getTime());
		        request.setAttribute("date", date);
		        
				request.setAttribute("loginStats", userCatalogo.getUser().getLoginStats("Catalogo"));
			} catch (SCAException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("loginSucedido", Boolean.TRUE);
        // Recoloca usuario/senha
		formulario.setUsuario(usuario);
		formulario.setSenha(senha);
		
		CookieUtil.gravar(response, userCatalogo);
		this.cleanHeader(response);
		return mapping.findForward("loginSucedido");
	}
	

    public void addActionErrorExpression( String propertyName, String expression, Object[] messageArgs, HttpServletRequest request )
    {	
        addActionErrorExpression( propertyName, expression, request, messageArgs );
    }


    public void addActionErrorExpression( String propertyName, String expression, HttpServletRequest request, Object[] messageArgs )
    {
    	ActionMessage msg = null ;
    	if (messageArgs.length > 0) {
    		msg = new ActionMessage("erro.campo.necessario", messageArgs);
    		
    	} else {
    		
    		if (propertyName.equals("usuarioInvalido")) {
    			msg = new ActionMessage("login.invalido", expression);
    			
    		} else if (propertyName.equals("erroLogin")) {
    			msg = new ActionMessage("login.negado", expression);
			} 
    		else {
    			msg = new ActionMessage(expression);
    			
    		}
    		
    	}
    	addActionError( propertyName, msg, request );
    }

    public void addActionError( String propertyName, ActionMessage error, HttpServletRequest request )
    {
        ActionMessages errors = ( ActionMessages ) request.getAttribute( Globals.ERROR_KEY );
        if ( errors == null ) request.setAttribute( Globals.ERROR_KEY, errors = new ActionMessages() );
        errors.add( propertyName, error );
    }
	
	private boolean tratarSCAException(SCAException ex, HttpServletRequest request) {
		boolean tratou = false;
		String message = null;
		if (ex.getErrorCode().equals("SCA_005"))
			message = "O utilizador n&atilde;o tem sess&otilde;es dispon&iacute;veis";
		else if (ex.getErrorCode().equals("SCA_016")) {
			request.setAttribute("alterarSenha", "A senha deve ser alterada, pois foi expirada.");
			return true;
		} else if (ex.getErrorCode().equals("SCA_009"))
			message = "Login inativo. Entre em contato com o coordenador de seguran&ccedil;a da informa&ccedil;&atilde;o";
		else if (ex.getErrorCode().equals("SCA_017"))
			message = "Utilizador n&atilde;o tem acesso &agrave; aplica&ccedil;&atilde;o";
		else if (ex.getErrorCode().equals("SCA_008"))
			message = "Login bloqueado. Entre em contato com o coordenador de seguran&ccedil;a da informa&ccedil;&atilde;o";
		else if (ex.getErrorCode().equals(SCAException.CHANGE_PWD_REQUIRED)){
			request.setAttribute("alterarSenha", "A senha deve ser alterada.");
			return true;
		}
			
		
		if (message != null) {
			tratou = true;
			addActionErrorExpression("erroLogin", message, new Object[]{}, request);
			request.getSession().setAttribute("erroLogin", message);
			request.setAttribute("erroLogin", Boolean.TRUE);
		}
		return tratou;
	}

	public ActionForward doLogout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setDateHeader("Expires", 0);
		try {
			if(request.getSession().getAttribute("logged_user")==null)
				return mapping.findForward("goToLogin");
			UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
			if( userCatalogo != null && SCAUtils.logout(userCatalogo));
				request.getSession().removeAttribute("logged_user");
		} catch (LoginException e) {
			request.getSession().setAttribute("erro_logout", e.getMessage());
			addActionErrorExpression("erro_logout", e.getMessage(), new Object[]{}, request);
			return mapping.findForward("logoutNaoSucedido");
		}
		request.getSession().invalidate();
		this.cleanHeader(response);
		return mapping.findForward("goToLogin");
	}

	public ActionForward carregarAlterarSenha(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String username = request.getParameter("alterar_senha_username");
		request.setAttribute("alterar_senha_username", username);
		this.cleanHeader(response);
		return mapping.findForward("alterarSenha");
	}

	public ActionForward alterarSenha(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		LoginForm formulario = (LoginForm)form;	

		if((formulario.getSenhaAtual().equals("")) || (formulario.getNovaSenha().equals("")) || (formulario.getNovaSenhaConfirmar().equals(""))) {
		
				StringBuffer msgErroCampos = new StringBuffer("É obrigatório informar o(s) campo(s)");
				boolean flagSeparador = false;
				
				if(formulario.getSenhaAtual().equals("")){
					msgErroCampos.append(" Senha Atual");
					flagSeparador = true;
				}
				if(formulario.getNovaSenha().equals("")){
					if (flagSeparador) {
						msgErroCampos.append(",");
					}
					msgErroCampos.append(" Nova Senha ");
					flagSeparador = true;
				}
				if(formulario.getNovaSenhaConfirmar().equals("")){
					if (flagSeparador) {
						msgErroCampos.append(",");
					}
					msgErroCampos.append(" Confirmar Nova Senha ");
				}
								
				String msgErro_Trim = msgErroCampos.toString().trim();
				msgErroCampos = new StringBuffer(msgErro_Trim).append(".");
				
				request.getSession().setAttribute("erroAlterarSenha", msgErroCampos.toString());
				addActionErrorExpression("erroAlterarSenha", msgErroCampos.toString() , new Object[]{}, request);
				request.setAttribute("flagPaginaAcessada", new Boolean(true));
				
				return mapping.findForward("fail");
				
				
		}		
		
		if(!formulario.getNovaSenha().equals(formulario.getNovaSenhaConfirmar())){
			request.setAttribute("erroAlterarSenha", "A Confirmação da Nova Senha deve ser igual à Nova Senha informada. Tente novamente.");
			request.setAttribute("flagPaginaAcessada", new Boolean(true));
			
			return mapping.findForward("fail");
		}
		
		try {
			String clientIP = "";
			String clientHostname = "";
			
			java.net.InetAddress inetAddress  = null;
	
			try{
				if (request.getHeader("x-forwarded-for") != null) {
					inetAddress = java.net.InetAddress.getByName(request.getHeader("x-forwarded-for"));
		        }
				else{
					inetAddress = java.net.InetAddress.getByName(request.getRemoteAddr());
				}
			    clientIP = inetAddress.getHostAddress();
			    clientHostname = inetAddress.getHostName();
		      }catch (Exception e){}

			UserCatalogo userCatalogo = SCAUtils.alterarSenha(formulario.getUsuario(), formulario.getSenhaAtual(), formulario.getNovaSenha(), clientHostname, clientIP);
			request.setAttribute("logged_user", userCatalogo);
		} catch (LoginException e) {
			request.setAttribute("erro_alterar_senha", e.getMessage());
			return mapping.findForward("failure");
	    }catch (UsuarioInvalidoException e) {
			request.setAttribute("erro_alterar_senha", e.getMessage());
			return mapping.findForward("failure");
		}
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward goHome(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.cleanHeader(response);
		response.setDateHeader("Expires", 0);
		
		return mapping.findForward("goHome");
	}

	public ActionForward resetCache(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BaseService.resetCache();
		try {
			response.getWriter().write("<h1>Limpeza do cache executada com sucesso.</h1>");
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward detalhesCache(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			response.getWriter().write(BaseService.detalhesCache());
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}