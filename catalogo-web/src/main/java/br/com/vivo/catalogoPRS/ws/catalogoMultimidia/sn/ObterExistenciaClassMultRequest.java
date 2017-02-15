/**
 * ObterExistenciaClassMultRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ObterExistenciaClassMultRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamObterExistenciaClassMult paramObterExistenciaClassMult;

    public ObterExistenciaClassMultRequest() {
    }

    public ObterExistenciaClassMultRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamObterExistenciaClassMult paramObterExistenciaClassMult) {
           this.paramObterExistenciaClassMult = paramObterExistenciaClassMult;
    }


    /**
     * Gets the paramObterExistenciaClassMult value for this ObterExistenciaClassMultRequest.
     * 
     * @return paramObterExistenciaClassMult
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamObterExistenciaClassMult getParamObterExistenciaClassMult() {
        return paramObterExistenciaClassMult;
    }


    /**
     * Sets the paramObterExistenciaClassMult value for this ObterExistenciaClassMultRequest.
     * 
     * @param paramObterExistenciaClassMult
     */
    public void setParamObterExistenciaClassMult(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamObterExistenciaClassMult paramObterExistenciaClassMult) {
        this.paramObterExistenciaClassMult = paramObterExistenciaClassMult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObterExistenciaClassMultRequest)) return false;
        ObterExistenciaClassMultRequest other = (ObterExistenciaClassMultRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paramObterExistenciaClassMult==null && other.getParamObterExistenciaClassMult()==null) || 
             (this.paramObterExistenciaClassMult!=null &&
              this.paramObterExistenciaClassMult.equals(other.getParamObterExistenciaClassMult())));
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
        if (getParamObterExistenciaClassMult() != null) {
            _hashCode += getParamObterExistenciaClassMult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObterExistenciaClassMultRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">obterExistenciaClassMultRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramObterExistenciaClassMult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ParamObterExistenciaClassMult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ParamObterExistenciaClassMult"));
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
