/**
 * ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn  implements java.io.Serializable {
    private long idTipoFrequencia;

    private long idTecnologia;

    public ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn() {
    }

    public ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn(
           long idTipoFrequencia,
           long idTecnologia) {
           this.idTipoFrequencia = idTipoFrequencia;
           this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the idTipoFrequencia value for this ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn.
     * 
     * @return idTipoFrequencia
     */
    public long getIdTipoFrequencia() {
        return idTipoFrequencia;
    }


    /**
     * Sets the idTipoFrequencia value for this ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn.
     * 
     * @param idTipoFrequencia
     */
    public void setIdTipoFrequencia(long idTipoFrequencia) {
        this.idTipoFrequencia = idTipoFrequencia;
    }


    /**
     * Gets the idTecnologia value for this ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn.
     * 
     * @return idTecnologia
     */
    public long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn)) return false;
        ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn other = (ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTipoFrequencia == other.getIdTipoFrequencia() &&
            this.idTecnologia == other.getIdTecnologia();
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
        _hashCode += new Long(getIdTipoFrequencia()).hashCode();
        _hashCode += new Long(getIdTecnologia()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloPorTecnologiaTipoFrequenciaParametrosModeloPorTecnologiaTpFrequenciaIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia>ParametrosModeloPorTecnologiaTpFrequenciaIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "idTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "idTecnologia"));
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
