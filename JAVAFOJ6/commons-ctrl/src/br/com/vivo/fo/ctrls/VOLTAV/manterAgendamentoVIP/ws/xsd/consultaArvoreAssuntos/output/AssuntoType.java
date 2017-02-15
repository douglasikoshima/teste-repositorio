/**
 * AssuntoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaArvoreAssuntos.output;

public class AssuntoType implements java.io.Serializable {

	private static final long serialVersionUID = -6059057693650774920L;

	private java.lang.String codigo;
	private java.lang.String codigoPai;
	private java.lang.String descricao;
	private java.lang.String tipo;
	private java.lang.String[] valores;

	public AssuntoType() {
	}

	public AssuntoType(java.lang.String codigo, java.lang.String codigoPai,
			java.lang.String descricao, java.lang.String tipo,
			java.lang.String[] valores) {
		this.codigo = codigo;
		this.codigoPai = codigoPai;
		this.descricao = descricao;
		this.tipo = tipo;
		this.valores = valores;
	}

	/**
	 * Gets the codigo value for this AssuntoType.
	 * 
	 * @return codigo
	 */
	public java.lang.String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo value for this AssuntoType.
	 * 
	 * @param codigo
	 */
	public void setCodigo(java.lang.String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the codigoPai value for this AssuntoType.
	 * 
	 * @return codigoPai
	 */
	public java.lang.String getCodigoPai() {
		return codigoPai;
	}

	/**
	 * Sets the codigoPai value for this AssuntoType.
	 * 
	 * @param codigoPai
	 */
	public void setCodigoPai(java.lang.String codigoPai) {
		this.codigoPai = codigoPai;
	}

	/**
	 * Gets the descricao value for this AssuntoType.
	 * 
	 * @return descricao
	 */
	public java.lang.String getDescricao() {
		return descricao;
	}

	/**
	 * Sets the descricao value for this AssuntoType.
	 * 
	 * @param descricao
	 */
	public void setDescricao(java.lang.String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets the tipo value for this AssuntoType.
	 * 
	 * @return tipo
	 */
	public java.lang.String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo value for this AssuntoType.
	 * 
	 * @param tipo
	 */
	public void setTipo(java.lang.String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the valores value for this AssuntoType.
	 * 
	 * @return valores
	 */
	public java.lang.String[] getValores() {
		return valores;
	}

	/**
	 * Sets the valores value for this AssuntoType.
	 * 
	 * @param valores
	 */
	public void setValores(java.lang.String[] valores) {
		this.valores = valores;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof AssuntoType))
			return false;
		AssuntoType other = (AssuntoType) obj;
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
				&& ((this.codigoPai == null && other.getCodigoPai() == null) || (this.codigoPai != null && this.codigoPai
						.equals(other.getCodigoPai())))
				&& ((this.descricao == null && other.getDescricao() == null) || (this.descricao != null && this.descricao
						.equals(other.getDescricao())))
				&& ((this.tipo == null && other.getTipo() == null) || (this.tipo != null && this.tipo
						.equals(other.getTipo())))
				&& ((this.valores == null && other.getValores() == null) || (this.valores != null && java.util.Arrays
						.equals(this.valores, other.getValores())));
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
		if (getCodigoPai() != null) {
			_hashCode += getCodigoPai().hashCode();
		}
		if (getDescricao() != null) {
			_hashCode += getDescricao().hashCode();
		}
		if (getTipo() != null) {
			_hashCode += getTipo().hashCode();
		}
		if (getValores() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getValores()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(
						getValores(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			AssuntoType.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName(
				"http://br.com.vivo.eai/xsd/consultaArvoreAssuntos/output",
				"assuntoType"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(new javax.xml.namespace.QName(
				"http://br.com.vivo.eai/xsd/consultaArvoreAssuntos/output",
				"codigo"));
		elemField.setXmlType(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoPai");
		elemField.setXmlName(new javax.xml.namespace.QName(
				"http://br.com.vivo.eai/xsd/consultaArvoreAssuntos/output",
				"codigoPai"));
		elemField.setXmlType(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descricao");
		elemField.setXmlName(new javax.xml.namespace.QName(
				"http://br.com.vivo.eai/xsd/consultaArvoreAssuntos/output",
				"descricao"));
		elemField.setXmlType(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipo");
		elemField.setXmlName(new javax.xml.namespace.QName(
				"http://br.com.vivo.eai/xsd/consultaArvoreAssuntos/output",
				"tipo"));
		elemField.setXmlType(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valores");
		elemField.setXmlName(new javax.xml.namespace.QName(
				"http://br.com.vivo.eai/xsd/consultaArvoreAssuntos/output",
				"valores"));
		elemField.setXmlType(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		elemField.setItemQName(new javax.xml.namespace.QName(
				"http://br.com.vivo.eai/xsd/consultaArvoreAssuntos/output",
				"valor"));
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
	public static org.apache.axis.encoding.Serializer getSerializer(
			java.lang.String mechType, java.lang.Class _javaType,
			javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType,
				_xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
			java.lang.String mechType, java.lang.Class _javaType,
			javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType,
				_xmlType, typeDesc);
	}

}
