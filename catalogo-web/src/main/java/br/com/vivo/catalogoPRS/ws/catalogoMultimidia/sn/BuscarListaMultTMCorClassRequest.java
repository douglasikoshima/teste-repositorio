/**
 * BuscarListaMultTMCorClassRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class BuscarListaMultTMCorClassRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamBuscarListaMultTMCorClass paramBuscarListaMultTMCorClass;

    public BuscarListaMultTMCorClassRequest() {
    }

    public BuscarListaMultTMCorClassRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamBuscarListaMultTMCorClass paramBuscarListaMultTMCorClass) {
           this.paramBuscarListaMultTMCorClass = paramBuscarListaMultTMCorClass;
    }


    /**
     * Gets the paramBuscarListaMultTMCorClass value for this BuscarListaMultTMCorClassRequest.
     * 
     * @return paramBuscarListaMultTMCorClass
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamBuscarListaMultTMCorClass getParamBuscarListaMultTMCorClass() {
        return paramBuscarListaMultTMCorClass;
    }


    /**
     * Sets the paramBuscarListaMultTMCorClass value for this BuscarListaMultTMCorClassRequest.
     * 
     * @param paramBuscarListaMultTMCorClass
     */
    public void setParamBuscarListaMultTMCorClass(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamBuscarListaMultTMCorClass paramBuscarListaMultTMCorClass) {
        this.paramBuscarListaMultTMCorClass = paramBuscarListaMultTMCorClass;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaMultTMCorClassRequest)) return false;
        BuscarListaMultTMCorClassRequest other = (BuscarListaMultTMCorClassRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paramBuscarListaMultTMCorClass==null && other.getParamBuscarListaMultTMCorClass()==null) || 
             (this.paramBuscarListaMultTMCorClass!=null &&
              this.paramBuscarListaMultTMCorClass.equals(other.getParamBuscarListaMultTMCorClass())));
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
        if (getParamBuscarListaMultTMCorClass() != null) {
            _hashCode += getParamBuscarListaMultTMCorClass().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaMultTMCorClassRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">buscarListaMultTMCorClassRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramBuscarListaMultTMCorClass");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ParamBuscarListaMultTMCorClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ParamBuscarListaMultTMCorClass"));
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
