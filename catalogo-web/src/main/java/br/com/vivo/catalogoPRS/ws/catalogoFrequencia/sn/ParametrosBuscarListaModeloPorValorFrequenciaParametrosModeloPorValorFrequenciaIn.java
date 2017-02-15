/**
 * ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn  implements java.io.Serializable {
    private long idValorFrequencia;

    public ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn() {
    }

    public ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn(
           long idValorFrequencia) {
           this.idValorFrequencia = idValorFrequencia;
    }


    /**
     * Gets the idValorFrequencia value for this ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn.
     * 
     * @return idValorFrequencia
     */
    public long getIdValorFrequencia() {
        return idValorFrequencia;
    }


    /**
     * Sets the idValorFrequencia value for this ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn.
     * 
     * @param idValorFrequencia
     */
    public void setIdValorFrequencia(long idValorFrequencia) {
        this.idValorFrequencia = idValorFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn)) return false;
        ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn other = (ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idValorFrequencia == other.getIdValorFrequencia();
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
        _hashCode += new Long(getIdValorFrequencia()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloPorValorFrequenciaParametrosModeloPorValorFrequenciaIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ParametrosBuscarListaModeloPorValorFrequencia>ParametrosModeloPorValorFrequenciaIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idValorFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "idValorFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
