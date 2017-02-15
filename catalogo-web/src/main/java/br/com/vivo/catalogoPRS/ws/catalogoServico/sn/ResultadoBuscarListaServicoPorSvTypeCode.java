/**
 * ResultadoBuscarListaServicoPorSvTypeCode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaServicoPorSvTypeCode  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode[] listaServicoPorSvTypeCode;

    public ResultadoBuscarListaServicoPorSvTypeCode() {
    }

    public ResultadoBuscarListaServicoPorSvTypeCode(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode[] listaServicoPorSvTypeCode) {
           this.listaServicoPorSvTypeCode = listaServicoPorSvTypeCode;
    }


    /**
     * Gets the listaServicoPorSvTypeCode value for this ResultadoBuscarListaServicoPorSvTypeCode.
     * 
     * @return listaServicoPorSvTypeCode
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode[] getListaServicoPorSvTypeCode() {
        return listaServicoPorSvTypeCode;
    }


    /**
     * Sets the listaServicoPorSvTypeCode value for this ResultadoBuscarListaServicoPorSvTypeCode.
     * 
     * @param listaServicoPorSvTypeCode
     */
    public void setListaServicoPorSvTypeCode(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPorSvTypeCodeListaServicoPorSvTypeCodeServicoPorSvTypeCode[] listaServicoPorSvTypeCode) {
        this.listaServicoPorSvTypeCode = listaServicoPorSvTypeCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaServicoPorSvTypeCode)) return false;
        ResultadoBuscarListaServicoPorSvTypeCode other = (ResultadoBuscarListaServicoPorSvTypeCode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaServicoPorSvTypeCode==null && other.getListaServicoPorSvTypeCode()==null) || 
             (this.listaServicoPorSvTypeCode!=null &&
              java.util.Arrays.equals(this.listaServicoPorSvTypeCode, other.getListaServicoPorSvTypeCode())));
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
        if (getListaServicoPorSvTypeCode() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaServicoPorSvTypeCode());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaServicoPorSvTypeCode(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaServicoPorSvTypeCode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaServicoPorSvTypeCode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaServicoPorSvTypeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaServicoPorSvTypeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaServicoPorSvTypeCode>ListaServicoPorSvTypeCode>ServicoPorSvTypeCode"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ServicoPorSvTypeCode"));
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
