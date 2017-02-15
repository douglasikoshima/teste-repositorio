package br.com.vivo.fo.commons.utils.presentation.botao; 

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class AbaItemTag extends TagSupport { 

	private static final long serialVersionUID = -6550099882045278468L;
	private String id         = null; 
    private String onclick    = null;
    private String value      = null;
    private String select     = null;
    private String width      = null; 
    public AbaItemTag() {
        super();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public void setSelect(String select) {
        this.select = select;
    }
    
    public void setWidth(String width) {
        this.width = width;
    }
    
    private void setClick(){
        if(onclick == null){
            return;
        }
        int size = onclick.length();
        for(int i=0;i<size;i++){
            if(i+1 == size){
                if(onclick.charAt(i) == ';'){
                    onclick += " DoResizeHeaderTableVivo();";   
                }else{
                    onclick += "; DoResizeHeaderTableVivo();";
                }                
            }
        }
    }

    public int doStartTag() throws JspException {
        StringBuffer results = new StringBuffer();
        
        this.setClick();

        String widthStr = ConstantesCRM.SVAZIO;
        if (this.width != null) {
            widthStr = " width = "+width;
        }    

        if( select != null && (select.trim().substring(0,1).equalsIgnoreCase("S") || select.trim().substring(0,1).equalsIgnoreCase("Y")) ) {
            results.append("        <td width='9'><img id='AbaLeft_" + id + "' src='/FrontOfficeWeb/resources/images/aba_left.gif' width='9' height='16'></td>\n");
            results.append("        <td id='" + id + "' onclick=\"" + onclick +"\" background='/FrontOfficeWeb/resources/images/aba_bkg.gif' class='abaSelected' height='16'"+widthStr+">" + value + "</td>\n");
            results.append("        <td width='9'><img id='AbaRight_" + id + "' src='/FrontOfficeWeb/resources/images/aba_right.gif' width='9' height='16'></td>\n");
        } else {
            results.append("        <td width='9'><img id='AbaLeft_" + id + "' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>\n");
            results.append("        <td id='" + id + "' onclick=\"" + onclick +"\" style='cursor:hand;' class='abaUnselected' height='16'"+widthStr+">" + value + "</td>\n");
            results.append("        <td width='9'><img id='AbaRight_" + id + "' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>\n");
        }

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
        onclick    = null;
        value      = null;
    }    
} 
