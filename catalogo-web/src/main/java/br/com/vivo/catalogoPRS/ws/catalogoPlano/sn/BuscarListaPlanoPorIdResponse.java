/**
 * BuscarListaPlanoPorIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarListaPlanoPorIdResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorId resultadoBuscarListaPlanoPorId;

    public BuscarListaPlanoPorIdResponse() {
    }

    public BuscarListaPlanoPorIdResponse(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorId resultadoBuscarListaPlanoPorId) {
           this.resultadoBuscarListaPlanoPorId = resultadoBuscarListaPlanoPorId;
    }


    /**
     * Gets the resultadoBuscarListaPlanoPorId value for this BuscarListaPlanoPorIdResponse.
     * 
     * @return resultadoBuscarListaPlanoPorId
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorId getResultadoBuscarListaPlanoPorId() {
        return resultadoBuscarListaPlanoPorId;
    }


    /**
     * Sets the resultadoBuscarListaPlanoPorId value for this BuscarListaPlanoPorIdResponse.
     * 
     * @param resultadoBuscarListaPlanoPorId
     */
    public void setResultadoBuscarListaPlanoPorId(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorId resultadoBuscarListaPlanoPorId) {
        this.resultadoBuscarListaPlanoPorId = resultadoBuscarListaPlanoPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPlanoPorIdResponse)) return false;
        BuscarListaPlanoPorIdResponse other = (BuscarListaPlanoPorIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaPlanoPorId==null && other.getResultadoBuscarListaPlanoPorId()==null) || 
             (this.resultadoBuscarListaPlanoPorId!=null &&
              this.resultadoBuscarListaPlanoPorId.equals(other.getResultadoBuscarListaPlanoPorId())));
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
        if (getResultadoBuscarListaPlanoPorId() != null) {
            _hashCode += getResultadoBuscarListaPlanoPorId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaPlanoPorIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarListaPlanoPorIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaPlanoPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ResultadoBuscarListaPlanoPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoBuscarListaPlanoPorId"));
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
