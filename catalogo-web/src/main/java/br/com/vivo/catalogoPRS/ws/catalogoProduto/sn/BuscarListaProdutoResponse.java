/**
 * BuscarListaProdutoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class BuscarListaProdutoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProduto resultadoBuscarListaProduto;

    public BuscarListaProdutoResponse() {
    }

    public BuscarListaProdutoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProduto resultadoBuscarListaProduto) {
           this.resultadoBuscarListaProduto = resultadoBuscarListaProduto;
    }


    /**
     * Gets the resultadoBuscarListaProduto value for this BuscarListaProdutoResponse.
     * 
     * @return resultadoBuscarListaProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProduto getResultadoBuscarListaProduto() {
        return resultadoBuscarListaProduto;
    }


    /**
     * Sets the resultadoBuscarListaProduto value for this BuscarListaProdutoResponse.
     * 
     * @param resultadoBuscarListaProduto
     */
    public void setResultadoBuscarListaProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProduto resultadoBuscarListaProduto) {
        this.resultadoBuscarListaProduto = resultadoBuscarListaProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaProdutoResponse)) return false;
        BuscarListaProdutoResponse other = (BuscarListaProdutoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaProduto==null && other.getResultadoBuscarListaProduto()==null) || 
             (this.resultadoBuscarListaProduto!=null &&
              this.resultadoBuscarListaProduto.equals(other.getResultadoBuscarListaProduto())));
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
        if (getResultadoBuscarListaProduto() != null) {
            _hashCode += getResultadoBuscarListaProduto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaProdutoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ResultadoBuscarListaProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaProduto"));
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
