/**
 * BuscarAssociacaoTecnologiaTpFrequenciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class BuscarAssociacaoTecnologiaTpFrequenciaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarAssociacaoTecnologiaTpFrequencia resultadoBuscarAssociacaoTecnologiaTpFrequencia;

    public BuscarAssociacaoTecnologiaTpFrequenciaResponse() {
    }

    public BuscarAssociacaoTecnologiaTpFrequenciaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarAssociacaoTecnologiaTpFrequencia resultadoBuscarAssociacaoTecnologiaTpFrequencia) {
           this.resultadoBuscarAssociacaoTecnologiaTpFrequencia = resultadoBuscarAssociacaoTecnologiaTpFrequencia;
    }


    /**
     * Gets the resultadoBuscarAssociacaoTecnologiaTpFrequencia value for this BuscarAssociacaoTecnologiaTpFrequenciaResponse.
     * 
     * @return resultadoBuscarAssociacaoTecnologiaTpFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarAssociacaoTecnologiaTpFrequencia getResultadoBuscarAssociacaoTecnologiaTpFrequencia() {
        return resultadoBuscarAssociacaoTecnologiaTpFrequencia;
    }


    /**
     * Sets the resultadoBuscarAssociacaoTecnologiaTpFrequencia value for this BuscarAssociacaoTecnologiaTpFrequenciaResponse.
     * 
     * @param resultadoBuscarAssociacaoTecnologiaTpFrequencia
     */
    public void setResultadoBuscarAssociacaoTecnologiaTpFrequencia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarAssociacaoTecnologiaTpFrequencia resultadoBuscarAssociacaoTecnologiaTpFrequencia) {
        this.resultadoBuscarAssociacaoTecnologiaTpFrequencia = resultadoBuscarAssociacaoTecnologiaTpFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarAssociacaoTecnologiaTpFrequenciaResponse)) return false;
        BuscarAssociacaoTecnologiaTpFrequenciaResponse other = (BuscarAssociacaoTecnologiaTpFrequenciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarAssociacaoTecnologiaTpFrequencia==null && other.getResultadoBuscarAssociacaoTecnologiaTpFrequencia()==null) || 
             (this.resultadoBuscarAssociacaoTecnologiaTpFrequencia!=null &&
              this.resultadoBuscarAssociacaoTecnologiaTpFrequencia.equals(other.getResultadoBuscarAssociacaoTecnologiaTpFrequencia())));
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
        if (getResultadoBuscarAssociacaoTecnologiaTpFrequencia() != null) {
            _hashCode += getResultadoBuscarAssociacaoTecnologiaTpFrequencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarAssociacaoTecnologiaTpFrequenciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">buscarAssociacaoTecnologiaTpFrequenciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarAssociacaoTecnologiaTpFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ResultadoBuscarAssociacaoTecnologiaTpFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ResultadoBuscarAssociacaoTecnologiaTpFrequencia"));
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
