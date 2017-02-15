/**
 * BuscarListaCoresPorModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class BuscarListaCoresPorModeloRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametroBuscarListaCoresPorModelo parametroBuscarListaCoresPorModelo;

    public BuscarListaCoresPorModeloRequest() {
    }

    public BuscarListaCoresPorModeloRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametroBuscarListaCoresPorModelo parametroBuscarListaCoresPorModelo) {
           this.parametroBuscarListaCoresPorModelo = parametroBuscarListaCoresPorModelo;
    }


    /**
     * Gets the parametroBuscarListaCoresPorModelo value for this BuscarListaCoresPorModeloRequest.
     * 
     * @return parametroBuscarListaCoresPorModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametroBuscarListaCoresPorModelo getParametroBuscarListaCoresPorModelo() {
        return parametroBuscarListaCoresPorModelo;
    }


    /**
     * Sets the parametroBuscarListaCoresPorModelo value for this BuscarListaCoresPorModeloRequest.
     * 
     * @param parametroBuscarListaCoresPorModelo
     */
    public void setParametroBuscarListaCoresPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametroBuscarListaCoresPorModelo parametroBuscarListaCoresPorModelo) {
        this.parametroBuscarListaCoresPorModelo = parametroBuscarListaCoresPorModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaCoresPorModeloRequest)) return false;
        BuscarListaCoresPorModeloRequest other = (BuscarListaCoresPorModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametroBuscarListaCoresPorModelo==null && other.getParametroBuscarListaCoresPorModelo()==null) || 
             (this.parametroBuscarListaCoresPorModelo!=null &&
              this.parametroBuscarListaCoresPorModelo.equals(other.getParametroBuscarListaCoresPorModelo())));
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
        if (getParametroBuscarListaCoresPorModelo() != null) {
            _hashCode += getParametroBuscarListaCoresPorModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaCoresPorModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaCoresPorModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametroBuscarListaCoresPorModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametroBuscarListaCoresPorModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametroBuscarListaCoresPorModelo"));
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
