/**
 * ParametrosBuscarModeloPorId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosBuscarModeloPorId  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloPorIdParametrosModelosPorIdModeloIn parametrosModelosPorIdModeloIn;

    public ParametrosBuscarModeloPorId() {
    }

    public ParametrosBuscarModeloPorId(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloPorIdParametrosModelosPorIdModeloIn parametrosModelosPorIdModeloIn) {
           this.parametrosModelosPorIdModeloIn = parametrosModelosPorIdModeloIn;
    }


    /**
     * Gets the parametrosModelosPorIdModeloIn value for this ParametrosBuscarModeloPorId.
     * 
     * @return parametrosModelosPorIdModeloIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloPorIdParametrosModelosPorIdModeloIn getParametrosModelosPorIdModeloIn() {
        return parametrosModelosPorIdModeloIn;
    }


    /**
     * Sets the parametrosModelosPorIdModeloIn value for this ParametrosBuscarModeloPorId.
     * 
     * @param parametrosModelosPorIdModeloIn
     */
    public void setParametrosModelosPorIdModeloIn(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloPorIdParametrosModelosPorIdModeloIn parametrosModelosPorIdModeloIn) {
        this.parametrosModelosPorIdModeloIn = parametrosModelosPorIdModeloIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarModeloPorId)) return false;
        ParametrosBuscarModeloPorId other = (ParametrosBuscarModeloPorId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosModelosPorIdModeloIn==null && other.getParametrosModelosPorIdModeloIn()==null) || 
             (this.parametrosModelosPorIdModeloIn!=null &&
              this.parametrosModelosPorIdModeloIn.equals(other.getParametrosModelosPorIdModeloIn())));
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
        if (getParametrosModelosPorIdModeloIn() != null) {
            _hashCode += getParametrosModelosPorIdModeloIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarModeloPorId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarModeloPorId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosModelosPorIdModeloIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosModelosPorIdModeloIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosBuscarModeloPorId>ParametrosModelosPorIdModeloIn"));
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
