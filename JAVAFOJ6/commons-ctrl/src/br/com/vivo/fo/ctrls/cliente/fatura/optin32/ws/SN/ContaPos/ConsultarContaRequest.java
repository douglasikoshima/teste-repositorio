/**
 * ConsultarContaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos;

public class ConsultarContaRequest  implements java.io.Serializable {
    private java.lang.String nrConta;

    private java.lang.String nrLinha;

    public ConsultarContaRequest() {
    }

    public ConsultarContaRequest(
           java.lang.String nrConta,
           java.lang.String nrLinha) {
           this.nrConta = nrConta;
           this.nrLinha = nrLinha;
    }


    /**
     * Gets the nrConta value for this ConsultarContaRequest.
     * 
     * @return nrConta
     */
    public java.lang.String getNrConta() {
        return nrConta;
    }


    /**
     * Sets the nrConta value for this ConsultarContaRequest.
     * 
     * @param nrConta
     */
    public void setNrConta(java.lang.String nrConta) {
        this.nrConta = nrConta;
    }


    /**
     * Gets the nrLinha value for this ConsultarContaRequest.
     * 
     * @return nrLinha
     */
    public java.lang.String getNrLinha() {
        return nrLinha;
    }


    /**
     * Sets the nrLinha value for this ConsultarContaRequest.
     * 
     * @param nrLinha
     */
    public void setNrLinha(java.lang.String nrLinha) {
        this.nrLinha = nrLinha;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultarContaRequest)) return false;
        ConsultarContaRequest other = (ConsultarContaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nrConta==null && other.getNrConta()==null) || 
             (this.nrConta!=null &&
              this.nrConta.equals(other.getNrConta()))) &&
            ((this.nrLinha==null && other.getNrLinha()==null) || 
             (this.nrLinha!=null &&
              this.nrLinha.equals(other.getNrLinha())));
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
        if (getNrConta() != null) {
            _hashCode += getNrConta().hashCode();
        }
        if (getNrLinha() != null) {
            _hashCode += getNrLinha().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultarContaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/ContaPos", ">consultarContaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nrConta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nrConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nrLinha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nrLinha"));
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
