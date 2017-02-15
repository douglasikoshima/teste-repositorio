package br.com.vivo.fo.commons.utils.presentation.script;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class Alert extends TagSupport
{ 

	private static final long serialVersionUID = -9208185934112971094L;
	private String scope = "request";
    private String atributo = ConstantesCRM.SVAZIO;
    private String afterFunction = ConstantesCRM.SVAZIO;
    
    public void setScope(String scope) {
        this.scope = scope;        
    }
    
    public void setAtributo(String atributo) {
        this.atributo = atributo;        
    }
    
    public void setAfterFunction(String afterFunction) {
        this.afterFunction = afterFunction;
    }

    
    public Alert()
    {
        super();
    }       
    
    public int doStartTag() throws JspException
    {
        try
        {
            String mensagem;
            //String funcao = "";
            if ( "request".equals( this.scope ) )
            {
                mensagem = (String) ((HttpServletRequest)pageContext.getRequest()).getAttribute(this.atributo);
            }
            else if ( "session".equals( this.scope ) )
            {
                mensagem = (String) pageContext.getSession().getAttribute(this.atributo);
            }
            else
            {
                throw new JspTagException("Scope invalido!");    
            }
            
            if ( mensagem != null && ! mensagem.equals(ConstantesCRM.SVAZIO) ) {
                
                JspWriter out = pageContext.getOut();
                if ( this.afterFunction != null && ! this.afterFunction.equals(ConstantesCRM.SVAZIO) ) {
                    out.write("<script>alert(\"" + mensagem + "\");"+this.afterFunction.toString()+"  </script>");
                } else {
                    out.write("<script>alert(\"" + mensagem + "\");  </script>");
                }
                
            }
            
        }
        catch(IOException e)
        {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }
    
    
    public int doEndTag()
        throws JspTagException, JspException
    {
        release();
        return EVAL_PAGE;
    }
    
    public void release()
    {
        this.scope = "request";
        this.afterFunction = ConstantesCRM.SVAZIO;
        this.atributo = ConstantesCRM.SVAZIO;
        super.release();
    }
    
} 
