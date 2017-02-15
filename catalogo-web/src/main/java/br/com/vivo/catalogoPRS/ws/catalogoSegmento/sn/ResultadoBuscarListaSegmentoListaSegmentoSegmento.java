/**
 * ResultadoBuscarListaSegmentoListaSegmentoSegmento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn;

public class ResultadoBuscarListaSegmentoListaSegmentoSegmento  implements java.io.Serializable {
    private java.lang.String sgSegmento;

    private java.lang.String dsSegmento;

    public ResultadoBuscarListaSegmentoListaSegmentoSegmento() {
    }

    public ResultadoBuscarListaSegmentoListaSegmentoSegmento(
           java.lang.String sgSegmento,
           java.lang.String dsSegmento) {
           this.sgSegmento = sgSegmento;
           this.dsSegmento = dsSegmento;
    }


    /**
     * Gets the sgSegmento value for this ResultadoBuscarListaSegmentoListaSegmentoSegmento.
     * 
     * @return sgSegmento
     */
    public java.lang.String getSgSegmento() {
        return sgSegmento;
    }


    /**
     * Sets the sgSegmento value for this ResultadoBuscarListaSegmentoListaSegmentoSegmento.
     * 
     * @param sgSegmento
     */
    public void setSgSegmento(java.lang.String sgSegmento) {
        this.sgSegmento = sgSegmento;
    }


    /**
     * Gets the dsSegmento value for this ResultadoBuscarListaSegmentoListaSegmentoSegmento.
     * 
     * @return dsSegmento
     */
    public java.lang.String getDsSegmento() {
        return dsSegmento;
    }


    /**
     * Sets the dsSegmento value for this ResultadoBuscarListaSegmentoListaSegmentoSegmento.
     * 
     * @param dsSegmento
     */
    public void setDsSegmento(java.lang.String dsSegmento) {
        this.dsSegmento = dsSegmento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaSegmentoListaSegmentoSegmento)) return false;
        ResultadoBuscarListaSegmentoListaSegmentoSegmento other = (ResultadoBuscarListaSegmentoListaSegmentoSegmento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sgSegmento==null && other.getSgSegmento()==null) || 
             (this.sgSegmento!=null &&
              this.sgSegmento.equals(other.getSgSegmento()))) &&
            ((this.dsSegmento==null && other.getDsSegmento()==null) || 
             (this.dsSegmento!=null &&
              this.dsSegmento.equals(other.getDsSegmento())));
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
        if (getSgSegmento() != null) {
            _hashCode += getSgSegmento().hashCode();
        }
        if (getDsSegmento() != null) {
            _hashCode += getDsSegmento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaSegmentoListaSegmentoSegmento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoSegmento", ">>>ResultadoBuscarListaSegmento>ListaSegmento>Segmento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgSegmento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoSegmento", "sgSegmento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsSegmento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoSegmento", "dsSegmento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
