/**
 * ResultBuscarPrecoProdutoPP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class ResultBuscarPrecoProdutoPP  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPPListaProdutoProduto[] listaProduto;

    private br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoOut paginacaoOut;

    public ResultBuscarPrecoProdutoPP() {
    }

    public ResultBuscarPrecoProdutoPP(
           br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPPListaProdutoProduto[] listaProduto,
           br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoOut paginacaoOut) {
           this.listaProduto = listaProduto;
           this.paginacaoOut = paginacaoOut;
    }


    /**
     * Gets the listaProduto value for this ResultBuscarPrecoProdutoPP.
     * 
     * @return listaProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPPListaProdutoProduto[] getListaProduto() {
        return listaProduto;
    }


    /**
     * Sets the listaProduto value for this ResultBuscarPrecoProdutoPP.
     * 
     * @param listaProduto
     */
    public void setListaProduto(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPPListaProdutoProduto[] listaProduto) {
        this.listaProduto = listaProduto;
    }


    /**
     * Gets the paginacaoOut value for this ResultBuscarPrecoProdutoPP.
     * 
     * @return paginacaoOut
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoOut getPaginacaoOut() {
        return paginacaoOut;
    }


    /**
     * Sets the paginacaoOut value for this ResultBuscarPrecoProdutoPP.
     * 
     * @param paginacaoOut
     */
    public void setPaginacaoOut(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoOut paginacaoOut) {
        this.paginacaoOut = paginacaoOut;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultBuscarPrecoProdutoPP)) return false;
        ResultBuscarPrecoProdutoPP other = (ResultBuscarPrecoProdutoPP) obj;
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
              java.util.Arrays.equals(this.listaProduto, other.getListaProduto()))) &&
            ((this.paginacaoOut==null && other.getPaginacaoOut()==null) || 
             (this.paginacaoOut!=null &&
              this.paginacaoOut.equals(other.getPaginacaoOut())));
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
        if (getPaginacaoOut() != null) {
            _hashCode += getPaginacaoOut().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultBuscarPrecoProdutoPP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ResultBuscarPrecoProdutoPP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ListaProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>>ResultBuscarPrecoProdutoPP>ListaProduto>Produto"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "Produto"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "PaginacaoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">PaginacaoOut"));
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
