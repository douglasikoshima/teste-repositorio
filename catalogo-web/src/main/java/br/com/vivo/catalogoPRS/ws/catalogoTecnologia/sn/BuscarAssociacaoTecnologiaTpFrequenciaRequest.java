/**
 * BuscarAssociacaoTecnologiaTpFrequenciaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class BuscarAssociacaoTecnologiaTpFrequenciaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequencia parametrosBuscarAssociacaoTecnologiaTpFrequencia;

    public BuscarAssociacaoTecnologiaTpFrequenciaRequest() {
    }

    public BuscarAssociacaoTecnologiaTpFrequenciaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequencia parametrosBuscarAssociacaoTecnologiaTpFrequencia) {
           this.parametrosBuscarAssociacaoTecnologiaTpFrequencia = parametrosBuscarAssociacaoTecnologiaTpFrequencia;
    }


    /**
     * Gets the parametrosBuscarAssociacaoTecnologiaTpFrequencia value for this BuscarAssociacaoTecnologiaTpFrequenciaRequest.
     * 
     * @return parametrosBuscarAssociacaoTecnologiaTpFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequencia getParametrosBuscarAssociacaoTecnologiaTpFrequencia() {
        return parametrosBuscarAssociacaoTecnologiaTpFrequencia;
    }


    /**
     * Sets the parametrosBuscarAssociacaoTecnologiaTpFrequencia value for this BuscarAssociacaoTecnologiaTpFrequenciaRequest.
     * 
     * @param parametrosBuscarAssociacaoTecnologiaTpFrequencia
     */
    public void setParametrosBuscarAssociacaoTecnologiaTpFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarAssociacaoTecnologiaTpFrequencia parametrosBuscarAssociacaoTecnologiaTpFrequencia) {
        this.parametrosBuscarAssociacaoTecnologiaTpFrequencia = parametrosBuscarAssociacaoTecnologiaTpFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarAssociacaoTecnologiaTpFrequenciaRequest)) return false;
        BuscarAssociacaoTecnologiaTpFrequenciaRequest other = (BuscarAssociacaoTecnologiaTpFrequenciaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarAssociacaoTecnologiaTpFrequencia==null && other.getParametrosBuscarAssociacaoTecnologiaTpFrequencia()==null) || 
             (this.parametrosBuscarAssociacaoTecnologiaTpFrequencia!=null &&
              this.parametrosBuscarAssociacaoTecnologiaTpFrequencia.equals(other.getParametrosBuscarAssociacaoTecnologiaTpFrequencia())));
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
        if (getParametrosBuscarAssociacaoTecnologiaTpFrequencia() != null) {
            _hashCode += getParametrosBuscarAssociacaoTecnologiaTpFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarAssociacaoTecnologiaTpFrequenciaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">buscarAssociacaoTecnologiaTpFrequenciaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarAssociacaoTecnologiaTpFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ParametrosBuscarAssociacaoTecnologiaTpFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosBuscarAssociacaoTecnologiaTpFrequencia"));
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
