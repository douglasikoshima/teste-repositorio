/**
 * ObterExistenciaClassMultResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ObterExistenciaClassMultResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultObterExistenciaClassMult resultObterExistenciaClassMult;

    public ObterExistenciaClassMultResponse() {
    }

    public ObterExistenciaClassMultResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultObterExistenciaClassMult resultObterExistenciaClassMult) {
           this.resultObterExistenciaClassMult = resultObterExistenciaClassMult;
    }


    /**
     * Gets the resultObterExistenciaClassMult value for this ObterExistenciaClassMultResponse.
     * 
     * @return resultObterExistenciaClassMult
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultObterExistenciaClassMult getResultObterExistenciaClassMult() {
        return resultObterExistenciaClassMult;
    }


    /**
     * Sets the resultObterExistenciaClassMult value for this ObterExistenciaClassMultResponse.
     * 
     * @param resultObterExistenciaClassMult
     */
    public void setResultObterExistenciaClassMult(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultObterExistenciaClassMult resultObterExistenciaClassMult) {
        this.resultObterExistenciaClassMult = resultObterExistenciaClassMult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObterExistenciaClassMultResponse)) return false;
        ObterExistenciaClassMultResponse other = (ObterExistenciaClassMultResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultObterExistenciaClassMult==null && other.getResultObterExistenciaClassMult()==null) || 
             (this.resultObterExistenciaClassMult!=null &&
              this.resultObterExistenciaClassMult.equals(other.getResultObterExistenciaClassMult())));
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
        if (getResultObterExistenciaClassMult() != null) {
            _hashCode += getResultObterExistenciaClassMult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObterExistenciaClassMultResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">obterExistenciaClassMultResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultObterExistenciaClassMult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ResultObterExistenciaClassMult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ResultObterExistenciaClassMult"));
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
