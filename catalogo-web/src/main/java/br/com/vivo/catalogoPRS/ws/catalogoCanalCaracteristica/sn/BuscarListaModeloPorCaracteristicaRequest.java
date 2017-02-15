/**
 * BuscarListaModeloPorCaracteristicaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarListaModeloPorCaracteristicaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaModeloPorCaracteristica parametrosBuscarListaModeloPorCaracteristica;

    public BuscarListaModeloPorCaracteristicaRequest() {
    }

    public BuscarListaModeloPorCaracteristicaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaModeloPorCaracteristica parametrosBuscarListaModeloPorCaracteristica) {
           this.parametrosBuscarListaModeloPorCaracteristica = parametrosBuscarListaModeloPorCaracteristica;
    }


    /**
     * Gets the parametrosBuscarListaModeloPorCaracteristica value for this BuscarListaModeloPorCaracteristicaRequest.
     * 
     * @return parametrosBuscarListaModeloPorCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaModeloPorCaracteristica getParametrosBuscarListaModeloPorCaracteristica() {
        return parametrosBuscarListaModeloPorCaracteristica;
    }


    /**
     * Sets the parametrosBuscarListaModeloPorCaracteristica value for this BuscarListaModeloPorCaracteristicaRequest.
     * 
     * @param parametrosBuscarListaModeloPorCaracteristica
     */
    public void setParametrosBuscarListaModeloPorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaModeloPorCaracteristica parametrosBuscarListaModeloPorCaracteristica) {
        this.parametrosBuscarListaModeloPorCaracteristica = parametrosBuscarListaModeloPorCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaModeloPorCaracteristicaRequest)) return false;
        BuscarListaModeloPorCaracteristicaRequest other = (BuscarListaModeloPorCaracteristicaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaModeloPorCaracteristica==null && other.getParametrosBuscarListaModeloPorCaracteristica()==null) || 
             (this.parametrosBuscarListaModeloPorCaracteristica!=null &&
              this.parametrosBuscarListaModeloPorCaracteristica.equals(other.getParametrosBuscarListaModeloPorCaracteristica())));
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
        if (getParametrosBuscarListaModeloPorCaracteristica() != null) {
            _hashCode += getParametrosBuscarListaModeloPorCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaModeloPorCaracteristicaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarListaModeloPorCaracteristicaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaModeloPorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosBuscarListaModeloPorCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosBuscarListaModeloPorCaracteristica"));
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
