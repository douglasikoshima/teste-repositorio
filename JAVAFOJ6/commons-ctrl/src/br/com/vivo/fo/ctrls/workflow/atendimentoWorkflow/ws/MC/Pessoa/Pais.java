/**
 * Pais.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa;


/**
 * Mantem informacoes de pais.
 */
public class Pais  implements java.io.Serializable {
    /* Codigo que identifica um pais. */
    private java.math.BigInteger codigo;

    /* Sigla que representa um pais. */
    private java.lang.String sigla;

    /* Nome do pais. */
    private java.lang.String nome;

    /* Identifica a nacionalidade. */
    private java.lang.String nacionalidade;

    public Pais() {
    }

    public Pais(
           java.math.BigInteger codigo,
           java.lang.String sigla,
           java.lang.String nome,
           java.lang.String nacionalidade) {
           this.codigo = codigo;
           this.sigla = sigla;
           this.nome = nome;
           this.nacionalidade = nacionalidade;
    }


    /**
     * Gets the codigo value for this Pais.
     * 
     * @return codigo   * Codigo que identifica um pais.
     */
    public java.math.BigInteger getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Pais.
     * 
     * @param codigo   * Codigo que identifica um pais.
     */
    public void setCodigo(java.math.BigInteger codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the sigla value for this Pais.
     * 
     * @return sigla   * Sigla que representa um pais.
     */
    public java.lang.String getSigla() {
        return sigla;
    }


    /**
     * Sets the sigla value for this Pais.
     * 
     * @param sigla   * Sigla que representa um pais.
     */
    public void setSigla(java.lang.String sigla) {
        this.sigla = sigla;
    }


    /**
     * Gets the nome value for this Pais.
     * 
     * @return nome   * Nome do pais.
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this Pais.
     * 
     * @param nome   * Nome do pais.
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the nacionalidade value for this Pais.
     * 
     * @return nacionalidade   * Identifica a nacionalidade.
     */
    public java.lang.String getNacionalidade() {
        return nacionalidade;
    }


    /**
     * Sets the nacionalidade value for this Pais.
     * 
     * @param nacionalidade   * Identifica a nacionalidade.
     */
    public void setNacionalidade(java.lang.String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Pais)) return false;
        Pais other = (Pais) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.sigla==null && other.getSigla()==null) || 
             (this.sigla!=null &&
              this.sigla.equals(other.getSigla()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.nacionalidade==null && other.getNacionalidade()==null) || 
             (this.nacionalidade!=null &&
              this.nacionalidade.equals(other.getNacionalidade())));
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
        if (getSigla() != null) {
            _hashCode += getSigla().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getNacionalidade() != null) {
            _hashCode += getNacionalidade().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Pais.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "Pais"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sigla");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "sigla"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nacionalidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "nacionalidade"));
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
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
