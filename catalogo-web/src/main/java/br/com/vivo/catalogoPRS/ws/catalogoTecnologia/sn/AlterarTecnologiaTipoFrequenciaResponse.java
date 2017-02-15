/**
 * AlterarTecnologiaTipoFrequenciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class AlterarTecnologiaTipoFrequenciaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoAlterarTecnologiaTipoFrequencia resultadoAlterarTecnologiaTipoFrequencia;

    public AlterarTecnologiaTipoFrequenciaResponse() {
    }

    public AlterarTecnologiaTipoFrequenciaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoAlterarTecnologiaTipoFrequencia resultadoAlterarTecnologiaTipoFrequencia) {
           this.resultadoAlterarTecnologiaTipoFrequencia = resultadoAlterarTecnologiaTipoFrequencia;
    }


    /**
     * Gets the resultadoAlterarTecnologiaTipoFrequencia value for this AlterarTecnologiaTipoFrequenciaResponse.
     * 
     * @return resultadoAlterarTecnologiaTipoFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoAlterarTecnologiaTipoFrequencia getResultadoAlterarTecnologiaTipoFrequencia() {
        return resultadoAlterarTecnologiaTipoFrequencia;
    }


    /**
     * Sets the resultadoAlterarTecnologiaTipoFrequencia value for this AlterarTecnologiaTipoFrequenciaResponse.
     * 
     * @param resultadoAlterarTecnologiaTipoFrequencia
     */
    public void setResultadoAlterarTecnologiaTipoFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoAlterarTecnologiaTipoFrequencia resultadoAlterarTecnologiaTipoFrequencia) {
        this.resultadoAlterarTecnologiaTipoFrequencia = resultadoAlterarTecnologiaTipoFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarTecnologiaTipoFrequenciaResponse)) return false;
        AlterarTecnologiaTipoFrequenciaResponse other = (AlterarTecnologiaTipoFrequenciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAlterarTecnologiaTipoFrequencia==null && other.getResultadoAlterarTecnologiaTipoFrequencia()==null) || 
             (this.resultadoAlterarTecnologiaTipoFrequencia!=null &&
              this.resultadoAlterarTecnologiaTipoFrequencia.equals(other.getResultadoAlterarTecnologiaTipoFrequencia())));
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
        if (getResultadoAlterarTecnologiaTipoFrequencia() != null) {
            _hashCode += getResultadoAlterarTecnologiaTipoFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarTecnologiaTipoFrequenciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">alterarTecnologiaTipoFrequenciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAlterarTecnologiaTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ResultadoAlterarTecnologiaTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ResultadoAlterarTecnologiaTipoFrequencia"));
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
