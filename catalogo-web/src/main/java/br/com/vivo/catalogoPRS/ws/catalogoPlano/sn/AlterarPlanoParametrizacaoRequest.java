/**
 * AlterarPlanoParametrizacaoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class AlterarPlanoParametrizacaoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlano parametroAlterarPlano;

    public AlterarPlanoParametrizacaoRequest() {
    }

    public AlterarPlanoParametrizacaoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlano parametroAlterarPlano) {
           this.parametroAlterarPlano = parametroAlterarPlano;
    }


    /**
     * Gets the parametroAlterarPlano value for this AlterarPlanoParametrizacaoRequest.
     * 
     * @return parametroAlterarPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlano getParametroAlterarPlano() {
        return parametroAlterarPlano;
    }


    /**
     * Sets the parametroAlterarPlano value for this AlterarPlanoParametrizacaoRequest.
     * 
     * @param parametroAlterarPlano
     */
    public void setParametroAlterarPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlano parametroAlterarPlano) {
        this.parametroAlterarPlano = parametroAlterarPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarPlanoParametrizacaoRequest)) return false;
        AlterarPlanoParametrizacaoRequest other = (AlterarPlanoParametrizacaoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametroAlterarPlano==null && other.getParametroAlterarPlano()==null) || 
             (this.parametroAlterarPlano!=null &&
              this.parametroAlterarPlano.equals(other.getParametroAlterarPlano())));
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
        if (getParametroAlterarPlano() != null) {
            _hashCode += getParametroAlterarPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarPlanoParametrizacaoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">alterarPlanoParametrizacaoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametroAlterarPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ParametroAlterarPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParametroAlterarPlano"));
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
