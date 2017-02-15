/**
 * BuscarQuantidadeModeloPorProdutoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class BuscarQuantidadeModeloPorProdutoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarQuantidadeProdutoPorModelo parametrosBuscarQuantidadeProdutoPorModelo;

    public BuscarQuantidadeModeloPorProdutoRequest() {
    }

    public BuscarQuantidadeModeloPorProdutoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarQuantidadeProdutoPorModelo parametrosBuscarQuantidadeProdutoPorModelo) {
           this.parametrosBuscarQuantidadeProdutoPorModelo = parametrosBuscarQuantidadeProdutoPorModelo;
    }


    /**
     * Gets the parametrosBuscarQuantidadeProdutoPorModelo value for this BuscarQuantidadeModeloPorProdutoRequest.
     * 
     * @return parametrosBuscarQuantidadeProdutoPorModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarQuantidadeProdutoPorModelo getParametrosBuscarQuantidadeProdutoPorModelo() {
        return parametrosBuscarQuantidadeProdutoPorModelo;
    }


    /**
     * Sets the parametrosBuscarQuantidadeProdutoPorModelo value for this BuscarQuantidadeModeloPorProdutoRequest.
     * 
     * @param parametrosBuscarQuantidadeProdutoPorModelo
     */
    public void setParametrosBuscarQuantidadeProdutoPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarQuantidadeProdutoPorModelo parametrosBuscarQuantidadeProdutoPorModelo) {
        this.parametrosBuscarQuantidadeProdutoPorModelo = parametrosBuscarQuantidadeProdutoPorModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarQuantidadeModeloPorProdutoRequest)) return false;
        BuscarQuantidadeModeloPorProdutoRequest other = (BuscarQuantidadeModeloPorProdutoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarQuantidadeProdutoPorModelo==null && other.getParametrosBuscarQuantidadeProdutoPorModelo()==null) || 
             (this.parametrosBuscarQuantidadeProdutoPorModelo!=null &&
              this.parametrosBuscarQuantidadeProdutoPorModelo.equals(other.getParametrosBuscarQuantidadeProdutoPorModelo())));
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
        if (getParametrosBuscarQuantidadeProdutoPorModelo() != null) {
            _hashCode += getParametrosBuscarQuantidadeProdutoPorModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarQuantidadeModeloPorProdutoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarQuantidadeModeloPorProdutoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarQuantidadeProdutoPorModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosBuscarQuantidadeProdutoPorModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosBuscarQuantidadeProdutoPorModelo"));
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
