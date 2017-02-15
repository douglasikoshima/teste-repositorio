/**
 * DesassociarListaTecnologiaTipoFrequenciaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class DesassociarListaTecnologiaTipoFrequenciaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequencia parametrosDesassociarListaTecnologiaTipoFrequencia;

    public DesassociarListaTecnologiaTipoFrequenciaRequest() {
    }

    public DesassociarListaTecnologiaTipoFrequenciaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequencia parametrosDesassociarListaTecnologiaTipoFrequencia) {
           this.parametrosDesassociarListaTecnologiaTipoFrequencia = parametrosDesassociarListaTecnologiaTipoFrequencia;
    }


    /**
     * Gets the parametrosDesassociarListaTecnologiaTipoFrequencia value for this DesassociarListaTecnologiaTipoFrequenciaRequest.
     * 
     * @return parametrosDesassociarListaTecnologiaTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequencia getParametrosDesassociarListaTecnologiaTipoFrequencia() {
        return parametrosDesassociarListaTecnologiaTipoFrequencia;
    }


    /**
     * Sets the parametrosDesassociarListaTecnologiaTipoFrequencia value for this DesassociarListaTecnologiaTipoFrequenciaRequest.
     * 
     * @param parametrosDesassociarListaTecnologiaTipoFrequencia
     */
    public void setParametrosDesassociarListaTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequencia parametrosDesassociarListaTecnologiaTipoFrequencia) {
        this.parametrosDesassociarListaTecnologiaTipoFrequencia = parametrosDesassociarListaTecnologiaTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DesassociarListaTecnologiaTipoFrequenciaRequest)) return false;
        DesassociarListaTecnologiaTipoFrequenciaRequest other = (DesassociarListaTecnologiaTipoFrequenciaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosDesassociarListaTecnologiaTipoFrequencia==null && other.getParametrosDesassociarListaTecnologiaTipoFrequencia()==null) || 
             (this.parametrosDesassociarListaTecnologiaTipoFrequencia!=null &&
              this.parametrosDesassociarListaTecnologiaTipoFrequencia.equals(other.getParametrosDesassociarListaTecnologiaTipoFrequencia())));
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
        if (getParametrosDesassociarListaTecnologiaTipoFrequencia() != null) {
            _hashCode += getParametrosDesassociarListaTecnologiaTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DesassociarListaTecnologiaTipoFrequenciaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">desassociarListaTecnologiaTipoFrequenciaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosDesassociarListaTecnologiaTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ParametrosDesassociarListaTecnologiaTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosDesassociarListaTecnologiaTipoFrequencia"));
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
