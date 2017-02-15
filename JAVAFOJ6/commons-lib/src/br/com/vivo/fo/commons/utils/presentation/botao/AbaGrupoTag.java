package br.com.vivo.fo.commons.utils.presentation.botao; 

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class AbaGrupoTag  extends TagSupport { 

	private static final long serialVersionUID = 3360411374952947921L;
	private String id         = null; 
    private String width      = null;
    private String height     = null;
    private String styleClass = null;
     
    public AbaGrupoTag() {
        super(); 
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
    
    public int doStartTag() throws JspException {
        StringBuffer results = new StringBuffer();

        results.append("<table border='0' cellpadding='0' cellspacing='0' width='" + width + "' height='" + height + "' background='/FrontOfficeWeb/resources/images/aba_bkg_off.gif' class='" + styleClass + "'>\n");
        results.append("    <tr id='" + id + "' valign='top'>\n");

        try {
            pageContext.getOut().print(results.toString());
            return (EVAL_BODY_INCLUDE);
            
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public int doEndTag() throws JspException {
        StringBuffer results = new StringBuffer();

        results.append("    </tr>\n");
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
        id         = null;
        width      = null;
        height     = null;
        styleClass = null;
    }    
} 
