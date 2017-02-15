/**
 * ParametrosBuscarListaProduto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosBuscarListaProduto  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosProdutosIn parametrosProdutosIn;

    public ParametrosBuscarListaProduto() {
    }

    public ParametrosBuscarListaProduto(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosProdutosIn parametrosProdutosIn) {
           this.parametrosProdutosIn = parametrosProdutosIn;
    }


    /**
     * Gets the parametrosProdutosIn value for this ParametrosBuscarListaProduto.
     * 
     * @return parametrosProdutosIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosProdutosIn getParametrosProdutosIn() {
        return parametrosProdutosIn;
    }


    /**
     * Sets the parametrosProdutosIn value for this ParametrosBuscarListaProduto.
     * 
     * @param parametrosProdutosIn
     */
    public void setParametrosProdutosIn(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosProdutosIn parametrosProdutosIn) {
        this.parametrosProdutosIn = parametrosProdutosIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaProduto)) return false;
        ParametrosBuscarListaProduto other = (ParametrosBuscarListaProduto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosProdutosIn==null && other.getParametrosProdutosIn()==null) || 
             (this.parametrosProdutosIn!=null &&
              this.parametrosProdutosIn.equals(other.getParametrosProdutosIn())));
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
        if (getParametrosProdutosIn() != null) {
            _hashCode += getParametrosProdutosIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaProduto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosBuscarListaProduto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosProdutosIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosProdutosIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosProdutosIn"));
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
