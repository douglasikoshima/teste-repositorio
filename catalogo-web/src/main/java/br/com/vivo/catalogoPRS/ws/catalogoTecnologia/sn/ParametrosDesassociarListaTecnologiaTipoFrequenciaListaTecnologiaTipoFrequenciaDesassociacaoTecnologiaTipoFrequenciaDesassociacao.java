/**
 * ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao  implements java.io.Serializable {
    private long idTecnologiaTpFrequencia;

    private java.lang.String justificativa;

    public ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao() {
    }

    public ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao(
           long idTecnologiaTpFrequencia,
           java.lang.String justificativa) {
           this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
           this.justificativa = justificativa;
    }


    /**
     * Gets the idTecnologiaTpFrequencia value for this ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao.
     * 
     * @return idTecnologiaTpFrequencia
     */
    public long getIdTecnologiaTpFrequencia() {
        return idTecnologiaTpFrequencia;
    }


    /**
     * Sets the idTecnologiaTpFrequencia value for this ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao.
     * 
     * @param idTecnologiaTpFrequencia
     */
    public void setIdTecnologiaTpFrequencia(long idTecnologiaTpFrequencia) {
        this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
    }


    /**
     * Gets the justificativa value for this ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao.
     * 
     * @return justificativa
     */
    public java.lang.String getJustificativa() {
        return justificativa;
    }


    /**
     * Sets the justificativa value for this ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao.
     * 
     * @param justificativa
     */
    public void setJustificativa(java.lang.String justificativa) {
        this.justificativa = justificativa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao)) return false;
        ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao other = (ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTecnologiaTpFrequencia == other.getIdTecnologiaTpFrequencia() &&
            ((this.justificativa==null && other.getJustificativa()==null) || 
             (this.justificativa!=null &&
              this.justificativa.equals(other.getJustificativa())));
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
        if (getJustificativa() != null) {
            _hashCode += getJustificativa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>>ParametrosDesassociarListaTecnologiaTipoFrequencia>ListaTecnologiaTipoFrequenciaDesassociacao>TecnologiaTipoFrequenciaDesassociacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologiaTpFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idTecnologiaTpFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("justificativa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "justificativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
