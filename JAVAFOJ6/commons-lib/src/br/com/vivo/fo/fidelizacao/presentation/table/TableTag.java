package br.com.vivo.fo.fidelizacao.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TableTag extends TagSupport {

	private static final long serialVersionUID = 2864360848882624555L;

public int doStartTag() throws JspException {

    try {

      pageContext.getOut().print("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style='border-collapse: collapse;'>\n");

    } catch (Exception e) {

      throw new JspException("Erro na tableTag: " + e.getMessage());

    }

    return (EVAL_BODY_INCLUDE);
  }

  public int doEndTag() throws JspException {

    try {

      //StringBuffer HTML = new StringBuffer("");

      pageContext.getOut().print(" </table>\n");

    } catch (Exception e) {

      throw new JspException("Erro na tableTag: " + e.getMessage());

    }

    return (EVAL_PAGE);
  }
  
}