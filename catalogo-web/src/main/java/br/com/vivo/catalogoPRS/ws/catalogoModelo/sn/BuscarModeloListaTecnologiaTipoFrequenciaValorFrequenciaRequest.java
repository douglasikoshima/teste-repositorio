/**
 * BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia;

    public BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest() {
    }

    public BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia) {
           this.parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia = parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia;
    }


    /**
     * Gets the parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia value for this BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest.
     * 
     * @return parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia getParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia() {
        return parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia;
    }


    /**
     * Sets the parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia value for this BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest.
     * 
     * @param parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia
     */
    public void setParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia) {
        this.parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia = parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest)) return false;
        BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest other = (BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia==null && other.getParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia()==null) || 
             (this.parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia!=null &&
              this.parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia.equals(other.getParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia())));
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
        if (getParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia() != null) {
            _hashCode += getParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia"));
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
