/**
 * ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo  implements java.io.Serializable {
    private java.lang.String nmModelo;

    public ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo() {
    }

    public ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo(
           java.lang.String nmModelo) {
           this.nmModelo = nmModelo;
    }


    /**
     * Gets the nmModelo value for this ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo.
     * 
     * @return nmModelo
     */
    public java.lang.String getNmModelo() {
        return nmModelo;
    }


    /**
     * Sets the nmModelo value for this ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo.
     * 
     * @param nmModelo
     */
    public void setNmModelo(java.lang.String nmModelo) {
        this.nmModelo = nmModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo)) return false;
        ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo other = (ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nmModelo==null && other.getNmModelo()==null) || 
             (this.nmModelo!=null &&
              this.nmModelo.equals(other.getNmModelo())));
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
        if (getNmModelo() != null) {
            _hashCode += getNmModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ResultadoBuscarDetalhesModelo>BuscaCaracteristicaCodigoProdutoPorModeloOut>Modelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmModelo"));
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
