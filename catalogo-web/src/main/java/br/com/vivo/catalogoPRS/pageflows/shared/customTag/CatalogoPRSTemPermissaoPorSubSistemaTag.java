package br.com.vivo.catalogoPRS.pageflows.shared.customTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.util.SCAUtils;

public class CatalogoPRSTemPermissaoPorSubSistemaTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String acao;

	public void setAcao(String acao) {
		this.acao = acao;
	}

	@Override
	public int doStartTag() throws JspException {
		UserCatalogo usuario = (UserCatalogo) pageContext.getSession().getAttribute("logged_user");
		if(usuario != null){
			String[] acoes = acao.split(",");
			for (String acao : acoes) {
				if (SCAUtils.hasPermissoesBySubSistema(usuario, acao))
					return EVAL_BODY_INCLUDE;
			}
		}
		return SKIP_BODY;
	}
}
