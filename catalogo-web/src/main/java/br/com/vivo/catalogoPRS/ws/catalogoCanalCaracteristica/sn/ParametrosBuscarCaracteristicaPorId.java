/**
 * ParametrosBuscarCaracteristicaPorId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosBuscarCaracteristicaPorId  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarCaracteristicaPorIdRaizCaracteristicaPorIdIn raizCaracteristicaPorIdIn;

    public ParametrosBuscarCaracteristicaPorId() {
    }

    public ParametrosBuscarCaracteristicaPorId(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarCaracteristicaPorIdRaizCaracteristicaPorIdIn raizCaracteristicaPorIdIn) {
           this.raizCaracteristicaPorIdIn = raizCaracteristicaPorIdIn;
    }


    /**
     * Gets the raizCaracteristicaPorIdIn value for this ParametrosBuscarCaracteristicaPorId.
     * 
     * @return raizCaracteristicaPorIdIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarCaracteristicaPorIdRaizCaracteristicaPorIdIn getRaizCaracteristicaPorIdIn() {
        return raizCaracteristicaPorIdIn;
    }


    /**
     * Sets the raizCaracteristicaPorIdIn value for this ParametrosBuscarCaracteristicaPorId.
     * 
     * @param raizCaracteristicaPorIdIn
     */
    public void setRaizCaracteristicaPorIdIn(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarCaracteristicaPorIdRaizCaracteristicaPorIdIn raizCaracteristicaPorIdIn) {
        this.raizCaracteristicaPorIdIn = raizCaracteristicaPorIdIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarCaracteristicaPorId)) return false;
        ParametrosBuscarCaracteristicaPorId other = (ParametrosBuscarCaracteristicaPorId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.raizCaracteristicaPorIdIn==null && other.getRaizCaracteristicaPorIdIn()==null) || 
             (this.raizCaracteristicaPorIdIn!=null &&
              this.raizCaracteristicaPorIdIn.equals(other.getRaizCaracteristicaPorIdIn())));
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
        if (getRaizCaracteristicaPorIdIn() != null) {
            _hashCode += getRaizCaracteristicaPorIdIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarCaracteristicaPorId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosBuscarCaracteristicaPorId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("raizCaracteristicaPorIdIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "RaizCaracteristicaPorIdIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>ParametrosBuscarCaracteristicaPorId>RaizCaracteristicaPorIdIn"));
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
