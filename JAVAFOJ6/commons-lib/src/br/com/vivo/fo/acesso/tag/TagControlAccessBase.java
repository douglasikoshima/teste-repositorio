package br.com.vivo.fo.acesso.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class TagControlAccessBase extends TagSupport {

	private static final long serialVersionUID = 3298477280033073295L;
	protected String nomeIdentificador;

	public String getNomeIdentificador() {
		return this.nomeIdentificador;
	}

	public void setNomeIdentificador(String aName) {
		this.nomeIdentificador = aName;
	}

	public int doStartTag() throws JspException {
		if (condition())
			return (EVAL_BODY_INCLUDE);
		else
			return (SKIP_BODY);
	}

	public int doEndTag() throws JspException {
		return (SKIP_BODY);
	}

	public void release() {
		super.release();
		nomeIdentificador = null;
	}

	protected abstract boolean condition() throws JspException;

}
