/**
 * CriarModeloResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class CriarModeloResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoCriarModelo resultadoCriarModelo;

    public CriarModeloResponse() {
    }

    public CriarModeloResponse(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoCriarModelo resultadoCriarModelo) {
           this.resultadoCriarModelo = resultadoCriarModelo;
    }


    /**
     * Gets the resultadoCriarModelo value for this CriarModeloResponse.
     * 
     * @return resultadoCriarModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoCriarModelo getResultadoCriarModelo() {
        return resultadoCriarModelo;
    }


    /**
     * Sets the resultadoCriarModelo value for this CriarModeloResponse.
     * 
     * @param resultadoCriarModelo
     */
    public void setResultadoCriarModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoCriarModelo resultadoCriarModelo) {
        this.resultadoCriarModelo = resultadoCriarModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarModeloResponse)) return false;
        CriarModeloResponse other = (CriarModeloResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoCriarModelo==null && other.getResultadoCriarModelo()==null) || 
             (this.resultadoCriarModelo!=null &&
              this.resultadoCriarModelo.equals(other.getResultadoCriarModelo())));
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
        if (getResultadoCriarModelo() != null) {
            _hashCode += getResultadoCriarModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarModeloResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">criarModeloResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoCriarModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ResultadoCriarModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ResultadoCriarModelo"));
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
