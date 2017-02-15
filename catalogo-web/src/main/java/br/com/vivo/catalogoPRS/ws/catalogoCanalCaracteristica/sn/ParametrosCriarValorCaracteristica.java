/**
 * ParametrosCriarValorCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosCriarValorCaracteristica  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosCriarValorCaracteristicaValorCaracteristicaCriacao valorCaracteristicaCriacao;

    public ParametrosCriarValorCaracteristica() {
    }

    public ParametrosCriarValorCaracteristica(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosCriarValorCaracteristicaValorCaracteristicaCriacao valorCaracteristicaCriacao) {
           this.valorCaracteristicaCriacao = valorCaracteristicaCriacao;
    }


    /**
     * Gets the valorCaracteristicaCriacao value for this ParametrosCriarValorCaracteristica.
     * 
     * @return valorCaracteristicaCriacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosCriarValorCaracteristicaValorCaracteristicaCriacao getValorCaracteristicaCriacao() {
        return valorCaracteristicaCriacao;
    }


    /**
     * Sets the valorCaracteristicaCriacao value for this ParametrosCriarValorCaracteristica.
     * 
     * @param valorCaracteristicaCriacao
     */
    public void setValorCaracteristicaCriacao(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosCriarValorCaracteristicaValorCaracteristicaCriacao valorCaracteristicaCriacao) {
        this.valorCaracteristicaCriacao = valorCaracteristicaCriacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosCriarValorCaracteristica)) return false;
        ParametrosCriarValorCaracteristica other = (ParametrosCriarValorCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valorCaracteristicaCriacao==null && other.getValorCaracteristicaCriacao()==null) || 
             (this.valorCaracteristicaCriacao!=null &&
              this.valorCaracteristicaCriacao.equals(other.getValorCaracteristicaCriacao())));
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
        if (getValorCaracteristicaCriacao() != null) {
            _hashCode += getValorCaracteristicaCriacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosCriarValorCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosCriarValorCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCaracteristicaCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ValorCaracteristicaCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>ParametrosCriarValorCaracteristica>ValorCaracteristicaCriacao"));
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
