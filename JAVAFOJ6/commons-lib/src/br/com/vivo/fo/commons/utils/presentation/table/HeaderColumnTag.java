package br.com.vivo.fo.commons.utils.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class HeaderColumnTag extends TagSupport {

	private static final long serialVersionUID = -6850904699275317654L;
	private String width = null;
	private String name = "notNamed";
	private String align = "left";
	private String type = ConstantesCRM.SVAZIO;

  public HeaderColumnTag() {
    super();
  }

  public int doStartTag() throws JspException {

    try {

      TableTag parentTable = null;
      HeaderTag parentHeader = null;

	  parentHeader = (HeaderTag) TagSupport.findAncestorWithClass(this, HeaderTag.class);
	  
      parentTable = (TableTag) TagSupport.findAncestorWithClass(this, TableTag.class);

	  String sortable = "onclick=\"if( document.all['" + parentTable.getStyleId() + "_body'] != null ) ordenarTabelaEx( " + parentTable.getStyleId() + "_body, " + parentHeader.getNewCellIndex() + ", '" + type + "');\" onMouseOver=\"hoverHeader();\" onMouseOut=\"unhoverHeader();\"";

      //Escreve Cabecalho

      pageContext.getOut().print(
        "              <td class=\"normal\" " + ( ( parentTable.getSortable().equals("true") && !type.equals("") ) ? sortable : "" ) + " width=\"" + width + "\" nome=\"" + name + "\">\n");

      //Repassa (sobe) dados das colunas


      parentTable.addHeaderColumnInfo(width, name, align);

    } catch (Exception e) {

      throw new JspException("Erro na headerColumn: " + e.getMessage());

    }

    return (EVAL_BODY_INCLUDE);
  }

  public int doEndTag() throws JspException {

    try {

      pageContext.getOut().print("              </td>\n");

    } catch (Exception e) {

      throw new JspException("Erro na TagSupport: " + e.getMessage());

    }

    return (EVAL_PAGE);
  }

  //-=-=-=-=-=-=-=-=-=-=-=-=- METODOS PARTICULARES -=-=--=-=-=-=-=-=-

  //-=-=-=-=-=-=-=-=-=-=-=-=- ATRIBUTOS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=

  // Atributo width

  public void setWidth(String sValue) {

    width = sValue;

  }

  public String getWidth() {

    return width;

  }

  // Atributo name

  public void setName(String sValue) {

    name = sValue;

  }

  public String getName() {

    return name;

  }

  // Atributo align

  public void setAlign(String sValue) {

    align = sValue;

  }
  
  public String getAlign() {

    return align;

  }


	/**
	 * Gets the type
	 * @return Returns a String
	 */
	public String getType() {
		return type;
	}
	/**
	 * Sets the type
	 * @param type The type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}