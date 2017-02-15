package br.com.vivo.fo.commons.utils.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class RowTag extends TagSupport {

    private static final long serialVersionUID = -1677453270140434912L;
    private int colIndex = -1;
    private static int zebrarID = 1;
    private static final String zebradoClass[]  = {"rowTabelaZebradoOn", "rowTabelaZebradoOff", "rowTabela"};   //"Zebrado ON", "Zebrado OFF", "Normal"
    private TableTag parentTable = null;
    
    //Atributos
    private String zebrar  = null;
    private String id  = null;
    private String idClass = null;
    private String title   = null;
    private String onRowClick = null;
    private String onMouseOver = null;
    private String onMouseOut = null;
    
    //obrigatorios
    private String key = null;
    
    public RowTag() {
    super();
    }
  
    protected void printHTML() throws JspException {
            
        try {

            StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

            HTML.append("            <tr id=" + id + " chave=\"" + key + "\" status=\"consulta\"");

            String rowClickStript = parentTable.getOnRowClick();
            if (getOnRowClick() != null) {
            rowClickStript = getOnRowClick();
            }    

            if( !rowClickStript.equals(ConstantesCRM.SVAZIO) ) {
              HTML.append(" onClick=\"" + rowClickStript + "\"");
            }

            if (!ConstantesCRM.SVAZIO.equals(getOnMouseOver())) {
                HTML.append(" onmouseover=\"" + getOnMouseOver() + "\"");
            }

            if (!ConstantesCRM.SVAZIO.equals(getOnMouseOut())) {
                HTML.append(" onmouseout=\"" + getOnMouseOut() + "\"");
            }

            //Verifica a zembra do formulário
            if( (zebrar != null) && zebrar.trim().substring(0,1).equalsIgnoreCase("S") ) {
                if( zebrarID == 0 ) zebrarID = 1;
                else                zebrarID = 0;
            
                //Plota a classe para zebrado on/off
                HTML.append(" class='" + (zebrarID == 0 ? zebradoClass[0] : zebradoClass[1]) + "' zebrado='S'");
            
            //Verifica o formulário sem zembra
            } else if( idClass == null ) {
                //Plota a classe default
                HTML.append(" class='" + zebradoClass[2] + "'");
            
            } else {
                //Plota a classe informada pelo usuário
                HTML.append(" class='" + idClass + "'");
            }
            
            
            if( title != null )
            HTML.append(" title='" + title + "'");
            
            //Fecha a tag
            HTML.append(">\n");
            
            pageContext.getOut().print(HTML.toString());
            
        } catch (Exception e) {
    
            throw new JspException("Erro na RowTag/printHTML: " + e.getMessage());
    
        }
    
    }

  public int doStartTag() throws JspException {
    try {

      parentTable = (TableTag) TagSupport.findAncestorWithClass(this, TableTag.class);

      printHTML();
      
    } catch (Exception e) {

      throw new JspException("Erro na RowTag: " + e.getMessage());

    }

    return (EVAL_BODY_INCLUDE);
  }

  public int doEndTag() throws JspException {
    try {

      StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

      if( parentTable.getExpireButton().equals("true") ) {
        HTML.append("              <td><div class=\"btnExpiraLinha\" title=\"" + parentTable.getExpireTip() + "\" onclick=\"excluido();\"/></td>");
      }

      if( parentTable.getDeleteButton().equals("true") ) {
        HTML.append("              <td><div class=\"btnRemoveLinha\" title=\"" + parentTable.getDeleteTip() + "\" onclick=\"removerLinha( this.parentElement.parentElement );\"/></td>");
      }

      HTML.append("            </tr>\n");

      pageContext.getOut().print(HTML.toString());

    } catch (Exception e) {

      throw new JspException("Erro na RowTag: " + e.getMessage());

    }

    return (EVAL_PAGE);
  }

  //-=-=-=-=-=-=-=-=-=-=-=-=- METODOS PARTICULARES -=-=--=-=-=-=-=-=-

    public String getCurrentColumnWidth() {
        return parentTable.getColumnWidth(colIndex);
    }
    
    public String getCurrentColumnName() {
        return parentTable.getColumnName(colIndex);
    }
    
    public String getCurrentColumnAlign() {
        return parentTable.getColumnAlign(colIndex);
    }
    
    public void registerNewColumn() {
        colIndex++;
    }

  //-=-=-=-=-=-=-=-=-=-=-=-=- ATRIBUTOS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public void setKey(String sValue) {
        key = sValue;
        colIndex = -1;
    }

    public String getKey() {
        return key;
    }

    public void setZebrar(String zebrar) {
        this.zebrar = zebrar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        if (this.id == null) this.id = ConstantesCRM.SVAZIO;
        return this.id;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOnRowClick() {
        return this.onRowClick;
    }

    public String getOnMouseOver() {
        if (this.onMouseOver == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.onMouseOver;
        }
    }

    public String getOnMouseOut() {
        if (this.onMouseOut == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.onMouseOut;
        }
    }

    public void setOnRowClick(String onRowClick) {
        this.onRowClick = onRowClick;
    }

    public void setOnMouseOver(String onMouseOver) {
        this.onMouseOver = onMouseOver;
    }

    public void setOnMouseOut(String onMouseOut) {
        this.onMouseOut = onMouseOut;
    }

}