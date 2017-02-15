package br.com.vivo.fo.commons.utils.presentation.quadro; 

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class QuadroFlutuanteTag extends TagSupport { 

	private static final long serialVersionUID = 7106499883698697277L;
	private String id             = null;
    private String idIframe       = null;
    private String label          = null;
    private String width          = null;
    private String height         = null;
    private String scroll         = "no";
    private String spacesTop      = null;
    private String spacesLeft     = null;
    private String display        = null;
    private String src            = null;
    private String url            = null;
    private String onclick = null;

    public QuadroFlutuanteTag() {
        super();
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setIdIframe(String idIframe) {
        this.idIframe = idIframe;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public void setWidth(String width) {
        this.width = width;
    }
    
    public void setheight(String height) {
        this.height = height;
    }
    
    public void setScroll(String scroll) {
        this.scroll = scroll;
    }
    
    public void setSpacesTop(String spacesTop) {
        this.spacesTop = spacesTop;
    }
    
    public void setSpacesLeft(String spacesLeft) {
        this.spacesLeft = spacesLeft;
    }
    
    public void setDisplay(String display) {
        this.display = display;
    }
    
    public void setSrc(String src) {
        this.src = src;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }
    
    public int doStartTag() throws JspException {
        /*
        try {
            //Definições
            int          x;
            StringBuffer results = new StringBuffer(30);

            //Monta o div de controle central
            results.append("<div id='" + this.id + "' name='" + this.id + "' style='z-index:998 ;position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(" + this.url + "/resources/images/window_bg.gif); border-width:1px; border-color:#adadad; display:" + this.display + ";'>\n");

            //Monta o div de aplicação dos espaçamentos
            results.append("    <table border='0' cellpadding='0' cellspacing='0'>\n");
            results.append("        <tr>\n");
            results.append("            <td style='height:" + Integer.parseInt(this.spacesTop) + ";' colspan='2'></td>\n");
            results.append("        </tr>\n");
            results.append("        <tr>\n");
            results.append("            <td style='width:" + Integer.parseInt(this.spacesLeft) + ";'></td>\n");
            
            //Monta o cabeçalho
            results.append("            <td>\n");
            results.append("                <table width='" + this.width + "' height='21' cellpadding='0' cellspacing='0' background='/FrontOfficeWeb/resources/images/window_topbg.gif' bgcolor='#0066cb' class='tbl_titulo'>\n");
            results.append("                    <tr>\n");
            results.append("                        <td width='" + (Integer.parseInt(this.width) - 100) + "' height='21'><div id='dv_" + this.id + "'>" + (this.label != null ? this.label : "</div></td>\n"));
            results.append("                        <td width='64' valign='top' background='/FrontOfficeWeb/resources/images/window_topbtbg.gif'>\n");
            results.append("                            <div align='right'>\n");
            results.append("                               <img hspace='3' src='" + this.url + "/resources/images/window_fechar.gif' onclick=\"" +  this.id + ".style.display='none';" + (onclick != null ? onclick : "") + "\" style='cursor:hand;'></td>\n");
            results.append("                            </div>\n");
            results.append("                        </td>\n");
            results.append("                    </tr>\n");
            results.append("                </table>\n");
            results.append("                <table width='" + this.width + "' height='" + this.height + "' border='0' cellpadding='0' cellspacing='1' bgcolor='#adadad'>\n");
            results.append("                    <tr>\n");
            results.append("                        <td bgcolor='#E3ECF4' align='center'>\n");

            //Monta o iframe de preenchimento alimentado pelo usuário
            if( this.idIframe != null )
                results.append("                            <iframe id='" + this.idIframe + "' name='" + this.idIframe + "' scrolling='" + this.scroll + "' height='" + (Integer.parseInt(this.height) - 5) + "' width='" + (Integer.parseInt(this.width) - 5) + "' ");
                // optionally add src attribute
                if ((this.src != null) && (this.src.trim().length() > 0)) {
                    results.append(" src='");
                    String trimSrc = this.src.trim();
                    // if not absolute path ("/something") neither relative based ("../something")
                    if ("/.".indexOf(trimSrc.charAt(0)) == -1) {
                        if ((this.url != null) && (this.url.trim().length() > 0)) {
                            // prepend by context path
                            results.append(this.url).append('/');
                        }
                    }
                    // add relative path
                    results.append(trimSrc);
                    results.append("'");
                }
                results.append(">"); 

            //Retorna informação
            pageContext.getOut().print(results.toString());

            return (EVAL_BODY_INCLUDE);
            
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
        */
        
        try {
            //Definições
            //int          x;
            StringBuffer results = new StringBuffer(30);

            //Monta o div de controle central
            results.append("<div id='");
            results.append(this.id);
            results.append("' name='");
            results.append(this.id);
            results.append("' style='z-index:998 ;position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(");
            results.append(this.url);
            results.append("/resources/images/window_bg.gif); border-width:1px; border-color:#adadad; display:");
            results.append(this.display);
            results.append(";'>\n");

            //Monta o div de aplicação dos espaçamentos
            results.append(" <table border='0' cellpadding='0' cellspacing='0'>\n");
            results.append(" <tr>\n");
            results.append(" <td style='height:");
            results.append(Integer.parseInt(this.spacesTop));
            results.append(";' colspan='2'></td>\n");
            results.append(" </tr>\n");
            results.append(" <tr>\n");
            results.append(" <td style='width:");
            results.append(Integer.parseInt(this.spacesLeft));
            results.append(";'></td>\n");
            
            //Monta o cabeçalho
            results.append(" <td>\n");
            results.append(" <table width='");
            results.append(this.width);
            results.append("' height='21' cellpadding='0' cellspacing='0' background='/FrontOfficeWeb/resources/images/window_topbg.gif' bgcolor='#0066cb' class='tbl_titulo'>\n");
            results.append(" <tr>\n");
            results.append(" <td width='");
            results.append((Integer.parseInt(this.width) - 100));
            results.append("' height='21'><div id='dv_");
            results.append(this.id);
            results.append("'>");
            if (this.label != null)
                results.append(this.label);
            else
                results.append("</div></td>\n");
            results.append(" <td width='64' valign='top' background='/FrontOfficeWeb/resources/images/window_topbtbg.gif'>\n");
            results.append(" <div align='right'>\n");
            results.append(" <img hspace='3' src='");
            results.append(this.url);
            results.append("/resources/images/window_fechar.gif' onclick=\"");
            results.append(this.id);
            results.append(".style.display='none';");
            if (onclick != null)
                results.append(onclick);
            else
                results.append("");
            results.append("\" style='cursor:hand;'></td>\n");
            results.append(" </div>\n");
            results.append(" </td>\n");
            results.append(" </tr>\n");
            results.append(" </table>\n");
            results.append(" <table width='");
            results.append(this.width);
            results.append("' height='");
            results.append(this.height);
            results.append("' border='0' cellpadding='0' cellspacing='1' bgcolor='#adadad'>\n");
            results.append(" <tr>\n");
            results.append(" <td bgcolor='#E3ECF4' align='center'>\n");

            
            //Monta o iframe de preenchimento alimentado pelo usuário
            if( this.idIframe != null )
            {
                results.append(" <iframe id='");
                results.append(this.idIframe);
                results.append("' name='");
                results.append(this.idIframe);
                results.append("' scrolling='");
                results.append(this.scroll);
                results.append("' height='");
                results.append((Integer.parseInt(this.height) - 5));
                results.append("' width='");
                results.append((Integer.parseInt(this.width) - 5));
                results.append("' ");                
            }
            
            // optionally add src attribute
            if ((this.src != null) && (this.src.trim().length() > 0)) 
            {
                results.append(" src='");
                String trimSrc = this.src.trim();
                // if not absolute path ("/something") neither relative based ("../something")
                if ("/.".indexOf(trimSrc.charAt(0)) == -1) 
                {
                    if ((this.url != null) && (this.url.trim().length() > 0)) 
                        // prepend by context path
                        results.append(this.url).append('/');
                }
                // add relative path
                results.append(trimSrc);
                results.append("'");
            }
            results.append(">"); 

            //Retorna informação
            pageContext.getOut().print(results.toString());

            return (EVAL_BODY_INCLUDE);
            
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }

        
        
    }
    
    

    public int doEndTag() throws JspException {

        try {

            StringBuffer results = new StringBuffer(30);
    
            //Monta o fechamento iframe de preenchimento alimentado pelo usuário
            if( this.idIframe != null )
                results.append("</iframe>\n");
    
            //Monta o final da tag
            results.append(" </td>\n");
            results.append(" </tr>\n");
            results.append(" </table>\n");
    
            //Fecha o div de aplicação dos espaçamentos
            results.append(" </td>\n");
            results.append(" </tr>\n");
            results.append(" </table>\n");
            
            //Fecha o div de controle central
            results.append("</div>");

            pageContext.getOut().print(results.toString());
            return (EVAL_PAGE);

        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public void release()
    {
        super.release();
        id             = null;
        idIframe       = null;
        label          = null;
        width          = null;
        height         = null;
        scroll         = null;
        spacesTop      = null;
        spacesLeft     = null;
        display        = null;
        src            = null;
        onclick = null;
    }    
} 
