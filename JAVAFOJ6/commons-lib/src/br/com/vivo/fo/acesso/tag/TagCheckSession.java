package br.com.vivo.fo.acesso.tag;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

public class TagCheckSession extends javax.servlet.jsp.tagext.TagSupport {

	private static final long serialVersionUID = -540726560865230760L;
	private transient Logger log = new Logger("start");

	public int doStartTag() throws JspException {
		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		if (ConstantesCRM.getCurrentUser(pageContext.getSession()) == null) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/session.jsp");
				try {
					log.debug("TagCheckSession: Redirecionando para session.jsp");
					rd.forward(request, response);
					return (SKIP_PAGE);
				} catch (ServletException ex) {
					log.error("TagCheckSession: " + ex.getMessage(), ex);
					throw new JspException(ex);
				}
			} catch (java.io.IOException ex) {
				log.error("TagCheckSession: " + ex.getMessage(), ex);
				throw new JspException(ex);
			}
		} else {
			return (EVAL_PAGE);
		}
	}

	public int doEndTag() throws JspException {
		return (EVAL_PAGE);
	}
}
