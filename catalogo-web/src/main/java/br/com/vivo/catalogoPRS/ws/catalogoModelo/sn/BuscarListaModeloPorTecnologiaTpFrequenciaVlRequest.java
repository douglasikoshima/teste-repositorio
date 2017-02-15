/**
 * BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl;

    public BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest() {
    }

    public BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl) {
           this.parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl = parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl;
    }


    /**
     * Gets the parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl value for this BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest.
     * 
     * @return parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl getParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl() {
        return parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl;
    }


    /**
     * Sets the parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl value for this BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest.
     * 
     * @param parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl
     */
    public void setParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl) {
        this.parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl = parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest)) return false;
        BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest other = (BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl==null && other.getParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl()==null) || 
             (this.parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl!=null &&
              this.parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl.equals(other.getParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl())));
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
        if (getParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl() != null) {
            _hashCode += getParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaModeloPorTecnologiaTpFrequenciaVlRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarListaModeloPorTecnologiaTpFrequenciaVlRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarListaModeloPorTecnologiaTpFrequenciaVl"));
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
