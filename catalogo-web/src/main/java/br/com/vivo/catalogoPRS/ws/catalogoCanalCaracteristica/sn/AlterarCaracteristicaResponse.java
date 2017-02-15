/**
 * AlterarCaracteristicaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class AlterarCaracteristicaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoAlterarCaracteristica resultadoAlterarCaracteristica;

    public AlterarCaracteristicaResponse() {
    }

    public AlterarCaracteristicaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoAlterarCaracteristica resultadoAlterarCaracteristica) {
           this.resultadoAlterarCaracteristica = resultadoAlterarCaracteristica;
    }


    /**
     * Gets the resultadoAlterarCaracteristica value for this AlterarCaracteristicaResponse.
     * 
     * @return resultadoAlterarCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoAlterarCaracteristica getResultadoAlterarCaracteristica() {
        return resultadoAlterarCaracteristica;
    }


    /**
     * Sets the resultadoAlterarCaracteristica value for this AlterarCaracteristicaResponse.
     * 
     * @param resultadoAlterarCaracteristica
     */
    public void setResultadoAlterarCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoAlterarCaracteristica resultadoAlterarCaracteristica) {
        this.resultadoAlterarCaracteristica = resultadoAlterarCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarCaracteristicaResponse)) return false;
        AlterarCaracteristicaResponse other = (AlterarCaracteristicaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAlterarCaracteristica==null && other.getResultadoAlterarCaracteristica()==null) || 
             (this.resultadoAlterarCaracteristica!=null &&
              this.resultadoAlterarCaracteristica.equals(other.getResultadoAlterarCaracteristica())));
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
        if (getResultadoAlterarCaracteristica() != null) {
            _hashCode += getResultadoAlterarCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarCaracteristicaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">alterarCaracteristicaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAlterarCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ResultadoAlterarCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ResultadoAlterarCaracteristica"));
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
