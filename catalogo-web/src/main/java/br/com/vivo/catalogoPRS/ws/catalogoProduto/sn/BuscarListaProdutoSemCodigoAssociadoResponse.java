/**
 * BuscarListaProdutoSemCodigoAssociadoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class BuscarListaProdutoSemCodigoAssociadoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoSemCodigoAssociado resultadoBuscarListaProdutoSemCodigoAssociado;

    public BuscarListaProdutoSemCodigoAssociadoResponse() {
    }

    public BuscarListaProdutoSemCodigoAssociadoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoSemCodigoAssociado resultadoBuscarListaProdutoSemCodigoAssociado) {
           this.resultadoBuscarListaProdutoSemCodigoAssociado = resultadoBuscarListaProdutoSemCodigoAssociado;
    }


    /**
     * Gets the resultadoBuscarListaProdutoSemCodigoAssociado value for this BuscarListaProdutoSemCodigoAssociadoResponse.
     * 
     * @return resultadoBuscarListaProdutoSemCodigoAssociado
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoSemCodigoAssociado getResultadoBuscarListaProdutoSemCodigoAssociado() {
        return resultadoBuscarListaProdutoSemCodigoAssociado;
    }


    /**
     * Sets the resultadoBuscarListaProdutoSemCodigoAssociado value for this BuscarListaProdutoSemCodigoAssociadoResponse.
     * 
     * @param resultadoBuscarListaProdutoSemCodigoAssociado
     */
    public void setResultadoBuscarListaProdutoSemCodigoAssociado(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoSemCodigoAssociado resultadoBuscarListaProdutoSemCodigoAssociado) {
        this.resultadoBuscarListaProdutoSemCodigoAssociado = resultadoBuscarListaProdutoSemCodigoAssociado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaProdutoSemCodigoAssociadoResponse)) return false;
        BuscarListaProdutoSemCodigoAssociadoResponse other = (BuscarListaProdutoSemCodigoAssociadoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaProdutoSemCodigoAssociado==null && other.getResultadoBuscarListaProdutoSemCodigoAssociado()==null) || 
             (this.resultadoBuscarListaProdutoSemCodigoAssociado!=null &&
              this.resultadoBuscarListaProdutoSemCodigoAssociado.equals(other.getResultadoBuscarListaProdutoSemCodigoAssociado())));
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
        if (getResultadoBuscarListaProdutoSemCodigoAssociado() != null) {
            _hashCode += getResultadoBuscarListaProdutoSemCodigoAssociado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaProdutoSemCodigoAssociadoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoSemCodigoAssociadoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaProdutoSemCodigoAssociado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ResultadoBuscarListaProdutoSemCodigoAssociado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaProdutoSemCodigoAssociado"));
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
