/**
 * BuscarGrupoProdutoPorIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarGrupoProdutoPorIdResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProduto[] resultadoModeloPorId;

    public BuscarGrupoProdutoPorIdResponse() {
    }

    public BuscarGrupoProdutoPorIdResponse(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProduto[] resultadoModeloPorId) {
           this.resultadoModeloPorId = resultadoModeloPorId;
    }


    /**
     * Gets the resultadoModeloPorId value for this BuscarGrupoProdutoPorIdResponse.
     * 
     * @return resultadoModeloPorId
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProduto[] getResultadoModeloPorId() {
        return resultadoModeloPorId;
    }


    /**
     * Sets the resultadoModeloPorId value for this BuscarGrupoProdutoPorIdResponse.
     * 
     * @param resultadoModeloPorId
     */
    public void setResultadoModeloPorId(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoModeloPorIdGrupoProduto[] resultadoModeloPorId) {
        this.resultadoModeloPorId = resultadoModeloPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarGrupoProdutoPorIdResponse)) return false;
        BuscarGrupoProdutoPorIdResponse other = (BuscarGrupoProdutoPorIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoModeloPorId==null && other.getResultadoModeloPorId()==null) || 
             (this.resultadoModeloPorId!=null &&
              java.util.Arrays.equals(this.resultadoModeloPorId, other.getResultadoModeloPorId())));
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
        if (getResultadoModeloPorId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoModeloPorId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoModeloPorId(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarGrupoProdutoPorIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarGrupoProdutoPorIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoModeloPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ResultadoModeloPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ResultadoModeloPorId"));
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
