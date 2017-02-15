package br.com.vivo.fo.commons.utils.presentation.layout; 

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class BodyTag extends TagSupport {

	private static final long serialVersionUID = 8390503921083313475L;
	private String idTable = null;
    private String idDiv   = null;
    private String height  = null;
    private String width   = null;
     
    public BodyTag() {
        super();
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public void setIdDiv(String idDiv) {
        this.idDiv = idDiv;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public int doStartTag() throws JspException {
        StringBuffer results = new StringBuffer();

        results.append("			<table id=" + idTable + " width='" + this.width + "' height='" +  this.height + "px' border='0' cellpadding='0' cellspacing='0'>\n");
		results.append("				<tr>\n");
		results.append("					<td bgcolor='#E3ECF4' align='center'>\n");
		results.append("						<div id=" + this.idDiv + " align='left' style='width:" + (Integer.parseInt(this.width) - 10) + "px;height=" + (Integer.parseInt(this.height) - 6) + "px;overflow:auto;'>");

        try {
            pageContext.getOut().print(results.toString());
            return (EVAL_BODY_INCLUDE);
            
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public int doEndTag() throws JspException {
        StringBuffer results = new StringBuffer();

        results.append("						</div>\n");
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

    public void release() {
        super.release();
        idTable = null;
        idDiv   = null;
        height  = null;
        width   = null;
    }    
    
} 
