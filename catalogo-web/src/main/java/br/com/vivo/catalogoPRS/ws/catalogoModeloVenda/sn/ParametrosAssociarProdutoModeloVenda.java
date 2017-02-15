/**
 * ParametrosAssociarProdutoModeloVenda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class ParametrosAssociarProdutoModeloVenda  implements java.io.Serializable {
    private long idModeloVenda;

    private long[] listaIdProdutos;

    public ParametrosAssociarProdutoModeloVenda() {
    }

    public ParametrosAssociarProdutoModeloVenda(
           long idModeloVenda,
           long[] listaIdProdutos) {
           this.idModeloVenda = idModeloVenda;
           this.listaIdProdutos = listaIdProdutos;
    }


    /**
     * Gets the idModeloVenda value for this ParametrosAssociarProdutoModeloVenda.
     * 
     * @return idModeloVenda
     */
    public long getIdModeloVenda() {
        return idModeloVenda;
    }


    /**
     * Sets the idModeloVenda value for this ParametrosAssociarProdutoModeloVenda.
     * 
     * @param idModeloVenda
     */
    public void setIdModeloVenda(long idModeloVenda) {
        this.idModeloVenda = idModeloVenda;
    }


    /**
     * Gets the listaIdProdutos value for this ParametrosAssociarProdutoModeloVenda.
     * 
     * @return listaIdProdutos
     */
    public long[] getListaIdProdutos() {
        return listaIdProdutos;
    }


    /**
     * Sets the listaIdProdutos value for this ParametrosAssociarProdutoModeloVenda.
     * 
     * @param listaIdProdutos
     */
    public void setListaIdProdutos(long[] listaIdProdutos) {
        this.listaIdProdutos = listaIdProdutos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAssociarProdutoModeloVenda)) return false;
        ParametrosAssociarProdutoModeloVenda other = (ParametrosAssociarProdutoModeloVenda) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idModeloVenda == other.getIdModeloVenda() &&
            ((this.listaIdProdutos==null && other.getListaIdProdutos()==null) || 
             (this.listaIdProdutos!=null &&
              java.util.Arrays.equals(this.listaIdProdutos, other.getListaIdProdutos())));
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
        _hashCode += new Long(getIdModeloVenda()).hashCode();
        if (getListaIdProdutos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdProdutos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdProdutos(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosAssociarProdutoModeloVenda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", ">ParametrosAssociarProdutoModeloVenda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModeloVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "idModeloVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdProdutos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ListaIdProdutos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "idProdutos"));
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
