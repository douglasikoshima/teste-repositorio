package br.com.vivo.fo.fidelizacao.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class DataCellTag extends TagSupport {

	private static final long serialVersionUID = 8011985024923455032L;
	public String key = ConstantesCRM.SVAZIO;

	public DataCellTag() {
		super();
	}

	public int doStartTag() throws JspException {

		try {

            pageContext.getOut().print("<td height='20' style='border: 1px solid #F5F5F5;padding-top: 2px;padding-bottom: 2px;padding-left: 5px;padding-right: 5px;'>");

		} catch (Exception e) {

			throw new JspException("Erro na RowColumnTag: " + e.getMessage());

		}

		return (EVAL_BODY_INCLUDE);
	}

	public int doEndTag() throws JspException {

		try {
			pageContext.getOut().print("&nbsp;</td>\n");

		} catch (Exception e) {

			throw new JspException("Erro na RowColumnTag: " + e.getMessage());

		}

		return (EVAL_PAGE);
	}

}