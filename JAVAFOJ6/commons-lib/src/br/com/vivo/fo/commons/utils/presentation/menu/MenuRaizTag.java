package br.com.vivo.fo.commons.utils.presentation.menu;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class MenuRaizTag extends HtmlBaseHandlerTag
{

	private static final long serialVersionUID = -8872092633759463289L;
	private String text;
//    private String title;
    private String value;
    private String width;
    private String haveNextMenu;

    public MenuRaizTag()
    {
    }

    public void setTitle(String title)
    {
        super.setTitle(getPropertyValue(title));
    }
    
    public void setValue(String value) 
    {
        this.value = value;
    }
    
    public void setWidth(String width) 
    {
        this.width = width;
    }
    
    public void setHaveNextMenu(String haveNextMenu) {
        this.haveNextMenu = haveNextMenu;
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

    public int doStartTag() throws JspException {
        StringBuffer results = new StringBuffer(30);
        results.append("    <td");
        
        if(super.getStyleId() != null)
            results.append(" id=\"" + super.getStyleId() + "\"");
            
        if(this.width != null)
            results.append(" width=\"" + this.width + "\"");
            
        if(super.getStyleClass() != null)
            results.append(" class=\"" + super.getStyleClass() + "\"");
            
        if(super.getStyle() != null)
            results.append(" style=\"" + super.getStyle() + "\"");
            
        if(super.getTitle() != null)
            results.append(" title=\"" + super.getTitle() + "\"");
            
        results.append(" ");
        results.append(prepareEventHandlers());
        
        if(this.value != null)
            results.append(" background='/FrontOfficeWeb/resources/images/menu_bg.gif' style='cursor:hand;'");

        results.append(">");
        
        if(this.value != null)
            results.append(this.value);
        
        try
        {
            super.pageContext.getOut().write(results.toString());
        }
        catch(IOException _ex)
        {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
        text = null;
        value = null;
        return 2;
    }

    public int doEndTag() throws JspException
    {
        StringBuffer results = new StringBuffer(30);
        if(text != null)
            results.append(text);

        if( (haveNextMenu != null) && (haveNextMenu.trim().substring(0,1).equalsIgnoreCase("S") || haveNextMenu.trim().substring(0,1).equalsIgnoreCase("Y")) ) {
            results.append("    <td><img src='/FrontOfficeWeb/resources/images/menu_delimit.gif'></td>\n");
            results.append("    <td><img src='/FrontOfficeWeb/resources/images/menu_left.gif'></td>\n");
        } else {
            results.append("    <td><img src='/FrontOfficeWeb/resources/images/menu_right.gif'></td>\n");
        }

        results.append("</td>\n");

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
        value = null;
    }
}