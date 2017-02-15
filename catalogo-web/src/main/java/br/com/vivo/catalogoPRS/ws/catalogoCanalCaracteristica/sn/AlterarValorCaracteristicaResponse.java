/**
 * AlterarValorCaracteristicaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class AlterarValorCaracteristicaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoAlterarValorCaracteristica resultadoAlterarValorCaracteristica;

    public AlterarValorCaracteristicaResponse() {
    }

    public AlterarValorCaracteristicaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoAlterarValorCaracteristica resultadoAlterarValorCaracteristica) {
           this.resultadoAlterarValorCaracteristica = resultadoAlterarValorCaracteristica;
    }


    /**
     * Gets the resultadoAlterarValorCaracteristica value for this AlterarValorCaracteristicaResponse.
     * 
     * @return resultadoAlterarValorCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoAlterarValorCaracteristica getResultadoAlterarValorCaracteristica() {
        return resultadoAlterarValorCaracteristica;
    }


    /**
     * Sets the resultadoAlterarValorCaracteristica value for this AlterarValorCaracteristicaResponse.
     * 
     * @param resultadoAlterarValorCaracteristica
     */
    public void setResultadoAlterarValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoAlterarValorCaracteristica resultadoAlterarValorCaracteristica) {
        this.resultadoAlterarValorCaracteristica = resultadoAlterarValorCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarValorCaracteristicaResponse)) return false;
        AlterarValorCaracteristicaResponse other = (AlterarValorCaracteristicaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAlterarValorCaracteristica==null && other.getResultadoAlterarValorCaracteristica()==null) || 
             (this.resultadoAlterarValorCaracteristica!=null &&
              this.resultadoAlterarValorCaracteristica.equals(other.getResultadoAlterarValorCaracteristica())));
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
        if (getResultadoAlterarValorCaracteristica() != null) {
            _hashCode += getResultadoAlterarValorCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarValorCaracteristicaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">alterarValorCaracteristicaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAlterarValorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ResultadoAlterarValorCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ResultadoAlterarValorCaracteristica"));
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
