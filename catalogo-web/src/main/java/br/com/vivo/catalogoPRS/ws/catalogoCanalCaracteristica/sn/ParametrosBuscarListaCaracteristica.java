/**
 * ParametrosBuscarListaCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosBuscarListaCaracteristica  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn raizCaracteristicaIn;

    public ParametrosBuscarListaCaracteristica() {
    }

    public ParametrosBuscarListaCaracteristica(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn raizCaracteristicaIn) {
           this.raizCaracteristicaIn = raizCaracteristicaIn;
    }


    /**
     * Gets the raizCaracteristicaIn value for this ParametrosBuscarListaCaracteristica.
     * 
     * @return raizCaracteristicaIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn getRaizCaracteristicaIn() {
        return raizCaracteristicaIn;
    }


    /**
     * Sets the raizCaracteristicaIn value for this ParametrosBuscarListaCaracteristica.
     * 
     * @param raizCaracteristicaIn
     */
    public void setRaizCaracteristicaIn(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn raizCaracteristicaIn) {
        this.raizCaracteristicaIn = raizCaracteristicaIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaCaracteristica)) return false;
        ParametrosBuscarListaCaracteristica other = (ParametrosBuscarListaCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.raizCaracteristicaIn==null && other.getRaizCaracteristicaIn()==null) || 
             (this.raizCaracteristicaIn!=null &&
              this.raizCaracteristicaIn.equals(other.getRaizCaracteristicaIn())));
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
        if (getRaizCaracteristicaIn() != null) {
            _hashCode += getRaizCaracteristicaIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosBuscarListaCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("raizCaracteristicaIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "RaizCaracteristicaIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>ParametrosBuscarListaCaracteristica>RaizCaracteristicaIn"));
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
