package br.com.vivo.fo.commons.utils.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class RowsTag extends TagSupport {

	private static final long serialVersionUID = -4279317082918246974L;
public String scroll = ConstantesCRM.SVAZIO;
  public String classStyle = "corpoTabela"; // atributo (nao requerido) para alterar o estilo do div/tabela - ppaula 10/05/2005

  public RowsTag() {
    super();
  }

  public int doStartTag() throws JspException {
    try {

      StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);
      TableTag parentTable = null;

      parentTable = (TableTag) TagSupport.findAncestorWithClass(this, TableTag.class);

      String onClick = ( (parentTable.getSelectable().equals("true") ) ? " onClick=\"selecionarLinha();\"" : ConstantesCRM.SVAZIO ) ;

      HTML.append("    <tr>\n");
      HTML.append("      <td>\n");
      if ((scroll == null) || (scroll.length()==0))
          HTML.append(
            "        <div id=\"" + parentTable.getStyleId() + "_div\" class=\""+ getClassStyle() +"\" style=\"width: "
              + parentTable.layoutWidth
              + "px; height: "
              + parentTable.layoutHeight
              + "px; overflow: auto\" onScroll=\"sincronizarScrollLateral( this.parentElement.parentElement.parentElement.parentElement.parentElement ) \">\n");
	  else
          HTML.append(
            "        <div id=\"" + parentTable.getStyleId() + "_div\" class=\"corpoTabela\" style=\"width: "
              + parentTable.layoutWidth
              + "px; height: "
              + parentTable.layoutHeight
              + "px; overflow-x: hidden; overflow-y: auto\" onScroll=\"sincronizarScrollLateral( this.parentElement.parentElement.parentElement.parentElement.parentElement ) \">\n");
      if ( parentTable.getSelectable().equals("lista"))
          HTML.append(
            "          <table id=\"" + parentTable.getStyleId() + "_body\" class=\"selecionavel\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"" + parentTable.tableWidth
              + "\"" );
      else
          HTML.append(
            "          <table id=\"" + parentTable.getStyleId() + "_body\" class=\"" + ( ( parentTable.getSelectable().equals("true") ) ? "selecionavel" : "normal" ) + "\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\""
              + parentTable.tableWidth
              + "\"" + onClick );
          
      if( parentTable.getSelectable().equals("true") ) {
	      HTML.append(" onMouseOver=\"hoverRow();\" onMouseOut=\"unhoverRow();\"");
      }
      
      HTML.append(">\n");

      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na RowsTag: " + e.getMessage());

    }

    return (EVAL_BODY_INCLUDE);
  }

  public int doEndTag() throws JspException {
    try {

      StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

      HTML.append("          </table>\n");
      HTML.append("        </div>\n");
      HTML.append("      </td>\n");
      HTML.append("    </tr>\n");

      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na RowsTag: " + e.getMessage());

    }

    return (EVAL_PAGE);
  }

  //-=-=-=-=-=-=-=-=-=-=-=-=- METODOS PARTICULARES -=-=--=-=-=-=-=-=-
	public String getScroll() {
		return this.scroll;
	}
	/**
	 * Sets the sortable
	 * @param sortable The sortable to set
	 */
	public void setScroll(String scroll) {
		this.scroll = scroll;
	}
    
    
    public String getClassStyle() {
		return this.classStyle;
	}
	/**
	 * Sets the div style
	 * @param classStyle The class style to set
	 */
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

  //-=-=-=-=-=-=-=-=-=-=-=-=- ATRIBUTOS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=

  // Esta Tag n?o tem atributos

}