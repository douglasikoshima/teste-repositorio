/**
 * BuscarListaCoresPorModeloResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class BuscarListaCoresPorModeloResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModelo resultadoBuscarListaCoresPorModelo;

    public BuscarListaCoresPorModeloResponse() {
    }

    public BuscarListaCoresPorModeloResponse(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModelo resultadoBuscarListaCoresPorModelo) {
           this.resultadoBuscarListaCoresPorModelo = resultadoBuscarListaCoresPorModelo;
    }


    /**
     * Gets the resultadoBuscarListaCoresPorModelo value for this BuscarListaCoresPorModeloResponse.
     * 
     * @return resultadoBuscarListaCoresPorModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModelo getResultadoBuscarListaCoresPorModelo() {
        return resultadoBuscarListaCoresPorModelo;
    }


    /**
     * Sets the resultadoBuscarListaCoresPorModelo value for this BuscarListaCoresPorModeloResponse.
     * 
     * @param resultadoBuscarListaCoresPorModelo
     */
    public void setResultadoBuscarListaCoresPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModelo resultadoBuscarListaCoresPorModelo) {
        this.resultadoBuscarListaCoresPorModelo = resultadoBuscarListaCoresPorModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaCoresPorModeloResponse)) return false;
        BuscarListaCoresPorModeloResponse other = (BuscarListaCoresPorModeloResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaCoresPorModelo==null && other.getResultadoBuscarListaCoresPorModelo()==null) || 
             (this.resultadoBuscarListaCoresPorModelo!=null &&
              this.resultadoBuscarListaCoresPorModelo.equals(other.getResultadoBuscarListaCoresPorModelo())));
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
        if (getResultadoBuscarListaCoresPorModelo() != null) {
            _hashCode += getResultadoBuscarListaCoresPorModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaCoresPorModeloResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaCoresPorModeloResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaCoresPorModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ResultadoBuscarListaCoresPorModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaCoresPorModelo"));
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
