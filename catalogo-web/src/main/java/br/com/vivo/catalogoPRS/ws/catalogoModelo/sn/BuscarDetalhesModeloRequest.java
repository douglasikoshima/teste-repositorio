/**
 * BuscarDetalhesModeloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarDetalhesModeloRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarDetalhesModelo parametrosBuscarDetalhesModelo;

    public BuscarDetalhesModeloRequest() {
    }

    public BuscarDetalhesModeloRequest(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarDetalhesModelo parametrosBuscarDetalhesModelo) {
           this.parametrosBuscarDetalhesModelo = parametrosBuscarDetalhesModelo;
    }


    /**
     * Gets the parametrosBuscarDetalhesModelo value for this BuscarDetalhesModeloRequest.
     * 
     * @return parametrosBuscarDetalhesModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarDetalhesModelo getParametrosBuscarDetalhesModelo() {
        return parametrosBuscarDetalhesModelo;
    }


    /**
     * Sets the parametrosBuscarDetalhesModelo value for this BuscarDetalhesModeloRequest.
     * 
     * @param parametrosBuscarDetalhesModelo
     */
    public void setParametrosBuscarDetalhesModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarDetalhesModelo parametrosBuscarDetalhesModelo) {
        this.parametrosBuscarDetalhesModelo = parametrosBuscarDetalhesModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarDetalhesModeloRequest)) return false;
        BuscarDetalhesModeloRequest other = (BuscarDetalhesModeloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarDetalhesModelo==null && other.getParametrosBuscarDetalhesModelo()==null) || 
             (this.parametrosBuscarDetalhesModelo!=null &&
              this.parametrosBuscarDetalhesModelo.equals(other.getParametrosBuscarDetalhesModelo())));
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
        if (getParametrosBuscarDetalhesModelo() != null) {
            _hashCode += getParametrosBuscarDetalhesModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarDetalhesModeloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarDetalhesModeloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarDetalhesModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosBuscarDetalhesModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarDetalhesModelo"));
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
