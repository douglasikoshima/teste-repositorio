/**
 * BuscarListaCaracteristicaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarListaCaracteristicaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristica parametrosBuscarListaCaracteristica;

    public BuscarListaCaracteristicaRequest() {
    }

    public BuscarListaCaracteristicaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristica parametrosBuscarListaCaracteristica) {
           this.parametrosBuscarListaCaracteristica = parametrosBuscarListaCaracteristica;
    }


    /**
     * Gets the parametrosBuscarListaCaracteristica value for this BuscarListaCaracteristicaRequest.
     * 
     * @return parametrosBuscarListaCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristica getParametrosBuscarListaCaracteristica() {
        return parametrosBuscarListaCaracteristica;
    }


    /**
     * Sets the parametrosBuscarListaCaracteristica value for this BuscarListaCaracteristicaRequest.
     * 
     * @param parametrosBuscarListaCaracteristica
     */
    public void setParametrosBuscarListaCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristica parametrosBuscarListaCaracteristica) {
        this.parametrosBuscarListaCaracteristica = parametrosBuscarListaCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaCaracteristicaRequest)) return false;
        BuscarListaCaracteristicaRequest other = (BuscarListaCaracteristicaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaCaracteristica==null && other.getParametrosBuscarListaCaracteristica()==null) || 
             (this.parametrosBuscarListaCaracteristica!=null &&
              this.parametrosBuscarListaCaracteristica.equals(other.getParametrosBuscarListaCaracteristica())));
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
        if (getParametrosBuscarListaCaracteristica() != null) {
            _hashCode += getParametrosBuscarListaCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaCaracteristicaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarListaCaracteristicaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosBuscarListaCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosBuscarListaCaracteristica"));
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
