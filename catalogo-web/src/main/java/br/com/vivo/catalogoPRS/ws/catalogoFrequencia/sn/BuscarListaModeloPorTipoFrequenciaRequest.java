/**
 * BuscarListaModeloPorTipoFrequenciaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class BuscarListaModeloPorTipoFrequenciaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequencia parametrosBuscarListaModeloPorTipoFrequencia;

    public BuscarListaModeloPorTipoFrequenciaRequest() {
    }

    public BuscarListaModeloPorTipoFrequenciaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequencia parametrosBuscarListaModeloPorTipoFrequencia) {
           this.parametrosBuscarListaModeloPorTipoFrequencia = parametrosBuscarListaModeloPorTipoFrequencia;
    }


    /**
     * Gets the parametrosBuscarListaModeloPorTipoFrequencia value for this BuscarListaModeloPorTipoFrequenciaRequest.
     * 
     * @return parametrosBuscarListaModeloPorTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequencia getParametrosBuscarListaModeloPorTipoFrequencia() {
        return parametrosBuscarListaModeloPorTipoFrequencia;
    }


    /**
     * Sets the parametrosBuscarListaModeloPorTipoFrequencia value for this BuscarListaModeloPorTipoFrequenciaRequest.
     * 
     * @param parametrosBuscarListaModeloPorTipoFrequencia
     */
    public void setParametrosBuscarListaModeloPorTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ParametrosBuscarListaModeloPorTipoFrequencia parametrosBuscarListaModeloPorTipoFrequencia) {
        this.parametrosBuscarListaModeloPorTipoFrequencia = parametrosBuscarListaModeloPorTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaModeloPorTipoFrequenciaRequest)) return false;
        BuscarListaModeloPorTipoFrequenciaRequest other = (BuscarListaModeloPorTipoFrequenciaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaModeloPorTipoFrequencia==null && other.getParametrosBuscarListaModeloPorTipoFrequencia()==null) || 
             (this.parametrosBuscarListaModeloPorTipoFrequencia!=null &&
              this.parametrosBuscarListaModeloPorTipoFrequencia.equals(other.getParametrosBuscarListaModeloPorTipoFrequencia())));
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
        if (getParametrosBuscarListaModeloPorTipoFrequencia() != null) {
            _hashCode += getParametrosBuscarListaModeloPorTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaModeloPorTipoFrequenciaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">buscarListaModeloPorTipoFrequenciaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaModeloPorTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ParametrosBuscarListaModeloPorTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosBuscarListaModeloPorTipoFrequencia"));
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
