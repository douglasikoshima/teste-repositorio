/**
 * ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao  implements java.io.Serializable {
    private long idTecnologiaTpFrequencia;

    private long idTecnologia;

    private long idTipoFrequencia;

    private java.lang.String justificativa;

    public ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao() {
    }

    public ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao(
           long idTecnologiaTpFrequencia,
           long idTecnologia,
           long idTipoFrequencia,
           java.lang.String justificativa) {
           this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
           this.idTecnologia = idTecnologia;
           this.idTipoFrequencia = idTipoFrequencia;
           this.justificativa = justificativa;
    }


    /**
     * Gets the idTecnologiaTpFrequencia value for this ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.
     * 
     * @return idTecnologiaTpFrequencia
     */
    public long getIdTecnologiaTpFrequencia() {
        return idTecnologiaTpFrequencia;
    }


    /**
     * Sets the idTecnologiaTpFrequencia value for this ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.
     * 
     * @param idTecnologiaTpFrequencia
     */
    public void setIdTecnologiaTpFrequencia(long idTecnologiaTpFrequencia) {
        this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
    }


    /**
     * Gets the idTecnologia value for this ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.
     * 
     * @return idTecnologia
     */
    public long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the idTipoFrequencia value for this ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.
     * 
     * @return idTipoFrequencia
     */
    public long getIdTipoFrequencia() {
        return idTipoFrequencia;
    }


    /**
     * Sets the idTipoFrequencia value for this ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.
     * 
     * @param idTipoFrequencia
     */
    public void setIdTipoFrequencia(long idTipoFrequencia) {
        this.idTipoFrequencia = idTipoFrequencia;
    }


    /**
     * Gets the justificativa value for this ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.
     * 
     * @return justificativa
     */
    public java.lang.String getJustificativa() {
        return justificativa;
    }


    /**
     * Sets the justificativa value for this ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.
     * 
     * @param justificativa
     */
    public void setJustificativa(java.lang.String justificativa) {
        this.justificativa = justificativa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao)) return false;
        ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao other = (ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTecnologiaTpFrequencia == other.getIdTecnologiaTpFrequencia() &&
            this.idTecnologia == other.getIdTecnologia() &&
            this.idTipoFrequencia == other.getIdTipoFrequencia() &&
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
        _hashCode += new Long(getIdTecnologia()).hashCode();
        _hashCode += new Long(getIdTipoFrequencia()).hashCode();
        if (getJustificativa() != null) {
            _hashCode += getJustificativa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarTecnologiaTipoFrequenciaParametrosTecnologiaTipoFrequenciaAlteracao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>ParametrosAlterarTecnologiaTipoFrequencia>ParametrosTecnologiaTipoFrequenciaAlteracao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologiaTpFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idTecnologiaTpFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
