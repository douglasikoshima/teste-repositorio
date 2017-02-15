/**
 * ParametrosExcluirValorFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ParametrosExcluirValorFrequencia  implements java.io.Serializable {
    private long idValorFrequencia;

    private java.lang.String nmUsuarioAlteracao;

    private java.lang.String justificativa;

    public ParametrosExcluirValorFrequencia() {
    }

    public ParametrosExcluirValorFrequencia(
           long idValorFrequencia,
           java.lang.String nmUsuarioAlteracao,
           java.lang.String justificativa) {
           this.idValorFrequencia = idValorFrequencia;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.justificativa = justificativa;
    }


    /**
     * Gets the idValorFrequencia value for this ParametrosExcluirValorFrequencia.
     * 
     * @return idValorFrequencia
     */
    public long getIdValorFrequencia() {
        return idValorFrequencia;
    }


    /**
     * Sets the idValorFrequencia value for this ParametrosExcluirValorFrequencia.
     * 
     * @param idValorFrequencia
     */
    public void setIdValorFrequencia(long idValorFrequencia) {
        this.idValorFrequencia = idValorFrequencia;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ParametrosExcluirValorFrequencia.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ParametrosExcluirValorFrequencia.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the justificativa value for this ParametrosExcluirValorFrequencia.
     * 
     * @return justificativa
     */
    public java.lang.String getJustificativa() {
        return justificativa;
    }


    /**
     * Sets the justificativa value for this ParametrosExcluirValorFrequencia.
     * 
     * @param justificativa
     */
    public void setJustificativa(java.lang.String justificativa) {
        this.justificativa = justificativa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosExcluirValorFrequencia)) return false;
        ParametrosExcluirValorFrequencia other = (ParametrosExcluirValorFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idValorFrequencia == other.getIdValorFrequencia() &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
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
        _hashCode += new Long(getIdValorFrequencia()).hashCode();
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getJustificativa() != null) {
            _hashCode += getJustificativa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosExcluirValorFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosExcluirValorFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idValorFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "idValorFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("justificativa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "justificativa"));
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
