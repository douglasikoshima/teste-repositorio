/**
 * BuscarListaProdutoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class BuscarListaProdutoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarListaProduto parametrosBuscarListaProduto;

    public BuscarListaProdutoRequest() {
    }

    public BuscarListaProdutoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarListaProduto parametrosBuscarListaProduto) {
           this.parametrosBuscarListaProduto = parametrosBuscarListaProduto;
    }


    /**
     * Gets the parametrosBuscarListaProduto value for this BuscarListaProdutoRequest.
     * 
     * @return parametrosBuscarListaProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarListaProduto getParametrosBuscarListaProduto() {
        return parametrosBuscarListaProduto;
    }


    /**
     * Sets the parametrosBuscarListaProduto value for this BuscarListaProdutoRequest.
     * 
     * @param parametrosBuscarListaProduto
     */
    public void setParametrosBuscarListaProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarListaProduto parametrosBuscarListaProduto) {
        this.parametrosBuscarListaProduto = parametrosBuscarListaProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaProdutoRequest)) return false;
        BuscarListaProdutoRequest other = (BuscarListaProdutoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaProduto==null && other.getParametrosBuscarListaProduto()==null) || 
             (this.parametrosBuscarListaProduto!=null &&
              this.parametrosBuscarListaProduto.equals(other.getParametrosBuscarListaProduto())));
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
        if (getParametrosBuscarListaProduto() != null) {
            _hashCode += getParametrosBuscarListaProduto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaProdutoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosBuscarListaProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosBuscarListaProduto"));
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
