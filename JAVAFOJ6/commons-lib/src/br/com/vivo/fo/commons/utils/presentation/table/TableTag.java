package br.com.vivo.fo.commons.utils.presentation.table;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class TableTag extends TagSupport {

	private static final long serialVersionUID = 6395734233318059619L;
private List lstWidth;
  private List lstName;
  private List lstAlign;

  //Atributos

  //obrigatorios
  public String layoutHeight = null;
  public String layoutWidth = null;
  public String tableWidth = null;
  private String styleId = null;

  //opcionais
  public String sortable = ConstantesCRM.SVAZIO;
  public String expireButton = ConstantesCRM.SVAZIO;
  public String deleteButton = ConstantesCRM.SVAZIO;
  public String expireTip = ConstantesCRM.SVAZIO;
  public String deleteTip = ConstantesCRM.SVAZIO;
  public String selectable = ConstantesCRM.SVAZIO;
  public String onRowClick = ConstantesCRM.SVAZIO;
  public String resize = ConstantesCRM.SVAZIO;

  public TableTag() {
    super();
    lstWidth = new ArrayList();
    lstName = new ArrayList();
    lstAlign = new ArrayList();
  }

  public int doStartTag() throws JspException {

    try {

      StringBuffer HTML = new StringBuffer("");

      // Inicio
      if ((resize == null) || (resize.length()==0))
        HTML.append("<SCRIPT for=document event=onreadystatechange LANGUAGE=\"JScript\">DoResizeHeaderTableVivo();</SCRIPT>");
      HTML.append(
        "<div class=\"holderTabela\" id=\"" + styleId + "\""
          + " style=\"width: 1px; height: 1px; overflow: visible\">\n");
          
      HTML.append("  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n");

      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na tableTag: " + e.getMessage());

    }

    return (EVAL_BODY_INCLUDE);
  }

  public int doEndTag() throws JspException {

    try {

      StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

      // Fecha Inicio 

      HTML.append("  </table>\n");
      HTML.append("</div>\n");      
      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na tableTag: " + e.getMessage());

    }

    return (EVAL_PAGE);
  }

  //-=-=-=-=-=-=-=-=-=-=-=-=- METODOS PARTICULARES -=-=--=-=-=-=-=-=-

  public void addHeaderColumnInfo(String psWidth, String psName, String psAlign) {

    lstWidth.add(psWidth);
    lstName.add(psName);
    lstAlign.add(psAlign);

  }

  public String getColumnWidth(int piIndex) {

    return (String) lstWidth.get(piIndex);

  }

  public String getColumnName(int piIndex) {

    return (String) lstName.get(piIndex);

  }

  public String getColumnAlign(int piIndex) {

    return (String) lstAlign.get(piIndex);

  }

  //-=-=-=-=-=-=-=-=-=-=-=-=- ATRIBUTOS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=

  // Atributo layoutHeight

  public void setLayoutHeight(String sValue) {

    layoutHeight = sValue;

  }

  public String getLayoutHeight() {

    return layoutHeight;

  }

  // Atributo layoutWidth

  public void setLayoutWidth(String sValue) {

    layoutWidth = Integer.toString(Integer.parseInt(sValue) + 16);

  }

  public String getLayoutWidth() {

    return Integer.toString(Integer.parseInt(layoutWidth) - 16);

  }

  // Atributo tableWidth

  public void setTableWidth(String sValue) {

    tableWidth = sValue;

  }

  public String getTableWidth() {

    return tableWidth;

  }

  // Atributo expireButton

  public void setExpireButton(String sValue) {

    expireButton = sValue;

  }

  public String getExpireButton() {

    return expireButton;

  }

  // Atributo deleteButton

  public void setDeleteButton(String sValue) {

    deleteButton = sValue;

  }

  public String getDeleteButton() {

    return deleteButton;

  }

  // Atributo expireTip

  public void setExpireTip(String sValue) {

    expireTip = sValue;

  }

  public String getExpireTip() {

    return expireTip;

  }

  // Atributo deleteTip

  public void setDeleteTip(String sValue) {

    deleteTip = sValue;

  }

  public String getDeleteTip() {

    return deleteTip;

  }

  // Atributo styleId

  public void setStyleId(String sValue) {

    styleId = sValue;

  }

  public String getStyleId() {

    return styleId;

  }

  // Atributo selectable

  public void setSelectable(String sValue) {

    selectable = sValue;

  }

  public String getSelectable() {

    return selectable;

  }

  // Atributo onRowClick

  public void setOnRowClick(String sValue) {

    onRowClick = sValue;

  }

  public String getOnRowClick() {

    return onRowClick;

  }

	/**
	 * Gets the sortable
	 * @return Returns a String
	 */
	public String getSortable() {
		return sortable;
	}
	/**
	 * Sets the sortable
	 * @param sortable The sortable to set
	 */
	public void setSortable(String sortable) {
		this.sortable = sortable;
	}
	/**
	 * Gets the sortable
	 * @return Returns a String
	 */
	public String getResize() {
		return resize;
	}
	/**
	 * Sets the sortable
	 * @param sortable The sortable to set
	 */
	public void setResize(String resize) {
		this.resize = resize;
	}

}