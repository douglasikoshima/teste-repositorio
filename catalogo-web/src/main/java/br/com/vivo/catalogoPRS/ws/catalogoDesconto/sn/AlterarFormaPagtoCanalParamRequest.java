/**
 * AlterarFormaPagtoCanalParamRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class AlterarFormaPagtoCanalParamRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarFormaPagtoCanalParam parametrosAlterarFormaPagtoCanalParam;

    public AlterarFormaPagtoCanalParamRequest() {
    }

    public AlterarFormaPagtoCanalParamRequest(
           br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarFormaPagtoCanalParam parametrosAlterarFormaPagtoCanalParam) {
           this.parametrosAlterarFormaPagtoCanalParam = parametrosAlterarFormaPagtoCanalParam;
    }


    /**
     * Gets the parametrosAlterarFormaPagtoCanalParam value for this AlterarFormaPagtoCanalParamRequest.
     * 
     * @return parametrosAlterarFormaPagtoCanalParam
     */
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarFormaPagtoCanalParam getParametrosAlterarFormaPagtoCanalParam() {
        return parametrosAlterarFormaPagtoCanalParam;
    }


    /**
     * Sets the parametrosAlterarFormaPagtoCanalParam value for this AlterarFormaPagtoCanalParamRequest.
     * 
     * @param parametrosAlterarFormaPagtoCanalParam
     */
    public void setParametrosAlterarFormaPagtoCanalParam(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarFormaPagtoCanalParam parametrosAlterarFormaPagtoCanalParam) {
        this.parametrosAlterarFormaPagtoCanalParam = parametrosAlterarFormaPagtoCanalParam;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarFormaPagtoCanalParamRequest)) return false;
        AlterarFormaPagtoCanalParamRequest other = (AlterarFormaPagtoCanalParamRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarFormaPagtoCanalParam==null && other.getParametrosAlterarFormaPagtoCanalParam()==null) || 
             (this.parametrosAlterarFormaPagtoCanalParam!=null &&
              this.parametrosAlterarFormaPagtoCanalParam.equals(other.getParametrosAlterarFormaPagtoCanalParam())));
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
        if (getParametrosAlterarFormaPagtoCanalParam() != null) {
            _hashCode += getParametrosAlterarFormaPagtoCanalParam().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarFormaPagtoCanalParamRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">alterarFormaPagtoCanalParamRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarFormaPagtoCanalParam");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "ParametrosAlterarFormaPagtoCanalParam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">ParametrosAlterarFormaPagtoCanalParam"));
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
