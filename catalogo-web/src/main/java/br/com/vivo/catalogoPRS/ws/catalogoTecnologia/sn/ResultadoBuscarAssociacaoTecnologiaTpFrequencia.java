/**
 * ResultadoBuscarAssociacaoTecnologiaTpFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ResultadoBuscarAssociacaoTecnologiaTpFrequencia  implements java.io.Serializable {
    private java.lang.Long idTecnologiaTpFrequencia;

    private java.lang.Long idTecnologia;

    private java.lang.Long idTipoFrequencia;

    public ResultadoBuscarAssociacaoTecnologiaTpFrequencia() {
    }

    public ResultadoBuscarAssociacaoTecnologiaTpFrequencia(
           java.lang.Long idTecnologiaTpFrequencia,
           java.lang.Long idTecnologia,
           java.lang.Long idTipoFrequencia) {
           this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
           this.idTecnologia = idTecnologia;
           this.idTipoFrequencia = idTipoFrequencia;
    }


    /**
     * Gets the idTecnologiaTpFrequencia value for this ResultadoBuscarAssociacaoTecnologiaTpFrequencia.
     * 
     * @return idTecnologiaTpFrequencia
     */
    public java.lang.Long getIdTecnologiaTpFrequencia() {
        return idTecnologiaTpFrequencia;
    }


    /**
     * Sets the idTecnologiaTpFrequencia value for this ResultadoBuscarAssociacaoTecnologiaTpFrequencia.
     * 
     * @param idTecnologiaTpFrequencia
     */
    public void setIdTecnologiaTpFrequencia(java.lang.Long idTecnologiaTpFrequencia) {
        this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
    }


    /**
     * Gets the idTecnologia value for this ResultadoBuscarAssociacaoTecnologiaTpFrequencia.
     * 
     * @return idTecnologia
     */
    public java.lang.Long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ResultadoBuscarAssociacaoTecnologiaTpFrequencia.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(java.lang.Long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the idTipoFrequencia value for this ResultadoBuscarAssociacaoTecnologiaTpFrequencia.
     * 
     * @return idTipoFrequencia
     */
    public java.lang.Long getIdTipoFrequencia() {
        return idTipoFrequencia;
    }


    /**
     * Sets the idTipoFrequencia value for this ResultadoBuscarAssociacaoTecnologiaTpFrequencia.
     * 
     * @param idTipoFrequencia
     */
    public void setIdTipoFrequencia(java.lang.Long idTipoFrequencia) {
        this.idTipoFrequencia = idTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarAssociacaoTecnologiaTpFrequencia)) return false;
        ResultadoBuscarAssociacaoTecnologiaTpFrequencia other = (ResultadoBuscarAssociacaoTecnologiaTpFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idTecnologiaTpFrequencia==null && other.getIdTecnologiaTpFrequencia()==null) || 
             (this.idTecnologiaTpFrequencia!=null &&
              this.idTecnologiaTpFrequencia.equals(other.getIdTecnologiaTpFrequencia()))) &&
            ((this.idTecnologia==null && other.getIdTecnologia()==null) || 
             (this.idTecnologia!=null &&
              this.idTecnologia.equals(other.getIdTecnologia()))) &&
            ((this.idTipoFrequencia==null && other.getIdTipoFrequencia()==null) || 
             (this.idTipoFrequencia!=null &&
              this.idTipoFrequencia.equals(other.getIdTipoFrequencia())));
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
        if (getIdTecnologiaTpFrequencia() != null) {
            _hashCode += getIdTecnologiaTpFrequencia().hashCode();
        }
        if (getIdTecnologia() != null) {
            _hashCode += getIdTecnologia().hashCode();
        }
        if (getIdTipoFrequencia() != null) {
            _hashCode += getIdTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarAssociacaoTecnologiaTpFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ResultadoBuscarAssociacaoTecnologiaTpFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologiaTpFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idTecnologiaTpFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
