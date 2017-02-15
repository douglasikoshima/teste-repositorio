/**
 * CriarCabecalhoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class CriarCabecalhoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoCriarCabecalho resultadoCriarCabecalho;

    public CriarCabecalhoResponse() {
    }

    public CriarCabecalhoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoCriarCabecalho resultadoCriarCabecalho) {
           this.resultadoCriarCabecalho = resultadoCriarCabecalho;
    }


    /**
     * Gets the resultadoCriarCabecalho value for this CriarCabecalhoResponse.
     * 
     * @return resultadoCriarCabecalho
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoCriarCabecalho getResultadoCriarCabecalho() {
        return resultadoCriarCabecalho;
    }


    /**
     * Sets the resultadoCriarCabecalho value for this CriarCabecalhoResponse.
     * 
     * @param resultadoCriarCabecalho
     */
    public void setResultadoCriarCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoCriarCabecalho resultadoCriarCabecalho) {
        this.resultadoCriarCabecalho = resultadoCriarCabecalho;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarCabecalhoResponse)) return false;
        CriarCabecalhoResponse other = (CriarCabecalhoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoCriarCabecalho==null && other.getResultadoCriarCabecalho()==null) || 
             (this.resultadoCriarCabecalho!=null &&
              this.resultadoCriarCabecalho.equals(other.getResultadoCriarCabecalho())));
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
        if (getResultadoCriarCabecalho() != null) {
            _hashCode += getResultadoCriarCabecalho().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarCabecalhoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">criarCabecalhoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoCriarCabecalho");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ResultadoCriarCabecalho"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoCriarCabecalho"));
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
