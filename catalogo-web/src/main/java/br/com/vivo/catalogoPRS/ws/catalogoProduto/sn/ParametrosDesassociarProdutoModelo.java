/**
 * ParametrosDesassociarProdutoModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosDesassociarProdutoModelo  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModeloListaProdutoModeloDesassociacaoProdutoModeloDesassociacao[] listaProdutoModeloDesassociacao;

    public ParametrosDesassociarProdutoModelo() {
    }

    public ParametrosDesassociarProdutoModelo(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModeloListaProdutoModeloDesassociacaoProdutoModeloDesassociacao[] listaProdutoModeloDesassociacao) {
           this.listaProdutoModeloDesassociacao = listaProdutoModeloDesassociacao;
    }


    /**
     * Gets the listaProdutoModeloDesassociacao value for this ParametrosDesassociarProdutoModelo.
     * 
     * @return listaProdutoModeloDesassociacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModeloListaProdutoModeloDesassociacaoProdutoModeloDesassociacao[] getListaProdutoModeloDesassociacao() {
        return listaProdutoModeloDesassociacao;
    }


    /**
     * Sets the listaProdutoModeloDesassociacao value for this ParametrosDesassociarProdutoModelo.
     * 
     * @param listaProdutoModeloDesassociacao
     */
    public void setListaProdutoModeloDesassociacao(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModeloListaProdutoModeloDesassociacaoProdutoModeloDesassociacao[] listaProdutoModeloDesassociacao) {
        this.listaProdutoModeloDesassociacao = listaProdutoModeloDesassociacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDesassociarProdutoModelo)) return false;
        ParametrosDesassociarProdutoModelo other = (ParametrosDesassociarProdutoModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaProdutoModeloDesassociacao==null && other.getListaProdutoModeloDesassociacao()==null) || 
             (this.listaProdutoModeloDesassociacao!=null &&
              java.util.Arrays.equals(this.listaProdutoModeloDesassociacao, other.getListaProdutoModeloDesassociacao())));
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
        if (getListaProdutoModeloDesassociacao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaProdutoModeloDesassociacao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaProdutoModeloDesassociacao(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosDesassociarProdutoModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosDesassociarProdutoModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaProdutoModeloDesassociacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ListaProdutoModeloDesassociacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosDesassociarProdutoModelo>ListaProdutoModeloDesassociacao>ProdutoModeloDesassociacao"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ProdutoModeloDesassociacao"));
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
