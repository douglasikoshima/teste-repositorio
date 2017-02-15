/**
 * BuscarListaProdutoPorModeloResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class BuscarListaProdutoPorModeloResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModelo resultadoBuscarListaProdutoPorModelo;

    public BuscarListaProdutoPorModeloResponse() {
    }

    public BuscarListaProdutoPorModeloResponse(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModelo resultadoBuscarListaProdutoPorModelo) {
           this.resultadoBuscarListaProdutoPorModelo = resultadoBuscarListaProdutoPorModelo;
    }


    /**
     * Gets the resultadoBuscarListaProdutoPorModelo value for this BuscarListaProdutoPorModeloResponse.
     * 
     * @return resultadoBuscarListaProdutoPorModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModelo getResultadoBuscarListaProdutoPorModelo() {
        return resultadoBuscarListaProdutoPorModelo;
    }


    /**
     * Sets the resultadoBuscarListaProdutoPorModelo value for this BuscarListaProdutoPorModeloResponse.
     * 
     * @param resultadoBuscarListaProdutoPorModelo
     */
    public void setResultadoBuscarListaProdutoPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModelo resultadoBuscarListaProdutoPorModelo) {
        this.resultadoBuscarListaProdutoPorModelo = resultadoBuscarListaProdutoPorModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaProdutoPorModeloResponse)) return false;
        BuscarListaProdutoPorModeloResponse other = (BuscarListaProdutoPorModeloResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaProdutoPorModelo==null && other.getResultadoBuscarListaProdutoPorModelo()==null) || 
             (this.resultadoBuscarListaProdutoPorModelo!=null &&
              this.resultadoBuscarListaProdutoPorModelo.equals(other.getResultadoBuscarListaProdutoPorModelo())));
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
        if (getResultadoBuscarListaProdutoPorModelo() != null) {
            _hashCode += getResultadoBuscarListaProdutoPorModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaProdutoPorModeloResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoPorModeloResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaProdutoPorModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ResultadoBuscarListaProdutoPorModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaProdutoPorModelo"));
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
