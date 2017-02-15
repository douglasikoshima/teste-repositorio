/**
 * BuscarListaCaracteristicaDispProdutoModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarListaCaracteristicaDispProdutoModeloRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaCaracteristicaDispProdutoModelo parametrosBuscarListaCaracteristicaDispProdutoModelo;

    public BuscarListaCaracteristicaDispProdutoModeloRequest() {
    }

    public BuscarListaCaracteristicaDispProdutoModeloRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaCaracteristicaDispProdutoModelo parametrosBuscarListaCaracteristicaDispProdutoModelo) {
           this.parametrosBuscarListaCaracteristicaDispProdutoModelo = parametrosBuscarListaCaracteristicaDispProdutoModelo;
    }


    /**
     * Gets the parametrosBuscarListaCaracteristicaDispProdutoModelo value for this BuscarListaCaracteristicaDispProdutoModeloRequest.
     * 
     * @return parametrosBuscarListaCaracteristicaDispProdutoModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaCaracteristicaDispProdutoModelo getParametrosBuscarListaCaracteristicaDispProdutoModelo() {
        return parametrosBuscarListaCaracteristicaDispProdutoModelo;
    }


    /**
     * Sets the parametrosBuscarListaCaracteristicaDispProdutoModelo value for this BuscarListaCaracteristicaDispProdutoModeloRequest.
     * 
     * @param parametrosBuscarListaCaracteristicaDispProdutoModelo
     */
    public void setParametrosBuscarListaCaracteristicaDispProdutoModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaCaracteristicaDispProdutoModelo parametrosBuscarListaCaracteristicaDispProdutoModelo) {
        this.parametrosBuscarListaCaracteristicaDispProdutoModelo = parametrosBuscarListaCaracteristicaDispProdutoModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaCaracteristicaDispProdutoModeloRequest)) return false;
        BuscarListaCaracteristicaDispProdutoModeloRequest other = (BuscarListaCaracteristicaDispProdutoModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaCaracteristicaDispProdutoModelo==null && other.getParametrosBuscarListaCaracteristicaDispProdutoModelo()==null) || 
             (this.parametrosBuscarListaCaracteristicaDispProdutoModelo!=null &&
              this.parametrosBuscarListaCaracteristicaDispProdutoModelo.equals(other.getParametrosBuscarListaCaracteristicaDispProdutoModelo())));
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
        if (getParametrosBuscarListaCaracteristicaDispProdutoModelo() != null) {
            _hashCode += getParametrosBuscarListaCaracteristicaDispProdutoModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaCaracteristicaDispProdutoModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarListaCaracteristicaDispProdutoModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaCaracteristicaDispProdutoModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosBuscarListaCaracteristicaDispProdutoModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarListaCaracteristicaDispProdutoModelo"));
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
