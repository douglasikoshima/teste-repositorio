package commons.errors; 

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.commons.utils.exceptions.ExceptionContainer;

public class FormError extends ActionForm {

    private static final long serialVersionUID = 5808819432160182475L;
    
	private String errorIcon;
    private String errorCode;
    private String errorMessage;
    private String errorStack;
    private String urlToGo;
    private String target;

    public FormError(ExceptionContainer container) {
        if (container == null) throw new NullPointerException("container is null");
        this.errorIcon = container.getErrorIcon();
        this.errorCode = container.getErrorCode();
        this.errorMessage = container.getErrorMessage();
        this.errorStack = container.getErrorStack();
        this.urlToGo = container.getUrlToGo();
        this.target = container.getTarget();
    }
    
    public void setErrorIcon(String errorIcon) {
            this.errorIcon = errorIcon;
    }

    public String getErrorIcon() {
        return this.errorIcon;
    }

    public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorStack(String errorStack) {
            this.errorStack = errorStack;
    }

    public String getErrorStack() {
        return this.errorStack;
    }

    public void setUrlToGo(String urlToGo) {
            this.urlToGo = urlToGo;
    }

    public String getUrlToGo() {
        return this.urlToGo;
    }

    public void setTarget(String target) {
            this.target = target;
    }

    public String getTarget() {
        return this.target;
    }
} 
