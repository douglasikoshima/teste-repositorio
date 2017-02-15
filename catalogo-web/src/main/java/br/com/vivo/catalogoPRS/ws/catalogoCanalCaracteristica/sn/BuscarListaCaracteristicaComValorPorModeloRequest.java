/**
 * BuscarListaCaracteristicaComValorPorModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarListaCaracteristicaComValorPorModeloRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaComValorPorModelo parametrosBuscarListaCaracteristicaComValorPorModelo;

    public BuscarListaCaracteristicaComValorPorModeloRequest() {
    }

    public BuscarListaCaracteristicaComValorPorModeloRequest(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaComValorPorModelo parametrosBuscarListaCaracteristicaComValorPorModelo) {
           this.parametrosBuscarListaCaracteristicaComValorPorModelo = parametrosBuscarListaCaracteristicaComValorPorModelo;
    }


    /**
     * Gets the parametrosBuscarListaCaracteristicaComValorPorModelo value for this BuscarListaCaracteristicaComValorPorModeloRequest.
     * 
     * @return parametrosBuscarListaCaracteristicaComValorPorModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaComValorPorModelo getParametrosBuscarListaCaracteristicaComValorPorModelo() {
        return parametrosBuscarListaCaracteristicaComValorPorModelo;
    }


    /**
     * Sets the parametrosBuscarListaCaracteristicaComValorPorModelo value for this BuscarListaCaracteristicaComValorPorModeloRequest.
     * 
     * @param parametrosBuscarListaCaracteristicaComValorPorModelo
     */
    public void setParametrosBuscarListaCaracteristicaComValorPorModelo(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaComValorPorModelo parametrosBuscarListaCaracteristicaComValorPorModelo) {
        this.parametrosBuscarListaCaracteristicaComValorPorModelo = parametrosBuscarListaCaracteristicaComValorPorModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaCaracteristicaComValorPorModeloRequest)) return false;
        BuscarListaCaracteristicaComValorPorModeloRequest other = (BuscarListaCaracteristicaComValorPorModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaCaracteristicaComValorPorModelo==null && other.getParametrosBuscarListaCaracteristicaComValorPorModelo()==null) || 
             (this.parametrosBuscarListaCaracteristicaComValorPorModelo!=null &&
              this.parametrosBuscarListaCaracteristicaComValorPorModelo.equals(other.getParametrosBuscarListaCaracteristicaComValorPorModelo())));
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
        if (getParametrosBuscarListaCaracteristicaComValorPorModelo() != null) {
            _hashCode += getParametrosBuscarListaCaracteristicaComValorPorModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaCaracteristicaComValorPorModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarListaCaracteristicaComValorPorModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaCaracteristicaComValorPorModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosBuscarListaCaracteristicaComValorPorModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosBuscarListaCaracteristicaComValorPorModelo"));
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
