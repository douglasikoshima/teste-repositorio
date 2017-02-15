/**
 * AssociarTecnologiaTipoFrequenciaValorRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class AssociarTecnologiaTipoFrequenciaValorRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaValor parametrosAssociarTecnologiaTipoFrequenciaValor;

    public AssociarTecnologiaTipoFrequenciaValorRequest() {
    }

    public AssociarTecnologiaTipoFrequenciaValorRequest(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaValor parametrosAssociarTecnologiaTipoFrequenciaValor) {
           this.parametrosAssociarTecnologiaTipoFrequenciaValor = parametrosAssociarTecnologiaTipoFrequenciaValor;
    }


    /**
     * Gets the parametrosAssociarTecnologiaTipoFrequenciaValor value for this AssociarTecnologiaTipoFrequenciaValorRequest.
     * 
     * @return parametrosAssociarTecnologiaTipoFrequenciaValor
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaValor getParametrosAssociarTecnologiaTipoFrequenciaValor() {
        return parametrosAssociarTecnologiaTipoFrequenciaValor;
    }


    /**
     * Sets the parametrosAssociarTecnologiaTipoFrequenciaValor value for this AssociarTecnologiaTipoFrequenciaValorRequest.
     * 
     * @param parametrosAssociarTecnologiaTipoFrequenciaValor
     */
    public void setParametrosAssociarTecnologiaTipoFrequenciaValor(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaValor parametrosAssociarTecnologiaTipoFrequenciaValor) {
        this.parametrosAssociarTecnologiaTipoFrequenciaValor = parametrosAssociarTecnologiaTipoFrequenciaValor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssociarTecnologiaTipoFrequenciaValorRequest)) return false;
        AssociarTecnologiaTipoFrequenciaValorRequest other = (AssociarTecnologiaTipoFrequenciaValorRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAssociarTecnologiaTipoFrequenciaValor==null && other.getParametrosAssociarTecnologiaTipoFrequenciaValor()==null) || 
             (this.parametrosAssociarTecnologiaTipoFrequenciaValor!=null &&
              this.parametrosAssociarTecnologiaTipoFrequenciaValor.equals(other.getParametrosAssociarTecnologiaTipoFrequenciaValor())));
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
        if (getParametrosAssociarTecnologiaTipoFrequenciaValor() != null) {
            _hashCode += getParametrosAssociarTecnologiaTipoFrequenciaValor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssociarTecnologiaTipoFrequenciaValorRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">associarTecnologiaTipoFrequenciaValorRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAssociarTecnologiaTipoFrequenciaValor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ParametrosAssociarTecnologiaTipoFrequenciaValor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosAssociarTecnologiaTipoFrequenciaValor"));
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
