package br.com.vivo.fo.commons.utils.exceptions;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class ExceptionContainer implements Serializable{

	private static final long serialVersionUID = -4123692671225132027L;
	public static final String KEYRING = "formError";
	public static final String DEFAULT_ICON = "erroGlobal.gif";
	public static final String DEFAULT_ERROR_CODE = "0000";
	public static final String DEFAULT_SYSTEM_CODE = "00";
	public static final String DEFAULT_STATUS_CODE = "00E0000";
	public static final String DEFAULT_TARGET = "_self";

	public static final String DEFAULT_CONTROLLER = "/begin.do";
	public static final String INTERNAL_ERROR = "Erro interno. Contacte o helpdesk.";
	public static final String NO_STACK_AVAILABLE = "Não há stack disponivel";

	private String errorIcon;
	private String errorCode;
	private String errorMessage;
	private String errorStack;
	private String urlToGo;
	private String target;

	public ExceptionContainer() {
		init(null);
	}

	public ExceptionContainer(HttpServletRequest request) {
		if (request != null) {
			init(request.getContextPath() + DEFAULT_CONTROLLER);
		} else {
			init(null);
		}
	}

	public ExceptionContainer(String url) {
		init(url);
	}

	private void init(String url) {
		setErrorIcon(null);
		setErrorCode(null);
		setErrorMessage(null);
		setErrorStack(null);
		setUrlToGo(url);
		setTarget(null);
	}

	public void setErrorIcon(String errorIcon) {
		if ((errorIcon == null) || (errorIcon.trim().length() == 0)) {
			this.errorIcon = DEFAULT_ICON;
		} else {
			this.errorIcon = errorIcon;
		}
	}

	public String getErrorIcon() {
		return this.errorIcon;
	}

	public void setErrorCode(String errorCode) {
		if ((errorCode == null) || (errorCode.trim().length() == 0)) {
			this.errorCode = DEFAULT_ERROR_CODE;
		} else {
			this.errorCode = errorCode;
		}
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		if ((errorMessage == null) || (errorMessage.trim().length() == 0)) {
			this.errorMessage = ConstantesCRM.SVAZIO;
		} else {
			this.errorMessage = errorMessage;
		}
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorStack(String errorStack) {
		if ((errorStack == null) || (errorStack.trim().length() == 0)) {
			this.errorStack = ConstantesCRM.SVAZIO;
		} else {
			this.errorStack = errorStack;
		}
	}

	public String getErrorStack() {
		return this.errorStack;
	}

	public void setUrlToGo(String urlToGo) {
		if ((urlToGo == null) || (urlToGo.trim().length() == 0)) {
			this.urlToGo = DEFAULT_CONTROLLER;
		} else {
			this.urlToGo = urlToGo;
		}
	}

	public String getUrlToGo() {
		return this.urlToGo;
	}

	public void setTarget(String target) {
		if ((target == null) || (target.trim().length() == 0)) {
			this.target = DEFAULT_TARGET;
		} else {
			this.target = target;
		}
	}

	public String getTarget() {
		return this.target;
	}


	static public JspException buildJspException(PageContext pageContext, String message, Exception ex) {
		if (message == null) {
			message = ex.getMessage();
		}
		return new JspException(message, ex);
	}

}
