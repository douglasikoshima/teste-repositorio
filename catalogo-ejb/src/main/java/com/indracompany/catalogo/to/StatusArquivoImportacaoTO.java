package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author equipe Catalogo
 *
 */
public class StatusArquivoImportacaoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public StatusArquivoImportacaoTO() {}
	
	private Integer idStatusArquivoImportacao;
	private String dscStatusArquivoImportacao;

	
	public String getDscStatusArquivoImportacao() {
		return dscStatusArquivoImportacao;
	}
	public void setDscStatusArquivoImportacao(String dscStatusArquivoImportacao) {
		this.dscStatusArquivoImportacao = dscStatusArquivoImportacao;
	}
	public Integer getIdStatusArquivoImportacao() {
		return idStatusArquivoImportacao;
	}
	public void setIdStatusArquivoImportacao(Integer idStatusArquivoImportacao) {
		this.idStatusArquivoImportacao = idStatusArquivoImportacao;
	}
	public StatusArquivoImportacaoTO(Integer idStatusArquivoImportacao, String dscStatusArquivoImportacao) {
		super();
		this.idStatusArquivoImportacao = idStatusArquivoImportacao;
		this.dscStatusArquivoImportacao = dscStatusArquivoImportacao;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{
				"idStatusArquivoImportacao: " + this.idStatusArquivoImportacao, 
				"dscStatusArquivoImportacao: " + this.dscStatusArquivoImportacao
				}, ", ");
	}



}