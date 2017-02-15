/**
 * AssociarTecnologiaTipoFrequenciaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class AssociarTecnologiaTipoFrequenciaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequencia parametrosAssociarTecnologiaTipoFrequencia;

    public AssociarTecnologiaTipoFrequenciaRequest() {
    }

    public AssociarTecnologiaTipoFrequenciaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequencia parametrosAssociarTecnologiaTipoFrequencia) {
           this.parametrosAssociarTecnologiaTipoFrequencia = parametrosAssociarTecnologiaTipoFrequencia;
    }


    /**
     * Gets the parametrosAssociarTecnologiaTipoFrequencia value for this AssociarTecnologiaTipoFrequenciaRequest.
     * 
     * @return parametrosAssociarTecnologiaTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequencia getParametrosAssociarTecnologiaTipoFrequencia() {
        return parametrosAssociarTecnologiaTipoFrequencia;
    }


    /**
     * Sets the parametrosAssociarTecnologiaTipoFrequencia value for this AssociarTecnologiaTipoFrequenciaRequest.
     * 
     * @param parametrosAssociarTecnologiaTipoFrequencia
     */
    public void setParametrosAssociarTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequencia parametrosAssociarTecnologiaTipoFrequencia) {
        this.parametrosAssociarTecnologiaTipoFrequencia = parametrosAssociarTecnologiaTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssociarTecnologiaTipoFrequenciaRequest)) return false;
        AssociarTecnologiaTipoFrequenciaRequest other = (AssociarTecnologiaTipoFrequenciaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAssociarTecnologiaTipoFrequencia==null && other.getParametrosAssociarTecnologiaTipoFrequencia()==null) || 
             (this.parametrosAssociarTecnologiaTipoFrequencia!=null &&
              this.parametrosAssociarTecnologiaTipoFrequencia.equals(other.getParametrosAssociarTecnologiaTipoFrequencia())));
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
        if (getParametrosAssociarTecnologiaTipoFrequencia() != null) {
            _hashCode += getParametrosAssociarTecnologiaTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssociarTecnologiaTipoFrequenciaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">associarTecnologiaTipoFrequenciaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAssociarTecnologiaTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ParametrosAssociarTecnologiaTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosAssociarTecnologiaTipoFrequencia"));
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
