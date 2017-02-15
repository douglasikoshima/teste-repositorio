/**
 * ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL  implements java.io.Serializable {
    private java.lang.String tecnologia;

    private java.lang.String tipoFrequencia;

    private java.lang.String vlFrequencia;

    public ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL() {
    }

    public ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL(
           java.lang.String tecnologia,
           java.lang.String tipoFrequencia,
           java.lang.String vlFrequencia) {
           this.tecnologia = tecnologia;
           this.tipoFrequencia = tipoFrequencia;
           this.vlFrequencia = vlFrequencia;
    }


    /**
     * Gets the tecnologia value for this ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL.
     * 
     * @return tecnologia
     */
    public java.lang.String getTecnologia() {
        return tecnologia;
    }


    /**
     * Sets the tecnologia value for this ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL.
     * 
     * @param tecnologia
     */
    public void setTecnologia(java.lang.String tecnologia) {
        this.tecnologia = tecnologia;
    }


    /**
     * Gets the tipoFrequencia value for this ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL.
     * 
     * @return tipoFrequencia
     */
    public java.lang.String getTipoFrequencia() {
        return tipoFrequencia;
    }


    /**
     * Sets the tipoFrequencia value for this ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL.
     * 
     * @param tipoFrequencia
     */
    public void setTipoFrequencia(java.lang.String tipoFrequencia) {
        this.tipoFrequencia = tipoFrequencia;
    }


    /**
     * Gets the vlFrequencia value for this ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL.
     * 
     * @return vlFrequencia
     */
    public java.lang.String getVlFrequencia() {
        return vlFrequencia;
    }


    /**
     * Sets the vlFrequencia value for this ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL.
     * 
     * @param vlFrequencia
     */
    public void setVlFrequencia(java.lang.String vlFrequencia) {
        this.vlFrequencia = vlFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL)) return false;
        ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL other = (ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tecnologia==null && other.getTecnologia()==null) || 
             (this.tecnologia!=null &&
              this.tecnologia.equals(other.getTecnologia()))) &&
            ((this.tipoFrequencia==null && other.getTipoFrequencia()==null) || 
             (this.tipoFrequencia!=null &&
              this.tipoFrequencia.equals(other.getTipoFrequencia()))) &&
            ((this.vlFrequencia==null && other.getVlFrequencia()==null) || 
             (this.vlFrequencia!=null &&
              this.vlFrequencia.equals(other.getVlFrequencia())));
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
        if (getTecnologia() != null) {
            _hashCode += getTecnologia().hashCode();
        }
        if (getTipoFrequencia() != null) {
            _hashCode += getTipoFrequencia().hashCode();
        }
        if (getVlFrequencia() != null) {
            _hashCode += getVlFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoExportarModelo>Modelo>ListaTecnologiaTpFrequenciaVL>TecnologiaTpFrequenciaVL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "tecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "tipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vlFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "vlFrequencia"));
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
