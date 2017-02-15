/**
 * BuscarListaModeloPorTecnologiaTipoFrequenciaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class BuscarListaModeloPorTecnologiaTipoFrequenciaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia parametrosBuscarListaModeloPorTecnologiaTipoFrequencia;

    public BuscarListaModeloPorTecnologiaTipoFrequenciaRequest() {
    }

    public BuscarListaModeloPorTecnologiaTipoFrequenciaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia parametrosBuscarListaModeloPorTecnologiaTipoFrequencia) {
           this.parametrosBuscarListaModeloPorTecnologiaTipoFrequencia = parametrosBuscarListaModeloPorTecnologiaTipoFrequencia;
    }


    /**
     * Gets the parametrosBuscarListaModeloPorTecnologiaTipoFrequencia value for this BuscarListaModeloPorTecnologiaTipoFrequenciaRequest.
     * 
     * @return parametrosBuscarListaModeloPorTecnologiaTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia getParametrosBuscarListaModeloPorTecnologiaTipoFrequencia() {
        return parametrosBuscarListaModeloPorTecnologiaTipoFrequencia;
    }


    /**
     * Sets the parametrosBuscarListaModeloPorTecnologiaTipoFrequencia value for this BuscarListaModeloPorTecnologiaTipoFrequenciaRequest.
     * 
     * @param parametrosBuscarListaModeloPorTecnologiaTipoFrequencia
     */
    public void setParametrosBuscarListaModeloPorTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia parametrosBuscarListaModeloPorTecnologiaTipoFrequencia) {
        this.parametrosBuscarListaModeloPorTecnologiaTipoFrequencia = parametrosBuscarListaModeloPorTecnologiaTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaModeloPorTecnologiaTipoFrequenciaRequest)) return false;
        BuscarListaModeloPorTecnologiaTipoFrequenciaRequest other = (BuscarListaModeloPorTecnologiaTipoFrequenciaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaModeloPorTecnologiaTipoFrequencia==null && other.getParametrosBuscarListaModeloPorTecnologiaTipoFrequencia()==null) || 
             (this.parametrosBuscarListaModeloPorTecnologiaTipoFrequencia!=null &&
              this.parametrosBuscarListaModeloPorTecnologiaTipoFrequencia.equals(other.getParametrosBuscarListaModeloPorTecnologiaTipoFrequencia())));
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
        if (getParametrosBuscarListaModeloPorTecnologiaTipoFrequencia() != null) {
            _hashCode += getParametrosBuscarListaModeloPorTecnologiaTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaModeloPorTecnologiaTipoFrequenciaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">buscarListaModeloPorTecnologiaTipoFrequenciaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaModeloPorTecnologiaTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosBuscarListaModeloPorTecnologiaTipoFrequencia"));
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
