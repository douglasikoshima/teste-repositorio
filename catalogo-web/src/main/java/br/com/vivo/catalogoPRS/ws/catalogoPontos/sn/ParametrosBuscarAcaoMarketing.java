/**
 * ParametrosBuscarAcaoMarketing.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPontos.sn;

public class ParametrosBuscarAcaoMarketing  implements java.io.Serializable {
    private java.lang.String numeroLinha;

    private java.lang.Long identificadorAcao;

    public ParametrosBuscarAcaoMarketing() {
    }

    public ParametrosBuscarAcaoMarketing(
           java.lang.String numeroLinha,
           java.lang.Long identificadorAcao) {
           this.numeroLinha = numeroLinha;
           this.identificadorAcao = identificadorAcao;
    }


    /**
     * Gets the numeroLinha value for this ParametrosBuscarAcaoMarketing.
     * 
     * @return numeroLinha
     */
    public java.lang.String getNumeroLinha() {
        return numeroLinha;
    }


    /**
     * Sets the numeroLinha value for this ParametrosBuscarAcaoMarketing.
     * 
     * @param numeroLinha
     */
    public void setNumeroLinha(java.lang.String numeroLinha) {
        this.numeroLinha = numeroLinha;
    }


    /**
     * Gets the identificadorAcao value for this ParametrosBuscarAcaoMarketing.
     * 
     * @return identificadorAcao
     */
    public java.lang.Long getIdentificadorAcao() {
        return identificadorAcao;
    }


    /**
     * Sets the identificadorAcao value for this ParametrosBuscarAcaoMarketing.
     * 
     * @param identificadorAcao
     */
    public void setIdentificadorAcao(java.lang.Long identificadorAcao) {
        this.identificadorAcao = identificadorAcao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarAcaoMarketing)) return false;
        ParametrosBuscarAcaoMarketing other = (ParametrosBuscarAcaoMarketing) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroLinha==null && other.getNumeroLinha()==null) || 
             (this.numeroLinha!=null &&
              this.numeroLinha.equals(other.getNumeroLinha()))) &&
            ((this.identificadorAcao==null && other.getIdentificadorAcao()==null) || 
             (this.identificadorAcao!=null &&
              this.identificadorAcao.equals(other.getIdentificadorAcao())));
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
        if (getNumeroLinha() != null) {
            _hashCode += getNumeroLinha().hashCode();
        }
        if (getIdentificadorAcao() != null) {
            _hashCode += getIdentificadorAcao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarAcaoMarketing.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/Pontos", "ParametrosBuscarAcaoMarketing"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroLinha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/Pontos", "numeroLinha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/Pontos", "identificadorAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
