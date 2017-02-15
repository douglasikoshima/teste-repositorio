/**
 * BuscarListaTipoPlanoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class BuscarListaTipoPlanoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaTipoPlano parametrosBuscarListaTipoPlano;

    public BuscarListaTipoPlanoRequest() {
    }

    public BuscarListaTipoPlanoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaTipoPlano parametrosBuscarListaTipoPlano) {
           this.parametrosBuscarListaTipoPlano = parametrosBuscarListaTipoPlano;
    }


    /**
     * Gets the parametrosBuscarListaTipoPlano value for this BuscarListaTipoPlanoRequest.
     * 
     * @return parametrosBuscarListaTipoPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaTipoPlano getParametrosBuscarListaTipoPlano() {
        return parametrosBuscarListaTipoPlano;
    }


    /**
     * Sets the parametrosBuscarListaTipoPlano value for this BuscarListaTipoPlanoRequest.
     * 
     * @param parametrosBuscarListaTipoPlano
     */
    public void setParametrosBuscarListaTipoPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaTipoPlano parametrosBuscarListaTipoPlano) {
        this.parametrosBuscarListaTipoPlano = parametrosBuscarListaTipoPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaTipoPlanoRequest)) return false;
        BuscarListaTipoPlanoRequest other = (BuscarListaTipoPlanoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaTipoPlano==null && other.getParametrosBuscarListaTipoPlano()==null) || 
             (this.parametrosBuscarListaTipoPlano!=null &&
              this.parametrosBuscarListaTipoPlano.equals(other.getParametrosBuscarListaTipoPlano())));
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
        if (getParametrosBuscarListaTipoPlano() != null) {
            _hashCode += getParametrosBuscarListaTipoPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaTipoPlanoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">buscarListaTipoPlanoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaTipoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ParametrosBuscarListaTipoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParametrosBuscarListaTipoPlano"));
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
