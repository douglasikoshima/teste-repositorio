/**
 * BuscarListaServicoPorSvTypeCodeRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaServicoPorSvTypeCodeRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoPorTypeCode parametrosBuscarListaServicoPorTypeCode;

    public BuscarListaServicoPorSvTypeCodeRequest() {
    }

    public BuscarListaServicoPorSvTypeCodeRequest(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoPorTypeCode parametrosBuscarListaServicoPorTypeCode) {
           this.parametrosBuscarListaServicoPorTypeCode = parametrosBuscarListaServicoPorTypeCode;
    }


    /**
     * Gets the parametrosBuscarListaServicoPorTypeCode value for this BuscarListaServicoPorSvTypeCodeRequest.
     * 
     * @return parametrosBuscarListaServicoPorTypeCode
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoPorTypeCode getParametrosBuscarListaServicoPorTypeCode() {
        return parametrosBuscarListaServicoPorTypeCode;
    }


    /**
     * Sets the parametrosBuscarListaServicoPorTypeCode value for this BuscarListaServicoPorSvTypeCodeRequest.
     * 
     * @param parametrosBuscarListaServicoPorTypeCode
     */
    public void setParametrosBuscarListaServicoPorTypeCode(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoPorTypeCode parametrosBuscarListaServicoPorTypeCode) {
        this.parametrosBuscarListaServicoPorTypeCode = parametrosBuscarListaServicoPorTypeCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaServicoPorSvTypeCodeRequest)) return false;
        BuscarListaServicoPorSvTypeCodeRequest other = (BuscarListaServicoPorSvTypeCodeRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaServicoPorTypeCode==null && other.getParametrosBuscarListaServicoPorTypeCode()==null) || 
             (this.parametrosBuscarListaServicoPorTypeCode!=null &&
              this.parametrosBuscarListaServicoPorTypeCode.equals(other.getParametrosBuscarListaServicoPorTypeCode())));
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
        if (getParametrosBuscarListaServicoPorTypeCode() != null) {
            _hashCode += getParametrosBuscarListaServicoPorTypeCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaServicoPorSvTypeCodeRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaServicoPorSvTypeCodeRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaServicoPorTypeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosBuscarListaServicoPorTypeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosBuscarListaServicoPorTypeCode"));
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
