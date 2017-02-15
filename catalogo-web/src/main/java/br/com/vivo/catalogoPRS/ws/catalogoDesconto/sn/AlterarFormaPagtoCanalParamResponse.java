/**
 * AlterarFormaPagtoCanalParamResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class AlterarFormaPagtoCanalParamResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoAlterarFormaPagtoCanalParam resultadoAlterarFormaPagtoCanalParam;

    public AlterarFormaPagtoCanalParamResponse() {
    }

    public AlterarFormaPagtoCanalParamResponse(
           br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoAlterarFormaPagtoCanalParam resultadoAlterarFormaPagtoCanalParam) {
           this.resultadoAlterarFormaPagtoCanalParam = resultadoAlterarFormaPagtoCanalParam;
    }


    /**
     * Gets the resultadoAlterarFormaPagtoCanalParam value for this AlterarFormaPagtoCanalParamResponse.
     * 
     * @return resultadoAlterarFormaPagtoCanalParam
     */
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoAlterarFormaPagtoCanalParam getResultadoAlterarFormaPagtoCanalParam() {
        return resultadoAlterarFormaPagtoCanalParam;
    }


    /**
     * Sets the resultadoAlterarFormaPagtoCanalParam value for this AlterarFormaPagtoCanalParamResponse.
     * 
     * @param resultadoAlterarFormaPagtoCanalParam
     */
    public void setResultadoAlterarFormaPagtoCanalParam(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoAlterarFormaPagtoCanalParam resultadoAlterarFormaPagtoCanalParam) {
        this.resultadoAlterarFormaPagtoCanalParam = resultadoAlterarFormaPagtoCanalParam;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarFormaPagtoCanalParamResponse)) return false;
        AlterarFormaPagtoCanalParamResponse other = (AlterarFormaPagtoCanalParamResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAlterarFormaPagtoCanalParam==null && other.getResultadoAlterarFormaPagtoCanalParam()==null) || 
             (this.resultadoAlterarFormaPagtoCanalParam!=null &&
              this.resultadoAlterarFormaPagtoCanalParam.equals(other.getResultadoAlterarFormaPagtoCanalParam())));
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
        if (getResultadoAlterarFormaPagtoCanalParam() != null) {
            _hashCode += getResultadoAlterarFormaPagtoCanalParam().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarFormaPagtoCanalParamResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">alterarFormaPagtoCanalParamResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAlterarFormaPagtoCanalParam");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "ResultadoAlterarFormaPagtoCanalParam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">ResultadoAlterarFormaPagtoCanalParam"));
        elemField.setMinOccurs(0);
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
