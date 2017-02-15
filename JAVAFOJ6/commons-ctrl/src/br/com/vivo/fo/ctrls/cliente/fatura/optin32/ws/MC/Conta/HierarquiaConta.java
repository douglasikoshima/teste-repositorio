/**
 * HierarquiaConta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package  br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta;

public class HierarquiaConta  implements java.io.Serializable {
    private java.lang.Boolean procAssincrono;

    /* Indica se o processo assincrono para marcar as
     * 						contas que serao geradas via boleto ou nao esta
     * 						em execucao ou ja terminou.
     * 						0 - nao esta em processamento;
     * 						1 - em processamento. */
    private java.math.BigInteger statusProcessamento;

    public HierarquiaConta() {
    }

    public HierarquiaConta(
           java.lang.Boolean procAssincrono,
           java.math.BigInteger statusProcessamento) {
           this.procAssincrono = procAssincrono;
           this.statusProcessamento = statusProcessamento;
    }


    /**
     * Gets the procAssincrono value for this HierarquiaConta.
     * 
     * @return procAssincrono
     */
    public java.lang.Boolean getProcAssincrono() {
        return procAssincrono;
    }


    /**
     * Sets the procAssincrono value for this HierarquiaConta.
     * 
     * @param procAssincrono
     */
    public void setProcAssincrono(java.lang.Boolean procAssincrono) {
        this.procAssincrono = procAssincrono;
    }


    /**
     * Gets the statusProcessamento value for this HierarquiaConta.
     * 
     * @return statusProcessamento   * Indica se o processo assincrono para marcar as
     * 						contas que serao geradas via boleto ou nao esta
     * 						em execucao ou ja terminou.
     * 						0 - nao esta em processamento;
     * 						1 - em processamento.
     */
    public java.math.BigInteger getStatusProcessamento() {
        return statusProcessamento;
    }


    /**
     * Sets the statusProcessamento value for this HierarquiaConta.
     * 
     * @param statusProcessamento   * Indica se o processo assincrono para marcar as
     * 						contas que serao geradas via boleto ou nao esta
     * 						em execucao ou ja terminou.
     * 						0 - nao esta em processamento;
     * 						1 - em processamento.
     */
    public void setStatusProcessamento(java.math.BigInteger statusProcessamento) {
        this.statusProcessamento = statusProcessamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HierarquiaConta)) return false;
        HierarquiaConta other = (HierarquiaConta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.procAssincrono==null && other.getProcAssincrono()==null) || 
             (this.procAssincrono!=null &&
              this.procAssincrono.equals(other.getProcAssincrono()))) &&
            ((this.statusProcessamento==null && other.getStatusProcessamento()==null) || 
             (this.statusProcessamento!=null &&
              this.statusProcessamento.equals(other.getStatusProcessamento())));
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
        if (getProcAssincrono() != null) {
            _hashCode += getProcAssincrono().hashCode();
        }
        if (getStatusProcessamento() != null) {
            _hashCode += getStatusProcessamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HierarquiaConta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "HierarquiaConta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procAssincrono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "procAssincrono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusProcessamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "statusProcessamento"));
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
