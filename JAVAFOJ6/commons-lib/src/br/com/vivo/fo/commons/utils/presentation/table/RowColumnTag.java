package br.com.vivo.fo.commons.utils.presentation.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class RowColumnTag extends TagSupport {

	private static final long serialVersionUID = -404997667025517260L;
//opcionais
  public String key = ConstantesCRM.SVAZIO;

	public RowColumnTag() {
		super();
	}

	public int doStartTag() throws JspException {

		try {

			StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

			//Referencia a linha "pai"
			RowTag myRow = (RowTag) RowColumnTag.findAncestorWithClass(this, RowTag.class);

			//Informa para a linha "pai" que uma nova coluna est? sendo criada.
			myRow.registerNewColumn();

			//Escreve HTML - Abre coluna com dados sincronizados com o cabe?alho

			HTML.append(
				"              <td nome=\""
					+ myRow.getCurrentColumnName()
					+ "\" align=\""
					+ myRow.getCurrentColumnAlign()
					+ "\" width=\""
					+ myRow.getCurrentColumnWidth()
					+ "\" key=\""
					+ key
					+ "\">\n");

			pageContext.getOut().print(HTML.toString());

		} catch (Exception e) {

			throw new JspException("Erro na RowColumnTag: " + e.getMessage());

		}

		return (EVAL_BODY_INCLUDE);
	}

	public int doEndTag() throws JspException {

		try {

			StringBuffer HTML = new StringBuffer(ConstantesCRM.SVAZIO);

			//Escreve HTML - Fecha Coluna

			HTML.append("&nbsp;</td>\n");

			pageContext.getOut().print(HTML.toString());

		} catch (Exception e) {

			throw new JspException("Erro na RowColumnTag: " + e.getMessage());

		}

		return (EVAL_PAGE);
	}



//-=-=-=-=-=-=-=-=-=-=-=-=- METODOS PARTICULARES -=-=--=-=-=-=-=-=-

//-=-=-=-=-=-=-=-=-=-=-=-=- ATRIBUTOS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=

  // Atributo key
  

  public void setKey(String sValue) {

    key = sValue;

  }

  public String getKey() {

    return key;

  }

}