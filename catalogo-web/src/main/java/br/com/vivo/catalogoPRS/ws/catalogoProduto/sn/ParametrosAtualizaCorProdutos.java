/**
 * ParametrosAtualizaCorProdutos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosAtualizaCorProdutos  implements java.io.Serializable {
    private java.lang.String[] listaProdutos;

    private java.lang.Long idCor;

    public ParametrosAtualizaCorProdutos() {
    }

    public ParametrosAtualizaCorProdutos(
           java.lang.String[] listaProdutos,
           java.lang.Long idCor) {
           this.listaProdutos = listaProdutos;
           this.idCor = idCor;
    }


    /**
     * Gets the listaProdutos value for this ParametrosAtualizaCorProdutos.
     * 
     * @return listaProdutos
     */
    public java.lang.String[] getListaProdutos() {
        return listaProdutos;
    }


    /**
     * Sets the listaProdutos value for this ParametrosAtualizaCorProdutos.
     * 
     * @param listaProdutos
     */
    public void setListaProdutos(java.lang.String[] listaProdutos) {
        this.listaProdutos = listaProdutos;
    }


    /**
     * Gets the idCor value for this ParametrosAtualizaCorProdutos.
     * 
     * @return idCor
     */
    public java.lang.Long getIdCor() {
        return idCor;
    }


    /**
     * Sets the idCor value for this ParametrosAtualizaCorProdutos.
     * 
     * @param idCor
     */
    public void setIdCor(java.lang.Long idCor) {
        this.idCor = idCor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAtualizaCorProdutos)) return false;
        ParametrosAtualizaCorProdutos other = (ParametrosAtualizaCorProdutos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaProdutos==null && other.getListaProdutos()==null) || 
             (this.listaProdutos!=null &&
              java.util.Arrays.equals(this.listaProdutos, other.getListaProdutos()))) &&
            ((this.idCor==null && other.getIdCor()==null) || 
             (this.idCor!=null &&
              this.idCor.equals(other.getIdCor())));
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
        if (getListaProdutos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaProdutos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaProdutos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdCor() != null) {
            _hashCode += getIdCor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAtualizaCorProdutos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosAtualizaCorProdutos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaProdutos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ListaProdutos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idProduto"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idCor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
