package br.com.vivo.fo.commons.utils.presentation.quadro;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class SessaoTag extends TagSupport {

	private static final long serialVersionUID = -8234642817777810672L;
	private String id        = null;
    private String label     = null;
    private String operacoes = null;
    private String width     = null;
    private String height    = null;
    private String onclick   = null;
    private String scroll    = null;

    public SessaoTag() {
        super();
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setOperacoes(String operacoes) {
        this.operacoes = operacoes;
    }

    public void setWidth(String width) {
        this.width = width;
    }
    
    public void setheight(String height) {
        this.height = height;
    }
    
    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }
    
    public void setScroll(String scroll) {
        this.scroll = scroll;
    }

    public int doStartTag() throws JspException {
        /*
        StringBuffer results = new StringBuffer(30);

        results.append("<table border='0' cellpadding='0' cellspacing='0' height='" + this.height + "' width='" + this.width + "'>\n");
        results.append("    <tr valign='top'>\n");
        results.append("        <td>\n");
        results.append("			<table width='" + this.width + "' border='0' cellpadding='0' cellspacing='0' bgcolor='#0066cb'>\n");
		results.append("				<tr>\n");
		results.append("					<td style='width:7px;'><img src='/FrontOfficeWeb/resources/images/bsessao_left.gif'></td>\n");
        results.append("					<td style='width:auto;' background='/FrontOfficeWeb/resources/images/bsessao_bg.gif' align='left' id='" + this.id + "'");
        
        if( label != null ) 
            results.append("<span class='sessaoTitulo'>" + label + "&nbsp;>&nbsp;</span>");
        else
            results.append("&nbsp;");
        
        if( operacoes != null )
            results.append("<span class='sessaoOperacoes'>"+ operacoes + "</span>");
        else
            results.append("&nbsp;");
        
        results.append("</td>\n");
		results.append("					<td style='width:93px;' background='/FrontOfficeWeb/resources/images/bsessao_bg.gif' align='right' class='tbl_titulo'>");
        
        if( onclick != null ) 
            results.append("<img src='/FrontOfficeWeb/resources/images/bestatica_lupa.gif' onclick=\"" + this.onclick + "\" style='cursor:hand;'>");
            
        results.append("</td>\n");
        
		results.append("				</tr>\n");
		results.append("			</table>\n");
        results.append("        </td>\n");
        results.append("    </tr>\n");
        results.append("    <tr valign='top'>\n");
        results.append("        <td height='" + this.height + "'>\n");
		results.append("			<table width='" + this.width + "' height='" + this.height + "' border='0' cellpadding='0' cellspacing='1' bgcolor='#adadad'>\n");
		results.append("				<tr>\n");
		results.append("					<td bgcolor='#E3ECF4' align='center'>\n");
		results.append("						<div align='left' style='width:" + (this.width.indexOf("%") == -1 ? (Integer.parseInt(this.width) - 10) + "px" : this.width) + ";height=" + (this.height.indexOf("%") == -1 ? (Integer.parseInt(this.height) - 6) + "px" : this.height) + ";overflow:");
        
        //Verifica se deseja o scroll no div
        if( scroll != null && scroll.trim().substring(0,1).equalsIgnoreCase("N") )
            results.append("hidden;");
        else
            results.append("auto;");
        
        //Fecha tag
        results.append("'>");
        */
        
        StringBuffer results = new StringBuffer();

        results.append("<table border='0' cellpadding='0' cellspacing='0' height='");
        results.append(this.height);
        results.append("' width='");
        results.append(this.width);
        results.append("'>\n");
        results.append(" <tr valign='top'>\n");
        results.append(" <td>\n");
        results.append(" <table width='");
        results.append(this.width);
        results.append("' border='0' cellpadding='0' cellspacing='0' bgcolor='#0066cb'>\n");
		results.append(" <tr>\n");
		results.append(" <td style='width:7px;'><img src='/FrontOfficeWeb/resources/images/bsessao_left.gif'></td>\n");
        results.append(" <td style='width:auto;' background='/FrontOfficeWeb/resources/images/bsessao_bg.gif' align='left' id='");
        results.append(this.id);
        results.append("'");
        
        if( label != null ) 
        {
            results.append("<span class='sessaoTitulo'>");
            results.append(label);
            results.append("&nbsp;>&nbsp;</span>");
        }
        else
            results.append("&nbsp;");
        
        if( operacoes != null )
        {
            results.append("<span class='sessaoOperacoes'>");
            results.append(operacoes);
            results.append("</span>");
        }
        else
            results.append("&nbsp;");
        
        results.append("</td>\n");
		results.append(" <td id='"+this.id+"_tdSessao' style='width:93px;' background='/FrontOfficeWeb/resources/images/bsessao_bg.gif' align='right' class='tbl_titulo'>");
        
        if( onclick != null ) 
            results.append("<img src='/FrontOfficeWeb/resources/images/bestatica_lupa.gif' onclick=\"" + this.onclick + "\" style='cursor:hand;'>");
            
        results.append("</td>\n");
        
		results.append(" </tr>\n");
		results.append(" </table>\n");
        results.append(" </td>\n");
        results.append(" </tr>\n");
        results.append(" <tr valign='top'>\n");
        results.append(" <td height='");
        results.append(this.height);
        results.append("'>\n");
        results.append(" <table width='");
        results.append(this.width);
        results.append("' height='");
        results.append(this.height);
        results.append("' border='0' cellpadding='0' cellspacing='1' bgcolor='#adadad'>\n");
		results.append(" <tr>\n");
		results.append(" <td bgcolor='#E3ECF4' align='center'>\n");
        results.append(" <div align='left' style='width:");
        if (this.width.indexOf("%") == -1)
        {
            results.append(Integer.parseInt(this.width) - 10);
            results.append("px");
        }
        else
            results.append(this.width);
        results.append(";height=");
        if (this.height.indexOf("%") == -1)
        {
            results.append(Integer.parseInt(this.height) - 6);
            results.append("px");
        }
        else
            results.append(this.height);        
        results.append(";overflow:");
        
        //Verifica se deseja o scroll no div
        if( scroll != null && scroll.trim().substring(0,1).equalsIgnoreCase("N") )
            results.append("hidden;");
        else
            results.append("auto;");
        
        //Fecha tag
        results.append("'>");
        
        
        try {
            pageContext.getOut().print(results.toString());
            
            return (EVAL_BODY_INCLUDE);
            
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public int doEndTag() throws JspException {
        StringBuffer results = new StringBuffer(30);

        results.append("</div>\n");
		results.append(" </td>\n");
		results.append(" </tr>\n");
		results.append(" </table>\n");
        results.append(" </td>\n");
        results.append(" </tr>\n");
        results.append("</table>\n");

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
        id        = null;
        label     = null;
        operacoes = null;
        width     = null;
        height    = null;
        onclick   = null;
    }
}