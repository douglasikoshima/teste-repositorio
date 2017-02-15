package br.com.vivo.fo.commons.utils.presentation.menu;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.apache.struts.taglib.html.LinkTag;

public class MenuItemTag extends LinkTag
{

	private static final long serialVersionUID = -8126610548519567328L;

	public MenuItemTag()
    {
    }

    public void setTitle(String title)
    {
        super.setTitle(getPropertyValue(title));
    }

    public int doStartTag()
        throws JspException
    {
        StringBuffer results = new StringBuffer(30);
        results.append("<tr>");
        results.append("    <td nowrap ");
        if(super.getTitle() != null)
            results.append(" title=\"" + super.getTitle() + "\"");
        if(super.getStyleClass() != null)
            results.append(" class=\"" + super.getStyleClass() + "\"");
        results.append(">");
        try
        {
            super.pageContext.getOut().write(results.toString());
        }
        catch(IOException _ex)
        {
            throw new JspTagException("Fatal error: the tagmenu item could not write to JSP out.");
        }
        return super.doStartTag();
    }

    public int doEndTag()
        throws JspTagException, JspException
    {
        super.doEndTag();
        StringBuffer results = new StringBuffer(30);
        results.append("    </td>");
        results.append("</tr>");
        try
        {
            super.pageContext.getOut().write(results.toString());
        }
        catch(IOException _ex)
        {
            throw new JspTagException("Fatal error: the tag menu item could not write to JSP out.");
        }
        return 6;
    }

    protected String getPropertyValue(String value)
    {
        if(value != null && value.trim().startsWith("${"))
            return (String)super.pageContext.getAttribute(value.substring(value.indexOf("{") + 1, value.lastIndexOf("}")));
        else
            return value;
    }
}