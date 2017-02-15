package br.com.vivo.fo.fidelizacao.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class TrTag extends TagSupport {
    
	private static final long serialVersionUID = 2260898133361174887L;
private String bgColor;
  private String onMouseOverColor;
  private String onMouseOutColor;
  
  public String getBgColor(){
    if(this.bgColor == null){
        this.bgColor = ConstantesCRM.SVAZIO;
    }
    return this.bgColor;
  }
  
  public void setBgColor(String color){
    this.bgColor = color;
  }
  
  public String getOnMouseOverColor(){
    if(this.onMouseOverColor == null){
        this.onMouseOverColor = ConstantesCRM.SVAZIO;
    }
    return this.onMouseOverColor;
  }
  
  public String getOnMouseOutColor(){
    if(this.onMouseOutColor == null){
        this.onMouseOutColor = getBgColor();
    }
    return this.onMouseOutColor;
  }
  
  public void setOnMouseOutColor(String color){
    this.onMouseOutColor = color;
  }
  
  public void setOnMouseOverColor(String color){
    this.onMouseOverColor = color;
  }

  public int doStartTag() throws JspException {

    try {
        
      StringBuffer htmlAux = new StringBuffer();
      
      if(!ConstantesCRM.SVAZIO.equals(getOnMouseOverColor())){
          htmlAux.append(" onMouseOver=\"this.style.backgroundColor='");
          htmlAux.append(getOnMouseOverColor());
          htmlAux.append("';this.style.cursor='hand';\"");
      
          htmlAux.append(" onMouseOut=\"this.style.backgroundColor='");
          htmlAux.append(getOnMouseOutColor());
          htmlAux.append("'\"");
      }

      pageContext.getOut().print("<tr bgcolor='" + getBgColor() + "'" + htmlAux.toString() + ">");

    } catch (Exception e) {

      throw new JspException("Erro na HeaderTag: " + e.getMessage());

    }

    return (EVAL_BODY_INCLUDE);
  }

  public int doEndTag() throws JspException {

    try {

        pageContext.getOut().print("</tr>");

    } catch (Exception e) {

      throw new JspException("Erro na HeaderTag: " + e.getMessage());

    }

    return (EVAL_PAGE);
  }

}