/**
 * ParametrosBuscarListaModeloPorValorCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosBuscarListaModeloPorValorCaracteristica  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosModelosPorValorCaracteristicaIn parametrosModelosPorValorCaracteristicaIn;

    public ParametrosBuscarListaModeloPorValorCaracteristica() {
    }

    public ParametrosBuscarListaModeloPorValorCaracteristica(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosModelosPorValorCaracteristicaIn parametrosModelosPorValorCaracteristicaIn) {
           this.parametrosModelosPorValorCaracteristicaIn = parametrosModelosPorValorCaracteristicaIn;
    }


    /**
     * Gets the parametrosModelosPorValorCaracteristicaIn value for this ParametrosBuscarListaModeloPorValorCaracteristica.
     * 
     * @return parametrosModelosPorValorCaracteristicaIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosModelosPorValorCaracteristicaIn getParametrosModelosPorValorCaracteristicaIn() {
        return parametrosModelosPorValorCaracteristicaIn;
    }


    /**
     * Sets the parametrosModelosPorValorCaracteristicaIn value for this ParametrosBuscarListaModeloPorValorCaracteristica.
     * 
     * @param parametrosModelosPorValorCaracteristicaIn
     */
    public void setParametrosModelosPorValorCaracteristicaIn(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosModelosPorValorCaracteristicaIn parametrosModelosPorValorCaracteristicaIn) {
        this.parametrosModelosPorValorCaracteristicaIn = parametrosModelosPorValorCaracteristicaIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloPorValorCaracteristica)) return false;
        ParametrosBuscarListaModeloPorValorCaracteristica other = (ParametrosBuscarListaModeloPorValorCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosModelosPorValorCaracteristicaIn==null && other.getParametrosModelosPorValorCaracteristicaIn()==null) || 
             (this.parametrosModelosPorValorCaracteristicaIn!=null &&
              this.parametrosModelosPorValorCaracteristicaIn.equals(other.getParametrosModelosPorValorCaracteristicaIn())));
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
        if (getParametrosModelosPorValorCaracteristicaIn() != null) {
            _hashCode += getParametrosModelosPorValorCaracteristicaIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloPorValorCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosBuscarListaModeloPorValorCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosModelosPorValorCaracteristicaIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosModelosPorValorCaracteristicaIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>ParametrosBuscarListaModeloPorValorCaracteristica>ParametrosModelosPorValorCaracteristicaIn"));
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
