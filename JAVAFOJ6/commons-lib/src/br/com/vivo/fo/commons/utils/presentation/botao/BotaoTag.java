package br.com.vivo.fo.commons.utils.presentation.botao; 

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;


public class BotaoTag extends TagSupport { 

	private static final long serialVersionUID = 8699856551974833342L;
	private String id         = null; 
    private String width      = null;
    private String height     = null;
    private String onclick    = null;
    private String styleClass = null;
    private String value      = null;
    private String enabled    = null;
     
    public BotaoTag() {
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

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public int doStartTag() throws JspException {
        StringBuffer results    = new StringBuffer();
        boolean      btEnabled  = ! (enabled != null && enabled.trim().substring(0,1).equalsIgnoreCase("N"));
        String       enabledGif = (btEnabled ? "" : "_off_");
        
        //Verifica se abilita ou não o botao

        results.append("<table width='" + width + "' height='" + height + "' border='0' cellpadding='0' cellspacing='0' class='" + styleClass + "'");
        if( btEnabled  )
            results.append(" style='cursor:hand'");

        results.append(" onclick=\"" + onclick +"\"");
            
        results.append(">\n");
        results.append("    <tr>\n");
        results.append("        <td width='3' height='4'><img src='/FrontOfficeWeb/resources/images/bt_template" + enabledGif + "01.gif' width='3' height='4'></td>\n");
        results.append("        <td background='/FrontOfficeWeb/resources/images/bt_template" + enabledGif + "02.gif'></td>\n");
        results.append("        <td width='4' height='4'><img src='/FrontOfficeWeb/resources/images/bt_template" + enabledGif + "03.gif' width='4' height='4'></td>\n");
        results.append("    </tr>\n");
        results.append("    <tr>\n");
        results.append("        <td background='/FrontOfficeWeb/resources/images/bt_template" + enabledGif + "04.gif'></td>\n");
        results.append("        <td id='" + id + "' align='center' valign='middle' class='" + (btEnabled ? "abaSelected" : "abaUnselected") + "'>" + value +"</td>\n");
        results.append("        <td background='/FrontOfficeWeb/resources/images/bt_template" + enabledGif + "05.gif'></td>\n");
        results.append("    </tr>\n");
        results.append("    <tr>\n");
        results.append("        <td width='3' height='6'><img src='/FrontOfficeWeb/resources/images/bt_template" + enabledGif + "06.gif' width='3' height='6'></td>\n");
        results.append("        <td background='/FrontOfficeWeb/resources/images/bt_template" + enabledGif + "07.gif'></td>\n");
        results.append("        <td width='4' height='6'><img src='/FrontOfficeWeb/resources/images/bt_template" + enabledGif + "08.gif' width='4' height='6'></td>\n");
        results.append("    </tr>\n");
        results.append("</table>\n");

        try {
            pageContext.getOut().print(results.toString());
            return (EVAL_BODY_INCLUDE);
            
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public int doEndTag() throws JspException {
        return (EVAL_PAGE);
    }

    public void release() {
        super.release();
        id         = null;
        width      = null;
        height     = null;
        onclick    = null;
        styleClass = null;
        value      = null;
        enabled    = null;
    }    
} 
