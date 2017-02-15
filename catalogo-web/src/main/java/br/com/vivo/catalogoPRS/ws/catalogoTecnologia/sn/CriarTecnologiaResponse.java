/**
 * CriarTecnologiaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class CriarTecnologiaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoCriarTecnologia resultadoCriarTecnologia;

    public CriarTecnologiaResponse() {
    }

    public CriarTecnologiaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoCriarTecnologia resultadoCriarTecnologia) {
           this.resultadoCriarTecnologia = resultadoCriarTecnologia;
    }


    /**
     * Gets the resultadoCriarTecnologia value for this CriarTecnologiaResponse.
     * 
     * @return resultadoCriarTecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoCriarTecnologia getResultadoCriarTecnologia() {
        return resultadoCriarTecnologia;
    }


    /**
     * Sets the resultadoCriarTecnologia value for this CriarTecnologiaResponse.
     * 
     * @param resultadoCriarTecnologia
     */
    public void setResultadoCriarTecnologia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoCriarTecnologia resultadoCriarTecnologia) {
        this.resultadoCriarTecnologia = resultadoCriarTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarTecnologiaResponse)) return false;
        CriarTecnologiaResponse other = (CriarTecnologiaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoCriarTecnologia==null && other.getResultadoCriarTecnologia()==null) || 
             (this.resultadoCriarTecnologia!=null &&
              this.resultadoCriarTecnologia.equals(other.getResultadoCriarTecnologia())));
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
        if (getResultadoCriarTecnologia() != null) {
            _hashCode += getResultadoCriarTecnologia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarTecnologiaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">criarTecnologiaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoCriarTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ResultadoCriarTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ResultadoCriarTecnologia"));
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
