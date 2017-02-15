package br.com.vivo.fo.commons.utils.presentation.quadro;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class QuadroTag extends TagSupport {

	private static final long serialVersionUID = 1429155691274283091L;
	private String id        = null;
    private String label     = null;
    private String width     = null;
    private String height    = null;
    private String onclick   = null;
    private String scroll    = null;

    public QuadroTag() {
        super();
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public void setWidth(String width) {
        this.width = width;
    }
    
    public void setheight(String height) {
        this.height = height;
    }
    
    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }
    
    public void setScroll(String scroll) {
        this.scroll = scroll;
    }

    public int doStartTag() throws JspException {

        StringBuffer results = new StringBuffer();

        results.append("<table border='0' cellpadding='0' cellspacing='0' height='");
        results.append(this.height);
        results.append("' width='");
        results.append(this.width);
        results.append("' id='table_");
        results.append(this.id);
        results.append("'>\n");
        results.append(" <tr valign='top'>\n");
        results.append(" <td>\n");
        results.append(" <table width='");
        results.append(this.width);
        results.append("' border='0' cellpadding='0' cellspacing='0' bgcolor='#0066cb'>\n");
        results.append(" <tr>\n");
		results.append(" <td style='width:7px;'><img src='/FrontOfficeWeb/resources/images/bcliente_left.gif'></td>\n");
        results.append(" <td style='width:auto;' align='left' id='");
        results.append(this.id);
        results.append("' class='tbl_tituloBG'>");

        if( label != null ) 
            results.append( label );
        else
            results.append("&nbsp;");

        results.append("</td>\n");
		results.append(" <td style='width:93px;' align='right' class='tbl_tituloBG'>");

        if( onclick != null && !"".equals(onclick)) {
            results.append("<img src='/FrontOfficeWeb/resources/images/bestatica_lupa.gif' onclick=\"");
            results.append(this.onclick);
            results.append("\" style='cursor:pointer;'>");
        }

        results.append("</td>\n");

		results.append(" </tr>\n");
		results.append(" </table>\n");
        results.append(" </td>\n");
        results.append(" </tr>\n");
        results.append(" <tr valign='top'>\n");
        results.append(" <td height='");
        results.append(this.height);
        results.append("'>\n");
        results.append(" <table width='");
        results.append(this.width);
        results.append("' height='");
        results.append(this.height);
        results.append("' border='0' cellpadding='0' cellspacing='1' bgcolor='#adadad'>\n");
		results.append(" <tr>\n");
		results.append(" <td bgcolor='#E3ECF4' align='center'>\n");
        results.append(" <div id='body_");
        results.append(this.id);
        results.append("' align='left' style='width:");

        if ( this.width.indexOf("%") == -1) {
            results.append(Integer.parseInt(this.width) - 10);
            results.append("px");
        } else
            results.append(this.width);

        results.append(";height:");
        if (this.height.indexOf("%") == -1) {
            results.append(Integer.parseInt(this.height) - 6);
            results.append("px");
        } else
            results.append(this.height);

        results.append(";overflow:");

        //Verifica se deseja o scroll no div
        if( scroll != null && scroll.trim().substring(0,1).equalsIgnoreCase("N") )
            results.append("hidden;");
        else
            results.append("auto;");

        results.append("' id='");
        results.append(this.id);

        //Fecha tag
        results.append("_div'>");

        try {
            pageContext.getOut().print(results.toString());
            return (EVAL_BODY_INCLUDE);
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public int doEndTag() throws JspException {

        StringBuffer results = new StringBuffer(30);

        results.append(" </div>\n");
		results.append(" </td>\n");
		results.append(" </tr>\n");
		results.append(" </table>\n");
        results.append(" </td>\n");
        results.append(" </tr>\n");
        results.append("</table>\n");

        try {
            pageContext.getOut().print(results.toString());
            return (EVAL_PAGE);
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public void release() {
        super.release();
        id        = null;
        label     = null;
        width     = null;
        height    = null;
        onclick   = null;
    }

}