package br.com.vivo.fo.commons.utils.presentation.script;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.FOPageFlowBusinessLogger;

public class BusinessLog extends TagSupport {

	private static final long serialVersionUID = -8208133112972194L;
    private String requestAttribute = ConstantesCRM.SVAZIO;
    private boolean inResetWindow = false;
    private boolean inAjaxCall = false;
    private FOPageFlowBusinessLogger businnesLog;

    public void setRequestAttribute(String requestAttribute) {
        this.requestAttribute = requestAttribute;
    }

    private String getRequestAttribute() {
        if (this.requestAttribute == ConstantesCRM.SVAZIO) {
            return null;
        }
        return this.requestAttribute;
    }

    public void setInResetWindow(boolean arg1) {
        this.inResetWindow = arg1;
    }

    private boolean getInResetWindow() {
        return this.inResetWindow;
    }

    public void setInAjaxCall(boolean arg1) {
        this.inAjaxCall = arg1;
    }

    private boolean getInAjaxCall() {
        return this.inAjaxCall;
    }

    public void setBusinnesLog(FOPageFlowBusinessLogger businnesLog) {
        this.businnesLog = businnesLog;
    }

    private FOPageFlowBusinessLogger getBusinnesLog() {
        if (((HttpServletRequest) pageContext.getRequest()).getAttribute(getRequestAttribute()) == null) {
            return null;
        }
        this.businnesLog = (FOPageFlowBusinessLogger) ((HttpServletRequest) pageContext.getRequest()).getAttribute(getRequestAttribute());
        return this.businnesLog;
    }

    public BusinessLog() {
        super();
    }

    public int doStartTag() throws JspException {

        try {

            if (getRequestAttribute() == null || 
                !"indrafo".equals(((HttpServletRequest) pageContext.getRequest()).getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)) 
                && !"adminfo".equals(((HttpServletRequest) pageContext.getRequest()).getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN))) {
                return EVAL_BODY_INCLUDE;
            }

            if (getBusinnesLog() != null) {

                StringBuffer htmlOUT = new StringBuffer()
                    .append("<script type=\"text/javascript\" language=\"javascript\">")
                    .append("    if (typeof(Prototype)) {");
                if (!getInAjaxCall()) {
                    htmlOUT.append("        document.observe(\"dom:loaded\", function() {");
                }
                htmlOUT.append("                if ($('debugWindowContent')) {");

                if (getInResetWindow()) {
                    htmlOUT.append("                $('debugWindowContent').value = \"");
                } else {
                    htmlOUT.append("                $('debugWindowContent').value = $F('debugWindowContent') + \"\\n")
                        .append("----------------------------------------------------------------------------------")
                        .append("\\n");
                }

                StringBuffer saidaHTML = new StringBuffer();

                String data;
                String chave;
                String valor;

                Iterator it = getBusinnesLog().getMapLogger().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                    chave = (String) pairs.getKey();
                    data  = (String) pairs.getValue();
                    data  = data.substring(0, data.indexOf("]") + 1);
                    valor = (String) pairs.getValue();
                    valor = (valor.substring(valor.indexOf("]") + 1, valor.length())).replaceAll("\"","\\'").replaceAll("\\n|\\r|  ","");

                    saidaHTML.append(data)
                        .append("\\n").append(chave)
                        .append(" = ").append(valor)
                        .append("\\n\\n");
                }

                htmlOUT.append(saidaHTML.toString());

                JspWriter out = pageContext.getOut();
                out.write(htmlOUT.toString());
            }

        } catch(IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }
    
    public int doEndTag() throws JspTagException, JspException {

        try {
            StringBuffer htmlOUT = new StringBuffer(ConstantesCRM.SVAZIO);

            if (getRequestAttribute() != null && getBusinnesLog() != null
                && ("indrafo".equals(((HttpServletRequest) pageContext.getRequest()).getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)) 
                    || "adminfo".equals(((HttpServletRequest) pageContext.getRequest()).getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)))) {

                htmlOUT.append("\";")
                    .append("            }")
                    .append("            if ($('debugWindowBody')) {");

                htmlOUT.append("                $('debugWindowBody').value = document.body.innerHTML;");

                htmlOUT.append("            }");
                if (!getInAjaxCall()) {
                    htmlOUT.append("    });");
                }
                htmlOUT.append("    }")
                    .append("</script>");
            }

            pageContext.getOut().print(htmlOUT.toString());

        } catch (Exception e) {
            throw new JspException("Erro na TagSupport: " + e.getMessage());
        }

        return EVAL_PAGE;
    }

}