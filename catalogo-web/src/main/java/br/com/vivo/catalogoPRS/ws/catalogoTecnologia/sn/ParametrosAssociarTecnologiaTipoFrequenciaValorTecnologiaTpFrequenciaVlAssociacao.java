/**
 * ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao  implements java.io.Serializable {
    private long idTecnologiaTpFrequencia;

    private long idVlFrequencia;

    public ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao() {
    }

    public ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao(
           long idTecnologiaTpFrequencia,
           long idVlFrequencia) {
           this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
           this.idVlFrequencia = idVlFrequencia;
    }


    /**
     * Gets the idTecnologiaTpFrequencia value for this ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao.
     * 
     * @return idTecnologiaTpFrequencia
     */
    public long getIdTecnologiaTpFrequencia() {
        return idTecnologiaTpFrequencia;
    }


    /**
     * Sets the idTecnologiaTpFrequencia value for this ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao.
     * 
     * @param idTecnologiaTpFrequencia
     */
    public void setIdTecnologiaTpFrequencia(long idTecnologiaTpFrequencia) {
        this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
    }


    /**
     * Gets the idVlFrequencia value for this ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao.
     * 
     * @return idVlFrequencia
     */
    public long getIdVlFrequencia() {
        return idVlFrequencia;
    }


    /**
     * Sets the idVlFrequencia value for this ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao.
     * 
     * @param idVlFrequencia
     */
    public void setIdVlFrequencia(long idVlFrequencia) {
        this.idVlFrequencia = idVlFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao)) return false;
        ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao other = (ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTecnologiaTpFrequencia == other.getIdTecnologiaTpFrequencia() &&
            this.idVlFrequencia == other.getIdVlFrequencia();
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
        _hashCode += new Long(getIdTecnologiaTpFrequencia()).hashCode();
        _hashCode += new Long(getIdVlFrequencia()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAssociarTecnologiaTipoFrequenciaValorTecnologiaTpFrequenciaVlAssociacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>ParametrosAssociarTecnologiaTipoFrequenciaValor>TecnologiaTpFrequenciaVlAssociacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologiaTpFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idTecnologiaTpFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idVlFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idVlFrequencia"));
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
