/**
 * BuscarModeloPorTecnologiaTpFrequenciaVlResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarModeloPorTecnologiaTpFrequenciaVlResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl resultadoBuscarModeloPorTecnologiaTpFrequenciaVl;

    public BuscarModeloPorTecnologiaTpFrequenciaVlResponse() {
    }

    public BuscarModeloPorTecnologiaTpFrequenciaVlResponse(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl resultadoBuscarModeloPorTecnologiaTpFrequenciaVl) {
           this.resultadoBuscarModeloPorTecnologiaTpFrequenciaVl = resultadoBuscarModeloPorTecnologiaTpFrequenciaVl;
    }


    /**
     * Gets the resultadoBuscarModeloPorTecnologiaTpFrequenciaVl value for this BuscarModeloPorTecnologiaTpFrequenciaVlResponse.
     * 
     * @return resultadoBuscarModeloPorTecnologiaTpFrequenciaVl
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl getResultadoBuscarModeloPorTecnologiaTpFrequenciaVl() {
        return resultadoBuscarModeloPorTecnologiaTpFrequenciaVl;
    }


    /**
     * Sets the resultadoBuscarModeloPorTecnologiaTpFrequenciaVl value for this BuscarModeloPorTecnologiaTpFrequenciaVlResponse.
     * 
     * @param resultadoBuscarModeloPorTecnologiaTpFrequenciaVl
     */
    public void setResultadoBuscarModeloPorTecnologiaTpFrequenciaVl(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl resultadoBuscarModeloPorTecnologiaTpFrequenciaVl) {
        this.resultadoBuscarModeloPorTecnologiaTpFrequenciaVl = resultadoBuscarModeloPorTecnologiaTpFrequenciaVl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarModeloPorTecnologiaTpFrequenciaVlResponse)) return false;
        BuscarModeloPorTecnologiaTpFrequenciaVlResponse other = (BuscarModeloPorTecnologiaTpFrequenciaVlResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarModeloPorTecnologiaTpFrequenciaVl==null && other.getResultadoBuscarModeloPorTecnologiaTpFrequenciaVl()==null) || 
             (this.resultadoBuscarModeloPorTecnologiaTpFrequenciaVl!=null &&
              this.resultadoBuscarModeloPorTecnologiaTpFrequenciaVl.equals(other.getResultadoBuscarModeloPorTecnologiaTpFrequenciaVl())));
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
        if (getResultadoBuscarModeloPorTecnologiaTpFrequenciaVl() != null) {
            _hashCode += getResultadoBuscarModeloPorTecnologiaTpFrequenciaVl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarModeloPorTecnologiaTpFrequenciaVlResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarModeloPorTecnologiaTpFrequenciaVlResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarModeloPorTecnologiaTpFrequenciaVl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl"));
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
