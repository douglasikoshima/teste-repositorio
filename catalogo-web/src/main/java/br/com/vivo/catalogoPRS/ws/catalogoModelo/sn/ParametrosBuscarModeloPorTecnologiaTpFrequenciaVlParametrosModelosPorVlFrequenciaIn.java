/**
 * ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn  implements java.io.Serializable {
    private long idTecnologiaTpFrequenciaVl;

    public ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn() {
    }

    public ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn(
           long idTecnologiaTpFrequenciaVl) {
           this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
    }


    /**
     * Gets the idTecnologiaTpFrequenciaVl value for this ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn.
     * 
     * @return idTecnologiaTpFrequenciaVl
     */
    public long getIdTecnologiaTpFrequenciaVl() {
        return idTecnologiaTpFrequenciaVl;
    }


    /**
     * Sets the idTecnologiaTpFrequenciaVl value for this ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn.
     * 
     * @param idTecnologiaTpFrequenciaVl
     */
    public void setIdTecnologiaTpFrequenciaVl(long idTecnologiaTpFrequenciaVl) {
        this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn)) return false;
        ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn other = (ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTecnologiaTpFrequenciaVl == other.getIdTecnologiaTpFrequenciaVl();
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
        _hashCode += new Long(getIdTecnologiaTpFrequenciaVl()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarModeloPorTecnologiaTpFrequenciaVlParametrosModelosPorVlFrequenciaIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosBuscarModeloPorTecnologiaTpFrequenciaVl>ParametrosModelosPorVlFrequenciaIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologiaTpFrequenciaVl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologiaTpFrequenciaVl"));
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
