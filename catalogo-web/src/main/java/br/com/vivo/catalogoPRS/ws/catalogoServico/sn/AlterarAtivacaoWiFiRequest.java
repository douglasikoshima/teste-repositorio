/**
 * AlterarAtivacaoWiFiRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class AlterarAtivacaoWiFiRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParamAlterarAtivacaoWiFi paramAlterarAtivacaoWiFi;

    public AlterarAtivacaoWiFiRequest() {
    }

    public AlterarAtivacaoWiFiRequest(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParamAlterarAtivacaoWiFi paramAlterarAtivacaoWiFi) {
           this.paramAlterarAtivacaoWiFi = paramAlterarAtivacaoWiFi;
    }


    /**
     * Gets the paramAlterarAtivacaoWiFi value for this AlterarAtivacaoWiFiRequest.
     * 
     * @return paramAlterarAtivacaoWiFi
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParamAlterarAtivacaoWiFi getParamAlterarAtivacaoWiFi() {
        return paramAlterarAtivacaoWiFi;
    }


    /**
     * Sets the paramAlterarAtivacaoWiFi value for this AlterarAtivacaoWiFiRequest.
     * 
     * @param paramAlterarAtivacaoWiFi
     */
    public void setParamAlterarAtivacaoWiFi(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParamAlterarAtivacaoWiFi paramAlterarAtivacaoWiFi) {
        this.paramAlterarAtivacaoWiFi = paramAlterarAtivacaoWiFi;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarAtivacaoWiFiRequest)) return false;
        AlterarAtivacaoWiFiRequest other = (AlterarAtivacaoWiFiRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paramAlterarAtivacaoWiFi==null && other.getParamAlterarAtivacaoWiFi()==null) || 
             (this.paramAlterarAtivacaoWiFi!=null &&
              this.paramAlterarAtivacaoWiFi.equals(other.getParamAlterarAtivacaoWiFi())));
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
        if (getParamAlterarAtivacaoWiFi() != null) {
            _hashCode += getParamAlterarAtivacaoWiFi().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarAtivacaoWiFiRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">alterarAtivacaoWiFiRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramAlterarAtivacaoWiFi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParamAlterarAtivacaoWiFi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParamAlterarAtivacaoWiFi"));
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
