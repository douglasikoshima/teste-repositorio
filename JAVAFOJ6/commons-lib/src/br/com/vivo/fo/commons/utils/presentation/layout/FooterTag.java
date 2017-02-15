package br.com.vivo.fo.commons.utils.presentation.layout; 

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class FooterTag  extends TagSupport {
    
	private static final long serialVersionUID = 1232393919916035947L;
	private String height = null;
    private String width  = null;
     
    public FooterTag() {
        super();
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public int doStartTag() throws JspException {
        StringBuffer results = new StringBuffer();

        results.append("			<table width='" + this.width + "' height='" +  this.height + "px' border='0' cellpadding='0' cellspacing='0'>\n");
		results.append("				<tr>\n");
		results.append("					<td align='center'><img src='/FrontOfficeWeb/resources/images/rodape.gif' width='" + (Integer.parseInt(this.width) - 2) + "px' height='" +  (Integer.parseInt(this.height) - 2) + "px'>");

        try {
            pageContext.getOut().print(results.toString());
            return (EVAL_BODY_INCLUDE);
            
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public int doEndTag() throws JspException {
        StringBuffer results = new StringBuffer();

        results.append("					</td>\n");
		results.append("				</tr>\n");
		results.append("			</table>\n");
        
        try {
            pageContext.getOut().print(results.toString());
            return (EVAL_PAGE);

        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public void release()
    {
        super.release();
    }    
} 
