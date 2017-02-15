/**
 * Conta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.cliente.detalheFatura;

/**
 * Mantem informacoes das conta de clientes e prospects.
 */
public class Conta implements java.io.Serializable {

	private static final long serialVersionUID = -6816026465422263713L;

	/*
	 * Codigo que identifica uma conta no vivonet (idconta).
	 */
	private java.math.BigInteger codigo;

	/*
	 * Codigo que identifica uma conta, no sistema origem, por exemplo no Atlys,
	 * essa coluna e representada pelo account_id.
	 */
	private java.lang.String codigoContaSistemOrigem;

	/* Data de expiracao da conta */
	private java.util.Calendar dataExpiracao;

	/* Data de vencimento de conta. */
	private java.util.Date dataVencimento;

	/* Data de inicio do ciclo. */
	private java.util.Date dataInicialCiclo;

	/* Data de fim do ciclo. */
	private java.util.Date dataFinalCiclo;

	/*
	 * Data de alteracao do status de conta. Data de inicio de vigencia de um
	 * status.
	 */
	private java.util.Calendar dataAlteracaoStatus;

	/* Valor da ultima fatura */
	private java.math.BigDecimal valorUltimaFatura;

	/*
	 * Identifica se a conta deve ser enviada por e-mail para o cliente. Caso o
	 * cliente deseje receber a conta por e-mail, esse indicador estara com o
	 * valor 1 (true).
	 */
	private java.lang.Boolean contaPorEmail;

	/*
	 * Indica se a conta e a conta pagadora (utilizado para hieraquia de
	 * contas). Se a conta for pagadora, esse indicador tera o valor 1 (true).
	 */
	private java.lang.Boolean contaPagadora;

	private java.lang.Boolean smsContaGerada;

	/*
	 * Indica se eh enviado SMS de codigo de barras (1 - true) ou nao (0 -
	 * false)
	 */
	private java.lang.Boolean smsCodigoBarras;

	/*
	 * Indica se a conta tem ativo o envio eletronico da fatura (true) ou tem
	 * ativo o envio por correio da fatura (false).
	 */
	private java.lang.Boolean envioEletronicoFatura;

	/* Referencia a entidade Ciclo. */
	private Ciclo ciclo;

	public Conta() {
	}

	public Conta(java.math.BigInteger codigo, java.lang.String codigoContaSistemOrigem,
			java.util.Calendar dataExpiracao, java.util.Date dataVencimento, java.util.Date dataInicialCiclo,
			java.util.Date dataFinalCiclo, java.util.Calendar dataAlteracaoStatus,
			java.math.BigDecimal valorUltimaFatura, java.lang.Boolean contaPorEmail, java.lang.Boolean contaPagadora,
			java.lang.Boolean smsContaGerada, java.lang.Boolean smsCodigoBarras,
			java.lang.Boolean envioEletronicoFatura, Ciclo ciclo) {
		this.codigo = codigo;
		this.codigoContaSistemOrigem = codigoContaSistemOrigem;
		this.dataExpiracao = dataExpiracao;
		this.dataVencimento = dataVencimento;
		this.dataInicialCiclo = dataInicialCiclo;
		this.dataFinalCiclo = dataFinalCiclo;
		this.dataAlteracaoStatus = dataAlteracaoStatus;
		this.valorUltimaFatura = valorUltimaFatura;
		this.contaPorEmail = contaPorEmail;
		this.contaPagadora = contaPagadora;
		this.smsContaGerada = smsContaGerada;
		this.smsCodigoBarras = smsCodigoBarras;
		this.envioEletronicoFatura = envioEletronicoFatura;
		this.ciclo = ciclo;
	}

	/**
	 * Gets the codigo value for this Conta.
	 * 
	 * @return codigo * Codigo que identifica uma conta no vivonet (idconta).
	 */
	public java.math.BigInteger getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo value for this Conta.
	 * 
	 * @param codigo
	 *            * Codigo que identifica uma conta no vivonet (idconta).
	 */
	public void setCodigo(java.math.BigInteger codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the codigoContaSistemOrigem value for this Conta.
	 * 
	 * @return codigoContaSistemOrigem * Codigo que identifica uma conta, no
	 *         sistema origem, por exemplo no Atlys, essa coluna e representada
	 *         pelo account_id.
	 */
	public java.lang.String getCodigoContaSistemOrigem() {
		return codigoContaSistemOrigem;
	}

	/**
	 * Sets the codigoContaSistemOrigem value for this Conta.
	 * 
	 * @param codigoContaSistemOrigem
	 *            * Codigo que identifica uma conta, no sistema origem, por
	 *            exemplo no Atlys, essa coluna e representada pelo account_id.
	 */
	public void setCodigoContaSistemOrigem(java.lang.String codigoContaSistemOrigem) {
		this.codigoContaSistemOrigem = codigoContaSistemOrigem;
	}

	/**
	 * Gets the dataExpiracao value for this Conta.
	 * 
	 * @return dataExpiracao * Data de expiracao da conta
	 */
	public java.util.Calendar getDataExpiracao() {
		return dataExpiracao;
	}

	/**
	 * Sets the dataExpiracao value for this Conta.
	 * 
	 * @param dataExpiracao
	 *            * Data de expiracao da conta
	 */
	public void setDataExpiracao(java.util.Calendar dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	/**
	 * Gets the dataVencimento value for this Conta.
	 * 
	 * @return dataVencimento * Data de vencimento de conta.
	 */
	public java.util.Date getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * Sets the dataVencimento value for this Conta.
	 * 
	 * @param dataVencimento
	 *            * Data de vencimento de conta.
	 */
	public void setDataVencimento(java.util.Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	/**
	 * Gets the dataInicialCiclo value for this Conta.
	 * 
	 * @return dataInicialCiclo * Data de inicio do ciclo.
	 */
	public java.util.Date getDataInicialCiclo() {
		return dataInicialCiclo;
	}

	/**
	 * Sets the dataInicialCiclo value for this Conta.
	 * 
	 * @param dataInicialCiclo
	 *            * Data de inicio do ciclo.
	 */
	public void setDataInicialCiclo(java.util.Date dataInicialCiclo) {
		this.dataInicialCiclo = dataInicialCiclo;
	}

	/**
	 * Gets the dataFinalCiclo value for this Conta.
	 * 
	 * @return dataFinalCiclo * Data de fim do ciclo.
	 */
	public java.util.Date getDataFinalCiclo() {
		return dataFinalCiclo;
	}

	/**
	 * Sets the dataFinalCiclo value for this Conta.
	 * 
	 * @param dataFinalCiclo
	 *            * Data de fim do ciclo.
	 */
	public void setDataFinalCiclo(java.util.Date dataFinalCiclo) {
		this.dataFinalCiclo = dataFinalCiclo;
	}

	/**
	 * Gets the dataAlteracaoStatus value for this Conta.
	 * 
	 * @return dataAlteracaoStatus * Data de alteracao do status de conta. Data
	 *         de inicio de vigencia de um status.
	 */
	public java.util.Calendar getDataAlteracaoStatus() {
		return dataAlteracaoStatus;
	}

	/**
	 * Sets the dataAlteracaoStatus value for this Conta.
	 * 
	 * @param dataAlteracaoStatus
	 *            * Data de alteracao do status de conta. Data de inicio de
	 *            vigencia de um status.
	 */
	public void setDataAlteracaoStatus(java.util.Calendar dataAlteracaoStatus) {
		this.dataAlteracaoStatus = dataAlteracaoStatus;
	}

	/**
	 * Gets the valorUltimaFatura value for this Conta.
	 * 
	 * @return valorUltimaFatura * Valor da ultima fatura
	 */
	public java.math.BigDecimal getValorUltimaFatura() {
		return valorUltimaFatura;
	}

	/**
	 * Sets the valorUltimaFatura value for this Conta.
	 * 
	 * @param valorUltimaFatura
	 *            * Valor da ultima fatura
	 */
	public void setValorUltimaFatura(java.math.BigDecimal valorUltimaFatura) {
		this.valorUltimaFatura = valorUltimaFatura;
	}

	/**
	 * Gets the contaPorEmail value for this Conta.
	 * 
	 * @return contaPorEmail * Identifica se a conta deve ser enviada por e-mail
	 *         para o cliente. Caso o cliente deseje receber a conta por e-mail,
	 *         esse indicador estara com o valor 1 (true).
	 */
	public java.lang.Boolean getContaPorEmail() {
		return contaPorEmail;
	}

	/**
	 * Sets the contaPorEmail value for this Conta.
	 * 
	 * @param contaPorEmail
	 *            * Identifica se a conta deve ser enviada por e-mail para o
	 *            cliente. Caso o cliente deseje receber a conta por e-mail,
	 *            esse indicador estara com o valor 1 (true).
	 */
	public void setContaPorEmail(java.lang.Boolean contaPorEmail) {
		this.contaPorEmail = contaPorEmail;
	}

	/**
	 * Gets the contaPagadora value for this Conta.
	 * 
	 * @return contaPagadora * Indica se a conta e a conta pagadora (utilizado
	 *         para hieraquia de contas). Se a conta for pagadora, esse
	 *         indicador tera o valor 1 (true).
	 */
	public java.lang.Boolean getContaPagadora() {
		return contaPagadora;
	}

	/**
	 * Sets the contaPagadora value for this Conta.
	 * 
	 * @param contaPagadora
	 *            * Indica se a conta e a conta pagadora (utilizado para
	 *            hieraquia de contas). Se a conta for pagadora, esse indicador
	 *            tera o valor 1 (true).
	 */
	public void setContaPagadora(java.lang.Boolean contaPagadora) {
		this.contaPagadora = contaPagadora;
	}

	/**
	 * Gets the smsContaGerada value for this Conta.
	 * 
	 * @return smsContaGerada
	 */
	public java.lang.Boolean getSmsContaGerada() {
		return smsContaGerada;
	}

	/**
	 * Sets the smsContaGerada value for this Conta.
	 * 
	 * @param smsContaGerada
	 */
	public void setSmsContaGerada(java.lang.Boolean smsContaGerada) {
		this.smsContaGerada = smsContaGerada;
	}

	/**
	 * Gets the smsCodigoBarras value for this Conta.
	 * 
	 * @return smsCodigoBarras * Indica se eh enviado SMS de codigo de barras (1
	 *         - true) ou nao (0 - false)
	 */
	public java.lang.Boolean getSmsCodigoBarras() {
		return smsCodigoBarras;
	}

	/**
	 * Sets the smsCodigoBarras value for this Conta.
	 * 
	 * @param smsCodigoBarras
	 *            * Indica se eh enviado SMS de codigo de barras (1 - true) ou
	 *            nao (0 - false)
	 */
	public void setSmsCodigoBarras(java.lang.Boolean smsCodigoBarras) {
		this.smsCodigoBarras = smsCodigoBarras;
	}

	/**
	 * Gets the envioEletronicoFatura value for this Conta.
	 * 
	 * @return envioEletronicoFatura * Indica se a conta tem ativo o envio
	 *         eletronico da fatura (true) ou tem ativo o envio por correio da
	 *         fatura (false).
	 */
	public java.lang.Boolean getEnvioEletronicoFatura() {
		return envioEletronicoFatura;
	}

	/**
	 * Sets the envioEletronicoFatura value for this Conta.
	 * 
	 * @param envioEletronicoFatura
	 *            * Indica se a conta tem ativo o envio eletronico da fatura
	 *            (true) ou tem ativo o envio por correio da fatura (false).
	 */
	public void setEnvioEletronicoFatura(java.lang.Boolean envioEletronicoFatura) {
		this.envioEletronicoFatura = envioEletronicoFatura;
	}

	/**
	 * Gets the ciclo value for this Conta.
	 * 
	 * @return ciclo * Referencia a entidade Ciclo.
	 */
	public Ciclo getCiclo() {
		return ciclo;
	}

	/**
	 * Sets the ciclo value for this Conta.
	 * 
	 * @param ciclo
	 *            * Referencia a entidade Ciclo.
	 */
	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Conta))
			return false;
		Conta other = (Conta) obj;
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
				&& ((this.codigo == null && other.getCodigo() == null) || (this.codigo != null && this.codigo
						.equals(other.getCodigo())))
				&& ((this.codigoContaSistemOrigem == null && other.getCodigoContaSistemOrigem() == null) || (this.codigoContaSistemOrigem != null && this.codigoContaSistemOrigem
						.equals(other.getCodigoContaSistemOrigem())))
				&& ((this.dataExpiracao == null && other.getDataExpiracao() == null) || (this.dataExpiracao != null && this.dataExpiracao
						.equals(other.getDataExpiracao())))
				&& ((this.dataVencimento == null && other.getDataVencimento() == null) || (this.dataVencimento != null && this.dataVencimento
						.equals(other.getDataVencimento())))
				&& ((this.dataInicialCiclo == null && other.getDataInicialCiclo() == null) || (this.dataInicialCiclo != null && this.dataInicialCiclo
						.equals(other.getDataInicialCiclo())))
				&& ((this.dataFinalCiclo == null && other.getDataFinalCiclo() == null) || (this.dataFinalCiclo != null && this.dataFinalCiclo
						.equals(other.getDataFinalCiclo())))
				&& ((this.dataAlteracaoStatus == null && other.getDataAlteracaoStatus() == null) || (this.dataAlteracaoStatus != null && this.dataAlteracaoStatus
						.equals(other.getDataAlteracaoStatus())))
				&& ((this.valorUltimaFatura == null && other.getValorUltimaFatura() == null) || (this.valorUltimaFatura != null && this.valorUltimaFatura
						.equals(other.getValorUltimaFatura())))
				&& ((this.contaPorEmail == null && other.getContaPorEmail() == null) || (this.contaPorEmail != null && this.contaPorEmail
						.equals(other.getContaPorEmail())))
				&& ((this.contaPagadora == null && other.getContaPagadora() == null) || (this.contaPagadora != null && this.contaPagadora
						.equals(other.getContaPagadora())))
				&& ((this.smsContaGerada == null && other.getSmsContaGerada() == null) || (this.smsContaGerada != null && this.smsContaGerada
						.equals(other.getSmsContaGerada())))
				&& ((this.smsCodigoBarras == null && other.getSmsCodigoBarras() == null) || (this.smsCodigoBarras != null && this.smsCodigoBarras
						.equals(other.getSmsCodigoBarras())))
				&& ((this.envioEletronicoFatura == null && other.getEnvioEletronicoFatura() == null) || (this.envioEletronicoFatura != null && this.envioEletronicoFatura
						.equals(other.getEnvioEletronicoFatura())))
				&& ((this.ciclo == null && other.getCiclo() == null) || (this.ciclo != null && this.ciclo.equals(other
						.getCiclo())));
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
		if (getCodigo() != null) {
			_hashCode += getCodigo().hashCode();
		}
		if (getCodigoContaSistemOrigem() != null) {
			_hashCode += getCodigoContaSistemOrigem().hashCode();
		}
		if (getDataExpiracao() != null) {
			_hashCode += getDataExpiracao().hashCode();
		}
		if (getDataVencimento() != null) {
			_hashCode += getDataVencimento().hashCode();
		}
		if (getDataInicialCiclo() != null) {
			_hashCode += getDataInicialCiclo().hashCode();
		}
		if (getDataFinalCiclo() != null) {
			_hashCode += getDataFinalCiclo().hashCode();
		}
		if (getDataAlteracaoStatus() != null) {
			_hashCode += getDataAlteracaoStatus().hashCode();
		}
		if (getValorUltimaFatura() != null) {
			_hashCode += getValorUltimaFatura().hashCode();
		}
		if (getContaPorEmail() != null) {
			_hashCode += getContaPorEmail().hashCode();
		}
		if (getContaPagadora() != null) {
			_hashCode += getContaPagadora().hashCode();
		}
		if (getSmsContaGerada() != null) {
			_hashCode += getSmsContaGerada().hashCode();
		}
		if (getSmsCodigoBarras() != null) {
			_hashCode += getSmsCodigoBarras().hashCode();
		}
		if (getEnvioEletronicoFatura() != null) {
			_hashCode += getEnvioEletronicoFatura().hashCode();
		}
		if (getCiclo() != null) {
			_hashCode += getCiclo().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			Conta.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Conta"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "codigo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoContaSistemOrigem");
		elemField
				.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "codigoContaSistemOrigem"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataExpiracao");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataExpiracao"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataVencimento");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataVencimento"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataInicialCiclo");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataInicialCiclo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataFinalCiclo");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataFinalCiclo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dataAlteracaoStatus");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataAlteracaoStatus"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorUltimaFatura");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "valorUltimaFatura"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("contaPorEmail");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "contaPorEmail"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("contaPagadora");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "contaPagadora"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("smsContaGerada");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "smsContaGerada"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("smsCodigoBarras");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "smsCodigoBarras"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("envioEletronicoFatura");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "envioEletronicoFatura"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("ciclo");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "ciclo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Ciclo"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
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
