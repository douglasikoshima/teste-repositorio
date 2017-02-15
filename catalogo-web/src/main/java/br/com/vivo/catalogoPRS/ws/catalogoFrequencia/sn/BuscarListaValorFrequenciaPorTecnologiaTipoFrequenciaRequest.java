/**
 * BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia;

    public BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest() {
    }

    public BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia) {
           this.parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia = parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia;
    }


    /**
     * Gets the parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia value for this BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest.
     * 
     * @return parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia getParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia() {
        return parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia;
    }


    /**
     * Sets the parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia value for this BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest.
     * 
     * @param parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia
     */
    public void setParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia) {
        this.parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia = parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest)) return false;
        BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest other = (BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia==null && other.getParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia()==null) || 
             (this.parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia!=null &&
              this.parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia.equals(other.getParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia())));
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
        if (getParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia() != null) {
            _hashCode += getParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">buscarListaValorFrequenciaPorTecnologiaTipoFrequenciaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosBuscarListaValorFrequenciaPorTecnologiaTipoFrequencia"));
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
