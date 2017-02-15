package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class FuncionalidadeTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FuncionalidadeTO() {}
	
	public FuncionalidadeTO(String cdFuncionalidade) {
		this.cdFuncionalidade = cdFuncionalidade;
	}
	
	private String cdFuncionalidade;
	private String nmFuncionalidade;
	private Date dtCriacao;

	public String getCdFuncionalidade() {
		return cdFuncionalidade;
	}

	public void setCdFuncionalidade(String cdFuncionalidade) {
		this.cdFuncionalidade = cdFuncionalidade;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getNmFuncionalidade() {
		return nmFuncionalidade;
	}

	public void setNmFuncionalidade(String nmFuncionalidade) {
		this.nmFuncionalidade = nmFuncionalidade;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"cdFuncionalidade: " + this.cdFuncionalidade, "nmFuncionalidade: " + this.nmFuncionalidade}, ", ");
	}
}
