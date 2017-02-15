/**
 * BuscarListaModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarListaModeloRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModelo parametrosBuscarListaModelo;

    public BuscarListaModeloRequest() {
    }

    public BuscarListaModeloRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModelo parametrosBuscarListaModelo) {
           this.parametrosBuscarListaModelo = parametrosBuscarListaModelo;
    }


    /**
     * Gets the parametrosBuscarListaModelo value for this BuscarListaModeloRequest.
     * 
     * @return parametrosBuscarListaModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModelo getParametrosBuscarListaModelo() {
        return parametrosBuscarListaModelo;
    }


    /**
     * Sets the parametrosBuscarListaModelo value for this BuscarListaModeloRequest.
     * 
     * @param parametrosBuscarListaModelo
     */
    public void setParametrosBuscarListaModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModelo parametrosBuscarListaModelo) {
        this.parametrosBuscarListaModelo = parametrosBuscarListaModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaModeloRequest)) return false;
        BuscarListaModeloRequest other = (BuscarListaModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaModelo==null && other.getParametrosBuscarListaModelo()==null) || 
             (this.parametrosBuscarListaModelo!=null &&
              this.parametrosBuscarListaModelo.equals(other.getParametrosBuscarListaModelo())));
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
        if (getParametrosBuscarListaModelo() != null) {
            _hashCode += getParametrosBuscarListaModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarListaModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosBuscarListaModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarListaModelo"));
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
