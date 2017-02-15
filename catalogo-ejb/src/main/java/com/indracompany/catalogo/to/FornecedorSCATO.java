package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class FornecedorSCATO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idFornecedorSCA;
	private String nmFornecedorSCA;
	
	public Integer getIdFornecedorSCA() {
		return idFornecedorSCA;
	}

	public void setIdFornecedorSCA(Integer idFornecedorSCA) {
		this.idFornecedorSCA = idFornecedorSCA;
	}

	public String getNmFornecedorSCA() {
		return nmFornecedorSCA;
	}

	public void setNmFornecedorSCA(String nmFornecedorSCA) {
		this.nmFornecedorSCA = nmFornecedorSCA;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idFornecedorSCA: " + this.idFornecedorSCA, "nmFornecedorSCA: " + this.nmFornecedorSCA}, ", ");
	}
}
