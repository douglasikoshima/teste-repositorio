package br.com.vivo.fo.commons.utils.presentation.menu;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

// Referenced classes of package com.bcp.taglib.presentation:
//            HtmlBaseHandlerTag

public class SubMenuTag extends HtmlBaseHandlerTag
{

	private static final long serialVersionUID = 7790268565911116065L;
	public SubMenuTag()
    {
    }

    public String getMenu()
    {
        return menu;
    }

    public void setMenu(String menu)
    {
        this.menu = menu;
    }

    public void setTitle(String title)
    {
        super.setTitle(getPropertyValue(title));
    }

    public int doAfterBody()
        throws JspException
    {
        if(super.bodyContent != null)
        {
            String v = super.bodyContent.getString().trim();
            if(v.length() > 0)
                text = v;
        }
        return 0;
    }

    public int doStartTag()
        throws JspException
    {
        text = null;
        return 2;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuffer results = new StringBuffer(50);
        results.append("    <tr>");
        results.append("        <td nowrap");
        if(menu != null)
        {
            results.append(" menuAssociado=\"");
            results.append(menu);
            results.append("\"");
        }
        if(super.getTitle() != null)
        {
            results.append(" title=\"");
            results.append(super.getTitle());
            results.append("\"");
        }
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        results.append(">");
        results.append(text);
        results.append("        </td>");
        results.append("    </tr>");
        try
        {
            super.pageContext.getOut().write(results.toString());
        }
        catch(IOException _ex)
        {
            throw new JspTagException("Fatal error: the tag sub menu could not write to JSP out.");
        }
        return 6;
    }

    public void release()
    {
        super.release();
        text = null;
        menu = null;
    }

    private String menu;
    private String text;
}