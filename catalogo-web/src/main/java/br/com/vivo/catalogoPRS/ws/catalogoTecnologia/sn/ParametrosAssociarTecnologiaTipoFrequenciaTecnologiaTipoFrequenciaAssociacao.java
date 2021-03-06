/**
 * ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao  implements java.io.Serializable {
    private long idTecnologia;

    private long idTipoFrequencia;

    public ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao() {
    }

    public ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao(
           long idTecnologia,
           long idTipoFrequencia) {
           this.idTecnologia = idTecnologia;
           this.idTipoFrequencia = idTipoFrequencia;
    }


    /**
     * Gets the idTecnologia value for this ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao.
     * 
     * @return idTecnologia
     */
    public long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the idTipoFrequencia value for this ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao.
     * 
     * @return idTipoFrequencia
     */
    public long getIdTipoFrequencia() {
        return idTipoFrequencia;
    }


    /**
     * Sets the idTipoFrequencia value for this ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao.
     * 
     * @param idTipoFrequencia
     */
    public void setIdTipoFrequencia(long idTipoFrequencia) {
        this.idTipoFrequencia = idTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao)) return false;
        ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao other = (ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTecnologia == other.getIdTecnologia() &&
            this.idTipoFrequencia == other.getIdTipoFrequencia();
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
        _hashCode += new Long(getIdTecnologia()).hashCode();
        _hashCode += new Long(getIdTipoFrequencia()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>ParametrosAssociarTecnologiaTipoFrequencia>TecnologiaTipoFrequenciaAssociacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idTipoFrequencia"));
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
