package br.com.vivo.fo.commons.utils.presentation.menu;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class MenuTag extends HtmlBaseHandlerTag
{

	private static final long serialVersionUID = -4142425937918497115L;
	private String text;
    private String width;
    private String idMenuMin;
    private String idMenuMax;

    public MenuTag()
    {
    }

    public void setWidth(String width) 
    {
        this.width = width;
    }

    public void setIdMenuMin(String idMenuMin) {
        this.idMenuMin = idMenuMin;
    }
    
    public void setIdMenuMax(String idMenuMax) {
        this.idMenuMax = idMenuMax;
    }

    public int doAfterBody()
        throws JspException
    {
        if(super.bodyContent != null)
        {
            String value = super.bodyContent.getString().trim();
            if(value.length() > 0)
                text = value;
        }
        return 0;
    }

    public int doStartTag() throws JspException
    {
        StringBuffer results = new StringBuffer(30);
        
        results.append("<table border='0' cellpadding='0' cellspacing='0'");
        
        if(super.getStyleId() != null)
            results.append(" id=\"" + super.getStyleId() + "\"");
            
        if(super.getStyleClass() != null)
            results.append(" class=\"" + super.getStyleClass() + "\"");
            
        if(this.width != null)
            results.append(" width=\"" + this.width + "\"");
            
        if(super.getStyle() != null)
            results.append(" style=\"" + super.getStyle() + "\"");
            
        results.append(">\n");
        results.append("    <tr>\n");
        results.append("        <td>\n");
        
        results.append("            <table width='790' id='" + idMenuMin + "' border='0' cellpadding='0' cellspacing='0' style='display:none;'>\n");
        results.append("                <tr>\n");
        results.append("                    <td width='17'><img id='menu_button_open' style='cursor:hand;' hspace='4' src='/FrontOfficeWeb/resources/images/menu_small_bt.gif' onclick='mudarMenu(" + idMenuMin + ", " + idMenuMax + ");'></td>\n");
        results.append("                    <td bgcolor='006699' onmouseover='mudarMenuMin(" + idMenuMin + ", " + idMenuMax + ");'><img style='cursor:hand;' src='/FrontOfficeWeb/resources/images/menu_small_txt.gif'></td>\n");
        results.append("                </tr>\n");
        results.append("            </table>\n");
          
        results.append("            <table id='" + idMenuMax + "' border='0' cellpadding='0' cellspacing='0' style='display:;' onmouseout='mudarMenuMax(" + idMenuMin + ", " + idMenuMax + ");'>\n");
        results.append("                <tr>\n");
        results.append("                    <td><img id='menu_button_close' style='cursor:hand;' onclick='mudarMenu(" + idMenuMin + ", " + idMenuMax + ");' src='/FrontOfficeWeb/resources/images/menu_bt.gif'></td>\n");
        
        try
        {
            super.pageContext.getOut().write(results.toString());
        }
        catch(IOException _ex)
        {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
        text = null;
        return 2;
    }

    public int doEndTag() throws JspException
    {
        StringBuffer results = new StringBuffer(30);
        
        if(text != null)
            results.append(text);

        results.append("                </tr>\n");
        results.append("            </table>\n");
        results.append("        </td>\n");
        results.append("    </tr>\n");
        results.append("</table>\n");
        
        try
        {
            super.pageContext.getOut().write(results.toString());
        }
        catch(IOException _ex)
        {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
        return 6;
    }

    public void release()
    {
        super.release();
        text = null;
    }
}