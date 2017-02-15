/**
 * AssociarTecnologiaTipoFrequenciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class AssociarTecnologiaTipoFrequenciaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoAssociarTecnologiaTipoFrequencia resultadoAssociarTecnologiaTipoFrequencia;

    public AssociarTecnologiaTipoFrequenciaResponse() {
    }

    public AssociarTecnologiaTipoFrequenciaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoAssociarTecnologiaTipoFrequencia resultadoAssociarTecnologiaTipoFrequencia) {
           this.resultadoAssociarTecnologiaTipoFrequencia = resultadoAssociarTecnologiaTipoFrequencia;
    }


    /**
     * Gets the resultadoAssociarTecnologiaTipoFrequencia value for this AssociarTecnologiaTipoFrequenciaResponse.
     * 
     * @return resultadoAssociarTecnologiaTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoAssociarTecnologiaTipoFrequencia getResultadoAssociarTecnologiaTipoFrequencia() {
        return resultadoAssociarTecnologiaTipoFrequencia;
    }


    /**
     * Sets the resultadoAssociarTecnologiaTipoFrequencia value for this AssociarTecnologiaTipoFrequenciaResponse.
     * 
     * @param resultadoAssociarTecnologiaTipoFrequencia
     */
    public void setResultadoAssociarTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoAssociarTecnologiaTipoFrequencia resultadoAssociarTecnologiaTipoFrequencia) {
        this.resultadoAssociarTecnologiaTipoFrequencia = resultadoAssociarTecnologiaTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssociarTecnologiaTipoFrequenciaResponse)) return false;
        AssociarTecnologiaTipoFrequenciaResponse other = (AssociarTecnologiaTipoFrequenciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAssociarTecnologiaTipoFrequencia==null && other.getResultadoAssociarTecnologiaTipoFrequencia()==null) || 
             (this.resultadoAssociarTecnologiaTipoFrequencia!=null &&
              this.resultadoAssociarTecnologiaTipoFrequencia.equals(other.getResultadoAssociarTecnologiaTipoFrequencia())));
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
        if (getResultadoAssociarTecnologiaTipoFrequencia() != null) {
            _hashCode += getResultadoAssociarTecnologiaTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssociarTecnologiaTipoFrequenciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">associarTecnologiaTipoFrequenciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAssociarTecnologiaTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ResultadoAssociarTecnologiaTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ResultadoAssociarTecnologiaTipoFrequencia"));
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
