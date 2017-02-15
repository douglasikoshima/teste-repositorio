/**
 * ConsultarContasEmAbertoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos;

public class ConsultarContasEmAbertoRequest  implements java.io.Serializable {
    private java.lang.String ddd;

    private java.lang.String numeroLinha;

    public ConsultarContasEmAbertoRequest() {
    }

    public ConsultarContasEmAbertoRequest(
           java.lang.String ddd,
           java.lang.String numeroLinha) {
           this.ddd = ddd;
           this.numeroLinha = numeroLinha;
    }


    /**
     * Gets the ddd value for this ConsultarContasEmAbertoRequest.
     * 
     * @return ddd
     */
    public java.lang.String getDdd() {
        return ddd;
    }


    /**
     * Sets the ddd value for this ConsultarContasEmAbertoRequest.
     * 
     * @param ddd
     */
    public void setDdd(java.lang.String ddd) {
        this.ddd = ddd;
    }


    /**
     * Gets the numeroLinha value for this ConsultarContasEmAbertoRequest.
     * 
     * @return numeroLinha
     */
    public java.lang.String getNumeroLinha() {
        return numeroLinha;
    }


    /**
     * Sets the numeroLinha value for this ConsultarContasEmAbertoRequest.
     * 
     * @param numeroLinha
     */
    public void setNumeroLinha(java.lang.String numeroLinha) {
        this.numeroLinha = numeroLinha;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultarContasEmAbertoRequest)) return false;
        ConsultarContasEmAbertoRequest other = (ConsultarContasEmAbertoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ddd==null && other.getDdd()==null) || 
             (this.ddd!=null &&
              this.ddd.equals(other.getDdd()))) &&
            ((this.numeroLinha==null && other.getNumeroLinha()==null) || 
             (this.numeroLinha!=null &&
              this.numeroLinha.equals(other.getNumeroLinha())));
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
        if (getDdd() != null) {
            _hashCode += getDdd().hashCode();
        }
        if (getNumeroLinha() != null) {
            _hashCode += getNumeroLinha().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultarContasEmAbertoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/ContaPos", ">consultarContasEmAbertoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ddd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ddd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroLinha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroLinha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
