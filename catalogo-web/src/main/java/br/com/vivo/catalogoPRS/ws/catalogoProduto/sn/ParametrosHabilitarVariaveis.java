/**
 * ParametrosHabilitarVariaveis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosHabilitarVariaveis  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis[] listaAssociaProdutoModelo;

    public ParametrosHabilitarVariaveis() {
    }

    public ParametrosHabilitarVariaveis(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis[] listaAssociaProdutoModelo) {
           this.listaAssociaProdutoModelo = listaAssociaProdutoModelo;
    }


    /**
     * Gets the listaAssociaProdutoModelo value for this ParametrosHabilitarVariaveis.
     * 
     * @return listaAssociaProdutoModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis[] getListaAssociaProdutoModelo() {
        return listaAssociaProdutoModelo;
    }


    /**
     * Sets the listaAssociaProdutoModelo value for this ParametrosHabilitarVariaveis.
     * 
     * @param listaAssociaProdutoModelo
     */
    public void setListaAssociaProdutoModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis[] listaAssociaProdutoModelo) {
        this.listaAssociaProdutoModelo = listaAssociaProdutoModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosHabilitarVariaveis)) return false;
        ParametrosHabilitarVariaveis other = (ParametrosHabilitarVariaveis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaAssociaProdutoModelo==null && other.getListaAssociaProdutoModelo()==null) || 
             (this.listaAssociaProdutoModelo!=null &&
              java.util.Arrays.equals(this.listaAssociaProdutoModelo, other.getListaAssociaProdutoModelo())));
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
        if (getListaAssociaProdutoModelo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAssociaProdutoModelo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAssociaProdutoModelo(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosHabilitarVariaveis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosHabilitarVariaveis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAssociaProdutoModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ListaAssociaProdutoModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosHabilitarVariaveis>ListaAssociaProdutoModelo>HabilitaVariaveis"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "HabilitaVariaveis"));
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
