/**
 * ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis  implements java.io.Serializable {
    private long idGrupoProduto;

    private long idProduto;

    public ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis() {
    }

    public ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis(
           long idGrupoProduto,
           long idProduto) {
           this.idGrupoProduto = idGrupoProduto;
           this.idProduto = idProduto;
    }


    /**
     * Gets the idGrupoProduto value for this ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis.
     * 
     * @return idGrupoProduto
     */
    public long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the idProduto value for this ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis.
     * 
     * @return idProduto
     */
    public long getIdProduto() {
        return idProduto;
    }


    /**
     * Sets the idProduto value for this ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis.
     * 
     * @param idProduto
     */
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis)) return false;
        ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis other = (ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idGrupoProduto == other.getIdGrupoProduto() &&
            this.idProduto == other.getIdProduto();
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
        _hashCode += new Long(getIdGrupoProduto()).hashCode();
        _hashCode += new Long(getIdProduto()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosHabilitarVariaveis>ListaAssociaProdutoModelo>HabilitaVariaveis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
