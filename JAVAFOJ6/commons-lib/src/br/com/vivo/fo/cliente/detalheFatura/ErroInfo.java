/**
 * ErroInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.cliente.detalheFatura;

/**
 * Mantem padrao definir as mensagens de erro.
 */
public class ErroInfo extends org.apache.axis.AxisFault implements java.io.Serializable {

	private static final long serialVersionUID = -1583456454395615279L;

	public ErroInfo(String namespace) {
		super(new javax.xml.namespace.QName(namespace, "ErroInfo"), null, null, null);
	}

	public ErroInfo(int codigo, String descricao, String xmlLog) {
		this.codigo = String.valueOf(codigo);
		this.descricao = descricao;
		this.xmlLog = xmlLog.replaceAll("[\t]", "").replaceAll("[\n]", "").replaceAll("[\r]", "");
	}

	/*
	 * Codigo que identifica o erro. Qualquer retorno diferente de 0 indica um
	 * possivel erro.
	 */
	private java.lang.String codigo;

	/*
	 * Descricao do erro ocorrido nas chamadas a servicos.
	 */
	private java.lang.String descricao;

	private java.util.Calendar dataTransacao;

	/*
	 * Dados adicionais que podem ser necessarios para identificar um erro.
	 */
	private java.lang.Object dadosAdicionais;

	/*
	 * Codigo da classificacao do erro, indicando se e emergencial, critico,
	 * warning, etc.
	 */
	private java.math.BigInteger codigoClassificacao;

	/* Descricao da classificacao */
	private java.lang.String descClassificacao;

	private java.lang.String xmlLog;

	public ErroInfo() {
	}

	public ErroInfo(java.lang.String codigo, java.lang.String descricao, java.util.Calendar dataTransacao,
			java.lang.Object dadosAdicionais, java.math.BigInteger codigoClassificacao,
			java.lang.String descClassificacao) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataTransacao = dataTransacao;
		this.dadosAdicionais = dadosAdicionais;
		this.codigoClassificacao = codigoClassificacao;
		this.descClassificacao = descClassificacao;
	}

	/**
	 * Gets the codigo value for this ErroInfo.
	 * 
	 * @return codigo * Codigo que identifica o erro. Qualquer retorno diferente
	 *         de 0 indica um possivel erro.
	 */
	public java.lang.String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo value for this ErroInfo.
	 * 
	 * @param codigo
	 *            * Codigo que identifica o erro. Qualquer retorno diferente de
	 *            0 indica um possivel erro.
	 */
	public void setCodigo(java.lang.String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the descricao value for this ErroInfo.
	 * 
	 * @return descricao * Descricao do erro ocorrido nas chamadas a servicos.
	 */
	public java.lang.String getDescricao() {
		return descricao;
	}

	/**
	 * Sets the descricao value for this ErroInfo.
	 * 
	 * @param descricao
	 *            * Descricao do erro ocorrido nas chamadas a servicos.
	 */
	public void setDescricao(java.lang.String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets the dataTransacao value for this ErroInfo.
	 * 
	 * @return dataTransacao
	 */
	public java.util.Calendar getDataTransacao() {
		return dataTransacao;
	}

	/**
	 * Sets the dataTransacao value for this ErroInfo.
	 * 
	 * @param dataTransacao
	 */
	public void setDataTransacao(java.util.Calendar dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	/**
	 * Gets the dadosAdicionais value for this ErroInfo.
	 * 
	 * @return dadosAdicionais * Dados adicionais que podem ser necessarios para
	 *         identificar um erro.
	 */
	public java.lang.Object getDadosAdicionais() {
		return dadosAdicionais;
	}

	/**
	 * Sets the dadosAdicionais value for this ErroInfo.
	 * 
	 * @param dadosAdicionais
	 *            * Dados adicionais que podem ser necessarios para identificar
	 *            um erro.
	 */
	public void setDadosAdicionais(java.lang.Object dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}

	/**
	 * Gets the codigoClassificacao value for this ErroInfo.
	 * 
	 * @return codigoClassificacao * Codigo da classificacao do erro, indicando
	 *         se e emergencial, critico, warning, etc.
	 */
	public java.math.BigInteger getCodigoClassificacao() {
		return codigoClassificacao;
	}

	/**
	 * Sets the codigoClassificacao value for this ErroInfo.
	 * 
	 * @param codigoClassificacao
	 *            * Codigo da classificacao do erro, indicando se e emergencial,
	 *            critico, warning, etc.
	 */
	public void setCodigoClassificacao(java.math.BigInteger codigoClassificacao) {
		this.codigoClassificacao = codigoClassificacao;
	}

	public java.lang.String getXmlLog() {
		return this.xmlLog;
	}

	public void setXmlLog(java.lang.String arg) {
		this.xmlLog = arg;
	}

	/**
	 * Gets the descClassificacao value for this ErroInfo.
	 * 
	 * @return descClassificacao * Descricao da classificacao
	 */
	public java.lang.String getDescClassificacao() {
		return descClassificacao;
	}

	/**
	 * Sets the descClassificacao value for this ErroInfo.
	 * 
	 * @param descClassificacao
	 *            * Descricao da classificacao
	 */
	public void setDescClassificacao(java.lang.String descClassificacao) {
		this.descClassificacao = descClassificacao;
	}

}
