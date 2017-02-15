/**
 * BuscarListaCaracteristicaComValorRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarListaCaracteristicaComValorRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaComValor parametrosBuscarListaCaracteristicaComValor;

    public BuscarListaCaracteristicaComValorRequest() {
    }

    public BuscarListaCaracteristicaComValorRequest(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaComValor parametrosBuscarListaCaracteristicaComValor) {
           this.parametrosBuscarListaCaracteristicaComValor = parametrosBuscarListaCaracteristicaComValor;
    }


    /**
     * Gets the parametrosBuscarListaCaracteristicaComValor value for this BuscarListaCaracteristicaComValorRequest.
     * 
     * @return parametrosBuscarListaCaracteristicaComValor
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaComValor getParametrosBuscarListaCaracteristicaComValor() {
        return parametrosBuscarListaCaracteristicaComValor;
    }


    /**
     * Sets the parametrosBuscarListaCaracteristicaComValor value for this BuscarListaCaracteristicaComValorRequest.
     * 
     * @param parametrosBuscarListaCaracteristicaComValor
     */
    public void setParametrosBuscarListaCaracteristicaComValor(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaComValor parametrosBuscarListaCaracteristicaComValor) {
        this.parametrosBuscarListaCaracteristicaComValor = parametrosBuscarListaCaracteristicaComValor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaCaracteristicaComValorRequest)) return false;
        BuscarListaCaracteristicaComValorRequest other = (BuscarListaCaracteristicaComValorRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaCaracteristicaComValor==null && other.getParametrosBuscarListaCaracteristicaComValor()==null) || 
             (this.parametrosBuscarListaCaracteristicaComValor!=null &&
              this.parametrosBuscarListaCaracteristicaComValor.equals(other.getParametrosBuscarListaCaracteristicaComValor())));
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
        if (getParametrosBuscarListaCaracteristicaComValor() != null) {
            _hashCode += getParametrosBuscarListaCaracteristicaComValor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaCaracteristicaComValorRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarListaCaracteristicaComValorRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaCaracteristicaComValor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosBuscarListaCaracteristicaComValor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosBuscarListaCaracteristicaComValor"));
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
