/**
 * ValidarListaServicoPorIdRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ValidarListaServicoPorIdRequest  implements java.io.Serializable {
    private long[] parametrosValidarListaServicoPorId;

    public ValidarListaServicoPorIdRequest() {
    }

    public ValidarListaServicoPorIdRequest(
           long[] parametrosValidarListaServicoPorId) {
           this.parametrosValidarListaServicoPorId = parametrosValidarListaServicoPorId;
    }


    /**
     * Gets the parametrosValidarListaServicoPorId value for this ValidarListaServicoPorIdRequest.
     * 
     * @return parametrosValidarListaServicoPorId
     */
    public long[] getParametrosValidarListaServicoPorId() {
        return parametrosValidarListaServicoPorId;
    }


    /**
     * Sets the parametrosValidarListaServicoPorId value for this ValidarListaServicoPorIdRequest.
     * 
     * @param parametrosValidarListaServicoPorId
     */
    public void setParametrosValidarListaServicoPorId(long[] parametrosValidarListaServicoPorId) {
        this.parametrosValidarListaServicoPorId = parametrosValidarListaServicoPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidarListaServicoPorIdRequest)) return false;
        ValidarListaServicoPorIdRequest other = (ValidarListaServicoPorIdRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosValidarListaServicoPorId==null && other.getParametrosValidarListaServicoPorId()==null) || 
             (this.parametrosValidarListaServicoPorId!=null &&
              java.util.Arrays.equals(this.parametrosValidarListaServicoPorId, other.getParametrosValidarListaServicoPorId())));
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
        if (getParametrosValidarListaServicoPorId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParametrosValidarListaServicoPorId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParametrosValidarListaServicoPorId(), i);
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
        new org.apache.axis.description.TypeDesc(ValidarListaServicoPorIdRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">validarListaServicoPorIdRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosValidarListaServicoPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosValidarListaServicoPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosValidarListaServicoPorId"));
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
