package br.com.vivo.fo.commons.utils.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class HeaderTag extends TagSupport {

	private static final long serialVersionUID = -6886137458789087196L;
private TableTag parentTable = null;
  private int cellIndex = -1;

  // Esta Tag n?o tem atributos

  public HeaderTag() {
    super();
  }

  public int doStartTag() throws JspException {

    try {
      cellIndex = -1;
      StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

      parentTable = (TableTag) TagSupport.findAncestorWithClass(this, TableTag.class);

      HTML.append("    <tr>\n");
      
      HTML.append("      <td>\n");
      
      HTML.append(
        "        <div class=\"headerTabela\" style=\"width: "
          + (parentTable.layoutWidth)
          + "px; overflow: hidden\">\n");
          
     int width = 0;
     try{
        width = Integer.parseInt(parentTable.tableWidth);
        width += 16;
     }catch(NumberFormatException nfe){
        nfe.printStackTrace();
     }
          
      HTML.append(
        "          <table tbheader=\""+parentTable.getStyleId()+"\" id=\"" + parentTable.getStyleId() + "_header\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\""
          + width
          + "\">\n");
          
      HTML.append("            <tr>\n");

      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na HeaderTag: " + e.getMessage());

    }

    return (EVAL_BODY_INCLUDE);
  }

  public int doEndTag() throws JspException {

    try {

      StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

      if( parentTable.getExpireButton().equals("true") ) {
        HTML.append("              <td><div style=\"visibility: hidden;\" class=\"btnExpiraLinha\" title=\"" + parentTable.getExpireTip() + "\" /></td>");
      }

      if( parentTable.getDeleteButton().equals("true") ) {
        HTML.append("              <td><div style=\"visibility: hidden;\" class=\"btnRemoveLinha\" title=\"" + parentTable.getDeleteTip() + "\" /></td>");
      }
      HTML.append("<td bgcolor=\"#F7F9FA\" width=\"16\"><img src=\"/FrontOfficeWeb/resources/images/pixel.gif\" width=\"16px\" height=\"1\"/></td>\n");
      HTML.append("            </tr>\n");
      HTML.append("          </table>\n");
      HTML.append("        </div>\n");
      HTML.append("      </td>\n");
      HTML.append("    </tr>\n");

      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na HeaderTag: " + e.getMessage());

    }

    return (EVAL_PAGE);
  }

  //-=-=-=-=-=-=-=-=-=-=-=-=- METODOS PARTICULARES -=-=--=-=-=-=-=-=-

  protected int getNewCellIndex(){
  	
  	cellIndex++;
  	return cellIndex;
  	
  }

  //-=-=-=-=-=-=-=-=-=-=-=-=- ATRIBUTOS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=

  // Esta Tag n?o tem atributos

}