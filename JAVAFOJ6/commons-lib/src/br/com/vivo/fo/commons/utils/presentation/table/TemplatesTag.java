package br.com.vivo.fo.commons.utils.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class TemplatesTag extends TagSupport {

	private static final long serialVersionUID = 4597135277241542946L;

public TemplatesTag() {
    super();
  }

  public int doStartTag() throws JspException {
    try {

      StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

      HTML.append("    <tr style=\"display: none;\">\n");
      HTML.append("      <td>\n");
      HTML.append("        <table>\n");

      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na TemplatesTag: " + e.getMessage());

    }

    return (EVAL_BODY_INCLUDE);
  }

  public int doEndTag() throws JspException {
    try {

      StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

      HTML.append("        </table>\n");
      HTML.append("      </td>\n");
      HTML.append("    </tr>\n");

      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na TemplatesTag: " + e.getMessage());

    }

    return (EVAL_PAGE);
  }

  //-=-=-=-=-=-=-=-=-=-=-=-=- METODOS PARTICULARES -=-=--=-=-=-=-=-=-

  //-=-=-=-=-=-=-=-=-=-=-=-=- ATRIBUTOS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=

  // Esta Tag n?o tem atributos

}