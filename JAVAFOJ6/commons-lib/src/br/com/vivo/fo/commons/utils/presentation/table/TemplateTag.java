package br.com.vivo.fo.commons.utils.presentation.table;

import javax.servlet.jsp.JspException;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class TemplateTag extends RowTag {

	private static final long serialVersionUID = 8090387950675642277L;
  private String name = null;
 
  protected void printHTML() throws JspException {
    	
    try {

      StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);
      HTML.append("            <tr id=\"" + name + "\" status=\"incluido\">\n");

      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na TemplateTag/printHTML: " + e.getMessage());

    }

  }

  //-=-=-=-=-=-=-=-=-=-=-=-=- ATRIBUTOS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=

  // Atributo name

  public void setName(String sValue) {

    name = sValue;

  }

  public String getName() {

    return name;

  }


}