/**
 * BuscarListaServicoPorSvTypeCodeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaServicoPorSvTypeCodeResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPorSvTypeCode resultadoBuscarListaServicoPorSvTypeCode;

    public BuscarListaServicoPorSvTypeCodeResponse() {
    }

    public BuscarListaServicoPorSvTypeCodeResponse(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPorSvTypeCode resultadoBuscarListaServicoPorSvTypeCode) {
           this.resultadoBuscarListaServicoPorSvTypeCode = resultadoBuscarListaServicoPorSvTypeCode;
    }


    /**
     * Gets the resultadoBuscarListaServicoPorSvTypeCode value for this BuscarListaServicoPorSvTypeCodeResponse.
     * 
     * @return resultadoBuscarListaServicoPorSvTypeCode
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPorSvTypeCode getResultadoBuscarListaServicoPorSvTypeCode() {
        return resultadoBuscarListaServicoPorSvTypeCode;
    }


    /**
     * Sets the resultadoBuscarListaServicoPorSvTypeCode value for this BuscarListaServicoPorSvTypeCodeResponse.
     * 
     * @param resultadoBuscarListaServicoPorSvTypeCode
     */
    public void setResultadoBuscarListaServicoPorSvTypeCode(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPorSvTypeCode resultadoBuscarListaServicoPorSvTypeCode) {
        this.resultadoBuscarListaServicoPorSvTypeCode = resultadoBuscarListaServicoPorSvTypeCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaServicoPorSvTypeCodeResponse)) return false;
        BuscarListaServicoPorSvTypeCodeResponse other = (BuscarListaServicoPorSvTypeCodeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaServicoPorSvTypeCode==null && other.getResultadoBuscarListaServicoPorSvTypeCode()==null) || 
             (this.resultadoBuscarListaServicoPorSvTypeCode!=null &&
              this.resultadoBuscarListaServicoPorSvTypeCode.equals(other.getResultadoBuscarListaServicoPorSvTypeCode())));
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
        if (getResultadoBuscarListaServicoPorSvTypeCode() != null) {
            _hashCode += getResultadoBuscarListaServicoPorSvTypeCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaServicoPorSvTypeCodeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaServicoPorSvTypeCodeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaServicoPorSvTypeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ResultadoBuscarListaServicoPorSvTypeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaServicoPorSvTypeCode"));
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
