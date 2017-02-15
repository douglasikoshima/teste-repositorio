/**
 * BuscarCaracteristicaPorIdRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarCaracteristicaPorIdRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarCaracteristicaPorId parametrosBuscarCaracteristicaPorId;

    public BuscarCaracteristicaPorIdRequest() {
    }

    public BuscarCaracteristicaPorIdRequest(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarCaracteristicaPorId parametrosBuscarCaracteristicaPorId) {
           this.parametrosBuscarCaracteristicaPorId = parametrosBuscarCaracteristicaPorId;
    }


    /**
     * Gets the parametrosBuscarCaracteristicaPorId value for this BuscarCaracteristicaPorIdRequest.
     * 
     * @return parametrosBuscarCaracteristicaPorId
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarCaracteristicaPorId getParametrosBuscarCaracteristicaPorId() {
        return parametrosBuscarCaracteristicaPorId;
    }


    /**
     * Sets the parametrosBuscarCaracteristicaPorId value for this BuscarCaracteristicaPorIdRequest.
     * 
     * @param parametrosBuscarCaracteristicaPorId
     */
    public void setParametrosBuscarCaracteristicaPorId(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarCaracteristicaPorId parametrosBuscarCaracteristicaPorId) {
        this.parametrosBuscarCaracteristicaPorId = parametrosBuscarCaracteristicaPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarCaracteristicaPorIdRequest)) return false;
        BuscarCaracteristicaPorIdRequest other = (BuscarCaracteristicaPorIdRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarCaracteristicaPorId==null && other.getParametrosBuscarCaracteristicaPorId()==null) || 
             (this.parametrosBuscarCaracteristicaPorId!=null &&
              this.parametrosBuscarCaracteristicaPorId.equals(other.getParametrosBuscarCaracteristicaPorId())));
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
        if (getParametrosBuscarCaracteristicaPorId() != null) {
            _hashCode += getParametrosBuscarCaracteristicaPorId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarCaracteristicaPorIdRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarCaracteristicaPorIdRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarCaracteristicaPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosBuscarCaracteristicaPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosBuscarCaracteristicaPorId"));
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
