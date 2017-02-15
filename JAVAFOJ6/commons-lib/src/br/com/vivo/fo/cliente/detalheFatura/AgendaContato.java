/**
 * AgendaContato.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.cliente.detalheFatura;

/**
 * Mantem informacoes dos contatos disponiveis nas agendas de clientes vivo.
 */
public class AgendaContato implements java.io.Serializable {

	private static final long serialVersionUID = 1759852618201324427L;

	/* Nome do contato da agenda */
	private java.lang.String nome;

	/* Sobrenome do contato */
	private java.lang.String sobrenome;

	/* Apelido do contato */
	private java.lang.String apelido;

	/*
	 * Indica o tipo do telefone do contato, se celular, comercial, residencial,
	 * etc
	 */
	private java.lang.String tipoTelefone;

	/* Grupo em que o contato esta, se Familia, Empresa, VIP, etc. */
	private java.lang.String grupoContato;

	/* Numero do telefone que foi cadastrado na agenda do contato */
	private java.lang.String telefone;

	public AgendaContato() {
	}

	public AgendaContato(java.lang.String nome, java.lang.String sobrenome, java.lang.String apelido,
			java.lang.String tipoTelefone, java.lang.String grupoContato, java.lang.String telefone) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.apelido = apelido;
		this.tipoTelefone = tipoTelefone;
		this.grupoContato = grupoContato;
		this.telefone = telefone;
	}

	/**
	 * Gets the nome value for this AgendaContato.
	 * 
	 * @return nome * Nome do contato da agenda
	 */
	public java.lang.String getNome() {
		return nome;
	}

	/**
	 * Sets the nome value for this AgendaContato.
	 * 
	 * @param nome
	 *            * Nome do contato da agenda
	 */
	public void setNome(java.lang.String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the sobrenome value for this AgendaContato.
	 * 
	 * @return sobrenome * Sobrenome do contato
	 */
	public java.lang.String getSobrenome() {
		return sobrenome;
	}

	/**
	 * Sets the sobrenome value for this AgendaContato.
	 * 
	 * @param sobrenome
	 *            * Sobrenome do contato
	 */
	public void setSobrenome(java.lang.String sobrenome) {
		this.sobrenome = sobrenome;
	}

	/**
	 * Gets the apelido value for this AgendaContato.
	 * 
	 * @return apelido * Apelido do contato
	 */
	public java.lang.String getApelido() {
		return apelido;
	}

	/**
	 * Sets the apelido value for this AgendaContato.
	 * 
	 * @param apelido
	 *            * Apelido do contato
	 */
	public void setApelido(java.lang.String apelido) {
		this.apelido = apelido;
	}

	/**
	 * Gets the tipoTelefone value for this AgendaContato.
	 * 
	 * @return tipoTelefone * Indica o tipo do telefone do contato, se celular,
	 *         comercial, residencial, etc
	 */
	public java.lang.String getTipoTelefone() {
		return tipoTelefone;
	}

	/**
	 * Sets the tipoTelefone value for this AgendaContato.
	 * 
	 * @param tipoTelefone
	 *            * Indica o tipo do telefone do contato, se celular, comercial,
	 *            residencial, etc
	 */
	public void setTipoTelefone(java.lang.String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	/**
	 * Gets the grupoContato value for this AgendaContato.
	 * 
	 * @return grupoContato * Grupo em que o contato esta, se Familia, Empresa,
	 *         VIP, etc.
	 */
	public java.lang.String getGrupoContato() {
		return grupoContato;
	}

	/**
	 * Sets the grupoContato value for this AgendaContato.
	 * 
	 * @param grupoContato
	 *            * Grupo em que o contato esta, se Familia, Empresa, VIP, etc.
	 */
	public void setGrupoContato(java.lang.String grupoContato) {
		this.grupoContato = grupoContato;
	}

	/**
	 * Gets the telefone value for this AgendaContato.
	 * 
	 * @return telefone * Numero do telefone que foi cadastrado na agenda do
	 *         contato
	 */
	public java.lang.String getTelefone() {
		return telefone;
	}

	/**
	 * Sets the telefone value for this AgendaContato.
	 * 
	 * @param telefone
	 *            * Numero do telefone que foi cadastrado na agenda do contato
	 */
	public void setTelefone(java.lang.String telefone) {
		this.telefone = telefone;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof AgendaContato))
			return false;
		AgendaContato other = (AgendaContato) obj;
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
				&& ((this.nome == null && other.getNome() == null) || (this.nome != null && this.nome.equals(other
						.getNome())))
				&& ((this.sobrenome == null && other.getSobrenome() == null) || (this.sobrenome != null && this.sobrenome
						.equals(other.getSobrenome())))
				&& ((this.apelido == null && other.getApelido() == null) || (this.apelido != null && this.apelido
						.equals(other.getApelido())))
				&& ((this.tipoTelefone == null && other.getTipoTelefone() == null) || (this.tipoTelefone != null && this.tipoTelefone
						.equals(other.getTipoTelefone())))
				&& ((this.grupoContato == null && other.getGrupoContato() == null) || (this.grupoContato != null && this.grupoContato
						.equals(other.getGrupoContato())))
				&& ((this.telefone == null && other.getTelefone() == null) || (this.telefone != null && this.telefone
						.equals(other.getTelefone())));
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
		if (getNome() != null) {
			_hashCode += getNome().hashCode();
		}
		if (getSobrenome() != null) {
			_hashCode += getSobrenome().hashCode();
		}
		if (getApelido() != null) {
			_hashCode += getApelido().hashCode();
		}
		if (getTipoTelefone() != null) {
			_hashCode += getTipoTelefone().hashCode();
		}
		if (getGrupoContato() != null) {
			_hashCode += getGrupoContato().hashCode();
		}
		if (getTelefone() != null) {
			_hashCode += getTelefone().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			AgendaContato.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "AgendaContato"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nome");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "nome"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sobrenome");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "sobrenome"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("apelido");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "apelido"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoTelefone");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "tipoTelefone"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("grupoContato");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "grupoContato"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("telefone");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "telefone"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
