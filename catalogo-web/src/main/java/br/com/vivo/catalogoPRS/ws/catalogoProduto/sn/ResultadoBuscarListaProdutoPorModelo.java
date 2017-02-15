/**
 * ResultadoBuscarListaProdutoPorModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ResultadoBuscarListaProdutoPorModelo  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModeloListaProdutoProduto[] listaProduto;

    public ResultadoBuscarListaProdutoPorModelo() {
    }

    public ResultadoBuscarListaProdutoPorModelo(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModeloListaProdutoProduto[] listaProduto) {
           this.listaProduto = listaProduto;
    }


    /**
     * Gets the listaProduto value for this ResultadoBuscarListaProdutoPorModelo.
     * 
     * @return listaProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModeloListaProdutoProduto[] getListaProduto() {
        return listaProduto;
    }


    /**
     * Sets the listaProduto value for this ResultadoBuscarListaProdutoPorModelo.
     * 
     * @param listaProduto
     */
    public void setListaProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModeloListaProdutoProduto[] listaProduto) {
        this.listaProduto = listaProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaProdutoPorModelo)) return false;
        ResultadoBuscarListaProdutoPorModelo other = (ResultadoBuscarListaProdutoPorModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaProduto==null && other.getListaProduto()==null) || 
             (this.listaProduto!=null &&
              java.util.Arrays.equals(this.listaProduto, other.getListaProduto())));
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
        if (getListaProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaProduto(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaProdutoPorModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaProdutoPorModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ListaProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaProdutoPorModelo>ListaProduto>Produto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "Produto"));
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
