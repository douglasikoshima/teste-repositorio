package br.com.vivo.fo.commons.utils.presentation.menu;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class MenuSeparatorTag extends TagSupport{

	private static final long serialVersionUID = 8582376321043093479L;

	public MenuSeparatorTag()
    {
    }

    public String getStyleClass()
    {
        return styleClass;
    }

    public void setStyleClass(String styleClass)
    {
        this.styleClass = styleClass;
    }

    public int doStartTag()
        throws JspException
    {
        return 0;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuffer results = new StringBuffer(30);
        results.append("<tr>");
        results.append("<td");
        if(styleClass != null)
            results.append(" class=\"" + styleClass + "\"");
        results.append(">");
        results.append(".</td>");
        results.append("</tr>\n");
        try
        {
            super.pageContext.getOut().write(results.toString());
        }
        catch(IOException _ex)
        {
            throw new JspTagException("Fatal error: the tag menu separator could not write to JSP out");
        }
        return 6;
    }

    public void release()
    {
        super.release();
        styleClass = null;
    }

    private String styleClass;
}