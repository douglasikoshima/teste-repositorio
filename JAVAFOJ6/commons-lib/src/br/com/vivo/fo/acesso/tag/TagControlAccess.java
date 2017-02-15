package br.com.vivo.fo.acesso.tag;

import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

@SuppressWarnings({"rawtypes"})
public class TagControlAccess extends TagControlAccessBase {

	private static final long serialVersionUID = 8481464026402300315L;

	private transient Logger log = new Logger("start");

	private ArrayList lista;

	protected boolean condition() throws JspException {

		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		if (ConstantesCRM.getCurrentUser(pageContext.getSession()) != null) {
			lista = (ArrayList) pageContext.getSession().getAttribute("lista");

		} else {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/session.jsp");
				try {
					log.debug("TagControlAccess: Redirecionando para session.jsp");
					rd.forward(request, response);
					return false;

				} catch (ServletException ex) {
					log.error("TagCheckSession: " + ex.getMessage(), ex);
					throw new JspException(ex);
				}

			} catch (java.io.IOException ex) {
				log.error("TagCheckSession: " + ex.getMessage(), ex);
				throw new JspException(ex);
			}
		}

		if (lista != null && lista.contains(this.getNomeIdentificador())) {
			return true;
		} else {
			return false;
		}

	}
}
