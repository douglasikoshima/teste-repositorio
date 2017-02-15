package global;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import br.com.vivo.fo.commons.utils.exceptions.ExceptionContainer;
import br.com.vivo.fo.commons.utils.exceptions.ExceptionHandler;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.bea.wlw.netui.pageflow.LoginExpiredException;
import com.bea.wlw.netui.pageflow.NotLoggedInException;
import com.bea.wlw.netui.pageflow.PageFlowException;
import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

/**
 * The Global page flow is used to define actions which can be invoked by any other
 * page flow in a webapp. The "jpf:catch" annotation provides a global way to catch
 * unhandled exceptions by forwarding to an error page.
 *
 * @jpf:catch type="Exception" method="handleException"
 * @jpf:catch type="PageFlowException" method="handlePageFlowException"
 */
public class Global extends AbstractAction {

	private static final long serialVersionUID = -6077156266864328547L;

	private static transient Logger log = new Logger(Global.class.getName());
	private static final String PAGE = "/index.jsp";

	public Global(){
	}

	/**
	 * Sample global action that will return to the default Controller.jpf in
	 * the webapp root.
	 * @jpf:action
	 * @jpf:forward name="home" path="/Controller.jpf"
	 */
	public ActionForward home() {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("home");
	}

	/**
	 * @jpf:exception-handler
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="reload" return-to="currentPage"
	 */
	protected ActionForward handleException(Exception ex, String actionName, String message, ActionForm form) {
		log.debug("GlobalApp.handleException started");
		String home = request.getContextPath() + ExceptionContainer.DEFAULT_CONTROLLER;
		FormError formError = new FormError(ExceptionHandler.buildExceptionContainer(ex, home));
		formError.setTarget(ConstantesCRM.FRAMEAPP);
		request.setAttribute("org.apache.struts.action.EXCEPTION", formError);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
	}

	/**
	 * Handler for native page flow exceptions (e.g., ActionNotFoundException,
	 * which is thrown when an unknown page flow action is requested). This handler
	 * allows PageFlowExceptions to write informative error pages to the response.
	 * To use the standard exception-handler for these exceptions, simply remove
	 * this method and the "jpf:catch" annotation for PageFlowException.
	 *
	 * @jpf:exception-handler
	 * @jpf:forward name="session" path="/session.jsp"
	 */
	public ActionForward handlePageFlowException(PageFlowException ex, String actionName, String message, ActionForm form) throws java.io.IOException {
		log.debug("GlobalApp.handlePageFlowException: " + ex.getClass().getName());
		request.setAttribute("frame", "_top");
		if (ex instanceof LoginExpiredException) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("session");
		} else if (ex instanceof NotLoggedInException) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("session");
		} else {
			return handleException(ex, actionName, message, form);
		}
	}

	/**
	 * Proxy para <code>ExceptionHandler.FormError(Exception e)</code>
	 *
	 * Constroi um objeto FormError contendo informações extraidas de uma Exception.<br/>
	 * Também contém a URL do controller principal, de nome <i>Controller.jpf</i>.
	 *
	 * @param Exception e é a exceção a ser exibida na tela de erro.
	 * @param String uri é a URI a ser acionada ao ser pressionado o botão "Fechar" da tela de erro.
	 *
	 * @see FormError
	 * @see /error.jsp
	 * @see /error.jsp
	 * @see ExceptionHandler.FormError(Exception e)
	 *
	 */
	public FormError buildFormError(Exception e) {
		String home = request.getContextPath() + ExceptionContainer.DEFAULT_CONTROLLER;
		return new FormError(ExceptionHandler.buildExceptionContainer(e, home));
	}

	/**
	 * Proxy para <code>ExceptionHandler.FormError(Exception e, String uri)</code>
	 *
	 * Constroi um objeto FormError contendo informações extraidas de uma Exception.<br/>
	 * Também contém a URI a ser acionada após a visualização da tela de erro.
	 *
	 * @param Exception e é a exceção a ser exibida na tela de erro.
	 * @param String uri é a URI a ser acionada ao ser pressionado o botão "Fechar" da tela de erro.
	 *
	 * @see FormError
	 * @see /error.jsp
	 * @see /error.jsp
	 * @see ExceptionHandler.FormError(Exception e, String url)
	 */
	public FormError buildFormError(Exception e, String uri) {
		return new FormError(ExceptionHandler.buildExceptionContainer(e, uri));
	}

	/**
	 * Proxy para <code>ExceptionHandler.FormError(Exception e, String url)</code><p/>
	 *
	 * Constroi um objeto FormError contendo informações extraidas de uma Exception.<br/>
	 * Também contém a URL da action <i>begin()</i> do Page Flow atual.
	 *
	 * @param Exception e é a exceção a ser exibida na tela de erro.
	 * @param HttpServletRequest request é passado pelo pageflow. Caso <code>request</code> seja null,
	 *        a URL acionada será "/Controller.jpf".
	 *
	 * @see FormError
	 * @see /error.jsp
	 * @see /error.jsp
	 * @see ExceptionHandler.FormError(Exception e, String url)
	 */
	public FormError buildFormError(Exception e, HttpServletRequest request) {
		String tmp = request.getContextPath();
		String home = tmp + ExceptionContainer.DEFAULT_CONTROLLER;
		if (tmp == null) {
			return new FormError(ExceptionHandler.buildExceptionContainer(e, home));
		} else {
			return new FormError(ExceptionHandler.buildExceptionContainer(e, home+PAGE));
		}
	}

	/**
	 * @deprecated
	 */
	public String warningFrame() {
		return ConstantesCRM.SVAZIO;
	}
}
