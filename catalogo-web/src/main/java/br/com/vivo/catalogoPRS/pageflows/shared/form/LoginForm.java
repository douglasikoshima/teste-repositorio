package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class LoginForm extends ValidatorActionForm  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String usuario;

	private String senha;
	
	private String senhaAtual;

	private String novaSenha;

	private String novaSenhaConfirmar;	
	
	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getNovaSenhaConfirmar() {
		return novaSenhaConfirmar;
	}

	public void setNovaSenhaConfirmar(String novaSenhaConfirmar) {
		this.novaSenhaConfirmar = novaSenhaConfirmar;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}