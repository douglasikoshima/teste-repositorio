/**
 * CopiarPerfilRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class CopiarPerfilRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosCopiarPerfil parametrosCopiarPerfil;

    public CopiarPerfilRequest() {
    }

    public CopiarPerfilRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosCopiarPerfil parametrosCopiarPerfil) {
           this.parametrosCopiarPerfil = parametrosCopiarPerfil;
    }


    /**
     * Gets the parametrosCopiarPerfil value for this CopiarPerfilRequest.
     * 
     * @return parametrosCopiarPerfil
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosCopiarPerfil getParametrosCopiarPerfil() {
        return parametrosCopiarPerfil;
    }


    /**
     * Sets the parametrosCopiarPerfil value for this CopiarPerfilRequest.
     * 
     * @param parametrosCopiarPerfil
     */
    public void setParametrosCopiarPerfil(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosCopiarPerfil parametrosCopiarPerfil) {
        this.parametrosCopiarPerfil = parametrosCopiarPerfil;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CopiarPerfilRequest)) return false;
        CopiarPerfilRequest other = (CopiarPerfilRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosCopiarPerfil==null && other.getParametrosCopiarPerfil()==null) || 
             (this.parametrosCopiarPerfil!=null &&
              this.parametrosCopiarPerfil.equals(other.getParametrosCopiarPerfil())));
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
        if (getParametrosCopiarPerfil() != null) {
            _hashCode += getParametrosCopiarPerfil().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CopiarPerfilRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">copiarPerfilRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosCopiarPerfil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosCopiarPerfil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosCopiarPerfil"));
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
