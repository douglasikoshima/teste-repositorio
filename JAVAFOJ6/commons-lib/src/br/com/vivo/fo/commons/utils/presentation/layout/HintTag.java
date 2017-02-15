package br.com.vivo.fo.commons.utils.presentation.layout;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

@SuppressWarnings({ "deprecation" })
public class HintTag extends BodyTagSupport {

    private static final long serialVersionUID = -7286380474805158854L;
    private int               maxLength;
    private String            spaces           = "N";

    public HintTag() {

    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public String getSpaces() {
        return this.spaces;
    }

    public void setSpaces(String spaces) {
        this.spaces = spaces;
    }

    public void setMaxLength(String maxLength) throws NumberFormatException {
        try {
            this.maxLength = Integer.parseInt(maxLength);
            if (this.maxLength <= 0) {
                throw new NumberFormatException("Parametro maxLength deve ser maior que zero.");
            }
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Parametro maxLength nao numerico.");
        }
    }

    public int doStartTag() throws JspException {
        StringBuffer result = new StringBuffer();
        try {
            pageContext.getOut().print(result.toString());

            return (EVAL_BODY_TAG);

        } catch (IOException _ex) {
            throw new JspTagException("Erro ao Iniciar a HintTag");
        }
    }

    public int doEndTag() throws JspException {
        try {
            StringBuffer result = new StringBuffer(ConstantesCRM.SVAZIO);
            BodyContent bc = this.getBodyContent();
            Reader reader = bc.getReader();
            final int LEN = 1;
            int i = 0;
            char[] buffer = new char[LEN];
            while ((i = reader.read(buffer)) != -1) {
                String str = new String(buffer);
                result.append(str);
                buffer = new char[LEN];
            }
            String texto = String.valueOf(result).trim();
            String[] strSpc = texto.split(" ");
            boolean toDecode = false;
            if (spaces != null && spaces.equals("S")) {
                for (int j = 0; j < strSpc.length; j++) {
                    if (maxLength < strSpc[j].length()) {
                        toDecode = true;
                        break;
                    }
                }
            } else {
                toDecode = true;
            }
            if (toDecode) {
                pageContext.getOut().print(getFormatedString(texto));
            } else {
                pageContext.getOut().print(texto);
            }
            this.release();
        } catch (Exception e) {

            throw new JspException("Erro na HintTag: " + e.getMessage());

        }
        return (EVAL_PAGE);
    }

    private String decodeString(String buffer) {
        buffer = buffer.replaceAll("\"", "&quot;");
        return buffer;
    }

    private String getFormatedString(String buffer) {
        if (buffer == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            if (getMaxLength() > buffer.length()) {
                return buffer;
            } else {
                String text = decodeString(buffer);
                String formatado = text.replaceAll("<span>", ConstantesCRM.SVAZIO);
                return ("<span title=\"" + formatado.replaceAll("</span>", ConstantesCRM.SVAZIO) + "\" class=\"PTHintTable\">"
                        + buffer.substring(0, getMaxLength()) + "&nbsp;<b>...</b></span>");
            }
        }
    }

}
