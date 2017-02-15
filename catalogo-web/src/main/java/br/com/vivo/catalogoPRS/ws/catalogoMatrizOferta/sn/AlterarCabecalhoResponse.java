/**
 * AlterarCabecalhoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class AlterarCabecalhoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoAlterarCabecalho resultadoAlterarCabecalho;

    public AlterarCabecalhoResponse() {
    }

    public AlterarCabecalhoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoAlterarCabecalho resultadoAlterarCabecalho) {
           this.resultadoAlterarCabecalho = resultadoAlterarCabecalho;
    }


    /**
     * Gets the resultadoAlterarCabecalho value for this AlterarCabecalhoResponse.
     * 
     * @return resultadoAlterarCabecalho
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoAlterarCabecalho getResultadoAlterarCabecalho() {
        return resultadoAlterarCabecalho;
    }


    /**
     * Sets the resultadoAlterarCabecalho value for this AlterarCabecalhoResponse.
     * 
     * @param resultadoAlterarCabecalho
     */
    public void setResultadoAlterarCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoAlterarCabecalho resultadoAlterarCabecalho) {
        this.resultadoAlterarCabecalho = resultadoAlterarCabecalho;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarCabecalhoResponse)) return false;
        AlterarCabecalhoResponse other = (AlterarCabecalhoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAlterarCabecalho==null && other.getResultadoAlterarCabecalho()==null) || 
             (this.resultadoAlterarCabecalho!=null &&
              this.resultadoAlterarCabecalho.equals(other.getResultadoAlterarCabecalho())));
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
        if (getResultadoAlterarCabecalho() != null) {
            _hashCode += getResultadoAlterarCabecalho().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarCabecalhoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarCabecalhoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAlterarCabecalho");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ResultadoAlterarCabecalho"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoAlterarCabecalho"));
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
