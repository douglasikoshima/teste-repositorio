/**
 * ParametrosDesabilitarVariaveis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosDesabilitarVariaveis  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveisListaDesassociaProdutoModeloDesassociacaoProdutoModelo[] listaDesassociaProdutoModelo;

    public ParametrosDesabilitarVariaveis() {
    }

    public ParametrosDesabilitarVariaveis(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveisListaDesassociaProdutoModeloDesassociacaoProdutoModelo[] listaDesassociaProdutoModelo) {
           this.listaDesassociaProdutoModelo = listaDesassociaProdutoModelo;
    }


    /**
     * Gets the listaDesassociaProdutoModelo value for this ParametrosDesabilitarVariaveis.
     * 
     * @return listaDesassociaProdutoModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveisListaDesassociaProdutoModeloDesassociacaoProdutoModelo[] getListaDesassociaProdutoModelo() {
        return listaDesassociaProdutoModelo;
    }


    /**
     * Sets the listaDesassociaProdutoModelo value for this ParametrosDesabilitarVariaveis.
     * 
     * @param listaDesassociaProdutoModelo
     */
    public void setListaDesassociaProdutoModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveisListaDesassociaProdutoModeloDesassociacaoProdutoModelo[] listaDesassociaProdutoModelo) {
        this.listaDesassociaProdutoModelo = listaDesassociaProdutoModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDesabilitarVariaveis)) return false;
        ParametrosDesabilitarVariaveis other = (ParametrosDesabilitarVariaveis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaDesassociaProdutoModelo==null && other.getListaDesassociaProdutoModelo()==null) || 
             (this.listaDesassociaProdutoModelo!=null &&
              java.util.Arrays.equals(this.listaDesassociaProdutoModelo, other.getListaDesassociaProdutoModelo())));
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
        if (getListaDesassociaProdutoModelo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaDesassociaProdutoModelo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaDesassociaProdutoModelo(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosDesabilitarVariaveis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosDesabilitarVariaveis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaDesassociaProdutoModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ListaDesassociaProdutoModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosDesabilitarVariaveis>ListaDesassociaProdutoModelo>DesassociacaoProdutoModelo"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "DesassociacaoProdutoModelo"));
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
