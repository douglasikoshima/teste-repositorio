/**
 * ValidarListaPlanoPorIdRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ValidarListaPlanoPorIdRequest  implements java.io.Serializable {
    private long[] parametrosValidarListaPlanoPorId;

    public ValidarListaPlanoPorIdRequest() {
    }

    public ValidarListaPlanoPorIdRequest(
           long[] parametrosValidarListaPlanoPorId) {
           this.parametrosValidarListaPlanoPorId = parametrosValidarListaPlanoPorId;
    }


    /**
     * Gets the parametrosValidarListaPlanoPorId value for this ValidarListaPlanoPorIdRequest.
     * 
     * @return parametrosValidarListaPlanoPorId
     */
    public long[] getParametrosValidarListaPlanoPorId() {
        return parametrosValidarListaPlanoPorId;
    }


    /**
     * Sets the parametrosValidarListaPlanoPorId value for this ValidarListaPlanoPorIdRequest.
     * 
     * @param parametrosValidarListaPlanoPorId
     */
    public void setParametrosValidarListaPlanoPorId(long[] parametrosValidarListaPlanoPorId) {
        this.parametrosValidarListaPlanoPorId = parametrosValidarListaPlanoPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidarListaPlanoPorIdRequest)) return false;
        ValidarListaPlanoPorIdRequest other = (ValidarListaPlanoPorIdRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosValidarListaPlanoPorId==null && other.getParametrosValidarListaPlanoPorId()==null) || 
             (this.parametrosValidarListaPlanoPorId!=null &&
              java.util.Arrays.equals(this.parametrosValidarListaPlanoPorId, other.getParametrosValidarListaPlanoPorId())));
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
        if (getParametrosValidarListaPlanoPorId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParametrosValidarListaPlanoPorId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParametrosValidarListaPlanoPorId(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidarListaPlanoPorIdRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">validarListaPlanoPorIdRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosValidarListaPlanoPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ParametrosValidarListaPlanoPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParametrosValidarListaPlanoPorId"));
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
