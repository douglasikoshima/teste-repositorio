/**
 * AlterarModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class AlterarModeloRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarModelo parametrosAlterarModelo;

    public AlterarModeloRequest() {
    }

    public AlterarModeloRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarModelo parametrosAlterarModelo) {
           this.parametrosAlterarModelo = parametrosAlterarModelo;
    }


    /**
     * Gets the parametrosAlterarModelo value for this AlterarModeloRequest.
     * 
     * @return parametrosAlterarModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarModelo getParametrosAlterarModelo() {
        return parametrosAlterarModelo;
    }


    /**
     * Sets the parametrosAlterarModelo value for this AlterarModeloRequest.
     * 
     * @param parametrosAlterarModelo
     */
    public void setParametrosAlterarModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarModelo parametrosAlterarModelo) {
        this.parametrosAlterarModelo = parametrosAlterarModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarModeloRequest)) return false;
        AlterarModeloRequest other = (AlterarModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarModelo==null && other.getParametrosAlterarModelo()==null) || 
             (this.parametrosAlterarModelo!=null &&
              this.parametrosAlterarModelo.equals(other.getParametrosAlterarModelo())));
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
        if (getParametrosAlterarModelo() != null) {
            _hashCode += getParametrosAlterarModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">alterarModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosAlterarModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosAlterarModelo"));
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
