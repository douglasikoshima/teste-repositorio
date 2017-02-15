/**
 * Fatura.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.cliente.detalheFatura;

/**
 * Mantem informacoes das faturas mensais.
 */
public class Fatura implements java.io.Serializable {

	private static final long serialVersionUID = 6365837954891597339L;

	private Conta conta;

	private java.util.Date dataEmissaoFatura;

	/* Mes de referencia no formato MMYY */
	private java.lang.String mesReferenciaFatura;

	/* Data de inicio do periodo da fatura. */
	private java.util.Date dataInicioPeriodo;

	/* Data de fim de periodo da fatura. */
	private java.util.Date dataFimPeriodo;

	/* Data de vencimento da Fatura. */
	private java.lang.String dataVencimentoFatura;

	/* Valor total da fatura. */
	private java.lang.String valorTotalFatura;

	private Pagamento pagamento;

	/* Imagem da fatura */
	private java.lang.String imagemFatura;

	/* Total de paginas da fatura */
	private java.math.BigInteger totalPaginas;

	public Fatura() {
	}

	public Fatura(Conta conta, java.util.Date dataEmissaoFatura, java.lang.String mesReferenciaFatura,
			java.util.Date dataInicioPeriodo, java.util.Date dataFimPeriodo, java.lang.String dataVencimentoFatura,
			java.lang.String valorTotalFatura, Pagamento pagamento, java.lang.String imagemFatura,
			java.math.BigInteger totalPaginas) {
		this.conta = conta;
		this.dataEmissaoFatura = dataEmissaoFatura;
		this.mesReferenciaFatura = mesReferenciaFatura;
		this.dataInicioPeriodo = dataInicioPeriodo;
		this.dataFimPeriodo = dataFimPeriodo;
		this.dataVencimentoFatura = dataVencimentoFatura;
		this.valorTotalFatura = valorTotalFatura;
		this.pagamento = pagamento;
		this.imagemFatura = imagemFatura;
		this.totalPaginas = totalPaginas;
	}

	/**
	 * Gets the conta value for this Fatura.
	 * 
	 * @return conta
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * Sets the conta value for this Fatura.
	 * 
	 * @param conta
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * Gets the dataEmissaoFatura value for this Fatura.
	 * 
	 * @return dataEmissaoFatura
	 */
	public java.util.Date getDataEmissaoFatura() {
		return dataEmissaoFatura;
	}

	/**
	 * Sets the dataEmissaoFatura value for this Fatura.
	 * 
	 * @param dataEmissaoFatura
	 */
	public void setDataEmissaoFatura(java.util.Date dataEmissaoFatura) {
		this.dataEmissaoFatura = dataEmissaoFatura;
	}

	/**
	 * Gets the mesReferenciaFatura value for this Fatura.
	 * 
	 * @return mesReferenciaFatura * Mes de referencia no formato MMYY
	 */
	public java.lang.String getMesReferenciaFatura() {
		return mesReferenciaFatura;
	}

	/**
	 * Sets the mesReferenciaFatura value for this Fatura.
	 * 
	 * @param mesReferenciaFatura
	 *            * Mes de referencia no formato MMYY
	 */
	public void setMesReferenciaFatura(java.lang.String mesReferenciaFatura) {
		this.mesReferenciaFatura = mesReferenciaFatura;
	}

	/**
	 * Gets the dataInicioPeriodo value for this Fatura.
	 * 
	 * @return dataInicioPeriodo * Data de inicio do periodo da fatura.
	 */
	public java.util.Date getDataInicioPeriodo() {
		return dataInicioPeriodo;
	}

	/**
	 * Sets the dataInicioPeriodo value for this Fatura.
	 * 
	 * @param dataInicioPeriodo
	 *            * Data de inicio do periodo da fatura.
	 */
	public void setDataInicioPeriodo(java.util.Date dataInicioPeriodo) {
		this.dataInicioPeriodo = dataInicioPeriodo;
	}

	/**
	 * Gets the dataFimPeriodo value for this Fatura.
	 * 
	 * @return dataFimPeriodo * Data de fim de periodo da fatura.
	 */
	public java.util.Date getDataFimPeriodo() {
		return dataFimPeriodo;
	}

	/**
	 * Sets the dataFimPeriodo value for this Fatura.
	 * 
	 * @param dataFimPeriodo
	 *            * Data de fim de periodo da fatura.
	 */
	public void setDataFimPeriodo(java.util.Date dataFimPeriodo) {
		this.dataFimPeriodo = dataFimPeriodo;
	}

	/**
	 * Gets the dataVencimentoFatura value for this Fatura.
	 * 
	 * @return dataVencimentoFatura * Data de vencimento da Fatura.
	 */
	public java.lang.String getDataVencimentoFatura() {
		return dataVencimentoFatura;
	}

	/**
	 * Sets the dataVencimentoFatura value for this Fatura.
	 * 
	 * @param dataVencimentoFatura
	 *            * Data de vencimento da Fatura.
	 */
	public void setDataVencimentoFatura(java.lang.String dataVencimentoFatura) {
		this.dataVencimentoFatura = dataVencimentoFatura;
	}

	/**
	 * Gets the valorTotalFatura value for this Fatura.
	 * 
	 * @return valorTotalFatura * Valor total da fatura.
	 */
	public java.lang.String getValorTotalFatura() {
		return valorTotalFatura;
	}

	/**
	 * Sets the valorTotalFatura value for this Fatura.
	 * 
	 * @param valorTotalFatura
	 *            * Valor total da fatura.
	 */
	public void setValorTotalFatura(java.lang.String valorTotalFatura) {
		this.valorTotalFatura = valorTotalFatura;
	}

	/**
	 * Gets the pagamento value for this Fatura.
	 * 
	 * @return pagamento
	 */
	public Pagamento getPagamento() {
		return pagamento;
	}

	/**
	 * Sets the pagamento value for this Fatura.
	 * 
	 * @param pagamento
	 */
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	/**
	 * Gets the imagemFatura value for this Fatura.
	 * 
	 * @return imagemFatura * Imagem da fatura
	 */
	public java.lang.String getImagemFatura() {
		return imagemFatura;
	}

	/**
	 * Sets the imagemFatura value for this Fatura.
	 * 
	 * @param imagemFatura
	 *            * Imagem da fatura
	 */
	public void setImagemFatura(java.lang.String imagemFatura) {
		this.imagemFatura = imagemFatura;
	}

	/**
	 * Gets the totalPaginas value for this Fatura.
	 * 
	 * @return totalPaginas * Total de paginas da fatura
	 */
	public java.math.BigInteger getTotalPaginas() {
		return totalPaginas;
	}

	/**
	 * Sets the totalPaginas value for this Fatura.
	 * 
	 * @param totalPaginas
	 *            * Total de paginas da fatura
	 */
	public void setTotalPaginas(java.math.BigInteger totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Fatura))
			return false;
		Fatura other = (Fatura) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true
				&& ((this.conta == null && other.getConta() == null) || (this.conta != null && this.conta.equals(other
						.getConta())))
				&& ((this.dataEmissaoFatura == null && other.getDataEmissaoFatura() == null) || (this.dataEmissaoFatura != null && this.dataEmissaoFatura
						.equals(other.getDataEmissaoFatura())))
				&& ((this.mesReferenciaFatura == null && other.getMesReferenciaFatura() == null) || (this.mesReferenciaFatura != null && this.mesReferenciaFatura
						.equals(other.getMesReferenciaFatura())))
				&& ((this.dataInicioPeriodo == null && other.getDataInicioPeriodo() == null) || (this.dataInicioPeriodo != null && this.dataInicioPeriodo
						.equals(other.getDataInicioPeriodo())))
				&& ((this.dataFimPeriodo == null && other.getDataFimPeriodo() == null) || (this.dataFimPeriodo != null && this.dataFimPeriodo
						.equals(other.getDataFimPeriodo())))
				&& ((this.dataVencimentoFatura == null && other.getDataVencimentoFatura() == null) || (this.dataVencimentoFatura != null && this.dataVencimentoFatura
						.equals(other.getDataVencimentoFatura())))
				&& ((this.valorTotalFatura == null && other.getValorTotalFatura() == null) || (this.valorTotalFatura != null && this.valorTotalFatura
						.equals(other.getValorTotalFatura())))
				&& ((this.pagamento == null && other.getPagamento() == null) || (this.pagamento != null && this.pagamento
						.equals(other.getPagamento())))
				&& ((this.imagemFatura == null && other.getImagemFatura() == null) || (this.imagemFatura != null && this.imagemFatura
						.equals(other.getImagemFatura())))
				&& ((this.totalPaginas == null && other.getTotalPaginas() == null) || (this.totalPaginas != null && this.totalPaginas
						.equals(other.getTotalPaginas())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		if (getConta() != null) {
			_hashCode += getConta().hashCode();
		}
		if (getDataEmissaoFatura() != null) {
			_hashCode += getDataEmissaoFatura().hashCode();
		}
		if (getMesReferenciaFatura() != null) {
			_hashCode += getMesReferenciaFatura().hashCode();
		}
		if (getDataInicioPeriodo() != null) {
			_hashCode += getDataInicioPeriodo().hashCode();
		}
		if (getDataFimPeriodo() != null) {
			_hashCode += getDataFimPeriodo().hashCode();
		}
		if (getDataVencimentoFatura() != null) {
			_hashCode += getDataVencimentoFatura().hashCode();
		}
		if (getValorTotalFatura() != null) {
			_hashCode += getValorTotalFatura().hashCode();
		}
		if (getPagamento() != null) {
			_hashCode += getPagamento().hashCode();
		}
		if (getImagemFatura() != null) {
			_hashCode += getImagemFatura().hashCode();
		}
		if (getTotalPaginas() != null) {
			_hashCode += getTotalPaginas().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			Fatura.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Fatura"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("conta");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "conta"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Conta"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataEmissaoFatura");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataEmissaoFatura"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mesReferenciaFatura");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "mesReferenciaFatura"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataInicioPeriodo");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataInicioPeriodo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataFimPeriodo");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataFimPeriodo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataVencimentoFatura");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataVencimentoFatura"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorTotalFatura");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "valorTotalFatura"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("pagamento");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "pagamento"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Pagamento"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("imagemFatura");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "imagemFatura"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("totalPaginas");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "totalPaginas"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
