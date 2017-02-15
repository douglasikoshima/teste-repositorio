/**
 * BuscarDescricaoPlanoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarDescricaoPlanoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarDescricaoPlano parametrosBuscarDescricaoPlano;

    public BuscarDescricaoPlanoRequest() {
    }

    public BuscarDescricaoPlanoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarDescricaoPlano parametrosBuscarDescricaoPlano) {
           this.parametrosBuscarDescricaoPlano = parametrosBuscarDescricaoPlano;
    }


    /**
     * Gets the parametrosBuscarDescricaoPlano value for this BuscarDescricaoPlanoRequest.
     * 
     * @return parametrosBuscarDescricaoPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarDescricaoPlano getParametrosBuscarDescricaoPlano() {
        return parametrosBuscarDescricaoPlano;
    }


    /**
     * Sets the parametrosBuscarDescricaoPlano value for this BuscarDescricaoPlanoRequest.
     * 
     * @param parametrosBuscarDescricaoPlano
     */
    public void setParametrosBuscarDescricaoPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarDescricaoPlano parametrosBuscarDescricaoPlano) {
        this.parametrosBuscarDescricaoPlano = parametrosBuscarDescricaoPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarDescricaoPlanoRequest)) return false;
        BuscarDescricaoPlanoRequest other = (BuscarDescricaoPlanoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarDescricaoPlano==null && other.getParametrosBuscarDescricaoPlano()==null) || 
             (this.parametrosBuscarDescricaoPlano!=null &&
              this.parametrosBuscarDescricaoPlano.equals(other.getParametrosBuscarDescricaoPlano())));
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
        if (getParametrosBuscarDescricaoPlano() != null) {
            _hashCode += getParametrosBuscarDescricaoPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarDescricaoPlanoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarDescricaoPlanoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarDescricaoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ParametrosBuscarDescricaoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParametrosBuscarDescricaoPlano"));
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
