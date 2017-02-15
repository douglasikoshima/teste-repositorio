/**
 * DisponibilizarProdutoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class DisponibilizarProdutoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProduto parametrosDisponibilizarProduto;

    public DisponibilizarProdutoRequest() {
    }

    public DisponibilizarProdutoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProduto parametrosDisponibilizarProduto) {
           this.parametrosDisponibilizarProduto = parametrosDisponibilizarProduto;
    }


    /**
     * Gets the parametrosDisponibilizarProduto value for this DisponibilizarProdutoRequest.
     * 
     * @return parametrosDisponibilizarProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProduto getParametrosDisponibilizarProduto() {
        return parametrosDisponibilizarProduto;
    }


    /**
     * Sets the parametrosDisponibilizarProduto value for this DisponibilizarProdutoRequest.
     * 
     * @param parametrosDisponibilizarProduto
     */
    public void setParametrosDisponibilizarProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProduto parametrosDisponibilizarProduto) {
        this.parametrosDisponibilizarProduto = parametrosDisponibilizarProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DisponibilizarProdutoRequest)) return false;
        DisponibilizarProdutoRequest other = (DisponibilizarProdutoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosDisponibilizarProduto==null && other.getParametrosDisponibilizarProduto()==null) || 
             (this.parametrosDisponibilizarProduto!=null &&
              this.parametrosDisponibilizarProduto.equals(other.getParametrosDisponibilizarProduto())));
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
        if (getParametrosDisponibilizarProduto() != null) {
            _hashCode += getParametrosDisponibilizarProduto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DisponibilizarProdutoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">disponibilizarProdutoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosDisponibilizarProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosDisponibilizarProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosDisponibilizarProduto"));
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
