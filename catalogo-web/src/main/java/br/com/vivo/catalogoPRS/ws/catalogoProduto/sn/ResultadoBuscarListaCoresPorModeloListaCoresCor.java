/**
 * ResultadoBuscarListaCoresPorModeloListaCoresCor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ResultadoBuscarListaCoresPorModeloListaCoresCor  implements java.io.Serializable {
    private java.lang.Long idCor;

    private java.lang.String nmCor;

    private java.lang.String rgb;

    private java.lang.Long quantidade;

    public ResultadoBuscarListaCoresPorModeloListaCoresCor() {
    }

    public ResultadoBuscarListaCoresPorModeloListaCoresCor(
           java.lang.Long idCor,
           java.lang.String nmCor,
           java.lang.String rgb,
           java.lang.Long quantidade) {
           this.idCor = idCor;
           this.nmCor = nmCor;
           this.rgb = rgb;
           this.quantidade = quantidade;
    }


    /**
     * Gets the idCor value for this ResultadoBuscarListaCoresPorModeloListaCoresCor.
     * 
     * @return idCor
     */
    public java.lang.Long getIdCor() {
        return idCor;
    }


    /**
     * Sets the idCor value for this ResultadoBuscarListaCoresPorModeloListaCoresCor.
     * 
     * @param idCor
     */
    public void setIdCor(java.lang.Long idCor) {
        this.idCor = idCor;
    }


    /**
     * Gets the nmCor value for this ResultadoBuscarListaCoresPorModeloListaCoresCor.
     * 
     * @return nmCor
     */
    public java.lang.String getNmCor() {
        return nmCor;
    }


    /**
     * Sets the nmCor value for this ResultadoBuscarListaCoresPorModeloListaCoresCor.
     * 
     * @param nmCor
     */
    public void setNmCor(java.lang.String nmCor) {
        this.nmCor = nmCor;
    }


    /**
     * Gets the rgb value for this ResultadoBuscarListaCoresPorModeloListaCoresCor.
     * 
     * @return rgb
     */
    public java.lang.String getRgb() {
        return rgb;
    }


    /**
     * Sets the rgb value for this ResultadoBuscarListaCoresPorModeloListaCoresCor.
     * 
     * @param rgb
     */
    public void setRgb(java.lang.String rgb) {
        this.rgb = rgb;
    }


    /**
     * Gets the quantidade value for this ResultadoBuscarListaCoresPorModeloListaCoresCor.
     * 
     * @return quantidade
     */
    public java.lang.Long getQuantidade() {
        return quantidade;
    }


    /**
     * Sets the quantidade value for this ResultadoBuscarListaCoresPorModeloListaCoresCor.
     * 
     * @param quantidade
     */
    public void setQuantidade(java.lang.Long quantidade) {
        this.quantidade = quantidade;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaCoresPorModeloListaCoresCor)) return false;
        ResultadoBuscarListaCoresPorModeloListaCoresCor other = (ResultadoBuscarListaCoresPorModeloListaCoresCor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idCor==null && other.getIdCor()==null) || 
             (this.idCor!=null &&
              this.idCor.equals(other.getIdCor()))) &&
            ((this.nmCor==null && other.getNmCor()==null) || 
             (this.nmCor!=null &&
              this.nmCor.equals(other.getNmCor()))) &&
            ((this.rgb==null && other.getRgb()==null) || 
             (this.rgb!=null &&
              this.rgb.equals(other.getRgb()))) &&
            ((this.quantidade==null && other.getQuantidade()==null) || 
             (this.quantidade!=null &&
              this.quantidade.equals(other.getQuantidade())));
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
        if (getIdCor() != null) {
            _hashCode += getIdCor().hashCode();
        }
        if (getNmCor() != null) {
            _hashCode += getNmCor().hashCode();
        }
        if (getRgb() != null) {
            _hashCode += getRgb().hashCode();
        }
        if (getQuantidade() != null) {
            _hashCode += getQuantidade().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaCoresPorModeloListaCoresCor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaCoresPorModelo>ListaCores>Cor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idCor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "nmCor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rgb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "rgb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "quantidade"));
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
