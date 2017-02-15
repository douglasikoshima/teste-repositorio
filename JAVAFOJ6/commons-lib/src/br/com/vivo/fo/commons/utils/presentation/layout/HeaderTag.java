package br.com.vivo.fo.commons.utils.presentation.layout;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class HeaderTag extends TagSupport
{

	private static final long serialVersionUID = -7704300463892050619L;
//	private String text      = null;
//    private String id        = null;
    private String idMenuMin = null;
    private String idMenuMax = null;
    private String value     = null;
    private String width     = null;
    private String idMin     = null;
    private String idMax     = null;
    private String reqMin     = null;

    public HeaderTag() {
        super();
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setIdMenuMin(String idMenuMin) {
        this.idMenuMin = idMenuMin;
    }
    
    public void setidMenuMax(String idMenuMax) {
        this.idMenuMax = idMenuMax;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public void setWidth(String width) {
        this.width = width;
    }
    
    public void setReqMin(String reqMin) {
        this.reqMin = reqMin;
    }

    public void setIdMin(String idMin) {
        this.idMin = idMin;
    }
    
    public void setIdMax(String idMax) {
        this.idMax = idMax;
    }

    public int doStartTag() throws JspException {
        StringBuffer results = new StringBuffer(30);

        results.append("<table border='0' cellpadding='0' cellspacing='0'");
        
        if(this.width != null)
            results.append(" width=\"" + this.width + "\"");
            
        results.append(">\n");
        results.append("    <tr>\n");
        results.append("        <td>\n");

        results.append("    <table id='" + idMenuMin + "' width='100%' height='22' border='0' cellpadding='0' cellspacing='0' background='/FrontOfficeWeb/resources/images/hdr_sml_bgctrl.gif' style='display:none;'>\n");
        results.append("    	<tr>\n");
        results.append("    		<td width='14' valign='top' background='/FrontOfficeWeb/resources/images/hdr_sml_bgesq.gif'><div align='right'><img id='hdr_button' src='/FrontOfficeWeb/resources/images/hdr_bt_open.gif' width='11' height='12' hspace='2' style='cursor:hand' vspace='5' onclick='mudarHeader(" + idMenuMin + ", " + idMenuMax + ");'></div></td>\n");
        results.append("    		<td width='2' background='/FrontOfficeWeb/resources/images/hdr_big_bgsepara.gif'><img src='/FrontOfficeWeb/resources/images/transp.gif' width='2' height='2'></td>\n");
        results.append("    		<td width='248'><img id='ddfd' src='/FrontOfficeWeb/resources/images/hdr_sml_logovivo.gif' width='34' height='22'></td>\n");
        results.append("    		<td width='224'><img src='/FrontOfficeWeb/resources/images/hdr_sml_logofo.gif' width='76' height='22'></td>\n");
        results.append("    		<td width='263' class='hdr_sml_subtitulo' id="+ idMin + ">" + value + "</td>\n");
        if ((reqMin != null) && (reqMin.length()>0))
            results.append("    		<td width='34' background='/FrontOfficeWeb/resources/images/hdr_sml_bgdir.gif' valign='middle' align='center'></td>\n");
        else
            results.append("    		<td width='34' background='/FrontOfficeWeb/resources/images/hdr_sml_bgdir.gif' valign='middle' align='center'><img src='/FrontOfficeWeb/resources/images/hdr_sml_minim.gif' onclick='mudarHeader(" + idMenuMin + ", " + idMenuMax + ");' style='cursor:hand' align='left'><img src='/FrontOfficeWeb/resources/images/hdr_sml_close.gif' onclick='top.frameApp.fecharSessao();' style='cursor:hand'></td>\n");
        results.append("    	</tr>\n");
        results.append("    </table>\n");

        results.append("    <table id='" + idMenuMax + "' width='100%' height='58' border='0' cellpadding='0' cellspacing='0' background='/FrontOfficeWeb/resources/images/hdr_big_bgctrl.gif' style='display:;'>\n");
        results.append("        <tr>\n");
        results.append("            <td width='13' valign='top' background='/FrontOfficeWeb/resources/images/hdr_big_bgesq.gif'><div align='right'><img src='/FrontOfficeWeb/resources/images/hdr_bt_close.gif' width='9' height='12' hspace='1' vspace='7' id='hdr_button' style='cursor:hand' onclick='mudarHeader(" + idMenuMin + ", " + idMenuMax + ");'></div></td>\n");
        results.append("            <td width='4' background='/FrontOfficeWeb/resources/images/hdr_big_bgsepara.gif'><img src='/FrontOfficeWeb/resources/images/transp.gif' width='4' height='1'></td>\n");
        results.append("            <td width='247'><img id='ddfd' src='/FrontOfficeWeb/resources/images/hdr_big_logovivo.gif' width='86' height='58'></td>\n");
        results.append("            <td width='215' valign=\"middle\" align=\"center\"><img src='/FrontOfficeWeb/resources/images/hdr_big_logofo.gif' width='92' height='58'></td>\n");
        results.append("            <td width='241' class='hdr_big_subtitulo'><center><span valign=middle id="+ idMax + ">" + value + "</span></center></td>\n");
        if ((reqMin != null) && (reqMin.length()>0))
            results.append("            <td width='60' style='text-align:right; vertical-align:top;'></td>\n");
        else
            results.append("            <td width='60' style='text-align:right; vertical-align:top;'><img src='/FrontOfficeWeb/resources/images/hdr_big_minim.gif' vspace='9' onclick='mudarHeader(" + idMenuMin + ", " + idMenuMax + ");'  style='cursor:hand' align='left'><img src='/FrontOfficeWeb/resources/images/hdr_big_close.gif' vspace='9' onclick='top.frameApp.fecharSessao();'  style='cursor:hand'></td>\n");
        results.append("            <td width='10' background='/FrontOfficeWeb/resources/images/hdr_big_bgdir.gif'></td>\n");
        results.append("        </tr>\n");

        try {
            pageContext.getOut().print(results.toString());
            
            return (EVAL_BODY_INCLUDE);
            
        } catch(IOException _ex) {
            throw new JspTagException("Fatal error: the tag menu could not write to JSP out.");
        }
    }

    public int doEndTag() throws JspException {
        StringBuffer results = new StringBuffer(30);
        results.append("    </table>\n");
        results.append("                </tr>\n");
        results.append("            </table>\n");
        results.append("        </td>\n");
        results.append("    </tr>\n");
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
//        text      = null;
        idMenuMin = null;
        idMenuMax = null;
        value     = null;
        width     = null;
    }
}