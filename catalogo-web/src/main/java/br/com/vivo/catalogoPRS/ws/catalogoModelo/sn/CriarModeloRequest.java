/**
 * CriarModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class CriarModeloRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosCriarModelo parametrosCriarModelo;

    public CriarModeloRequest() {
    }

    public CriarModeloRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosCriarModelo parametrosCriarModelo) {
           this.parametrosCriarModelo = parametrosCriarModelo;
    }


    /**
     * Gets the parametrosCriarModelo value for this CriarModeloRequest.
     * 
     * @return parametrosCriarModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosCriarModelo getParametrosCriarModelo() {
        return parametrosCriarModelo;
    }


    /**
     * Sets the parametrosCriarModelo value for this CriarModeloRequest.
     * 
     * @param parametrosCriarModelo
     */
    public void setParametrosCriarModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosCriarModelo parametrosCriarModelo) {
        this.parametrosCriarModelo = parametrosCriarModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarModeloRequest)) return false;
        CriarModeloRequest other = (CriarModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosCriarModelo==null && other.getParametrosCriarModelo()==null) || 
             (this.parametrosCriarModelo!=null &&
              this.parametrosCriarModelo.equals(other.getParametrosCriarModelo())));
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
        if (getParametrosCriarModelo() != null) {
            _hashCode += getParametrosCriarModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">criarModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosCriarModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosCriarModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosCriarModelo"));
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
