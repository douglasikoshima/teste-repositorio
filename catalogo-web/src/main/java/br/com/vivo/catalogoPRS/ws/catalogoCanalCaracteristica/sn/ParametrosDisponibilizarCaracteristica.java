/**
 * ParametrosDisponibilizarCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosDisponibilizarCaracteristica  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica parametrosDispCaracteristica;

    public ParametrosDisponibilizarCaracteristica() {
    }

    public ParametrosDisponibilizarCaracteristica(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica parametrosDispCaracteristica) {
           this.parametrosDispCaracteristica = parametrosDispCaracteristica;
    }


    /**
     * Gets the parametrosDispCaracteristica value for this ParametrosDisponibilizarCaracteristica.
     * 
     * @return parametrosDispCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica getParametrosDispCaracteristica() {
        return parametrosDispCaracteristica;
    }


    /**
     * Sets the parametrosDispCaracteristica value for this ParametrosDisponibilizarCaracteristica.
     * 
     * @param parametrosDispCaracteristica
     */
    public void setParametrosDispCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica parametrosDispCaracteristica) {
        this.parametrosDispCaracteristica = parametrosDispCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDisponibilizarCaracteristica)) return false;
        ParametrosDisponibilizarCaracteristica other = (ParametrosDisponibilizarCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosDispCaracteristica==null && other.getParametrosDispCaracteristica()==null) || 
             (this.parametrosDispCaracteristica!=null &&
              this.parametrosDispCaracteristica.equals(other.getParametrosDispCaracteristica())));
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
        if (getParametrosDispCaracteristica() != null) {
            _hashCode += getParametrosDispCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosDisponibilizarCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosDisponibilizarCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosDispCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosDispCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>ParametrosDisponibilizarCaracteristica>ParametrosDispCaracteristica"));
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
