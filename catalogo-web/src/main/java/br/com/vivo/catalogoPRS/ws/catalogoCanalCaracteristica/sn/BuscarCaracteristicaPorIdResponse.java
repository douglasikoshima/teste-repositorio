/**
 * BuscarCaracteristicaPorIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarCaracteristicaPorIdResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarCaracteristicaPorId resultadoBuscarCaracteristicaPorId;

    public BuscarCaracteristicaPorIdResponse() {
    }

    public BuscarCaracteristicaPorIdResponse(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarCaracteristicaPorId resultadoBuscarCaracteristicaPorId) {
           this.resultadoBuscarCaracteristicaPorId = resultadoBuscarCaracteristicaPorId;
    }


    /**
     * Gets the resultadoBuscarCaracteristicaPorId value for this BuscarCaracteristicaPorIdResponse.
     * 
     * @return resultadoBuscarCaracteristicaPorId
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarCaracteristicaPorId getResultadoBuscarCaracteristicaPorId() {
        return resultadoBuscarCaracteristicaPorId;
    }


    /**
     * Sets the resultadoBuscarCaracteristicaPorId value for this BuscarCaracteristicaPorIdResponse.
     * 
     * @param resultadoBuscarCaracteristicaPorId
     */
    public void setResultadoBuscarCaracteristicaPorId(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoBuscarCaracteristicaPorId resultadoBuscarCaracteristicaPorId) {
        this.resultadoBuscarCaracteristicaPorId = resultadoBuscarCaracteristicaPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarCaracteristicaPorIdResponse)) return false;
        BuscarCaracteristicaPorIdResponse other = (BuscarCaracteristicaPorIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarCaracteristicaPorId==null && other.getResultadoBuscarCaracteristicaPorId()==null) || 
             (this.resultadoBuscarCaracteristicaPorId!=null &&
              this.resultadoBuscarCaracteristicaPorId.equals(other.getResultadoBuscarCaracteristicaPorId())));
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
        if (getResultadoBuscarCaracteristicaPorId() != null) {
            _hashCode += getResultadoBuscarCaracteristicaPorId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarCaracteristicaPorIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarCaracteristicaPorIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarCaracteristicaPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ResultadoBuscarCaracteristicaPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ResultadoBuscarCaracteristicaPorId"));
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
