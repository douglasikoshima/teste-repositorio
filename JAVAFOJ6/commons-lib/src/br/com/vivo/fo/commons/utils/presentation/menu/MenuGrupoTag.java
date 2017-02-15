package br.com.vivo.fo.commons.utils.presentation.menu;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class MenuGrupoTag extends HtmlBaseHandlerTag{
    
	private static final long serialVersionUID = -4193239941457977485L;
	public MenuGrupoTag(){
    }

    public String getMenu(){
        return menu;
    }

    public void setMenu(String menu){
        this.menu = menu;
    }

    public int doAfterBody() throws JspException {
        if(super.bodyContent != null) {
            String value = super.bodyContent.getString().trim();
            if(value.length() > 0)
                text = value;
        }
        return 0;
    }

    public int doStartTag() throws JspException {
        StringBuffer results = new StringBuffer(30);
        results.append("<div");
        if(super.getStyleId() != null)
            results.append(" id=\"" + super.getStyleId() + "\"");
        if(super.getStyleClass() != null)
            results.append(" class=\"" + super.getStyleClass() + "\"");
        if(super.getStyle() != null)
            results.append(" style=\"z-index:99;" + super.getStyle() + "\"");
        if(menu != null)
            results.append(" menuPai=\"" + menu + "\"");
        results.append(">\n");
        results.append("    <table>\n");
        try{
            super.pageContext.getOut().write(results.toString());
        }catch(IOException _ex){
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
        text = null;
        return 2;
    }

    public int doEndTag() throws JspException {
        StringBuffer results = new StringBuffer(30);
        if(text != null)
            results.append(text);
        results.append("    </table>\n");
        results.append("</div>\n");
        try{
            super.pageContext.getOut().write(results.toString());
        }catch(IOException _ex){
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
        return 6;
    }

    public void release(){
        super.release();
        text = null;
        menu = null;
    }

    private String menu;
    private String text;
}