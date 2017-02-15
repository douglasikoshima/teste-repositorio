/**
 * BuscarListaTecnologiaPorModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class BuscarListaTecnologiaPorModeloRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarListaTecnologiaPorModelo parametrosBuscarListaTecnologiaPorModelo;

    public BuscarListaTecnologiaPorModeloRequest() {
    }

    public BuscarListaTecnologiaPorModeloRequest(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarListaTecnologiaPorModelo parametrosBuscarListaTecnologiaPorModelo) {
           this.parametrosBuscarListaTecnologiaPorModelo = parametrosBuscarListaTecnologiaPorModelo;
    }


    /**
     * Gets the parametrosBuscarListaTecnologiaPorModelo value for this BuscarListaTecnologiaPorModeloRequest.
     * 
     * @return parametrosBuscarListaTecnologiaPorModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarListaTecnologiaPorModelo getParametrosBuscarListaTecnologiaPorModelo() {
        return parametrosBuscarListaTecnologiaPorModelo;
    }


    /**
     * Sets the parametrosBuscarListaTecnologiaPorModelo value for this BuscarListaTecnologiaPorModeloRequest.
     * 
     * @param parametrosBuscarListaTecnologiaPorModelo
     */
    public void setParametrosBuscarListaTecnologiaPorModelo(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarListaTecnologiaPorModelo parametrosBuscarListaTecnologiaPorModelo) {
        this.parametrosBuscarListaTecnologiaPorModelo = parametrosBuscarListaTecnologiaPorModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaTecnologiaPorModeloRequest)) return false;
        BuscarListaTecnologiaPorModeloRequest other = (BuscarListaTecnologiaPorModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaTecnologiaPorModelo==null && other.getParametrosBuscarListaTecnologiaPorModelo()==null) || 
             (this.parametrosBuscarListaTecnologiaPorModelo!=null &&
              this.parametrosBuscarListaTecnologiaPorModelo.equals(other.getParametrosBuscarListaTecnologiaPorModelo())));
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
        if (getParametrosBuscarListaTecnologiaPorModelo() != null) {
            _hashCode += getParametrosBuscarListaTecnologiaPorModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaTecnologiaPorModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">buscarListaTecnologiaPorModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaTecnologiaPorModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ParametrosBuscarListaTecnologiaPorModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosBuscarListaTecnologiaPorModelo"));
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
