/**
 * DisponibilizarModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class DisponibilizarModeloRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosDisponibilizarModelo parametrosDisponibilizarModelo;

    public DisponibilizarModeloRequest() {
    }

    public DisponibilizarModeloRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosDisponibilizarModelo parametrosDisponibilizarModelo) {
           this.parametrosDisponibilizarModelo = parametrosDisponibilizarModelo;
    }


    /**
     * Gets the parametrosDisponibilizarModelo value for this DisponibilizarModeloRequest.
     * 
     * @return parametrosDisponibilizarModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosDisponibilizarModelo getParametrosDisponibilizarModelo() {
        return parametrosDisponibilizarModelo;
    }


    /**
     * Sets the parametrosDisponibilizarModelo value for this DisponibilizarModeloRequest.
     * 
     * @param parametrosDisponibilizarModelo
     */
    public void setParametrosDisponibilizarModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosDisponibilizarModelo parametrosDisponibilizarModelo) {
        this.parametrosDisponibilizarModelo = parametrosDisponibilizarModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DisponibilizarModeloRequest)) return false;
        DisponibilizarModeloRequest other = (DisponibilizarModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosDisponibilizarModelo==null && other.getParametrosDisponibilizarModelo()==null) || 
             (this.parametrosDisponibilizarModelo!=null &&
              this.parametrosDisponibilizarModelo.equals(other.getParametrosDisponibilizarModelo())));
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
        if (getParametrosDisponibilizarModelo() != null) {
            _hashCode += getParametrosDisponibilizarModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DisponibilizarModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">disponibilizarModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosDisponibilizarModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosDisponibilizarModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosDisponibilizarModelo"));
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
