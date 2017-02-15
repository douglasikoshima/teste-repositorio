/**
 * AlterarPlanoParametrizacaoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class AlterarPlanoParametrizacaoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoAlterarPlano resultadoAlterarPlano;

    public AlterarPlanoParametrizacaoResponse() {
    }

    public AlterarPlanoParametrizacaoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoAlterarPlano resultadoAlterarPlano) {
           this.resultadoAlterarPlano = resultadoAlterarPlano;
    }


    /**
     * Gets the resultadoAlterarPlano value for this AlterarPlanoParametrizacaoResponse.
     * 
     * @return resultadoAlterarPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoAlterarPlano getResultadoAlterarPlano() {
        return resultadoAlterarPlano;
    }


    /**
     * Sets the resultadoAlterarPlano value for this AlterarPlanoParametrizacaoResponse.
     * 
     * @param resultadoAlterarPlano
     */
    public void setResultadoAlterarPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoAlterarPlano resultadoAlterarPlano) {
        this.resultadoAlterarPlano = resultadoAlterarPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarPlanoParametrizacaoResponse)) return false;
        AlterarPlanoParametrizacaoResponse other = (AlterarPlanoParametrizacaoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAlterarPlano==null && other.getResultadoAlterarPlano()==null) || 
             (this.resultadoAlterarPlano!=null &&
              this.resultadoAlterarPlano.equals(other.getResultadoAlterarPlano())));
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
        if (getResultadoAlterarPlano() != null) {
            _hashCode += getResultadoAlterarPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarPlanoParametrizacaoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">alterarPlanoParametrizacaoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAlterarPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ResultadoAlterarPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoAlterarPlano"));
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
