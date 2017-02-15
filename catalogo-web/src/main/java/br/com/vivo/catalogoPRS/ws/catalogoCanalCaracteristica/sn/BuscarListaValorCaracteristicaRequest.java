/**
 * BuscarListaValorCaracteristicaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarListaValorCaracteristicaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaValorCaracteristica parametrosBuscarListaValorCaracteristica;

    public BuscarListaValorCaracteristicaRequest() {
    }

    public BuscarListaValorCaracteristicaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaValorCaracteristica parametrosBuscarListaValorCaracteristica) {
           this.parametrosBuscarListaValorCaracteristica = parametrosBuscarListaValorCaracteristica;
    }


    /**
     * Gets the parametrosBuscarListaValorCaracteristica value for this BuscarListaValorCaracteristicaRequest.
     * 
     * @return parametrosBuscarListaValorCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaValorCaracteristica getParametrosBuscarListaValorCaracteristica() {
        return parametrosBuscarListaValorCaracteristica;
    }


    /**
     * Sets the parametrosBuscarListaValorCaracteristica value for this BuscarListaValorCaracteristicaRequest.
     * 
     * @param parametrosBuscarListaValorCaracteristica
     */
    public void setParametrosBuscarListaValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaValorCaracteristica parametrosBuscarListaValorCaracteristica) {
        this.parametrosBuscarListaValorCaracteristica = parametrosBuscarListaValorCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaValorCaracteristicaRequest)) return false;
        BuscarListaValorCaracteristicaRequest other = (BuscarListaValorCaracteristicaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaValorCaracteristica==null && other.getParametrosBuscarListaValorCaracteristica()==null) || 
             (this.parametrosBuscarListaValorCaracteristica!=null &&
              this.parametrosBuscarListaValorCaracteristica.equals(other.getParametrosBuscarListaValorCaracteristica())));
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
        if (getParametrosBuscarListaValorCaracteristica() != null) {
            _hashCode += getParametrosBuscarListaValorCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaValorCaracteristicaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarListaValorCaracteristicaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaValorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosBuscarListaValorCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosBuscarListaValorCaracteristica"));
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
