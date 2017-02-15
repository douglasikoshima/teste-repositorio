package br.com.vivo.fo.fidelizacao.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class HeaderCellTag extends TagSupport {

	private static final long serialVersionUID = -4649234423876196470L;

	public int doStartTag() throws JspException {
		try {
			pageContext
					.getOut()
					.print(
							"<td height='30' align='center' bgcolor='#545454' style='border: 1px solid #F5F5F5;padding-top: 2px;padding-bottom: 2px;padding-left: 5px;padding-right: 5px;'><b><font color='white' size='1'>");
		} catch (Exception e) {
			throw new JspException("Erro na headerColumn: " + e.getMessage());
		}
		return (EVAL_BODY_INCLUDE);
	}

  public int doEndTag() throws JspException {

    try {

      pageContext.getOut().print("</font></b></td>\n");

    } catch (Exception e) {

      throw new JspException("Erro na TagSupport: " + e.getMessage());

    }

    return (EVAL_PAGE);
  }
}